    <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a>  >
<a href="/t_customer/list.htm">客户登记-列表</a>>
<a href="#">客户登记</a>
</div>
  <form action="/t_customer/save.htm" method="post" id="contractForm">  
    <div id="content">
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
            <div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">客户信息</div>                          
                                       <tr>
                                       <input name="t_Customer.id" value="${t_Customer.id}" hidden/>
                                         <td width="50" class="tab_title red"><p>客户性质</p></td>
                                         <td width="300">
                                         <select id="cusnatures" name="t_Customer.cusNature">
											<option value="">请选择</option>
											<option value="1" <#if  t_Customer.cusNature==1>selected='selected'</#if> >委托单位</option>
										    <option value="2" <#if  t_Customer.cusNature==2>selected='selected'</#if> >建设单位</option>
											<option value="3" <#if  t_Customer.cusNature==3>selected='selected'</#if> >施工单位</option>
											<option value="4" <#if  t_Customer.cusNature==4>selected='selected'</#if> >设计单位</option>
										 </select>
                                         </td>
                                         <td width="50" class="tab_title"><p>客户类别</p></td>
                                         <td width="300">
                                         <select name="t_Customer.cusType">
											<option value="">请选择</option>
						                    <option value="1" <#if  t_Customer.cusType==1>selected='selected'</#if> >企业客户</option>
										    <option value="2" <#if  t_Customer.cusType==2>selected='selected'</#if> >政府客户</option>
											<option value="3" <#if  t_Customer.cusType==3>selected='selected'</#if> >其它</option>
										 </select>
                                         </td>
                                       </tr>
                                       
                                       <tr>
                                         <td width="50" class="tab_title red"><p>客户名称</p></td>
                                         <td colspan="3"><input id="cusName"  type="text"  class="text_a"  name="t_Customer.cusName" value="${t_Customer.cusName}" onblur="javascript:checkNO();"/></td>
                                       </tr>
                                       <tr>
                                         <td  width="50" class="tab_title"><p>组织机构代码</p></td>
                                         <td width="300"><input  type="text"   class="text_a" name="t_Customer.ogCode" value="${t_Customer.ogCode}"/></td>
                                          <td width="50" class="tab_title"><p>客户信用级别</p></td>
                                         <td width="300">
                                          <select name="t_Customer.cusLevel">
											<option value="">请选择</option>
						                    <option value="1" <#if  t_Customer.cusType==1>selected='selected'</#if> >A</option>
										    <option value="2" <#if  t_Customer.cusType==2>selected='selected'</#if> >B</option>
											<option value="3" <#if  t_Customer.cusType==3>selected='selected'</#if> >C</option>
											<option value="4" <#if  t_Customer.cusType==4>selected='selected'</#if> >D</option>
										  </select>
                                         </td>
                                       </tr>
                                      <tr>
                                         <td  width="50" class="tab_title"><p>法人代表</p></td>
                                         <td width="300"><input  type="text"   class="text_a" name="t_Customer.lega" value="${t_Customer.lega}"/></td>
                                          <td width="50" class="tab_title"><p>客户主页</p></td>
                                         <td width="300"><input  id="urls" type="text"  class="text_a" name="t_Customer.cusHomepage" value="${t_Customer.cusHomepage}"/></td>
                                       </tr>
                                        <tr>
                                         <td  width="50" class="tab_title"><p>邮政编码</p></td>
                                         <td width="300"><input id="postCode" type="text"   class="text_a" name="t_Customer.postCode" value="${t_Customer.postCode}"  onkeyup='this.value=this.value.replace(/\D/gi,"")'/></td>
                                          <td width="50" class="tab_title"><p>传真</p></td>
                                         <td width="300"><input id="fax" type="text" placeholder="格式010-12345678或0719-12345678 "  class="text_a" name="t_Customer.fax" value="${t_Customer.fax}"/></td>
                                       </tr>
                                       <tr>
                                         <td  width="50" class="tab_title " ><p>开户银行</p></td>
                                         <td width="300"><input id="bankAccount" type="text"   class="text_a"  name="t_Customer.bankAccount" value="${t_Customer.bankAccount}"/></td>
                                          <td width="50" class="tab_title "><p>开户账号</p></td>
                                         <td width="300"><input id="accountOpening"  type="text" maxlength='23' class="text_a" name="t_Customer.accountOpening" value="${t_Customer.accountOpening}" onkeyup='this.value=this.value.replace(/\D/gi,"")'/></td>
                                       </tr>
                                       <tr>
                                         <td  width="50" class="tab_title"><p>电子邮箱</p></td>
                                         <td width="300"><input  id="email" type="text"   class="text_a"  name="t_Customer.email" value="${t_Customer.email}"/></td>
                                          <td width="50" class="tab_title"><p>办公电话</p></td>
                                         <td width="300"><input   id="officePhone" type="text"   class="text_a" name="t_Customer.officePhone" value="${t_Customer.officePhone}"/></td>
                                       </tr>
                                       <tr>
                                       		<td  width="50" class="tab_title"><p>详细地址</p></td>
                                         	<td colspan="3"><textarea  style="width:100%;height:80px;" name="t_Customer.address">${t_Customer.address}</textarea></td>
                                       </tr>
                                       <tr>
                                       		<td  width="50" class="tab_title"><p>备注</p></td>
                                         	<td colspan="3"><textarea  style="width:100%;height:80px;" name="t_Customer.remark">${t_Customer.remark}</textarea></td>
                                       </tr>  
                                       
                                       <tr>
				    <td colspan="4">
				    
				        <div style=" display:block; text-align:right; border:1px solid #dadada; border-bottom:0px" class="add_link" >
				                                            <a href="javascript:addContact();">新增</a> <a href="javascript:removeContact()">删除</a>
				     </div>
				    <table border="1" id="fileTableId"  cellspacing="1" cellpadding="1" class="list_table4">
				    
				  <tr>
				    <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;">序号</td>
				     <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;"><input id="qxcheckbox1"  type="checkbox"/></td>
				    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">联系人</td>
				    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">部门</td>
				    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">职务</td>
				     <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">电话</td>
				      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">手机</td>
				      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1; ">E-Mail</td>
				      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">QQ</td>
				      <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">默认联系人</td>
				      <td style="text-align:center; font-weight:bold; background-color:#d3e0f1;">备注</td>
				  </tr>
				    <#list t_Contact.list as t_Contact>
					    <input type="hidden" value=${t_Contact.id}/>
					  <tr>
					    <td style=" text-align:center;background-color:#fff;">${t_Contact_index + 1}</td>
					    <td style=" text-align:center;background-color:#fff;"><input name="a" type="checkbox" autocomplete="off" value="${t_Contact.id}" class="a" /></td>
					    <td style=" text-align:center;background-color:#fff;"> <input type="text" name="t_Contactcontact"  class="text_a" value="${t_Contact.contact }" /><input name="t_Contactid" type="hidden" value=${t_Contact.id}></td>
					      <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_ContactdeptName"  class="text_a" value="${t_Contact.deptName }" /></td>
					    <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_Contactpost"  class="text_a" value="${t_Contact.post }" /></td>
					       <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_Contactphone"  class="text_a"  value="${t_Contact.phone }"/></td>
					    <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_Contacttelephone"  class="text_a"  value="${t_Contact.telephone }"/></td>
					    <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_Contactemail"  class="text_a" value="${t_Contact.email }" /></td>
					    <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_Contactqq"  class="text_a"  value="${t_Contact.qq }"/></td>
					       <td style=" text-align:center;background-color:#fff;">
					       <#if t_Contact.defContact==1>
					       <div><input style="margin:10px auto" type="radio" name="t_ContactdefContact"  checked="checked" class="text_a bbbb" value="1"/><input name="t_ContactdefContact" value="0" hidden/></div>
					       </#if>
					       <#if t_Contact.defContact!=1>
					       <div><input style="margin:10px auto" type="radio" name="t_ContactdefContact"  class="text_a bbbb" value="1"/><input name="t_ContactdefContact" value="0" hidden/></div>
					       </#if>
					       </td>
					    <td style=" text-align:center;background-color:#fff;"><input type="text" name="t_Contactremark"  class="text_a"  value="${t_Contact.remark }"/></td>
					  </tr>
					</#list>
				</table></td>
				    </tr>
				    
                                        
                                         <tr><td colspan="4"><div class="t_sub_adds" onclick="attachment();">添加附件</div><div class="t_sub_delete" onclick="detelefile();">删除附件</div></td></tr> 
                                        <tr>
                                        <td colspan="4" id="we">
                                        <table width="100%" border="1" id="contactTableId"  cellspacing="1" cellpadding="1" class="list_table4">
                                        <tr>
                                        <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;">序号</td>
									     <td style="text-align:center; font-weight:bold; background-color:#d3e0f1; width:42px;"><input id="qxcheckbox2"  type="checkbox"/></td>
									    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;width:25%">文件名称</td>
									    <td style=" text-align:center; font-weight:bold; background-color:#d3e0f1;">文件描述</td>
                                        </tr>
                                         <#list list as t_file>
										  <tr>
										   <td style=" text-align:center;background-color:#fff;">${t_file_index + 1}</td>
										    <td style=" text-align:center;background-color:#fff;"><input name="files" type="checkbox" autocomplete="off" value="${t_file.id}" class="files" /></td>
										    <td style=" text-align:center;background-color:#fff;"> ${t_file.name}</td>
										      <td style=" text-align:center;background-color:#fff;">${t_file.remarks}</td>
										  </tr>
										  </#list>
                                        </table>
                                        </td>
                                        </tr>
                                        <tr>
                                            <td colspan="4" style=" text-align:right;">
                                            <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="cursor:pointer;" class="sub"/>
                                            <input type="button" onclick="javascript:submitForm();"  value="保存" style="cursor:pointer;" class="sub" />                                              
                                            </td>
                                        </tr> 
           </table>
    </div>
</form>
</div>
<style>
    td{
    word-break: break-all;
    text-align:center;
    }
    </style>
<script>
	$().ready(function() {
	
		$("#contractForm").validate({
			rules : {  
	  	    		 't_Customer.cusNature' : { required : true } ,
	  	    		 't_Customer.cusName' : { required : true } ,
	  	    		 't_Customer.email' : { email : true } ,
	  	    		 't_Customer.cusHomepage' : { url : true } ,
	  	    		 't_Customer.postCode' : { postCode: true } ,
	  	    		 't_Customer.officePhone' : { isPhone  : true } ,
	  	    		 't_Customer.fax' : { isTel  : true } ,
	  	    	     't_Customer.accountOpening' : { creditcard: true } ,
	  	    		 //'qq' : { qq: true } ,
	  	    		// 't_Contactqq' : { qq: true } ,
					}  
		})
	});
	
 $(function(){  
        $('#accountOpening').keyup(function(){  
          var value=$(this).val().replace(/\s/g,'').replace(/(\d{4})(?=\d)/g,"$1 ");    
          $(this).val(value)  
        })   
      })
$('#accountOpening').keypress(function(event) {
			      if (!$.browser.mozilla) {
			       if (event.keyCode && (event.keyCode < 48 || event.keyCode > 57)) // IE
			        event.preventDefault();
			      }
			      else {
			       if (event.charCode && (event.charCode < 48 || event.charCode > 57)) //firefox
			        event.preventDefault();
			      }
			     });      
      
       
var b=0;
function detelefile(){

		var id = "";
      $('input:checkbox[name=files]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      var cc=$("input[type='checkbox']:checked.c").length;
      if(id=="" && cc==0){
      alert("请选择删除项!");
      return;
      } else if (!confirm("确定删除?")) {
			return;
	 }
      if(id!=""){
		var url = "/t_customer/deletefile.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					//window.parent.location.reload();
					//location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
		}
$("input[type='checkbox']:checked.files").parent().parent().remove();
$("input[type='checkbox']:checked.c").parent().parent().remove();
}
		function attachment() {
				var iWidth=650;                          //弹出窗口的宽度; 
		   		var iHeight=500;                         //弹出窗口的高度; 
		   		//获得窗口的垂直位置 
		   		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		   		//获得窗口的水平位置 
		   		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;      
		        window.open('/t_file/filet_cus.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
		}
		function returnFiletCus(id,name,remarks){
				b=$("#contactTableId tr").length-1;
				b++;
				var htmls='<tr>';
				htmls+='<td style="text-align:center; width:42px;">'+b+' <input  name="file_id"  type="hidden" value="'+id+'"/></td>';
				htmls+='<td style="text-align:center; width:42px;"><input  type="checkbox" autocomplete="off" value="'+id+'" class="c" /></td>';
				htmls+='<td style="text-align:center; width:42px;">'+name+'</td>';
				htmls+='<td style="text-align:center; width:42px;">'+remarks+'</td>';
				htmls+='</tr>'
				$("#contactTableId").append(htmls);
		}
$(function(){
//全选
$("#qxcheckbox1").click(function(){
		
		if($("#qxcheckbox1").is(':checked')){
		$("[name='a']").attr("checked",'true');
		$(".b").attr("checked",'true');
		}else{
		$("[name='a']").removeAttr("checked",'true');
		$(".b").removeAttr("checked",'true');
		}
		$("input[name='a']").click(function(){
		$("#qxcheckbox1").removeAttr("checked",'true')
		});
		$(".b").click(function(){
		$("#qxcheckbox1").removeAttr("checked",'true')
		});
		});
$("#qxcheckbox2").click(function(){
		
		if($("#qxcheckbox2").is(':checked')){
		$("[name='files']").attr("checked",'true');
		$(".c").attr("checked",'true');
		}else{
		$("[name='files']").removeAttr("checked",'true');
		$(".c").removeAttr("checked",'true');
		}
		
		$("input[name='files']").click(function(){
		$("#qxcheckbox2").removeAttr("checked",'true')
		});
		$(".c").click(function(){
		$("#qxcheckbox2").removeAttr("checked",'true')
		});
		});
		
//单选
$(".bbbb").click(function(){
$(".zzzz").attr("checked",false); 
});
$("td").on("click",".zzzz",function(){
  $(".bbbb").attr("checked",false); 
});
$('#postCode').keypress(function(event) {
			      if (!$.browser.mozilla) {
			       if (event.keyCode && (event.keyCode < 48 || event.keyCode > 57)) // IE
			        event.preventDefault();
			      }
			      else {
			       if (event.charCode && (event.charCode < 48 || event.charCode > 57)) //firefox
			        event.preventDefault();
			      }
			     });
});
var i=0;
var flag = true;
function AddRow()
	{
			//添加一行
			var tab1 = document.getElementById("fileTableId");
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
			var newTd7 = newTr.insertCell();
			var newTd8 = newTr.insertCell();
			var newTd9 = newTr.insertCell();
			var newTd10 = newTr.insertCell();
			//设置列内容和属性
			newTd0.innerHTML ='<p style="text-align:center;">'+i+'</p>' ; 
			newTd1.innerHTML = '<input style="margin:10px auto" type="checkbox" class="text_a b"  />';
			newTd2.innerHTML = '<input type="text" name="contact"  class="text_a"  />';
			newTd3.innerHTML = '<input type="text" name="deptName"  class="text_a"  />'; 
			newTd4.innerHTML = '<input type="text" name="post"  class="text_a"  />';
			newTd5.innerHTML = '<input type="text" name="phone"  class="text_a"  />';
			newTd6.innerHTML = '<input type="text" name="telephone"  class="text_a"  />';
			newTd7.innerHTML = '<input type="text" name="email"  class="text_a"  />';
			newTd8.innerHTML = '<input type="text"  name="qq"  class="text_a"  />';
			newTd9.innerHTML = '<div><input style="margin:10px auto" type="radio" name="defContact"  class="text_a  zzzz" value="1"/><input name="defContact" value="0" hidden/></div>';
			newTd10.innerHTML = '<input type="text" name="remark"  class="text_a"  />';
	}
	function addContact(){
		AddRow();
	}
		function checkNO(){
		var cusName = $('#cusName').val().trim();
		if(cusName==""){
		  return;
		}
		var url = "/t_customer/checkNO.htm" ;
		$.ajax({
			type:"post",
			url:url,
			data : { 't_Customer.cusName': cusName },
			asyn:true,
			dataType:'text',
			success:function(d){
				if (d == '1') {
				} else if(d == '0'){
					alert('该客户已登记!');
					//$('#cusName').val('')
				}
				}
		});
	}
	function removeContact(){

		var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      var cc=$("input[type='checkbox']:checked.b").length;
      //alert(id+"=="+cc);
      if(id=="" && cc==0){
      alert("请选择删除项!");
      return;
      } else 	if (!confirm("确定删除?")) {
			return;
		}
      if(id!=""){
		var url = "/t_customer/deletecls.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					//window.parent.location.reload();
					//location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
		}
	$("input[type='checkbox']:checked.a").parent().parent().remove();
	$("input[type='checkbox']:checked.b").parent().parent().remove();
	}
	function submitForm(){
	var ok=true;
/*	if($("#cusnatures").val()==""){
	alert("请选择客户性质!!");
	ok=false;
	return;
	}
	if($("#cusName").val().trim()==""){
	alert("客户名称不能为空!");
	ok=false;
	return;
	}
		if($("#bankAccount").val().trim()==""){
	alert("开户银行不能为空!");
	ok=false;
	return;
	}
	if($("#accountOpening").val().trim()==""){
	alert("开户账号不能为空!");
	ok=false;
	return;
	}
	for(var i=0;i<$("input[name='contact']").length;i++){
	if($("input[name='contact']").eq(i).val().trim()==""){
   alert("联系人不能为空!");
	ok=false;
	return;
	}
	}
  var email=$("#email").val().trim();
  if(!email.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/))
  {
  if(email!=""){
  alert("邮箱格式不正确！");
   ok=false;
	return;
  } 
 }
  	var urls=$("#urls").val().trim();
  if(!urls.match( /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/))
  {
  if(urls!=""){
  alert("客户主页格式不正确!");
   ok=false;
	return;
  } 
  }
  var postCode=$("#postCode").val().trim();
  if(postCode!=""){
  if(!postCode.match( /^[1-9][0-9]{5}$/)){
  alert("邮政编码格式不正确!");
	ok=false;
	return;
  }
  }
  var fax=$("#fax").val().trim();
  if(fax!=""){
  if(!fax.match(/^\d{3}-\d{8}|\d{4}-\d{7}$/)){
  alert("传真格式不正确!");
	ok=false;
	return;
  }
  }
 var officePhone=$("#officePhone").val().trim();
  if(officePhone!=""){
  if(!officePhone.match(/^\d{3}-\d{8}|\d{4}-\d{7}$/)){
  alert("办公电话格式不正确!");
	ok=false;
	return;
  }
  }
 var accountOpening=$("#accountOpening").val().trim();
  if(accountOpening!=""&&!accountOpening.match(/^\d{16}|\d{19}$/)){
  alert("开户账号格式不正确!");
	ok=false;
	return;
  }*/
  for(var i=0;i<$("input[name='qq']").length;i++){
	if($("input[name='qq']").eq(i).val().trim()!=""){
	if(!($("input[name='qq']").eq(i).val().trim()).match(/^[1-9]\d{4,10}$/)){
	alert("qq格式不正确!");
	ok=false;
	return;
	}
	}
	}
	 for(var i=0;i<$("input[name='email']").length;i++){
	if($("input[name='email']").eq(i).val().trim()!=""){
	if(!($("input[name='email']").eq(i).val().trim()).match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)){
	alert("Email格式不正确!");
	ok=false;
	return;
	}
	}
	}
	 for(var i=0;i<$("input[name='phone']").length;i++){
	if($("input[name='phone']").eq(i).val().trim()!=""){
	if(!($("input[name='phone']").eq(i).val().trim()).match(/^\d{3}-\d{8}|\d{4}-\d{7}|\d{7}$/)){
	alert("电话格式不正确!");
	ok=false;
	return;
	}
	}
	}
	 for(var i=0;i<$("input[name='telephone']").length;i++){
	if($("input[name='telephone']").eq(i).val().trim()!=""){
	if(!($("input[name='telephone']").eq(i).val().trim()).match(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/)){
	alert("手机格式不正确!");
	ok=false;
	return;
	}
	}
	}
	if(ok){
	$('input[name="defContact"]:checked').siblings("input").remove();
	$("#contractForm").submit();
	}
	 
}
	</script>