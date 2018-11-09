<script src="/js/jquery.js"></script>
<script src="/js/ajaxfileupload.js" type="text/javascript"></script>
<script>
  function uploaFile(){
      var uptypeid = $("#uptypeid").val();
      var remark = $("#remark").val();
      ajaxFileUpload(uptypeid,remark);
  }

  
  function ajaxFileUpload(uptypeid,remark) {
      $.ajaxFileUpload
      (
          {		 
              url: '/doc/uploadFile.htm?uptypeid='+uptypeid  
              +'&remark='+remark,
              secureuri: false, //是否需要安全协议，一般设置为false
              fileElementId: 'file1', //文件上传域的ID
              dataType: 'json', //返回值类型 一般设置为json
              success: function (d)  //服务器成功响应处理函数
              {
             		 if(d=='1'){
               		alert("上传成功");
                	 window.close();
              		}else{
              		alert("文件名重复");
              		}
            	
              },
              error: function (d, e)//服务器响应失败处理函数
              {
                  alert(e);
              }
          }
      )
      return false;
  }
   
</script>
<form action="/doc/uploadFile.htm" method="post" id="uploadForm" enctype="multipart/form-data">
        <div id="content">
              <div align="center" class="list_table stripe" style="width:80%; margin:20px auto; min-width:650px;">
            		<div class="article" style="margin:0; clear:both; width:90%">
					 <div style=" float:left; width:90% margin-bottom:15px;">
					 <div style=" float:left; width:100px; height:50px; line-height:40px;">选择文件：</div>
					 <input type="file" name="file" id="file1"  style="float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" />
                     
                     <div style="clear:both;"></div>
                     <div style=" float:left; width:100px; height:50px; line-height:40px;">文件类型：</div>
                     <div>    <select name="uptypeid" id="uptypeid" style=" float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px; background-color:#fff;">
									<#list upty as uptype>
										<option value="${uptype.id}"   >${uptype.name}</option>
									</#list>
								</select>
					 </div>
                     <div style="clear:both;"></div>
					 <div style=" float:left; width:100px; height:50px; line-height:40px;">文件备注：</div>
                     <div><input name="remark"  id="remark" type="text" style=" float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                     
                     <div style="clear:both;"></div>
                     <div style=" float: right; width:90% height:50px; line-height:40px;">
                     <input type="button"  onclick="uploaFile();"  value="保存" style="float: left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:0px; font-family:"新宋体";"/>
                     </div>
                    </div>
             </div>
              <div style="clear:both;"></div>                    
   </div>
</form>

