<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">发票管理</a> >
<a href="#">发票列表</a>
</div>
    <div id="content">
        <form action="/invoice/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:80%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;">
                     <div class="sub_add" onclick="javascript:addInvoice();">添加</div>
                     <div class="sub_add" onclick="javascript:addProject();">作废</div>
                     <div class="sub_add" onclick="delProject();">删除</div>
                     <table>
                     <tr>
                     <td><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >项目名称:</div></td>
                     <td><input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >发票编号人:</div></td>
                     <td><input name="invoice.invoiceNo" type="text" value="${invoice.invoiceNo}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td rowspan="2"><div class="sub_sear" onclick="javascript:$('#searchForm').submit()">查询</div></td>
                     </tr>
                     <tr>
                     <td><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >项目编号:</div></td>
                     <td><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >收费状态</div></td>
                     <td>
	         			<select id="reStatus" name="invoice.reStatus" value="${invoice.reStatus}" style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;">
							<option value="">-选择汇款状态-</option>
							<option value="1">未回款</option>
							<option value="2">已回款</option>
							<option value="3">部分回款</option>
						</select>
                     </td>
                     </tr>
                     </table>
                    </div>
             </div>
         </form>
          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:30px;"><p>序号 </p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>项目编号</p></td>
                                          <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>项目名称</p></td>                                        
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>实际应收总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>累计已开票(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>已到账总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>发票编号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>开票金额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:90px;"><p>开票日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>收费状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>项目负责人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;"><p>项目类别</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;width:120px;"><p>操作</p></td>
                                       </tr>
                            <#list pageBean.list as invoice>
                            		<input type="hidden" value=${invoice.id}/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${invoice_index + 1}</p></td>
                                         <td>${invoice.project.no}</td>
                                          <td>${invoice.project.name}</td>
                                          <td>${invoice.rAccount}</td>
                                          <td>${invoice.cAccount}</td>
                                          <td>${invoice.aAccount}</td>
                                          <td>${invoice.invoiceNo}</td>
                                          <td>${invoice.invoiceAmount}</td>
                                          <td>${invoice.invoiceDate?string('yyyy-MM-dd')}</td>
                                          <td>
                                          <#if invoice.reStatus ==1 >未回款</#if>
                                          <#if invoice.reStatus ==2 >已回款</#if>
                                          <#if invoice.reStatus ==3 >部分回款</#if>
                                          </td>                                        
                                          <td></td>
                                          <td>${invoice.project.projectType.name}</td>
                                          <td>
                                  			<a href="javascript:deleteUser('${u.id}')" title="删除">删除</a>
                                  			<a href="edit.htm?id=${u.id}" title="编辑">编辑</a>
											<a href="/t_customer/show.htm?t_Customer.id=44" title="查看">查看</a>
                                          </td>
                					</tr>
   							</#list>
           </table>
                 <div style="width:80%; margin:0 auto; min-width:980px;">
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
    function addInvoice(){
    window.parent.location.href="/invoice/new.htm";
    }
    function delProject() {
		var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
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
		});
	}
    </script>