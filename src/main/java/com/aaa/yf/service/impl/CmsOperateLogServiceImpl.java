package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsOperateLogDao;
import com.aaa.yf.entity.CmsExceptionLog;
import com.aaa.yf.entity.CmsOperateLog;
import com.aaa.yf.service.ICmsOperateLogService;

@Service("colservice")
public class CmsOperateLogServiceImpl implements ICmsOperateLogService{
	
	@Autowired
	private ICmsOperateLogDao coldao;

	public ICmsOperateLogDao getColdao() {
		return coldao;
	}

	public void setColdao(ICmsOperateLogDao coldao) {
		this.coldao = coldao;
	}

	public List<CmsOperateLog> findOperateLog(Map condition, String sortName,
			String sortType, Integer page, Integer rows) {
		List<CmsOperateLog> list = coldao.findInfo(condition, sortName, sortType, page, rows);
		for (CmsOperateLog cmsOperateLog : list) {
			cmsOperateLog.getCmsUser().getUserName();
		}
		return list;
				
	}

	public long findCount(Map condition) {
		return coldao.findCount(condition);
	}

	public void doDeleteOperateLog(String ids) {
		coldao.deleteOperateLog(ids);
	}
	
	
}
