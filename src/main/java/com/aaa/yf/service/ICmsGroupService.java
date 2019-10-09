package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsGroup;

public interface ICmsGroupService {

	public List<CmsGroup> findAllGroup(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	
	public void doAddGroup(CmsGroup group);
	
	public void doUpdateGroup(CmsGroup group);
	
	public void doDeleteGroup(CmsGroup group);
	
}
