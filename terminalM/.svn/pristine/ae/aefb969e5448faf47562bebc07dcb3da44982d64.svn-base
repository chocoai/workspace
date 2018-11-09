

var appScreenShotStr=[]
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
	this.splice(index, 1);
	}
};

var screenShotUploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'updateImg',
	url : '../appImgUpload',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
		    { title : "Image files", extensions : "jpg,png" }, 
		]
	},
	
	init: {
		FilesAdded :  function(uploader, files) {
			if(uploader.files.length > 4){
				alert("最多只能添加3张图片")
				return;
			}
			
			plupload.each(files, function(file) {
				uploader.start(); //开始上传
			});
		},
		UploadProgress: function(uploader, file) {

		},
		FileUploaded:function(uploader,file,responseObject){
			top.art.dialog({id:"fileProgress"}).close();

			var app = eval('('+responseObject.response+')');
			
			$("li[name='updateImgLi']").before(function(n){
			      return "<li class='wql_li' name='appImg'><img src="+app.uploadFileUrl+"><i class='wql_delimgbtn' name="+file.id+"><input type='hidden' name='path' value="+app.uploadFileUrl+"></i></li>";
			});
			
			appScreenShotStr.push(app.uploadFileUrl);

		},
		UploadComplete : function(uploader,files){
		},
		FilesRemoved : function(uploader,files){
		},
		Error: function(uploader, err) {
			alert(err.message)
		}
	}
});


$("ul[name='screenShots']").on("click",".wql_delimgbtn",function(){
	var fileId = $(this).attr("name");
	
	if(fileId!=''){
		var file = screenShotUploader.getFile(fileId);
		
		screenShotUploader.removeFile(file);
	}
	
	
	$(this).parent().remove();
	
	var attrValue = $(this).find("input[name='path']").val()
	
	console.log(attrValue);
	
	appScreenShotStr.remove(attrValue);
	
})




screenShotUploader.init();//上传文件初始化