<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<link rel="stylesheet" href="/kindeditor/themes/default/default.css" />

<link rel="stylesheet" href="/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="/kindeditor/plugins/code/prettify.js"></script>
<script>

KindEditor.ready(function(K) {

	var editor1 = K.create('textarea[name="notice.content"]', {
	
	cssPath : '/kindeditor/plugins/code/prettify.css',
	
	uploadJson : '/kindeditor/upload_json.jsp',
	
	fileManagerJson : '/kindeditor/file_manager_json.jsp',
	
	allowFileManager : true,
	
	afterCreate : function() {
	
	var self = this;
	
	K.ctrl(document, 13, function() {
	
	self.sync();
	
	document.forms['example'].submit();
	
	});
	
	K.ctrl(self.edit.doc, 13, function() {
	
	self.sync();
	
	document.forms['example'].submit();
	
	});
	
	}
	
	});
	
	prettyPrint();

});

</script>


<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/systemManager.htm">系统管理</a> >
<a href="/notice/list.htm">信息发布</a> >
<a >通知公告</a>
</div>
<form action="/notice/save.htm" method="post" id="contractForm"  name="contractForm">
    <div id="content">
 
   
        
              <div  align="center" class="list_table stripe" style="width:80%; margin:20px auto; min-width:980px;">
			        <div class="article" style="margin:0; clear:both; width:100%;">
					  <div style=" float:left; width:80px; height:50px; line-height:40px; text-align: right;">发布栏目：</div>
                     <div>
					 <select name="noticeItemId" style=" float:left;line-height:22px; width:20%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px; background-color:#fff;">
			     			<#list noticeItemList as noticeItem>
								<option value="${noticeItem.id}"  <#if notice.noticeItem != null && notice.noticeItem.id == noticeItem.id>selected='selected'</#if>  >${noticeItem.name}</option>
							</#list>
                     </select>
                   </div>

                     <div style="clear:both;"></div>
					</div>
            		<div class="article" style="margin:0; clear:both; width:100%;">
					
                     <div style=" float:left; width:80px; height:50px; line-height:40px; text-align:right;">标题：</div>
                     <div><input name="notice.id" value="${notice.id}"  type="hidden" />
                     <input name="notice.title" value="${notice.title}" type="text" style=" float:left;line-height:22px; width:80%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                     <div style="clear:both; "></div>
					 <div style=" float:left; width:80px; height:50px; line-height:40px; text-align:right;">主题词：</div>
                     <div><input name="notice.keyword" value="${notice.keyword}" type="text" style=" float:left;line-height:22px; width:80%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                     <div style="clear:both;"></div>
					 <div style=" float:left; width:80px; height:50px; line-height:40px; text-align:right;">重要度：</div>
                     <div>
					 <select name="notice.important" style=" float:left;line-height:22px; width:21%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px; background-color:#fff;">
                                           
                                   <option value="0" <#if notice.important== 0>selected='selected'</#if> >低</option>
                                    <option value="1" <#if notice.important== 1>selected='selected'</#if> >中</option>
                                      <option value="2" <#if notice.important== 2>selected='selected'</#if> >高</option>
                                           
                                         </select></div>
										 <div style=" float:left; width:50px; height:50px; line-height:40px; text-align:right;">文号：</div>
                     <div><input name="notice.number" value="${notice.number}" type="text" style=" float:left;line-height:22px; width:21%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px; background-color:#fff;" /></div>
										 <div style=" float:left; width:50px; height:50px; line-height:40px; text-align: right;">作者：</div>
                     <div><input name="notice.author" value="${notice.author}" type="text" style=" float:left;line-height:22px; width:21%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px; background-color:#fff;" /></div>
       
                     <div style="clear:both;"></div>
					 <div style=" float:left; width:80px; height:50px; line-height:40px; text-align:right;">引用链接：</div>
                     <div><input name="notice.src" value="${notice.src}" type="text" style=" float:left;line-height:22px; width:80%; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                     <div style="clear:both;"></div>
                     <div style=" float:left; width:80px; height:50px; line-height:40px; text-align:right">内容：</div>
                     <div resize="none" style="float:left;width:80%; margin:0 auto; padding-left:20px;">
                     <textarea cols="" name="notice.content" style="line-height:22px; width:100%; height:550px;  border:1px solid #c1e9ff; margin:6px 0px 0px 0px;" >${notice.content}</textarea></div>
                     
                     <div style="clear:both;"></div>
                     <div  style="float: right; width:100%; height:50px; line-height:40px;">
                     <input type="submit"  value="保存" style="float:left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:100px; font-family:"新宋体";"/>
                     <input type="button"  value="取消" onclick="javascript:history.go(-1);" style="float:left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:10px; font-family:"新宋体";" /></div>
                    
                    
                    </div>
             </div>
              <div style="clear:both;"></div>                    

    </div>
   </form>
<script>
/*var i=0;
var flag = true;
$().ready(function() {  
  	      $("#contractForm").validate({
				rules : {  
				  'noticeItemId'：{ required : true },
				  'notice.title'：{ required : true } 
				  'contract.name' : { required : true , minlength : 2} ,
				  'contract.allAmount' : { required : true , number:true } , 
				  'contract.contractDept' : { required : true } ,
				  'contract.contractSignDate' : { required : true } ,
				  'contract.planEndDate' : { required : true } ,
				  'contract.chargeRemark' : { required : true } ,
				  'contract.diedAmount' : { number:true } , 
				  'contract.splitAmount' : { number:true } , 
				   'contract.depositAmount' : { number:true } ,    
				 '' : { digits:true } 
				}
			})
}); */
	$().ready(function() {
	
		$("#contractForm").validate({
			rules : {  
	  	    		 'noticeItemId' : { required : true } ,
	  	    		 'notice.title' : { required : true } 
					},
        errorElement: "em", //可以用其他标签，记住把样式也对应修改  
		})
	});

</script>
<style type="text/css">
  em {
  float:left;   line-height:40px;
  font-weight: bold;
  }
</style>