/*var nowSns = {0:"",1:"",2:"",3:""};*/
var lastNum = 3;
var snArr = new Array();
$(function(){
	$("#select_kin").change(function(){
		$("#box4").val($(this).find("option:selected").text());
		
		getMonitors($(this).val());

	});
	
	$("#select_kin").change();
	
	/*PlayUrl("http://seeyun.cn/API/player2", name, 1, "sn=B4B6115100950&w=320&h=261&scr=1,1");
	PlayUrl("http://seeyun.cn/API/player2", name, 2, "sn=4D41215105062&w=320&h=261&scr=1,1");*/
	/*$("#camera2").html('<script type="text/javascript"  src="http://seeyun.cn/API/player2?sn=4D41215105062&w=320&h=261&scr=1,1"></script>');*/
});
var setting = {
  view: {
	  showIcon:false
  },
  callback: {
    beforeClick: beforeClick,
    onClick: onClick
  }
};
function beforeClick(treeId, treeNode, clickFlag) {
  if(treeNode.sn!=undefined){
	  var sn = treeNode.id;
	  var exists = false;
	  $.each(snArr,function(n,value) {
		 if(value==sn){
			 exists = true;
			 return false;
		 }
	  });
	  
	  if(exists){
		  return;
	  }
	  
	  lastNum = (lastNum+1)%4;
	  
	  snArr[lastNum] = sn;
	  
	  /*PlayUrl("http://seeyun.cn/API/player2", treeNode.name, lastNum+1, "sn="+treeNode.sn+"&w=320&h=261&scr=1,1");*/
	  PlayUrl("huamai://125.71.21.68:8100/?loginkey=", treeNode.name, lastNum+1,"sn="+treeNode.sn+"&actor=48126&usealarm=0&usetransfer=1024&natip1=&natport1=0&natip2=null&natport2=0&transferip=&transferport=0&nvsip=118.122.165.155&nvsport=10081");
	  
	  console.info(lastNum);
	  
	  /*alert(treeNode.sn);*/
  }
}
function onClick(event, treeId, treeNode, clickFlag) {
  //alert(clickFlag+"zzz");
}
function getMonitors(kinId){
	if(kinId==null||kinId<0){
		return;
	}
	
	$.ajax({
		type : "POST",
		async:false,
		url : "${ctx}/userMonitorAction_getMonitor.action?time="+(new Date()).valueOf(),
		data:{"In.kindergarten.id":kinId},
		dataType : 'json',
		success : function(data) {
			 $.fn.zTree.init($("#treeMonitor"), setting, data);
		}
	});
}