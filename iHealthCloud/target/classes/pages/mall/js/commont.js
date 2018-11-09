var pathName=window.document.location.pathname;
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var u = navigator.userAgent.toLowerCase();
var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
var weixin=isWeiXin();
var mobile = u.indexOf('mobile') > -1;//移动手机端
$(function(){
    // 返回顶部
    $(window).scroll(function(){
	        if($(window).scrollTop()>=20){
	           $(".gototop").fadeIn(300);
	        }else{
	           $(".gototop").fadeOut(300);
	        }
    })
    $(".gototop a").click(function(){
      	$("html,body").animate({scrollTop:"0px"},300)
    })
    
    //支付方式
    $(".ordersPay").click(function() {
    	var orderNumber = $(this).attr("id");
    	var type = $(this).attr("lang");
    	if(!orderNumber){
    		layer.open({
    		    content: '订单号不存在'
    		    ,skin: 'msg'
    		    ,time: 2 //2秒后自动关闭
    		});
    		return;
    	}
    	$("#orderNumber").val(orderNumber);
    	$.ajax({
    		url : projectName+"/h5/admin/pay/getOrderNoOvertime",
    		data : {
    			orderNo : orderNumber,
    			type : type
    		},
    		dataType : "json",
    		success : function(result) {
    			if(result.result==0){
    				layer.open({
    				    content: '操作失败'
    				    ,skin: 'msg'
    				    ,time: 2 //2秒后自动关闭
    				});
    			}else if(result.result==1){
    				if(result.num>30){
    					layer.open({
    				    content: '订单已超过30分钟，请重新下单。'
    				    ,skin: 'msg'
    				    ,time: 2 //2秒后自动关闭
    				  });
    				}else{
    					$("#ordersPay").modal("show");
    				}
    			}else{
    				layer.open({
    				    content: '此订单状态已完成支付'
    				    ,skin: 'msg'
    				    ,time: 2 //2秒后自动关闭
    					  });
    				}
    			}
    		})
    })
    //支付宝支付
    $("#small").click(function() {
    	var type = $("#type").val();
    	var orderNo = $("#orderNumber").val();
    	var url="";
    	if(weixin){
			window.location.href=projectName+"/h5/pay/gotobrowser?type="+type+"&cext1="+orderNo;
		}else{
			if(!mobile){
				if(type=="goods"){
					window.location.href=projectName+"/h5/pay/aliApi/webYwOrderPay?cext1="+orderNo;
				}else{
					window.location.href=projectName+"/h5/pay/aliApi/webWorkOrderPay?cext1="+orderNo;
				}
			}else{
				if(type=="goods"){
					window.location.href=projectName+"/h5/pay/aliApi/ywOrderPay?cext1="+orderNo;
				}else{
					window.location.href=projectName+"/h5/pay/aliApi/workOrderPay?cext1="+orderNo;
				}
			}
		}
    })
    //微信支付
    $("#weixin").click(function() {
    	var type = $("#type").val();
    	var orderNumber = $("#orderNumber").val();
    	var url="";
    	if(!mobile){
			layer.open({
			    content: '不支持PC端微信支付'
			    ,skin: 'msg'
			    ,time: 2 //2秒后自动关闭
			  });
		}else{
	    	if(weixin){
	    		WeixinJSBridgeReady(type,orderNumber);
			}else{
				if(type=='goods'){
		    		wxYwOrderWxPay(orderNumber);
		    	}else{
		    		wxWordOrderWxPay(orderNumber);
		    	}
			}
		} 
    })
	
	//订单取消
    $(".orderDel").click(function() {
		  var type = $("#type").val();
		  var orderid = $(this).attr("id");
		  $.ajax({
			  url : projectName+"/h5/admin/myOrdersDel/"+type,
				data : {
					cid: orderid
				},
				dataType : "json",
				async : false,
				traditional : true,
				cache : true,
				success : function(data) {
					if(data.status==202){
						window.location.href=projectName+"/h5/login";
					}else if(data.status==1){
						layer.open({
							  content: '取消成功',
							  skin: 'msg',
							  time: 2 ,//2秒后自动关闭
							  end: function () {
								  window.location.reload();
					          }
						 });
					}else{
						layer.open({
						    content: '取消失败'
						    ,skin: 'msg'
						    ,time: 2 //2秒后自动关闭
					   });
					}
				}
		  })
	  })
	
})

//微信支付商品调起
  function wxYwOrderWxPay(cext1){
  	$.ajax({
		url : projectName+"/h5/pay/wx/ywOrderWxPay",
		data : {
			cext1 : cext1
		},
		dataType : "json",
		success : function(result) {
			if(result.map.return_code=="SUCCESS"){
				window.location.href=result.map.mweb_url;
			}
		}
	});
  }

//微信支付服务预约调起
function wxWordOrderWxPay(cext1){
	$.ajax({
		url : projectName+"/h5/pay/wx/workOrderWxPay",
		data : {
			cext1 : cext1
		},
		dataType : "json",
		success : function(result) {
			if(result.map.return_code=="SUCCESS"){
				window.location.href=result.map.mweb_url;
			}
		}
	});
}

function isWeiXin(){
    if(u.match(/MicroMessenger/i) == 'micromessenger'){
        return true;
    }else{
        return false;
    }
}


function WeixinJSBridgeReady(type,cext1) {
	 try {
        if (typeof WeixinJSBridge == "undefined") {
			if (document.addEventListener) {
				document.addEventListener('WeixinJSBridgeReady', onBridgeReady(type,cext1), false);
			} else if (document.attachEvent) {
				document.attachEvent('WeixinJSBridgeReady', onBridgeReady(type,cext1));
				document.attachEvent('onWeixinJSBridgeReady', onBridgeReady(type,cext1));
			}
		} else {
			onBridgeReady(type,cext1);
		}
    } catch (e) {
        alert(e);
    }
    window.event.returnValue = false;
    return false;
}

function onBridgeReady(type,cext1) {
	var url = projectName + "/h5/pay/wx/ywOrderWeixinJSBridgePay";
	if(type!="goods"){
		url = projectName + "/h5/pay/wx/wxWorkOrderWeixinJSBridge";
	}
	$.ajax({
		url : url,
		data : {
			cext1 : cext1
		},
		dataType : "json",
		success : function(result) {
			WeixinJSBridge.invoke(
				'getBrandWCPayRequest', {
					"appId" : result.appId, //公众号名称，由商户传入     
					"timeStamp" : result.timeStamp, //时间戳，自1970年以来的秒数     
					"nonceStr" : result.nonceStr, //随机串     
					"package" : result.package,
					"signType" : result.signType, //微信签名方式：     
					"paySign" : result.paySign //微信签名 
				},
				function(res) {
					if (res.err_msg == "get_brand_wcpay_request:ok") {
						setTimeout(getPaydone(cext1),1000);
					}else if(res.err_msg == "get_brand_wcpay_request:fail"){
						layer.open({
							content : '支付失败',
							skin : 'msg',
							time : 2 //2秒后自动关闭
						});
					}else{
						layer.open({
							content : '支付已取消',
							skin : 'msg',
							time : 2 //2秒后自动关闭
						});
					}  
				}
			);
		}
	});

}

function getPaydone(cext1){
	var type = $("#type").val();
	if (type == "goods") {
		type = 1;
	} else if (type == "service") {
		type = 2;
	}
	window.location.href = projectName + "/h5/admin/pay/getPaydone?type=" + type + "&result=1&cext1=" + cext1;
}
