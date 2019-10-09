package com.aaa.yf.util;

public class JsonFile {
	
	private String text;
	private long length;
	private String path;
	private String _parentId;
	private String updatetime;
	private String state;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	public JsonFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JsonFile(String text, long length, String path) {
		super();
		this.text = text;
		this.length = length;
		this.path = path;
	}
	public String get_parentId() {
		return _parentId;
	}
	public void set_parentId(String parentId) {
		_parentId = parentId;
	}
//	public JsonFile(String text, long length, String path, String parentId) {
//		super();
//		this.text = text;
//		this.length = length;
//		this.path = path;
//		_parentId = parentId;
//	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public JsonFile(String text, long length, String path, String parentId,
			String updatetime) {
		super();
		this.text = text;
		this.length = length;
		this.path = path;
		_parentId = parentId;
		this.updatetime = updatetime;
	}
	public JsonFile(String text, long length, String path, String updatetime) {
		super();
		this.text = text;
		this.length = length;
		this.path = path;
		this.updatetime = updatetime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
