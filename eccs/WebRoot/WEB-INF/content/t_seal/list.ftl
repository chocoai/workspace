<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">印章借用-列表</a>
</div>
    <div id="content">
        <form action="/t_seal/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		
                     <div class="sub_add" onclick="javascript:addProject();">添加</div>
                     <div class="t_sub_delete" onclick="delProject();">删除</div><br/><br/>
                     <table style="width:100%;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目名称</div></td>
                     <td style="width:60px"><input name="uproject" type="text" value="${uproject}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >借用部门</div></td>
                     <td style="width:60px"><input name="ubranch" type="text" value="${ubranch}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >借用人</div></td>
                     <td style="width:60px"><input name="uname" type="text" value="${uname}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td rowspan="2" style="width:60px"><div class="sub_sear" onclick="javascript:$('#searchForm').submit()">查询</div></td>
                    <td></td>
                     </tr>
                     <tr>
                     
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >印章类型:</div></td>
                     <td style="width:60px"><div >  <select name="t_sealBorrow.type" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
					<option value="">全部</option>
					<option value="1" <#if  t_sealBorrow.type==1>selected='selected'</#if> >公章</option>
				    <option value="2" <#if  t_sealBorrow.type==2>selected='selected'</#if> >合同章</option>
					<option value="3" <#if  t_sealBorrow.type==3>selected='selected'</#if> >项目章</option>
				    <option value="4" <#if  t_sealBorrow.type==4>selected='selected'</#if> >其他</option>
				    </select></div></td>
				     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >登记日期:</div></td>
                     <td style="width:60px"><input name="rtimec" id="rtimec" onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${rtimec}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >至:</div></td>
                     <td style="width:60px"><input name="rtimej" id="rtimej" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${rtimej}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
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
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>借用部门</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>借用人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>印章类型</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>项目名称</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>盖章份数</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>归还状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>处理意见</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>登记日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:120px;background-color:#e6f6ff;"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as t_sealBorrow>
                            		<input type="hidden" value="${t_sealBorrow.id}"/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(t_sealBorrow_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="text-align:center;"><input name="a" type="checkbox" autocomplete="off" value="${t_sealBorrow.id}" /><input type="hidden" value="${t_sealBorrow.state}" /><input type="hidden" value="${t_sealBorrow.option_id.option}" /></td>
                                          <td style="text-align:left;">${t_sealBorrow.dept_id.name}</td>
                                          <td style="text-align:left;">${t_sealBorrow.user_id.name}</td>
                                          <td style="text-align:left;"> 
                                          <#if t_sealBorrow.type ==1>公章</#if>
                                          <#if t_sealBorrow.type ==2>合同章</#if>
                                          <#if t_sealBorrow.type ==3>项目章</#if>
                                          <#if t_sealBorrow.type ==4>其他</#if>
                                          </td>
                                          <td style="text-align:left;">${t_sealBorrow.project_id.name}</td>
                                          <td style="text-align:left;">${t_sealBorrow.count}</td>
                                          <td style="text-align:left;">
                                          <#if t_sealBorrow.state ==1>已归还</#if>
                                          <#if t_sealBorrow.state ==2>未归还</#if>
                                          </td>
                                          <td style="text-align:left;">
                                          <#if t_sealBorrow.option_id.option ==1>准许</#if>
                                          <#if t_sealBorrow.option_id.option ==2>不准</#if>
                                          <#if t_sealBorrow.option_id.option ==3>占用</#if>
                                          </td>
                                          <td style="text-align:left;">${t_sealBorrow.rtime}</td>
                                          <td style="text-align:left;">
                                          <#if t_sealBorrow.state ==2 && t_sealBorrow.option_id.user_id.id==user.id><a onclick="revert(${t_sealBorrow.id})" title="归还">归还</a></#if>
                                          <#if t_sealBorrow.option_id.option == "" && t_sealBorrow.sealdept_id.id==dept><a href="/t_seal/manage.htm?t_sealBorrow.id=${t_sealBorrow.id}" title="处理">处理</a></#if>
                                          
                                          <#if t_sealBorrow.option_id.option == ""><a href="/t_seal/new.htm?t_sealBorrow.id=${t_sealBorrow.id}" title="编辑">编辑</a></#if>
                                         <a href="/t_seal/show.htm?t_sealBorrow.id=${t_sealBorrow.id}" title="查看">查看</a>
                                          </td>
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="11">暂无数据!</td></tr>
   							</#if>
           </table>
           </br>
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
    window.parent.location.href="/t_seal/new.htm";
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
       if($(this).next().val()==0&&$(this).next().next().val()==""){
       	ok = false;
       	alert("借用未处理，不能删除");
       	return false;
       }
       if($(this).next().val()==2){
       	ok = false;
       	alert("借用未归还，不能删除");
       	return false;
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
      if(ok){
		var url = "/t_seal/delete.htm?Stringid=" + id;
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
		var url = "/t_seal/revert.htm?zzid=" + id;
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