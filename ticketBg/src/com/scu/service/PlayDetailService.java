package com.scu.service;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.scu.bean.PlayDetail;
import com.scu.dao.PlayDetailDAO;


@Scope("prototype")
@Service("playDetailService")
public class PlayDetailService extends BaseService<PlayDetail>{
	
	@Resource
	private PlayDetailDAO playDetailDAO;
	
	@Resource(name = "playDetailDAO")
	public void setDAO(PlayDetailDAO dao){
		this.playDetailDAO =  dao;
		super.baseDAO = dao;
	}
	
}
