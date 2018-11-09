<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=gb2312">
<meta name=ProgId content=Excel.Sheet>
<meta name=Generator content="Microsoft Excel 12">
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="../../../js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../../../js/jQuery.print.js"></script>
<style>
table {
	mso-displayed-decimal-separator: "\.";
	mso-displayed-thousand-separator: "\,";
	table-layout: fixed;
}

table tr td {
	padding-left: 8px;
	padding-right: 8px;
}

@page {
	margin: .75in .7in .75in .7in;
	mso-header-margin: .3in;
	mso-footer-margin: .3in;
}

ruby {
	ruby-align: left;
}

rt {
	color: windowtext;
	font-size: 9.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-char-type: none;
	display: none;
}

tr {
	mso-height-source: auto;
	mso-ruby-visibility: none;
}

col {
	mso-width-source: auto;
	mso-ruby-visibility: none;
}

br {
	mso-data-placement: same-cell;
}

ruby {
	ruby-align: left;
}

.style0 {
	mso-number-format: General;
	text-align: general;
	vertical-align: middle;
	mso-rotate: 0;
	mso-background-source: auto;
	mso-pattern: auto;
	color: black;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	border: none;
	mso-protection: locked visible;
	mso-style-name: 常规;
	mso-style-id: 0;
}

td {
	mso-style-parent: style0;
	padding-top: 1px;
	padding-right: 1px;
	padding-left: 1px;
	mso-ignore: padding;
	color: black;
	font-size: 11.0pt;
	font-weight: 400;
	font-style: normal;
	text-decoration: none;
	font-family: 宋体;
	mso-generic-font-family: auto;
	mso-font-charset: 134;
	mso-number-format: General;
	text-align: general;
	vertical-align: middle;
	border: none;
	mso-background-source: auto;
	mso-pattern: auto;
	mso-protection: locked visible;
	mso-rotate: 0;
	word-break: break-all;
	word-wrap: break-word;
}

.xl65 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	font-weight: 700;
	text-align: center;
	border: .5pt solid windowtext;
}

.xl66 {
	mso-style-parent: style0;
	font-size: 11.0pt;
	font-weight: 700;
	text-align: center;
	border-top: 1.5pt solid windowtext;
	border-right: 1.5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: 1.5pt solid windowtext;
}

.xl67 {
	mso-style-parent: style0;
	font-weight: 700;
	text-align: center;
	border-top: 1.0pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl68 {
	mso-style-parent: style0;
	font-weight: 700;
	text-align: center;
	border-top: 1.0pt solid windowtext;
	border-right: 1.0pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl69 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	font-weight: 700;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: 1.5pt solid windowtext;
}

.xl70 {
	mso-style-parent: style0;
	font-size: 10.0pt;
	font-weight: 700;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: 1.0pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl71 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: 1.5pt solid windowtext;
}

.xl72 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	border: .5pt solid windowtext;
}

.xl73 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	border-top: .5pt solid windowtext;
	border-right: 1.0pt solid windowtext;
	border-bottom: .5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl74 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: 1.5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl75 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	border-top: .5pt solid windowtext;
	border-right: 1.5pt solid windowtext;
	border-bottom: 1.5pt solid windowtext;
	border-left: .5pt solid windowtext;
}

.xl76 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	text-align: center;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: 1.5pt solid windowtext;
	border-left: 1.5pt solid windowtext;
}

.xl77 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	text-align: left;
	border: .5pt solid windowtext;
}

.xl78 {
	mso-style-parent: style0;
	font-size: 9.0pt;
	text-align: left;
	border-top: .5pt solid windowtext;
	border-right: .5pt solid windowtext;
	border-bottom: 1.5pt solid windowtext;
	border-left: .5pt solid windowtext;
}
</style>

<script language="JavaScript">
	function toPrint() {
		$("#print").print({
			globalStyles : true,
			mediaPrint : false,
			stylesheet : null,
			noPrintSelector : ".no-print",
			iframe : true,
			append : null,
			prepend : null,
			manuallyCopyFormValues : true,
			deferred : $.Deferred()
		});
	}
</script>
</head>

<body link=blue vlink=purple>
	<div align="center">
		<table>
			<tr align="center">
				<td>
					<font style="font-family: Microsoft YaHei; font-size: 14px;" color="red">第一次打印，会提示安装ActiveX控件，请安装控件，否则将导致打印功能无法正常使用</font>
				</td>
			</tr>
		</table>
	</div>
	<div id="print" align="center">
		<table border=0 cellpadding=0 cellspacing=0 width=850
			style='border-collapse: collapse; table-layout: fixed; width: 639pt'
		>
			<col width=70 style='mso-width-source: userset; mso-width-alt: 1920; width: 50pt'>
			<col width=78 style='mso-width-source: userset; mso-width-alt: 2496; width: 59pt'>
			<col width=409 style='mso-width-source: userset; mso-width-alt: 13088; width: 307pt'>
			<col width=101 span=3 style='mso-width-source: userset; mso-width-alt: 3232; width: 76pt'>

			<tr height=19 style='height: 14.25pt'>
				<td height=19 colspan=6 style='height: 14.25pt; mso-ignore: colspan'></td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td colspan=6 height=26 class=xl66 width=850 style='height: 20.1pt; width: 639pt'>投标任务分配表</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td rowspan=2 height=52 class=xl69 style='height: 40.2pt; border-top: none'>阶段</td>
				<td rowspan=2 class=xl65 style='border-top: none'>任务名称</td>
				<td rowspan=2 class=xl65 style='border-top: none'>工作内容描述</td>
				<td rowspan=2 class=xl65 style='border-top: none'>责任人</td>
				<td colspan=2 class=xl65 style='border-right: 1.5pt solid black; border-left: none'>计划</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td height=26 class=xl65 style='height: 20.1pt; border-top: none; border-left: none'>开始</td>
				<td class=xl70 style='border-right: 1.5pt solid black;'>结束</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td rowspan=2 height=52 class=xl71 style='height: 40.2pt; border-top: none'>策划阶段</td>
				<td class=xl77 style='border-top: none; border-left: none'>前期策划</td>
				<td class=xl72 style='border-top: none; border-left: none'>
					信息收集，投标与否做出论证
					<span style='mso-spacerun: yes'>&nbsp;</span>
				</td>
				<td class=xl72 style='border-top: none; border-left: none'>${user1.name}</td>
				<td class=xl72 style='border-top: none; border-left: none'>${pstart1}</td>
				<td class=xl73 style='border-right: 1.5pt solid black;'>${pcontinue1}</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td height=26 class=xl77 style='height: 20.1pt; border-top: none; border-left: none'>后期策划</td>
				<td class=xl72 style='border-top: none; border-left: none'>制定投标策略</td>
				<td class=xl72 style='border-top: none; border-left: none'>${user2.name}</td>
				<td class=xl72 style='border-top: none; border-left: none'>${pstart2}</td>
				<td class=xl73 style='border-right: 1.5pt solid black;'>${pcontinue2}</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td rowspan=3 height=78 class=xl71 style='height: 60.3pt; border-top: none'>实施阶段</td>
				<td class=xl77 style='border-top: none; border-left: none'>投标报名</td>
				<td class=xl72 style='border-top: none; border-left: none'>
					报名资料准备，投标保证金缴纳
					<span style='mso-spacerun: yes'>&nbsp;</span>
				</td>
				<td class=xl72 style='border-top: none; border-left: none'>${user3.name}</td>
				<td class=xl72 style='border-top: none; border-left: none'>${pstart3}</td>
				<td class=xl73 style='border-right: 1.5pt solid black;'>${pcontinue3}</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td height=26 class=xl77 style='height: 20.1pt; border-top: none; border-left: none'>投标准备</td>
				<td class=xl72 style='border-top: none; border-left: none'>
					招标文件提疑，投标文件编制及审核
					<span style='mso-spacerun: yes'>&nbsp;</span>
				</td>
				<td class=xl72 style='border-top: none; border-left: none'>${user4.name}</td>
				<td class=xl72 style='border-top: none; border-left: none'>${pstart4}</td>
				<td class=xl73 style='border-right: 1.5pt solid black;'>${pcontinue4}</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td height=26 class=xl77 style='height: 20.1pt; border-top: none; border-left: none'>投标</td>
				<td class=xl72 style='border-top: none; border-left: none'>递交投标文件或竞争性谈判、磋商文件</td>
				<td class=xl72 style='border-top: none; border-left: none'>${user5.name}</td>
				<td class=xl72 style='border-top: none; border-left: none'>${pstart5}</td>
				<td class=xl73 style='border-right: 1.5pt solid black;'>${pcontinue5}</td>
			</tr>
			<tr height=26 style='mso-height-source: userset; height: 20.1pt'>
				<td height=26 class=xl76 style='height: 20.1pt; border-top: none'>结束阶段</td>
				<td class=xl78 style='border-top: none; border-left: none'>投标总结</td>
				<td class=xl74 style='border-top: none; border-left: none'>投标总结，好的做法及失误情况分析中标项目领取中标通知书，项目移交</td>
				<td class=xl74 style='border-top: none; border-left: none'>${user6.name}</td>
				<td class=xl74 style='border-top: none; border-left: none'>${pstart6}</td>
				<td class=xl75 style='border-right: 1.5pt solid black;'>${pcontinue6}</td>
			</tr>

			<tr height=19 style='height: 14.25pt'>
				<td height=19 colspan=6 style='height: 14.25pt; mso-ignore: colspan'></td>
			</tr>

			<tr height=0 style='display: none'>
				<td width=60 style='width: 45pt'></td>
				<td width=78 style='width: 59pt'></td>
				<td width=409 style='width: 307pt'></td>
				<td width=101 style='width: 76pt'></td>
				<td width=101 style='width: 76pt'></td>
				<td width=101 style='width: 76pt'></td>
			</tr>

		</table>
	</div>

	<div align="center">
		<input type="button" class="sub" name="print" value="打印" onclick="javascript:toPrint()">
	</div>
</body>

</html>
