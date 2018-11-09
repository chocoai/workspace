$('.chklist2').hcheckbox2();
$('.radiolist').hradio2();

var shutdown = 1
var restart = 0;

$('#shutdown').on('click', function() { // 带取消，确定按钮的
	$('#shutdown').removeClass("ni_res ni_off");
	$('#restsrt').removeClass("ni_res ni_off");

	$('#shutdown').addClass("ni_off");
	$('#restsrt').addClass("ni_res");
	shutdown = 1;
	restart = 0;
});

$('#restsrt').on('click', function() { // 带取消，确定按钮的
	$('#shutdown').removeClass("ni_res ni_off");
	$('#restsrt').removeClass("ni_res ni_off");

	$('#shutdown').addClass("ni_res");
	$('#restsrt').addClass("ni_off");
	shutdown = 0;
	restart = 1;
});

function getCheckValue() {
	var deviceCode = "";
	$.each($("input[name='chkItm']"), function(i, ckItm) {
		if (ckItm.checked) {
			deviceCode += "," + ckItm.value;
		}
	});
	if (deviceCode != "") {
		return deviceCode.substring(1);
	}
	return "";
}

function appMonitor(deviceCode) {
	$.ajax({
		type : "post",
		async : true,
		url : "deviceAppTop",
		data : {
			deviceCode : deviceCode
		},
		dataType : "json",
		success : function(result) {
			$(".ni_g_black_a").html();
			if (result != null) {
				var list = result.list;
				var htmlStr = '';
				for (var i = 0; i < list.length; i++) {
					var appName = cutstr(list[i].app_name, 10);
					htmlStr += '<a href="#" title="' + list[i].app_name + '">'
							+ appName + '</a>';
					console.log(htmlStr)
				}
				console.log(htmlStr)
				$(".ni_g_black_a").html(htmlStr);
			}

		},
		error : function(errorMsg) {
		}
	})

	var dialog = art.dialog({
		title : '应用监控',
		content : $(".ni_g_pop_app_jk")[0],
		width : '500px',
		ok : true,
		padding : "20px",
		cancelVal : '关闭',
		cancel : true,
		maskClick : true
	// 判断当有背景的时候，点背景是否关闭弹出框，默认为false，点击关闭，如果不需要点击关闭则加上maskClick:true这一配置
	});
}

function screenMonitor(deviceCode,name,classRoomName) {
	$(".ni_g_pop_screenMonitor_udp").html();
	
	var img = '<img width="100px" style="margin-top:100px;margin-left:400px" height="100px" src="../../images/timg.gif"/>';
	
	$(".ni_g_pop_screenMonitor_udp").html(img);
	
	$.ajax({
		type : "post",
		async : true,
		url : "screenMonitor",
		data : {
			deviceCode : deviceCode
		},
		dataType : "json",
		success : function(result) {
			
			var img ='';
			console.log(result.resultMap.path)
			if (result != null) {
				img = '<img width="1000px" height="500px" src="' + result.resultMap.path + '"/>';
			}
			$(".ni_g_pop_screenMonitor_udp").html(img);
		},
		error : function(errorMsg) {
		}
	})

	var dialog = art.dialog({
		title : '屏幕监控 '+classRoomName+'-['+name+']',
		content : $(".ni_g_pop_screenMonitor_udp")[0],
		width : '900px',
	    height : '500px',
        padding:0,
		cancelVal : '关闭',
		cancel : true,
		maskClick : true
	});

}

$('.ni_fun_pop_zdjk').on('click', function() { // 带取消，确定按钮的
	var dialog = art.dialog({
		title : '终端管控',
		content : $(".ni_g_pop_zdgk")[0],
		width : '500px',
		ok : function() {
			var url;
			if (shutdown == 1) {
				url = "batchShutdown";
			} else {
				url = "batchRestart";
			}
			var deviceCodes = getCheckValue();
			$.ajax({
				type : "post",
				async : true,
				url : url,
				data : {
					deviceCodes : deviceCodes
				},
				dataType : "json",
				success : function(result) {
					this.close();
				},
				error : function(errorMsg) {

				}
			})
		},
		padding : 0,
		cancelVal : '关闭',
		cancel : true,
		maskClick : true
	// 判断当有背景的时候，点背景是否关闭弹出框，默认为false，点击关闭，如果不需要点击关闭则加上maskClick:true这一配置
	});
});

function cutstr(str, len) {
	var str_length = 0;
	var str_len = 0;
	str_cut = new String();
	str_len = str.length;
	for (var i = 0; i < str_len; i++) {
		a = str.charAt(i);
		str_length++;
		if (escape(a).length > 4) {
			// 中文字符的长度经编码之后大于4
			str_length++;
		}
		str_cut = str_cut.concat(a);
		if (str_length >= len) {
			str_cut = str_cut.concat("...");
			return str_cut;
		}
	}
	// 如果给定字符串小于指定长度，则返回源字符串；
	if (str_length < len) {
		return str;
	}
}

$('.ni_fun_pop_wzgb').on('click', function() { // 带取消，确定按钮的
	var dialog = art.dialog({
		title : '文本消息广播',
		content : $(".ni_g_pop_txt_udp")[0],
		width : '500px',
		ok : function() {
			var sendText = $("#sendMessage").val();
			var delayTime = $("#delayTime").val();

			var deviceCodes = getCheckValue();

			console.log(sendText);
			console.log(delayTime);
			$.ajax({
				type : "post",
				async : true,
				url : "batchSendMessage",
				data : {
					sendText : sendText,
					delayTime : delayTime,
					deviceCodes : deviceCodes
				},
				dataType : "json",
				success : function(result) {
					this.close();
				},
				error : function(errorMsg) {

				}
			})
		},
		padding : 0,
		cancelVal : '关闭',
		cancel : true,
		maskClick : true
	});
});
