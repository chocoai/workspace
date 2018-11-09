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
	        	$("#nodeName").val(node.text);
	        	$("#deep").val(node.attributes);
	        	//节点凑发事件加载表格
	        	query();
	        }
	   	});
	}
	
	function query(){
		var comOrareId = $("#comOrareId").val();
		var nodeName = $("#nodeName").val();
		var deep = $("#deep").val();
		var startTime = $("#startTime").datebox('getValue');
		var endTime = $("#endTime").datebox('getValue');
		var payWay = $("#payWay").combobox('getValue');
		var deviceNo = $("#deviceNo").val();
		var cardNo = $("#cardNo").val();
		var userAccount = $("#userAccount").val();
		params={
				"comOrareId":comOrareId,
				"deep":deep,
				"qStartTime":startTime,
				"qEndTime":endTime,
				"payWay":payWay,
				"deviceNo":deviceNo,
				"cardNo":cardNo,
				"userAccount":userAccount
		};
		$("#dg").datagrid({
			url:ctx + '/record/queryRecord',
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
				{field:'siteName',title:'投放点地址',width:100,align:'center'},
	          	{field:'nodeName',title:'所属区域',width:100,align:'center',formatter: function(value, row, index){
	          		return nodeName;
	          	}},
	          	{field:'deviceNo',title:'设备编号',width:60,align:'center'},
	          	{field:'portNo',title:'设备端口号',width:50,align:'center'},
	          	{field:'userAccount',title:'用户',width:80,align:'center'},
	          	{field:'startTime',title:'充电开始时间',width:120,align:'center'},
	          	{field:'endTime',title:'预计充电结束时间',width:120,align:'center'},
	          	{field:'fEndTime',title:'实际结束时间',width:120,align:'center'},
	          	{field:'expectTime',title:'预充时长（分钟）',width:50,align:'center'},
	        	{field:'factTime',title:'实充时长（分钟）',width:50,align:'center'},
	        	{field:'payWay',title:'充电方式',width:50,align:'center',formatter: function(value, row, index){
	          		if(value=='0'){
	          			return '线下'; 
	          		}else if(value=='1'){
	          			return '线上';
	          		}else{
	          			return '<font color="red">记录错误,请核查</font>';
	          		}
	          	}},
	        	{field:'status',title:'充电状态',width:60,align:'center',formatter: function(value, row, index){
	          		if(value=='0'){
	          			return '正在充电'; 
	          		}else if(value=='1'){
	          			return '充电完成';
	          		}else{
	          			return '<font color="red">记录错误,请核查</font>';
	          		}
	          	}},
	          	{field:'money',title:'充电金额',width:40,align:'center'}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { siteName: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'siteName',  
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
			<input type="text" class="easyui-datebox" id="startTime"/>
		</td>
		<td>结束时间：</td>
		<td >
			<input type="text" class="easyui-datebox" id="endTime"/>
		</td>
		<td>充电方式：</td>
		<td >
			<select class="easyui-combobox" id="payWay" data-options="panelHeight:'auto'" >
				<option value="">--请选择--</option>
				<option value="1">线上</option>
				<option value="0">线下</option>
			</select>
		</td>
		<br>
		<td>设备编号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="deviceNo"/>
		</td>
		<td>卡号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="cardNo"/>
		</td>
		<td>充电用户：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="userAccount"/>
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
	<input type="hidden" id="nodeName"/>
	<input type="hidden" id="deep"/>
</body>  
</html>  