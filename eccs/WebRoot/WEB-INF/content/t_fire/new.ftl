<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
    
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a>  >
<a href="/t_fire/list.htm">收款管理列表</a>>
<a href="#">收款管理</a>
</div>
  <!--<form action="/t_liborrow/save.htm" method="post" id="contractForm">  -->
    <form action="/t_fire/save.htm" method="post" id="contractForm" name="contractForm">  
    <div id="content">
    	<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
				收款信息
			</div>
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
         							<!-- <tr>
       								<td colspan="6"> <p ><strong style="margin-left:35px;">项目信息</strong></p></td>
                                      </tr> -->
                                       <tr>
                                        <td width="120"width="50" class="tab_title"><p>项目编号</p></td>
                                         <td colspan="2" style="background-color: #fff;">${invoice.project.no}</td>
                                        <td width="120" class="tab_title"><p>实际应收金额(元)</p></td>
                                         <td colspan="2" style="background-color: #fff;">${pay}</td>
                                       </tr>
                                       <tr>
                                        <td width="120" class="tab_title"><p>项目名称</p></td>
                                         <td colspan="5"  style="background-color: #fff;">${invoice.project.name}</td>
                                       </tr>
                                       <tr>
                                        <td width="120" class="tab_title"><p>累计已开票(元)</p></td>
                                         <td colspan="2" style="background-color: #fff;">${aay}</td>
                                        <td width="120" class="tab_title" ><p >未开票总额(元)</p></td>
                                        
                                         <td colspan="2" style="background-color: #fff;">${bby}</td>
                                       </tr>
                                       <tr>
                                    <td colspan="6"> <p ><strong style="margin-left:35px;">发票信息</strong></p></td>
                                       </tr>
                                       <tr>
                                       <td width="120" class="tab_title"><p>发票编号</p></td>
                                        <td colspan="5"  style="background-color: #fff;">${invoice.invoiceNo}</td>
                                       </tr>
                                       <tr>
                                       <td width="120" class="tab_title"><p>开票金额(元)</p></td>
                                         <td colspan="2" style="background-color: #fff;">${invoice.invoiceAmount}</td>
                                        <td width="120" class="tab_title"><p>应收金额(元)</p></td>
                                       <td colspan="2" style="background-color: #fff;">${invoice.invoiceAmount}</td>
                                       </tr>
                                       <tr>
                                        <td width="120" class="tab_title"><p>已到账金额(元)</p></td>
                                         <td colspan="2" style="background-color: #fff;">${cby}</td>
                                        <td width="120" class="tab_title"><p>未到账金额(元)</p></td>
                                       <input  id="dbyid" type="hidden" value="${dby}"  />
                                         <td colspan="2" style="background-color: #fff;">${dby}</td>
                                       </tr>
                                       <tr>
                                         <td width="120" class="tab_title"><p>发票内容</p></td>
                                         <td colspan="5"  style="background-color: #fff;">${invoice.invoiceContent}</td>
                                       </tr>
                                       <input type="hidden" name="receivables.invoice.id" value="${invoice.id}"/>
                                       <input type="hidden" name="receivables.project.id" value="${invoice.project.id}"/>
                                       <input type="hidden" name="receivables.id" value="${receivables.id}"/>
                                       	<tr>
                                       <td colspan="6"> <p ><strong style="margin-left:35px;">收款信息</strong></p></td>
                                     	</tr>
                                       <tr>
                                        <td width="120" class="tab_title red"><p>本次到账金额(元)</p></td>
                                         <!--<td><input id="arrivalAmounts" onkeypress="return IsNum(event)" type="text"  class="text_a"  name="receivables.arrivalAmount" value="${receivables.arrivalAmount}"/></td>-->
                                         <td colspan="2"><input id="arrivalAmounts"  type="text"  class="text_a"  name="receivables.arrivalAmount" value="${receivables.arrivalAmount}"    onkeyup="value=value.replace(/[^\d\.]/g,'')"/></td>
                                        <td width="120" class="tab_title red"><p>到账日期</p></td>
                                         <td colspan="2"><input id="yeeedate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<#if receivables.arrivalDate!="">${receivables.arrivalDate?string('yyyy-MM-dd')}</#if>" readonly="true" type="text"  class="text_a"  name="receivables.arrivalDate"/></td>
                                       </tr>
                                         <tr>
                                         <td width="120" class="tab_title"><p>备注</p></td>
                                         <td colspan="5">

                                         <textarea  style="width:100%;height:80px;overflow-y:auto; overflow-x:auto; " name="receivables.remark">${receivables.remark}</textarea></td>
                                       </tr>
                                       
                                       <tr>
                                            <td colspan="6" style=" text-align:right;">
                                            <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                           <#if invoice.fistatu==1>
                                            <input type="button" onclick="javascript:submitForm();"  value="保存" style="cursor:pointer;" class="sub" />                                              
                                            </#if>
                                            </td>
                                        </tr> 
           </table>
    </div>
</form>
<script>
	$().ready(function() {
		$("#contractForm").validate({
			rules : {  
	  	    		 'receivables.arrivalAmount' : {  required : true, Amount: true, bdy: $("#dbyid").val()  } ,
	  	    		 'receivables.arrivalDate' : {  required : true } 
					}  
		})
	});


var i=0;
var flag = true;
var id="";
var j=0;
function addpapers(){
var tab1 = document.getElementById("papersTableId");
			if(flag ){
				j=  j+ tab1.rows.length;
				flag =false;
			}else{
				j = j+ 1;
			}
			var newTr = tab1.insertRow();
			newTr.id = j;
			//添加列
			var newTd0 = newTr.insertCell();
			var newTd1 = newTr.insertCell();
			var newTd2 = newTr.insertCell();
			var newTd3 = newTr.insertCell();
			var newTd4 = newTr.insertCell();
			var newTd5 = newTr.insertCell();
			var newTd6 = newTr.insertCell();
			var newTd7 = newTr.insertCell();
			var newTd8 = newTr.insertCell();
			var newTd9 = newTr.insertCell();
			//设置列内容和属性
			newTd0.innerHTML =j; 
			newTd1.innerHTML = '<input type="checkbox" class="text_a ac"  />';
			newTd2.innerHTML = '<input type="text" name="name"  class="text_a"  />';
			newTd3.innerHTML = '<input type="text" name="no"  class="text_a"  />'; 
			newTd4.innerHTML = '<input type="text" name="major"  class="text_a"  />';
			newTd5.innerHTML = '<input type="text" name="grade"  class="text_a"  />';
			newTd6.innerHTML = '<input type="text" name="issuing_unit"  class="text_a"  />';
			newTd7.innerHTML = '<input type="text" name="idate"  class="text_a ccc" readonly="true" type="text" />';
			newTd8.innerHTML = '<input type="text" name="validity_period"  class="text_a"  />';
			newTd9.innerHTML ='<input type="text" name="remark"  class="text_a"  />';
}
/*$(function(){
$("table").on("click",".ccc",function(){
WdatePicker({dateFmt:'yyyy-MM-dd'});
});
var a=fmoney($("#pays").val(),2);
$("#pays").val(a);
var b=fmoney($("#aays").val(),2);
$("#aays").val(b);
var c=fmoney($("#bbys").val(),2);
$("#bbys").val(c);
var d=fmoney($("#aas").val(),2);
$("#aas").val(d);
var e=fmoney($("#bbs").val(),2);
$("#bbs").val(e);
var f=fmoney($("#ccs").val(),2);
$("#ccs").val(f);
var g=fmoney($("#dds").val(),2);
$("#dds").val(g);
});
*/
function removepapers(){
$("input[type='checkbox']:checked.ac").parent().parent().remove();
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
			
			//设置列内容和属性
			newTd0.innerHTML =i ; 
			newTd1.innerHTML = '<input type="checkbox" class="text_a b"  />';
			newTd2.innerHTML = '<input type="text" name="college"  class="text_a"  />';
			newTd3.innerHTML = '<input type="text" name="education"  class="text_a"  />'; 
			newTd4.innerHTML = '<input type="text" name="gdate"  class="text_a ccc" readonly="true" type="text" />';
	}
	function addContact(){
		AddRow();
	}
	function removeContact(){
	$("input[type='checkbox']:checked.b").parent().parent().remove();
	}
	function submitForm(){
/*	var ok=true;
	if($("#arrivalAmounts").val().trim()==""){
	alert("本次到账金额不能为空!");
	ok=false;
	return;
	}else if(!$("#arrivalAmounts").val().trim().match(/^(([1-9]\d*)|\d)(\.\d{1,2})?$/)){
	alert("本次到账金额格式不正确 !");
	ok=false;
	return;
	}
	if($("#yeeedate").val().trim()==""){
	alert("到账日期不能为空!");
	ok=false;
	return;
	}
	var s=$("#arrivalAmounts").val().trim()-$("#dbyid").val().trim();
	if(s>0){
	alert("本次到账金额不能大于未到账金额!");
	ok=false;
	return;
	}
	if(ok){
	$("#contractForm").submit();
	}*/
	$("#contractForm").submit();
}


	function getDept() {
		window.open('/dept/selectDept.htm','_blank','channelmode=yes,width=400,height=500,left=200,top=100');
	}
	
	function doAfterGetDept(obj){
		$('#senderDeptIdId').val(obj.deptId);
		$('#takbranch').val(obj.deptName);
	}

function getUser(){
   	var iWidth=650;                          //弹出窗口的宽度; 
   	var iHeight=500;                         //弹出窗口的高度; 
   	//获得窗口的垂直位置 
   	var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   	//获得窗口的水平位置 
   	var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;      
	window.open('/user/selectUser.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
}
function returnParam(id,name,username){
	$("#userId4").val(id);
	$("#takname").val(name);
}

/*     function fmoney(s, n) {  
        n = n > 0 && n <= 20 ? n : 2;  
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
        var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
        t = "";  
        for (i = 0; i < l.length; i++) {  
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
        }  
        return t.split("").reverse().join("") + "." + r;  
    }  
  function IsNum(e) {
            var k = window.event ? e.keyCode : e.which;
            if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
            } else {
                if (window.event) {
                    window.event.returnValue = false;
                }
                else {
                    e.preventDefault(); //for firefox 
                }
            }
        } */
	</script>