<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/layer/css/layui.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/webuploader/webuploader.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/blueimp/css/blueimp-gallery.min.css">
<script type="text/javascript" src="<%=contextPath%>/res/plugins/layer/js/layui.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/blueimp/js/jquery.blueimp-gallery.min.js"></script>
<style type="text/css">
.radioinput span { margin-right:10px; line-height:34px; vertical-align:middle;}
.radioinput span label { margin-bottom:0;}
</style>
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" style="margin-bottom: 10px;">
			<li><a href="toList?orgId=${org.orgId }">服务供应商列表</a></li>
			<li class="active">
				<c:choose>
					<c:when test="${provider.providerId != null }">
						<a href="#">编辑供应商</a>
					</c:when>
					<c:otherwise>
						<a href="#">新增供应商</a>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>

		<div class="modal fade bs-example-modal-sm" id="areaTree" tabindex="-1" role="dialog">
			<div class="modal-dialog" style="width: 450px;" role="document">
				<div class="modal-content"></div>
			</div>
		</div>
		
		<div class="col-sm-12" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
			<form class="form-horizontal pull-left form-normallr" action="add" id="providerForm" method="post" enctype="multipart/form-data">
	    	<c:choose>
				<c:when test="${provider.providerId!=null }">
					<input type="hidden" name="editType" value="edit">
					<input type="hidden" id="providerId" name="providerId" value="${provider.providerId}">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="editType" value="new">
					<input type="hidden" id="providerId" name="providerId" value="${providerId}">
				</c:otherwise>
			</c:choose>
	      	<div class="form-gd-hd">服务商必填信息</div>
      		<table class="table table-bordered">
          		<tr>
          			<th><span style="color: red;">* 标题图：</span></th>
	                <td>
	                	<input type="file" id="titleImgFile" name="titleImgFile" accept="image/jpg,image/jpeg,image/png,image/gif" style="display: none">
						<div class="input-group">
							<input type="text" id="titleImg" name="titleImg" value="${provider.titleImg}" class="form-control" readonly="readonly" required="required">
							<span class="input-group-btn"> 
								<a class="btn btn-default" onclick="$('input[id=titleImgFile]').click();">浏览</a> 
								<c:if test="${not empty provider.titleImg}">
									<a class="btn btn-default" href="${provider.titleImg}" target="_blank">查看</a>
								</c:if>
							</span>
						</div>
					</td>
          			<th><span style="color: red;">* 供应商名称：</span></th>
               		<td>
						<input type="text" class="form-control" id="providerName" name="providerName" value="${provider.providerName}" required="required">
               		</td>
          		</tr>
          		
          		<tr>
	           		<th><span style="color: red;">* 供应商类别：</span></th>
               		<td>
						<form:select id="providerCategory" path="provider.providerCategory" onchange="getProviderTypeItem();" items="${applicationScope.provider_category__}" itemLabel="value" itemValue="key" class="form-control" required="required"/>
               		</td>
               		
               		<th><span style="color: red;">* 供应商类型：</span></th>
               		<td>
						<select id="providerType" name="providerType" class="form-control" >
					   		<c:forEach items="${providerTypeList }" var="provider1">
					       		<option value="${provider1.dictValue}" <c:if test="${provider.providerType==sprovider1.dictValue}">selected = "selected"</c:if> >${provider1.dictKey}</option> 
					   		</c:forEach>
						</select>
               		</td>
		      	</tr>
		      	
          		<tr>
            		<th><span style="color: red;">* 营业执照：</span></th>
               		<td>
               			<input type="file" id="businessLicenseFile" name="businessLicenseFile"  accept="image/jpg,image/jpeg,image/png,image/gif" style="display: none">
						<div class="input-group">
							<input type="text" id="businessLicense" name="businessLicense" value="${provider.businessLicense}" class="form-control" readonly="readonly" required="required" >
							<span class="input-group-btn"> 
								<a class="btn btn-default" onclick="$('input[id=businessLicenseFile]').click();">浏览</a> 
								<c:if test="${not empty provider.businessLicense}">
									<a class="btn btn-default" href="${provider.businessLicense}" target="_blank">查看</a>
								</c:if>
							</span>
						</div>
               		</td>
           			<th><span style="color: red;">* 执照号码：</span></th>
               		<td>
                 		<input type="text" class="form-control" id="licenseNo" name="licenseNo" value="${provider.licenseNo}">
               		</td>
            	</tr>
            	
            	<tr>
          			<th><span style="color: red;">* 归属区域：</span></th>
               		<td>
						<div class="input-group">
							<input type="text" class="form-control" id="area" name="area" value="${provider.area}" placeholder="请选择所属区域" readonly="readonly" required="required"> 
							<input type="hidden" name="areaId" id="areaId" value="${provider.areaId }"> 
							<span class="input-group-btn">
								<button class="btn btn-default" id="areaBtn" type="button" data-toggle="modal" href="<%=contextPath%>/area/showAreaTree?areaId=${provider.areaId }" data-target="#areaTree">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
               		</td>
               		<th><span style="color: red;">* 经纬度：</span></th>
               		<td>
						<div class="input-group">
							<input type="text" class="form-control" id="lnglat" name="lnglat" value="${provider.lnglat}" placeholder="格式:114.39956,30.443168" required="required">
							<input type="hidden" id="longitude" name="longitude" value="${provider.longitude}"> 
							<input type="hidden" id="latitude" name="latitude" value="${provider.latitude}">
							<span class="input-group-btn"> 
								<a class="btn btn-default" href="http://lbs.amap.com/console/show/picker" target="_blank"><i class="fa fa-search"></i></a>
							</span>
						</div>
               		</td>
          		</tr>
          		
          		<tr>
		       		<th><span style="color: red;">* 负责人：</span></th>
               		<td>
						<input type="text" class="form-control" id="linkman" name="linkman" value="${provider.linkman}" >
					</td>
	           		<th><span style="color: red;">* 手机号码：</span></th>
	                <td>
						<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${provider.phoneNumber}" required="required">
					</td>
				</tr>
				
      		</table>
      		
      		
      		<div class="form-gd-hd">服务商其他信息</div>
          	<table class="table table-bordered">
          		<tr>
              		<th>所属机构：</th>
               		<td>
                 		<input type="text" class="form-control" value="${org.orgName}" readonly="readonly"> 
						<input type="hidden" id="orgId" name="orgId" value="${org.orgId}">
               		</td>
               		<th>
						<label class="control-label">是否推荐小程序：</label>
               		</th>
               		<td class="radioinput">
               			<form:radiobuttons id="isSmallpro" path="provider.isSmallpro" items="${applicationScope.is_smallpro_}" itemLabel="value" itemValue="key" />
               		</td>
             	</tr>
             	
          		<tr>
              		<th>供应商logo：</th>
	                <td>
	                	<input type="file" id="providerLogoFile" name="providerLogoFile" accept="image/jpg,image/jpeg,image/png,image/gif" style="display: none;">
						<div class="input-group">
							<input type="text" id="providerLogo" value="${provider.providerLogo}" class="form-control" readonly="readonly"> 
							<span class="input-group-btn"> 
								<a class="btn btn-default" onclick="$('input[id=providerLogoFile]').click();">浏览</a> 
								<c:if test="${not empty provider.providerLogo}">
									<a class="btn btn-default" href="${provider.providerLogo}" target="_blank">查看</a>
								</c:if>
							</span>
						</div>
	                </td>
	                <th>供应商性质：</th>
	               	<td>
	                 	<form:select id="providerNature" path="provider.providerNature" items="${applicationScope.provider_nature__}" itemLabel="value" itemValue="key" class="form-control"/>
	               	</td>
              	</tr>
              	
             	<tr>
		      		<th>供应商标签：</th>
               		<td>
               			<input type="text" class="form-control" id="providerTag" name="providerTag" value="${provider.providerTag}" >
               		</td>
               		<th>供应商评分：</th>
	                <td>
	                	<input type="text" class="form-control" id="providerScore" name="providerScore" value="${provider.providerScore}">
	                </td>
		      	</tr>
		      	
             	<tr>
	               	<th>固话号码：</th>
	               	<td>	
	               		<input type="text" class="form-control" id="tel" name="tel" value="${provider.tel}" placeholder="格式:010-12345678">
					</td>
					<th>传真：</th>
	               	<td>
	                 	<input type="text" class="form-control" id="fax" name="fax" value="${provider.fax}" placeholder="格式:010-12345678">
	               	</td>
             	</tr>
             	
              	<tr>
              		<th>电子邮箱：</th>
                	<td>
                  		<input type="email" class="form-control" id="email" name="email" value="${provider.email}" >
                	</td>
                	<th>邮政编号：</th>
                	<td>
						<input type="text" class="form-control" id="postcode" maxlength="6" name="postcode" value="${provider.postcode}" placeholder="格式:438300">
					</td>
              	</tr>
							
            	<tr>
              		<th>联系地址：</th>
               		<td colspan="3">
	                 	<textarea class="form-control" id="address" name="address" style="resize: none;">${provider.address}</textarea>
               		</td>
   				</tr>
            	
            	<tr>
               		<th>
               			<label class="control-label">上传图片：</label>
               		</th>
               		<td colspan="3">
               			<div class="row">
                            <div id="showImage" class="lightBoxGallery">
                                <c:forEach items="${imgList }" var="img" varStatus="status">
                                    <div class="col-xs-6 col-md-3" style="margin-bottom:30px;">
                                        <a href="${img.imgPath }" title="已上传的图片" data-gallery="">
                                          <img style="width: 100%; height: 180px;" src="${img.imgPath }" alt="图片丢失">
                                        </a>
                                        <a onclick="delImg(${img.imgId})" style="float: right;">删除</a>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        
                        <div class="col-sm-3">
		                    <div id="uploader">
		                    	<p>请添加1~5张主图展示产品</p>
		                        <div class="btns">
		                            <div id="progress" class="layui-progress layui-progress-big" lay-filter="demo" lay-showPercent="true" style="display:none;margin-bottom:20px;">
		                                <div class="layui-progress-bar" lay-percent="0%"></div>
		                            </div>
		                            <div id="filePicker" style="float:left;margin:0px 0px 30px 0px;"></div>
		                            <div style="float:left;padding:5px 10px 10px 10px;">
		                               <a class="btn btn-primary" id="uploader_btn">上传图片</a>
		                            </div>
		                            <div class="fileInfo" style="clear:left;"></div>
		                        </div>
		                    </div>
	                	</div>
                	   	<div class="col-sm-9">
                	   		<div id="fileList" class="uploader-list"></div>
                	   	</div>
               		</td>
          		</tr>
          		
            	<tr>
              		<th>供应商简介：</th>
               		<td colspan="3">
	                 	<textarea class="form-control" id="providerIntro" name="providerIntro" style="resize: none;">${provider.providerIntro}</textarea>
               		</td>
   				</tr>
   				
            	<tr>
               		<th>供应商设施：</th>
           			<td colspan="3">
                 		<textarea class="form-control" id="providerFacility" name="providerFacility" style="resize: none;">${provider.providerFacility}</textarea>
               		</td>
           		</tr>
           		
           		<tr>
	         		<th colspan="5">
	         			<button type="submit" class="btn btn-primary">保存</button>&nbsp;
						<button type="reset" class="btn btn-default">重置</button>
					</th>
	         	</tr>
       		</table>
    		</form>
   		</div>
	</div>

<script type="text/javascript">

	$("#lnglat").change(function(){
	  var lnglat = $("#lnglat").val().split(",");
	  $("#longitude").val(lnglat[0]);
	  $("#latitude").val(lnglat[1]);
	});
	
	$('input[id=titleImgFile]').change(function() { 
		$('#titleImg').val($(this).val()); 
	});
	
	$('input[id=providerLogoFile]').change(function() { 
		$('#providerLogo').val($(this).val()); 
	});
	
	$('input[id=businessLicenseFile]').change(function() { 
		$('#businessLicense').val($(this).val()); 
	});
	
	//二级联动查询
    function getProviderTypeItem(){
    	var value  = $("#providerCategory").val();
    	$.ajax({
			cache : true,
            type : "post",
            url : '<%=contextPath%>/dict/getTypeItem',
            data : { parentValue : value, type : "provider_category" },
            async : false,
            dataType : "json",
            traditional : true,
            success : function(data) {
            	 $("#providerType").empty();
            	for(var i=0;i<data.length;i++){
            		 $("#providerType").append("<option value='"+data[i].dictValue+"'>"+data[i].dictKey+"</option>"); // 1.为Select追加一个Option(下拉项)    
            	}
          	},
            error : function() {
            	layer.msg('供应商类型加载失败', { anim: 6 });
          	}
        });
    }

	$(function() {
		$("#providerForm").validate({
			rules : {
				providerName : {
					required : true,
					isNotEmpty : true,
					remote : {
						type : "POST",
						url : "isExistProvider",
						data : {
							orgId : function() {
								return $("#orgId").val();
							},
							providerName : function() {
								return $("#providerName").val();
							},
							providerId : function() {
								return $("#providerId").val();
							}
						}
					}
				},
				businessLicense : {
					required : true
				},
				licenseNo : {
					required : true,
					isNotEmpty : true
				},
				area : "required",
				linkman : {
					required : true,
					isNotEmpty : true
				},
				phoneNumber : {
					required : true,
					isMobile : true
				},
				tel : {
					isTel : true
				},
				fax : {
					isTel : true
				},
				postcode : {
					postCode : true
				},
				orderNum : {
					digits : true
				}
			},
			messages : {
				providerName : {
					remote : $.validator.format("当前机构下存在同名服务供应商，请重新输入")
				}
			},
			errorPlacement : function(error, element) {
				layer.tips($(error).text(), element, {
					tips : 3,
					tipsMore : true
				});
			}
		});
	});
	
	var $list = $("#fileList");
    var $uploadBtn = $("#uploader_btn");
    var fileCount = 0;
    var successCount = 0;
    var failCount = 0;
    var fileSize = 0;
    var percentages = {};
    var state = "waiting";//上传按钮初始状态
    
    var uploader = WebUploader.create({
    
        // swf文件路径
        swf: '<%=contextPath%>/res/plugins/webuploader/Uploader.swf',
    
        // 文件接收服务端。
        server: "<%=contextPath%>/upload/img?module_code=provider&commonId="+$("#providerId").val(),

        //设置文件上传域的name
        fileVal: 'upfile',

        //上传并发数
        threads: 1,
    
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id: '#filePicker',
            innerHTML: "选择图片"
        },
    
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false,

        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/gif,image/jpg,image/jpeg,image/bmp,image/png'
        },

        //生成缩略图的策略
        thumb: {
            width: 120,
            height: 90,

            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality: 70,

            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify: true,

            // 是否允许裁剪。
            crop: true,

            // 为空的话则保留原有图片格式。
            // 否则强制转换成指定的类型。
            type: 'image/jpeg'
        },

        //上传时不压缩
        compress:false
        
    });
    
    // 当有文件添加进来的时候
    uploader.on( 'fileQueued', function( file ) {
        var fileName = file.name.substring(0,4)+'---'+file.name.substring(file.name.lastIndexOf('.'));
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail" style="width:120px;height:90px;float:left;margin-left:10px;">' +
                    '<img>' +
                    '<div class="info">' + fileName + '<a id="removeFile" href="javascript:;" style="float:right;">取消</a>'+'</div>' +
                '</div>'
                ),
            $img = $li.find('img');

        $li.on('click', '#removeFile', function() {
            uploader.removeFile( file,true );
            $li.remove();
            fileSize -= file.size;
            fileCount--;
            delete percentages[ file.id ];
            if(fileCount == 0){
                $(".fileInfo").text("");
                $("#progress").css("display","none");
            }else{
                $(".fileInfo").text("选中"+fileCount+"张图片,共"+WebUploader.formatSize(fileSize)+"。");
            }
        });
        
        // $list为容器jQuery实例
        $list.append( $li );
        fileSize += file.size;
        fileCount++;
        $(".fileInfo").text("选中"+fileCount+"张图片,共"+WebUploader.formatSize(fileSize)+"。");
        
        // 创建缩略图
        // 如果为非图片文件，可以不用调用此方法。
        // thumbnailWidth x thumbnailHeight 为 100 x 100
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        });
        percentages[ file.id ] = [ file.size, 0 ];
        setState('ready');
    });
    
    function totalProgress(percentage){

        total = 0;
        loaded = 0;
        $.each( percentages, function( k, v ) {
            total += v[ 0 ];
            loaded += v[ 0 ] * v[ 1 ];
        } );

        percent = total ? loaded / total : 0;
        var str=Number(percent*100).toFixed(0);
        str+="%";
        layui.use('element', function(){
              var element = layui.element();
              element.progress('demo', str);
        });
    }

    uploader.on('uploadProgress',function(file,percentage ){
        $("#progress").css("display","");
        percentages[file.id][1] = percentage;
        totalProgress();
    });

    uploader.on('uploadSuccess',function(file,response ){
        if(response.state == 'SUCCESS'){
            successCount++;
        }else{
            failCount++;
        }
    });

    uploader.on('uploadError',function(file,reason){
        failCount++;
    });
    
    uploader.on('uploadFinished',function(){
        layer.msg("成功上传"+successCount+"个文件"+","+"上传失败"+failCount+"个文件。");
        setState('waiting');
        resetClick();
        setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
        	showImage();
        },1500);
    });
    
    $uploadBtn.on('click', function( file ) {
        if ( $(this).hasClass( 'disabled' ) ) {
            return false;
        }
        if ( state === 'ready' ) {
            uploader.upload();
        }
    });
    
    $("#reset_uploader").on("click",function(){
    	fileCount = 0;
        successCount = 0;
        failCount = 0;
        fileSize = 0;
    	resetClick();
    });
    
    function resetClick(){
    	uploader.reset();
        $list.empty();
        $(".fileInfo").text("");
        $("#progress").css("display","none");
        $("#reset_uploader").css("display","none");
    }
    
    function setState(val){
        if ( val === state ) {
            return;
        }
        $uploadBtn.removeClass( 'state-' + state );
        $uploadBtn.addClass( 'state-' + val );
        state = val;
    }
    
    function delImg(imgId){
		layer.confirm('确认删除该图片吗？删除后不可恢复', {btn: ['确认','取消']}, function(){
			$.ajax({
				cache : true,
               	type : "post",
               	url : '<%=contextPath%>/upload/delImg',
               	async : false,
               	data : {
                 	"imgId" : imgId
               	},
                traditional : true,
                success : function(data) {
                	layer.msg('删除成功', { anim: 6 });
                    setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                    	showImage();
                  	},1500);
              	},
                error : function() {
                	layer.msg('删除失败', { anim: 6 });
                }
         	});
    	}, function(){
        	return;
       	});
	}
    
    function showImage(){
    	$.ajax({
			cache : true,
            type : "post",
            url : '<%=contextPath%>/ywImage/showImage?uid='+$("#providerId").val()+'&type=provider',
            async : false,
            traditional : true,
            success : function(data) {
            	$("#showImage").empty();
            	var obj = JSON.parse(data);
            	var strHtml=""; 
            	for(var i=0; i<obj.length;i++){
            		strHtml +=  "<div class='col-xs-6 col-md-3' style='margin-bottom:30px;'>"
                	+"<a href="+obj[i].imgPath+" title='已上传的图片' data-gallery=''>"
                	+"<img style='width: 100%; height: 180px;' src="+obj[i].imgPath+" alt='图片丢失'></a>"
                	+"<a onclick='delImg("+obj[i].imgId+")' style='float: right;'>删除</a></div>";
            	}
            	$("#showImage").append(strHtml);
          	},
            error : function() {
            	layer.msg('显示图片失败', { anim: 6 });
          	}
        });
    }
</script>
</body>
</html>