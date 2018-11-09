<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运营情况报表页面</title>
</head>
<body>
<div class="mg15 txq_main">
	<form id="pageForm"  name="pageForm" action="${ctx}/manage/userOperation/list" method="post">
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
	        	<input name="notAllZero" value="1" <c:if test="${notAllZero=='1'}">checked="checked"</c:if>  type="checkbox" class="inp focus"/>
	        	不全为零
	        </p>
	        <p class="fl"><input type="button" onclick="search()" class="btn_blue" value="查询"/></p>
	    </div>
    </div>
    <div class="mgtb10">
		<div class="base_title"><strong>教学助手${systemDate }运营情况报表</strong></div>
		<div class="clearfix mgtb10">
			<input type="button" class="btn_blue" value="导出EXCEL报表" onclick="exportexcel()"/>
		</div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
			<tr>
				<th width="5%" rowspan="2">序号</th>
				<th width="9%" rowspan="2">教师</th>
				<th width="10%" rowspan="2">账号</th>
				<th width="10%" rowspan="2" class="order" onclick="orderBy('logintimes')">
					<span <c:if test="${orderField=='logintimes' }">class="selected"</c:if>>
						登录次数<br>(教学助手)
						<c:choose>
							<c:when test="${orderField=='logintimes' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='logintimes' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="10%" rowspan="2" class="order" onclick="orderBy('lastlogintime')">
					<span <c:if test="${orderField=='lastlogintime' }">class="selected"</c:if>>
						最后登录时间<br>(教学助手)
						<c:choose>
							<c:when test="${orderField=='lastlogintime' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='lastlogintime' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="6%" rowspan="2" class="order" onclick="orderBy('coursecount')">
					<span <c:if test="${orderField=='coursecount' }">class="selected"</c:if>>
					我的资源总数<c:choose>
							<c:when test="${orderField=='coursecount' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='coursecount' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="6%" rowspan="2" class="order" onclick="orderBy('guidancecount')">
					<span <c:if test="${orderField=='guidancecount' }">class="selected"</c:if>>
					我的导学总数
					<c:choose>
							<c:when test="${orderField=='guidancecount' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='guidancecount' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="6%" rowspan="2" class="order" onclick="orderBy('preparationcount')">
					<span <c:if test="${orderField=='preparationcount' }">class="selected"</c:if>>
					我的课件总数
					<c:choose>
							<c:when test="${orderField=='preparationcount' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='preparationcount' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="15%" colspan="2">我的课堂</th>
				<th width="6%" rowspan="2" class="order" onclick="orderBy('examcount')">
					<span <c:if test="${orderField=='examcount' }">class="selected"</c:if>>
					我的习题总数
					<c:choose>
							<c:when test="${orderField=='examcount' && orderState=='asc' }">
								<img src="${ctx}/images/order_asc.png">
							</c:when>
							<c:when test="${orderField=='examcount' && orderState=='desc' }">
								<img src="${ctx}/images/order_desc.png">
							</c:when>
							<c:otherwise>
								<img src="${ctx}/images/order.png">
							</c:otherwise>
						</c:choose>
					</span>
				</th>
				<th width="6%" rowspan="2" class="order" onclick="orderBy('homeworkcount')">
					<span <c:if test="${orderField=='homeworkcount' }">class="selected"</c:if>>
					我的作业总数
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
				<th width="11%" rowspan="2">操作</th>
		    </tr>
		    <tr>
		    	<th width="7%">板书文件数</th>
		    	<th width="8%">课堂实录数</th>
		    </tr>
		    <c:if test="${fn:length(list) < 1}">
		    	<tr>
		    		<td colspan="13" style="color: red">暂无数据</td>
		    	</tr>
		    </c:if>
		    <c:if test="${fn:length(list) >= 1}">
			<c:forEach items="${list}" var="obj" varStatus="status">
			    <tr>
					<td>${status.count }</td>
					<td>${obj.username }</td>
					<td>${obj.usercode }</td>
					<td>${obj.logintimes }</td>
					<td>${obj.lastlogintime }</td>
					<td>${obj.coursecount }</td>
					<td>${obj.guidancecount }</td>
					<td>${obj.preparationcount }</td>
					<td>${obj.writingcount }</td>
					<td>${obj.classroomcount }</td>
					<td>${obj.examcount }</td>
					<td>${obj.homeworkcount }</td>
					<td><a href="javascript:void(0);" onclick="detail('${obj.id}')">详情</a></td>
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
				action="${ctx}/manage/userOperation/list"></newTag:page>
		</c:if>
	</div>
	</form>
</div>

<!--详情-->
<div class="popup jumpBox detail dis_none" style="width: 850px;">
    <div class="tit"><span class="fl">详情</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
    	<div class="base_title"><strong>基本信息</strong></div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="10%" align="right">教师姓名：</td>
               <td width="23%" align="left"><span id="username"></span></td>
               <td width="10%" align="right">用户账号：</td>
               <td width="23%" align="left"><span id="usercode"></span></td>
               <td width="10%" align="right">性别：</td>
               <td width="24%" align="left"><span id="gender"></span></td>
           </tr>
           <tr>
               
               <td align="right">年龄：</td>
               <td align="left"><span id="birthday"></span></td>
               <td align="right">联系电话：</td>
               <td align="left"><span id="mobnum"></span></td>
               <td align="right">联系邮箱：</td>
               <td align="left"><span id="email"></span></td>
           </tr>
            <tr>
               <td align="right">所属区域：</td>
               <td align="left"><span id="areaname"></span></td>
               <td align="right">所属学校：</td>
               <td align="left" colspan="3"><span id="organame"></span></td>
           </tr>
       </table>
       <div class="base_title"><strong>使用详情</strong></div>
		<table class="small_space data_list" width="100%" style="border-collapse:collapse;">
           <tr>
               <td width="15%" align="right">【登录次数】</td>
               <td width="18%" align="left"><span id="logintimes"></span></td>
               <td width="15%" align="right">【最后登录时间】</td>
               <td width="18%" align="left"><span id="lastlogintime"></span></td>
               <td width="15%" align="right">【我的资源总数】</td>
               <td width="19%" align="left"><span id="coursecount"></span></td>
           </tr>
           <tr>
           	   <td align="right">【我的导学总数】</td>
               <td align="left"><span id="guidancecount"></span></td>
               <td align="right">【我的课件总数】</td>
               <td align="left"><span id="preparationcount"></span></td>
               <td align="right">【我的课堂】</td>
               <td align="left">板书：<span id="writingcount"></span>，实录：<span id="classroomcount"></span></td>
           </tr>
           <tr>
           	   <td align="right">【我的习题总数】</td>
               <td align="left"><span id="examcount"></span></td>
               <td align="right">【我的作业总数】</td>
               <td align="left"><span id="homeworkcount"></span></td>
               <td align="right">【我的数字教材数】</td>
               <td align="left"><span id="bookcount"></span></td>
           </tr>
            <tr>
               <td align="right">【我的收藏数】</td>
               <td align="left"><span id="collectcount"></span></td>
               <td align="right">【我的下载数】</td>
               <td align="left"><span id="duwnloadcount"></span></td>
               <td align="right">【我的上传数】</td>
               <td align="left"><span id="uploadcount"></span></td>
           </tr>
       </table>
    </div>
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

var dataArray = eval('('+'${jsonList}'+')');

function getObjById(id){
	for(var i=0;i<dataArray.length;i++){
		if(id==dataArray[i].id){
			return dataArray[i];
		}
	}
}

function detail(id){
	var obj = getObjById(id);
	for(var key in obj){
	    if($("#"+key)!=null){
	    	$("#"+key).text(obj[key]);
	    }
	}
	$(".detail").jumpBox(true);
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
			$('#city').html('<option value="">全部</option>');
			$('#area').html('<option value="">全部</option>');
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
			$('#area').html('<option value="">全部</option>');
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