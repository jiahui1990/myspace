package com.scu.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scu.bean.FieldInfo;
import com.scu.dao.FieldInfoDAO;


@Scope("prototype")
@Service("fieldInfoService")
public class FieldInfoService extends BaseService<FieldInfo>{
	
	@Resource
	private FieldInfoDAO fieldInfoDAO;
	
	@Resource(name = "fieldInfoDAO")
	public void setDAO(FieldInfoDAO dao){
		this.fieldInfoDAO =  dao;
		super.baseDAO = dao;
	}
	
}
