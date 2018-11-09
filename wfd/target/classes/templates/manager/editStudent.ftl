<#assign base=request.contextPath />
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black">
  <meta name="screen-orientation" content="portrait">
  <meta name="x5-fullscreen" content="true">
  <title>微辅导</title>
  <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/light7.css">
  <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/wql_base.css">
  <link rel="stylesheet" href="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/css/weiLesson.css">
  <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/jquery.js"></script>
  <script>
    $.config = {
      autoInit: true,
      router: false,
    }
  </script>
  <script src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/weiLesson/js/light7.js"></script>
</head>

<body>
  <div class="page">
    <header class="bar bar-nav bgfff">
      <a class="icon icon-left fl" href="${base}/page/manager/editClass.html?userId=${userId}&classId=${classId}"></a>
      <h1 class="title">添加学生</h1>
    </header>
    <div class="zxf_creadHead f085 c_norma bb search fixed bgfff">
      <div class="zxf_search_box pd1 clearfix bgfff">
        <div class="zxf_search w70 fl">
          <label class="icon_search" for="search"></label>
          <input type="search" id="search" placeholder="请输入学生姓名">
        </div>
        <a href="javascript:;" class="zxf_bg_blueBtn zxf_add_studentBtn fr mgr075 mgt01 search_studentBtn">搜索</a>
      </div>
    </div>
    <div class="content bgfff mgt05 pdb32">
      <div class="zxf_creatClass_wp">
        <div class="zxf_creatClass_item pdlr075">
          <div class="zxf_creatCon pdtb075 c_9f9f9f f085">
            <div class="clearfix mgb05">
              <div class="fl zxf_seleBox f075">
              	<#if (classList?size>0) >
              			<#list classList as classes>
              				<#if classes_index == 0>
              					<p class="zxf_seleValue pdl05 currentClass" style="width: 6.625rem;height: 1.45rem;line-height: 1.45rem;" id="${classes.classId}" >${classes.className}</p>
              				</#if >
              			</#list>
                
                <#else>
                <p class="zxf_seleValue pdl05" style="width: 6.625rem;height: 1.45rem;line-height: 1.45rem;">无班级数据</p>
                </#if >
                <div class="zxf_sele_dropWp" style="width: 6.625rem;">
                  <i class="icon_triangle"></i>
                  <#list classList as classes>
                  	 <a href="javascript:;" id="${classes.classId}">${classes.className}</a>
                  </#list>
                </div>
              </div>
              <span class="fr zxf_hasChked">已选<em class="num"></em>人</span>
            </div>
            <div class="zxf_add_studentBox pdtb05">
              <ul class="clearfix">
                <!--<#list studentList as student>
              		<li class="t_c f085 c_normal">
	                  <a href="javascript:;" class="icon_chked_blue"></a>
	                  <img src="${student.logoUrl}" alt="">
	                  <p class="txt_ellipsis w90 mgt02" platformCode="${student.platformCode}" id="${student.personId}">${student.name}</p>
	                </li>
              	</#list>-->          
              </ul>
              <a href="javascropt:;" class="zxf_add_stuBtn mgl075"></a>

            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="fixed t_c zxf_nav_bottom bt pdtb06 bgfff">
      <ul class="clearfix">
        <li class="fl w50"><a href="javascript:;" class="zxf_bg_grayBtn_normal cancel" id="cancel" style="width:140px;">取消</a></li>
        <li class="fl w50"><a href="javascript:;" class="zxf_bg_blueBtn_normal add" style="width:140px;">添加</a></li>
      </ul>
    </div>
  </div>
  
    <input type="hidden" name="userId" id="userId" value="${userId}"/>
  <input type="hidden" name="classId" id="classId" value="${classId}"/>
  
  <script type="text/javascript" src="${base}/js/common/common.js"></script>
  <script type="text/javascript" src="${base}/js/manager/editStudent.js"></script>
  <script type="text/javascript" src="${base}/js/json2.js"></script>
  <script type="text/javascript" src="${base}/js/fastclick.js"></script>
  <script>
  	$(function() {    
	    FastClick.attach(document.body);    
	}); 
  
  var userId = $("#userId").val();
  var classId = $("#classId").val();
 
 	appGoback = function(){
	 	window.location.href="${base}/page/manager/editClass.html?userId=${userId}&classId=${classId}";
	}
 	
 
  	$(document).on('touchend','#cancel',function(){
	 		location.href = "${base}/page/manager/editClass.html?userId=${userId}&classId=${classId}";
 	});
  
  		$(document).ready(function(){ 
	  		var checklength = 0;
	  		
	  		<#if (studentList?size>0) >
	  			<#list studentList as student>
	  				var studentId = '${student.personId}' ;
	  				var studentName = '${student.name}' ;
	  				var studentLogo = '${student.logoUrl}';
	  				var platformCode = '${student.platformCode}';
	  		
	  				console.log(${student.platformCode});
	  		
	  		     	var student = new Student(studentId,studentName,studentLogo,platformCode);
			     	student.id = studentId;
			    	student.name = studentName;	
					student.logoUrl = studentLogo;
					student.platformCode = platformCode;
					changeStudent.push(student);
					
					checklength = checklength + 1;
	  			</#list>
	  		</#if>
	  		
	  		//$('.zxf_add_studentBox').find('li').each(function() {
	              
	         //var studentId = $(this).find(".txt_ellipsis").attr("id");
		  	 //var studentName = $(this).find(".txt_ellipsis").text();
		 	 //var studentLogo = $(this).find("img").attr("src");
						
				
			//	checklength = checklength + 1;
			//	var student = new Student(studentId,studentName,studentLogo);
			//	student.id = studentId;
			//	student.name = studentName;	
			//	student.logoUrl = studentLogo;
			//    $(this).addClass("chked");
			    
			//	changeStudent.push(student);
	       //    })
	            $(".zxf_hasChked").find(".num").text(checklength);
	            
	            var currentClassId = $(".currentClass").attr("id");
	            //alert(currentClassId);
	            getStudent(currentClassId,userId)
	            
	            
        });
  
    // 删除
    $(document).on('touchend', '.icon_del_red', function () {
      $(this).parents('li').remove();
    });
    // 下拉
    $(document).on("click", function (e) {
      e.stopPropagation();
      $(".zxf_sele_dropWp").hide().parents(".zxf_seleBox").find(".zxf_seleValue").removeClass("on");
    }).on("click", ".zxf_seleValue", function (e) {
      e.stopPropagation()
      if ($(this).hasClass("on")) {
        $(this).removeClass("on").parents(".zxf_seleBox").find(".zxf_sele_dropWp").hide();
      } else {
        $(this).addClass("on").parents(".zxf_seleBox").find(".zxf_sele_dropWp").show();
      }
    }).on("click", ".zxf_sele_dropWp a", function (e) {
       var aamClassId = $(this).attr("id");
      
       
       //if(classId=='-1'){
       	//	var html = $(".zxf_add_studentBox ul").html('');
       //		var htmlStr = '';
       	//	for (var i = 0; i < changeStudent.length; i++) {
		//		var object = changeStudent[i]
		//		htmlStr += '<li class="t_c f085 c_normal chked">'
		//		htmlStr +=  '<a href="javascript:;" class="icon_chked_blue"></a>'
		//		htmlStr +=  '<img src="'+object.logoUrl+'" alt="">'
		//		htmlStr +=  '<p class="txt_ellipsis w90 mgt02" id="'+object.id+'">'+object.name+'</p>'
		//		htmlStr +=  '</li>'
		//	}
		//	$(".zxf_add_studentBox ul").html(htmlStr);
       		
       //}else{
       	  getStudent(aamClassId,userId);
      // }
       
       
    
      e.stopPropagation();
      $(this).parents(".zxf_seleBox").find(".zxf_sele_dropWp").hide();
      $(this).parents(".zxf_seleBox").find(".zxf_seleValue").text($(this).text()).removeClass("on");
    });
    // 选中
    $(".zxf_add_studentBox ").on("click", "ul>li", function () {
      $(this).toggleClass("chked");
      var $len = $(".zxf_add_studentBox ul li.chked").length;
      
      
      var classValue = $(this).attr("class");
	  var studentId = $(this).find(".txt_ellipsis").attr("id");
	  var studentName = $(this).find(".txt_ellipsis").text();
	  var studentLogo = $(this).find("img").attr("src");
	  var platformCode = $(this).find(".txt_ellipsis").attr("platformCode");
	  
	   if(classValue.indexOf("chked")>-1){
	    	var student = new Student(studentId,studentName,studentLogo,platformCode);
	    	student.id = studentId;
	    	student.name = studentName;	
	    	student.logoUrl = studentLogo;
	    	student.platformCode = platformCode
	        changeStudent.push(student);
	   }else{
	    	for(var i=0;i<changeStudent.length;i++){
	    		var student = changeStudent[i];
	    		
	    		if(studentId == changeStudent[i].id){
	    		  remove(changeStudent,i)
	    		}
	    	}
	    	console.log(changeStudent.length)
	   }
	   $(".zxf_hasChked").find(".num").text(changeStudent.length);
	   
	   
	   
    })
     
  </script>
</body>

</html>