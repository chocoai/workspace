//登录浮层
var Login = {
	oBtn:$('#login_btn'),
	oMask: $('#m_mask'),
	oDialog:$('#m_login'),
	oClose: $('#m_close'),
	oTab: $('.logoCaseTab'), 
	oPwd: $('.passWord-case'),
	oSml: $('.smLoing-rect'),
	oSmc: $('.sm-case'),
	oSmi: $('.sm-invalid'),
	//弹出浮层
	toggleDialog: function () {
		var _this = this;
		var oInp =_this.oDialog.find(".text").eq(0);
		_this.oMask.stop().addClass("m_mask_show").animate({'opacity':0.7},'fast');
		_this.oDialog.stop().addClass("m_login_show").animate({'opacity':1,'top':'50%'},'slow');
		setTimeout(function () { oInp.focus()}, 350);
		_this.oPwd.show();
		_this.oSml.hide();
		_this.oSmc.show();
		_this.oSmi.hide();
		_this.oTab.removeClass('CaseTab2');
	},
	btnClick:function(){
		var _this = this;
		var oInp =_this.oDialog.find(".text").eq(0);
		_this.oBtn.on("click",function(event){
			_this.toggleDialog();
			event.stopPropagation();
		})
	},
	clickClose:function(){
		var _this = this;
		_this.oClose.on("mousedown",function(){
			_this.oMask.removeClass("m_mask_show").animate({'opacity':0},'fast');
			_this.oDialog.animate({'opacity':0,'top':'0%'},'slow',function(){_this.oDialog.removeClass("m_login_show")});
		})
	},
	documentClick:function(){
		var _this = this;
		$(document).on("click",function(event){ 
			if(event.which==3){
				 event.stopPropagation();
			}
			else{
				_this.oMask.removeClass("m_mask_show").stop().animate({'opacity':0},'fast');
				_this.oDialog.stop().animate({'opacity':0,'top':'0%'},'slow',function(){_this.oDialog.removeClass("m_login_show")});
			}
		})
		_this.oDialog.on("click",function(event){
			event.stopPropagation();
		})	

	},
	//回车键登录
	bindEnterKey: function () { 
	var _this=this;
		var aInp =_this.oDialog.find(".text");
		var oSubmit =_this.oDialog.find(".submit").eq(0);
		
		for(var i =0; i < aInp.length; i++) {
			aInp.eq(i).on("keydown",function(ev){
				var oEvent = ev || event;
				if(oEvent.keyCode == 13) {
					oSubmit.click();
				}	
			})	
		}
	},
	//登录框焦点
	inputFocusBlur:function(){
		var _this=this;
		_this.oDialog.find('input[type!="button"]').on({
			focus:function(){
				$(this).parent().addClass('acitve');
			},
			blur:function(){
				$(this).parent().removeClass('acitve');
			}
		});	
	},
	//登录记住我
	rememberMe:function(){
		var _this=this;
		//点击关闭错误提示
		setTimeout(function () { _this.oDialog.find('.err-tips').hide()}, 3000);
		_this.oDialog.find('.err-tips .close').click(function(){
			$(this).parent().hide();
		})	
	},
	init: function () {
		this.btnClick();
		this.clickClose();
		this.documentClick();
		this.bindEnterKey();
		this.inputFocusBlur();
		this.rememberMe();
	},
	autoinit: function () {
		this.toggleDialog();
		this.clickClose();
		this.documentClick();
		this.bindEnterKey();
		this.inputFocusBlur();
		this.rememberMe();
	}
}


//登录框交互
function loginJH(obj,fn){
	var oLoginBox = $(obj);
	var oLoginTabTit = oLoginBox.find('.logoCaseTab');
	var oLoginTip = oLoginBox.find('.logoTips');
	var oLoginCont = oLoginBox.find('div[name="LoginRect"]');
	var oErrorTip = oLoginBox.find('.err-tips .close');
	var time1 = null;
	var time2 = null;
	var onoff = false;
	init();
	function init(){
		if(time1)clearTimeout(time1);
		if(time2)clearTimeout(time2);
		oLoginCont.eq(1).hide();
		oLoginCont.eq(1).find('.sm-case').show().removeClass('no-tips');;
		oLoginCont.eq(1).find('.sm-invalid').hide();
		oLoginCont.eq(0).fadeIn(300);
		oLoginTip.find('span').html('扫码登录更安全');
		onoff = false;
	}
	
	oLoginBox.hover(function(){oLoginTip.fadeIn(300)},function(){oLoginTip.fadeOut(300)});
	oLoginTabTit.click(function(){
		if($(this).hasClass('CaseTab2')){
			$(this).removeClass('CaseTab2');
			init();
		}else{
			$(this).addClass('CaseTab2');
			oLoginCont.eq(0).hide();
			oLoginCont.eq(1).fadeIn(300);
			oLoginTip.find('span').html('密码登录在这里');
			
			if(time1)clearTimeout(time1);
			time1 =  setTimeout(function(){
				oLoginCont.eq(1).find('.sm-case').addClass('no-tips');
				onoff = true;
			},5000);
		}
	});
	oLoginCont.eq(1).find('.sm-case').hover(function(){
		if(!onoff)return false;
		$(this).removeClass('no-tips');
	},function(){
		if(!onoff)return false;
		$(this).addClass('no-tips');
	});
	
	oErrorTip.click(function(){
		$(this).parent().hide();
	})
	
}



//clear placeholder
(function(f,h,$){var a="placeholder" in h.createElement("input"),d="placeholder" in h.createElement("textarea"),i=$.fn,c=$.valHooks,k,j;if(a&&d){j=i.placeholder=function(){return this};j.input=j.textarea=true}else{j=i.placeholder=function(){var l=this;l.filter((a?"textarea":":input")+"[placeholder]").not(".placeholder").bind({"focus.placeholder":b,"blur.placeholder":e}).data("placeholder-enabled",true).trigger("blur.placeholder");return l};j.input=a;j.textarea=d;k={get:function(m){var l=$(m);return l.data("placeholder-enabled")&&l.hasClass("placeholder")?"":m.value},set:function(m,n){var l=$(m);if(!l.data("placeholder-enabled")){return m.value=n}if(n==""){m.value=n;if(m!=h.activeElement){e.call(m)}}else{if(l.hasClass("placeholder")){b.call(m,true,n)||(m.value=n)}else{m.value=n}}return l}};a||(c.input=k);d||(c.textarea=k);$(function(){$(h).delegate("form","submit.placeholder",function(){var l=$(".placeholder",this).each(b);setTimeout(function(){l.each(e)},10)})})}function g(m){var l={},n=/^jQuery\d+$/;$.each(m.attributes,function(p,o){if(o.specified&&!n.test(o.name)){l[o.name]=o.value}});return l}function b(m,n){var l=this,o=$(l);if(l.value==o.attr("placeholder")&&o.hasClass("placeholder")){if(o.data("placeholder-password")){o=o.hide().next().show().attr("id",o.removeAttr("id").data("placeholder-id"));if(m===true){return o[0].value=n}o.focus()}else{l.value="";o.removeClass("placeholder");l==h.activeElement&&l.select()}}}function e(){var q,l=this,p=$(l),m=p,o=this.id;if(l.value==""){if(l.type=="password"){if(!p.data("placeholder-textinput")){try{q=p.clone().attr({type:"text"})}catch(n){q=$("<input>").attr($.extend(g(this),{type:"text"}))}q.removeAttr("name").data({"placeholder-password":true,"placeholder-id":o}).bind("focus.placeholder",b);p.data({"placeholder-textinput":q,"placeholder-id":o}).before(q)}p=p.removeAttr("id").hide().prev().attr("id",o).show()}p.addClass("placeholder");p.attr("isInit",true);p[0].value=p.attr("placeholder")}else{p.removeClass("placeholder")}}}(this,document,jQuery));$(function(){$("input, textarea").placeholder();if(!window.addEventListener){$(document).ajaxStop(function(){var inputs=$("input, textarea");inputs.each(function(index){if(!$(this).attr("isInit")&&!$(this).data("placeholder-enabled")){$(this).placeholder()}})})}});

