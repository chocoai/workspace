<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教学助手</title>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
<link href="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/css/jx_zhushou.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="g_header">
  <div class="m_banner">
    <div class="w1200 pos_rel">
      <p class="p_btnbox pos_abs"> <a href="http://jxzs.tianyuyun.com:21215/wecourse/api/downloadclient/downclient" class="download_btn_2">免费下载</a></p>
      <p class="banner_text pos_abs"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/banner_text.png"  alt="教学助手" /></p>
      <p class="computer pos_abs"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/coputer.png"/></p>
      <div class="user_num">
      	<span class="num dis_none">${userCount}</span>
        <ul>
        	
        </ul>
        <span class="tips">位老师正在使用</span>
      </div>
      
    </div>
  </div>
</div>
<div class="g_content">
  <div class="m_section1">
    <div class="pos_rel w1200">
      <h3 class="tit"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/title_1.png"/></h3>
      <ul class="cont">
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico1.png"/> <span>课前导学</span> </li>
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico2.png"/> <span>同步备课</span> </li>
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico3.png"/> <span>互动课堂</span> </li>
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico4.png"/> <span>随堂检测</span> </li>
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico5.png"/> <span>课后作业</span> </li>
      </ul>
    </div>
  </div>
  <div class="m_section2">
    <div class="pos_rel w1200">
      <h3 class="tit"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/title_2.png"/></h3>
       <p class="cont"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/cont_img1.png"/></p>
    </div>
  </div>
  <div class="m_section3">
    <div class="pos_rel w1200">
      <h3 class="tit"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/title_3.png"/></h3>
      <p class="cont"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/cont_img2.png"/></p>
    </div>
  </div>
  <div class="m_section4">
    <div class="pos_rel w1200">
      <h3 class="tit"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/title_4.png"/></h3>
      <p class="cont"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/cont_img3.png"/></p>
    </div>
  </div>
  <div class="m_section5">
    <div class="pos_rel w1200">
      <h3 class="tit"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/title_5.png"/></h3>
      <ul class="cont">
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico6.png"/> <span>学科工具</span> </li>
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico7.png"/> <span>数字教材</span> </li>
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico8.png"/> <span>基础资源</span> </li>
        <li><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico9.png"/> <span>互动试题</span> </li>
        <li style="margin-right:0px;"><img src="http://www.wuhaneduyun.cn:21001/tianyu_edu_dev/area/888888/images/jx_zhushou/img_ico10.png"/> <span>教学模板</span> </li>
      </ul>
    </div>
  </div>
</div>
<script type="text/javascript">
	var userNum = {
		oNum : $('.user_num .num'),
		oUl : $('.user_num ul'),
		oTips : $('.user_num .tips'),
		numArr : [],
		minlen : 7,
		init:function(){
			this.numArr = this.oNum.html().split('');
			var _this = this;
			var len = _this.minlen-_this.numArr.length;
			for(var i=0; i<len; i++){
				_this.numArr.unshift(0);
			}
			var str = '';
			$.each(_this.numArr,function(index,arritem){
				str += '<li><p><span>0</span><span>1</span><span>2</span><span>3</span><span>4</span><span>5</span><span>6</span><span>7</span><span>8</span><span>9</span></p></li>'
			});
			_this.oUl.append(str);
			_this.scrollFn();
		},
		scrollFn:function(){
			var _this = this;
			_this.oUl.find('li').each(function(i){
				ScrollP(i)
			});
			function ScrollP(num){
				var oP = _this.oUl.find('li').eq(num).find('p');
				oP.stop().animate({top:-_this.numArr[num]*41},_this.numArr[num]*200);
			}
		}
	}
	userNum.init()
</script>

</body>
</html>