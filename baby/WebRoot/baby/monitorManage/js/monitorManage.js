$(function(){
	$("#allCheck").click(function(){
		if($(this).attr("checked")){
			$(".check_id").attr("checked",'true');
		}else{
			$(".check_id").removeAttr("checked");
		}
	});
	$("#batchDelete").click(function(){
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
	$("#export_excel").click(function(){
		var url = "/monitorManageAction_exportMonitor.action?search="+encodeURI(encodeURI($("#hid_search").val()));
		
		if(curpage!=undefined&&curpage!=null){
			url += "&page="+curpage;
		}
		
		window.open(url);
	});
	
	$('.pagination').jqPagination({
		current_page:curpage,
		page_string:'当前 {current_page} / {max_page}' ,
	    paged: function(page) {
	    	window.location.href = "/monitorManageAction_initMonitorManage.action?search="+encodeURI(encodeURI($("#hid_search").val()))+"&page="+page;
	    }
	});
	
	$("#btn_search").click(function(){
		window.location.href = "/monitorManageAction_initMonitorManage.action?search="+encodeURI(encodeURI($("#search").val()));
	});
	
	$(".delete").click(function(){
		var tr = $(this).parent().parent("tr");
		if(confirm("您确认删除"+tr.children("td").eq(0).html()+"?")){
			deleteMonitor(tr.attr("data-id"));
		}
	});
	
	$(".update").click(function(){
		/*$("#tr_kin").hide();
		$("#tr_area").show();*/
		
		var tr = $(this).parent().parent("tr");
		
		$("#monitorId").val(tr.attr("data-id"));
		$("#monitorname").val(tr.attr("data-monitorname"));
		$("#sn").val(tr.attr("data-sn"));
		$("#kinderId").val(tr.attr("data-kinderId"));
		$("#pkaId").val(tr.attr("data-pkaId"));
		$("#kaId").val(tr.attr("data-kaId"));
		
		var timeQuantum = tr.attr("data-timeQuantum");
		var timeArr = timeQuantum.split("-");
		$('#begintime').val(timeArr[0]);
		$('#endtime').val(timeArr[1]);
		
		showupdate();
	});
	
	$("#btn_cancel").click(function(){
		hideupdate();
	});
	
	$("#btn_commit").click(function(){
		updateMonitor();
	});
	
	$("#kinId").change(function(){			
		getarea($(this).val());
	});
	
	$("#areaId").change(function(){			
		getcarea($(this).val());
	});
	
});

function hideupdate(){
	$("#update_bg").hide();
	$("#div_update").hide();
}

function showupdate(){	
	if($("#kinderId").val()!=""){
		$("#kinId").val($("#kinderId").val());
	}
	$("#kinId").change();
	
	
	
	$("#update_bg").show();
	$("#div_update").show();
	

	
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
}

function updateMonitor(){
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/monitorManageAction_updateMonitor.action?time="+(new Date()).valueOf(),
		data:$("#form_update").serialize(),
		dataType : 'json',
		success : function(data) {
			alert(data.info);
			if(data.isSuccess=="MDGV1"){
				window.location.reload();
			}
		}
	});
}

function deleteMonitor(monId){
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/monitorManageAction_deleteMonitor.action?time="+(new Date()).valueOf(),
		data:{"In.monitor.id":monId},
		dataType : 'json',
		success : function(data) {
			alert(data.info);
			if(data.isSuccess=="MDGV1"){
				window.location.reload();
			}
		}
	});
}

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
			
			if($("#pkaId").val()!=""){
				$("#areaId").val($("#pkaId").val());
			}
			
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
			if($("#kaId").val()!=""){
				$("#careaId").val($("#kaId").val());
			}
			
			$("#careaId").change();
		}
	});
}