<#assign base=request.contextPath />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>教师空间首页</title>
    <link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/common/css/public.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/common/css/zTreeStyle2/zTreeStyle2.css" type="text/css">
    <link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/common/css/z_netdisk.css" rel="stylesheet" type="text/css"/>
    <link href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/area/edu4.0/css/spaceindex4_new.css" rel="stylesheet" type="text/css"/>
</head>
<body class="b-cfff">



<div class="xy_nd_sc pd15 mgt5" style="height:36px;">
	<form id="pageForm"  name="pageForm" action="${base}/myMnote/list.html" method="post">
    <p class="qjf_search yx_search">
    	<input type="hidden" name="pageNum" id="pageNum" value="${pageNum}">
    	<input type="hidden" name="pageSize" id="pageSize" value="${pageSize}">
    
    	<input type="hidden" name="noteType" id="noteType" <#if noteType?? > value="${noteType}" </#if>>
    	<input type="hidden" name="usesessionId" id="usesessionId" value="${usesessionId}" >
        <input class="qjf_ndinp w195" type="text" name="name" <#if name?? > value="${name}" </#if> placeholder="请输入关键字"/>
        <input type="hidden" name="userId" id="userId" value="${userId}" >
        <input type="hidden" name="userPlatformCode" id="userPlatformCode" value="${userPlatformCode}" >
        <input type="button" value="搜索" class="qjf_searchbtn" onclick="search1()"/>
    </p>
    </from> 
</div>
<div class="xy_nd_nav f14 mgt10">
	<#if noteType?? >
		<a href="javascript:void(0)" <#if !noteType??> class="cur" </#if>   onclick="search2()"   style="margin-left:0;">全部</a><em>|</em> 
		<a href="javascript:void(0)" <#if noteType =="1"> class="cur" </#if>  onclick="search2(1)" >使用中</a><em>|</em>
		<a href="javascript:void(0)" <#if noteType =="2"> class="cur" </#if> onclick="search2(2)" >已归档</a></div> 
	<#else>
		<a href="javascript:void(0)"  class="cur" onclick="search2(0)"  style="margin-left:0;">全部</a><em>|</em> 
		<a href="javascript:void(0)"   onclick="search2(1)" >使用中</a><em>|</em>
		<a href="javascript:void(0)"  onclick="search2(2)" >已归档</a></div> 
	</#if>

	
    
<div class="xy_nd_mbx qjf_topbor mgt10">
    <p class="xy_nd_path fl "><a href="#" class="sbao_s">我的笔记</a></p>
    <span class="fr pdr15">已全部加载，共${pageInfo.total}个</span>
</div>
<div class="wp_notebook_list">
		
		<#if isFind >
	
		<ul class="clearfix">
		<#list noteList as note>
	        <li>
	            <a href="${note.shareMnoteAddBase64}" target="_blank" class="img">
	            	<#if note.coverImageUrl??>  
	            		<img src="${note.coverImageUrl}" alt="">	
	            	<#else>
	            		<img src="http://whty.bj.bcebos.com/mnote/notesconfig/0_0_0_cover.png" alt="">
	            	</#if>
	            </a>
	            
	            <#if note.noteType=='2'>
	            	<div class="save_info">
	                	<p>已归档</p>
	                	<p>${note.startTime?date}-${note.endTime?date}</p>
	            	</div>
	            
	            </#if>

	            <div class="note_info mgt20">
	                <h2 class="mgt10"><a href="#">${note.noteName}</a></h2>
	                <p>更新时间: <span>${note.showUpdateTime?date}</span></p>
	            </div>
	        </li>
		</#list>
		
		</ul>
		
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
				                    <#if navigatepageNum!=pageInfo.pageNum>  
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
		
		
		<#else>
		
		  <div class="tip">
	        <p class="t_c mgtb20">
	            <img width="100%" height="100%" src="${base}/images/index.png" alt="">
	        </p>
	    </div>
		</#if>
  </div>

<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/common/js/jquery.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/area/edu4.0/js/space4.js?v=2"></script>
<script>

	function queryAll(pageNum,pageSize){
		$("#pageNum").val(pageNum);
		$("#pageSize").val(pageSize);
		$("#pageForm").submit();
	}


	var search2 = function(n){
	
		$("#pageNum").val(1);
		$("#pageSize").val(8);
	
		$("#noteType").val(n);
		$("#pageForm").submit();
	}


	var search1 = function(){
		$("#pageForm").submit();
	}


    //面包屑
    var Aobj =$(".xy_nd_path").find("a");
    for(var i=0;i<Aobj.length;i++)
    {
        Aobj.eq(i).css("z-index",Aobj.length-i);
    }
    $(".xy_nd_path a:first-child").addClass("fsty");
</script>
</body>
</html>
