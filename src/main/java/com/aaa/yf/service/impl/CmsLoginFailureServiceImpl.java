package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsLoginFailureDao;
import com.aaa.yf.entity.CmsLoginFailure;
import com.aaa.yf.service.ICmsLoginFailureService;

@Service("clfservice")
public class CmsLoginFailureServiceImpl implements ICmsLoginFailureService{

	@Autowired
	private ICmsLoginFailureDao clfdao;

	public ICmsLoginFailureDao getClfdao() {
		return clfdao;
	}

	public void setClfdao(ICmsLoginFailureDao clfdao) {
		this.clfdao = clfdao;
	}

	public void doAddLoginLog(CmsLoginFailure clf) {
		clfdao.add(clf);
	}

	public List<CmsLoginFailure> findLoginFailLog(Map condition,
			String sortName, String sortType, Integer page, Integer rows) {
		return clfdao.findInfo(condition, sortName, sortType, page, rows);
	}

	public long findCount(Map condition) {
		return clfdao.findCount(condition);
	}

	public void doDeleteLoginLog(String ids) {
		clfdao.deletLoginLog(ids);
	}
	
}
