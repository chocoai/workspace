<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/js/page.js"></script>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev">系统管理 &gt;</span><span class="on mgl5">校园地点</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox " >
            <div class="wql_Lside wql_bgfff wql_b_radius6 ">
                <div class="wql_scollbox">
                <div class="wql_sideTit clearfix">
                    <h3 class="fl mgl35">校园服务功能区归类</h3>
                    <div class="wql_g_btn fr mgr10">
                        <div class="wql_btn03">
                            <a href="javascript:;" class="wql_addBtn"></a>
                            <a href="javascript:;" class="wql_delBtn"></a>
                        </div>
                    </div>
                </div>
                
                <div class="wql_g_sideNav">
                    <dl class="wql_dl">
                    	<c:forEach items="${list}" var="obj" varStatus="status">
                    		<dt class="on">
                        		${obj.name}<i class="wql_upico"></i>
                        		<span style="display:none" name="schoolLocationId">${obj.id}</span>
                        		<span style="display:none" name="schoolLocationName">${obj.name}</span>
                        		<span style="display:none" name="schoolLocationCode">${obj.code}</span>
                        	</dt>

                        	 	 <c:if test="${not empty obj.layers }">
                        	 	 	<c:set value="${fn:split(obj.layers, ',')}" var="layers" />
                        	 	 	<c:forEach items="${layers}" var="layer">
                        	 	 	<dd>
										<a href="javascript:;">${layer}层</a>
										<span style="display:none" name="schoolLocationId">${obj.id}</span>
										<span style="display:none" name="schoolLocationName">${obj.name}</span>
                        				<span style="display:none" name="schoolLocationCode">${obj.code}</span>
										<span style="display:none" name="layer">${layer}</span>
			                        </dd>
									</c:forEach>
                        	 	 </c:if>
                    	</c:forEach>
                    </dl>
                </div>
                </div>
            </div>
            <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40" style="min-height: 440px;">
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">查询条件</h3>
                    </div>
        
                </div>
                <div class="wql_g_list02">
                    <ul class="wql_ul clearfix">
                        <li class="wql_li">
                            <div class="wql_li_box">
                                <span>编号：</span>
                                <input type="text" class="site_sysinp" name="number" style="width:220px;">
                            </div>
                        </li>
                        
                        <c:if test="${not empty schoolList }">
                         <li class="wql_li mgl10">
                            <div class="wql_li_box mgl10">
                                <span>学校：</span>
                                <select name="schoolId" class="sel">
                                	<option value="">请选择学校</option>
                                	<c:forEach items="${schoolList}" var="obj" varStatus="status">
                                	<option value="${obj.id }" <c:if test="${obj.id==schoolId}">selected="selected"</c:if>>${obj.name}</option>
                                	</c:forEach>
                                </select>
                            </div>
                        </li>
                        </c:if>
                        <li class="wql_li">
                            <div class="wql_li_box">
                                <a href="javascript:;" class="wql_btn_search">查询</a>
                            </div>
                        </li> 
                    </ul>
                </div>
                <div class="wql_g_tit ">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">功能区构成信息列表</h3>
                        <div class="wql_g_btn  fr">
                            <div class="wql_btn01 clearfix">
                                <!-- <a href="#" class="wql_addClass01 fl mgr20">批量导入构成</a> -->
                                <a href="#" class="wql_addClass02 fr">添加构成</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table01 t_l">
                        <thead>
                            <tr>
                                <th class="pdl15">编号</th>
                                <th>名称</th>
                                <th>面积</th>
                                <th>功能属性</th>
                                <th>创建人</th>
                                <th>创建日期</th>
                                <th width="125">操作</th>
                            </tr>
                        </thead>
                        <tbody id="schoolLocationAreaList"></tbody>
                    </table>
                   
                    <div class="mgt40">
                    
                </div>
            </div>
        </div>
        

        <!-- 主体部分 E-->

    </div>
</div>

<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none ">
	
    <div class="wql_artDialog01 wql_scollbox wql_Lside">
        <div class="wql_g_list01 w8em wql_scollbox wql_Lside">
            <ul class="wql_ul">
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>服务功能区名称
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " name="locationName" type="text" style="width:300px;">
                            <div class="wql_tips" name="locationNameError"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>地下层数
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" name="ugLayer" type="text" style="width:300px;"  onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();">
                            <div class="wql_tips" name="ugLayerError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>地上层数
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" name="ogLayer" type="text" style="width:300px;"  onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();">
                        	<div class="wql_tips" name="ogLayerError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注
                        </div>
                        <div class="wql_box_r"><textarea name="memo" class="wql_textarea" style="width:300px;" placeholder="限50字"></textarea></div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    
</div>




<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w8em">
            <ul class="wql_ul">
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>服务功能区名称
                        </div>
                        <div class="wql_box_r">
                           <div class="wql_text"style="width:307px;" name="areaSchoolLocationName"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>服务功能区编码
                        </div>
                        <div class="wql_box_r">
                            <div class="wql_text"style="width:307px;" name="areaSchoolLocationCode"></div>
                        </div>
                    </div>
                </li>
               	<li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>楼层
                        </div>
                        <div class="wql_box_r">
                            <div class="wql_text"style="width:307px;" name="areaLayer"></div>
                        </div>
                    </div>
                </li>
               
               <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>名称
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " name="areaName" type="text" style="width:300px;">
                            <div class="wql_tips" name="areaNameError"></div>
                        </div>
                    </div>
                </li>
               
                <li class="wql_li mgt20">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>功能属性
                        </div>
                        <div class="wql_box_r">
                            <span class="radiolist2 clearfix">
                            	<c:forEach items="${listLocationAreaAttribute}" var="locationAreaAttribute" varStatus="status">
                            		<label class="sys_hRadio hRadio mgr10">
                            		<input type="radio" name="locationAreaAttribute" value="${locationAreaAttribute.key}" style="display: none;">
                            		${locationAreaAttribute.value}</label>
                            	</c:forEach>
                            </span>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>面积
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" style="width:280px;" name="area" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9-]+/,'');}).call(this)" onblur="this.v();"> ㎡
                            <div class="wql_tips" name="areaError"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注
                        </div>
                        <div class="wql_box_r"><textarea class="wql_textarea" name="description" style="width:300px;" placeholder="限50字"></textarea></div>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
</div>


<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w8em">
            <ul class="wql_ul">
            
                <li class="wql_li">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>名称
                        </div>
                        <div class="wql_box_r">
                           <input class="wql_inp " name="editAreaSchoolLocationName" type="text" style="width:300px;">
                           <div class="wql_tips" name="editAreaSchoolLocationNameError"></div>
                        </div>
                    </div>
                </li>
                
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>面积
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " name="editAreaSchoolLocation" type="text" style="width:300px;">
                            <div class="wql_tips" name="editAreaSchoolLocationError"></div>
                        </div>
                    </div>
                </li>
              
                <li class="wql_li mgt30">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>备注
                        </div>
                        <div class="wql_box_r">
                        	<textarea class="wql_textarea" name="editDescription" style="width:300px;" placeholder="限50字"></textarea>
                        </div>
                    </div>
                </li>
                
            </ul>
        </div>
    </div>
</div>
</div>
<form id="pageForm"  name="pageForm" action="${ctx}/manage/schoolLocation/list" method="post">
	<input type="hidden" name="schoolId" value="${schoolId }">
</form>

<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">



<script type="text/javascript" src="${ctx}/js/MyPageJs/page.js"></script>
<script type="text/javascript">

var schoolId= $("input[name='schoolId']").val();
var currentPage ;//当前页
var totalPage ;//总页数
//登录浮层
var schoolLocationId;
var layer;

var schoolLocationName;
var schoolLocationCode;

var number='';

function loadList(pageValue){
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/schoolLocation/querySchoolLocationArea",
		data : {
			"schoolLocationId" : schoolLocationId,
			"layer":layer,
			"number":number,
			"pageValue":pageValue,
			"schoolId":schoolId
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			totalPage = data.pages;
       	 	currentPage = data.pageNum;
			var htmlStr = '';
			
			if(data != null){
				if(data.list != null && data.list.length > 0){
					dataArray = data.list;
					for(var i=0;i<data.list.length;i++){
						var obj = data.list[i];
						htmlStr += '<tr>';
						htmlStr += '<td><span class="pdl15">'+obj.number+'</span></td>';
						htmlStr += '<td>'+obj.name+'</td>';
						htmlStr += '<td>'+obj.area+'㎡</td>';
						
					    $(".sys_hRadio").each(function(){  
					    	var key = $(this).children("input[name=locationAreaAttribute]").val();
					    	var value = $(this).text();
					    	if(obj.attributeType==key){
					    		htmlStr += '<td>'+value+'</td>';
					    	}
					    });  
						htmlStr += '<td>'+obj.creatorName+'</td>';
						htmlStr += '<td>'+new Date(obj.createTime.time).format("yyyy-MM-dd hh:mm:ss")+'</td>';
						htmlStr += '<td><span><a href="javascript:void(0)" onclick="deleteSchoolLocationArea('+obj.id+')" class="">删除</a></span>  <span><a href="javascript:void(0)" onclick="editSchoolLocationArea('+obj.id+')" class="">编辑</a></span></td>';
						htmlStr += '</tr>';
					}
				}
			}
			myPage();
			
			if(htmlStr != ''){
				$("#schoolLocationAreaList").html(htmlStr);
			}else{
				$("#schoolLocationAreaList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}

function editSchoolLocationArea(id){
	
	var dialog = art.dialog({
        title:'编辑',
        content:$(".wql_g_artDialog")[2],
        width:'560px',
        okValue:"确定",      
        cancelValue:"取消",
        cancel:true,
        initialize: function() {
        	$.ajax({
 				type : "post",
 				async : true,
 				url : "detailSchoolLocationArea",
 				data : {
 					id : id,
 				},
 				dataType : "json",
 				success : function(data) {
 					$("input[name='editAreaSchoolLocationName']").val(data.name);//分类名称
 					$("input[name='editAreaSchoolLocation']").val(data.area);//分类名称
 		         	$("textarea[name='editDescription']").val(data.description);//备注
 				},
 				error : function(errorMsg) {
 				}
 			})
        },
        ok:function() {
        	var name = $("input[name='editAreaSchoolLocationName']").val();//分类名称
			var area = $("input[name='editAreaSchoolLocation']").val();//分类名称
        	var description = $("textarea[name='editDescription']").val();
        	
			
        	var flag=true;
 			
 			if(name == '' ){
 				$("div[name='editAreaSchoolLocationNameError']").html('名称必填');
 				flag=false;
 			}
 			
 			if(area == '' ){
 				$("div[name='editAreaSchoolLocationError']").html("面积必填");
 				flag=false;
 			}
 				
 			if(!flag)
 				return false;
			
 			$.ajax({
 				type : "post",
 				async : true,
 				url : "editSchoolLocationArea",
 				data : {
 					id : id,
 					name:name,
 					area:area,
 					description:description
 				},
 				dataType : "text",
 				success : function(msg) {
 					$("#pageForm").submit();
 				},
 				error : function(errorMsg) {
 				}
 			})
 			
 		}
    });
}

function deleteSchoolLocationArea(id){
	myConfirm('系统确认框','是否删除！',function(r){  
        if(r){  
        	$.ajax({
    			type : "POST",
    			url : "${ctx}/manage/schoolLocation/deleteSchoolLocationArea",
    			data : {
    				"id"  : id
    			},
    			async : false,
    			dataType : 'text',
    			success : function(msg) {
    				if(msg=='success'){
    					loadList(1);
    				}
    			}
    		});
        }  
    });    
}


$(function(){
	
	$("select[name=schoolId]").bind("change",function(){
		var schoolId = $(this).val();
		
		$("input[name='schoolId']").val(schoolId);
		
		$("#pageForm").submit();
	});
	
	$('.wql_scollbox').perfectScrollbar();
	
	$(".wql_btn_search").on("click",function(){
	   	number =  $("input[name='number']").val();
	   	loadList(1);
	})
	
	//loadList();
    $('.radiolist2').hradio2()
    $(".sys_seleautodiv").sysSeleautoBox();
    var content_h = $('.wql_g_content').outerHeight();
    function oneScreen(){
        var win_h = $(window).height();
        
        var header_h = $('.g_syshead').outerHeight();
        var footer_h = $('.g_sysfooter').outerHeight();
        var mainWrap_h = win_h - header_h - footer_h;
        console.log(win_h+' '+header_h+' '+footer_h+' '+content_h)
        if(content_h<mainWrap_h){
            $('.wql_g_content').height(mainWrap_h-30);
        }
        else{
            $('.wql_g_content').height(content_h-30);
        }
    };
    oneScreen();
    $(window).on('resize',function(){
        oneScreen();
    });

    $(".wql_g_sideNav dd").on('click',function(){
        $(this).addClass('active').siblings('dd').removeClass('active');
        $(this).siblings('dt').removeClass('on');
        
        schoolLocationId = $(this).children("span[name=schoolLocationId]").text();
   	 	layer = $(this).children("span[name=layer]").text();
   	 
   	 	schoolLocationName = $(this).children("span[name=schoolLocationName]").text();
   		schoolLocationCode = $(this).children("span[name=schoolLocationCode]").text();
   	 	
   	 	
   	 	//console.log(schoolLocationId+":"+layer)
   	 	loadList(1);
        
        if($(this).prevUntil('dt').length>0){
             $(this).prevUntil('dt').last().prev().addClass('on');
        }
        else{
             $(this).prev().addClass('on');
        }
    })

    $(".wql_g_sideNav dt").on("click",function(){
    	
    	schoolLocationName = $(this).children("span[name=schoolLocationName]").text();
   		schoolLocationCode = $(this).children("span[name=schoolLocationCode]").text();
    	schoolLocationId = $(this).children("span[name=schoolLocationId]").text();
    	layer='';//未选择楼层
    	//console.log(schoolLocationId)
    	
    	loadList(1);
    	
        var that = $(this);
        var ico = that.find('i');
        if(ico.hasClass('wql_upico')){
            ico.removeClass('wql_upico').addClass('wql_downico');
            that.nextUntil('dt').hide();
        }
        else{
            ico.removeClass('wql_downico').addClass('wql_upico');
            that.nextUntil('dt').show();
        }
    })

    
    $('.wql_g_btn .wql_btn03 .wql_delBtn').on('click',function(){
   
    	
    	if(schoolLocationId==null ||schoolLocationId==''){
				myAlert('系统确认框','请选择服务区！',function(r){
	    	          
	    	    });
				return ;
		}
    	
    	
    	$.ajax({
				type : "post",
				async : true,
				url : "deleteSchoolLocation",
				data : {
					schoolLocationId : schoolLocationId,
					layer :layer
				},
				dataType : "text",
				success : function(msg) {
					if(msg=='success'){
						$("#pageForm").submit();					
					}else{
						myAlert('系统确认框',msg,function(r){
			    	          
			    	    });
					}
				},
				error : function(errorMsg) {
				}
		})
    });
    
    $('.wql_g_btn .wql_btn03 .wql_addBtn').on('click',function(){
    	
    	if(schoolId==''){
    		myAlert('系统确认框','请选择学校！',function(r){  
  	          
	    	});
			return ;
    	}
    	
    	
    	$("input[name='locationName']").val('');
		$("input[name='ugLayer']").val('');
		$("input[name='ogLayer']").val('');
		$("textarea[name='memo']").val('');//分类名称
    	
    	
		$("div[name='locationNameError']").html('');
		$("div[name='ugLayerError']").html('');
		$("div[name='ogLayerError']").html('');
		

        var dialog = art.dialog({
            title:'添加楼栋',
            content:$(".wql_g_artDialog")[0],
            width:'560px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
            	
        		$("div[name='locationNameError']").html('');
        		$("div[name='ugLayerError']").html('');
        		$("div[name='ogLayerError']").html('');
            	
            	var localName = $("input[name='locationName']").val();
     			var ugLayer = $("input[name='ugLayer']").val();
     			var memo = $("textarea[name='memo']").val();//分类名称
     			var ogLayer = $("input[name='ogLayer']").val();
     			
            	
				var flag=true;
     			
     			if(localName == '' ){
     				$("div[name='locationNameError']").html('服务功能区必填');
     				flag=false;
     			}
     			
     			if(ugLayer == '' ){
     				$("div[name='ugLayerError']").html("地下层数必填");
     				flag=false;
     			}
     			
     			if(ogLayer == '' ){
     				$("div[name='ogLayerError']").html("地上层数必填");
     				flag=false;
     			}
     			
     			
     			
     			if(!flag)
     				return false;
     			
     			$.ajax({
     				type : "post",
     				async : true,
     				url : "saveSchoolLocation",
     				data : {
     					name : localName,
     					ugLayer:ugLayer,
     					ogLayer:ogLayer,
     					memo:memo,
     					schoolId:$("input[name='schoolId']").val()
     				},
     				dataType : "text",
     				success : function(msg) {
     					$("#pageForm").submit();
     				},
     				error : function(errorMsg) {
     				}
     			})
     			
     		}
        });
    });
    $('.wql_addClass02').on('click',function(){
    	
    	if(schoolLocationName==null ||schoolLocationName==''){
			myAlert('系统确认框','请选择服务区！',function(r){  
	    	          
	    	});
			return ;
		}
    	
    	
    	$("div[name='areaSchoolLocationName']").text(schoolLocationName)
    	$("div[name='areaSchoolLocationCode']").text(schoolLocationCode)
    	$("div[name='areaLayer']").text(layer)
    	
    	$("div[name='areaNameError']").html('');
    	$("div[name='areaError']").html('');
    	
    	
        var dialog = art.dialog({
            title:'添加房间信息',
            content:$(".wql_g_artDialog")[1],
            width:'560px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            ok:function() {
            	
               	$("div[name='areaNameError']").html('');
               	$("div[name='areaError']").html('');
               	
     			var description = $("textarea[name='description']").val();//分类名称
     			var area = $("input[name='area']").val();
     			var attributeType =  $("input[name='locationAreaAttribute']").val();
     			var areaName = $("input[name='areaName']").val();
     			
				var flag=true;
     			
     			if(areaName == ''){
     				$("div[name='areaNameError']").html('名称必填');
     				flag=false;
     			}
     			
     			if(area == ''){
     				$("div[name='areaError']").html('面积必填');
     				flag=false;
     			}
     			
     			if(!flag)
     				return false;
     			
     			$.ajax({
     				type : "post",
     				async : true,
     				url : "saveSchoolLocationArea",
     				data : {
     					layer:layer,
     					schoolLocationId : schoolLocationId,
     					attributeType : attributeType,
     					area : area,
     					name : areaName,
     					description:description
     				},
     				dataType : "text",
     				success : function(msg) {
     					$("#pageForm").submit();
     				},
     				error : function(errorMsg) {
     				}
     			})
     			
     		}
        });
    });
})



</script>