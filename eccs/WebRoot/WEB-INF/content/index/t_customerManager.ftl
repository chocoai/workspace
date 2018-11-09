<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="#">首页</a> >
<a href="#">客户管理</a>
</div>
    
<div id="content">
   <ul class="but">

   <#if myResMap['006001'] != null>
      <li><a href="/t_customer/list.htm" class="colour_one"><img src="/images/b2.png" />登记管理</a></li>
   </#if>
   
   <#if myResMap['006002'] != null>
       <li><a href="/t_customer/squery.htm" class="colour_five"><img src="/images/b5.png" />统计查询</a></li>
  </#if>
   </ul>
</div>