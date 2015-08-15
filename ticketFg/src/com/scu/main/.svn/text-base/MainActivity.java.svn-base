package com.scu.main;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.scu.R;
import com.scu.dto.FieldInfoDto;
import com.scu.utils.Consts;
import com.scu.utils.HttpClientPost;
import com.scu.utils.PdaUtil;
import com.scu.utils.ToastUtil;

import android.app.Activity;
import android.content.Intent;
import android.hardware.reader.rfid;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
	
	private GridView gridView;
	//private TextView tv_userDescription;
	//private String userAccount;
	//private Intent foreIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 初始化gridview界面
	 */
	private void init() {
		//检查wifi
		new PdaUtil().checkWifi(this);
		
		//加载游乐项目列表
		String url_get_fields = getResources().getString(R.string.BASE_URL)+"/fieldInfoAction_getAllFields";
		System.out.println(url_get_fields);
		new MainUtil().getAllProjects(this, url_get_fields);
	    
	}

	/**
	 * 设置gridView点击事件
	 */
	private void setGridViewListener() {
		
	     gridView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, //The AdapterView where the click happened   
					View arg1, //The view within the AdapterView that was clicked  
					int arg2,  //The position of the view in the adapter  
					long arg3  //The row id of the item that was clicked 
					) {
				//在本例中arg2=arg3  
			    HashMap<String, Object> item=(HashMap<String, Object>) arg0.getItemAtPosition(arg2);  
			    //显示所选Item的itemType
			    String projectName = (String) item.get("itemText");
			    String projectType = (String) item.get("itemType");//0记次，1计时
			    
			    if(projectName.equals(Consts.FIELD_PPC)){
			    	new MainUtil().playSound(MainActivity.this, Consts.SOUND_SELECT_PPC);
			    }else if(projectName.equals(Consts.FIELD_DDC)){
			    	new MainUtil().playSound(MainActivity.this, Consts.SOUND_SELECT_DDC);
			    }
			    
			    Intent intent = new Intent(MainActivity.this,CardActivity.class);
			    intent.putExtra("projectName", projectName);
				intent.putExtra("projectType", projectType);
				startActivity(intent);
			}
		});
	}
	
	/**
	 * 处理访问服务器的结果消息
	 */
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Consts.LOAD_FIELD_FAIL:
			{
				try {
					Thread.currentThread().sleep(3000);
					String url_get_fields = getResources().getString(R.string.BASE_URL)+"/fieldInfoAction_getAllFields";
					new MainUtil().getAllProjects(MainActivity.this, url_get_fields);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ToastUtil.showShort(MainActivity.this, "连接服务器失败");
			}break;
			case Consts.NET_FAIL:
			{
				ToastUtil.showShort(MainActivity.this, "连接服务器失败");
			}break;
			case Consts.COMMON_FAIL:
			{
				ToastUtil.showShort(MainActivity.this, "获取数据为空");
			}break;
			case Consts.GET_FIELDS_SUCCESS:
			{
				//将服务器返回数据加载到gridview中显示
				String data = msg.getData().getString("data");
				List<FieldInfoDto> list = JSON.parseArray(data, FieldInfoDto.class);
				
				List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
			    for (int i = 0; i < list.size(); i++) {
			        Map<String, Object> item = new HashMap<String, Object>();
			        item.put("itemImage", new MainUtil().getImgResource(list.get(i).getField()));
			        item.put("itemText", list.get(i).getField());
			        //item.put("itemType", list.get(i).getType()+"");
			        items.add(item);
			    }
			    
			    //实例化一个适配器
			    SimpleAdapter adapter = new SimpleAdapter(MainActivity.this, items, R.layout.grid_item, 
			    		new String[]{"itemImage", "itemText"}, new int[]{R.id.item_image, R.id.item_text});
			    //获得GridView实例
			    gridView = (GridView)findViewById(R.id.gv_field);
			    gridView.setNumColumns(Consts.ROW_COUNT);//可以在xml中设置
			    //gridView.setGravity(Gravity.CENTER);//同上
			    //将GridView和数据适配器关联
			    gridView.setAdapter(adapter);
			    setGridViewListener();
			}break;
			default:
				break;
			}
		};
	};
	
}
