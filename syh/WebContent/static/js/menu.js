$(function(){
  // ++++++++++++++++++++++++++++++++++++++++++
  // 1.菜单
  // ++++++++++++++++++++++++++++++++++++++++++
  $(".level01 > a").click(function(){
      if($(this).next().css("display") == "none"){
          $(this).next().show()
              .parent(".level01").siblings().find(".level02").hide();
          $(this).css("border-bottom","1px solid #ddd")
              .parent(".level01").siblings().children("a").css("border-bottom","none");
          $(this).find(".icon-arrow").removeClass("icon-angle-right").addClass("icon-angle-down")
              .parents(".level01").siblings().find(".icon-arrow").removeClass("icon-angle-down").addClass("icon-angle-right")
      }else{
          $(this).next().hide();
          $(this).find(".icon-arrow").removeClass("icon-angle-down").addClass("icon-angle-right");
          $(this).css("border-bottom","none");
      };
      return false;
      
  })
})