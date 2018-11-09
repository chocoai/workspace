<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
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
<link rel="stylesheet" href="<%=contextPath%>/res/css/jimmy.css" type="text/css">
<style type="text/css">
#treeDemoList.ztree {
	max-height: 200px;
	border: 1px solid #617775;
	background: #f0f6e4;
	overflow-y: scroll;
	overflow-x: auto;
}
</style>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px;padding-right: 0px;">
	<div class="row-fluid" >
		<div id="left" class="collapse in" style="width: 130px;">
			<div id="secondMenu">
				<ul class="nav nav-pills nav-stacked text-center">
					<shiro:hasPermission name="submenu:user:employee:list">
						<li><a href="<%=contextPath%>/employee/list" target="_self"><span>员工管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:user:doctor:list">
						<li class="active"><a href="<%=contextPath%>/doctor/list" target="_self"><span>医师管理</span></a></li>
					</shiro:hasPermission>
					<shiro:hasPermission name="submenu:user:disable:list">
						<li><a href="<%=contextPath%>/employee/list?status=1" target="_self"><span>禁用列表</span></a></li>
					</shiro:hasPermission>
				</ul>
			</div>
		</div>
		<div id="openClose" class="open" data-toggle="collapse" data-target="#left">&nbsp;</div>
		<div id="right" style="padding-top: 0px;padding-left: 0px;padding-right: 0px;margin-left: 0px;margin-right: 0px;">
			
			<ul class="nav nav-tabs" style="margin-bottom: 10px;">
				<li><a href="list">医师列表</a></li>
				<li class="active">
					<c:choose>
						<c:when test="${doc.docId != null }">
							<a href="edit?docId=${doc.docId }">编辑员工</a>
						</c:when>
						<c:otherwise>
							<a href="edit">添加医师</a>
						</c:otherwise>
					</c:choose>
				</li>
			</ul>
			
			<div id="menuContentList" class="menuContent" style="display: none; position: absolute; z-index: 999;">
				<ul id="treeDemoList" class=" ztree" style="margin-top: 0; width: 389px;"></ul>
			</div>
			
			<div class="col-sm-12" style="padding-left: 0px;padding-right: 0px;">
			<form class="form-horizontal pull-left form-normallr" id="docForm" method="post" action="save" enctype="multipart/form-data">
		    	<input type="hidden" id="docId" name="docId" value="${doc.docId }">
		      	<div class="form-gd-hd">医师基本信息</div>
	      		<table class="table table-bordered">
	          		<tr>
	          			<th><span style="color: red;">* 所属机构：</span></th>
	               		<td>
							<input id="citySelList" type="text" class="form-control" readonly onclick="showMenuList(); return false;" value="${doc.org.orgName }"> 
							<input type="hidden" id="orgList" name="orgId" value="${doc.org.orgId }">
	               		</td>
	               		<th><span style="color: red;">* 账号：</span></th>
	               		<td>
							<input id="userCode" type="text" class="form-control" name="userCode" value="${doc.user.userCode }" >
	               		</td>
	          		</tr>
	          		<c:if test="${doc.docId==null }">
	          			<tr>
		          			<th><span style="color: red;">* 输入密码：</span></th>
		               		<td>
		              			<input type="password" id="pwd" name="pwd" class="form-control" >
		               		</td>
		               		<th><span style="color: red;">* 确认密码：</span></th>
		               		<td>
		               			<input type="password" id="repwd" name="repwd" class="form-control">
		               		</td>
	          			</tr>
	          		</c:if>
	          		
			       	<tr>
			       		<th><span style="color: red;">* 姓名：</span></th>
	               		<td>
							<input type="text" id="realName" class="form-control" name="realName" value="${doc.realName}" >
						</td>
		           		<th><span style="color: red;">* 性别：</span></th>
		                <td>
		                	<div class="radio">
		                    	<label>
		                        	<input type="radio" name="sex" value=1 style="margin-bottom:5px;">男
		                      	</label>
	                          	&nbsp;&nbsp;
	                          	<label>
	                              	<input type="radio" name="sex" value=0 style="margin-bottom:5px;" >女
	                          	</label>
	                      	</div>
						</td>
					</tr>
			       	<tr>
						<th><span style="color: red;">* 身份证号码：</span></th>
		                <td>
							<input type="text" class="form-control" name="identityCard" id="identityCard" value="${doc.identityCard}" >
						</td>
						<th><span style="color: red;">* 手机号：</span></th>
	               		<td>
	               			<input type="text" name="phoneNo" class="form-control" id="phoneNo" value="${doc.phoneNo}" >
	               		</td>
			      	</tr>
			      	
			      	<tr>
	                	<th><span style="color: red;">* 角色：</span></th>
	                	<td>
	               			<select class="form-control" id="roleId" name="roleId" >
	                        	<c:forEach var="role" items="${role_list }">
									<option value="${role.roleId }">${role.roleName }</option>
	                          	</c:forEach>
	                  		</select>
	                	</td>
	                	<th>职位：</th>
	               		<td>
	               			<form:select id="specialty" path="doc.specialty" items="${applicationScope.doc_specialty__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
	               		</td>
		         	</tr>
			      	
	          		<tr>
	               		<th>电子邮箱：</th>
	               		<td>
	               			<input type="text" name="email" class="form-control" id="email" value="${doc.email}">
	               		</td>
	          		</tr>
		         	<tr>
	                	<th>备注:</th>
	                	<td colspan="3">
	                		<textarea rows="3" class="form-control" name="remark" id="remark" >${doc.remark }</textarea>
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
	
	//显示机构树
	var zTreeObjList;
	var settingList = {
		async : {
			autoParam : [],
			contentType : "application/json",
			dataType : "json",
			enable : true,
			url : "/healthCloud/org/getOrgTree",
			type : "post"
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : null
			}
		},
		view : {
			dblClickExpand : true,
			showLine : true,
			selectedMulti : false
		},
		callback : {
			onNodeCreated : onNodeCreatedList,
			onClick : onClickList
		}
	};
	
	$(document).ready(function() {
		zTreeObjList = $.fn.zTree.init($("#treeDemoList"), settingList);
	});
	
	function showMenuList() {
		var cityObj = $("#citySelList");
		var modalOffset = $("#right").offset();
		var cityOffset = $("#citySelList").offset();
		var left = cityOffset.left - modalOffset.left;
		var top = cityOffset.top - modalOffset.top;
		$("#menuContentList").css({
			left : left + "px",
			top : top + cityObj.outerHeight() + "px"
		}).slideDown("fast");
		$("body").bind("mousedown", onBodyDownList);
	}
	function hideMenuList() {
		$("#menuContentList").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDownList);
	}
	function onClickList(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemoList"), nodes = zTree.getSelectedNodes(), v = "", orgId = "";
		nodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for (var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
			orgId = nodes[i].id;
		}
		if (v.length > 0){
			v = v.substring(0, v.length - 1);
		}
		var cityObj = $("#citySelList");
		cityObj.attr("value", v);
		$("#orgList").attr("value", orgId);
		hideMenuList();
	}
	function onNodeCreatedList(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		var nodes = zTree.getNodes();
		nodes = zTree.getNodeByParam("id", $("#orgList").val() , null);
		zTree.selectNode(nodes, false , false);
		zTree.expandNode(nodes, true, false, false);
		if ($("#citySelList").val() == "") {
			onClickList(e, treeId, treeNode);
		}
	}
	function onBodyDownList(event) {
		if (!(event.target.id == "citySelList"
				|| event.target.id == "menuContentList" || $(event.target)
				.parents("#menuContentList").length > 0)) {
			hideMenuList();
		}
	}

	$("#submitBtn").click(function() {
		return validata();
	});
	
	function validata() {
		$("#docForm").validate({
			rules : {
				userCode : {
					required : true,
                    isNotEmpty : true,
					remote : "checkUserCode"
				},
				realName :{
					required : true,
                    isNotEmpty : true
				},
				identityCard : {
					required : true,
					isIdCard : true,
					remote : {
						url : "checkID", //后台处理程序  
						type : "post", //数据发送方式  
						dataType : "json", //接受数据格式     
						data : { //要传递的数据  
							identityCard : function() {
								return $('#identityCard').val();
							},
							docId : function() {
								return $('#docId').val();
							}
						}
					}
				},
				pwd : {
					required : true,
					minlength : 6
				},
				repwd : {
					required : true,
					equalTo : "#pwd"
				},
				phoneNo : {
					required : true,
					rangelength : [ 11, 11 ],
					isMobile : true
				},
				email : {
					email : true
				},
				role : {
					required : true
				}
			},
			messages : {
				userCode : {
					remote : "账号已被占用"
				},
				role : {
					required : "至少要分配一个角色"
				},
				identityCard : {
					remote : "身份证号码已被注册"
				}
			},
			errorPlacement : function(error, element) {
				layer.tips($(error).text(), element, {
					tips : 3,
					tipsMore : true
				});
			},
			ignore : "disabled"
		});
	}
	
	$(document).ready(function() {
		if ("${doc}" != null && "${doc}" != "") {
			$("#roleId").val("${doc.roleId}");
			var sex = document.getElementsByName("sex");
			if ("${doc.sex}" == 0) {
				sex[1].setAttribute("checked", "checked");
			} else {
				sex[0].setAttribute("checked", "checked");
			}
			var userCode = document.getElementById("userCode");
			userCode.setAttribute("disabled", "true");
		} else {
			var sex = document.getElementsByName("sex");
			sex[0].setAttribute("checked", "checked");
			$("#orgId").val("${org.orgId}");
			$("#orgName").val("${org.orgName}");
		}
		$("#specialty option").each(function() {
			var op = $(this).text();
			if ("${doc.specialty}" == op) {
				$(this).attr("selected", "selected");
			}
		});
	});
</script>
</html>