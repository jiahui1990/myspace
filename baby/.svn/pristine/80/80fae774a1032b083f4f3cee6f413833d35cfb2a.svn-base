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
			
			$("#areaId").append('<option value="-1">新增</option>');
			
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
			
			$("#careaId").append('<option value="-1">新增</option>');
			
			$("#careaId").change();
		}
	});
}

$(function(){
	$("#allCheck").click(function(){
		if($(this).attr("checked")){
			$(".check_id").attr("checked",'true');
		}else{
			$(".check_id").removeAttr("checked");
		}
	});
	$("#u574").click(function(){
		if(pages==0){
			turn('/monitorManageAction_initMonitorManage.action');
			return;
		}
		if(!confirm("当前有未保存的设备，是否继续退出。")){
			return;
		}
		turn('/monitorManageAction_initMonitorManage.action');
	});
	$("#u572").click(function(){
		if(pages==0){
			alert("没有需要保存的设备");
			return;
		}
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/monitorManageAction_saveMonitors.action?time="+(new Date()).valueOf(),
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
		
		if(!confirm("您确认批量删除选中的设备吗？")){
			return;
		};
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/monitorManageAction_batchDeleteMonitor.action?time="+(new Date()).valueOf(),
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
	
	$('.pagination').jqPagination({
		current_page:curpage,
		page_string:'当前 {current_page} / {max_page}' ,
	    paged: function(page) {
	    	window.location.href = "/monitorManageAction_initAddMonitor.action?page="+page;
	    }
	});
	
	$("#kinId").change(function(){
		if($(this).val()==-1){
			
			$(this).css("width","105");
			$("#kinName").show();
			
			$("#areaId").children().remove();
			$("#areaId").append('<option value="-1">新增</option>');
			$("#areaId").change();
		}else{
			
			$(this).css("width","220");
			$("#kinName").hide();
			
			getarea($(this).val());
		}
	});
	
	$("#areaId").change(function(){
		if($(this).val()==-1){
			$(this).css("width","105");
			$("#areaName").show();
			
			$("#careaId").children().remove();
			$("#careaId").append('<option value="-1">新增</option>');
			$("#careaId").change();
		}else{
			$(this).css("width","220");
			$("#areaName").hide();
			
			getcarea($(this).val());
		}
	});
	
	$("#careaId").change(function(){
		if($(this).val()==-1){
			$(this).css("width","105");
			$("#careaName").show();
		}else{
			$(this).css("width","220");
			$("#careaName").hide();
		}
	});
	

	
	$("#kinId").change();
	
	$("#btn_createmonitor").click(function(){
		if($("#monitorname").val()==""){
			alert("设备名称不能为空。");
			return;
		}
		
		if($("#sn").val()==""){
			alert("sn号不能为空");
			return;
		}

		if($("#kinId").val()=="-1"){
			if($("#kinName").val()==""){
				alert("幼儿园名字不能为空。");
				return;
			}
		}
		if($("#areaId").val()=="-1"){
			if($("#areaName").val()==""){
				alert("区域名不能为空。");
				return;
			}
		}
		if($("#careaId").val()=="-1"){
			if($("#careaName").val()==""){
				alert("分区名不能为空。");
				return;
			}
		}
		
		
		$.ajax({
			type : "POST",
			async:false,
			url : "${ctx}/monitorManageAction_addMonitor.action?time="+(new Date()).valueOf(),
			data: $("#form_monitor").serialize(),
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
		
	$('#begintime').val("09:00");
	$('#endtime').val("17:00");
	
	$('#begintime').mobiscroll().time({
        theme: "",     // Specify theme like: theme: 'ios' or omit setting to use default 
        mode: "scroller",       // Specify scroller mode like: mode: 'mixed' or omit setting to use default 
        display: "bubble", // Specify display mode like: display: 'bottom' or omit setting to use default 
        lang: "zh"        // Specify language like: lang: 'pl' or omit setting to use default 
    });
	$('#endtime').mobiscroll().time({
        theme: "",     // Specify theme like: theme: 'ios' or omit setting to use default 
        mode: "scroller",       // Specify scroller mode like: mode: 'mixed' or omit setting to use default 
        display: "bubble", // Specify display mode like: display: 'bottom' or omit setting to use default 
        lang: "zh"        // Specify language like: lang: 'pl' or omit setting to use default 
    });
	
});