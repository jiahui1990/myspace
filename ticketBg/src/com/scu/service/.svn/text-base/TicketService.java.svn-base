package com.scu.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scu.bean.SaleTicketForm;
import com.scu.dao.TicketDAO;


@Scope("prototype")
@Service("ticketService")
public class TicketService extends BaseService<SaleTicketForm>{
	
	@Resource
	private TicketDAO ticketDAO;
	
	@Resource(name = "ticketDAO")
	public void setDAO(TicketDAO dao){
		this.ticketDAO =  dao;
		super.baseDAO = dao;
	}
	
}
