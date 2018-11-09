<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style type="text/css">
.alertMsg .warning {width:500px;height:80px; overflow :auto; font-size: 14px; color: #a82929; text-align: center; padding-top: 30px;padding-bottom: 30px;word-wrap: break-word;word-break : break-all;}
.alertMsg .btn,.loading {text-align: center;}
</style>
<script type="text/javascript">
var Dialog = function() {
	return {
		build : function(type, callback, html) {
			render.renderDialog(type, callback, html);
		}
	}
};

var render={
	/** 
	* 根据需要生成对话框 
	* @param {Object} type 
	* @param {Object} callback 
	* @param {Object} html 
	*/ 
	renderDialog:function(type,callback,html){ 
		if(type=='alert'){ 
			this.renderAlert(type,callback,html); 
		}else{ 
			this.renderConfirm(type,callback,html); 
		} 
	}, 

	/** 
	 * 生成alert 
	 * @param {Object} callback 
	 * @param {Object} html 
	 */
	renderAlert : function(type,callback, html) {
		$(".alertMsg .warning").html(html);
		$(".alertMsg .alertAbolish").hide();
		
		$(".alertMsg").jumpBox(true);
		this.bindEvents(type, callback);
	},
	/** 
	 * 生成confirm 
	 * @param {Object} callback 
	 * @param {Object} html 
	 */
	renderConfirm : function(type,callback, html) {
		$(".alertMsg .warning").html(html);
		$(".alertMsg .alertAbolish").show();
		
		$(".alertMsg").jumpBox(true);
		
		this.bindEvents(type, callback);
	},
	/** 
	 * 绑定事件 
	 * @param {Object} type 
	 * @param {Object} callback 
	 */
	bindEvents : function(type, callback) {
		$('.alertMsg .btn .alertConfirm').unbind();
		if ($.isFunction(callback)) {
			$('.alertMsg .btn .alertConfirm').click(function() {
				$(".alertMsg .btn").hide();
				$(".alertMsg .loading").show();
				setTimeout(function(){
					callback();
					$(".alertMsg .btn").show();
					$(".alertMsg .loading").hide();
					$(".alertMsg").closeBox();
				}, 1);
			});
		} else {
			$('.alertMsg .btn .alertConfirm').click(function() {
				$(".alertMsg").closeBox();
			});
		}
		
		$(".alertMsg .alertClose").click(function(){
			$(".alertMsg").closeBox();
		});
		
		if (type == "confirm") {
			$(".alertMsg .alertAbolish").click(function(){
				$(".alertMsg").closeBox();
			});
		}
	}
};

$.extend({
	alert : function(html, callback) {
		var dialog = new Dialog();
		dialog.build('alert', callback, html);
	},
	confirm : function(html, callback) {
		var dialog = new Dialog();
		dialog.build('confirm', callback, html);
	},
	alertClose : function() {
		$(".alertMsg").closeBox();
	}
});

</script>

<!--#弹窗层-->
<div class="popup alertMsg dis_none">
    <div class="tit"><span class="fl">提示</span><span class="close" name="close">X</span></div>
    <div class="cont pd10">
		<div class="warning"></div>
		<div class="btn">  
			<input type="button" class="alertConfirm btn_blue" value="确定"/>
			<input type="button" class="alertAbolish btn_gray"  name="close" value="取消"/>
		</div>
		<div class="loading dis_none"><img src="${ctx}/images/loading.gif">&nbsp;&nbsp;&nbsp;系统正在处理中...</div>
    </div>
</div>
<div class="dis_none" id="screen"></div>