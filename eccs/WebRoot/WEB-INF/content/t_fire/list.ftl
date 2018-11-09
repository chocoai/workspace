<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/financeManager.htm">财务管理</a> >
<a href="#">收款管理列表</a>
</div>
    <div id="content">
        <form action="/t_fire/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		<div class="article" style="margin:0; clear:both; width:100%;margin-top:8px;">
                     <table width="100%" >
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;" >项目名称</div></td>
                     <td style="width:60px"><input name="project.name" type="text" value="${project.name}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >发票编号</div></td>
                     <td style="width:60px"><input name="invoice.invoiceNo" type="text" value="${invoice.invoiceNo}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >项目编号</div></td>
                     <td style="width:60px"><input name="project.no" type="text" value="${project.no}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >收费状态</div></td>
                     <td style="width:60px">
	         			<select id="reStatus" name="invoice.reStatus" value="${invoice.reStatus}" style=" float:left;line-height:22px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;height:30px;line-height:30px;overflow:hidden;">
							<option value="">全部</option>
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
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>项目编号</p></td>
                                          <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>项目名称</p></td>                                        
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>实际应收总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>累计已开票(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>已到账总额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;width:120px; background-color:#e6f6ff;"><p>发票编号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>开票金额(元)</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; background-color:#e6f6ff;"><p>开票日期</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>收费状态</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>项目负责人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;width:80px ;background-color:#e6f6ff;"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as invoice>
                            		<input type="hidden" value="${invoice.id}">     
                                       <tr style="text-align:center;font-size:12px;" onclick="sshow(${invoice.id});">
                                         <td width="30" style="text-align:center;"><p>${(invoice_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td >${invoice.project.no}</td>
                                          <td>${invoice.project.name}</td>
                                          <td class="hc1">${invoice.rAccount}</td>
                                          <td class="hc2">${invoice.cAccount}</td>
                                          <td class="hc3">${invoice.aAccount}</td>
                                          <td width="30">${invoice.invoiceNo}</td>
                                          <td class="hc4">${invoice.invoiceAmount}</td>
                                          <td ><#if invoice.invoiceDate!="">${invoice.invoiceDate?string('yyyy-MM-dd')}</#if></td>
                                          <td >
                                          <#if invoice.reStatus ==1 >未回款</#if>
                                          <#if invoice.reStatus ==2 >已回款</#if>
                                          <#if invoice.reStatus ==3 >部分回款</#if>
                                          </td>                                        
                                          <td >${invoice.project.recordName}</td>
                                          <td style="text-align: center;" >
		                                          <a href="/t_fire/new.htm?id=${invoice.id}">添加收款</a>
		                                         <!-- <a onclick="sshow(${invoice.id});")><img style="vertical-align:middle;margin" src="../../../images/search2.png" width="20" height="20" title="查看收款"/></a>-->
                                          </td>
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="13">暂无数据!</td></tr>
   							</#if>
           </table>
                 <br/>
                 <div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
				</div>
<div style="width:90%; margin:50px auto; min-width:980px;"  id="zzzz">
<table   border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:100%" id="receid">
<tr class="head">
  <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center; width:30px;background-color:#e6f6ff;"><p>序号 </p></td>
  <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>发票编号</p></td>
   <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>付款单位</p></td>                                        
    <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>应收金额(元)</p></td>
    <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>开票金额(元)</p></td>
   <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>到账金额(元)</p></td>
   <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>到账日期</p></td>
   <td style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;"><p>登记人</p></td>
   <td  style=" background:url(../../../images/table_head.jpg) repeat top center; text-align:center;background-color:#e6f6ff;">操作</td>
    </tr>
    
</table>
                 
</div>
  </div>
	<style>
    td{
    word-break: break-all;
    }
    </style>
    <script>
    function sshow(c){
    $(".aac").remove();
    var url = "/t_fire/adetail.htm?id=" +c;
    var is=1;
		$.ajax({
			type:"get",
			url:url,
			dataType:'json',
			success:function(d){
			var invoiceNos=d.invoiceNos;
			var payCompanys=d.payCompanys;
			var invoiceAmounts=d.invoiceAmounts;
			var arrivalAmounts=d.arrivalAmounts;
			var arrivalDates=d.arrivalDates;
			var users=d.users;
			var Cumulatives=d.Cumulatives;
			var ids=d.ids;
			var invoid=d.invoid;
			var fistatu=d.fistatu;
				for(var i=0;i<invoiceNos.length;i++){
					var htmls='<tr class="aac"><td width="30" style="text-align:center;"><p>'+(is++)+'</p></td>';
								htmls+='<td  style="text-align:center;">'+invoiceNos[i]+'</td>';
								htmls+='<td  style="text-align:center;">'+payCompanys[i]+'</td>';
								//htmls+='<td  style="text-align:center;" class="hq1">'+formatCurrency(Cumulatives[i])+'</td>';
								htmls+='<td  style="text-align:center;" class="hq1">'+formatCurrency(invoiceAmounts[i])+'</td>';
								htmls+='<td  style="text-align:center;" class="hq2">'+formatCurrency(invoiceAmounts[i])+'</td>';
								htmls+='<td  style="text-align:center;" class="hq3">'+formatCurrency(arrivalAmounts[i])+'</td>';
								htmls+='<td style="text-align:center;">'+arrivalDates[i]+'</td>';
								htmls+='<td  style="text-align:center;">'+users[i]+'</td>';
								htmls+='<td style="text-align: center;">';
								if(fistatu==1){
								htmls+='<a onclick="manage('+ids[i]+','+invoid[i]+')" title="编辑">编辑</a>';
                                }
                                htmls+='<a href="/t_fire/show.htm?receivables.id='+ids[i]+'" title="查看">查看</a>';
                                htmls+='<a onclick="delProject('+ids[i]+')" title="删除">删除</a>';
								htmls+='</td></tr>';
								$("#receid").append(htmls);
				}
			}
		});
    }
    function manage(a,b){
    window.parent.location.href="/t_fire/new.htm?fireId="+a+"&id="+b;
    }
    function addInvoice(){
    window.parent.location.href="/t_invoice/new.htm";
    }
    function delProject(id) {
    if (!confirm("确定删除?")) {
			return;
		}
		var url = "/t_fire/delete.htm?receivables.id=" + id;
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
	$(function(){
	for(var i=0;i<$(".hc1").length;i++){
	$(".hc1").eq(i).html(formatCurrency($(".hc1").eq(i).html()));
	$(".hc2").eq(i).html(formatCurrency($(".hc2").eq(i).html()));
	$(".hc3").eq(i).html(formatCurrency($(".hc3").eq(i).html()));
	$(".hc4").eq(i).html(formatCurrency($(".hc4").eq(i).html()));
	}
	for(var i=0;i<$(".hq1").length;i++){
	$(".hq1").eq(i).html(formatCurrency($(".hq1").eq(i).html()));
	$(".hq2").eq(i).html(formatCurrency($(".hq2").eq(i).html()));
	$(".hq3").eq(i).html(formatCurrency($(".hq3").eq(i).html()));
	}
	});
	function fmoney(s, n) {  
        n = n > 0 && n <= 20 ? n : 2;  
        s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";  
        var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];  
        t = "";  
        for (i = 0; i < l.length; i++) {  
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");  
        }  
        return t.split("").reverse().join("") + "." + r;  
    }  
    
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