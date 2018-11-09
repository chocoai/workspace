<div id="map">
	<img src="/images/home.png" width="19" height="24" />当前位置：
	<a href="/workbench.htm">工作台</a> >
	<a href="/performanceManager.htm">绩效管理</a> >
	<a href="javascript:window.location.reload();">绩效列表</a>
</div>
    <div id="content">
        <form action="/performance/list.htm" method="get" id="searchForm">
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
                     <td style=" background-color:#e6f6ff; text-align:center;width:100px;"><p>操作</p></td>
                   </tr>
                   <#list pageBean.list as project>     
                   <tr>
					 <td width="30" style="text-align:center;"><p>${project_index + 1}</p></td>
					 <td>${project.no}</td>
					 <td>${project.name}</td>
					 <td>${project.recordDate?string("yyyy-MM-dd")}</td>
					 <td>${project.customer.cusName}</td>
					 <td>${project.serviceType.name}</td>
					 <td>${project.editorialType.name}</td>
					<td style=" text-align:center;">
					<#if project.performanceid ==-1 >
							<a href="/performance/new.htm?id=${project.id}" title="新增">新增</a>
					</#if>
					<#if project.performanceid ==1 >
			 			<a href="/performance/edit.htm?id=${project.id}" title="编辑">编辑</a>
						<a href="/performance/show.htm?id=${project.id}" title="查看">查看</a>
						<a href="javascript:deleteper(${project.id})" title="删除">删除</a>
					</#if>
					</td>                             
				</tr>
			</#list>
           </table>
          <div style="width:80%; margin:0 auto; min-width:980px;">
				<#import "/WEB-INF/tld/page.ftl" as tt>
				<@tt.page pageBean=pageBean formId="searchForm" />
		  </div>
    </div>
<script>
	function deleteper(duId) {
		if (!confirm("确定删除此表单")) {
			return;
		}
		$.ajax({
			type:"post",
			url:'/performance/delete.htm?id1=' + duId,
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
				if (d == '1') {
					location.href=location.href;
				} else {
					alert('删除失败');
				}
			}
		})
	}
</script>



