<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/marketManager.htm">经营管理</a> >
<a >项目列表</a>
</div>
    <div id="content">
        <form action="/project/myProjectList.htm" method="get" id="searchForm">
              <div align="center" class="list_table stripe" style="width:80%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;">
                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;" >项目编号:</div>
                     <div><input name="all" id="allid" type="hidden" value="market" ><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></div>
                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;" >项目名称:</div>
                     <div><input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div>
                     <div class="sub_sear" onclick="javascript:getMyProject();">查询</div>
                    </div>
             </div>
         </form>

          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
                                       <tr class="head">
                                         <td style=" background-color:#e6f6ff; text-align:center; width:30px;"><p>序号 </p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>项目编号</p></td>
                                         <td colspan="2" style=" background-color:#e6f6ff; text-align:center;"><p>项目名称</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;width:90px; width:60px;"><p>立项日期</p></td>
                                         <td colspan="2" style=" background-color:#e6f6ff; text-align:center;"><p>委托单位</p></td>
                                         <td  style=" background-color:#e6f6ff; text-align:center;width:90px; width:75px;"><p>咨询类型</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center; width:45px;"><p>紧急程度</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center; width:45px;"><p>立项人</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>项目节点</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;width:120px;"><p>操作</p></td>
                                       </tr>
                            <#list pageBean.list as project>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${project_index + 1}</p></td>
                                         <td>${project.no}</td>
                                         <td colspan="2">${project.name} </td>
                                         <td >${project.recordDate}</td>
                                         <td colspan="2">${project.customer.cusName}</td>
                                         <td >${project.serviceType.name}</td>
                                           <td>${project.urgentType.name}</td>
                                         <td>${project.recordName}</td>
                                          <td style="color:red;">
											<#if project.step ==-5 >立项</#if>
											<#if project.step ==-4 >投标策划</#if>
											<#if project.step ==-3 >投标实施</#if>
											<#if project.step ==-2 >投标总结</#if>
											<#if project.step ==-1 >合同评审</#if>
											<#if project.step ==0 >合同登记</#if>
											<#if project.step ==1 >咨询任务书</#if>
											<#if project.step ==2 >资料登记</#if>
											<#if project.step ==3 >编制咨询方案</#if>
											<#if project.step ==4 >整理资料清单</#if>
											<#if project.step ==5 >现场勘察</#if>
											<#if project.step ==6 >底稿编制</#if>
											<#if project.step ==7 >校对</#if>
											<#if project.step ==8 >审核</#if>
											<#if project.step ==9 >审定</#if>
											<#if project.step ==10 >征求意见</#if>
											<#if project.step ==11 >成果文件编制</#if>
											<#if project.step ==12 >成果文件签订签发</#if>
											<#if project.step ==13 >回访记录</#if>
											<#if project.step ==14 >项目总结</#if>
											<#if project.step ==15 >资料归档</#if>	
											<#if project.step ==16 >已归档</#if>											
									</td>
                                         <td style=" background-color:#f6f6ff;text-align:center;width:120px;"><a href="/project/checkMarketPerson.htm?project.id=${project.id}">员工预览</a></td>
                                                  
                				</tr>
   							</#list>
   			<tr style="height:5px;"></tr>				
          </table>
          	 <div style="width:80%; margin:0 auto; min-width:980px;">
							<#import "/WEB-INF/tld/page.ftl" as tt>
							<@tt.page pageBean=pageBean formId="searchForm" />
			 </div>
    </div>
<script>
	function delProject(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		
		var url = "/project/delete.htm?project.id=" + id;
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
	function getMyProject(){
		document.getElementById("searchForm").action = "/project/myProjectList.htm";
		document.getElementById("allid").value ="market";
		document.getElementById("searchForm").submit();
	}
	
	function addProject() {
         window.parent.location.href="/project/new.htm";
	}
</script>



