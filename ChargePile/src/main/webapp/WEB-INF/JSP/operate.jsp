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
	var mobile = /^1[3|4|5|7|8]\d{9}$/;	//验证手机号码
	var phone  = /^0\d{2,3}-?\d{7,8}$/;	//验证座机号码
	var lngRe  = /^(((\d|[1-9]\d|1[1-7]\d|0)\.\d{0,4})|(\d|[1-9]\d|1[1-7]\d|0{1,3})|180\.0{0,4}|180)$/;	//验证经度
	var latRe  = /^([0-8]?\d{1}\.\d{0,4}|90\.0{0,4}|[0-8]?\d{1}|90)$/;	//验证纬度
	
	var params={};
	var html="";
	$(function(){
		InitTreeData();
		$("#binding").css('display','none'); 
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
	        	$('#dlgBingding').dialog('close');
	        	$('#dlgPort').dialog('close');
	        	//将区域id放入隐藏input
	        	$("#communityId").val(node.id);
	        	$("#nodeName").val(node.text);
	        	$("#deep").val(node.attributes);
	        	if(node.id!=''){
	        		$("#binding").css('display',''); 
	        	}else{
	        		$("#binding").css('display','none'); 
	        	}
	        	
	        	//节点凑发事件加载dg表格
	        	query();
	        }
	   	});
	}
	
	function query(){
		var communityId = $("#communityId").val();
		var nodeName = $("#nodeName").val();
		var deep = $("#deep").val();
		var device_no = $("#device_no").val();
		var card_no = $("#card_no").val();
		var status = $("#status").combobox('getValue');
		params={"comOrareId":communityId,"deep":deep,"deviceNo":device_no,"cardNo":card_no,"status":status}
		$("#dg").datagrid({
			url:ctx + '/device/queryOperateDevice',
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
				{field:'siteName',title:'投放点地址',width:90,align:'center'},
	          	{field:'nodeName',title:'所属区域',width:90,align:'center',formatter: function(value, row, index){
	          		return nodeName;
	          	}},
	          	{field:'name',title:'设备名称',width:90,align:'center'},
	          	{field:'deviceNo',title:'设备编号',width:80,align:'center'},
	          	{field:'cardNo',title:'卡号',width:90,align:'center'},
	          	{field:'deviceIp',title:'通信号',width:80,align:'center'},
	          	{field:'portNum',title:'端口数量',width:60,align:'center',formatter: function(value, row, index){
	          		var obj = JSON.stringify(row);
	          		if(row.status=='1'){
	          			return "<a href='#' class='easyui-linkbutton' onclick='dlgPort("+obj+");'>"+value+"</a>";; 
	          		}else{
	          			return value;
	          		}
	          	}},
	          	{field:'telPhone',title:'物管号码',width:90,align:'center'},
	          	{field:'lng',title:'经度',width:90,align:'center'},
	          	{field:'lat',title:'纬度',width:90,align:'center'},
	          	{field:'startTime',title:'充电起始时间',width:90,align:'center'},
	          	{field:'endTime',title:'充电结束时间',width:90,align:'center'},
	          	{field:'count',title:'使用次数',width:50,align:'center'},
	          	{field:'status',title:'设备状态',width:50,align:'center',formatter: function(value, row, index){
	          		if(value=='0'){
	          			return '禁用'; 
	          		}else if(value=='1'){
	          			return '启用';
	          		}else{
	          			return '暂未设置';
	          		}
	          	}},
	          	{field:'endLoadTime',title:'最后登录时间',width:120,align:'center'},
	          	{field:'cz',title:'操作',width:50,align:'center',formatter: function(value, row, index){
	          		var obj = JSON.stringify(row);
	            	if(row.status=='1'){
	            		return "<a href='#' class='easyui-linkbutton' onclick='update(0,"+obj+");'>禁用</a>";
	            	}else{
		           		return "<a href='#' class='easyui-linkbutton' onclick='update(1,"+obj+");'>启用</a>";  
	            	}
	          	}}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { siteName: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'siteName',  
            			colspan:15
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
	
	//勾选设备
	function setDevice(){
		var comOrareId=$("#communityId").val();
		var deep=$("#deep").val();
		$.post(ctx + "/device/queryDeviceByComm",{"comOrareId":comOrareId,"deep":deep},function(result){
			html=""
			var count=null;
			for(var i=0;i<result.length;i++){ 
				if(result[i].communityId!=null || result[i].areaId!=null){
					count=i;
					html+="<input type='checkbox'  checked='checked' name='device' value="+result[i].id+"><span name='reName'>"+result[i].name+"<span/>"
				}else{
					html+="<input type='checkbox' name='device' value="+result[i].id+"><span name='reName'>"+result[i].name+"<span/>"					
				}
			}
			$("#deviceBox").append(html);
			if(count!=null){
				$("#siteName").val(result[count].siteName);
				$("#telPhone").val(result[count].telPhone);
				$("#lng").val(result[count].lng);
				$("#lat").val(result[count].lat);
				$("#startTime").val(result[count].startTime);
				$("#endTime").val(result[count].endTime);
			}else{
				common();
			}
		},"json")
	}
	
	function change(number){
		var siteName=$("#siteName").val();
		var telPhone=$("#telPhone").val();
		var lng=$("#lng").val();
		var lat=$("#lat").val();
		if(number == '1'){
			if(!siteName || re.test(siteName)){
				$("#siteNameMsg").text("请输入投放点名称！");
				return ;
			}else{
				$("#siteNameMsg").text("");
			}
		}else if(number == '2'){
			if(!lng || re.test(lng)){
				$("#lngMsg").text("请输入经度！");
				return ;
			}else{
				if(!lngRe.test(lng)){
					$("#lngMsg").text("请输入正确的经度！");
				}else{
					$("#lngMsg").text("");
				}
			}
		}else if(number == '3'){
			if(!lat || re.test(lat)){
				$("#latMsg").text("请输入纬度！");
				return ;
			}else{
				if(!latRe.test(lat)){
					$("#latMsg").text("请输入正确的纬度！");
				}else{
					$("#latMsg").text("");
				}
			}
		}else{
			if(telPhone){
				if(!mobile.test(telPhone) && !phone.test(telPhone)){
					$("#telPhoneMsg").text("请输入正确联系方式！");
				}else{
					$("#telPhoneMsg").text("");
				}
			}
		}
	}
	
	//绑定设备弹出层
	function binding(){
    	$('#dlgBingding').dialog('open');
    	$('#dlgPort').dialog('close');
    	var bool1=$("input[name='device']").remove();
    	var bool2=$("span[name='reName']").remove();
    	if(bool1){
        	setDevice();
    	}
	}
	
	//确认绑定设备
	function save(){
		params={}
		if($("input[name='device']:checked").length==0){
			$.messager.alert("提示消息", "请勾选需要绑定的设备!", 'info',function(){});  
			setTimeout(function(){
		  		$(".messager-body").window('close');  
			},2000);
			return ;
		}
		var checkIds = "";
		$("input:checkbox[name='device']:checked").each(function() {
			checkIds += $(this).val() + ",";
		});
		           
		var communityId = $("#communityId").val();
		var deep = $("#deep").val();
		var siteName = $("#siteName").val();
		if(!siteName || re.test(siteName)){
			$("#siteNameMsg").text("请输入投放点名称！");
			return ;
		}else{
			$("#siteNameMsg").text("");
		}
		
		var telPhone = $("#telPhone").val();
		if(telPhone && !re.test(telPhone)){
			if(!mobile.test(telPhone) && !phone.test(telPhone)){
				$("#telPhoneMsg").text("请输入正确联系方式！");
			}else{
				$("#telPhoneMsg").text("");
			}
		}
		
		var lng = $("#lng").val();
		if(!lng || re.test(lng)){
			$("#lngMsg").text("请输入经度！");
			return ;
		}else{
			if(!lngRe.test(lng)){
				$("#lngMsg").text("请输入正确的经度！");
			}else{
				$("#lngMsg").text("");
			}
		}
		
		var lat = $("#lat").val();
		if(!lat || re.test(lat)){
			$("#latMsg").text("请输入纬度！");
			return ;
		}else{
			if(!latRe.test(lat)){
				$("#latMsg").text("请输入正确的纬度！");
			}else{
				$("#latMsg").text("");
			}
		}
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		params={
			"ids":checkIds,
			"deep":deep,
			"communityId":communityId,
			"siteName":siteName,
			"telPhone":telPhone,
			"lng":lng,
			"lat":lat,
			"startTime":startTime,
			"endTime":endTime
		}
		$.post(ctx + "/device/updateRelatDevice",{data:JSON.stringify(params)},function(result){
			$('#dlgBingding').dialog('close');
			if(result.state == "SUCCESS"){
				$("#dg").datagrid("reload");
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
	
	function cancel(){
		$('#dlgBingding').dialog('close');
		common();
	}
	
	function common(){
		$("#siteName").val("");
		$("#telPhone").val("");
		$("#lng").val("");
		$("#lat").val("");
		$("#startTime").val("00:00:00");
		$("#endTime").val("24:00:00");
		
		$("#siteNameMsg").text("");
		$("#telPhoneMsg").text("");
		$("#lngMsg").text("");
		$("#latMsg").text("");
	}
	
	//修改设备状态
	function update(status,obj){
		$.post(ctx + "/device/updateStatusById",{"status":status,"id":obj.id,"deviceNum":obj.deviceNo},function(result){
			if(result.state == "SUCCESS"){
				$("#dg").datagrid("reload");
				$.messager.alert("提示消息", "修改成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "FALSE"){
				$.messager.alert("提示消息", "参数出错,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "SFALSE"){
				$.messager.alert("提示消息", "服务端请求异常,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else{
				$.messager.alert("提示消息", "修改失败!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}
		},"json")
	}
	
	//设备端口表格
	function dlgPort(obj){
		$('#dlgPort').dialog('open');
		$('#dlgBingding').dialog('close');
		//加载对应设备的用电量信息
		$("#dgPort").datagrid({
			url:ctx + '/port/queryPort',
			queryParams: {"deviceId":obj.id},
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
				{field:'portNo',title:'端口编号',width:60,align:'center'},
	          	{field:'name',title:'所属设备名称',width:120,align:'center',formatter: function(value, row, index){
	          		return obj.name;
	          	}},
	          	{field:'deviceNo',title:'所属设备编号',width:100,align:'center',formatter: function(value, row, index){
	          		return obj.deviceNo;
	          	}},
	          	{field:'status',title:'端口状态',width:100,align:'center',formatter: function(value, row, index){
	          		if(value=='0'){
	          			return '禁用'; 
	          		}else if(value=='1'){
	          			return '启用';
	          		}
	          	}},
	          	{field:'cz',title:'操作',width:100,align:'center',formatter: function(value, row, index){
	            	if(row.status=='1'){
	            		return "<a href='#' class='easyui-linkbutton' onclick='updatePort(0,"+row.id+");'>禁用</a>";
	            	}else{
		           		return "<a href='#' class='easyui-linkbutton' onclick='updatePort(1,"+row.id+");'>启用</a>";  
	            	}
	          	}}
			]],
			onLoadSuccess: function(data) {
				if(data.total == 0){
					//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
                	$(this).datagrid('appendRow', { portNo: '<div style="text-align:center;color:red">没有相关记录！</div>'
                	}).datagrid('mergeCells',{  
            			index: 0,  
            			field: 'portNo',  
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
	
	//修改设备端口状态
	function updatePort(status,id){
		$.post(ctx + "/port/updatePort",{"status":status,"id":id},function(result){
			if(result.state == "SUCCESS"){
				$("#dgPort").datagrid("reload");
				$.messager.alert("提示消息", "修改成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "FALSE"){
				$.messager.alert("提示消息", "参数出错,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else{
				$.messager.alert("提示消息", "修改失败!", 'info',function(){});  
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
		<legend>查询条件</legend>
		<td>设备编号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="device_no"/>
		</td>
		<td>卡号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="card_no"/>
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
			<a href="#" class="easyui-linkbutton" onclick="query();">查询</a>
			<a id="binding" href="#" class="easyui-linkbutton" onclick="binding();">新增站点</a>
		</td>
		</fieldset>
	</div>
    <div data-options="region:'west',border:false,split:true" style="width:15%;height:auto;border:1px solid #ccc;">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
	
    <!-- 设备运营表 -->
	<div data-options="region:'center'" style="height:auto;border:1px solid #ccc;">
        <table id="dg"></table>
	</div>
	
	<!-- 绑定信息 -->
	<div id="dlgBingding" class="easyui-dialog" title="绑定设备信息" style="width:620px;height:280px;padding:10px;" closed="true" >
		<div id="deviceBox" style="height:auto;border:1px solid #ccc;"></div>
		<div style="height:auto;border:1px solid #ccc;margin-top:20px;" >
			<table>
				<tr>
					<td style="text-align: right;width:120px">投放点名称：</td>
					<td >
						<input type="text" class="easyui-validatebox" id="siteName" onchange="change('1');"/>
						<font id="siteNameMsg" color="red"></font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;width:120px">联系方式：</td>
					<td >
						<input type="text" class="easyui-validatebox" id="telPhone" onchange="change('4');"/>
						<font id="telPhoneMsg" color="red"></font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">经度：</td>
					<td >
						<input type="text" class="easyui-validatebox" id="lng" onchange="change('2');" />
						<font id="lngMsg" color="red"></font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">纬度：</td>
					<td >
						<input type="text" class="easyui-validatebox" id="lat" onchange="change('3');" />
						<font id="latMsg" color="red"></font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">起始时间：</td>
					<td >
						<input type="text" class="easyui-validatebox" id="startTime" value="00:00:00" />
						<font>* 默认 00:00:00</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">结束时间：</td>
					<td >
						<input type="text" class="easyui-validatebox" id="endTime" value="24:00:00" />
						<font>* 默认 24:00:00</font>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" colspan="2">
						<input type="hidden" id="communityId"/>
						<input type="hidden" id="nodeName"/>
						<input type="hidden" id="deep"/>
						<a href="#" class="easyui-linkbutton" onclick="save();">确定</a>
						<a href="#" class="easyui-linkbutton" onclick="cancel();">取消</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 端口信息 -->
	<div id="dlgPort" class="easyui-dialog" title="设备端口信息表" style="width:700px;height:370px;padding:10px;" closed="true" >
		<table id="dgPort"></table>
	</div>
</body>  
</html>  