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
		$("#add").css('display','none');
		$("#update").css('display','none'); 
		$("#del").css('display','none'); 
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
	        	//切换小区默认关闭弹出层
	        	$('#dlgEle').dialog('close');
	        	//将区域id放入隐藏input
	        	$("#communityId").val(node.id);
	        	$("#nodeName").val(node.text);
	        	$("#deep").val(node.attributes);
	        	if(node.attributes == '9'){
	        		$("#add").css('display','');
	        		$("#update").css('display',''); 
	        		$("#del").css('display',''); 
	        		//节点凑发事件加载dg表格
		        	query();
	        	}else{
	        		$("#add").css('display','none');
	        		$("#update").css('display','none'); 
	        		$("#del").css('display','none'); 
	        	}
	        }
	   	});
	}
	
	function query(){
		var communityId = $("#communityId").val();
		var nodeName=$("#nodeName").val();
		var eleName = $("#ele_name").val();
		var regCode = $("#reg_code").val();
		var eleBrand = $("#ele_brand").combobox('getValue');
		params={"communityId":communityId,"eleName":eleName,"regCode":regCode,"eleBrand":eleBrand}
		$("#dg").datagrid({
			url:ctx + '/elevator/queryElevator',
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
				{field:'ck',checkbox:"true",width:10,align:'center'},
				{field:'eleName',title:'电梯名称',width:150,align:'center'},
	          	{field:'regCode',title:'电梯注册码',width:120,align:'center'},
	          	{field:'commName',title:'所属小区名称',width:120,align:'center',formatter: function(value, row, index){
	          		return nodeName;
	          	}},
	          	{field:'eleBrand',title:'电梯品牌',width:80,align:'center'},
	          	{field:'companyName',title:'所属公司名称',width:100,align:'center'},
	          	{field:'createTimeStr',title:'录入时间',width:120,align:'center'},
	          	{field:'createUser',title:'录入用户',width:80,align:'center'}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					$("#dg").datagrid('hideColumn', 'ck');
					//添加一个新数据行
                	$(this).datagrid('appendRow', {eleName: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'eleName',  
            			colspan:7
        			});  
                	//隐藏分页导航条
                	$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').hide();
            	}else{
            		$("#dg").datagrid('showColumn', 'ck');
	            	//显示分页导航条
            		$(this).closest('div.datagrid-wrap').find('div.datagrid-pager').show();
            	}
            },
		});
	}
	
	function change(number){
		var eleName=$("#eleName").val();
		var regCode=$("#regCode").val();
		var eleBrand=$("#eleBrand").val();
		if(number == '1'){
			if(!eleName || re.test(eleName)){
				$("#eleNameMsg").text("请输入电梯名称！");
				return ;
			}else{
				$("#eleNameMsg").text("");
			}
		}else if(number == '2'){
			if(!regCode || re.test(regCode)){
				$("#regCodeMsg").text("请输入电梯注册码！");
				return ;
			}else{
				$("#regCodeMsg").text("");
			}
		}else{
			if(!eleBrand || re.test(eleBrand)){
				$("#eleBrandMsg").text("请输入注册品牌！");
			}else{
				$("#eleBrandMsg").text("");
			}
		}
	}
	
	//新增电梯信息
	function add(){
    	$('#dlgEle').dialog('open');
    	common();
	}
	
	//确认绑定设备
	function save(){
		params={}
		var id=$("#id").val();
		var communityId=$("#communityId").val();
		var eleName=$("#eleName").val();
		if(!eleName || re.test(eleName)){
			$("#eleNameMsg").text("请输入电梯名称！");
			return ;
		}else{
			$("#eleNameMsg").text("");
		}
		var regCode=$("#regCode").val();
		if(!regCode || re.test(regCode)){
			$("#regCodeMsg").text("请输入电梯注册码！");
			return ;
		}else{
			$("#regCodeMsg").text("");
		}
		var eleBrand=$("#eleBrand").val();
		if(!eleBrand || re.test(eleBrand)){
			$("#eleBrandMsg").text("请输入注册品牌！");
		}else{
			$("#eleBrandMsg").text("");
		}
		var companyName=$("#companyName").val();
		params={
			"id":id,
			"communityId":communityId,
			"eleName":eleName,
			"regCode":regCode,
			"eleBrand":eleBrand,
			"companyName":companyName
		}
		$.post(ctx + "/elevator/saveElevator",{data:JSON.stringify(params)},function(result){
			cancel();
			if(result.state == "SUCCESS"){
				$("#dg").datagrid("reload");
				$.messager.alert("提示消息", "保存成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "FALSE"){
				$.messager.alert("提示消息", "参数出错,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "SAME"){
				$.messager.alert("提示消息", "电梯名称或注册码重复!", 'info',function(){});  
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
	
	function cancel(){
		$('#dlgEle').dialog('close');
		common();
	}
	
	function common(){
		$("#id").val("");
		$("#eleName").val("");
		$("#regCode").val("");
		$("#eleBrand").val("");
		$("#companyName").val("");
		
		$("#eleNameMsg").text("");
		$("#regCodeMsg").text("");
		$("#eleBrandMsg").text("");
	}
	
	//修改电梯 
	function update(){
		//获取选中行的数据,返回的是数组  
	    var selectRows = $("#dg").datagrid("getSelections");
	    //如果没有选中行的话，提示信息  
	    if (selectRows.length != 1) {  
	        $.messager.alert("提示消息", "请选择一条要修改的记录！", 'info');  
	        return;
	    } else {
	    	$("#dlgEle").dialog("open");
	    	$("#id").val(selectRows[0].id);
	    	$("#eleName").val(selectRows[0].eleName);
			$("#regCode").val(selectRows[0].regCode);
			$("#eleBrand").val(selectRows[0].eleBrand);
			$("#companyName").val(selectRows[0].companyName);
	    }
	}
	
	
	// 删除数据
	function del(){
		//获取选中行的数据,返回的是数组  
	    var selectRows = $("#dg").datagrid("getSelections");  
	    //如果没有选中行的话，提示信息  
	    if (selectRows.length < 1) {  
	        $.messager.alert("提示消息", "请选择要删除的记录！", 'info');  
	        return;  
	    }  
	    //如果选中行了，则要进行判断  
	    $.messager.confirm("确认消息", "确定要删除所选记录吗？", function (isDelete) {  
	        //如果为真的话  
	        if (isDelete) {  
	            //定义变量值  
	            var strIds = "";  
	            //拼接字符串，这里也可以使用数组，作用一样  
	            for (var i = 0; i < selectRows.length; i++) {  
	                strIds += selectRows[i].id + ",";  
	            }  
	            //循环切割  
	            strIds = strIds.substr(0, strIds.length - 1);
	            $.post(ctx + "/elevator/delElevator",{"ids":strIds},function (result) {  
	                if (result.state == "SUCCESS") {  
	                    $("#dg").datagrid("reload"); //删除成功后
	                    $.messager.alert("提示消息", "删除成功!", 'info',function(){});  
	    				setTimeout(function(){
	    			  		$(".messager-body").window('close');  
	    				},3000);
	                } else {  
	                    $.messager.alert('提示信息', '删除失败，请联系管理员！',function(){}); 
	    				setTimeout(function(){
	    			  		$(".messager-body").window('close');  
	    				},3000);
	                }  
	            }, "json");  
	        }  
	    });  
	}
</script>
</head>  
<body class="easyui-layout">
	<div data-options="region:'north',border:false,split:true" style="width:99%;height:12%;border:1px solid #ccc;">
		<fieldset style="border-width: 2px; border-color: #0088cc;">
		<legend>查询条件</legend>
		<td>电梯名称：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="ele_name"/>
		</td>
		<td>电梯注册码：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="reg_code"/>
		</td>
		<td>电梯品牌：</td>
		<td >
			<select class="easyui-combobox" id="ele_brand" data-options="panelHeight:'auto'" >
				<option value="">--请选择--</option>
				<option value="1">启用</option>
				<option value="0">停用</option>
			</select>
		</td>
		<td>
			<a href="#" class="easyui-linkbutton" onclick="query();">查询</a>
			<a id="add" href="#" class="easyui-linkbutton" onclick="add();">新增</a>
			<a id="update" href="#" class="easyui-linkbutton" onclick="update();">修改</a>
			<a id="del" href="#" class="easyui-linkbutton" onclick="del();">删除</a>
		</td>
		</fieldset>
	</div>
    <div data-options="region:'west',border:false,split:true" style="width:15%;height:auto;border:1px solid #ccc;">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
	
    <!-- 小区电梯表 -->
	<div data-options="region:'center'" style="height:auto;border:1px solid #ccc;">
        <table id="dg"></table>
	</div>
	
	<!-- 新增电梯信息 -->
	<div id="dlgEle" class="easyui-dialog" title="小区电梯信息" style="width:460px;height:220px;padding:10px;" closed="true" >
		<table>
			<tr>
				<td style="text-align: right;width:120px">电梯名称：</td>
				<td >
					<input type="text" class="easyui-validatebox" id="eleName" onchange="change('1');"/>
					<font id="eleNameMsg" color="red"></font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;width:120px">电梯注册码：</td>
				<td >
					<input type="text" class="easyui-validatebox" id="regCode" onchange="change('2');"/>
					<font id="regCodeMsg" color="red"></font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">电梯品牌：</td>
				<td >
					<input type="text" class="easyui-validatebox" id="eleBrand" onchange="change('3');" />
					<font id="eleBrandMsg" color="red"></font>
				</td>
			</tr>
			<tr>
				<td style="text-align: right;">所属公司：</td>
				<td >
					<input type="text" class="easyui-validatebox" id="companyName" />
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2">
					<input type="hidden" id="id"/>
					<input type="hidden" id="communityId"/>
					<input type="hidden" id="nodeName"/>
					<input type="hidden" id="deep"/>
					<a href="#" class="easyui-linkbutton" onclick="save();">确定</a>
					<a href="#" class="easyui-linkbutton" onclick="cancel();">取消</a>
				</td>
			</tr>
		</table>
	</div>
	
</body>  
</html>  