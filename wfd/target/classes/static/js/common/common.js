function Student(id, name, logoUrl, platformCode) {
	this.id = id;
	this.name = name;
	this.logoUrl = logoUrl;
	this.platformCode = platformCode;
}

function Plate(plateId) {
	this.id = plateId;
}

function remove(array, index) {
	if (index <= (array.length - 1)) {
		for (var i = index; i < array.length; i++) {
			array[i] = array[i + 1];
		}
	} else {
		throw new Error('超出最大索引！');
	}
	array.length = array.length - 1;
	return array;
}
function strlen(str) {
	var len = 0;
	for (var i = 0; i < str.length; i++) {
		var c = str.charCodeAt(i);
		// 单字节加1
		if ((c >= 0x0001 && c <= 0x007e) || (0xff60 <= c && c <= 0xff9f)) {
			len++;
		} else {
			len += 2;
		}
	}
	return len;
}
var jmz = {};
jmz.GetLength = function(str) {
	// /<summary>获得字符串实际长度，中文2，英文1</summary>
	// /<param name="str">要获得长度的字符串</param>
	var realLength = 0, len = str.length, charCode = -1;
	for (var i = 0; i < len; i++) {
		charCode = str.charCodeAt(i);
		if (charCode >= 0 && charCode <= 128)
			realLength += 1;
		else
			realLength += 2;
	}
	return realLength;

};

String.prototype.gblen = function() {  
	  var len = 0;  
	  for (var i=0; i<this.length; i++) {  
	    if (this.charCodeAt(i)>127 || this.charCodeAt(i)==94) {  
	       len += 2;  
	     } else {  
	       len ++;  
	     }  
	   }  
	  return len;  
	}

function getboeAbstractLength(str)
{
    var sTmpStr,sTmpChar;
    var sOriLenth=0;
    var sReLenth=0;
    
    sTmpStr = new String(str);
    sOriLenth = sTmpStr.length;
    
    for(var i=0; i < sOriLenth; i++)
    {
      sTmpChar = sTmpStr.charAt(i);
      if(escape(sTmpChar).length > 4)//汉字
      {
          sReLenth += 2;
      }
      else if(sTmpChar != '\r')//换行
      {
          sReLenth++;
      }
    }
    
    return sReLenth;   
}

function getByteLen(val) {
	var len = 0;
	for (var i=0; i<val.length; i++) {
        var c = val.charCodeAt(i);
        //单字节加1
        if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
            len++;
        }
        else {
            len+=2;
        }
    }
	return len;
}
String.prototype.gblen = function() {
	var len = 0;
	for (var i = 0; i < this.length; i++) {
		if (this.charCodeAt(i) > 127 || this.charCodeAt(i) == 94) {
			len += 2;
		} else {
			len++;
		}
	}
	return len;
}