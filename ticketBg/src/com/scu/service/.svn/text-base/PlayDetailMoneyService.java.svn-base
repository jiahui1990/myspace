package com.scu.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scu.bean.PlayDetailMoney;
import com.scu.dao.PlayDetailMoneyDAO;


@Scope("prototype")
@Service("playDetailMoneyService")
public class PlayDetailMoneyService extends BaseService<PlayDetailMoney>{
	
	@Resource
	private PlayDetailMoneyDAO playDetailMoneyDAO;
	
	@Resource(name = "playDetailMoneyDAO")
	public void setDAO(PlayDetailMoneyDAO dao){
		this.playDetailMoneyDAO =  dao;
		super.baseDAO = dao;
	}
	
}
