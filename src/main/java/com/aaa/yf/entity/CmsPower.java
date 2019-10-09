package com.aaa.yf.entity;

import java.util.HashSet;
import java.util.Set;



/**
 * CmsPower entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class CmsPower implements java.io.Serializable {

	// Fields

	private Integer id;
	private String text;
	private String url;
	private Integer _parentId;
	private String state;
	private String iconCls;
	private boolean checked;
	private Set<CmsRole> cmsRoles = new HashSet<CmsRole>();

	// Constructors

	/** default constructor */
	public CmsPower() {
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


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	


	public Integer get_parentId() {
		return _parentId;
	}


	public void set_parentId(Integer _parentId) {
		this._parentId = _parentId;
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


	public Set<CmsRole> getCmsRoles() {
		return cmsRoles;
	}


	public void setCmsRoles(Set<CmsRole> cmsRoles) {
		this.cmsRoles = cmsRoles;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CmsPower other = (CmsPower) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	public boolean isChecked() {
		return checked;
	}


	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}