<script src="/js/jquery.js"></script>
<script src="../../../js/ajaxfileupload.js" type="text/javascript"></script>
<form action="/t_customer/uploadFile.htm" method="post" id="uploadForm" enctype="multipart/form-data">
        <div id="content">
              <div align="center" class="list_table stripe" style="width:80%; margin:20px auto; min-width:650px;">
            		<div class="article" style="margin:0; clear:both; width:90%">
					 <div style=" float:left; width:90% margin-bottom:15px;">
					 <div style=" float:left; width:100px; height:50px; line-height:40px;">选择文件</div>
					 <input type="file" name="file" id="file1"  style="float:left;line-height:22px; width:420px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;" />
                     <div style="clear:both;"></div>
                     <div style=" float:left; width:100px; height:50px; line-height:40px;">文件描述</div>
                     <div><textarea name="t_file.remarks" id="remarks" cols="" style=" float:left;line-height:22px; height:100px; width:420px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 20px 0px 20px;"></textarea></div>
                     
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
      var remarks=$("#remarks").val().trim();
      ajaxFileUpload(remarks);
  }
  function ajaxFileUpload(remarks) {
      $.ajaxFileUpload({
              url: '/t_customer/uploadFile.htm?remarks='+remarks,  //用于文件上传的服务器端请求地址
              secureuri: false, //是否需要安全协议，一般设置为false

              fileElementId: 'file1', //文件上传域的ID
              dataType: 'json', //返回值类型 一般设置为json
              success: function (data, status){
	             window.opener.returnFiletCus(data.id,data.name,remarks);
                 alert("上传成功");
                 window.close();
              },
              error: function (data, status,e){
                  alert("上传失败");
              }
          }
      )
      return false;
  }
   
</script>