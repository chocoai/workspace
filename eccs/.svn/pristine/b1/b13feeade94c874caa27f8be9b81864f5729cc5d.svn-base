<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<script src="../../../js/jquery.js" type="text/javascript"></script>
<script src="../../../js/common.js" type="text/javascript"></script>
  <form action="/project/savecustomer.htm" method="post" id="contractForm3" name="contractForm3">  
    <div id="content">
        <table border="1" cellspacing="1" cellpadding="1" class="input_table">
                                      
                                       <tr>
                                         <td width="100" class="tab_title red" ><p>客户性质:</p></td>
                                         <td width="300">
                                         <select id="cusnatures" name="t_Customer.cusNature" style="width:100%;">
											<option value="">请选择</option>
											<option value="1" <#if  t_Customer.cusNature==1>selected='selected'</#if> >委托单位</option>
										    <option value="2" <#if  t_Customer.cusNature==2>selected='selected'</#if> >建设单位</option>
											<option value="3" <#if  t_Customer.cusNature==3>selected='selected'</#if> >施工单位</option>
											<option value="4" <#if  t_Customer.cusNature==4>selected='selected'</#if> >设计单位</option>
										 </select>
                                         </td>
                                       </tr>
                                       <tr>
                                         <td width="100" class="tab_title red"><p>客户名称:</p></td>
                                         <td colspan="3"><input id="cusName"  type="text"  class="text_a"  name="t_Customer.cusName" value="${t_Customer.cusName}" onblur="javascript:checkNO();" style="width:100%;"/></td>
                                       </tr>
                                        <tr>
                                            <td colspan="4" style=" text-align:right;">
                                            <input type="button"  value="取消" onclick="window.close();" style="cursor:pointer;" class="sub"/>
                                            <input type="button"   onclick="javascript:submitForm();"  value="保存"  class="sub" />                                              
                                            </td>
                                        </tr> 
                                       
                                                  
           </table>
           
    </div>
    
</form>

<script>
	$().ready(function() {  
	  	      $("#contractForm3").validate({  
					rules : {  
	  	    		 't_Customer.cusName' : { required : true } 
					}
				})
	});
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
				}
				}
		});
	}
 function submitForm(){
 	var cusName = $('#cusName').val().trim();
 	if(cusName==""){
 	$('#cusName').focus();
 	return;
 	}
	document.getElementById("contractForm3").submit();
	window.opener.callback();
	//window.opener.location.reload(); 
	window.close();
    }
	</script>