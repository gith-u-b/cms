package com.aaa.yf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsDuplicateDao;
import com.aaa.yf.entity.CmsDuplicate;
import com.aaa.yf.service.ICmsDuplicateService;

@Service("cdupservice")
public class CmsDuplicateServiceImpl implements ICmsDuplicateService {

	@Autowired
	private ICmsDuplicateDao cdupdao;

	public ICmsDuplicateDao getCdupdao() {
		return cdupdao;
	}

	public void setCdupdao(ICmsDuplicateDao cdupdao) {
		this.cdupdao = cdupdao;
	}

	public void doAddDuplicate(CmsDuplicate dup) {

		this.cdupdao.add(dup);
	}
	
}
