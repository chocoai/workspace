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
<script type="text/javascript">
	var ctx = "<%=request.getContextPath()%>";
	var re = new RegExp("^[ ]+$");
	var params={};
	$(function(){
		InitTreeData();
		$("#type").combobox({
			url:ctx + '/code/queryAll',
		    valueField:'id',
			textField:'value',
			onLoadSuccess: function (data) {
	            if (data) {
	                $('#type').combobox('setValue',data[0].value);
	            }
	        },
	        onSelect : function (nValue,oValue){
	        	$("#describe").val(nValue.content);
        	}
		});
		$("#nodeGroup").combobox({
			url:ctx + '/node/queryAll',
		    valueField:'id',
			textField:'groupName',
			onLoadSuccess: function (data) {
	            if (data) {
	                $('#nodeGroup').combobox('setValue',data[0].groupName);
	            }
	        }
		});
		$("#binding").css('display','none');
		$("#group").css('display','none');
	});
	
	function InitTreeData(){
		$("#tt").tree({
			url:ctx+'/node/nodeTree',
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
	        	//切换小区默认关闭弹出层
	        	$('#dlgBingding').dialog('close');
	        	$('#dlgGroup').dialog('close');
	        	//将区域id放入隐藏input
	        	$("#nodeId").val(node.id);
	        	$("#nodeName").val(node.text);
	        	$("#url").val(node.attributes);
	        	console.log(node.attributes);
	        	if(node.id!=''){
	        		$("#binding").css('display','');
	        		$("#group").css('display','');
	        		//节点凑发事件加载dg表格
	        		query();
	        	}else{
	        		$("#binding").css('display','none');
	        		$("#group").css('display','none');
	        	}
	        }
	   	});
	}
	
	function query(){
		var url = $("#url").val();
		params={"nodeUrl":url}
		$("#dg").datagrid({
			url:ctx + '/code/queryCodeByNid',
			queryParams: params,
			rownumbers : true,
			fitColumns : true,
			border : false,
			striped : true,
			// fit : true,
			singleSelect : false,
			pagination : true,
			pageSize : 20,
			pageList : [ 20, 30 ,40],
			columns:[[
				{field:'codeNum',title:'二维码编号',width:150,align:'center'},
				{field:'nodeUrl',title:'二维码节点路径',width:150,align:'center'},
	          	{field:'descpt',title:'节点描述',width:150,align:'center'},
	          	{field:'status',title:'二维码状态',width:50,align:'center',formatter: function(value, row, index){
	          		if (value == '1') {
	          	        return "绑定";
	          	    } else {
	          	    	return '空闲';
	          	    }
	          	}},
	          	{field:'createTimeStr',title:'创建时间',width:100,align:'center'},
	          	{field:'createUser',title:'创建用户',width:60,align:'center'},
	          	{field:'cz',title:'操作',width:50,align:'center',formatter: function(value, row, index){
	          		return "<a href='#' class='easyui-linkbutton' onclick='unbundlingCode("+row.id+");'>解绑</a>"; 
	          	}}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					$("#binding").css('display',''); 
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { codeNum: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'codeNum',  
            			colspan:7
        			});  
            	}else{
            		$("#binding").css('display','none'); 
            	}
				//隐藏分页导航条
            	$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
		});
	}
	
	//解绑
	function unbundlingCode(id){
		$.post(ctx + "/code/unbundlingCode",{"id":id},function(result){
			if(result.state == "SUCCESS"){
				$("#dg").datagrid("reload");
				$.messager.alert("提示消息", "解绑成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "FALSE"){
				$.messager.alert("提示消息", "参数出错,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else{
				$.messager.alert("提示消息", "解绑失败!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}
		},"json")
	}
	
	//绑定设备弹出层
	function binding(){
    	$('#dlgBingding').dialog('open');
    	$("#type").combobox("setValue",null);
    	$("#describe").val("");
    	dgCode();
	}
	
	//设备端口表格
	function dgCode(){
		//加载对应设备的用电量信息
		$("#dgCode").datagrid({
			url:ctx + '/code/queryCodeUnbounded',
			queryParams: {},
			rownumbers : true,
			fitColumns : true,
			border : false,
			striped : true,
			//fit : true,
			singleSelect : false,
			pagination : true,
			pageSize : 10,
			pageList : [ 10, 15 ,20],
			columns:[[
				{field:'codeNum',title:'二维码编号',width:220,align:'center'},
	          	{field:'status',title:'二维码状态',width:50,align:'center',formatter: function(value, row, index){
	          		if (value == '1') {
	          	        return "绑定";
	          	    } else {
	          	    	return '空闲';
	          	    }
	          	}},
	          	{field:'createTimeStr',title:'创建时间',width:120,align:'center'},
	          	{field:'createUser',title:'创建用户',width:80,align:'center'},
	          	{field:'cz',title:'操作',width:50,align:'center',formatter: function(value, row, index){
	          		return "<a href='#' class='easyui-linkbutton' onclick='codeSave("+row.id+");'>绑定</a>"
	          	}}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { codeNum: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'codeNum',  
            			colspan:4
        			});  
                	//隐藏分页导航条
                	$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            	}else{
	            	//显示分页导航条
            		$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
            	}
            },
		});
	}
	
	//绑定二维码
	function codeSave(id){
		var type=$("#type").combobox("getValue");
		if(!type){
			$.messager.alert("提示消息", "请选择绑定类型!", 'info',function(){});  
			setTimeout(function(){
		  		$(".messager-body").window('close');  
			},2000);
			return ;
		} 
		var describe=$("#describe").val();
		//需要进行格式验证 json字符串
		if(!describe || re.test(describe)){
			$.messager.alert("提示消息", "描述不能为空!", 'info',function(){});  
			setTimeout(function(){
		  		$(".messager-body").window('close');  
			},2000);
			return ;
		}else{
			if(typeof describe == 'string'){
				try {
					JSON.parse(describe);
				} catch(e) {
					$.messager.alert("提示消息", "请输入正确的json格式!", 'info',function(){});  
					setTimeout(function(){
				  		$(".messager-body").window('close');  
					},2000);
		            return ;
		        }
		    }
		}
		var nodeUrl=$("#url").val();
		params={"id":id,"nodeUrl":nodeUrl,"type":type,"descpt":describe}
		$.post(ctx + "/code/updateCode",{data:JSON.stringify(params)},function(result){
			if(result.state == "SUCCESS"){
				$("#dgCode").datagrid("reload");
				$("#dg").datagrid("reload");
				$('#dlgBingding').dialog('close');
				$.messager.alert("提示消息", "绑定成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "FALSE"){
				$.messager.alert("提示消息", "参数出错,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else{
				$.messager.alert("提示消息", "绑定失败!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}
		},"json")
	}
	
	//节点分组信息弹出层
	function group(){
		$('#dlgGroup').dialog('open');
    	$("#nodeGroup").combobox("setValue",null);
    	nodeGroup();
	}
	
	//节点信息表
	function nodeGroup(){
		var nodeId = $("#nodeId").val();
		$("#dgNode").datagrid({
			url:ctx + '/node/queryByNid',
			queryParams: {"nodeId":nodeId},
			rownumbers : true,
			fitColumns : true,
			border : false,
			striped : true,
			// fit : true,
			singleSelect : false,
			pagination : true,
			pageSize : 20,
			pageList : [ 20, 30 ,40],
			columns:[[
				{field:'nodeId',title:'节点编号',width:80,align:'center'},
				{field:'nodeName',title:'节点名称',width:100,align:'center'},
	          	{field:'isNode',title:'是否叶节点',width:50,align:'center',formatter: function(value, row, index){
	          		if (value == '1') {
	          	        return "是";
	          	    } else {
	          	    	return "否";
	          	    }
	          	}},
	          	{field:'nodeStatus',title:'节点状态',width:50,align:'center',formatter: function(value, row, index){
	          		if (value == '1') {
	          	        return "使用";
	          	    } else {
	          	    	return "停用";
	          	    } 
	          	}},
	          	{field:'propertyName',title:'属性名称',width:100,align:'center'},
	          	{field:'propertyType',title:'属性类型',width:60,align:'center',formatter: function(value, row, index){
	          		if (value == '1') {
	          	        return "第一级节点";
	          	    } else if (value == '2') {
	          	    	return "第二级节点";
	          	    } else if (value == '3') {
	          	    	return "第三级节点";
	          	    }
	          	}},
	        	/* {field:'propertyStatus',title:'属性状态',width:60,align:'center'}, */
	        	{field:'groupName',title:'所属组名',width:100,align:'center'},
	        	{field:'descpt',title:'分组描述',width:120,align:'center'},
	        	{field:'createTimeStr',title:'创建时间',width:120,align:'center'},
	        	{field:'createUser',title:'创建用户',width:80,align:'center'}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { nodeId: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'nodeId',  
            			colspan:10
        			});  
            	}
				//隐藏分页导航条
            	$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            }
		});
	}
	
	//节点分组保存信息表
	function groupSave(){
		var nodeId = $("#nodeId").val();
		var nodeGroup=$("#nodeGroup").combobox("getValue");
		if(!nodeGroup){
			$.messager.alert("提示消息", "请选择分组类型!", 'info',function(){});  
			setTimeout(function(){
		  		$(".messager-body").window('close');  
			},2000);
			return ;
		} 
		$.post(ctx + "/node/updateNode",{"nodeId":nodeId,"groupId":nodeGroup},function(result){
			if(result.state == "SUCCESS"){
				$("#dgNode").datagrid("reload");
				$.messager.alert("提示消息", "分组成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "FALSE"){
				$.messager.alert("提示消息", "参数出错,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else{
				$.messager.alert("提示消息", "分组失败!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}
		},"json")
	}
	
</script>
</head>  
<body class="easyui-layout">
	<div data-options="region:'north',border:false,split:true" style="width:99%;height:12%;border:1px solid #ccc;">
		<fieldset style="border-width: 2px; border-color: #0088cc;">
		<legend>操作</legend>
			<a id="binding" href="#" class="easyui-linkbutton" onclick="binding();">绑定二维码</a>
			<a id="group" href="#" class="easyui-linkbutton" onclick="group();">节点分组</a>
		</fieldset>
	</div>
    <div data-options="region:'west',border:false,split:true" style="width:15%;height:auto;border:1px solid #ccc;">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
	
    <!-- 节点绑定二维码表 -->
	<div data-options="region:'center'" style="height:auto;border:1px solid #ccc;">
		<div id="tab-div1"><table id="dg"></table></div>
	</div>
	
	<!-- 绑定信息 -->
	<div id="dlgBingding" class="easyui-dialog" title="未绑定二维码信息" style="width:700px;height:420px;padding:10px;" closed="true" >
		<div>
		<fieldset style="border-width: 2px; border-color: #0088cc;">
			<legend>绑定条件</legend>
			绑定类型：<input type="text" class="easyui-combobox" id="type" data-options="panelHeight:'auto'" style="width: 20%;"/>
			描述：<input type="text" class="easyui-validatebox" id="describe" style="width: 60%;" />
		</fieldset>
		</div>
		<table id="dgCode"></table>
	</div>
	<!-- 节点分组信息 -->
	<div id="dlgGroup" class="easyui-dialog" title="节点分组信息" style="width:750px;height:200px;padding:10px;" closed="true" >
		<div>
		<fieldset style="border-width: 2px; border-color: #0088cc;">
			<legend>分组条件</legend>
			分组类型：<input type="text" class="easyui-combobox" id="nodeGroup" data-options="panelHeight:'auto'" style="width: 20%;"/>
			<a href="#" class="easyui-linkbutton" onclick="groupSave();">保存</a>
		</fieldset>
		</div>
		<table id="dgNode"></table>
	</div>
	
	<input type="hidden" id="nodeId"/>
	<input type="hidden" id="nodeName"/>
	<input type="hidden" id="url"/>
</body>  
</html>  