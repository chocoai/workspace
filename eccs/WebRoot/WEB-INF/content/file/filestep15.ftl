<script src="/js/jquery.js"></script>
<script src="../../../js/ajaxfileupload.js" type="text/javascript"></script>
<form action="/review/uploadFile.htm" method="post" id="uploadForm" enctype="multipart/form-data">
        <div id="content">
              <div align="center" class="list_table stripe" style="width:80%; margin:20px auto; min-width:650px;">
            		<div class="article" style="margin:0; clear:both; width:90%">
					 <div style=" float:left; width:90% margin-bottom:15px;">
					 <div style=" float:left; width:100px; height:50px; line-height:40px;">选择文件：</div>
					 <input type="file" name="file" id="file1"  style="float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" />
                     
                     <div style="clear:both;"></div>
                     <div style=" float:left; width:100px; height:50px; line-height:40px;">文件类型：</div>
                     <div>    <select name=annexTypeId id="annexTypeId" style=" float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px; background-color:#fff;">
									<#list annexTypeList as annexType>
										<option value="${annexType.id}"   >${annexType.name}</option>
									</#list>
								</select>
					 </div>
                     <div style="clear:both;"></div>
					 <div style=" float:left; width:100px; height:50px; line-height:40px;">文件字号：</div>
                     <div><input name="fileNum" id="fileNum" type="text" style=" float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                     <div style="clear:both;"></div>
					 <div style=" float:left; width:100px; height:50px; line-height:40px;">文件作者：</div>
                     <div><input name="fileOwner"  id="fileOwner" type="text" style=" float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                     <div style="clear:both;"></div>
					 <div style=" float:left; width:100px; height:50px; line-height:40px;">文件页号：</div>
                     <div><input name="filePage" id="filePage" type="text" style=" float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" /></div>
                     <div style="clear:both;"></div>
                     <div style=" float:left; width:100px; height:50px; line-height:40px;">文件描述：</div>
                     <div><textarea name="description" id="description" cols="" style=" float:left;line-height:22px; height:100px; width:420px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;"></textarea></div>
                     
                     <div style="clear:both;"></div>
                     <div style=" float: right; width:90% height:50px; line-height:40px;">
                     <input type="button"  onclick="javascript:uploadFile();"  value="上传" style="float: left; width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background-color:#01749e; margin-top:10px; margin-bottom:10px; margin-left:0px; font-family:"新宋体";"/>
                     </div>
                    </div>
             </div>
              <div style="clear:both;"></div>                    
   </div>
</form>

<script >
  function uploadFile(){
      var annexTypeId = $("#annexTypeId").val();
      var fileNum = $("#fileNum").val();
      var fileOwner = $("#fileOwner").val();
      var filePage = $("#filePage").val();
      var description = $("#description").val();
      ajaxFileUpload(annexTypeId,fileNum,fileOwner,filePage,description);
  }
  function ajaxFileUpload(annexTypeId,fileNum,fileOwner,filePage,description) {
      $.ajaxFileUpload
      (
          {
              url: '/step15/uploadFile.htm?project.id=${projectId}&annexTypeId='+annexTypeId+'&fileNum='+fileNum+'&fileOwner='
              +fileOwner+'&filePage='+filePage+'&description='+description,  //用于文件上传的服务器端请求地址
              secureuri: false, //是否需要安全协议，一般设置为false

              fileElementId: 'file1', //文件上传域的ID
              dataType: 'text', //返回值类型 一般设置为json
              success: function (data, status)  //服务器成功响应处理函数
              {
               window.opener.addFileUploadlocation();
                 alert("上传成功");
                 window.close();
              },
              error: function (data, status, e)//服务器响应失败处理函数
              {
                  alert(e);
              }
          }
      )
      return false;
  }
   
</script>