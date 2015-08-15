package com.scu.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.opensymphony.xwork2.ActionSupport;
import com.scu.dto.Json;
import com.scu.service.FieldInfoService;
import com.scu.service.FieldMoneyXihuaService;
import com.scu.service.PlayDetailMoneyService;
import com.scu.service.PlayDetailService;
import com.scu.service.SaleTimeScheduleService;
import com.scu.service.SalorService;
import com.scu.service.TicketService;
import com.scu.utils.ActionUtil;
import com.scu.utils.StringUtil;

@SuppressWarnings("serial")
@Scope("prototype")
@Controller("baseAction")
@Transactional
public class BaseAction extends ActionSupport {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BaseAction.class);
	
	/** *******************service*************************** */
	@Resource
	public SalorService salorService;
	@Resource
	public FieldInfoService fieldInfoService;
	@Resource
	public TicketService ticketService;
	@Resource
	public PlayDetailService playDetailService;
	@Resource
	public PlayDetailMoneyService playDetailMoneyService;
	@Resource
	public FieldMoneyXihuaService fieldMoneyXihuaService;
	@Resource
	public SaleTimeScheduleService saleTimeScheduleService;
	
	/** *******************私有变量*************************** */
	private HttpServletRequest		request;
	private HttpServletResponse		response;
	private HttpSession				session;
	private ServletContext			application;
	private boolean					isAsyncRequest;			//请求为异步的标志
	private Map<String,Object>		actiomParametersMap;	//传入的参数�?�?

	private Map<String,Object>		actiomReturnValuesMap;	//传出的key、�?


	/** 关于验证 **/
	private List<String>			validateErrKeys;		//验证出错的字�?


	private JSONObject				validataeJO;			//异步验证出错的字段�?错误消息

	
	//private MessageList             messageList;            //同步验证

	/** 关于消息 **/
	private List<String>			msgList;
	/** *******************私有变量*************************** */
	
	
	/** ********提供给aop的方法，在aop的invoke之前，只能�?过这个方法得到request及其他参�?********* */
	public HttpServletRequest 		getRequest() 						{ return ServletActionContext.getRequest(); }
	public HttpServletResponse 		getResponse()						{ return ServletActionContext.getResponse(); }
	public Map 						getReturnValuesMap()				{ return actiomReturnValuesMap; }
	/** ********提供给aop的方法，在aop的invoke之前，只能�?过这个方法得到request及其他参�?********* */
	
	public Boolean isSaveParameter = true;
	/** *******************负责初始化和转发的execute方法********************************/
	public String execute() {
		request					=	ServletActionContext.getRequest();
		response				=	ServletActionContext.getResponse();
		session					=	request.getSession();
		application				=	session.getServletContext();
		isAsyncRequest			=	false;
		actiomParametersMap		=	new HashMap<String,Object>();
		actiomReturnValuesMap	=	new HashMap<String,Object>();
		validateErrKeys			=	new ArrayList<String>();
		validataeJO				=	new JSONObject();
		msgList					=	new ArrayList<String>();
		//messageList             =   new MessageList();
		// 提取请求参数并加入参数map
		ActionUtil.setParametersFromRequestIntoParametersMap(actiomParametersMap, request);
		// 设置是否为异步请求的标志�?


		if(request.getParameter("asyncFlag")!=null)	{ 
			isAsyncRequest = true; 
			if(request.getParameter("ajaxForm")!=null)
				actiomReturnValuesMap.put("ajaxForm", true); 	//标记表单的异步提交（用于处理不能回写json的问�?-改为回写html�?


		}
		// 转发到请求的方法
		/*String action = request.getParameter(application.getInitParameter("dispathParam"));
		action = action==null?"index":action;
		
		Method[] declaredMethods = this.getClass().getDeclaredMethods();
		try {
			for (int i = 0; i < declaredMethods.length; i++) {
				logger.info(declaredMethods[i].getName());
				if (action.equals(declaredMethods[i].getName())) {
					String result = (String) declaredMethods[i].invoke(this);
					if (isAsyncRequest)	{
						setMessageIntoReturnValues();
						setAllReturnValuesIntoResponse();
						return null;
					}else {
						//setMessageIntoSession();
						//returnMsg(messageList);
						setMessageIntoReturnValues();
						
						//是否保存页面参数
						if(isSaveParameter){
							setAll();
						}else{
							isSaveParameter = true;
						}
						setAllReturnValuesIntoRequest();
						return result;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//logger.info("异常：没有找到请求的 "+action+" 方法...");
		return null;
	}
	/** *******************负责初始化和转发的execute方法******************************* */
	
	/** *********************************(私有方法)对域变量的操�?*********************************************** */
	private String		getParameterFromRequest(String key)				{ return ActionUtil.getParameterFromParametersMap(actiomParametersMap, key); }
	private String[] 	getParametersFromRequest(String key) 			{ return ActionUtil.getParametersFromParametersMap(actiomParametersMap, key); }
	private String 		getTheIp() 										{ return ActionUtil.getRemortIP(request); }
	private Object 		getAttributeFromSession(String key) 			{ return session.getAttribute(key); }
	private void 		setAttributeIntoSession(String key, Object o) 	{ session.setAttribute(key, o); }
	private void 		removeAttributeFromSession(String key) 			{ session.removeAttribute(key); }
	private void 		destoryTheSession() 							{ session.invalidate(); }
	private Object 		getAttributeFromApplication(String key)			{ return application.getAttribute(key); }
	/** *********************************私有方法************************************************ */
	
	
	/** *******************对参数的操作******************************* */
	// 获取request参数
	public String 		$(String key) 									{ return getParameterFromRequest(key); }
	public String[] 	$$(String key) 									{ return getParametersFromRequest(key); }
	public Object 		getFromSession(String key) 						{ return getAttributeFromSession(key); }
	public void 		setIntoSession(String key, Object o) 			{ setAttributeIntoSession(key, o); }
	public void 		removeFormSession(String key) 					{ removeAttributeFromSession(key); }
	public void 		destorySession() 								{ destoryTheSession(); }
	public String 		getIp() 										{ return getTheIp() ; }
	public Object 		getFromApplication(String key) 					{ return getAttributeFromApplication(key); }
	/** *******************对参数的操作******************************* */

	
	/** *********************************返回值的处理************************************************ */
	public void 		set(String key, Object o) 						{ actiomReturnValuesMap.put(key, o); }
	private void 		setAll() 										{ actiomReturnValuesMap.putAll(actiomParametersMap); }
	private void 		setAllReturnValuesIntoRequest() 				{ ActionUtil.setReturnValuesIntoRequest(actiomReturnValuesMap, request); }
	private void 	setAllReturnValuesIntoResponse() throws IOException { ActionUtil.setReturnValuesIntoResponse(actiomReturnValuesMap, response); }
	/** *********************************返回值的处理************************************************ */
	
	
	/** *******************负责解析消息******************************* */
	/*public void returnMsg(MessageList meesageList) {
		MessageElement e = null;
		for (int i = 0; i < meesageList.size(); i++) {
			e = (MessageElement) meesageList.get(i);
			//e.setMsgFormat(getText(e.getMsgId(), e.getValues()));
			//meesageList.set(i, e);
			//return messageList
			if(Constant.WARING_MESSAGE.equals(e.getMsgKind())){
				msgList.add(getText("警告信息:" + getText(e.getMsgId(), e.getValues())));
			}else if(Constant.ERROR_MESSAGE.equals(e.getMsgKind())){
				msgList.add(getText("错误信息:" + getText(e.getMsgId(), e.getValues())));
			}else if(Constant.INFO_MESSAGE.equals(e.getMsgKind())){
				msgList.add(getText("提示信息:" + getText(e.getMsgId(), e.getValues())));
			}else if(Constant.QA_MESSAGE.equals(e.getMsgKind())){
				msgList.add(getText("问题信息:" + getText(e.getMsgId(), e.getValues())));
			}else{
				msgList.add(getText("其他信息:" + getText(e.getMsgId(), e.getValues())));
			}
		}
	}*/
	/** *******************负责解析消息******************************* */
	/** *******************消息******************************* */
	public void 		message(String msg) 							{ msgList.add(msg); }
	public void 		setMessageIntoSession() 						{ ActionUtil.setActionMessageIntoSession(msgList, session); }
	public void 		setMessageIntoReturnValues() 					{ ActionUtil.setActionMessageIntoReturnValues(msgList, actiomReturnValuesMap, validateErrKeys.size()==0?true:false); }
	/** *******************session域内的提示消�?****************************** */


	/** *******************文件上传******************************* */
	public File[] 		getUploadFiles(String key) 						{ return ((MultiPartRequestWrapper)request).getFiles(key); }
	public String[] 	getUploadFileNames(String key) 					{ return ((MultiPartRequestWrapper)request).getFileNames(key); }
	public File getUploadFile(String key) {
		java.io.File[] files = ((MultiPartRequestWrapper)request).getFiles(key);
		return files==null?null:files[0];
	}
	public String getUploadFileName(String key) {
		String[] names = ((MultiPartRequestWrapper)request).getFileNames(key);
		return names==null?null:names[0];
	}
	public String copyFile(File src,String fileName){
		String extension = fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()).trim().toLowerCase();
		int m=fileName.indexOf(".");
		String name=fileName.substring(0, m);
		//String firstname = fileName.substring(fileName.indexOf(".")+1, fileName.length()).trim().toLowerCase();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String ssTime = df.format(new Date());
		//int n = (int)(Math.random()*9000000)+1000000;
		//fileName = name+ssTime+"_"+String.valueOf(n)+"."+extension;
		fileName = name+"_"+ssTime+"."+extension;
		//获取文件上传后在服务器保存的位置
		//注意要在Webcontent下创�?
		//attachments文件夹用于保存文�?
		String dirPath=
			ServletActionContext.getServletContext()
			.getRealPath("/attachments");
		//按照原文件名在images文件夹下构建文件
		String path = dirPath+"//"+fileName;
		File file=new File(path);
		//利用commons-io包中的FileUtiles类实现文件上�?
		try {
			FileUtils.copyFile(src, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	
	}
//	private boolean isExtensionAllowed(String fileName, String allowedList) { return FileUtil.isExtensionAllowed(fileName, allowedList); }
	
	public String getPath (){
		String path= session.getServletContext().getRealPath("")+"\\createexcelfile\\";
		return path;
	}
	/** *******************文件上传******************************* */
	/** *******************文件下载******************************* */
	// 用于把文件读入内�?
			private InputStream inputStream;
	public void download(String path)throws Exception{
		File file = new File(path);
		inputStream = new FileInputStream(file);
		
	}
	/** *******************文件下载******************************* */
	
	/** *******************导出Excel******************************* */	
	/**导出Excel
	 * @param list list内放String[] 并且第一个String[]和jsp页面的dategride的头�?��,后面的数据依次构建数据�?

	 * @return null
	 */
/*	public String getDownloadExcel(List<String[]> list){
		String path = getPath();
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String ssTime = df.format(new Date());
		int n = (int) (Math.random() * 9000000) + 1000000;
		String fileName = ssTime + "_" + String.valueOf(n) + ".xls";
		try {
			AbstractExcelFileWrite fr = (AbstractExcelFileWrite) ExcelFileWriteFactory.getInstance().getFileWriteInstance();
			short color = -1;
			short color2 = -1;
			fr.createNewSheet("Sheet1");
			fr.setField(list.get(0), (short) 13, color, color2);
			fr.setCellFormat((short) 10, color, color2);
			for (int i = 1; i < list.size(); i++) {
				if ((i - 1) % 500 == 0 && i != 1) {
					fr.creatNewRow(list.get(i));
					fr.setField(list.get(0), (short) 13, color, color2);
					fr.setCellFormat((short) 10, color, color2);					
				}
				fr.creatNewRow(list.get(i));
			}
			fr.WriteFile(path + fileName);
		} catch (Exception e) {
			//e.printStackTrace();
		}
		HttpServletResponse res = ServletActionContext.getResponse();
		res.setContentType("application/msexcel;charset=UTF-8");
		res.setHeader("Content-disposition", "attachment; filename=" + fileName);
		OutputStream out;
		try {
			out = res.getOutputStream();
			DataInputStream in = new DataInputStream(new FileInputStream(path + fileName));
			int len;
			byte[] data = new byte[10240];
			while ((len = in.read(data)) > 0)
				out.write(data, 0, len);
			out.flush();
			out.close();
			out = null;
		} catch (Exception e) {

		}
		return null;
	}
	/** *******************导出Excel******************************* */

	
	/** *******************分页查询******************************* */
	/*public Doi getPageDoi(){
		Doi doi = new Doi();
		if($("page")!=null)
			doi.setCurPage(Integer.parseInt($("page")));
		if($("rows")!=null)
			doi.setPageSize(Integer.parseInt($("rows")));
		return doi;
	}*/
	/** *******************分页查询******************************* */
	
	
	/** *******************异步验证方法******************************* */
/*	public boolean isFileEmpty(String key) {
		if (!validateErrKeys.contains(key) && getUploadFile(key)==null){
			validateErrKeys.add(key);
			validataeJO.put(key, "不能为空");
			return true;
		}
		return false;
	}
	public boolean isFileNotAllowed(String key, String ext) {
		if (!validateErrKeys.contains(key) && getUploadFile(key)!=null && !isExtensionAllowed(getUploadFileName(key), ext)){
			validateErrKeys.add(key);
			validataeJO.put(key, "允许的文件类型："+ext);
			return true;
		}
		return false;
	}
	public boolean isFileMaxLength(String key, int bytes) {
		if (!validateErrKeys.contains(key) && getUploadFile(key)!=null && getUploadFile(key).length()>bytes){
			validateErrKeys.add(key);
			validataeJO.put(key, "文件应小于："+bytes+"字节");
			return true;
		}
		return false;
	}
	*/
	/*public boolean isEmpty(String key) {
		if (!validateErrKeys.contains(key) && ValidationUtil.isEmpty($(key))){
			validateErrKeys.add(key);
			validataeJO.put(key, "不能为空");
			return true;
		}
		return false;
	}*/
	/*public boolean maxLength(String key, int max) {
		if (!validateErrKeys.contains(key) && $(key)!=null && ValidationUtil.maxLength($(key), max)){
			validateErrKeys.add(key);
			validataeJO.put(key, "长度不能超过"+max);
			return true;
		}
		return false;
	}
	public boolean minLength(String key, int max) {
		if (!validateErrKeys.contains(key) && !isEmpty(key) && ValidationUtil.minLength($(key), max)){
			validateErrKeys.add(key);
			validataeJO.put(key, "长度不少�?+max);
			return true;
		}
		return false;
	}
	public boolean isInt(String key) {
		if (!validateErrKeys.contains(key) && $(key)!=null && !ValidationUtil.isInt($(key))){
			validateErrKeys.add(key);
			validataeJO.put(key, "只允许整�?);
			return true;
		}
		return false;
	}
	public boolean isFloat(String key) {
		if (!validateErrKeys.contains(key) && $(key)!=null && !ValidationUtil.isFloat($(key))){
			validateErrKeys.add(key);
			validataeJO.put(key, "只允许浮点数");
			return true;
		}
		return false;
	}
	public boolean isDecimal(String key) {
		if (!validateErrKeys.contains(key) && $(key)!=null && !ValidationUtil.isDecimal($(key))){
			validateErrKeys.add(key);
			validataeJO.put(key, "1-8位整数最多两位小�?);
			return true;
		}
		return false;
	}
	public boolean isDouble(String key) {
		if (!validateErrKeys.contains(key) && $(key)!=null && !ValidationUtil.isDouble($(key))){
			validateErrKeys.add(key);
			validataeJO.put(key, "1-12位整数最多两位小�?);
			return true;
		}
		return false;
	}
	public boolean isCharNumber(String key) {
		if (!validateErrKeys.contains(key) && $(key)!=null && !ValidationUtil.isCharNumber($(key))){
			validateErrKeys.add(key);
			validataeJO.put(key, "只允许数字和英文");
			return true;
		}
		return false;
	}
	public boolean isDuplicate(String key ,boolean isDupli) {
		if (!validateErrKeys.contains(key) && isDupli){
			validateErrKeys.add(key);
			validataeJO.put(key, $(key)+"已经存在");
			return true;
		}
		return false;
	}
	public boolean notEqual(String key ,String value,String render) {
		if (!validateErrKeys.contains(key) && $(key)!=null && $(key).equals(value)){
			validateErrKeys.add(key);
			validataeJO.put(key, "不能为："+(render==null?value:render));
			return true;
		}
		return false;
	}
	
	
	public boolean validated() {
		if (!validataeJO.isEmpty()) {
			actiomReturnValuesMap.put("validateErr", validataeJO.toString());
			return false;
		}

		if (hasErrorMessage()) {
			actiomReturnValuesMap.put("messgaeListr", messageList);
			return false;
		}
		return true;
	}*/
	/** *******************异步验证方法******************************* */
	
	/*public MessageList getMessageList(){
		return messageList;
	}

	public void setIsSaveParameter(Boolean isSaveParameter) {
		this.isSaveParameter = isSaveParameter;
	}
	
	public Boolean hasErrorMessage(){
		MessageElement e = null;
		for (int i = 0; i < messageList.size(); i++) {
			e = (MessageElement) messageList.get(i);
			if(Constant.ERROR_MESSAGE.equals(e.getMsgKind())){
				return true;
			}
		}
		return false;
	}*/
	
	public void uploadFile(){
		  System.out.println("uploadFile");
		  Json json = new Json();
		  HttpServletRequest request = ServletActionContext.getRequest();
		  DiskFileItemFactory factory = new DiskFileItemFactory();
		  // 设置内存缓冲区，超过后写入临时文�?
		  factory.setSizeThreshold(10240000);
		  // 设置临时文件存储位置
		  String base = ServletActionContext.getServletContext().getRealPath("/document/upload");
		  File file = new File(base);
		  if(!file.exists())
		   file.mkdirs();
		  factory.setRepository(file);
		  ServletFileUpload upload = new ServletFileUpload(factory);
		  // 设置单个文件的最大上传�?
		  upload.setFileSizeMax(10002400000l);
		  // 设置整个request的最大�?
		  upload.setSizeMax(10002400000l);
		  upload.setHeaderEncoding("UTF-8");
		 
		  String ranStr = StringUtil.getRandom(3);
		 
		  try {
		   List<?> items = upload.parseRequest(request);
		   FileItem item = null;
		   String fileName = null;
		   for (int i = 0 ;i < items.size(); i++){
		    item = (FileItem) items.get(i);
		    //fileName = base + File.separator + item.getName();
		    fileName=base+File.separator+ranStr+"-"+item.getName();
		    // 保存文件
		    if (!item.isFormField() && item.getName().length() > 0) {
		     //setIntoSession("uploadFileUrl", item.getName());
		     json.setMsg("/document/upload/"+ranStr+"-"+item.getName());
		     item.write(new File(fileName));
		    }
		   }
		  } catch (FileUploadException e) {
		   e.printStackTrace();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		 
		 
		  json.setSuccess(true);
		  writeJson(json);
		 }
	
	/**
	 * 将对象转换成JSON字符串，并响应回前台
	 * 
	 * @param object
	 * @throws IOException
	 */
	public void writeJson(Object object) {
		try {
			// SerializeConfig serializeConfig = new SerializeConfig();
			// serializeConfig.setAsmEnable(false);
			String json = JSON.toJSONStringWithDateFormat(object,"yyyy-MM-dd HH:mm:ss",SerializerFeature.DisableCircularReferenceDetect);
			/*JsonConfig config = new JsonConfig();
			config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONArray subMsgs = JSONArray.fromObject(object, config);
			String json = subMsgs.toString();*/
			// String json = JSON.toJSONString(object, serializeConfig, SerializerFeature.PrettyFormat);
			//String json = JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat); 
			//String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			System.out.println(json);
			// String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss", SerializerFeature.PrettyFormat);
			ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
			ServletActionContext.getResponse().getWriter().flush();
			ServletActionContext.getResponse().getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}