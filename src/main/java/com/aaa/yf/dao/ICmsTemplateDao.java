package com.aaa.yf.dao;

import java.util.List;

import com.aaa.yf.entity.CmsTemplate;

public interface ICmsTemplateDao extends IBaseDao<CmsTemplate> {

	public List<CmsTemplate> findTemplateByType();
}
