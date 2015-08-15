package javacommon.filter;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javacommon.base.dao.IGlobalvariableDao;
import javacommon.base.model.GlobalVariable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParserFactory;

import org.apache.velocity.app.Velocity;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
* Title:自定义拦截规则的类
* Description:拦截规则的类
* @author yf
* @version Revision: 1.0 Date: 2014/03/16
*/
public class LoginCheckFilter implements Filter {
	/**
	 * 定义拦截器默认的返回页面
	 */
	private String loginPage = null;
	private String getSessionName;
	/**
	 * 存取页面不需要拦截的请求
	 */
	private List<String> listFileter = new ArrayList<String>();
	@Override
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getServletPath();
		url = url.substring(1);
		HttpSession session = httpServletRequest.getSession();
		RequestDispatcher rd = request
		.getRequestDispatcher(loginPage);
		
		Integer index = url.lastIndexOf("?");
		String subUrl = "";
		if(index>0){
			subUrl = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("?"));
		}else{
			subUrl = url.substring(url.lastIndexOf("/")+1);
		}
		if(!listFileter.contains(subUrl)){
			if(session.getAttribute(getSessionName) == null){
				rd.forward(request, response);
			}else{
				chain.doFilter(request, response);
			}
			/*else{
				List<String> urlList =new ArrayList<String>();
				if(!urlList.contains(url)){
						rd.forward(request, response);
				}else{
					chain.doFilter(request, response);
				}
			}*/
		}else{
			chain.doFilter(request, response);
		}
		/*chain.doFilter(request, response);*/
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.loginPage = "/";
		this.getSessionName = "login_info";
		
		this.listFileter.add("");
		this.listFileter.add("?");
		this.listFileter.add("loginAction_online.action");
		this.listFileter.add("appAction_invoke.action");
		this.listFileter.add("loginAction_login.action");
		this.listFileter.add("loginAction_sendForgotPassCode.action");
		this.listFileter.add("loginAction_vertifyForgotPassCode.action");
		this.listFileter.add("loginAction_modifyPassword.action");
		/*this.loginPage = filterConfig.getInitParameter("loginPage");
		WebApplicationContext wac = getApplicationContext(filterConfig.getServletContext());
		IGlobalvariableDao globalvariableDao = (IGlobalvariableDao) wac.getBean("theGlobalvariableDao");
		getSessionName = globalvariableDao.getGlobalvariable(new GlobalVariable("MDGV118")).getContent();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			String path = filterConfig.getServletContext().getRealPath("/")+"\\WEB-INF\\classes\\menufilter.xml";
			builder = dbf.newDocumentBuilder();
			Document doc = builder
					.parse(path);
			Element root = doc.getDocumentElement();
			NodeList url = root.getElementsByTagName("url");
			for (int i = 0; i < url.getLength(); i++){
				listFileter.add(url.item(i).getFirstChild().getNodeValue().trim());
			ServletContext application = filterConfig.getServletContext();
			application.setAttribute("listFileter", listFileter);
			}
			readXml(path);
		}catch (Exception e) {
			System.out.println("拦截器过滤文件错误"+e.getMessage());
			e.printStackTrace();
		}
		Properties properties = new Properties();
		  properties.put("runtime.log",filterConfig.getServletContext().getRealPath("/WEB-INF/log/velocity.log"));
		  // 指定模板文件加载位置
		  properties.put("file.resource.loader.path",filterConfig.getServletContext().getRealPath("/pages/vm"));
		  // 指定输入编码格式
		  properties.put("input.encoding", "UTF-8");
		  // 指定velocity的servlet向浏览器输出内容的编码
		  properties.put("default.contentType", "text/html;charset\\=UTF-8");
		  // 指定输出编码格式
		  properties.put("output.encoding", "UTF-8");
		  // 迭代索引名
		  //properties.put("directive.foreach.counter.name", "index");
		  // 迭代开始的下标
		 // properties.put("directive.foreach.counter.initial.value", "0");
        Velocity.init(properties);*/
	}
	//获取Spring上下文
	private WebApplicationContext getApplicationContext(ServletContext servletContext) {
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		return applicationContext;
	}
	/**
	 * 解析XML
	 * @param xmlPath
	 * @throws Exception
	 */
	private void readXml(String xmlPath) throws Exception {
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		XMLReader xmlReader = saxParserFactory.newSAXParser().getXMLReader();
		XmlHandler xmlHandler = new XmlHandler(this.listFileter);
		xmlReader.setContentHandler(xmlHandler);
		xmlReader.parse(new InputSource(new FileInputStream(xmlPath)));
	}
	
	class XmlHandler extends DefaultHandler {
		private List<String> list = null;
		public XmlHandler(List<String> list) {
			this.list = list;
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			if(qName.equals("menuName")){			
				list.add(attributes.getValue("content"));
			}
		}
		
	}
	
}
