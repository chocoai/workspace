<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="newTag" uri="/WEB-INF/tld/pageTag.tld"%>
<%@ taglib prefix="permission" uri="/WEB-INF/tld/PermissionTag.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/css/tianyu_terminal_base.css" rel="stylesheet" type="text/css">
<link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/css/tianyu_terminal.css" rel="stylesheet" type="text/css">


<link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/css/perfect-scrollbar.css" rel="stylesheet" type="text/css">

<link href="${ctx}/css/zhxy_ytj.css" rel="stylesheet" type="text/css">

<link type="text/css" rel="stylesheet" href="${ctx}/css/wdstyle.css">  
<link type="text/css" rel="stylesheet" href="${ctx}/js/layer/css/layer.css">

<script type="text/javascript" src="${ctx}/js/My97DatePicker/skin/WdatePicker.css"></script>


<link type="text/css" rel="stylesheet" href="${ctx}/js/select2/css/select2.css">  
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>


<!-- <script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/My97DatePicker/WdatePicker.js"></script> -->


<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/fun.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/scrollbar/jquery.mousewheel.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/scrollbar/perfect-scrollbar.js"></script>


<script type="text/javascript" src="${ctx}/js/terminalM/jquery.artDialog.js"></script>
<script type="text/javascript" src="${ctx}/js/terminalM/jquery.artDialog.plugins.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/tianyu_terminal_base.js"></script>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-validation/localization/messages_zh.js"></script>
<script type="text/javascript" src="${ctx}/js/layer/js/layer.js"></script>

<script type="text/javascript" src="${ctx}/js/noty/packaged/jquery.noty.packaged.js"></script>
<script type="text/javascript" src="${ctx}/js/noty/layouts/center.js"></script>


<script type="text/javascript" src="${ctx}/js/select2/js/i18n/zh-CN.js"></script>

<script type="text/javascript" src="${ctx}/js/terminalM/alert.js"></script>

<script type="text/javascript">
function getTopWinow() {
	var p = window;
	while (p != p.parent) {
		p = p.parent;
	}
	return p;
}

Date.prototype.format = function(format) {
    var o = {
        "M+" : this.getMonth() + 1,
        "d+" : this.getDate(),
        "h+" : this.getHours(),
        "m+" : this.getMinutes(),
        "s+" : this.getSeconds(),
        "q+" : Math.floor((this.getMonth() + 3) / 3),
        "S" : this.getMilliseconds()
    };
    if (/(y+)/.test(format)) {
            format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4- RegExp.$1.length));
        }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)){
            format = format.replace(RegExp.$1, RegExp.$1.length == 1? o[k]:("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
    
};

$(document).ready(function() {
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

	$(".data_list tr td").mouseout(function() {
		var bgc = $(this).parent().attr("bg");
		$(this).parent().find("td").css("background-color", bgc);
	});

	var color = "#f6f7f6";
	$(".data_list tr:even td").css("background-color", color);
	$(".data_list tr:even").attr("bg", color);
	$(".data_list tr:odd").attr("bg", "#fff");
});
</script>
