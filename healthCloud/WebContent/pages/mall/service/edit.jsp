<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<% String contextPath = request.getContextPath(); %>
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/UMeditor/themes/default/css/umeditor.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/webuploader/webuploader.css">
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/layer/css/layui.css" >
<link type="text/css" rel="stylesheet" href="<%=contextPath%>/res/plugins/blueimp/css/blueimp-gallery.min.css">
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/res/css/bootstrap-select.css">
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<style type="text/css">
.alert-gray { float:left; background-color: #f5f5f5; color: #333; padding: 0 30px 0 12px; height: 34px; line-height: 34px; border-radius: 17px; margin-right: 20px; margin-bottom: 10px; }
.alert-gray strong { font-weight: normal;}
.alert-gray button span { line-height: 34px; left: -12px; position: absolute; }
</style>
<script type="text/javascript">
	UMimageId = 0;
	upload_url = "<%=contextPath%>/upload/img?module_code=service_editor";
	if("${service.serviceId}" != null && "${service.serviceId}" != ""){
	    param = "&commonId=${service.serviceId}";
	}else{
	    param = "&commonId=${serviceId}";
	}
</script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/third-party/template.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/umeditor.config.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/umeditor.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/UMeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/webuploader/webuploader.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/layer/js/layui.js"></script>
<script type="text/javascript" src="<%=contextPath%>/res/plugins/blueimp/js/jquery.blueimp-gallery.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=contextPath%>/res/css/bootstrap-select.css">    
<script type="text/javascript" src="<%=contextPath%>/res/js/bootstrap-select.js"></script>    
<script type="text/javascript">  
	$(window).on("load", function () {  
	    $(".selectpicker").selectpicker({  
	        "selectedText": "cat"  
	    });  
	});  
</script>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
	<div class="row-fluid" >
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:workOrder:list">
						<li><a href="<%=contextPath%>/workOrder/list" target="_self"><span>工单管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:service:list">
						<li class="active"><a href="<%=contextPath%>/service/list" target="_self"><span>服务管理</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
			<ul class="nav nav-tabs">
				<li><a href="list">服务列表</a></li>
				<li class="active">
				    <c:choose>
						<c:when test="${service.serviceId!=null }">
							<a href="edit?serviceId=${service.serviceId }">编辑服务</a>
						</c:when>
						<c:otherwise>
							<a href="edit">添加服务</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
		
			<div class="col-sm-12" style="padding-right: 0px; padding-left: 0px;">
		    	<form class="form-horizontal pull-left form-normallr" id="serviceForm" method="post" action="save" enctype="multipart/form-data">
		    	<input type="hidden" id="priceNum" >
		    	<c:choose>
					<c:when test="${service.serviceId!=null }">
						<input type="hidden" name="editType" value="edit">
						<input type="hidden" id="serviceId" name="serviceId" value="${service.serviceId }">
					</c:when>
					<c:otherwise>
						<input type="hidden" name="editType" value="new">
						<input type="hidden" id="serviceId" name="serviceId" value="${serviceId }">
					</c:otherwise>
				</c:choose>
		      	<div class="form-gd-hd">服务必填信息</div>
	      		<table class="table table-bordered">
	          		<tr>
	          			<th><span style="color: red;">* 所属服务商：</span></th>
	               		<td>
	           				<select id="providerId" name="providerId" class="selectpicker show-tick form-control" re data-live-search="true" >
							   <option value="">--请选择--</option>
							   <c:forEach items="${providerList }" var="list">
							       <option value="${list.providerId}" <c:if test="${service.providerId == list.providerId}">selected = "selected"</c:if> >${list.providerName}</option> 
							   </c:forEach>
							</select>
	               		</td>
	               		<th><span style="color: red;">* 服务名称：</span></th>
	               		<td>
	               			<input id="serviceName" type="text" class="form-control" name="serviceName" value="${service.serviceName }">
	               		</td>
	          		</tr>
	          		
	          		<tr>
	          			<th><span style="color: red;">* 服务类别：</span></th>
	               		<td>
	              			<form:select id="serviceCategory" path="service.serviceCategory" onchange="getServiceTypeItem();" name="serviceCategory" items="${applicationScope.service_category__}" itemLabel="value" itemValue="key" class="form-control"/>
	               		</td>
	               		<th><span style="color: red;">* 服务类型：</span></th>
	               		<td>
	               			<select id="serviceType" name="serviceType" class="form-control" >
						   		<c:forEach items="${serviceTypeList }" var="serviceType1">
						       		<option value="${serviceType1.dictValue}" <c:if test="${service.serviceType==serviceType1.dictValue}">selected = "selected"</c:if> >${serviceType1.dictKey}</option> 
						   		</c:forEach>
							</select>
	               		</td>
	          		</tr>
			       	<tr>
		           		<th><span style="color: red;">* 服务价格：</span></th>
		                <td colspan="3">
		                	<div class="row" style="margin-left:0;margin-right:0;">
	                            <div id="showPrice">
	                                <c:forEach items="${priceList }" var="price" varStatus="status">
	                                   	<div class="alert alert-gray alert-dismissible pull-left" role="alert">
							                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true" onclick="delPrice(${price.cid})" >&times;</span></button>
							                <strong><a onclick="updatePrice(${price.cid})"><span>${price.priceDetails}</span></a></strong>
							            </div>
	                            	</c:forEach>
	                        	</div>
	                        	<div class="col-sm-1">
									<a class="btn btn-primary" onclick="addServicePrice();" ><p>+商品价格</p></a>
		               			</div>
	                       	</div>
						</td>
			      	</tr>
			      	<tr>
		           		<th><span style="color: red;">* 服务营业状态：</span></th>
		                <td>
		                	<select class="form-control" id="serviceStatus" name="serviceStatus">
								<option value="1">营业</option>
								<option value="0">停业</option>
							</select>
						</td>
			      	</tr>
	      		</table>
		      	
		      	<div class="form-gd-hd">服务其他信息</div>
	      		<table class="table table-bordered">
	          		<tr>
	               		<th>服务开始时间：</th>
	               		<td>
							<input type="text" id="serviceStime" name="serviceStime" class="form-control" readonly >
						</td>
	               		<th>服务结束时间：</th>
	               		<td>
	               			<input type="text" id="serviceEtime" name="serviceEtime" class="form-control" readonly >
	               		</td>
	          		</tr>
	          		
	          		<tr>
	               		<th>标题图：</th>
	               		<td>
							<input type="file" id="titleImgFile" name="titleImgFile" accept="image/jpg,image/jpeg,image/png,image/gif" style="display: none">
							<div class="input-group">
								<input type="text" id="titleImg" value="${service.titleImg}" class="form-control" readonly="readonly">
								<span class="input-group-btn"> 
									<a class="btn btn-default" onclick="$('input[id=titleImgFile]').click();">浏览</a> 
									<c:if test="${not empty service.titleImg}">
										<a class="btn btn-default" href="${service.titleImg}" target="_blank">查看</a>
									</c:if>
								</span>
							</div>
	               		</td>
	               		<th>奖励积分：</th>
	               		<td>
	               			<input type="text" name="score" class="form-control" id="score" value="${service.score}">
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
	                	<th>服务简介：</th>
	                	<td colspan="3">
	                		<textarea rows="3" class="form-control" name="serviceIntro" id="serviceIntro" style="resize: none;">${service.serviceIntro }</textarea>
	                	</td>
		         	</tr>
		         	
		         	<tr>
	                	<th>服务详情：</th>
	                	<td colspan="3">
	                		<input type="hidden" id="content" name="content">
	                		<!--style给定宽度可以影响编辑器的最终宽度-->
							<script type="text/plain" id="myEditor" style="width: 100%; height: 240px;">${service.content}</script>
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
    <div class="modal fade bs-example-modal-lg" id="addPrice" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" style="width: 700px;" role="document">
			<div class="modal-content ">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="myModalLabel">服务价格信息</h4>
				</div>
				
				<form class="form-horizontal" id="priceForm" method="post">
					<div class="modal-body">
						<input type="hidden" id="cid" name="cid">
						<table class="table table-bordered">
				        	<tr>
				        		<th><span style="color: red;">* 服务价格：</span></th>
				          		<td>
									<input type="text" class="form-control" id="price" name="price" required="required">
				           		</td>
				           		<th>服务原价：</th>
				           		<td>
				           			<input type="text" class="form-control" id="oriPrice" name="oriPrice" >
				           		</td>
				       		</tr>
						
				       		<tr>
				        		<th><span style="color: red;">* 服务级别：</span></th>
				          		<td>
									<input type="text" class="form-control" id="level" name="level" required="required">
				           		</td>
				           		<th><span style="color: red;">* 价格单位：</span></th>
				           		<td>
									<form:select class="form-control" id="priceUnit" path="service.unit" items="${applicationScope.service_price_unit_}" itemLabel="value" itemValue="key"/>
				           		</td>
				       		</tr>
				       		
				       		<tr>
				       			<th><span style="color: red;">* 价格类型：</span></th>
				           		<td>
				           			<select class="form-control" id="type" name="type" onchange="updateType();" required="required">
										<option value="">--选择--</option>
										<option value="1">一次性价格</option>
										<option value="0">长期价格</option>
									</select>
				           		</td>
				       		</tr>
				       		
				       		<tr id="is_show">
				           		<th>服务时长：</th>
				           		<td>
				      				<input type="text" class="form-control" id="serviceNum" name="serviceNum" >
				           		</td>
				           		<th>服务单位：</th>
				           		<td>
									<select class="form-control" id="serviceUnit" name="serviceUnit">
										<option value="">--选择--</option>
										<option value="4">年</option>
										<option value="3">月</option>
										<option value="2">周</option>
										<option value="1">天</option>
									</select>
				           		</td>
				       		</tr>
				       		
				       		<tr>
				       			<th>描述：</th>
				       			<td colspan="3">
									<textarea class="form-control" id="desct" maxlength="256" name="desct" placeholder="请输入描述信息"  style="resize: none;"></textarea>
				       			</td>
				       		</tr>
				       		
				       		<tr>
				        		<th colspan="5">
				        			<button type="button" class="btn btn-primary" onclick="savePrice();">保存</button>
									<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
								</th>
				        	</tr>
				   		</table>
					</div>
				</form>
			</div>
		</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	$(function(){
		if("${service}"){
			$("#priceNum").val("${priceNum}");
	        $("#serviceType").val("${service.serviceType}");
	        $("#serviceStatus").val("${service.serviceStatus}");
	        $("#serviceCategory").val("${service.serviceCategory}");
	        $("#serviceStime").val("${service.serviceStime}");
	        $("#serviceEtime").val("${service.serviceEtime}");
	    }
		
		var type = "${servicePrice.type}";
		if(type && type == 0){
	    	$("#is_show").show();
		}else{
			$("#is_show").hide();
		}
		$("#type").val(type);
		$("#priceUnit").val("${servicePrice.unit}");
	});
	
	function updateType(){
		var type = $("#type").val();
		if(type && type == 0){
	    	$("#is_show").show();
		}else{
			$("#is_show").hide();
		}
	}
	
	//打开新增弹出层
	function addServicePrice(){
		dataClear();
		updateType();
		$("#addPrice").modal("show");
	}
	
	//新增服务价格保存
	function savePrice(){
		if(!$("#price").val()){
			layer.msg('服务价格不能为空!');
			return ;
		}
		if(!$("#level").val()){
			layer.msg('服务级别不能为空!');
			return ;
		}
		if(!$("#priceUnit").val()){
			layer.msg('价格单位不能为空!');
			return ;
		}
		if(!$("#type").val()){
			layer.msg('服务类型不能为空!');
			return ;
		}
		var params = {
				cid : $("#cid").val(),
				serviceId : $("#serviceId").val(),
				price : $("#price").val(),
				oriPrice : $("#oriPrice").val(),
			   	level : $("#level").val(),
			   	unit : $("#priceUnit").val(),
			   	type : $("#type").val(),
			    serviceNum : $("#serviceNum").val(),
			    serviceUnit : $("#serviceUnit").val(),
			    desct : $("#desct").val()
		};
		
		$.ajax({
            type : "post",
            url : '<%=contextPath%>/servicePrice/save',
            data : params,
           	dataType:'json',
            success : function(data) {
            	if(data.state == '1'){
            		$("#priceNum").val(parseInt($("#priceNum").val())+1);
            		showPrice(data.msg);
    				$("#addPrice").modal("hide");
                    layer.msg('保存成功', { anim: 6 });
            	}else{
    				$("#addPrice").modal("hide");
                    layer.msg('保存失败', { anim: 6 });
            	}
            },
            error : function() {
                layer.msg('保存失败', { anim: 6 });
            }
        });
	};
	
	//填充服务价格div标签内容
	function showPrice(obj){
		$("#showPrice").empty();
    	var strHtml=""; 
    	for(var i=0; i<obj.length;i++){
    		strHtml +=  "<div class='alert alert-gray alert-dismissible pull-left' role='alert'>"
    		+"<button type='button' class='close' data-dismiss='alert' aria-label='Close'>"
    		+"<span aria-hidden='true' onclick='delPrice("+obj[i].cid+")' >&times;</span></button>"
    		+"<strong><a onclick='updatePrice("+obj[i].cid+")'><span>"+obj[i].priceDetails+"</span></a></strong></div>"; 
    	}
    	$("#showPrice").append(strHtml);
    	dataClear();
	}
	
	function dataClear(){
		//清空价格表单数据
    	$("#cid").val(""),
    	$("#price").val("");
    	$("#oriPrice").val("");
    	$("#level").val("");
    	$("#priceUnit").val("");
    	$("#type").val("");
    	$("#serviceNum").val("");
    	$("#serviceUnit").val("");
    	$("#desct").val("");
	}
	
	function updatePrice(cid){
		$("#addPrice").modal("show");
		$.ajax({
            type : "post",
            url : '<%=contextPath%>/servicePrice/queryByCid',
            data : {cid : cid},
           	dataType:'json',
            success : function(data) {
            	$("#cid").val(data.cid);
            	$("#price").val(data.price);
            	$("#oriPrice").val(data.oriPrice);
            	$("#level").val(data.level);
            	$("#priceUnit").val(data.unit);
            	$("#type").val(data.type);
            	$("#serviceNum").val(data.serviceNum);
            	$("#serviceUnit").val(data.serviceUnit);
            	$("#desct").val(data.desct);
            	updateType();
            },
            error : function() {
                layer.msg('加载失败', { anim: 6 });
            }
        });
		
	}
	
	//删除服务价格
	function delPrice(cid){
		var serviceId = $("#serviceId").val();
		$.ajax({
            type : "post",
            url : '<%=contextPath%>/servicePrice/delPrice',
            data : {cid : cid, serviceId : serviceId},
           	dataType:'json',
            success : function(data) {
            	if(data.state == '1'){
            		$("#priceNum").val(parseInt($("#priceNum").val())-1);
            		showPrice(data.msg);
                    layer.msg('删除成功', { anim: 6 });
            	}else{
                    layer.msg('删除失败', { anim: 6 });
            	}
            },
            error : function() {
                layer.msg('删除失败', { anim: 6 });
            }
        });
	}

	$("#serviceStime").datetimepicker({
	    format : "hh:ii",
	    startView : 1
	});
	
	$("#serviceEtime").datetimepicker({
	    format : "hh:ii",
	    startView : 1
	});
	
	var um = UM.getEditor('myEditor');
	if("${service}"!=""){
	    $("#content").val(um.getContent());
	}
    um.addListener('blur',function(){
        $("#content").val(um.getContent());
    });
	
	$("#submitBtn").click(function() {
		var priceNum = $("#priceNum").val();
		if(priceNum > 0){
			validata();
		}else{
			layer.msg("服务价格不能为空");
			return false;
		}
	});
	
	function validata() {
		$("#serviceForm").validate({
			rules : {
				providerId : "required",
				serviceName :  {
	            	required:true,
	            	isNotEmpty : true
	            },
				serviceCategory : "required",
				serviceType : "required",
				serviceStatus : "required",
				score : "number"
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
                    	  showImage();//局部刷新
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
	
	$("input[id=titleImgFile]").change(function(){
		var filePath = document.getElementById("titleImgFile").value;
	    var suffix = filePath.toLowerCase().split('.');//以“.”分隔上传文件字符串
	    if(suffix[suffix.length-1]=='jpg'||suffix[suffix.length-1]=='jpeg'||suffix[suffix.length-1]=='png'||suffix[suffix.length-1]=='gif'){
	        var fileSize =  document.getElementById("titleImgFile").files[0].size;
            if(fileSize>1024*1024*5){
                layer.msg("文件过大,请将大小控制在5M以内");
                $("#titleImgFile").val("");
                return false;
            }
	    }else{
	        layer.msg("请选择格式为*.jpg、*.jpeg、*.png、*.gif 的文件");//jpg和jpeg格式是一样的只是系统Windows认jpg，Mac OS认jpeg，
	        $("#titleImgFile").val("");
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
        server: "<%=contextPath%>/upload/img?module_code=service" + param,
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
    uploader.on("fileQueued", function(file) {
        var fileName = file.name.substring(0,4)+'*'+file.name.substring(file.name.lastIndexOf('.'));
        var $li = $(
                '<div id="' + file.id + '" class="file-item thumbnail" style="width:120px;height:90px;float:left;margin-left:10px;">' +
                	'<img>' +
                	'<div class="info">' + fileName + '<a id="removeFile" href="javascript:;" style="float:right;">取消</a>'+'</div>' +
                '</div>'
        ),
        $img = $li.find("img");
        $li.on("click", "#removeFile", function() {
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
        setTimeout(function(){  //使用  setTimeout（）方法设定定时1500毫秒
        	showImage();//局部刷新
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
    
    function resetClick(){
    	uploader.reset();
        $list.empty();
        $(".fileInfo").text("");
        $("#progress").css("display","none");
    }
    
    function setState(val){
        if ( val === state ) {
            return;
        }
        $uploadBtn.removeClass( 'state-' + state );
        $uploadBtn.addClass( 'state-' + val );
        state = val;
    }
    
    
    //二级联动查询
    function getServiceTypeItem(){
    	var value  = $("#serviceCategory").val();
    	$.ajax({
			cache : true,
            type : "post",
            url : '<%=contextPath%>/dict/getTypeItem',
            data : { parentValue : value, type : "service_category" },
            async : false,
            dataType : "json",
            traditional : true,
            success : function(data) {
            	 $("#serviceType").empty();
            	for(var i=0;i<data.length;i++){
            		 $("#serviceType").append("<option value='"+data[i].dictValue+"'>"+data[i].dictKey+"</option>"); // 1.为Select追加一个Option(下拉项)    
            	}
          	},
            error : function() {
            	layer.msg('服务类型加载失败', { anim: 6 });
          	}
        });
    }
    
    function showImage(){
    	$.ajax({
			cache : true,
            type : "post",
            url : '<%=contextPath%>/ywImage/showImage?uid='+$("#serviceId").val()+'&type=service',
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
</html>