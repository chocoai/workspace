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
			d.add('${myCompany.id}','-1',"<input type='checkbox' id='t1' name='dept' onclick='javascript:addDept(\"${myCompany.id}\", \"${myCompany.name}\",this);' value='${myCompany.id}'></input>${myCompany.name}","",'${myCompany.name}','','','','true');
			<#list deptList as dept>
				d.add('${dept.id}','${dept.pid}',"<input type='checkbox' id='t1' name='dept' onclick='javascript:addDept(\"${dept.id}\", \"${dept.name}\",this);' value='${dept.id}'></input>${dept.name}","",'${dept.name}','','','','true');
			</#list>
			document.write(d);
		</script>
		
		<div>
			<input type="hidden" id="deptIds"/>
			<input type="hidden" id="deptNames"/>
			<a href="javascript:submit();">确定</a>
			<a href="javascript:window.close();">取消</a>
		</div>
	</body>
	<script>
		var deptIds = [];
		var deptNames = [];
		
		Array.prototype.indexOf = function(val) {
			for (var i = 0; i < this.length; i++) {
				if (this[i] == val) return i;
			}
			return -1;
		};
		
		Array.prototype.remove = function(val) {
			var index = this.indexOf(val);
			if (index > -1) {
				this.splice(index, 1);
			}
		};
		
		function addDept(deptId, deptName, o){
			deptIds = $("#deptIds").val().split(",");
			deptNames = $("#deptNames").val().split(",");
			
			if(o.checked){
				if(jQuery.inArray(deptId,deptIds) < 0){
					deptIds.push(deptId);
					deptNames.push(deptName);
				}
			}else{
				deptIds.remove(deptId);
				deptNames.remove(deptName);
			}
			$("#deptIds").val(deptIds.join());
			$("#deptNames").val(deptNames.join());
		}
		
		function submit(){
			deptIds = $("#deptIds").val().split(",");
			deptNames = $("#deptNames").val().split(",");
			deptIds.shift();
			deptNames.shift();
			var obj = {deptId:deptIds,deptName:deptNames};
			window.opener.doAfterGetMultiDept(obj);
			window.close();
		}
	</script>
</html>
