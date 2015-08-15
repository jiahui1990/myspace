package com.scu.dao;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Id;





import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.scu.utils.MyLog;
import com.scu.utils.ReflectUtil;

public class BaseDAO<M extends java.io.Serializable> extends HibernateDaoSupport {
	//泛型M的Class
	protected Class<M> entityClass;
	
	//查出�?���?
	private final String HQL_LIST_ALL;
	//查出总数据量
    @SuppressWarnings("unused")
	private final String HQL_COUNT_ALL;
    //查询主键大于指定值的�?��列，按升序排�?
    @SuppressWarnings("unused")
	private final String HQL_OPTIMIZE_PRE_LIST_ALL;
    //查询主键小于指定值的�?��列，按降序排�?
    @SuppressWarnings("unused")
	private final String HQL_OPTIMIZE_NEXT_LIST_ALL;
    //主键
    private String pkName = null;

    public BaseDAO() {
//        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    	this.entityClass = ReflectUtil.getSuperClassGenricType(getClass());
        Field[] fields = this.entityClass.getDeclaredFields();
        for(Field f : fields) {
            if(f.isAnnotationPresent(Id.class)) {
            	//primary key获取,�?��在实体类中相应字段加annotation
                this.pkName = f.getName();
            }
        }
//        Assert.assertNotNull(pkName);
        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " asc";
        HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " > ? order by " + pkName + " asc";
        HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " < ? order by " + pkName + " desc";
        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
    }
	
//----------------------------query--------------------------------------------

	public M findById(int id){
		return  find(entityClass , id);
	}
	
	@SuppressWarnings("unchecked")
	public M findByIdWithLock(int id, LockMode lock) {
		M m = (M) getHibernateTemplate().get(entityClass, id, lock);
        if (m != null) { 
            this.getSession().flush(); // 立即刷新，否则锁不会生效�?
        } 
        return m; 
	}

	@SuppressWarnings("unchecked")
	public <X> X find(final Class<X> clazz ,final int id){
		return (X) this.getHibernateTemplate().get(clazz, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<M> findBySql(String sql){
		return this.getHibernateTemplate().find(sql);
	}
	
	@SuppressWarnings({ "unchecked" })
	public List<M> findByExample(M example){
		List<M> list = this.getHibernateTemplate().findByExample(example);
		this.getSession().flush();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<M> findByProperty(String propertyName, Object value) {
		String queryString = "from " + this.entityClass.getSimpleName() + " as model where model." 
        + propertyName + "= ?";
		return  (List<M>)getHibernateTemplate().find(queryString, value);
	}

	@SuppressWarnings("unchecked")
	public <X> List<X> findAll(final Class<X> clazz){
		return this.createCriteria(clazz).list();
	}

	@SuppressWarnings("unchecked")
	public List<M> findAll(){
		return this.createCriteria().list();
	}
	
	//-------------------------------删除--------------------------------------
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void delete(final Class clazz ,final int id){
		this.getHibernateTemplate().delete(this.find(clazz, id));
	}
	
	public void delete(final M model){
		this.getHibernateTemplate().delete(model);//测试类可以无法删除，页面无法删除
	}
	
	public void deleteByLock(M m, LockMode lock) {
		getHibernateTemplate().delete(m, lock); 
		this.getSession().flush();
	}

	public void deleteById(final int id){
		this.getHibernateTemplate().delete(this.find(entityClass, id));
	}
	
	public void deleteByIdWithLock(int id, LockMode lock) {
		this.getHibernateTemplate().delete(this.find(entityClass, id), lock);
	}

	//-------------------------------保存、修�?--------------------------------------
	public void saveOrUpdate(final M model){
		try {
			this.getHibernateTemplate().saveOrUpdate(model);
			this.getSession().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
			this.getSession().getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	public void save(String entityName,Object entity){
		this.getHibernateTemplate().save(entityName, entity);
		this.getSession().flush();
	}

//------------------------------utils-----------------------------------------------------

	@SuppressWarnings("rawtypes")
	public Criteria createCriteria(final Class clazz ,final  Criterion ... criterions){
		Criteria criteria = createCriteria(criterions);
		return criteria;
	}
	
	public Criteria createCriteria(final  Criterion ... criterions){
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria = criteria.add(c);
		}
		return criteria;
	}
	
	public Criteria createCriteria(String orderBy, Criterion... criterions) {
		Criteria criteria = createCriteria(criterions);
		if(!"".equals(orderBy)&&orderBy!=null){
			if(orderBy.equals("asc")){
				criteria.addOrder(Order.asc(orderBy));
			}else{
				criteria.addOrder(Order.desc(orderBy));
			}
		}
		return criteria;
	}
	
//----------------------------------------------------------------------------
////////////////////////////hql  参数//////////////////////////////////////////	

	@SuppressWarnings("rawtypes")
	public List createQuery(final String hql , final Object ... objects){
		Query query = this.getSession().createQuery(hql);
		if(objects!=null){
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
//		createSqlQuery("sd",2);
		return query.list();
	}
////////////////////////////sql  参数//////////////////////////////////////////

	public List<Object> createSqlQuery(final String sql , final Object ... objects){
		Query query = this.getSession().createSQLQuery(sql);
		if(objects!=null){
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return (List<Object>)query.list();
	}
	
	public List createSqlQueryWithClass(final String sql ,Class clazz, final Object ... objects){
		Query query = this.getSession().createSQLQuery(sql)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("ticketId", Hibernate.STRING)
				.addScalar("fieldNum", Hibernate.INTEGER)
				.addScalar("ticketDateBegin", Hibernate.TIMESTAMP)
				.addScalar("ticketDateEnd", Hibernate.TIMESTAMP)
				.addScalar("msg", Hibernate.STRING)
				.addScalar("total", Hibernate.DOUBLE)
				.addScalar("mini", Hibernate.STRING);
		query.setResultTransformer(Transformers.aliasToBean(clazz));
				/*.addScalar("id", Hibernate.INTEGER)
				.addScalar("ticketId", Hibernate.STRING)
				.addScalar("fieldNum", Hibernate.INTEGER)
				.addScalar("ticketDateBegin", Hibernate.TIMESTAMP)
				.addScalar("ticketDateEnd", Hibernate.TIMESTAMP)
				.addScalar("msg", Hibernate.STRING)
				.addScalar("playTime", Hibernate.STRING)
				.addScalar("total", Hibernate.STRING);*/
		if(objects!=null){
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}

//--------------------------------获取分页数据----------------------------------------------	
	
	@SuppressWarnings("unchecked")
	public int getTotalCount() {
		String sql = "select count(*) "+HQL_LIST_ALL;
		List<M> list = createQuery(sql, null);
		return list.size();
	}
	
	@SuppressWarnings("rawtypes")
	public int getTotalCount(Criterion... criterion) {
		List list =createCriteria(criterion).list();
		MyLog.info("===BaseDao.getTotalCount(criterion)返回数据�?+list.size()");
		return list.size();
	}

	public int getTotalCount(M model){
		List<M> list = findByExample(model);
		MyLog.info("===BaseDao.getTotalCount(model)返回数据�?+list.size()");
		return list.size();
	}
	@SuppressWarnings("unchecked")
	public List<M> getPage(M model,int firstIndex,int maxIndex){
		return (List<M>) this.getHibernateTemplate().findByExample(model,firstIndex,maxIndex);
	}
	
	@SuppressWarnings("unchecked")
	public List<M> getPage( int from,int length,Criterion... criterions) {
		return this.createCriteria(criterions).list();
	}

	@SuppressWarnings("unchecked")
	public List<M> findBySQL(String sql){
		return this.getHibernateTemplate().find(sql);
	}
	
}
