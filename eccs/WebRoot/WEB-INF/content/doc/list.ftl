<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="javascript:void(0);">我的文档</a>
</div>
<input type="hidden" name="name" id="name" value="${uptype.name}">
<form action="/doc/list.htm" method="post" id="searchForm">
	<input type="hidden" name="id" id="id" value="${uptype.id}">
</form>
<div class="about">
	<div class="left" style="height:500px;">
		<link type="text/css" rel="stylesheet" href="/js/dtree/dtree.css">
		<script src="/js/dtree/dtree.js"></script>
		<script type="text/javascript">
			var d = new dTree('d');
			
			d.add('0','-1','我选择的类型',"",'','','','');
			<#list upty as uptype>
			d.add('${uptype.id}','0','${uptype.name}',"javascript:search('${uptype.id}')",'','','','');
			</#list>
			document.write(d);
		</script>
	</div>
	<form action="/doc/list.htm" method="post" id="searchForm1">
		<div class="list_table stripe" style=" float:left;width:70%; min-width:700px;">
			<div class="article" style="margin:0; clear:both; width:100%; margin-left:20px;">
				<a href="javascript:addUpty()"><div class="sub_add">添加类型</div></a>
				<a href="javascript:editUpty()"><div class="sub_add">编辑类型</div></a>
				<a href="javascript:deleteUpty()"><div class="sub_add">删除类型</div></a>
				<a href="javascript:addDoc()"><div class="sub_add">添加文件</div></a>
				<div>
					<input name="id" value="${uptype.id}" type="hidden" />
					<input name="mix" id="mix" placeholder="请输入文件名或备注" value="${mix}" type="text" style="float: left; line-height: 22px; width: 300px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 20px 0px 20px;" />
				</div>
				<div class="sub_sear" onclick="javascript:$('#searchForm1').submit()">查询</div>
			</div>
		</div>
	</form>
	<table border="1" cellspacing="1" cellpadding="1" class="list_table stripe" style=" float:left;width:70%; min-width:700px; margin-left:20px;margin-bottom:10px;">
		<tr class="head">
			<td style=" background-color:#e6f6ff; text-align:center; width:30px;">
				<p>编号</p>
			</td>
			<td width="62" style=" background-color:#e6f6ff; text-align:center;width:300px;">
				<p>文件名</p>
			</td>
			<td width="80" style=" background-color:#e6f6ff; text-align:center;">
				<p>上传日期</p>
			</td>
			<td width="80" style=" background-color:#e6f6ff; text-align:center;">
				<p>类型</p>
			</td>
			<td style=" background-color:#e6f6ff; text-align:center;width:50px;">
				<p>大小</p>
			</td>
			<td width="192" style=" background-color:#e6f6ff; text-align:center;width:200px;">
				<p>备注</p>
			</td>
			<td width="120" style=" background-color:#e6f6ff; text-align:center;">
				<p>操作</p>
			</td>
		</tr>
		<#list pageBean.list as du>
		<tr>
			<td width="44" style="text-align:center;"><p>${du_index + 1}</p></td>
			<td><p>${du.name}</p></td>
			<td style="text-align:center;"><p>${du.ctime?string("yyyy-MM-dd")}</p></td>
			<td style="text-align:center;"><p>${du.uptype.name}</p></td>
			<td style="text-align:center;"><p>${du.size}KB</p></td>
			<td style="text-align:center;"><p>${du.remark}</p></td>
			<td style="text-align:center;">
				<p>
					<a href="javascript:deleteUser(${du.id})" title="删除">删除</a>
					<a href="/doc/xiazai.htm?ids=${du.id}">下载</a>
				</p>
			</td>
		</tr>
		</#list>
	</table>
	<div style="margin-left: 20px;float: left;margin-top: 10px;">
		<#import "/WEB-INF/tld/page.ftl" as tt>
		<@tt.page pageBean=pageBean formId="searchForm1" />
	</div>
</div>
<script>
	function search(id) {
		$("#id").val(id);
		$("#searchForm").submit();
	}
	
	function deleteUser(duId) {
		if (!confirm("确定删除此文件")) {
			return;
		}
		$.ajax({
			type:"get",
			url:'/doc/delete.htm?ids=' + duId,
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
				if (d == '1') {
					location.href=location.href;
				} else {
					alert('删除失败');
				}
			}
		})
	}
	
	function addUpty() {
		  	   var iWidth=400;                          //弹出窗口的宽度; 
		       var iHeight=200;                         //弹出窗口的高度; 
		       //获得窗口的垂直位置 
		       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
		       //获得窗口的水平位置 
		       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
			   window.open('/doc/newuptype.htm','新增类型','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	
	function editUpty() {
		  var	id = $("#id").val();
		  var iWidth=400;                          //弹出窗口的宽度; 
	       var iHeight=200;                         //弹出窗口的高度; 
	       //获得窗口的垂直位置 
	       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
	       //获得窗口的水平位置 
	       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open('/doc/edituptype.htm?id=' + id,'编辑类型','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	
	function deleteUpty() {
		var name = $('#name').val();
		var id = $('#id').val();
		if (!confirm("确定删除 " + name)) {
			return;
		}
		$.ajax({
			type:"get",
			url:'/doc/deleteuptype.htm?id=' + id,
			asyn:true,		//false为同步提交
			dataType:'text',	//返回文本
			success:function(d){
				if (d == '1') {
					location.href=location.href;
				} else {
					alert('删除失败');
				}
			}
		})
	}
	
	function addDoc() {
		   var iWidth=600;                          //弹出窗口的宽度; 
	       var iHeight=300;                         //弹出窗口的高度; 
	       //获得窗口的垂直位置 
	       var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
	       //获得窗口的水平位置 
	       var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;  
		   window.open('/file/fileDoc.htm','新增文件','height=' + iHeight + ',innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=no, resizable=no,titlebar=no');
	}
</script>