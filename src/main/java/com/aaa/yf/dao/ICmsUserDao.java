package com.aaa.yf.dao;

import java.util.List;

import com.aaa.yf.entity.CmsUser;

public interface ICmsUserDao extends IBaseDao<CmsUser> {

	public List<CmsUser> login(CmsUser user);
	public List<CmsUser> findUserByName(String uname); 
	public void doDisableUser(String ids);
	public void doUsableUser(String ids);
	public List<CmsUser> findUserByRole(String ids); //删除角色时，判断是否关联着其他用户
	public List<CmsUser> frontLogin(CmsUser user);//前台登陆
	
	public void deleteUser(String ids);
	
	public List<Object[]> registerCountChart(String way,String year);
}
