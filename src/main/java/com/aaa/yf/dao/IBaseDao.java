package com.aaa.yf.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;

public interface IBaseDao<T> {

	public abstract void mySetSessionFactory(SessionFactory sessionFactory);

	public abstract void add(T t);

	public abstract void update(T t);

	public abstract void delete(T t);

	public abstract List<T> findByHql(String hql, Object... params);

	public abstract void executeHql(String hql, Object... params);

	public abstract T findById(Integer id);

	public abstract long findCount(String hql, Object... params);

	public abstract List<T> findByPage(String hql, Integer page, Integer rows,
			Object... params);
	public List<T> findInfo(Map condition,String sortName,String sortType,Integer page,Integer rows);
	public long findCount(Map condition);
	public void executeSql(String sql);
	
}
