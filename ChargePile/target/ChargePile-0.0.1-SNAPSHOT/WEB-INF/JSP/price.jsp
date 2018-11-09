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
	var html="";
	$(function(){
		InitTreeData();
		var node={id:"",attributes:""};
		loadPrice(node);
		//默认加载时 隐藏   
		$("#startUp").css('display','none'); 
		$("#shutDown").css('display','none');
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
	        	//将树节点放入隐藏input
	        	$("#comOrareId").val(node.id);
	        	//节点层级
	        	$("#deep").val(node.attributes);
	        	//节点凑发事件加载
	        	var bool=$("tr[name='tr']").remove();
	        	if(bool){
	        		loadPrice(node);
	        	}
	        }
	   	});
	}
	
	function loadPrice(node){
		$.post(ctx + "/price/queryPrice",{"comOrareId":node.id,"deep":node.attributes},function(result){
			html=""
			if(result!=null && result.length>0 ){
				if(node.id){
					if(result[0].status == '1'){
						$("#startUp").css('display','none');
						$("#shutDown").css('display','');
					}else{
						$("#startUp").css('display',''); 
						$("#shutDown").css('display','none');
					}
				}else{
					$("#startUp").css('display','none'); 
					$("#shutDown").css('display','none');
				}
				for(var i=0;i<result.length;i++){
					html+="<tr name='tr'><td>分钟:</td><td><input type='text' class='easyui-validatebox' name='mins' onchange='change(1);' value="+result[i].mins+">"+
	    			"<font color='red'> *(单位:分钟)</font></td><td>电价:</td><td>"+
	    			"<input type='text' class='easyui-validatebox' name='price' onchange='change(2);' value="+result[i].price+">"+
	    			"<font color='red'> *(单位:元)</font></td></tr>";
				}
			}else{
				$("#startUp").css('display','none'); 
				$("#shutDown").css('display','none');
			}
			$("#tb").append(html);
		},"json");
	}
	
	function add(){
		$("#tb").append("<tr name='tr'><td>分钟:</td><td><input type='text' class='easyui-validatebox' name='mins' onchange='change(1);'/>"+
        			"<font color='red'> *(单位:分钟)</font></td><td>电价:</td><td>"+
        			"<input type='text' class='easyui-validatebox' name='price' onchange='change(2);'/>"+
        			"<font color='red'> *(单位:元)</font></td></tr>");
	}
	
	function save(){
		var minsArr = new Array;
	    $("input[name='mins']").each(function(index,item){
	    	minsArr[index] = $(this).val();
	    });
	    var mins= minsArr.join(',');
	    if(!mins){
	    	$.messager.alert("提示消息", "分钟行不能为空!", 'info',function(){});  
			setTimeout(function(){
		  		$(".messager-body").window('close');  
			},2000);
			return ;
	    }
	    var priceArr = new Array;
	    $("input[name='price']").each(function(index,item){
	    	priceArr[index] = $(this).val();
	    });
	    var price= priceArr.join(',');
	    if(!price){
	    	$.messager.alert("提示消息", "	电价行不能为空!", 'info',function(){});  
			setTimeout(function(){
		  		$(".messager-body").window('close');  
			},2000);
			return ;
	    }
	    var deep=$("#deep").val();
	    var comOrareId=$("#comOrareId").val();
	    //后台补充 具体错误信息  分钟和电价 同步 不能为空
		$.post(ctx + "/price/addPrice",{"mins":mins,"price":price,"deep":deep,"comOrareId":comOrareId},function(result){
			if(result.state == "SUCCESS"){
				var boo=$("tr[name='tr']").remove();
	        	if(boo){
	        		var param={id:comOrareId,attributes:deep}
					loadPrice(param);
	        	}
				$.messager.alert("提示消息", "保存成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else if(result.state == "FALSE"){
				$.messager.alert("提示消息", "请保持分钟和电价参数同时存在!", 'info',function(){});  
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
	
	//1:启用  -1:禁用
	function updateStatus(num){
		var deep="";
		var comOrareId="";
		if(num=='1'){
			var deep=$("#deep").val();
			var comOrareId=$("#comOrareId").val();
		}
		$.post(ctx + "/price/priceServer",{"deep":deep,"comOrareId":comOrareId},function(result){
			if(result.state == "SUCCESS"){
				$.messager.alert("提示消息", "设置成功!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}else{
				$.messager.alert("提示消息", "服务端请求异常,请检查!", 'info',function(){});  
				setTimeout(function(){
			  		$(".messager-body").window('close');  
				},3000);
			}
		},"json")
	}
</script>
<body class="easyui-layout">
    <div data-options="region:'west',border:false,split:true" style="width:15%;height:auto;border:1px solid #ccc;">
		<ul id="tt" class="easyui-tree"></ul>
	</div>
	<div class="main" data-options="region:'center'" >
        <table id="tb">
        	<tr>
        		<td colspan="4" >
        			<a href="#" class="easyui-linkbutton" onclick="add();">新增</a>
					<a href="#" class="easyui-linkbutton" onclick="save();">保存</a>
					<a id="startUp" href="#" class="easyui-linkbutton" onclick="updateStatus(1);">启用</a>
					<a id="shutDown" href="#" class="easyui-linkbutton" onclick="updateStatus(-1);">禁用</a>
        		</td>
        	</tr> 
        </table>
    </div>
	<input type="hidden" id="comOrareId"/>
	<input type="hidden" id="deep"/>
</body>  
</html>  