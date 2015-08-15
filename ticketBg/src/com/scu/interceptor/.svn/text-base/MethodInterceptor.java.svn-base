package com.scu.interceptor;


import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.scu.utils.MyLog;

/** 
 * 方法拦截器Interceptor，用于对方法执行进行日志记录，方便观察方法执行
* @author jiahui.li 
* @date 2012-8-9 17:55
* 
*/ 

@Scope("prototype")
public class MethodInterceptor implements Interceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		MyLog.info("======="+invocation.getInvocationContext().getName()+"开始=======");
//		long start = System.currentTimeMillis();
//		Log.info("======="+invocation.getInvocationContext().getLocale()+"=======");  
//		Log.info("======="+invocation.getInvocationContext().getParameters()+"=======");
		String result = invocation.invoke();
//		long end = System.currentTimeMillis();
		MyLog.info("======="+invocation.getInvocationContext().getName()+"结束=======");
		//Log.info("==="+invocation.getInvocationContext().getName()+"执行时间 = " + (end - start)+"毫秒===");
		return result;
	}

}
