var resendCount = 120;
var curCount = 120;
var countDown = null;

$(function(){
	$("#next").click(function(){
		if($("#telphone").val().length!=11){
			alert("手机号码格式不正确");
			return;
		}
		
		if($("#code").val().length!=6){
			alert("验证码为6位数字");
			return;
		}
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/loginAction_vertifyForgotPassCode.action?time="+(new Date()).valueOf(),
			data : $("#form_forgotPass").serialize(),
			dataType : 'json',
			success : function(data) {
				if(data.isSuccess=="MDGV1"){
					$(".tabhead").removeClass("focus");
					$(".tabhead").eq(1).addClass("focus");
					$("#tab1").hide();
					$("#tab2").show();
				}else{
					alert(data.info);
				}
			}
		});
		
		
		
	});
	
	$("#sendphonecode").click(function(){
		var telphone = $("#telphone").val();
		if(telphone.length!=11){
			alert("手机号码格式不正确");
			return;
		}
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/loginAction_sendForgotPassCode.action?time="+(new Date()).valueOf(),
			data : $("#form_forgotPass").serialize(),
			dataType : 'json',
			success : function(data) {
				if(data.isSuccess=="MDGV1"){
					$("#sendphonecode").hide();
					$("#resendphonecode").show();
					
					countDown = setInterval(resendCountDown,1000);
					curCount = resendCount;
					$("#resendphonecode").html(curCount+"秒");
					$("#resendphonecode").unbind();
				}else{
					alert(data.info);
				}
			}
		});
		
		
	});
	
	$("#resendphonecode").click(function(){
		resendClick();
	});
	
	$("#password").blur(function(){
		passVertify();
	});
	$("#confirm").blur(function(){
		confirmVertify();
	});
	
	$("#submit").click(function(){
		if(!passVertify()){
			$("#password").focus();
			return;
		}
		if(!confirmVertify()){
			$("#confirm").focus();
			return;
		}
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/loginAction_modifyPassword.action?time="+(new Date()).valueOf(),
			data : $("#form_forgotPass").serialize(),
			dataType : 'json',
			success : function(data) {
				if(data.isSuccess=="MDGV1"){
					alert("密码修改成功，即将转到登陆页面。");
					window.location.href = "/";
				}else{
					alert(data.info);
				}
			}
		});
		
	});
});

function passVertify(){
	if($("#password").val().length<6){
		$("#passError").html("密码字符小于6个，强度过低");
		$("#passError").show();
		return false;
	}
	$("#passError").hide();
	return true;
}
function confirmVertify(){
	if($("#confirm").val()!=$("#password").val()){
		$("#confirmError").html("两次输入密码不一致");
		$("#confirmError").show();
		return false;
	}
	$("#confirmError").hide();
	return true;
}

function resendClick(){
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/loginAction_sendForgotPassCode.action?time="+(new Date()).valueOf(),
		data : $("#form_forgotPass").serialize(),
		dataType : 'json',
		success : function(data) {
			if(data.isSuccess=="MDGV1"){
				countDown = setInterval(resendCountDown,1000);
				curCount = resendCount;
				$("#resendphonecode").html(curCount+"秒");
				$("#resendphonecode").unbind();
			}else{
				alert(data.info);
			}
		}
	});
	
	
}

function resendCountDown(){
	curCount -=1;
	if(curCount<=0){
		clearInterval(countDown);
		$("#resendphonecode").html("再次发送");
		
		$("#resendphonecode").click(function(){
			resendClick();
		});
		return;
	}
	$("#resendphonecode").html(curCount+"秒");
}