function getarea(kId){
	if(kId==null||kId<=0){
		return;
	}
	
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/monitorManageAction_getOneLebelArea.action?time="+(new Date()).valueOf(),
		data:{"In.kindergarten.id":kId},
		dataType : 'json',
		success : function(data) {
			$("#areaId").children().remove();
			$.each(data,function(i,area){
				$("#areaId").prepend('<option value="'+area.id+'">'+area.name+'</option>');
			});
			
			$("#areaId").change();
		}
	});
}

function getcarea(pid){
	if(pid==null||pid<=0){
		return;
	}
	
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/monitorManageAction_getTwoLebelArea.action?time="+(new Date()).valueOf(),
		data:{"In.area.id":pid},
		dataType : 'json',
		success : function(data) {
			$("#careaId").children().remove();
			$.each(data,function(i,area){
				$("#careaId").prepend('<option value="'+area.id+'">'+area.name+'</option>');
			});
			
			
			$("#careaId").change();
		}
	});
}

$(function(){
	
	$('.pagination').jqPagination({
		current_page:curpage,
		page_string:'当前 {current_page} / {max_page}' ,
	    paged: function(page) {
	    	window.location.href = "/userManageAction_initGenerateUsers.action?page="+page;
	    }
	});
	
	$("#kinId").change(function(){			
			getarea($(this).val());
	});
	
	$("#areaId").change(function(){			
			getcarea($(this).val());
	});
	
	$("#allCheck").click(function(){
		if($(this).attr("checked")){
			$(".check_id").attr("checked",'true');
		}else{
			$(".check_id").removeAttr("checked");
		}
	});
	$("#u574").click(function(){
		if(pages==0){
			turn('/userManageAction_initUserManage.action');
			return;
		}
		if(!confirm("当前有未保存的用户，是否继续退出。")){
			return;
		}
		turn('/userManageAction_initUserManage.action');
	});
	$("#u572").click(function(){
		if(pages==0){
			alert("没有需要保存的用户");
			return;
		}
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/userManageAction_saveUsers.action?time="+(new Date()).valueOf(),
			dataType : 'json',
			success : function(data) {
				if(data.isSuccess=="MDGV1"){
					alert(data.info);
					window.location.reload();
				}else{
					alert(data.info);
				}
			}
		});
	});
	
	$("#u570").click(function(){
		var ids='';
		$(".check_id").each(function(){
			if($(this).attr("checked")){
				ids += $(this).val()+",";
			}			
		});
		
		if(ids==''){
			alert("未选中任何项");
			return;
		}
		
		if(!confirm("您确认批量删除选中的用户吗？")){
			return;
		};
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/userManageAction_batchDeleteUser.action?time="+(new Date()).valueOf(),
			data: {"In.ids":ids},
			dataType : 'json',
			success : function(data) {
				if(data.isSuccess=="MDGV1"){
					alert(data.info);
					window.location.reload();
				}else{
					alert(data.info);
				}
			}
		});
		
	});
	

	
	$("#kinId").change();
	
	$("#role").change(function(){
		if($(this).val()=="1"){
			$("#tr_ex").nextAll().hide();
		}else if($(this).val()=="2"){
			$("#tr_ex").nextAll().show();
			$("#tr_kin").nextAll().hide();
		}else{
			$("#tr_ex").nextAll().show();
		}
	});
	
	$("#btn_generateUsers").click(function(){
		if(isNaN($("#userCount").val())||$("#userCount").val()==""){
			alert("生成用户数错误");
			return;
		}
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/userManageAction_generateUsers.action?time="+(new Date()).valueOf(),
			data: $("#form_users").serialize(),
			dataType : 'json',
			success : function(data) {
				if(data.isSuccess=="MDGV1"){
					alert(data.info);
					window.location.reload();
				}else{
					alert(data.info);
				}
			}
		});
	});
	
	var nowDate = new Date();
	$('#starttime').val(nowDate.Format("YYYY-MM-DD hh:mm:ss"));
	$('#endtime').val(nowDate.DateAdd("y",1).Format("YYYY-MM-DD hh:mm:ss"));
	
	$('#starttime').mobiscroll().date({
		dateFormat: 'yy-mm-dd 00:00:00', 
		endYear:2050,
		theme: "",     // Specify theme like: theme: 'ios' or omit setting to use default 
        mode: "scroller",       // Specify scroller mode like: mode: 'mixed' or omit setting to use default 
        display: "bubble", // Specify display mode like: display: 'bottom' or omit setting to use default 
        lang: "zh"        // Specify language like: lang: 'pl' or omit setting to use default 
    });
	$('#endtime').mobiscroll().date({
		dateFormat: 'yy-mm-dd 00:00:00', 
		endYear:2050,
		theme: "",     // Specify theme like: theme: 'ios' or omit setting to use default 
        mode: "scroller",       // Specify scroller mode like: mode: 'mixed' or omit setting to use default 
        display: "bubble", // Specify display mode like: display: 'bottom' or omit setting to use default 
        lang: "zh"        // Specify language like: lang: 'pl' or omit setting to use default 
    });

});