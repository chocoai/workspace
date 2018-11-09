<#macro head>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <#assign base = request.contextPath />
 <#assign formatDuring = "com.whty.ecspage.common.utils.freemarker.FormatDuring"?new()/>
  <#include "_public_meta.ftl">
  <#include "_public_styles.ftl">
 
  <#nested>
</head>
</#macro>
<#macro body>
<body class="m_body">
  <script>if(!window.addEventListener) {document.body.innerHTML = '您的浏览器版本太低，请升级您的浏览器！'; document.execCommand("stop");}</script>
  <#nested>
  <#include "_iclass_footer.ftl">
</#macro>
<#macro extra>
  <#nested>
</#macro>
<#macro scripts>
   <#include "_public_scripts.ftl">
  <#nested>
</#macro>
</body>
</html>