package com.aaa.yf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsContentTypeDao;
import com.aaa.yf.entity.CmsContentType;
import com.aaa.yf.service.ICmsContentTypeService;

@Service("cnewtservice")
public class CmsContentTypeServiceImpl implements ICmsContentTypeService {

	@Autowired
	private ICmsContentTypeDao cnewtdao;

	
	public ICmsContentTypeDao getCnewtdao() {
		return cnewtdao;
	}

	public void setCnewtdao(ICmsContentTypeDao cnewtdao) {
		this.cnewtdao = cnewtdao;
	}

	public List<CmsContentType> findAllContentType() {
		return cnewtdao.findInfo(null, null, null, null, null);
	}
	
}
