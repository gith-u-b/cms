package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsLoginSuccessDao;
import com.aaa.yf.entity.CmsLoginSuccess;
import com.aaa.yf.service.ICmsLoginSuccessService;

@Service("clsservice")
public class CmsLoginSuccessServiceImpl implements ICmsLoginSuccessService{

	@Autowired
	private ICmsLoginSuccessDao clsdao;

	public ICmsLoginSuccessDao getClsdao() {
		return clsdao;
	}

	public void setClsdao(ICmsLoginSuccessDao clsdao) {
		this.clsdao = clsdao;
	}

	public void doAddLoginLog(CmsLoginSuccess cls) {
		clsdao.add(cls);
	}

	public List<CmsLoginSuccess> findLoginSucLog(Map condition,
			String sortName, String sortType, Integer page, Integer rows) {
		List<CmsLoginSuccess> list = clsdao.findInfo(condition, sortName, sortType, page, rows);
		for (CmsLoginSuccess cmsLoginSuccess : list) {
			cmsLoginSuccess.getUser().getUserName();
		}
		return list;
	}

	public long findCount(Map condition) {
		return clsdao.findCount(condition);
	}

	public void doDeleteLoginLog(String ids) {
		clsdao.deleteLoginLog(ids);
	}

	
	
}
