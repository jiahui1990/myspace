package com.scu.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scu.bean.SaleTimeSchedule;
import com.scu.dao.SaleTimeScheduleDAO;


@Scope("prototype")
@Service("saleTimeScheduleService")
public class SaleTimeScheduleService extends BaseService<SaleTimeSchedule>{
	
	@Resource
	private SaleTimeScheduleDAO saleTimeScheduleDAO;
	
	@Resource(name = "saleTimeScheduleDAO")
	public void setDAO(SaleTimeScheduleDAO dao){
		this.saleTimeScheduleDAO =  dao;
		super.baseDAO = dao;
	}
	
}
