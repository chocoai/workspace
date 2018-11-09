<div id="map"><img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<a href="/project/myProjectList.htm">我的项目</a> >
<#if project!=null><a href="javascript:void(0);">${project.name}</a></#if>
</div>
    
<div id="content">
   <ul class="but">
	<#list psdList as psd>
			<#if ((psd_index+1)/5)?int == 0 && (psd_index+1)%5 == 1>
		      	<div class="classification">准备阶段</div>
		    <#elseif ((psd_index+1)/5)?int  == 1  && (psd_index+1)%5 == 1> 	
		      	<div style="clear:left;"></div>
      			<div class="classification">实施阶段</div>
      		<#elseif ((psd_index+1)/5)?int  == 2  && (psd_index+1)%5 == 1>	
      		    <div style="clear:left;"></div>
     			<div class="classification">终结阶段</div>
			</#if>
			
			<#if (psd.sort)?eval ==5 && isHandle?? && isHandle=="true" >
				<li><a href="<#if psd.stepCode.equals('17')>/requ/edit<#else>/step${psd.stepCode}/edit</#if>.htm?project.id=${project.id}" class="colour_diabled"><#if psd.stepCode.equals('17')><img src="/images/b7.png" /><#else><img src="/images/c${psd.stepCode}.png" /></#if>${psd.stepName}</a></li>
			<#else>
				<#if pps.currentStep.sort gt (psd.sort)?eval>
				 	<li><a href="<#if psd.stepCode.equals('17')>/requ/show<#else>/step${psd.stepCode}/show</#if>.htm?project.id=${project.id}" class="colour_${(psd_index+1)%5}"><#if psd.stepCode.equals('17')><img src="/images/b7.png" /><#else><img src="/images/c${psd.stepCode}.png" /></#if>${psd.stepName}</a></li>  
				<#elseif (pps.currentStep.sort == (psd.sort)?eval) && pps.currentUsers?contains(userid?string)>
					<li><a href="<#if psd.stepCode.equals('17')>/requ/edit<#else>/step${psd.stepCode}/edit</#if>.htm?project.id=${project.id}" class="colour_diabled"><#if psd.stepCode.equals('17')><img src="/images/b7.png" /><#else><img src="/images/c${psd.stepCode}.png" /></#if>${psd.stepName}</a></li>
				<#else>
					<li><a href="<#if psd.stepCode.equals('17')>/requ/show<#else>/step${psd.stepCode}/show</#if>.htm?" class="colour_gray"><#if psd.stepCode.equals('17')><img src="/images/b7.png" /><#else><img src="/images/c${psd.stepCode}.png" /></#if>${psd.stepName}</a></li>
				</#if>
			</#if>
	</#list>
   </ul>
</div>