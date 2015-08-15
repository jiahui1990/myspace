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
	/*$("#div_update").css("left", (document.body.clientWidth-338)/2);
	$(window).resize(function(){
		$("#div_update").css("left", (document.body.clientWidth-338)/2);
	});*/
	
	$("#export").click(function(){
		var url = "/userManageAction_exportUser.action?search="+encodeURI(encodeURI($("#hid_search").val()));
		if(curpage!=undefined&&curpage!=null){
			url += "&page="+curpage;
		}
		
		window.open(url);
	});
	$('.pagination').jqPagination({
		current_page:curpage,
		page_string:'当前 {current_page} / {max_page}' ,
	    paged: function(page) {
	    	window.location.href = "/userManageAction_initUserManage.action?search="+encodeURI(encodeURI($("#hid_search").val()))+"&page="+page;
	    }
	});
	
	$("#btn_search").click(function(){
		window.location.href = "/userManageAction_initUserManage.action?search="+encodeURI(encodeURI($("#search").val()));
	});
	
	$(".delete").click(function(){
		var tr = $(this).parent().parent("tr");
		
		var delete_username = tr.children("td").html();
		var delete_userId = tr.attr("data-id");
		
		if(confirm("您是否确定删除用户："+delete_username)){
			deleteUser(delete_userId);
		}
	});
	
	$(".update").click(function(){
		/*$("#tr_kin").hide();
		$("#tr_area").show();*/
		
		var tr = $(this).parent().parent("tr");
		
		$("#userId").val(tr.attr("data-id"));
		$("#username").val(tr.attr("data-username"));
		$("#roleName").html(tr.attr("data-roleName"));
		$("#roleId").val(tr.attr("data-roleId"));
		$("#kinderId").val(tr.attr("data-kinId"));
		$("#pkaId").val(tr.attr("data-pkaId"));
		$("#kaId").val(tr.attr("data-kaId"));
		$("#starttime").val(tr.attr("data-starttime"));
		$("#endtime").val(tr.attr("data-endtime"));
		
		showupdate();
	});
	
	$("#btn_cancel").click(function(){
		hideupdate();
	});
	
	$("#btn_commit").click(function(){
		updateUser();
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
	
	switch ($("#roleId").val()) {
	case "1":
		$("#tr_ex").nextAll().hide();
		break;
	case "2":
		$("#tr_ex").nextAll().show();
		$("#tr_kin").nextAll().hide();
		break;
	case "3":
		$("#tr_ex").nextAll().show();
	break;
	
	default:
		break;
	}
	
	$("#tr_btn").show();
	
	if($("#kinderId").val()!=""){
		$("#kinId").val($("#kinderId").val());
	}
	$("#kinId").change();
	
	
	
	$("#update_bg").show();
	$("#div_update").show();
	$("#asd").show();

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
}

function updateUser(){
	if($("#password").val()!=$("#password_confirm").val()){
		alert("两次输入密码不一致！");
		return;
	}
	
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/userManageAction_updateUser.action?time="+(new Date()).valueOf(),
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

function deleteUser(userId){
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/userManageAction_deleteUser.action?time="+(new Date()).valueOf(),
		data:{"In.users.id":userId},
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