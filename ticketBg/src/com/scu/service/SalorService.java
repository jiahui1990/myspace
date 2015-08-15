package com.scu.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scu.bean.SalorForm;
import com.scu.dao.SalorDAO;


@Scope("prototype")
@Service("salorService")
public class SalorService extends BaseService<SalorForm>{
	
	@Resource
	private SalorDAO salorDAO;
	
	@Resource(name = "salorDAO")
	public void setDAO(SalorDAO dao){
		this.salorDAO =  dao;
		super.baseDAO = dao;
	}
	
}
