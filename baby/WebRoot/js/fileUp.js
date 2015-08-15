var attt = null;
function uploadSuccess(file,data,response){	
	data = $.parseJSON(data);
	attt = data.attachment;
}

function initUpAttt(visualSavePath){
	defaultUpload("file_upload","uploadIn.file","${ctx }/uploadAction_upload.action;jsessionid=${pageContext.session.id}",true,false,"50MB","*.ppt;*.doc;*.xls;*.pdf;*.jpg;*.jpeg;*.bmp;*.png;*.zip;*.rar",{'uploadIn.savePath':visualSavePath},"选择附件",100,30,uploadSuccess);
	$("#file_upload").after('<span>'
								+'文档大小不超过50M，如有多个文件，请压缩上传。支持*.ppt、*.doc、*.xls、*.pdf、*.jpg、*.jpeg、*.bmp、*.png、*.zip、*.rar'
								+'</span>');
}
