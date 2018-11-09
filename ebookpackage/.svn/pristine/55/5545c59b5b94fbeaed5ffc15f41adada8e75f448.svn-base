<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息详情页面</title>

<style type="text/css">
table tr td{padding-top: 10px;}
.platform{width:650px;}
.platform input{margin-right: 5px;}
.platform div{margin-left:20px;}
.platform div span{display: inline-block;width:200px;height: 25px;}
.platform p{height:25px;}
</style>
</head>
<body>
<div class="mg15 txq_main">
   	 <div class="mgtb10">
    	<div class="base_title">
			<strong><a class="mgr10" href="javascript:window.location.href='${ctx}/manage/message/list';" name="wddx" style="color: #497cc0;">返回上一级</a>通知详情</strong>
		</div>
        <table border="0" width="100%" style="font-size: 12px;">
         	<tr>
               <td width="10%" align="right">发件人：</td>
               <td width="90%">
               	<span>${message.senderName}</span>
               </td>
             </tr>
             <tr>
               <td align="right">主题：</td>
               <td>
               	<span>${message.theme}</span>
               </td>
             </tr>
             <tr>
               <td align="right" style="vertical-align:top;">产品：</td>
               <td>
               	<div style="width:400px;">${message.receiveObjName}</div>
               </td>
             </tr>
             <tr>
               <td align="right" style="vertical-align:top;">发布时间：</td>
               <td>
               	<div style="width:400px;"><fmt:formatDate value="${message.releaseTime }" pattern="yyyy-MM-dd HH:mm:ss" /></div>
               </td>
             </tr>
             <tr>
               <td align="right" style="vertical-align:top;">通知类型：</td>
               <td>
               	<span>${message.messageTypeName}</span>
               </td>
             </tr>
             <tr>
             	<td colspan="2">
             		<div style="width: 530px;min-height: 300px;word-wrap: break-word;word-break : break-all;border: 1px solid #dadada;">
             			${message.content }
					</div>
             	</td>
             </tr>
		</table>
	</div>
</div>

</body>
</html>