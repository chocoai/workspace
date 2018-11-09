 <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">证照登记-列表</a>
</div>
    <div id="content">
        <form action="/t_liregistration/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		
                     <div class="sub_add" onclick="javascript:addProject();">添加</div>
                     <div class="t_sub_delete" onclick="delProject();">删除</div><br/><br/>
                     <table style="width:100%;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center" >证照名称</div></td>
                     <td style="width:60px"><div><input name="liregistration.licname" type="text" value="${liregistration.licname}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></div></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center" >证照编号</div></td>
                     <td style="width:60px"><div><input name="liregistration.licnumber" type="text" value="${liregistration.licnumber}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center" >状态</div></td>
                     <td style="width:60px"><div >  <select name="liregistration.licstatus" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
					<option value="">全部</option>
					<option value="1" <#if  liregistration.licstatus==1>selected='selected'</#if> >闲置</option>
				    <option value="2" <#if  liregistration.licstatus==2>selected='selected'</#if> >占用</option>
					<option value="3" <#if  liregistration.licstatus==3>selected='selected'</#if> >其他</option>
				    </select></div></td>
				    <td style="width:60px"><div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin:6px 0px 0px 0px">查询</div></td>
                     <td></td>
                     </tr>
                     </table>
                    
             </div>
         </form>
          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:30px;background-color:#e6f6ff;"><p>序号 </p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:30px;background-color:#e6f6ff;"><p><input id="qxcheckbox"  type="checkbox"/></p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:30px;background-color:#e6f6ff;"><p>状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>证照编号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>证照名称</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>标识</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>颁发部门</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>颁发日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>有效期至</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>下次年检日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:120px;background-color:#e6f6ff;"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as liregistration>
                            		<input type="hidden" value=${liregistration.id}/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(liregistration_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="text-align:center;"><input name="a" type="checkbox" autocomplete="off" value="${liregistration.id}" /></td>
                                         <input type="hidden" value="${liregistration.licstatus}" class="def"/>
                                         <td style="text-align:left;">
                                         	
	                     					<#if liregistration.licstatus ==1 >闲置</#if>
											<#if liregistration.licstatus ==2 >占用</#if>
											<#if liregistration.licstatus ==3 >其他</#if>
                                         </td>
                                         <td style="text-align:left;">${liregistration.licnumber}</td>                                        
                                         <td style="text-align:left;">${liregistration.licname}</td>
                                         <td style="text-align:left;">
                                         <#if liregistration.characteristic ==1 >正本</#if>
										 <#if liregistration.characteristic ==2 >副本</#if>
                                         </td>
                                         <td style="text-align:left;">${liregistration.prebranch}</td>
                                          <td style="text-align:left;">${liregistration.predate}</td>
                                          <td style="text-align:left;">${liregistration.effdate}</td>
                                           <td style="text-align:left;">${liregistration.anndate}</td> 
                                           <td style="text-align:left;">
                                           <a href="/t_liregistration/new.htm?liregistration.id=${liregistration.id}" title="编辑">编辑</a>
                                         <a href="/t_liregistration/show.htm?liregistration.id=${liregistration.id}" title="查看">查看</a>
                                           </td>                 
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="11">暂无数据!</td></tr>
   							</#if>
           </table>
           <br/>
              <div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
				</div>

    </div>
    <style>
    td{
    word-break: break-all;
    }
    </style>
<script>
$(function(){
	$("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='a']").attr("checked",'true');
		}else{
		$("[name='a']").removeAttr("checked",'true');
		}
		
		$("input[name='a']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		
		});
	});
	function delProject() {
	if (!confirm("确定删除?")) {
			return;
		}
	var ok=true;
		var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
      var b=$(this).parent().siblings(".def").val();
      if(b!=1){
      ok=false;
      alert("证照占用,不能删除");
      }
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
      if(ok){
      	var url = "/t_liregistration/delete.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
					location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
      }
	}
	function addProject() {
         window.parent.location.href="/t_liregistration/new.htm";
	}
</script>



