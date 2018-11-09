    <link rel="stylesheet" type="text/css" href="/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="/easyui/themes/icon.css">
    <script type="text/javascript" src="/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" >
			$(function(){
			    $('#test').treegrid({ 
			       // url:"/test/data/treegrid_data.json", 
			        data:  ${jsong},
			        idField:'id', 
			        treeField:'name', 
			        animate:"true",
			        rownumbers:"true",
			        width : '98%',
			        height : 720,
			        columns:[[ 
			            {title:'权限名称',field:'name',formatter:function(value,rowData,rowIndex){
				            
			                return "<input class='ck_checkbox'    TYPE='checkbox' value="+rowData.id+"  id=ck_"+rowData.id+" ></INPUT>" + rowData.name;
			        },width:240}, 
			            {field:'id',title:'权限编号',width:280}, 
			            {field:'url',title:'地址',width:320}
			        ]] 
			    });

					<#list myResMap?keys as r>
					id = 'ck_' + '${r}';
					$("#" + id).attr('checked', true);
					</#list>
			});
			
			function set_power_status(){ 
			    var idList = ""; 
			     $("input:checked").each(function(){
			        var id = $(this).attr("id");
			        if(id.indexOf("ceshi_")>-1)
			            idList += id.replace("ceshi_",'')+',';
			     })
			    alert(idList);
			}
			
			function consleclick(){
			    var node = $('#test').treegrid('expandAll',2);
			}
	</script>
	
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/systemManager.htm">系统管理</a> >
<a href="javascript:;">我的权限</a>
</div>

<form action="save.htm" method="post" id="submitForm">
	<input type="hidden" name="roleId" id="roleId" value="${roleId}">
	<input type="hidden" name="resIdList" id="resIdList" value="">
    <table id="test" title="权限分配"  style="width:80%;height:300px;" >       
    </table>   
</form>



