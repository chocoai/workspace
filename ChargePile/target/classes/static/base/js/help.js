$(document).ready(function() {
	//var username = '${SESSION_USER.username }';
	var username = SESSION_USER_username;
	if(username!=null&&username!="") {
		//alert(username);
		$("#not_login").hide();
		$("#login_in").show();
	} else {
		$("#not_login").show();
		$("#login_in").hide();
	}
});

$(window).scroll(function(){
	   if($(window).scrollTop() > 80) {
      $('#index-navbar').addClass('fixed-top');
  }
  else {
  	 $('#index-navbar').removeClass('fixed-top');
  }
	})
	

//自动目录生成
$.MagicNav = function($nav, $navTo, options) {
    var magicNav = this;
    var defaults = {
        titles: 'text',
        ease: function(x, t, b, c, d) {
            if ((t /= d / 2) < 1) return c / 2 * t * t + b;
            return -c / 2 * ((--t) * (t - 2) - 1) + b;
        },
        duration: 1000,
        offset: 0
    };

    var i,
        numItems = $navTo.length;
    magicNav.settings = $.extend({}, defaults, options);

    var getTitle = magicNav.settings.titles === 'text' ?
        function($el) {
            return $.trim($el.text());
        } :
        function($el) {
            return $el.attr(magicNav.settings.titles);
        };

    var navMarkup = '';
    var firstLevel = '';
    for (i = 0; i < numItems; i++) {
        if(!i){
            firstLevel = $navTo.eq(i)[0].tagName.toLowerCase()
        }
        if(($navTo.eq(i)[0].tagName.toLowerCase() == firstLevel) || ($navTo.eq(i).hasClass('faq-title')) || ($navTo.eq(i).hasClass('ref-title'))){
            navMarkup += '<a class="magicnav-link">' + getTitle($navTo.eq(i)) + '</a>';
        }else{
            navMarkup += '<a class="magicnav-link" style="margin-left:60px;font-size:12px">' + getTitle($navTo.eq(i)) + '</a>';
        }

    }
    $nav.html(navMarkup);
    $('.magicnav-link').on('click', function() {
        $('html,body').animate({
                scrollTop: $navTo.eq($(this).index()).offset().top + magicNav.settings.offset
            },
            magicNav.settings.duration,
            magicNav.settings.ease);
    });
};

$.MagicNav($('.help-dir-content'),$('.help-content h1,.help-content h2,.help-content h3,.help-content h4'),{});
