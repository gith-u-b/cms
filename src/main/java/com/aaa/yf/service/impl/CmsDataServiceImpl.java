package com.aaa.yf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsDataDao;
import com.aaa.yf.service.ICmsDataService;
import com.aaa.yf.util.CmsDatapool;

@Service("cdservice")
public class CmsDataServiceImpl implements ICmsDataService {
	@Autowired
	private ICmsDataDao cddao;

	public ICmsDataDao getCddao() {
		return cddao;
	}

	public void setCddao(ICmsDataDao cddao) {
		this.cddao = cddao;
	}

	public List<CmsDatapool> findAllTable() {
		return cddao.findAllTable();
	}

	public List<CmsDatapool> findTableColumnByTableName(String tableName) {
		return cddao.findTableColumnByTableName(tableName);
	}

	public List<CmsDatapool> findAllDatabase() {
		return cddao.findAllDatabase();
	}
	
	
	
}
