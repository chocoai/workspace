/**
 * /**
shao  * @see 上传文件公共方法
 * @param options.ctx 项目路径
 * @param url 请求链接
 * @param browse_button 上传控件Id
 * @param container 接受容器Id
 * @param uploadProgress 显示进度ID
 * @param setValId 将返回值设置到对应ID
 * @param isShow 对应ID是否显示
 * @param isImg 是否是图片
 * 
 * 
 * eg;
var options = {
				uploader : '#uploader',
				ctx : '${ctx }',
				browse_button : "browse_btn",
				max_file_size : "1mb",
				mime_types : 'png,jpg',
				chunk_size : 100*1024*1024,
				multipart_params : {op:'products',isImg:'false', width:'100',height:'100'},
				setValId : "#app_image"
		}
 */
function uploadFn(options) {
	var upoptions = options;
	var upthis = this;
	var $wrap = $(options.uploader),
	 	$queue = $wrap.find('.filelist'),
	 	$statusBar = $wrap.find('.statusBar'),
	 	$info = $statusBar.find( '.info'),
	 	$progress = $statusBar.find( '.progress'),
	 	fileSize = 0;
	// 上传
	var mime_types = [];
	if(options.mime_types){
		var obj = {};
		obj.title = "Mime types";
		obj.extensions=options.mime_types;
		mime_types.push(obj);
	}
	var url = options.uploadUrl?options.uploadUrl:(options.ctx+'/_upload.html');
	var checkExistUrl = options.checkExistUrl?options.checkExistUrl:(options.ctx+'/_uploadMd5.html');
	var uploader = new plupload.Uploader({
		runtimes : 'html5,flash',
		browse_button : options.browse_button, // 上传控件Id
//		container : options.container,
		url : url,
		flash_swf_url : options.ctx+'/common/plupload/js/Moxie.swf',
		silverlight_xap_url : options.ctx+'/common/plupload/js/Moxie.xap',
		multi_selection : false,
		unique_names : true,
		chunk_size : options.chunk_size,
		multipart_params: {
			  params: options.multipart_params
			},
		filters : {
			max_file_size : options.max_file_size,
			mime_types : mime_types
		},

		init : {
			PostInit : function() {
//				 alert("PostInit");
				upoptions['$wrap'] = $wrap;
				upoptions['$queue'] = $queue;
				upoptions['$statusBar'] = $statusBar;
				upoptions['$info'] = $info;
				upoptions['$progress'] = $progress;
				upoptions['checkExistUrl'] = checkExistUrl;
			},
			//暂不清楚该事件的意义，但根据测试得出，该事件会在每一个文件被添加到上传队列前触发 
			FileFiltered : function(uploaderr,file) {
				upthis.updateStatus(options,0);
				//禁用样式
				$('#'+options.browse_button).addClass('my-disabled');
				uploader.disableBrowse(true);//禁用游览功能
				//console.log(file)
				fileSize = file.origSize / 1000 ;
				upoptions['fileSize'] = fileSize;
				var browserMatch = getBrowserInfo();
				if (browserMatch.browser==='IE'){
					var version = browserMatch.version;
					//ie10以下版本不sha1
					if(parseInt(version)<10){
						addFiles(file, $queue);
						uploader.start()
				  } else {
					  sha1File(upthis, uploader, upoptions, file);
				  }
				}  else {
					sha1File(upthis, uploader, upoptions, file);
//					selectFile(upthis, uploader, upoptions, file);
				}
			},
			//当文件添加到上传队列后触发 
			FilesAdded : function(up, files) {
//				alert('FilesAdded');
				//console.log(files.length)
					
			},
			//会在文件上传过程中不断触发，可以用此事件来显示上传进度 
			UploadProgress : function(up, file) {
//				alert("UploadProgress");
				updateTotalProgress(options,file);
			},
			//当队列中的某一个文件上传完成后触发 
			FileUploaded : function(up, file, res) {
				//bootbox.hideAll();//ie10一下关闭弹窗口异常，故这样关闭
				var reponse = eval("(" + res.response + ")");
//				 alert(reponse.error);
				if (reponse.result != "success") {
					bb_alert(null,reponse.error.message);
				} else {
					var timeid = window.setInterval(function(){
						updateStatus(upoptions, file.status);
//						if($progressBar.css("width")=='245px'){
							$('#'+options.browse_button).removeClass('my-disabled');
							uploader.disableBrowse(false);
							bootbox.hideAll();//不能放到uploader.disableBrowse(false);前面，ie8报错
							bb_alert(null,"上传成功");
							window.clearInterval(timeid);
//						}
					}, 1500); 
					
					$(options.setValId).val(reponse.url);
				}
			},
			//当文件从上传队列移除后触发 
			FilesRemoved : function(up, files) {
//				alert("ilesRemoved");
//		        removeFile( files[0] );
//		        updateTotalProgress();
				$('#'+options.browse_button).removeClass('my-disabled');
				uploader.disableBrowse(false);
			},
			//当上传队列中所有文件都上传完成后触发 ,最后执行，在error后面
			UploadComplete : function(up, files) {
				//alert("UploadComplete");
			},

			ChunkUploaded : function(up, file, res) {
				// alert("ChunkUploaded");
			},
			//当发生错误时触发 
			Error : function(up, err) {
				$('#'+options.browse_button).removeClass('my-disabled');
				uploader.disableBrowse(false);
				//alert("Error");
				bootbox.hideAll();//ie10一下关闭弹窗口异常，故这样关闭
				bb_alert(null,'上传失败:' + err.message);
			},
			//当调用destroy方法时触发 
			Destroy : function(up) {
				alert("Destroy");
			}
		}
	});
	
	f1 = function(){
		alert('f1')
	}
	
	//添加预览图片
	addFile = function(file, $li, del) {
		var fdel = del == undefined ? true : del;
		//图片预览
//		$li = $queue.find('li');
		$li.attr("id",file.id);
        $li.html('<p class="title">' + file.name + '</p>' +
                '<p class="imgWrap"></p>');
        if(fdel) {
        	var $btns = $('<div class="file-panel">' +
        	'<span class="cancel">删除</span>').appendTo( $li );
        	
        	$li.on( 'mouseenter', function() {
        		$btns.stop().animate({height: 30});
        	});
        	
        	$li.on( 'mouseleave', function() {
        		$btns.stop().animate({height: 0});
        	});
        	
        	$btns.on( 'click', 'span', function() {
        		var index = $(this).index(),
        		deg;
        		
        		switch ( index ) {
        		case 0:
        			uploader.removeFile( file );
        			removeFile(file);
        			return;
        		case 1:
        			file.rotation += 90;
        			break;
        			
        		case 2:
        			file.rotation -= 90;
        			break;
        		} 
        	});
        }
        $imgWrap = $li.find('.imgWrap');        
//		$li.appendTo($queue);
        //图片预览超过300M浏览器出错
        if(file.size/1024/1024 < 300){
        	previewImage(file,function(imgsrc){
        		$imgWrap.append('<img src="'+ imgsrc +'" />');
        	})
        }
		//$li.append( '<span class="success"></span>' );//不要加，头像上传的时候样式不一致
	}
	
	addFiles = function (file, $queue) {
		$li = $queue.find('li');
		if($li.length==1) {
	        	addFile( file, $li, false);
	        } else if($li.length>1) {
	        	$li.each(function(index){
	        		//alert($(this).attr('id'));
	        		if($(this).html()==='') {
	        			addFile( file, $(this));
	        			return false;
	        		}
	        	});
	        }
	}
	
	previewImage = function(file,callback){//file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
		if(!file || !/image\//.test(file.type)) return; //确保文件是图片
		if(file.type=='image/gif'){//gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
			var fr = new mOxie.FileReader();
			fr.onload = function(){
				callback(fr.result);
				fr.destroy();
				fr = null;
			}
			fr.readAsDataURL(file.getSource());
		}else{
			var preloader = new mOxie.Image();
			preloader.onload = function() {
				preloader.downsize( 300, 300 );//先压缩一下要预览的图片,宽300，高300
				var imgsrc = preloader.type=='image/jpeg' ? preloader.getAsDataURL('image/jpeg',80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
				callback && callback(imgsrc); //callback传入的参数为预览图片的url
				preloader.destroy();
				preloader = null;
			};
			preloader.load( file.getSource() );
		}	
	}
	
	updateTotalProgress = function(options,file,name) {
		options.$statusBar.show();
		options.$info.hide();
		options.$progress.show();
	    var loaded = 0,
	        total = 0,
	        spans = options.$progress.children(),
	        percent;
	    percent = file.percent;
	//	        loaded = file.loaded;
	    var txt = name ||'上传中...   ';
	    if(uploader.total.bytesPerSec!=0){
	    	txt += uploader.total.bytesPerSec /1000 + 'KB/s  ';
	    }
	    txt += percent  + '%';
	    spans.eq( 0 ).text( txt );
//	    spans.eq( 0 ).text( uploader.total.bytesPerSec  + '%' );
	    spans.eq( 1 ).css( 'width',  percent   + '%' );
	   // updateStatus();
	}
	
	updateStatus = function(options,status){
		options.$statusBar.show();
		options.$progress.hide();
		options.$info.show();
		var text = '', stats;
		if(status === 5) {
			options.$info.removeClass().addClass("info success");
			text = '上传成功	大小:'+ options.fileSize +'K';
		} else if(status === 4) {
			options.$info.removeClass().addClass("info faile");
			text = '上传失败，请重新上传';
		} else if(status === 0) {
			options.$info.removeClass().addClass("info warning");
			text = '上传准备中...请稍等...';
		}
		options.$info.html( text );
	}
	
	
  // 负责view的销毁
	function removeFile(file) {
        var $li = $('#'+file.id);
        $li.off().find('.file-panel').off();
        $li.html('');
    }
    
	uploader.init();
	return uploader;
}

