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
      <a class="icon icon-left fl" href="${base}/page/manager/plate/edit.html?userId=${userId}&plateId=${plateId}"></a>
      <h1 class="title">添加老师</h1>
    </header>
    <div class="zxf_creadHead f085 c_norma bb search fixed">
      <div class="zxf_search_box pd1 clearfix bgfff">
        <div class="zxf_search w65 fl">
          <label class="icon_search" for="search"></label>
          <input type="search" id="search" placeholder="请输入老师姓名">
        </div>
        <a href="javascript:;" class="zxf_bg_blueBtn zxf_add_studentBtn fr mgr075 mgt01">搜索</a>
      </div>
    </div>
    <div class="content bgfff mgt05 pdb32">
      <div class="zxf_creatClass_wp">
        <div class="zxf_creatClass_item pdlr075">
          <div class="zxf_creatCon pdtb075 c_9f9f9f f085">
            <div class="clearfix mgtb05">
              <span class="zxf_hasChked">已选<em class="num"></em>人</span>
            </div>
            <div class="zxf_add_studentBox pdtb05">
              <ul class="clearfix">
                
               	<#list allUserList as teacher>
                     <li class="t_c f085 c_normal">
	                  <a href="javascript:;" class="icon_chked_blue"></a>
	                  <img src="<#if teacher.logoUrl??>${teacher.logoUrl}<#else>http://www.huijiaoyun.com/uploads/avatar/data/41/45/191ac780686340f59d9f61dad257aa27_90x90.jpg</#if>" alt="">
	                  <p class="txt_ellipsis w90 mgt02" id="${teacher.personId}" platformCode="${teacher.platformCode}">${teacher.name}</p>
	                </li>       
                             
                </#list>
              </ul>
              <a href="javascropt:;" class="zxf_add_stuBtn mgl075"></a>

            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="fixed t_c zxf_nav_bottom bt pdtb06 bgfff">
      <ul class="clearfix">
        <li class="fl w50"><a href="javascript:;" class="zxf_bg_grayBtn_normal cancel" >取消</a></li>
        <li class="fl w50"><a href="javascript:;" class="zxf_bg_blueBtn_normal add" >添加</a></li>
      </ul>
    </div>
    
   <input type="hidden" name="plateId" id="plateId" value="${plateId}">
   <input type="hidden" name="userId" id="userId" value="${userId}">
    
    <script type="text/javascript" src="${base}/js/json2.js"></script>
    <script type="text/javascript" src="${base}/js/manager/plate/editTeacher.js"></script>
    <script type="text/javascript" src="${base}/js/fastclick.js"></script>
    <script>
    $(function() {    
	    FastClick.attach(document.body);    
	}); 
    	appGoback = function(){
	 		window.location.href="${base}/page/manager/plate/edit.html?userId=${userId}&plateId=${plateId}";
		}

      $(document).on('touchend', '.cancel', function () {
       	location.href = "${base}/page/manager/plate/edit.html?userId=${userId}&plateId=${plateId}";
      });
    	
    
    	$(document).ready(function(){ 
    		var checklength=0;
    	
    		$('.zxf_add_studentBox').find('li').each(function() {
                
                <#if (plateUserList?size>0) >
	                <#list plateUserList as user>
						var userId = '${user.personId}';
						var userName = '${user.name}';
						var userLogo = '${user.logoUrl}';
						var platformCode = '${user.platformCode}';
						var teacherId = $(this).find(".txt_ellipsis").attr("id");
						
						console.log(userId+":"+teacherId)
						
						if(userId == teacherId){
							$(this).addClass("chked");
							
							checklength=checklength + 1;
							
							var student = new Student(userId,userName,userLogo,platformCode);
					    	student.id = userId;
					    	student.name = userName;	
					    	student.logoUrl = userLogo;
					    	student.platformCode = platformCode;
					        changeStudent.push(student);
						}
						
					</#list>
				</#if>
                
            })
            $(".zxf_hasChked").find(".num").text(checklength);
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
        e.stopPropagation();
        $(this).parents(".zxf_seleBox").find(".zxf_sele_dropWp").hide();
        $(this).parents(".zxf_seleBox").find(".zxf_seleValue").text($(this).text()).removeClass("on");
      });
      // 选中
      $(".zxf_add_studentBox ").on("click", "ul>li", function () {
        $(this).toggleClass("chked");
        var $len = $(".zxf_add_studentBox ul li.chked").length;
        $(".zxf_hasChked").find(".num").text($len);
        
        
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
	    	student.platformCode = platformCode;
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
        
        
      })
    </script>
  </div>
</body>

</html>