//判断后台返回null时做空串处理
function isStringNUll(obj) {
	if (obj == null) {
		return "";
	} else {
		return obj;
	}
}
//判断后台返回null时返回0
function isStrNUll(obj) {
	if (obj == null) {
		return "0";
	} else {
		return obj;
	}
}
//判断后台返回null时返回0.00
function isStrNUll0(obj) {
	if (obj == null||obj =='') {
		return "0.00";
	} else {
		return obj;
	}
}
/*用js限制字数，超出部分以省略号...显示*/
function LimitNumber(txt,num) {
    if (txt == null) {
    	return "";
    }else if(txt.length <= num){
    	return txt;
    }else{
    	return txt.substr(0,num) + '...' ;
    }
    
}

// 非空验证
function isNull(input) {
	if (input != "") {
		return true;
	} else {
		return false;
	}
}

// 验证数字(double类型) [可以包含小数点]
function isNumber(input) {
	var regex = /^\d+$|^(\d+)(\.\d+)?$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// 验证正整数
function IsIntegerPositive(input) {
	var regex = /^[1-9]*[1-9][0-9]*$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}
// 验证正整数 + 0
function isNum(input) {
	var regex = /^([1-9]\d*|0)$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// 蒋健范围的特殊格式，如1.25.52
function IsFormatTime(input) {
	var regex = /^[1-9]\d*\.\d*\.\d*|[1-9]\d*\.\d*|0\.\d*[1-9]\d*$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}
// 验证正整数和小数
function IsIntegerDecimal(input) {
	var regex = /^\d+(\.\d+)?$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// 验证小数
function IsDecimal(input) {
	var regex = /^([-+]?[1-9]\d*\.\d+|-?0\.\d*[1-9]\d*)$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// 验证只包含英文字母
function IsEnglishCharacter(input) {
	var regex = /^[A-Za-z]+$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// 验证只包含数字和英文字母
function IsIntegerAndEnglishCharacter(input) {
	var regex = /^[0-9A-Za-z]+$/;
	if (input.match(regex)) {
		return true;
	} else {
		return false;
	}
}

// 验证日期 [只能验证日期，不能验证时间]
function IsDateTime(input) {
	if (Date.parse(input)) {
		return true;
	} else {
		return false;
	}
}
/*
 * 身份证15位编码规则：dddddd yymmdd xx p dddddd：6位地区编码 yymmdd: 出生年(两位年)月日，如：910215 xx:
 * 顺序编码，系统产生，无法确定 p: 性别，奇数为男，偶数为女
 * 
 * 身份证18位编码规则：dddddd yyyymmdd xxx y dddddd：6位地区编码 yyyymmdd:
 * 出生年(四位年)月日，如：19910215 xxx：顺序编码，系统产生，无法确定，奇数为男，偶数为女 y: 校验码，该位数值可通过前17位计算获得
 */
function IsIDCard(input) {
	input = input.toUpperCase();
	// 验证身份证号码格式 [一代身份证号码为15位的数字；二代身份证号码为18位的数字或17位的数字加字母X]
	if ((/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/
			.test(input))) {
		return true;
	} else {
		return false;
	}
}

function IsPhone(input) {
	input = input.toUpperCase();
	// 验证电话号码格式 [手机号：1开头，第二位可能是区号+座机号码+分机号码]
	if ((/^1\d{10}$/.test(input))
			|| (/^(0[0-9]{2,3}-)?([2-9][0-9]{6,7})+(-[0-9]{1,4})?$/.test(input))) {
		return true;
	} else {
		return false;
	}
}

/*
 * 整数不转换 小数保留n位小数
 * 
 */
function fomatFloat(src, n) {
	return Math.round(src * Math.pow(10, n)) / Math.pow(10, n);
}

// 功能：将浮点数四舍五入，取小数点后2位
function toDecimal(x) {
	var f = parseFloat(x);
	if (isNaN(f)) {
		return;
	}
	f = Math.round(x * 100) / 100;
	return f;
}
// 制保留2位小数，如：2，会在2后面补上00.即2.00

function toDecimal2(x) {
	var f = parseFloat(x);
	if (isNaN(f)) {
		return false;
	}
	var f = Math.round(x * 100) / 100;
	var s = f.toString();
	var rs = s.indexOf('.');
	if (rs < 0) {
		rs = s.length;
		s += '.';
	}
	while (s.length <= rs + 2) {
		s += '0';
	}
	return s;
}
