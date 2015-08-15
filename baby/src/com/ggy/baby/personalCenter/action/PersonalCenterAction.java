package com.ggy.baby.personalCenter.action;

import javacommon.base.action.BaseAction;

import com.ggy.baby.personalCenter.pageModel.PersonalCenterIn;
import com.ggy.baby.personalCenter.pageModel.PersonalCenterOut;
import com.ggy.baby.personalCenter.service.IPersonalCenterService;
/**
 * 个人中心Action
 * @author Chencong
 *
 */
public class PersonalCenterAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7810547442062924294L;
	/**
	 * 个人中心in参数
	 */
	private PersonalCenterIn in = new PersonalCenterIn();
	/**
	 * 个人中心service
	 */
	private IPersonalCenterService personalCenterService;	
	
	public PersonalCenterIn getIn() {
		return in;
	}
	public void setIn(PersonalCenterIn in) {
		this.in = in;
	}
	public IPersonalCenterService getPersonalCenterService() {
		return personalCenterService;
	}
	public void setPersonalCenterService(IPersonalCenterService personalCenterService) {
		this.personalCenterService = personalCenterService;
	}
	/**
	 * 初始化个人中心
	 * @author Chencong
	 * @return
	 */
	public String initPersonalCenter(){
		PersonalCenterOut out = new PersonalCenterOut();
		
		try {
			in.getUsers().setId(getCurrentUserId());
			
			out = personalCenterService.initPersonalCenter(in);
			
			getRequest().setAttribute("user", out.getUsers());
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		return out.getJumpInfo().getUrl();
	}
	/**
	 * 更新个人信息
	 * @author Chencong
	 * @return
	 */
	public String updateUsers(){
		PersonalCenterOut out = new PersonalCenterOut();
		
		try {
			in.getUsers().setId(getCurrentUserId());
			out = personalCenterService.updateUsers(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		return null;
	}
	/**
	 * 更新用户手机号码
	 * @author Chencong
	 * @return
	 */
	public String updateUserPhone(){
		PersonalCenterOut out = new PersonalCenterOut();
		
		try {
			in.getUsers().setId(getCurrentUserId());
			out = personalCenterService.updateUserPhone(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		return null;
	}
}
