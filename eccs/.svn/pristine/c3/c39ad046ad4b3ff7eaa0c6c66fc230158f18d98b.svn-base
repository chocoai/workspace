 <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
 <a href="#">发票管理-统计</a>
 </div>
    <div id="content">
        <form action="/t_invoice/report.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%; margin-top:8px;">
                     <div class="t_sub_excel" onclick="javascript:exportScore();">Excel</div>
                     <div class="t_sub_stamp" onclick="javascript:pang();">打印</div><br/><br/>
                     <table width="100%" style=" margin-top:-12px;" >
                     <tr>
                     <td style="width:20%" colspan="1"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目名称:</div>
                     <input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:20%" colspan="1"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >发票编号:</div>
                     <input name="invoice.invoiceNo" type="text" value="${invoice.invoiceNo}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:20%" colspan="1"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目编号:</div>
                     <input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                    <td rowspan="2" style="width:20%" colspan="1"><div class="sub_sear" onclick="show();" style="margin:6px 0px 0px 0px;">查询</div></td>
                     </tr><tr>
                     <td style="width:20%" colspan="1"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >收费状态:</div>
	         			<select id="reStatus" name="invoice.reStatus" value="${invoice.reStatus}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;height:30px;line-height:30px;overflow:hidden;">
							<option value="" >全部</option>
							<option value="1" <#if invoice.reStatus==1>selected='selected'</#if>>未回款</option>
							<option value="2" <#if invoice.reStatus==2>selected='selected'</#if>>已回款</option>
							<option value="3" <#if invoice.reStatus==3>selected='selected'</#if>>部分回款</option>
						</select>
                     </td>
                   <td style="width:20%" colspan="1"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >开票日期:</div>
                     <input name="rtimec" id="rtimec" onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${rtimec}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:20%" colspan="1"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >至:</div>
                     <input name="rtimej"  id="rtimej" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${rtimej}" style=" float:left;line-height:22px; height:30px;width:150px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     </tr>
                     </table>
                    </div>
             </div>
         </form>
          <div style="clear:both;"></div> 
          <!--startprint-->                                          
              <table border="1" align="center" cellpadding="1"  border="1px" cellspacing="0px" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;  margin-top:8px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:30px;background-color:#e6f6ff;border:1px solid #ccc"><p>序号 </p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>项目编号</p></td>
                                          <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>项目名称</p></td>                                        
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>实际应收总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>累计已开票(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>未开票总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>发票编号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>开票金额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:90px;background-color:#e6f6ff;border:1px solid #ccc"><p>开票日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>收费状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>项目负责人</p></td>
                                       <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>状态</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as invoice>
                            		<input type="hidden" value=${invoice.id}/>     
                                       <tr>
                                         <td  style="text-align:center;width:30px;border:1px solid #ccc"><p>${(invoice_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td  style="border:1px solid #ccc">${invoice.project.no}</td>
                                          <td  style="border:1px solid #ccc">${invoice.project.name}</td>
                                          <td  style="border:1px solid #ccc" class="hc1">${invoice.rAccount}</td>
                                          <td  style="border:1px solid #ccc"  class="hc2">${invoice.cAccount}</td>
                                          <td  style="border:1px solid #ccc"  class="hc3">${invoice.wAccount}</td>
                                          <td  style="border:1px solid #ccc">${invoice.invoiceNo}</td>
                                          <td  style="border:1px solid #ccc"  class="hc4">${invoice.invoiceAmount}</td>
                                          <td  style="border:1px solid #ccc"><#if invoice.invoiceDate!="">${invoice.invoiceDate?string('yyyy-MM-dd')}</#if></td>
                                          <td  style="border:1px solid #ccc">
                                          <#if invoice.reStatus ==1 >未回款</#if>
                                          <#if invoice.reStatus ==2 >已回款</#if>
                                          <#if invoice.reStatus ==3 >部分回款</#if>
                                          </td>                                        
                                          <td  style="border:1px solid #ccc">${invoice.project.recordName}</td>
                                         <td  style="text-align:center;border:1px solid #ccc">
                                          <#if invoice.fistatu ==1 >正常</#if>
                                          <#if invoice.fistatu ==2 >作废</#if>
                                          </td> 
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="12">暂无数据!</td></tr>
   							</#if>
           </table>
           <!--endprint-->
                 <br/>
                 <div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
				</div>

  </div>
	<style> 
.table-a table{border:1px solid #F00} 
/* css注释：只对table标签设置红色边框样式 */ 
    td{
    word-break: break-all;
    
    }
    </style>
    <script>
    function exportScore(){  
    $('#searchForm').attr('action', '/t_invoice/excel.htm').submit();
    $('#searchForm').attr('action', '/t_invoice/report.htm');
}  
function show(){
$('#searchForm').attr('action', '/t_invoice/report.htm');
$('#searchForm').submit();
}
//打印
 function pang(){
    //preview();
    
    $('#searchForm').attr('target', 'newWindow');
    $('#searchForm').attr('action', '/t_invoice/reportPrint.htm');
    var win =  window.open("about:blank","newWindow","height=700,width=1200,top=40,left=70,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
    $('#searchForm').submit(); 
    $('#searchForm').removeAttr("target");
    $('#searchForm').attr('action', '/t_invoice/report.htm');
   
     
 } 
function preview()
{
bdhtml=window.document.body.innerHTML;//获取当前页的html代码
sprnstr="<!--startprint-->";//设置打印开始区域
eprnstr="<!--endprint-->";//设置打印结束区域
prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html

prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html
window.document.body.innerHTML=prnhtml;
window.print();
window.document.body.innerHTML=bdhtml;
}
    </script>