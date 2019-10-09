package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsTemplate;

public interface ICmsTemplateService {

	public List<CmsTemplate> findTemplateByCondition(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	public List<CmsTemplate> findTemplateByType();
	public CmsTemplate findTemplateById(Integer id);
	public void doAddTemplate(CmsTemplate t);
	public void doDeleteTemplate(CmsTemplate template);
	public void doUpdateTemplate(CmsTemplate template);
}
