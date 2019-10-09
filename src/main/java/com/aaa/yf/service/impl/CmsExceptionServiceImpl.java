package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsExceptionLogDao;
import com.aaa.yf.entity.CmsExceptionLog;
import com.aaa.yf.service.ICmsExceptionLogService;

@Service("celservice")
public class CmsExceptionServiceImpl implements ICmsExceptionLogService{

	@Autowired
	private ICmsExceptionLogDao celdao;

	public ICmsExceptionLogDao getCeldao() {
		return celdao;
	}

	public void setCeldao(ICmsExceptionLogDao celdao) {
		this.celdao = celdao;
	}

	public List<CmsExceptionLog> findExceptionLog(Map condition,
			String sortName, String sortType, Integer page, Integer rows) {
		return celdao.findInfo(condition, sortName, sortType, page, rows);
	}

	public long findCount(Map condition) {
		return celdao.findCount(condition);
	}

	public void doDeleteExceptionLog(String ids) {
		celdao.deleteExceptionLog(ids);
	}
	
	
}
