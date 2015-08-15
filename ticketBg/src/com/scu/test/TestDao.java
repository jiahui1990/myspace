package com.scu.test;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scu.bean.PlayDetail;
import com.scu.bean.SalorForm;
import com.scu.dto.PlayDetailDto;
import com.scu.service.FieldInfoService;
import com.scu.service.SaleTimeScheduleService;
import com.scu.service.SalorService;
import com.scu.utils.Consts;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TestDao{
	
	@Resource
	public SalorService salorService;
	@Resource
	public FieldInfoService projectInfoService;
	@Resource
	public SaleTimeScheduleService saleTimeScheduleService;
	
	@Test
	public void testDAO(){
		/*SalorForm salorForm = new SalorForm();
		salorForm.setUserAccount("admin");
		System.out.println(salorService.findByExample(salorForm).get(0).getUserPassword());*/
		
		List list = saleTimeScheduleService.findAll();
		System.out.println(list.get(0));
		
		/*List<PlayDetailDto> list = salorService.executeSql(Consts.SQL_JIFEI,PlayDetailDto.class,"12896701");
		System.out.println(list.get(0).getTotal());*/
	}
	
	public static void main(String[] args) {
		//System.out.println(60001%1000);
		/*Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		timestamp.setHours(12);
		System.out.println(timestamp);*/
		
		/*double b = 9.7;
		String c = String.valueOf(b);
		String[] strs = c.split("\\.");
		System.out.println(strs[0]);
		System.out.println(strs[1]);*/
		
		System.out.println(30/60.000);
	}

}
