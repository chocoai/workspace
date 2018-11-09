<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="javascript:window.location.reload();">共享列表</a>
</div>
    <div id="content">
        <form action="/project/listShare.htm" method="get" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;">
                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;" >项目编号:</div>
                     <div><input name="all" id="allid" type="hidden" value="true" ><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></div>
                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;" >项目名称:</div>
                     <div><input name="name" type="text" value="${name}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div>
                     <div class="sub_sear" onclick="javascript:getMyProject();">查询</div>
                    </div>
             </div>
         </form>
          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
                      				 <tr class="head">
					                       <td style=" background-color:#e6f6ff; text-align:center; width:30px;"><p>序号 </p></td>
					                       <td style=" background-color:#e6f6ff; text-align:center;width:120px;"><p>项目编号</p></td>
					                       <td colspan="2" style=" background-color:#e6f6ff; text-align:center;"><p>项目名称</p></td>
					                       <td style=" background-color:#e6f6ff; text-align:center;width:70px;"><p>立项日期</p></td>
					                       <td colspan="2" style=" background-color:#e6f6ff; text-align:center;"><p>委托单位</p></td>
					                       <td  style=" background-color:#e6f6ff; text-align:center;width:90px;width:50px;"><p>咨询类型</p></td>
					                       <td style=" background-color:#e6f6ff; text-align:center;width:50px;"><p>紧急程度</p></td>
					                       <td style=" background-color:#e6f6ff; text-align:center;width:40px;"><p>立项人</p></td>
					                       <td style=" background-color:#e6f6ff; text-align:center;width:75px;"><p>项目节点</p></td>
					                       <td style=" background-color:#e6f6ff; text-align:center;width:60px;"><p>工作流程</p></td>
					                       <td style=" background-color:#e6f6ff; text-align:center;width:80px;"><p>其他</p></td>
                           			   </tr>
                           	 <#if pageBean.list?size !=0 > 		   
                            <#list pageBean.list as pro>     
                                       <tr onclick="javascript:project('/project/showmyproject.htm?project.id=${pro.project.id}')">
                                         <td width="30" style="text-align:center;"><p>${pro_index + 1}</p></td>
                                         <td>
                                         	<span title="${pro.project.no}">
	                                         	<#if (pro.project.no)?length gt 15> ${pro.project.no[0..15]}... <#else>${pro.project.no}</#if>
	                                         </span>
                                         </td>
                                         <td colspan="2">
	                                         <span title="${pro.project.name}">
	                                         	<#if (pro.project.name)?length gt 11> ${pro.project.name[0..11]}... <#else>${pro.project.name}</#if>
	                                         </span>
                                         </td>
                                         <td >${pro.project.recordDate}</td>
                                         <td colspan="2">
                                          <span title="${pro.project.customer.cusName}">
	                                         	<#if (pro.project.customer.cusName)?length gt 10> ${pro.project.customer.cusName[0..10]}... <#else>${pro.project.customer.cusName}</#if>
	                                      </span>
                                         </td>
                                         <td >${pro.project.serviceType.name}</td>
                                          <td>${pro.project.urgentType.name}</td>
                                         <td>${pro.project.recordName}</td>
                                          <td style="color:red;">
                                          	<#if pro.project.step!=16 >
                                          		<a href="javascript:void(0)" onclick="project('/step${pro.project.step}/show.htm?project.id=${pro.project.id}',event)">
													<#if pro.project.step ==0 >立项</#if>
													<#if pro.project.step ==1 >咨询任务书</#if>
													<#if pro.project.step ==2 >资料登记</#if>
													<#if pro.project.step ==3 >编制咨询方案</#if>
													<#if pro.project.step ==4 >整理资料清单</#if>
													<#if pro.project.step ==5 >现场勘察</#if>
													<#if pro.project.step ==6 >底稿编制</#if>
													<#if pro.project.step ==7 >校对</#if>
													<#if pro.project.step ==8 >审核</#if>
													<#if pro.project.step ==9 >审定</#if>
													<#if pro.project.step ==10 >征求意见</#if>
													<#if pro.project.step ==11 >成果文件编制</#if>
													<#if pro.project.step ==12 >成果文件签订签发</#if>
													<#if pro.project.step ==13 >回访记录</#if>
													<#if pro.project.step ==14 >项目总结</#if>
													<#if pro.project.step ==15 >资料归档</#if>	
												</a>
											<#else>													
												<#if pro.project.step ==16 >已归档</#if>
											</#if>																
									</td>
									<td style=" text-align:center;">
										<a onclick="project('/project/workflowShow.htm?project.id=${pro.project.id}',event)">工作流程</a>
									</td>
                                     <td style=" text-align:center;">                                       
                                     	<a href="javascript:delProject(${pro.id})">删除</a>
                                     </td>                     
                				</tr>
   							</#list>
   							<#else>
   								<tr>
   									<td style="text-align:center;" colspan="13">暂无数据!</td>
   								</tr>
   							</#if>
          </table>
           <div style="width:90%; margin:0 auto; min-width:980px;margin-top: -40px;">
				<#import "/WEB-INF/tld/page.ftl" as tt>
				<@tt.page pageBean=pageBean formId="searchForm" />
		   </div>
    </div>
<script>
	function delProject(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		var url = "/project/deleteShare.htm?id=" + id;
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
		 document.getElementById("searchForm").submit();
	}
	//阻止事件冒泡
	function stopEventBubble(event){
	        var e=event || window.event;

	        if (e && e.stopPropagation){
	            e.stopPropagation();    
	        }
	        else{
	            e.cancelBubble=true;
	        }
	 }
	function project(url,e){
		if(e){
			stopEventBubble(e);
		}
		window.location=url;
	}
	
	
</script>



