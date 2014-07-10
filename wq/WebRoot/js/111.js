$(function(){
	$('#null').uploadify({
		buttonText:'浏览',
		auto:false,
		progressData:'speed',
		multi:true,
		height:25,
		overrideEvents:['onDialogClose'],
		fileTypeDesc:'文件格式:',
		queueID:'filediv',
		fileTypeExts:'',
		fileSizeLimit:'15MB',
		swf:'plug-in/uploadify/uploadify.swf',	
		uploader:'nullonUploadStart : function(file) { } ,
		onQueueComplete : function(queueData) { 
			var win = frameElement.api.opener;
			win.reloadTable();
			win.tip(serverMsg);
			frameElement.api.close();
		},
		onUploadSuccess : function(file, data, response) {
			var d=$.parseJSON(data);
			if(d.success){
				var win = frameElement.api.opener;
				serverMsg = d.msg;
				}
		},
		onFallback : function(){
			tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
		},
		onSelectError : function(file, errorCode, errorMsg){
			switch(errorCode) {
			case -100:tip("上传的文件数量已经超出系统限制的"+$('#null').uploadify('settings','queueSizeLimit')+"个文件！");
			break;case -110:tip("文件 ["+file.name+"] 大小超出系统限制的"+$('#null').uploadify('settings','fileSizeLimit')+"大小！");
			break;case -120:tip("文件 ["+file.name+"] 大小异常！");
			break;case -130:tip("文件 ["+file.name+"] 类型不正确！");
			break;
		},
		onUploadProgress : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) { }});});function upload() {	$('#null').uploadify('upload', '*');		return flag;}function cancel() {$('#null').uploadify('cancel', '*');}</script>
}
