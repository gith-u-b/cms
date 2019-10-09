package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsUser;

public interface ICmsUserService {

	public List<CmsUser> login(CmsUser user);
	public List<CmsUser> findByPage(Map condition,String sortName,String sortType,Integer page,Integer rows);
	public long findCount(Map condition);
	public void doAddUser(CmsUser user);
	public CmsUser findUserById(Integer id);
	public void doUpdateUser(CmsUser user);
	public boolean findUserByName(String uname);
	public void doDisableUser(String ids);
	public void doUsableUser(String ids);
	public List<CmsUser> frontLogin(CmsUser user);
	
	
	public void doDeleteUser(String ids); //删除会员
	
	public List<Object[]> registerCountChart(String way,String year);  //注册量报表
	
}
