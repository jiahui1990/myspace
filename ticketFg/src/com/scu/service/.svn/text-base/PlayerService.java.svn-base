package com.scu.service;

import com.scu.R;
import com.scu.utils.Consts;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayerService extends Service{
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//System.out.println("service:"+Thread.currentThread());
		
		int sound = intent.getIntExtra("sound",0);//101是默认值
		String time = intent.getStringExtra("time");
		String yue = intent.getStringExtra("yue");
		
		playSound(sound,time,yue);
		
		return super.onStartCommand(intent, flags, startId);
	}
	
    @SuppressWarnings("static-access")
	public void playSound(int sound,String time, String yue ) {
    	MediaPlayer   player ;
    	MediaPlayer   playerShijian ;
    	MediaPlayer   playerDian ;
    	MediaPlayer   playerFen ;
    	MediaPlayer   playerFenzhong;
    	MediaPlayer   playerYue;
    	MediaPlayer   playerYuebuzu;
    	MediaPlayer   playerYuan;
    	MediaPlayer   playerQianfei;
    	
    	switch (sound) {
		case Consts.SOUND_BEGIN://计时开始，时间x点x分
	    	String strShortTime = time.substring(11,16);
	    	String strArrayShortTime[] = strShortTime.split(":");
	    	String strHour = strArrayShortTime[0];
	    	String strMinite = strArrayShortTime[1];
	    	System.out.println(strHour+"---"+strMinite);
	    	
			player  =   new MediaPlayer().create(this,R.raw.sound_begin);
			player.start();
			endPlayer(player);
			
			playerShijian  =   new MediaPlayer().create(this,R.raw.sound_time_shijian);
			playerShijian.start();
			endPlayer(playerShijian);
			
			endShuziPlayer(strHour);
			
			playerDian  =   new MediaPlayer().create(this,R.raw.sound_time_dian);
			playerDian.start();
			endPlayer(playerDian);
			
			endShuziPlayer(strMinite);
			
			playerFen  =   new MediaPlayer().create(this,R.raw.sound_time_fen);
			playerFen.start();
			endPlayer(playerFen);
			break;
		case Consts.SOUND_COST_BY_CI://扣费成功，余额x元
			player  =   new MediaPlayer().create(this,R.raw.sound_cost_by_card);
			player.start();
			endPlayer(player);
			
			playerYue  =   new MediaPlayer().create(this,R.raw.sound_yue);
			playerYue.start();
			endPlayer(playerYue);
			
			endShuziPlayer(yue);
			
			playerYuan  =   new MediaPlayer().create(this,R.raw.sound_yuan);
			playerYuan.start();
			endPlayer(playerYuan);
			break;
		case Consts.SOUND_END://游玩结束，时间x分钟(欠费的情况:欠费x元)
			player  =   new MediaPlayer().create(this,R.raw.sound_end);
			player.start();
			endPlayer(player);
			
			playerShijian  =   new MediaPlayer().create(this,R.raw.sound_time_shijian);
			playerShijian.start();
			endPlayer(playerShijian);
			
			endShuziPlayer(time);
			
			playerFenzhong  =   new MediaPlayer().create(this,R.raw.sound_time_fenzhong);
			playerFenzhong.start();
			endPlayer(playerFenzhong);
			
			int i_yue = (int) Double.parseDouble(yue);
			if(i_yue<0){
				playerQianfei  =   new MediaPlayer().create(this,R.raw.sound_qianfei);
				playerQianfei.start();
				endPlayer(playerQianfei);
				
				endShuziPlayer(yue);
				
				playerYuan  =   new MediaPlayer().create(this,R.raw.sound_yuan);
				playerYuan.start();
				endPlayer(playerYuan);
			}
			
			break;
		case Consts.SOUND_OTHER_NOT_END://正在游玩其他项目
			player  =   new MediaPlayer().create(this,R.raw.sound_other_not_end);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_BEGIN_DUPLICATE://计时已开始，请勿重复刷卡
			player  =   new MediaPlayer().create(this,R.raw.sound_begin_duplicate);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_END_DUPLICATE://游玩已结束，时间x分钟
			player  =   new MediaPlayer().create(this,R.raw.sound_end_duplicate);
			player.start();
			endPlayer(player);
			
			playerShijian  =   new MediaPlayer().create(this,R.raw.sound_time_shijian);
			playerShijian.start();
			endPlayer(playerShijian);
			
			endShuziPlayer(time);
			
			playerFenzhong  =   new MediaPlayer().create(this,R.raw.sound_time_fenzhong);
			playerFenzhong.start();
			endPlayer(playerFenzhong);
			
			break;
		case Consts.SOUND_NOT_ENOUGH_MONEY://余额不足，余额x元
			playerYuebuzu  =   new MediaPlayer().create(this,R.raw.sound_yuebuzu);
			playerYuebuzu.start();
			endPlayer(playerYuebuzu);
			
			playerYue  =   new MediaPlayer().create(this,R.raw.sound_yue);
			playerYue.start();
			endPlayer(playerYue);
			
			endShuziPlayer(yue);
			
			playerYuan  =   new MediaPlayer().create(this,R.raw.sound_yuan);
			playerYuan.start();
			endPlayer(playerYuan);
			
			break;
		case Consts.SOUND_NOT_KAITONG://此卡未开通
			player  =   new MediaPlayer().create(this,R.raw.sound_not_kaitong);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_READ_CARD_ERROR://读卡失败
			player  =   new MediaPlayer().create(this,R.raw.sound_read_card_error);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_MULTIPLAY_WAIT_OF_JISHI:
			player  =   new MediaPlayer().create(this,R.raw.sound_multiplay_wait);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_SELECT_PPC:
			player  =   new MediaPlayer().create(this,R.raw.sound_select_ppc);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_SELECT_DDC:
			player  =   new MediaPlayer().create(this,R.raw.sound_select_ddc);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_SELECT_JTC:
			player  =   new MediaPlayer().create(this,R.raw.sound_select_jtc);
			player.start();
			endPlayer(player);
			break;
		case Consts.SOUND_SELECT_QT:
			player  =   new MediaPlayer().create(this,R.raw.sound_select_qt);
			player.start();
			endPlayer(player);
			break;
		default:
			System.out.println("声音参数错误");
			break;
		}
    }   

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@SuppressWarnings("static-access")
	public void endShuziPlayer(String shuzi){
		System.out.println("shuzi------------->"+shuzi);
		int i_shuzi = (int) Double.parseDouble(shuzi);
		shuzi = Math.abs(i_shuzi)+"";
		if(shuzi.length()==1){
			int numberGe = Integer.parseInt(shuzi);
			MediaPlayer playerNumber = getPlayerOfNumber(numberGe);
			playerNumber.start();
			endPlayer(playerNumber);
		}else if(shuzi.length()==2){
			int numberShi = Integer.parseInt(shuzi.substring(0,1));
			MediaPlayer playerNumberShi = getPlayerOfNumber(numberShi);
			MediaPlayer playerShi = new MediaPlayer().create(this,R.raw.sound_time_over_10);
			int numberGe = Integer.parseInt(shuzi.substring(1));
			
			if(numberShi!=0){
				playerNumberShi.start();
				endPlayer(playerNumberShi);
				
				playerShi.start();
				endPlayer(playerShi);
			}
			if(numberGe!=0){
				MediaPlayer playerNumberGe = getPlayerOfNumber(numberGe);
				playerNumberGe.start();
				endPlayer(playerNumberGe);
			}
		}else if(shuzi.length()==3){
			int numberBai = Integer.parseInt(shuzi.substring(0,1));
			MediaPlayer playerNumberBai = getPlayerOfNumber(numberBai);
			MediaPlayer playerBai = new MediaPlayer().create(this,R.raw.sound_time_over_100);
			int numberShi = Integer.parseInt(shuzi.substring(1,2));
			MediaPlayer playerNumberShi = getPlayerOfNumber(numberShi);
			MediaPlayer playerShi = new MediaPlayer().create(this,R.raw.sound_time_over_10);
			int numberGe = Integer.parseInt(shuzi.substring(2));
			
			playerNumberBai.start();
			endPlayer(playerNumberBai);
			playerBai.start();
			endPlayer(playerBai);
			
			if(!(numberShi==0&&numberGe==0)){
				playerNumberShi.start();
				endPlayer(playerNumberShi);
				playerShi.start();
				endPlayer(playerShi);
				
				if(numberGe!=0){
					MediaPlayer playerNumberGe = getPlayerOfNumber(numberGe);
					playerNumberGe.start();
					endPlayer(playerNumberGe);
				}
			}
		}else{
			System.out.println("游玩时间过长！");
		}
	}
	
	public void endPlayer(MediaPlayer mediaPlayer){
		while(true){
			if(mediaPlayer.isPlaying()==false){
				mediaPlayer.release();
				break;
			}
		}
	}
	
	@SuppressWarnings("static-access")
	public MediaPlayer getPlayerOfNumber(int number){
		MediaPlayer playerNumber = null;
		switch (number) {
		case 0:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_0);
			break;
		case 1:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_1);
			break;
		case 2:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_2);
			break;
		case 3:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_3);
			break;
		case 4:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_4);
			break;
		case 5:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_5);
			break;
		case 6:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_6);
			break;
		case 7:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_7);
			break;
		case 8:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_8);
			break;
		case 9:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_9);
			break;
		default:
			playerNumber =  new MediaPlayer().create(this,R.raw.sound_time_null);
			break;
		}
		return playerNumber;
	}
}
