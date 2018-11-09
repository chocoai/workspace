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
                <span class="prev"><a href="" class="mgr5">系统管理</a>&gt;</span><span class="on mgl5">模块设置</span>
            </div>
        </div>
        <!-- 面包屑 E-->
        <!-- 主体部分 S-->
        <div class="clearfix wql_g_mainBox ">
            <div class="wql_Lside wql_bgfff wql_b_radius6">
            	<div class="wql_scollbox">
                <div class="wql_sideTit t_c">
                    <h3>模块</h3>
                </div>
                <div class="wql_g_sideNav">
                    <dl class="wql_dl">
                        <c:forEach items="${sysModulars}" var="sysModular" >
                        	<c:if test="${sysModular.parentId == 1}">
                    		<dt class="on" data-id="${sysModular.id}">
                        		${sysModular.modularName}<i class="wql_upico"></i>
                        	</dt>
                        	<c:forEach items="${sysModular.modulars}" var="modular2">
                  	 	 	<dd>
								<a href="javascript:;">${modular2.modularName }</a>
								<span style="display:none" name="modularId">${modular2.id }</span>
								<span style="display:none" name="terminalName">${modular2.modularName }</span>
			                </dd>
			                </c:forEach>
			                </c:if>
                    	</c:forEach>
                    </dl>
                </div>
            	</div>
            </div>
            <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
                <div class="wql_g_tit">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">查询条件</h3>
                    </div>
                    
                </div>
                <div class="wql_g_list02">
                    <ul class="wql_ul clearfix mgt25">
                        <li class="wql_li">
                            <div class="wql_li_box">
                                <span>模块名称：</span>
                                <input type="text" class="site_sysinp" name="modularName2" style="width:220px;">
                                <a href="javascript:;" class="wql_btn_search" >查询</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="wql_g_tit mgt20">
                    <div class="wql_tit01 clearfix">
                        <h3 class="fl">模块信息列表</h3>
                        <div class="wql_g_btn mgt8 fr">
                            <div class="wql_btn01">
                                <a href="#" class="wql_addClass02">添加模块</a>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="site_sysTable wql_g_table mgt15">
                    <table class="wql_table01 t_l">
                        <thead>
                            <tr>
                                <th class="pdl15" width="20%">模块名称</th>
                                <th width="20%">模块地址</th>
                                <th width="20%">模块排序</th>
                                <th width="20%">按钮</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody id="supplierinfoList">
                        </tbody>
                    </table>
                    
                    <div class="mgt40">
                    	
                	</div>
                </div>
            </div>
        </div>
        

        <!-- 主体部分 E-->

    </div>
</div>

<!-- 弹窗 S-->
<div class="wql_g_artDialog dis_none">
    <div class="wql_artDialog01 ">
        <div class="wql_g_list01 w8em" style="">
        	<form id="modularForm">
            <ul class="wql_ul">
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>模块名称
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp " type="text" name="modularName" style="width:300px;">
                            <div class="wql_tips" name="modularName"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>模块地址
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name="modularPath" style="width:300px;">
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>模块排序
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name ="modularSort" style="width:300px;">
                            <div class="wql_tips" name="modularSort"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>上级模块ID
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name ="parentId" style="width:300px;background: #999" readonly="readonly" onclick="editPermision()">
                            <div class="wql_tips" name="parentId"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 1000px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>按钮
                        </div>
                        <div class="wql_box_r">
                        	<c:forEach items="${buttons }" var="button">
                        		<input class="wql_inp" type="checkbox" name="buttons" value="${button.value }">${button.value }
                        	</c:forEach>
                         </div>
                    </div>
                </li>
                 <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em>*</em>是否有效连接
                        </div>
                        <div class="wql_box_r">
                        	<select name="isParent" class="wql_inp" onchange="checkParent()">
                            	<option value="0">是</option>
                            	<option value="1">否</option>
                            </select>
                            <div class="wql_tips" name="isParent"></div>
                        </div>
                    </div>
                </li>
                <li class="wql_li mgt35" style="width: 500px;float: left">
                    <div class="wql_li_box">
                        <div class="wql_box_l">
                            <em></em>图文样式
                        </div>
                        <div class="wql_box_r">
                            <input class="wql_inp" type="text" name="imgcss" style="width:300px;">
                            <div class="wql_tips" name="imgcss"></div>
                        </div>
                    </div>
                </li>
                
            </ul>
            </form>
        </div>
    </div>
</div>
<input type="hidden" name="currPage" id="currPage" value="1">
<input type="hidden" name="totalPage" id="totalPage">
<input type="hidden" name="pageSize" id="pageSize" value="10">

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
<script type="text/javascript" src="${ctx}/js/MyPageJs/page.js"></script>
<script type="text/javascript">
var modularId='';
var parentId='';
var modularName = '';
var parentName='';
var currentPage ;//当前页
var totalPage ;//总页数


function search(){
	supplierNum = $("input[name='searchSupplierNum']").val('');
	name = $("input[name='searchSupplierName']").val('');
	loadList(1);
}


function loadList(pageValue){
	$.ajax({
		type : "POST",
		url : "loadList",
		data : {
			"modularId" : modularId,"modularName":modularName,'pageValue':pageValue
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
						htmlStr += '<td>'+obj.modularName+'</td>';
						htmlStr += '<td>'+obj.modularPath+'</td>';
						htmlStr += '<td>'+obj.modularSort+'</td>';
						htmlStr += '<td>'+obj.buttons+'</td>';
						htmlStr += '<td><span><a href="javascript:void(0)" onclick="deleteSupplierInfo('+obj.id+')" class="">删除</a></span>  <span><a href="javascript:void(0)" onclick="editSupplierInfo('+obj.id+')" class="">编辑</a></span></td>';
						htmlStr += '</tr>';
					}
				}
			}
			
			myPage();
			if(htmlStr != ''){
				$("#supplierinfoList").html(htmlStr);
			}else{
				$("#supplierinfoList").html('<tr><td colspan="12" align="center"><font color="red">暂无数据</font></td></tr>');
				$("#base_page").html('');
			}
		}
	});
}

function checkParent(){
	if($("select[name='isParent']").val()==1){
		$("input[name='modularPath']").val("/");
		 $("input[name='buttons']").each(function () {
	            $(this).attr("checked", false);
	         })
		$("input[name='buttons'][value='无效']").prop("checked",true);
	}
}


function editSupplierInfo(id){
	
	var dialog = art.dialog({
        title:'编辑',
        content:$(".wql_g_artDialog")[0],
        width:'560px',
        okValue:"确定",      
        cancelValue:"取消",
        cancel:true,
        zIndex:1,
        initialize: function() {
        	$.ajax({
 				type : "post",
 				async : true,
 				url : "getModular",
 				data : {
 					id : id
 				},
 				dataType : "json",
 				success : function(data) {
 					if(data.sysModular.buttons != "undefined" && data.sysModular.buttons !=null){
 						var buttons =data.sysModular.buttons.split(",");
 	 		 			$("input[name='buttons']").prop("checked",false);
 	 		 			for(var i =0;i<buttons.length;i++){
 	 		 				$("input[name='buttons'][value="+buttons[i]+"]").prop("checked",true);
 	 		 			}
 					}
 					$("input[name='modularName']").val(data.sysModular.modularName);
 		 			$("input[name='modularPath']").val(data.sysModular.modularPath);
 		 			$("input[name='modularSort']").val(data.sysModular.modularSort);
 		 			$("select[name='isParent']").val(data.sysModular.isParent);
 		 			$("input[name='imgcss']").val(data.sysModular.imgcss);
 		 			$("input[name='parentId']").val(data.sysModular.parentName);
 		 			parentId=data.sysModular.parentId;
 				},
 				error : function(errorMsg) {
 				}
 			})
        },
        ok:function() {
        	if(!$("#modularForm").valid()){
        		return false;
        	}
        	var modularName = $("input[name='modularName']").val();
 			var modularPath = $("input[name='modularPath']").val();
 			var modularSort = $("input[name='modularSort']").val();
 			var isParent = $("select[name='isParent']").val();
 			var imgcss = $("input[name='imgcss']").val();
 			var buttons =[]; 
 			$('input[name="buttons"]:checked').each(function(){ 
 				buttons.push($(this).val()); 
 			}); 
 			
        	setTimeout(function(){
 				$.ajax({
     				type : "post",
     				async : true,
     				url : "udpModular",
     				data : {
     					id:id,
     		 			modularName:modularName,
     		 			modularPath:modularPath,
     		 			modularSort:modularSort,
     		 			parentId:parentId,
     		 			isParent:isParent,
     		 			buttons:buttons.toString(),
     		 			imgcss:imgcss
     				},
     				dataType : "text",
     				success : function(msg) {
     					if(msg==1){
     						window.location.reload();
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
	
	
}

function deleteSupplierInfo(id){
	myConfirm('系统确认框','是否删除！',function(r){  
        if(r){  
        	$.ajax({
    			type : "POST",
    			url : "${ctx}/manage/sysModular/delModular",
    			data : {
    				"id"  : id
    			},
    			async : false,
    			dataType : 'text',
    			success : function(msg) {
    				if(msg==1){
    					window.location.reload();
    				}
    			}
    		});
        }  
    });  
}

//登录浮层
$(function(){
	loadList(1);
	
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

        modularId=$(this).children("span[name=modularId]").text();
        parentId = $(this).children("span[name=modularId]").text();
        parentName = $(this).children("span[name=terminalName]").text();
        modularName ="";
        loadList(1)
        
        
        if($(this).prevUntil('dt').length>0){
             $(this).prevUntil('dt').last().prev().addClass('on')
        }
        else{
             $(this).prev().addClass('on')
        }
        
    })

    $(".wql_btn_search").on("click",function(){
    	modularId='';
    	modularName = $("input[name='modularName2']").val();
   		loadList(1);
    })
    
    
    $(".wql_g_sideNav dt").on("click",function(){
        var that = $(this);
        var ico = that.find('i');
        modularId=$(this).attr('data-id');
        parentId=$(this).attr('data-id');
        parentName=that.text().trim();
        
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
    	var dialog = art.dialog({
            title:'添加模块',
            content:$(".wql_g_artDialog")[0],
            width:'1200px',
            okValue:"确定",
            padding:'30px 40px',
            cancelValue:"取消",
            cancel:true,
            zIndex:1,
            initialize: function() {
            	$("input[name='buttons']").prop("checked",false);
            	$("input[name='parentId']").val("");
            	$("input[name='modularName']").val('');
     			$("input[name='modularPath']").val('');
     			$("input[name='modularSort']").val('');
     			$("select[name='isParent']").val('');
     			$("input[name='imgcss']").val('');
            },
            ok:function(){
            	if(!$("#modularForm").valid()){
            		return false;
            	}
            	
            	var modularName = $("input[name='modularName']").val();
     			var modularPath = $("input[name='modularPath']").val();
     			var modularSort = $("input[name='modularSort']").val();
     			var isParent = $("select[name='isParent']").val();
     			var imgcss = $("input[name='imgcss']").val();
     			var buttons =[]; 
     			$('input[name="buttons"]:checked').each(function(){ 
     				buttons.push($(this).val()); 
     			}); 
            	setTimeout(function(){
     				$.ajax({
         				type : "post",
         				async : true,
         				url : "saveModular",
         				data : {
         		 			modularName:modularName,
         		 			modularPath:modularPath,
         		 			modularSort:modularSort,
         		 			parentId:parentId,
         		 			isParent:isParent,
         		 			buttons:buttons.toString(),
         		 			imgcss:imgcss
         				},
         				dataType : "text",
         				success : function(msg) {
         					if(msg==1){
         						window.location.reload();
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

function editPermision() {
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
            var nodes = treeObj.getSelectedNodes();
            $("input[name='parentId']").val(nodes[0].name);
            parentId = nodes[0].id;
        }
        })
	loadTree();
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

	};
function loadTree() {
	$("#tree").html("加载中...");
	$.ajax({
		type : "POST",
		url : "${ctx}/manage/sysRole/queryModular",
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
$("#modularForm").validate({
	rules : {
		modularName:{
			required : true,
			},
		modularPath : {
			required : true,
			},
		modularSort : {
			required : true,
			},
		buttons : {
			required : true,
			},
		parentId : {
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
	$('.wql_scollbox').perfectScrollbar();
</script>