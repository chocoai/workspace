<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/backlog/list.htm">待办事项</a> >
<a href="">${project.name}</a> >
<a href="javascript:window.location.reload();">勘查记录列表</a>
</div>
    <div id="content">
        <form action="/step5/list.htm" method="get" id="searchForm">
              <div align="center" class="list_table stripe" style="width:80%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;">
                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;" >项目编号:</div>
                     <div><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></div>
                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;" >项目名称:</div>
                     <div><input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div>
                     <div class="sub_sear" onclick="javascript:$('#searchForm').submit()">查询</div>
                    </div>
             </div>
         </form>
          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
                   <tr class="head">
	                     <td style=" background-color:#e6f6ff; text-align:center; width:30px;"><p>序号 </p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;"><p>项目编号</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;"><p>项目名称</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;width:70px;"><p>立项日期</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;"><p>委托单位</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;width:75px;"><p>咨询类型</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;"><p>编审类型</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;width:50px;"><p>紧急程度</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;width:60px;"><p>项目负责人</p></td>
	                     <td style=" background-color:#e6f6ff; text-align:center;width:120px;"><p>操作</p></td>
                   </tr>
                 <#if pageBean.list?size !=0 >
       			 <#list pageBean.list as step5>     
                   <tr>
	                     <td width="30" style="text-align:center;"><p>${step1_index + 1}</p></td>
	                     <td>${step5.project.no}</td>
	                     <td>${step5.project.name} </td>
	                     <td>${step5.project.recordDate}</td>
	                     <td>${step5.project.customer.cusName}</td>
	                     <td>${step5.project.serviceType.name}</td>
	                     <td>${step5.project.editorialType.name}</td>
	                     <td>${step5.project.urgentType.name}</td>
	                     <td>${step5.project.recordName}</td>
	                     <td style=" text-align:center;">
			                     <a href="/step5/edit.htm?step5.id=${step5.id}" title="编辑">编辑</a>
			                     <a href="/step5/show.htm?step5.id=${step5.id}" title="查看">查看</a>
	                     </td>                             
				</tr>
   			</#list>
   			<#else>
   				<tr>
   					<td style="text-align:center;" colspan="10">数据已处理!</td>
   				</tr>
   			</#if>
           </table>
              <div style="width:80%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
			  </div>

    </div>
<script>
	function delStep5(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		var url = "/step5/delete.htm?step5.id=" + id;
		$.ajax({
			type:"post",
			url:url,
			asyn:true,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}
	
	function addStep5() {
         window.parent.location.href="/step5/new.htm";
	}
</script>