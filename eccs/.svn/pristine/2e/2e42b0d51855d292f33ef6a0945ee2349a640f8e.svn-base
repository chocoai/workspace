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
				            
			                return "<input class='ck_checkbox'   onchange=checkItem('"+rowData.id+"')   TYPE='checkbox' value="+rowData.id+"  id=ck_"+rowData.id+" ></INPUT>" + rowData.name;
			        },width:240}, 
			            {field:'id',title:'权限编号',width:280}, 
			            {field:'url',title:'地址',width:320}
			        ]] 
			    });
				<#list list as r>
					id =  '${r.id}';
					$("#ck_" + id).attr('checked', true);
			
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
<a href="/role/list.htm">系统管理</a> >
<a href="/role/list.htm">角色列表</a> >
<a href="javascript:;">修改权限</a>
<input type="button" onclick="javascript:submitForm();" style=" float:right; width:100px; height:28px; background-color:#ec7a00;-moz-border-radius: 3px;-webkit-border-radius: 3px; border-radius: 3px; color:#fff; margin-top:8px; margin-right:20px;" value="保存权限" />
</div>
<form action="save.htm" method="post" id="submitForm">
	<input type="hidden" name="roleId" id="roleId" value="${roleId}">
	<input type="hidden" name="resIdList" id="resIdList" value="">
    <table id="test" title="权限分配"  style="width:400px;height:300px" >       
    </table>   
</form>
<script>
	
	function submitForm() {
		var resIdList = "";
		$(".ck_checkbox").each(function() {
			var flag = $(this).attr('checked');
			var id = $(this).val();
			if (flag == 'checked') {
				resIdList = resIdList + "," + id;
			}
		});
		$('#resIdList').val(resIdList);
		$('#submitForm').submit();
	}

	function checkItem(v) {
		//alert(v)
		var id = "ck_" + v;   //001
		var ck = $('#' + id).attr('checked');
		
		//大类取消，小类都取消
		if (ck != 'checked' ) {
			$(".ck_checkbox").each(function() {
				var cid = $(this).attr('id');
				if (cid.startWith(id)) {
					$(this).attr('checked', false);
				}
				
			});

			
		}else{
			$(".ck_checkbox").each(function() {
				var cid = $(this).attr('id');
				if (cid.startWith(id)) {
					$(this).attr('checked', true);
				}
				if (id.startWith(cid)) {
					$(this).attr('checked', true);
				}
			});
		}



	}
</script>


