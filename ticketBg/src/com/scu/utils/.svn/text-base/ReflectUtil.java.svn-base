package com.scu.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;



/**
 * 反射工具类
 * @author jiahui.li 2012-8-16 上午12:00
 * 获取泛型参数的Class实体
 */
public class ReflectUtil {
	
	/**
	 * 获取EntityDao的父类（即HibernateDao）的泛型参数Class实体
	 * @param <T>
	 * @param c 是EntityDao（UserDao etc）的Class实体
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public static final <T> Class<T> getSuperClassGenricType(final Class c){
		return getClassGenricType(c, 0);
	}
	
	/**
	 * 
	 * 获取HibernateDao的第�?��泛型Class实体
	 * @param c 是EntityDao（UserDao etc）的Class实体
	 * @param index 参数列表索引 
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public static final <T> Class<T> getClassGenricType(Class c, int index){
		//得到泛型父类
		Type superClassType = c.getGenericSuperclass();
		////如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class 
		if(!(superClassType instanceof ParameterizedType)){
			return (Class<T>) Object.class;
		}
		//得到泛型参数数组
		Type[] gencics = ((ParameterizedType)superClassType).getActualTypeArguments();
		if(gencics.length < index || index  <0){
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();//用于获取当前方法名字
			//在静态方法内获取当前类名。
			String clazzName = new SecurityManager() {
				public String getClassName() {
					return getClassContext()[1].getName();
				}
			}.getClassName();
			MyLog.error(methodName+"."+Thread.currentThread().getStackTrace()[1].getMethodName()+"==="+"输入的索引值小于0或超出参数的总个数"+"===");
			throw new RuntimeException("你输入的索引"+ (index<0 ? "不能小于0" : "超出了参数的总个数"));  
		}
		
		if(!(gencics[index] instanceof Class)){
			return (Class<T>) Object.class;
		}
		
		//返回第一个泛型参数类型实例
		return (Class<T>) gencics[index];
	}
	
}
