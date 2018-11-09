$(function(){
  // ++++++++++++++++++++++++++++++++++++++++++
  // 1.双向选择
  // ++++++++++++++++++++++++++++++++++++++++++
    var j_all_area = $("#sel_all_area"), j_selected_areas = $("#sel_selected_areas");
        $("#btn_select_all_area").click(function(){
             j_all_area.find("option").each(function(){
                 $(this).appendTo(j_selected_areas);
             });
             return false;
         });
        $("#btn_choose_selected_area").click(function(){
            
             j_all_area.find("option:selected").each(function(){
                 $(this).appendTo(j_selected_areas);
             });
             return false;
        });
         $("#btn_remove_selected_area").click(function(){
             j_selected_areas.find("option:selected").each(function(){
                 $(this).appendTo(j_all_area);
             });
             return false;
         });
         $("#btn_remove_all_area").click(function(){
             j_selected_areas.find("option").each(function(){
                 $(this).appendTo(j_all_area);
             });
             j_selected_areas.find("option").each(function(){
                 $(this).appendTo(j_all_area);
             });
             return false;
         });
         j_all_area.find("option").on("dblclick", function(){
             if ($(this).closest("select").is(j_all_area)) {
                 $("#btn_choose_selected_area").click();
             }
             else {
                 $("#btn_remove_selected_area").click();
            }
             
             return false;
         });
         j_selected_areas.find("option").on("dblclick", function(){
             if ($(this).closest("select").is(j_all_area)) {
                 $("#btn_choose_selected_area").click();
             }
             else {
                $("#btn_remove_selected_area").click();
            }
             
            return false;
         });

         var selectedAreaArray = [];
         $("#sel_selected_areas option").each(function(i){
         		selectedAreaArray[i] = $(this).val();
         });

})