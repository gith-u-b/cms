package com.aaa.yf.util;

import java.util.List;


public class toJsonUtil {

	private List rows;
	private long total;

	
	public toJsonUtil() {
		super();
	}
	public toJsonUtil(List rows, long total) {
		super();
		this.rows = rows;
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	
	
	
}
