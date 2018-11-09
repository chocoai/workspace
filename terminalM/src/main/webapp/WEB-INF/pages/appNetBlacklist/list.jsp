<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/js/plupload-2.1.8/plupload.full.min.js"></script>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">设备管理 &gt;</span><span class="on mgl5">规则管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <div class="wql_g_nav fl mgt10">
                        <div class="wql_nav01">
                            <ul class="clearfix">
                                 <li class="wql_li "><a href="${ctx}/manage/useTimeRule/list">使用时段设置</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/urlBlackListRule/list">网址黑名单</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/appWhitelistRule/list">应用白名单</a></li>
                                <li class="wql_li "><span class="wql_bl"></span><a href="${ctx}/manage/browserWhitelistRule/list">浏览器上网设置</a></li>
                                <li class="wql_li"><span class="wql_bl"></span><a href="${ctx}/manage/browserTagRule/list">浏览器上网标签设置</a></li>
                                <li class="wql_li on"><span class="wql_bl"></span><a href="${ctx}/manage/appNetBlackList/list">应用网络黑名</a></li>        

                            </ul>
                        </div>
                    </div>
                    <div class="wql_g_btn mgt8 fr">
			            <div class="wql_btn01">
			                 <a href="javascript:;" class="wql_upfile">上传应用</a>
			            </div>
			        </div>
                </div>
                 
            </div>

            
            <c:forEach items="${list}" var="obj" varStatus="status">
            <div class="wql_g_box mgt20">
            	<div class="wql_box01 f14 lh24 pdlr20 pdtb15 wql_bg_eef5fd w5em">
            		<div class="clearfix">
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">应用名称</div>
            				<div class="wql_box_r">${obj.name}</div>
            			</div>
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">包名</div>
            				<div class="wql_box_r">
            					${obj.pkg }
            				</div>
            			</div>
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">版本</div>
            				<div class="wql_box_r">${obj.version}/${obj.versionCode}</div>
            			</div>
            			<div class="wql_g_btn fr">
            				<div class="wql_btn07">
            					<a href="javascript:;" class="wql_del" onclick="deleteRule('${obj.id}')"></a>
            				</div>
            			</div>
            		</div>
            		<div class="clearfix mgt20">
            			<div class="wql_box_cont fl">
            				<div class="wql_box_l c888 t_r">创建时间</div>
            				<div class="wql_box_r">
            					<fmt:formatDate value="${obj.createTime}" pattern="yyyy-MM-dd" />
            				</div>
            			</div>
            		</div>
            	</div>
            </div>
            </c:forEach>
             <form id="pageForm"  name="pageForm" action="${ctx}/manage/appNetBlackList/list" method="post">
	            <div class="mgt40">
	                 <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
								<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
								currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
								action="${ctx}/manage/appNetBlackList/list"></newTag:page>
					 </c:if>
	            </div>
            </form>
        </div>

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
                <div class="mgt25 lh20">累计推送终端数<em>3450</em></div>
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
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript" src="${ctx}/js/appInfo.js"></script>
<script type="text/javascript">

function deleteRule(id){
	myConfirm('系统确认框','是否删除！',function(r){
		 if(r){  
			 $.ajax({
					type : "POST",
					url : "${ctx}/manage/appNetBlackList/deleteAppNetBlackList",
					data : {
						"id":id
					},
					async : false,
					dataType : 'text',
					success : function(data) {
						$("#pageForm").submit();
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
			    	 
			    	 $.ajax({
		     				type : "POST",
		     				url : "${ctx}/manage/appNetBlackList/saveAppNetBlackList",
		     				data : {
		     					"name":appName,
		     					"version":version,
		     					"pkg":packageName,
		     					"versionCode":versionCode,
		     					"icon":iconPath
		     				},
		     				async : false,
		     				dataType : 'text',
		     				success : function(data) {
		     					$("#pageForm").submit();
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
									console.log(data);			     					

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

   $('.radiolist2').each(function(){
   		var that = $(this)

   		that.find('label').on('click',function(){
   			that.find('label.hRadio_Checked').text()==='自定义'?that.next('.wql_user_defined').show():that.next('.wql_user_defined').hide();
   			$(this).find('input').click(function(e){
   				
   				var type = $(this).attr("type");
   				if(type='redio'){
   					var name = $(this).attr('name');
   					var dateType = $("input[name='"+name+"']").removeAttr("checked");
   					$(this).attr('checked','true');
   				}
	      		e.stopPropagation()
	      	});
   		})
   })


})

$('.wql_upfile').on('click',function(){
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
        	   
        	  
           }
        })
 })

</script>
