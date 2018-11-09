<!DOCTYPE html>
﻿<html>
	<head>
		<title> 工程咨询云工作平台 </title>
		<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
			<style>
			.xl69 {
			mso-style-parent: style0;
			font-size: 16.0pt;
			font-weight: 700;
			font-family: 黑体, monospace;
			mso-font-charset: 134;
			text-align: center;
		}

		table{
		 table-layout:fixed;word-wrap:break-word;
		  border-collapse:collapse;
		  }
		table, td{
		mso-style-parent: style0;
		font-size: 9.0pt;
 		 border:1px solid black;
  		}
		</style>
		<script>
			function toPrint(){
				$("#print").print({
					globalStyles: true,
				    mediaPrint: false,
				    stylesheet: null,
				    noPrintSelector: ".no-print",
				    iframe: true,
				    append: null,
				    prepend: null,
				    manuallyCopyFormValues: true,
				    deferred: $.Deferred()
				});
			}
		</script>
	</head>
	<body link=blue vlink=purple>
		<div id="print" align="center">  
		        <p height=48 class=xl69 > 发票信息统计表        
		                                      
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
           
           
		</div><br/>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>
</html>
