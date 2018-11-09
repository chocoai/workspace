<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title" id="myModalLabel">新增组织机构</h4>
</div>

<form action="add" id="orgForm" method="post" class="form-horizontal">
	<div class="modal-body">
		<input type="hidden" name="orgId" value="${org.orgId}"> 
		<input type="hidden" name="orgCode" value="${org.orgCode}">
		<div class="form-group">
			<label class="col-sm-2 control-label">上级机构</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="" name="" placeholder="" value="${parentOrg.orgName}" readonly="readonly"> 
				<input type="hidden" id="parentOrgId" name="parentOrgId" value="${parentOrg.orgId}">
			</div>
			<label class="col-sm-2 control-label"><span style="color: red;">* 机构名称</span></label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="orgName" name="orgName"
					placeholder="" value="${org.orgName}" required="required">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"><span style="color: red;">* 归属区域</span></label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="area" name="area"
					placeholder="" value="${org.area}" required="required">
			</div>
			<label class="col-sm-2 control-label"><span style="color: red;">* 负责人</span></label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="linkman" name="linkman"
					value="${org.linkman}" placeholder="" required="required">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"><span style="color: red;">* 手机号码</span></label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="phoneNumber"
					name="phoneNumber" value="${org.phoneNumber}" placeholder="">
			</div>
			<label class="col-sm-2 control-label">固话号码</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="tel" name="tel"
					value="${org.tel}" placeholder="格式:010-12345678">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">电子邮箱</label>
			<div class="col-sm-4">
				<input type="email" class="form-control" id="email" name="email"
					value="${org.email}" placeholder="">
			</div>
			<label class="col-sm-2 control-label">传真</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="fax" name="fax"
					value="${org.fax}" placeholder="格式:010-12345678">
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">邮政编号</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="postcode" maxlength="6"
					name="postcode" value="${org.postcode}" placeholder="格式:438300">
			</div>
			<label class="col-sm-2 control-label">排序号</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="address" name="orderNum"
					value="${org.orderNum}" placeholder="">
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">联系地址</label>
			<div class="col-sm-10">
				<textarea rows="" cols="" class="form-control" id="" name="address"
					style="resize: none;">${org.address}</textarea>
			</div>

		</div>

	</div>

	<div class="modal-footer">
		<button type="submit" class="btn btn-primary">保存</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	</div>
</form>

<script type="text/javascript">
	$(function() {
		$("#orgForm").validate(

		{
			rules : {
				//orgName : "required",
				orgName : {
					required : true,
					remote : {
						type : "POST",
						url : "isExistOrg",
						data : {
							parentOrgId : function() {
								return $("#parentOrgId").val();
							},
							orgName : function() {
								return $("#orgName").val();
							}
						}
					}
				},
				area : "required",
				linkman : "required",
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