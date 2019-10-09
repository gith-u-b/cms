package com.aaa.yf.dao;

import java.util.List;

import com.aaa.yf.entity.CmsPower;

public interface ICmsPowerDao extends IBaseDao<CmsPower>{

	public List<CmsPower> findAllPower();
	public void doDeletePower(Integer id);
	public void doDeleteRP(Integer id);
}
