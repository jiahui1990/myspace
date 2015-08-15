var onlineT = "";
var baby_mac ="";

//--------------------------------------------------- 
//日期格式化 
//格式 YYYY/yyyy/YY/yy 表示年份 
//MM/M 月份 
//W/w 星期 
//dd/DD/d/D 日期 
//hh/HH/h/H 时间 
//mm/m 分钟 
//ss/SS/s/S 秒 
//--------------------------------------------------- 
Date.prototype.Format = function(formatStr) 
{ 
var str = formatStr; 
var Week = ['日','一','二','三','四','五','六']; 

str=str.replace(/yyyy|YYYY/,this.getFullYear()); 
str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100)); 

str=str.replace(/MM/,this.getMonth()>9?this.getMonth().toString():'0' + this.getMonth()); 
str=str.replace(/M/g,this.getMonth()); 

str=str.replace(/w|W/g,Week[this.getDay()]); 

str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate()); 
str=str.replace(/d|D/g,this.getDate()); 

str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours()); 
str=str.replace(/h|H/g,this.getHours()); 
str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes()); 
str=str.replace(/m/g,this.getMinutes()); 

str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds()); 
str=str.replace(/s|S/g,this.getSeconds()); 

return str; 
};

//+--------------------------------------------------- 
//| 日期计算 
//+--------------------------------------------------- 
Date.prototype.DateAdd = function(strInterval, Number) { 
var dtTmp = this; 
switch (strInterval) { 
case 's' :return new Date(Date.parse(dtTmp) + (1000 * Number)); 
case 'n' :return new Date(Date.parse(dtTmp) + (60000 * Number)); 
case 'h' :return new Date(Date.parse(dtTmp) + (3600000 * Number)); 
case 'd' :return new Date(Date.parse(dtTmp) + (86400000 * Number)); 
case 'w' :return new Date(Date.parse(dtTmp) + ((86400000 * 7) * Number)); 
case 'q' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number*3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds()); 
case 'm' :return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + Number, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds()); 
case 'y' :return new Date((dtTmp.getFullYear() + Number), dtTmp.getMonth(), dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(), dtTmp.getSeconds()); 
} 
};


var settings={"ActiveX_Url": "http://seeyun.cn/Soft/IPC_Plugin_ky_v1.0.0.13571.exe","downloadServer":""};

$(function(){
	/*$.cookie("baby_mac",null);*/
	
	baby_mac = getCookie();
	
	onlineT = setInterval(online,5000);
	
	if(userid!=""&&userphone==""){
		/*$.cookie("baby_first"+userid,"1");*/
		baby_first = getFirstLoginCookie();
		
		if(baby_first == "1"){
			$("#div_first").css("left", (document.body.clientWidth-400)/2);
			$(window).resize(function(){
				$("#div_first").css("left", (document.body.clientWidth-400)/2);
			});
			
			$("#bg_first").show();
			$("#div_first").show();
			$.cookie("baby_first"+userid,"0");
		}
		
	}
	
	$("#tag_btn_cancel").click(function(){
		hideFirst();
	});
	
	$("#tag_btn_commit").click(function(){
		var telphone = $("#tag_telphone").val();
		if(telphone.length<11){
			alert("手机号码格式不正确。");
			return;
		}
		
		$.ajax({
			type : "POST",
			async:false,
			url : "/personalCenterAction_updateUserPhone.action?time="+(new Date()).valueOf(),
			data:{"In.users.telphone":telphone},
			dataType : 'json',
			success : function(data) {
				alert(data.info);
				if(data.isSuccess=="MDGV1"){
					hideFirst();
				}
			}
		});
		
	});
});

function hideFirst(){
	$("#bg_first").hide();
	$("#div_first").hide();
}

function getFirstLoginCookie(){
	if($.cookie("baby_first"+userid)!="0"){
		$.cookie('baby_first'+userid, "1", { expires: 36500 });
	}
	return $.cookie("baby_first"+userid);
}

function getCookie(){
	if($.cookie("baby_mac")==null){
		$.cookie('baby_mac', Math.uuid(), { expires: 36500 });
	}
	return $.cookie("baby_mac");
}

function online(){
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/loginAction_online.action?time="+(new Date()).valueOf(),
		data:{"In.mac":baby_mac},
		dataType : 'json',
		success : function(data) {
			if(data.isSuccess!="MDGV1"){
				window.location.href = "/?offline=1";
			}
		}
	});
}

function cancleOnline(){
	clearInterval(onlineT);
}
/*!
Math.uuid.js (v1.4)
http://www.broofa.com
mailto:robert@broofa.com
 
Copyright (c) 2010 Robert Kieffer
Dual licensed under the MIT and GPL licenses.
*/
 
/*
 * Generate a random uuid.
 *
 * USAGE: Math.uuid(length, radix)
 *   length - the desired number of characters
 *   radix  - the number of allowable values for each character.
 *
 * EXAMPLES:
 *   // No arguments  - returns RFC4122, version 4 ID
 *   >>> Math.uuid()
 *   "92329D39-6F5C-4520-ABFC-AAB64544E172"
 *
 *   // One argument - returns ID of the specified length
 *   >>> Math.uuid(15)     // 15 character ID (default base=62)
 *   "VcydxgltxrVZSTV"
 *
 *   // Two arguments - returns ID of the specified length, and radix. (Radix must be <= 62)
 *   >>> Math.uuid(8, 2)  // 8 character ID (base=2)
 *   "01001010"
 *   >>> Math.uuid(8, 10) // 8 character ID (base=10)
 *   "47473046"
 *   >>> Math.uuid(8, 16) // 8 character ID (base=16)
 *   "098F4D35"
 */
(function() {
  // Private array of chars to use
  var CHARS = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'.split('');
 
  Math.uuid = function (len, radix) {
    var chars = CHARS, uuid = [], i;
    radix = radix || chars.length;
 
    if (len) {
      // Compact form
      for (i = 0; i < len; i++) uuid[i] = chars[0 | Math.random()*radix];
    } else {
      // rfc4122, version 4 form
      var r;
 
      // rfc4122 requires these characters
      uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
      uuid[14] = '4';
 
      // Fill in random data.  At i==19 set the high bits of clock sequence as
      // per rfc4122, sec. 4.1.5
      for (i = 0; i < 36; i++) {
        if (!uuid[i]) {
          r = 0 | Math.random()*16;
          uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
        }
      }
    }
 
    return uuid.join('');
  };
 
  // A more performant, but slightly bulkier, RFC4122v4 solution.  We boost performance
  // by minimizing calls to random()
  Math.uuidFast = function() {
    var chars = CHARS, uuid = new Array(36), rnd=0, r;
    for (var i = 0; i < 36; i++) {
      if (i==8 || i==13 ||  i==18 || i==23) {
        uuid[i] = '-';
      } else if (i==14) {
        uuid[i] = '4';
      } else {
        if (rnd <= 0x02) rnd = 0x2000000 + (Math.random()*0x1000000)|0;
        r = rnd & 0xf;
        rnd = rnd >> 4;
        uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
      }
    }
    return uuid.join('');
  };
 
  // A more compact, but less performant, RFC4122v4 solution:
  Math.uuidCompact = function() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
      return v.toString(16);
    });
  };
})();