$(document).ready(function() {
	var info=new Object;
	info.opeartionType = $("#opeartionType").val();
	info.opeartionUser = $("#opeartionUser").val();
	info.timeStart = $("#timeStart").val();
	info.timeEnd = $("#timeEnd").val();
	$.jgrid.defaults.styleUI = "Bootstrap";
	$("#table_dict").jqGrid({
		caption : "日志",
		url : $("#contextPath").val() + "/sysLog/queryPage?jsonString="+JSON.stringify(info),
		mtype: 'POST', 
		datatype : "json",
		height : 215,
		autowidth : true,
		shrinkToFit : true,
		autoScroll: true, 
		forceFit:true,
		rowNum : 5,
		rowList : [ 5, 10,30],
		sortname : 'orderNum', 
		sortorder : "desc", 
		rownumbers: true,
		pager : "#pager_list",
		pagerpos:"left",
		multiselect: true,//复选框  
		colNames:["序号","操作人","操作时间","操作IP","操作内容","操作详情"],
		colModel:[
		          {name : 'cid',index : 'cid',key:true, hidden: true }, 
		          {name : 'opeartionUser',sortable:false}, 
		          {name : 'opeartionTime',sortable:false},
		          {name : 'opeartionIp',sortable:false}, 
		          {name : 'opeartionContent',sortable:false},
		          {name : 'opeartionDetail',sortable:false}
		         ],

        gridComplete: function() {
            var rowIds = $("#table_dict").jqGrid('getDataIDs');
            for(var k=0; k<rowIds.length; k++) {
               var curRowData = jQuery("#table_dict").jqGrid('getRowData', rowIds[k]);
               var curChk = $("#"+rowIds[k]+"").find(":checkbox");
               curChk.attr('name', 'cid');   //给每一个checkbox赋名字
               curChk.attr('value', curRowData['cid']);   //给checkbox赋值
               //curChk.attr('checked', 'true');   //设置所有checkbox被选中
        }
        }
	});
	var width = $(".jqGrid_wrapper").width();
	$("#table_dict").setGridWidth(width)
   $("#deleteBtn").click(function() {
		var url = $("#contextPath").val() + '/sysLog/deleteInfo';
		deleteLog(url);

   });
   $("#queryBtn").click(function(){
	   var info=new Object;
	   info.opeartionType = $("#opeartionType").val();
	   info.opeartionUser = $("#opeartionUser").val();
	   info.timeStart = $("#timeStart").val();
	   info.timeEnd = $("#timeEnd").val();
	   $("#table_dict").jqGrid("setGridParam",{
		   url:$("#contextPath").val() + "/sysLog/queryPage?jsonString="+JSON.stringify(info),
		   page:1, 
		   datatype:"json"
	   }).trigger("reloadGrid");
   });
   //开始时间
   $('#timeStart').datepicker({  
	    todayBtn : "linked",  
	    autoclose : true,  
	    todayHighlight : true,  
	    endDate : new Date()  
	}).on('changeDate',function(e){  
	    var startTime = e.date;  
	    $('#timeEnd').datepicker('setStartDate',startTime);  
	});  
	//结束时间  
	$('#timeEnd').datepicker({  
	    todayBtn : "linked",  
	    autoclose : true,  
	    todayHighlight : true,  
	}).on('changeDate',function(e){  
	    var endTime = e.date;  
	    $('#timeStart').datepicker('setEndDate',endTime);  
	});
	
	$("#opeartionType").change(function(){
		$("#opeartionUser").val("");	
		$("#timeStart").val("");
		$("#timeEnd").val("");
		$("#queryBtn").click();
	})
});

//ajax提交删除操作请求
function deleteLog(url){
	var deleteType = $("#deleteType").val();
	var index = null;
	var cids = [];
	var info=new Object;
	if(deleteType=='0'){
		$('input[name="cid"]:checked').each(function() {
			cids.push($(this).val());
		});
		if (cids.length == 0) {
			layer.msg('请至少选择一条记录');
			return;
		}
	}else{
		 info.opeartionType = $("#opeartionType").val();
		 info.opeartionUser = $("#opeartionUser").val();
		 info.timeStart = $("#timeStart").val();
		 info.timeEnd = $("#timeEnd").val();
		 
		 if(!info.opeartionType){
			 layer.msg('选择需要删除的日志类型');
			 return;
		 }
	}
		
	
	layer.confirm('确定删除所选记录？', {
			title: '提示信息',
			btn : [ '确定', '取消' ],
			icon: 3
		// 按钮
		}, function() {
			$.ajax({
				type : 'POST',
				url :url,
				traditional : true,
				data : {
					cids : cids,
					deleteType:deleteType,
					jsonString:JSON.stringify(info)
				},
				dataType : 'html',
				timeout : 3000,
				beforeSend : function() {
					index = layer.load(0, {
						shade : false
					}); // 0代表加载的风格，支持0-2
				},
				complete : function() {
					layer.close(index);
				},
				error : function() {
					layer.msg('系统故障', {
						icon : 2
					});
				},
				success : function(data) {
					if (data== 'success') {
						layer.msg('删除成功', {
							icon : 1,
							time : 1000
						});
					$("#opeartionUser").val("");	
					$("#timeStart").val("");
					$("#timeEnd").val("");
					$("#queryBtn").click();
					} else if (data== "failed") {
						layer.msg('删除失败', {
							icon : 2,
							time : 1000
						});
					}else if(data=="nodata"){
						layer.msg('没有数据要处理', {
							icon : 2,
							time : 1000
						});
					}
				}
			});
		}, function() {
			layer.msg('已取消', {
				time : 1000
			// 1s后自动关闭
			});
		});
}
