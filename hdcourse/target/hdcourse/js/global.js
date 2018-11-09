"use strict"
$.fn.extend({
	//输入框字符显示
	showWord:function(){
		var str=$(this).val();
		var _c=$(this).css('color');
		$(this).focus(function(){
			if(str == $(this).val()){ 
				$(this).val('');
				$(this).css('color','#000');
			}
		})
		$(this).blur(function(){
			if("" == $(this).val()){
				 $(this).val(str);
				 $(this).css('color',_c);
			}
		})
	},
	//end 输入框字符显示
	
	
	//选项卡
	tabControl:function(tab,con,mor){
		$(this).each(function(){
			var _method=this;
			$(this).find(tab).click(function(){
				if(this.className=='more'){return false;}
				$(this).addClass('on').siblings().removeClass('on');
				$(_method).find(con).addClass('dis_none');
				$(_method).find(con).eq($(this).index()).removeClass('dis_none');
				//$(_method).find(tab+'.more').removeClass('on');
				return false;
			});
			$(this).find(mor).click(function(){
				window.location.href=$(this).attr('href');
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
	//end 选项卡
	
	//open box
	jumpBox:function(style){
		var _method=$(this);
		$(this).css({'position':'absolute'});
		//$("body select").css('visibility','hidden');
		//$(".jumpBox select").css('visibility','visible');
		center($(this),style);
		$("#screen").css({'display':'block','width':'00%','height':$(document).height()});
		$(this).css({'display':'block'});
		$(this).find("*[name='close']").click(function(){
			//$("body select").css('visibility','visible');	
			$("#screen").hide();
			_method.hide();
			//enableScroll();
			return false;
		})
	},
	
	closeBox:function(){
		//$("body select").css('visibility','visible');	
		$("#screen").hide();
		//enableScroll();
		$(this).hide();
	}
});

//改变偶数行背景色
function changTd(obj,col){
	$(obj).find('tr:even td').css("background-color",col); 
	$(obj).find('tr:even').attr("bg",col);
	$(obj).find('tr:odd').attr("bg","#fff");
}


//复选框多选行
function ckAllItm(obj){
	var $ckAll =$(obj).find("input[name='ckAll']");
	var $ckItm = $(obj).find("input[name='ckItm']");
	var len = $ckItm.length;
	$ckAll.click(function() {
		$ckItm.prop('checked',this.checked);
	});
	$ckItm.click(function() {
		var b=$ckItm.filter(":checked").length==len;
		var flag=$ckAll.prop("checked",b?true:false);
	});
}

//for center
	function center(obj,style) {		//取中间值
	var screenWidth = $(window).width(), screenHeight = window.document.body.clientHeight;// $(window).height()
	var scrolltop = $(document).scrollTop();
	var objLeft = (screenWidth - obj.width())/2 ;
	var objTop = (screenHeight - obj.height())/2 + scrolltop;
	
	obj.css({position:'absolute',left: objLeft + 'px', top: objTop + 'px'/*,'display': 'block'*/});
	
	/*$(window).bind('resize', function() { 
		center(obj,!loaded);
	});*/
	//disableScroll();
	/*if(true==style){
		$(window).bind('scroll', function() { 
			objTop = (screenHeight - obj.height())/2 + $(document).scrollTop();
			obj.css({top:objTop+'px'});
		});	
	}else{
		$(window).unbind('scroll');
	}*/
}
	
/**---------------屏蔽和启用页面滚动条-----------------------*/
function disableScroll() {
    //鼠标移动到滚动条上时，禁止滚动条随滚轮滚动和鼠标拖动
    var height = $(window).scrollTop(); 
    $(window).scroll(function(){
		$(window).scrollTop(height);
	});
}
//启用滚动条
function enableScroll() {
    //启用滚动条
    $(window).unbind("scroll");
}

//clear placeholder
;(function(f,h,$){var a='placeholder' in h.createElement('input'),d='placeholder' in h.createElement('textarea'),i=$.fn,c=$.valHooks,k,j;if(a&&d){j=i.placeholder=function(){return this};j.input=j.textarea=true}else{j=i.placeholder=function(){var l=this;l.filter((a?'textarea':':input')+'[placeholder]').not('.placeholder').bind({'focus.placeholder':b,'blur.placeholder':e}).data('placeholder-enabled',true).trigger('blur.placeholder');return l};j.input=a;j.textarea=d;k={get:function(m){var l=$(m);return l.data('placeholder-enabled')&&l.hasClass('placeholder')?'':m.value},set:function(m,n){var l=$(m);if(!l.data('placeholder-enabled')){return m.value=n}if(n==''){m.value=n;if(m!=h.activeElement){e.call(m)}}else{if(l.hasClass('placeholder')){b.call(m,true,n)||(m.value=n)}else{m.value=n}}return l}};a||(c.input=k);d||(c.textarea=k);$(function(){$(h).delegate('form','submit.placeholder',function(){var l=$('.placeholder',this).each(b);setTimeout(function(){l.each(e)},10)})});$(f).bind('beforeunload.placeholder',function(){$('.placeholder').each(function(){this.value=''})})}function g(m){var l={},n=/^jQuery\d+$/;$.each(m.attributes,function(p,o){if(o.specified&&!n.test(o.name)){l[o.name]=o.value}});return l}function b(m,n){var l=this,o=$(l);if(l.value==o.attr('placeholder')&&o.hasClass('placeholder')){if(o.data('placeholder-password')){o=o.hide().next().show().attr('id',o.removeAttr('id').data('placeholder-id'));if(m===true){return o[0].value=n}o.focus()}else{l.value='';o.removeClass('placeholder');l==h.activeElement&&l.select()}}}function e(){var q,l=this,p=$(l),m=p,o=this.id;if(l.value==''){if(l.type=='password'){if(!p.data('placeholder-textinput')){try{q=p.clone().attr({type:'text'})}catch(n){q=$('<input>').attr($.extend(g(this),{type:'text'}))}q.removeAttr('name').data({'placeholder-password':true,'placeholder-id':o}).bind('focus.placeholder',b);p.data({'placeholder-textinput':q,'placeholder-id':o}).before(q)}p=p.removeAttr('id').hide().prev().attr('id',o).show()}p.addClass('placeholder');p[0].value=p.attr('placeholder')}else{p.removeClass('placeholder')}}}(this,document,jQuery));

//operation on
$('input, textarea').placeholder();

//
$(".my_plate").click(function(){
$(".sec_list").toggle();
});
