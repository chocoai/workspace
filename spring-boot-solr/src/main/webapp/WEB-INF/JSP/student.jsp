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
<script type="text/javascript" src="../static/js/jquery-form.js"></script>
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
		url:ctx+'/main/gradeTree',
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
        	$("#schoolId").val(1);
        	$("#gradeId").val(1);
        	$("#classId").val(1);
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
	var schoolId = 1;
	var gradeId = 1;
	var classId = 1;
	var studentName = $("#studentName").val();
	var studentId = $("#studentId").val();
	params={"id":studentId,"name":studentName,"schoolId":schoolId,"gradeId":gradeId,"classId":classId}
	$("#dg").datagrid({
		url:ctx + '/main/queryStudent',
		queryParams: params,
		rownumbers : true,
		fitColumns : true,
		border : false,
		striped : true,
		fit : true,
		singleSelect : false,
		pagination : true,
		pageSize : 2,
		pageList : [ 1, 2 ,1],
		columns:[[
			{field:'id',title:'学生ID',width:90,align:'center'},
          	{field:'name',title:'名字',width:90,align:'center'},
          	{field:'gender',title:'性别',width:80,align:'center',formatter: function(value, row, index){
          		if(value=='0'){
          			return '男'; 
          		}else if(value=='1'){
          			return '女';
          		}
          	}},
          	{field:'age',title:'年龄',width:90,align:'center'},
          	{field:'headpic',title:'头像',width:150,align:'center',formatter: function(value, row, index){
           		return "<img src="+value+" onerror=\"javascript:this.src='..\/static\/images\/user.png';\" height='100' width='150' >";  
          	}},
          	{field:'schoolId',title:'学校名',width:120,align:'center',formatter: function(value, row, index){
          		return schoolName(value);
          	}},
          	{field:'gradeId',title:'年级',width:120,align:'center'},
          	{field:'classId',title:'班级',width:120,align:'center'},
          	{field:'cz',title:'编辑',width:20,align:'center',formatter: function(value, row, index){
            	var obj = JSON.stringify(row);
           		return "<a href='#' class='easyui-linkbutton' onclick='update("+obj+");'>编辑</a>";  
          	}}
		]],
		onLoadSuccess: function(data) {
			if(data.total == 0){
				//添加一个新数据行，第一列的值为你需要的提示信息，然后将其他列合并到第一列来，注意修改colspan参数为你columns配置的总列数
            	$(this).datagrid('appendRow', { siteName: '<div style="text-align:center;color:red">没有相关记录！</div>'
            	}).datagrid('mergeCells',{  
        			index: 0,  
        			field: 'id',  
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

function schoolName(value){
	var schoolId = 1;
	var schoolName ;
	$.ajax({
		type : "post",
		url : ctx + '/main/schoolName',
		async : false,
		data : {'schoolId':schoolId},
		dataType : "json",
		traditional : true,
		success : function(data) {
			schoolName = data;
		}
	})
	return schoolName;
}

function binding(){
	$('#dlgBingding').dialog('open');
	$("#id").val("");
	$("#name").val("");
	$("#age").val("");
}

function update(obj){
	$('#dlgBingding').dialog('open');
	$("#id").val(obj.id);
	$("#name").val(obj.name);
	if(obj.gender==0){
		$("input[name='gender'][value=0]").attr("checked",true); 
	}else if(obj.gender==1){
		$("input[name='gender'][value=1]").attr("checked",true);
	}
	$("#age").val(obj.age);
}

function cancel(){
	$('#dlgBingding').dialog('close');
}

function uploadPic(){
	$('#uploadPics').dialog('open');
}
function save(){
	var id = $('#id').val();
	var name = $('#name').val();
	var gender = $("input[name='gender']:checked").val();
	var age = $('#age').val();
	var schoolId = $('#schoolId').val();
	var gradeId = $('#gradeId').val();
	var classId = $('#classId').val();
	params={
			"id":id,
			"name":name,
			"gender":gender,
			"age":age,
			"schoolId":schoolId,
			"gradeId":gradeId,
			"classId":classId,
		}
	if(id!=null || id==""){
		uploadImage();
	}
	$.post(ctx + "/main/addStudent",{data:JSON.stringify(params)},function(result){
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

function uploadImage(){
	//获取form数据
    $("#importFileForm").ajaxSubmit({    
        type:"post",  //提交方式    
        url:"uploadPic", //请求url    
        success:function(data){ //提交成功的回调函数    
        	$.messager.alert("提示消息",data);  
        	setTimeout(function(){
		  		$(".messager-body").window('close');  
			},1000);
        }    
    });    
	 /* $.ajax({
         type: "POST",
         url: "uploadPic",
         data: formData,
         async: false,
         cache: false,
         contentType: false,
         processData: false,
         success: function(data) {
             alert("上传成功");
         },
         error: function(XMLHttpRequest, textStatus, errorThrown) {
             alert("上传失败，请检查网络后重试");
         }
     }); */
}
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',border:false,split:true" style="width:99%;height:12%;border:1px solid #ccc;">
		<fieldset style="border-width: 2px; border-color: #0088cc;">
		<legend>查询条件</legend>
		<td>学生编号：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="studentId"/>
		</td>
		<td>名字：</td>
		<td >
			<input type="text" class="easyui-validatebox" id="studentName"/>
		</td>
		<td>
			<a href="#" class="easyui-linkbutton" onclick="query();">查询</a>
			<a id="binding" href="#" class="easyui-linkbutton" onclick="binding();">新增学生</a>
			<a id="uploadPic" href="#" class="easyui-linkbutton" onclick="uploadPic();">上传图片</a>
		</td>
		</fieldset>
	</div>
    <div data-options="region:'west',border:false,split:true" style="width:15%;height:auto;border:1px solid #ccc;">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
	
	<!-- 学生信息表 -->
	<div data-options="region:'center'" style="height:auto;border:1px solid #ccc;">
        <table id="dg"></table>
	</div>
	
	
	<!-- 新增学生信息窗口 -->
	<div id="dlgBingding" class="easyui-dialog" title="新增学生信息" style="width:620px;height:280px;padding:10px;" closed="true" >
		<div id="studentBox" style="height:auto;border:1px solid #ccc;"></div>
		<div style="height:auto;border:1px solid #ccc;margin-top:20px;" >
			<table>
				<tr>
					<td style="text-align: right;width:120px">学生姓名：</td>
					<td >
						<input type="text" class="easyui-validatebox" name="name" id="name" />
						<font id="siteNameMsg" color="red"></font>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;width:120px">性别：</td>
					<td >
						<input type="radio" class="easyui-validatebox" name="gender" value="0" id="gender" />男
						<input type="radio" class="easyui-validatebox" name="gender" value="1" id="gender" />女
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">年龄：</td>
					<td >
						<input type="text" name="age" class="easyui-validatebox" id="age"  />
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">头像：</td>
					<td >
						<form  id="importFileForm" action="uploadPic" method="POST" enctype="multipart/form-data"accept="image/gif, image/jpeg,image/jpg, image/png"> 
						<input type="hidden" name="id" id="id"/>  
				            <div style="width: 100%;height: 100%">  
				                <div style="text-align: center;margin-top: 20px">  
				                     <input  class="easyui-filebox" id="fileImport" name="fileImport" style="width:260px;"><br/>   
				                 </div>  
				            </div>  
				        </form>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" colspan="2">
						<input type="hidden" name="schoolId" id="schoolId"/>
						<input type="hidden" name="gradeId" id="gradeId"/>
						<input type="hidden" name="classId" id="classId"/>
						<a href="#" class="easyui-linkbutton" onclick="save();">确定</a>
						<a href="#" class="easyui-linkbutton" onclick="cancel();">取消<d>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>