

$("#ni_g_btn").click(function(){
	$("#pageForm").submit();
});

$("#ni_g_export").click(function(){
	var startTime = $("#d4311").val();
	var endTime = $("#d4312").val();
	window.location.href='exportReport?startTime='+startTime+'&endTime='+endTime;
});


function loadList2() {
	$.ajax({
		type : "post",
		async : true,
		url : "queryTeacherUseLog",
		data : {
			pageNum: $("#pageNum").val(),
			pageSize: $("#pageSize").val(),
			startTime: $("#d4311").val(),
			endTime: $("#d4312").val()
		},
		dataType : "json",
		success : function(data) {
			var htmlStr = '';
			$("#teacherUseLogPage").html('');
			if (data != null) {
				if (data.list != null && data.list.length > 0) {
					for (var i = 0; i < data.list.length; i++) {
						var result = data.list[i];
						var useTaking = formatSeconds(result.use_taking);
						htmlStr += '<tr>';
						htmlStr += '<td>' + (i + 1) + '</td>';
						htmlStr += '<td>' + result.user_name + '</td>';
						htmlStr += '<td>' + result.subject_name + '</td>';
						htmlStr += '<td>' + result.org_name + '</td>';
						htmlStr += '<td>' + useTaking + '</td>';
						htmlStr += '<td>' + result.count + '</td>';
						htmlStr += '<td><a href="queryTeacherUseLogDetail.html?userName='+result.user_name+'&userId='+result.user_id+'">查看</a></td>';
						htmlStr += '</tr>';
					}
				}
				if (data.page != null) {
					setPage2($("#teacherUseLogPage"), data.page);
				}
				$("#teacherUseLog").html(htmlStr);
			}
		},
		error : function(errorMsg) {
		}
	})
}


function loadList3() {
	$.ajax({
		type : "post",
		async : true,
		url : "queryDeviceUseCount",
		data : {
			pageNum: $("#pageNum").val(),
			pageSize: $("#pageSize").val(),
			startTime: $("#d4311").val(),
			endTime: $("#d4312").val()
		},
		dataType : "json",
		success : function(data) {
			var htmlStr = '';
			$("#deviceUseCountPage").html('');
			if (data != null) {
				if (data.list != null && data.list.length > 0) {
					for (var i = 0; i < data.list.length; i++) {
						var result = data.list[i];
						
						var useTaking = formatSeconds(result.use_taking);
						
						htmlStr += '<tr>';
						htmlStr += '<td>' + (i + 1) + '</td>';
						htmlStr += '<td>' + result.name + '</td>';
						htmlStr += '<td>' + result.class_room_name + '</td>';
						htmlStr += '<td>' + result.use_count + '</td>';
						htmlStr += '<td>' + useTaking + '</td>';
						htmlStr += '<td>' + result.user_name + '</td>';
						htmlStr += '<td>' + result.user_use_count + '</td>';
						htmlStr += '<td><a href="queryDeviceUseCountDetal.html?deviceCode='+result.device_code+'&deviceName='+result.name+'">查看</a></td>';
						htmlStr += '</tr>';
					}
				}
				if (data.page != null) {
					setPage3($("#deviceUseCountPage"), data.page);
				}
				$("#deviceUseCount").html(htmlStr);
			}
		},
		error : function(errorMsg) {
		}
	})
}

$(document).ready(function() {
	loadList2();
	loadList3();
});