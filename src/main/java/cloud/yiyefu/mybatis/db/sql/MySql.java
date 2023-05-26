package cloud.yiyefu.mybatis.db.sql;

import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cloud.yiyefu.mybatis.db.entry.Database;


import cloud.yiyefu.mybatis.db.entry.Item;
import cn.hutool.core.util.StrUtil;
import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import com.intellij.collaboration.ui.codereview.timeline.comment.CommentInputComponentFactory;


public class MySql implements Sql {

	@Override
	public Connection getConnection(Database database) throws SQLException,
			ClassNotFoundException{
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动类
			
			
			String DB_URL="jdbc:mysql://"+database.getUrl()+":"+database.getPort()+"/information_schema?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
			try {
				conn = DriverManager.getConnection(DB_URL,database.getUsername(),database.getPassword());
			}catch (Exception e){
				e.printStackTrace();
			}

			//建立连接
			System.out.println(conn);//打印对象，看是否建立成功

			
		} catch (ClassNotFoundException e) {

			print(e.getMessage());
			e.printStackTrace();

		}catch (Exception e){
			e.printStackTrace();
		}
		System.out.println(conn);
		return conn;
	}
	@Override
	public List<Item> listItem(Database database, String tableName) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Item> list=new ArrayList<Item>();
		try {
	        
			conn = getConnection(database);
			String query = "select * from COLUMNS where table_schema = '"+database.getName()+"' and table_name = '"+tableName+"'";
			
			st = conn.createStatement();
			
			rs = st.executeQuery(query);
			
			while (rs.next()) {
				Item item=new Item();
				
				String name=rs.getString("COLUMN_NAME");
				item.setTableItemName(name);
				item.setField(name);
				item.setName(StrUtil.toCamelCase(name));
				//.replace(" ", "_").replace("-", "_")
				//item.setName(converterDown.convert(name.replace(" ", "_").replace("-", "_")));
				String type=rs.getString("DATA_TYPE");
				item.setSqlType(type);
				String javaType = "";
				if (type.equals("int") || type.equals("bigint")||type.endsWith("integer")) {

					javaType = "Integer";
				} else if (type.equals("char") || type.equals("varchar")
						|| type.equals("nvarchar") || type.equals("nchar")
						|| type.equals("text") || type.equals("ntext")) {
					javaType = "String";
				} else if (type.equals("decimal")) {
					javaType = "BigDecimal";
				} else if (type.equals("float")) {
					javaType = "Float";
				} else if (type.indexOf("date") != -1
						|| type.indexOf("time") != -1) {
					javaType = "Date";
				}
				if (javaType.equals("")) {
					javaType = "String";
				}
				item.setType(javaType);

				item.setLabel(rs.getString("COLUMN_COMMENT"));
				String key=rs.getString("COLUMN_KEY");
				if(key.equals("PRI")){
					item.setPk(true);
					item.setKey(1);
				}else{
					item.setPk(false);
					item.setKey(0);
				}
				
			
				item.setSize(rs.getInt("CHARACTER_MAXIMUM_LENGTH"));
				
				item.setNullable(rs.getString("IS_NULLABLE").equals("NO"));

				print(item.toString());
				list.add(item);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block

			print(e.getMessage());
			e.printStackTrace();
		} finally {
			close(conn, st, rs);
		}
		return list;
	}
	public void print(String message) {
		System.out.println(message);
	}
	@Override
	public void close(Connection con, Statement sm, ResultSet rs) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (sm != null) {
			try {
				sm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		Database db=new Database();	
		String dbName = "test";
		String url = "localhost";
		String account = "root";
		String password = "1q2w3e4r!W";
		db.setName(dbName);
		db.setPassword(password);
		db.setUsername(account);
		db.setUrl(url);
		db.setPort(3306);
		MySql sql=new MySql();
		try {
			Connection conn = sql.getConnection(db);
			System.out.println(sql.listItem(db, "user").toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
