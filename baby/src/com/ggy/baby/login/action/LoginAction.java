package com.ggy.baby.login.action;

import javacommon.base.action.BaseAction;
import javacommon.base.model.GlobalVariable;

import com.ggy.baby.login.pageModel.LoginIn;
import com.ggy.baby.login.pageModel.LoginOut;
import com.ggy.baby.login.service.ILoginService;
/**
 * 登陆Action
 * @author Chencong
 *
 */
public class LoginAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7381511500778964210L;
	/**
	 * 登陆in参数
	 */
	private LoginIn in = new LoginIn();
	/**
	 * 登陆service
	 */
	private ILoginService loginService;	
	
	public LoginIn getIn() {
		return in;
	}
	public void setIn(LoginIn in) {
		this.in = in;
	}
	public ILoginService getLoginService() {
		return loginService;
	}
	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}
	/**
	 * 登陆
	 * @author Chencong
	 * @return
	 */
	public String login(){
		LoginOut out = new LoginOut();
		try {
			out = loginService.login(in);
			
			if("MDGV1".equals(out.getJumpInfo().getIsSuccess())){
				getSession().setAttribute(getGlobalvariable(new GlobalVariable("MDGV5")).getContent(), out.getUsers());
				
				String macKey = out.getUsers().getId().toString();
				Object mac = getAppliction().getAttribute(macKey);
				Object macTime = getAppliction().getAttribute(macKey+"time");
				if(mac!=null && !mac.equals(in.getMac())){
					if(macTime!=null){
						Long macTimeVal = Long.valueOf(macTime.toString());
						if(System.currentTimeMillis()-macTimeVal<=10*1000){
							out.getJumpInfo().setFlag("MSG4006");
						}else{
							getAppliction().setAttribute(macKey, in.getMac());
						}
					}else{
						getAppliction().setAttribute(macKey, in.getMac());						
					}
				}else{
					getAppliction().setAttribute(macKey, in.getMac());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out);
		
		return null;
	}
	/**
	 * 发送手机验证码
	 * @author Chencong
	 * @return
	 */
	public String sendPhoneCode(){
		LoginOut out = new LoginOut();
		try {
			out = loginService.sendPhoneCode(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	/**
	 * 登陆时有其他设备在线时，确认上线。
	 * @author Chencong
	 * @return
	 */
	public String confirmOnline(){
		LoginOut out = new LoginOut();
		try {
			in.setUsers(getCurrentUser());
			out = loginService.confirmOnline(in);
			
			if(out.getJumpInfo().getIsSuccess().equals("MDGV1")){
				getAppliction().setAttribute(out.getUsers().getId().toString(), in.getMac());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		super.writeJson(out.getJumpInfo());
		
		return null;
	}
	/**
	 * 获取自己当前是否在线
	 * @author Chencong
	 * @return
	 */
	public String online(){
		LoginOut out = new LoginOut();
		try {
			String macKey = getCurrentUserId().toString();
			getAppliction().setAttribute(macKey+"time", System.currentTimeMillis());
			
			Object macObj = getAppliction().getAttribute(macKey);
			if(macObj==null){
				out.getJumpInfo().setFlag("MSG4011");
			}else{
				if(macObj.toString().equals(in.getMac())){
					out.getJumpInfo().setFlag("MSG0001");
				}else{
					out.getJumpInfo().setFlag("MSG4011");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		return null;
	}
	/**
	 * 退出登陆
	 * @author Chencong
	 * @return
	 */
	public String loginoff(){
		LoginOut out = new LoginOut();
		try {
			getAppliction().removeAttribute(getCurrentUserId().toString());
			getSession().removeAttribute(getGlobalvariable(new GlobalVariable("MDGV5")).getContent());
			
			out.getJumpInfo().setFlag("MSG6015");
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		
		return out.getJumpInfo().getUrl();
	}
	/**
	 * 找回密码发送手机验证码
	 * @author Chencong
	 * @return
	 */
	public String sendForgotPassCode(){
		LoginOut out = new LoginOut();
		try {
			out = loginService.sendForgotPassCode(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		return null;
	}
	/**
	 * 验证找回密码的验证码
	 * @author Chencong
	 * @return
	 */
	public String vertifyForgotPassCode(){
		LoginOut out = new LoginOut();
		try {
			out = loginService.vertifyForgotPassCode(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		return null;
	}
	/**
	 * 修改密码
	 * @author Chencong
	 * @return
	 */
	public String modifyPassword(){
		LoginOut out = new LoginOut();
		try {
			out = loginService.modifyPassword(in);
		} catch (Exception e) {
			e.printStackTrace();
			out.getJumpInfo().setFlag("MSG4001");
		}
		
		super.writeJson(out.getJumpInfo());
		return null;
	}
}
