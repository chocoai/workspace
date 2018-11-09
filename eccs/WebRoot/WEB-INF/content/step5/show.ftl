<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.validate.js" type="text/javascript"></script>
<script src="/js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/projectManager.htm">项目管理</a> >
<#if showShare==1><a href="/project/listShare.htm">共享列表</a> ></#if>
<#if showPro==0><a href="/project/myProjectList.htm">我的项目</a> ></#if>
<#if project!=null><a href="javascript:history.go(-1);">${project.name}</a> ></#if>
 <a href="javascript:window.location.reload();">勘察记录</a>
 </div>
  <form action="/step5/save.htm" method="post" id="contractForm">  
	<div id="content">
		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			施工现场勘察记录
		</div>
 <table border="1" cellspacing="1" cellpadding="1" class="input_table">
	  <!-- <tr>
	    	<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">施工现场勘察记录</td>
	  </tr> -->
	  <tr>
		    <td width="13%" class="tab_title">工程名称</td>
		    <td width="29%" style="background-color:#fff;">${project.name}</td>
		    <td width="21%" class="tab_title">工程地点</td>
		    <td width="37%" style="background-color:#fff;">${step5.projectAddress}</td>
	  </tr>
	  <tr>
			<td width="13%" class="tab_title" align="right" style="font-weight:bold;" height="30"><font size="2">勘察结果</font></td>
		    <td colspan="3" style="background-color:#fff;">
		  			<#list step5LogoList as step5Logo>
		  					<font size="2">${step5Logo.ctime}：[${step5Logo.user.name}]&nbsp;&nbsp;${step5Logo.logoNext}</font><br>
		    		</#list>
		    </td>
	   </tr>
	  <tr>
		    <td  class="tab_title">勘察结果确认</td>
			<td colspan="3">
					<table border="1" cellspacing="1" cellpadding="1" class="input_table">
						<tbody >
								<tr style="background-color:#fff;">
									<td width="40%"  style=" text-align:center; font-weight:bold;">单位</td>
									<td width="40%"  style=" text-align:center; font-weight:bold;">法人代表</td>
								</tr>
								<#if step5ItemList!=null && step5ItemList?size!=0>
									<#list step5ItemList as step5Item>
											<tr>
												<td style="text-align:center;background-color:#fff;">${step5Item.company}</td>
												<td style="text-align:center;background-color:#fff;">${step5Item.companyRen}</td>
											</tr>
									</#list>
								<#else>
											<tr height="24px">
												<td style="text-align:center;background-color:#fff;"></td>
												<td style="text-align:center;background-color:#fff;"></td>
											</tr>
											<tr height="24px">
												<td style="text-align:center;background-color:#fff;"></td>
												<td style="text-align:center;background-color:#fff;"></td>
											</tr>
											<tr height="24px">
												<td style="text-align:center;background-color:#fff;"></td>
												<td style="text-align:center;background-color:#fff;"></td>
											</tr>
								</#if>	
						</tbody >
						
					</table>
				</td>
			</tr>
	        <tr>
		        <td colspan="7" style=" text-align:right;">
		       		 <input type="button"  onclick="location.href ='javascript:history.back();';" value="返回" class="sub"/>                                           
		        	 <input type="button"  onclick="javascript:toPrint(${project.id});" value="打印" class="sub"/>                               
		        </td>
	   	    </tr>  
</table>
    </div>
</form>
<script>
	function toPrint(id){
		var url = "/step5/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>