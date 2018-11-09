<body>
<link href="/css/main1.css" rel="stylesheet" type="text/css" />
<script src="/js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<div id="main">
  <div id="map">
  <img src="/images/home.png" width="19" height="24" />当前位置：
  <a href="/workbench.htm">工作台</a> >
  <a href="/performanceManager.htm">绩效管理</a> >
  <a href="/performance/list.htm">绩效列表</a> >
  <a href="javascript:window.location.reload();">绩效表操作</a>
  </div>
    <div id="jixiao">
 <form action="/performance/save.htm" method="get" id="contractForm">
  <table border="1" cellspacing="1" cellpadding="1" class="input_table">
     <input name="id1" value="${project.id}" type="hidden" />
    <input name="id" value="${performance.id}" type="hidden" />
  <tr><td colspan="12" style=" font-weight:bold;font-size:16px;text-align:center;">绩效提成单</td></tr>
  <tr >
        <td colspan="2" style=" text-align:center;"width="110">项目名称</td>
        <td colspan="4" style=" text-align:center;"width="110"><input type="text"  value="${project.name}" readonly="readonly"  style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110">报告文号</td>
        <td colspan="4" style=" text-align:center;"width="110"><input type="text"  name="performance.wenhao" readonly="readonly" value="${performance.wenhao}" style="width:100%;"/></td>
      </tr>
      <tr>
        <td colspan="2" style=" text-align:center;"width="110">委托单位</td>
        <td colspan="2" style=" text-align:center;"width="110"><input type="text"  value="${project.customer.cusName}" readonly="readonly"  style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110">存档时间</td>
        <td colspan="2" style=" text-align:center;"width="110"><input type="text" readonly="readonly" name="performance.ctime" value="${performance.ctime}" style="width:100%;" readonly /></td>
        <td colspan="2" style=" text-align:center;"width="110">档案签收人签字</td>
        <td colspan="2" style=" text-align:center;"width="110"><input type="text" readonly="readonly" name="performance.signmen" value="${performance.signmen}" style="width:100%;"/></td>
      </tr>
      <tr>
       <td colspan="2" style=" text-align:center;"width="110">工作内容</td>
       <td colspan="10" style=" text-align:left;"width="110">
        <#list annexTypeList  as annexType>
       <input name="performance.job" type="checkbox" disabled="disabled" <#if performance.job != null && performance.job?contains('${annexType.id}') >checked='checked'</#if>  value="${annexType.id}" />&nbsp;${annexType.name}&nbsp;&nbsp;&nbsp;&nbsp;
     	</#list>
       </td>
      </tr>
      <tr>
       <td colspan="2" style=" text-align:center;"width="110">咨询合同编号</td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performance.contractno" value="${performance.contractno}" style="width:100%;"/></td>
      	<td colspan="2" style=" text-align:center;"width="110">按合同应收费（元）</td>
        <td colspan="1" style=" text-align:center;"width="110"><input type="text" readonly="readonly" name="performance.charge" value="${performance.charge}"  style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110">是否到帐</td>
        <td colspan="1" style=" text-align:center;"width="110"><input type="text" readonly="readonly" name="performance.daozhang"  value="${performance.daozhang}"  style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110">应收费及到帐情况核实（经营部）</td>
        <td colspan="1" style=" text-align:center;"width="110"><input type="text" readonly="readonly" name="performance.prove" value="${performance.prove}"  style="width:100%;"/></td>
      </tr>
      <tr>
        <td colspan="2" style=" text-align:center;"width="110">可分配执业奖（元）</td>
        <td colspan="2" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performance.bbonus"  value="${performance.bbonus}" style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110">项目分配调整系数</td>
        <td colspan="2" style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performance.coefficient"  value="${performance.coefficient}" style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110">调整后可分配金额（元）</td>
        <td colspan="2" style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performance.abonus" value="${performance.abonus}" style="width:100%;"/></td>
      </tr>
      <tr>
        <td colspan="2" style=" text-align:center;"width="110">项目组成员</td>
        <td colspan="1" style=" text-align:center;"width="110">  
        <input name="userid1"  id="userid1" type="hidden" value="${performanceUser1.user.id}"/>
		<input name="" id="username1" readonly value="${performanceUser1.user.name}" onclick="getUser('userid1','username1')" type="text" style="text-align:center; width:100%;"/>
		</td>
        <td colspan="1" style=" text-align:center;"width="110">
        <input name="userid2"  id="userid2"  type="hidden"  value="${performanceUser2.user.id}" />
		<input name="" id="username2" readonly  value="${performanceUser2.user.name}" onclick="getUser('userid2','username2')" type="text" style="text-align:center; width:100%; "/>
        </td>
        <td colspan="1" style=" text-align:center;"width="110">
        <input name="userid3"  id="userid3"  type="hidden"   value="${performanceUser3.user.id}"/>
		<input name="" id="username3" readonly  value="${performanceUser3.user.name}" onclick="getUser('userid3','username3')" type="text" style="text-align:center; width:100%;"/>
        </td>
        <td colspan="1" style=" text-align:center;"width="110">
        <input name="userid4"  id="userid4"   type="hidden"  value="${performanceUser4.user.id}" />
		<input name="" id="username4" readonly  value="${performanceUser4.user.name}" onclick="getUser('userid4','username4')" type="text" style="text-align:center; width:100%;"/>
        </td>
        <td colspan="1" style=" text-align:center;"width="110">
        <input name="userid5"  id="userid5" type="hidden" value="${performanceUser5.user.id}"/>
		<input name="" id="username5" readonly  value="${performanceUser5.user.name}" onclick="getUser('userid5','username5')" type="text" style="text-align:center; width:100%;"/>
        </td>
        <td colspan="1"style=" text-align:center;"width="110">
        <input name="userid6"  id="userid6"    type="hidden"  value="${performanceUser6.user.id}" />
		<input name="" id="username6" readonly  value="${performanceUser6.user.name}" onclick="getUser('userid6','username6')" type="text" style="text-align:center; width:100%;"/>
        </td>
        <td colspan="1"style=" text-align:center;"width="110">
        <input name="userid7"  id="userid7"    type="hidden"   value="${performanceUser7.user.id}"/>
		<input name="" id="username7" readonly  value="${performanceUser7.user.name}" onclick="getUser('userid7','username7')" type="text" style="text-align:center; width:100%;"/>
        </td>
        <td colspan="1"style=" text-align:center;"width="110">
        <input name="userid8"  id="userid8"   type="hidden"   value="${performanceUser8.user.id}"/>
		<input name="" id="username8" readonly  value="${performanceUser8.user.name}" onclick="getUser('userid8','username8')" type="text" style="text-align:center; width:100%;"/>
        </td>
        <td colspan="1"style=" text-align:center;"width="110">
        <input name="userid9"  id="userid9"   type="hidden"  value="${performanceUser9.user.id}" />
		<input name="" id="username9" readonly  value="${performanceUser9.user.name}" onclick="getUser('userid9','username9')" type="text" style="text-align:center; width:100%;"/>
        </td>
        <td colspan="1"style=" text-align:center;"width="110">
        <input name="userid10"  id="userid10"   type="hidden"   value="${performanceUser10.user.id}" />
		<input name="" id="username10" readonly  value="${performanceUser10.user.name}" onclick="getUser('userid10','username10')" type="text" style="text-align:center; width:100%;"/>
        </td>
      </tr>
      <tr>
        <td colspan="2" style=" text-align:center;"width="110">完成本项目所用时间（实用天数，非时间跨度）</td>
        <td colspan="1"style=" text-align:center;"width="110">  <input type="type" name="performanceUser1.ctime" value="${performanceUser1.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser2.ctime" value="${performanceUser2.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser3.ctime"  value="${performanceUser3.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser4.ctime"  value="${performanceUser4.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser5.ctime"  value="${performanceUser5.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser6.ctime"  value="${performanceUser6.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser7.ctime"  value="${performanceUser7.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser8.ctime"  value="${performanceUser8.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" name="performanceUser9.ctime" value="${performanceUser9.ctime}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110">  <input type="type" name="performanceUser10.ctime" value="${performanceUser10.ctime}" style="width:100%;"/></td>
      </tr>
      <tr>
       <td colspan="2" style=" text-align:center;"width="110">分配比例</td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser1.fenpeiproportion" value="${performanceUser1.fenpeiproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser2.fenpeiproportion" value="${performanceUser2.fenpeiproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser3.fenpeiproportion" value="${performanceUser3.fenpeiproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly"  name="performanceUser4.fenpeiproportion" value="${performanceUser4.fenpeiproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser5.fenpeiproportion" value="${performanceUser5.fenpeiproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser6.fenpeiproportion" value="${performanceUser6.fenpeiproportion}"  style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser7.fenpeiproportion" value="${performanceUser7.fenpeiproportion}"  style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser8.fenpeiproportion" value="${performanceUser8.fenpeiproportion}"  style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser9.fenpeiproportion" value="${performanceUser9.fenpeiproportion}"  style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly"  name="performanceUser10.fenpeiproportion" value="${performanceUser10.fenpeiproportion}" style="width:100%;"/></td>
      </tr>
      <tr >
         <td colspan="2" style=" text-align:center;"width="110">核定比例</td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser1.hedingproportion" value="${performanceUser1.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly"  name="performanceUser2.hedingproportion" value="${performanceUser2.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser3.hedingproportion" value="${performanceUser3.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser4.hedingproportion" value="${performanceUser4.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser5.hedingproportion" value="${performanceUser5.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly"  name="performanceUser6.hedingproportion" value="${performanceUser6.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser7.hedingproportion" value="${performanceUser7.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser8.hedingproportion" value="${performanceUser8.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performanceUser9.hedingproportion" value="${performanceUser9.hedingproportion}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser10.hedingproportion" value="${performanceUser10.hedingproportion}" style="width:100%;"/></td>
      </tr>
      <tr rowspan="3">
        <td colspan="1" rowspan="3" style=" text-align:center;"width="110">执业奖</td>
        <td colspan="1" style=" text-align:center;"width="110">总额</td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser1.sum" value="${performanceUser1.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser2.sum" value="${performanceUser2.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser3.sum" value="${performanceUser3.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser4.sum" value="${performanceUser4.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser5.sum" value="${performanceUser5.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser6.sum" value="${performanceUser6.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser7.sum" value="${performanceUser7.sum}"  style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser8.sum" value="${performanceUser8.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser9.sum" value="${performanceUser9.sum}" style="width:100%;"/></td>
        <td colspan="1"style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser10.sum" value="${performanceUser10.sum}" style="width:100%;"/></td>
        </br>
      </tr>
      <tr>
        <td colspan="1" style=" text-align:center;"width="110">其中：存档后发放  %</td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser1.archive" value="${performanceUser1.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser2.archive" value="${performanceUser2.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser3.archive" value="${performanceUser3.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser4.archive" value="${performanceUser4.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser5.archive" value="${performanceUser5.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser6.archive" value="${performanceUser6.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser7.archive" value="${performanceUser7.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser8.archive" value="${performanceUser8.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser9.archive" value="${performanceUser9.archive}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser10.archive" value="${performanceUser10.archive}" style="width:100%;"/></td></br>
      </tr>
      <tr>
       <td colspan="1" style=" text-align:center;"width="110"> 到帐后发放   %</td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly"  name="performanceUser1.daozhang" value="${performanceUser1.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser2.daozhang" value="${performanceUser2.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser3.daozhang" value="${performanceUser3.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser4.daozhang" value="${performanceUser4.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser5.daozhang" value="${performanceUser5.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser6.daozhang" value="${performanceUser6.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser7.daozhang" value="${performanceUser7.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser8.daozhang" value="${performanceUser8.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser9.daozhang" value="${performanceUser9.daozhang}" style="width:100%;"/></td>
        <td colspan="1" style=" text-align:center;"width="110"> <input type="type"  readonly="readonly" name="performanceUser10.daozhang" value="${performanceUser10.daozhang}" style="width:100%;"/></td>
      </tr>
      <tr>
          <td colspan="1" style=" text-align:center;"width="110"> 备注</td>
          <td colspan="1" style=" text-align:center;"width="110">项目概况（栋数、层数、建筑面积、执业时间跨度等）、其他情况说明</td>
     	 <td colspan="10"style=" text-align:center;"width="110"> 
     	 <textarea rows="6" name="performance.remark"  readonly="readonly" style="width:100%; border:none;">${performance.remark}</textarea>
     	 </td>
      </tr>
      <tr>
        <td colspan="2" style=" text-align:center;"width="110"> 编制：    </td>
        <td colspan="2"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performance.compilemen" value="${performance.compilemen}"  style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110"> 审核： </td>
        <td colspan="2"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performance.auditmen" value="${performance.auditmen}"  style="width:100%;"/></td>
        <td colspan="2" style=" text-align:center;"width="110">   批准：</td>
        <td colspan="2"style=" text-align:center;"width="110"> <input type="type" readonly="readonly" name="performance.approval" value="${performance.approval}" style="width:100%;"/></td>
      </tr>
      <tr>
      <td colspan="12" width="110">
      <input type="button" class="sub" onclick="javascript:history.go(-1);" value="返回"/>
      </td>
      </tr>
      </table>
   </form>
  </div>
</div>
<script src="/js/jquery.metadata.js" type="text/javascript"></script>
<script type="text/javascript">
</script>
</body>
