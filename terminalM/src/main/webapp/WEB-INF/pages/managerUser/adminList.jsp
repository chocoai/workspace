<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<link href="${ctx}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/js/zTree/jquery.ztree.excheck-3.5.min.js"></script>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <!-- 面包屑 S-->
        <div class="wql_g_mbx">
            <div class="wql_mbx01">
                <span class="prev"><a href="" class="mgr5">用户管理</a>&gt;</span><span class="on mgl5">管理员</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">管理员列表</h3>
                    <div class="wql_g_btn mgt8 fr">
                        <div class="wql_btn01">
                            <a href="#" class="wql_addClass01">添加管理员</a>
                        </div>
                    </div>
                </div>
                
            </div>
            <div class="site_sysTable wql_g_table mgt15">
                <table class="wql_table01 t_l" style="text-align:center">
                    <thead>
                        <tr>
                        	<th class="pdl15" width="60">省/市/区</th>
                                <th width="150">学校</th>
                                <th width="80">角色名称</th>
                                <th width="120">姓名</th>
                                <th width="120">登录账号</th>
                                <th width="120">生效日期</th>
                                <th width="120">状态</th>
                                <th width="100">管理员所属单位</th>
                                <th width="80">操作</th>                          
                        </tr>
                    </thead>
                    <tbody>
                	<c:forEach items="${users}" var="obj" varStatus="status">
			    		<tr class="dictTypeTr"> 
			    			<td class="pdl15" width="60">${obj.provinceName.areaName }${obj.cityName.areaName }${obj.areaName.areaName }</td>
                                <td width="150">${obj.school.name }</td>
                                <td width="80">${obj.role.roleName }</td>
                                <td width="120">${obj.userName }</td>
                                <td width="120">${obj.account }</td>
                                <td width="120"><fmt:formatDate value="${obj.effectiveTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                <td width="120"><c:if test="${obj.status==0 }">正常</c:if><c:if test="${obj.status==1 }">禁用</c:if></td>
                                <td width="100">${obj.department }</th>
                            <td>
                            	<a href="javascript:void(0);" onclick="updateUser('${obj.id }','${obj.school.name }','${obj.cityName.areaName }','${obj.areaName.areaName }')">编辑</a>
                            </td>                                     
                        </tr>
		    		</c:forEach>
                    </tbody>
                </table>
                
                <form id="pageForm"  name="pageForm" action="${ctx}/manage/dict/dictDatalist" method="post">
                <input type="hidden" name="dictTypeId" value="${typeId}">
               
                <div class="mgt40">
                   <c:if test="${not empty page.totalPage && page.totalPage > 1 }">
						<newTag:page totalPage="${page.totalPage}" formName="pageForm" 
						currentPage="${page.currentPage}" pageSize="${page.pageSize}"  totalRows="${page.totalRows}"
						action="${ctx}/manage/dictController/dictDatalist"></newTag:page>
				   </c:if>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w7em" style="height: 300px;">
        	<form id="userForm">
            <ul class="wql_ul">
                <li class="wql_li mgt35" style="width: 500px;float: left;margin-top: 32px;">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>省、市、区
                        </div>
                        <div class="wql_box_r">
                            <select class="wql_inp" name="provinceCode" id="provinceCode" style="width: 100px;" onchange="getCity()">
                            	<option value="">请选择</option>
                        		<c:forEach items="${province }" var="area">
	                        		<option value="${area.areaCode}">${area.areaName }</option>
                        		</c:forEach>
                        	</select>
                        	<select class="wql_inp" name="cityCode" id="cityCode" style="width: 100px;" onclick="getArea()">
                        		<option value="">请选择</option>
                        	</select>
                        	<select class="wql_inp" name="areaCode" id="areaCode" style="width: 100px;">
                        		<option value="">请选择</option>
                        	</select>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>学校
                        </div>
                        <div class="wql_box_r">
                        	<select class="wql_inp" name="schoolId" id="schoolId">
	                        	<option value="">请选择</option>
	                        	<c:forEach items="${schools }" var="school">
	                        		<option value="${school.id}">${school.name }</option>
	                        	</c:forEach>
                        	</select>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left;">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>角色
                        </div>
                        <div class="wql_box_r">
                        	<select class="wql_inp" name="roleId">
                        		<c:forEach items="${sysRoles }" var="role">
	                        		<option value="${role.id }">${role.roleName }</option>
                        		</c:forEach>
                        	</select>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>姓名
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="text" name="userName" style="width:300px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>登录账号
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="text" name="account" style="width:300px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>登录密码
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="password" name="password" style="width:300px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>所在单位
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="text" name="department" style="width:300px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>状态
                        </div>
                        <div class="wql_box_r">
                            <select class="wql_inp" name="status">
	                        		<option value="0">正常</option>
	                        		<option value="1">禁用</option>
                        	</select>
                        </div>
                    </div>
                </li>
            </ul>
            </form>
        </div>
    </div>
</div>

<!--资源树-->
<div class="popup jumpBox editPermision dis_none" style="width: 300px;z-index:1000000012">
	<input type="hidden" name="role_id">
    <div class="tit"><span class="fl">资源树</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<div style="width:100%; height:300px;overflow :auto;">
            <ul id="tree" class="ztree"></ul>
        </div>
        <div style="width:100%;text-align: center;">
            <input type="button" name="save" class="btn_blue" value="保存" onclick="getModular()"/>
           	<input type="button" name="close" class="btn_gray" value="取消" />
        </div>
    </div>
</div>

<script type="text/javascript">
//登录浮层



function updateUser(id,shcoolName,cityName,aeraName){
	 var dialog = art.dialog({
         title:'修改角色',
         content:$(".wql_g_artDialog")[0],
         width:'1200px',
         okValue:"确定",
         padding:'30px 60px',
         cancelValue:"取消",
         cancel:true,
         zIndex:1,
         initialize:function(){
        	 $.ajax({
        		 type:'post',
        		 url:'getUser',
        		 data:{
        			 id:id
        		 },
        		 success:function(obj){
        			$("select[name='provinceCode']").val(obj.taManageUserInfo.provinceCode);
        			if(typeof(obj.taManageUserInfo.cityCode) == "undefined" || obj.taManageUserInfo.cityCode==""){
        				var optionstring1="<option value=\"\" >请选择</option>"; 
	       				$("#cityCode").html(optionstring1);
   	        	 	}else{
	   	        	 	var optionstring1="<option value=\"" +obj.taManageUserInfo.cityCode+ "\" >" + cityName + "</option>"; 
	       				$("#cityCode").html(optionstring1);
   	        	 	}
        			if(typeof(obj.taManageUserInfo.areaCode) == "undefined" || obj.taManageUserInfo.areaCode==""){
        				var optionstring2="<option value=\"\" >请选择</option>"; 
	       				$("#areaCode").html(optionstring2);
   	        	 	}else{
	   	        	 	var optionstring2="<option value=\"" +obj.taManageUserInfo.areaCode+ "\" >" + aeraName + "</option>"; 
	       				$("#areaCode").html(optionstring2);
   	        	 	}
        			$("select[name='schoolId']").val(obj.taManageUserInfo.schoolId);
       	         	$("select[name='roleId']").val(obj.taManageUserInfo.roleId);
       	         	$("input[name='userName']").val(obj.taManageUserInfo.userName);
       	         	$("input[name='account']").val(obj.taManageUserInfo.account);
       	         	$("input[name='password']").val(obj.taManageUserInfo.password);
       	         	$("input[name='department']").val(obj.taManageUserInfo.department);
       	         	$("select[name='status']").val(obj.taManageUserInfo.status);
        		 }
        	 })
         },
         ok:function() {
        	if(!$("#userForm").valid()){
         		return false;
         	}
        	var provinceCode = $("select[name='provinceCode']").val();
         	var cityCode = $("select[name='cityCode']").val();
         	var areaCode = $("select[name='areaCode']").val();
         	var schoolId = $("select[name='schoolId']").val();
         	var roleId = $("select[name='roleId']").val();
         	var userName = $("input[name='userName']").val();
         	var account = $("input[name='account']").val();
         	var password = $("input[name='password']").val();
         	var department = $("input[name='department']").val();
         	var status = $("select[name='status']").val();
 			
 			$.ajax({
 				type : "post",
 				async : true,
 				url : "updateUser",
 				data : {
 					id : id,
 					provinceCode : provinceCode,
					cityCode : cityCode,
					areaCode : areaCode,
					schoolId : schoolId,
					roleId : roleId,
					userName : userName,
					account : account,
					password : password,
					department : department,
					status : status
 				},
 				dataType : "json",
 				success : function(msg) {
 					if(msg==1){
 						window.location.reload();
 					}
 				},
 				error : function(errorMsg) {
 				}
 			})
 			//$("#pageForm").submit();
 		}
     });
}

$(function(){
    
	$(".dictTypeTr").bind("click",function(){
		//alert("1");
	});
	
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

    
    $('.wql_addClass01').on('click',function(){
    	
        var dialog = art.dialog({
            title:'角色添加',
            content:$(".wql_g_artDialog")[0],
            width:'1200px',
            okValue:"确定",
            padding:'30px 60px',
            cancelValue:"取消",
            cancel:true,
            zIndex:1,
            initialize:function(){
            	$("select[name='provinceCode']").val(provinceCode);
             	var optionstring1="<option value=\"\" >请选择</option>"; 
    			$("#cityCode").html(optionstring1);
    			var optionstring2="<option value=\"\" >请选择</option>"; 
    			$("#areaCode").html(optionstring2);
    			$("select[name='schoolId']").val(schoolId);
             	$("select[name='roleId']").val("");
             	$("input[name='userName']").val("");
             	$("input[name='account']").val("");
             	$("input[name='password']").val("");
             	$("input[name='department']").val("");
             },
            ok:function() {
            	if(!$("#userForm").valid()){
             		return false;
             	}
            	var provinceCode = $("select[name='provinceCode']").val();
            	var cityCode = $("select[name='cityCode']").val();
            	var areaCode = $("select[name='areaCode']").val();
            	var schoolId = $("select[name='schoolId']").val();
            	var roleId = $("select[name='roleId']").val();
            	var userName = $("input[name='userName']").val();
            	var account = $("input[name='account']").val();
            	var password = $("input[name='password']").val();
            	var department = $("input[name='department']").val();
    			$.ajax({
    				type : "post",
    				async : false,
    				url : "addUser",
    				data : {
    					provinceCode : provinceCode,
    					cityCode : cityCode,
    					areaCode : areaCode,
    					schoolId : schoolId,
    					userName:userName,
    					roleId : roleId,
    					account : account,
    					password : password,
    					department : department
    				},
    				dataType : "text",
    				success : function(msg) {
    					if(msg==1){
     						window.location.reload();
     					}
    				},
    				error : function(errorMsg) {
    				}
    			})  
    			
    		}
        });
    });

})

function getModular(){
	var roleId=$(".editPermision input[name='role_id']").val();
	var treeObj=$.fn.zTree.getZTreeObj("tree");
    var nodes=treeObj.getCheckedNodes(true);
    var modularIds = "";
    for(var i=0;i<nodes.length;i++){
    	modularIds += nodes[i].id+",";
    }
    $.ajax({
		type : "POST",
		url : "${ctx}/manage/sysRole/saveRolePermission",
		data : {
			"roleId" : roleId,
			"modularIds" : modularIds,
		},
		async : false,
		dataType : 'text',
		success : function(msg) {
			alert(msg)
		}
	});
}
function getCity(){
	var provinceId = $("select[name='provinceCode']").val();
	$.ajax({
		type :"post",
		url :"getArea",
		data : {
			"provinceId":provinceId
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			if(data.areas!=null && data.areas.length>0){
				var optionstring="<option value=\"\" >请选择</option>";  
				for(var i = 0 ;i<data.areas.length;i++){
					optionstring += "<option value=\"" + data.areas[i].id + "\" >" + data.areas[i].areaName + "</option>";  
				}
				$("#cityCode").html(optionstring); //获得要赋值的select的id，
			}else{
             	var optionstring1="<option value=\"\" >请选择</option>"; 
    			$("#cityCode").html(optionstring1);
    			var optionstring2="<option value=\"\" >请选择</option>"; 
    			$("#areaCode").html(optionstring2);
    			var optionstring3="<option value=\"\" >请选择</option>"; 
    			$("#schoolId").html(optionstring3);
			}
			if(data.schools!=null && data.schools.length>0){
				var schoolstring="<option value=\"\" >请选择</option>";
				for(var i = 0 ;i<data.schools.length;i++){
					schoolstring += "<option value=\"" + data.schools[i].id + "\" >" + data.schools[i].name + "</option>";  
				}
				$("#schoolId").html(schoolstring); //获得要赋值的select的id，
			}else{
				var schoolstring="<option value=\"\" >暂无数据</option>";
				$("#schoolId").html(schoolstring); //获得要赋值的select的id，
			}
		}
	})
}

function getArea(){
	var cityId = $("select[name='cityCode']").val();
	$.ajax({
		type :"post",
		url :"getArea",
		data : {
			"cityId":cityId
		},
		async : false,
		dataType : 'json',
		success : function(data) {
			var optionstring="<option value=\"\" >请选择</option>";  
			if(data.areas!=null && data.areas.length>0){
				for(var i = 0 ;i<data.areas.length;i++){
					optionstring += "<option value=\"" + data.areas[i].id + "\" >" + data.areas[i].areaName + "</option>";  
				}
				$("#areaCode").html(optionstring);
			}
			if(data.schools!=null && data.schools.length>0){
				var schoolstring="<option value=\"\" >请选择</option>";
				for(var i = 0 ;i<data.schools.length;i++){
					schoolstring += "<option value=\"" + data.schools[i].id + "\" >" + data.schools[i].name + "</option>";  
				}
				$("#schoolId").html(schoolstring); //获得要赋值的select的id，
			}else{
				var schoolstring="<option value=\"\" >暂无数据</option>";
				$("#schoolId").html(schoolstring); //获得要赋值的select的id，
			}
		}
	})
}
$("#userForm").validate({
	rules : {
		provinceCode:{
			required : true,
			},
		roleId : {
			required : true,
			},
		userName : {
			required : true,
			},
		account : {
			required : true,
			},
		password : {
			required : true,
			},
		department : {
			required : true,
			},
	},
	
	errorPlacement:function(error,element) {
    	layer.tips($(error).text(), element, {
    		tips: 2,
    		tipsMore: true
    	});
    }
	})
</script>


