<%@page language="java" pageEncoding="utf-8"%>
<!-- JSTL -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- SPRING MVC -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div id="secondMenu">
	<ul class="nav nav-pills nav-stacked text-center">

		<c:forEach items="${resList }" var="res">
			<li><a href="${res.resUrl }" target="mainFrame"><span>${res.resName }</span></a></li>
		</c:forEach>

	</ul>
</div>


<script type="text/javascript">
	$(document).ready(function() {
		$("#secondMenu li a").click(function() {
			$("#secondMenu li").removeClass("active");
			$(this).parent().addClass("active");
		});
		
		$("#secondMenu ul li:eq(0) a span").click();
	});
</script>