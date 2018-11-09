var changeStudent=[]//选中的学生列表

var userId = $("#userId").val()
var plateId = $("#plateId").val()

function Student(id,name,logoUrl){
	   this.id=id;
	   this.name=name;
	   this.logoUrl = logoUrl;
}

function remove(array,index)
{ 
 if(index<=(array.length-1))
 { 
  for(var i=index;i<array.length;i++)
  { 
   array[i]=array[i+1]; 
  } 
 }
 else
 { 
  throw new Error('超出最大索引！'); 
 } 
 array.length=array.length-1; 
 return array; 
}

$(document).on("touchend",".zxf_bg_blueBtn_normal.add",function(){
	var plateId = $("#plateId").val();
	var studentList = JSON.stringify(changeStudent);
	var userId = $("#userId").val();
	$.ajax({
		type : "post",
		async : true,
		url : "addTeacherToPlate",
		data : {
			plateId : plateId,
			teacherList : studentList,
			userId:userId
		},
		dataType : "text",
		success : function(msg) {
			location.href = "edit.html?userId="+userId+"&plateId="+plateId;
		},
		error : function(errorMsg) {
		
		}
	})
});


$(document).on("touchend",".zxf_add_studentBtn.zxf_bg_blueBtn",function(){
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

