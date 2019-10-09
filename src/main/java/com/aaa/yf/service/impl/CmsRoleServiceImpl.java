package com.aaa.yf.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsRoleDao;
import com.aaa.yf.dao.ICmsUserDao;
import com.aaa.yf.entity.CmsPower;
import com.aaa.yf.entity.CmsRole;
import com.aaa.yf.service.ICmsRoleService;

@Service("crservice")
public class CmsRoleServiceImpl implements ICmsRoleService {

	@Autowired
	private ICmsRoleDao crdao;
	@Autowired
	private ICmsUserDao cudao;

	public ICmsRoleDao getCrdao() {
		return crdao;
	}

	public void setCrdao(ICmsRoleDao crdao) {
		this.crdao = crdao;
	}

	public List<CmsRole> findAllRole(Map condition,String sortName,String sortType,Integer page,Integer rows) {
		return crdao.findInfo(condition, sortName, sortType, page, rows);
	}

	public void doAddRole(CmsRole role) {
		crdao.add(role);
	}

	public void doUpdateRole(CmsRole role) {
		crdao.update(role);
	}

	public CmsRole findRoleById(Integer id) {
		return crdao.findById(id);
	}

	public boolean doDeleteRole(String ids) {
		
		if(cudao.findUserByRole(ids)!= null){  //这一步没有必要
			if(cudao.findUserByRole(ids).size() == 0){
				crdao.doDeleteRP(ids);
				crdao.doDeleteRole(ids);
				return true;
			}
		}
		return false;
	}

	public ICmsUserDao getCudao() {
		return cudao;
	}

	public void setCudao(ICmsUserDao cudao) {
		this.cudao = cudao;
	}

	public void doGrantRole(String ids, Integer id) {
		
		CmsRole role = crdao.findById(id);
		role.getCmsPowers().clear();
		if(ids.length() > 0){
			String[] idsArr = ids.split(",");
			Set<Integer> set = new HashSet<Integer>();
			for (int i = 0; i < idsArr.length; i++) {
				set.add(Integer.parseInt(idsArr[i]));
			}
			
			for (Integer pid : set) {
				CmsPower p = new CmsPower();
				p.setId(pid);
				role.getCmsPowers().add(p);
			}
			
		}
		crdao.add(role);
	}

	
	
}
