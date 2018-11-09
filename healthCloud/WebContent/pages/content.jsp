<%@page language="java" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title></title>

<jsp:include page="/pages/base/base.jsp"></jsp:include>


<style type="text/css">
#left, #right, #openClose {
	float: left;
}

#openClose.close {
	background-position: 1px center;
}

#openClose, #openClose.close {
	background: #fdfdfd url(/healthCloud/res/img/openclose.png) no-repeat
		-29px center;
}

#openClose {
	width: 8px;
	margin: 0 1px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container-fluid" style="padding-left: 0px; padding-right: 0px;">
		<div class="row-fluid">
			<div id="left" class="collapse in" style="width: 160px;">
				<jsp:include page="menu.jsp"></jsp:include>
			</div>
			<div id="openClose" class="close" data-toggle="collapse" data-target="#left">&nbsp;</div>
			<div id="right" style="padding-left: 0px;">
				<iframe id="mainFrame" name="mainFrame" src="" scrolling="yes" frameborder="0"></iframe>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#menu li a").click(function() {
				$("#menu li").removeClass("active");
				$(this).parent().addClass("active");
			})
		});
		
		$("#menuFrame,#openClose,#mainFrame").height(window.innerHeight - 5);
		$("#mainFrame").width(window.innerWidth - $("#openClose").width() - $("#left").width()-5);
		
		$('#left').on('show.bs.collapse', 
			function () {
				$("#left").width(160);
				$("#mainFrame").width(window.innerWidth - $("#openClose").width() - $("#left").width()-5);
			}
		)
		
		$('#left').on('hide.bs.collapse', 
			function () {
				$("#left").width(0);
				$("#mainFrame").width(window.innerWidth - $("#openClose").width()-5);
			}
		)
		
	</script>


</body>
</html>