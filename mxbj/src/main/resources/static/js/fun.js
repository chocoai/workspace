// JavaScript Document
$.fn.extend({
    //选项卡.start
	tabControl:function(tab,con,mor){
		$(this).each(function(){
			var _method=this;
            $(this).find(tab).each(function(i){
                $(this).click(function(){
				if(this.className=='more'){return false;}
				$(this).addClass('on').siblings().removeClass('on');
				$(_method).find(con).addClass('dis_none');
                $(_method).find(con).eq(i).removeClass('dis_none');
				return false;
				});
         	});                                               
			$(this).find(mor).click(function(){
				window.location.href=$(this).attr('href');
			});
		});
	},
	//选项卡.over
	
	//checkbox.美化.start
	checkboxFn:function(){
		$(this).each(function(){
			var _method=$(this);
			var disable=_method.attr("disabled")=="disabled"?" disabled=\"disabled\"":"";
			var str=_method.attr("checked")=="checked"?"ckboxBtn_on":"ckboxBtn";
			var cls=_method.attr("disabled")=="disabled"?"c888":"";
			if("ckboxBtn_on"==str && ""!=disable) str="ckboxBtn_disabled";
			if('none'==_method.parent().css('display')||_method.css('display')=='none') return false;
			_method.parent().hide().before("<label name=\""+_method.attr("name")+"\" class=\"ckboxTb\"><span class=\""+cls+"\">"+_method.parent().text()+"</span></label>"); 		
			var _parent=_method.parent().prev();
			_parent.prepend("<span class='"+str+"' "+disable+"></span>");
			_parent.hover(function(){_method.css({"cursor":"pointer"})},function(){_method.css({"cursor":"normal"})});
			_parent.bind('click',function(event){
	            if(event.target.tagName=='LABEL'){
	            }else{
					var obj=_parent.find("span").eq(0);
					if(_method.attr("disabled")=="disabled"){ return false;}
					obj.attr('class',obj.attr('class')=='ckboxBtn'?'ckboxBtn_on':'ckboxBtn');	
					_method.attr('checked',obj.attr('class')=='ckboxBtn'?false:true);
				}
			})
		})
	},
    //checkbox美化.over

    //模拟上传框.start
    	fileTxFn:function(){
		$(this).each(function(){
			if(0==$(this).width()) return false;
			var _method=$(this),w=$(this).width();
			$(this).height(0).width(0).before("<span class='apps_fn_fileCss'><input type='text' class='apps_hdlt apps_w180' value='"+$(this).val()+"' readonly='readonly' /> <input class='apps_btn3 apps_w100' type='button' value='涓婁紶'></span>");
			var _parent=_method.prev('span');
			_parent.unbind().bind('click',function(){_method.click();return false;})
			_method.unbind('change').bind('change',function(){_parent.find("input[type='text']").val(_method.val());return false;})
		})
	},//模拟上传框.over

	//checkbox 美化(checkbox不被包含在lable里面).start
    hcheckbox:function(options){
		this.each(function () {
			var _this = $(this);
			var oChkAll = _this.find('label.chkAll');
			$(':checkbox+label', this).each(function() {
				$(this).addClass('checkbox');
				if ($(this).prev().is(':disabled') == false) {
					if ($(this).prev().is(':checked')) $(this).addClass("checked");
				} else {
					$(this).addClass('disabled');
				}
			}).click(function(event) {
				if (!$(this).prev().is(':checked')) {
					$(this).addClass("checked").prev()[0].checked = true;
					if(oChkAll[0]) {
						var aLabel = _this.find(':checkbox+label').not('.chkAll');
						aLabel.each(function (index) {
							if(!$(this).prev().is(':checked')) return false;
							if(index >= aLabel.length - 1) oChkAll.addClass('checked').prev()[0].checked = true;
						});
					}
				} else {
					$(this).removeClass('checked').prev()[0].checked = false;
					if(oChkAll.hasClass('checked')) oChkAll.removeClass('checked').prev()[0].checked = false;
				}
				event.stopPropagation();
			}).prev().hide();
			oChkAll.click(function () { //增加全选
				var bFlag = $(this).prev().is(':checked');
				_this.find(':checkbox+label').each(function () {
					if(bFlag) {
						$(this).addClass("checked").prev()[0].checked = true;
					} else {
						$(this).removeClass('checked').prev()[0].checked = false;
					}
				});
			});
		});
	},
	//checkbox 美化(checkbox不被包含在lable里面).over

	//checkbox 美化(checkbox被包含在lable里面).start 
    hcheckbox2:function(chkItem,chkAll){
		this.each(function () {
			var _this = $(this);
			var oChkAll = chkAll? _this.find(chkAll):_this.find('label.chkAll');
			var chkCell = chkItem? chkItem:'label';
			$(chkCell, this).each(function() {
				$(this).addClass('checkbox');
				if ($(this).children().is(':disabled') == false) {
					if ($(this).children().is(':checked')) $(this).addClass("checked");
				} else {
					$(this).addClass('disabled');
				}
			}).click(function(event) {
				if (!$(this).children().is(':checked')) {
					$(this).addClass("checked").children()[0].checked = true;
					if(oChkAll[0]) {
						var aLabel = _this.find('label.checkbox').not('.chkAll');
						aLabel.each(function (index) {
							if(!$(this).children().is(':checked')) return false;
							if(index >= aLabel.length - 1) {
								oChkAll.addClass('checked').children()[0].checked = true;
							}
						});
					}
				} else {
					$(this).removeClass('checked').children()[0].checked = false;
					if(oChkAll.hasClass('checked')) oChkAll.removeClass('checked').children()[0].checked = false;
				}
				event.stopPropagation();
			}).children().hide();
			oChkAll.click(function () { //增加全选
				var bFlag = $(this).children().is(':checked');
				if(chkCell.indexOf('label') == 0) chkCell = chkCell+'.checkbox';
				_this.find(chkCell).each(function () {
					if(bFlag) {
						$(this).addClass("checked").children()[0].checked = true;
					} else {
						$(this).removeClass('checked').children()[0].checked = false;
					}
				});
			});
		});
	},
 	//checkbox 美化(checkbox被包含在lable里面).over

	//checkbox全选.start
    allCheckCmenu:function(_obj){
		$(this).each(function(){
			$("input[type='checkbox']").checkboxFn();
			var _method=$(this);
			_method.prev().click(function(){
				if('checked'==_method.find("input").attr('checked')){
					_obj.attr('checked','checked');
					_obj.each(function(){$(this).parent().prev().find('span').eq(0).attr('class','ckboxBtn_on');})
				}else{
					_obj.attr('checked',false);
					_obj.each(function(){$(this).parent().prev().find('span').eq(0).attr('class','ckboxBtn');})
				}
			})
		})
	},
    //checkbox全选.over

 	//radio 美化(radio不被包含在lable里面).start
    hradio:function(options){
		var self = this;
		return this.each(function () {
			var _this = $(this);
			$(':radio+label',this).each(function(){
				$(this).addClass('hRadio');
				if($(this).prev().is(":checked")) {
					$(this).addClass('hRadio_Checked'); 
					_this.find(':radio+label').not($(this)).removeClass("hRadio_Checked");
				}
			}).click(function(event){
				_this.find(':radio+label').not($(this)).removeClass("hRadio_Checked");
				if(!$(this).prev().is(':checked')){
					$(this).addClass("hRadio_Checked");
					$(this).prev()[0].checked = true;
				}
				event.stopPropagation();
			}).prev().hide();
		});
	},
	//radio 美化(radio不被包含在lable里面).over

	//radio 美化(radio被包含在lable里面).start
	hradio2:function(){
		var self = this;
		$(this).each(function () {
			var _this = $(this);
			$(this).find('label').each(function(){
				$(this).addClass('hRadio');
				if($(this).children().hasClass("checked")) {
					$(this).addClass('hRadio_Checked').siblings().removeClass("hRadio_Checked");
				}
			}).click(function(event){
				$(this).addClass('hRadio_Checked').siblings().removeClass("hRadio_Checked");
				if(!$(this).children().hasClass("checked")){
					$(this).addClass("hRadio_Checked");
					$(this).children()[0].checked = true;
				}
				event.stopPropagation();
			}).children().hide();
		});
	},
	//radio 美化(radio被包含在lable里面).over  
	
	//改版select
	SeleautoBox:function(){
		$(this).each(function() {          
			$(".qjf_seleautocur").hover(
				function(){$(this).addClass("bluebor")},
				function(){$(this).removeClass("bluebor")}
			);
			var Myobj = $(this);
			var oInp = Myobj.find('input.selRes');
			Myobj.find(".qjf_seleautodrop a").each(function () {
				if($(this).hasClass('active')) {
					Myobj.find(".qjf_seleautocur p").html($(this).html());
					if(oInp[0]) oInp.val($(this).attr('value'));
					return false; 
				}
			});
			Myobj.unbind().live('click',function(ev){
				var Ocur = $(this).find(".qjf_seleautocur");
				var Odrop = $(this).find(".qjf_seleautodrop");	
				var Owidth = $(this).width();	
						
				$(this).css("z-index",3);							
				Odrop.toggle(0,function(){
					$(this).addClass('animate');
					$(this).css({'z-index':9999,"width":Odrop.width()>Owidth?Odrop.width():Owidth});					
				});				
				if($.browser.msie&&parseInt($.browser.version) <= 6&&Myobj.find(".qjf_seleautodrop").height()>196 && !$.support.style)
				{
					Myobj.find(Odrop).css({"height":"196px","overflow-y":"scroll"});
				}
				$(".qjf_seleautodiv").not($(this)).css("z-index",1);
				$(".qjf_seleautodiv").not($(this)).find(".qjf_seleautodrop").hide();				
				ev.stopPropagation();
				$(document).click(function(){
					$(".qjf_seleautodrop").hide();	
				});				
				Odrop.find("a").live('click', function(){
					Ocur.find("p").html($(this).html());
					Odrop.hide();
					$(this).addClass('active').siblings().removeClass('active');
					if(oInp[0]) oInp.val($(this).attr('value'));
					return false;
				});
			});		
        });	
	},
	//分页跳转框输入弹出效果
	inputPageFocus:function(opts){
		  var set = $.extend({
		    btnClass:''
		  },opts||{});
		  var btnClass = set.btnClass;
		  var This = $(this);
		  This.live('click',function(ev){
			  $(this).next(btnClass).stop(true).animate({'left':36});
			  ev.stopPropagation();
		  });
		  $(document).live('click', function (){
		  	 This.next(btnClass).stop(true).animate({'left':0});
		  });
	},
	//评分功能.start
	grade:function(valObj,scoreObj,resObj){
		var resObj=resObj||false;
		$(this).click(function(event){
			var event=event||window.event;
			var l=$(this).width();
			var b = 2;
			var result = "涓€鑸�";
			var score=Math.ceil((event.pageX-$(this).offset().left)/(2*l)*10)*2;
			$(this).find('em').width(score*10+'%');
			valObj.val(score);	
			scoreObj&&scoreObj.html(score/b+".0分");
			switch (score) {				
				case 0:result="\5f88\5dee";break;
				case 2:result="\4e0d\597d";break;
 				case 4: result="\4e00\822c";break;
				case 6:result="\4e00\822c";break;	
				case 8:result="\5f88\597d";break;
				default:result="\5f88\597d";break;
				};
			resObj&&resObj.html(result)	
		})
	},//评分功能.over
	grade3:function(valObj){
		var onoff = true;
		var inum = 0;
		$(this).on('mouseover mousemove',function(event){
			var event=event||window.event;
			getGrade($(this),event);
		});
		
		$(this).mouseout(function(){
			if(!onoff){
				$(this).find('em').width(inum*10+'%');
			}else{
				$(this).find('em').width(0);
			}
		});
		$(this).click(function(event){
			var event=event||window.event;
			inum = getGrade($(this),event);
			valObj.val(inum/2);
			onoff = false;
		});
		function getGrade(obj,ev){
			var l=obj.width();
			var b = 2;
			var score=Math.ceil((ev.pageX-obj.offset().left)/(2*l)*10)*2;
			obj.find('em').width(score*10+'%');
			return score;
		}
	},//评分功能.over
	//评分 保留一位小数点 start
	grade2: function (options) {
		$(this).click(function (ev) {
			var iNum = (ev.pageX - $(this).offset().left) / $(this).width();
			if(iNum > 0.9) {
				iNum = 1;
			} else if(iNum < 0.1) {
				iNum = 0;
			}
			$(this).children('.grade_inner').css('width', parseInt(iNum * 70)).parents('.grade_wrap').find('i').text((iNum * 5).toFixed(1)).parents('.grade_wrap').find('input').val((iNum * 5).toFixed(1));
		});
	},
	//弹窗.start
	jumpBox:function(style){
		var _method=$(this);
		var clientWidth = _method.innerWidth(), clientHeight = _method.innerHeight();		
		center(_method,style);
		$("body select").css('visibility','hidden');
		$("body .popWrap select").css('visibility','visible');//修改
		_method.find("*[name='close']").click(function(ev){
			ev.preventDefault();
			$("body select").css('visibility','visible');			
			_method.hide();
			$("#screen").hide();
			return false;
		})
		_method.find("*[name='close'] img").hover(function(){
			$(this).attr('src','http://www.wuhaneduyun.cn:21001/statics_dev/common/images/ico/close-popho.png');	
		},function(){
			$(this).attr('src','http://www.wuhaneduyun.cn:21001/statics_dev/common/images/ico/close-pop.png');	
		})
		$("#screen").css({'display':'block','width':'100%','height':$(document).height()});		
		if(_method.is(":hidden")){$(window).bind('resize',function(){center(_method,style)});}
	},
	Newjump:function(style){
		var _Obj=$(this);
		middcen(_Obj,style);
		$("body select").css('visibility','hidden');
		$("body .popWin select").css('visibility','visible');
		_Obj.find("*[name='close']").click(function(ev){
			ev.preventDefault();
			$("body select").css('visibility','visible');
			_Obj.hide();
			$("#Wscreen").hide();
			return false;
		});
		if(_Obj.is(":hidden")){$(window).bind('resize',function(){middcen(_Obj,style)});}
	}
});
function middcen(Opo,style){
	var OpoLeft = ($(window).width() - Opo.width())/2+'px';
	var OpoTop = ($(window).height() - Opo.outerHeight(true))/2 + $(document).scrollTop()+'px';
	Opo.css({'position':'fixed',left:OpoLeft, top: OpoTop,'z-index':999999,'display':'block'});
	if($.browser.msie&&parseInt($.browser.version) <= 6&& !$.support.style)
	{
		Opo.css({'position':'absolute'});	
	}	
	$("body").append('<div id="Wscreen"></div');
	$("#Wscreen").css({'position':'absolute','left':0,'top':0,'display':'block','width':'100%','height':$(document).height()});
	if(true==style){		
		OpoTop = ($(window).height() - Opo.outerHeight(true))/2+'px'
		Opo.css({top:OpoTop});
		
		if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) { 
			$(window).bind('scroll', function() { 			
				OpoTop = ($(window).height() - Opo.outerHeight(true))/2 + $(document).scrollTop()+'px';
				Opo.css({top:OpoTop});
			});	
			OpoTop = ($(window).height() - Opo.outerHeight(true))/2 + $(document).scrollTop()+'px';
			Opo.css({top:OpoTop});
		} 
	}else{
		$(window).unbind('scroll');
	}
}
//居中
function center(obj,style, pos) {
	var _obj=obj;
	var screenWidth = $(window).width(), screenHeight = $(window).height(); 
	var scrolltop = $(document).scrollTop();
	var objLeft = (screenWidth - obj.width())/2 + 'px';
	var objTop = (screenHeight - obj.outerHeight(true))/2 + scrolltop + 'px';
	obj.css({position:'fixed',left: objLeft, top: objTop,'display': 'block'});
	if($.browser.msie&&parseInt($.browser.version) <= 6&& !$.support.style)
	{
		obj.css({'position':'absolute'});	
	}
	if(true==style){
		objTop = (screenHeight - obj.outerHeight(true))/2+'px';
		obj.css({top:objTop});
		if(pos && typeof pos.top == 'number') { obj.css('top', pos.top); }
		if(pos && typeof pos.left == 'number') { obj.css('left', pos.left); }
		if ($.browser.msie && ($.browser.version == "6.0") && !$.support.style) { 
			$(window).bind('scroll', function() { 
				objTop = (screenHeight - obj.outerHeight(true))/2 + $(document).scrollTop()+'px';
				obj.css({top:objTop});
				if(pos && typeof pos.top == 'number') { obj.css('top', $(document).scrollTop() + pos.top); }
			});
			objTop = ($(window).height() - obj.outerHeight(true))/2 + $(document).scrollTop()+'px';
			obj.css({top:objTop});	
			if(pos && typeof pos.top == 'number') { obj.css('top', $(document).scrollTop() + pos.top); }
		}
	}else{
		$(window).unbind('scroll');
	}
}

//执行分页跳转框输入弹出效果
$(function(){
	$(".num_text").inputPageFocus({
	  btnClass:'.anim'
	});
});

//clear placeholder
(function(f,h,$){var a='placeholder' in h.createElement('input'),d='placeholder' in h.createElement('textarea'),i=$.fn,c=$.valHooks,k,j;if(a&&d){j=i.placeholder=function(){return this};j.input=j.textarea=true}else{j=i.placeholder=function(){var l=this;l.filter((a?'textarea':':input')+'[placeholder]').not('.placeholder').bind({'focus.placeholder':b,'blur.placeholder':e}).data('placeholder-enabled',true).trigger('blur.placeholder');return l};j.input=a;j.textarea=d;k={get:function(m){var l=$(m);return l.data('placeholder-enabled')&&l.hasClass('placeholder')?'':m.value},set:function(m,n){var l=$(m);if(!l.data('placeholder-enabled')){return m.value=n}if(n==''){m.value=n;if(m!=h.activeElement){e.call(m)}}else{if(l.hasClass('placeholder')){b.call(m,true,n)||(m.value=n)}else{m.value=n}}return l}};a||(c.input=k);d||(c.textarea=k);$(function(){$(h).delegate('form','submit.placeholder',function(){var l=$('.placeholder',this).each(b);setTimeout(function(){l.each(e)},10)})});$(f).bind('beforeunload.placeholder',function(){$('.placeholder').each(function(){this.value=''})})}function g(m){var l={},n=/^jQuery\d+$/;$.each(m.attributes,function(p,o){if(o.specified&&!n.test(o.name)){l[o.name]=o.value}});return l}function b(m,n){var l=this,o=$(l);if(l.value==o.attr('placeholder')&&o.hasClass('placeholder')){if(o.data('placeholder-password')){o=o.hide().next().show().attr('id',o.removeAttr('id').data('placeholder-id'));if(m===true){return o[0].value=n}o.focus()}else{l.value='';o.removeClass('placeholder');l==h.activeElement&&l.select()}}}function e(){var q,l=this,p=$(l),m=p,o=this.id;if(l.value==''){if(l.type=='password'){if(!p.data('placeholder-textinput')){try{q=p.clone().attr({type:'text'})}catch(n){q=$('<input>').attr($.extend(g(this),{type:'text'}))}q.removeAttr('name').data({'placeholder-password':true,'placeholder-id':o}).bind('focus.placeholder',b);p.data({'placeholder-textinput':q,'placeholder-id':o}).before(q)}p=p.removeAttr('id').hide().prev().attr('id',o).show()}p.addClass('placeholder');p[0].value=p.attr('placeholder')}else{p.removeClass('placeholder')}}}(this,document,jQuery));
$(function () { $('input, textarea').placeholder(); });

