

$(document).on("touchend",".zxf_big_bg_blueBtn",function(){
	var userId = $("#userId").val();
	location.href = "create.html?userId="+userId;
});

$(document).on("click",".fwn",function(){
	var userId = $("#userId").val();
	var plateId = $(this).attr("id");
	location.href = "edit.html?userId="+userId+"&plateId="+plateId;
});



$(document).on("click",".zxf_editBtn",function(){
	var userId = $("#userId").val();
	var plateId = $(this).attr("id");
	location.href = "create.html?userId="+userId+"&plateId="+plateId;
});

$(document).on("touchend",".icon_add_tag",function(){
	var userId = $("#userId").val();
	location.href = "create.html?userId="+userId;
});


$(document).on("click",".zxf_delBtn",function(){
	var plateId = $(this).attr("id");
 	
	var userId = $("#userId").val();
	$.modal({
        title:  '提示',  
        text:'删除版块后其中的帖子等内容都会被删除',            
        buttons: [
          {
            text: '取消',
            onClick: function() {
            
            }
          },
          {
            text: '删除版块',
            onClick: function() {
            	$.ajax({
            		type : "post",
            		async : true,
            		url : "delete",
            		traditional:true,
            		data : {
            			plateId : plateId
            		},
            		dataType : "text",
            		success : function(msg) {
            			location.href = "list.html?userId="+userId;
            		},
            		error : function(errorMsg) {
            		}
            	})
            }
          }
        ]
      })
});

