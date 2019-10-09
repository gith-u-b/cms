package com.aaa.yf.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.aaa.yf.dao.ICmsUserDao;
import com.aaa.yf.entity.CmsUser;
@Repository("cudao")
public class CmsUserDaoImpl extends BaseDaoImpl<CmsUser> implements ICmsUserDao {

	
	private String entityName = "com.aaa.yf.entity.CmsUser"; 
	public List<CmsUser> login(CmsUser user) {
		return this.findByHql("select u from CmsUser u where   u.userName = ? and u.userPassword = ? and u.isDisabled = 1 and u.isAdmin = 1", user.getUserName(),user.getUserPassword());
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public List<CmsUser> findUserByName(String uname) {
		return this.findByHql("select u from CmsUser u where u.userName = ?", uname);
	}
	public void doDisableUser(String ids) {
		this.executeHql("update CmsUser u set u.isDisabled = 0 where u.userId in ("+ids+")");
	}
	public void doUsableUser(String ids) {
		this.executeHql("update CmsUser u set u.isDisabled = 1 where u.userId in ("+ids+")");		
	}
	public List<CmsUser> findUserByRole(String ids) {
		return this.findByHql("select u from CmsUser u where u.cmsRole.roleId in ("+ids+")");
	}
	public List<CmsUser> frontLogin(CmsUser user) {
		return this.findByHql("select u from CmsUser u where u.userName = ? and u.userPassword = ? and u.isDisabled = 1 ", user.getUserName(),user.getUserPassword());
	}
	public void deleteUser(String ids) {
		this.executeHql("delete CmsUser u where u.userId in ("+ids+")");
	}
	public List<Object[]> registerCountChart(String way,String year) {
		List<Object[]> list = new ArrayList<Object[]>();
		if(year.equals("1")){	  //则是按年查找
			list = this.findObjectByHql("select YEAR(u.registerTime) ,count(u)   from CmsUser u where  u.isAdmin = 0  group by YEAR(u.registerTime)");
		}
		if(!year.equals("1") && way.equals("m")){
			list = this.findObjectByHql("select MONTH(u.registerTime) ,count(u)   from CmsUser u where u.isAdmin = 0 and YEAR(u.registerTime) = '"+year+"' group by YEAR(u.registerTime) ");
		}
		if(!year.equals("1") && way.equals("w")){
			list = this.findObjectByHql("select WEEKDAY(u.registerTime) ,count(u)   from CmsUser u where u.isAdmin = 0 and YEAR(u.registerTime) = '"+year+"' group by YEAR(u.registerTime) ");
		}
		if(!year.equals("1") && way.equals("d")){
			list = this.findObjectByHql("select DAYOFMONTH(u.registerTime) ,count(u)   from CmsUser u where u.isAdmin = 0 and YEAR(u.registerTime) = '"+year+"' group by YEAR(u.registerTime) ");
		}
		
		return list;
	}
	
}
