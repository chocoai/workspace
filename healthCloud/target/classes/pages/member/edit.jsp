<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<% String contextPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
<jsp:include page="/pages/base/base.jsp"></jsp:include>
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<script src="<%=contextPath%>/res/js/login/jquery.validate.method.min.js"></script>
<style type="text/css">
.myformnew table { margin-bottom:0;}
</style>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
	<div class="row-fluid">
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:member:list">
						<li class="active"><a href="<%=contextPath%>/member/list?status=0" target="_self"><span>会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:disable:list">
						<li><a href="<%=contextPath%>/member/list?status=1" target="_self"><span>禁用会员列表</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:member:unbind:list">
						<li><a href="<%=contextPath%>/member/unbandList" target="_self"><span>未绑定会员列表</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-left: 0px; padding-right: 0px;">
		
			<ul class="nav nav-tabs" style="margin-bottom: 10px;">
				<li><a href="list?status=0">会员列表</a></li>
				<li class="active"><a href="#">编辑会员信息</a></li>
			</ul>
			
			<div class="col-sm-12" style="padding-left: 0px; padding-right: 0px;">
				<form class="form-horizontal myformnew" action="save" id="memberForm" method="post" enctype="multipart/form-data">
		    	<input type="hidden" id="memberId" name="memberId" value="${member.memberId}" >
		    	<input type="hidden" name="memberInfo.cid" value="${member.memberInfo.cid}" >
		      	<div class="form-gd-hd">会员展示信息</div>
	      		<table class="table table-bordered">
	          		<tr>
	          			<th>所属机构：</th>
	               		<td>
							<input type="text" class="form-control" id="orgName" name="orgName" value="${member.org.orgName}" readonly="readonly">
	               		</td>
	               		<th>登录账号：</th>
	               		<td>
							<input type="text" class="form-control" id="userCode" name="userCode" value="${member.user.userCode}" readonly="readonly">
	               		</td>
	          		</tr>
	          		
	          		<tr>
			       		<th>员工姓名：</th>
	               		<td>
							<input type="text" class="form-control" id="empName" name="empName" value="${member.empName}" readonly="readonly">
						</td>
		           		<th>医师姓名：</th>
		                <td>
							<input type="text" class="form-control" id="docName" name="docName" value="${member.docName}" readonly="readonly">
						</td>
					</tr>
					
	          		<tr>
	          			<th>会员类型：</th>
	               		<td>
							<select id="memberType" name="memberType" class="form-control" disabled="disabled">
								<c:if test="${member.memberType == 0}">
									<option value="0">普通会员</option>
								</c:if>
								<c:if test="${member.memberType == 1}">
									<option value="1">VIP会员</option>
								</c:if>
							</select>
	               		</td>
	               		<th>会员状态：</th>
	               		<td>
							<select id="status" name="status" class="form-control" disabled="disabled">
								<c:if test="${member.status == 0}">
									<option value="0">正常</option>
								</c:if>
								<c:if test="${member.status == 1}">
									<option value="1">禁用</option>
								</c:if>
							</select>
	               		</td>
	          		</tr>
			       	
	      		</table>
		      	
		      	<div class="form-gd-hd">会员编辑信息</div>
	      		<table class="table table-bordered">
	      			<tr>
		           		<th><span style="color: red;">* 会员姓名：</span></th>
		                <td>
		                	<input type="text" class="form-control" id="realName" name="realName" value="${member.realName}" >
						</td>
						<th>会员昵称：</th>
	               		<td>
	               			<input type="text" class="form-control" id="nickName" name="nickName" value="${member.nickName}" >
	               		</td>
			      	</tr>
	          		<tr>
	          			<th><span style="color: red;">* 身份证号：</span></th>
	               		<td>
	               			<input type="text" class="form-control" id="identityCard" name="identityCard" value="${member.identityCard}" required="required" >
	               		</td>
	               		<th><span style="color: red;">* 手机号码：</span></th>
	               		<td>
	               			<input type="text" class="form-control" id="phoneNo" name="phoneNo" value="${member.phoneNo}" required="required" >
	               		</td>
	          		</tr>
	          		
	          		<tr>
	          			<th>会员性别：</th>
	               		<td>
	               			<select id="gender" name="gender" class="form-control">
								<option value="">--请选择--</option>
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
	               		</td>
	               		<th>电子邮箱：</th>
	               		<td>
							<input type="text" class="form-control" id="email" name="email" value="${member.email}" >
	               		</td>
	          		</tr>
	          		
		         	<tr>
	                	<th>备注：</th>
	                	<td colspan="3">
	                		<textarea class="form-control" id="remark" name="remark" style="resize: none;"  rows="" cols="" >${member.remark}</textarea>
	                	</td>
		         	</tr>
	      		</table>
	      		
	      		<div class="form-gd-hd">会员详细信息</div>
	          	<table class="table table-bordered">
	          		<tr>
	              		<th>民族：</th>
		                <td>
		                	<input type="text" class="form-control" id="nation" name="memberInfo.nation" value="${member.memberInfo.nation }" >
		                </td>
		                <th>籍贯：</th>
		                <td>
		                	<input type="text" class="form-control" id="nativePlace" name="memberInfo.nativePlace" value="${member.memberInfo.nativePlace }" >
		                </td>
	              	</tr>
	              	<tr>
	              		<th>政治面貌：</th>
	               		<td>
	                 		<select class="form-control" id="politicsStatus" name="memberInfo.politicsStatus" >
								<option value="">--请选择--</option>
								<option value="1">群众</option>
								<option value="2">团员</option>
								<option value="3">党员</option>
			      			</select>
	               		</td>
	               		<th>文化程度：</th>
		               	<td>
		                 	<select class="form-control" id="degree" name="memberInfo.degree" >
								<option value="">--请选择--</option>
								<option value="1">大专及大专以下</option>
								<option value="2">本科</option>
								<option value="3">硕士</option>
								<option value="4">博士</option>
					  		</select>
		               	</td>
	             	</tr>
	             	<tr>
		               	<th>老人类别：</th>
		               	<td>	
		               		<select class="form-control" id="memberInfoType" name="memberInfo.memberType"  >
								<option value="">--请选择--</option>
								<option value="1">普通老人</option>
								<option value="2">退休工人</option>
								<option value="3">退休干部</option>
								<option value="4">退休军人</option>
								<option value="5">其他</option>
							</select>
						</td>
						<th>婚姻状况：</th>
		               	<td>
		                 	<select class="form-control" id="marriageStatus" name="memberInfo.marriageStatus" >
								<option value="">--请选择--</option>
								<option value="1">未婚</option>
								<option value="2">有配偶</option>
								<option value="3">丧偶</option>
								<option value="4">离婚</option>
					  		</select>
		               	</td>
	             	</tr>
	              	 
	              	<tr>
	              		<th>居住状况：</th>
	                	<td>
	                  		<select class="form-control" id="liveStatus" name="memberInfo.liveStatus" >
								<option value="">--请选择--</option>
								<option value="1">与子女合住</option>
								<option value="2">独居</option>
								<option value="3">养老院</option>
								<option value="4">其他</option>
					  		</select>
	                	</td>
	                	<th>经济来源：</th>
	                	<td>
	                		<select class="form-control" id="moneySource" name="memberInfo.moneySource" >
	                			<option value="">--请选择--</option>
								<option value="1">退休工资</option>
								<option value="2">养老基金</option>
								<option value="3">子女供养</option>
								<option value="4">其他</option>
							</select>
						</td>
	              	</tr>
	              	
	              	<tr>
	                	<th>原单位：</th>
	                	<td>
	                  		<input type="text" class="form-control" id="company" name="memberInfo.company" value="${member.memberInfo.company }" />
	                	</td>
	                	<th>职称：</th>
	                	<td>
	                		<input type="text" class="form-control" id="professionalTitle" name="memberInfo.professionalTitle" value="${member.memberInfo.professionalTitle }" >
	                	</td>
	              	</tr>
	              	<tr>
		               	<th>月收入：</th>
	                	<td>
	                		<input type="text" class="form-control" id="salary" name="memberInfo.salary" value="${member.memberInfo.salary }" >
	                	</td>
	                	<th>宗教信仰：</th>
	               		<td>
	                 		<input type="text" class="form-control" id="religion" name="memberInfo.religion" value="${member.memberInfo.religion }" >
	               		</td>
	              	</tr>
	              	
	              	<tr>
	                	<th>详细地址：</th>
	                	<td colspan="3">
	                		<textarea class="form-control" id="address" name="memberInfo.address" style="resize: none;"  >${member.memberInfo.address}</textarea>
	                	</td>
		         	</tr>
				</table>
				<div class="form-gd-hd">健康医疗信息</div>
	       		<table class="table table-bordered">
	           		<tr>
	           			<th>社保号：</th>
	               		<td>
	                 		<input type="text" class="form-control" id="securityNum" name="memberInfo.securityNum" value="${member.memberInfo.securityNum }" >
	               		</td>
	              		<th>医疗保障：</th>
	               		<td>
	               			<input type="text" class="form-control" id="guarantee" name="memberInfo.guarantee" value="${member.memberInfo.guarantee }" >
	               		</td>
	            	</tr>
	            	<tr>
	            		<th>血型：</th>
	               		<td>
	               			<select class="form-control" id="bloodType" name="memberInfo.bloodType" >
	               				<option value="">--请选择--</option>
								<option value="1">A型</option>
								<option value="2">B型</option>
								<option value="3">O型</option>
								<option value="4">AB型</option>
								<option value="5">其他</option>
				  			</select>
	               		</td>
	           			<th>慢性病：</th>
	               		<td>
	                 		<input type="text" class="form-control" id="disease" name="memberInfo.disease" value="${member.memberInfo.disease }" >
	               		</td>
	            	</tr>
	            	<tr>
	              		<th>失能情况：</th>
	               		<td>
	               			<select class="form-control" id="disability" name="memberInfo.disability" >
		                 		<option value="">--请选择--</option>
								<option value="1">健康</option>
								<option value="2">失能</option>
								<option value="3">半失能</option>
				   	  		</select>
	               		</td>
	               		<th>残疾情况：</th>
	           			<td>
	                 		<input type="text" class="form-control" id="deformity" name="memberInfo.deformity" value="${member.memberInfo.deformity }" >
	               		</td>
	           		</tr>
	           		<tr>
	              		<th>服务类型：</th>
	               		<td>
		                 	<select class="form-control" id="serviceType" name="memberInfo.serviceType" >
		                 		<option value="">--请选择--</option>
								<option value="1">无偿服务</option>
								<option value="2">低偿服务</option>
								<option value="3">有偿服务</option>
								<option value="4">社会力量认购服务</option>
								<option value="5">义工服务</option>
								<option value="6">残联购买</option>
								<option value="7">政府购买</option>
				   	  		</select>
	               		</td>
	   				</tr>
	       		</table>
	       		
	       		<div class="form-gd-hd">亲属信息</div>
	       		<table id="table" class="table table-bordered table-responsive" style="text-align:center;">
	          		<thead style="background:#ebf8f1;">
	          			<tr>
		          			<th colspan=1>类型</th>
		          			<th colspan=1>姓名</th>
		          			<th colspan=1>关系</th>
		          			<th colspan=1>联系电话</th>
		          			<th colspan=1>家庭地址</th>
		          			<th colspan=1><a class="btn btn-info btn-xs" onclick="addHtml();">添加</a></th>
	          			</tr>
		          	</thead>
		          	<tbody>
	          	 	<c:forEach items="${member.contactList }" var="memberContact" varStatus="status">
		          		<tr>
		          			<td>
			          			<select class="form-control input-sm" name="contactList[${ status.index }].type" style="width:90px;">
									<option <c:if test="${memberContact.type=='1' }">selected="selected"</c:if> value="1">监护人</option>
									<option <c:if test="${memberContact.type=='2' }">selected="selected"</c:if> value="2">紧急联系人</option>
							  	</select>
		          			</td>
		          			<td>
		          				<input type="text" class="form-control" name="contactList[${ status.index }].name" value="${memberContact.name}" >
		          			</td>
		          			<td>
		          				<input type="text" class="form-control" name="contactList[${ status.index }].relation" value="${memberContact.relation}" >
		          			</td>
		          			<td>
		          				<input type="text" class="form-control" name="contactList[${ status.index }].telphone" value="${memberContact.telphone}" >
		          			</td>
		          			<td>
		          				<input type="text" class="form-control" name="contactList[${ status.index }].address" value="${memberContact.address}" >
		          			</td>
		          			<td>
		          				<input type="hidden" name="contactList[${ status.index }].memberId" value="${memberContact.memberId}" >
		          				<a class="btn btn-warning btn-xs" href="javascript:;" onclick="del(${ status.index })">删除</a>
		          			</td>
	          			</tr>
	          	 	</c:forEach>
          			</tbody>
          			<tr>
		         		<th colspan="6">
		         			<button type="submit" class="btn btn-primary">保存</button>&nbsp;
							<a class="btn btn-default" href="list?status=0">取消</a>
						</th>
		         	</tr>
	          	</table>
	    	</form>
	   		</div>
	   	</div>
   	</div>
</div>
<script type="text/javascript" src="<%=contextPath%>/res/js/jimmy.common.js"></script>
<script type="text/javascript">
	$(function(){
		$("#gender").val("${member.gender}");
		$("#politicsStatus").val("${member.memberInfo.politicsStatus}");
		$("#degree").val("${member.memberInfo.degree}");
		$("#memberInfoType").val("${member.memberInfo.memberType}");
		$("#marriageStatus").val("${member.memberInfo.marriageStatus}");
		$("#liveStatus").val("${member.memberInfo.liveStatus}");
		$("#moneySource").val("${member.memberInfo.moneySource}");
		$("#bloodType").val("${member.memberInfo.bloodType}");
		$("#disability").val("${member.memberInfo.disability}");
		$("#serviceType").val("${member.memberInfo.serviceType}");
		
		$("#memberForm").validate({
			rules : {
				realName :  {
	            	required:true,
	            	isNotEmpty : true
	            },
				email : {
                    email:true, 
                },
                identityCard : {
                	card : true,
                },
                phoneNo : {
                	isMobile : true,
                }
			},
            errorPlacement:function(error,element) {
                layer.tips($(error).text(), element, {
                	tips: 3,
                	tipsMore: true
                });
            }
		});
	});
	
	function addHtml(){
	 	var index =$("#table").find("tr").length - 2;
		$("#table").append(
				'<tr><td>'+
						'<select class="form-control input-sm" name="contactList['+index+'].type" style="width:90px;">'+
							'<option <c:if test="${memberContact.type=='1' }">selected="selected"</c:if> value="1">监护人</option>'+
							'<option <c:if test="${memberContact.type=='2' }">selected="selected"</c:if> value="2">紧急联系人</option>'+
						'</select></td>'+
					'<td><input type="text" class="form-control" name="contactList['+index+'].name"  ></td>'+
					'<td><input type="text" class="form-control" name="contactList['+index+'].relation" ></td>'+
					'<td><input type="text" class="form-control" name="contactList['+index+'].telphone" ></td>'+
					'<td><input type="text" class="form-control" name="contactList['+index+'].address" ></td>'+
					'<td>'+
						'<input type="hidden" name="contactList['+index+'].memberId" value="${member.memberId }" >'+
						'<a class="btn btn-warning btn-xs" href="javascript:;" onclick="del('+index+')">删除</a>'+
					'</td></tr>');
	}
	
	function del(index){
		var table = document.getElementById("table");
	    table.deleteRow(index+1);
	}
	
</script>
</body>
</html>