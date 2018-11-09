<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gb2312">
		<meta name=ProgId content=Excel.Sheet>
		<meta name=Generator content="Microsoft Excel 12">
		<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
		<style>
		.table-d table{
		//border:1px solid #000000;
		//border-collapse: collapse
		}
		.table-d table td{border:1px solid #000000;}
		table tr td{padding-left: 8px;padding-right: 8px;}
		</style>
		<script language="JavaScript">
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
	</br></br>
		<div id="print" align="center" class="table-d">
			<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:18px;margin-top:20px;margin-bottom:20px;">
				施工现场勘察记录
			</div>
		<table border="1" cellspacing="0" cellpadding="0" width="800" >
	  <tr>
		    <td width="13%" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">工程名称</font></td>
		    <td width="29%" style="background-color:#fff;" align="left"><font size="2">${project.name}</font></td>
		    <td width="28%" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">工程地点</font></td>
		    <td width="30%" style="background-color:#fff;" align="left"><font size="2">${step5.projectAddress}</font></td>
	  </tr>
	  <tr style="min-height: 100px;">
		    <td width="13%" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">勘察结果</font></td>
		    <td colspan="3">
		  			<#list step5LogoList as step5Logo>
		  					<font size="2">${step5Logo.ctime}：[${step5Logo.user.name}]&nbsp;&nbsp;${step5Logo.logoNext}</font><br>
		    		</#list>
		    </td>
	  </tr>
	  <tr>
		    <td rowspan="2" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">勘察结果确认</font></td>
		    <td colspan="3" style="padding-right: 0px; padding-left: 0px;">
					<table border="1" cellspacing="1" cellpadding="1" class="input_table" frame="void" style="width: 100%;border-collapse: collapse; ">
						<tbody >
								<tr style="background-color:#fff;">
									<td width="40%"  style=" text-align:center; font-weight:bold;">单位</td>
									<td width="40%"  style=" text-align:center; font-weight:bold;">法人代表</td>
								</tr>
								<#if step5ItemList!=null && step5ItemList?size!=0>
									<#list step5ItemList as step5Item>
											<tr>
												<td style="text-align:center;">${step5Item.company}</td>
												<td style="text-align:center;">${step5Item.companyRen}</td>
											</tr>
									</#list>
								<#else>
											<tr height="24px">
												<td style="text-align:center;"></td>
												<td style="text-align:center;"></td>
											</tr>
											<tr height="24px">
												<td style="text-align:center;"></td>
												<td style="text-align:center;"></td>
											</tr>
											<tr height="24px">
												<td style="text-align:center;"></td>
												<td style="text-align:center;"></td>
											</tr>
								</#if>
						</tbody >
					</table>
				</td>
			</tr>
          </table>
		</div>
		</br>
		<div align="center">
			<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
		</div>
		
	</body>

</html>
