package com.aaa.yf.service;

import java.util.List;
import java.util.Map;

import com.aaa.yf.entity.CmsTemplateType;



public interface ICmsTemplateTypeService {

	public List<CmsTemplateType> findTemplateTypeByCondition(Map condition, String sortName, String sortType,
			Integer page, Integer rows);
	
}
