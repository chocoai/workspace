<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>

<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a >操作人员分工一览</a>
</div>
  <form action="/project/saveMarketPerson.htm" method="post" id="contractForm">  
 <div id="content">

 <table border="1" cellspacing="1" cellpadding="1" class="input_table">
      <tr>
        <td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">操作人员分工一览表</td>
        </tr>
      <tr>
        <td   style="text-align:right;width:25%;">项目名称</td>
         <td style="text-align:right;width:25%; ">
         <input name="project.id" value="${project.id}" type="hidden" />
    <input name="project.name" value="${project.name}" readonly="readonly"   id="projectNameId"  type="text" class="text_a" />
    </td>
     <td style="text-align:right;width:25%;">项目编号</td>
        <td style="text-align:right;width:25%; "> <input readonly="readonly" value="${project.no }"  id="projectNoId"  type="text" class="text_a" /></td>
      </tr>
     <tr>
        <td  style=" text-align:center; font-weight:bold;">有关操作人员</td> 
        <td  style=" text-align:center; font-weight:bold;">姓名</td>
        <td  style=" text-align:center; font-weight:bold;">执业资格</td>
        <td  style=" text-align:center; font-weight:bold;">执业资格注册编号</td>
      </tr>
      <#list type0List as type4>
      <tr>
        <td style=" text-align:center; font-weight:bold;">经营负责人</td>
        <td style=" text-align:center;"> 
        <input name="userId4" id="userId4_${type4_index}"    value="${type4.workUser.id }" type="hidden" class="text_a"/>
        <input name="name4" id="name4_${type4_index}" readonly="readonly" onclick="getUser('userId4_${type4_index}','name4_${type4_index}');"  value="${type4.workUser.name}" type="text" class="text_a"/></td>
        <td style=" text-align:center;"><input name="workLevel4" value="${type4.workLevel }" type="text" class="text_a"/></td>
        <td  style=" text-align:center;"><input name="workLevelNo4" value="${type4.workLevelNo }" type="text" class="text_a"/></td>
      </tr>
      </#list>
      <#list type1List as type5>
      <#if  (type5_index==0) >
      <tr>
        <td  width="72" rowspan="${type1List?size}" style=" text-align:center; font-weight:bold;">编制人员</td>
        
        <td style=" text-align:center;">
        <input name="userId5" id="userId5_${type5_index}"+ value="${type5.workUser.id }" type="hidden" class="text_a"/>
        <input name="name5" id="name5_${type5_index}" readonly="readonly" onclick="getUser('userId5_${type5_index}','name5_${type5_index}');" value="${type5.workUser.name}" type="text" class="text_a"/></td>
        <td style=" text-align:center;"><input name="workLevel5" value="${type5.workLevel }" type="text" class="text_a"/></td>
        <td  style=" text-align:center;"><input name="workLevelNo5" value="${type5.workLevelNo }" type="text" class="text_a"/></td>
      </tr>
       </#if>
        <#if (type5_index>0) >
      <tr>
        <td style=" text-align:center;">
         <input name="userId5" id="userId5_${type5_index}"+ value="${type5.workUser.id }" type="hidden" class="text_a"/>
        <input name="name5" id="name5_${type5_index}" readonly="readonly" onclick="getUser('userId5_${type5_index}','name5_${type5_index}');" value="${type5.workUser.name}" type="text" class="text_a"/></td>
        <td style=" text-align:center;"><input name="workLevel5" value="${type5.workLevel }" type="text" class="text_a"/></td>
        <td  style=" text-align:center;"><input name="workLevelNo5" value="${type5.workLevelNo }" type="text" class="text_a"/></td>
      </tr>
      </#if>
	</#list>
					    <tr>
					          <td colspan="4" style=" text-align:right;">
					          <input type="button" onclick="location.href ='javascript:history.back();';" value="取消" class="sub"/>
					          <input type="submit"  value="保存"  class="sub" />
					           </td>
                        </tr>  
    </table>


      </div>
</form>
<script>

$().ready(function() {  
  	      $("#contractForm").validate({
				rules : {  
				  'step3.techMasterName' : { required : true } ,
				  'step3.lawMasterName' : { required : true } 
				}
			})
}); 
var id;
var name;
function getUser(useridid , usernameid){
    	id = $("#"+useridid);
    	name = $("#"+usernameid);
	
		var iWidth=650;                          //弹出窗口的宽度; 
   		var iHeight=500;                         //弹出窗口的高度; 
   		//获得窗口的垂直位置 
   		var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
   		//获得窗口的水平位置 
   		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
		window.open('/user/selectUser.htm','','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
}

function returnParam(id,name,username){
			id.val(id);
			name.val(name);
	}
</script>
