<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="/pages/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>个人中心</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="/baby/personalCenter/js/nCenter.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		.imgbtn{cursor: pointer;}
		input[type=text]{
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
		input[type=password]{
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
		.title{font-size: 20px;}
	</style>
	
  </head>
  
  <body>
  
  <div class="div_main">
  <%@include file="/pages/nHead.jsp" %>
    <div style="position: absolute;top: 100px;;left:270px; width: 422px;">
    	<div class="div_right">
	    	<form id="form_user">
	    	<table style="border-spacing:10px 20px; text-align: left;font-size: 18px;font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">
	    		<tr>
	    			<td width="130px;"><span class="title">昵称</span></td>
	    			<td><input value="${user.nickName }" type="text" name="In.users.nickName"></td>
    			</tr>
	    		<tr>
	    			<td width="130px;"><span class="title">密码</span></td>
	    			<td><input id="password" type="password" name="In.users.password"></td>
	    		</tr>
	    		<tr>
	    			<td width="130px;"><span class="title">确认密码</span></td>
	    			<td><input id="confirm" type="password"></td>
	    		</tr>
	    		<tr>
	    			<td width="130px;"><span class="title">手机绑定</span></td>
	    			<td><input id="telphone" value="${user.telphone }" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="11" type="text" name="In.users.telphone"></td>
	    		</tr>
	    		<%-- <tr>
	    			<td width="130px;"><span class="title">地址</span></td>
	    			<td><input value="${user.address }" type="text" name="In.users.address"></td>
	    		</tr> --%>
	    		<tr>
	    			<td colspan="2" align="center" width="400px;">
	    				<div id="btn_commit" class="ax_形状" style="margin-left:100px; margin-top:40px; float:left; width:100px;height:30px; cursor: pointer;background-image: url('/images/getp/u9.png')">
			              
			              <!-- Unnamed () -->
			              <div class="text" style="position:relative; top: 8px; color:#ffffff; -webkit-transform-origin: 48px 7px 0px; transform-origin: 48px 7px 0px;">
			                <p><span>提交</span></p>
			              </div>
			            </div>
			            
			            <div id="btn_cancel" class="ax_形状" style="margin-left:40px; margin-top:40px; float:left;width:100px;height:30px; cursor: pointer;background-image: url('/images/getp/u9.png')">
			              
			              <!-- Unnamed () -->
			              <div class="text" style="position:relative; top: 8px; color:#ffffff; -webkit-transform-origin: 48px 7px 0px; transform-origin: 48px 7px 0px;">
			                <p><span>退出</span></p>
			              </div>
			            </div>
	    			</td>
	    			
	    		</tr>
	    	</table>
	    	
	    	</form>
    	</div>
    </div>
    </div>
    <script type="text/javascript">
			icon(2);
	</script>
  </body>
</html>
