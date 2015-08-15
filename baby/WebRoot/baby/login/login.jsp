<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/pages/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登陆</title>
    
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="/data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="/files/login/styles.css" type="text/css" rel="stylesheet"/>
    <script src="/baby/login/js/login.js"></script>    
    <!-- <script src="/files/login/data.js"></script> -->
    <style type="text/css">
    	#u4{cursor: pointer;}
    </style>
    <script type="text/javascript">
    	var offline = '<%=request.getParameter("offline") %>';
    </script>
  </head>
  <body style="margin:0px auto; width: 795px; position: absolute;">
    <div class="">
    	<div style="margin-left: 150px;margin-top: 0px;">
    	<div style="width:795px;">
    	<span style="color: rgb(60,95,156); font-weight: 800;font-size: 35px; font-family: '幼圆';">看宝宝视频监控系统</span>
    	</div>
    	<div style="width:795px;">
    	<span style=" margin-left:300px; color: rgb(60,95,156); font-weight: 400;font-size: 25px; font-family: '幼圆';">--时刻守护您的宝贝</span>
    	</div>
		</div>
      <!-- Unnamed (Image) -->
      <div id="u0" class="ax_image" style="width: 795px;height: 451px;">
        <img id="u0_img" class="img " src="/images/login/u1.png" style="width: 795px;height: 451px;margin-top: -20px;"/>
        <!-- Unnamed () -->
        <div id="u1" class="text">
          <p><span></span></p>
        </div>
      </div>
      
	<form id="form_login">
		<input type="hidden" id="txt_mac" name="In.mac">
      <!-- userNameTextfiled (文本框) -->
      <div id="u2" class="ax_文本框" data-label="userNameTextfiled">
      	<img alt="" src="/images/login/user.png" style="position: absolute; left: 2px; z-index: 1;height: 35px; width: 35px;" />
        <input id="u2_input" name="In.users.username" type="text" value="" placeholder="用户名"/>
      </div>

      <!-- password (文本框) -->
      <div id="u3" class="ax_文本框" data-label="password">
      	<img alt="" src="/images/login/key.png" style="position: absolute; left: 2px; z-index: 1;height: 35px; width: 35px;" />
        <input id="u3_input" name="In.users.password" type="password" value="" placeholder="密码"/>
      </div>
      <div id="u66" class="ax_文本框" data-label="password" style="display: none;">
      	<input id="u66_input" name="In.phoneCode" type="text" value="" maxlength="6" placeholder="手机验证码"/>
      	<input id="btn_sendcode" disabled="disabled" class="reSendCode" type="button" value="120秒后可再发送"/>
      </div>
      <!-- <div id="u66" class="ax_文本框" data-label="password">
        <input id="u66_input" name="In.phoneCode" type="text" value="" maxlength="4" placeholder="手机验证码"/>
      </div> -->
	</form>
      <!-- LoginButton (形状) -->
      <div id="u4" class="ax_形状" data-label="LoginButton">
        <!-- <img id="u4_img" class="img " src="images/login/loginbutton_u4.png"/> -->
        <!-- Unnamed () -->
        <div id="u5" class="text">
          <p><span>登 录</span></p>
        </div>
      </div>

      <!-- Unnamed (复选框) -->
      <div id="u6" class="ax_复选框">
        <label for="u6_input">
          <!-- Unnamed () -->
          <div id="u7" class="text">
            <p><span>记住密码</span></p>
          </div>
        </label>
        <input id="u6_input" type="checkbox" value="checkbox"/>
      </div>

      <!-- Unnamed (形状) -->
      <div id="u8" class="ax_形状" style="cursor: pointer;" onclick="window.location.href='/baby/forgotPasswd/forgotPasswd.jsp';">
        <img id="u8_img" class="img " src="resources/images/transparent.gif"/>
        <!-- Unnamed () -->
        <div id="u9" class="text">
          <p><span style="text-decoration:underline;">密码找回</span></p>
        </div>
      </div>

      <!-- noUserName (动态面板) -->
      <div id="u10" class="ax_动态面板" data-label="noUserName">
        <div id="u10_state0" class="panel_state" data-label="State1">
          <div id="u10_state0_content" class="panel_state_content">

            <!-- Unnamed (形状) -->
            <div id="u11" class="ax_形状">
              <img id="u11_img" class="img " src="images/login/u11.png"/>
              <!-- Unnamed () -->
              <div id="u12" class="text">
                <p><span id="err_info">用户名和密码不能为空</span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u13" class="ax_形状" title="注意">
              <img id="u13_img" class="img " src="images/login/u13.png"/>
              <!-- Unnamed () -->
              <div id="u14" class="text">
                <p><span>提示</span></p>
              </div>
            </div>

            <!-- Unnamed (形状) -->
            <div id="u15" class="ax_形状">
              <img id="u15_img" class="img " src="images/login/u15.png"/>
              <!-- Unnamed () -->
              <div id="u16" class="text">
                <p><span>确定</span></p>
              </div>
            </div>
          </div>
        </div>
      </div>
	
	<div style="width: 795px; height: 50px;top:505px;position: absolute;">
		<div style="margin-left: 400px; width: 795px;"><img style="width: 50px;height: 50px;" alt="" src="/images/login/logo.png"> </div>
		<div style="width: 795px;margin-top:10px; "><span style="margin-left: 330px;font-size: 15px; color: rgb(60,95,156);">版权归@伊尔根有限公司所有</span></div>
		<div style="width: 795px;margin-top:5px; "><span style="margin-left: 200px;font-size: 15px; color: rgb(60,95,156);">蜀ICP备14025524号-2&nbsp;客服电话：0838-6669110&nbsp;QQ：1907216670</span></div>
	</div>
	
    </div>
  </body>
</html>
