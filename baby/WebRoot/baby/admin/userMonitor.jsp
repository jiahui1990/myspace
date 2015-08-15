<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="/pages/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>实时</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/baby/admin/css/zTreeStyle.css">
	<script type="text/javascript" src="/baby/admin/js/jquery.ztree.core-3.5.js"></script>
	
	<style type="text/css">
		.btn_left{line-height:30px;height: 30px;width: 163px;font-size: 18px;cursor: pointer;}
		.btn_focus{background-image: url('/images/normol/camera1-__u3.png');color: #FFFFFF;}
		.btn_lose{color: #999999;}
		.error{color: #ff0000;}
	</style>
	
	<script type="text/javascript" src="/baby/admin/js/nPlay.js"></script>
	
	<script type="text/javascript">
	var unixTime = ${time };
	var time = null;
	var playStartTime = '';
	var playEndTime = '';
	var isPlay = false;
	
	$(function(){
		time = new Date(unixTime);
		setCurtimeSpan();
	
		$(".btn_left").click(function(){
			var timesquen = $(this).attr("data-timesquen");
			var timeArr = timesquen.split("-");
			playStartTime = timeArr[0];
			playEndTime = timeArr[1];
		
			$(".btn_left").removeClass("btn_focus");
			$(".btn_left").addClass("btn_lose");
			$(this).removeClass("btn_lose");
			$(this).addClass("btn_focus");
			
			
			InitPlay($("#video"));
			
			var canplay = canPlay();
			if(canplay==0){
				PlayUrl("huamai://125.71.21.68:8100/?loginkey=", 
						/* $(this).children("span").html() */"", 
						0,
						"sn="+$(this).attr("data-sn")+"&actor=48126&usealarm=0&usetransfer=1024&natip1=&natport1=0&natip2=null&natport2=0&transferip=&transferport=0&nvsip=118.122.165.155&nvsport=10081");
				$("#timesquen").removeClass("error");
				isPlay = true;
			}else if(canplay==1){
				alert("已经超过该摄像头的结束观看时间");
				$("#timesquen").addClass("error");
			}else{
				alert("还没到该摄像头的观看时间");
				$("#timesquen").addClass("error");
			}
			
			$("#timesquen").html(timesquen);
		});
		
		$(".btn_left").eq(0).click();
		
		setInterval(playCheck,5000);
	});
	
	function setCurtimeSpan(){
		/* $("#span_curtime").html(time.getHours()+':'+time.getMinutes()); */
		$("#span_curtime").html(timeStamp2String(time));
	}
	
	function timeStamp2String(time){
	    var datetime = time;
	    /* datetime.setTime(time); */
	    var year = datetime.getFullYear();
	    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
	    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
	    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
	    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
	    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
	    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
	}
	
	function playCheck(){
		time = new Date(time.getTime()+5000);		
		setCurtimeSpan();
		if(!isPlay){
			return;
		}
		
		var canplay = canPlay();
		
		if(canplay==1){		
			InitPlay($("#video"));
			isPlay = false;
			alert("已经超过该摄像头的结束观看时间");
				$("#timesquen").addClass("error");
		}else if(canplay == -1){
			InitPlay($("#video"));
			isPlay = false;
			alert("还没到该摄像头的观看时间");
			$("#timesquen").addClass("error");
		}
	}
	
	function canPlay(){
		var startArr = playStartTime.split(":");
		var endArr = playEndTime.split(":");
		
		var startH = parseInt(startArr[0]);
		var startM = parseInt(startArr[1]);
		
		var endH = parseInt(endArr[0]);
		var endM = parseInt(endArr[1]);
		
		var curH = time.getHours();
		var curM = time.getMinutes();
		
		if(startH>curH){
			return -1;
		}
		if(startH == curH&&startM>curM){			
			return -1;
		}
		
		if(curH>endH){
			return 1;
		}
		if(curH==endH&&curM>endM){
			return 1;
		}
		
		return 0;
		
	};
		
	</script>
  </head>
  
  <body>
  <div class="div_main">
  <%@include file="/pages/nHead.jsp" %>
    <div style="position: absolute;top: 78px; width: 1500px;">
    	<div style="width: 163px; top: 74px;position: absolute;">
    		<c:forEach items="${monitors }" var="mon">
    			<div class="btn_left" align="right"  data-sn="${mon.sn }" data-timesquen="${mon.timeQuantum }">
    				<span style="margin-right: 8px;">${mon.monitorName }</span>
    			</div>
    		</c:forEach>
    		<!-- <div class="btn_left btn_focus" align="right" >
    			<span style="margin-right: 8px;">摄像头1</span>
    		</div>
    		<div align="right" class="btn_left btn_lose">
    			<span style="margin-right: 8px;">摄像头1</span>
    		</div> -->
    	</div>
    	
    	<div id="u34" class="ax_形状" style="cursor: auto;position: absolute;left: 138px;margin: 0px;padding: 0px;">
	        <img id="u34_img" class="img " src="/images/normol/u34.png">
	        <!-- Unnamed () -->
	        <div id="u35" class="text" style="top: 750px; -webkit-transform-origin: -1.5px 0px 0px; transform-origin: -1.5px 0px 0px;">
	          <p><span></span></p>
	        </div>
	      </div>
	      
      <div style="position: absolute;left: 245px;top: 70px;">
      	<div style="  height: 20px;font-size: 18px;text-align: left;  color: #333333;">
      		此摄像头观看时间段为<span id="timesquen" style="margin-left: 10px;"></span>
      	</div>
      	<div id="video" style="width: 590px;height: 405px;"></div>
      	<div style="width: 590px;"><div class="error" style="float: right; margin-top: 5px;margin-right: 20px;"><span>当前时间:</span> <span id="span_curtime"></span></div></div>
      </div>
    </div>
    </div>
    
	<script type="text/javascript">
			icon(1);
	</script>
  </body>
</html>
