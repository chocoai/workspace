
var classId = $("#classId").val()
var userId = $("#userId").val()

 
 $(document).on("touchend",".wql_link",function(){
	 	var classId = $("#classId").val();
	 	var name = $("#userId").val();
	 	var className = $("#className").val();
	 	var userId = $("#userId").val();
	 	
	 	if(className==''){
	 		$.modal({
		          title:  '提示',  
		          text:'请填写班级名称',            
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
	 	
	 	
	 	//var classNameLen = getByteLen(className);
	 	
	 	if(className.length>20){
	 		$.modal({
		          title:  '提示',  
		          text:'限20个字符',            
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
	 	//学生列表
	 	$('.zxf_add_studentBox').find('li').each(function() {
	 		var classValue = $(this).attr("class");
	 		
		 	 if(classValue.indexOf("last")>-1){
		 		return true;
			 }
	 		 
		 	 var platformCode = $(this).find(".txt_ellipsis").attr("platformCode");
	 		 var studentId = $(this).find(".txt_ellipsis").attr("id");
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
	 	
	 	$.ajax({
			type : "post",
			async : true,
			url : "saveClass",
			traditional:true,
			data : {
				userId : userId,
				className :className,
				studentList : studentList,
				plateList : plate,
				classId:classId
			},
			dataType : "text",
			success : function(msg) {
				location.href = "classManage.html?userId="+userId;
			},
			error : function(errorMsg) {
			}
		})
 });
 
 
 
 
//添加学生  数据保存到memcache
 $(".zxf_add_studentBox").on("touchend","li.last",function(){
   //var $html="";
   //$html+='<li class="t_c f085 c_normal">';
   //$html+='<a href="javascript:;" class="icon_del_red"></a>';
   //$html+='<img src="images/img.png" alt="">';
   //$html+='<p class="txt_ellipsis w90 mgt02">林丽</p>';
   //$html+='</li>';
   //$(this).before($html);
   
   var userId = $("#userId").val();
   var classId = $("#classId").val();
   var className = $("#className").val();
   
   var plateList = []
   $('.zxf_relModuleBox').find('li').each(function() {
   	    var classValue = $(this).attr("class");
 
   	    var studentId = $(this).find(".txt_ellipsis").attr("id");
   	    var studentName = $(this).find(".txt_ellipsis").text();
   	    var studentLogo = $(this).find("img").attr("src");
   	    
		 if(classValue.indexOf("on")>-1){
			 	var plateId = $(this).attr("id");
			 
		    	var plate = new Student(plateId);
		    	plate.id = plateId;
		    	
		    	plateList.push(plate);
		  }
   })
  
   var studentList = []
   
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
		     studentList.push(student);
		  }
  })
   
   $.ajax({
		type : "post",
		async : true,
		url : "saveTmpClass",
		data : {
			className :className,
			classId : classId,
			plateList : JSON.stringify(plateList),
			studentList : JSON.stringify(studentList)
		},
		dataType : "text",
		success : function(msg) {
			location.href = "addStudent.html?userId="+userId+"&classId="+classId;
		},
		error : function(errorMsg) {
		}
	})
                 
 })
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 