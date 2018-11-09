<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
<img src="/images/home.png" width="19" height="24" />当前位置：
<a href="/workbench.htm">工作台</a> >
<a href="/dailyOfficeManager.htm">OA办公</a> >
<a href="#">信息登记-列表</a>
</div>
    <div id="content">
        <form action="/t_reso/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		
                     <div class="sub_add" onclick="javascript:addProject();">添加</div>
                     <div class="t_sub_delete" onclick="delProject();">删除</div><br/><br/>
                     <table style="width:100%;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >姓名</div></td>
                     <td style="width:60px"><input name="t_hremployee.name" type="text" value="${t_hremployee.name}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >毕业院校</div></td>
                     <td style="width:60px"><input name="shoolname" type="text" value="${shoolname}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >联系电话</div></td>
                     <td style="width:60px"><input name="t_hremployee.phone" type="text" value="${t_hremployee.phone}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:0px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;text-align:center;" >性别</div></td>
                     <td style="width:60px"><div >  <select name="t_hremployee.sex" style=" float:left;line-height:22px; width:100px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;">
					<option value="">全部</option>
					<option value="1" <#if  t_hremployee.sex==1>selected='selected'</#if> >男</option>
				    <option value="2" <#if  t_hremployee.sex==2>selected='selected'</#if> >女</option>
				    </select></div></td>
				    <td style="width:60px"><div class="sub_sear" onclick="javascript:$('#searchForm').submit() " style="margin:6px 0px 0px 0px">查询</div></td>
				    <td></td>
                     </tr>
                     </table>
                    
             </div>
         </form>
          <div style="clear:both;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:3%;background-color:#e6f6ff;"><p>序号 </p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:3%;background-color:#e6f6ff;"><p><input id="qxcheckbox"  type="checkbox"/></p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:7%;background-color:#e6f6ff;"><p>姓名</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:3%;background-color:#e6f6ff;"><p>性别</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:10%;background-color:#e6f6ff;"><p>出生年月</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:8%;background-color:#e6f6ff;"><p>学历</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:10%;background-color:#e6f6ff;"><p>专业</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:13%;background-color:#e6f6ff;"><p>毕业院校</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:10%;background-color:#e6f6ff;"><p>联系电话</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:10%;background-color:#e6f6ff;"><p>分配部门</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:12%;background-color:#e6f6ff;"><p>备注</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:17%;background-color:#e6f6ff;"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as t_hremployee>
                            		<input type="hidden" value="${t_hremployee.id}"/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(t_hremployee_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="text-align:center;"><input name="a" type="checkbox" autocomplete="off" value="${t_hremployee.id}" /><input type="hidden" value="${t_hremployee.state}" /></td>
                                          <td style="text-align:left;">${t_hremployee.name}</td>
                                          <td style="text-align:left;">
                                          <#if t_hremployee.sex ==1>男</#if>
                                          <#if t_hremployee.sex ==2>女</#if>
                                          </td>
                                          <td style="text-align:left;"><#if t_hremployee.birth !="">${t_hremployee.birth?string('yyyy-MM-dd')}</#if></td>
                                          <td style="text-align:left;">${t_hremployee.t_hreducation.education}</td>
                                          <td style="text-align:left;">${t_hremployee.major}</td>
                                          <td style="text-align:left;">${t_hremployee.t_hreducation.college}</td>
                                          <td style="text-align:left;">${t_hremployee.phone}</td>
                                          <td style="text-align:left;">${t_hremployee.dept_id.name}</td>
                                          <td style="text-align:left;">${t_hremployee.remark}</td>
                                          <td style="text-align:left;">
                                          <#if t_hremployee.state ==0 ><a class="a" onclick="becomeid(${t_hremployee.id})">转正</a></#if>
                                          <#if t_hremployee.state ==1||t_hremployee.state ==0 ><a class="b" onclick="dimissionid(${t_hremployee.id})">离职</a></#if>
                                          <#if t_hremployee.state ==2 ></#if>
                                          	 <a href="/t_reso/new.htm?t_hremployee.id=${t_hremployee.id}" title="编辑">编辑</a>
                                        	 <a href="/t_reso/show.htm?t_hremployee.id=${t_hremployee.id}" title="查看">查看</a>
                                          </td>
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="12">暂无数据!</td></tr>
   							</#if>
           </table>
               <br/>
              <div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
				</div>

    </div>
    <div >
<div class="b"  style="width: 300px; height: 100px; background:#ccc; display: none;  position:absolute; top:20%; left:40%;">
    <table style="border:1px solid red;width:100%;height:100%">
    <tr><td><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >转正日期:</div></td>
        <td><input id="becomeid"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" readonly= "true" style=" float:left;line-height:22px;  height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td></tr>
    <tr><td><input id="sss" type="hidden"/><button id="become" style="cursor:pointer">确认</button></td><td><button id="qx1" style="cursor:pointer">取消</button></td></tr>
    </table>
</div>
<div class="cc"  style="width: 300px; height: 100px; background:#ccc; display: none;  position:absolute; top:20%; left:40%;">
    <table style="border:1px solid red;width:100%;height:100%">
    <tr><td><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >离职日期:</div></td>
        <td><input id="dimissionid"  onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  type="text" readonly= "true" style=" float:left;line-height:22px;  height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td></tr>
    <tr><td><input id="zzz" type="hidden"/><button id="dimission" style="cursor:pointer">确认</button></td><td><button id="qx2" style="cursor:pointer">取消</button></td></tr>
    </table>
    
</div>
</div>
	<style>
    td{
    word-break: break-all;
    text-align:center;
    }
    </style>
    <script>
    
    $(function(){
    $("#qxcheckbox").click(function(){
		
		if($("#qxcheckbox").is(':checked')){
		$("[name='a']").attr("checked",'true');
		}else{
		$("[name='a']").removeAttr("checked",'true');
		}
		
		$("input[name='a']").click(function(){
		$("#qxcheckbox").removeAttr("checked",'true')
		});
		
		});
		
     $("#qx1").click(function(){
     $('div.b').slideToggle();
     });
     $("#qx2").click(function(){
     $('div.cc').slideToggle();
     });
    $("#become").click(function(){
    var ok=true;
   var becomeid=$("#sss").val();
   var fdate = $("#becomeid").val();
     if(fdate==""){
    alert("转正日期不能为空!");
    ok=false;
    }
     if(ok){
      	var url = "/t_reso/dimission.htm?characteristic=1&becomeid="+becomeid+"&fdate="+fdate;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('转正操作成功!');
					window.parent.location.reload();
					location.reload();
				} else {
					alert('转正操作失败!');
				}
			}
		});
		}
    });
    $("#dimission").click(function(){
    var ok=true;
   var dimissionid=$("#zzz").val();
   var qdate = $("#dimissionid").val();
     if(qdate==""){
    alert("离职日期不能为空!");
    ok=false;
    }
     if(ok){
      	var url = "/t_reso/dimission.htm?characteristic=2&dimissionid="+dimissionid+"&qdate="+qdate;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('离职操作成功!');
					window.parent.location.reload();
					location.reload();
				} else {
					alert('离职操作失败!');
				}
			}
		});
		}
    });
    });
   function becomeid(a){
   $("#sss").val(a);
   $('#becomeid').val("");
    $('div.b').slideToggle();
    }
   function dimissionid(a){
    $("#zzz").val(a);
   $('#dimissionid').val("");
    $('div.cc').slideToggle();
    }
    
    function addProject(){
    window.parent.location.href="/t_reso/new.htm";
    }
    function delProject() {
    if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
		var ok = true;
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
       if($(this).next().val()!=2){
       	ok = false;
       	alert("未离职，不能删除");
       	return false;
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
      if(ok){
		var url = "/t_reso/delete.htm?Stringid=" + id;
		$.ajax({
			type:"post",
			url:url,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
					location.reload();
				} else {
					alert('删除失败');
				}
			}
		});}
	}
	
    </script>