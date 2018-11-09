var userId = $("#userId").val()

$(document).on("click",".zxf_bg_blueBtn.relative.on",function(){
	var plateId = $(this).attr("id");
 	var classId = $("#classId").val();
	
 	$.modal({
        title:  '提示',  
        text:'取消关联后，本班将看不到对应的版块',            
        buttons: [
          {
            text: '取消',
            onClick: function() {
            
            }
          },
          {
            text: '取消关联',
            onClick: function() {
            	$.ajax({
            		type : "post",
            		async : true,
            		url : "deleteClassPlate",
            		traditional:true,
            		data : {
            			plateId : plateId,
            			classId : classId
            		},
            		dataType : "text",
            		success : function(msg) {
            		},
            		error : function(errorMsg) {
            		}
            	})
            }
          }
        ]
      })
      
 	
});


$(document).on("click",".zxf_bg_grayBtn.relative",function(){
	var plateId = $(this).attr("id");
 	var classId = $("#classId").val();
	
 	$.modal({
        title:  '提示',  
        text:'是否关联该版块',            
        buttons: [
          {
            text: '取消',
            	onClick: function() {
            	
            }
          },
          {
            text: '关联',
            onClick: function() {
            	$.ajax({
            		type : "post",
            		async : true,
            		url : "addClassPlate",
            		traditional:true,
            		data : {
            			plateId : plateId,
            			classId : classId
            		},
            		dataType : "text",
            		success : function(msg) {
            		},
            		error : function(errorMsg) {
            		}
            	})
            }
          }
        ]
      })
 	
});


$(document).on("click",".zxf_bor_grayBtn",function(){
	var userId = $(this).attr("id");
 	var classId = $("#classId").val();
	
 	
	$.modal({
        title:  '提示',  
        text:'退出班级后，用户将看不到班级的内容',            
        buttons: [
          {
            text: '取消',
            onClick: function() {
            
            }
          },
          {
            text: '退出班级',
            onClick: function() {
            	$.ajax({
            		type : "post",
            		async : true,
            		url : "deleteClassUser",
            		traditional:true,
            		data : {
            			classId : classId,
            			userId : userId
            		},
            		dataType : "text",
            		success : function(msg) {
            			location.href = "editClass.html?userId="+userId+"&classId="+classId;
            		},
            		error : function(errorMsg) {
            		}
            	})
            }
          }
        ]
      })
 	
 	
});


//跳转到编辑老师页面
$(document).on("click",".zxf_add_studentBtn",function(){
 	var classId = $("#classId").val();
 	
 	window.location.href = "editStudent.html?userId="+userId+"&classId="+classId;
});


//查询老师
$(document).on("touchend",".icon_search",function(){
	var search = $("#search").val();
	var classId = $("#classId").val();
 	window.location.href = "editClass.html?userId="+userId+"&classId="+classId+"&name="+search;
});

