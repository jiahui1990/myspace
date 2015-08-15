/// <reference path="../Scripts/Common.js" />
var _DeviceByGroup;

var _videoWidth = 590;
var _videoHeight = 405;

///变量
var UNDEF = "undefined", OBJECT = "object", SEE1000PLAYER_PLUGIN = "ipcamera plugin", SEE1000PLAYER_MIME_TYPE = "application/x-ipcameraplugin", SEE1000PLAYER_OCX = "ipcamera.ipcameraplugin.1", SEE1000PLAYER_CLSID = "CLSID:DFE02170-BE7A-5FD5-9483-BA47C65A3518", SEE1000PLAYER_OCX_OLD = "SEE1000PLAYEROCX.See1000PlayerOcxCtrl.1", SEE1000PLAYER_CLSID_OLD = "CLSID:98AD92BA-94D9-4C3F-AFAE-7786EDD40B7F", ON_READY_STATE_CHANGE = "onreadystatechange", win = window, doc = document, nav = navigator, plugin = false, regObjArr = [], objIdArr = [], listenersArr = [], storedAltContent, storedAltContentId, storedCallbackFn, storedCallbackObj, isDomLoaded = false, isExpressInstallActive = false, dynamicStylesheet, dynamicStylesheetMedia, autoHideShow = true;
var ua = function () {
	var w3cdom = typeof doc.getElementById != UNDEF && typeof doc.getElementsByTagName != UNDEF && typeof doc.createElement != UNDEF, u = nav.userAgent.toLowerCase(), p = nav.platform.toLowerCase(), windows = p ? /win/.test(p) : /win/.test(u), mac = p ? /mac/.test(p) : /mac/.test(u), webkit = /webkit/.test(u) ? parseFloat(u.replace(/^.*webkit\/(\d+(\.\d+)?).*$/, "$1")) : false, ie = ! +"\v1", playerVersion = 0, d = null, hasplayer = false;
	if (typeof nav.plugins != UNDEF && typeof nav.plugins[SEE1000PLAYER_PLUGIN] == OBJECT) {
		a = nav.plugins[SEE1000PLAYER_PLUGIN];
		if (a && !(typeof nav.mimeTypes != UNDEF && nav.mimeTypes[SEE1000PLAYER_MIME_TYPE] && !nav.mimeTypes[SEE1000PLAYER_MIME_TYPE].enabledPlugin)) {
			plugin = true;
			hasplayer = true;
			ie = false;
		}
	}
	else /*if (typeof(win.ActiveXObject) != UNDEF)*/ {
		try {
			var a = new ActiveXObject(SEE1000PLAYER_OCX);
			if (a) {
				ie = true;
				hasplayer = true;
			}
			a = null;
		}
		catch (e) {
		}
		try {
			var b = new ActiveXObject(SEE1000PLAYER_OCX_OLD);
			if (b) {
				ie = true;
				hasplayer = true;
			}
			b = null;
		}
		catch (e) {
		}
	}
	return {
		w3: w3cdom, hp: hasplayer, pv: playerVersion, wk: webkit, ie: ie, win: windows, mac: mac
	};
} ();


///基本方法
//初始化数据.

function FindDeviceConfig(id) {
	for (var i = 0; i < _DeviceConfig.length; i++) {
		var dc = _DeviceConfig[i];
		if (dc[dcKey.DeviceID] == id)
			return dc;
	}
}

function FindDevice(id) {
	for (var i = 0; i < _Device.length; i++) {
		var d = _Device[i];
		if (d.Id == id)
			return d;
	}
}

//遍历得到适合JsTreede Json数据
//加入了设备的绑定
//私有方法
function DeepGroup(arr, count) {
	var myArr = [];
	$.each(arr, function (i) {
		var obj = {
			attributes: { id: arr[i]["ID"], attr: "g" },
			data: arr[i]["GroupName"],
			children: []
		};

		var myCount = [0, 0];

		if (arr[i]["Childs"].length > 0) {
			obj["children"] = DeepGroup(arr[i]["Childs"], myCount);
		}


		var ds = _DeviceByGroup[obj.attributes.id];
		if (ds) {
			for (var i = 0; i < ds.length; i++) {
				var d = ds[i];

				if (count) {
					if (d.online) myCount[0]++;
					myCount[1]++;
				}

				obj.children.push(SpliceDevice(d));
			}
		}

		if (count) {
			obj["data"] = obj["data"] + "(" + myCount[0] + "/" + myCount[1] + ")";
			count[0] += myCount[0];
			count[1] += myCount[1];
		}

		if (myCount[1] == "0" || myCount[1] == 0) {

		}
		else {
			myArr.push(obj);
		}
	});

	return myArr;
}



function SpliceDevice(d) {
	var deviceObj = {
		attributes: { id: d.Id, attr: "d", isEnd: "true", channel: "" },
		data: { title: d.DeviceName, icon: d.online ? _OnLinePic : _UnLinePic }
	};

	//绑定通道

	var channels = (d.Channel == null) ? "" : d.Channel.split(',');
	//			if (channels.length > 1) {
	//				deviceObj.attributes.isEnd = "false";
	//				deviceObj["children"] = [];
	//				for (var ii = 0; ii < channels.length; ii++) {
	//					var cns = channels[ii].split(':');

	//					deviceObj["children"].push({
	//						attributes: { id: d.Id, attr: "d", channel: cns[0], style: " color: #666666;" },
	//						data: { title: cns[1], icon: d.online ? _OnLinePic : _UnLinePic }
	//					});
	//				}
	//			}

	if (channels.length >= 1) {
	    if (channels[0] != "") {
			deviceObj.attributes.isEnd = "false";
			deviceObj["children"] = [];
			var chanarr = new Array();
			var channel = "";
			for (var ii = 0; ii < channels.length; ii++) {
			    if (channels[ii] != "") {
			        var cns = channels[ii].split(':');
			        
			        if (cns[1].substr(cns[1].length - 1) == ";")
			             channel = cns[1].substring(0, cns[1].length - 1);
			        chanarr[0] = cns[0];
			        chanarr[1] = channel == "" ? cns[1] : channel;

			        deviceObj["children"].push({
			            attributes: { id: d.Id, attr: "d", channel: chanarr[0], style: " color: #666666;" },
			            data: { title: chanarr[1], icon: d.online ? _OnLinePic : _UnLinePic }
			        });
			    }
			}
		}
	}

	return deviceObj;
}

///初始化插件
function InitPlay(container) {
	container = $("#video");

	//先清空
	container.empty();

	if (!ua.win) {
		container.css('font-weight', 'bold');
		container.append('视频观看暂不支持非Windows操作系统观看，请使用Windows操作系统观看。');
		return;
	}

	if (!ua.hp) {
		container.css('font-weight', 'bold');
		container.append('<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>您还没有安装网络摄像机播放器软件，将不能正常观看视频。</p><p>' +
								'<a href="' + settings.downloadServer + settings.ActiveX_Url + '">点击这里下载并安装最新版网络摄像机播放器软件</a></p><p>&nbsp;</p>');
		//'<a href="#">点击这里下载并安装最新版网络摄像机播放器软件</a></p><p>&nbsp;</p>');
		//window.setInterval(function() {
		try {
			/*var a = new ActiveXObject("ipcamera.ipcameraplugin.1");
			if (typeof (a) != UNDEF) {
				window.location.reload();
			}*/
		}
		catch (e) { }
		//}, 1000); 
		return;
	}

	Player = createPlayer(container, { id: 'VideoPlayer', width: (_videoWidth ? _videoWidth : '800'), height: (_videoHeight ? _videoHeight : '640') });
	if (Player) {
		try {
			Player.StopAllVideo();
			SetScreenNum(1, 1);
		}
		catch (e) {

		}
	}

	try {
		Player.SetAuthLevel(actor);
	}
	catch (e) { }

	if (Player && (typeof (Player) != UNDEF) && (typeof (Player.GetVersionNum) != UNDEF)) {
		curversion = Player.GetVersionNum();
		/*if (curversion == 0) {//没有插件
			Player = null;
			container.empty().css('font-weight', 'bold').append(formatString(lang.play_v_ax_update, settings.downloadServer + settings.ActiveX_Url)).append('<p>&nbsp;</p>');

			$(lang.play_v_ax_update).one('click', function () {
				$(this).after(lang.play_v_ax_install);
			})

			return;
		}

		else if (curversion < reqversion) {
			Player = null;
			container.empty().css('font-weight', 'bold').append(formatString(lang.play_g_ax_newver, reqversion, curversion)).append($(formatString(lang.play_v_ax_update, settings.downloadServer + settings.ActiveX_Url)).one('click', function () {
				$(this).after(lang.play_v_ax_install);
			})).append('<p>&nbsp;</p>');
			return;
		}
		else if (curversion < optversion) {*/
			$('#playnote').show().html(formatString(lang.play_update_recommend, curversion, 20000, settings.downloadServer + settings.ActiveX_Url));
			window.setTimeout(function () {
				$('#playnote').hide().empty();
			}, 60000);
		/*}*/

		try {
			if (Lang.toLowerCase() == 'en-us') Player.SelectLanguage(2);
			else if (Lang.toLowerCase() == 'zh-cn') Player.SelectLanguage(1);
			else if (Lang.toLowerCase() == 'zh-tw') Player.SelectLanguage(3);
		}
		catch (e) { }

	}
};

var lang = {"play_update_recommend": '<p><br />您的播放器版本是：{0}，现已提供更新版本：{1} 下载。<br /><a href="{2}" target="_blank">点击这里下载网络摄像机播放器插件 {1} 版</a></p>'};

var Lang = "zh-cn";
function formatString(){var t=[],n;if((tp=typeof arguments[0])!="string")throw"format string can not be a :"+tp;for(n=1;n<arguments.length;n++)t.push(arguments[n]);return arguments[0].replace(/\{(\d+)\}/g,function(n,i){return t[i]})}

//创建插件
function createPlayer(container, attObj) {
	var r;
	if (typeof (container) == UNDEF) return r;
	if (ua.wk && ua.wk < 312) {
		return r;
	}
	if (typeof attObj.id === UNDEF) attObj.id = 'VideoPlayer';
	if (typeof attObj.name === UNDEF) attObj.id = 'VideoPlayer';

	container.empty();

	if (ua.ie && ua.win) {
		var att = "";
		for (var i in attObj) {
			if (attObj[i] != Object.prototype[i]) {
				if (i.toLowerCase() == "data") {
					parObj.movie = attObj[i];
				}
				else if (i.toLowerCase() == "styleclass") {
					att += ' class="' + attObj[i] + '"';
				}
				else if (i.toLowerCase() != "classid") {
					att += ' ' + i + '="' + attObj[i] + '"';
				}
			}
		}
		//container.append($('<object type="' + SEE1000PLAYER_MIME_TYPE + '" classid="' + SEE1000PLAYER_CLSID + '" ' + att + '></object>'));
		container[0].innerHTML = '<object type="' + SEE1000PLAYER_MIME_TYPE + '" classid="' + SEE1000PLAYER_CLSID + '" ' + att + '></object>';
		r = doc.getElementById(attObj.id);
		if (r == null || typeof (r.GetVersionNum) == UNDEF) {
			container[0].innerHTML = '<object classid="' + SEE1000PLAYER_CLSID_OLD + '" ' + att + '></object>';
			r = doc.getElementById(attObj.id);
		}
	}
	else {
		var o = doc.createElement(OBJECT);
		o.setAttribute("type", SEE1000PLAYER_MIME_TYPE);
		for (var m in attObj) {
			if (attObj[m] != Object.prototype[m]) {
				// filter out prototype additions from other potential libraries
				if (m.toLowerCase() == "styleclass") {
					// 'class' is an ECMA4 reserved keyword
					o.setAttribute("class", attObj[m]);
				}
				else if (m.toLowerCase() != "classid") {
					// filter out IE specific attribute
					o.setAttribute(m, attObj[m]);
				}
			}
		}
		container.append(o);
		r = doc.getElementById(attObj.id);
	}
	return r;
};

//视频播放
function PlayUrl(url, name, num, args, pValue) {
	//			var arr = args.split('&');
	//			var arg = "";
	//			for (var i = 0; i < arr.length; i++) {
	//				arg +="<br />" + arr[i];
	//			}

	//			Alert.TipInside("Url:" + url + "<br/>name:" + name + "<br/>num:" + num + arg);
	try {
		Player.PlayUrlEx(url, name, num, args);
	}
	catch (e) {
		try {
			Player.PlayUrl(url, name, num);
		}
		catch (e) {
			try {
				Player.PlayUrl(url, name, num);
			}
			catch (ex) {

			}
		}
	}
};

function SetScreenNum(h, v) {
	try {
		if (Player) Player.SetScreenNum(h, v);
		
	}
	catch (e) {
	}
};

///直连
function PlayDeviceConnect(device, chnno, name, num, dc) {

}

///NVS 为什么会想到用这种丑陋的方式。。。shit
function PlayDeviceNVS(device, chnno, name, num, dc) {
	var key = (device.LoginKey) ? device.LoginKey : "";
	var url = device.URI + chnno + '?loginkey=' + key;
	var args;

	if (dc.TransferID == emptyGuid) {
		//走直连
		args = 'sn=' + device.SN
			 + '&actor=' + device.PowerValue

			 + '&usealarm=' + dc[dcKey.UserAlarmService]
			 + '&usetransfer=' + dc[dcKey.TransferType]
			 + '&natip1=' + ""
			 + '&natport1=' + 0
			 + '&natip2=' + ""
			 + '&natport2=' + 0
			 + '&transferip=' + ""
			 + '&transferport=' + 0;

	} else {
		//走nvs
	var port = parseInt(dc[dcKey.RelayServerPort]) - 1;
	args = 'sn=' + device.SN
			 + '&actor=' + device.PowerValue
			 + '&usealarm=' + dc[dcKey.UserAlarmService]
			 + '&usetransfer=' + (dc[dcKey.UseTransferService] >= 8 ? dc[dcKey.TransferType] : dc[dcKey.UseTransferService])
			 + '&natip1=' + dc[dcKey.NATServerIP1]
			 + '&natport1=' + dc[dcKey.NATServerPort1]
			 + '&natip2=' + dc[dcKey.NATServerIP2]
			 + '&natport2=' + dc[dcKey.NATServerPort2]
			 + '&transferip=' + ""
			 + '&transferport=' + 0
			 + '&nvsip=' + dc[dcKey.RelayServerIP]
			 + '&nvsport=' + ((port > -1) ? port : 0);
	}

	PlayUrl(url, name, num, args, device.PowerValue);
}

function PlayDeviceReplay(device, chnno, name, num, dc) {
	var key = (device.LoginKey) ? device.LoginKey : "";
	var url = device.URI + chnno + '?loginkey=' + key;
	var args;
	if (dc) {
		args = 'sn=' + device.SN
			 + '&actor=' + device.PowerValue //角色问题
			 + '&usealarm=' + dc[dcKey.UserAlarmService]
			 + '&usetransfer=' + dc[dcKey.UseTransferService]
			 + '&natip1=' + dc[dcKey.NATServerIP1]
			 + '&natport1=' + dc[dcKey.NATServerPort1]
			 + '&natip2=' + dc[dcKey.NATServerIP2]
			 + '&natport2=' + dc[dcKey.NATServerPort2]
			 + '&transferip=' + dc[dcKey.RelayServerIP]
			 + '&transferport=' + dc[dcKey.RelayServerPort];
	}
	else {
		//不允许有没有配置的设备
		Alert.TipInside("配置数据出错,出错sn:" + device.SN);
		return false;
	}
	//ars = "sn=RDPTTEST00001&actor=3&usealarm=518&usetransfer=0&natip1=&natport1=0&natip2=&natport2=0&transferip=&transferport=0";
	PlayUrl(url, name, num, args, device.PowerValue);
	return true;
}

function DeviceUseGroup() {
	var deviceUseJson = {
		attributes: { id: "deviceUse", attr: "g" },
		data: "最近使用设备"
	};

	var usedDevice = GetRecentlyUserDevice();

	var arr = [];
	var arrCount = [0, 0];
	for (var i = usedDevice.length - 1; i >= 0; i--) {
		var id = usedDevice[i];
		var d = FindDevice(id);
		if (!d || d == null || d == "") continue;

		if (d.online) arrCount[0]++;
		arrCount[1]++;

		arr.push(SpliceDevice(d));
	}

	deviceUseJson.data = deviceUseJson.data + "(" + arrCount[0] + "/" + arrCount[1] + ")";
	deviceUseJson.children = arr;
	return deviceUseJson;
}

//调用
$(document).ready(function () {
	///初始化插件
	InitPlay($("#video"));

});

function SortDevice(devs) {
	if (devs.length == 0 && actor == '1') {

		if (!_window) {
			Alert.Confirm("您还没有添加设备，现在就去添加？", function () {
				_window = true;
				setTimeout(function () {
					window.location.href = "/device/DeviceManager";
				}, 1000);
			}, "top");
		}
	}

	devs = JSON.stringify(devs);
	devs = devs.replace(/\"dn\"/g, 'DeviceName');
	devs = devs.replace(/\"i\"/g, 'Id');
	devs = devs.replace(/\"s\"/g, 'SN');

	devs = devs.replace(/\"lu\"/g, 'LastUpdate');
	devs = devs.replace(/\"p\"/g, 'PID');

	devs = devs.replace(/\"v\"/g, 'Visible');
	devs = devs.replace(/\"a\"/g, 'Actor');

	devs = devs.replace(/\"u\"/g, 'URI');
	devs = devs.replace(/\"l\"/g, 'LoginKey');

	devs = devs.replace(/\"c\"/g, 'Channel');
	devs = devs.replace(/\"pv\"/g, 'PowerValue');
	devs = devs.replace(/\"d\"/g, 'Dtime');


	var halou = eval('(' + devs + ')');
	//sd = JSON.parse(sd);
	//alert(sd);
	_Device = halou;
	//debugger;
	_Device.sort(function (a, b) {
		return a.DeviceName.localeCompare(b.DeviceName)
	});

	var json = {};
	$.each(_Device, function (i, d) {

		var g = json[d.PID];
		if (!g)
			json[d.PID] = [];

		json[d.PID].push(d);
	});

	_DeviceByGroup = json;
}

function InitEvent() {
	$('#ptz_screen_1').click(function () {
		SCR_H = 1;
		SCR_V = 1;
		SetScreenNum(SCR_H, SCR_V);
		return false;
	});

	$('#ptz_screen_4').click(function () {
		SCR_H = 2;
		SCR_V = 2;
		SetScreenNum(SCR_H, SCR_V);
		return false;
	});
	$('#ptz_screen_9').click(function () {
		SCR_H = 3;
		SCR_V = 3;
		SetScreenNum(SCR_H, SCR_V);
		return false;
	});
	$('#ptz_fullscreen').click(function () {
		if (Player) Player.fullscreen();
		return false;
	});

	$('#ptz_save').click(function () {
		if (Player) Player.SetFilePath();
		return false;
	});

	$('#ptz_openvideo').click(function () {
		if (Player) Player.OpenSaveVedioFolder();
		return false;
	});

	$('#ptz_openpic').click(function () {
		if (Player) Player.OpenSavePicFolder();
		return false;
	});

	$('#ptz_box div').click(function () {
		var direction = $(this).attr('value');
		if (direction) Ptz_Ctrl(direction, PTZ_Speed);
	}).mousedown(function () {
		$(this).addClass('mousedown');
		var direction = $(this).attr('value');
		if (direction) {
			window.clearTimeout(ptz_timeout);
			window.clearInterval(ptz_interval);
			ptz_timeout = window.setTimeout(function () {
				ptz_interval = window.setInterval(function () {
					Ptz_Ctrl(direction, PTZ_Speed);
				}
					, 200);
			}
				, 100);
		};
	}).mouseout(function () {
		$(this).removeClass('mousedown');
		window.clearTimeout(ptz_timeout);
		window.clearInterval(ptz_interval);
	}).mouseup(function () {
		$(this).removeClass('mousedown');
		window.clearTimeout(ptz_timeout);
		window.clearInterval(ptz_interval);
	}).hover(function (e) {
		$(this).addClass('hover');
		var title = $(this).attr('title');
		window.status = title;
	}, function (e) {
		$(this).removeClass('hover');
		window.status = '';
	});

	function Ptz_Ctrl(direction, speed) {
		try {
			if (Player) Player.Ptz_Ctrl(direction, speed);
		}
		catch (e) {
		}
	};

}

function ctrl(str, obj) {
	switch (str) {
		case "ptz_screen_1":
			SCR_H = 1;
			SCR_V = 1;
			SetScreenNum(SCR_H, SCR_V);
			break;
		case "ptz_screen_4":
			SCR_H = 2;
			SCR_V = 2;
			SetScreenNum(SCR_H, SCR_V);
			break;
		case "ptz_screen_9":
			SCR_H = 3;
			SCR_V = 3;
			SetScreenNum(SCR_H, SCR_V);
			break;
		case "ptz_fullscreen":
			if (Player) Player.fullscreen();
			break;
		case "ptz_save":
			if (Player) Player.SetFilePath();
			break;
		case "ptz_openvideo":
			if (Player) Player.OpenSaveVedioFolder();
			break;
		case "ptz_openpic":
			if (Player) Player.OpenSavePicFolder();
			break;
	}

	var my = $(obj);
	var str1 = my.html();
	if (str1.length > 2) {
		//my.addClass("selectbig");
		return;
	}
	else {
		my.addClass("select");
	}

	if (_operateSelect) {
		var str = _operateSelect.html();
		if (str.length > 2) {
			_operateSelect.removeClass("selectbig");
		}
		else {
			_operateSelect.removeClass("select");
		}
	}

	_operateSelect = my;
}


