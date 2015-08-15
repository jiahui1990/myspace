<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="/pages/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="/jqPagination/css/jqpagination.css" />

	<script src="/jqPagination/js/jquery.jqpagination.js"></script>
	
	<script src="/baby/userManage/js/kuserManage.js"></script>
	
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.imgbtn{cursor: pointer;}
		.table{font-size:13px; border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;}
		.table td{border-right:1px solid #888;border-bottom:1px solid #888;padding:5px 15px;}
		
		table input[type=text]{
			width: 220px;
			  height: 29px;
			  line-height: normal; 
			  line-height: 29px\9;
			  /* -ms-line-height:29px; */
			  font-family: 'Arial Normal', 'Arial';
			  font-weight: 400;
			  font-style: normal;
			  font-size: 13px;
			  text-decoration: none;
			  color: #000000;
			  text-align: left;
		}
		table input[type=password]{
			width: 220px;
			  height: 29px;
			  line-height: normal; 
			  line-height: 29px\9;
			  /* line-height:29px; */
			  font-family: 'Arial Normal', 'Arial';
			  font-weight: 400;
			  font-style: normal;
			  font-size: 13px;
			  text-decoration: none;
			  color: #000000;
			  text-align: left;
		}
		select{
			padding: 3px 0;
		}
	</style>
	<script type="text/javascript">
		var curpage = ${page };
		
	</script>
  </head>
  
  <body>
  <div class="div_main">
  <%@include file="/pages/kmHead.jsp" %>
    <div style="position: absolute;top: 88px;width: 900px; margin-left: 0px;">
    	<div>
         
         <div style="position: absolute; left: 550px;width: 350px;">
         	<input id="hid_search" value="${search }" type="hidden" />
         	<input id="search" value="${search }" placeholder="请输入用户名等关键字" style="width: 210px;height:25px; margin-top:10px; background-color: white;border: 2px inset; border-image-source: initial;">
         	<div id="btn_search" class="imgbtn" style="position: absolute; left: 250px;width: 60px; margin-top: -25px;">
	           <img class="img" src="images/manage/u297.png">
	           <!-- Unnamed () -->
	           <div class="text" style="position:absolute; top: 36px;font-size: 13px;">
	             <p><span></span></p>
	           </div>
	         </div>
         </div>
         	
         </div>
         
         <div style="margin-top: 60px;">
	         <table class="table">
	         	<tr height="28px;">
	         		<td width="150px;"  align="center">用户名</td>
	         		<td width="150px;" align="center">幼儿园名</td>
	         		<td width="150px;" align="center">所在班级</td>
	         		<td width="150px;" align="center">注册手机</td>
	         		<td width="150px;" align="center">生效时间</td>
	         		<td width="150px;" align="center">终止时间</td>
	         		<td width="150px;" align="center">权限</td>
	         	</tr>
	         	
	         	<c:forEach items="${viewUserAreas }" var="ua" >
	         		<tr height="28px;">
	         		<td width="150px;"  align="center">${ua.userName }</td>
	         		<td width="150px;" align="center">${ua.kindergartenName }</td>
	         		<td width="150px;" align="center">${ua.areaName }</td>
	         		<td width="150px;" align="center">${ua.telphone }</td>
	         		<td width="150px;" align="center">${fn:substring(ua.operationTime,0,10) }</td>
	         		<td width="150px;" align="center">${fn:substring(ua.expireTime,0,10) }</td>
	         		<td width="150px;" align="center">${ua.roleName }</td>
	         	</tr>
	         	</c:forEach>
	         </table>
	         
	         <div class="pagination">
			    <a href="#" class="first" data-action="first">&laquo;</a>
			    <a href="#" class="previous" data-action="previous">&lsaquo;</a>
			    <input id="pageNum" type="text" readonly="readonly" data-max-page="${pages }" />
			    <a href="#" class="next" data-action="next">&rsaquo;</a>
			    <a href="#" class="last" data-action="last">&raquo;</a>
			</div>
         </div>
    </div>
    
    </div>
    
    <script type="text/javascript">
			icon(2);
	</script>
  </body>
</html>
