<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>工程咨询云工作平台</title>
		<link rel="stylesheet" href="/css/base.css"/>
		<script src="/js/jquery.js"></script>
	</head>

	<body class="zh14">
		<link type="text/css" rel="stylesheet" href="/js/dtree/dtree.css">
		<script src="/js/dtree/dtree.js"></script>
		<script type="text/javascript">
			var d = new dTree('d');
			d.add('${myDept.id}','-1','${myDept.name}',"javascript:addDept('${myDept.id}', '${myDept.name}')",'${myDept.name}','','','');
			<#list deptList as dept>
				d.add('${dept.id}','${dept.pid}','${dept.name}',"javascript:addDept('${dept.id}','${dept.name}')",'','','','');
			</#list>
			document.write(d);
		</script>
	</body>
	<script>
		function addDept(id, name) {
			window.returnValue = id + "," + name;
    		window.close();
		}
	</script>
</html>
