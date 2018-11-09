<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">年检提醒-列表</a>
</div>
    <div id="content">
        <form action="/t_liregistration/zzlist.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;">
                     <table style="width:100%">
                     <tr>
                     <td  style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >证照名称</div></td>
                     <td style="width:60px"<div><input name="liregistration.licname" type="text" value="${liregistration.licname}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></div></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >证照编号</div></td>
                     <td style="width:60px"><div><input name="liregistration.licnumber" type="text" value="${liregistration.licnumber}" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >状态</div></td>
                     <td style="width:60px"><div >  <select name="liregistration.licstatus" style=" float:left;line-height:22px; width:200px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
					<option value="">全部</option>
					<option value="1" <#if  liregistration.licstatus==1>selected='selected'</#if> >闲置</option>
				    <option value="2" <#if  liregistration.licstatus==2>selected='selected'</#if> >占用</option>
					<option value="3" <#if  liregistration.licstatus==3>selected='selected'</#if> >其他</option>
				    </select></div></td>
				    <td style="width:60px"><div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin:6px 0px 0px 0px">查询</div></td>
                     <td></td>
                     </tr>
                     </table>
                    </div>
             </div>
         </form>
          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:30px;background-color:#e6f6ff;width:4%;"><p>序号 </p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:30px;background-color:#e6f6ff;width:4%;"><p>状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:15%;"><p>证照编号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:15%;"><p>证照名称</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:6%;"><p>标识</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:14%;"><p>颁发部门</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:14%;"><p>颁发日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;width:14%;"><p>有效期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; background-color:#e6f6ff;width:14%;"><p>下次年检日期</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as liregistration>
                            		<input type="hidden" value=${liregistration.id}/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(liregistration_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="text-align:left;">
	                     					<#if liregistration.licstatus ==1 >闲置</#if>
											<#if liregistration.licstatus ==2 >占用</#if>
											<#if liregistration.licstatus ==3 >其他</#if>
                                         </td>
                                         <td style="text-align:left;">${liregistration.licnumber}</td>                                        
                                         <td style="text-align:left;">${liregistration.licname}</td>
                                         <td style="text-align:left;">
                                         <#if liregistration.characteristic ==1 >正本</#if>
										 <#if liregistration.characteristic ==2 >副本</#if>
                                         </td>
                                         <td style="text-align:left;">${liregistration.prebranch}</td>
                                          <td style="text-align:left;">${liregistration.predate}</td>
                                          <td style="text-align:left;">${liregistration.effdate}</td>
                                           <td style="text-align:left;" class="bb">${liregistration.anndate}</td>                  
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="9">暂无数据!</td></tr>
   							</#if>
           </table>
           <br/>
              <div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
				</div>

    </div>
    <style>
    td{
    word-break: break-all;
    }
    .clo{
   color:red;
    }
    </style>
<script>
	//超期部分加样式
	$(function(){
	var bb=$(".bb").length;
	for(var i=0;i<bb;i++){
	var aa=$(".bb").eq(i).html();
	var start=new Date(aa.replace("-", "/").replace("-", "/"));//下次年检时间
	var date=new Date();//当前时间
	if(start<date){
	$(".bb").eq(i).parent().addClass("clo");
	}
	}
	});
</script>



