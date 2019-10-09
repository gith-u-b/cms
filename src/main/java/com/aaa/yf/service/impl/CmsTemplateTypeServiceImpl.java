package com.aaa.yf.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsRoleDao;
import com.aaa.yf.dao.ICmsTemplateTypeDao;
import com.aaa.yf.dao.ICmsUserDao;
import com.aaa.yf.entity.CmsPower;
import com.aaa.yf.entity.CmsRole;
import com.aaa.yf.entity.CmsTemplateType;
import com.aaa.yf.service.ICmsRoleService;
import com.aaa.yf.service.ICmsTemplateTypeService;

@Service("cttservice")
public class CmsTemplateTypeServiceImpl implements ICmsTemplateTypeService {

	@Autowired
	private ICmsTemplateTypeDao cttdao;

	public ICmsTemplateTypeDao getCttdao() {
		return cttdao;
	}

	public void setCttdao(ICmsTemplateTypeDao cttdao) {
		this.cttdao = cttdao;
	}

	public List<CmsTemplateType> findTemplateTypeByCondition(Map condition, String sortName, String sortType,
			Integer page, Integer rows) {
		return cttdao.findInfo(condition, sortName, sortType, page, rows);
	}
	

	
	
}
