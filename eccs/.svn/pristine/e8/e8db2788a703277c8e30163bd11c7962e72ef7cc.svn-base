<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>工程咨询云工作平台</title>
		<link type="text/css" rel="stylesheet" href="/css/base.css"/>
		<link type="text/css" rel="stylesheet" href="/js/dtree/dtree.css">
		<script src="/js/jquery.js"></script>
		<script src="/js/dtree/dtree.js"></script>
	</head>

	<body class="zh14">
		<script type="text/javascript">
			var d = new dTree('d');
			d.add('${myCompany.id}','-1','${myCompany.name}',"javascript:addDept('${myCompany.id}', '${myCompany.name}')",'${myCompany.name}','','','','true');
			<#list deptList as dept>
				d.add('${dept.id}','${dept.pid}','${dept.name}',"javascript:addDept('${dept.id}','${dept.name}')",'','','','','true');
			</#list>
			document.write(d);
		</script>
	</body>
	<script>
		function addDept(id, name) {
			var obj = {deptId:id,deptName:name};
			window.opener.doAfterGetDept(obj);
    		window.close();
		}
	</script>
</html>
