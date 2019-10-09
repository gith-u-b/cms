package com.aaa.yf.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aaa.yf.dao.ICmsPowerDao;
import com.aaa.yf.dao.ICmsRoleDao;
import com.aaa.yf.entity.CmsPower;
import com.aaa.yf.entity.CmsRole;
import com.aaa.yf.service.ICmsPowerService;

@Service("cpservice")
public class CmsPowerServiceImpl implements ICmsPowerService {

	@Autowired
	private ICmsPowerDao cpdao;
	@Autowired
	private ICmsRoleDao crdao;

	public ICmsPowerDao getCpdao() {
		return cpdao;
	}

	public void setCpdao(ICmsPowerDao cpdao) {
		this.cpdao = cpdao;
	}



	public List<CmsPower> findAllPower(Integer rid) {
		CmsRole role = crdao.findById(rid);
		Set<CmsPower> pset = role.getCmsPowers();
		List<CmsPower> plist = cpdao.findAllPower();
		
		for (CmsPower cmsPower : plist) {
			if(pset.contains(cmsPower)){
				cmsPower.setChecked(true);
			}
		}
		return plist;
	}

	public ICmsRoleDao getCrdao() {
		return crdao;
	}

	public void setCrdao(ICmsRoleDao crdao) {
		this.crdao = crdao;
	}

	public List<CmsPower> findAllPower() {
		return cpdao.findAllPower();
	}

	public List<CmsPower> findPowerByPage() {
		return cpdao.findInfo(null, null, null, null, null);
	}

	public long findCount() {
		return cpdao.findCount(null);
	}

	public void doDeletePower(Integer id) {
		cpdao.doDeleteRP(id);
		cpdao.doDeletePower(id);
	}

	public void doAddPower(CmsPower power) {
		cpdao.add(power);
	}

	public void doUpdatePower(CmsPower power) {
		cpdao.update(power);
	}
	
	
}
