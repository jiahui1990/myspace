package javacommon.base.action;

import java.io.IOException;
import java.util.Date;

import javacommon.base.model.BusinessActivity;
import javacommon.base.model.GlobalVariable;
import javacommon.base.model.SystemLog;
import javacommon.base.service.BaseService;
import javacommon.javautil.TimeService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ggy.baby.login.model.Users;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Title:Action中的父类对象
 * Description: 两个数相加
 * @version Revision: 1.0 Date: 2014/04/03
 */
public class BaseAction extends ActionSupport {
	private static final long serialVersionUID = -4176181046825776064L;
	/**
	 * yf
	 * 父类service
	 */
	private BaseService baseservice;

	public BaseService getBaseservice() {
		return baseservice;
	}

	public void setBaseservice(BaseService baseservice) {
		this.baseservice = baseservice;
	}
	/**
	 * yf
	 * 获取request
	 * @return request
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	/**
	 * yf
	 * 获取session
	 * @return session
	 */
	public HttpSession getSession() {
		return this.getRequest().getSession();
	}
	/**
	 * yf
	 * 获取Response
	 * @return Response
	 */
	public HttpServletResponse getResponse() {
			return ServletActionContext.getResponse();
	}
	/**
	 * yf
	 * 获取appliction
	 * @return Response
	 */
	public ServletContext getAppliction() {
			return ServletActionContext.getServletContext();

	}
	/**
	 * 获取ServletContext
	 * @return ServletContext
	 */
	public ServletContext getServletContext() {
			return ServletActionContext.getServletContext();

	}
	/**
	 * LiuLun
	 * 函 数 名:writeJson <br>
	 * 功 能： 将对象转换为json字符串，并输出到页面 <br>
	 * 输 入 参 数： <br>
	 * 返 回 值： <br>
	 * @param args
	 * @return 输出参数
	 * @exception 异常处理类
	 * @author LiuLun
	 */	
	public void writeJson(Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object,
					"yyyy-MM-dd HH:mm:ss", SerializerFeature.DisableCircularReferenceDetect);
			this.getResponse().setContentType("text/html;charset=utf-8");
			this.getResponse().getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * QiuYuan
	 * @Description: 将对象输出到页面上，ajax访问返回，不会提示下载
	 * @param object
	 * @param i
	 */
	public void writeJson(Object object,int i) {
			try {
				String json = JSON.toJSONStringWithDateFormat(object,
						"yyyy-MM-dd HH:mm:ss");
				this.getResponse().setContentType("text/html;charset=utf-8");
				this.getResponse().getWriter().write(json);
				this.getResponse().getWriter().flush();
				this.getResponse().getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 得到当前时间
	 * @return
	 */
	public Date getCurrentTime(){
		return new Date();
	}
	/**
	 *
	 * @Description: 得到当前时间的字符串形式
	 * @return
	 */
	public String getCurrentTimeToString(){
		return TimeService.format(getCurrentTime());
	}
	
	/**
	 * 函 数 名：getLocalIP 
	 * 功 能：取得本地的IP地址
	 *  输 入 参 数：无
	 *   返 回 值：IP地址字符串
	 * @throws Exception
	 */
	public String getLocalIP() { // 此方法有问题,用花生壳测试外网访问的时候无法获取真实ip地址
		HttpServletRequest request = getRequest();
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public Users getCurrentUser() {
		GlobalVariable  glo = new GlobalVariable();
		glo.setNumbers("MDGV5");
		glo = this.getGlobalvariable(glo);
		return (Users) getSession().getAttribute(glo.getContent());
	}
	/**
	 * 获取当前登录用户ID
	 * @return
	 */
	public Long getCurrentUserId() {
		return getCurrentUser().getId();
	}
	/**
	 * 函 数 名：getLocalTime 
	 * 功 能：取得本地时间 
	 * 输 入 参 数：
	 * 无 返 回 值：本地时间字符串
	 * @return String
	 * @roseuid 531D62CD0222
	 */
	public String getLocalTime() {
		Date date = new Date();
		String format = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		return format;
	}
	
	/**
	 * 构建Systemlog
	 * @param request 请求对象
	 * @param action Systemlog里面的action字段
	 * @param username	Systemlog里面的username字段
	 * @return	返回构建好的Systemlog
	 */
	public SystemLog genarateSystemlog(HttpServletRequest request, Long action, String descripetion, String username){
		SystemLog systemlog = new SystemLog(null, null, action, getCurrentTime(),descripetion,request.getRemoteAddr(), username);
		return systemlog;
	}
	/**
	 * 
	 * @Title: setLog
	 * @Description: TODO(构建系统日志对象)
	 * @author QinChuan 
	 * @param 
	 * @return void
	 * @throws
	 */
	/*public SystemLog setLog(String businessActivity, String descripetion) {
		String username = null;
		if (getCurrentUser() != null) {
			username = getCurrentUser().getUsername();
		}
		BusinessActivity bu = getBusinessActivity(new BusinessActivity(
				businessActivity));
		SystemLog systemlog = genarateSystemlog(getRequest(), bu.getId(), descripetion,
				username);
		return systemlog;
	}*/
	
	/**
	 * 获取全局变量对象
	 * @param globalVariable
	 * @return
	 */
	public GlobalVariable getGlobalvariable(GlobalVariable globalVariable){
		WebApplicationContext  ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		setBaseservice((BaseService) ctx.getBean("serviceI"));
		return baseservice.getGlobalvariable(globalVariable);
		
	}
	
	/**
	 * 根据id获取全局变量对象
	 * @param globalVariable
	 * @return
	 */
	public GlobalVariable getGlobalvariableByid(GlobalVariable globalVariable){
		WebApplicationContext  ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		setBaseservice((BaseService) ctx.getBean("serviceI"));
		return baseservice.getGlobalvariableByid(globalVariable);
	}

	/**
	 * 根据编号获取业务字典对象
	 * @param businessActivity
	 * @return BusinessActivity业务字典对象
	 */
	public BusinessActivity getBusinessActivity(
			BusinessActivity businessActivity) {
		WebApplicationContext  ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		setBaseservice((BaseService) ctx.getBean("serviceI"));
		return baseservice.getBusinessActivity(businessActivity);
	}
	/**
	 * 根据id获取业务字典对象
	 * @param businessActivity
	 * @return BusinessActivity业务字典对象
	 */
	public BusinessActivity getBusinessActivityByid(
			BusinessActivity businessActivity) {
		WebApplicationContext  ctx = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		setBaseservice((BaseService) ctx.getBean("serviceI"));
		return baseservice.getBusinessActivityByid(businessActivity);
	}
	
	/**
	 * 直接向前台返回字符串
	 * @param string
	 */
	public void writeJsonString(String jsonString) {
		try {
			this.getResponse().setContentType("text/html;charset=utf-8");
			this.getResponse().getWriter().write(jsonString);
			this.getResponse().getWriter().flush();
			this.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * 将对象转换为json字符串(可能有问题)
	 * @param object
	 * @return
	 */
	public String objToJson(Object object) {
		return JSON.toJSONStringWithDateFormat(object,
				"yyyy-MM-dd HH:mm:ss",SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * 
	 * @author QC
	 * @Title: getRootRealPath 
	 * @Description: TODO(获取服务器上真实路径) 
	 * @return String    返回类型 
	 * @throws
	 */
	public String getRootRealPath() {
		return getSession().getServletContext().getRealPath("/");
	}
}
