package com.scu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.scu.bean.SalorForm;
import com.scu.dto.Json;
import com.scu.service.SalorService;
import com.scu.utils.DataGrid;
import com.scu.utils.StringUtil;

@Scope("prototype")
@Controller("salorAction")
public class SalorAction extends BaseAction implements ModelDriven<SalorForm>{
	
	private static final long serialVersionUID = 1L;
	private SalorForm salor = new SalorForm(); 
	Json j = new Json();
	
	private List<SalorForm> list;
	public SalorAction() {
		execute();
	}
	
	/**
	 * 通过判断账号密码类型是否与数据库记录匹配对用户进行判
	 * 如果有匹配记录，登录成功，到管理界面；否则登录失败，返回登录界面
	 */
	public void login(){
		list = salorService.findByExample(salor);
		if(list.size()>0){
			//SessionUtil.setSalor(list.get(0));
			//SessionUtil.setObject("username", list.get(0).getSalorname());
			//SessionUtil.setObject("id", list.get(0).getId()+"");
			j.setMsg("loginSuc");
		}else{
			j.setMsg("loginFail");
		}
		super.writeJson(j);
	}
	
	/**
	 * 从session里remove当前登录用户，实现注�?���?	 * @return String
	 */
	public String logout(){
		//SessionUtil.removeSalor();
		return "logout";
	}
	
	public void reg(){
//		salorForm.setPermission(StringUtil.luanma(salorForm.getPermission(), 1));
//		salorForm.setNickname(StringUtil.luanma(salorForm.getNickname(), 1));
		salorService.saveOrUpdate(salor);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("注册成功!");
		writeJson(j);
	}
	
	public void editPassword(){
		/*Json json = new Json();
		salorForm = salorService.findById(salorForm.getId());
		if(!oldPwd.equals(salorForm.getPassword())){
			json.setSuccess(false);
			json.setMsg("旧密码输入错�?");
			writeJson(json);
		}else{
			salorForm = salorService.findById(salorForm.getId());
			salorForm.setPassword(newPwd);
			salorService.saveOrUpdate(salorForm);
			
			json.setSuccess(true);
			json.setMsg("修改密码成功!");
			writeJson(json);
			
		}*/
	}
	
	public void save(){
		salorService.saveOrUpdate(salor);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("操作成功!");
		writeJson(j);
	}
	
	public void delete(){
		salorService.deleteObject(salor);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除用户成功!");
		writeJson(j);
	}
	
//-------------------------------------------------------------------------------------------

	public SalorForm getModel() {
		return salor;
	}
	
	public SalorService getSalorService() {
		return salorService;
	}

	public SalorForm getSalorForm() {
		return salor;
	}

	public void setSalorForm(SalorForm salorForm) {
		this.salor = salorForm;
	}

	public void setSalorService(SalorService salorService) {
		this.salorService = salorService;
	}

	public List<SalorForm> getList() {
		return list;
	}

	public void setList(List<SalorForm> list) {
		this.list = list;
	}

	public SalorForm getSalor() {
		return salor;
	}

	public void setSalor(SalorForm salorForm) {
		this.salor = salorForm;
	}
	
}
