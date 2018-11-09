/* Documentation sample */


function addPage(page, book) {//添加页面

    var id, pages = book.turn('pages');
    var element = $('<div />', {});
    if (book.turn('addPage', element, page)) {
        var browser=navigator.userAgent;
        var b_version=browser.split(";");
        var version=b_version[1].trim().split(" ")[1];
        if(page==pages-1){
            if(browser.indexOf('MSIE')>0&&parseInt(version)<=9){
                element.addClass('page_cover last2');
            }else{
                element.addClass('hard page_cover last2');
            }
        }else if(page==pages){
            if(browser.indexOf('MSIE')>0&&parseInt(version)<=9)
            {
                element.addClass('page_cover last1');
            }else{
                element.addClass('hard page_cover last1');
            }
        }
    }
}


function getViewNumber(book, page) {
    return parseInt((page || book.turn('page')), 10);
}

function moveBar(yes) {
    if (Modernizr && Modernizr.csstransforms) {
        $('#slider .ui-slider-handle').css({zIndex: yes ? -1 : 10000});
    }
}

function setPreview(view,pageNum) {
    var previewHeight = 30,
        preview = $(_thumbPreview.children(':first'));
        preview.css({
            height: previewHeight,
            lineHeight:previewHeight+'px',
            display:'block',
            textAlign:'center'
        })
    if(view==1){
        preview.text('封面');
    }else if(view==pageNum-1||view==pageNum){
        preview.text('封底(本书共'+(pageNum-4)+'页)');
    }else{
        preview.text((view-2)+'/'+(pageNum-4));
    }
    _thumbPreview.addClass('no-transition').css({
        width: 'auto',
        height: 'auto',
        top: -previewHeight-20,
        left: ($($('#slider').children(':first')).width()-115) / 2
    });
}
