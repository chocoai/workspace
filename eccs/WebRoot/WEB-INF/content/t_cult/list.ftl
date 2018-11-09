   <script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
   <link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map"><img src="/images/home.png" width="19" height="24" />当前位置：<a href="/workbench.htm">工作台</a> > <a href="/dailyOfficeManager.htm">OA办公</a> > <a href="#">培训管理-列表</a></div>
    <div id="content">
        <form action="/t_cult/list.htm" method="post" id="searchForm">
              <div align="center" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
            		
                     <div class="sub_add" onclick="javascript:addProject();">添加</div>
                     <div class="t_sub_delete" onclick="delProject();">删除</div><br/><br/>
                     <table  border="1"  align="center" cellpadding="1" cellspacing="1" style="width:100%; margin:0 auto; min-width:980px;">
                     <tr>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 0px;height:30px;line-height:30px;overflow:hidden;" >主题</div></td>
                     <td style="width:60px"><input name="t_hrtrain.theme" type="text" value="${t_hrtrain.theme}" style=" float:left;line-height:22px; height:30px;width:150px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >授课人</div></td>
                     <td style="width:60px"><input name="t_hrtrain.teach" type="text" value="${t_hrtrain.teach}" style=" float:left;line-height:22px; height:30px; width:150px;text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >培训日期</div></td>
                     <td style="width:60px"><input  id="rtimec" name="rtimec"  onFocus="var rtimej=$dp.$('rtimej');WdatePicker({onpicked:function(){rtimej.focus();},maxDate:'#F{$dp.$D(\'rtimej\')}'})"  type="text" readonly= "true" value="${rtimec}" style=" float:left;line-height:22px; width:120px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                     <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >至</div></td>
                     <td style="width:60px"><input  id="rtimej" name="rtimej"  onFocus="WdatePicker({minDate:'#F{$dp.$D(\'rtimec\')}'})" type="text" readonly= "true" value="${rtimej}" style=" float:left;line-height:22px; height:30px;width:120px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></td>
                    <td style="width:60px"><div class="sub_sear" style="margin:6px 0px 0px 0px;" onclick="javascript:$('#searchForm').submit()">查询</div></td>
                     <td></td>
                     </tr>
                     </table>
                    
             </div>
         </form>
          <div style="clear:both; margin:0px 0px 0px 0px;"></div>                                           
              <table border="1" align="center" cellpadding="1" cellspacing="1" class="list_table stripe" style="width:90%; margin:0 auto; min-width:980px;">
                                       <tr class="head">
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:30px;background-color:#e6f6ff;"><p>序号</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:30px;background-color:#e6f6ff;"><p><input id="qxcheckbox"  type="checkbox"/></p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>登记时间</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>主题</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>培训地点</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>培训时间</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>授课人</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;background-color:#e6f6ff;"><p>受训人员</p></td>
                                         <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center; width:25%;background-color:#e6f6ff;"><p>主要内容</p></td>
                                       <td style=" background:url(../../../images/table_head.jpg) repeat-x top center; text-align:center;width:120px;border:1px solid #ccc;background-color:#e6f6ff;"><p>操作</p></td>
                                       </tr>
                                       <#if pageBean.list?size!=0>
                            <#list pageBean.list as t_hrtrain>
                            		<input type="hidden" value="${t_hrtrain.id}"/>     
                                       <tr>
                                         <td width="30" style="text-align:center;"><p>${(t_hrtrain_index + 1)+(pageBean.pageNo-1)*(pageBean.pageSize)}</p></td>
                                         <td style="text-align:center;"><input name="a" type="checkbox" autocomplete="off" value="${t_hrtrain.id}" /></td>
                                          <td style="text-align:left;">${t_hrtrain.rtime}</td>
                                          <td style="text-align:left;">${t_hrtrain.theme}</td>
                                          <td style="text-align:left;">${t_hrtrain.tplace}</td>
                                          <td style="text-align:left;">${t_hrtrain.tdate}</td>
                                          <td style="text-align:left;">${t_hrtrain.teach}</td>
                                          <td style="text-align:left;">${t_hrtrain.traineestr}</td>
                                          <td style="text-align:left;">${t_hrtrain.content}</td>
                                          <td style="text-align:left;">
                                         <a href="/t_cult/new.htm?t_hrtrain.id=${t_hrtrain.id}" title="编辑">编辑</a>
                                         <a href="/t_cult/show.htm?t_hrtrain.id=${t_hrtrain.id}" title="查看">查看</a>
                                         	</td>  
                					</tr>
   							</#list>
   							</#if>
   							<#if pageBean.list?size==0>
   							<tr><td style="text-align:center;" colspan="10">暂无数据!</td></tr>
   							</#if>
           </table>
           <br/>
            <div style="width:90%; margin:0 auto; min-width:980px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
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
   // var aa={syz:[{a:1,b:2,c:3},{a:1,b:2,c:3}]};
    //alert(aa.syz[1].c);
    
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
	});
    function addProject(){
    window.parent.location.href="/t_cult/new.htm";
    }
    function delProject() {
     if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
      $('input:checkbox[name=a]:checked').each(function(i){
       if(0==i){
        id = $(this).val();
       }else{
        id+= (","+$(this).val());
       }
      });
      if(id==""){
      alert("请选择删除项!");
      return;
      }
		var url = "/t_cult/delete.htm?Stringid=" + id;
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
		});
	}
    </script>