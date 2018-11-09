 <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
<a href="#">发票管理列表</a>
</div>
    <div id="content">
        <form action="/t_invoice/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%; margin-top:8px;">
                     <div class="sub_add" onclick="javascript:addInvoice();" >添加</div>
                     <div class="t_sub_cancel" onclick="cancelid();">作废</div>
                     <div class="t_sub_delete" onclick="delProject();">删除</div><br/><br/>
                     <table width="100%" style=" margin-top:-12px;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:1px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目名称</div></td>
                     <td style="width:60px"><input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:1px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >发票编号</div></td>
                     <td style="width:60px"><input name="invoice.invoiceNo" type="text" value="${invoice.invoiceNo}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:1px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >项目编号</div></td>
                     <td style="width:60px"><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:1px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >收费状态</div></td>
                     <td style="width:60px">
	         			<select id="reStatus" name="invoice.reStatus" value="${invoice.reStatus}" style="float:left;line-height:22px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;height:30px;line-height:30px;overflow:hidden;">
							<option value="" >全部</option>
							<option value="1" <#if invoice.reStatus==1>selected='selected'</#if>>未回款</option>
							<option value="2" <#if invoice.reStatus==2>selected='selected'</#if>>已回款</option>
							<option value="3" <#if invoice.reStatus==3>selected='selected'</#if>>部分回款</option>
						</select>
                     </td>
                     <td></td>
                     <td><div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin:6px 0px 0px 0px;">查询</div></td>
                     </tr>
                     </table>
                    </div>
             </div>
         </form>
          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;  margin-top:8px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:30px;background-color:#e6f6ff;"><p>序号 </p></td>
                                          <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:30px;background-color:#e6f6ff;"><p><input id="qxcheckbox"  type="checkbox"/></p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>项目编号</p></td>
                                          <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>项目名称</p></td>                                        
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>实际应收总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>累计已开票(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>已到账总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>发票编号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>开票金额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:90px;background-color:#e6f6ff;"><p>开票日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>收费状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>项目负责人</p></td>
                                       <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>状态</p></td>
                                       <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as invoice>
                            		<input type="hidden" value=${invoice.id}/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(invoice_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                          <td style="text-align:center;"><input name="a" type="checkbox" autocomplete="off" value="${invoice.id}" /></td>
                                         <td  >${invoice.project.no}</td>
                                          <td >${invoice.project.name}</td>
                                          <td class="hc1">${invoice.rAccount}</td>
                                          <td class="hc2">${invoice.cAccount}</td>
                                          <td class="hc3">${invoice.aAccount}</td>
                                          <td >${invoice.invoiceNo}</td>
                                          <td class="hc4">${invoice.invoiceAmount}</td>
                                          <td ><#if invoice.invoiceDate!="">${invoice.invoiceDate?string('yyyy-MM-dd')}</#if></td>
                                          <td>
                                          <#if invoice.reStatus ==1 >未回款</#if>
                                          <#if invoice.reStatus ==2 >已回款</#if>
                                          <#if invoice.reStatus ==3 >部分回款</#if>
                                          </td>                                        
                                          <td >${invoice.project.recordName}</td>
                                         <td  style="text-align:center;">
                                          <#if invoice.fistatu ==1 >正常</#if>
                                          <#if invoice.fistatu ==2 >作废</#if>
                                          </td> 
                                          <td style="text-align:left;">
                                          <#if invoice.fistatu ==1 >
                                          <a href="/t_invoice/new.htm?invoice.id=${invoice.id}" title="编辑">编辑</a>
                                           </#if>
                                         <a href="/t_invoice/show.htm?invoice.id=${invoice.id}" title="查看">查看</a>
                                          </td>
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="14">暂无数据!</td></tr>
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
    </style>
    <script>
    function addInvoice(){
    window.parent.location.href="/t_invoice/new.htm";
    }
    function delProject() {
    if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
		var url = "/t_invoice/delete.htm?Stringid=" + id;
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
	function cancelid(){
	if (!confirm("确定作废?")) {
			return;
		}
	var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      if(id==""){
      alert("请选择作废项!");
      return;
      }
		var url = "/t_invoice/cancel.htm?cancelid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('作废成功');
					window.parent.location.reload();
					location.reload();
				} else {
					alert('作废失败');
				}
			}
		});
	}
	$(function(){
	$("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='a']").attr("checked",'true');
		}else{
		$("[name='a']").removeAttr("checked",'true');
		}
		
		$("input[name='a']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		
		});
	for(var i=0;i<$(".hc1").length;i++){
	$(".hc1").eq(i).html(formatCurrency($(".hc1").eq(i).html()));
	$(".hc2").eq(i).html(formatCurrency($(".hc2").eq(i).html()));
	$(".hc3").eq(i).html(formatCurrency($(".hc3").eq(i).html()));
	$(".hc4").eq(i).html(formatCurrency($(".hc4").eq(i).html()));
	}
	});
	function formatCurrency(num) {  
    num = num.toString().replace(/\$|\,/g,'');  
    if(isNaN(num))  
        num = "0";  
    sign = (num == (num = Math.abs(num)));  
    num = Math.floor(num*100+0.50000000001);  
    cents = num%100;  
    num = Math.floor(num/100).toString();  
    if(cents<10)  
    cents = "0" + cents;  
    for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
    num = num.substring(0,num.length-(4*i+3))+','+  
    num.substring(num.length-(4*i+3));  
    return (((sign)?'':'-') + num + '.' + cents);  
}  
    </script>