<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<link rel="stylesheet" href="/kindeditor/themes/default/default.css" />



<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/systemManager.htm">系统管理</a> >
<a href="/notice/list.htm">信息发布</a> >
<a >栏目添加</a>
</div>
<form action="/notice/saveitem.htm" method="post" id="contractForm" onSubmit="return check()">
    <div id="content">
    	<div align="center" class="list_table stripe" style="width:80%; margin:0px auto; min-width:980px;margin-top:10px;">
			   <div class="add_left">
			      <ul>
			      <#list noticeItemList as noticeItem>
				     <li><a href="javascript:selectNoticeItem('${ noticeItem.id}','${noticeItem.name}','${noticeItem.description}','${noticeItem.status}');">${noticeItem.name }</a></li>
					</#list>
			      </ul>
			   </div>     
               <div class="add_right">
		
					  <div style=" float:left; width:80px; height:50px; line-height:40px; text-align: right;">栏目名称：</div>
                      <div><input name="noticeItem.id" id="itemid" value="${noticeItem.id}"  type="hidden"  />
                      <input name="noticeItem.name" id="itemname"  value="${noticeItem.name}" type="text" style=" float:left;line-height:22px; width:80%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                      <div style="clear:both;"></div>
					  
					   <div style=" float:left; width:80px; height:50px; line-height:40px; text-align: right;">栏目描述：</div>
                      <div><textarea cols="" id="itemdescription"  name="noticeItem.description"  style=" float:left;line-height:22px; height:100px; width:80%; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;">${noticeItem.description}</textarea></div>
                      <div style="clear:both;"></div>
				      
					  <div style=" float:left; width:80px; height:50px; line-height:40px; text-align:right;">启用状态：</div>
                     <div>
					 <select name="noticeItem.status" id="itemstatus" style=" float:left;line-height:22px; width:20%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px; background-color:#fff;">
                                           <option value="1" <#if noticeItem.status== 1>selected='selected'</#if> >启用</option>
                                           <option value="0" <#if noticeItem.status== 0>selected='selected'</#if> >停用</option>
                                           
                                         </select></div>
					<div style="clear:both;"></div>					 
										 
					<div style=" float:right; width:100%; height:50px; line-height:40px;">
					
		   <input type="submit"  value="保存"  style="float:left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:10px; font-family:"新宋体";"/>
                   <input type="button"  value="取消"  onclick="javascript:cancle();" style="float:left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:10px; font-family:"新宋体";" />
                 <!--  <input type="button"  value="删除"  onclick="javascript:deleteitem();"   style="float:left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:10px; font-family:"新宋体";"/> --></div>
                    </div>
			   
			   </div>	
             </div>
                    
   </form>
<script>

$().ready(function() {  
  	      $("#contractForm").validate({
				rules : {  
/*				  'contract.name' : { required : true , minlength : 2} ,
				  'contract.allAmount' : { required : true , number:true } , 
				  'contract.contractDept' : { required : true } ,
				  'contract.contractSignDate' : { required : true } ,
				  'contract.planEndDate' : { required : true } ,
				  'contract.chargeRemark' : { required : true } ,
				  'contract.diedAmount' : { number:true } , 
				  'contract.splitAmount' : { number:true } , 
				   'contract.depositAmount' : { number:true } ,    */
				 // '' : { digits:true } 
				}
			})
}); 

function cancle(){
 window.parent.location.href="/notice/list.htm";
}
function deleteitem(){
	var id =document.getElementById("itemid").value;
	if(id==""){
         alert("请选着需要删除的栏目");
         return ;
	}
         if(confirm("确定删除吗？")){
	 window.location.href= "/notice/deleteItem.htm?noticeItem.id="+id;
         }
         return ;
}




	
function check(){
	var itemname =document.getElementById("itemname").value;
        var ite=itemname.replace(/(^\s*)/g,""); 
	if(ite.length==0){
        alert("请输入栏目");
        document.getElementById("itemname").focus();
        return false;
	}
        return true;	
}
function selectNoticeItem(id,name,description,status){
	var itemid = document.getElementById("itemid");
	var itemname = document.getElementById("itemname");
	var itemdescription = document.getElementById("itemdescription");
	var itemstatus = document.getElementById("itemstatus");
	itemid.value=id;
	itemname.value=name;
	itemdescription.value=description;
	itemstatus.value=status;
	
}


</script>
