package com.scu.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scu.bean.FieldMoneyXihua;
import com.scu.dao.FieldMoneyXihuaDAO;


@Scope("prototype")
@Service("fieldMoneyXihuaService")
public class FieldMoneyXihuaService extends BaseService<FieldMoneyXihua>{
	
	@Resource
	private FieldMoneyXihuaDAO fieldMoneyXihuaDAO;
	
	@Resource(name = "fieldMoneyXihuaDAO")
	public void setDAO(FieldMoneyXihuaDAO dao){
		this.fieldMoneyXihuaDAO =  dao;
		super.baseDAO = dao;
	}
	
}
