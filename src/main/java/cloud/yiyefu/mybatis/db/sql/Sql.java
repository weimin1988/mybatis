package cloud.yiyefu.mybatis.db.sql;

import cloud.yiyefu.mybatis.db.entry.Database;
import cloud.yiyefu.mybatis.db.entry.Item;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


public interface Sql {
	public  Connection getConnection(Database database) throws SQLException, ClassNotFoundException;

	public List<Item> listItem(Database database, String tableName) ;
	public void close(Connection con, Statement sm, ResultSet rs);

}
