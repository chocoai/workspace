function showImg5(el){
	$(el).each(function(){
		var imgs = $(this).attr("data-img").split(",")
		for(var i=0;i<imgs.length;i++){
			if(i >=5){
				break;
			}
			
			if(imgs[i]==''){
				continue;
			}
			
			var img = $("<img src='"+imgs[i]+"'/>");
			$(this).prepend(img);
		}
	})
}

showImg5(".ni_fun_tab_img");

$('.ni_fun_tab_img img').on('click', function () {
	var dia_imgs = null;
	
	var ni_fun_tab_img = $(this).parent(".ni_fun_tab_img");
	var igs = [];
	ni_fun_tab_img.each(function(){
	
		var imgs = $(this).attr("data-img").split(",")
		
		
		for(var i=0;i<imgs.length;i++){
			var json={src:imgs[i]}
			
			igs.push(json)
		}
	})
	
    var dialog = art.dialog({
        title: '截图',
        content: $('.ni_g_pop_show_imgs_list')[0],   //弹出框的内容
        width: '870px',
        padding:0,
        initialize:function(){
    		dia_imgs = new showImgList();
			dia_imgs.init(".ni_img_box",".ni_ul",".ni_next",".ni_prev",igs)
        },
    });
    return false;
})

//查看大图列表 
function showImgList(){

}
showImgList.prototype = {
	el_box:null,	//总元素
	el_img_box:null,//图片集父元素
	el_next:null,	//下一个，翻页按钮
	el_prev:null,	//上一个，翻页按钮
	el_time:null,	//图片时间
	el_all_num:null,//总图片数
	el_now_num:null,//当前图片数
	now_num:null,	//当前第几个
	imgs:null,		//图片集合对象		
	init:function(box,img_box,next,prev,imgs){
		this.el_box = $(box);
		this.el_img_box = this.el_box.find(img_box);
		this.el_next = this.el_box.find(next);
		this.el_prev = this.el_box.find(prev);
		this.el_time = this.el_box.find(".ni_time em");
		this.el_all_num = this.el_box.find(".ni_num .ni_all");
		this.el_now_num = this.el_box.find(".ni_num .ni_now");
		this.imgs = imgs;
		//重置
		this.off();
		//初始化图片列表，时间，页数
		this.el_img_box.html("");
		for(var i=0;i<this.imgs.length;i++){
			var $li = $("<li class='ni_li'><img src='"+this.imgs[i].src+"'/></li>")
			this.el_img_box.append($li);
		}
		this.el_time.html(this.imgs[0].time);
		this.el_all_num.html(this.imgs.length);
		this.el_now_num.html(1);
		this.now_num = 1;
		this.to_page(1);
		var _this = this;
		//绑定翻页事件
		this.el_prev.on("click",function(){
			_this.fun_prev();
		})
		this.el_next.on("click",function(){
			_this.fun_next();
		})
	},
	fun_prev:function(){
		if(this.now_num <=1){
			return;
		}else{
			this.to_page(--this.now_num);
		}
	},
	fun_next:function(){
		if(this.now_num >= this.imgs.length){
			return;
		}else{
			this.to_page(++this.now_num);
		}
	},
	to_page:function(num){
		var mgl = (num-1)*this.el_img_box.find("img").outerWidth()*-1;
		this.el_img_box.stop(true,true).animate({"margin-left":mgl},400);
		this.el_time.html(this.imgs[num-1].time);
		this.el_now_num.html(num);
		if(this.imgs.length <=1){
			this.el_prev.addClass("no_hover");
			this.el_next.addClass("no_hover");
		}
		else if(num <=1){
			this.el_prev.addClass("no_hover")
			this.el_next.removeClass("no_hover");
		}
		else if(num >= this.imgs.length){
			this.el_next.addClass("no_hover")
			this.el_prev.removeClass("no_hover");
		}

	},
	off:function(){
		this.el_prev.off("click");
		this.el_next.off("click");
		this.el_img_box.html("");
		this.el_img_box.css("margin-left","0px");
	}
}




$("#ni_g_btn").click(function(){
	$("#pageForm").submit();
});