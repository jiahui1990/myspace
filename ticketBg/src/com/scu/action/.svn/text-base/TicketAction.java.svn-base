package com.scu.action;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.scu.bean.SaleTicketForm;
import com.scu.dto.Json;

@Scope("prototype")
@Controller("ticketAction")
public class TicketAction extends BaseAction implements ModelDriven<SaleTicketForm>{
	
	private static final long serialVersionUID = 1L;
	private SaleTicketForm saleTicketForm = new SaleTicketForm(); 
	Json j = new Json();
	
	private List<SaleTicketForm> list;
	public TicketAction() {
		execute();
	}
	
	public void getTicketByTicketID(){
		list =  ticketService.findByExample(saleTicketForm);
		if(list.size()==1){
			saleTicketForm = list.get(0);
			writeJson(saleTicketForm);
		}else{
			j.setMsg("noInfo");
			writeJson(j);
			System.out.println("无此ticket_ID信息");
		}
	}
	
	/*public void openCard(){
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		saleTicketForm.setTicketDateSold(d);
		ticketService.saveOrUpdate(saleTicketForm);
		j.setMsg("openSuc");
		writeJson(j);
	}*/
	
	public void save(){
		ticketService.saveOrUpdate(saleTicketForm);
		Json j = new Json();
		j.setSuccess(true);
		j.setMsg("操作成功!");
		writeJson(j);
	}
	
//-------------------------------------------------------------------------------------------

	public SaleTicketForm getModel() {
		return saleTicketForm;
	}
	
	public SaleTicketForm getTicket() {
		return saleTicketForm;
	}

	public void setTicket(SaleTicketForm ticketForm) {
		this.saleTicketForm = ticketForm;
	}

	public List<SaleTicketForm> getList() {
		return list;
	}

	public void setList(List<SaleTicketForm> list) {
		this.list = list;
	}

	public SaleTicketForm getSalor() {
		return saleTicketForm;
	}

	public void setSalor(SaleTicketForm ticketForm) {
		this.saleTicketForm = ticketForm;
	}
	
}
