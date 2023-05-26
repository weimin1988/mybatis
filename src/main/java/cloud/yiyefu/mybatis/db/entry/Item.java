package cloud.yiyefu.mybatis.db.entry;

public class Item {
	private String name;
	private String field;
	//private String defaultValue;
	private String label;
	private String type;
	private Integer size;
	private boolean nullable;
	private Integer key;
	private String sqlType;
	private String tableItemName;
	private boolean pk;

	public String getTableItemName() {
		return tableItemName;
	}

	public void setTableItemName(String tableItemName) {
		this.tableItemName = tableItemName;
	}

	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}


	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public boolean isPk() {
		return pk;
	}

	public void setPk(boolean pk) {
		this.pk = pk;
	}

	@Override
	public String toString() {
		return "Item{" +
				"name='" + name + '\'' +
				", field='" + field + '\'' +
				", label='" + label + '\'' +
				", type='" + type + '\'' +
				", size=" + size +
				", nullable=" + nullable +
				", key=" + key +
				", sqlType='" + sqlType + '\'' +
				", tableItemName='" + tableItemName + '\'' +
				", pk=" + pk +
				'}';
	}
}
