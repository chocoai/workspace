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
	// 加载数据表格
	$(function(){
		queryDg();
	})
	
	//查询表格
	function queryDg(){
		var deviceNo=$("#device_no").val();
		var status=$("#status").combobox('getValue');
		params={"deviceNo":deviceNo, "status":status};
		$("#dg").datagrid({
			url:ctx + '/power/queryPower',
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
	          	{field:'deviceNo',title:'设备编号',width:20,align:'center'},
	          	{field:'name',title:'设备名称',width:20,align:'center'},
	          	{field:'CommOrAreaName',title:'所属区域',width:20,align:'center',formatter: function(value, row, index){
	          		if(row.commName){
	          			return row.commName; 
	          		}else if(row.areaName){
	          			return row.areaName;
	          		}else{
	          			return '暂未绑定';
	          		}
	          	}},
	          	{field:'siteName',title:'投放点名称',width:20,align:'center'},
	          	{field:'status',title:'设备状态',width:20,align:'center',formatter: function(value, row, index){
	          		if(value=='0'){
	          			return '停用'; 
	          		}else if(value=='1'){
	          			return '启用';
	          		}else{
	          			return '暂未设置';
	          		}
	          	}},
	          	{field:'todayKwh',title:'用电电量',width:20,align:'center'},
	          	{field:'kwh',title:'累计用电电量',width:30,align:'center'},
	          	{field:'endDate',title:'用电时间',width:20,align:'center'}
			]], 
			onClickRow : function(index, row){
				dlgOpen(row);
	        },
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { deviceNo: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'deviceNo',  
            			colspan:8
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
	
	//电量表格
	function dlgOpen(obj){
		$('#dlg').dialog('open');
		//加载对应设备的用电量信息
		$("#dgPower").datagrid({
			url:ctx + '/power/queryByDid',
			queryParams: {"deviceId":obj.deviceId},
			rownumbers : true,
			fitColumns : true,
			border : false,
			striped : true,
			fit : true,
			singleSelect : false,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 15 ,20],
			columns:[[
				{field:'endDate',title:'用电日期',width:100,align:'center'},
				{field:'deviceNo',title:'设备编号',width:100,align:'center',formatter: function(value, row, index){
	          		return obj.deviceNo;
	          	}},
	          	{field:'name',title:'设备名称',width:120,align:'center',formatter: function(value, row, index){
	          		return obj.name;
	          	}},
	          	{field:'todayKwh',title:'用电电量',width:60,align:'center'},
	          	{field:'kwh',title:'统计用电量',width:80,align:'center'},
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { endDate: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'endDate',  
            			colspan:5
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
	<div class="header"  data-options="region:'north',border:false,split:true" style="width:99%;height:12%;">
		<fieldset style="border-width: 2px; border-color: #0088cc;">
		<legend>查询条件</legend>
		<td>设备编号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="device_no"/>
		</td>
		<td>设备状态：</td>
		<td >
			<select class="easyui-combobox" id="status" data-options="panelHeight:'auto'" >
				<option value="">--请选择--</option>
				<option value="1">启用</option>
				<option value="0">停用</option>
			</select>
		</td>
		<td>
			<a href="#" class="easyui-linkbutton" onclick="queryDg();">查询</a>
		</td>
		</fieldset>
    </div>

    <!-- 中间tabs -->
    <div class="main" data-options="region:'center'" >
    	<!-- 用户消息面板 -->
        <table id="dg"></table>
    </div>
    
    <div id="dlg" class="easyui-dialog" title="设备电量表" style="width:700px;height:370px;padding:10px;" closed="true" >
		<table id="dgPower"></table>
	</div>
</body>  
</html>  