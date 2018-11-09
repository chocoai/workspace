   <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
      <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">招聘计划-统计</a>
</div>
    <div id="content">
        <form action="/t_recr/report.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		
                     <div class="t_sub_excel" onclick="javascript:exportScore();">Excel</div>
                     <div class="t_sub_stamp" onclick="javascript:pang();">打印</div><br/><br/>
                     <table  border="1"  align="center" cellpadding="1" cellspacing="1" style="width:100%; margin:0 auto; min-width:980px;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;" >需求部门</div></td>
                     <td style="width:60px"><input id="dept_id" name="dept_id" type="text" value="${dept_id}" style=" float:left;line-height:22px; height:30px;width:150px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>

                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >登记时间</div></td>
                     <td style="width:60px"><input id="rtimec" name="rtimec"  onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${rtimec}"  type="text" readonly= "true" value="${rtimec}" style=" float:left;line-height:22px; width:120px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >至</div></td>
                     <td style="width:60px"><input id="rtimej" name="rtimej"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${rtimej}" style=" float:left;line-height:22px; height:30px;width:120px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                    <td style="width:60px"><div class="sub_sear" style="margin:6px 0px 0px 0px;" onclick="javascript:$('#searchForm').submit()">查询</div></td>
                     <td></td>
                     </tr>
                     </table>
                    
             </div>
         </form>
          <div style="clear:both; margin:0px 0px 0px 0px;"></div>
          <!--startprint-->                                           
              <table border="1" align="center" cellpadding="1"  border="1px" cellspacing="0px" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:30px;background-color:#e6f6ff;border:1px solid #ccc"><p>序号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>需求部门</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>登记人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>登记时间</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>人员需求岗位描述</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:10%;background-color:#e6f6ff;border:1px solid #ccc"><p>备注</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>处理时间</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>处理意见</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:15%;background-color:#e6f6ff;border:1px solid #ccc"><p>意见明细</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as t_hrrecruitment>
                            		<input type="hidden" value="${t_hrrecruitment.id}"/>     
                                       <tr>
                                         <td width="30" style="text-align:center;border:1px solid #ccc"><p>${(t_hrrecruitment_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hrrecruitment.dept_id.name}</td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hrrecruitment.user_id.name}</td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hrrecruitment.rdate}</td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hrrecruitment.demand}</td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hrrecruitment.remark}</td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hrrecruitment.t_hropinion.cdate}</td>
                                          <td style="text-align:left;border:1px solid #ccc">
                                          <#if t_hrrecruitment.t_hropinion.opinion ==1>同意</#if>
                                          <#if t_hrrecruitment.t_hropinion.opinion ==2>不同意</#if>
                                          </td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hrrecruitment.t_hropinion.detail}</td>
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="9">暂无数据!</td></tr>
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
    td{
  
    word-break: break-all;
    text-align:center;
    }
    </style>
    <script>
	function exportScore(){  
	$('#searchForm').attr('action', '/t_recr/excel.htm').submit();
	$('#searchForm').attr('action', '/t_recr/report.htm');
}  
//打印
 function pang(){
 preview();
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