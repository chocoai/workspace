
var plateId = $("#plateId").val();
var userId = $("#userId").val();

$(document).on("touchend",".wql_link",function(){

 	var name = $("#userId").val();
 	
 	var plateName = $("#plateName").val();
 	
 	if(plateName==''){
 		$.modal({
 	          title:  '提示',  
 	          text:'请填写版块名称',            
 	          buttons: [
 	            {
 	              text: '确定',
 	              onClick: function() {
 	              
 	              }
 	            }
 	          ]
 	        })
 	   return false;
 	}

 	//var plateNameLen = gblen(plateName);
 	
 	//alert(plateName.length);
 	
 	
 	if(plateName.length>10){
 		$.modal({
	          title:  '提示',  
	          text:'版块名称限10个字符',            
	          buttons: [
	            {
	              text: '确定',
	              onClick: function() {
	              
	              }
	            }
	          ]
	        })
	   return false;
 	}
 	
 	var changeStudent = []
 	//老师列表
 	$('.zxf_add_studentBox').find('li').each(function() {
 		var classValue = $(this).attr("class");
 		
 		if(classValue.indexOf("last")>-1){
	 		return true;
		 }
 		
 		 var studentId = $(this).find(".txt_ellipsis").attr("id");
 		 var platformCode = $(this).find(".txt_ellipsis").attr("platformCode");
 		 var studentName = $(this).find(".txt_ellipsis").text();
 		 var studentLogo = $(this).find("img").attr("src");
 		 var student = new Student(studentId,studentName,studentLogo,platformCode);
 		 changeStudent.push(student);
 	})
 	
 	var plate=[]
 	//板块列表
 	$('.zxf_relModuleBox').find('li').each(function() {		
 		var classValue = $(this).attr("class");
 		if(classValue.indexOf("on")>-1){
 			plate.push($(this).attr("id"))
 		}
 	})
 	
 	var studentList = JSON.stringify(changeStudent);
 	
 	
 	
 	var plateLogo = $(".logo").attr("src");
 	
 	
 	if(plateLogo == 'http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/images/wql_addImg.png'){
 		$.modal({
	          title:  '提示',  
	          text:'请上传版块图片',            
	          buttons: [
	            {
	              text: '确定',
	              onClick: function() {
	              
	              }
	            }
	          ]
	        })
	   return false;
 	}
 	

 	
 	var plateText = $("#plateText").val();
 	
 	//var plateTextLen = getByteLen(plateText);
 	
 	if(plateText.length>20){
 		$.modal({
	          title:  '提示',  
	          text:'版块简介限20个字符',            
	          buttons: [
	            {
	              text: '确定',
	              onClick: function() {
	              
	              }
	            }
	          ]
	        })
	   return false;
 	}

 	
 	$.ajax({
		type : "post",
		async : true,
		url : "save",
		traditional:true,
		data : {
			plateLogo : plateLogo,
			userId : userId,
			plateName :plateName,
			teacherList : studentList,
			plateText : plateText,
			plateId:plateId
		},
		dataType : "text",
		success : function(msg) {
			location.href = "list.html?userId="+userId;
		},
		error : function(errorMsg) {
			location.href = "list.html?userId="+userId;
		}
	})
});




$(document).on("click",".zxf_add_tagBtn.addStudent",function(){
	
	var plateId = $("#plateId").val()
	var plateName = $("#plateName").val();
	var plateText = $("#plateText").val();
	var icon = $(".logo").attr("src");
	
	
	console.log(plateName+":"+plateText)
	
	
	var changeStudent = []
 	//老师列表
 	$('.zxf_add_studentBox').find('li').each(function() {
 		var classValue = $(this).attr("class");
 		if(classValue.indexOf("last")>-1){
		 	
		  }else{
			 var studentId = $(this).find(".txt_ellipsis").attr("id");
			 var studentName = $(this).find(".txt_ellipsis").text();
			 var studentLogo = $(this).find("img").attr("src");
			 var platformCode = $(this).find(".txt_ellipsis").attr("platformCode");
			 
			 var student = new Student(studentId,studentName,studentLogo,platformCode);
		     student.id = studentId;
		     student.name = studentName;	
		     student.logoUrl = studentLogo;
		     student.platformCode = platformCode;
		     changeStudent.push(student);
		  }
 		
 		
 	})

	var teacherList = JSON.stringify(changeStudent);
	
	$.ajax({
	type : "post",
		async : true,
		url : "saveTmp",
		data : {
			plateId : plateId,
			plateName : plateName,
			plateText: plateText,
			teacherList :teacherList,
			icon:icon
		},
		dataType : "text",
		success : function(msg) {
			window.location.href = "addTeacher.html?userId="+userId+"&plateId="+plateId;
		},
		error : function(errorMsg) {
		
		}
	})
});