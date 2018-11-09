<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/invoice/list.htm">发票管理</a> >
<a href="#">添加发票</a>
</div>
  <form action="/invoice/save.htm" method="post" id="contractForm">  
	
		<div id="content">
			<table border="1" cellspacing="1" cellpadding="1" class="input_table">
				<tr>
				  <td class="tab_title red"><p>选择项目</p></td>
	                 <td width="226">
			        <select name="project" onchange="showProj(this.value)">
			        	<option value="" >请选择</option>
						<#list prjList as project>
							<option value="${project.id}" >${project.name}</option>
						</#list>
					</select>
				</td>
				</tr>
				<input type="hidden" id="pid" value="${projectId}"/>
				 <#list tiList as invoice>
				 <#if invoice.project.id = projectId >
				<tr>
					<td colspan="1" class="tab_title">
						<p>项目编号</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" />
					</td>
					<td colspan="1" class="tab_title">
						<p>实际应收总额(元)</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>项目名称</p>
					</td>
					<td colspan="5">
						<input id="" type="text" class="text_a" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>累计已开票(元)</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" readonly="readonly"/>
					</td>
					<td colspan="1" class="tab_title">
						<p>未开票总额(元)</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" readonly="readonly"/>
					</td>
				</tr>
				</#if>
				</#list>
				<tr>
					<td class="lefttd" colspan="6" style="text-align: center; font-size: large;">开票信息</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>付款单位</p>
					</td>
					<td colspan="5">
						<input id="" type="text" class="text_a" />
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title red">
						<p>发票编号</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" />
					</td>
					<td colspan="1" class="tab_title">
						<p>发票类型</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" />
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title red">
						<p>开票金额</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" />
					</td>
					<td colspan="1" class="tab_title red">
						<p>开票日期</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" />
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>发票内容</p>
					</td>
					<td colspan="5">
						<textarea id="" class="text_a" rows="2" cols="80" class="text_a"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>备注</p>
					</td>
					<td colspan="5">
						<textarea id="" class="text_a" rows="2" cols="80" class="text_a"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title red">
						<p>发票领用人</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" />
					</td>
					<td colspan="1" class="tab_title red">
						<p>领用日期</p>
					</td>
					<td colspan="2">
						<input id="" type="text" class="text_a" />
					</td>
				</tr>
				<tr>
					<td colspan="6" style="text-align: right;">
						<input type="button" id="esc" value="取消" class="sub" />
						<input type="button" id="saveAndEsc" value="保存并关闭" class="sub" />
						<input type="button" id="save" value="保存" class="sub" />
					</td>
				</tr>
			</table>
		</div>
	
</form>
<script>
	 function showProj(pid){
	 	$("#pid").val(pid);
	 }


	var i=0;
	var flag = true;
	
$().ready(function() {  
  	      $("#contractForm").validate({
				rules : {  
				  'contract.name' : { required : true , minlength : 2} ,
				  'contract.allAmount' : { required : true , number:true } , 
				  'contract.contractDept' : { required : true } ,
				  'contract.contractSignDate' : { required : true } ,
				  'contract.planEndDate' : { required : true } ,
				  'contract.chargeRemark' : { required : true } ,
				  'contract.diedAmount' : { number:true } , 
				  'contract.splitAmount' : { number:true } , 
				   'contract.depositAmount' : { number:true } , 
				  'contract.serviceTime' : { digits:true } 
				}
			})
}); 
function receivechange(receive){
	var value = receive.value;
	if(value==1){ //
	       $("#doContactId").val(1);
	}
}

function contactchange(contact){
	var value = contact.value;
	if(value==0){ //不签合同 一定不投标
       $("#receiveTypeId").val(0);
	}
}

function submitForm(){
	$("#bidResultId").removeAttr("disabled"); 
	 $("#contractForm").submit();
}


	function AddRow()
	{
			//添加一行
			var tab1 = document.getElementById("contactTableId");
			if(flag ){
				i =  i+ tab1.rows.length;
				flag =false;
			}else{
				i =  i+ 1;
			}
			var newTr = tab1.insertRow();
			newTr.id = i;
			//添加列
			var newTd0 = newTr.insertCell();
			var newTd1 = newTr.insertCell();
			var newTd2 = newTr.insertCell();
			var newTd3 = newTr.insertCell();
			var newTd4 = newTr.insertCell();
			var newTd5 = newTr.insertCell();
			var newTd6 = newTr.insertCell();
			//设置列内容和属性
			newTd0.innerHTML = '<input type="text" name="deptName"  class="text_a"  />'; 
			newTd1.innerHTML = '<input type="text" name="deptType"  class="text_a"  />';
			newTd2.innerHTML = '<input type="text" name="userName"  class="text_a"  />';
			newTd3.innerHTML = '<input type="text" name="officeTel"  class="text_a"  />'; 
			newTd4.innerHTML = '<input type="text" name="tel"  class="text_a"  />';
			newTd5.innerHTML = '<input type="text" name="isTrack"  class="text_a"  />';
			newTd6.innerHTML = '<a onclick="javascript:deleteRow('+i+')">删除</a>';
	}
	function deleteRow(id){
	      
		  $('#'+id).remove();
	}

	function addContact(){
		AddRow();
	}
	
	function getDept() {
		window.open('/dept/selectDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetDept(obj){
		$('#senderDeptIdId').val(obj.deptId);
		$('#senderDeptNameId').val(obj.deptName);
	}

</script>

