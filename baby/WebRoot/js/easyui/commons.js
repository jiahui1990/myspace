/**
 * 
 * @param date
 * @returns {String}
 */
function formatDatetime(date){
	var y = date.getFullYear();  
    var m = date.getMonth()+1;  
    var d = date.getDate();  
    var h = date.getHours();  
    var min = date.getMinutes();  
    var sec = date.getSeconds();  
    var str = y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d)+' '+(h<10?('0'+h):h)+':'+(min<10?('0'+min):min)+':'+(sec<10?('0'+sec):sec);  
    return str;  
} 
/**
 * 提示框位置
 */
function alertTopRight(title,msg,showType){
	$.messager.show({
		title:title,
		msg:msg,
		showType:showType,
		style:{
			left:'',
			right:0,
			top:document.body.scrollTop+document.documentElement.scrollTop,
			bottom:''
		}
	});
}

/**
 * 自定义easyUI验证，它内部已经包含如下验证
 *   email: Match email regex rule.
 *   url: Match URL regex rule.
 *   length[0,100]: Between x and x characters allowed.
 *   remote['http://.../action.do','paramName']: Send ajax request to do validate value, return 'true' when successfully.
 */
$.extend($.fn.validatebox.defaults.rules, {
	//用法：
    /*<input id="pwd" name="pwd" type="password" class="easyui-validatebox" data-options="required:true">
      <input id="rpwd" name="rpwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'equals[\'#pwd\']'">*/
	equals: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: '两次输入的密码不一致.'
	},
	//用法：data-options="validType:'minLength[5]'"
	minLength: {
		validator: function(value, param){
			return value.length >= param[0];
		},
		message: '请至少输入{0}个字符.'
	},
	maxLength: {
		validator: function(value, param){
			return value.length <= param[0];
		},
		message: '不能超过{0}个字符.'
	}
});