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
</head>
<body>
	<div class="container-fluid">
		<ul class="nav nav-tabs" style="margin-bottom: 10px;">
			<li><a href="list?parentOrgId=${parentOrg.orgId }">机构列表</a></li>
			<li class="active">
				<c:choose>
					<c:when test="${org.orgId != null }">
						<a href="#">编辑机构</a>
					</c:when>
					<c:otherwise>
						<a href="#">添加机构</a>
					</c:otherwise>
				</c:choose>
			</li>
		</ul>

		<div class="modal fade bs-example-modal-sm" id="areaTree" tabindex="-1" role="dialog" aria-labelledby="gridSystemModalLabel">
			<div class="modal-dialog" style="width: 450px;" role="document">
				<div class="modal-content"></div>
			</div>
		</div>
		
		<div class="col-sm-12" style="padding-left: 0px; padding-right: 0px; overflow-x: hidden;">
	    	<form class="form-horizontal pull-left form-normallr" action="add" id="orgForm" method="post" enctype="multipart/form-data">
	    	<c:choose>
				<c:when test="${org.orgId!=null }">
					<input type="hidden" name="editType" value="edit">
					<input type="hidden" id="orgId" name="orgId" value="${org.orgId}">
					<input type="hidden" name="orgCode" value="${org.orgCode}">
				</c:when>
				<c:otherwise>
					<input type="hidden" name="editType" value="new">
					<input type="hidden" id="orgId" name="orgId" value="${orgId}">
				</c:otherwise>
			</c:choose>
	      	<div class="form-gd-hd">机构必填信息</div>
      		<table class="table table-bordered">
          		<tr>
               		<th>
               			<label class="control-label"><span style="color: red;">* 机构名称：</span></label>
               		</th>
               		<td>
               			<input type="text" class="form-control" id="orgName" name="orgName" value="${org.orgName}" required="required">
               		</td>
               		<th>
          				<label class="control-label"><span style="color: red;">* 机构类型：</span></label>
          			</th>
               		<td>
              			<form:select id="orgType" path="org.orgType" items="${applicationScope.org_type__}" itemLabel="value" itemValue="key" cssStyle="" class="form-control"/>
               		</td>
          		</tr>
          		
		      	<tr>
					<th>
               			<label class="control-label"><span style="color: red;">* 归属区域：</span></label>
					</th>
               		<td>
               			<div class="input-group">
							<input type="text" class="form-control" id="area" name="area" placeholder="" value="${org.area}" placeholder="请选择所属区域" readonly="readonly" required="required"> 
							<input type="hidden" name="areaId" id="areaId" value="${org.areaId }"> 
							<span class="input-group-btn">
								<button class="btn btn-default" id="areaBtn" type="button" data-toggle="modal" href="<%=contextPath%>/area/showAreaTree?areaId=${org.areaId }" data-target="#areaTree">
									<i class="fa fa-search"></i>
								</button>
							</span>
						</div>
               		</td>
               		<th>
               			<label class="control-label"><span style="color: red;">* 经纬度：</span></label>
					</th>
               		<td>
               			<div class="input-group">
							<input type="text" class="form-control" id="lnglat" name="lnglat" value="${org.lnglat}" placeholder="格式:114.39956,30.443168" required="required">
							<input type="hidden" id="longitude" name="longitude" value="${org.longitude}"> <input type="hidden" id="latitude" name="latitude" value="${org.latitude}">
							<span class="input-group-btn"> 
								<a class="btn btn-default" href="http://lbs.amap.com/console/show/picker" target="_blank"><i class="fa fa-search"></i></a>
							</span>
						</div>
               		</td>
          		</tr>
          			<tr>
		       		<th>
               			<label class="control-label"><span style="color: red;">* 负责人：</span></label>
               		</th>
               		<td>
               			<input type="text" class="form-control" id="linkman" name="linkman" value="${org.linkman}" placeholder="" required="required">
               		</td>
               		<th>
               			<label class="control-label"><span style="color: red;">* 手机号码：</span></label>
					</th>
               		<td class="radioinput">
               			<input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${org.phoneNumber}" placeholder="">
               		</td>
		      	</tr>
		      	
      		</table>
	      	
	      	<div class="form-gd-hd">机构其他信息</div>
      		<table class="table table-bordered">
          		<tr>
          			<th>
          				<label class="control-label">上级机构：</label>
          			</th>
               		<td>
           				<input type="text" class="form-control" value="${parentOrg.orgName}" readonly="readonly"> 
						<input type="hidden" id="parentOrgId" name="parentOrgId" value="${parentOrg.orgId}">
               		</td>
					<th>
               			<label class="control-label">联系地址：</label>
               		</th>
               		<td>
               			<input type="text" class="form-control" id="address" name="address" value="${org.address}" >
               		</td>
          		</tr>
          		
          		<tr>
					<th>
               			<label class="control-label">机构性质：</label>
               		</th>
               		<td>
               			<form:select id="orgNature" path="org.orgNature" items="${applicationScope.org_nature__}" itemLabel="value" itemValue="key" class="form-control"/>
               		</td>
               		<th>
	           			<label class="control-label">机构标签：</label>
					</th>
	                <td>
	                	<input type="text" class="form-control" id="orgTag" name="orgTag" value="${org.orgTag}">
					</td>
          		</tr>
          		
          		<tr>
               		<th>
               			<label class="control-label">固话号码：</label>
               		</th>
               		<td>
               			<input type="text" class="form-control" id="tel" name="tel" value="${org.tel}" placeholder="格式:010-12345678">
               		</td>
               		<th>
               			<label class="control-label">传真：</label>
               		</th>
               		<td>
               			<input type="text" class="form-control" id="fax" name="fax" value="${org.fax}" placeholder="格式:010-12345678">
               		</td>
          		</tr>
          		
          		<tr>
          			<th>
               			<label class="control-label">电子邮箱：</label>
               		</th>
               		<td>
               			<input type="email" class="form-control" id="email" name="email" value="${org.email}" placeholder="">
               		</td>
               		<th>
               			<label class="control-label">邮政编号：</label>
               		</th>
               		<td>
               			<input type="text" class="form-control" id="postcode" maxlength="6" name="postcode" value="${org.postcode}" placeholder="格式:438300">
               		</td>
          		</tr>
          		
          		<tr>
                	<th>
                		<label class="control-label">机构简介：</label>
                	</th>
                	<td colspan="3">
                		<textarea class="form-control" id="orgIntro" name="orgIntro" style="resize: none;">${org.orgIntro}</textarea>
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

	$(function() {
		$("#orgForm").validate({
			rules : {
				orgName : {
					required : true,
					isNotEmpty : true,
					remote : {
						type : "POST",
						url : "isExistOrg",
						data : {
							parentOrgId : function() {
								return $("#parentOrgId").val();
							},
							orgName : function() {
								return $("#orgName").val();
							},
							orgId : function() {
								return $("#orgId").val();
							}
						}
					}
				},
				orgType : "required",
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
				orgName : {
					remote : $.validator.format("当前机构下存在同名机构，请重新输入")
				}
			},

			errorPlacement : function(error, element) {
				layer.tips($(error).text(), element, {
					tips : 3,
					tipsMore : true
				});
			}
		}
		);
	});
	
</script>
</body>
</html>