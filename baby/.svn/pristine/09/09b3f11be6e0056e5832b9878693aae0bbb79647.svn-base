package com.ggy.baby.userMonitor.action;

import java.util.ArrayList;
import java.util.List;

import javacommon.base.action.BaseAction;

import com.alibaba.fastjson.JSONObject;
import com.ggy.baby.monitorManage.model.KindergartenArea;
import com.ggy.baby.monitorManage.model.ViewMonitor;
import com.ggy.baby.userMonitor.pageModel.UserMonitorIn;
import com.ggy.baby.userMonitor.pageModel.UserMonitorOut;
import com.ggy.baby.userMonitor.service.IUserMonitorService;
/**
 * 用户监控Action
 * @author Chencong
 *
 */
public class UserMonitorAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6752474548540712232L;
	/**
	 * 用户监控in参数
	 */
	private UserMonitorIn in = new UserMonitorIn();
	/**
	 * 用户监控service
	 */
	private IUserMonitorService userMonitorService;	
	
	public UserMonitorIn getIn() {
		return in;
	}
	public void setIn(UserMonitorIn in) {
		this.in = in;
	}
	public IUserMonitorService getUserMonitorService() {
		return userMonitorService;
	}
	public void setUserMonitorService(IUserMonitorService userMonitorService) {
		this.userMonitorService = userMonitorService;
	}
	/**
	 * 实时监控 管理员
	 * @author Chencong
	 * @return
	 */
	public String actualTimeMonitor(){
		UserMonitorOut out = new UserMonitorOut();
		
		try {
			out = userMonitorService.actualTimeMonitor(in);
			
			getRequest().setAttribute("kinders", out.getKindergartenList());
		} catch (Exception e) {
			out.getJumpInfo().setFlag("MSG4001");
			e.printStackTrace();
		}
		
		return out.getJumpInfo().getUrl();
	}
	/**
	 * 获取幼儿园所有区域
	 * @author Chencong
	 * @return
	 */
	public String getAreas(){
		UserMonitorOut out = new UserMonitorOut();
		
		try {
			out = userMonitorService.getAreas(in);
			
		} catch (Exception e) {
			out.getJumpInfo().setFlag("MSG4001");
			e.printStackTrace();
		}
		
		super.writeJson(out.getAreaList());
		
		return null;
	}
	
	/**
	 * 获取幼儿园所有的监控
	 * @author Chencong
	 * @return
	 */
	public String getMonitor(){
		UserMonitorOut out = new UserMonitorOut();
		
		try {
			out = userMonitorService.getMonitor(in);
			
		} catch (Exception e) {
			out.getJumpInfo().setFlag("MSG4001");
			e.printStackTrace();
		}
		
		
		
		super.writeJson(generateMonitorTree(out));
		
		return null;
	}
	/**
	 * 构建监控树
	 * @author Chencong
	 * @param out
	 * @return
	 */
	private List<JSONObject> generateMonitorTree(UserMonitorOut out){
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		for (KindergartenArea parea : out.getAreaList()) {
			if(parea.getPid()==0l){
				JSONObject json = generateByArea(parea);
				
				List<JSONObject> careaList = getChildAreaList(parea, out);
				
				if(careaList!=null&&careaList.size()>0){
					json.put("children", careaList);
				}
				
				jsonList.add(json);
			}
		}
		
		return jsonList;
	}
	/**
	 * 根据父区域构建子区域集合
	 * @author Chencong
	 * @param parea
	 * @param out
	 * @return
	 */
	private List<JSONObject> getChildAreaList(KindergartenArea parea,UserMonitorOut out){
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		for (KindergartenArea carea : out.getAreaList()) {
			if(carea.getPid().equals(parea.getId())){
				JSONObject json = generateByArea(carea);
				
				List<JSONObject> monitorList = getMonitorList(carea,out);
				if(monitorList!=null&&monitorList.size()>0){
					json.put("children", monitorList);
				}
				
				jsonList.add(json);
			}
		}
		
		return jsonList;
	}
	/**
	 * 构建jsonObject通过区域
	 * @author Chencong
	 * @param area
	 * @return
	 */
	private JSONObject generateByArea(KindergartenArea area){
		JSONObject json = new JSONObject();
		json.put("id", area.getId());
		json.put("name", area.getName());		
		return json;
	}
	/**
	 * 构建jsonObject通过监控
	 * @author Chencong
	 * @param monitor
	 * @return
	 */
	private JSONObject generateByMonitor(ViewMonitor monitor){
		JSONObject json = new JSONObject();
		json.put("id", monitor.getMonitorId());
		json.put("name", monitor.getMonitorName());	
		json.put("sn", monitor.getSn());	
		return json;
	}
	/**
	 * 根据区域获取所有监控
	 * @author Chencong
	 * @param area
	 * @param out
	 * @return
	 */
	private List<JSONObject> getMonitorList(KindergartenArea area,UserMonitorOut out){
		List<JSONObject> jsonList = new ArrayList<JSONObject>();
		
		for (ViewMonitor monitor : out.getViewMonitorList()) {
			if(monitor.getAreaId().equals(area.getId())){
				JSONObject json = generateByMonitor(monitor);
				
				jsonList.add(json);
			}
		}
		
		return jsonList;
	}
	
	/**
	 * 个人监控
	 * @author Chencong
	 * @return
	 */
	public String userMonitor(){
		UserMonitorOut out = new UserMonitorOut();
		
		try {
			in.setUsers(getCurrentUser());
			
			out = userMonitorService.userMonitor(in);
			
			getSession().setAttribute("area_info", "欢迎"+in.getUsers().getUsername()+"用户,您的孩子所在班级："+out.getUserArea().getAreaId().getName());
			/*getRequest().setAttribute("userArea", out.getUserArea());*/
			getRequest().setAttribute("monitors", out.getViewMonitorList());
			
			getRequest().setAttribute("time", System.currentTimeMillis());
		} catch (Exception e) {
			out.getJumpInfo().setFlag("MSG4001");
			e.printStackTrace();
		}
		
		return out.getJumpInfo().getUrl();
	}
	
	/**
	 * 实时监控 幼儿园管理员管理员
	 * @author Chencong
	 * @return
	 */
	public String actualTimeMonitorK(){
		UserMonitorOut out = new UserMonitorOut();
		
		try {
			in.setUsers(getCurrentUser());
			
			out = userMonitorService.actualTimeMonitor_K(in);
			
			getRequest().setAttribute("kin", out.getUserKinder().getKindergartenId());
		} catch (Exception e) {
			out.getJumpInfo().setFlag("MSG4001");
			e.printStackTrace();
		}
		
		return out.getJumpInfo().getUrl();
	}
}
