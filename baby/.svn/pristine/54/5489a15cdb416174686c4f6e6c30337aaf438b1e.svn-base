function showMessage(info){
	$("#err_info").html(info);
	$("#u10").show();
	$("#u10").css("visibility","visible");
}

function insertPhoneCode(){
	$("#u3").css("top", "314px");
	$("#btn_sendcode").attr("disabled","disabled");
	clearInterval(resendCountDown);
	resendCountDown = setInterval(resendCount,1000);
	$("#u66").show();
}
function hidePhoneCode(){
	$("#u3").css("top", "352px");
	$("#u66").hide();
}

var lastUsername = "";
var resendTime = 120;
var curResendTime = 120;
var resendCountDown = null;

function resendCount(){
	curResendTime--;
	
	if(curResendTime<=0){
		clearInterval(resendCountDown);
		$("#btn_sendcode").removeAttr("disabled");
		$("#btn_sendcode").val("再次发送");
		return;
	}
	
	$("#btn_sendcode").val(curResendTime+"秒后可再发送");
}
function confirmLogin(){
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/loginAction_confirmOnline.action?time="+(new Date()).valueOf(),
		data:{"In.mac":$("#txt_mac").val()},
		dataType : 'json',
		success : function(data) {
			if(data.isSuccess=="MDGV1"){
				window.location.href = data.url;
			}else{
				showMessage(data.info);
				/**
				 * 弹出框确定按钮
				 */
				$("#u15").unbind();
				$("#u15").click(function(){
					$("#u10").hide();
					$("#u10").css("visibility","hidden");
				});
			}
		}
	});
}

$(function(){
	$("body").css("left", (document.documentElement.clientWidth-795)/2);
	$(window).resize(function(){
		$("body").css("left", (document.documentElement.clientWidth-795)/2);
	});
	
	$("#u2_input").blur(function(){
		if(lastUsername==$(this).val()){
			/*lastUsername=$(this).val();*/
			return;
		}

		lastUsername=$(this).val();
		hidePhoneCode();
		
		$("#u3_input").val("");
	});
	
	/*alert(document.body.clientWidth);*/
	$("#u2_input").keyup(function(event){
		if(event.keyCode==13){
			$("#u3_input").focus();
		}
	});
	
	$("#u3_input").keyup(function(event){
		if(event.keyCode==13){
			$("#u4").click();
		}
	});
	
	if($.cookie("baby_username")!=null){
		$("#u6_input").attr("checked","checked");
		$("#u2_input").val($.cookie("baby_username"));
		lastUsername = $.cookie("baby_username");
	}
	if($.cookie("baby_password")!=null){
		$("#u6_input").attr("checked","checked");
		$("#u3_input").val($.cookie("baby_password"));
	}
	
	if(offline == "1"){
		/*$("#u16").css("margin-top","20");*/
		showMessage("您的登录已失效，请重新登录。");
		/**
		 * 弹出框确定按钮
		 */
		$("#u15").unbind();
		$("#u15").click(function(){
			$("#u10").hide();
			$("#u10").css("visibility","hidden");
		});
	}
	
	$("#txt_mac").val(baby_mac);
	$("#u4").click(function(){
		if($("#u6_input").attr("checked")=="checked"){
			$.cookie('baby_username', $("#u2_input").val(), { expires: 36500 });
			$.cookie('baby_password', $("#u3_input").val(), { expires: 36500 });
		}else{
			$.cookie('baby_username', null);
			$.cookie('baby_password', null);
		}
		
		/*alert($("#form_login").serialize());*/		
		/*showMessage("123456");*/
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/loginAction_login.action?time="+(new Date()).valueOf(),
			data : $("#form_login").serialize(),
			dataType : 'json',
			success : function(data) {
				if(data.jumpInfo.isSuccess=="MDGV1"){
					window.location.href = data.jumpInfo.url;
				}else{
					showMessage(data.jumpInfo.info);
					/**
					 * 弹出框确定按钮
					 */
					$("#u15").unbind();
					$("#u15").click(function(){
						$("#u10").hide();
						$("#u10").css("visibility","hidden");
					});

					if(data.jumpInfo.flag=="MSG4007"){						
						insertPhoneCode();
						if(typeof(data.code)!="undefined"){
							$("#u66_input").val(data.code);
						}
						return;
					}

					if(data.jumpInfo.flag=="MSG4006"){
						/**
						 * 弹出框确定按钮
						 */
						$("#u15").unbind();
						$("#u15").click(function(){
							
							$("#u10").hide();
							$("#u10").css("visibility","hidden");
							
							confirmLogin();
						});
						
						return;
					}
				}
			}
		});
		
	});
	
	/**
	 * 找回密码按钮
	 */
	$("#u8").unbind();
	
	clearInterval(onlineT);
});
