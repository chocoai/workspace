<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="newTag" uri="/WEB-INF/tld/pageTag.tld"%>
<%@ taglib prefix="permission" uri="/WEB-INF/tld/PermissionTag.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<c:set var="css" value="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev" scope="request" />
<link href="${ctx}/css/wdstyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/global.js"></script>
<script type="text/javascript" src="${ctx}/js/role.js"></script>

<%@ include file="/WEB-INF/pages/common/alert.jsp"%>
<%@ include file="/WEB-INF/pages/common/changePwd.jsp"%>

<script type="text/javascript">
function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}
$(document).ready(function(){ 
	$.ajaxSetup({
		contentType : "application/x-www-form-urlencoded;charset=utf-8",
		cache: false,
		complete : function(XMLHttpRequest, textStatus) {
			var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
			if (sessionstatus == "timeout") {
				var top = getTopWinow();
				top.location.href = '${ctx}/login.html';
			}
		}
	});
	
	$(".data_list tr td").mouseout(function(){
		var bgc = $(this).parent().attr("bg");
		$(this).parent().find("td").css("background-color",bgc);
	});
	
	var color="#f6f7f6";
	$(".data_list tr:even td").css("background-color",color);  //改变偶数行背景色
	$(".data_list tr:even").attr("bg",color);
	$(".data_list tr:odd").attr("bg","#fff");
});
</script>
