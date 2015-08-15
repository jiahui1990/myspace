<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/pages/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>找回密码</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
    <link href="/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="/data/styles.css" type="text/css" rel="stylesheet"/>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script src="/baby/forgotPasswd/js/forgotPasswd.js"></script>
    <script type="text/javascript">
    $(function(){

    	clearInterval(onlineT);
    });
    </script>
    <style type="text/css">
    	.tabhead{text-align:center;line-height:30px; font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 13.2px;width: 120px;height: 30px;border-radius: 3px 3px 0 0;background-image: url('/images/getp/u3.png'); float: left;margin-right: 3px;}
    	.focus{background-image: url('/images/getp/u5.png'); color: #fff;}
    	a{text-decoration:none; font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 13px;color: #333333;text-align: center;cursor: pointer;}
    	a:hover {text-decoration: underline;color: rgb(153,153,153);}
    	.error{text-decoration:none; font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 13px;color: #f00;text-align: center;}
    </style>
  </head>
  
  <body>
    <div class="" style="width: 700px;height:400px; margin: 50px auto;">

     
	    <form action="" id="form_forgotPass">
      		<div style="width: 700px;height: 30px;">
	      		<div class="tabhead focus"><span>验证</span></div>
	      		<div class="tabhead">修改</div>
	      		
	      		<a href="/" style="float: right; font-size: 18px;line-height: 30px;">登陆</a>
    		</div>
          <div style="border: 2px; border-color: rgb(84,193,26); width: 700px;height: 314px;border-style: solid; float: none;">
          	
	        <div id="tab1">
	        	<table style="margin-top: 70px;margin-left: 170px;font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 21px;color: #333333;">
	        		<tr height="60px;">
	        			<td width="100px;">手机号</td>
	        			<td width="230px;"><input name="In.phone" id="telphone" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="11" type="text" style="width: 200px;height: 25px;"/></td>
	        			<td><a id="sendphonecode" href="javascript:void();" style="">点击获取验证码</a></td>
	        		</tr>
	        		<tr height="60px;">
	        			<td width="100px;">验证码</td>
	        			<td width="230px;"><input name="In.phoneCode" onkeyup="this.value=this.value.replace(/\D/g,'')" id="code" maxlength="6" type="text" style="width: 120px;height: 25px;"/> <a id="resendphonecode" href="javascript:void();" style="margin-left: 25px;display: none;">重新发送</a></td>
	        			
	        		</tr>
	        		
	        		<tr>
	        			<td colspan="2" align="center">
	        				<input id="next" type="button"  value="下一步" style="color:#fff;cursor: pointer;width: 100px;height:30px; background-image: url('/images/getp/u9.png');border: 0;border-radius: 3px;margin-top: 20px;margin-left: -20px;"/>
	        			</td>
	        		</tr>
	        		
	        	</table>
	        </div>
	        <div id="tab2"  style="display: none;">
	        	<table style="margin-top: 70px;margin-left: 170px;font-family: 'Arial Normal', 'Arial';font-weight: 400;font-style: normal;font-size: 21px;color: #333333;">
	        		<tr height="60px;">
	        			<td width="100px;">新密码</td>
	        			<td width="230px;"><input name="In.password" id="password" type="password" style="width: 200px;height: 25px;"/></td>
	        			<td><span id="passError" class="error"  style="display: none;"></span></td>
	        		</tr>
	        		<tr height="60px;">
	        			<td width="100px;">确认密码</td>
	        			<td width="230px;"><input id="confirm" type="password" style="width: 200px;height: 25px;"/></td>
	        			<td><span id="confirmError" class="error" style="display: none;"></span></td>
	        		</tr>
	        		
	        		<tr>
	        			<td colspan="2" align="center">
	        				<input id="submit" type="button"  value="提交" style="color:#fff; width: 100px;height:30px; background-image: url('/images/getp/u9.png');border: 0;border-radius: 3px;margin-top: 20px;margin-left: -20px; cursor: pointer;"/>
	        			</td>
	        		</tr>
	        		
	        	</table>
	        </div>
	        
          </div>
	        	</form>
    </div>
  </body>
</html>
