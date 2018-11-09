<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
<a href="/requ/list.htm">项目请款</a>
</div>
    <div id="content">
        <form action="/requ/list.htm" method="get" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;margin-top:8px;">
                     <div class="sub_add" onclick="javascript:addRequ();">添加</div>
                     <div style=" float:left;line-height:30px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;vertical-align:middle" >项目编号:</div>
                     <div><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></div>
                     <div style=" float:left;line-height:30px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;vertical-align:middle" >项目名称:</div>
                     <div><input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div>
                     <div class="sub_sear" onclick="javascript:$('#searchForm').submit()">查询</div>
                    </div>
             </div>
         </form>
              <div style="clear:both;"></div>                    
                                     
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe"  style="margin-top:8px;">
                                       <tr class="head">
                                         
                                         <td style=" background-color:#e6f6ff; text-align:center; width:45px;"><p>编号 </p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>项目编号</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>项目名称</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>请款金额 </p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>请款日期</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;width:120px;"><p>操作</p></td>
                                       </tr>
                            <#list pageBean.list as requisition>        
                                       <tr>
                                         <td width="30" style="text-align:center;">${requisition_index + 1}</td>
                                         <td>${requisition.project.no}</td>
                                         <td>${requisition.project.name}</td>
            
                                         <td>${requisition.amount}</td>
                                         <td>${requisition.requisitionDate}</td>

                                         <td style=" text-align:center;"><a href="javascript:delRequ(${requisition.id})" title="删除">删除</a>
                                         <a href="/requ/edit.htm?requisition.id=${requisition.id}" title="编辑">编辑</a>
                                         <a href="/requ/show.htm?requisition.id=${requisition.id}" title="查看">查看</a>
                                         	</td>                             
                					</tr>
   							</#list>
           </table>
              <div style="width:90%; margin:0 auto; min-width:980px;margin-top: -30px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
				</div>

    </div>
<script>
	function delRequ(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		var url = "/requ/delete.htm?requisition.id=" + id;
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
	function addRequ() {
         window.parent.location.href="/requ/new.htm";
	}
</script>



