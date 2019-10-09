package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsGroupDao;
import com.aaa.yf.entity.CmsGroup;
import com.aaa.yf.service.ICmsGroupService;

@Service("cgservice")
public class CmsGroupServiceImpl implements ICmsGroupService {

	@Autowired
	private ICmsGroupDao cgdao;

	public ICmsGroupDao getCgdao() {
		return cgdao;
	}

	public void setCgdao(ICmsGroupDao cgdao) {
		this.cgdao = cgdao;
	}

	public List<CmsGroup> findAllGroup(Map condition, String sortName, String sortType,
			Integer page, Integer rows) {
		return cgdao.findInfo(condition, sortName, sortType, page, rows);
	}

	public void doAddGroup(CmsGroup group) {
		cgdao.add(group);
	}

	public void doUpdateGroup(CmsGroup group) {
		cgdao.update(group);
		
	}

	public void doDeleteGroup(CmsGroup group) {
		cgdao.delete(group);
		
	}
	
}
