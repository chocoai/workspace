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
	var re = new RegExp("^[ ]+$");
	var params={};
	// 加载数据表格
	$(function(){
		queryDg();
		//查询条件下拉框数据显示
		$("#made_in").combobox({
		    url:ctx + '/device/showCombobox',
		    valueField:'madeIn',
			textField:'madeIn',
			onLoadSuccess: function (data) {
	            if (data) {
	                $('#made_in').combobox('setValue',data[0].madeIn);
	            }
	        }
		});
	})
	
	//查询表格
	function queryDg(){
		var deviceNo=$("#device_no").val();
		var cardNo=$("#card_no").val();
		var madeIn=$("#made_in").combobox('getValue');
		var inDate=$("#in_date").datebox('getValue');
		params={"deviceNo":deviceNo, "cardNo":cardNo, "madeIn":madeIn, "date":inDate};
		$("#dg").datagrid({
			url:ctx + '/device/queryDevice',
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
	          	{field:'name',title:'设备名称',width:20,align:'center'},
	          	{field:'deviceNo',title:'设备编号',width:20,align:'center'},
	          	{field:'cardNo',title:'卡号',width:20,align:'center'},
	          	{field:'portNum',title:'端口数量',width:20,align:'center'},
	          	{field:'status',title:'设备状态',width:20,align:'center',formatter: function(value, row, index){
	          		if(value=='0'){
	          			return '停用'; 
	          		}else if(value=='1'){
	          			return '启用';
	          		}else{
	          			return '暂未设置';
	          		}
	          	}},
	          	{field:'deviceIp',title:'通信号',width:20,align:'center'},
	          	{field:'madeIn',title:'生产厂家',width:30,align:'center'},
	          	{field:'outDateStr',title:'出厂日期',width:20,align:'center'},
	          	{field:'inDateStr',title:'入库日期',width:20,align:'center'},
	          	{field:'cz',title:'操作',width:20,align:'center',formatter: function(value, row, index){
	            	var obj = JSON.stringify(row);
	           		return "<a href='#' class='easyui-linkbutton' onclick='update("+obj+");'>编辑</a>";  
	          	}}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { name: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'name',  
            			colspan:10
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
	
	function change(number){
		var name=$("#name").val();
		var deviceNo=$("#deviceNo").val();
		if(number=="1"){
			if(!name || re.test(name)){
				$("#nameMsg").text("请输入设备名称！");
				return ;
			}else{
				$("#nameMsg").text("");
			}
		}else if(number=="2"){
			if(!deviceNo || re.test(deviceNo)){
				$("#deviceNoMsg").text("请输入设备编号！");
				return ;
			}else{
				$("#deviceNoMsg").text("");
			}
		}
	}
	
	$("#portNum").numberbox({
		onChange: function (newVal,oldVal) {
			if(!newVal){
				$("#portNumMsg").text("请输入端口数量！");
				return ;
			}else{
				$("#portNumMsg").text("");
			}
        }
    });
	
	// 新增修改保存按钮
	function save(){
		params={};
		var id = $("#id").val();
		var name=$("#name").val();
		if(!name || re.test(name)){
			$("#nameMsg").text("请输入设备名称！");
			return ;
		}else{
			$("#nameMsg").text("");
		}
		var deviceNo=$("#deviceNo").val();
		if(!deviceNo || re.test(deviceNo)){
			$("#deviceNoMsg").text("请输入设备编号！");
			return ;
		}else{
			$("#deviceNoMsg").text("");
		}
		var portNum = $("#portNum").numberbox("getValue");
		if(!portNum){
			$("#portNumMsg").text("请输入端口数量！");
			return ;
		}else{
			$("#portNumMsg").text("");
		}
		var cardNo=$("#cardNo").numberbox("getValue");
		var madeIn=$("#madeIn").val();
		var outDate=$("#outDate").datebox("getValue");
		params={
				"id":id,
				"name":name,
				"deviceNo":deviceNo,
				"portNum":portNum,
				"cardNo":cardNo,
				"madeIn":madeIn,
				"outDate":outDate
		}
		$.post(ctx + "/device/saveDevice",{data:JSON.stringify(params)},function(result){
			cancel();
			if(result.state == "SUCCESS"){
				$("#dg").datagrid("reload");
				$.messager.alert("提示消息", "保存成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "SAME"){
				$.messager.alert("提示消息", "数据重复，请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else{
				$.messager.alert("提示消息", "保存失败!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}
		},"json")
	}
	
	// 取消按钮
	function cancel(){
		$('#dlg').dialog('close');
		common();
	}
	
	// 新增弹出层
	function add(){
		$('#dlg').dialog('open');
		common();
	}
	
	//公共代码
	function common(){
		$("#id").val("");
		$("#name").val("");
		$("#deviceNo").val("");
		$("#portNum").numberbox("setValue","");
		$("#cardNo").numberbox("setValue","");
		$("#madeIn").val("");
		$("#outDate").datebox("setValue","");
		
		$("#nameMsg").text("");
		$("#deviceNoMsg").text("");
		$("#portNumMsg").text("");
	}
	
	//编辑修改给dialog弹出层对应字段赋值
	function update(obj){
		$("#dlg").dialog("open");
    	$("#id").val(obj.id);
    	$("#name").val(obj.name);
    	$("#deviceNo").val(obj.deviceNo);
    	$("#portNum").numberbox("setValue",obj.portNum);
    	$("#cardNo").numberbox("setValue",obj.cardNo);
		$("#madeIn").val(obj.madeIn);
		$("#outDate").datebox("setValue",obj.outDateStr);
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
		<td>卡号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="card_no"/>
		</td>
		<td>生产厂商：</td>
		<td >
			<input type="text" class="easyui-combobox" id="made_in" data-options="panelHeight:'auto'"/>
		</td>
		<td>创建时间：</td>
		<td >
			<input type="text" class="easyui-datebox" id="in_date"/>
		</td>
		<td>
			<a href="#" class="easyui-linkbutton" onclick="queryDg();">查询</a>
			<a href="#" class="easyui-linkbutton" onclick="add();">新增</a>
		</td>
		</fieldset>
    </div>

    <!-- 中间tabs -->
    <div class="main" data-options="region:'center'" >
    	<!-- 用户消息面板 -->
        <table id="dg"></table>
    </div>
    
    <div id="dlg" class="easyui-dialog" title="设备台账管理" style="width:460px;height:280px;padding:10px;" closed="true" >
		<table>
			<tr>
				<td style="text-align: right;width:120px">设备名称：</td>
				<td >
					<input type="text" class="easyui-validatebox" id="name" onchange="change('1');"/>
					<font id="nameMsg" color="red"></font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">设备编号：</td>
				<td >
					<input type="text" class="easyui-validatebox" id="deviceNo" onchange="change('2');"/>
					<font id="deviceNoMsg" color="red"></font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">端口数量：</td>
				<td >
					<input type="text" class="easyui-numberbox" id="portNum" maxLength=4 />
					<font id="portNumMsg" color="red"></font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">卡号：</td>
				<td >
					<input type="text" class="easyui-numberbox" id="cardNo" maxLength=8 />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">生产厂商：</td>
				<td >
					<input type="text" class="easyui-validatebox" id="madeIn" />
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">出厂日期：</td>
				<td >
					<input type="text" class="easyui-datebox" id="outDate" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<input type="hidden" id="id"/>
					<a href="#" class="easyui-linkbutton" onclick="save();">确定</a>
					<a href="#" class="easyui-linkbutton" onclick="cancel();">取消</a>
				</td>
			</tr>
		</table>
	</div>
</body>  
</html>  