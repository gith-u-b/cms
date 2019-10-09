package com.aaa.yf.service;

import java.util.List;

import com.aaa.yf.entity.CmsPower;

public interface ICmsPowerService {

	public List<CmsPower> findAllPower(Integer rid);    //查找所有权限，并标记当前角色已拥有权限
	public List<CmsPower> findAllPower();
	public List<CmsPower> findPowerByPage();
	public long findCount();
	public void doDeletePower(Integer id);
	public void doAddPower(CmsPower power);
	public void doUpdatePower(CmsPower power);
}
