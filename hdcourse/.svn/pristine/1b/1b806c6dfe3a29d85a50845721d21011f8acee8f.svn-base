
window.onload = function () {
	my.app.toLogin();
	my.app.toSidebar();
	my.app.toCheck();
	my.app.toFilter();
	my.app.toBtn();
	my.app.toDelete();
	my.app.toActive();
	my.app.toUser();
	my.app.toRun();
	my.app.toFileArea();
};


var my = {};

my.tools = {};

my.tools.getStyle = function (obj, attribute) {
	if(obj.currentStyle) {
		return obj.currentStyle[attribute];
	} else {
		return getComputedStyle(obj, false)[attribute];
	}
};

my.tools.getByClass = function (oParent, sClass) {
	var aEle = oParent.getElementsByTagName('*');
	var aResult = [];
	var re = new RegExp('\\b'+sClass+'\\b', 'i');
	
	for(var i = 0; i < aEle.length; i++) {
		if(re.test(aEle[i].className)) {
			aResult.push(aEle[i]);
		}
	}
	
	return aResult;
};

my.tools.addEvent = function(obj, ev, fn)
{
	if(obj.attachEvent)
	{
		obj.attachEvent('on'+ev, fn);
	}
	else
	{
		obj.addEventListener(ev, fn, false);
	}
}


my.ui = {};

my.ui.startMove = function (obj, json, fnEnd) {
	clearInterval(obj.timer);
	obj.timer = setInterval(function () {
		var bStop = true;
		
		for(var attr in json) {
			var cur = 0;
			
			if(attr == 'opacity') {
				cur = Math.round(parseFloat(my.tools.getStyle(obj, attr) * 100));
			} else {
				cur = parseInt(my.tools.getStyle(obj, attr));
			}
			
			var speed = (json[attr] - cur) / 6;
			speed = speed > 0 ? Math.ceil(speed) : Math.floor(speed);
			
			if(cur != json[attr]) {
				bStop = false;
			}
			
			if(attr == 'opacity') {
				obj.style.filter = 'alpha(opacity:' + (cur + speed) + ')';
				obj.style.opacity = (cur + speed) / 100;		
			} else {
				obj.style[attr] = cur + speed  + 'px';
			}
			
			}
		
			if(bStop) {
				clearInterval(obj.timer);
				
				if(fnEnd) {
					fnEnd();
				}
			
		}
	}, 30);
};

my.ui.textChange = function (obj, str) {
	obj.onfocus = function () {
		
		if(obj.value == str) {
			obj.value = '';			
		}
	};
	
	obj.onblur = function () {
		if(obj.value == '') {
			obj.value = str;
		}
	}
};

my.ui.checkBox = function (arr, num) {
	setTimeout(function () {
		var allcheckValue = arr[num].checked;
		arr[num].onclick = function () {
			if(this.checked == true) {
				for(var i = 0; i < arr.length; i++) {
					arr[i].checked = true;
				} 
			} else {
					for(var i = 0; i < arr.length; i++) {
						arr[i].checked = false;
					} 
				}
		};
		for(var i=1;i<arr.length;i++){
			arr[i].onclick = function () {
				var flag = true;
				for(var i=1;i<arr.length;i++){
					if(arr[i].checked==false){
						flag = false;
					}
				}
				arr[num].checked = flag;
			};
		}
	}, 10);

};


my.app = {}; 

my.app.toLogin = function () {
	if(document.getElementById('lfy_lcontent')) {
		var oContent = document.getElementById('lfy_lcontent');
		var aInput = oContent.getElementsByTagName('input');
		
		my.ui.textChange(aInput[0], '请输入您的用户名');
		
		aInput[1].onfocus = function () {
			this.style.display = 'none';
			aInput[2].style.display = 'block';
			aInput[2].focus();
		};
		
		aInput[2].onblur = function () {
			if(this.value == '') {
				this.style.display = 'none';
				aInput[1].style.display = 'block';
			}
		};
		
		
		
	}
};

my.app.toSidebar = function () {
	if(document.getElementById('lfy_sidebar')) {
		var oSidebar = document.getElementById('lfy_sidebar');
		var aBtn = oSidebar.getElementsByTagName('dt');
		var aWrap = my.tools.getByClass(oSidebar, 'wrap');
	
		for(var i = 1; i < aBtn.length; i++) {
			
			//使按钮文字不被选中
			aBtn[i].onselectstart =function () { return false; }; 
					
			aBtn[i].index = i;
			aBtn[i].onclick = function () {
				
				for(var i = 1; i < aBtn.length; i++) {
					var oIco = aBtn[i].getElementsByTagName('img')[0];
					oIco.style.display = 'none';
					aWrap[i].style.display = 'none';
				
				}
				
				var oIco = aBtn[this.index].getElementsByTagName('img')[0];
				aWrap[this.index].style.display = 'block';
				oIco.style.display = 'block';
	
			};
		}
	}
};

my.app.toCheck = function () {
	if(document.getElementById('lfy_checkbox')) {
		var oDiv = document.getElementById('lfy_checkbox');
		var aBox = oDiv.getElementsByTagName('input');
		my.ui.checkBox(aBox, 0);
		//my.ui.checkBox(aBox, aBox.length - 1);
	}
};

my.app.toFilter = function () {
	if(document.getElementById('lfy_share')) {
		var oDiv = document.getElementById('lfy_share');
		var aDl = oDiv.getElementsByTagName('dl');
		var aDD = new Array();
		
		for(var i = 1; i < aDl.length; i++) {
			aDD[i] = aDl[i].getElementsByTagName('dd');
			
			for(var j = 0; j < aDD[i].length; j++) {
				aDD[i][j].index = i;
				aDD[i][j].index2 = j;
				aDD[i][j].onclick = function () {
					for(var t = 0; t < aDD[this.index].length; t++) {
						aDD[this.index][t].className = '';
					}
					aDD[this.index][this.index2].className = 'active';
					
					var oDD = document.createElement('dd');
					oDD.innerHTML = aDD[this.index][this.index2].innerHTML;
					oDD.className = this.index;
					
					var aDD2 = aDl[0].getElementsByTagName('dd');
					
					for(var i = 0; i < aDD2.length; i++) {
						if(aDD2[i].className == this.index) {
							aDl[0].removeChild(aDD2[i]);
						}
					}
					
					if(!(oDD.innerHTML == '不限')) {
						aDl[0].appendChild(oDD);
					}
					
				};
			}
			
		}
	}
};

my.app.toActive = function () {
	if(document.getElementById('lfy_sidebar')) {
		var oDiv = document.getElementById('lfy_sidebar');
		var aA = oDiv.getElementsByTagName('a');
		
		for(var i = 0; i < aA.length; i++) {
			aA[i].index = i;	
			aA[i].onclick = function () {
				for(var i = 0; i < aA.length; i++) {
					aA[i].className = '';	
				}
				aA[this.index].className = 'active';
				
			};
		}
	}
};

my.app.toBtn = function () {
	if(document.getElementById('lfy_share')) {
		var aClose = my.tools.getByClass(document, 'pop_close');
		var aConfirm = my.tools.getByClass(document, 'confirm');
		var oPrompt = my.tools.getByClass(document, 'lfy_popPrompt')[0];
		var oResult = my.tools.getByClass(document, 'lfy_result')[0];
		var aAbolish = my.tools.getByClass(document, 'abolish');
		
		for(var i = 0; i < aClose.length; i++) {
			aClose[i].onclick = function () {
				this.parentNode.parentNode.parentNode.style.display = 'none';
			};
		}
		
		for(var i = 0; i < aConfirm.length; i++) {
			aConfirm[i].onclick = function (e) {
			var ste = e.srcElement.id;
			if(ste=='shutDown'){
			 var type=1
			}else{
			 var type=0
			}
			$.ajax({
					 type: "POST",
					   url: "{ctx}/manageUser/updateEduUserState.action",
					   data: {"isused":type,"person_id":$("#personId").val()},
					   async: false,
					   dataType:'json',
					   success: function(msg){
					   }
				});
				userEduList();
				reloadAgain();
				this.parentNode.parentNode.style.display = 'none';
				oResult.style.display = 'block';
				setTimeout(function () {
					oResult.style.display = 'none';
				}, 1000);
			};
		}
		
		for(var i = 0; i < aAbolish.length; i++) {
			aAbolish[i].onclick = function () {
				this.parentNode.parentNode.style.display = 'none';	
			};
		}
		
	}
};

my.app.toDelete = function () {
	if(document.getElementById('lfy_delete')) {
		var oTable = document.getElementById('lfy_delete');
		var aDelete = oTable.getElementsByTagName('a');
		var aInput =  oTable.getElementsByTagName('input');
		var oPrompt = my.tools.getByClass(document, 'lfy_popPrompt')[0];
		var oBatch = document.getElementById('lfy_batch');
		
		for(var i = 0; i < aDelete.length; i++) {
			aDelete[i].onclick = function () {
				oPrompt.style.display = 'block';
			};
		}
		
		
		oBatch.onclick = function () {
			for(var i = 0; i < aInput.length; i++) {
				if(aInput[i].checked == true) {
					oPrompt.style.display = 'block';
				}
			}
		};
	}
	
};

my.app.toUser = function () {
	if(document.getElementById('lfy_user')) {
		var oTable = document.getElementById('lfy_user');
		var aDisable = my.tools.getByClass(oTable, 'disable'); //禁用
		var aInput =  oTable.getElementsByTagName('input');
		var oPrompt = my.tools.getByClass(document, 'lfy_popPrompt')[0];
		var oPrompt2 = my.tools.getByClass(document, 'lfy_popPrompt')[1];
		var oBatch = document.getElementById('lfy_batch');
		var oStart = document.getElementById('lfy_start');
		var aView = my.tools.getByClass(oTable, 'viewBtn');
		var aDetails = my.tools.getByClass(document, 'lfy_popDeta')[0];

		var tag=new Array(aDisable.length);
		for(var i = 0; i < aDisable.length; i++) {
		
			aDisable[i].onclick = function (e) {
					var arry =userArrList[0];
					var ste = e.srcElement.id;
					var s = ste.substring(4);
					var obj = arry[s]
					$("#personId").val(obj.person_id); //给隐藏域赋值
				var s = $("#"+e.srcElement.id).text();
				if(s=='禁用'){
				oPrompt.style.display = 'block';
				}else{
				oPrompt2.style.display = 'block';
				}
			};
			
			aView[i].onclick = function (e) {
						$("#userDetail").html('');
						 var arry =userArrList[0];
						 var obj = arry[e.srcElement.id]
						 $("#personId").val(obj.person_id); //给隐藏域赋值
						 var str='';
								str+='<tr>';
			                    	str+='<td align="right">用户id：</td>';
			                    	str+='<td>'+obj.person_id+'</td>';
			                    	str+='<td align="right">学校：</td>';
			                        str+='<td>'+obj.org_name+'</td>';
		                    	str+='</tr>';
			                	str+='<tr>';
			                    	str+='<td align="right">用户名：</td>';
			                    	str+='<td>'+obj.account+'</td>';
			                    	str+='<td align="right">用户类型：</td>';
			                        str+='<td>老师</td>';
			                    str+='</tr>';
			                	str+='<tr>';
			                    	str+='<td align="right">姓名：</td>';
			                    	str+='<td>'+obj.user_name+'</td>';
			                    	str+='<td align="right">地区：</td>';
			                    	str+='<td>'+obj.area_code+'</td>';
			                    str+='</tr>';
			                	str+='<tr>';
			                    	str+='<td align="right">性别：</td>';
			                    	if(obj.gender==0){
			                    	str+='<td>女</td>';
			                    	}else{
			                    	str+='<td>男</td>';
			                    	}
			                    	str+='<td align="right">电子邮箱：</td>';
			                        str+='<td>'+obj.email+'</td>';
			                    str+='</tr>';
			                	str+='<tr>';
			                    	str+='<td align="right">生日：</td>';
			                    	str+='<td>'+obj.birthday+'</td>';
			                    	str+='<td align="right">手机号码：</td>';
			                        str+='<td>'+obj.phone_number+'</td>';
			                    str+='</tr>';
			                $("#userDetail").html(str);
			                
			                $("#loginDetail").html('');
			                var temp = '';
			               	temp+= '<tr>';
                    			temp+= '<td align="right">首次登陆时间：</td>';
                    			temp+= '<td>'+obj.create_time+'</td>';
                    		temp+= '</tr>';
		                	temp+= '<tr>';
			                    temp+= '<td align="right">最后登陆时间：</td>';
			                    temp+= '<td>'+obj.last_time+'</td>';
		                    temp+= '</tr>';
		                	temp+= '<tr>';
			                    temp+= '<td align="right">状态：</td>';
			                    if(obj.isused==0){
			                    temp+= '<td>正常</td>';
			                    }else{
			                     temp+= '<td>禁用</td>';
			                    }
		                    temp+= '</tr>';
		                	temp+= '<tr>';
			                    temp+= '<td align="right">登陆次数：</td>';
			                    temp+= '<td>'+obj.login_counts+'</td>';
		                    temp+= ' </tr>';
		                    $("#loginDetail").html(temp);   
				aDetails.style.display = 'block';
			};
			
		}
		
		oBatch.onclick = function () {
			var str='';
			var arry =userArrList[0];
			for(var i = 0; i < aInput.length; i++) {
				if(aInput[i].checked == true) {
					if(i>0){
					var objwe = arry[i-1];
					str += "'"+objwe.person_id + "',";
					}
					oPrompt.style.display = 'block';
				}
			}
			str = str.substring(0,str.length-1);
			$("#personId").val(str); //给隐藏域赋值
		};
		
		oStart.onclick = function () {
			var str='';
			var arry =userArrList[0];
			for(var i = 0; i < aInput.length; i++) {
			console.log(aInput.length);
				if(aInput[i].checked == true) {
					if(i>0){
					var objwe = arry[i-1];
					str += "'"+objwe.person_id + "',";
					}
					oPrompt2.style.display = 'block';
				}
			}
			str = str.substring(0,str.length-1);
			$("#personId").val(str); //给隐藏域赋值
		};
		
		
	}
};

my.app.toRun = function () {
	if(document.getElementById('lfy_run')) {
		var oDiv = document.getElementById('lfy_run');
		var oLeft = my.tools.getByClass(oDiv, 'left')[0];
		var oRight = my.tools.getByClass(oDiv, 'right')[0];
		var oUl = oDiv.getElementsByTagName('ul')[0];
		var aLi = oUl.getElementsByTagName('li');
		
		var iNow = 0;
		
		oUl.innerHTML += oUl.innerHTML;
		oUl.style.width = aLi[0].offsetWidth * aLi.length + 'px';
		
		oLeft.onclick = function () {
			
			if(iNow == 0){
				iNow = aLi.length/2;
				oUl.style.left = -oUl.offsetWidth/2 + 'px';
			}
			
			my.ui.startMove(oUl, {'left':  -iNow * aLi[0].offsetWidth + aLi[0].offsetWidth});
			
			iNow--;
		};
		
		oRight.onclick = function () {
			if(iNow == aLi.length /2) {
				iNow = 0;
				oUl.style.left = 0;
			}
			
			
			my.ui.startMove(oUl, {'left': -iNow * aLi[0].offsetWidth - aLi[0].offsetWidth});
			
			iNow++;
		};
		
		/*隔行变色*/
		var aList = my.tools.getByClass(document, 'lfy_oplist');
		
		for(var j = 0; j < aList.length; j++) {
			var aListson = aList[j].getElementsByTagName('li');
			for(var i = 0; i < aListson.length; i++) {
				if(i % 2 == 0) {
					aListson[i].style.background = '#f4fffb';
				}
			}
		}
		
	}
};

my.app.toFileArea = function () {
	if(document.getElementById('fileArea')) {
		var oDiv = document.getElementById('fileArea');
		var oFile = my.tools.getByClass(oDiv, 'file')[0];	
		var oTxt = my.tools.getByClass(oDiv, 'txt')[0];	
		
		oFile.onchange = function () {
			oTxt.value = oFile.value;
		};
	}

};

function reloadAgain(){
	my.app.toLogin();
	my.app.toSidebar();
	my.app.toCheck();
	my.app.toFilter();
	my.app.toBtn();
	my.app.toDelete();
	my.app.toActive();
	my.app.toUser();
	my.app.toRun();
	my.app.toFileArea();
}
