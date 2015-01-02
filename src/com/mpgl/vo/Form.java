package com.mpgl.vo;

/**
 * 查询表单Vo
 * 
 * @author user
 * 
 */
public class Form {

	private String id;

	/**
	 * 用户ID
	 */
	private String user_id;

	/**
	 * 用户实际姓名
	 */
	private String name;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 角色ID
	 */
	private String role_id;

	/**
	 * 账户是否锁定
	 */
	private String isLock;

	/**
	 * 字典表KEY
	 */
	private String dictionaryKey;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 城市
	 */
	private String city;

	/**
	 * 电话号码
	 */
	private String phone;

	private String title;

	private String url;
	/**
	 * 页码
	 */
	private int pageIndex;

	/**
	 * 页码尺寸
	 */
	private int pageSize;

	private String comboboxKey;

	private String comboboxValue;

	private String comboboxTable;

	private String comboboxWhere;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole_id() {
		return role_id;
	}

	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getDictionaryKey() {
		return dictionaryKey;
	}

	public void setDictionaryKey(String dictionaryKey) {
		this.dictionaryKey = dictionaryKey;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComboboxKey() {
		return comboboxKey;
	}

	public void setComboboxKey(String comboboxKey) {
		this.comboboxKey = comboboxKey;
	}

	public String getComboboxValue() {
		return comboboxValue;
	}

	public void setComboboxValue(String comboboxValue) {
		this.comboboxValue = comboboxValue;
	}

	public String getComboboxTable() {
		return comboboxTable;
	}

	public void setComboboxTable(String comboboxTable) {
		this.comboboxTable = comboboxTable;
	}

	public String getComboboxWhere() {
		return comboboxWhere;
	}

	public void setComboboxWhere(String comboboxWhere) {
		this.comboboxWhere = comboboxWhere;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


}
