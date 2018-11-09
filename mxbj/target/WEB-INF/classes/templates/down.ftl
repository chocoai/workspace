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
$(function(){
	
	$(function(){
        $("#d_warp").swipe( {
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
        swipeLeft:function(event, direction, distance, duration, fingerCount) {
            if(distance>80){
                         window.location.href='http://zhkt.huijiaoyun.com/wap/wkt.html';
             }
   		 },
        threshold:0,
        fingers:'all'
      });
})
})

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
	var u = navigator.userAgent, app = navigator.appVersion;
	var browser = {
		versions : function() {
			return {
				trident : u.indexOf('Trident') > -1, //IE内核
				presto : u.indexOf('Presto') > -1, //opera内核
				webKit : u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
				gecko : u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
				mobile : !!u.match(/AppleWebKit.*Mobile.*/)
						|| !!u.match(/AppleWebKit/), //是否为移动终端
				ios : !!u.match(/(i[^;]+;(U;)? CPU.+Mac OS X)/), //ios终端
				android : u.indexOf('Android') > -1
						|| u.indexOf('Linux') > -1, //android终端或者uc浏览器
				iPhone : u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
				iPad : u.indexOf('iPad') > -1, //是否iPad
				webApp : u.indexOf('Safari') == -1
			//是否web应该程序，没有头部与底部
			};
		}(),
		language : (navigator.browserLanguage || navigator.language)
				.toLowerCase()
	}

	var headCode = "";
	var terminalType = "1";
	
	if ('' != '') {
		headCode = '';
	}

	
	if (browser.versions.ios) {
		terminalType = '2';
	} else if (browser.versions.android) {
		terminalType = '1';
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