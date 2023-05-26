package cloud.yiyefu.mybatis;


import cloud.yiyefu.mybatis.db.entry.Database;
import cloud.yiyefu.mybatis.db.entry.Entry;
import cloud.yiyefu.mybatis.db.entry.Item;
import cloud.yiyefu.mybatis.db.sql.MySql;
import cloud.yiyefu.mybatis.db.sql.Sql;
import cn.hutool.core.text.NamingCase;
import com.intellij.openapi.project.Project;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


import java.io.*;
import java.sql.Connection;
import java.util.List;

public class Code {
    CodeDialog codeDialog;

    public Code(CodeDialog codeDialog) {
        this.codeDialog = codeDialog;
    }
    public void generator(){
        String tableName=codeDialog.getTableName();
        String beanName= NamingCase.toCamelCase(tableName);
        String className=NamingCase.toPascalCase(tableName);
        Connection conn=codeDialog.getConnection();
        if(conn==null){
            return;
        }
        Database database=new Database();
        database.setUrl(codeDialog.getUrl());
        database.setPort(codeDialog.getPort());
        database.setUsername(codeDialog.getUserName());
        database.setPassword(codeDialog.getPassword());
        database.setName(codeDialog.getDatabaseName());
        Sql sql;
        switch (codeDialog.getType()){
            case "mysql":
                sql=new MySql();
            default:
                sql=new MySql();
        }
        List<Item> items=sql.listItem(database, codeDialog.getTableName());
        Entry entry=new Entry();
        entry.setPackageName(codeDialog.getPackageName());
        entry.setFields(items);
        entry.setPath(codeDialog.getPath());
        entry.setClassName(className);
        entry.setTableName(tableName);
        entry.setName(beanName);
        Project project=codeDialog.getProject();
        String dir=project.getBasePath();
        Configuration configuration = configure();
        createEntry(configuration,entry,dir);
        createMapper(configuration,entry,dir);
        createService(configuration,entry,dir);
        createServiceImpl(configuration,entry,dir);
        createMapperXml(configuration,entry,dir);
        createController(configuration,entry,dir);
        //Code.class.getClassLoader().getResource("")


    }
    private void createEntry(Configuration configuration,Entry entry,String dir){
        //InputStream inputStream = Code.class.getClassLoader().getResourceAsStream("template/entry.ftl");


        try {
            Template template = configuration.getTemplate("entry.ftl");
            String entryDir=dir+"/src/main/java/"+entry.getPackageName().replace(".","/")+"/entry";
            File javaDir=new File(entryDir);
            if(!javaDir.exists()){
                javaDir.mkdirs();
            }
            Writer out =new FileWriter(new File(entryDir+"/"+entry.getClassName()+".java"));
            template.process(entry,out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    private void createMapper(Configuration configuration,Entry entry,String dir){
        //InputStream inputStream = Code.class.getClassLoader().getResourceAsStream("template/entry.ftl");


        try {
            Template template = configuration.getTemplate("mapper.ftl");
            String entryDir=dir+"/src/main/java/"+entry.getPackageName().replace(".","/")+"/mapper";
            File javaDir=new File(entryDir);
            if(!javaDir.exists()){
                javaDir.mkdirs();
            }
            Writer out =new FileWriter(new File(entryDir+"/"+entry.getClassName()+"Mapper.java"));
            template.process(entry,out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    private void createService(Configuration configuration,Entry entry,String dir){
        //InputStream inputStream = Code.class.getClassLoader().getResourceAsStream("template/entry.ftl");


        try {
            Template template = configuration.getTemplate("service.ftl");
            String entryDir=dir+"/src/main/java/"+entry.getPackageName().replace(".","/")+"/service";
            File javaDir=new File(entryDir);
            if(!javaDir.exists()){
                javaDir.mkdirs();
            }
            Writer out =new FileWriter(new File(entryDir+"/"+entry.getClassName()+"Service.java"));
            template.process(entry,out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    private void createController(Configuration configuration,Entry entry,String dir){
        //InputStream inputStream = Code.class.getClassLoader().getResourceAsStream("template/entry.ftl");


        try {
            Template template = configuration.getTemplate("controller.ftl");
            String entryDir=dir+"/src/main/java/"+entry.getPackageName().replace(".","/")+"/controller";
            File javaDir=new File(entryDir);
            if(!javaDir.exists()){
                javaDir.mkdirs();
            }
            Writer out =new FileWriter(new File(entryDir+"/"+entry.getClassName()+"Controller.java"));
            template.process(entry,out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    private void createServiceImpl(Configuration configuration,Entry entry,String dir){
        //InputStream inputStream = Code.class.getClassLoader().getResourceAsStream("template/entry.ftl");


        try {
            Template template = configuration.getTemplate("serviceImpl.ftl");
            String entryDir=dir+"/src/main/java/"+entry.getPackageName().replace(".","/")+"/service/impl";
            File javaDir=new File(entryDir);
            if(!javaDir.exists()){
                javaDir.mkdirs();
            }
            Writer out =new FileWriter(new File(entryDir+"/"+entry.getClassName()+"ServiceImpl.java"));
            template.process(entry,out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    private void createMapperXml(Configuration configuration,Entry entry,String dir){
        //InputStream inputStream = Code.class.getClassLoader().getResourceAsStream("template/entry.ftl");


        try {
            Template template = configuration.getTemplate("xml/mapper.ftl");
            String entryDir=dir+"/src/main/resources/mapper";
            File javaDir=new File(entryDir);
            if(!javaDir.exists()){
                javaDir.mkdirs();
            }
            Writer out =new FileWriter(new File(entryDir+"/"+entry.getClassName()+"Mapper.xml"));
            template.process(entry,out);
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
    private Configuration configure(){
        Configuration configuration=new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassLoaderForTemplateLoading(Code.class.getClassLoader(),"template");
        return configuration;
    }
}
