var startLine=0;
var sortType=1;
//置顶列表
 $.ajax({
	type : "post",
	async : true,
	url : "getTopPlatePostList",
	data : {
		plateId : $("#plateId").val()
	},
	dataType : "json",
	success : function(result) {
		$(".zxf_subject_intro ul").html();
			if (result != null) {
				var list = result.list;
				var htmlStr = '';
				for (var i = 0; i < list.length; i++) {
					htmlStr += '<li class="clearfix pdlr075 bb">'
				    htmlStr +=   '<a href="javascript:;" class="go_top zxf_bor_blueBtn fl">置顶</a>'
				    htmlStr +=   '<p class="des">'+list[i].content+'</p>'
				    htmlStr += '</li>'
				}
					$(".zxf_subject_intro ul").html(htmlStr);
			}
	},
	error : function(errorMsg) {
	}
})

function loadPost(){
	//帖子列表
	 $.ajax({
		type : "post",
		async : true,
		url : "platePostList",
		data : {
			plateId : $("#plateId").val(),
			startLine : startLine,
			sortType : sortType
		},
		dataType : "json",
		success : function(result) {
			var html = $(".zxf_stu_item_wp").html();
				if (result != null) {
					var list = result.list;
					var htmlStr = '';
					
					if(list.length==0){
						//$('.preloader').html('已经滑动到底部');
						$.detachInfiniteScroll($('.infinite-scroll'));
						$('.infinite-scroll-preloader').remove();
					}
					
					for (var i = 0; i < list.length; i++) {
						var object = list[i]
						
						var postImgList = object.postImgList;
						
						htmlStr += '<div class="zxf_stu_item pd075 mgb05 bgfff">'
						htmlStr +=  '<div class="clearfix">'
						htmlStr +=      '<img src="images/img_stu1.png" alt="" class="fl zxf_stu_img">'
						htmlStr +=      '<div class="zxf_stu_text">'
						htmlStr +=        '<h2 class="clearfix">'
						htmlStr +=         '<p class="fl">'+object.userName +'</p>'
						htmlStr +=          '<span class="fr subject">'+object.plateName+'</span>'
						htmlStr +=        '</h2>'
						htmlStr +=        '<p class="zxf_visit_info">'
						htmlStr +=          '<span>'+object.relativeCreateTime+'</span>'
						htmlStr +=          '<em class="mglr05">|</em>'
						htmlStr +=         '<span><em class="num">'+object.viewCount+'</em>人浏览</span>'
						htmlStr +=       ' </p>'
						htmlStr +=      '</div>'
						htmlStr +=  '</div>'
						htmlStr +=  '<div class="zxf_des_wp mgt05">'
						htmlStr +=    '<p>'
						htmlStr +=       ''+object.content+''
						htmlStr +=   '</p>'
						htmlStr +=  '</div>'
						htmlStr +=  '<div class="zxf_img_Box mgt05">'
						htmlStr +=    '<a href="javascript:;" class="photo_btn">共<em class="num">'+object.imgCount+'</em>'+postImgList.length+'</a>'
						
						var imgStr ='';
						
						for(var j=0;j<postImgList.length;j++){
							var postImg = postImgList[j]
							imgStr += '<img src="'+postImg.downUrl+'" alt="">';
						}
						
						htmlStr +=  imgStr
						htmlStr +=  '</div>'
						htmlStr +=  '<div class="mgt05 zxf_user_response bt clearfix">'
						htmlStr +=    '<a href="javascript:;" class="zxf_comment_btn fl">'
						htmlStr +=      '<i class="icon_comment mgr025"></i>'
						htmlStr +=      '<em>'+object.messageCount+'</em>'
						htmlStr +=    '</a>'
						htmlStr +=    '<a href="javascript:;" class="zxf_like_btn fr">'
						htmlStr +=      '<i class="icon_like"></i>'
						htmlStr +=      '<em>'+object.likeCount+'</em>'
						htmlStr +=    '</a>'
						htmlStr +=  '</div>'
						htmlStr += '</div>'
					}
					$(".zxf_stu_item_wp").html(html+=htmlStr);
				}
		},
		error : function(errorMsg) {
		}
	})
}

loadPost();

$(document).on("click",".refuse_btn",function(){
	 startLine=0;
	sortType=2;
	$(".zxf_stu_item_wp").html('');
	loadPost();
});

$(document).on("click",".send_btn",function(){
	startLine=0;
	sortType=1;
	$(".zxf_stu_item_wp").html('');
	loadPost();
});

