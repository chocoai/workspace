<header class="bar bar-nav">
    <a class="icon icon-left pull-left close-popup" onclick="getBack()"></a>
    <h1 class="title">选择提醒的人</h1>
</header>
    <div class="content">
        <div class="list-block contacts-block">
            <div class="list-group">
                <ul>
            <#if (aUserList?size>0) >
                <li class="list-group-title">A</li>
                <#list aUserList as user>
                    <li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
                </#list>
            </#if>

          	<#if (bUserList?size>0) >
                <li class="list-group-title">B</li>
                <#list bUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
                </#list>
            </#if>

           <#if (cUserList?size>0) >
               <li class="list-group-title">C</li>
               <#list cUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (dUserList?size>0) >
               <li class="list-group-title">D</li>
               <#list dUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

          <#if (eUserList?size>0) >
              <li class="list-group-title">E</li>
              <#list eUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
              </#list>
          </#if>

           <#if (fUserList?size>0) >
               <li class="list-group-title">F</li>
               <#list fUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (gUserList?size>0) >
               <li class="list-group-title">G</li>
               <#list gUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (hUserList?size>0) >
               <li class="list-group-title">H</li>
               <#list hUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (iUserList?size>0) >
               <li class="list-group-title">I</li>
               <#list iUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (jUserList?size>0) >
               <li class="list-group-title">J</li>
               <#list jUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (kUserList?size>0) >
               <li class="list-group-title">K</li>
               <#list kUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (lUserList?size>0) >
               <li class="list-group-title">L</li>
               <#list lUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (mUserList?size>0) >
               <li class="list-group-title">M</li>
               <#list mUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (nUserList?size>0) >
               <li class="list-group-title">N</li>
               <#list nUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (oUserList?size>0) >
               <li class="list-group-title">O</li>
               <#list oUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (pUserList?size>0) >
               <li class="list-group-title">P</li>
               <#list pUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (qUserList?size>0) >
               <li class="list-group-title">Q</li>
               <#list qUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

            <#if (rUserList?size>0) >
                <li class="list-group-title">R</li>
                <#list rUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
                </#list>
            </#if>

           <#if (sUserList?size>0) >
               <li class="list-group-title">S</li>
               <#list sUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (tUserList?size>0) >
               <li class="list-group-title">T</li>
               <#list tUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (uUserList?size>0) >
               <li class="list-group-title">U</li>
               <#list uUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (vUserList?size>0) >
               <li class="list-group-title">V</li>
               <#list vUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (wUserList?size>0) >
               <li class="list-group-title">W</li>
               <#list wUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (xUserList?size>0) >
               <li class="list-group-title">X</li>
               <#list xUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (yUserList?size>0) >
               <li class="list-group-title">Y</li>
               <#list yUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>

           <#if (zUserList?size>0) >
               <li class="list-group-title">Z</li>
               <#list zUserList as user>
					<li id="${user.id?c}">
                        <div class="item-content">
                            <div class="item-inner">
                                <div class="item-title">${user.name}</div>
                            </div>
                        </div>
                    </li>
               </#list>
           </#if>
                </ul>
            </div>
        </div>
    </div>
	<script>
        $(".contacts-block").indexList();
        //点击选中人后加入输入框
        $(".item-content").on("click", function () {
            var id = $(this).parent("li").attr("id");
            txt = $(this).find('.item-title').text();
            //添加人名到输入框
            var $edit = $("#content");
            $edit.val($edit.val() + " @" + txt + " ")
            //选完人后元素复位操作
            $(".zxf_navRight").hide();
            $('.zxf_popup').animate({
                'bottom': '-100%'
            }, 500, function () {
            });
            $('#content').focus();
            $("#addForm").append("<input type=\"hidden\" name=\"userId\" value=" + id + ">")
            setCaretPosition($('#content')[0], $('#content').val().length);
            $(".index-list-bar").remove()
            return;
        })
    </script>
