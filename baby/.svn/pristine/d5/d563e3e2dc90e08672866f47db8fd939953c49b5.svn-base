<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="/pages/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>实时</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="/baby/admin/css/zTreeStyle.css">
	<script type="text/javascript" src="/baby/admin/js/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="/baby/admin/js/Play.js"></script>
	
	<script type="text/javascript" src="/baby/admin/js/select.js"></script>
	<script type="text/javascript" src="/baby/admin/js/actualTime.js"></script>
	
	<style type="text/css">
		.sp{margin-left:179px;width:18px;overflow:hidden; }
		.bo4{line-height: normal;line-height: 27px\9; position:absolute; width: 110px; left:80px; height: 27px; margin-top:2px; border:0px; font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 13px;text-decoration: none;color: #000000;}
		.autocomplete{background:#fff; position:absolute;width: 138px;z-index:9999;left:77px;  margin:0px; padding:0px; border:#008080 1px solid; }
		.autocomplete li{display:block; z-index:9999; font-size:12px; font-family:"Lucida Console", Monaco, monospace; font-weight:bold; cursor:pointer; height:20px; line-height:20px}
		.hovers{ background-color:#3368c4; color:fff}
		#VideoPlayer{z-index: 1;}
	</style>
  </head>
  
  <body>
  <div class="div_main">
  <%@include file="/pages/mHead.jsp" %>
    <div style="position: absolute;top: 78px; width: 900px;">
    	<div style="margin-left: 0px; width:355px; margin-top: 0px;line-height:30px;height: 30px;font-size: 15px;">
    		条件筛选
    		<select id="select_kin" style="width: 140px;height: 30px;font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 13px;text-decoration: none;color: #000000;margin-left: 10px;">
    		
    			<c:forEach items="${kinders }" var="kin">
    				<option value="${kin.id }">${kin.name }</option> 
    			</c:forEach>
    			
    		</select>
    		<input name="box4" id="box4" class="bo4" > 
    		<!-- <div style="background: #000000; width: 300px; height: 300px;position: absolute; z-index: 1000000000;" ></div> -->
    		<!-- <div class="ax_形状" style="cursor: pointer;position: relative; float: right; margin-top: 3px;">
              <img id="u148_img" class="img " src="/images/getp/u9.png" tabindex="0">
              Unnamed ()
              <div id="u149" class="text" style="top: 8px; -webkit-transform-origin: 48px 7px 0px; transform-origin: 48px 7px 0px;color: #ffffff;">
                <p><span>确</span><span>认检索</span></p>
              </div>
            </div> -->
    	</div>
    	
    	<div style="width: 900px;height: 500px;margin-top: 10px;">
    		<!-- <div style="width: 800px;height: 15px;">
    			<a href="javascript:vold();" style="float: right;font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 13px;color: #333333;">返回</a>
    		</div> -->
    		
    		<table style=" border-spacing: 0px;margin-top: 3px;" border="1" bordercolor="#cccccc">
    			<tr>
    			<td>
    			
    			<div style="width: 200px;height: 500px;overflow:auto;">
    				<ul	id="treeMonitor" class="ztree">
    				</ul>
    			</div>
    			</td>
    			<td>
    				<div style="width: 700px;height: 500px; z-index: 0;" id="video">
    					<!-- <script type="text/javascript"  src="http://seeyun.cn/API/player2?sn=B4B6115100950&w=900&h=900&scr=1,1"></script> -->
    				</div>
    			</td>
    			<!-- <td>
    				<div style="width: 320px;height: 261px;" id="camera2">
    					<script type="text/javascript"  src="http://seeyun.cn/API/player2?sn=4D41215105062&w=320&h=261&scr=1,1"></script>
    				</div>
				</td> -->
    			</tr>
    			<!-- <tr>
    				<td>
					<div style="width: 320px;height: 261px;">
    				</div>
					</td>
    				<td>
    				<div style="width: 320px;height: 261px;">
    				</div>
    				</td>
    			</tr> -->
    		</table>
    	</div>
    </div>
    
    </div>
	<script type="text/javascript">
			icon(1);
	</script>
  </body>
</html>
