<%@page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% String contextPath = request.getContextPath(); %>
<style>

/*
        树形图背景
*/
ul.ztree {max-height:200px;border: 1px solid #617775;background: #f0f6e4;overflow-y:scroll;overflow-x:auto;}

</style>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title" id="myModalLabel">分配服务人员</h4>
</div>

<form class="form-horizontal">
	<div id="modalBody" class="modal-body" style="text-align:center;">
		<div id="menuContentModal" class="menuContent" style="display:none; position: absolute;z-index:999;">
	     	<ul id="treeDemoModal" class="ztree" style="margin-top:0; width:250px;"></ul>
		</div>
		<table class="table table-bordered">
			<c:if test="${not empty orderId }">
			<tr>
       			<th>工单编号：</th>
           		<td>
					<input id="cext1" type="text" class="form-control" name="cext1" value="${workOrder.cext1 }" readonly="readonly">
           		</td>
           		<th>订购用户：</th>
           		<td>
           			<input id="memberName" type="text" class="form-control" name="memberName" value="${workOrder.member.realName }" readonly="readonly">
          		</td>
      		</tr>
      		
      		<tr>
      			<th>工单内容：</th>
          		<td>
          			<input id="orderContent" type="text" class="form-control" name="orderContent" value="${workOrder.orderContent }" readonly="readonly">
           		</td>
           		<th>服务对象：</th>
          		<td>
          			<input id="serviceObject" type="text" class="form-control" name="serviceObject" value="${workOrder.serviceObject }" readonly="readonly">
          		</td>
       		</tr>
          		
  			<tr>
          		<th>服务费用：</th>
                <td>
               		<input type="text" name="serviceFee" class="form-control" id="serviceFee" value="${workOrder.serviceFee}" readonly="readonly">
				</td>
           		<th>联系电话：</th>
                <td>
                	<input id="tel" type="text" class="form-control" name="tel" value="${workOrder.tel }" readonly="readonly">
				</td>
	      	</tr>
		      	
	      	<tr>
           		<th>服务地址：</th>
                <td colspan="3">
                	<textarea rows="3" class="form-control" name="address" id="address" style="resize:none;" readonly="readonly">${workOrder.address }</textarea>
				</td>
	      	</tr>
	      	</c:if>
		      	
        	<tr>
        		<th>服务机构：</th>
          		<td>
					<input id="citySelModal" type="text" class="form-control" readonly onclick="showMenuModal(); return false;"/>
		            <input type="hidden" id="orgModal" name="orgId" value="">
                    <input type="hidden" id="orderId" name="orderId" value="${orderId }">
		            <c:forEach items="${orderIds }" var="orderId" varStatus="status">
	                	<input type="hidden" id="orderIds" name="orderIds" value="${orderId }">
		            </c:forEach>
           		</td>
           		<th>服务人员：</th>
           		<td>
					<select id="toUser" name="toUser" class="form-control"></select>
           		</td>
       		</tr>
       		
       		<tr>
       			<th>
		       		<label class="control-label">备注信息</label>
       			</th>
       			<td colspan="3">
					<textarea maxlength="100" class="form-control" id="remark" name="remark" style="resize:none;" placeholder="请输入备注信息"></textarea>
       			</td>
       		</tr>
       		
       		<tr>
        		<th colspan="5">
        			<button type="button" class="btn btn-primary" onclick="assign()">确认</button>
    				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				</th>
        	</tr>
   		</table>
	</div>
</form>

<script type="text/javascript">

	function assign(){
   		var orderId = $("#orderId").val();
    	var orderIds = new Array();
    	$("input[type='hidden'][name='orderIds']").each(function(i) {
        	orderIds[i] = parseInt($(this).val());
    	});
    	var toUser = $("#toUser").val();
    	var remark = $("#remark").val();
    	if(!toUser){
    		layer.msg('服务人员不能为空');
    		return ;
    	}
    	layer.confirm('您确认要分配这些工单吗？', { btn: ['确认','取消'] }, function(){
    		$.ajax({
    			cache : true,
    			type : "post",
    			url : "assign",
    			async : false,
    			data : {
    				"toUser" : toUser,
    				"orderId" : orderId,
    				"orderIds" : orderIds,
    				"remark" : remark
    			},
    			traditional : true,
    			success : function(data) {
    				layer.msg('分配成功', { anim: 6 });
    				setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
    					window.location.reload();//页面刷新
    				},2000);
    			},
    			error : function() {
    				layer.msg('分配失败', { anim: 6 });
    			}
    		});
    	}, function(){
    		return;
    	});
	}

	function getEmpByOrgId(){
    	var orgId = $("#orgModal").val();
		$.ajax({
        	cache : true,
        	type : "post",
        	url : "getEmpByOrgId",
        	async : false,
        	data : {
            	"orgId" : orgId,
        	},
        	traditional : true,
        	success : function(data) {
            	$("#toUser").empty();
            	var json = eval(data);
            	if(json.length == 0){
            		$("#toUser").val("");
            	}
        		$.each(json, function (index, item) { 
        	    	$("#toUser").append("<option value='"+item.empId+"'>"+item.empName+"</option>");
        		});
        	},
        	error : function() {
            	layer.msg('获取员工信息失败', { anim: 6 });
        	}
    	});
	}

	//显示机构树
	var zTreeObjModal;
	var settingModal = {
			async: {
                autoParam: [],
                contentType: "application/json",
                dataType: "json",
                enable: true,
                url: "<%=contextPath%>/org/getOrgTree",
                type: "post"
        	},
        	data: {
                simpleData: {
                	enable: true,
                    idKey: "id",
                    pIdKey: "pId",
                    rootPId: null
                }
        	},
        	view: {
                dblClickExpand: true,
                showLine: true,
                selectedMulti: false
        	},
        	callback: {
                onNodeCreated: onNodeCreatedModal,
                onClick: onClickModal
        	}
	};
	
	$(document).ready(function(){
    	zTreeObjModal = $.fn.zTree.init($("#treeDemoModal"), settingModal);
 	});

	function showMenuModal() {
        var cityObj = $("#citySelModal");
        var modalOffset = $("#modalBody").offset();
        var cityOffset = $("#citySelModal").offset();
        //这里因为是相对于模态框的body部分位移，所以这里在取值时需要减去模态框距离页面左边和上边的数值得到的才是模态框边界到input框的距离
        var left = cityOffset.left-modalOffset.left;
        var top = cityOffset.top-modalOffset.top;
        $("#menuContentModal").css({left:left + "px", top:top + cityObj.outerHeight() + "px"} ).slideDown("fast");
        $("body").bind("mousedown", onBodyDownModal);
	}

	
	function hideMenuModal() {
        $("#menuContentModal").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDownModal);
	}

	function onClickModal(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("treeDemoModal"),
        nodes = zTree.getSelectedNodes(),
        v = "";
        var orgId;
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
                v += nodes[i].name + ",";
                orgId = nodes[i].id;
        }
        if (v.length > 0 ) v = v.substring(0, v.length-1);
        var cityObj = $("#citySelModal");
        cityObj.attr("value", v);
        $("#orgModal").attr("value", orgId);
        hideMenuModal();
        getEmpByOrgId();
	}

	function onNodeCreatedModal(e, treeId, treeNode){
	    var zTree = $.fn.zTree.getZTreeObj(treeId);
	    var nodes = zTree.getNodes();
	    zTree.selectNode(nodes[0]);
	    zTree.expandNode(nodes[0], true, false, false);
	    if($("#citySelModal").val()==""){
	        onClickModal(e, treeId, treeNode);
	    }
	}

	function onBodyDownModal(event) {
        if (!(event.target.id == "citySelModal" || event.target.id == "menuContentModal" || $(event.target).parents("#menuContentModal").length>0)) {
        	hideMenuModal();
        }
	}
</script>