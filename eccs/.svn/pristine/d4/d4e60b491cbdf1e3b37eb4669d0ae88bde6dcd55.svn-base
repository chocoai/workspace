<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>

<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/notices/lists.htm">消息公告</a> >
<a href="#">通知公告</a>
</div>
<form action="/notice/save.htm" method="post" id="contractForm">
    <div id="content">
              <div class="list_table stripe" style="width:80%; margin:50px auto; min-width:980px; padding-left:  6em;">
            		<div class="article" style="margin:0; clear:both; width:100%;">
                        <h1 style=" float:left;line-height:22px; height:24px; width:80%; text-indent:6px;  margin:6px 20px 0px 20px;color:#007799;font-size:40px">${notice.title}</h1><br>
                        <p style=" float:left;line-height:22px; height:24px; width:80%; text-indent:6px;  margin:6px 20px 0px 20px;color:#666666;font-weight: 100;font-size:18px; padding-top:20px">
                        <span class="time" >时间：${notice.ctime?string('yyyy-MM-dd')}</span>
                         <span style="padding-left:  6em;">发布人：${notice.user.name}</span>
                        </p>
                     <div style="clear:both;"></div>
                       <div id="say" readonly="readonly" style="  margin-left:10px;overflow-y:auto; overflow-x:auto;  float:left;line-height:22px; height:450px; width:80%; text-indent:2em; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;background-color:#fff;letter-spacing:2px;">
                     ${notice.content}        
                    </div>
                    <div style="clear:both;"></div>
                     <div style=" float:right; width:100%; height:50px; line-height:40px;">
                    
                     <input type="button"  value="返回" onclick="javascript:history.go(-1);" style="float:left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:20px; font-family:'新宋体';" /></div>
                    </div>
             </div>
              <div style="clear:both;"></div>     
         </div>
   </form>
<script>
var i=0;
var flag = true;
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
				  '' : { digits:true } 
				}
			})
}); 


</script>
