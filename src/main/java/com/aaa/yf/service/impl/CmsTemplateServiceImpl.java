package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsTemplateDao;
import com.aaa.yf.entity.CmsTemplate;
import com.aaa.yf.service.ICmsTemplateService;

@Service("ctservice")
public class CmsTemplateServiceImpl implements ICmsTemplateService {

	@Autowired
	private ICmsTemplateDao ctdao;

	
	public ICmsTemplateDao getCtdao() {
		return ctdao;
	}

	public void setCtdao(ICmsTemplateDao ctdao) {
		this.ctdao = ctdao;
	}

	public List<CmsTemplate> findTemplateByChannel(Map condition,
			String sortName, String sortType, Integer page, Integer rows) {
		
		return ctdao.findInfo(condition, sortName, sortType, page, rows);
	}

	public List<CmsTemplate> findTemplateByCondition(Map condition,
			String sortName, String sortType, Integer page, Integer rows) {
		List<CmsTemplate> list = ctdao.findInfo(condition, sortName, sortType, page, rows);
		for (CmsTemplate cmsTemplate : list) {
			cmsTemplate.getCmsUser().getUserName();
		}
		
		return list; 
	}

	public List<CmsTemplate> findTemplateByType() {
		return ctdao.findTemplateByType();
	}

	public CmsTemplate findTemplateById(Integer id) {
		CmsTemplate ct = ctdao.findById(id);
		ct.getCmsChannels().size();
		ct.getCmsContents().size();
		return ct;
	}

	public void doAddTemplate(CmsTemplate t) {
		ctdao.add(t);
	}

	public void doDeleteTemplate(CmsTemplate template) {
		ctdao.delete(template);
	}

	public void doUpdateTemplate(CmsTemplate template) {
		ctdao.update(template);
	}
	
	
}
