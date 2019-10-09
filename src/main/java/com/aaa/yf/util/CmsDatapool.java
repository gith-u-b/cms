package com.aaa.yf.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CmsDatapool implements Serializable {
	private String dataBaseName;//数据库名
	private String tableName;	//数据库表名
	private String columName;	//字段名
	private String columType;	//字段类型
	private String columAttr;	//字段属性
	private String columDefaultValue;//字段默认值
	private String characterSetName;	 //字段附加属性
	private JsonFile jsonFile;
	
	public CmsDatapool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getColumName() {
		return columName;
	}
	public void setColumName(String columName) {
		this.columName = columName;
	}
	public String getColumType() {
		return columType;
	}
	public void setColumType(String columType) {
		this.columType = columType;
	}
	public String getColumAttr() {
		return columAttr;
	}
	public void setColumAttr(String columAttr) {
		this.columAttr = columAttr;
	}
	public String getColumDefaultValue() {
		return columDefaultValue;
	}
	public void setColumDefaultValue(String columDefaultValue) {
		this.columDefaultValue = columDefaultValue;
	}
	public String getCharacterSetName() {
		return characterSetName;
	}
	public void setCharacterSetName(String characterSetName) {
		this.characterSetName = characterSetName;
	}
	public JsonFile getJsonFile() {
		return jsonFile;
	}
	public void setJsonFile(JsonFile jsonFile) {
		this.jsonFile = jsonFile;
	}
}
