package cloud.yiyefu.mybatis;

import cloud.yiyefu.mybatis.db.entry.Database;
import cloud.yiyefu.mybatis.db.sql.MySql;
import cloud.yiyefu.mybatis.db.sql.Sql;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.DialogWrapper;

import com.intellij.openapi.ui.Messages;
import com.intellij.ui.table.JBTable;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class CodeDialog extends DialogWrapper {
    private String url;
    private Integer port;
    private String databaseName;
    private String tableName;
    private String userName;
    private String password;
    //0 mysql,1: mssql;2:oracle;3:pg
    private String type;
    private String train;
    private String action;
    private String packageName;
    private  String path;
    private Project project;
    protected CodeDialog(@Nullable Project project, boolean canBeParent, String title) {
        super(project, canBeParent);
        this.project=project;
        init();
        setTitle(title);
    }
    private JPanel center = new JPanel();
    private JPanel south = new JPanel();
    {
        setSize(400, 320);
    }

    private final JLabel typeLabel = new JLabel("type");
    private final ComboBox<String> typeBox = new ComboBox<>();
    private final JLabel urlLabel = new JLabel("address");
    private final JTextField urlField = new JTextField("127.0.0.1");
    private final JLabel portLabel = new JLabel("port");
    private final JTextField portField = new JTextField("3306");
    private final JLabel userNameLabel = new JLabel("user name");
    private final JTextField userNameField = new JTextField("root");
    private final JLabel passwordLabel = new JLabel("password");
    private final JTextField passwordField = new JTextField();
    private final JLabel dbLabel = new JLabel("database");
    private final JTextField dbField = new JTextField("wangjing");
    private final JLabel tableLabel = new JLabel("table");
    private final JTextField tableField = new JTextField("flow");
    private final JLabel trainLabel = new JLabel("transaction");
    private final ComboBox<String> trainBox = new ComboBox<>();
    private final JLabel packageLabel=new JLabel("package");

    private final JTextField packageField=new JTextField("cloud.yiyefu");
    private final JLabel pathLabel=new JLabel("controller path");
    private final JTextField pathField=new JTextField("/test");
    private Connection connection;
    @Override
    protected @Nullable JComponent createCenterPanel() {

        GridLayout gridLayout = new GridLayout(20, 1);
        center.setLayout(gridLayout);
       // typeLabel.setPreferredSize(new Dimension(100,30));
        center.add(typeLabel);

        typeBox.addItem("mysql");
        typeBox.addItem("oracle");
        typeBox.addItem("sql server");
        typeBox.addItem("pg");
       // typeBox.setPreferredSize(new Dimension(300,30));
        center.add(typeBox);
        center.add(urlLabel);
        center.add(urlField);
        center.add(portLabel);
        center.add(portField);
        center.add(userNameLabel);
        center.add(userNameField);
        center.add(passwordLabel);
        center.add(passwordField);
        center.add(dbLabel);
        center.add(dbField);
        center.add(tableLabel);
        center.add(tableField);
        center.add(trainLabel);
        trainBox.addItem("yes");
        trainBox.addItem("no");
        center.add(trainBox);
        center.add(packageLabel);
        center.add(packageField);
        center.add(pathLabel);
        center.add(pathField);
        return center;
    }
    @Override
    protected JComponent createSouthPanel() {
        CodeDialog codeDialog=this;
        JButton test = new JButton("test");
        test.setHorizontalAlignment(SwingConstants.CENTER);
        test.setVerticalAlignment(SwingConstants.CENTER);
        GridLayout gridLayout = new GridLayout(1, 2);
        south.setLayout(gridLayout);
        south.add(test);
        test.addActionListener(e -> {
            if(this.test("test")){
                Messages.showMessageDialog("success!","test connect",null);
            }
        });
        JButton code = new JButton("code");
        code.setHorizontalAlignment(SwingConstants.CENTER);
        code.setVerticalAlignment(SwingConstants.CENTER);
        south.add(code);
        code.addActionListener(e -> {
            if(this.test("code")){
                Code code1 = new Code(codeDialog);
                code1.generator();
                dispose();
            }

        });
        return south;
    }
    private boolean test(String action){
        this.type= (String) this.typeBox.getSelectedItem();
        if(StringUtils.isEmpty(this.type)){
            message("type can not be null!");
            return false;
        }
        this.url=this.urlField.getText();
        if(StringUtils.isEmpty(this.url)){
            message("address can not be null!");
            return false;
        }
        if(StringUtils.isEmpty(this.portField.getText())){
            message("port can not be null!");
            return false;
        }
        this.port= Integer.valueOf(this.portField.getText());
        this.databaseName=this.dbField.getText();
        if(StringUtils.isEmpty(this.getDatabaseName())){
            message("database can not be null!");
            return false;
        }

        this.tableName=this.tableField.getText();
        this.train= (String) this.trainBox.getSelectedItem();
        this.userName=this.userNameField.getText();
        if(StringUtils.isEmpty(this.userName)){
            message("username can not be null!");
            return false;
        }
        this.password=this.passwordField.getText();
        if(StringUtils.isEmpty(this.password)){
            message("password can not be null!");
            return false;
        }
        this.action=action;
        Database database=new Database();
        database.setUrl(this.url);
        database.setPort(this.port);
        database.setUsername(this.userName);
        database.setPassword(this.password);
        database.setName(this.databaseName);
        Sql sql;
        switch (this.type){
            case "mysql":
                sql=new MySql();
            default:
                sql=new MySql();
        }

        try {
            connection=sql.getConnection(database);
            System.out.println(connection);
            if(connection==null){
                message("can not connect database!");
                return false;
            }

        } catch (SQLException | ClassNotFoundException  e) {
            message("can not connect database!");
            e.printStackTrace();
            return false;

        } catch (Exception e){

            message("can not connect database!");
            e.printStackTrace();
            return false;
        }
        if(connection==null){
            message("can not connect database!");
            return false;
        }
        if("code".equals(action)){
            this.packageName=this.packageField.getText();
            if(StringUtils.isEmpty(packageName)){
                message("packageName can not be null!");
                return false;
            }
            this.path=this.pathField.getText();
            if(StringUtils.isEmpty(this.path)){
                message("controller path can not be null!");
                return false;
            }
        }
        return true;
    }
    private void message(String message){
        Messages.showErrorDialog(message,"error");

    }
    public String getTrain() {
        return train;
    }
    public String getAction() {
        return action;
    }
    public String getUrl() {
        return url;
    }

    public Integer getPort() {
        return port;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getTableName() {
        return tableName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Connection getConnection() {
        return connection;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "CodeDialog{" +
                "url='" + url + '\'' +
                ", port=" + port +
                ", databaseName='" + databaseName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                ", train=" + train +
                '}';
    }

}
