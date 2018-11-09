<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link href="<%=contextPath%>/res/plugins/UMeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<link href="<%=contextPath%>/res/plugins/webuploader/webuploader.css" type="text/css" rel="stylesheet">
<link href="<%=contextPath%>/res/plugins/layer/css/layui.css" type="text/css" rel="stylesheet">
<link href="<%=contextPath%>/res/plugins/blueimp/css/blueimp-gallery.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<style type="text/css">
.radioinput span { margin-right:10px; line-height:34px; vertical-align:middle;}
.radioinput span label { margin-bottom:0;}
</style>
<script type="text/javascript">
	UMimageId = 0;
	upload_url = "<%=contextPath%>/upload/img?module_code=goods_editor";
	if("${goods.goodsId}" != null && "${goods.goodsId}" != ""){
	    param = "&commonId=${goods.goodsId}";
	}else{
	    param = "&commonId=${goodsId}";
	}
</script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/third-party/template.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/umeditor.config.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/umeditor.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/layer/js/layui.js"></script>
<script src="<%=contextPath%>/res/plugins/blueimp/js/jquery.blueimp-gallery.min.js"></script>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:mall:order:list">
						<li><a href="<%=contextPath%>/ywOrder/list" target="_self"><span>订单管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:mall:goods:list">
						<li class="active"><a href="<%=contextPath%>/goods/list" target="_self"><span>商品管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<ul class="nav nav-tabs">
				<li><a href="list">商品列表</a></li>
				<li class="active">
					<c:choose>
						<c:when test="${goods.goodsId != null }">
							<a href="edit?goodsId=${goods.goodsId }">编辑商品</a>
						</c:when>
						<c:otherwise>
							<a href="edit">添加商品</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		
			<div class="col-sm-12" style="padding-right: 0px; padding-left: 0px;">
		    	<form class="form-horizontal pull-left form-normallr" id="goodsForm" method="post" action="save" enctype="multipart/form-data">
		    	<c:choose>
					<c:when test="${goods.goodsId!=null }">
						<input type="hidden" name="editType" value="edit">
						<input type="hidden" id="goodsId" name="goodsId" value="${goods.goodsId }">
					</c:when>
					<c:otherwise>
						<input type="hidden" name="editType" value="new">
						<input type="hidden" id="goodsId" name="goodsId" value="${goodsId }">
					</c:otherwise>
				</c:choose>
		      	<div class="form-gd-hd">商品必填信息</div>
	      		<table class="table table-bordered">
	          		<tr>
	          			<th><span style="color: red;">* 商品名称：</span></th>
	               		<td>
	           				<input type="text" id="goodsName" class="form-control" name="goodsName" value="${goods.goodsName }">
	               		</td>
	               		<th><span style="color: red;">* 库存量：</span></th>
	               		<td>
	               			<input id="stockAmount" type="text" class="form-control" name="stockAmount" value="${goods.stockAmount }">
	               		</td>
	          		</tr>
	          		
	          		<tr>
	          			<th><span style="color: red;">* 商品类别：</span></th>
	               		<td>
	              			<form:select class="form-control" id="goodsCategory" path="goods.goodsCategory" onchange="getGoodsTypeItem();" name="goodsCategory" items="${applicationScope.goods_category__}" itemLabel="value" itemValue="key" cssStyle=""/>
	               		</td>
	               		<th><span style="color: red;">* 商品类型：</span></th>
	               		<td>
	               			<select id="goodsType" name="goodsType" class="form-control" >
							   <c:forEach items="${goodsTypeList }" var="list">
							       <option value="${list.dictValue}" <c:if test="${goods.goodsType==list.dictValue}">selected = "selected"</c:if> >${list.dictKey}</option> 
							   </c:forEach>
							</select>
	               		</td>
	          		</tr>
	          		
			       	<tr>
						<th><span style="color: red;">* 商品原价：</span></th>
	               		<td>
							<input type="text" class="form-control" id="originalPrice" name="originalPrice" oninput="getValue();" value="${goods.originalPrice }">
						</td>
		           		<th><span style="color: red;">* 计价单位：</span></th>
		                <td>
		                	<select class="form-control" id="unit" name="unit">
								<option value="元/个">元/个</option>
								<option value="元/盒">元/盒</option>
								<option value="元/箱">元/箱</option>
								<option value="元/人">元/人</option>
							</select>
						</td>
			      	</tr>
	      		</table>
		      	
		      	<div class="form-gd-hd">服务其他信息</div>
	      		<table class="table table-bordered">
	      			<tr>
	               		<th>商品折扣：</th>
	               		<td>
	               			<input type="text" class="form-control" id="discount" name="discount" oninput="getValue();" maxlength="4" value="${goods.discount }">
	               		</td>
	               		<th>商品单价：</th>
		                <td>
		                	<input id="unitPrice" type="text" class="form-control" name="unitPrice" value="${goods.unitPrice }" readonly="readonly">
						</td>
	          		</tr>
	          		<tr>
	               		<th>标题图：</th>
	               		<td>
	               			<input type="file" id="titleImgFile" name="titleImgFile" accept="image/jpg,image/jpeg,image/png,image/gif" style="display: none">
							<div class="input-group">
								<input type="text" id="titleImg" value="${goods.titleImg}" class="form-control" readonly="readonly">
								<span class="input-group-btn"> 
									<a class="btn btn-default" onclick="$('input[id=titleImgFile]').click();">浏览</a> 
									<c:if test="${not empty goods.titleImg}">
										<a class="btn btn-default" href="${goods.titleImg}" target="_blank">查看</a>
									</c:if>
								</span>
							</div>
	               		</td>
	               		<th>奖励积分：</th>
	               		<td>
	               			<input type="text" class="form-control" id="score" name="score" value="${goods.score }">
	               		</td>
	          		</tr>
	          		
	          		<tr>
	               		<th>是否推荐小程序：</th>
	               		<td class="radioinput">
	               			<form:radiobuttons id="iext1" path="goods.iext1" items="${applicationScope.is_smallpro_}" itemLabel="value" itemValue="key" cssClass="" />
	               		</td>
	               		<th>奖励积分：</th>
	               		<td>
	               			<input type="text" class="form-control" id="score" name="score" value="${goods.score }">
	               		</td>
	          		</tr>
	          		
	          		<tr>
	               		<th>上传图片：</th>
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
	                	<th>商品简介：</th>
	                	<td colspan="3">
	                		<textarea rows="3" class="form-control" name="goodsIntro" id="goodsIntro" style="resize: none;">${goods.goodsIntro }</textarea>
	                	</td>
		         	</tr>
		         	
		         	<tr>
	                	<th>商品详情：</th>
	                	<td colspan="3">
	                		<input type="hidden" id="content" name="content">
	                		<!--style给定宽度可以影响编辑器的最终宽度-->
							<script type="text/plain" id="myEditor" style="width: 100%; height: 240px;">${goods.content}</script>
	                	</td>
		         	</tr>
		         	<tr>
		         		<th colspan="5">
		         			<input type="submit" id="submitBtn" class="btn btn-primary" value="提交">
							<input type="reset" class="btn btn-default">
						</th>
		         	</tr>
	      		</table>
		    	</form>
		    </div>
	   	</div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">

    if("${goods}" != ''){
        $("#unit").val("${goods.unit}");
    }
    
	var um = UM.getEditor('myEditor');
	if("${goods}"!=""){
	    $("#content").val(um.getContent());
	}
    um.addListener('blur',function(){
        $("#content").val(um.getContent());
    });
	
    function getValue(){//取值
    	var unitPrice = $("#originalPrice").val();
    	var discount = $("#discount").val();
    	if(!$.isEmptyObject(discount)){
    		unitPrice = accMul(unitPrice, discount);
    	}
		$("#unitPrice").val(unitPrice);
	}
    
    function accMul(num1,num2){
    	var m=0, s1=num1.toString(), s2=num2.toString();   
    	try{
    		m+=s1.split(".")[1].length;
    	}catch(e){
    		
    	};  
    	try{
    		m+=s2.split(".")[1].length;
    	}catch(e){
    		
    	};  
    	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m);  
    } 
    
	$("#submitBtn").click(function() {
		return validata();
	});
	function validata() {
		$("#goodsForm").validate({
			rules : {
				goodsName : {
	            	required:true,
	            	isNotEmpty : true
	            },
	            originalPrice : {
                    required : true,
                    number : true   
                },
                stockAmount : {
                    required : true,
                    number : true   
                },
                goodsCategory : "required",
                goodsType : "required",
                unit : "required",
                originalPrice : "number",
                score : "number",
                discount : {
                    number : true,
                   	min:0,
                    max:1
                }
			},
            errorPlacement:function(error,element) {
                layer.tips($(error).text(), element, {
                        tips: 3,
                        tipsMore: true
                });
            }
		});
	}

    function delImg(imgId){
        layer.confirm('确认删除该图片吗？删除后不可恢复', {
            btn: ['确认','取消'] //按钮
          }, function(){
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
                      setTimeout(function(){  //使用  setTimeout（）方法设定定时1500毫秒
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
	$("#titleImgFile").change(function(){
	    var aa=document.getElementById("titleImgFile").value.toLowerCase().split('.');//以“.”分隔上传文件字符串
	    if(aa[aa.length-1]=='jpg'||aa[aa.length-1]=='jpeg'||aa[aa.length-1]=='png'||aa[aa.length-1]=='gif'){
	        var excelSize =  document.getElementById("titleImgFile").files[0].size;
            if(excelSize>1024*1024*5){
                layer.msg("文件过大,请将大小控制在5M以内");
                $("#titleImgFile").after($("#titleImgFile").clone().val("")); 
                $("#titleImgFile").remove();
                return false;
            }
	    } else{
	        layer.msg('请选择格式为*.jpg、*.jpeg、*.png、*.gif');//jpg和jpeg格式是一样的只是系统Windows认jpg，Mac OS认jpeg，
	        $("#titleImgFile").after($("#titleImgFile").clone().val("")); 
	        $("#titleImgFile").remove();
            return false;
	    }
	    $("#titleImg").val($(this).val());
	    return true;
	});
    var $list = $('#fileList');
    var $uploadBtn = $("#uploader_btn");
    var fileCount = 0;
    var successCount = 0;
    var failCount = 0;
    var fileSize = 0;
    var percentages = {};
    var state = 'waiting';//上传按钮初始状态
    
    var uploader = WebUploader.create({
    
        // swf文件路径
        swf: '<%=contextPath%>/res/plugins/webuploader/Uploader.swf',
    
        // 文件接收服务端。
        server: "<%=contextPath%>/upload/img?module_code=goods" + param,

        //开启自动上传
        //auto: true,
        
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
        uploader.reset();
        $list.empty();
        $(".fileInfo").text("");
        $("#progress").css("display","none");
        $("#reset_uploader").css("display","none");
        
        setTimeout(function(){  //使用  setTimeout（）方法设定定时1500毫秒
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
        uploader.reset();
        $list.empty();
        $(".fileInfo").text("");
        $("#progress").css("display","none");
        fileCount = 0;
        successCount = 0;
        failCount = 0;
        fileSize = 0;
        $("#reset_uploader").css("display","none");
    });
    
    function setState(val){
        if ( val === state ) {
            return;
        }
        $uploadBtn.removeClass( 'state-' + state );
        $uploadBtn.addClass( 'state-' + val );
        state = val;
    }
    
    function showImage(){
    	$.ajax({
			cache : true,
            type : "post",
            url : "<%=contextPath%>/ywImage/showImage?uid="+$('#goodsId').val()+"&type=goods",
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
    
    //二级联动下拉框查询
    function getGoodsTypeItem(){
    	var value  = $("#goodsCategory").val();
    	$.ajax({
			cache : true,
            type : "post",
            url : '<%=contextPath%>/dict/getTypeItem',
            data : { parentValue : value, type : "goods_category" },
            async : false,
            dataType : "json",
            traditional : true,
            success : function(data) {
            	 $("#goodsType").empty();
            	for(var i=0;i<data.length;i++){
            		 $("#goodsType").append("<option value='"+data[i].dictValue+"'>"+data[i].dictKey+"</option>"); // 1.为Select追加一个Option(下拉项)    
            	}
          	},
            error : function() {
            	layer.msg('服务类型加载失败', { anim: 6 });
          	}
        });
    }
</script>
</html>