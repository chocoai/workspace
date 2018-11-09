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
 <a href="javascript:window.location.reload();">接收资料登记</a>
 </div>
<form action="/step4/save.htm" method="post" id="contractForm">  
  	<div id="content">
  		<div style="text-align:center; font-weight:bold;font-family:Microsoft YaHei;font-size:20px;margin-top:20px;margin-bottom:20px;">
			建设工程造价咨询相关资料整理单
		</div>
  		<table border="1" cellspacing="1" cellpadding="1" class="input_table">
  		<!-- <tr>
    		<td colspan="4" style=" font-weight:bold; font-size:20px; text-align:center;">建设工程造价咨询相关资料整理单</td>
    	</tr> -->
  		<tr>
		    <td width="15%" class="tab_title">工程名称</td>
		    <td colspan="3" style="background-color:#fff;">
		   	${project.name}
		    </td>
   		 </tr>
  		<tr>
		    <td class="tab_title">委托单位</td>
		    <td width="34%" style="background-color:#fff;">${project.customer.cusName}</td>
		    <td width="16%" class="tab_title">咨询类别</td>
		    <td width="35%" style="background-color:#fff;">
			${project.serviceType.name} 
    		 </td>
  		</tr>
  		<tr>
				<td width="15%" class="tab_title">
					软件、规范等
				</td>
				<td colspan="3" style="background-color:#fff;">${step4.standard}</td>
			</tr>
			<tr>
				<td width="15%" class="tab_title">
					其他相关资料
				</td>
				<td colspan="3" style="background-color:#fff;">${step4.other}</td>
		</tr>
  		<tr>
		    <td colspan="4" style=" text-align:center; font-weight:bold;color:red;">委托方于<label for="input"></label>
		   	${step4.senderDataDate }
			日将下列资料交给受委托方：${step4.receiverUnit }</td>
   		 </tr>
  		<tr>
    		<td colspan="4"  style="padding: 0px 0px;">
   	 <table border="1" cellspacing="1" cellpadding="1" class="list_table4"  style="margin: 0px auto;border-width:0px; border-style:hidden;">
		  <tr>
		    <td style=" text-align:center; font-weight:bold;"  width="15%;">序号</td>
		    <td style=" text-align:center; font-weight:bold;"  width="70%;">资料名称</td>
		    <td style=" text-align:center; font-weight:bold;"  width="15%;">份数</td>
		  </tr>
	 <#list  numList as num >
		  <tr >
		    <td  style=" text-align:center;background-color:#fff;">${(num_index)+1}</td>
		    <td style="background-color:#fff; text-align:center;">${num.name }</td>
		    <td style="background-color:#fff; text-align:center;">${num.count}</td>
		  </tr>
	</#list>
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
		var url = "/step4/print.htm?project.id=" + id;
		window.open(url,"","height=700,width=900,top=100,left=50,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no");
	}
</script>