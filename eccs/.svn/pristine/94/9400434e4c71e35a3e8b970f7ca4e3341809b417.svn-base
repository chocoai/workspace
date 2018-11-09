   <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
        <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">证照借用-列表</a>
</div>
    <div id="content">
        <form action="/t_liborrow/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		
                     <div class="sub_add" onclick="javascript:addProject();">添加</div>
                     <div class="t_sub_delete" onclick="delProject();">删除</div><br/><br/>
                     <table  border="1"  align="center" cellpadding="1" cellspacing="1" style="width:100%; margin:0 auto; min-width:980px;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center" >申请单据</div></td>
                     <td style="width:60px"><input name="liborrow.documents" type="text" value="${liborrow.documents}" style=" float:left;line-height:22px; height:30px;width:150px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center" >登记人</div></td>
                     <td style="width:60px"><input name="uname" type="text" value="${liregistration.licname}" style=" float:left;line-height:22px; height:30px; width:150px;text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center" >登记日期</div></td>
                     <td style="width:60px"><input name="rtimec"  id="rtimec" onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${rtimec}" style=" float:left;line-height:22px; width:120px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center" >至</div></td>
                     <td style="width:60px"><input name="rtimej"  id="rtimej" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${rtimej}" style=" float:left;line-height:22px; height:30px;width:120px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                    <td style="width:60px"><div class="sub_sear" style="margin:6px 0px 0px 0px;" onclick="javascript:$('#searchForm').submit()">查询</div></td>
                     <td></td>
                     </tr>
                     </table>
                    
             </div>
         </form>
          <div style="clear:both; margin:10px 0px 0px 0px;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:30px; background-color:#e6f6ff;"><p>序号 </p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:30px; background-color:#e6f6ff;"><p><input id="qxcheckbox"  type="checkbox"/></p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>申请单据 </p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>归还时间</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>借用备注</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>保管部门</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>经办人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>登记人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;"><p>登记日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:120px; background-color:#e6f6ff;"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as t_liborrow>
                            		<input type="hidden" value="${t_liborrow.id}"/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(t_liborrow_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="text-align:center;"><input name="a" type="checkbox" autocomplete="off" value="${t_liborrow.id}" /><input type="hidden"  value="${t_liborrow.state}"/><input type="hidden"  value="${t_liborrow.handle.suggestion}"/></td>
                                          <td style="text-align:left;">
                                          <#if t_liborrow.handle.suggestion!="" >已完成</#if>
                                          <#if t_liborrow.handle.suggestion =="" >处理中</#if>
                                          </td>
                                          <td style="text-align:left;">${t_liborrow.documents}</td>
                                          <td style="text-align:left;">${t_liborrow.returntime}</td>
                                          <td style="text-align:left;">${t_liborrow.remarks}</td>
                                          <td style="text-align:left;">${t_liborrow.sdeptid.name}</td>
                                          <td style="text-align:left;">${t_liborrow.handle.user.name}</td>
                                          <td style="text-align:left;">${t_liborrow.user.name}</td>
                                          <td style="text-align:left;">${t_liborrow.rtime}</td>
                                          <td style="text-align:left;">
                                         
                                         <#if t_liborrow.state ==2 && t_liborrow.handle.user.id==user.id><a type="button"   onclick="revert(${t_liborrow.id})" title="归还">归还</a></#if>
                                         
                                         <#if t_liborrow.handle.suggestion =="" && t_liborrow.sdeptid.id==dept> <a href="/t_liborrow/manage.htm?clid=${t_liborrow.id}" title="处理">处理</a></#if>
                                         
                                         <#if t_liborrow.handle.suggestion =="" > <a href="/t_liborrow/new.htm?liborrow.id=${t_liborrow.id}" title="编辑">编辑</a></#if>
                                         <a href="/t_liborrow/show.htm?liborrow.id=${t_liborrow.id}" title="查看">查看</a>
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
    text-align:center;
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
    function addProject(){
    window.parent.location.href="/t_liborrow/new.htm";
    }
    function delProject() {
    if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
		var ok = true;
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
       if($(this).next().val()==2||($(this).next().val()==""&&$(this).next().next().val()=="")){
       	ok = false;
       	return false;
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
      if(!ok){
      	alert("借用操作未完成，不能删除");
      }
      if(ok){
		var url = "/t_liborrow/delete.htm?Stringid=" + id;
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
		});}
	}
	function revert(id) {
		if (!confirm("确定归还")) {
			return;
		}
		var url = "/t_liborrow/revert.htm?zzid=" + id;
		$.ajax({
			type:"post",
			url:url,
			asyn:true,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('归还成功');
					window.parent.location.reload();
				} else {
					alert('归还失败');
				}
			}
		});
	}
    </script>