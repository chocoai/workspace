
var changeStudent=[]//选中的学生列表





function getStudent(aamClassId,userId){
	$.ajax({
		type : "post",
		async : true,
		url : "getStudent",
		data : {
			classId :aamClassId,
			userId : userId
		},
		dataType : "json",
		success : function(result) {
			var html = $(".zxf_add_studentBox ul").html('');//清空
			//var html = $(".zxf_add_studentBox").html();
				if (result != null) {
					var list = result.classStuentList;
					var htmlStr = '';
					console.log(htmlStr)
					for (var i = 0; i < list.length; i++) {
						var isChange=false;
						var object = list[i]
						
						
						for(var j=0;j<changeStudent.length;j++){
							console.log(object)
							console.log(object.studnetId +":" +changeStudent[j].id)
							if( object.studnetId == changeStudent[j].id ){
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
						htmlStr +=  '<img src="' + object.studentLogo +'" alt="">'
						htmlStr +=  '<p class="txt_ellipsis w90 mgt02" platformCode="'+ object.platformCode +'" id="'+object.studnetId+'">'+object.studentName+'</p>'
						htmlStr +=  '</li>'
					}
					$(".zxf_add_studentBox ul").html(htmlStr);
				}
		},
		error : function(errorMsg) {
		}
	})
	
	
}

 

 //搜索班级
 $(document).on("touchend",".search_studentBtn",function(){
	    var userId = $("#userId").val();
	    var studentName = $("#search").val();
	    console.log(studentName)
	 	$.ajax({
			type : "post",
			async : true,
			url : "searchStudent",
			data : {
				userId :userId,
				studentName : studentName
			},
			dataType : "json",
			success : function(result) {
				var html = $(".zxf_add_studentBox ul").html('');//清空
				//var html = $(".zxf_add_studentBox").html();
					if (result != null) {
						var list = result.classStuentList;
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
 
 
 

 //添加学生数据
 $(document).on("touchend",".zxf_bg_blueBtn_normal.add",function(){
	 	var classId = $("#classId").val();
	 	var userId = $("#userId").val();
	 	var studentList = JSON.stringify(changeStudent);	 	
	 	$.ajax({
			type : "post",
			async : true,
			url : "addStudentToClass",
			data : {
				userId : userId,
				classId : classId,
				studentList : studentList
			},
			dataType : "text",
			success : function(msg) {
				location.href = "editClass.html?userId="+userId+"&classId="+classId;
			},
			error : function(errorMsg) {
			}
		})
 });