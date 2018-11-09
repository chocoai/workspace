<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">应用商城 &gt;</span><span class="on mgl5"> 终端应用管控</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <form id="pageForm"  name="pageForm" action="${ctx}/manage/appInfo/list" method="post">
        <input type="hidden" name="searchSubjects" value="${searchSubjects}">
        <input type="hidden" name="searchGrades" value="${searchGrades}">
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
        	
            <div class="clearfix mgt10">
                <div class="wql_g_btn fr mgl20">
                    <div class="wql_btn06">
                        <a href="javascript:;" class="wql_upfile">上传应用</a>
                    </div>
                </div>
                <div class="wql_g_search fr">
                    <div class="wql_search01">
                        <input type="text" class="wql_inp" name="appNames" value="${appNames}" placeholder="应用" style="width:210px;">
                        <span class="wql_searchico"></span>
                    </div>
                </div>
            </div>
            <div class="wql_g_list01 w6em bb2">
                <ul class="wql_ul">
                    <li class="wql_li mgt20">
                        <div class="wql_li_box">
                            <div class="wql_box_l">
                                <em></em>适用年级：
                            </div>
                            <div class="wql_box_r">
                                <div class="wql_g_sel">
                                    <div class=" wql_selbtn01" name="searchGrade">
                                        <a href="javascript:;" class="mgr10 mgb10" name="-1">不限年级</a>
                                        <c:forEach items="${gradeList}" var="grade" varStatus="status">
                                        	<a href="javascript:;" class="mgr10 mgb10" name="${grade.id }"> ${grade.name }</a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                    <li class="wql_li mgt20">
                        <div class="wql_li_box">
                            <div class="wql_box_l">
                                <em></em>适用学科：
                            </div>
                            <div class="wql_box_r">
                                <div class="wql_g_sel">
                                    <div class=" wql_selbtn01" name="searchSubject">
                                        <a href="javascript:;" class="mgr10 mgb10" name="-1">不限学科</a>
                                        <c:forEach items="${subjectList}" var="subject" varStatus="status">
                                        	<a href="javascript:;" class="mgr10 mgb10" name="${subject.id}">${subject.name}</a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="wql_g_list mgt20">
                <div class="wql_list05">
                    <ul class="wql_ul clearfix">
                    	<c:forEach items="${list}" var="obj" varStatus="status">
	                    	<li class="wql_li">
	                            <div class="wql_li_head clearfix">
	                                <div class="fl wql_tit"><h3>${obj.name}</h3></div>
	                                <div class="wql_g_btn fr">
	                                    <div class="wql_btn07">
	                                        <a href="javascript:;" class="wql_edit mgr10" onclick="editAppInfo('${obj.id}')" ></a>
	                                        <a href="javascript:;" class="wql_del mgr10" onclick="deleteAppInfo('${obj.id}')" ></a>

	                                        <c:if test="${obj.bosStatus == 1 }">
	                                    		上传云中
	                                    	</c:if>
	                                    	
	                                    	<c:if test="${obj.bosStatus == 2 }">
	                                    		<a href="${obj.downUrl}" class="wql_down" target="_blank"></a>
	                                    	</c:if>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="wql_li_box mgt25">
	                                <div class="wql_box_l"><img src="${obj.icon }"></div>
	                                <div class="wql_box_r">
	                                    <div class="wql_versionsbox clearfix">
	                                        <div class="wql_versions fl">${obj.version }版本</div>
	                                        <span class="fr wql_link">已推送终端数<em>${obj.pushDeviceNum}</em></span>
	                                    </div>
	                                    <div class="wql_appinfo"><span class="pdr5 mgr5 br">${obj.fileSize }</span><span class="pdr5 mgr5 br">${obj.creatorName}</span><span class=""><fmt:formatDate value="${obj.createTime }" pattern="yyyy-MM-dd" /></span></div>
	                                    <div>${obj.company}  
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="clearfix mgt20">
	                                <div class="wql_g_btn fr">
	                                    <div class="wql_btn07">
	                                        <a href="javascript:;" class="wql_unfold" onclick="detail('${obj.id}')"></a>
	                                    </div>
	                                </div>
	                            </div>
	                        </li>
                    	</c:forEach>
                    </ul>
                </div>
            </div>
            <div class="mgt40">
                <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
							<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
							currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
							action="${ctx}/manage/appInfo/list"></newTag:page>
				</c:if>
            </div>
        </div>
		</form>
        <!-- 主体部分 E-->

    </div>
</div>


<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none" >
    <div class="wql_artDialog03 pdtb30">
        <div class="wql_DialogBox ">
            <div class="wql_box_l pdlr20">
                <div class="wql_appimg t_c">
                    <img src="" name="detailIcon">
                    <p class="wql_tit lh20 mgtb20 f20 c454545" name="detailName"></p>
                </div>
                <div class="wql_appinfo">
                    <div name="detailVersion"></div>
                    <div class=""><span class="pdr5 mgr5 br" name="detailFileSize"></span>
                    <span class="pdr5 mgr5 br" name="detailCreatorName"></span>
                    <span class="" name="detailCreateTime"></span></div>
                    <div name="detailCompany"></div>
                </div>
                <div class="wql_targets">
                    <div class="wql_tit mgt15 f20 lh30">适用年级</div>
                    <div class="mgt10" name="detailGrade">
                       
                    </div>
                </div>
                <div class="wql_targets">
                    <div class="wql_tit mgt15 f20 lh30">适用学科</div>
                    <div class="mgt10" name="detailSubject">
                       
                    </div>
                </div>
                <div class="mgt25 lh20">累计推送终端数<em name="pushDeviceNum"></em></div>
            </div>
            <div class="wql_box_r pdlr20">
                <div class="clearfix">
                    <div class="fl lh20 f18">应用截图</div>
                    <div class="wql_g_btn fr">
                        <div class="wql_btn07">
                            <a href="javascript:;" class="wql_close"></a>
                        </div>
                    </div>
                </div>
                <div class="wql_bannerbox mgtb15">
                    <ul class="wql_bannerimg">
                      
                    </ul>
                    <div class="wql_num clearfix">
                        
                    </div>
                </div>
                
                <div class="lh20 f18">应用介绍</div>
                <div class="mgt10" name="detailDescription">
                
                </div>
                    

            </div>
        </div>
    </div>
</div>


<!-- 上传应用 01-->
<div class="wql_g_artDialog dis_none" >
    <div class="wql_artDialog04 pdtb30">
        <div class="wql_DialogBox ">
            <div class="wql_btn">
                <a href="javascript:;" class="wql_upfileBtn" id="browse">上传应用</a>
            </div>
            <div class="wql_tips t_c">
                <p>（支持apk文件，单个文件最大支持2GB，支持中断后续传）</p>
            </div>
        </div>
    </div>
</div>

<!-- 上传应用 02-->
<div class="wql_g_artDialog dis_none step1" >
    <div class="wql_artDialog04 pdtb30">
        <div class="wql_DialogBox ">
            <div class="wql_btn">
                <a href="javascript:;" class="">确定引用</a>
            </div>
            <div class="wql_tips t_c">
                <p>当前所上传应用已存在，无需上传，点击“确定引用”，可直接引用</p>
               
            </div>
        </div>
    </div>
</div>
<!-- 上传应用 03-->
<div class="wql_g_artDialog dis_none" >
    <div class="wql_artDialog04 pdtb30">
        <div class="wql_DialogBox">
            <div class="wql_bar">
                <p></p>
            </div>
            <div class="wql_tips t_c rate">
                
            </div>
        </div>
        <div class="wql_btnBox clearfix">
          
        </div>
    </div>
</div>
<!-- 上传应用 04-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01">
        <div class="t_c">
            <img name="iconImg"  widget="50px" height="50px">
            <p class="wql_tit lh20 mgtb10 f16 c454545" name="appName"></p>
            <p class="wql_tit lh20 mgtb10 f16 c454545">
            <span class="pdr5 mgr5 br" name="version"></span>
            <span class="pdr5 mgr5" name="fileSize"></span></p>
        </div>
        <div class="wql_g_list01 mgt30 w7em">
            <ul class="wql_ul">
            	
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>上传时间：
                        </div>
                        <div class="wql_box_r">
                            <div class="wql_text" style="width:404px;" name="uploadTime"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>上传者：
                        </div>
                        <div class="wql_box_r">
                            <div class="wql_text" style="width:404px;" name="creatorName"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	内部版本号：
                        </div>
                        <div class="wql_box_r">
                       	 	<div class="wql_text" style="width:404px;" name="versionCode"></div>
                            <div class="wql_tips" name="versionError"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	包名：
                        </div>
                        <div class="wql_box_r">
                        	<div class="wql_text" style="width:404px;" name="packageName"></div>
                            <div class="wql_tips" name="packageNameError"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	应用开发商：
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" style="width:395px;" name="developer">
                            <div class="wql_tips" name="developerError"></div>
                        </div>
                    </div>
                </li>

                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	适用年级：
                        </div>
                        <div class="wql_box_r">
                            <div class="wql_g_sel">
                                <div class="wql_selbtn01" name="grade">
                                    <a href="javascript:;" class="mgr10 mgb10" name="-1">不限年级</a>
                                    <c:forEach items="${gradeList}" var="grade" varStatus="status">
                                    	<a href="javascript:;" name="${grade.id }" class="mgr10 mgb10">${grade.name}</a>
									</c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	适用学科：
                        </div>
                        <div class="wql_box_r">
                            <div class="wql_g_sel">
                                <div class="wql_selbtn01" name="subject">
                                    <a href="javascript:;" class="mgr10 mgb10" name="-1">不限学科</a>
                                    <c:forEach items="${subjectList}" var="subject" varStatus="status">
                                    	<a href="javascript:;" name="${subject.id }" class="mgr10 mgb10">${subject.name}</a>
									</c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	备注：
                        </div>
                        <div class="wql_box_r">
                            <textarea class="wql_textarea" name="description" style="width:395px;"></textarea>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            	应用截图：<p class="lh20 f14">（最多3张）</p>
                        </div>
                        <div class="wql_box_r">
                            <div id="" class="wql_g_upfile">
                                <ul class="wql_ul clearfix" name="screenShots">
                                    <li class="wql_li " name="updateImgLi"><a href="" class="wql_btn" id="updateImg"></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<!-- 弹窗 E-->


<script type="text/javascript" src="${ctx}/js/appInfo.js"></script>


<script type="text/javascript">

function editAppInfo(id){
	var dialog = art.dialog({
	    title:'应用详情',
	    content:$(".wql_g_artDialog")[4],
	    width:'600px',
	    okValue:"确定",
	    padding:'20px',
	    okValue:"确定",
	    cancelValue:"退出",
	    ok:function(){
	    	 var appName = $("p[name='appName']").text();
	    	 var version = $("span[name='version']").text();
	    	 
	    	 var packageName = $("div[name='packageName']").text();
	    	 
	    	 var versionCode = $("div[name='versionCode']").text();
	    	 var developer = $("input[name='developer']").val();
	    	 var description = $("input[name='description']").val();
	    	 var uploadTime = $("div[name='uploadTime']").text();
	    	 var fileSize = $("span[name='fileSize']").text;
	    	 //var uploadFileUrl = app.uploadFileUrl;

	    	 var iconPath = $("img[name='iconImg']").attr("src");
	    	 
	    	 var subjectNode = $("div[name='subject']").find("a");//所有学科
	    	 var gradeNode = $("div[name='grade']").find("a");//所有年级
	    
	    	 var subjectList = [];
	    	 var gradeList = [];
	    	 
	    	 console.log(subjectNode);
	    	 console.log(gradeNode);
	    	 $.each(subjectNode, function(i, ckItm){
	    			var name = $(this).attr("name");
	    			var classValue = $(this).attr("class");
	    			
	    			if(classValue.indexOf("active")>-1){
	    				if(name == '-1'){
	    					subjectList = [];
	    					return false;
	    				}else{
	    					subjectList.push(name);			    					
	    				}
	    				
	    			}
	    	});
	    	 
	    	 $.each(subjectNode, function(i, ckItm){
	    			var name = $(this).attr("name");
	    			var classValue = $(this).attr("class");
	    			
	    			if(classValue.indexOf("active")>-1){
	    				if(name =='-1'){
	    					gradeList =[];
	    					return false;
	    				}else{
		    				gradeList.push(name);
	    				}			    				
	    			}
	    	  });			    	 
	    	 
	    	 $.ajax({
    				type : "POST",
    				url : "${ctx}/manage/appInfo/editAppInfo",
    				data : {
    					"id":id,
    					"appName" : appName,
    					"version": version,
    					"company" : developer,
    					"description" : description,
    					"iconPath" :iconPath,
    					"subjectList" :subjectList,
    					"gradeList" : gradeList,
    					"appImgList":appScreenShotStr
    				},
    				async : false,
    				dataType : 'text',
    				traditional: true,
    				success : function(msg) {
    					if(msg=="success"){
    						$("#pageForm").submit();
    					}else{
    						myAlert('系统消息',msg,function(r){  
    	  	    	    });
    					}
    				}
    		});
	    },
	    cancel:true,
	    initialize: function() {
	    	 $.each(uploader.files, function (i, file) {
	       	        uploader.files.splice(i, 1);
	       	    	});
	        	   
	       	$("li[name='appImg']").remove();
	        	   
	        appScreenShotStr=[];
	        	   
	        $.each(screenShotUploader.files, function (i, file) {
	        		   //console.log(file)
	        	screenShotUploader.removeFile(file);
	        		   //screenShotUploader.files.splice(i, 1);
	         });
	          $.ajax({
	     				type : "POST",
	     				url : "${ctx}/manage/appInfo/detail",
	     				data : {
	     					"id":id
	     				},
	     				async : false,
	     				dataType : 'json',
	     				success : function(data) {
	     					
	     					 	
	     					$("p[name='appName']").text(data.name);//应用名称
	     					$("span[name='version']").text(data.version);//应用名称
	     					$("div[name='versionCode']").text(data.versionCode);//应用名称
	     					$("div[name='packageName']").text(data.packageName);//应用名称
	     					$("input[name='developer']").val(data.company);//提供者
	     					$("textarea[name='description']").val(data.description);//详情
	     					$("img[name='iconImg']").attr("src",data.icon)
	     					$("div[name='creatorName']").text(data.creatorName);//创建者
	     					$("span[name='fileSize']").text(data.fileSize);//文件大小
	     					$("div[name='uploadTime']").text(new Date(data.createTime.time).format("yyyy-MM-dd"));//上传时间
	     					
	     					var grades = data.gradeList;
	     					var subjects = data.subjectList;

	     					 var subjectNode = $("div[name='subject']").find("a");//所有学科
	    			    	 var gradeNode = $("div[name='grade']").find("a");//所有年级
	    			    
	    			    	 $.each(gradeNode, function(i, ckItm){
	    			    		 $(gradeNode[i]).removeClass("active");
	    			    	 });
	    			    	
	    			    	 
	    			    	 $.each(subjectNode, function(i, ckItm){
	    			    		 $(subjectNode[i]).removeClass("active");
	    			    	 });
	    			    	 
	    			    	 $.each(gradeNode, function(i, ckItm){
	    			    			var name = $(this).attr("name");
	    			    			var classValue = $(this).attr("class");
	    			    			
	    			    			$.each(grades,function(index,value){
	    			    			  if(name == value.id){
	    			    				  $(gradeNode[i]).addClass("active");
	    			    			  }	
	  	     						});
	    			    	});
	    			    	 
	    			    	 $.each(subjectNode, function(i, ckItm){
	    			    			var name = $(this).attr("name");
	    			    			var classValue = $(this).attr("class");
	    			    			
	    			    			$.each(subjects,function(index,value){
	    			    				console.log(i+":"+ckItm)
	    			    				if(name == value.id){
	    			    					$(subjectNode[i]).addClass("active");
		    			    			}	
	  	     						});
	    			    	});
	    			    	 
	    			    	 var appImgList = data.appImgList;

	    			    	 
	    			    	 
	    			    	 $.each(appImgList,function(index,value){
	    			    		 console.log(value.url)
	    			    		 var html = "<li class='wql_li' name='appImg'><img src="+value.url+"><i class='wql_delimgbtn' name=''><input type='hidden' name='path' value="+value.url+"></i></li>";
	    			    		 $("li[name='updateImgLi']").before(html);
	    			    	 });
	     				}
	     		});   
	   }
	})
}

function deleteAppInfo(id){
	
	myConfirm('系统确认框','是否删除该应用！',function(r){
		 if(r){  
			 $.ajax({
					type : "post",
					async : true,
					url : "deleteAppInfo",
					data : {
						id : id
					},
					dataType : "text",
					success : function(msg) {
						if(msg=="success"){
								$("#pageForm").submit();
							}else{
								myAlert('系统消息',msg,function(r){  
			  	    	    });
							}
					},
					error : function(errorMsg) {
					}
				})
		 }
	}); 
}

function detail(id){
	   $(".wql_bannerimg").html('<li class="wql_li active"><img src="../../images/timg.jpg"></li>');
	   $(".wql_num").html('<a href="javascript:;" class="active"></a>');
       var dialog = art.dialog({
    	   	id:"detailDialog",
            title:false,
            content:$(".wql_g_artDialog")[0],
            width:'900px',
            okValue:"确定",
            padding:'0',
            cancel: false,
            initialize: function() {
            	$('.wql_close').on('click',function(){
            		top.art.dialog({id:"detailDialog"}).close();
                })
            	$.ajax({
     				type : "POST",
     				url : "${ctx}/manage/appInfo/detail",
     				data : {
     					"id" : id,
     				},
     				async : false,
     				dataType : 'json',
     				traditional: true,
     				success : function(data) {

     					$("em[name='pushDeviceNum']").text(data.pushDeviceNum);
     					$("img[name='detailIcon']").attr('src',data.icon);
     					$("p[name='detailName']").html(data.name);
     					$("div[name='detailVersion']").html(data.version);
     					$("span[name='detailFileSize']").html(data.fileSize);
     					$("span[name='detailCreatorName']").html(data.creatorName);
     					$("span[name='detailCreateTime']").html(new Date(data.createTime.time).format("yyyy-MM-dd"));
     					$("div[name='detailCompany']").html(data.company);
     					$("div[name='detailDescription']").html(data.description);
     					
     					var grades = data.gradeList;
     					var subjects = data.subjectList;
     					var gradespan = '';
     					var subjectspan = '';
     					$.each(grades,function(index,value){
     						  gradespan += '<span>'+value.name+'</span>';
     					});
     					
     					
     					if(gradespan==''){
     						gradespan="全年级";
     					}
     					
     					$("div[name='detailGrade']").html(gradespan);
     					$.each(subjects,function(index,value){
     						  subjectspan += '<span>'+value.name+'</span>';
     					});
     					
     					if(subjectspan==''){
     						subjectspan="全学科";
     					}
     					
     					$("div[name='detailSubject']").html(subjectspan);
     					
     					var appImgList = data.appImgList;
     					var wql_bannerimgLi ='';
     				
     					var wql_a = '';
     					
     					console.log(appImgList)
     					
     					$.each(appImgList,function(index,value){
     						
     						if(index==0){
	     						wql_bannerimgLi += '<li class="wql_li active"><img src="'+value.url+'"></li>';
   							 	wql_a +='<a href="javascript:;" class="active"></a>';				
     						}else{
     							wql_bannerimgLi += '<li class="wql_li"><img src="'+value.url+'"></li>';
     							wql_a +='<a href="javascript:;"></a>';
     						}
   						});
     					
     					
     					if(wql_bannerimgLi !=''){
     						$(".wql_bannerimg").html('');
     						$(".wql_bannerimg").html(wql_bannerimgLi);

     						$(".wql_num").html('');
     						$(".wql_num").html(wql_a);
     					}
     				}
     		});
            }
        })
}

var iconPath = '';


var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
	browse_button : 'browse',
	url : '${ctx}/manage/appInfo/upload',
	flash_swf_url : '${ctx}/js/plupload-2.1.8/Moxie.swf',
	silverlight_xap_url : '${ctx}/js/plupload-2.1.8/Moxie.xap',
	multi_selection : false,
	chunk_size: '10mb', //分块大小
	filters : {
		mime_types: [
			{title : "apk files", extensions : "apk"}
			//{title : "exe files", extensions : "exe"}
		]
	},
	
	init: {
		FilesAdded :  function(uploader, files) {
			if(uploader.files.length == 2){
				uploader.files.splice(uploader.files.length-1, 1);
				return;
			}
			plupload.each(files, function(file) {
				top.art.dialog({id:"changeFile"}).close();
				$(".wql_tips t_c rate").html('');
				var dialog = art.dialog({
					id : 'fileProgress',
				    title:'上传应用',
				    content:$(".wql_g_artDialog")[3],
				    width:'600px',
				    okValue:"确定",
				    padding:'0',
				    initialize: function() {
				                
				    }
				})
				
				$(".wql_bar").find('p').css({"width": "0%"}); 
				$(".rate").html('');
				var html= '<span class="wql_appname">'+file.name+'</span><span class="wql_appsize01" name="companySize">'+plupload.formatSize(file.loaded)+'</span>  / <span class="wql_appsize02" name="fileSize">'+plupload.formatSize(file.size)+'</span>';
				$(".rate").html(html);
				uploader.start(); //开始上传
			});
		},
		UploadProgress: function(uploader, file) {
			var percent = file.percent; 
    		$(".wql_bar").find('p').css({"width": percent + "%"}); 
    		$("span[name='companySize']").html(plupload.formatSize(file.loaded));
    		
		},
		FileUploaded:function(uploader,file,responseObject){
			top.art.dialog({id:"fileProgress"}).close();

			var app = eval('('+responseObject.response+')');
			
			$("span[name='appName']").text('');
			$("span[name='version']").text('');
			$("input[name='developer']").val('');
			$("textarea[name='description']").val('');
			$("div[name='uploadTime']").text(app.updateTime);
			$("div[name='creatorName']").text(app.creator);
			$("span[name='fileSize']").text(plupload.formatSize(file.size));
			
			$("div[name='uploadTime']").text(app.uploadTime);
			
			var dialog = art.dialog({
			    title:'上传应用',
			    content:$(".wql_g_artDialog")[4],
			    width:'600px',
			    okValue:"确定",
			    padding:'20px',
			    okValue:"确定",
			    cancelValue:"退出",
			    ok:function(){
			    	 var appName = $("p[name='appName']").text();
			    	 var version = $("span[name='version']").text();
			    	 var packageName =$("div[name='packageName']").text();
			    	 var versionCode = $("div[name='versionCode']").text();
			    	 var developer = $("input[name='developer']").val();
			    	 var description = $("input[name='description']").val();
			    	 var uploadTime = $("div[name='uploadTime']").text();
			    	 var fileSize = $("span[name='fileSize']").text;
			    	 var uploadFileUrl = app.uploadFileUrl;
			    	 
			    	 var subjectNode = $("div[name='subject']").find("a");//所有学科
			    	 var gradeNode = $("div[name='grade']").find("a");//所有年级
			    
			    	 var subjectList = [];
			    	 var gradeList = [];
			    	 
			    	 $.each(subjectNode, function(i, ckItm){
			    			var name = $(this).attr("name");
			    			var classValue = $(this).attr("class");
			    			
			    			if(classValue.indexOf("active")>-1){
			    				if(name == '-1'){
			    					subjectList = [];
			    					return false;
			    				}else{
			    					subjectList.push(name);			    					
			    				}
			    				
			    			}
			    	});
			    	 
			    	 $.each(subjectNode, function(i, ckItm){
			    			var name = $(this).attr("name");
			    			var classValue = $(this).attr("class");
			    			
			    			if(classValue.indexOf("active")>-1){
			    				if(name =='-1'){
			    					gradeList =[];
			    					return false;
			    				}else{
				    				gradeList.push(name);
			    				}			    				
			    			}
			    	  });			    	 
			    	 
			    	 $.ajax({
		     				type : "POST",
		     				url : "${ctx}/manage/appInfo/save",
		     				data : {
		     					"appName" : appName,
		     					"packageName" : packageName,
		     					"version": version,
		     					"versionCode" : versionCode,
		     					"company" : developer,
		     					"description" : description,
		     					"updateTime" : app.updateTime,
		     					"fileSize" : plupload.formatSize(file.size),
		     					"uploadFileUrl": uploadFileUrl,
		     					"iconPath" :iconPath,
		     					"subjectList" :subjectList,
		     					"gradeList" : gradeList,
		     					"appImgList":appScreenShotStr
		     				},
		     				async : false,
		     				dataType : 'text',
		     				traditional: true,
		     				success : function(msg) {
		     					if(msg=="success"){
		     						$("#pageForm").submit();
		     					}else{
		     						myAlert('系统消息',msg,function(r){  
		     	     	    	    });
		     					}
		     				}
		     		});
			    },
			    cancel:true,
			    initialize: function() {
			          $.ajax({
			     				type : "POST",
			     				url : "${ctx}/manage/appInfo/getIcon",
			     				data : {
			     					"filePath":app.uploadFileUrl
			     				},
			     				async : false,
			     				dataType : 'json',
			     				success : function(data) {
			     					//默认选择所有学科
			     					var searchSubjectNode = $("div[name='subject']").find("a");//所有学科
			     					var searchGradeNode = $("div[name='grade']").find("a");//所有年级
			     					$.each(searchSubjectNode, function(i, ckItm){
			     						 var name = $(this).attr("name");
			     						 var classValue = $(this).attr("class");
			     						 
			     						 if(name =='-1'){
			     							 $(this).addClass("active")
			     						 }
			     					 });
			     					
			     					$.each(searchGradeNode, function(i, ckItm){
			     						 var name = $(this).attr("name");
			     						 var classValue = $(this).attr("class");
			     						 
			     						 if(name =='-1'){
			     							 $(this).addClass("active")
			     						 }
			     					 });
			     					

			     					iconPath = data.iconPath;//图片路径
			     					$("img[name='iconImg']").attr("src", data.iconPath);
			     					$("p[name='appName']").text(data.label);
			     					$("div[name='versionCode']").text(data.versionCode);
			     					$("span[name='version']").text(data.version);
			     					$("div[name='packageName']").text(data.packageName);
			     				}
			     		});   
			   }
			})
			   
		},
		UploadComplete : function(uploader,files){
		},
		FilesRemoved : function(uploader,files){
		},
		Error: function(uploader, err) {
			
			alert(err.message)
		}
	}
});




uploader.init();//上传文件初始化

$(function(){
	
	var searchSubjectNode = $("div[name='searchSubject']").find("a");//所有学科
	var searchGradeNode = $("div[name='searchGrade']").find("a");//所有年级
	
 	var searchSubjects = $("input[name='searchSubjects']").val();
 	var searchGrades = $("input[name='searchGrades']").val();
	
 	var arrSearchSubjects = searchSubjects.split(",");
 	var arrSearchGrades = searchGrades.split(",");
 	
	 $.each(searchSubjectNode, function(i, ckItm){
		 var name = $(this).attr("name");
		 var classValue = $(this).attr("class");
		 
		 for(var i=0;i<arrSearchSubjects.length;i++){
			 if(name == arrSearchSubjects[i]){
				 $(this).addClass("active")
			 }
		}
	 });

	 
	 $.each(searchGradeNode, function(i, ckItm){
		 var name = $(this).attr("name");
		 var classValue = $(this).attr("class");
		 for(var i=0;i<arrSearchGrades.length;i++){
			 if(name == arrSearchGrades[i]){
				 $(this).addClass("active")
			 }
		}
	 });
	 
	 $(".wql_searchico").on("click",function(){
		var searchSubjectNode = $("div[name='searchSubject']").find("a");//所有学科
   	 	var searchGradeNode = $("div[name='searchGrade']").find("a");//所有年级
	  	
   		var searchSubjectList = [];
	 	var searchGradeList = [];
   	 	
	 	 $.each(searchSubjectNode, function(i, ckItm){
	 			var name = $(this).attr("name");
	 			var classValue = $(this).attr("class");
	 			if(classValue.indexOf("active")>-1){
	 				searchSubjectList.push(name);
	 			}
	 	});
	 	 
	 	 $.each(searchGradeNode, function(i, ckItm){
	 			var name = $(this).attr("name");
	 			var classValue = $(this).attr("class");
	 			if(classValue.indexOf("active")>-1){
		    		searchGradeList.push(name);			    				
	 			}
	 	  });
	 	
	 	 
	 	 
	 	$("input[name='searchSubjects']").val(searchSubjectList);
	 	$("input[name='searchGrades']").val(searchGradeList);
		$("#pageForm").submit();
	})
	
    //复选框
    $('.chklist2').hcheckboxnew2(); 

    $('.radiolist2').hradio2()
	
    $(".sys_seleautodiv").sysSeleautoBox();
   
    function oneScreen(){
        var win_h = $(window).height();        
        var header_h = $('.g_syshead').outerHeight();
        var footer_h = $('.g_sysfooter').outerHeight();
        var mainWrap_h = win_h - header_h - footer_h;      
        $('.wql_mainBox').css('minHeight',mainWrap_h-135);
    };
    oneScreen();
    $(window).on('resize',function(){
        oneScreen();
    });

    //模拟多选
    $('.wql_g_sel .wql_selbtn01 a').on("click",function(){
        $(this).hasClass('active')?$(this).removeClass('active'):$(this).addClass('active');
    })

    Banner()

    // banner
    function Banner(){
    
        $('.wql_bannerbox').each(function() {
            //获取控件
            var img = $(this).find('.wql_li')
            var btn = $(this).find('.wql_num a')
            
            var w = btn.outerWidth(true);  //每个按钮的宽度 
            var l = img.length;
          
            $('.wql_num').css('marginLeft',-w*l/2) //底部按钮定位
            var index = 0;
            
            var timer = setInterval(function(){
                index = ++index>(l-1)?0:index;
                showimg(index,img);
            },3000)
			
			function showimg(){
                img.eq(index).show().siblings().hide()
                img.eq(index).addClass('active').siblings().removeClass('active');
                btn.eq(index).addClass('active').siblings().removeClass('active');
            }
            
            $(btn).on('click',function(){
                index = $(this).index();
                showimg()
            })
        });
        
    }


   
    $('.wql_g_btn .wql_btn06 .wql_upfile').on('click',function(){
        var dialog = art.dialog({
           id : 'changeFile',
           title:'上传应用',
           content:$(".wql_g_artDialog")[1],
           width:'600px',
           okValue:"确定",
           padding:'0',
           initialize: function() {
        	   $.each(uploader.files, function (i, file) {
       	        uploader.files.splice(i, 1);
       	    	});
        	   
        	   $("li[name='appImg']").remove();
        	   
        	   appScreenShotStr=[];
        	   
        	   $.each(screenShotUploader.files, function (i, file) {
        		   //console.log(file)
        		   screenShotUploader.removeFile(file);
        		   //screenShotUploader.files.splice(i, 1);
          	   });
           }
        })
    })
  
})



</script>