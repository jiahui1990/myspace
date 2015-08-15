package com.scu.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.scu.bean.FieldMoneyXihua;
import com.scu.bean.PlayDetailMoney;
import com.scu.bean.SaleTicketForm;
import com.scu.bean.SaleTimeSchedule;
import com.scu.dto.Json;
import com.scu.dto.PlayDetailDto;
import com.scu.utils.Consts;
import com.scu.utils.StringUtil;

@Scope("prototype")
@Controller("playAction")
public class PlayAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	public PlayDetailMoney playDetailMoney = new PlayDetailMoney();
	public PlayDetailDto playDetailDto = new PlayDetailDto();
	Json j = new Json();
	
	private List<PlayDetailMoney> playDetailMoneyOfAllFieldList;
	private List<PlayDetailMoney> playDetailMoneyOfCurrentFieldList;
	private List<SaleTicketForm> saleTicketFormList;
	private List<FieldMoneyXihua> fieldMoneyXihuaList;
	
	public PlayAction() {
		execute();
	}
	
	/**
	 * 刷卡(分记次扣费和计时扣费)
	 */
	@Transactional
	public void shuaka(){
		/********************************************参数获取*******************************************/
		String ticketId = $("ticketId"); 
		String field = StringUtil.luanma($("field")); 
		String count = $("count");
		System.out.println("ticketId:"+ticketId+",field:"+field+",count:"+count);
		/********************************************参数获取*******************************************/
		
		/******************初始化saleTicketFormList、playDetailMoneyList、playDetailDto******************/
		SaleTicketForm saleTicketForm = new SaleTicketForm();
		saleTicketForm.setTicketId(ticketId);
		saleTicketFormList = ticketService.findByExample(saleTicketForm);
		
		playDetailMoney = new PlayDetailMoney();
		playDetailMoney.setTicketId(ticketId);
		playDetailMoneyOfAllFieldList = playDetailMoneyService.findByExample(playDetailMoney);//得到此卡号所有项目的游玩记录
		playDetailMoney.setField(field);
		playDetailMoneyOfCurrentFieldList = playDetailMoneyService.findByExample(playDetailMoney);//得到此卡号当前项目的游玩记录
		
		FieldMoneyXihua fieldMoneyXihua = new FieldMoneyXihua();
		fieldMoneyXihua.setField(field);
		fieldMoneyXihuaList = fieldMoneyXihuaService.findByExample(fieldMoneyXihua);
		
		//初始化playDetailDto的restMoney,perMoney,lowestMoney,total
		initMoney(saleTicketFormList, playDetailMoneyOfAllFieldList, fieldMoneyXihuaList,playDetailDto);
		playDetailDto.setField(field);
		playDetailDto.setTicketId(ticketId);
		playDetailDto.setCountToCostByCi(count);
		/******************初始化saleTicketFormList和playDetailMoneyList、playDetailDto******************/
		
		if(playDetailDto.perMoney != null && playDetailDto.lowestMoney != null){
			if(saleTicketFormList.size()==1){//有效卡
				saleTicketForm = saleTicketFormList.get(0);
				
				//判断卡是否已开通
				if(Consts.STR_CARD_NOT_USED.equals(saleTicketForm.getTicketIsUsed())){
					//判断游玩项目是否已结束游玩，boolPlayStatus=true表示其他项目未游玩或已游玩结束
					boolean boolPlayStatus = checkPlayStatus(playDetailMoneyOfAllFieldList,field);
					if(boolPlayStatus){
						if(playDetailDto.lowestMoney <= playDetailDto.restMoney){
							if(Consts.FIELD_QT.equals(field)){//汽艇记次扣费
								int i_count = Integer.parseInt(playDetailDto.countToCostByCi);
								if((playDetailDto.perMoney)*i_count <= playDetailDto.restMoney){
									costByCi(playDetailDto,playDetailMoneyOfCurrentFieldList);
								}else{
									playDetailDto.setMsg(Consts.MSG_CARD_MONEY_NOT_ENOUGH+"");
								}
							}else if(Consts.FIELD_PPC.equals(field)||Consts.FIELD_DDC.equals(field)||Consts.FIELD_JTC.equals(field)){//计时扣费项目
							//计时操作
								//计时项目能游玩多次的情况
								deleteDulplicateDataOfPlay(playDetailMoneyOfCurrentFieldList);
								if(ifAllFieldFinished(playDetailMoneyOfCurrentFieldList)){//开始计时
									startTiming(playDetailDto,playDetailMoneyOfCurrentFieldList);
								}else{//停止计时
									for (int i = 1; i < playDetailMoneyOfCurrentFieldList.size(); i++) {
										if(playDetailMoneyOfCurrentFieldList.get(i).getTicketDateEnd() == null){
											playDetailMoney = playDetailMoneyOfCurrentFieldList.get(i);
										}
									}
									endTiming(playDetailDto,playDetailMoney);
								}
								
								/*计时项目只能游玩一次的情况
								if(playDetailMoneyOfCurrentFieldList.size()==1&&playDetailMoneyOfCurrentFieldList.get(0).getTicketDateEnd()==null){
									playDetailMoney = playDetailMoneyOfCurrentFieldList.get(0);
									endTiming(playDetailDto,playDetailMoney);
								}else if(playDetailMoneyOfCurrentFieldList.size()>1&&playDetailMoneyOfCurrentFieldList.get(0).getTicketDateEnd()==null){//重复数据的删除
									deleteDulplicateDataOfPlay(playDetailMoneyOfCurrentFieldList);
									playDetailMoney = playDetailMoneyOfCurrentFieldList.get(0);
									endTiming(playDetailDto,playDetailMoney);
								}else{//游玩结束后的重复刷卡(此处包含field等于当前游乐项目和不等于当前游乐项目两种情况)
									playDetailMoney = playDetailMoneyOfCurrentFieldList.get(0);
									playDetailDto.updateData(playDetailMoney);
									playDetailDto.setMsg(Consts.MSG_CARD_END_DUPLICATE+"");
								}*/

							}else{//前台请求的游玩项目在数据库中不存在
								playDetailDto.setMsg(Consts.MSG_FIELD_ERROR+"");
							}
						}else{//卡内余额不足
							playDetailDto.setMsg(Consts.MSG_CARD_MONEY_NOT_ENOUGH+"");
						}
					}else{//存在其他项目未结束游玩
						playDetailDto.setMsg(Consts.MSG_CARD_OTHER_NOT_END+"");
					}	
				}else{//此卡未开通
					System.out.println("AU");
					playDetailDto.setMsg(Consts.MSG_CARD_NOT_KAITONG+"");
				}
				
			}else if(saleTicketFormList.size()>1){//卡号重复(冲突)
				System.out.println("系统错误，卡号重复");
				playDetailDto.setMsg(Consts.MSG_CARD_DULPLICATE_ERROR+"");
			}else{//错误的ticketId，归入未开通
				System.out.println("ticketId error");
				playDetailDto.setMsg(Consts.MSG_CARD_NOT_KAITONG+"");
			}
		}else{//起步金额和最低金额未在数据库中设置
			playDetailDto.setMsg(Consts.MSG_DB_ERROR+"");
		}
		
		super.writeJson(playDetailDto);
	}
	
	public void queryCardInfo(){
		/********************************************参数获取*******************************************/
		String ticketId = $("ticketId"); 
		System.out.println("ticketId:"+ticketId);
		/********************************************参数获取*******************************************/
		
		//返回信息
		playDetailDto.setTicketId(ticketId);
		
		/*************************************playDetailDto信息设置*************************************/
		SaleTicketForm saleTicketForm = new SaleTicketForm();
		saleTicketForm.setTicketId(ticketId);
		saleTicketFormList = ticketService.findByExample(saleTicketForm);
		
		playDetailMoney = new PlayDetailMoney();
		playDetailMoney.setTicketId(ticketId);
		playDetailMoneyOfAllFieldList = playDetailMoneyService.findByExample(playDetailMoney);//得到此卡号所有项目的游玩记录
		
		if(saleTicketFormList.size()==1){
			saleTicketForm = saleTicketFormList.get(0);
			double total = 0;
			for (int i = 0; i < playDetailMoneyOfAllFieldList.size(); i++) {
				PlayDetailMoney playDetailMoney = playDetailMoneyOfAllFieldList.get(i);
				if(playDetailMoney.getTotal()!=null){
					total += playDetailMoney.getTotal();
				}
			}
			playDetailDto.setTicketStatus(Consts.STR_CARD_NOT_USED.equals(saleTicketForm.getTicketIsUsed())?"有效卡":"已结账");
			playDetailDto.setTicketDateSold(saleTicketForm.getTicketDateSold());
			playDetailDto.setTicketDateReturn(saleTicketForm.getTicketEffectiveDate());
			playDetailDto.setTotal(total);
			playDetailDto.setTicketPrice(saleTicketForm.getTicketPrice());
			playDetailDto.setRestMoney(saleTicketForm.getTicketPrice()-total);
		}else{
			playDetailDto.setTicketStatus("未开通");
		}
		
		playDetailDto.setMsg(Consts.MSG_QUERY_CARD_INFO+"");
		super.writeJson(playDetailDto);
		/*************************************playDetailDto信息设置*************************************/
	}

//----------------------------------------------------辅助方法-------------------------------------------------------------------------
	/**
	 * 计时扣费(开始计时)
	 */
	private void startTiming(PlayDetailDto playDetailDto,List<PlayDetailMoney> playDetailMoneyOfCurrentFieldList) {
		Timestamp beginTime = new Timestamp(System.currentTimeMillis());
		
		if(playDetailMoneyOfCurrentFieldList.size()>0){//表示之前玩过该项目
			PlayDetailMoney playDetailMoneyTmp = null;
			for (int i = 0; i < playDetailMoneyOfCurrentFieldList.size(); i++) {
				playDetailMoneyTmp = playDetailMoneyTmp==null?playDetailMoneyOfCurrentFieldList.get(i):playDetailMoneyTmp;
				playDetailMoneyTmp = playDetailMoneyOfCurrentFieldList.get(i).getTicketDateEnd().after(playDetailMoneyTmp.getTicketDateEnd())
						?playDetailMoneyOfCurrentFieldList.get(i):playDetailMoneyTmp;
			}
			Timestamp lastEndTime = playDetailMoneyTmp.getTicketDateEnd();
			
			long beginTimeMillis = beginTime.getTime();
			long lastEndTimeMillis = lastEndTime.getTime();
			
			long intervalMillis = beginTimeMillis-lastEndTimeMillis;//如果之前玩过该项目，则当前时间和上次结束时间间隔必须够1分钟才能继续计时
			if(intervalMillis>60000){
				PlayDetailMoney playDetailMoney = new PlayDetailMoney();
				playDetailMoney.setField(playDetailDto.field);
				playDetailMoney.setTicketId(playDetailDto.ticketId);
				playDetailMoney.setTicketDateBegin(beginTime);
				
				playDetailMoneyService.saveOrUpdate(playDetailMoney);
				
				playDetailDto.updateData(playDetailMoney);
				playDetailDto.setMsg(Consts.MSG_CARD_BEGIN_SUCCESS+"");
			}else{
				playDetailDto.setMsg(Consts.MSG_MULTIPLAY_WAIT_OF_JISHI+"");
			}
		}else{
			PlayDetailMoney playDetailMoney = new PlayDetailMoney();
			playDetailMoney.setField(playDetailDto.field);
			playDetailMoney.setTicketId(playDetailDto.ticketId);
			playDetailMoney.setTicketDateBegin(beginTime);
			
			playDetailMoneyService.saveOrUpdate(playDetailMoney);
			
			playDetailDto.updateData(playDetailMoney);
			playDetailDto.setMsg(Consts.MSG_CARD_BEGIN_SUCCESS+"");
		}
		
	}
	
	/**
	 * 计时扣费(停止计时)
	 */
	private void endTiming(PlayDetailDto playDetailDto,PlayDetailMoney playDetailMoney) {
		Timestamp endTime = new Timestamp(System.currentTimeMillis());
		Timestamp beginTime = playDetailMoney.getTicketDateBegin();
		
		long endTimeMillis = endTime.getTime();
		long beginTimeMillis = beginTime.getTime();
		
		long intervalMillis = endTimeMillis-beginTimeMillis;
		/*long fenzhong = intervalMillis/60000;
		long miaozhong = intervalMillis/1000 - fenzhong*60;
		String playTime = fenzhong+"分"+miaozhong+"秒";*/
		
		//游玩时间是否够1分钟
		if(intervalMillis>60000){
			//计算消费金额
			playDetailMoney.setTicketDateEnd(endTime);
			playDetailMoneyService.update(playDetailMoney);
			
			List<PlayDetailDto> list = playDetailMoneyService.executeSql(Consts.SQL_JIFEI,PlayDetailDto.class,playDetailDto.ticketId,playDetailDto.field);
			Double total = list.get(0).getTotal();//SQL_JIFEI查询按id降序，所以选第一个
			String mini = list.get(0).getMini();
			
			playDetailMoney.setTicketDateEnd(endTime);
			playDetailMoney.setTotal(total);
			playDetailMoney.setMini(mini);
			playDetailMoneyService.saveOrUpdate(playDetailMoney);
			
			playDetailDto.updateData(playDetailMoney);
			playDetailDto.setTotal(playDetailDto.total+total);
			playDetailDto.setCostOfCurrent(total);
			playDetailDto.setRestMoney(playDetailDto.restMoney - total);
			playDetailDto.setMsg(Consts.MSG_CARD_END_SUCCESS+"");//停止计时
		}else{//不够1分钟，即重复刷卡
			playDetailDto.updateData(playDetailMoney);
			playDetailDto.setMsg(Consts.MSG_CARD_INTERVAL_NOT_ENOUGH_OF_ANSHI+"");
		}
	}

	/**
	 * 记次扣费
	 */
	private void costByCi(PlayDetailDto playDetailDto,List<PlayDetailMoney> playDetailMoneyOfCurrentFieldList) {
		//每次刷卡需間隔5秒
		Timestamp beginTime = new Timestamp(System.currentTimeMillis());
		if(playDetailMoneyOfCurrentFieldList.size()>0){//表示之前玩过该项目
			PlayDetailMoney playDetailMoneyTmp = null;
			for (int i = 0; i < playDetailMoneyOfCurrentFieldList.size(); i++) {
				playDetailMoneyTmp = playDetailMoneyTmp==null?playDetailMoneyOfCurrentFieldList.get(i):playDetailMoneyTmp;
				playDetailMoneyTmp = playDetailMoneyOfCurrentFieldList.get(i).getTicketDateEnd().after(playDetailMoneyTmp.getTicketDateEnd())
						?playDetailMoneyOfCurrentFieldList.get(i):playDetailMoneyTmp;
			}
			Timestamp lastEndTime = playDetailMoneyTmp.getTicketDateEnd();
			
			long beginTimeMillis = beginTime.getTime();
			long lastEndTimeMillis = lastEndTime.getTime();
			
			long intervalMillis = beginTimeMillis-lastEndTimeMillis;//如果之前玩过该项目，则当前时间和上次结束时间间隔必须够1分钟才能继续计时
			
			if(intervalMillis > 5000){
				costByCiCommon(playDetailDto,beginTime);
			}else{
				System.out.println("刷卡時間間隔限制");
				playDetailDto.setMsg(Consts.MSG_MULTIPLAY_WAIT_OF_ANCI+"");
			}
		}else{
			costByCiCommon(playDetailDto,beginTime);
		}
		
	}
	/**
	 * 抽取costByCi方法中用於向數據庫插入數據的代碼共同部分
	 * @param playDetailDto
	 * @param playDetailMoneyOfCurrentFieldList
	 * @param beginTime
	 */
	private void costByCiCommon(PlayDetailDto playDetailDto,Timestamp beginTime) {
		PlayDetailMoney playDetailMoney = new PlayDetailMoney();
		playDetailMoney.setTicketDateBegin(beginTime);
		playDetailMoney.setTicketDateEnd(beginTime);
		playDetailMoney.setTicketId(playDetailDto.ticketId);
		playDetailMoney.setField(playDetailDto.field);
		playDetailMoney.setMini("0");
		
		double costOfCurrent = 0;
		if(null!=playDetailDto.getCountToCostByCi()){
			int count = Integer.parseInt(playDetailDto.getCountToCostByCi());
			costOfCurrent = (playDetailDto.perMoney)*count;
			playDetailMoney.setTotal(costOfCurrent);
		}else{
			costOfCurrent = playDetailDto.perMoney;
			playDetailMoney.setTotal(costOfCurrent);
		}
		
		playDetailMoneyService.saveOrUpdate(playDetailMoney);
		
		playDetailDto.updateData(playDetailMoney);
		
		//计算按次扣费次数
		playDetailMoney = new PlayDetailMoney();
		playDetailMoney.setTicketId(playDetailDto.getTicketId());
		playDetailMoney.setField(playDetailDto.getField());
		List<PlayDetailMoney> playDetailMoneyOfCurrentFieldList = playDetailMoneyService.findByExample(playDetailMoney);//得到此卡号当前项目的游玩记录
		double totalOfCurrentField = 0;
		for (int i = 0; i < playDetailMoneyOfCurrentFieldList.size(); i++) {
			totalOfCurrentField += playDetailMoneyOfCurrentFieldList.get(i).getTotal();
		}
		
		playDetailDto.setCountOfCostByCi(totalOfCurrentField/playDetailDto.perMoney+"");
		playDetailDto.setTotal(playDetailDto.total+costOfCurrent);
		playDetailDto.setCostOfCurrent(playDetailDto.perMoney);
		playDetailDto.setTotalOfCurrentField(totalOfCurrentField);
		playDetailDto.setTotalOfOtherField(playDetailDto.getTotal()-playDetailDto.getTotalOfCurrentField());
		playDetailDto.setRestMoney(playDetailDto.restMoney - costOfCurrent);
		playDetailDto.setMsg(Consts.MSG_CARD_COST_BY_CI_SUCCESS+"");
	}
	
	/**
	 * 检查项目游玩状态
	 */
	private boolean checkPlayStatus(List<PlayDetailMoney> playDetailMoneyList,String field) {
		boolean playFlag = true;//true表示上一个游玩项目已停止计时或未游玩过
		for (int i = 0; i < playDetailMoneyList.size(); i++) {
		    playDetailMoney = playDetailMoneyList.get(i);
		    if(!field.equals(playDetailMoney.getField())&&playDetailMoney.getTicketDateEnd()==null){
		    	playDetailDto.setFieldNotEnd(playDetailMoney.getField());
		    	System.out.println(playDetailMoney.getField()+"未结束游玩");
		    	playFlag = false;
		    }
		}
		return playFlag;
	}
	
	/**
	 * 初始化playDetailDto的total,ticketPrice,perMoney,restMoney
	 */
	private void initMoney(List<SaleTicketForm> saleTicketFormList,List<PlayDetailMoney> playDetailMoneyList,List<FieldMoneyXihua> fieldMoneyXihuaList,PlayDetailDto playDetailDto){
		if(fieldMoneyXihuaList.size() != 0){
			FieldMoneyXihua fieldMoneyXihua = fieldMoneyXihuaList.get(0);
			playDetailDto.setPerMoney(fieldMoneyXihua.getPerMoney());
			playDetailDto.setLowestMoney(fieldMoneyXihua.getLowestMoney());
		}
		
		if(saleTicketFormList.size()==1){
			SaleTicketForm saleTicketForm = saleTicketFormList.get(0);
			double total = 0;
			for (int i = 0; i < playDetailMoneyList.size(); i++) {
				PlayDetailMoney playDetailMoney = playDetailMoneyList.get(i);
				if(playDetailMoney.getTotal()!=null){
					total += playDetailMoney.getTotal();
				}
			}
			playDetailDto.setTotal(total);
			playDetailDto.setTicketPrice(saleTicketForm.getTicketPrice());
			playDetailDto.setRestMoney(saleTicketForm.getTicketPrice()-total);
		}
	}
	
	/**
	 * 重复数据(有时刷卡会一次插入两条数据)的删除
	 * @param playDetailMoneyList
	 */
	private void deleteDulplicateDataOfPlay(List<PlayDetailMoney> playDetailMoneyList){
		int count = 0;
		PlayDetailMoney playDetailMoneyTemp;
		for (int i = 1; i < playDetailMoneyList.size(); i++) {
			playDetailMoneyTemp = playDetailMoneyList.get(i);
			if(playDetailMoneyTemp.getTicketDateEnd() == null){
				count++;
			}
			if(count>=2){
				playDetailMoneyList.remove(i);
				playDetailMoneyService.deleteObject(playDetailMoneyTemp);
			}
		}
	}
	
	/**
	 * 判断是不是所有计时项目都已刷出场(全部已出场返回true，否则返回false)
	 * @return
	 */
	private boolean ifAllFieldFinished(List<PlayDetailMoney> playDetailMoneyList){
		boolean check = true;
		for (int i = 0; i < playDetailMoneyList.size(); i++) {
			if(playDetailMoneyList.get(i).getTicketDateEnd()==null){
				check = false;
				break;
			}
		}
		return check;
	}
	
//-------------------------------------------------------------------------------------------
	public Json getJ() {
		return j;
	}

	public void setJ(Json j) {
		this.j = j;
	}

	public PlayDetailMoney getPlayDetailMoney() {
		return playDetailMoney;
	}

	public void setPlayDetailMoney(PlayDetailMoney playDetailMoney) {
		this.playDetailMoney = playDetailMoney;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
