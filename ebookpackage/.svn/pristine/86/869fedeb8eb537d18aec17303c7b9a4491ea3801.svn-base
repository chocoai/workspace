<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>right</title>
<script type="text/javascript">
$(function(){
	var now = new Date();
	$("#now").text(now.toLocaleString());
	setInterval('curentTime()',1000);
});
function curentTime(){
	var now = new Date();
	$("#now").text(now.toLocaleString());
}
</script>
</head>
<body>
<div class="mg15">
	<div class="wd_welcome clearfix">
    	<div class="fl mgr20 mgtb10"><img src="${ctx}/images/wd_smile.gif" width="28" height="25"></div>
        <div class="fl">
        	<p class="f16">欢迎你　${sessionScope.sysUser.account}　，现在是<span id="now"></span></p>
            <p><span class="c8">上次登录时间：</span><fmt:formatDate value="${sessionScope.sysUser.last_time}" pattern="yyyy年MM月dd日 HH:mm:ss"/></p>
        </div>
    </div>
    
	<h2 class="wd_title mgt20">系统信息</h2>
    <div class="pdtb15 wd_pdb10 clearfix">
        <div class="fl mgr20"><img src="${ctx}/images/bk-ico-1.gif" width="99" height="99" /></div>
        <div class="fl">
            <p><span class="c8">当前浏览器版本：</span>
            	<script>
            	var ClientParams={};
                var Sys = {};
                var ua = navigator.userAgent.toLowerCase();
                var s;
                (s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] :
                (s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] :
                (s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] :
                (s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] :
                (s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0;
                if (Sys.ie) {
                    ClientParams.Browser = "IE " + Sys.ie;
                }
                else if (Sys.firefox) {
                    ClientParams.Browser = " Firefox " + Sys.firefox;
                }
                else if (Sys.chrome) {
                    ClientParams.Browser = " Chrome " + Sys.chrome;
                }
                else if (Sys.opera) {
                    ClientParams.Browser = " Opera " + Sys.opera;
                }
                else if (Sys.safari) {
                    ClientParams.Browser = "Safari " + Sys.safari;
                }
                else {
                    ClientParams.Browser = "无法检测出您正在使用的浏览器版本!";
                }
                document.write(ClientParams.Browser);
            	</script> 
            </p>
            <p><span class="c8">操作系统：</span>
            	<script>
            	var os = navigator.platform;
        		var userAgent = navigator.userAgent;
        		var info  = "";
        		if(os.indexOf("Win") > -1){
        			if(userAgent.indexOf("Windows NT 5.0") > -1){
        				info += "Win2000";
        			}else if(userAgent.indexOf("Windows NT 5.1") > -1){
        				info += "WinXP";
        			}else if(userAgent.indexOf("Windows NT 5.2") > -1){
        				info += "Win2003";
        			}else if(userAgent.indexOf("Windows NT 6.0") > -1){
        				info += "WindowsVista";
        			}else if(userAgent.indexOf("Windows NT 6.1") > -1 || userAgent.indexOf("Windows 7") > -1){
        				info += "Win7";
        			}else if(userAgent.indexOf("Windows 8") > -1){
        				info += "Win8";
        			}else{
        				info += "Other";
        			}
        		}else if(os.indexOf("Mac") > -1){
        			info += "Mac";
        		}else if(os.indexOf("X11") > -1){
        			info += "Unix";
        		}else if(os.indexOf("Linux") > -1){
        			info += "Linux";
        		}else{
        			info += "Other";
        		}
        		document.write(info);
            	</script>
            </p>
            <p><span class="c8">用户名：</span>${sessionScope.sysUser.account} </p>
            <p><span class="c8">IP地址信息：</span><span id="keleyivisitorip"></span> 
			<script type="text/javascript" src="http://tool.keleyi.com/ip/visitoriphost/"></script> 
            </p>
            <p><span class="c8">技术提供方：</span>武汉天喻信息产业股份有限公司</p>
        </div>
    </div>
    
</div>
</body>
</html>
