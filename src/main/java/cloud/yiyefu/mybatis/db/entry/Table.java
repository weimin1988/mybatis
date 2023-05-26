package cloud.yiyefu.mybatis.db.entry;

public class Table {
	private String name;
	private String pre;
	private String suff;
	private String beanName;
	private String objectName;
	private String key;
	public String getName() {
		return name;
	}
	
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getPre() {
		return pre;
	}
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getSuff() {
		return suff;
	}
	public void setSuff(String suff) {
		this.suff = suff;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
}
