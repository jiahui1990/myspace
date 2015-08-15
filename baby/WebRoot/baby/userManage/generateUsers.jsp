<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@include file="/pages/taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>添加用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script src="/mobiscroll/js/mobiscroll.core.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.js"></script>
    <script src="/mobiscroll/js/mobiscroll.scroller.js"></script>
    <script src="/mobiscroll/js/mobiscroll.util.datetime.js"></script>
    <script src="/mobiscroll/js/mobiscroll.datetimebase.js"></script>
    <script src="/mobiscroll/js/mobiscroll.datetime.js"></script>
    <script src="/mobiscroll/js/mobiscroll.select.js"></script>
    <script src="/mobiscroll/js/mobiscroll.listbase.js"></script>
    <script src="/mobiscroll/js/mobiscroll.image.js"></script>
    <script src="/mobiscroll/js/mobiscroll.treelist.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.android.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.android-holo.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.ios-classic.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.ios.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.jqm.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.sense-ui.js"></script>
    <script src="/mobiscroll/js/mobiscroll.frame.wp.js"></script>
    <script src="/mobiscroll/js/mobiscroll.android-holo-light.js"></script>
    <script src="/mobiscroll/js/mobiscroll.wp-light.js"></script>
    <script src="/mobiscroll/js/mobiscroll.mobiscroll-dark.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.cs.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.de.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.en-UK.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.es.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.fa.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.fr.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.hu.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.it.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.ja.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.nl.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.no.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.pl.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.pt-BR.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.pt-PT.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.ro.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.ru.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.ru-UA.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.sk.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.sv.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.tr.js"></script>
    <script src="/mobiscroll/js/i18n/mobiscroll.i18n.zh.js"></script>
    <link href="/mobiscroll/css/mobiscroll.animation.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.icons.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.android.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.android-holo.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.ios-classic.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.ios.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.jqm.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.sense-ui.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.frame.wp.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.android.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.android-holo.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.ios-classic.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.ios.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.jqm.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.sense-ui.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.scroller.wp.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.image.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.android-holo-light.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.wp-light.css" rel="stylesheet" type="text/css" />
    <link href="/mobiscroll/css/mobiscroll.mobiscroll-dark.css" rel="stylesheet" type="text/css" />

	<link rel="stylesheet" href="/jqPagination/css/jqpagination.css" />

	<script src="/jqPagination/js/jquery.jqpagination.js"></script>
	<script type="text/javascript">
		var curpage = ${page };
		var pages = ${pages };
	</script>
	
	<style type="text/css">
		table {table-layout:fixed;}
		td{word-wrap:break-word;}
		.table{font-size:13px; border-collapse:collapse;border-spacing:0;border-left:1px solid #888;border-top:1px solid #888;}
		.table td{border-right:1px solid #888;border-bottom:1px solid #888;overflow:hidden;text-overflow:ellipsis;}
		
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
		select{
			padding: 3px 0;
		}
		
	</style>

	<script type="text/javascript" src="/baby/userManage/js/generateUsers.js"></script>
  </head>
  
  <body>
  <div class="div_main">
     <%@include file="/pages/mHead.jsp" %>
    <div style="position: absolute;top: 83px;left:0px;width: 900px;height: 450px;border-radius:5px;background: rgb(242,242,242);">
    	<div style="position: absolute; left: 5px; top: 20px; width: 344px; height: 414px; background-image: url('/images/manage/u381.png')">
    		<div style="  position: absolute;left: 0px;top: 13px;width: 344px;height: 30px; background-image: url('/images/manage/u383.png')">
    			
    		</div>
    		<div id="btn_generateUsers" style="position: absolute;left: 240px;top: 18px;cursor: pointer;" class="ax_形状" >
               <img id="u1277_img" class="img " src="images/manage/u554.png" tabindex="0">
               <!-- Unnamed () -->
               <div id="u1278" class="text" style="top: 3px; -webkit-transform-origin: 37px 7.5px 0px; transform-origin: 37px 7.5px 0px;">
                 <p><span>确认生成</span></p>
               </div>
             </div>
             
             <div id="div_table" style="  position: absolute; top: 45px;left: 5px;">
             	<form id="form_users">
             	<table style="border-spacing:8px 15px; text-align: center;font-size: 18px;font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">
             		<tr>
             			<td width="81px;" style="font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">权限</td>
             			<td width="220px;">
             				<select id="role" name="In.role.id" style="width: 220px;height: 29px;text-align: center;">
             					<option value="3">普通用户</option>
             					<option value="2">幼儿园管理员</option>
             					<option value="1">管理员</option>
             				</select>
             			<!-- <input name="In.monitor.sn" style="width: 220px;height: 29px;text-align: center;"/> --></td>
             		</tr>             		
             		<tr>
             			<td width="81px;" style="font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">用户人数</td>
             			<td width="220px;"><input id="userCount" onkeyup="this.value=this.value.replace(/\D/g,'')" name="In.userCount" style="width: 220px;height: 29px;text-align: center;" /></td>
             		</tr>
             		<tr >
             			<td width="81px;" style="font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">生效时间</td>
             			<td width="220px;"><input id="starttime" name="In.operationTime" style="width: 220px;height: 29px;text-align: center;"/></td>
             		</tr>
             		<tr id="tr_ex">
             			<td width="81px;" style="font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">过期时间</td>
             			<td width="220px;"><input id="endtime" name="In.expireTime" style="width: 220px;height: 29px;text-align: center;"/></td>
             		</tr>
             		
             		<tr id="tr_kin">
             			<td width="90px;" style="font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">幼儿园选择</td>
             			<td width="220px;"><!-- <input style="width: 220px;height: 29px;text-align: center;"/> -->
             				<select id="kinId" name="In.kindergarten.id" style="width: 220px;height: 29px;text-align: center;">
             					<c:forEach items="${kinders}" var="kin">
             						<option value="${kin.id}">${kin.name }</option>
             					</c:forEach>
             				</select>
             				<!-- <input id="kinName" style=" display:none; width: 105px;height: 29px;text-align: center;" name="In.kindergarten.name"/> -->
             			</td>
             		</tr>
             		<tr>
             			<td width="90px;" style="font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">区域选择</td>
             			<td width="220px;">
             				<select id="areaId" style="width: 220px;height: 29px;text-align: center;">
             					
             				</select>
             				<!-- <input id="areaName" style=" display:none; width: 105px;height: 29px;text-align: center;" name="In.area.name"/> -->
             			
             			<!-- <input style="width: 220px;height: 29px;text-align: center;"/> --></td>
             		</tr>
             		<tr>
             			<td width="90px;" style="font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;">分区名</td>
             			<td width="220px;">
             				<select id="careaId" style="width: 220px;height: 29px;text-align: center;" name="In.area.id">
             					
             				</select>
             				<!-- <input id="careaName" style=" display:none; width: 105px;height: 29px;text-align: center;" name="In.carea.name"/> -->
             			<!-- <input name="In.cAreaName" style="width: 220px;height: 29px;text-align: center;"/> --></td>
             		</tr>
             		
             	</table>
             	</form>
             </div>
             
             <div style="position: absolute;left:349px; width: 540px;height: 414px;border-radius:5px;background-image: url('/images/manage/u379.png')">
             	<div style="position: absolute;left:0px;top: 13px; height: 30px; width:540px; background-image: url('/images/manage/u567.png');">                   
                 </div>
                 
                 <div style="position: absolute;left: 20px;top: 18px;cursor: pointer;" class="ax_形状">
                    <img id="u569_img" class="img " src="/images/manage/u569.png">
                    <!-- Unnamed () -->
                    <div id="u570" class="text" style="top: 3px; -webkit-transform-origin: 37px 7.5px 0px; transform-origin: 37px 7.5px 0px;">
                      <p><span>清除选中项</span></p>
                    </div>
                  </div>
                  <div style="position: absolute;left: 115px;top: 18px;cursor: pointer;" style="cursor: pointer;">
                    <img id="u571_img" class="img " src="/images/manage/u554.png" tabindex="0">
                    <!-- Unnamed () -->
                    <div id="u572" class="text" style="top: 3px;width:78px;text-align:center; font-size:13.2px; -webkit-transform-origin: 37px 7.5px 0px; transform-origin: 37px 7.5px 0px;">
                      <p><span>保</span><span>存</span></p>
                    </div>
                  </div>
                  <div style="position: absolute;left: 210px;top: 18px;cursor: pointer;"  class="ax_形状" style="cursor: pointer;">
                    <img id="u573_img" class="img " src="/images/manage/u554.png" tabindex="0">
                    <!-- Unnamed () -->
                    <div id="u574" class="text" style="top: 3px;width:78px;text-align:center; font-size:13.2px; -webkit-transform-origin: 37px 7.5px 0px; transform-origin: 37px 7.5px 0px;">
                      <p><span>退</span><span>出</span></p>
                    </div>
                  </div>
                  <div style="width: 530px;position: absolute;top:52px; margin-left: 5px; ">
                  	<table class="table" width="530px;">
			         	<tr height="28px;">
			         		<td width="15px"><input type="checkbox" id="allCheck"/></td>
			         		<td width="75px" nowrap="nowrap" align="center">用户名</td>
			         		<td width="75px" nowrap="nowrap" align="center">幼儿园名</td>
			         		<td width="75px" nowrap="nowrap" align="center">所在班级</td>
			         		<td width="75px" nowrap="nowrap" align="center">注册手机</td>
			         		<!-- <td width="140px;" align="center">生效时间</td>
			         		<td width="140px;" align="center">终止时间</td> -->
			         		<td width="75px" nowrap="nowrap" align="center">权限</td>
			         	</tr>
			         	
			         	<c:forEach items="${viewUserAreas }" var="ua" >
			         		<tr height="28px;">
			         		<td width="15px"><input type="checkbox" class="check_id" value="${ua.userId }" /></td>
			         		<td width="75px" nowrap="nowrap"  align="center">${ua.userName }</td>
			         		<td width="75px" nowrap="nowrap" align="center">${ua.kindergartenName }</td>
			         		<td width="75px" nowrap="nowrap" align="center">${ua.areaName }</td>
			         		<td width="75px" nowrap="nowrap" align="center">${ua.telphone }</td>
			         		<%-- <td width="140px;" align="center">${fn:substring(ua.operationTime,0,10) }</td>
			         		<td width="140px;" align="center">${fn:substring(ua.expireTime,0,10) }</td> --%>
			         		<td width="75px" nowrap="nowrap" align="center">${ua.roleName }</td>
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
    </div>
    
    </div>
    
    <script type="text/javascript">
			icon(2);
	</script>
  </body>
</html>
