<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
 <div id="map">
 <img src="/images/home.png" width="19" height="24" />当前位置：
 <a href="/workbench.htm">工作台</a> >
 <a href="/systemManager.htm">系统管理</a> >
 <a >信息发布</a>
 </div>
 <input type="hidden" name="name" id="name" value="${noticeItem.name}">
<form action="/notice/list.htm" method="post" id="searchForm1">
	<input type="hidden" name="id" id="id" value="${noticeItem.id}">
</form>
 <div id="content">
 <div class="left" style="float:left;height:800px;width:15%; ">
         <link type="text/css" rel="stylesheet" href="/js/dtree/dtree.css">
		<script src="/js/dtree/dtree.js"></script>
		<script type="text/javascript">
			var d = new dTree('d');
			
			d.add('0','-1','栏目',"",'','','','');
			<#list Item as noticeItem>
				d.add('${noticeItem.id}','0','${noticeItem.name}',"javascript:search('${noticeItem.id}')",'','','','');
			</#list>
			document.write(d);
		</script>
 </div>
 <div style=" float:left;width:80%; margin-left:20px;">
        <form action="/notice/list.htm" method="get" id="searchForm">
              <div align="center" class="list_table stripe" >
            		<div class="article" style="margin:0; clear:both; width:100%;">
                     <div class="sub_add" onclick="javascript:addNoticeItem();">添加栏目</div>
                     <div class="sub_add" onclick="javascript:addNotice();">发布消息</div>
        <table style="width:100%;" >
              <tr>
              <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:12px; border:1px solid #c1e9ff; margin:6px 0px 0px -1px;height:30px;line-height:30px;overflow:hidden;" >标题:</div></td>
              <td style="width:60px"><div><input name="notice.title" type="text" value="${notice.title}" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff;margin:6px 10px 0px 0px;" /></div></td>
              <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:3px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden;" >发布时间:</div></td>
              <td style="width:60px"><div><input name="ctimes" type="text" value="${ctimes}" id="ctimes" readonly="readonly"  onFocus="var ctimee=$dp.$('ctimee');WdatePicker({onpicked:function(){ctimee.focus();},maxDate:'#F{$dp.$D(\'ctimee\')}'})" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div></td>
              <td style="width:60px"><div style=" float:left;line-height:22px; width:60px; height:30px; text-indent:20px; border:1px solid #c1e9ff; margin:6px 0px 0px 10px;height:30px;line-height:30px;overflow:hidden; ">到:</div></td>
              <td style="width:60px"><div><input name="ctimee" id="ctimee" type="text" value="${ctimee}" readonly="readonly" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'ctimes\')}'})" style=" float:left;line-height:22px; width:150px; height:30px; text-indent:6px; border:1px solid #c1e9ff; margin:6px 10px 0px 0px;" /></div></td>
              <td style="width:60px"><div class="sub_sear" onclick="javascript:$('#searchForm').submit()" style="margin:6px 0px 0px 0px">查询</div></td>
              <td></td>
              </tr>
              </table>
               </div>
             </div>
         </form>
              <div style="clear:both;"></div>                    
                                     
              <table border="1" cellpadding="1" cellspacing="1" class="list_table stripe" style=" float:left;width:80%; ">
                                       <tr class="head">
                                         <td style=" background-color:#e6f6ff; text-align:center; width:30px;"><p>编号 </p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>标题</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>发布部门</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>发布人</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;"><p>发布时间</p></td>
                                         <td style=" background-color:#e6f6ff; text-align:center;width:120px;"><p>操作</p></td>
                                       </tr>
                            <#list pageBean.list as notice>        
                                       <tr>
                                         <td width="30" style="text-align:center;">${notice_index + 1}</td>
                                         <td>${notice.title}</td>
                                         <td>${notice.dept.name}</td>
                                         <td>${notice.user.name}</td>
                                         <td>${notice.ctime}</td>

                                         <td style=" text-align:center;"><a href="javascript:delNotice(${notice.id})" title="删除">删除</a>
                                         <a href="/notice/edit.htm?notice.id=${notice.id}" title="编辑">编辑</a>
                                         <a href="/notice/show.htm?notice.id=${notice.id}" title="查看">查看</a>
                                         	</td>                             
                					</tr>
   							</#list>
           </table>
              <div style=" float:left;width:80%;margin-top: -40px;">
					<#import "/WEB-INF/tld/page.ftl" as tt>
					<@tt.page pageBean=pageBean formId="searchForm" />
			</div> 
     </div>
    </div>
<script>
	function search(id) {
		$("#id").val(id);
		$("#searchForm1").submit();
	}
	function delNotice(id) {
		if (!confirm("确定删除此信息")) {
			return;
		}
		
		var url = "/notice/delete.htm?notice.id=" + id;
		$.ajax({
			type:"post",
			url:url,
			asyn:true,
			dataType:'text',
			success:function(d){
				if (d == '1') {
					alert('删除成功');
					window.parent.location.reload();
				} else {
					alert('删除失败');
				}
			}
		});
	}
	function addNoticeItem() {
         window.parent.location.href="/notice/newitem.htm";
	}
	function addNotice() {
         window.parent.location.href="/notice/new.htm";
	}
</script>



