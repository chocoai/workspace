<#assign state_nav_list = "3">
<#assign state_nav_type = "school">

<#import "../inc/_layout.ftl" as layout>

<@layout.head>
  <title>一体机监控系统</title>
</@layout.head>

<@layout.body>
  <#include "../inc/_m_nav.ftl">
  <#include "../inc/school/_queryTeacherUseLogDetail.ftl">
</@layout.body>

<@layout.extra>
</@layout.extra>

<@layout.scripts>
  <script type="text/javascript" src="../../js/school/queryDeviceUseCount.js"></script>
</@layout.scripts>