package javacommon.interf;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public interface IDao<T> {
	/**
	* 获取当前线程的session
	* @return Session 返回当前线程的session
	* @throws Exception
	*/
	public Session getCurrentSession();
 
	/**
	* 保存对象
	* @param o 需要新增的对象
	* @throws Exception
	*/
	public Serializable save(T o);

	/**
	* 删除对象
	* @param o 需要删除的对象
	* @throws Exception
	*/
	public void delete(T o);
	/**
	* 更新对象
	* @param o 需要更新的对象
	* @throws Exception
	*/
	public void update(T o);
	/**
	* 保存或更新对象
	* @param o 需要保存或更新的对象
	* @throws Exception
	*/
	public void saveOrUpdate(T o);
	
	/**
	* 根据主键查询对象
	* @param E 需要查询的对象
	* @param id 查询对象的主键
	* @return T 需要查询的对象
	* @throws Exception
	*/
	public T get(Class<T> E, Serializable id);
	/**
	* 执行hql语句查询
	* @param hql 查询语句
	* @return T 执行hql语句之后的T对象
	* @throws Exception
	*/
	public T get(String hql);
	/**
	* 带参数的hql语句查询
	* @param hql 查询语句
	* @param params Map<String, Object>类型的参数
	* @return T 执行hql语句之后的T对象
	* @throws Exception
	*/
	public T get(String hql, Map<String, Object> params);
	/**
	* 执行hql语句查询
	* @param hql 查询语句
	* @return List<T> 执行hql语句之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql);
	/**
	* 带参数的hql语句查询
	* @param hql 查询语句
	* @param params Map<String, Object>类型的参数
	* @return List<T> 执行hql语句之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql, Map<String, Object> params);
	/**
	* 分页查询，返回分页查询之后的对象集合
	* @param hql 分页查询的hql语句
	* @param page 分页起始位置
	* @param rows 分页结束的位置
	* @return List<T> 分页之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql, int page, int rows);
	/**
	* 带参数的分页查询，返回分页查询之后的对象集合
	* @param hql 分页查询的hql语句
	* @param params Map<String, Object>类型的参数
	* @param page 分页起始位置
	* @param rows 分页结束的位置
	* @return List<T> 分页之后的List对象集合
	* @throws Exception
	*/
	public List<T> find(String hql, Map<String, Object> params, int page,
			int rows);
	/**
	* 执行带参数的hql语句返回受影响的行数
	* @param hql 执行的hql语句
	* @param params Map<String, Object>类型的参数
	* @return Integer 返回受影响的行数
	* @throws Exception
	*/
	public Integer executeHql(String hql, Map<String, Object> params);
	
	/**
	* 执行带参数的hql语句返回List集合
	* @param hql 语句
	* @param params Map<String, Object>类型的参数
	* @return List<?> 返回List对象集合
	* @throws Exception
	*/
	public List<?> queryHql(String hql, Map<String, Object> params);
	
	/**
	* 执行删除的hql语句返回受影响的行数
	* @param hql 删除的hql语句
	* @return Integer 返回受影响的行数
	* @throws Exception
	*/
	public Integer delete(String hql);
}
