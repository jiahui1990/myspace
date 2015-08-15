package com.scu.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.scu.bean.FieldInfo;
import com.scu.dto.Json;

@Scope("prototype")
@Controller("fieldInfoAction")
public class FieldInfoAction extends BaseAction implements ModelDriven<FieldInfo>{
	
	private static final long serialVersionUID = 1L;
	private FieldInfo fieldInfo = new FieldInfo(); 
	Json j = new Json();
	
	private List<FieldInfo> list;
	public FieldInfoAction() {
		execute();
	}
	
	public void getAllFields(){
		list = fieldInfoService.findAll();
		writeJson(list);
	}
	
	public void save(){
		fieldInfoService.saveOrUpdate(fieldInfo);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("操作成功!");
		writeJson(j);
	}
	
	public void delete(){
		fieldInfoService.deleteObject(fieldInfo);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("删除用户成功!");
		writeJson(j);
	}
	
//-------------------------------------------------------------------------------------------

	public FieldInfo getModel() {
		return fieldInfo;
	}
	
	public FieldInfo getFieldInfo() {
		return fieldInfo;
	}

	public void setFieldInfo(FieldInfo fieldInfoForm) {
		this.fieldInfo = fieldInfoForm;
	}

	public List<FieldInfo> getList() {
		return list;
	}

	public void setList(List<FieldInfo> list) {
		this.list = list;
	}

}
