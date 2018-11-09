<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>作业情况报表页面</title>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/userOperation/homework" method="post">
	<input type="hidden" id="search_type" name="search_type"/>
	<input type="hidden" id="areaid" value="${areaid}"/>
	<input type="hidden" id="orderField" name="orderField" value="${orderField}"/>
	<input type="hidden" id="orderState" name="orderState" value="${orderState}"/>
	
    <div class="mgtb10 txq_txtbox clearfix">
    	<div class="mgt10 clearfix">
	        <p class="fl mgr10"><span>所属平台：</span>
	        	<select name="platformcode" class="sel" style="width:150px;">
					<option value="" <c:if test="${empty platformcode}">selected="selected"</c:if>>全部</option>
					<c:forEach items="${platformList}" var="p" varStatus="st">
					<option value="${p.platform_code }" <c:if test="${p.platform_code==platformcode}">selected="selected"</c:if>>${p.platform_name }</option>
					</c:forEach>
				</select>
	        </p>
	        <p class="fl mgr10"><span>所属区域：</span>
	        	<select id="province" name="provinceCode" class="sel mgr10" style="width:150px;">
					<option value="" <c:if test="${empty provinceCode}">selected="selected"</c:if>>全部</option>
					<c:if test="${not empty provinceList}">
					<c:forEach items="${provinceList}" var="a" varStatus="st">
					<option value="${a.areaCode }" <c:if test="${a.areaCode==provinceCode}">selected="selected"</c:if>>${a.areaName }</option>
					</c:forEach>
					</c:if>
				</select>
				<select id="city" name="cityCode" class="sel mgr10" style="width:150px;">
					<option value="" <c:if test="${empty cityCode}">selected="selected"</c:if>>全部</option>
					<c:if test="${not empty cityList}">
					<c:forEach items="${cityList}" var="a" varStatus="st">
					<option value="${a.areaCode }" <c:if test="${a.areaCode==cityCode}">selected="selected"</c:if>>${a.areaName }</option>
					</c:forEach>
					</c:if>
				</select>
				<select id="area" name="areaCode" class="sel" style="width:150px;">
					<option value="" <c:if test="${empty areaCode}">selected="selected"</c:if>>全部</option>
					<c:if test="${not empty areaList}">
					<c:forEach items="${areaList}" var="a" varStatus="st">
					<option value="${a.areaCode }" <c:if test="${a.areaCode==areaCode}">selected="selected"</c:if>>${a.areaName }</option>
					</c:forEach>
					</c:if>
				</select>
	        </p>
	        <p class="fl mgr10"><span>所属学校：</span>
	        	<input name="organame" value="${organame }" type="text" style="width:150px;" class="inp focus"/>
	        </p>
	    </div>
	    <div class="mgt10 clearfix">
	        <p class="fl mgr10"><span>教师姓名：</span>
	        	<input name="username" value="${username }" type="text" style="width:150px;" class="inp focus"/>
	        </p>
	        <p class="fl mgr10"><span>教师账号：</span>
	        	<input name="usercode" value="${usercode }" type="text" style="width:150px;" class="inp focus"/>
	        </p>
	        <p class="fl mgr10"><span>筛选：</span>
	        	<input name="notAllZero" value="2" <c:if test="${notAllZero=='2'}">checked="checked"</c:if>  type="checkbox" class="inp focus"/>
	        	不全为零
	        </p>
	        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
	    </div>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>教学助手${systemDate }作业使用情况报表</strong></div>
		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="导出EXCEL报表" onclick="exportexcel()"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%">序号</th>
				<th width="10%">教师</th>
				<th width="10%">账号</th>
				<th width="15%" class="order" onclick="orderBy('homeworkcount')">
					<span <c:if test="${orderField=='homeworkcount' }">class="selected"</c:if>>
					布置作业次数
					<c:choose>
							<c:when test="${orderField=='homeworkcount' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='homeworkcount' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="15%" class="order" onclick="orderBy('hwlastdate')">
					<span <c:if test="${orderField=='hwlastdate' }">class="selected"</c:if>>
					最后布置时间
					<c:choose>
							<c:when test="${orderField=='hwlastdate' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='hwlastdate' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="15%" class="order" onclick="orderBy('hwpartakenum')">
					<span <c:if test="${orderField=='hwpartakenum' }">class="selected"</c:if>>
					已参与人数
					<c:choose>
							<c:when test="${orderField=='hwpartakenum' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='hwpartakenum' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="15%" class="order" onclick="orderBy('hwreceivenum')">
					<span <c:if test="${orderField=='hwreceivenum' }">class="selected"</c:if>>
					覆盖人数<br>(收到作业消息总人数)
					<c:choose>
							<c:when test="${orderField=='hwreceivenum' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='hwreceivenum' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="15%">批注作业次数</th>
		    </tr>
		    <c:if test="${fn:length(list) < 1}">
		    	<tr>
		    		<td colspan="8" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:if test="${fn:length(list) >= 1}">
			<c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td>${status.count }</td>
					<td>${obj.username }</td>
					<td>${obj.usercode }</td>
					<td>${obj.homeworkcount }</td>
					<td>${obj.hwlastdate }</td>
					<td>${obj.hwpartakenum }</td>
					<td>${obj.hwreceivenum }</td>
					<td>${obj.hwpostilcount }</td>
			    </tr>
		    </c:forEach>
		    </c:if>
		</table>
    </div>
	<div class="base_page clearfix">
		<!-- 分页 -->
		<c:if test="${not empty page.totalPage && page.totalPage >= 1 }">
				<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
				currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
				action="${ctx}/manage/userOperation/homework"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<script>
//tab
$("div[name='slide']").tabControl("*[name='slideTag'] a" , "*[name='slideCont']" , '.more');
//弹出层
$('*[name="roleChoose"]').each(function(){$(this).click(function(){$(".jumpBox").jumpBox(true);});});

var $ckAll = $("input[name='ckAll']");
var $ckItm = $("input[name='ckItm']");
var len = $ckItm.length;
$ckAll.click(function() {
    $ckItm.prop("checked",this.checked);
});
$ckItm.click(function() {
    var b=$ckItm.filter(":checked").length==len;
    var flag=$ckAll.prop("checked",b?true:false);
});

function search(){
	$("#search_type").val(0);
	$("#pageForm").submit();
}

function orderBy(field){
	if(field==$('#orderField').val()){
		$('#orderState').val($('#orderState').val()=='desc'?'asc':'desc');
	}else{
		$('#orderField').val(field);
		$('#orderState').val('desc');
	}
	$("#search_type").val(0);
	$("#pageForm").submit();
}

//导出
function exportexcel(){
	var platformcode=$('select[name="platformcode"]').val();
	var organame=$('input[name="organame"]').val();
	var username=$('input[name="username"]').val();
	var usercode=$('input[name="usercode"]').val();
	var areaid=$('#areaid').val();
	var orderField=$('#orderField').val();
	var orderState=$('#orderState').val();
	var notAllZero=$('input[name="notAllZero"]:checked').val();
	window.location.href='${exportExcelUrl}'+'?platformcode='+platformcode+'&organame='+organame+
			'&username='+username+'&username='+username+'&areaid='+areaid
			+'&orderField='+orderField+'&orderState='+orderState+'&notAllZero='+notAllZero;
}

$(function(){
	$("#province").bind("change", function(){
		if($("#province").val()==''){
			return;		
		}
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/userOperation/queryArea",
			data : {"parentId" : $("#province").val(),
					"levelId" : 2},
			async : false,
			dataType : 'json',
			success : function(arr) {
				if(arr != null){
					var htmlStr = '<option value="">全部</option>';
					for(var i=0;i<arr.length;i++){
						htmlStr += '<option value="'+arr[i].areaCode+'">'+arr[i].areaName+'</option>';
					}
					$('#city').html(htmlStr);
				}
			}
		});
		$('#area').html('<option value="">全部</option>');
	});
	$("#city").bind("change", function(){
		if($("#city").val()==''){
			return;		
		}
		$.ajax({
			type : "POST",
			url : "${ctx}/manage/userOperation/queryArea",
			data : {"parentId" : $("#city").val(),
					"levelId" : 3},
			async : false,
			dataType : 'json',
			success : function(arr) {
				if(arr != null){
					var htmlStr = '<option value="">全部</option>';
					for(var i=0;i<arr.length;i++){
						htmlStr += '<option value="'+arr[i].areaCode+'">'+arr[i].areaName+'</option>';
					}
					$('#area').html(htmlStr);
				}
			}
		});
	});
});
</script>
</body>
</html>