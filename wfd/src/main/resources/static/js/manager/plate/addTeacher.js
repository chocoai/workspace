var changeStudent=[]//选中的学生列表


//取消
$(document).on("touchend",".zxf_bg_grayBtn",function(){
	
});

//搜索按钮
$(document).on("touchend",".searchTeacherBtn",function(){
		var userId = $("#userId").val();
	    var teacherName = $("#search").val();
	  
	 	$.ajax({
			type : "post",
			async : true,
			url : "searchTeacher",
			data : {
				userId :userId,
				teacherName : teacherName
			},
			dataType : "json",
			success : function(result) {
				var html = $(".zxf_add_studentBox ul").html('');//清空
				//var html = $(".zxf_add_studentBox").html();
					if (result != null) {
						var list = result.teacherList;
						var htmlStr = '';
						console.log(htmlStr)
						for (var i = 0; i < list.length; i++) {
							var isChange=false;
							var object = list[i]
							
							
							for(var j = 0;j < changeStudent.length;j++){
								
								console.log(object.personId +":" +changeStudent[j].id)
								if( object.personId == changeStudent[j].id ){
									console.log(1)
									isChange=true;
									break;
								}
							}
							console.log(isChange)
							
							if(isChange){
								htmlStr += '<li class="t_c f085 c_normal chked">'
							}else{
								htmlStr += '<li class="t_c f085 c_normal ">'
							}
							
							htmlStr +=  '<a href="javascript:;" class="icon_chked_blue"></a>'
							htmlStr +=  '<img src="'+object.logoUrl+'" alt="">'
							htmlStr +=  '<p class="txt_ellipsis w90 mgt02" platformCode="'+ object.platformCode +'" id="'+object.personId+'">'+object.name+'</p>'
							htmlStr +=  '</li>'
						}
						$(".zxf_add_studentBox ul").html(htmlStr);
					}
			},
			error : function(errorMsg) {
			}
	 	})
	
});


//添加 
$(document).on("touchend",".zxf_bg_blueBtn_normal.add",function(){
	var plateId = $("#plateId").val();
	var studentList = JSON.stringify(changeStudent);
	var userId = $("#userId").val();
	$.ajax({
		type : "post",
		async : true,
		url : "saveTmpTeacher",
		data : {
			plateId : plateId,
			teacherList : studentList
		},
		dataType : "text",
		success : function(msg) {
			location.href = "create.html?userId="+userId+"&plateId="+plateId;
		},
		error : function(errorMsg) {
		
		}
	})
});
