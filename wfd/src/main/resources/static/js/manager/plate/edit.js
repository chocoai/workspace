var userId = $("#userId").val()

//$(document).on("click",".zxf_relativeBtn.qxgl.on",function(){
//	var classId = $(this).attr("id");
 //	var plateId = $("#plateId").val();
	
 //	$.ajax({
//		type : "post",
//		async : true,
//		url : "relationPlate",
//		traditional:true,
//		data : {
//			plateId : plateId,
//			classId : classId
//		},
//		dataType : "text",
//		success : function(msg) {
			//$(this).removeClass("on zxf_bg_blueBtn").addClass("zxf_bg_grayBtn").text("取消关联");
//		},
//		error : function(errorMsg) {
//		}
//	})
//});


//$(document).on("click",".gxbj 1",function(){
//	var classId = $(this).attr("id");
 //	var plateId = $("#plateId").val();
	
	
 //	$.ajax({
//		type : "post",
//		async : true,
//		url : "deleteRelationPlate",
//		traditional:true,
//		data : {
//			plateId : plateId,
//			classId : classId
//		},
//		dataType : "text",
//		success : function(msg) {
			//$(this).addClass("on zxf_bg_blueBtn").removeClass("zxf_bg_grayBtn").text("关联");
//		},
	//	error : function(errorMsg) {
//		}
//	})
	
	
//});

//查询老师
$(document).on("touchend",".icon_search",function(){
	var search = $("#search").val();
	var plateId = $("#plateId").val();
 	window.location.href = "edit.html?userId="+userId+"&plateId="+plateId+"&name="+search;
});




$(document).on("click",".zxf_bor_grayBtn",function(){
	var teacherId = $(this).attr("id");
 	var plateId = $("#plateId").val();
	
 	$.modal({
        title:  '提示',  
        text:'退出版块后，用户将不能看到版块的内容',            
        buttons: [
          {
            text: '取消',
            onClick: function() {
            
            }
          },
          {
            text: '退出版块',
            onClick: function() {
            	$.ajax({
            		type : "post",
            		async : true,
            		url : "deletePlateUser",
            		traditional:true,
            		data : {
            			plateId : plateId,
            			userId : teacherId
            		},
            		dataType : "text",
            		success : function(msg) {
            			location.href = "edit.html?userId="+userId+"&plateId="+plateId;
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
$(document).on("touchend",".zxf_add_studentBtn",function(){
 	var plateId = $("#plateId").val();

 	window.location.href = "editTeacher.html?userId="+userId+"&plateId="+plateId;
});

