<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">人员信息一览-列表</a>
</div>
    <div id="content">
        <form action="/t_reso/report.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
   <div class="t_sub_excel" onclick="javascript:exportScore();">Excel</div>
                     <div class="t_sub_stamp" onclick="javascript:pang();">打印</div><br/><br/>
                     <table style="width:100%;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >姓名</div></td>
                     <td style="width:60px"><input id="t_hremployeename" name="t_hremployee.name" type="text" value="${t_hremployee.name}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >毕业院校</div></td>
                     <td style="width:60px"><input id="t_hremployeeshoolname" name="shoolname" type="text" value="${shoolname}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >联系电话</div></td>
                     <td style="width:60px"><input id="t_hremployeephone" name="t_hremployee.phone" type="text" value="${t_hremployee.phone}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >性别</div></td>
                     <td style="width:60px"><div >  <select id="t_hremployeesex" name="t_hremployee.sex" style=" float:left;line-height:22px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
					<option value="">全部</option>
					<option value="1" <#if  t_hremployee.sex==1>selected='selected'</#if> >男</option>
				    <option value="2" <#if  t_hremployee.sex==2>selected='selected'</#if> >女</option>
				    </select></div></td>
				    <td style="width:60px"><div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin:6px 0px 0px 0px">查询</div></td>
				    <td></td>
                     </tr>
                     </table>
                    
             </div>
         </form>
          <div style="clear:both;"></div>
          <!--startprint-->                                           
              <table border="1" align="center" cellpadding="1"  border="1px" cellspacing="0px" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:30px;background-color:#e6f6ff;width:2%;"><p>序号 </p></td>
                                         <td style=" border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:4%;"><p>姓名</p></td>
                                         <td style=" border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:2%;"><p>性别</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:6%;"><p>出生年月</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:8%;"><p>专业</p></td>
                                         <td style=" border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:4%;"><p>学历</p></td>
                                         <td style=" border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:8%;"><p>毕业院校</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:8%;"><p>联系电话</p></td>
                                         <td style="border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>分配部门</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>人员信息备注</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>证书名称</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>证书编号</p></td>
                                         <td style=" border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:4%;"><p>等级</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>专业</p></td>
                                         <td style="border:1px solid #ccc; background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>签发单位</p></td>
                                         <td style=" border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>签发日期</p></td>
                                         <td style="border:1px solid #ccc;background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:7%;"><p>证书备注</p></td>
                                         
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as t_hremployee>
                            		<input type="hidden" value="${t_hremployee.id}"/>     
                                       <tr>
                                         <td width="30" style="text-align:center;border:1px solid #ccc;"><p>${(t_hremployee_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                          <td style="border:1px solid #ccc;text-align:left;">${t_hremployee.name}</td>
                                          <td style="border:1px solid #ccc;text-align:left;">
                                          <#if t_hremployee.sex ==1>男</#if>
                                          <#if t_hremployee.sex ==2>女</#if>
                                          </td>
                                          <td style="border:1px solid #ccc;text-align:left;"><#if t_hremployee.birth !="">${t_hremployee.birth?string('yyyy-MM-dd')}</#if></td>
                                          <td style="border:1px solid #ccc;text-align:left;">${t_hremployee.major}</td>
                                          <td colspan="2" style="text-align:left;padding:0px 0px 0px 0px;border:1px solid #ccc">
                                          <table class="sssss" border=0 cellspacing=0 cellpadding=0 style="text-align:left;width:100%;height:100%">
                                          <#list t_hremployee.hredlist as s>
                                          <tr class="hredlistss" >
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-left:0;border-right:0;">${s.education}</td>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-right:0;">${s.college}</td>
                                          </tr>
                                          </#list>
                                          </table>
                                          </td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hremployee.phone}</td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hremployee.dept_id.name}</td>
                                          <td style="text-align:left;border:1px solid #ccc">${t_hremployee.remark}</td>
                                          <td style="text-align:left;padding:0px 0px 0px 0px;border:1px solid #ccc" colspan="7">
                                          <table class="zzzzz" border=0 cellspacing=0 cellpadding=0 style="text-align:left;width:100%;height:100%">
                                          <#list t_hremployee.hrcelist as s>
                                          <tr>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-left:0;border-right:0;">${s.name}</td>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-right:0;">${s.no}</td>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-right:0;">${s.grade}</td>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-right:0;">${s.major}</td>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-right:0;">${s.issuing_unit}</td>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-right:0;"><#if  s.idate!="">${s.idate?string('yyyy-MM-dd')}</#if></td>
                                          <td style="border:1px solid #ccc;width:14.29%;text-align:left;border-bottom:0;border-right:0;">${s.remark}</td>
                                          </#list>
                                          </tr>
                                          </table>
                                          </td>
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="17">暂无数据!</td></tr>
   							</#if>
           </table>
           <!--endprint-->
              <br/>
              <div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
				</div>

    </div>
    <div >

</div>
	<style>
    td{
    word-break: break-all;
    text-align:center;
    
    }
    </style>
    <script>
    $(function(){
    for(var i=0;i<$(".sssss").length;i++){
   $(".sssss").eq(i).find("tr").eq(0).children("td").css("border-top","0");
    }
    for(var i=0;i<$(".zzzzz").length;i++){
   $(".zzzzz").eq(i).find("tr").eq(0).children("td").css("border-top","0");
    }
    });
   function exportScore(){  
   	$('#searchForm').attr('action', '/t_reso/excel.htm').submit();
   	$('#searchForm').attr('action', '/t_reso/report.htm')
}  
//打印
 function pang(){
 		preview();
		//reportPrint();
 
 } 
function reportPrint(){
	 	$('#searchForm').attr('target', 'newWindow');
	    $('#searchForm').attr('action', '/t_reso/reportPrint.htm');
	    
        var iWidth=1000;                          //弹出窗口的宽度; 
        var iHeight=600;                         //弹出窗口的高度; 
        //获得窗口的垂直位置 
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
        //获得窗口的水平位置 
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
	    var win =  window.open('about:blank','newWindow','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes,titlebar=no');
	    $('#searchForm').submit(); 
	    $('#searchForm').removeAttr("target");
	    $('#searchForm').attr('action', '/t_reso/report.htm');
}
 
 
function preview(){
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