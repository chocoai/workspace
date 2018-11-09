<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<link rel="stylesheet" href="../static/scripts/themes/default/easyui.css">  
<link rel="stylesheet" href="../static/scripts/themes/icon.css">  
<script type="text/javascript" src="../static/scripts/jquery.min.js"></script>
<script type="text/javascript" src="../static/scripts/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../static/scripts/locale/easyui-lang-zh_CN.js"></script>
</head>
<script type="text/javascript">
	var ctx = "<%=request.getContextPath()%>";
	var params={};
	$(function(){
		InitTreeData();
	});
	
	function InitTreeData(){
		$("#tt").tree({
			url:ctx+'/area/areaTree',
	        checkbox:false,
	        onLoadSuccess:function(node,data){
                var tree = $(this);  
                if(data){  
                    $(data).each(function(index,d) {  
                        if (this.state=='closed') {  
                            //tree.tree('expandAll');
                        }  
                    });  
                }  
           },
	        onClick:function(node){
	        	//将区域id放入隐藏input
	        	$("#comOrareId").val(node.id);
	        	$("#deep").val(node.attributes);
	        	//节点凑发事件加载dg表格
	        	query();
	        }
	   	});
	}
	
	function query(){
		var comOrareId = $("#comOrareId").val();
		var deep = $("#deep").val();
		var qStartTime = $("#qStartTime").datebox("getValue");
		var qEndTime = $("#qEndTime").datebox("getValue");
		var deviceNo = $("#deviceNo").val();
		var orderNo = $("#orderNo").val();
		var status = $("#status").combobox("getValue");
		var payType = $("#payType").combobox("getValue");
		params={
				"comOrareId":comOrareId,
				"deep":deep,
				"qStartTime":qStartTime,
				"qEndTime":qEndTime,
				"deviceNo":deviceNo,
				"orderNo":orderNo,
				"status":status,
				"payType":payType
		}
		$("#dg").datagrid({
			url:ctx + '/order/queryOrder',
			queryParams: params,
			rownumbers : true,
			fitColumns : true,
			border : false,
			striped : true,
			fit : true,
			singleSelect : false,
			pagination : true,
			pageSize : 20,
			pageList : [ 20, 30 ,40],
			columns:[[
				{field:'orderNo',title:'订单编号',width:100,align:'center'},
	          	{field:'deviceNo',title:'设备编号',width:80,align:'center'},
	          	{field:'portNo',title:'设备端口号',width:50,align:'center'},
	          	{field:'userAccount',title:'用户',width:80,align:'center'},
	          	{field:'cTimeStr',title:'订单时间',width:120,align:'center'},
	          	{field:'price',title:'使用电价',width:50,align:'center'},
	          	{field:'mins',title:'电价时长',width:50,align:'center'},
	          	{field:'priceType',title:'订单支付方式',width:50,align:'center',formatter: function(value, row, index){
	          		if(value=='0'){
	          			return '后付款'; 
	          		}else if(value=='1'){
	          			return '预付费';
	          		}else{
	          			return '<font color="red">订单支付方式错误,请核查</font>';
	          		}
	          	}},
	        	{field:'status',title:'订单状态',width:50,align:'center',formatter: function(value, row, index){
	        		if(value=='-1'){
	          			return '关闭'; 
	          		}else if(value=='0'){
	          			return '未处理';
	          		}else if(value=='1'){
	          			return '已处理';
	          		}else{
	          			return '<font color="red">订单状态错误,请核查</font>';
	          		}
	          	}},
	        	{field:'payType',title:'支付类型',width:60,align:'center',formatter: function(value, row, index){
	        		if(value=='0'){
	          			return '钱包支付'; 
	          		}else if(value=='1'){
	          			return '支付宝支付';
	          		}else if(value=='2'){
	          			return '微信支付';
	          		}else if(value=='3'){
	          			return '其他';
	          		}else{
	          			return '<font color="red">订单支付类型错误,请核查</font>';
	          		}
	          	}},
	          	{field:'orderMoney',title:'订单应缴金额',width:50,align:'center'},
	          	{field:'payMoney',title:'订单实缴金额',width:50,align:'center'},
	          	{field:'saveMoney',title:'优惠金额',width:50,align:'center'}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { orderNo: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'orderNo',  
            			colspan:13
        			});  
                	//隐藏分页导航条，这个需要熟悉datagrid的html结构，直接用jquery操作DOM对象，easyui datagrid没有提供相关方法隐藏导航条
                	$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            	}else{
	            	//如果通过调用reload方法重新加载数据有数据时显示出分页导航容器
            		$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
            	}
            },
		});
	}
</script>  
<body class="easyui-layout">
	<div data-options="region:'north',border:false,split:true" style="width:99%;height:16%;border:1px solid #ccc;">
		<fieldset style="border-width: 2px; border-color: #0088cc;">
		<legend>查询条件</legend>
		<td>开始时间：</td>
		<td >
			<input type="text" class="easyui-datebox" id="qStartTime"/>
		</td>
		<td>结束时间：</td>
		<td >
			<input type="text" class="easyui-datebox" id="qEndTime"/>
		</td>
		<td>设备编号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="deviceNo"/>
		</td>
		<br>
		<td>订单编号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="orderNo"/>
		</td>
		<td>订单状态：</td>
		<td >
			<select class="easyui-combobox" id="status" data-options="panelHeight:'auto'" >
				<option value="">--请选择--</option>
				<option value="0">未处理</option>
				<option value="1">已处理</option>
				<option value="-1">关闭</option>
			</select>
		</td>
		<td>支付类型：</td>
		<td >
			<select class="easyui-combobox" id="payType" data-options="panelHeight:'auto'" >
				<option value="">--请选择--</option>
				<option value="0">钱包支付</option>
				<option value="1">支付宝支付</option>
				<option value="2">微信支付</option>
				<option value="3">其他</option>
			</select>
		</td>
		<td>
			<a href="#" class="easyui-linkbutton" onclick="query();">查询</a>
		</td>
		</fieldset>
	</div>
    <div data-options="region:'west',border:false,split:true" style="width:15%;height:auto;border:1px solid #ccc;">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
	<div class="main" data-options="region:'center'" >
    	<table id="dg"></table>
    </div>
	<input type="hidden" id="comOrareId"/>
	<input type="hidden" id="deep"/>
</body>  
</html>  