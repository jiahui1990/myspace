package com.ggy.baby.monitorManage.action;

import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javacommon.base.action.BaseAction;
import javacommon.javautil.ExcelService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ggy.baby.monitorManage.model.ViewMonitor;
import com.ggy.baby.monitorManage.pageModel.MonitorManageIn;
import com.ggy.baby.monitorManage.pageModel.MonitorManageOut;
import com.ggy.baby.monitorManage.service.IMonitorManageService;
/**
 * 监控管理Action
 * @author Chencong
 *
 */
public class MonitorManageAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3871514834063782016L;
	/**
	 * 监控管理in参数
	 */
	private MonitorManageIn in = new MonitorManageIn();
	/**
	 * 监控管理service
	 */
	private IMonitorManageService monitorManageService;	
	
	/**
	 * 页数
	 */
	private Integer page;
	/**
	 * 搜索内容
	 */
	private String search = "";
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) throws UnsupportedEncodingException {
		this.search = URLDecoder.decode(search,"UTF-8");
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public MonitorManageIn getIn() {
		return in;
	}
	public void setIn(MonitorManageIn in) {
		this.in = in;
	}
	public IMonitorManageService getMonitorManageService() {
		return monitorManageService;
	}
	public void setMonitorManageService(IMonitorManageService monitorManageService) {
		this.monitorManageService = monitorManageService;
	}
	
	/**
	 * 初始化监控管理页面
	 * @author Chencong
	 * @return
	 */
	public String initMonitorManage(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			if(page==null){
				page=1;
			}
			in.setPage(page);
			in.setSearch(search);
			
			out = monitorManageService.initMonitorManage(in);

			getRequest().setAttribute("viewMonitors", out.getViewMonitorList());
			getRequest().setAttribute("kinders", out.getKindergartenList());
			
			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
			getRequest().setAttribute("search", search);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return out.getJumpInfo().getUrl();
	}
	/**
	 * 更新监控
	 * @author Chencong
	 * @return
	 */
	public String updateMonitor(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.updateMonitor(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		super.writeJson(out.getJumpInfo());
		return null;
	}
	
	/**
	 * 删除监控
	 * @author Chencong
	 * @return
	 */
	public String deleteMonitor(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.deleteMonitor(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		super.writeJson(out.getJumpInfo());
		return null;
	}
	
	/**
	 * 查询监控
	 * @author Chencong
	 * @return
	 */
	public String findMonitor(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.findMonitor(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		super.writeJson(out.getMonitorList());
		return null;
	}
	
	/**
	 * 根据幼儿园获取所有区域
	 * @author Chencong
	 * @return
	 */
	public String getArea(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.getArea(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getKindergartenAreaList());
		
		return null;
	}
	
	/**
	 * 获取幼儿园1级区域
	 * @author Chencong
	 * @return
	 */
	public String getOneLebelArea(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.getOneLebelArea(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getKindergartenAreaList());
		return null;
	}
	
	/**
	 * 获取幼儿园2级区域
	 * @author Chencong
	 * @return
	 */
	public String getTwoLebelArea(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.getTwoLebelArea(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getKindergartenAreaList());
		return null;
	}
	
	/**
	 * 进入添加监控页面
	 * @author Chencong
	 * @return
	 */
	public String initAddMonitor(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			if(page==null){
				page=1;
			}
			in.setPage(page);
			
			out = monitorManageService.initAddMonitor(in);
			
			getRequest().setAttribute("kinders", out.getKindergartenList());
			getRequest().setAttribute("viewMonitors", out.getViewMonitorList());

			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		
		return out.getJumpInfo().getUrl();
	}
	
	/**
	 * 添加监控
	 * @author Chencong
	 * @return
	 */
	public String addMonitor(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.addMonitor(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	/**
	 * 监控信息 幼儿园管理员页面
	 * @author Chencong
	 * @return
	 */
	public String monitorInfo(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			in.setUsers(getCurrentUser());
			
			if(page==null){
				page=1;
			}
			in.setPage(page);
			in.setSearch(search);
			
			out = monitorManageService.monitorInfo(in);

			getRequest().setAttribute("viewMonitors", out.getViewMonitorList());
			
			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
			getRequest().setAttribute("search", search);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return out.getJumpInfo().getUrl();
	}
	/**
	 * 导出监控
	 * @author Chencong
	 * @return
	 */
	public String exportMonitor(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			if(page==null){
				page=1;
			}
			in.setPage(page);
			in.setSearch(search);
			
			out = monitorManageService.exportMonitor(in);
			
			getResponse().setCharacterEncoding("UTF-8");
			getResponse().setHeader("Content-Disposition","attachment;fileName=monitor.xls");
			ExcelService.doopen(write(out),new DataOutputStream(getResponse().getOutputStream()));
			/*getRequest().setAttribute("viewMonitors", out.getViewMonitorList());
			
			getRequest().setAttribute("page", page);
			getRequest().setAttribute("pages", out.getPages());
			getRequest().setAttribute("search", search);*/
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return null;
	}
	/**
	 * 批量删除监控
	 * @author Chencong
	 * @return
	 */
	public String batchDeleteMonitor(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.batchDeleteMonitor(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	/**
	 * 保存所有未保存的监控
	 * @author Chencong
	 * @return
	 */
	public String saveMonitors(){
		MonitorManageOut out = new MonitorManageOut();
		
		try {
			out = monitorManageService.saveMonitors(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	
	/**
	 * 格式化导出excel的json
	 * @author Chencong
	 * @param out
	 * @return
	 */
	private JSONArray write(MonitorManageOut out) {
		List<ViewMonitor> monitors = out.getViewMonitorList();
		JSONArray arrayObject = new JSONArray();
		
		if(monitors == null||monitors.size()<=0){
			return arrayObject;
		}
		
		JSONObject monitorName = new JSONObject();
	    JSONObject sn = new JSONObject();
	    JSONObject updateTime = new JSONObject();
	    JSONObject status = new JSONObject();
	    JSONObject timeQuantum = new JSONObject();
	    JSONObject kinder = new JSONObject();
	    JSONObject area = new JSONObject();
	    monitorName.put("name", "设备名称");
	    sn.put("name", "sn");
	    updateTime.put("name", "最后更新时间");
	    status.put("name", "状态");
	    timeQuantum.put("name", "摄像头观看时间段");
	    kinder.put("name", "幼儿园");
	    area.put("name", "班级");
	    
	    JSONArray array_monitorName = new JSONArray();
	    JSONArray array_sn = new JSONArray();
	    JSONArray array_updateTime = new JSONArray();
	    JSONArray array_status = new JSONArray();
	    JSONArray array_timeQuantum = new JSONArray();
	    JSONArray array_kinder = new JSONArray();
	    JSONArray array_area = new JSONArray();
		
		for (ViewMonitor monitor : monitors) {
			array_monitorName.add(monitor.getMonitorName());
			array_sn.add(monitor.getSn());
			array_timeQuantum.add(monitor.getTimeQuantum());
			array_kinder.add(monitor.getKindergartenName());
			array_area.add(monitor.getAreaName());
		}
		
		monitorName.put("value", array_monitorName);
		sn.put("value", array_sn);
		updateTime.put("value", array_updateTime);
		status.put("value", array_status);
		timeQuantum.put("value", array_timeQuantum);
		kinder.put("value", array_kinder);
		area.put("value", array_area);
		
		arrayObject.add(monitorName);
		arrayObject.add(sn);
		arrayObject.add(updateTime);
		arrayObject.add(status);
		arrayObject.add(timeQuantum);
		arrayObject.add(kinder);
		arrayObject.add(area);
		
		return arrayObject;
	}
}
