<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">招聘计划-列表</a>
</div>
    <div id="content">
        <form action="/t_recr/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		
                     <div class="sub_add" onclick="javascript:addProject();">添加</div>
                     <div class="t_sub_delete" onclick="delProject();">删除</div><br/><br/>
                     <table  border="1"  align="center" cellpadding="1" cellspacing="1" style="width:100%; margin:0 auto; min-width:980px;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;" >需求部门</div></td>
                     <td style="width:60px"><input name="dept_id" type="text" value="${dept_id}" style=" float:left;line-height:22px; height:30px;width:150px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >登记人</div></td>
                     <td style="width:60px"><input name="user_id" type="text" value="${user_id}" style=" float:left;line-height:22px; height:30px; width:150px;text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >登记时间</div></td>
                     <td style="width:60px"><input name="rtimec" id="rtimec" onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${rtimec}" style=" float:left;line-height:22px; width:120px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >至</div></td>
                     <td style="width:60px"><input name="rtimej"  id="rtimej" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${rtimej}" style=" float:left;line-height:22px; height:30px;width:120px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                    <td style="width:60px"><div class="sub_sear" style="margin:6px 0px 0px 0px;" onclick="javascript:$('#searchForm').submit()">查询</div></td>
                     <td></td>
                     </tr>
                     </table>
                    
             </div>
         </form>
          <div style="clear:both; margin:0px 0px 0px 0px;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:30px;background-color:#e6f6ff;"><p>序号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:30px;background-color:#e6f6ff;"><p><input id="qxcheckbox"  type="checkbox"/></p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>需求部门</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>登记人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>登记时间</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>人员需求岗位描述</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:10%;background-color:#e6f6ff;"><p>备注</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>处理时间</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>处理意见</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:15%;background-color:#e6f6ff;"><p>意见明细</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:120px"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as t_hrrecruitment>
                            		<input type="hidden" value="${t_hrrecruitment.id}"/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(t_hrrecruitment_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="text-align:center;"><input name="a" type="checkbox" autocomplete="off" value="${t_hrrecruitment.id}" /><input type="hidden" value="${t_hrrecruitment.t_hropinion}" /></td>
                                          <td style="text-align:left;">${t_hrrecruitment.dept_id.name}</td>
                                          <td style="text-align:left;">${t_hrrecruitment.user_id.name}</td>
                                          <td style="text-align:left;">${t_hrrecruitment.rdate}</td>
                                          <td style="text-align:left;">${t_hrrecruitment.demand}</td>
                                          <td style="text-align:left;">${t_hrrecruitment.remark}</td>
                                          <td style="text-align:left;">${t_hrrecruitment.t_hropinion.cdate}</td>
                                          <td style="text-align:left;">
                                          <#if t_hrrecruitment.t_hropinion.opinion ==1>同意</#if>
                                          <#if t_hrrecruitment.t_hropinion.opinion ==2>不同意</#if>
                                          </td>
                                          <td style="text-align:left;">${t_hrrecruitment.t_hropinion.detail}</td>
                                          <td style="text-align:left;">
                                          <#if t_hrrecruitment.t_hropinion=="" && t_hrrecruitment.hdept_id.id==dept>
                                          <a onclick="manage(${t_hrrecruitment.id})" title="处理">处理</a>
                                          </#if>
                                           <#if t_hrrecruitment.t_hropinion==""><a  href="/t_recr/new.htm?t_hrrecruitment.id=${t_hrrecruitment.id}" title="编辑">编辑</a></#if>
                                         <a href="/t_recr/show.htm?t_hrrecruitment.id=${t_hrrecruitment.id}" title="查看">查看</a>
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
    function manage(a){
    
    var url = "/t_recr/manage.htm?t_hrrecruitment.id=" + a;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('操作失败!');
				} else {
				    window.parent.location.href="/t_recr/manage.htm?t_hrrecruitment.id="+a;
				}
			}
		});

    }
    function addProject(){
    window.parent.location.href="/t_recr/new.htm";
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
       if($(this).next().val()==""){
       	ok = false;
       	alert("招聘信息未处理，不能删除");
       	return false;
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
      if(ok){
		var url = "/t_recr/delete.htm?Stringid=" + id;
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
    </script>