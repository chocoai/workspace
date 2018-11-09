<header class="bar bar-nav zxf_header bgfff">
    <a class="icon icon-left pull-left close-popup" onclick="getBack()"></a>
    <h1 class="title">选择提醒的人</h1>
</header>
       <#--<div class="zxf_search_box pdlr075 pdtb05 bgfff clearfix">
            <div class="zxf_search bgfff w100 fl">
                <label class="icon_search" for="search"></label>
                <input type="search" autofocus="autofocus" id="search" onkeyup="getStudentName()" placeholder="搜索">
            </div>
        </div>-->
        <div class="zxf_navRight">
            <ul class="zxf_navR_list">
                <#if (aUserList?size>0) >
                    <li><a href="#a">A</a></li>
                </#if>
                <#if (bUserList?size>0) >
                    <li><a href="#b">B</a></li>
                </#if>
                <#if (cUserList?size>0) >
                    <li><a href="#c">C</a></li>
                </#if>
                <#if (dUserList?size>0) >
                    <li><a href="#d">D</a></li>
                </#if>
                <#if (eUserList?size>0) >
                    <li><a href="#e">E</a></li>
                </#if>
                <#if (fUserList?size>0) >
                    <li><a href="#f">F</a></li>
                </#if>
                <#if (gUserList?size>0) >
                    <li><a href="#g">G</a></li>
                </#if>
                <#if (hUserList?size>0) >
                    <li><a href="#h">H</a></li>
                </#if>
                <#if (iUserList?size>0) >
                    <li><a href="#i">I</a></li>
                </#if>
                <#if (jUserList?size>0) >
                    <li><a href="#j">J</a></li>
                </#if>
                <#if (kUserList?size>0) >
                    <li><a href="#k">K</a></li>
                </#if>
                <#if (lUserList?size>0) >
                    <li><a href="#l">L</a></li>
                </#if>
                <#if (mUserList?size>0) >
                    <li><a href="#m">M</a></li>
                </#if>
                <#if (nUserList?size>0) >
                    <li><a href="#n">N</a></li>
                </#if>
                <#if (oUserList?size>0) >
                    <li><a href="#o">O</a></li>
                </#if>
                <#if (pUserList?size>0) >
                    <li><a href="#p">P</a></li>
                </#if>
                <#if (qUserList?size>0) >
                    <li><a href="#q">Q</a></li>
                </#if>
                <#if (rUserList?size>0) >
                    <li><a href="#r">R</a></li>
                </#if>
                <#if (sUserList?size>0) >
                    <li><a href="#s">S</a></li>
                </#if>
                <#if (tUserList?size>0) >
                    <li><a href="#t">T</a></li>
                </#if>
                <#if (uUserList?size>0) >
                    <li><a href="#u">U</a></li>
                </#if>
                <#if (vUserList?size>0) >
                    <li><a href="#v">V</a></li>
                </#if>
                <#if (wUserList?size>0) >
                    <li><a href="#w">W</a></li>
                </#if>
                <#if (xUserList?size>0) >
                    <li><a href="#x">X</a></li>
                </#if>
                <#if (yUserList?size>0) >
                    <li><a href="#y">Y</a></li>
                </#if>
                <#if (zUserList?size>0) >
                    <li><a href="#z">Z</a></li>
                </#if>
            </ul>
        </div>
        <div class="content zxf_content infinite-scroll" data-distance="100">
            <div class="zxf_con-block">
                <div class="zxf_cstu_item_wp">
                    <#if (aUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="a">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">A</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list aUserList as user>
                                <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                    <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                    <span class="name fl">${user.name}</span>
                                </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (bUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="b">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">B</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list bUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (cUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="c">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">C</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list cUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (dUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="d">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">D</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list dUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (eUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="e">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">E</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list eUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (fUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="f">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">F</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list fUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (gUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="g">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">G</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list gUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (hUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="h">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">H</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list hUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (iUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="i">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">I</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list iUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (jUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="j">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">J</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list jUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (kUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="k">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">K</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list kUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (lUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="l">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">L</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list lUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (mUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="m">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">M</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list mUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (nUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="n">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">N</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list nUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (oUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="o">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">O</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list oUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (pUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="p">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">P</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list pUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (qUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="q">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">Q</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list qUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (rUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="r">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">R</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list rUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (sUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="s">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">S</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list sUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (uUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="u">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">U</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list uUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (vUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="v">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">V</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list vUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (wUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="w">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">W</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list wUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (xUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="x">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">X</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list xUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (yUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="y">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">Y</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list yUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                    <#if (zUserList?size>0) >
                    <div class="zxf_cstu_item zxf_selec_who" id="z">
                        <h4 class="f085 pdtb035 c_normal pdl075 pdr075 bgf0f0f0">Z</h4>
                        <div class="zxf_cstuCon bgfff">
                            <ul class="student_list pdl075">
                                <#list zUserList as user>
                                    <li class="clearfix f085 c_normal bb pdr075" id="${user.id?c}">
                                        <img src="${user.logoUrl}" alt="" class="fl mgr05">
                                        <span class="name fl">${user.name}</span>
                                    </li>
                                </#list>
                            </ul>
                        </div>
                    </div>
                    </#if>
                </div>
            </div>
        </div>
<script>
    //点击选中人后加入输入框
    $(".student_list>li").on("click", function () {
        var id = $(this).attr("id");
        txt = $(this).find('.name').text();
        //添加人名到输入框
        var $edit = $("#content");
        $edit.html($edit.html() + " @" + txt + " ")
        //选完人后元素复位操作
        $(".zxf_navRight").hide();
        $('.zxf_popup').animate({
            'bottom': '-100%'
        }, 500, function () {
        });
        //$('#content').focus();
        $("#addForm").append("<input type=\"hidden\" name=\"userId\" value=" + id + ">")
        setCaretPosition($('#content')[0], $('#content').html().length);
        $(".index-list-bar").remove()
        return;
    })

    function getStudentName(){
        var search = $("#search").val();
        if(search==""){
            getUsers()
        }else{
            $.ajax({
                dataType : "json",
                type:"get",
                url:"../wfd/page/manager/getPlateUser",
                data:{
                    plateId:plateId,
                    userName:search
                },
                async: true,
                success:function(data){
                    console.log(data.users.length)
                    $(".zxf_cstu_item_wp").empty();
                    var hmtl ='';
                    if(data.users.length>0){
                        hmtl +='<div class="zxf_cstu_item zxf_selec_who" id="z">' +
                                '<div class="zxf_cstuCon bgfff">' +
                                '                            <ul class="student_list pdl075">'
                        for(var i =0;i<data.users.length;i++){
                            hmtl +=' <li class="clearfix f085 c_normal bb pdr075" onclick="getTest(\''+data.users[i].id+'\',\''+data.users[i].name+'\')" id=\''+data.users[i].id+'\'>' +
                                    '    <img src=\''+data.users[i].logoUrl+'\'" alt="" class="fl mgr05">' +
                                    '    <span class="name fl">'+data.users[i].name+'</span>' +
                                    '</li>'
                        }
                        hmtl +='</ul>' +
                                ' </div></div>'
                        $(".zxf_cstu_item_wp").append(hmtl);
                    }

                }
            })
        }

    }
    
    function  getTest(id,name) {
        //添加人名到输入框
        var $edit = $("#content");
        $edit.html($edit.html() + " @" + name + " ")
        //选完人后元素复位操作
        $(".zxf_navRight").hide();
        $('.zxf_popup').animate({
            'bottom': '-100%'
        }, 500, function () {
        });
        //$('#content').focus();
        $("#addForm").append("<input type=\"hidden\" name=\"userId\" value=" + id + ">")
        setCaretPosition($('#content')[0], $('#content').html().length);
        $(".index-list-bar").remove()
        return;
    }
</script>