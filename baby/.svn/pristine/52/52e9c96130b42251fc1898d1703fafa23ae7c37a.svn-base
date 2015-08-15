/*var nowSns = {0:"",1:"",2:"",3:""};*/
var klastNum = 3;
var ksnArr = new Array();
$(function(){
	getMonitors(kinId);
	/*$("#select_kin").change(function(){
		getMonitors($(this).val());		

	});
	
	$("#select_kin").change();*/
	
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
 
}
function onClick(event, treeId, treeNode, clickFlag) {
	 if(treeNode.sn!=undefined){
		  var sn = treeNode.id;
		  
		  for (var int = 0; int < ksnArr.length; int++) {
			if(ksnArr[int]==sn){
				return;
			}
		  }
		  
		  klastNum = (klastNum+1)%4;
		  
		  ksnArr[klastNum] = sn;
		  
		  /*PlayUrl("http://seeyun.cn/API/player2", treeNode.name, klastNum+1, "sn="+treeNode.sn+"&w=320&h=261&scr=1,1");*/
		  PlayUrl("huamai://125.71.21.68:8100/?loginkey=", treeNode.name, klastNum+1,"sn="+treeNode.sn+"&actor=48126&usealarm=0&usetransfer=1024&natip1=&natport1=0&natip2=null&natport2=0&transferip=&transferport=0&nvsip=118.122.165.155&nvsport=10081");
		  
		  
		  /*alert(treeNode.sn);*/
	  }
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