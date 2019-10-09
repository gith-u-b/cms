package com.aaa.yf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.cache.CountClickCache;
import com.aaa.yf.dao.ICmsClickDao;
import com.aaa.yf.service.IJobService;

@Service("cclickservice")
public class QuartzClickWork implements IJobService {

	@Autowired
	private ICmsClickDao cclickdao;

	public ICmsClickDao getCclickdao() {
		return cclickdao;
	}

	public void setCclickdao(ICmsClickDao cclickdao) {
		this.cclickdao = cclickdao;
	}

	public void doWorkOne() {
		CountClickCache.output();
		CountClickCache.clear();
		cclickdao.batchSave();
		
	}

	public void doWorkTwo() {
		cclickdao.clearData();
	}

	public void doWorkThree() {
		cclickdao.clearLog();
	}
	
	
}
