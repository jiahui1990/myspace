<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<link href="/resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
<link href="/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
<link href="/data/styles.css" type="text/css" rel="stylesheet"/>
<link href="/files/manage/styles.css" type="text/css" rel="stylesheet"/>
<script src="/pages/js/nHead.js"></script>
<style type="text/css">
	.ax_形状{cursor: pointer;}
</style>
<style>

.div_main{width:900px; margin: 0 auto;position: absolute;}
</style>
<script type="text/javascript">
$(function(){
	$(".div_main").css("left", (document.documentElement.clientWidth-900)/2);
	$(window).resize(function(){
		$(".div_main").css("left", (document.documentElement.clientWidth-900)/2);
	});
});
</script>
<div>

	<div style="position: absolute;left: 20px;top: 45px;font-size: 20px;">
		<c:if test="${area_info!=null }">
			${area_info }
		</c:if>
	</div>
	 <!-- 管理员 (动态面板) -->
      <div id="u0" class="ax_动态面板" data-label="管理员">
        <div id="u0_state0" class="panel_state" data-label="实时">
          <div id="u0_state0_content" class="panel_state_content">

            <!-- Unnamed (形状) -->
            <div id="u1" class="ax_形状">
              <img id="u1_img" class="img " src="/images/manage/u1.png"/>
              <!-- Unnamed () -->
              <div id="u2" class="text">
                <p><span></span></p>
              </div>
            </div>

            <!-- 实时-实时 (形状) -->
            <div style="  position: absolute;left: 559px; width: 80px;" id="u3" class="ax_形状 mhead" data-label="实时-实时"  onclick="turn('/userMonitorAction_userMonitor.action');">
              <img style="width: 80px;" id="u3_img" class="img " src="/images/manage/u3.png"/>
              <!-- Unnamed () -->
              <div style="width: 80px;" id="u4" class="text">
                <p><span>视频中心</span></p>
              </div>
            </div>

            <!-- 实时-实时-水平线 (水平线) -->
            <div id="u5" class="ax_水平线" data-label="实时-实时-水平线" style="display: none;">
              <img id="u5_start" class="img " src="/resources/images/transparent.gif" alt="u5_start"/>
              <img id="u5_end" class="img " src="/resources/images/transparent.gif" alt="u5_end"/>
              <img id="u5_line" class="img " src="/images/manage/u5_line.png" alt="u5_line"/>
            </div>

            <!-- 实时-用户管理 (形状) -->
            <div id="u6" style="position: absolute;left: 644px; width: 80px;"" class="ax_形状 mhead" data-label="实时-用户管理" onclick="turn('personalCenterAction_initPersonalCenter.action');">
              <img id="u6_img" class="img " src="/resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u7" class="text">
                <p><span>个人中心</span></p>
              </div>
            </div>

            <!-- 实时-用户管理-水平线 (水平线) -->
            <div id="u8" class="ax_水平线" data-label="实时-用户管理-水平线" style="display: none;">
              <img id="u8_start" class="img " src="/resources/images/transparent.gif" alt="u8_start"/>
              <img id="u8_end" class="img " src="/resources/images/transparent.gif" alt="u8_end"/>
              <img id="u8_line" class="img " src="/images/manage/u8_line.png" alt="u8_line"/>
            </div>

            <!-- 实时-设备管理 (形状) -->
            <div id="u9" style="position: absolute;left: 729px; width: 80px;"  class="ax_形状 mhead" data-label="实时-设备管理"  onclick="turn('loginAction_loginoff.action');">
              <img id="u9_img" class="img " src="/resources/images/transparent.gif"/>
              <!-- Unnamed () -->
              <div id="u10" class="text">
                <p><span>退出登陆</span></p>
              </div>
            </div>
            </div></div></div>
</div>