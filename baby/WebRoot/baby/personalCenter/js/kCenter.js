$(function(){
	
	$("#btn_commit").click(function(){
		if($("#telphone").val()!=""&&$("#telphone").val().length<11){
			alert("手机格式不正确");
			return;
		}
		if($("#password").val()!=$("#confirm").val()){
			alert("两次输入密码不一致。");
			return;
		}

		if($("#password").val()!=""&&$("#password").val()<6){
			alert("密码长度不够6位,强度过低。");
			return;
		}
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/personalCenterAction_updateUsers.action?time="+(new Date()).valueOf(),
			data: $("#form_user").serialize(),
			dataType : 'json',
			success : function(data) {
				alert(data.info);
			}
		});
	});
	
	$("#btn_cancel").click(function(){
		turn('/userMonitorAction_actualTimeMonitorK.action');
	});
});