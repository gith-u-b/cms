package com.aaa.yf.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.aaa.yf.dao.IBaseDao;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T>  {

		
	private String entityName;
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#mySetSessionFactory(org.hibernate.SessionFactory)
	 */
	@Autowired
	public void mySetSessionFactory(SessionFactory sessionFactory){
		this.setSessionFactory(sessionFactory);
	}
	
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#add(T)
	 */
	public void add(T t){
		this.getHibernateTemplate().save(t);
	}
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#update(T)
	 */
	public void update(T t){
		this.getHibernateTemplate().update(t);
	}
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#delete(T)
	 */
	public void delete(T t){
		this.getHibernateTemplate().delete(t);
	}
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#findByHql(java.lang.String, java.lang.Object)
	 */
	public List<T> findByHql(String hql,Object...params){
		
		return this.getHibernateTemplate().find(hql,params);
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#executeHql(java.lang.String, java.lang.Object)
	 */
	public void executeHql(String hql,Object...params){
		this.getHibernateTemplate().bulkUpdate(hql,params);
	}
	
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#findById(java.lang.Integer)
	 */
	public T findById(Integer id){
		return (T) this.getHibernateTemplate().get(getEntityName(), id);
	}
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#findCount(java.lang.String, java.lang.Object)
	 */
	public long findCount(String hql,Object...params){
		return (Long) this.getHibernateTemplate().find(hql,params).get(0);
	}
	/* (non-Javadoc)
	 * @see com.aaa.yf.dao.impl.IBaseDao#findByPage(java.lang.String, java.lang.Integer, java.lang.Integer, java.lang.Object)
	 */
	public List<T> findByPage(String hql,Integer page,Integer rows,Object...params){
		Session session = this.getSession();
		Query q = session.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			q.setParameter(i, params[i]);
		}
		q.setFirstResult((page-1)*rows);
		q.setMaxResults(rows);
		List<T> list = q.list();
		this.releaseSession(session);
		
		return list;
		
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<T> findInfo(Map condition, String sortName, String sortType,
			Integer page, Integer rows) {
			List values = new ArrayList();
			StringBuffer hql = new StringBuffer("select obj from "+this.getEntityName()+" obj where 1 = 1 ");
			if(condition != null){
				Set<String> keySet = condition.keySet();
				
				for (String key : keySet) {
					if(key.indexOf(" in ") != -1 ){
						hql.append(" and "+key+" "+condition.get(key));
					}else{
						if(condition.get(key)!= null && !condition.get(key).equals("%null%") && !condition.get(key).equals("%%") && !"".equals(condition.get(key))){
							hql.append(" and "+key);
							values.add(condition.get(key));
						}
					}
				}
				
			}
			
			if(sortName != null && sortType != null){
				hql.append("order by "+sortName+" "+sortType);
			}
			
			Session session = this.getSession();
			Query q = session.createQuery(hql.toString());
			if(values.size() > 0){
				for (int i = 0; i < values.size(); i++) {
					q.setParameter(i, values.get(i));
				}
			}
			if(page != null && rows != null){
				q.setFirstResult((page-1)*rows);
				q.setMaxResults(rows);
			}
			List<T> list = q.list();
			this.releaseSession(session);
		
		return list;
	}

	public long findCount(Map condition) {
		StringBuffer hql = new StringBuffer("select count(obj) from "+this.getEntityName()+" obj where 1 = 1 ");
		List values= new ArrayList();
		
		if(condition != null){
			Set<String> keys = condition.keySet();
			for (String key : keys) {
				if(key.indexOf(" in ") != -1 ){
					hql.append(" and "+key+""+condition.get(key));
				}else{
					if(condition.get(key)!= null && !condition.get(key).equals("%null%") && !condition.get(key).equals("%%") && !"".equals(condition.get(key))){
						hql.append(" and "+key);
						values.add(condition.get(key));
					}
				}
			}
		}
		Session session = this.getSession();
		Query q = session.createQuery(hql.toString());
		if(values.size() > 0){
			for (int i = 0; i < values.size(); i++) {
				q.setParameter(i, values.get(i));
			}
		}
		long allRecorders = (Long) q.uniqueResult();
		this.releaseSession(session);
		
		return allRecorders;
	}

	public void executeSql(String sql) {
		Session session = this.getSession(); 
		Query q = session.createSQLQuery(sql);
		q.executeUpdate(); 
		this.releaseSession(session);
		
	}
	public List findBySql(String sql) {
		Session session = this.getSession(); 
		Query q = session.createSQLQuery(sql);
		List list = q.list();
		this.releaseSession(session);
		return list;
	}
	public List<Object[]> findObjectByHql(String sql) {
		return this.getHibernateTemplate().find(sql);
	}

	
	
	
	
	
	
}
