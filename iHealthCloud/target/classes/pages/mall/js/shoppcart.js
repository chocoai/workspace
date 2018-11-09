/*计算总钱数*/
function total(){
	setTimeout(function(){
		var S=0;
		var idSub = [];
	    $.each($('.total'), function() {
	    	var $ul_total=$(this).prev('ul').find("input[type='checkbox']");
	    	var s=0;
	        var n1=0;
	    	$.each($(this).prev('ul').find(".number"), function(i) {
		if($ul_total.eq(i).attr("checked")=="checked"){
			s=s+parseFloat($(this).html())*parseFloat($(this).parent().prev().html().replace("￥",""));
			n1=n1+parseFloat($(this).html());
		}
	});
	$(this).children("span").html("￥"+s.toFixed(2));
	$(this).children("number").html(n1);
	S=S+s;
	    });
	    $(".cartIdChecked").each(function(){
	    	if($(this).attr("checked")=="checked"){
	    		idSub.push($(this).attr("id"));
	    	}
	    })
	$("#idSub").val(idSub);
	$("#total").val(S.toFixed(2));
	$("#amount").val($(".totalNumber").children("number").html());
	$(".bottom span").html(S.toFixed(2));
	},100)
}
/*计算总钱数*/
/*判断有无数据*/
function hide(){
	if ($(".content").length==0) {
		$(".bottom").hide();
		$(".no").css("display","-webkit-box");
		return;
	}else{
		$(".bottom").eq(0).show();
		$(".no").css("display","none");
	}
}
/*判断有无数据*/
/*判断是否全选*/
function sum(){
	if ($("ul input[checked='checked']").length==$("li").length) {
		$(".bottom input[type=checkbox]").attr("checked","checked");
		$(".bottom input[type=checkbox]").next("img").attr("src",projectName+"/pages/mall/images/c_checkbox_on.png");
	}else{
		$(".bottom input[type=checkbox]").removeAttr("checked");
		$(".bottom input[type=checkbox]").next("img").attr("src",projectName+"/pages/mall/images/c_checkbox_off.png");
	}
}
/*判断是否全选*/
/*给单选框或复选框添加样式*/
function checkbox($this){
	if($this.attr('type')=="checkbox"){
		   if ($this.attr('checked')=="checked") {
		   	$this.removeAttr("checked");
		   	 $this.next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_off.png");
		   }else{
		   	 $this.attr("checked","checked");
		   	$this.next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_on.png");
		   }
		}
		/*计算总钱数*/
		total();
		/*计算总钱数*/
}

/*给单选框或复选框添加样式*/
$(function(){
	hide();
	total();
/*编辑*/
$(".shoppcart-edit").click(function(){
       if ($(this).html()=="编辑") {
       	$(this).html("完成");
       	$(".bottom").eq(1).show();
       }else{
       	$(this).html("编辑");
       	$(".bottom").eq(1).hide();
       }
       hide();   
});
/*编辑*/
/*底部全选*/
$('.bottom-label input').change(function(){
		if ($(this).attr("checked")=="checked") {
			$(".con input[type='checkbox']").removeAttr("checked");
			$(".con input[type='checkbox']").next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_off.png");
		}else{
			$(".con input[type='checkbox']").attr("checked","checked");
			$(".con input[type='checkbox']").next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_on.png");
		}
		checkbox($(this));
})
/*底部全选*/
/*子项全选*/
$('.list input').change(function(){
	var $list_input=$(this).parents('.list').next('ul').find('input[type=checkbox]');
		if ($(this).attr("checked")==undefined) {
			$list_input.attr("checked","checked");
			$list_input.next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_on.png");
		}else{
			$list_input.removeAttr("checked");
			$list_input.next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_off.png");
		} 
		checkbox($(this));
		sum();
})
/*子项全选*/
$("ul input[type='checkbox']").change(function(){
	checkbox($(this));
	var $ul_input=$(this).parents('ul').prev('.list').find('input');
	if($(this).parents('ul').find("input[checked='checked']").length==$(this).parents("ul").children('li').length){	
		$ul_input.attr("checked","checked");
		$ul_input.next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_on.png");
	}else{
		$ul_input.removeAttr("checked");
		$ul_input.next('img').attr("src",projectName+"/pages/mall/images/c_checkbox_off.png");
	} 
	sum();
})
/*点击加一*/
		$('.btn2').click(function(){
			var cartCid=$(this).prev('.number').attr("id");
			var stockAmount = parseFloat($("#cartId_"+cartCid).attr("value"));
			if($(this).prev('.number').text()<stockAmount){
				$(this).prev('.number').html(parseFloat($(this).prev('.number').html())+1);
				/*计算总钱数*/
				total();
				/*计算总钱数*/
				updateCart(cartCid,$(this).prev('.number').html());
			}else{
				var rmk='不能超出库存量'+stockAmount+'件';
				layer.open({
				    content:rmk
				    ,skin: 'msg'
				    ,time: 2 //2秒后自动关闭
				  });
			}
		})
		/*点击加一*/
		/*点击减一*/
		$('.btn1').click(function(){
			if($(this).next('.number').html()==0)
				$(this).next('.number').html(0);
			else
				if(parseFloat($(this).next('.number').html())==1){
					layer.open({
					    content:'受不了了，宝贝不能再减少了哦'
					    ,skin: 'msg'
					    ,time: 2 //2秒后自动关闭
					  });
				}else{
					var cartCid=$(this).next('.number').attr("id");
					$(this).next('.number').html(parseFloat($(this).next('.number').html())-1);	
					/*计算总钱数*/
					total();
					/*计算总钱数*/
					updateCart(cartCid,$(this).next('.number').html());
				}
				
		})
		/*点击减一*/
		$(".number").click(function(){
			/*$('.text1').css({"display":"flex","-webkit-display":"flex"}).attr({'ind':$(this).parents('li').index(),"ind_1":$(this).parents("ul").attr("ind")});
			$('.text1 input[type=number]').val($(this).html());*/
		})
		$('.text1 input[type="button"]').click(function(){
			if($('.text1 input[type=number]').val()==""){
				$('.alert').show().html('请输入数量！');
				 setTimeout(function(){$('.alert').hide();},2000);
				return false;
			}
			if ($('.text1 input[type=number]').val()>100) {
				$('.alert').show().html('超出库存了！');
				 setTimeout(function(){$('.alert').hide();},2000);
				return false;
			}
			$("ul").eq($('.text1').attr('ind_1')).find(".number").eq($('.text1').attr('ind')).html($('.text1 input[type=number]').val());
			$('.text1').css({"display":"none","-webkit-display":"none"});
			total();
		})
/*结算*/
$('.sett').click(function(){
	var entrance;
	var id = $(this).attr("id");
	if(id=="entrance1"){
		//购物车进入结算
		entrance=1;
	}else{
		//详情页直接结算
		entrance=2;
	}
	var amount=$("#amount").val();
	var idSub=$("#idSub").val();
	var total = $("#total").val();
	if(total!=null&&total>0){
		var url = projectName+"/h5/admin/cart/getSubmitOrder?entrance="+entrance+"&idSub="+idSub;
		if($("#a").val()==1){
			url+="&a=1&u="+$("#u").val();
		}
		window.location.href=url;
	}else{
		layer.open({
		    content:'请选中商品后再结算'
		    ,skin: 'msg'
		    ,time: 2 //2秒后自动关闭
		  });
	}
});
/*结算*/
/*删除*/
$('.delete').click(function(){
	var cartIdSub=[];
	$.each($('li'), function() {
		if ($(this).find("input[type=checkbox]").attr("checked")=="checked") {
			cartIdSub.push($(this).find("input[type=checkbox]").attr("id"));
			$(this).remove();
		}
	});
	if(cartIdSub.length>0){
		$('input[type=checkbox]').attr("checked","checked");
		$('input[type=checkbox]').next("img").attr("src",projectName+"/pages/mall/images/c_checkbox_on.png");
		$.each($(".content"), function() {
			if ($(this).find("li").length==0) {
				$(this).remove();
			}
		});
		
		hide();
		total();
		//删除购物车方法/cart/delete
		deleteCartIdSub(cartIdSub);
	}else{
	  		layer.open({
			    content: '请先选中商品后再删除'
			    ,skin: 'msg'
			    ,time: 2 //2秒后自动关闭
			  });
	}
});
/*删除*/
})
