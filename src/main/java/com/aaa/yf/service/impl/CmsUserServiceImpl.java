package com.aaa.yf.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsUserDao;
import com.aaa.yf.entity.CmsUser;
import com.aaa.yf.service.ICmsUserService;

@Service("cuservice")
public class CmsUserServiceImpl implements ICmsUserService {
	@Autowired
	private ICmsUserDao cudao;

	public ICmsUserDao getCudao() {
		return cudao;
	}

	public void setCudao(ICmsUserDao cudao) {
		this.cudao = cudao;
	}

	public List<CmsUser> login(CmsUser user) {
		List<CmsUser> list = cudao.login(user);
		
		if(list != null){
			if(list.size() > 0){
				for (CmsUser cmsUser : list) {
					cmsUser.getCmsRole().getRoleName();
					cmsUser.getCmsRole().getCmsPowers().size();
					cmsUser.setLastLoginTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
					try {
						cmsUser.setLastLoginIp(InetAddress.getLocalHost().getHostAddress());
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					cmsUser.setLoginCount(cmsUser.getLoginCount()+1);
				}
				return list;
			}
		}
		
		return null;
	}

	public List<CmsUser> findByPage(Map condition,String sortName,String sortType,Integer page,Integer rows) {
		
		List<CmsUser> list = cudao.findInfo(condition, sortName, sortType, page, rows);
		if(list != null){
			if(list.size() > 0){
				for (CmsUser cmsUser : list) {
					if(cmsUser.getCmsRole() != null){
						cmsUser.getCmsRole().getRoleName();
					}
					cmsUser.getCmsGroup().getGroupName();
				}
				return list;
			}
		}
		return list;
	}

	public long findCount(Map condition) {
		return cudao.findCount(condition);
	}

	public void doAddUser(CmsUser user) {
		cudao.add(user);
	}

	public CmsUser findUserById(Integer id) {
		return cudao.findById(id);
	}

	public void doUpdateUser(CmsUser user) {
		cudao.update(user);
	}

	public boolean findUserByName(String uname) {
		List<CmsUser> list = cudao.findUserByName(uname);
		if(list != null){
			if(list.size()>=1){
				return false;
			}
		}
		
		return true;
		
	}

	public void doDisableUser(String ids) {
		cudao.doDisableUser(ids);
	}

	public void doUsableUser(String ids) {
		cudao.doUsableUser(ids);
	}

	public List<CmsUser> frontLogin(CmsUser user) {
		List<CmsUser> list = cudao.frontLogin(user);
		if(list != null){
			if(list.size() > 0){
				for (CmsUser cmsUser : list) {
					cmsUser.getCmsGroup().getGroupName();
					cmsUser.setLastLoginTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
					try {
						cmsUser.setLastLoginIp(InetAddress.getLocalHost().getHostAddress());
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
					cmsUser.setLoginCount(cmsUser.getLoginCount()+1);
				}
				return list;
			}
		}
		
		return null;
	}

	public void doDeleteUser(String ids) {
		cudao.deleteUser(ids);
	}

	public List<Object[]> registerCountChart(String way,String year) {
		return cudao.registerCountChart(way,year);
	}
	
	
}
