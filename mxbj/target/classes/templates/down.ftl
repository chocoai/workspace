<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
<title>墨香笔记</title>
<link rel="stylesheet" href="css/index/index.css"/>
<script src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/common/js/jquery.js" type="text/javascript"></script>
<script src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/common/js/jquery.js" type="text/javascript"></script>
<script type="text/javascript" src="http://www.163css.com/myweb/hihilinxuan/template/hihilinxuan/cssjs/2012/12/ifengtouch/js/jquery.min.js"></script>
<script type="text/javascript" src="http://www.163css.com/myweb/hihilinxuan/template/hihilinxuan/cssjs/2012/12/ifengtouch/js/jquery.touchSwipe.min.js"></script>
<script type="text/javascript" src="js/mobile-detect.js"></script>

</head>
<body>
<div class='d_warp' id="d_warp">
        <img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/download/images/logo.png" alt="" class='logo'/>
        <div class='d_content'>
            <i class='d_c_pic1'><img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/download/images/1.png" alt=""/></i>
            <p class='c_button'><a href="#"  onclick="downSoftwore();"></a></p>
            <span class='d_c_pic2'><img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/download/images/2.png" alt=""/></span>
            <em><img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/download/images/3.png" alt=""/></em>
            <strong><img src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/touch/download/images/4.png" alt=""/></strong>
        </div>
        <div class='d_footer'>
            <i class='scan'></i>
            <i class='connection'></i>
        </div>
        
       <div class='moban dis_none'>
        	<img src="images/index/moban.png" alt=""/>
    	</div>
</div>
    
<script type="text/javascript">
//$(function(){
	
	//$(function(){
    //    $("#d_warp").swipe( {
        //swipe:function(event, direction, distance, duration, fingerCount, fingerData) {
         //       if(direction =='left'){
         //               if(distance>80){
         //                       window.location.href='http://zhkt.huijiaoyun.com/wap/wkt.html';
         //               }
         //       }
         //       if(direction =='right'){
        //                if(distance>80){
        //                        window.location.href='http://zhkt.huijiaoyun.com/wap/hdkt.html';
        //                }
       //         }
        //},
        //swipeLeft:function(event, direction, distance, duration, fingerCount) {
        //    if(distance>80){
        //                 window.location.href='http://zhkt.huijiaoyun.com/wap/wkt.html';
        //     }
   		// },
       // threshold:0,
       // fingers:'all'
    //  });
//})
//})

$(".c_button a").on('touchend',function () {
	var ua = window.navigator.userAgent.toLowerCase();
    if(ua.match(/MicroMessenger/i) == 'micromessenger'){
    	var moban=$('.moban');
        moban.removeClass('dis_none');
        moban.bind("touchmove",function(e){
            e.preventDefault();
        });
        moban.on('touchend',function () {
            moban.addClass('dis_none');
        })
    }
})

function check() {
		var md = new MobileDetect(window.navigator.userAgent);
	    var html = '';
	    var os = md.os();
	    var terminalType = "1";
	    
	    if(os.indexOf("Android") != -1){
	    	terminalType="1";
	    	var isMobile = md.mobile();
	    	if(isMobile.indexOf("Tablet") != -1){
	    		terminalType="3";
	    	}
	    }else if(os.indexOf("iOS") !=-1){
	    	terminalType="2";
	    	
	    	var isMobile = md.mobile();
	    	
	    	if(isMobile.indexOf("iPad") != -1){
	    		terminalType="4";
	    	}
	    }
	    
	    
	var webroot = document.location.pathname;
	var rootpath = webroot.substring(0, webroot.substr(1).indexOf('/') + 1);
 
  	$.ajax({
		//url : 'http://mxbj.huijiaoyun.com/mxbj/api/appDownload/download',
		url : rootpath + '/api/appDownload/download',
		type : "GET",
		cache : false,
		async : false,
		dataType : "text",
		data : {
			"terminalType" : terminalType
		},
		success : function(data) {
			if (data != "") {
				window.location.href = data;
			}
		}
	});    
} 


function downSoftwore(){
	check();
}


























</script>
    
</body>
</html>