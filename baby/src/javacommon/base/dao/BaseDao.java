package javacommon.base.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
* Title:持久层，操作数据库基本方法
* Description: 持久层，操作数据库基本方法
* @author qinchuan
* @version Revision: V1.0 Date: 2014/03/10
*/ 
@SuppressWarnings("all")
public class BaseDao<T> extends HibernateTemplate {
	/**
	 * 执行方法时的hql param集合
	 */
	public Map<String, Object> params = new HashMap<String, Object>();
	
	/**
	 * Hibernate的session工厂
	 */
	private SessionFactory sessionFactory; 
	
	private Session session;

	/**
	* SessionFactory的构造方法
	* @return 返回sessionFactory的实例
	* @throws Exception
	*/
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	* SessionFactory的构造方法
	* @param sessionFactory sessionFactory的实例对象
	* @throws Exception
	*/
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	* 获取当前线程的session
	* @return Session 返回当前线程的session
	* @throws Exception
	*/
	public Session getCurrentSession() {
		 try{
			return this.session = this.sessionFactory.getCurrentSession();
		 }catch (Exception e) {
			 System.out.println("采用opensession"+e.getMessage());
			 return this.session = this.sessionFactory.openSession();
		}
	}
	
	public void setCurrentSession(Session session) {
		this.session = session;
	}

	/**
	* 执行hql语句查询
	* @param hql 查询语句
	* @return T 执行hql语句之后的T对象
	* @throws Exception
	*/
	public T get(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		query.setMaxResults(1);
		return (T) query.uniqueResult();
	}
	/**
	* 带参数的hql语句查询
	* @param hql 查询语句
	* @param params Map<String, Object>类型的参数
	* @return T 执行hql语句之后的T对象
	* @throws Exception
	*/
	public T get(String hql, Map<String, Object> params) {
		Query query =getSession().createQuery(hql);
		query.setMaxResults(1);
		if(params != null && !params.isEmpty()){
			for(String key:params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		return  (T) query.uniqueResult();
	}
	/**
	 * 更具SQL语句查询对象
	 * @param sql
	 * @return
	 */
	public T getSQL(String sql, Class<T> c){
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.setMaxResults(1);
		sqlQuery.addEntity(c);
		return (T) sqlQuery.uniqueResult();
	}
	/**
	 * 根据SQL语句查询对象集合(带分页)
	 * @param sql
	 * @return
	 */
	public List<T> findSQL(String sql,Class<T> c,T o){
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		sqlQuery.addEntity(c);
		Integer page = null;
		Integer rows = null;
		try {
			page = (Integer) invokeAttribute(o, "page", null);
			rows = (Integer) invokeAttribute(o, "rows", null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(page == null || page == 0)
			page = 1;
		if(rows == null || rows == 0)
			rows = 10;
		return sqlQuery.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	
	/**
	* 执行hql语句查询
	* @param hql 查询语句
	* @return List<T> 执行hql语句之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return query.list();
	}
	/**
	* 带参数的hql语句查询
	* @param hql 查询语句
	* @param params Map<String, Object>类型的参数
	* @return List<T> 执行hql语句之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.list();
	}
	/**
	* 分页查询，返回分页查询之后的对象集合
	* @param hql 分页查询的hql语句
	* @param page 分页起始位置
	* @param rows 分页结束的位置
	* @return List<T> 分页之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql, int page, int rows) {
		Query query = this.getCurrentSession().createQuery(hql);
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}
	/**
	* 带参数的分页查询，返回分页查询之后的对象集合
	* @param hql 分页查询的hql语句
	* @param params Map<String, Object>类型的参数
	* @param page 分页起始位置
	* @param rows 分页结束的位置
	* @return List<T> 分页之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql, Map<String, Object> params, Integer page,Integer rows) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if(page != null && rows != null){
			
			List<T> list = query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
			
			return list;
		}else{
			return find(hql, params);
		}
		
	}
	/**
	 * 方法功能说明：分页查询，page跳过多少页<br />
	 * QiuYuan
	 * @param hql 分页查询的hql语句
	 * @param params Map<String, Object>类型的参数
	 * @param page 分页起始位置(备注：page是跳过多少页，例如：5，跳过前五条，查询6-10条数据)
	 * @param rows 分页结束的位置
	 * @return
	 * List<T>
	 * @see 类参考方法：
	 * @exception 异常处理类：
	 */
	public List<T> findSkipRows(String hql, Map<String, Object> params, Integer page,Integer rows) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if(page != null && rows != null){
			return query.setFirstResult(page).setMaxResults(rows).list();
		}else{
			return find(hql, params);
		}
		
	}
	
	
	/**
	 * 方法功能说明：分页查询，page跳过多少页<br />
	 * QiuYuan
	 * @param hql 分页查询的hql语句
	 * @param params Map<String, Object>类型的参数
	 * @param page 分页起始位置(备注：page是跳过多少页，例如：5，跳过前五条，查询6-10条数据)
	 * @param rows 分页结束的位置
	 * @return
	 * List<T>
	 * @see 类参考方法：
	 * @exception 异常处理类：
	 */
	public List<T> findSkipRowsCreateSQLQuery(String hql, Map<String, Object> params, Integer page,Integer rows) {
		Query query = this.getCurrentSession().createSQLQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if(page != null && rows != null){
			return query.setFirstResult(page).setMaxResults(rows).list();
		}else{
			return find(hql, params);
		}
		
	}
	
	
	
	
	
	
	/**
	* 执行带参数的hql语句返回受影响的行数
	* @param hql 执行的hql语句
	* @param params Map<String, Object>类型的参数
	* @return Integer 返回受影响的行数
	* @throws Exception
	*/
	public Integer executeHql(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if(params != null && !params.isEmpty()){
			for(String key:params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		return query.executeUpdate();
	}
	
	/**
	* 执行带参数的hql语句返回List集合
	* @param hql 语句
	* @param params Map<String, Object>类型的参数
	* @return List<?> 返回List对象集合
	* @throws Exception
	*/
	public List<?> queryHql(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.list();
	}
	
	/**
	* 执行删除的hql语句返回受影响的行数
	* @param hql 删除的hql语句
	* @return Integer 返回受影响的行数
	* @throws Exception
	*/
	public Integer delete(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}
	
	/**
	 * 根据HQL得到查询记录数
	 * @param hql
	 * @return
	 */
	public Long count(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * 根据带参数的HQL得到查询记录数
	 * @param hql
	 * @param params
	 * @return
	 */
	public Long count(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return (Long) query.uniqueResult();
	}
	/**
	 * 根据SQL语句获取记录数
	 * @param sql
	 * @return
	 */
	public Long countSQL(String sql){
		SQLQuery sqlQuery = this.getCurrentSession().createSQLQuery(sql);
		BigDecimal count = (BigDecimal) sqlQuery.uniqueResult();
		return  count.longValue();
	}
	
	public Long sum(String hql, Map<String, Object> params) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return (Long) query.iterate().next();
	}
	
	
	/**
	 * 获取某个对象的记录数
	 * @param o
	 * @return
	 */
	public Long count(T o){
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM ");
		sb.append(c.getName()).append(" o WHERE 1=1");
		try {
			sb.append(generateQueryParams(o));
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		}
		return count(sb.toString(), params);
	}
	/**
	 * 模糊获取某个对象的记录数
	 * @param o
	 * @return
	 */
	public Long countLike(T o){
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM ");
		sb.append(c.getName()).append(" o WHERE 1=1");
		try {
			sb.append(generateLikeQueryParams(o));
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		}
		return count(sb.toString(), params);
	}	
	/**
	 * 根据对象查询数据库中的对象集合 "排序","分页","模糊查询","时间范围"全支持的条件
	 * @param o
	 * @return
	 */
	public Long countTimeBucket(T o){
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM ");
		sb.append(c.getName()).append(" o WHERE 1=1");
		try {
			sb.append(generateTimeBucketParams(o));
			sb.append(generateLikeQueryParams(o));
		} catch (Exception e) {
			e.printStackTrace();
			return -1L;
		}
		return count(sb.toString(), params);
	}	
	
	
	/**
	 * 删除某个对象
	 * @param o
	 * @return
	 */
	public Integer deleteObject(T o){
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder("DELETE ");
		sb.append(c.getName()).append(" o WHERE 1=1");
		try {
			sb.append(generateQueryParams(o));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		return executeHql(sb.toString(), params);
	}
	
	/**
	 * 根据对象查询数据库中的对象 如果对象中的orderString有值 则需要排序
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public T query(T o) {
		String hql = null;
		try {
			hql = generateQueryHql(o);
			String orderString = generateOrderParams(o);
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		return this.get(hql,params);
	}	
	/**
	 * 根据对象查询数据库中的对象集合 如果对象中的orderString有值 则需要排序
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public List<T> queryList(T o) {		
		String hql = null;
		try {
			hql = generateQueryHql(o);
			String orderString = generateOrderParams(o);
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		return this.find(hql,params);
	}
	
	/**
	 * 根据对象查询数据库中的对象 排序和模糊查询全支持
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public T globalQuery(T o) {
		String hql = null;
		try {
			hql = generateLikeQueryHql(o);
			String orderString = generateOrderParams(o);
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		
		return this.get(hql,params);
	}	
	/**
	 * 根据对象查询数据库中的对象集合 排序分页和模糊查询全支持
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public List<T> globalQueryList(T o) {		
		String hql = null;
		try {
			hql = generateLikeQueryHql(o);
			String orderString = generateOrderParams(o);
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		Object pageObj = null;
		Object rowsObj = null;
		try {
			pageObj = invokeAttribute(o, "page", null);
			rowsObj = invokeAttribute(o, "rows", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(pageObj!=null&&rowsObj!=null){
			return this.find(hql, params, (Integer)pageObj, (Integer)rowsObj);
		}
		return this.find(hql,params);
	}
	
	/**
	 * 根据对象查询数据库中的对象集合 "排序","分页","模糊查询","时间范围"全支持
	 * @param o
	 * @return
	 */
	public List<T> globalTimeBucketQueryList(T o) {
		String hql = null;
		try {
			hql = generateLikeQueryHql(o);
			String 	timeBucket = generateTimeBucketParams(o);
			String orderString = generateOrderParams(o);
			hql += timeBucket;
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		if(hql==null){
			return null;
		}
		Object pageObj = null;
		Object rowsObj = null;
		
		try {
			pageObj = invokeAttribute(o, "page", null);
			rowsObj = invokeAttribute(o, "rows", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(pageObj!=null&&rowsObj!=null){
			return this.find(hql, params, (Integer)pageObj, (Integer)rowsObj);
		}
		return this.find(hql,params);
		
	}
	
	/**
	 * 构建排序参数
	 * @param o
	 * @return
	 */
	private String generateOrderParams(T o){
		Class c = o.getClass();
		Object v;
		try {
			v = invokeAttribute(o, "orderString", null);
		} catch (Exception e) {
			return null;
		}
		if(v!=null && !v.equals("")){
			return " ORDER BY "+(String)v;
		}
		return null;
	}
	/**
	 * 构造时间范围参数
	 * @param o
	 * @return
	 */
	private String generateTimeBucketParams(T o){
		Class c = o.getClass();
		Object timeObj = null;
		try {
			timeObj = invokeAttribute(o, "time", null);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if(timeObj != null && !timeObj.equals("")){
			StringBuilder sb = new StringBuilder("");
			try{
				String timeStr = timeObj.toString();
				
				boolean b  = (timeStr.indexOf("more") > 0 || timeStr.indexOf("less") > 0);
				String[] str = timeStr.split(",");
				
				if(!b){
					sb.append(" AND o.").append(str[0]).append(" between ").append("'"+str[1]+ "'").append(" and ").append("'"+str[2]+ "'").append("");
				}else{
					String time = "'"+str[2]+ "'"; 
					
					if(str[1].equals("more")){
						sb.append(" AND o.").append(str[0]).append(">").append(time).append(" ");
					}else if(str[1].equals("less")){
						sb.append(" AND o.").append(str[0]).append("<").append(time).append(" ");
					}else if(str[1].equals("moreequal")){
						sb.append(" AND o.").append(str[0]).append(">=").append(time).append(" ");
					}else if(str[1].equals("lessequal")){
						sb.append(" AND o.").append(str[0]).append("<=").append(time).append(" ");
					}
				}
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
			return sb.toString();
		}else if(timeObj == null || timeObj.equals("")){
			return "";
		}
		return "";
		
	}
	
	/**
	 * 根据对象查询数据库中的对象集合(分页) 如果对象中的orderString有值 则需要排序
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public List<T> queryListIncludePaging(T o) {		
		String hql = null;
		try {
			hql = generateQueryHql(o);
			String orderString = generateOrderParams(o);
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		try {
			return this.find(hql, params, (Integer)invokeAttribute(o, "page", null), (Integer)invokeAttribute(o, "rows", null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据对象查询数据库中的对象集合(模糊查询)
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public List<T> queryListIncludeLike(T o) {		
		String hql = null;
		try {
			hql = generateLikeQueryHql(o);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		return this.find(hql, params);
	}
	/**
	 * 根据对象查询数据库中的对象(模糊查询)
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	public T queryIncludeLike(T o) {
		String hql = null;
		try {
			hql = generateLikeQueryHql(o);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		return this.get(hql, params);
	}
	/**
	 * 构建根据对象模糊查询的hql语句
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	private String generateLikeQueryHql(T o) throws Exception{
		Class c = o.getClass();
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("FROM ");
		hqlBuffer.append(c.getName()).append(" o WHERE 1=1");
		
		hqlBuffer.append(generateLikeQueryParams(o));
		return hqlBuffer.toString();
	}
	
	/**
	 * 构建根据对象模糊查询和or参数的hql语句
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	private String generateLikeAndOrQueryHql(T o) throws Exception{
		Class c = o.getClass();
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("FROM ");
		hqlBuffer.append(c.getName()).append(" o WHERE 1=1");
		
		hqlBuffer.append(generateLikeAndOrQueryParams(o));
		return hqlBuffer.toString();
	}
	
	/**
	 * 构建带模糊查询的WHERE后面的参数
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	private String generateLikeQueryParams(T o) throws Exception{
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder();
		Field field[] = c.getDeclaredFields();
		params.clear();
		for (Field f : field) {
			//这里判断Set和List是否为空
			if(f.getType().getName().equals("java.util.List")||f.getType().getName().equals("java.util.Set")){
				continue;
			}
			Object v = invokeAttribute(o,f.getName(),null);
//			System.out.println(f.getType().getName());
			if(v!=null && !v.equals("")){
				if(f.getType().getName().equals("java.lang.String")){
					String s = (String)v;
//					Pattern pattern = Pattern.compile("like "); 
//					Matcher matcher = pattern.matcher(s);
					if(s.indexOf("like")==0){
						sb.append(" AND o.").append(f.getName()).append(" like:").append(f.getName());
						params.put(f.getName(), "%"+s.replaceFirst("like ", "")+"%");
						continue;
					}
				}
				sb.append(" AND o.").append(f.getName()).append("=:").append(f.getName());
				params.put(f.getName(), v);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 构建带模糊查询和or参数的WHERE后面的参数
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	private Object generateLikeAndOrQueryParams(T o) throws Exception {
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder();
		Field field[] = c.getDeclaredFields();
		params.clear();
		Object orObj = invokeAttribute(o,"orString",null);
		
		if(orObj==null||orObj.equals("")){
			return generateLikeQueryParams(o);
		}
		
		String[] orArray = orObj.toString().split(",");		
		List<String> orList = Arrays.asList(orArray);
		StringBuilder orSb = new StringBuilder();
		
		for (Field f : field) {
			//这里判断Set和List是否为空
			if(f.getType().getName().equals("java.util.List")||f.getType().getName().equals("java.util.Set")){
				continue;
			}
			Object v = invokeAttribute(o,f.getName(),null);
//			System.out.println(f.getType().getName());
			if(v!=null && !v.equals("")){
				//or的判断
				if(orList.contains(f.getName())){
					if(orSb.length()==0){
						orSb.append(" AND (").append("o.").append(f.getName()).append("=:").append(f.getName());
					}
					else{
						orSb.append(" or ").append("o.").append(f.getName()).append("=:").append(f.getName());
					}
					
					params.put(f.getName(), v);
					
					continue;
				}
				
				//like的判断
				if(f.getType().getName().equals("java.lang.String")){
					String s = (String)v;
//					Pattern pattern = Pattern.compile("like "); 
//					Matcher matcher = pattern.matcher(s);
					if(s.indexOf("like")==0){
						sb.append(" AND o.").append(f.getName()).append(" like:").append(f.getName());
						params.put(f.getName(), "%"+s.replaceFirst("like ", "")+"%");
						continue;
					}
				}
				sb.append(" AND o.").append(f.getName()).append("=:").append(f.getName());
				params.put(f.getName(), v);
			}
		}
		
		sb.append(orSb).append(")");
		
		
		return sb.toString();
	}

	/**
	 * 构建根据对象查询的hql语句
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	private String generateQueryHql(T o) throws Exception{
		Class c = o.getClass();
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("FROM ");
		hqlBuffer.append(c.getName()).append(" o WHERE 1=1");
		
		hqlBuffer.append(generateQueryParams(o));
		return hqlBuffer.toString();
	}
	
	
	/**
	 * 构建WHERE后面的参数
	 * @param o
	 * @return
	 * @throws Exception 
	 */
	private String generateQueryParams(T o) throws Exception{
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder();
		Field field[] = c.getDeclaredFields();
		params.clear();
		for (Field f : field) {
			//这里判断Set和List是否为空
			if(f.getType().getName().equals("java.util.List")||f.getType().getName().equals("java.util.Set")){
				continue;
			}
			Object v = invokeAttribute(o,f.getName(),null);
//			System.out.println(f.getType().getName());
			if(v!=null && !v.equals("")){
				sb.append(" AND o.").append(f.getName()).append("=:").append(f.getName());
				params.put(f.getName(), v);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 根据对象更新 
	 * @param o
	 * @return
	 */
	public Integer updateObject(T o){
		params.clear();
		
		Class c = o.getClass();
		
		//获取主键的值
		Object primaryValue = null;
		try {
			primaryValue = invokeAttribute(o,"id",null);
		} catch (Exception e) {
			return -1;
		}
		if(primaryValue==null){
			return -1;
		}
		
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("UPDATE ");
		hqlBuffer.append(c.getName()).append(" o SET");
		
		Field field[] = c.getDeclaredFields();
		for (Field f : field) {
			//如果是主键 直接返回
			if(f.getName().equals("id")){
				continue;
			}
			//这里判断Set和List是否为空
			if(f.getType().getName().equals("java.util.List")||f.getType().getName().equals("java.util.Set")){
				continue;
			}
			
			Object v = null;
			try {
				v = invokeAttribute(o,f.getName(),null);
			} catch (Exception e) {
				return -1;
			}
			if(v!=null && !v.equals("")){
				hqlBuffer.append(" o.").append(f.getName()).append("=:").append(f.getName()).append(",");
				params.put(f.getName(), v);
			}
		}
		
		if(!params.isEmpty()){
			hqlBuffer.replace(hqlBuffer.length()-1, hqlBuffer.length(), " ");
		}
		//加主键条件
		hqlBuffer.append("WHERE o.id=:id");
		//把主键写入params
		params.put("id", primaryValue);
		
		
		return this.executeHql(hqlBuffer.toString(), params);
	}
	
	 /** 
     * 获得对象属性的值 
     */  
    @SuppressWarnings("unchecked")  
    private static Object invokeAttribute(Object owner, String methodName,  Object[] args) throws Exception {  
        Class ownerClass = owner.getClass();  
        methodName = methodName.substring(0, 1).toUpperCase()  
                + methodName.substring(1);  
        Method method = null;  
        try {  
            method = ownerClass.getMethod("get" + methodName);  
        } catch (SecurityException e) {  
        	return null;
        } catch (NoSuchMethodException e) {  
            return null;  
        }  
        return method.invoke(owner);  
    }
    
    
    public List<T> globalTimeBucketAndMoneyList(T o) {
		String hql = null;
		try {
			hql = generateLikeQueryHql(o);
			String 	timeBucket = generateTimeBucketParams(o);
			String moneyBucket = generateMoneyBucketParams(o);
			String ids = generateInParams(o);
			String orderString = generateOrderParams(o);
			hql += timeBucket;
			hql += moneyBucket;
			hql += ids;
			try {
				Object conditionString=invokeAttribute(o, "conditionString", null);
				if(conditionString!=null){
					hql=hql+" AND "+conditionString;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		if(hql==null){
			return null;
		}
		Object startObj = null;
		Object countObj = null;
		try {
			startObj = invokeAttribute(o, "start", null);
			countObj = invokeAttribute(o, "count", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(startObj!=null&&countObj!=null){
			return this.findLimit(hql, params, (Integer)startObj, (Integer)countObj);
		}
		
		Object pageObj = null;
		Object rowsObj = null;
		
		try {
			pageObj = invokeAttribute(o, "page", null);
			rowsObj = invokeAttribute(o, "rows", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(pageObj!=null&&rowsObj!=null){
			return this.find(hql, params, (Integer)pageObj, (Integer)rowsObj);
		}
		return this.find(hql,params);
		
	}
    
    
	/**
	 * 函 数 名: countTimeBucketAndMoney<br>
	 * 功 能：对象集合 "排序","分页","模糊查询","时间范围" 金钱范围 包括in 对应globalTimeBucketAndMoneyList <br>
	 * @time：2014-11-24 下午5:22:54  
	 * @author LiuLun
	 */
	public Long countTimeBucketAndMoney(T o){
		Class c = o.getClass();
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM ");
		sb.append(c.getName()).append(" o WHERE 1=1");
		try {
			sb.append(generateTimeBucketParams(o));
			sb.append(generateLikeQueryParams(o));
			sb.append(generateMoneyBucketParams(o));
			sb.append(generateInParams(o));
			Object conditionString=null;
			try {
				conditionString = invokeAttribute(o, "conditionString", null);
				if (conditionString != null) {
					sb.append(" AND ");
					sb.append(conditionString);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count(sb.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
    
    /**
     * 包括金钱区间 时间区间和or参数的查询集合
     * @author Chencong
     * @param o
     * @return
     */
    public List<T> globalTimeBucketAndMoneyAndOrList(T o) {
		String hql = null;
		try {
			hql = generateLikeAndOrQueryHql(o);
			String 	timeBucket = generateTimeBucketParams(o);
			String moneyBucket = generateMoneyBucketParams(o);
			String ids = generateInParams(o);
			String orderString = generateOrderParams(o);
			hql += timeBucket;
			hql += moneyBucket;
			hql += ids;
			try {
				Object conditionString=invokeAttribute(o, "conditionString", null);
				if(conditionString!=null){
					hql=hql+" AND "+conditionString;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		if(hql==null){
			return null;
		}
		Object startObj = null;
		Object countObj = null;
		try {
			startObj = invokeAttribute(o, "start", null);
			countObj = invokeAttribute(o, "count", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(startObj!=null&&countObj!=null){
			return this.findLimit(hql, params, (Integer)startObj, (Integer)countObj);
		}
		
		Object pageObj = null;
		Object rowsObj = null;
		
		try {
			pageObj = invokeAttribute(o, "page", null);
			rowsObj = invokeAttribute(o, "rows", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(pageObj!=null&&rowsObj!=null){
			return this.find(hql, params, (Integer)pageObj, (Integer)rowsObj);
		}
		return this.find(hql,params);
		
	}
    
    
    
    
    public Long countGlobalTimeBucketAndMoneyAndOrList(T o) {
    	Class c = o.getClass();
		StringBuilder sb = new StringBuilder("SELECT COUNT(*) FROM ");
		sb.append(c.getName()).append(" o WHERE 1=1");
		try {
			sb.append(generateTimeBucketParams(o));
			sb.append(generateLikeAndOrQueryParams(o));
			sb.append(generateMoneyBucketParams(o));
			sb.append(generateInParams(o));
			Object conditionString=null;
			try {
				conditionString = invokeAttribute(o, "conditionString", null);
				if (conditionString != null) {
					sb.append(" AND ");
					sb.append(conditionString);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count(sb.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
    
    
    /**
	 * 构造金钱范围参数
	 * @param o
	 * @return
	 */
	private String generateMoneyBucketParams(T o){
		Class c = o.getClass();
		Object moneyObj = null;
		try {
			moneyObj = invokeAttribute(o, "moneyBucket", null);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if(moneyObj != null && !moneyObj.equals("")){
			StringBuilder sb = new StringBuilder("");
			try{
				String[] str = moneyObj.toString().split(",");
				if(str[1].equals("more")){
					sb.append(" AND o.").append(str[0]).append(">").append("'").append(str[2]).append("'");
				}else if(str[1].equals("less")){
					sb.append(" AND o.").append(str[0]).append("<").append("'").append(str[2]).append("'");
				}else if(str[1].equals("moreequal")){
					sb.append(" AND o.").append(str[0]).append(">=").append("'").append(str[2]).append("'");
				}else if(str[1].equals("lessequal")){
					sb.append(" AND o.").append(str[0]).append("<=").append("'").append(str[2]).append("'");
				}else{
					sb.append(" AND o.").append(str[0]).append(" between '").append(str[1]).append("' and '").append(str[2]).append("'");				
				}
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
			return sb.toString();
		}else if(moneyObj == null || moneyObj.equals("")){
			return "";
		}
		return "";
	}
	/**
	 * 构建ids
	 * @param o
	 * @return
	 */
	private String generateInParams(T o){
		Class c = o.getClass();
		Object idsObj = null;
		try {
			idsObj = invokeAttribute(o, "viewTreasureRecordIds", null);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		if(idsObj != null && !idsObj.equals("")){
			StringBuilder sb = new StringBuilder("");
			try{
				sb.append(" AND o.userid in (").append(idsObj).append(")");
			}catch(Exception e){
				e.printStackTrace();
				return "";
			}
			return sb.toString();
		}else if(idsObj == null || idsObj.equals("")){
			return "";
		}
		return "";
	}
	
	/**
	* 查询集合，从start开始取count条
	* @param hql 分页查询的hql语句
	* @param params Map<String, Object>类型的参数
	* @param start 从哪里开始取
	* @param count 取多少条
	* @return List<T> 分页之后的List对象集合
	* @throws Exception
	*/
	public List<T> findLimit(String hql, Map<String, Object> params, Integer start,Integer count) {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if(start != null && count != null){
			return query.setFirstResult(start).setMaxResults(count).list();
		}else{
			return find(hql, params);
		}
	}
	
	
	
	private String generateParamsAndLikeSearchContent(T o){
		StringBuilder sb = new StringBuilder();
		StringBuilder sblike = new StringBuilder();
		try {
			Class c = o.getClass();
			Field field[] = c.getDeclaredFields();
			String str = (String)invokeAttribute(o, "search_content", null);
			params.clear();
			
			int status = 0;
			for (Field f : field) {
				Object v = invokeAttribute(o,f.getName(),null);
//				String s = String.valueOf(v);
				/*if(f.getType().getName().equals("java.lang.String")){*/
					if(v !=null && !v.equals("")){
						sb.append(" AND o.").append(f.getName()).append("=:").append(f.getName());
						params.put(f.getName(), v);
						continue;
					}
					/*}*/
					if(StringUtils.isNotEmpty(str)){
						/*for (Field f : field) {*/
							/*Object v = invokeAttribute(o,f.getName(),null);*/
//							String s = String.valueOf(v);
							if(f.getType().getName().equals("java.lang.String")){
								if(v ==null || "".equals(v)){
									status ++;
									if(status == 1){
										sblike.append(" AND ( ");
									}else{
										sblike.append(" OR ");
									}
									sblike.append("o." + f.getName()).append(" like:").append(f.getName());
									params.put(f.getName(), "%"+ str +"%");
									continue;
								}
							/*}*/
						}
						
					}
				}
			if(StringUtils.isNotEmpty(sblike)){
				sblike.append(" ) ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		StringBuffer where = new StringBuffer();
		where.append(sb);
		where.append(sblike);
		return where.toString();
	}
	/**
	 * 
	* @Title: generateParamsAndLikeSearchContent 
	* @Description: TODO( 实体字段为空时构建like ) 
	* @param @param o 实体 content like
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	* @author 向冠宇
	 */
	public List<T> globalParamsAndLikeSearchContent(T o){
		String hql = null;
		try {
			Class c = o.getClass();
			
			StringBuffer hqlBuffer = new StringBuffer();
			hqlBuffer.append("FROM ");
			hqlBuffer.append(c.getName()).append(" o WHERE 1=1");
			
			hql = hqlBuffer.toString();
			
			String orLikeString = generateParamsAndLikeSearchContent(o);
			if(!StringUtils.isEmpty(orLikeString)){
				hql += orLikeString;
			}
			String timeString = generateTimeBucketParams(o);
			if(StringUtils.isNotEmpty(timeString)){
				hql += timeString;
			}
			String orderString = generateOrderParams(o);
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		Object pageObj = null;
		Object rowsObj = null;
		try {
			pageObj = invokeAttribute(o, "page", null);
			rowsObj = invokeAttribute(o, "rows", null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(pageObj!=null&&rowsObj!=null){
			return this.find(hql, params, (Integer)pageObj, (Integer)rowsObj);
		}
		return this.find(hql,params);
	}
	
	public Long countGlobalParamsAndLikeSearchContent(T o){
		String hql = null;
		try {
			Class c = o.getClass();
			
			StringBuffer hqlBuffer = new StringBuffer();
			hqlBuffer.append("SELECT COUNT(*) FROM ");
			hqlBuffer.append(c.getName()).append(" o WHERE 1=1");
			
			hql = hqlBuffer.toString();
			
			String orLikeString = generateParamsAndLikeSearchContent(o);
			if(!StringUtils.isEmpty(orLikeString)){
				hql += orLikeString;
			}
			String timeString = generateTimeBucketParams(o);
			if(StringUtils.isNotEmpty(timeString)){
				hql += timeString;
			}
			String orderString = generateOrderParams(o);
			if(!StringUtils.isEmpty(orderString)){
				hql += orderString;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(hql==null){
			return null;
		}
		return this.count(hql,params);
	}
}
