<#-- 自定义的分页指令。
作者：充满智慧的威哥
属性说明：
	pageBean分页bean
	formId	要提交哪个form里的数据（把此form里的所有input复制到pageController里提交）如果为空则只有pageNo传递到后台
	divId	ajax分页，指明要刷新的divId   
	url		交给哪个action处理
	style	样式选择 1有总数量 2无总数量
ps:
	formId 和 url 至少填写一个
	如果divId有值则为ajax分页，反之为一般分页
	ajax分页需要jquery支持
 使用方式：
	<#import "/WEB-INF/tld/page.ftl" as sw>
	<@sw.page pageBean=pageBean formId="" divId="" url="/demo/fmIndex.htm"/>
 -->

<#macro page pageBean formId="" divId="" url="" style="1">
	<link href="${path}/css/page.css" rel="stylesheet" type="text/css" />
	<#-- 定义局部变量  pageNo pageSize totalCount -->
	<#assign pageNo = pageBean.pageNo>
	<#assign pageSize = pageBean.pageSize>
	<#assign totalCount = pageBean.totalCount>
	<#-- 定义局部变量pageCount保存总页数 -->
	<#assign pageCount = ((totalCount + pageSize - 1) / pageSize)?int>  
	<#if totalCount == 0><#return/></#if>
	<#-- 页号越界处理 -->
	<#if (pageNo > pageCount)>
		<#assign pageNo = pageCount>
	</#if>
	<#if (pageNo < 1)>
		<#assign pageNo = 1>
	</#if>
	<#-- 输出分页表单 -->
	<div class="pagination">
		<form id="pageController" name="pageController" method="get">
			<#assign pageNum = 9><#-- 分页控件中显示多少页 -->
			<a href="javascript:turnToPage(1)" class="show pre-nex"><span>首 页</span></a>
			<#-- 上一页处理 -->
			<#if (pageNo != 1)>
				<a href="javascript:turnToPage(${pageNo-1})" class="show pre-nex"><span>上一页</span></a>
			</#if>
			<#-- 显示页数范围 -->
			<#assign start = 1>
			<#assign end = pageCount>
			<#-- 总页数大于显示页数 --> 
			<#if (pageCount > pageNum) >
				<#assign start = (pageNo - pageNum / 2 + 1) >
				<#assign end = (pageNo + pageNum / 2) >
				<#if (start < 1) >
					<#assign start = 1 >
					<#assign end = (pageNum - start + 1) >
				</#if>
				<#if (end > pageCount) >
					<#assign end = pageCount >
					<#assign start = (pageCount - pageNum + 1) >
				</#if>
			</#if>
			<#-- 显示当前页号和它附近的页号 -->
			<#list start..end as i>
		    	<#if (pageNo == i)>
					<div class="show current">${i}</div>
				<#else>
					<a href="javascript:turnToPage(${i})" class="show nocurrent"><span>${i}</span></a>
				</#if>
			</#list>
			<#-- 下一页处理 -->
			<#if (pageNo != pageCount)>
				<a href="javascript:turnToPage(${pageNo+1})" class="show pre-nex"><span>下一页</span></a>
		  	</#if>
		  	<a href="javascript:turnToPage(${pageCount})" class="show pre-nex"><span>尾  页</span></a>
		  	<div class="page-count zfd">
				<span>共</span>
				<span class="b">${totalCount}</span>
				<span>条</span>
			</div>
		</form>
	</div>
	
	<script language="javascript">
	function turnToPage(pageNo) {
		var url = "${url}";
		var formId = "${formId}";
		var divId = "${divId}";
		var pageCount = "${pageCount}";
		
		if (formId.length == 0 && url.length == 0) {
			alert("分页标签参数错误");
			return;
		}
		
		var targetForm = null; //目标form
		var method = null; //提交方式 get post
		if (formId.length > 0) {
			targetForm = document.getElementById(formId);
			method = targetForm.method;
		}
		var pageForm = document.getElementById('pageController'); //分页form
		if (method != null && method != '') {
			pageForm.method = method;
		}
		
		//页号是否越界
		if(pageNo > pageCount) {
	    	pageNo = pageCount;
	    }
	    if(pageNo < 1) {
	    	pageNo = 1;
	    }

		//使用url还是form的地址
	    if (url.length > 0) {
	    	pageForm.action = url;
	    } else {
	    	pageForm.action = targetForm.action;
	    }
	    
	    //得到url中传递的参数
	    if (url.indexOf('?') != -1) {
	    	var params = url.split('?')[1];
	    	var paramList = params.split('&');
	    	var name = "";
	    	var value = "";
	    	for (i = 0; i < paramList.length; i++) {
	    		name = paramList[i].split("=")[0];
	    		value = paramList[i].split("=")[1];
	    		if (name != "") {
	    			//动态生成控件并添加到分页form里
	    			addInputControl(pageForm, null, name, value);
	    		}
	    	}
	    }
	    
	    //添加pageNo到表单中
		addInputControl(pageForm, "pageNo", "pageNo", pageNo);
		
		//得到目标form里所有的控件
		if (targetForm != null) {
			//input集合
			var inputList = targetForm.getElementsByTagName("input");
			for (i = 0; i < inputList.length; i++) {
				var id = inputList[i].id;
				var name = inputList[i].name;
				var value = inputList[i].value;
				var type = inputList[i].type.toLowerCase();
				//过滤控件name为空的情况
				if (name == null || name == "") {
					continue;
				}
				//过滤值为空的情况
				if (value == null || value == "") {
					continue;
				}
				//如果有pageNo的控件则不添加
				if (name == "pageNo") {
					continue;
				}
				//button
				if (type == "button" || type == "submit" || type == "reset") {
					continue;
				}
				//radio没被选中
				if (type == "radio" && !inputList[i].checked) {
					continue;
				}
				//checkbox没被选中
				if (type == "checkbox" && !inputList[i].checked) {
					continue;
				}
				//动态生成控件并添加到分页form里
				addInputControl(pageForm, id, name, value);
			}
			//select集合
			var selectList = targetForm.getElementsByTagName("select");
			for (i = 0; i < selectList.length; i++) {
				var id = selectList[i].id;
				var name = selectList[i].name;
				var value = selectList[i].value;
				addInputControl(pageForm, id, name, value);
			}
		}
		
		if (divId.length == 0) {
			pageForm.submit(); // 一般提交
		} else {
			submitByAjax(divId);// ajax提交
		}
	}
	
	//ajax提交
	function submitByAjax(divId) {
	    var s = $("#pageController");
	    var url = s[0].action;
	    var method = s[0].method;
	    var data = s.serialize();
	    $.ajax({
	    	url:url,
	    	type:method,
	    	async:true,
	    	data:data,
	    	beforeSend: function() {
	    		$('#load').show(); //等待加载的图标
			},
	    	success:function(d) {
	    		$("#" + divId).html(d);
	    	}
	    })
	}
	
	//给表单添加input控件
	function addInputControl(form, id, name, value) {
		var inputControl = document.createElement("input");
		if (id != null && id != '') {
			inputControl.setAttribute("id", id);
		}
		inputControl.setAttribute("name", name);
		inputControl.setAttribute("value", value);
		inputControl.setAttribute("type", "hidden");
		form.appendChild(inputControl);
	}
	
	</script>
</#macro>