    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
<a href="/t_invoice/list.htm">发票管理列表</a>>
<a href="#">发票管理</a>
</div>
  <form action="/t_invoice/save.htm?invoice.id=${invoice.id}" method="post" id="contractForm" name="contractForm">  
	
		<div id="content">
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				发票信息
			</div>
			<table border="1" cellspacing="1" cellpadding="1" class="input_table" width="800px">
			 <!-- <tr>
       		<td colspan="6"> <p ><strong style="margin-left:35px;">项目信息</strong></p></td>
             </tr> -->
				<tr>
				  <td class="tab_title red"width="120px"><p>选择项目</p></td>
				  <input name="invoice.project.id"  id="senderDeptIdId"   type="hidden" value="${invoice.project.id}"  />
	                 <td  colspan="5">
			       <!-- <select id="projectid" name="invoice.project.id" <#if invoice.id!="">disabled="disabled"</#if>>
			        	<option value="" >请选择</option>
						<#list list as project>
							<option value="${project.id}" <#if  invoice.project.id=='${project.id}'>selected='selected'</#if>>${project.name}</option>
						</#list>
					</select>-->
					<input id="t_liborrowsdeptid"  onclick="getProject()" readonly="true"  type="text"  class="text_a"  value="${invoice.project.name}"/>
				</td>
				</tr>
				<input type="hidden" id="pid" value="${projectId}"/>
				<tr>
					<td colspan="1" class="tab_title">
						<p>项目编号</p>
					</td>
					<td colspan="2">
						<input id="yi" type="text" class="text_a" disabled="value" style="background-color:#fff;border:1px solid #fff" value="${invoice.project.no}"/>
					</td>
					<td colspan="1" class="tab_title">
						<p>实际应收总额(元)</p>
					</td>
					<td colspan="2">
						<input id="er" type="text" class="text_a" disabled="value" style="background-color:#fff;border:1px solid #fff"  value="${pay}"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>项目名称</p>
					</td>
					<td colspan="5">
						<input id="san" type="text" class="text_a" disabled="value" style="background-color:#fff;border:1px solid #fff" value="${invoice.project.name}"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>累计已开票(元)</p>
					</td>
					<td colspan="2">
						<input id="si" type="text" class="text_a" disabled="value" style="background-color:#fff;border:1px solid #fff" value="${aay}"/>
					</td>
					<td colspan="1" class="tab_title">
						<p>未开票总额(元)</p>
					</td>
					<td colspan="2">
					<input  id="bayid" type="hidden" value="${bay}"  />
						<input id="wu" type="text" class="text_a" disabled="value" style="background-color:#fff;border:1px solid #fff" value="${bay}"/>
					</td>
				</tr>
				<tr>
					<td colspan="6"> <p ><strong style="margin-left:35px;">发票信息</strong></p></td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title red">
						<p>付款单位</p>
					</td>
					<td colspan="5">
						<input id="invoicepayCompany" type="text" class="text_a" name="invoice.payCompany" value="${invoice.payCompany}"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title red">
						<p>发票编号</p>
					</td>
					<td colspan="2">
					     <input id="invos" type="hidden" value="${invos}"/>
						<!--<input id="invoiceNos" type="text" class="text_a" name="invoice.invoiceNo" readonly="readonly" value="${invoice.invoiceNo}"/>-->
						       <input id="invoiceNos" type="text" class="text_a" name="invoice.invoiceNo"  value="${invoice.invoiceNo}"/>
					</td>
					<td colspan="1" class="tab_title">
						<p>发票类型</p>
					</td>
					<td colspan="2">
	         			<select id="reStatus" name="invoice.invoiceType" >
							<option value="" <#if  invoice.invoiceType=="">selected='selected'</#if>>--请选择--</option>
							<option value="1" <#if  invoice.invoiceType==1>selected='selected'</#if>>普通发票</option>
							<option value="2" <#if  invoice.invoiceType==2>selected='selected'</#if>>增值税专用发票</option>
							<option value="3" <#if  invoice.invoiceType==3>selected='selected'</#if>>定额发票</option>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title red">
						<p>开票金额(元)</p>
					</td>
					<td colspan="2">
						<input id="invoiceAmounts" type="text" class="text_a" pag="${bay} " onkeypress="return IsNum(event)" name="invoice.invoiceAmount" value="${invoice.invoiceAmount}"/>
					</td>
					<td colspan="1" class="tab_title red">
						<p>开票日期</p>
					</td>
					<td colspan="2">
						<input id="invoiceDates" type="text" class="text_a" name="invoice.invoiceDate" onFocus="var litime=$dp.$('litime');WdatePicker({onpicked:function(){},maxDate:'#F{$dp.$D(\'litime\')}'})" readonly="true"  value="<#if invoice.invoiceDate!=''>${invoice.invoiceDate?string('yyyy-MM-dd')}</#if>"/>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>发票内容</p>
					</td>
					<td colspan="6">
						<textarea id="" class="text_a" style="width:100%;height:80px;overflow-y:auto; overflow-x:auto;" class="text_a"  name="invoice.invoiceContent">${invoice.invoiceContent}</textarea>
					</td>
				</tr>
				<tr>
					<td colspan="1" class="tab_title">
						<p>备注</p>
					</td>
					<td colspan="6">
						<textarea id="" class="text_a"style="width:100%;height:80px;overflow-y:auto; overflow-x:auto;" class="text_a" name="invoice.remark">${invoice.remark}</textarea>
					</td>
				</tr>
				<tr>
				 <td width="50" class="tab_title red"><p>发票领用人</p></td>
                      <input name="invoice.invoiceUser.id" id="userId4"    value="${invoice.invoiceUser.id}" type="hidden" class="text_c"/>
                      <td colspan="2"><input  id="takname" type="text" readonly= "true" onclick="getUser()"  class="text_a" name="invoice.invoiceUser.name" value="${invoice.invoiceUser.name}"/></td>
					<td colspan="1" class="tab_title red">
						<p>领用日期</p>
					</td>
					<td colspan="2">
						<input id="litime" type="text" class="text_a" name="invoice.userDate" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'invoiceDates\')}'})" value="<#if invoice.userDate!=''>${invoice.userDate?string('yyyy-MM-dd')}</#if>" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="6" style="text-align: right;">
						<input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
						<input type="button" onclick="save()" value="保存" class="sub"  style="cursor:pointer;"/>
					</td>
				</tr>
			</table>
		</div>
	
</form>
<script>
	$().ready(function() {
	
		$("#contractForm").validate({
			rules : {  
	  	    		 'invoice.payCompany' : {  required : true } ,
	  	    		 'invoice.invoiceAmount' : { required: true , Amount: true, } ,
	  	    		 'invoice.invoiceDate' : { required : true},
	  	    		 'invoice.invoiceUser.name' : {  required : true},
	  	    		 'invoice.userDate' : { required : true}
					}  
		})
	});


function save(){
/*ok=true;
if($("#t_liborrowsdeptid").val().trim()==""){
	alert("请选择项目!");
	ok=false;
	return;
	}
	if($("#invoiceNos").val().trim()!=""){
	var str=$("#invos").val();
	var strs=str.split(",,,");
	for(var i=0;i<strs.length;i++){
	if(strs[i]==$("#invoiceNos").val().trim()){
	alert("发票编号重复!");
	ok=false;
	return;
	}
	}
	}
 if(!$("#invoiceAmounts").val().trim().match(/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)){
	alert("开票金额格式不正确 !");
	ok=false;
	return;
	}

	var s=$("#invoiceAmounts").val().trim() - $("#bayid").val().trim();
	if(s>0){
	alert("开票金额不能大于未开票金额!");
	ok=false;
	return;
	}
if(ok){
	$("#contractForm").submit();
	}*/
	$("#contractForm").submit();
}
	$(function(){
	//shows();
	var er=$("#er").val();
	if(er!=""){
	$("#er").val(fmoney(er,2));
	}
	var si=$("#si").val();
	if(si!=""){
	$("#si").val(fmoney(si,2));
	}
	var wu=$("#wu").val();
	if(wu!=""){
	$("#wu").val(fmoney(wu,2));
	}
	$("#projectid option").click(function(){
	   var id=$("#projectid  option:selected").val().trim();
		if(id==""){
			$("#yi").val("");
				$("#er").val("");
				$("#san").val("");
				$("#si").val("");
				$("#wu").val("");
				return;
		}
		var url = "/t_invoice/choose.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
			var list=d.split(",");
			$("#yi").val(list[0]);
				var ers=fmoney(list[1],2)
				$("#er").val(ers);
				$("#san").val(list[2]);
				var sis=fmoney(list[3],2)
				$("#si").val(sis);
				var wus=fmoney(list[4],2)
				$("#wu").val(wus);
			}
		});
		
	});
	});
	function getUser(){
	var iWidth=650;                          //弹出窗口的宽度; 
   	var iHeight=500;                         //弹出窗口的高度; 
   	//获得窗口的垂直位置 
   	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   	//获得窗口的水平位置 
   	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;      
	window.open('/user/selectUser2.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
}
	function returnParam2(userId, name, userName){
		$("#userId4").val(userId);
		$("#takname").val(name);
	}

  function fmoney(s, n) {  
        n = n > 0 && n <= 20 ? n : 2;  
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
        var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
        t = "";  
        for (i = 0; i < l.length; i++) {  
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
        }  
        return t.split("").reverse().join("") + "." + r;  
    }  
    function shows(){
     var ids=$("#projectid  option:selected").val().trim();
		if(ids==""){
			$("#yi").val("");
				$("#er").val("");
				$("#san").val("");
				$("#si").val("");
				$("#wu").val("");
				return;
		}
		var url = "/t_invoice/choose.htm?Stringid=" + ids;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
			var list=d.split(",");
			$("#yi").val(list[0]);
				var ers=fmoney(list[1],2)
				$("#er").val(ers);
				$("#san").val(list[2]);
				var sis=fmoney(list[3],2)
				$("#si").val(sis);
				var wus=fmoney(list[4],2)
				$("#wu").val(wus);
			}
		});
    }
    function getProject(){
   	var iWidth=1000;                          //弹出窗口的宽度; 
   	var iHeight=500;                         //弹出窗口的高度; 
   	//获得窗口的垂直位置 
   	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   	//获得窗口的水平位置 
   	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;     
	window.open('/t_invoice/choose.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	
    }
    
    function returninvoice(id,no,payAmount,name,totalinvoice,noinvoice){
		$("#yi").val(no);
		$("#er").val(fmoney(payAmount,2));
		$("#san").val(name);
		$("#si").val(fmoney(totalinvoice,2));
		$("#wu").val(fmoney(noinvoice,2));
		$("#bayid").val(noinvoice);
		$("#invoiceAmounts").attr("pag",noinvoice);
		
		$("#t_liborrowsdeptid").val(name);
		$("#senderDeptIdId").val(id);
    }
    
    
    
    function IsNum(e) {
            var k = window.event ? e.keyCode : e.which;
            if (((k >= 48) && (k <= 57)) || k == 8 || k == 0|| k==46) {
            } else {
                if (window.event) {
                    window.event.returnValue = false;
                }
                else {
                    e.preventDefault(); //for firefox 
                }
            }
        } 
</script>

