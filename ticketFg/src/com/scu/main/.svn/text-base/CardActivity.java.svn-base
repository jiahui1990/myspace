package com.scu.main;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.scu.R;
import com.scu.dto.Json;
import com.scu.dto.PlayDetailDto;
import com.scu.nfc.NfcUtil;
import com.scu.utils.Consts;
import com.scu.utils.PdaUtil;
import com.scu.utils.StringUtil;
import com.scu.utils.ToastUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Service;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.reader.rfid;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CardActivity extends Activity {
	
	public TextView tvRest;
	public TextView tvInfo;
	public TextView tvTitle;
	public Spinner fieldsSpinner;
	public Spinner countSpinner;
	public Handler handler;
	private String field;
	private String projectType;
	private Intent foreIntent;
	private String baseUrl;
	long preMillis;
	private SharedPreferences sp;
	private ArrayAdapter<String> adapter;
	private List<String> list = new ArrayList<String>();
	private Vibrator vib;
	private CardUtil cardUtil;
	
	//刷卡相关
	NfcAdapter nfcAdapter;     
	Intent m_intent; 
	String strUI = "";    
	Tag m_tagFromIntent;  

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_card);
		
		//System.out.println("activity:"+Thread.currentThread());
		
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void init(){
		//调用开发包函数
		processAdapterAction(getIntent());
		
		fieldsSpinner = (Spinner) this.findViewById(R.id.spinner_fields);
		countSpinner = (Spinner) this.findViewById(R.id.spinner_count);
		/*//第一步：添加一个下拉列表项的list，这里添加的项就是下拉列表的菜单项
        list.add("游乐项目:碰碰船");
        list.add("游乐项目:电动船");
        list.add("游乐项目:脚踏船");
        list.add("游乐项目:汽艇");
        //第二步：为下拉列表定义一个适配器，这里就用到里前面定义的list。
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        //第三步：为适配器设置下拉列表下拉时的菜单样式。
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fieldsSpinner.setAdapter(adapter);*/
		
		//显示之前选择的游玩项目
		sp=getSharedPreferences("config",MODE_PRIVATE);
		int select_item_field=sp.getInt("field_item", 0);
		fieldsSpinner.setSelection(select_item_field);
		int select_item_count=sp.getInt("count_item", 0);
		countSpinner.setSelection(select_item_count);
		
		setListener();
		//检查wifi
		new PdaUtil().checkWifi(this);
		
		vib = (Vibrator) CardActivity.this.getSystemService(Service.VIBRATOR_SERVICE);
		cardUtil = new CardUtil();
		
		tvInfo = (TextView) findViewById(R.id.tv_info);
		tvInfo.getPaint().setFakeBoldText(true);
		
		baseUrl = getResources().getString(R.string.BASE_URL);
		preMillis = 0;
	}

	private void setListener() {
		//String selectValue1=fieldsSpinner.getSelectedItem().toString();
		//System.out.println("开始取的"+selectValue1);
		//保存选择的游玩项目
		fieldsSpinner.setOnItemSelectedListener (new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Editor edit=sp.edit();
				edit.putInt("field_item", position);
				edit.commit();
				
				String selectField=fieldsSpinner.getSelectedItem().toString().split(":")[1];
				//System.out.println("选择的时候"+selectValue2);
				cardUtil.playSound(CardActivity.this, selectField);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		}); 
		
		countSpinner.setOnItemSelectedListener (new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Editor edit=sp.edit();
				edit.putInt("count_item", position);
				edit.commit();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				
			}
		}); 
		
		//查询按钮，用于停止监听该游乐项目的刷卡
		findViewById(R.id.bt_card_query).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				queryCardInfo();
			}
		});
		
		findViewById(R.id.bt_card_shuaka).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(preMillis==0){
					preMillis = System.currentTimeMillis();
					shuaka();
				}else{
					long curMillis = System.currentTimeMillis();
					if(curMillis-preMillis>1000){
						shuaka();
						preMillis = curMillis;
					}else{
						System.out.println("点击过于频繁");
					}
				}
			}
		});	
		
		//http请求返回消息处理
		handler = new Handler(){
			public void handleMessage(android.os.Message msg) {
				String data = msg.getData().getString("data");
				PlayDetailDto playDetailDto = JSON.parseObject(data, PlayDetailDto.class);
				
				switch (msg.what) {
				case Consts.NET_FAIL:
				{
					ToastUtil.showShort(CardActivity.this, "连接服务器失败");
				}break;
				case Consts.MSG_CARD_BEGIN_SUCCESS:{
					tvInfo.setText("卡号："+playDetailDto.getTicketId()+"\n项目："+playDetailDto.getField()+"\n开始时间："+playDetailDto.getTicketDateBegin()+"\n卡内余额："+playDetailDto.getRestMoney()+"元");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_BEGIN,playDetailDto.getTicketDateBegin()+"");
					vib.vibrate(200);
				}break;
				case Consts.MSG_CARD_INTERVAL_NOT_ENOUGH_OF_ANSHI:{
					tvInfo.setText("卡号："+playDetailDto.getTicketId()+"\n项目："+playDetailDto.getField()+"\n开始时间："+playDetailDto.getTicketDateBegin()+"\n卡内余额："+playDetailDto.getRestMoney()+"元");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_BEGIN_DUPLICATE);
					vib.vibrate(200);
				}break;
				case Consts.MSG_CARD_END_SUCCESS:{
					tvInfo.setText("卡号："+playDetailDto.getTicketId()+"\n项目："+playDetailDto.getField()+"\n开始时间："+playDetailDto.getTicketDateBegin()
							+"\n结束时间："+playDetailDto.getTicketDateEnd()+"\n累计游玩时间："+playDetailDto.getMini()+"分钟\n消费金额："+playDetailDto.getCostOfCurrent()+"元"+"\n卡内余额："+playDetailDto.getRestMoney()+"元");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_END,playDetailDto.getMini(),playDetailDto.getRestMoney()+"");
					vib.vibrate(200);
				}break;
				case Consts.MSG_CARD_COST_BY_CI_SUCCESS:{
					tvInfo.setText("卡号："+playDetailDto.getTicketId()+"\n项目："+playDetailDto.getField()+"\n单次消费金额："+playDetailDto.getPerMoney()+"元\n扣费次数："+playDetailDto.getCountOfCostByCi()
							+"次\n当前项目消费："+playDetailDto.getTotalOfCurrentField()+"元\n其他项目消费："+playDetailDto.getTotalOfOtherField()+"元\n卡内余额："+playDetailDto.getRestMoney()+"元");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_COST_BY_CI,playDetailDto.getMini(),playDetailDto.getRestMoney()+"");
					vib.vibrate(200);
				}break;
				case Consts.MSG_CARD_END_DUPLICATE:{
					//tvInfo.setText("游玩已结束，请勿重复刷卡");
					tvInfo.setText("卡号："+playDetailDto.getTicketId()+"\n项目："+playDetailDto.getField()+"\n开始时间："+playDetailDto.getTicketDateBegin()
							+"\n结束时间："+playDetailDto.getTicketDateEnd()+"\n累计游玩时间："+playDetailDto.getMini()+"分钟\n消费金额："+playDetailDto.getTotal()+"元"+"\n卡内余额："+playDetailDto.getRestMoney()+"元");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_END_DUPLICATE,playDetailDto.getMini());
					vib.vibrate(200);
				}break;
				case Consts.MSG_CARD_OTHER_NOT_END:{
					tvInfo.setText("此卡没有在"+playDetailDto.getFieldNotEnd()+"处刷结束时间！");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_OTHER_NOT_END);
				}break;
				case Consts.MSG_CARD_MONEY_NOT_ENOUGH:{
					tvInfo.setText("卡号："+playDetailDto.getTicketId()+"\n项目："+playDetailDto.getField()+"\n提示：此卡余额不足！"+"\n最低游玩金额："+playDetailDto.getLowestMoney()+"元\n当前余额："+playDetailDto.getRestMoney()+"元");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_NOT_ENOUGH_MONEY,"",playDetailDto.getRestMoney()+"");
				}break;
				case Consts.MSG_CARD_NOT_KAITONG:{
					tvInfo.setText("此卡未开通！");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_NOT_KAITONG);
				}break;
				case Consts.MSG_QUERY_CARD_INFO:{
					tvInfo.setText("卡号："+playDetailDto.getTicketId()+"\n卡状态："+playDetailDto.getTicketStatus()+"\n售卡时间："+playDetailDto.getTicketDateSold()
							+"\n消费金额："+playDetailDto.getTotal()+"元"+"\n当前余额："+playDetailDto.getRestMoney()+"元");
					//cardUtil.playSound(CardActivity.this, Consts.SOUND_NOT_ENOUGH_MONEY,"",playDetailDto.getRestMoney()+"");
				}break;
				case Consts.MSG_MULTIPLAY_WAIT_OF_JISHI:{
					//tvInfo.setText("遊玩已结束，再次遊玩需等待1分钟！");
					ToastUtil.showShort(CardActivity.this, "遊玩已结束，再次遊玩需等待1分钟！");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_MULTIPLAY_WAIT_OF_JISHI);
				}break;
				case Consts.MSG_MULTIPLAY_WAIT_OF_ANCI:{
					//tvInfo.setText("遊玩已结束，再次遊玩需等待1分钟！");
					ToastUtil.showShort(CardActivity.this, "刷卡间隔时间为5秒！");
				}break;
				case Consts.MSG_FIELD_ERROR:{
					tvInfo.setText("系统不存在此游乐项目！");
				}break;
				case Consts.MSG_DB_ERROR:{
					tvInfo.setText("系统数据库错误！");
				}break;
				case Consts.MSG_CARD_DULPLICATE_ERROR:{
					tvInfo.setText("卡号重复！");
				}break;
				case Consts.MSG_READ_CARD_ERROR:{
					tvInfo.setText("获取卡号失败，请将卡放置于设备后。");
					ToastUtil.showShort(CardActivity.this, "获取卡号失败，请将卡放置于设备后。");
					cardUtil.playSound(CardActivity.this, Consts.SOUND_READ_CARD_ERROR);
				}break;
				default:
					break;
				}
			};
		};
		
		//对当前游乐项目进行刷卡
		//handler.post(cardListenerRunnable);
		
	}
	
	/**
	 * 刷卡计费
	 */
	private void shuaka(){
		String selectValue = fieldsSpinner.getSelectedItem().toString();
		field = selectValue.split(":")[1];
		
		/************************************************记次刷卡扣费次数************************************************/
		String count = countSpinner.getSelectedItem().toString().substring(0, 1);
		/************************************************记次刷卡扣费次数************************************************/
		
		/****************************************读卡及刷卡(分nfc和nfid两种读卡方式)****************************************/
		String ticketId = NfcUtil.read(m_intent);
		System.out.println("ticketId------------------->"+ticketId);
		if("".equals(ticketId)||ticketId==null){
			handler.sendEmptyMessage(Consts.MSG_READ_CARD_ERROR);
			System.out.println("获取卡号失败，请将卡放置于设备后");
		}else{
			//开始刷卡
			System.out.println("开始刷卡");
			String url = baseUrl+ "/playAction_shuaka?ticketId="+ticketId+"&field="+field+"&count="+count;
			new CardUtil().getInfoFromServer(CardActivity.this, url);
		}
		
		/*String cardUid = rfid.ReadUID();
		if(cardUid.equals("")){
			handler.sendEmptyMessage(Consts.MSG_READ_CARD_ERROR);
			System.out.println("获取卡号失败，请将卡放置于设备后");
		}else{
			//ToastUtil.showShort(CardActivity.this, "卡号："+cardUid);
			byte[] buffer = new byte[16];
	    	int result = rfid.ReadBlockSimple(buffer);
	    	if (result == 0) {
	    		String ticketId = new StringUtil().getCardTicketId(buffer);
    			//开始刷卡
    			System.out.println("开始刷卡");
				String url = baseUrl+ "/playAction_shuaka?ticketId="+ticketId+"&field="+field;
				//url = "http://192.168.99.113:8080/ticketBg/playAction_shuaka?ticketId=12896701&field=碰碰船";
    			new CardUtil().getInfoFromServer(CardActivity.this, url);
	    	}else{
				ToastUtil.showShort(CardActivity.this,"读取第 " + Consts.TICKET_ID_BLOCK + "块数据错误");
	    	}
		}*/
		/****************************************读卡及刷卡(分nfc和nfid两种读卡方式)****************************************/
		
		/**************************************************无卡测试**************************************************/
		/*String ticketId = "10000002";
		//开始刷卡
		System.out.println("开始刷卡");
		String url = baseUrl+ "/playAction_shuaka?ticketId="+ticketId+"&field="+field;
		new CardUtil().getInfoFromServer(CardActivity.this, url);*/
		/**************************************************无卡测试**************************************************/
	}
	
	/**
	 * 卡信息查询
	 */
	private void queryCardInfo(){
		/****************************************读卡及刷卡(分nfc和nfid两种读卡方式)****************************************/
		String ticketId = NfcUtil.read(m_intent);
		System.out.println("ticketId------------------->"+ticketId);
		if("".equals(ticketId)||ticketId==null){
			handler.sendEmptyMessage(Consts.MSG_READ_CARD_ERROR);
			System.out.println("获取卡号失败，请将卡放置于设备后");
		}else{
			//开始刷卡
			System.out.println("开始查询");
			String url = baseUrl+ "/playAction_queryCardInfo?ticketId="+ticketId;
			new CardUtil().getInfoFromServer(CardActivity.this, url);
		}
		
		/*String cardUid = rfid.ReadUID();
		if(cardUid.equals("")){
			handler.sendEmptyMessage(Consts.MSG_READ_CARD_ERROR);
			System.out.println("获取卡号失败，请将卡放置于设备后");
		}else{
			//ToastUtil.showShort(CardActivity.this, "卡号："+cardUid);
			byte[] buffer = new byte[16];
	    	int result = rfid.ReadBlockSimple(buffer);
	    	if (result == 0) {
	    		String ticketId = new StringUtil().getCardTicketId(buffer);
    			//开始刷卡
    			System.out.println("开始刷卡");
				String url = baseUrl+ "/playAction_queryCardInfo?ticketId="+ticketId;
				//url = "http://192.168.99.113:8080/ticketBg/playAction_shuaka?ticketId=12896701&field=碰碰船";
    			new CardUtil().getInfoFromServer(CardActivity.this, url);
	    	}else{
				ToastUtil.showShort(CardActivity.this,"读取第 " + Consts.TICKET_ID_BLOCK + "块数据错误");
	    	}
		}*/
		/****************************************读卡及刷卡(分nfc和nfid两种读卡方式)****************************************/
		
		/****************************************无卡测试****************************************/
		/*String ticketId = "10000002";
		//开始查询
		System.out.println("开始查询");
		String url = baseUrl+ "/playAction_queryCardInfo?ticketId="+ticketId;
		new CardUtil().getInfoFromServer(CardActivity.this, url);*/
		/****************************************无卡测试****************************************/
	}

/****************************************************开发包程序**************************************************************************/
	public void processAdapterAction(Intent intent)
	{
	     //当系统检测到tag中含有NDEF格式的数据时，且系统中有activity声明可以接受包含NDEF数据的Intent的时候，系统会优先发出这个action的intent。
	     //得到是否检测到ACTION_NDEF_DISCOVERED触发                           序号1
	     if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {   
	     	System.out.println("ACTION_NDEF_DISCOVERED");
	         //处理该intent   
	         processIntent(intent);   
	         return;
	     }   
	     //当没有任何一个activity声明自己可以响应ACTION_NDEF_DISCOVERED时，系统会尝试发出TECH的intent.即便你的tag中所包含的数据是NDEF的，但是如果这个数据的MIME type或URI不能和任何一个activity所声明的想吻合，系统也一样会尝试发出tech格式的intent，而不是NDEF.
	     //得到是否检测到ACTION_TECH_DISCOVERED触发                           序号2
	     if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {   
	     	System.out.println("ACTION_TECH_DISCOVERED");
	         //处理该intent   
	         processIntent(intent);   
	         return;
	     } 
	     //当系统发现前两个intent在系统中无人会接受的时候，就只好发这个默认的TAG类型的
	     //得到是否检测到ACTION_TAG_DISCOVERED触发                           序号3
	     if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {   
	     	System.out.println("ACTION_TAG_DISCOVERED");
	         //处理该intent   
	         processIntent(intent);   
	         return;  
	     } 
	}
	@Override
	public void onNewIntent(Intent intent) {
		processAdapterAction(intent);
	}

    private void processIntent(Intent intent) {  
    	//System.out.print(intent.getAction());
    	m_intent=intent;
        //取出封装在intent中的TAG 
    	m_tagFromIntent= intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
    	//System.out.print(m_tagFromIntent);
        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);   
        for (String tech : tagFromIntent.getTechList()) {   
           //System.out.println(tech);   
        }    
//        boolean auth = false;   
//        //读取TAG       
//        MifareClassic mfc = MifareClassic.get(tagFromIntent);   
//        try {   
//            String metaInfo = "";   
//            //Enable I/O operations to the tag from this TagTechnology object.   
//            mfc.connect();   
//            int type = mfc.getType();//获取TAG的类型   
//            int sectorCount = mfc.getSectorCount();//获取TAG中包含的扇区数   
//            String typeS = "";   
//            switch (type) {   
//            case MifareClassic.TYPE_CLASSIC:   
//                typeS = "TYPE_CLASSIC";   
//                break;   
//            case MifareClassic.TYPE_PLUS:   
//                typeS = "TYPE_PLUS";   
//                break;   
//            case MifareClassic.TYPE_PRO:   
//                typeS = "TYPE_PRO";   
//                break;   
//            case MifareClassic.TYPE_UNKNOWN:   
//                typeS = "TYPE_UNKNOWN";   
//                break;   
//            }  
//            
//            metaInfo += "卡片类型：" + typeS + "\n共" + sectorCount + "个扇区\n共"   
//                    + mfc.getBlockCount() + "个块\n存储空间: " + mfc.getSize() + "B\n"; 
//            
//            for (int j = 0; j < sectorCount; j++) {   
//                //Authenticate a sector with key A.   
//                auth = mfc.authenticateSectorWithKeyA(j,   
//                        MifareClassic.KEY_DEFAULT);   
//                int bCount;   
//                int bIndex;   
//                if (auth) {   
//                    metaInfo += "Sector " + j + ":验证成功\n";   
//                    // 读取扇区中的块   
//                    bCount = mfc.getBlockCountInSector(j);   
//                    bIndex = mfc.sectorToBlock(j);   
//                    for (int i = 0; i < bCount; i++) {   
//                        byte[] data = mfc.readBlock(bIndex);   
//                        metaInfo += "Block " + bIndex + " : "   
//                                + bytesToHexString(data) + "\n";   
//                        bIndex++;   
//                    }   
//                } else {   
//                    metaInfo += "Sector " + j + ":验证失败\n";   
//                }   
//            }   
//            resultView_MF1.setText(metaInfo);   
//        } catch (Exception e) {   
//            e.printStackTrace();   
//        }   
    }
/****************************************************开发包程序**************************************************************************/
}

