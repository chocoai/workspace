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
                <span class="prev"><a href="" class="mgr5">系统管理</a>&gt;</span><span class="on mgl5">角色管理</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_tit">
                <div class="wql_tit01 clearfix">
                    <h3 class="fl">角色信息</h3>
                    <%@ include file="/WEB-INF/pages/common/button.jsp"%>
                </div>
            </div>
            <div class="site_sysTable wql_g_table mgt15">
                <table class="wql_table01 t_l" style="text-align:center">
                    <thead>
                        <tr>
                        	<th width="5%">序号</th>
                            <th width="20%">角色名称</th>
                            <th width="10%">角色等级</th>
                            <th width="20%">角色职责</th>
                            <th width="20%">创建时间</th>
                            <th width="10%">角色状态</th>
							<th>操作</th>                                  
                        </tr>
                    </thead>
                    <tbody>
                	<c:forEach items="${sysRoles}" var="obj" varStatus="status">
			    		<tr class="dictTypeTr"> 
			    			<td>${status.count} </td>
                            <td>${obj.roleName}</td>
                            <td>
                                <c:if test="${obj.roleLevel == 5}">校级</c:if>
                                <c:if test="${obj.roleLevel == 4}">区级</c:if>
                                <c:if test="${obj.roleLevel == 3}">市级</c:if>
                                <c:if test="${obj.roleLevel == 2}">省级</c:if>
                                <c:if test="${obj.roleLevel == 1}">超级</c:if>
                            </td>
                            <td>${obj.roleComment}</td>
                            <td>${obj.createTime}</td>
                            <td>
                            	<c:if test="${obj.status == 0}">正常</c:if>
                            	<c:if test="${obj.status == 1}">禁用</c:if>
                            </td>
                            <td>
                            	<a href="javascript:void(0);"  onclick="updateDictData('${obj.id}','${obj.roleName}','${obj.roleComment}','${obj.status}','${obj.createTime}')">编辑</a>
                            	<a href="javascript:void(0);"  onclick="deleteDictData('${obj.id}')">删除</a>
                            	<a href="javascript:void(0);"  onclick="editPermision('${obj.id}')">权限配置</a>
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
        	<form id="roleForm">
            <ul class="wql_ul">
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>角色名称
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="text" name="roleName" style="width:300px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>角色等级
                        </div>
                        <div class="wql_box_r">
                            <select class="wql_inp " name="roleLevel">
                                <option value="5">校级</option>
                                <option value="4">区级</option>
                                <option value="3">市级</option>
                                <option value="2">省级</option>
                                <option value="1">超级</option>
                            </select>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>角色职责
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="text" name="roleComment" style="width:300px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>角色状态
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
<div class="wql_g_artDialog dis_none" >
    <div class="wql_artDialog04">
        <div class="wql_DialogBox ">
            <input type="hidden" name="role_id">
		    <div class="cont pd10">
				<div style="width:100%; height:300px;overflow :auto;">
		            <ul id="tree" class="ztree"></ul>
		        </div>
		    </div>
        </div>
    </div>
</div>

<script type="text/javascript">
//登录浮层



function updateDictData(id,roleName,roleComment,status,createTime){
	 $("#dictDataName").val(name);//分类名称
	 var dialog = art.dialog({
         title:'修改角色',
         content:$(".wql_g_artDialog")[0],
         width:'660px',
         okValue:"确定",
         padding:'30px 60px',
         cancelValue:"取消",
         cancel:true,
         zIndex:1,
         initialize:function(){
        	$("input[name='roleName']").val(roleName);
 			$("input[name='roleComment']").val(roleComment);
 			$("select[name='status']").val(status);
         },
         ok:function() {
        	 if(!$("#roleForm").valid()){
        		 return false;
        	 }
        	var roleName = $("input[name='roleName']").val();
        	var roleLevel = $("select[name='roleLevel']").val();
 			var roleComment = $("input[name='roleComment']").val();
 			var status = $("select[name='status']").val();
 			
 			$.ajax({
 				type : "post",
 				async : true,
 				url : "udpSole",
 				data : {
 					id : id,
 					roleName : roleName,
					roleComment : roleComment,
                    roleLevel:roleLevel,
					status : status,
					createTime : createTime
 				},
 				dataType : "json",
 				success : function(msg) {
 					if(msg=="1"){
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

function deleteDictData(id){
    if(confirm("确定删除？")){
        $.ajax({
            type:"post",
            url:"delSole",
            data:{
                id:id
            },
            success:function(msg){
                if(msg==1){
                    layer.msg("删除成功", {
                        time: 2000, //2s后自动关闭
                    },function (){
                        window.location.reload();
                    });
                }else{
                    layer.msg("删除失败", {
                        time: 2000, //2s后自动关闭
                    });
                }
            }

        })
    }
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
            width:'560px',
            okValue:"确定",
            padding:'30px 60px',
            cancelValue:"取消",
            cancel:true,
            zIndex:1,
            initialize:function(){
            	$("input[name='roleName']").val("");
     			$("input[name='roleComment']").val("");
             },
            ok:function() {
            	if(!$("#roleForm").valid()){
            		return false
            	}
    			var roleName = $("input[name='roleName']").val();
    			var roleComment = $("input[name='roleComment']").val();
    			var status = $("select[name='status']").val();
                var roleLevel = $("select[name='roleLevel']").val();
                $.ajax({
    				type : "post",
    				async : false,
    				url : "saveSole",
    				data : {
    					roleName : roleName,
                        roleLevel:roleLevel,
    					roleComment : roleComment,
    					status : status
    				},
    				dataType : "text",
    				success : function(msg) {
    					if(msg=="1"){
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

function editPermision(roleId) {
	var dialog = art.dialog({
        title:'资源树',
        content:$(".wql_g_artDialog")[1],
        width:'400px',
        okValue:"确定",
        padding:'30px 40px',
        cancelValue:"取消",
        cancel:true,
        ok:function() {
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
        			if(msg=="success"){
        				window.location.reload();
        			}
        		}
        	});
        }
        })
	loadTree(roleId);
}
var zNodes;
var setting = {
		view : {
			showLine : false,
			showIcon : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			chkboxType : {
				"Y" : "ps",
				"N" : "ps"
			},
			chkStyle : 'checkbox',
			enable : true
		}

	};
function loadTree(roleId) {
	$("#tree").html("加载中...");
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/sysRole/authority",
		data:{
			roleId:roleId
		},
		async : false,
		dataType : 'json',
		success : function(msg) {
			if (msg == null || msg == '[]' || msg.length < 1) {
				$("#tree").html("没有数据！");
			} else {
				zNodes = msg;
				$.fn.zTree.init($("#tree"), setting, zNodes);
				var treeObj=$.fn.zTree.getZTreeObj("tree");
				treeObj.expandAll(true); 
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			$("#tree").html('查询失败');
		}
	});

}

$("#roleForm").validate({
	rules : {
		roleName:{
			required : true,
			},
		roleComment : {
			required : true,
			}
	},
	errorPlacement:function(error,element) {
    	layer.tips($(error).text(), element, {
    		tips: 2,
    		tipsMore: true
    	});
    }
	})
</script>

