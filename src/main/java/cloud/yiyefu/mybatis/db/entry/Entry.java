package cloud.yiyefu.mybatis.db.entry;

import java.util.List;

public class Entry {
    String tableName;
    String className;
    String name;

    String packageName;
    String path;
    List<Item> fields;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Item> getFields() {
        return fields;
    }

    public void setFields(List<Item> fields) {
        this.fields = fields;
    }
}
