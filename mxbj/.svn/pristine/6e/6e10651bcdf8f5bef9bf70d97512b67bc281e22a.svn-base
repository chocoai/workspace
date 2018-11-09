<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"/>
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"/>
<title>墨香笔记</title>
<link rel="stylesheet" href="css/index/index.css"/>
<script src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/common/js/jquery.js" type="text/javascript"></script>

<script type="text/javascript" src="http://www.163css.com/myweb/hihilinxuan/template/hihilinxuan/cssjs/2012/12/ifengtouch/js/jquery.min.js"></script>
<script type="text/javascript" src="http://www.163css.com/myweb/hihilinxuan/template/hihilinxuan/cssjs/2012/12/ifengtouch/js/jquery.touchSwipe.min.js"></script>
<script type="text/javascript" src="js/mobile-detect.js"></script>

</head>
<body>
<a href="#"  onclick="downSoftwore();">下载</a>
 <div></div>
 <span id="bb"></span>
         
    
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
	    
	    html += md.mobile()+"<p>";
	    html += md.phone()+"<p>";
	    html += md.tablet()+"<p>";
	    html += md.userAgent()+"<p>";
	    html += md.os()+"<p>";
	    html += md.is('iPhone')+"<p>";
	    html += md.is('bot')+"<p>";
	    html += md.version('Webkit') +"<p>";
	    html += md.match('playstation|xbox')+"<p>";
	    
	    $("#bb").html(html);
	
	  
	    
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
	    
	  
	    alert(terminalType);
	
	    
	   
		
} 


function downSoftwore(){
	check();
}


























</script>
    
</body>
</html>