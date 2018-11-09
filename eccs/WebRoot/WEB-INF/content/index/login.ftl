<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">
<META http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="GENERATOR" content="MSHTML 11.00.9600.17496">
<TITLE>工程咨询云工作平台登陆界面</TITLE>

<link rel="stylesheet" href="css/main.css" />
<SCRIPT src="js/jquery.js" type="text/javascript"></SCRIPT>
<SCRIPT src="js/svgloader.js" type="text/javascript"></SCRIPT>

<style>
        .animated {
            -webkit-animation-duration: .5s;
            -moz-animation-duration: .5s;
            -o-animation-duration: .5s;
            animation-duration: .5s;
            -webkit-animation-fill-mode: both;
            -moz-animation-fill-mode: both;
            -o-animation-fill-mode: both;
            animation-fill-mode: both
        }
        .container {
            display: none;
        }
        .container.show {
            display: block;
        }
        /* 覆盖页面 */
        .pageload-overlay {
            position: fixed;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            visibility: hidden;
        }
        .pageload-overlay.show {
            visibility: visible;
        }
        .pageload-overlay svg {
            position: absolute;
            top: 0;
            left: 0;
            pointer-events: none;
        }
        .pageload-overlay svg path {
        	fill: #fff;
            filter:alpha(opacity=50);  
	      	-moz-opacity:0.5;  
	      	-khtml-opacity: 0.5;  
	      	opacity: 0.5;
        }
        .pageload-overlay::after,
        .pageload-overlay::before {
            content: '';
            position: fixed;
            width: 20px;
            height: 20px;
            top: 50%;
            left: 50%;
            margin: -10px 0 0 -10px;
            border-radius: 50%;
            visibility: hidden;
            opacity: 0;
            z-index: 1000;
            -webkit-transition: opacity 0.15s, visibility 0s 0.15s;
            transition: opacity 0.15s, visibility 0s 0.15s;
        }
        .pageload-overlay::after {
            background: #6cc88a;
            -webkit-transform: translateX(-20px);
            transform: translateX(-20px);
            -webkit-animation: moveRight 0.6s linear infinite alternate;
            animation: moveRight 0.6s linear infinite alternate;
        }
        .pageload-overlay::before {
            background: #4fc3f7;
            -webkit-transform: translateX(20px);
            transform: translateX(20px);
            -webkit-animation: moveLeft 0.6s linear infinite alternate;
            animation: moveLeft 0.6s linear infinite alternate;
        }
        @-webkit-keyframes moveRight {
            to {
                -webkit-transform: translateX(20px);
            }
        }
        @keyframes moveRight {
            to {
                transform: translateX(20px);
            }
        }
        @-webkit-keyframes moveLeft {
            to {
                -webkit-transform: translateX(-20px);
            }
        }
        @keyframes moveLeft {
            to {
                transform: translateX(-20px);
            }
        }
        .pageload-loading.pageload-overlay::after,
        .pageload-loading.pageload-overlay::before {
            opacity: 1;
            visibility: visible;
            -webkit-transition: opacity 0.3s;
            transition: opacity 0.3s;
        }
        .copyright p {
            line-height: 1.2em;
        }
</style>

<SCRIPT type="text/javascript">
	$(function() {
		//得到焦点
		$("#password").focus(function() {
			$("#left_hand").animate({
				left : "150",
				top : " -38"
			}, {
				step : function() {
					if (parseInt($("#left_hand").css("left")) > 140) {
						$("#left_hand").attr("class", "left_hand");
					}
				}
			}, 2000);
			$("#right_hand").animate({
				right : "-64",
				top : "-38px"
			}, {
				step : function() {
					if (parseInt($("#right_hand").css("right")) > -70) {
						$("#right_hand").attr("class", "right_hand");
					}
				}
			}, 2000);
		});
		//失去焦点
		$("#password").blur(function() {
			$("#left_hand").attr("class", "initial_left_hand");
			$("#left_hand").attr("style", "left:100px;top:-12px;");
			$("#right_hand").attr("class", "initial_right_hand");
			$("#right_hand").attr("style", "right:-112px;top:-12px");
		});
	});
</SCRIPT>


</HEAD>


<BODY
	style=" width:100%; height:100%; background:url(/images/load.jpg) no-repeat top center;">
	
	<div id="loader" style="z-index:99999;" class="pageload-overlay" data-opening="M 40 -21.875 C 11.356078 -21.875 -11.875 1.3560784 -11.875 30 C -11.875 58.643922 11.356078 81.875 40 81.875 C 68.643922 81.875 91.875 58.643922 91.875 30 C 91.875 1.3560784 68.643922 -21.875 40 -21.875 Z">
        
       <svg xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" viewBox="0 0 80 60" preserveAspectRatio="xMidYMid slice">
            <path d="M40,30 c 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 0,0 Z" />
       </svg>
        
    </div>
	
	<DIV class="top_div">
		<div style="text-align:center; height:100px; padding-top:80px;">
			<img src="/images/load_title.png" width="567" height="80" />
		</div>
	</DIV>
	
	<DIV
		style="background: rgb(255, 255, 255); margin: -100px auto auto; border: 1px solid rgb(231, 231, 231); border-image: none; width: 400px; height: 200px; text-align: center;">
		<DIV style="width: 165px; height: 96px; position: absolute;">
			<DIV class="tou"></DIV>
			<DIV class="initial_left_hand" id="left_hand"></DIV>
			<DIV class="initial_right_hand" id="right_hand"></DIV>
		</DIV>
		<P style="padding: 30px 0px 10px; position: relative;">
			<SPAN class="u_logo"></SPAN> 
			<INPUT id="username" name="username" class="ipt" type="text" placeholder="请输入用户名" value="">
		</P>
		<P style="position: relative;">
			<SPAN class="p_logo"></SPAN> 
			<INPUT id="password" name="password" class="ipt" type="password" placeholder="请输入密码" value="">
		</P>
		<P style="position: relative;">
					<h5 style="float: left;margin: 10px 30px;text-align: center;font-size: 12px;    color: #008ead;">(本系统建议使用主流浏览器进行操作。如：IE，火狐，谷歌等)</h5>
		</P>
		<DIV style="height: 50px; line-height: 50px; margin-top: 30px; border-top-color: rgb(200, 200, 200); border-top-width: 1px; border-top-style: solid;">
			<P style="margin: 0px 35px 20px 45px;">
				<span style="float: left;">
					<!-- <a style="color: rgb(115, 115, 115);" href="#">忘记密码?</a>-->
				</span> 
				<span style="float: right;">
					<a style="background: rgb(0, 142, 173); padding: 7px 10px; border-radius: 4px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;"
					href="javascript:login()">登录</a>
				</span>
			</P>
			
		</DIV>
	</DIV>
	
	<SCRIPT>
	
	loader = new SVGLoader( document.getElementById( 'loader' ), { speedIn : 300, easingIn : mina.easeinout } );
	
	document.onkeydown=function(event){ 
		e = event ? event :(window.event ? window.event : null); 
		if(e.keyCode==13){   //回车
			login();
		} 
	}
	 
	function login() {
			var username = encodeURIComponent($('#username').val());
			var password = encodeURIComponent($('#password').val());

			if (username == '') {
				alert('请填写用户名');
				return;
			}
			if (password == '') {
				alert('请填写密码');
				return;
			}
			
			loader.show(function(){ 
	            //动画加载完成后的代码 
	        })
			
			$.ajax({
		    	type:"post",
		    	url:'/checkUser.htm?username=' + username + "&password=" + password,
		    	dataType:'text',
		    	success:function(d){
		    		loader.hide();
		    		if (d == '1') {
		    			location.href = '/workbench.htm';
		    		} else {
		    			alert('用户名或密码错误');
		    		}
		    	}
		    })				
	}
	
	</SCRIPT>
	
</BODY>
</HTML>
