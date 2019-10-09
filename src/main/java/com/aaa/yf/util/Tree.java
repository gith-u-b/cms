package com.aaa.yf.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tree {

	private Integer id;
	private String text;
	@SuppressWarnings("rawtypes")
	private Map attributes  =new HashMap();
	private String state;
	private String iconCls;
	private Boolean checked;
	private String url;
	private Integer pid;
	private Integer _parentId;
	private Tree[] children;
	
	
	
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@SuppressWarnings("rawtypes")
	public Map getAttributes() {
		return attributes;
	}
	@SuppressWarnings("rawtypes")
	public void setAttributes(Map attributes) {
		this.attributes = attributes;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Tree[] getChildren() {
		return children;
	}
	public void setChildren(Tree[] children) {
		this.children = children;
	}
	public Tree(Integer id, String text, String state, String iconCls) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.iconCls = iconCls;
	}
	public Tree() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Tree>findTree(List<Tree> list){
		List<Tree>ptrees=new ArrayList<Tree>();
		for (Tree tree : list) {
			if(tree.getAttributes().get("pid").toString().equals("0")){
				ptrees.add(tree);
			}
		}
		for (int i=0;i<ptrees.size();i++) {
			recursion(ptrees.get(i), list);
		}
		return ptrees;
		
	}
	public void recursion(Tree t,List<Tree>list){
		if("closed".equals(t.getState())){
			List<Tree>ts=new ArrayList<Tree>();
			for (int i=0;i<list.size();i++) {
				Tree tree=list.get(i);
				if(tree.getAttributes().get("pid").toString().equals(t.getId()+"")){
					ts.add(tree);
					recursion(list.get(i),list);
				}
			}
			t.setChildren(formate(ts));
		}
	}
	public Tree[]formate(List<Tree>ts){
		Tree[]children=new Tree[ts.size()];
		for (int i=0;i<ts.size();i++) {
			children[i]=ts.get(i);
		}
		return children;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer get_parentId() {
		return _parentId;
	}
	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
	}
	public Tree(Integer id, String text, String url, String state,
			String iconCls, Integer pid, Integer _parentId) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.state = state;
		this.iconCls = iconCls;
		this.pid = pid;
		this._parentId = _parentId;
	}
}
