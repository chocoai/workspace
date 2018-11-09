<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
<a href="#">收款管理统计</a>
</div>
    <div id="content">
        <form action="/t_fire/report.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:6px 0px 0px 0px; clear:both; width:100%;">
            		<div class="t_sub_excel" onclick="javascript:exportScore();">Excel</div>
                     <div class="t_sub_stamp" onclick="javascript:pang();">打印</div>
                     <table width="100%" >
                     <tr>
		                     <td style="width:20%" colspan="1">
				                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目名称:</div>
				                     <input name="projectname" type="text" value="${projectname}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" />
		                     </td>
		                     <td style="width:20%" colspan="1">
				                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目编号:</div>
				                     <input name="projectno" type="text" value="${projectno}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" />
		                     </td>
				             <td style="width:20%;text-align:center;" colspan="1">
				                     <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >发票编号:</div>
				                     <input name="invoiceNo" type="text" value="${invoiceNo}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" />
		                     </td>
		                    <td rowspan="2" colspan="1" style="width:20%" align="center;">
		                   			 <div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin:6px 0px 0px 0px;">查询</div>
		                    </td>
                    </tr>
                    <tr>
		                    <td style="width:20%">
				                    <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >付款单位:</div>
				                     <input name="payCompany" type="text" value="${payCompany}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" />
		                     </td>
		                      <td style="width:20%">
				                      <div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >到账日期:</div>
				                      <input name="rtimec" id="rtimec" onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${rtimec}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" />
		                    </td>
		                     <td style="width:20%">
				                     <div style=" float:left;text-align:center;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >至:</div>
				                     <input name="rtimej"  id="rtimej" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${rtimej}" style=" float:left;line-height:22px; height:30px;width:150px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" />
		                     </td>
                     </tr>
                     </table>
                    </div>
             </div>
         </form>
          <div style="clear:both;"></div>
          <!--startprint-->                                           
              <table border="1" align="center" cellpadding="1"  border="1px" cellspacing="0px" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px; margin-top:8px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:30px;background-color:#e6f6ff;border:1px solid #ccc"><p>序号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>项目编号</p></td>
                                          <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>项目名称</p></td>  
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>发票编号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>付款单位</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc;width:100px"><p>开票金额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc"><p>合计</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc;width:100px"><p>应收金额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc;width:100px"><p>到账金额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc;width:100px"><p>到账日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;border:1px solid #ccc;width:100px"><p>登记人</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as rece>
                            		<input type="hidden" value=${rece.id}/>     
                                       <tr>
                                       
                                         <td width="30" style="text-align:center;border:1px solid #ccc"><p>${(rece_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="border:1px solid #ccc">${rece.project.no}</td>
                                          <td style="border:1px solid #ccc">${rece.project.name}</td>
                                          <td  style="border:1px solid #ccc" >${rece.invoiceNo}</td>
                                          <td style="border:1px solid #ccc">${rece.payCompany}</td>
                                          <td  style="width:100px;border-bottom:0;border-left:0;border-right:0;border:1px solid #ccc" class="hc2">${rece.invoiceAmount}</td>
                                          <td style="border:1px solid #ccc">${rece.aAccount}</td>
                                          <td style="padding:0px 0px 0px 0px;border:1px solid #ccc" colspan="4">
                                         <table class="zzzzz" border=0 cellspacing=0 cellpadding=0 style="width:100%;height:100%">
                                         <#list rece.receivableslist as receivableslist>
                                         <tr>
                                          <td  style="border:1px solid #ccc;width:25%;border-bottom:0;border-left:0;border-right:0;" class="hc1">${receivableslist.receivable}</td>
                                          <td  style="border:1px solid #ccc;width:25%;border-bottom:0;border-right:0;" class="hc3">${receivableslist.arrivalAmount}</td>
                                          <td  style="border:1px solid #ccc;width:25%;border-bottom:0;border-right:0;" ><#if receivableslist.arrivalDate!="">${receivableslist.arrivalDate?string('yyyy-MM-dd')}</#if></td>
                                          <td  style="border:1px solid #ccc;width:25%;border-bottom:0;border-right:0;">${receivableslist.user.name}</td>
                                          </tr>
                                          </#list>
                                          </table>
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
  </div>
	<style>
    td{
       word-break: break-all;
    }
    </style>
    <script>
    $(function(){
for(var i=0;i<$(".zzzzz").length;i++){
   $(".zzzzz").eq(i).find("tr").eq(0).children("td").css("border-top","0");
    }
});
function exportScore(){  
			$('#searchForm').attr('action', '/t_fire/excel.htm').submit();
			$('#searchForm').attr('action', '/t_fire/report.htm');
}  
//打印
 function pang(){
		   //preview();
		 
		   $('#searchForm').attr('target', 'newWindow');
		   $('#searchForm').attr('action', '/t_fire/reportPrint.htm');
		   
		   var iWidth=1200;                          //弹出窗口的宽度; 
           var iHeight=600;                         //弹出窗口的高度; 
           //获得窗口的垂直位置 
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           //获得窗口的水平位置 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open('about:blank','newWindow','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
		   $('#searchForm').submit(); 
		   $('#searchForm').removeAttr("target");
		   $('#searchForm').attr('action', '/t_fire/report.htm'); 

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