$(function(){
	$('.pagination').jqPagination({
		current_page:curpage,
		page_string:'当前 {current_page} / {max_page}' ,
	    paged: function(page) {
	    	window.location.href = "/userManageAction_usersInfo.action?search="+encodeURI(encodeURI($("#hid_search").val()))+"&page="+page;
	    }
	});
	
	$("#btn_search").click(function(){
		window.location.href = "/userManageAction_usersInfo.action?search="+encodeURI(encodeURI($("#search").val()));
	});
});