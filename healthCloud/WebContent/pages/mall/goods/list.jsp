<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%String contextPath = request.getContextPath();%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
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
			<div class="col-md-12" style="padding-right: 0px; padding-top: 10px;">
				<a class="btn btn-info btn-sm" href="edit">+ 新增商品</a>&nbsp;&nbsp;
				<button class="btn btn-info btn-sm" id="batchChangeGoodsStatus">- 下架商品</button>&nbsp;&nbsp;
				<ol class="breadcrumb" style="margin-top: 10px;">
					<form id="goodsForm" action="list" class="form-inline">
						<div class="form-group">
							<label for="">商品名称</label>&nbsp; 
							<input type="text" class="form-control" style="width: 180px;" id="goodsName" name="goodsName" value="${goods.goodsName }" placeholder="请输入商品名称">
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">商品类别</label>&nbsp; 
							<form:select id="goodsCategory" path="goods.goodsCategory" onchange="getGoodsTypeItem();" name="goodsCategory" items="${applicationScope.goods_category__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
						</div>
						<div class="form-group" id="is_show">
							<label for="">商品类型</label>&nbsp; 
							<select id="goodsType" name="goodsType" class="form-control" >
						   		<c:forEach items="${goodsTypeList }" var="list">
						       		<option value="${list.dictValue}" <c:if test="${goods.goodsType==list.dictValue}">selected = "selected"</c:if> >${list.dictKey}</option> 
						   		</c:forEach>
							</select>
						</div>
						&nbsp;&nbsp;
						<div class="form-group">
							<label for="">商品状态</label>&nbsp; 
							<select id="status" name="status" class="form-control">
								<option value="">全部</option>
								<option value="0">正常</option>
								<option value="1">下架</option>
							</select>
						</div>
						&nbsp;&nbsp;
						<button type="submit" class="btn btn-primary">查询</button>
					</form>
				</ol>
			</div>

			<div class="col-md-12" style="padding-right: 0px; margin-top: -20px;">
				<table id="dataTable" style="table-layout: fixed;" class="table table-striped table-hover table-bordered">
					<thead>
						<tr>
							<th style="width: 6%;"><input type="checkbox" id="checkAll"> 选择</th>
							<th style="width: 12%;">商品名称</th>
							<th style="width: 9%;">商品类别</th>
							<th style="width: 12%;">商品类型</th>
							<th style="width: 11%;">商品单价(元)</th>
							<th style="width: 9%;">商品折扣</th>
							<th style="width: 10%;">商品库存量</th>
							<th>商品简介</th>
							<th style="width: 9%;">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${goodsList }" var="goods" varStatus="status">
							<tr>
								<td style="text-align: center;">
									<input type="checkbox" id="check_goods" name="check_goods" value="${goods.goodsId}">
								</td>
								<td>${goods.goodsName }</td>
								<td>${goods.goodsCategoryView }</td>
								<td>${goods.goodsTypeView }</td>
								<td>${goods.unitPrice }</td>
								<td>${goods.discount }</td>
								<td>${goods.stockAmount }</td>
								<td style="overflow:hidden;white-space:nowrap;text-overflow:ellipsis;" title="${goods.goodsIntro }">${goods.goodsIntro }</td>
								<td>
									<a type="button" class="btn btn-primary btn-xs" href="edit?goodsId=${goods.goodsId}">编辑</a> 
									<c:if test="${goods.status == '1' }">
										<button type="button" class="btn btn-info btn-xs" onclick="putAway('0', ${goods.goodsId})">上架</button>
									</c:if>
									<c:if test="${goods.status == '0' }">
										<button type="button" class="btn btn-danger btn-xs" onclick="soldOut('1', ${goods.goodsId})">下架</button>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
    $(function(){
    	if("${goods.goodsCategory}"){
	    	$("#is_show").show();
    	}else{
    		$("#is_show").hide();
    	}
		$("#status").val("${goods.status}");
		
    	if("${state}"=="SUCCESS"){
	        layer.msg("提交成功");
	    }else if("${state}"=="FALSE"){
            layer.msg("提交失败");
		}
    });
	    		
	function putAway(goodsStatus, goodsId){
    	layer.confirm('您确认要上架该商品吗？', {btn: ['确认','取消']}, function(){
    		$.ajax({
    			cache : true,
                type : "post",
                url : 'changeGoodsStatus',
                async : true,
                data : {
                	"goodsId" : goodsId,
                    "goodsStatus" : goodsStatus
             	},
                traditional : true,
                success : function(data) {
                	layer.msg('上架成功', { anim: 6 });
                    setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                    	$("#goodsForm").submit();
                    },2000);
              	},
                error : function() {
                	layer.msg('上架失败', { anim: 6 });
                }
    		});
    	}, function(){
    		return;
    	});
	}
		
    function soldOut(goodsStatus, goodsId){
    	layer.confirm('确认下架该商品吗？下架后用户将不能购买该商品，在再次启用时上架即可', {btn: ['确认','取消']}, function(){
        	$.ajax({
        		cache : true,
                type : "post",
                url : 'changeGoodsStatus',
                async : true,
                data : {
                	"goodsId" : goodsId,
                    "goodsStatus" : goodsStatus
                },
                traditional : true,
                success : function(data) {
                	layer.msg('下架成功', { anim: 6 });
                    setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                    	$("#goodsForm").submit();
                    },2000);
                },
                error : function() {
                	layer.msg('下架失败', { anim: 6 });
                }
           	});
    	}, function(){
    		return;
      	});
	}

	$(document).ready(function() {
		$('#dataTable').dataTable({
			"bProcessing" : true, //DataTables载入数据时，是否显示‘进度’提示
			"searching": false, // 关闭Datatables的搜索功能
			"aLengthMenu" : [ 10, 20, 40 ], //更改显示记录数选项
			"lengthChange": false,//禁用调整显示记录数选项
			"iDisplayLength" : 10, //默认显示的记录数
			"bAutoWidth" : true, //是否自适应宽度
			"bScrollCollapse" : true, //是否开启DataTables的高度自适应，当数据条数不够分页数据条数的时候，插件高度是否随数据条数而改变  
			"bPaginate" : true, //是否显示（应用）分页器  
			"bInfo" : true, //是否显示页脚信息，DataTables插件左下角显示记录数  
			"sPaginationType" : "full_numbers", //详细分页组，可以支持直接跳转到某页  
			"bSort" : true, //是否启动各个字段的排序功能  
			"aaSorting" : [ [7, "desc" ] ], //默认的排序方式，第2列，升序排列  
			columnDefs:[{
				 orderable:false,//禁用排序
				 targets:[0]   //指定的列
			 }],
			"bFilter" : true, //是否启动过滤、搜索功能  
			"oLanguage" : { //国际化配置  
				"sProcessing" : "正在获取数据，请稍后...",
				"sLengthMenu" : "显示 _MENU_ 条",
				"sZeroRecords" : "没有您要搜索的内容",
				"sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
				"sInfoEmpty" : "记录数为0",
				"sInfoFiltered" : "(全部记录数 _MAX_ 条)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索&nbsp;&nbsp;",
				"sUrl" : "",
				"oPaginate" : {
					"sFirst" : "第一页",
					"sPrevious" : "上一页",
					"sNext" : "下一页",
					"sLast" : "最后一页"
				}
			}
		});
	});

	//全选、取消全选的事件 
    $('#checkAll').click(function() {
    	if ($("#checkAll").prop("checked")) {
        	$(':checkbox').prop("checked", true);
        } else {
        	$(':checkbox').prop("checked", false);
        }
	}); 
       
	//子复选框的事件
    $(":checkbox").click(function() {
    	if (!$("#check_goods").checked) {
        	$("#checkAll").prop("checked",false);
        }
        var ckbLength = $("input[type='checkbox'][id='check_goods']").length;
        var checkedLengtn = $("input[type='checkbox'][id='check_goods']:checked").length;
        if (checkedLengtn == ckbLength) {
        	$("#checkAll").prop("checked",true);
        }
    });
	

	$("#batchChangeGoodsStatus").click(function(){
		var goodsIds = new Array();
		$("input[type='checkbox'][name='check_goods']:checked").each(function(i) {
			goodsIds[i] = $(this).val();
		});
		if(goodsIds.length == 0){
			layer.msg('请先选取要下架的商品', {anim: 6});
			return;
		}
		layer.confirm('确认下架该商品吗？下架后用户将不能购买该商品，在再次启用时上架即可', {btn: ['确认','取消']}, function(){
			$.ajax({
				cache : true,
				type : "post",
				url : 'batchChangeGoodsStatus',
				async : false,
				data : {
					"goodsIds" : goodsIds
				},
				traditional : true,
				success : function(data) {
					layer.msg('下架成功', { anim: 6 });
	                setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
	                	$("#goodsForm").submit();
	                },2000);
				},
				error : function() {
					layer.msg('下架失败', { anim: 6 });
				}
			});
		}, function(){
			return;
		});
	});
	
	 //二级联动下拉框查询
    function getGoodsTypeItem(){
    	var value  = $("#goodsCategory").val();
    	if(value){
    		$("#is_show").show();
    	}else{
    		$("#is_show").hide();
    	}
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
</body>
</html>
