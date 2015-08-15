<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="/css/button.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script src="/pages/js/tag.js"></script>
<meta name="renderer" content="webkit">
<script type="text/javascript">
var userid="${login_info.id}";
var userphone = "${login_info.telphone}";
	
</script>

<style type="text/css">
.tag_error{color: #f00;}

</style>

<div id="bg_first" style="background: rgba(0,0,0,.7);filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#B2000000,endColorstr=#B2000000);width: 100%;height: 100%;position: absolute;left: 0;top:0;z-index: 1002;display: none;">
	
</div>

<div id="div_first" style="position: absolute; width:400px; margin:0 auto;z-index: 1003;display: none;">
	<table style="border-spacing:8px 2px; text-align: center;font-size: 18px;font-family: 'Arial Normal', 'Arial'; font-weight: 400; font-style: normal; color: #333333;background: #fff;">
		<thead><tr><td colspan="2">绑定手机号码</td></tr></thead>
		<tr>
			<td>手机号码</td>
			<td><input id="tag_telphone" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="11" style="width: 220px;height: 29px;line-height: normal;line-height: 29px\9;text-align: center;"/></td>
		</tr>
		<tr>
			<td colspan="2"><span id="tag_info">绑定手机号不产生任何费用</span></td>
		</tr>
		<tr>
   			<td colspan="2">
   				<div id="tag_btn_commit" class="ax_形状" style="margin-left:40px; float:left; width:100px;height:30px; cursor: pointer;background-image: url('/images/getp/u9.png')">         
		          <div class="text" style="position:relative; top: 8px; color:#ffffff; -webkit-transform-origin: 48px 7px 0px; transform-origin: 48px 7px 0px;">
		            <p><span>绑定</span></p>
		          </div>
		        </div>
		        <div id="tag_btn_cancel" class="ax_形状" style="margin-left:40px; float:left; width:100px;height:30px; cursor: pointer;background-image: url('/images/getp/u9.png')">         
		          <div class="text" style="position:relative; top: 8px; color:#ffffff; -webkit-transform-origin: 48px 7px 0px; transform-origin: 48px 7px 0px;">
		            <p><span>取消</span></p>
		          </div>
		        </div>
   			</td>
   		</tr>
	</table>
</div>
	
<!-- <script src="/resources/scripts/jquery-ui-1.8.10.custom.min.js"></script> 
<script src="/resources/scripts/axure/axQuery.js"></script>
<script src="/resources/scripts/axure/globals.js"></script>
<script src="/resources/scripts/axutils.js"></script>
<script src="/resources/scripts/axure/annotation.js"></script>
<script src="/resources/scripts/axure/axQuery.std.js"></script>
<script src="/resources/scripts/axure/doc.js"></script>
 <script src="/data/document.js"></script>
<script src="/resources/scripts/messagecenter.js"></script>
<script src="/resources/scripts/axure/events.js"></script>
<script src="/resources/scripts/axure/action.js"></script>
<script src="/resources/scripts/axure/expr.js"></script>
<script src="/resources/scripts/axure/geometry.js"></script>
<script src="/resources/scripts/axure/flyout.js"></script>
<script src="/resources/scripts/axure/ie.js"></script>
<script src="/resources/scripts/axure/model.js"></script>
<script src="/resources/scripts/axure/repeater.js"></script>
<script src="/resources/scripts/axure/sto.js"></script>
<script src="/resources/scripts/axure/utils.temp.js"></script>
<script src="/resources/scripts/axure/variables.js"></script>
<script src="/resources/scripts/axure/drag.js"></script>
<script src="/resources/scripts/axure/move.js"></script>
<script src="/resources/scripts/axure/visibility.js"></script>
<script src="/resources/scripts/axure/style.js"></script>
<script src="/resources/scripts/axure/adaptive.js"></script>
<script src="/resources/scripts/axure/tree.js"></script>
<script src="/resources/scripts/axure/init.temp.js"></script>
<script src="/resources/scripts/axure/legacy.js"></script>
<script src="/resources/scripts/axure/viewer.js"></script> -->
