/**
 * 默认图片上传方法
 * @param fileId
 * @param fileObjName
 * @param uploader
 * @param auto
 * @param multi
 * @param fileSizeLimit
 * @param fileTypeExts
 * @param formData
 * @param buttonImage
 * @param onUploadSuccess
 */
function defaultUpload(fileId,fileObjName,uploader,auto,multi,fileSizeLimit,fileTypeExts,formData,buttonText,width,height,onUploadSuccess){
	$('#'+fileId).uploadify({
		'method'   : 'post',
    	'fileObjName' : fileObjName, 
        'swf'      : '/js/uploadify/uploadify.swf',
        'uploader' : uploader,
        'auto'	   : auto,
        'multi'    : multi,
        //'debug'    : true,
        'fileSizeLimit' : fileSizeLimit,
        'fileTypeDesc' : 'All Files',
        'fileTypeExts' : fileTypeExts, //根据后缀名来判断的，需后台代码判断MIME类型才准确
        'formData'      : formData,
        //'buttonClass' : 'some-class',
        //'buttonImage' : buttonImage,
        'buttonText' : buttonText,
        'width' : width,
        'height' : height,
        'onUploadSuccess' : onUploadSuccess
	});
}
