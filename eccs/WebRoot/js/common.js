/** 浏览器判断 **/
function isIE() {
	return /msie/.test(navigator.userAgent.toLowerCase());
}

function isFF() {
	return /firefox/.test(navigator.userAgent.toLowerCase());
}

function isOpera() {
	return /opera/.test(navigator.userAgent.toLowerCase());
}

function isWebkit() {
	return /webkit/.test(navigator.userAgent.toLowerCase());
}

/** 去前后空格及回车 **/
String.prototype.trim = function() {
	return this.replace(/[ \r\n]*/g, "");
}

/** 去掉html标签 **/
String.prototype.htm2Txt = function() {
	return this.replace(/<[^>].*?>/g,"");
}

/** 获得字符数，全角算2个字符 **/
String.prototype.getBytes = function() {       
	var cArr = this.match(/[^\x00-\xff]/ig);       
	return this.length + (cArr == null ? 0 : cArr.length);        
} 

/** endWith **/
String.prototype.endWith = function(str) {
	if(str==null||str==""||this.length==0||str.length>this.length)
		return false;
	if(this.substring(this.length-str.length)==str)
  		return true;
	else
		return false;
	return true;
}

/** startWith **/
String.prototype.startWith = function(str) {
	if(str==null||str==""||this.length==0||str.length>this.length)
		return false;
	if(this.substr(0,str.length)==str)
		return true;
	else
		return false;
	return true;
}

/** replaceAll替换 **/
String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {   
	if (!RegExp.prototype.isPrototypeOf(reallyDo)) {   
		return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);   
	} else {   
		return this.replace(reallyDo, replaceWith);   
	}   
}   

/** 获取随机数 num位数 **/
function getRandom(num) {
	if (num == null || num == 0) {
		num = 4;
	}
	var result = "";
	for (i = 0; i < num; i++) {
		result += parseInt(10 * Math.random());
	}
	return result;
}

/** 格式化文件大小 **/
function getFmtDataSize(dataSize) {
	var byteSize = Math.round(dataSize / 1024 * 100) * .01;
	var suffix = 'KB';
	if (byteSize > 1000) {
		byteSize = Math.round(byteSize / 1024 * 100) * .01;
		suffix = 'MB';
	}
	return byteSize + suffix;
}

/** 计算2个时间的差(毫秒) d2 > d1 **/
function optTime(d1, d2)   { 
	//var d1 = "2007-04-04 12:12:12"; 
	//var d2 = "2007-04-04 12:13:13"; 
	//diff为d1-d2的毫秒差 
	var diff = new Date(d2.replace(/\-/g, "/ ")).valueOf() - new Date(d1.replace(/\-/g, "/ ")).valueOf(); 
	return diff;
}

/** 得到当前时间 格式 yyyy-MM-dd HH:mm:ss **/
function getNowTime() {
	var today = new Date();
	var year = today.getYear();
	if (navigator.userAgent.indexOf("MSIE") <= 0) {
		year += 1900;
	}
	var month = today.getMonth() + 1;
	if (month < 10) {
		month = '0' + month;
	}
	var day = today.getDate();
	if (day < 10) {
		day = '0' + day;
	}
	var hour = today.getHours();
	if (hour < 10) {
		hour = "0" + hour;
	}
	var min = today.getMinutes();
	if (min < 10) {
		min = "0" + min;
	}
	var second = today.getSeconds();
	if(second < 10){
		second = "0" + second;
	}
	var date = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + second;
	return date;
}

/** 获取当前日期 **/
function getNowDate() {
	var time = new Date();
	var year = time.getYear(); // 年
	if (navigator.userAgent.indexOf("MSIE") <= 0) {
		year += 1900;
	}
	var month = time.getMonth() + 1; // 月
	var date = time.getDate(); // 日
	var hour = time.getHours(); // 时
	var min = time.getMinutes(); // 分
	var sec = time.getSeconds(); // 秒
	var day = time.getDay(); // 星期几

	var dayNow = "";
	switch (day) {
	case 0:
		dayNow = "日";
		break;
	case 1:
		dayNow = "一";
		break;
	case 2:
		dayNow = "二";
		break;
	case 3:
		dayNow = "三";
		break;
	case 4:
		dayNow = "四";
		break;
	case 5:
		dayNow = "五";
		break;
	case 6:
		dayNow = "六";
		break;
	}

	// 时间格式修正 当分、秒小于10时 前面自动补0
	if (min < 10) {
		min = "0" + min;
	}
	if (sec < 10) {
		sec = "0" + sec;
	}
	return year + "年" + month + "月" + date + "日 " + " 星期" + dayNow;
}

/**
 * 格式化数字显示方式 用法 formatNumber(12345.999,'#,##0.00');
 * formatNumber(12345.999,'#,##0.##'); formatNumber(123,'000000');
 * 
 * @param num
 * @param pattern
 */
function formatNumber(num, pattern) {
	var strarr = num ? num.toString().split('.') : [ '0' ];
	var fmtarr = pattern ? pattern.split('.') : [ '' ];
	var retstr = '';

	// 整数部分
	var str = strarr[0];
	var fmt = fmtarr[0];
	var i = str.length - 1;
	var comma = false;
	for ( var f = fmt.length - 1; f >= 0; f--) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i >= 0)
				retstr = str.substr(i--, 1) + retstr;
			break;
		case '0':
			if (i >= 0)
				retstr = str.substr(i--, 1) + retstr;
			else
				retstr = '0' + retstr;
			break;
		case ',':
			comma = true;
			retstr = ',' + retstr;
			break;
		}
	}
	if (i >= 0) {
		if (comma) {
			var l = str.length;
			for (; i >= 0; i--) {
				retstr = str.substr(i, 1) + retstr;
				if (i > 0 && ((l - i) % 3) == 0)
					retstr = ',' + retstr;
			}
		} else
			retstr = str.substr(0, i + 1) + retstr;
	}

	retstr = retstr + '.';
	// 处理小数部分
	str = strarr.length > 1 ? strarr[1] : '';
	fmt = fmtarr.length > 1 ? fmtarr[1] : '';
	i = 0;
	for ( var f = 0; f < fmt.length; f++) {
		switch (fmt.substr(f, 1)) {
		case '#':
			if (i < str.length)
				retstr += str.substr(i++, 1);
			break;
		case '0':
			if (i < str.length)
				retstr += str.substr(i++, 1);
			else
				retstr += '0';
			break;
		}
	}
	return retstr.replace(/^,+/, '').replace(/\.$/, '');
}

//==================================================================================================
/** 替换标签 **/
var facePath = '/js/face/arclist/';
function replace_em(str){
	str = str.replace(/\</g,'&lt;');
	str = str.replace(/\>/g,'&gt;');
	str = str.replace(/\n/g,'<br/>');
	str = str.replace(/\[em_([0-9]*)\]/g, "<img src='" + facePath + "$1.gif' border='0' />");
	return str;
}

/**
 * 评价，赞 or 黑
 * @param name
 * @param id
 * @param type 1,2
 * @return
 */
function addEval(name, id, type) {
	$.ajax({
		type:"post",
		url:"/eval?id=" + id + "&name=" + name + "&type=" + type,
		asyn:true,		//false为同步提交
		dataType:'text',	//返回文本
		success:function(d){
			if (d == '1') {
				if (type == '1') {
					var zanCount = $('.zanCount_' + id).html();
					$('.zanCount_' + id).html(parseInt(zanCount) + 1);
				} else if (type == '2') {
					var heiCount = $('.heiCount_' + id).html();
					$('.heiCount_' + id).html(parseInt(heiCount) + 1);
				}
			} else if(d == '0') {
				if (type == '1') {
					alert('赞不动了...');
				} else if (type == '2') {
					alert('黑不动了...');
				}
			}
			
		}
	});
}


