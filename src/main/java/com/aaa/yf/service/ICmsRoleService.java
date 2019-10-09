package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsRole;

public interface ICmsRoleService {

	public List<CmsRole> findAllRole(Map condition,String sortName,String sortType,Integer page,Integer rows);
	public void doAddRole(CmsRole role);
	public void doUpdateRole(CmsRole role);
	public CmsRole findRoleById(Integer id);
	public boolean doDeleteRole(String ids);
	public void doGrantRole(String ids,Integer id);
}
