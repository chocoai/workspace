
//创建班级页面
$(document).on("click",".zxf_big_bg_blueBtn",function(){
 	var userId = $("#userId").val();
 	location.href = "createClass.html?userId="+userId;
});


//编辑班级
$(document).on("click",".zxf_editBtn",function(){
	var classId = $(this).attr("id");
 	var userId = $("#userId").val();
 	location.href = "createClass.html?userId="+userId+"&classId="+classId;
});

//创建班级页面
$(document).on("click",".icon_add_btn",function(){
 	var userId = $("#userId").val();
 	location.href = "createClass.html?userId="+userId;
});

//编辑班级学生
$(document).on("click",".fwn",function(){
	var classId = $(this).attr("id");
 	var userId = $("#userId").val();
 	location.href = "editClass.html?userId="+userId+"&classId="+classId;
});

//删除
$(document).on("click",".zxf_delBtn",function(){
	var classId = $(this).attr("id");
 	var userId = $("#userId").val();
	
	$.modal({
        title:  '提示',  
        text:'删除此班级，其中的所有学生都会被删除',            
        buttons: [
          {
            text: '取消',
            onClick: function() {
            
            }
          },
          {
            text: '删除班级',
            onClick: function() {
            	$.ajax({
            		type : "post",
            		async : true,
            		url : "deleteClass",
            		traditional:true,
            		data : {
            			classId : classId,
            			userId:userId,
            		},
            		dataType : "text",
            		success : function(msg) {
            			location.href = "classManage.html?classId="+classId+"&userId="+userId;
            		},
            		error : function(errorMsg) {
            		}
            	})
            }
          }
        ]
      })
});