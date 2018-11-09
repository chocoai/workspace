
<#if pageInfo??>
			<#if (pageInfo.pages > 1) >
				 <div class="turnPage t_c mgt10">  
				            <span>
				                <#if !pageInfo.isFirstPage >
				                    <a class="btn" class="on" href="javascript:queryAll(${pageInfo.firstPage}, ${pageInfo.pageSize});">第一页</a>
				                    <a class="btn" class="on" href="javascript:queryAll(${pageInfo.prePage}, ${pageInfo.pageSize});">上一页</a>
				                </#if>
				                <#list pageInfo.navigatepageNums as navigatepageNum>
				                    <#if navigatepageNum == pageInfo.pageNum>
				                       <a class="btn" class="on" href="javascript:queryAll(${navigatepageNum}, ${pageInfo.pageSize});">${navigatepageNum}</a>  
				                    </#if>
				                    <#if navigatepageNum != pageInfo.pageNum>  
				                       <a class="btn" class="on" href="javascript:queryAll(${navigatepageNum}, ${pageInfo.pageSize});">${navigatepageNum}</a>  
				                    </#if>
				                </#list>
				                <#if !pageInfo.isLastPage>  
				                    <a class="btn" class="on" href="javascript:queryAll(${pageInfo.nextPage}, ${pageInfo.pageSize});">下一页</a>
				                    <a class="btn" class="on" href="javascript:queryAll(${pageInfo.lastPage}, ${pageInfo.pageSize});">最后页</a>  
				                </#if>
				            </span>
				           	当前显示第 <i class="blue">${pageInfo.pageNum}/${pageInfo.pages}</i> 页  
				   </div>
			  </#if>
</#if>
		
<script>
	function queryAll(pageNum,pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#pageForm").submit();
	}
</script>