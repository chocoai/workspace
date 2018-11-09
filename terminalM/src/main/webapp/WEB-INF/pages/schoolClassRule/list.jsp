<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>

<style>a{text-decoration:none}</style>  
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">平板管控设置 &gt;</span><span class="on mgl5">班级规则设置</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox ">
            <div class="wql_Lside wql_bgfff wql_b_radius6">
            <div class="wql_scollbox">
                <div class="wql_sideTit t_c">
                    <h3>班级</h3>
                </div>
                <div class="wql_g_sideNav">
                    <dl class="wql_dl">
                    	<c:if test="${not empty schoolClassTree }">
	                        <c:forEach items="${schoolClassTree}" var="tree" varStatus="status">
	                    		<dt class="on">
	                        		${tree.name}<i class="wql_upico"></i>
	                        	</dt>
	                        	 	 <c:if test="${not empty tree.sub_value }">
	                        	 	 	<c:set value="${fn:split(tree.sub_value, ',')}" var="subValues" />
	                        	 	 	<c:forEach items="${subValues}" var="subValue">
		                        	 	 	<c:set value="${fn:indexOf(subValue, ':')}" var="fgf" />
		                        	 	 	<c:set value="${fn:substring(subValue, 0,fgf)}" var="id" />
		                        	 	 	<c:set value="${fn:length(subValue)}" var="subValueLength" />
		                        	 	 	<c:set value="${fn:substring(subValue, fgf+1,subValueLength)}" var="className" />
		                        	 	 	<dd>
												<a href="javascript:;">${className}</a>
												<span style="display:none" name="schoolClassName">${className}</span>
												<span style="display:none" name="schoolClassId">${id}</span>
					                        </dd>
										</c:forEach>
	                        	 	 </c:if>
	                    	</c:forEach>
                    	</c:if>
                    </dl>
                </div>
            </div>
            </div>
            <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40" >
             <div class="wql_g_list02">
                    <ul class="wql_ul clearfix ">
                    	<c:if test="${not empty schoolList }">
                         <li class="wql_li ">
                            <span class="wql_li_box ">
                                <span>学校：</span>
                                <select name="schoolId" class="sel">
                                	<option value="">请选择学校</option>
                                	<c:forEach items="${schoolList}" var="obj" varStatus="status">
                                	<option value="${obj.id }" <c:if test="${obj.id==schoolId}">selected="selected"</c:if>>${obj.name}</option>
                                	</c:forEach>
                                </select>
                            </span>
                          </li>
                 		</c:if>
                    	
                    		
	                    <li>
	                    	<div class="wql_g_btn mgt4 fl mgl10">
			                        <div class="wql_btn01">
			                            <a href="javascript:;" onclick="checkUrl()" class="wql_btn_search">推送规则</a>
			                        </div>
			                </div>
	                    </li>
                    </ul>
                </div>
            	
                        
				 	 <div class="site_sysTable  mgt15">
			            <div class="wql_g_tit">
			                <div class="wql_tit01 clearfix">
			                    <div class="wql_g_nav fl mgt10">
			                        <div class="wql_nav01">
			                            <ul class="clearfix">
			                                <li class="wql_li on" id="1" style="padding:0 5px"><a href="javascript:void(0)">使用时段设置</a></li>
			                                <li class="wql_li" id="2" style="padding:0 5px"><span class="wql_bl"></span><a href="javascript:void(0)">网址黑名单</a></li>
			                                <li class="wql_li" id="3" style="padding:0 5px"><span class="wql_bl"></span><a href="javascript:void(0)">应用白名单</a></li>
			                                <li class="wql_li" id="4" style="padding:0 5px"><span class="wql_bl"></span><a href="javascript:void(0)">浏览器上网</a></li>
			                                <li class="wql_li" id="5" style="padding:0 5px"><span class="wql_bl"></span><a href="javascript:void(0)">浏览器上网标签</a></li>
			                                <li class="wql_li" id="6" style="padding:0 5px"><span class="wql_bl"></span><a href="javascript:void(0)">应用网络黑名单</a></li>          
			                            </ul>
			                        </div>
			                    </div>
			                    <div class="wql_g_btn mgt8 fr">
			                        <div class="wql_btn01">
			                            <a href="#" class="wql_addBtn">添加规则</a>
			                        </div>
			                    </div>
			                </div>
			            </div>

			            
			            <div class="tab_new1" name="slideCont">
		                	<div class="base_page clearfix" id="base_page_1" onselectstart="return false"></div>
	                	</div>
	                
	                	<div class="tab_new2 dis_none" name="slideCont">
		                	<div class="base_page clearfix" id="base_page_2" onselectstart="return false"></div>
	                	</div>
	                
	                	<div class="tab_new3 dis_none" name="slideCont">
		                	<div class="base_page clearfix" id="base_page_3" onselectstart="return false"></div>
	                	</div>
	                
	                	<div class="tab_new4 dis_none" name="slideCont">
		                	<div class="base_page clearfix" id="base_page_4" onselectstart="return false"></div>
	                	</div>
	                
	                	<div class="tab_new5 dis_none" name="slideCont">
		                	<div class="base_page clearfix" id="base_page_5" onselectstart="return false"></div>
	                	</div>
	                	
	                	<div class="tab_new6 dis_none" name="slideCont">
		                	<div class="base_page clearfix" id="base_page_6" onselectstart="return false"></div>
	                	</div>
            </div>
        </div>
        
        
        
        

        <!-- 主体部分 E-->

    </div>
</div>

</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>规则名称：
                        </div>
                        <div class="wql_box_r">
                        	<span name="name"></span>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>可使用日期：
                        </div>
                        <div class="wql_box_r">
                        	<span name="useDate"></span>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	可使用时点：
                        </div>
                        <div class="wql_box_r">
                        	<span name="useTime"></span>
                        </div>
                    </div>
                </li>

                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	备注：
                        </div>
                        <div class="wql_box_r">
                        	<span name="memo"></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>


<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	规则名称：
                        </div>
                        <div class="wql_box_r">
                        	<span name="name"></span>
                        </div>
                    </div>
                </li>
             
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	备注：
                        </div>
                        <div class="wql_box_r">
                        	<span name="memo"></span>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class=" mgt30">
            <div class="clearfix">
                <div class="fl lh32 f18">黑名单网址列表</div>
            </div>
            <div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>
                            <th>网址</th>
                        </tr>
                    </thead>
                    <tbody id="urlBlackList">
                    
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	规则名称：
                        </div>
                        <div class="wql_box_r">
                        	<span name="name"></span>
                        </div>
                    </div>
                </li>
             
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注：
                        </div>
                        <div class="wql_box_r">
                        	<span name="memo"></span>
                        </div>
                    </div>
                </li>
                
                
                
            </ul>
        </div>
        <div class=" mgt30">
            <div class="clearfix">
                <div class="fl lh32 f18">应用列表</div>
                <div class="wql_g_btn fr">
                    <div class="wql_btn01 clearfix">
                        <!-- <a href="javascript:;" class="fl mgr20 wql_add_URL">添加应用</a> -->
                    </div>
                </div>
            </div>
            <div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>
                            <th>应用</th>
                        </tr>
                    </thead>
                    <tbody id="appList">
                    
                    </tbody>
                </table>
            </div>
            <div class="mgt40">
               
            </div>
        </div>
    </div>
</div>

<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	规则名称：
                        </div>
                        <div class="wql_box_r">
                        	<span name="name"></span>
                        </div>
                    </div>
                </li>
             
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	备注：
                        </div>
                        <div class="wql_box_r">
                        	<span name="memo"></span>
                        </div>
                    </div>
                </li>
                
                
                
            </ul>
        </div>
        <div class=" mgt30">
            <div class="clearfix">
                <div class="fl lh32 f18">黑名单网址列表</div>
                <div class="wql_g_btn fr">
                    <div class="wql_btn01 clearfix">
                        <!-- <a href="javascript:;" class="fl mgr20 wql_add_URL">添加黑名单</a> -->	
                        <!-- <a href="javascript:;" class="fl wql_remove_URL">批量移除黑名单</a> -->
                    </div>
                </div>
            </div>
            <div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>
                            <th>网址</th>
                        </tr>
                    </thead>
                    <tbody id="browserWhitelistUrlBlackList">
                    
                    </tbody>
                </table>
                
            </div>
            <div class="mgt40">
               
            </div>
        </div>
    </div>
</div>


<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        
        <div class="wql_g_list01 w7em">
            <ul class="wql_ul">
               
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	规则名称：
                        </div>
                        <div class="wql_box_r">
                        	<span name="ruleName"></span>
                        </div>
                    </div>
                </li>
             
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注：
                        </div>
                        <div class="wql_box_r">
                        	<span name="memo"></span>
                        </div>
                    </div>
                </li> 
            </ul>
        </div>
        <div class=" mgt30">
            <div class="clearfix">
                <div class="fl lh32 f18">浏览器标签网址列表</div>
                <div class="wql_g_btn fr">
                    <div class="wql_btn01 clearfix">
                        <!-- <a href="javascript:;" class="fl mgr20 wql_add_URL">添加网址</a> -->
                        <!-- <a href="javascript:;" class="fl wql_remove_URL">批量移除黑名单</a> -->
                    </div>
                </div>
            </div>
            <div class="site_sysTable wql_g_table mgt15 chklist2">
                <table class="wql_table02 t_c">
                    <thead>
                        <tr>
                            <th>网址</th>
                            <th>标签</th>
                            <th>Logo</th>
                            <th>分组编号</th>
                            <th>分组名称</th>
                        </tr>
                    </thead>
                    <tbody id="tagList">
                    
                    </tbody>
                </table>
                
            </div>
            <div class="mgt40">
               
            </div>
        </div>
    </div>
</div>

<form id="pageForm"  name="pageForm" action="${ctx}/manage/schoolClassRule/list" method="post">
	<input type="hidden" name="schoolId" value="${schoolId }">
</form>

<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">


<script type="text/javascript">

var schoolId= $("input[name='schoolId']").val();

var schoolClassId='';

var tabId=1;//标签编号

function search(){
	supplierNum = $("input[name='searchSupplierNum']").val('');
	name = $("input[name='searchSupplierName']").val('');
	loadList();
}


function checkUrl(){
	$.ajax({
		type : "post",
		async : true,
		url : "../monitor/checkRule",
		data : {
			schoolId : schoolId
		},
		dataType : "text",
		success : function(msg) {
			if(msg=='success'){
				var n = noty({
				    text        : '指令已发送',
				    type        : 'success',
				    dismissQueue: true,
				    timeout     : 10000,
				    closeWith   : ['click'],
				    layout      : 'center',
				    theme       : 'defaultTheme',
				    maxVisible  : 10
				});
			}else{
				myAlert('系统确认框',msg,function(r){  
	    	          
	    	    });
			}
			
		},
		error : function(errorMsg) {
		}
	})
}

function loadList(){
	
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/schoolClassRule/querySchoolClassRule",
		data : {
			"schoolClassId" : schoolClassId,
			"currPage" : $('#currPage').val(),
			"pageSize" : $('#pageSize').val(),
			"schoolId" : schoolId
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var htmlStr = '';
			
			if(data != null){		
				var browserTagRuleList = data.browserTagRuleList;
				var browserWhitelistRuleList = data.browserWhitelistRuleList;
				var appWhitelistRuleList = data.appWhitelistRuleList;
				var urlBlackListRuleList = data.urlBlackListRuleList;
				var useTimeRuleList = data.useTimeRuleList;
				
				var appNetBlackList = data.appNetBlackList;
				
				console.log(useTimeRuleList);
				
					for(var i = 0;i <5;i++){
						htmlStr += '<tr>';
						if(i==0){
							var ruleHtml='';
							if(useTimeRuleList != null  && useTimeRuleList.length > 0){
								for(var j = 0; j < useTimeRuleList.length;j++){
									var obj = useTimeRuleList[j];
									ruleHtml += '<span>'+obj.name+'<a href="javascript:void(0)" onclick="deleteSchoolClassRule('+schoolClassId+','+obj.id+',1)" class="">移除</a></span>';
								}
							}
							htmlStr += '<td>'+ ruleHtml +'</td>';	
							htmlStr += '<td>使用时段</td>';
							htmlStr += '<td><span><a href="javascript:void(0)" onclick="addSchoolClassRule('+schoolClassId+',1)" class="">添加规则</a></span></td>';
						}
						
						if(i==1){
							var ruleHtml='';
							if(urlBlackListRuleList != null  && urlBlackListRuleList.length > 0){
								for(var j=0;j < urlBlackListRuleList.length;j++){
									var obj = urlBlackListRuleList[j];
									ruleHtml += '<span>'+obj.name+'<a href="javascript:void(0)" onclick="deleteSchoolClassRule('+schoolClassId+',1)" class="">移除</a></span>';
								}
							}
							
							htmlStr += '<td>'+ ruleHtml +'</td>';	
							htmlStr += '<td>网址黑名单</td>';
							htmlStr += '<td><span><a href="javascript:void(0)" onclick="addSchoolClassRule('+schoolClassId+',2)" class="">添加规则</a></span></td>';
						}
						
						if(i==2){
							var ruleHtml='';
							if(appWhitelistRuleList != null  && appWhitelistRuleList.length > 0){
								for(var j = 0; j < appWhitelistRuleList.length;j++){
									var obj = appWhitelistRuleList[j];
									ruleHtml += '<span>'+obj.name+'<a href="javascript:void(0)" onclick="deleteSchoolClassRule('+schoolClassId+',1)" class="">移除</a></span>';
								}
							}
							
							htmlStr += '<td>'+ ruleHtml +'</td>';
							htmlStr += '<td>应用白名单</td>';
							htmlStr += '<td><span><a href="javascript:void(0)" onclick="addSchoolClassRule('+schoolClassId+',3)" class="">添加规则</a></span></td>';
						}

						if(i==3){
							var ruleHtml='';
							if(browserWhitelistRuleList != null  && browserWhitelistRuleList.length > 0){
								for(var j = 0; j < browserWhitelistRuleList.length;j++){
									var obj = browserWhitelistRuleList[j];
									ruleHtml += '<span>'+obj.name+'<a href="javascript:void(0)" onclick="deleteSchoolClassRule('+schoolClassId+',1)" class="">移除</a></span>';
								}
							}
							
							htmlStr += '<td>'+ ruleHtml +'</td>';		
							htmlStr += '<td>浏览器白名单</td>';
							htmlStr += '<td><span><a href="javascript:void(0)" onclick="addSchoolClassRule('+schoolClassId+',4)" class="">添加规则</a></span></td>';
						}
						
						if(i==4){
							var ruleHtml='';
							if(browserTagRuleList != null  && browserTagRuleList.length > 0){
								for(var j = 0; j < browserTagRuleList.length; j++){
									var obj = browserTagRuleList[j];
									ruleHtml += '<span>'+obj.name+'<a href="javascript:void(0)" onclick="deleteSchoolClassRule('+schoolClassId+',1)" class="">移除</a></span>';
								}
							}
							
							htmlStr += '<td>'+ ruleHtml +'</td>';		
							htmlStr += '<td>浏览器标签</td>';
							htmlStr += '<td><span><a href="javascript:void(0)" onclick="addSchoolClassRule('+schoolClassId+',5)" class="">添加规则</a></span></td>';							
						}
						
						if(i==5){
							var ruleHtml='';
							if(appNetBlackList != null  && appNetBlackList.length > 0){
								for(var j = 0; j < browserTagRuleList.length; j++){
									var obj = browserTagRuleList[j];
									ruleHtml += '<span>'+obj.name+'<a href="javascript:void(0)" onclick="deleteSchoolClassRule('+schoolClassId+',1)" class="">移除</a></span>';
								}
							}
							
							htmlStr += '<td>'+ ruleHtml +'</td>';		
							htmlStr += '<td>浏览器标签</td>';
							htmlStr += '<td><span><a href="javascript:void(0)" onclick="addSchoolClassRule('+schoolClassId+',6)" class="">添加规则</a></span></td>';
							
							
						}
						
						htmlStr += '</tr>';					
					}
				}

			if(htmlStr != ''){
				$("#supplierinfoList").html(htmlStr);
			}else{
				$("#supplierinfoList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}

//添加规则
function addSchoolClassRule(schoolClassId,ruleType){
	window.location.href="addSchoolClassRulePage?ruleType="+ruleType+"&schoolClassId="+schoolClassId;
}

function deleteSchoolClassRule(schoolClassId,ruleId,ruleType){
	$.ajax({
			type : "post",
			async : true,
			url : "deleteRule",
			data : {
				schoolClassId : schoolClassId,
				ruleType:ruleType,
				ruleId :ruleId
			},
			dataType : "text",
			success : function(data) {
				loadList();
			},
			error : function(errorMsg) {
			}
		})
}





function tabList(){
	$('.wql_nav01 li').each(function(){
		var classValue = $(this).attr("class");
		if(classValue.indexOf("on")>-1){
			tabId = $(this).attr("id");
		}
	})
	
	$('div[name="slideCont"]').each(function(){
		$(this).addClass('dis_none')
	});
	
	$('.tab_new'+tabId+'').removeClass("dis_none");
	
	
	$.ajax({
		type : "post",
		async : true,
		url : "queryRule",
		data : {
			"ruleId" : tabId,
			"schoolClassId":schoolClassId,
			"currPage" : $('#currPage').val(),
			"pageSize" : $('#pageSize').val()
		},
		dataType : "json",
		success : function(data) {
		
			if(tabId==1){
				var htmlStr = '';
				if(data != null){
					if(data.list != null && data.list.length > 0){
						dataArray = data.list;
						for(var i=0;i<data.list.length;i++){
							var obj = data.list[i];
							htmlStr += '<div class="wql_g_box mgt20">';
							htmlStr += '<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w5em">';
							htmlStr += 	'<div class="clearfix">';
							htmlStr += 		'<div class="wql_box_cont fl">';
							htmlStr += 			'<div class="wql_box_l c888 t_r">规则名称</div>';
							htmlStr += 			'<div class="wql_box_r">'+obj.name+'</div>';
							htmlStr += 		'</div>';
							htmlStr += 		'<div class="wql_box_cont fl">';
							htmlStr += 			'<div class="wql_box_l c888 t_r">可使用日期</div>';
							htmlStr += 			'<div class="wql_box_r">';
							if(obj.typeName!=''){
								htmlStr += obj.typeName;
							}else{
								htmlStr += '无日期';
							}
							htmlStr += 			'</div>';
							htmlStr += 		'</div>';
							htmlStr += 		'<div class="wql_box_cont fl">';
							htmlStr += 			'<div class="wql_box_l c888 t_r">可使用时点</div>';
							htmlStr += 			'<div class="wql_box_r">'+obj.startTime+'--'+obj.endTime+'</div>';
							htmlStr += 		'</div>';
							htmlStr += 		'<div class="wql_g_btn fr">';
							htmlStr += 			'<div class="wql_btn07">'
							htmlStr += 				'<a href="javascript:;" class="wql_edit mgr20" onclick="detailRule('+obj.id+',1)"></a>'
							htmlStr +=					'<a href="javascript:;" class="wql_del" onclick="deleteRule('+obj.id+',1)"></a>'
							htmlStr += 			'</div>'
							htmlStr += 		'</div>'
							htmlStr += 	'</div>'
							htmlStr += 	'<div class="clearfix mgt20">'
							htmlStr += 		'<div class="wql_box_cont fl">'
							htmlStr += 			'<div class="wql_box_l c888 t_r">备注</div>'
							htmlStr += 			'<div class="wql_box_r">'
							
							if(obj.memo != ''){
								htmlStr += obj.memo;
							}else{
								htmlStr += '无备注';
							}
				            htmlStr +=				'</div>';
				            htmlStr +=			'</div>';
				            htmlStr +=	'</div>';
				            htmlStr +=	'</div>';
				            htmlStr += '</div>';
						}
					}
					
					if(data.page != null){
						setPage($("#base_page_1"),data.page);
					}
				}
				if(htmlStr != ''){
					$(".tab_new1").html(htmlStr);
				}else{
					$(".tab_new1").html('');
					$("#base_page_1").html('');
				}
				
				
			}
			
			if(tabId==2){
				var htmlStr = '';
				if(data != null){
					if(data.list != null && data.list.length > 0){
						dataArray = data.list;
						for(var i=0;i<data.list.length;i++){
							var obj = data.list[i];
							htmlStr += '<div class="wql_g_box mgt20">'
							htmlStr +=	'<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w6em">'
							htmlStr +=		'<div class="clearfix">'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">规则名称</div>'
							htmlStr +=				'<div class="wql_box_r">'+obj.name+'</div>'
							htmlStr +=			'</div>'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=              '<div class="wql_box_l c888 t_r">黑名单网址数</div>'
							htmlStr +=         		' <div class="wql_box_r">'+obj.urlTotal+'个</div>'
							htmlStr +=          	'</div>'
							htmlStr +=			'<div class="wql_g_btn fr">'
							htmlStr += 			'<div class="wql_btn07">'
							htmlStr +=			'<a href="javascript:;" class="wql_edit mgr20" onclick="detailRule('+obj.id+',2)"></a>'
							htmlStr +=			'<a href="javascript:;" class="wql_del" onclick="deleteRule('+obj.id+',2)"></a>'
							htmlStr +=			'</div>'
							htmlStr +=			'</div>'
							htmlStr +=		'</div>'
							htmlStr +=		'<div class="clearfix mgt20">'
							htmlStr +=		'<div class="wql_box_cont fl">'
							htmlStr +=			'<div class="wql_box_l c888 t_r">备注</div>'
							htmlStr +=				'<div class="wql_box_r">'
							
							if(obj.memo != ''){
								htmlStr += obj.memo;
							}else{
								htmlStr += '无备注';
							}
							
				            htmlStr +=				'</div>';
				            htmlStr +=			'</div>';
				            htmlStr +=	'</div>';
				            htmlStr +=	'</div>';
				            htmlStr += '</div>';
						}
					}
					
					if(data.page != null){
						setPage($("#base_page_1"),data.page);
					}
				}
				if(htmlStr != ''){
					$(".tab_new2").html(htmlStr);
				}else{
					$(".tab_new2").html('');
					$("#base_page_2").html('');
				}
			}
			
			if(tabId==3){
				var htmlStr = '';
				if(data != null){
					if(data.list != null && data.list.length > 0){
						dataArray = data.list;
						for(var i=0;i<data.list.length;i++){
							var obj = data.list[i];
							htmlStr +=  '<div class="wql_g_box mgt20">'
							htmlStr +=  '<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w6em">'
							htmlStr += 	'<div class="clearfix">'
							htmlStr += 		'<div class="wql_box_cont fl">'
							htmlStr += 			'<div class="wql_box_l c888 t_r">规则名称</div>'
							htmlStr += 			'<div class="wql_box_r">'+obj.name+'</div>'
							htmlStr += 		'</div>'
							htmlStr += 		'<div class="wql_box_cont fl">'
							htmlStr +=            ' <div class="wql_box_l c888 t_r">应用数量</div>'
							htmlStr +=             '<div class="wql_box_r">'+obj.totalNum+'个</div>'
							htmlStr +=         '</div>'
			                htmlStr +=		'<div class="wql_g_btn fr">'
			                htmlStr +=			'<div class="wql_btn07">'
			                htmlStr +=				'<a href="javascript:;" class="wql_edit mgr20" onclick="detailRule('+obj.id+',3)"></a>'
			                htmlStr +=				'<a href="javascript:;" class="wql_del" onclick="deleteRule('+obj.id+',3)"></a>'
			                htmlStr +=			'</div>'
			                htmlStr +=		'</div>'
			                htmlStr +=	'</div>'
			                htmlStr +=	'<div class="clearfix mgt20">'
			                htmlStr +=		'<div class="wql_box_cont fl">'
			                htmlStr +=			'<div class="wql_box_l c888 t_r">备注</div>'
			                htmlStr +=			'<div class="wql_box_r">'
			                
			                if(obj.memo != ''){
			                	htmlStr += obj.memo;
			                }else{
			                	htmlStr += '无备注';
			                }
			                
			                
				            htmlStr +=				'</div>';
				            htmlStr +=			'</div>';
				            htmlStr +=	'</div>';
				            htmlStr +=	'</div>';
				            htmlStr += '</div>';
						}
					}
					
					if(data.page != null){
						setPage($("#base_page_1"),data.page);
					}
				}
				if(htmlStr != ''){
					$(".tab_new3").html(htmlStr);
				}else{
					$(".tab_new3").html('');
					$("#base_page_3").html('');
				}
			}
			
			if(tabId==4){
				var htmlStr = '';
				if(data != null){
					if(data.list != null && data.list.length > 0){
						dataArray = data.list;
						for(var i=0;i<data.list.length;i++){
							var obj = data.list[i];
							htmlStr += '<div class="wql_g_box mgt20">'
							htmlStr += '<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w6em">'
							htmlStr +=	'<div class="clearfix">'
							htmlStr +=		'<div class="wql_box_cont fl">'
							htmlStr +=			'<div class="wql_box_l c888 t_r">规则名称</div>'
							htmlStr +=			'<div class="wql_box_r">'+obj.name+'</div>'
							htmlStr +=		'</div>'
							htmlStr +=		'<div class="wql_box_cont fl">'
							htmlStr +=			'<div class="wql_box_l c888 t_r">黑名单网址数</div>'
							htmlStr +=			'<div class="wql_box_r">'+obj.urlTotal+'个</div>'
							htmlStr +=		'</div>'
							htmlStr +=		'<div class="wql_g_btn fr">'
							htmlStr +=			'<div class="wql_btn07">'
							htmlStr +=				'<a href="javascript:;" class="wql_edit mgr20" onclick="detailRule('+obj.id+',4)"></a>'
							htmlStr +=				'<a href="javascript:;" class="wql_del" onclick="deleteRule('+obj.id+',4)"></a>'
							htmlStr +=			'</div>'
							htmlStr +=		'</div>'
							htmlStr +=	'</div>'
							htmlStr +=	'<div class="clearfix mgt20">'
							htmlStr +=		'<div class="wql_box_cont fl">'
							htmlStr +=			'<div class="wql_box_l c888 t_r">备注</div>'
							htmlStr +=			'<div class="wql_box_r">'
			            				
							
			            	if(obj.memo != ''){
			            		htmlStr += obj.memo
			            	}else{
			            		htmlStr += "无备注"
			            	}

							 htmlStr +=			'</div>';
					         htmlStr +=		'</div>';
					         htmlStr +=	'</div>';
					         htmlStr +=	'</div>';
					         htmlStr += '</div>';
						}
					}
					
					if(data.page != null){
						setPage($("#base_page_4"),data.page);
					}
				}
				if(htmlStr != ''){
					$(".tab_new4").html(htmlStr);
				}else{
					$(".tab_new4").html('');
					$("#base_page_4").html('');
				}
			}
			
			if(tabId==5){
				var htmlStr = '';
				if(data != null){
					if(data.list != null && data.list.length > 0){
						dataArray = data.list;
						for(var i=0;i<data.list.length;i++){
							var obj = data.list[i];
							htmlStr += '<div class="wql_g_box mgt20">'
							htmlStr +=	'<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w6em">'
							htmlStr +=		'<div class="clearfix">'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">规则名称</div>'
							htmlStr +=				'<div class="wql_box_r">'+obj.name+'</div>'
							htmlStr +=			'</div>'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">标签网址数</div>'
							htmlStr +=				'<div class="wql_box_r">'+obj.urlTotal+'个</div>'
							htmlStr +=			'</div>'
							htmlStr +=			'<div class="wql_g_btn fr">'
							htmlStr +=				'<div class="wql_btn07">'
							htmlStr +=					'<a href="javascript:;" class="wql_edit mgr20" onclick="detailRule('+obj.id+',5)"></a>'
							htmlStr +=					'<a href="javascript:;" class="wql_del" onclick="deleteRule('+obj.id+',5)"></a>'
							htmlStr +=				'</div>'
							htmlStr +=			'</div>'
							htmlStr +=		'</div>'
							htmlStr +=		'<div class="clearfix mgt20">'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">备注</div>'
							htmlStr +=				'<div class="wql_box_r">'
							
							if(obj.memo != ''){
								htmlStr += obj.memo;
							}else{
								htmlStr += '无备注'
							}
							htmlStr +=			'</div>';
					        htmlStr +=		'</div>';
					        htmlStr +=	'</div>';
					        htmlStr +=	'</div>';
					        htmlStr += '</div>';
						}
					}
					
					if(data.page != null){
						setPage($("#base_page_5"),data.page);
					}
				}
				if(htmlStr != ''){
					$(".tab_new5").html(htmlStr);
				}else{
					$(".tab_new5").html('');
					$("#base_page_5").html('');
				}
			}
			
			
			
			if(tabId==6){
				var htmlStr = '';
				if(data != null){
					if(data.list != null && data.list.length > 0){
						dataArray = data.list;
						for(var i=0;i<data.list.length;i++){
							var obj = data.list[i];
							
							console.log(obj)
							
							htmlStr += '<div class="wql_g_box mgt20">';	
							htmlStr +=	'<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w5em">';
							htmlStr +=		'<div class="clearfix">';
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">应用名称</div>'
							htmlStr += 				'<div class="wql_box_r">'+obj.name+'</div>'
							htmlStr +=			'</div>'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">包名</div>'
							htmlStr +=				'<div class="wql_box_r">'
							htmlStr +=					obj.pkg 
							htmlStr +=				'</div>'
							htmlStr +=			'</div>'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">版本</div>'
							htmlStr +=				'<div class="wql_box_r">'+obj.version+'/'+obj.versionCode+'</div>'
							htmlStr +=			'</div>'
							htmlStr +=			'<div class="wql_g_btn fr">'
							htmlStr +=			'	<div class="wql_btn07">'
							htmlStr +=			'<a href="javascript:;" class="wql_del" onclick="deleteRule('+obj.id+',6)"></a>'
							htmlStr +=				'</div>'
							htmlStr +=			'</div>'
							htmlStr +=		'</div>'
							htmlStr +=		'<div class="clearfix mgt20">'
							htmlStr +=			'<div class="wql_box_cont fl">'
							htmlStr +=				'<div class="wql_box_l c888 t_r">创建时间</div>'
							htmlStr +=				'<div class="wql_box_r">'
							var s = new Date(obj.createTime.time).format("yyyy-MM-dd")
							htmlStr +=				s	
							htmlStr +=				'</div>'
							htmlStr +=			'</div>'
							htmlStr +=		'</div>'
							htmlStr +=	'</div>'
							htmlStr +=	'</div>'
						}
					}
					
					if(data.page != null){
						setPage($("#base_page_1"),data.page);
					}
				}
				if(htmlStr != ''){
					$(".tab_new6").html(htmlStr);
				}else{
					$(".tab_new6").html('');
					$("#base_page_6").html('');
				}
			}
			
			
		},
		error : function(errorMsg) {
		}
	})
	
}

//规则详情
function detailRule(ruleId,ruleType){
	if(ruleType==1){
		$("span[name='name']").text('');
	 	$("span[name='memo']").text('');
	 	
	 	$("span[name='useTime']").text('');
	 	$("span[name='useDate']").text('');
	 	
		$.ajax({
	 		type : "POST",
	 		url : "${ctx}/manage/useTimeRule/detailUseTimeRule",
	 		traditional:true,
	 		data :{
	 			"id" :ruleId
	 		},
	 		async : false,
	 		dataType : 'json',
	 		success : function(data) {
	 			$("span[name='name']").text(data.name);//分类名称
	         	$("span[name='memo']").text(data.memo);//备注
	         	
	 			var dateType = data.dateType;
	         	var timeType = data.timeType;
	         	
	         	if(dateType == 1){
	         		$("span[name='useDate']").text("无限制");//备注
	         	}else if(dateType == 2){
	         		$("span[name='useDate']").text("国家法定节假日和双休日");//备注
	         	}else if(dateType == 3){
	         		$("span[name='useDate']").text("正常工作日");//备注
	         	}else if(dateType == 4){
	         		$("span[name='useDate']").text(new Date(data.startDate).format("yyyy-MM-dd") +"--"+new Date(data.endDate).format("yyyy-MM-dd")  );//备注
	         	}
	       		
	         	if(timeType == 1){
	         		$("span[name='useTime']").text("无限制");
	         	}else if(timeType == 2){
	         		$("span[name='useTime']").text(data.startTime+"--"+data.endTime);
	         	}else if(timeType == 3){
	         		$("span[name='useTime']").text(data.startTime+"--"+data.endTime);
	         	}else if(timeType == 4){
	         		$("span[name='useTime']").text(data.startTime+"--"+data.endTime);
	         	}
	 		}
	 	});
		
		 var dialog = art.dialog({
	         title:'规则详情',
	         content:$(".wql_g_artDialog")[0],
	         width:'500px',
	         padding:'20px',
	         okValue:"确定",
	         traditional:true,
	         cancelValue:"退出",
	         cancel:true
	     })
	}else if(ruleType==2){
		$("input[name='name']").val('');//分类名称
	 	$("textarea[name='memo']").val('');//备注
	 	$("#urlBlackList").children("tr").remove();
	 	
		$.ajax({
	 		type : "POST",
	 		url : "${ctx}/manage/urlBlackListRule/detailUrlBlackList",
	 		traditional:true,
	 		data :{
	 			"id" : ruleId
	 		},
	 		async : false,
	 		dataType : 'json',
	 		success : function(data) {
	 			$("span[name='name']").text(data.name);//分类名称
	         	$("span[name='memo']").text(data.memo);//备注

	         	var urlList = data.urlList;
	       
	         	for(var i=0;i<urlList.length;i++){
	         		 var tr = '<tr class="wl_addTr">'+
	                 '   <td><span name="url">'+urlList[i].url+'</span></td>'+
	                '</tr>'
	                
	        		$('#urlBlackList').append(tr);
	         	}
	 		}
	 	});
		
		 var dialog = art.dialog({
	         title:'规则详情',
	         content:$(".wql_g_artDialog")[1],
	         width:'500px',
	         padding:'20px',
	         okValue:"确定",
	         traditional:true,
	         cancelValue:"退出",
	         cancel:true
	     })
	}else if(ruleType==3){
		$("span[name='name']").text('');//分类名称
	 	$("span[name='memo']").text('');//备注
	 	$("#appList").children("tr").remove();
	 	
		$.ajax({
	 		type : "POST",
	 		url : "${ctx}/manage/appWhitelistRule/detailAppWhitelistRule",
	 		traditional:true,
	 		data :{
	 			"id" :ruleId
	 		},
	 		async : false,
	 		dataType : 'json',
	 		success : function(data) {	 			
	 			
	 			$("span[name='name']").text(data.name);//分类名称
	         	$("span[name='memo']").text(data.memo);//备注
			 	
	         	var appWhiteList = data.appWhitelist;
	       	
	         	for(var i=0;i<appWhiteList.length;i++){
	         		 var tr = '<tr class="wl_addTr">'+
	                 '   <td><span name="'+appWhiteList[i].appInfoId+'">'+appWhiteList[i].appInfoName+'</span><input type="hidden" name="tdAppId" value="'+appWhiteList[i].appInfoId+'"></td>'+
	                 '</tr>'
	                
	        		$('#appList').append(tr);
	         	}
	 		}
	 	});
		
		 var dialog = art.dialog({
	         title:'规则详情',
	         content:$(".wql_g_artDialog")[2],
	         width:'500px',
	         okValue:"确定",
	         padding:'20px',
	         traditional:true,
	         cancelValue:"退出",
	         cancel:true
	         
	     })
	}else if(ruleType==4){
		$("input[name='name']").val('');//分类名称
	 	$("textarea[name='memo']").val('');//备注
	 	$("#browserWhitelistUrlBlackList").children("tr").remove();
	 	
		$.ajax({
	 		type : "POST",
	 		url : "${ctx}/manage/browserWhitelistRule/detailBrowserWhitelistRule",
	 		traditional:true,
	 		data :{
	 			"id" :ruleId
	 		},
	 		async : false,
	 		dataType : 'json',
	 		success : function(data) {
	 			console.log(data)
	 			
	 			$("span[name='name']").text(data.name);//分类名称
	         	$("span[name='memo']").text(data.memo);//备注
	         	
	         	var urlList = data.browserWhitelist;
	       
	         	for(var i=0;i<urlList.length;i++){
	         		 var tr = '<tr class="wl_addTr">'+
	                 '   <td><span name="url">'+urlList[i].url+'</span></td>'+
	                '</tr>'
	        		$('#browserWhitelistUrlBlackList').append(tr);
	         	}
	 		}
	 	});
		
		 var dialog = art.dialog({
	         title:'规则详情',
	         content:$(".wql_g_artDialog")[3],
	         width:'500px',
	         okValue:"确定",
	         padding:'20px',
	         traditional:true,
	         cancelValue:"退出",
	         cancel:true
	     })
	}else if(ruleType==5){
		$("span[name='name']").text('');//分类名称
	 	$("span[name='memo']").text('');//备注
	 	$("#tagList").children("tr").remove();
	 	
		$.ajax({
	 		type : "POST",
	 		url : "${ctx}/manage/browserTagRule/detailBrowserTagRule",
	 		traditional:true,
	 		data :{
	 			"id" :ruleId
	 		},
	 		async : false,
	 		dataType : 'json',
	 		success : function(data) {
	 			$("span[name='ruleName']").text(data.name);//分类名称
	         	$("span[name='memo']").text(data.memo);//备注
	         	
	 			
	         	var tagList = data.browserTagList;
	       
	         	for(var i=0;i<tagList.length;i++){
	         		 var tr = '<tr class="wl_addTr">'+
	                 '   <td><span name="url">'+tagList[i].url+'</span></td>'+
	                 '   <td><span name="name">'+tagList[i].name+'</span></td>'+
	                 '   <td><span name="logo">'+tagList[i].logo+'</span></td>'+
	                 '   <td><span name="groupNum">'+tagList[i].groupNum+'</span></td>'+
	                 '   <td><span name="groupName">'+tagList[i].groupName+'</span></td>'+
	                '</tr>'
	                
	        		$('#tagList').append(tr);
	         	}
	 		}
	 	});
		
		 var dialog = art.dialog({
	         title:'规则详情',
	         content:$(".wql_g_artDialog")[4],
	         width:'500px',
	         okValue:"确定",
	         padding:'20px',
	         traditional:true,
	         cancelValue:"退出",
	         cancel:true
	     })
	}
}

function deleteRule(ruleId,ruleType){
	myConfirm('系统确认框','是否删除！',function(r){  
        if(r){  
        	$.ajax({
        		type : "post",
        		async : true,
        		url : "deleteRule",
        		data : {
        			schoolClassId : schoolClassId,
        			ruleType:ruleType,
        			ruleId :ruleId
        		},
        		dataType : "text",
        		success : function(data) {
        			tabList();
        		},
        		error : function(errorMsg) {
        		}
        	})
        }
     })
}

//登录浮层
$(function(){
	
	$("select[name=schoolId]").bind("change",function(){
		var schoolId = $(this).val();
		
		$("input[name='schoolId']").val(schoolId);
		
		$("#pageForm").submit();
	});
	
	$('.wql_scollbox').perfectScrollbar();
	
	$(".wql_nav01 li").on('click',function(){
		$(this).siblings('li').removeClass('on')
		$(this).addClass('on')
		tabList();
	});
	
	
	 $('.wql_addBtn').on('click',function(){
		 
		 if(schoolClassId==''){
			 myAlert('系统确认框','请选择班级！',function(r){  
   	          
	    	 });
			 return ;
		 }
		 
		 window.location.href="addSchoolClassRulePage?ruleType="+tabId+"&schoolClassId="+schoolClassId;
	 })
	
    var content_h = $('.wql_g_content').outerHeight();
    function oneScreen(){
        var win_h = $(window).height();
        
        var header_h = $('.g_syshead').outerHeight();
        var footer_h = $('.g_sysfooter').outerHeight();
        var mainWrap_h = win_h - header_h - footer_h;
        //console.log(win_h+' '+header_h+' '+footer_h+' '+content_h)
        if(content_h<mainWrap_h){
            $('.wql_g_content').height(mainWrap_h-30);
        }
        else{
            $('.wql_g_content').height(content_h-30);
        }
        $('.wql_mainBox').css('minHeight',mainWrap_h-135);
    };
    oneScreen();
    $(window).on('resize',function(){
        oneScreen();
    });

    $(".wql_g_sideNav dd").on('click',function(){
        $(this).addClass('active').siblings('dd').removeClass('active');

        $(this).siblings('dt').removeClass('on')

        schoolClassId = $(this).children("span[name=schoolClassId]").text();
        
        tabList();
       	
        if($(this).prevUntil('dt').length>0){
             $(this).prevUntil('dt').last().prev().addClass('on')
        }
        else{
             $(this).prev().addClass('on')
        }
        
    })

    $(".wql_btn_search").on("click",function(){
        supplierNum = $("input[name='searchSupplierNum']").val();
   		name =  $("input[name='searchSupplierName']").val();
   		loadList();
    })
    
    
    $(".wql_g_sideNav dt").on("click",function(){
        var that = $(this);
        var ico = that.find('i');
        
        terminalDeviceTypeId='';
        terminalName='';
        
        if(ico.hasClass('wql_upico')){
            ico.removeClass('wql_upico').addClass('wql_downico');
            that.nextUntil('dt').hide();
        }
        else{
            ico.removeClass('wql_downico').addClass('wql_upico');
            that.nextUntil('dt').show();
        }
       
       
    })

    $('.wql_addClass02').on('click',function(){

    	if(terminalDeviceTypeId==null ||terminalDeviceTypeId==''){
				myAlert('系统确认框','请选择终端设备！',function(r){  
	    	          
	    	    });
				return ;
		}
    	
    	$("input[name='name']").val('');
		$("input[name='contact']").val('');
		$("textarea[name='memo']").val('');//分类名称
		$("input[name='contactTel']").val('');
		$("input[name='address']").val('');
    	
		
		$("div[name='nameError']").html('');
		$("div[name='contactError']").html('');
		$("div[name='contactTelError']").html('');
		$("div[name='addressError']").html('');
		
        var dialog = art.dialog({
            title:'添加供应商',
            content:$(".wql_g_artDialog")[0],
            width:'560px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
            	
    			$("div[name='nameError']").html('');
     			$("div[name='contactError']").html('');
     			$("div[name='contactTelError']").html('');
     			$("div[name='addressError']").html('');
            	
            	var name = $("input[name='name']").val();
     			var contact = $("input[name='contact']").val();
     			var contactTel = $("input[name='contactTel']").val();
     			var memo = $("textarea[name='memo']").val();//分类名称
     			var address = $("input[name='address']").val();
     			
     			if(terminalDeviceTypeId==null ||terminalDeviceTypeId==''){
     				myAlert('系统确认框','请选择终端设备类型！',function(r){  
     	    	          
     	    	    });
     				return ;
     			}
     			
				var flag=true;
     			
     			if(name == ''){
     				$("div[name='nameError']").html('供应商名称必填');
     				flag=false;
     			}
     			
     			if(contact == ''){
     				$("div[name='contactError']").html("供应商联系人必填");
     				flag=false;
     			}
     			
     			if(contactTel == '' ){
     				$("div[name='contactTelError']").html("供应商联系方式必填");
     				flag=false;
     			}

     			if(address == ''){
     				$("div[name='addressError']").html("供应商地址必填");
     				flag=false;
     			}
     			
     			if(!flag)
     				return false;
     			
     			setTimeout(function(){
     			
     				$.ajax({
         				type : "post",
         				async : true,
         				url : "saveSupplierInfo",
         				data : {
         					name : name,
         					contact:contact,
         					memo:memo,
         					contactTel:contactTel,
         					address:address,
         					terminalDeviceTypeId :terminalDeviceTypeId
         				},
         				dataType : "text",
         				success : function(msg) {
         				
         					if(msg=="success"){
         						loadList();
         					}else{
         						myAlert('系统消息',msg,function(r){  
         	     	    	          
         	     	    	    });
         					}
         					
         				},
         				error : function(errorMsg) {
         				}
         			})
		
				}, 1);
     		}
        });
    });

})



</script>