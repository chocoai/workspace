<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> ></#if>
<#if project!=null><a href="javascript:void(0);">${project.name}</a></#if>
</div>   
<div id="content">
   <ul class="but">
      <div class="classification">准备阶段</div>
      <#if (project.step >= 1) >
        	<li><a href="/step1/show.htm?project.id=${project.id}" class="colour_one"><img src="/images/c1.png" />咨询任务书</a></li>
      <#else>
  <!--       <li><a   class="colour_diabled"><img src="/images/c1.png" />咨询任务书</a></li> -->	   
         <li><a   href="/step1/show.htm" class="colour_diabled"><img src="/images/c1.png" />咨询任务书</a></li>
	</#if>
<!--      **********************************************************************************************************    -->	
   <#if (project.step >= 2)>
	      <li><a href="/step2/show.htm?project.id=${project.id}" class="colour_two"><img src="/images/b2.png" />(接收)资料登记</a></li>
    <#else>
<!--      <li><a  class="colour_diabled"><img src="/images/b2.png" />(接收)资料登记</a></li> -->
      <li><a   href="/step2/show.htm" class="colour_diabled"><img src="/images/b2.png" />(接收)资料登记</a></li>
	</#if>
<!--      **********************************************************************************************************    -->
     <#if (project.step >= 3)>
       <li><a href="/step3/show.htm?project.id=${project.id}" class="colour_three"><img src="/images/c3.png" />编制咨询方案</a></li>
      <#else>
<!--         <li><a   class="colour_diabled"><img src="/images/c3.png" />编制咨询方案</a></li> -->	
         <li><a   href="/step3/show.htm" class="colour_diabled"><img src="/images/c3.png" />编制咨询方案</a></li>
	</#if>
<!--      **********************************************************************************************************    -->	
     <#if (project.step>=4) >
      	<li><a href="/step4/show.htm?project.id=${project.id}" class="colour_fore"><img src="/images/b4.png" />整理资料清单</a></li>
      <#else>
<!--        <li><a   class="colour_diabled"><img src="/images/b4.png" />整理资料清单</a></li> -->	
       <li><a   href="/step4/show.htm" class="colour_diabled"><img src="/images/b4.png" />整理资料清单</a></li>
	</#if>
<!--      **********************************************************************************************************    -->		
      <#if (project.step>=5) >
         <li><a href="/step5/show.htm?project.id=${project.id}" class="colour_five"><img src="/images/b5.png" />现场勘察</a></li>
      <#else>
<!--      <li><a   class="colour_diabled"><img src="/images/b5.png" />现场勘察</a></li> -->		
      <li><a   href="/step5/show.htm"   class="colour_diabled"><img src="/images/b5.png" />现场勘察</a></li>
	</#if>

      
      <div style="clear:left;"></div>
      <div class="classification">实施阶段</div>

      <#if (project.step >= 6)>
      	  <li><a href="/step6/show.htm?project.id=${project.id}" class="colour_severn"><img src="/images/b2.png" />底稿编制</a></li>
      <#else>
<!--       <li><a   class="colour_diabled"><img src="/images/b2.png" />底稿编制</a></li> -->		
       <li><a     href="/step6/show.htm" class="colour_diabled"><img src="/images/b2.png" />底稿编制</a></li>
	</#if>
<!--      **********************************************************************************************************    -->	
	<#if (project.step >= 7)>
       <li><a href="/step7/show.htm?project.id=${project.id}" class="colour_one"><img src="/images/c7.png" />校对</a></li>
      <#else>
<!--        <li><a   class="colour_diabled"><img src="/images/c7.png" />校对</a></li> -->		
        <li><a   href="/step7/show.htm"   class="colour_diabled"><img src="/images/c7.png" />校对</a></li>
	</#if>
<!--      **********************************************************************************************************    -->		
      <#if (project.step >= 8)>
       <li><a href="/step8/show.htm?project.id=${project.id}" class="colour_two"><img src="/images/c8.png" />审核</a></li>
      <#else>
  <!--       <li><a   class="colour_diabled"><img src="/images/c8.png" />审核</a></li> -->		
        <li><a   href="/step8/show.htm"   class="colour_diabled"><img src="/images/c8.png" />审核</a></li>
	</#if>
<!--      **********************************************************************************************************    -->	     
      <#if (project.step >= 9)>
       <li><a href="/step9/show.htm?project.id=${project.id}" class="colour_three"><img src="/images/c9.png" />审定</a></li>
       <#else>
  <!--      <li><a   class="colour_diabled"><img src="/images/c9.png" />审定</a></li> -->		
       <li><a   href="/step9/show.htm"   class="colour_diabled"><img src="/images/c9.png" />审定</a></li>
	</#if>
<!--      **********************************************************************************************************    -->		
      <#if (project.step >= 10)>
      	<li><a href="/step10/show.htm?project.id=${project.id}" class="colour_six"><img src="/images/b4.png" />征求意见及反馈</a></li>
      <#else>
 <!--      <li><a   class="colour_diabled"><img src="/images/b4.png" />征求意见及反馈</a></li> -->		
      <li><a    href="/step10/show.htm"  class="colour_diabled"><img src="/images/b4.png" />征求意见及反馈</a></li>
	</#if>

    
      <div style="clear:left;"></div>
      <div class="classification">终结阶段</div>
      <#if (project.step >= 11)>
       <li><a href="/step11/show.htm?project.id=${project.id}" class="colour_five"><img src="/images/c10.png" />成果文件编制</a></li>
       <#else>
<!--         <li><a   class="colour_diabled"><img src="/images/c10.png" />成果文件编制</a></li> -->	   
        <li><a   href="/step11/show.htm"   class="colour_diabled"><img src="/images/c10.png" />成果文件编制</a></li>
	</#if>
<!--      **********************************************************************************************************    -->		
      <#if (project.step >= 12)>
      	<li><a href="/step12/show.htm?project.id=${project.id}" class="colour_six"><img src="/images/c11.png" />成果文件签订并签发</a></li>
	<#else>
<!--          <li><a   class="colour_diabled"><img src="/images/c11.png" />成果文件签订并签发</a></li> -->	   
         <li><a   href="/step12/show.htm"   class="colour_diabled"><img src="/images/c11.png" />成果文件签订并签发</a></li>
	</#if>
<!--      **********************************************************************************************************    -->	
      <#if (project.step >= 13)>
      <li><a href="/step13/show.htm?project.id=${project.id}" class="colour_severn"><img src="/images/c12.png" />回访记录</a></li>
	<#else>
 <!--          <li><a  class="colour_diabled"><img src="/images/c12.png" />回访记录</a></li> -->	   
         <li><a   href="/step13/show.htm"  class="colour_diabled"><img src="/images/c12.png" />回访记录</a></li>
	</#if>
<!--      **********************************************************************************************************    -->	
     <#if (project.step >= 14)>
       <li><a href="/step14/show.htm?project.id=${project.id}" class="colour_one"><img src="/images/c13.png" />项目总结</a></li>
	<#else>
  <!--        <li><a   class="colour_diabled"><img src="/images/c13.png" />项目总结</a></li> -->	   
        <li><a   href="/step14/show.htm"   class="colour_diabled"><img src="/images/c13.png" />项目总结</a></li>
	</#if>
 <!--      **********************************************************************************************************    -->	
      <#if (project.step >= 15)>
      <li><a href="/step15/show.htm?project.id=${project.id}" class="colour_two"><img src="/images/c14.png" />资料归档</a></li>
	<#else>
   <!--        <li><a  class="colour_diabled"><img src="/images/c3.png" />资料归档</a></li> -->	   
         <li><a   href="/step15/show.htm"  class="colour_diabled"><img src="/images/c3.png" />资料归档</a></li>
	</#if>
 <!--      **********************************************************************************************************    -->	   

   </ul>
</div>