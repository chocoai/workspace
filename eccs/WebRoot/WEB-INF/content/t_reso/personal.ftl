<script src="../../../js/mydate/WdatePicker.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.js" type="text/javascript"></script>
<script src="../../../js/jquery.validate.messages_cn.js" type="text/javascript"></script>
<script src="../../../js/jquery.metadata.js" type="text/javascript"></script>
<link href="../../../css/t_main.css" rel="stylesheet" type="text/css">
<div id="map">
	<img src="/images/home.png" width="19" height="24" />
	当前位置：
	<a href="/workbench.htm">工作台</a>
	>
	<a href="javascript:void(0)">个人资料修改</a>
</div>
<div id="content">
	<div style="width: 70%; min-width: 980px; margin: 20px auto;">
		<div class="tabPanel">
			<ul style="border-bottom: 0px solid #aaa;">
				<li class="hit" style="border-bottom: 0px solid #fff;">修改个人资料</li>
				<li onclick="javascript:getRevise();">修改密码</li>
			</ul>

			<div class="pane" style="display: block; border: 1px solid #aaa;">
				<form action="/t_reso/save.htm" method="post" id="contractForm">
					<input name="t_hremployee.id" value="${t_hremployee.id}" type="hidden" />
					<input name="t_hrentryrecord.id" value="${t_hrentryrecord.id}" type="hidden" />
					<input name="type" value="revise" type="hidden" />
					<div>
						<table border="1" cellspacing="1" cellpadding="1" class="input_table">
							<tr>
								<td width="50" class="tab_title red">
									<p>姓名:</p>
								</td>
								<td>
									<input id="yeename" type="text" class="text_a" value="${t_hremployee.name}" name="t_hremployee.name" />
								</td>
								<td width="50" class="tab_title red">
									<p>系统账号:</p>
								</td>
								<#if t_hremployee!=null>
								<input name="t_hremployee.sys_account.id" id="userId4" value="${t_hremployee.sys_account.id }" type="hidden"
									class="text_c"
								/>
								<td>
									<input id="yeesys_account" type="text" class="text_a" readonly="true" name="t_hremployee.sys_account.username"
										value="${t_hremployee.sys_account.username}"
									/>
								</td>
								<#else>
								<input name="t_hremployee.sys_account.id" id="userId4" value="${user.id }" type="hidden" class="text_c" />
								<td>
									<input id="yeesys_account" type="text" class="text_a" readonly="true" name="t_hremployee.sys_account.username"
										value="${user.username}"
									/>
								</td>
								</#if>
							</tr>
							<tr>
								<td width="50" class="tab_title red">
									<p>性别:</p>
								</td>
								<td>
									&nbsp;&nbsp;
									<input type="radio" name="t_hremployee.sex" value="1"
									<#if t_hremployee.sex==1>checked='checked'</#if>>男&nbsp;&nbsp;&nbsp;
									<input type="radio" name="t_hremployee.sex" value="2"
									<#if t_hremployee.sex==2>checked='checked'</#if>>女
								</td>
								<td width="50" class="tab_title">
									<p>出生年月:</p>
								</td>
								<td>
									<input onClick="WdatePicker({maxDate:'{%y-16}-%M-%d'})" value="<#if t_hremployee.birth !="">${t_hremployee.birth?string('yyyy-MM-dd')}</#if>"
									readonly="true" type="text" class="text_a" name="t_hremployee.birth"/>
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>联系电话:</p>
								</td>
								<td>
									<input type="text" id="phones" class="text_a" name="t_hremployee.phone" value="${t_hremployee.phone}" />
								</td>
								<td width="50" class="tab_title">
									<p>专业:</p>
								</td>
								<td>
									<input type="text" class="text_a" name="t_hremployee.major" value="${t_hremployee.phone}" />
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>紧急联系人:</p>
								</td>
								<td>
									<input type="text" class="text_a" name="t_hremployee.emg_contact" value="${t_hremployee.emg_contact}" />
								</td>
								<td width="50" class="tab_title red">
									<p>电子邮箱:</p>
								</td>
								<td>
									<input type="text" class="text_a" id="emails" name="t_hremployee.email" value="${t_hremployee.email}" />
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>身份证:</p>
								</td>
								<td>
									<input type="text" class="text_a" id="id_cards" name="t_hremployee.id_card" value="${t_hremployee.id_card}" />
								</td>
								<td width="50" class="tab_title">
									<p>排序号:</p>
								</td>
								<td>
									<input type="text" class="text_a" id="sort_nos" name="t_hremployee.sort_no" value="${t_hremployee.sort_no}" />
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>学历信息:</p>
								</td>
								<td colspan="4">
									<div style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px" class="add_link">
										<a href="javascript:addContact();">新增</a>
										<a href="javascript:removeContact()">删除</a>
									</div>
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title"></td>
								<td colspan="4">
									<table border="1" id="contactTableId" cellspacing="1" cellpadding="1" class="list_table4">
										<tr>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">序号</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">
												<input id="qxcheckbox1" type="checkbox" />
											</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">毕业院校</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">学历</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">毕业时间</td>
										</tr>
										<#list listtion as t_hreducation>
										<input type="hidden" value=${t_hreducation.id } />
										<tr>
											<td style="text-align: center; background-color: #fff;">${t_hreducation_index + 1}</td>
											<td style="text-align: center; background-color: #fff;">
												<input name="zz" type="checkbox" autocomplete="off" value="${t_hreducation.id}" class="cc" />
												<input name="t_hreducationid" type="hidden" value=${t_hreducation.id}>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hreducationcollege" onchange="check($(this));" class="text_a"
													value="${t_hreducation.college }"
												/>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<!--input type="text" name="t_hreducationeducation"  class="text_a" value="${t_hreducation.education }"/-->
												<select onchange="check2($(this));" name="t_hreducationeducation">
													<option value="">无</option>
													<option value="初中"<#if t_hreducation.education=="初中">selected='selected'</#if> >初中</option>
													<option value="高中"<#if t_hreducation.education=="高中">selected='selected'</#if> >高中</option>
													<option value="大专"<#if t_hreducation.education=="大专">selected='selected'</#if> >大专</option>
													<option value="大本"<#if t_hreducation.education=="大本">selected='selected'</#if> >大本</option>
													<option value="硕士"<#if t_hreducation.education=="硕士">selected='selected'</#if> >硕士</option>
													<option value="博士"<#if t_hreducation.education=="博士">selected='selected'</#if> >博士</option>
												</select>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hreducationgdate" onblur="check1($(this));" class="text_agdate ccc"
													value="<#if t_hreducation.gdate !="
												">${t_hreducation.gdate?string('yyyy-MM-dd')}</#if>"
												readonly="true"/>
											</td>
										</tr>
										</#list>
									</table>
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>QQ:</p>
								</td>
								<td>
									<input type="text" class="text_a" id="qqs" name="t_hremployee.qq" value="${t_hremployee.qq}" />
								</td>
								<td width="50" class="tab_title">
									<p>微信:</p>
								</td>
								<td>
									<input type="text" class="text_a" name="t_hremployee.we_chat" value="${t_hremployee.we_chat}" />
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>MSN:</p>
								</td>
								<td>
									<input type="text" id="msns" class="text_a" name="t_hremployee.msn" value="${t_hremployee.msn}" />
								</td>
								<td width="50" class="tab_title">
									<p>其它:</p>
								</td>
								<td>
									<input type="text" class="text_a" name="t_hremployee.other" value="${t_hremployee.other}" />
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>备注:</p>
								</td>
								<td colspan="4">
									<textarea style="width: 100%; height: 80px;" name="t_hremployee.remark" id="detailid">${t_hremployee.remark}</textarea>
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title red">
									<p>入职时间:</p>
								</td>
								<td>
									<input id="yeeedate"
										onClick="WdatePicker({maxDate:'#F{$dp.$D(\'t_hrentryrecord.labor_date\')||\'%y-%M-%d\'}'})" readonly="true"
										value="${t_hrentryrecord.edate}" type="text" class="text_a" name="t_hrentryrecord.edate"
									/>
								</td>
								<td width="50" class="tab_title red">
									<p>岗位描述:</p>
								</td>
								<td>
									<input id="yeepost" type="text" class="text_a" name="t_hrentryrecord.post" value="${t_hrentryrecord.post}" />
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>工作性质:</p>
								</td>
								<td>
									<select name="t_hrentryrecord.field"
										style="float: left; line-height: 22px; width: 200px; height: 30px; text-indent: 6px; border: 1px solid #c1e9ff; margin: 6px 10px 0px 0px;"
									>
										<option value="">--请选择--</option>
										<option value="1"<#if t_hrentryrecord.field==1>selected='selected'</#if> >全职</option>
										<option value="2"<#if t_hrentryrecord.field==2>selected='selected'</#if> >兼职</option>
									</select>
								</td>
								<td width="100" class="tab_title">
									<p>劳动合同截止期:</p>
								</td>
								<td>
									<input onFocus="WdatePicker({minDate:'#F{$dp.$D(\'yeeedate\')}'})" readonly="true" type="text" class="text_a"
										id="t_hrentryrecord.labor_date" name="t_hrentryrecord.labor_date" 
										value="<#if t_hrentryrecord.labor_date !="">${t_hrentryrecord.labor_date?string('yyyy-MM-dd')}</#if>"/>
								</td>
							</tr>
							<tr>
								<td width="50" class="tab_title">
									<p>档案所在地:</p>
								</td>
								<td colspan="4">
									<input type="text" class="text_a" name="t_hrentryrecord.archives" value="${t_hrentryrecord.archives}" />
								</td>
							</tr>
							<tr>

								<td colspan="5">
									<div style="display: block; text-align: right; border: 1px solid #dadada; border-bottom: 0px" class="add_link">
										<a href="javascript:addpapers();">新增</a>
										<a href="javascript:removepapers()">删除</a>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<table border="1" id="papersTableId" cellspacing="1" cellpadding="1" class="list_table4">
										<tr>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">序号</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">
												<input id="qxcheckbox2" type="checkbox" />
											</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;" class="red">证件名称</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">证书编号</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;" class="red">专业</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">等级</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">签发单位</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">签发日期</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">有效期(年)</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">备注</td>
										</tr>
										<#list listcate as t_hrcertificate>
										<input type="hidden" value=${t_hrcertificate.id } />
										<tr>
											<td style="text-align: center; background-color: #fff;">${t_hrcertificate_index + 1}</td>
											<td style="text-align: center; background-color: #fff;">
												<input name="yy" type="checkbox" autocomplete="off" value="${t_hrcertificate.id}" class="dd" />
												<input name="t_hrcertificateid" type="hidden" value=${t_hrcertificate.id}>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificatename" onchange="check($(this));" class="text_a"
													value="${t_hrcertificate.name }"
												/>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificateno" class="text_a" value="${t_hrcertificate.no }" />
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificatemajor" onchange="check($(this));" class="text_a"
													value="${t_hrcertificate.major }"
												/>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificategrade" class="text_a" value="${t_hrcertificate.grade }" />
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificateissuing_unit" class="text_a"
													value="${t_hrcertificate.issuing_unit }"
												/>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificateidate" class="text_a ccc" readonly="true"
													value="<#if t_hrcertificate.idate !="
												">${t_hrcertificate.idate?string('yyyy-MM-dd')}</#if>"
												/>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificatevalidity_period" class="text_a"
													value="${t_hrcertificate.validity_period }"
												/>
											</td>
											<td style="text-align: center; background-color: #fff;">
												<input type="text" name="t_hrcertificateremark" class="text_a" value="${t_hrcertificate.remark }" />
											</td>
										</tr>
										</#list>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									<div class="t_sub_adds" onclick="attachment();">添加附件</div>
									<div class="t_sub_delete" onclick="detelefile();">删除附件</div>
								</td>
							</tr>
							<tr>
								<td colspan="4" id="we">
									<table width="100%" border="1" id="fileTableId" cellspacing="1" cellpadding="1" class="list_table4">
										<tr>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">序号</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 42px;">
												<input id="qxcheckbox3" type="checkbox" />
											</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1; width: 25%">文件名称</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">文件描述</td>
											<td style="text-align: center; font-weight: bold; background-color: #d3e0f1;">操作</td>
										</tr>
										<#list list as t_file>
										<tr>
											<td style="text-align: center; background-color: #fff;">${t_file_index + 1}</td>
											<td style="text-align: center; background-color: #fff;">
												<input name="files" type="checkbox" autocomplete="off" value="${t_file.id}" class="files" />
											</td>
											<td style="text-align: center; background-color: #fff;">${t_file.name}</td>
											<td style="text-align: center; background-color: #fff;">${t_file.remarks}</td>
											<td style="text-align: center; background-color: #fff;">
												<a href="/t_file/xiazai.htm?t_file.id=${t_file.id}">下载</a>
											</td>
										</tr>
										</#list>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align: right;">
									<input type="button" value="取消" onclick="javascript:history.go(-1);" style="cursor: pointer;" class="sub" />
									<input type="submit" style="cursor: pointer;" value="保存" class="sub" />
								</td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<script>
	$()
			.ready(
					function() {
						$("#contractForm")
								.validate(
										{
											//debug : true,
											rules : {
												't_hremployee.name' : {
													required : true
												},
												't_hremployee.sex' : {
													required : true
												},
												't_hrentryrecord.edate' : {
													required : true
												},
												't_hrentryrecord.post' : {
													required : true
												},
												't_hremployee.email' : {
													required : true,
													email : true
												},
												't_hremployee.phone' : {
													isPhone : true
												},
												't_hremployee.id_card' : {
													idCard : true
												},
												't_hremployee.sort_no' : {
													sortNo : true
												},
												't_hremployee.qq' : {
													qq : true
												},
												't_hremployee.msn' : {
													msn : true
												},
												't_hrentryrecord.ss_base' : {
													positiveNumber : true
												},
												't_hrentryrecord.af_base' : {
													positiveNumber : true
												}
											},
											submitHandler : function(form) { //表单提交句柄,为一回调函数，带一个参数：form
												$(".remove").remove();
												var ok = true;
												if ($("#papersTableId tr").length > 1) {
													$(".ac")
															.each(
																	function() {
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=name]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=major]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.next()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																	});
													$(".dd")
															.each(
																	function() {
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=t_hrcertificatename]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=t_hrcertificatemajor]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.next()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																	});
												}
												if ($("#contactTableId tr").length > 1) {
													$(".cc")
															.each(
																	function() {
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=t_hreducationcollege]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'select[name=t_hreducationeducation]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=t_hreducationgdate]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.next()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																	});
													$(".b")
															.each(
																	function() {
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=college]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'select[name=education]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																		if ($(
																				this)
																				.parent()
																				.parent()
																				.find(
																						'input[name=gdate]')
																				.val()
																				.trim() == "") {
																			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
																			$(
																					this)
																					.parent()
																					.next()
																					.next()
																					.next()
																					.append(
																							html);
																			ok = false;
																		}
																	});
												}
												if (ok) {
													form.submit();
												}
											}
										})
					});
	function check($this) {
		$this.next().remove();
	}
	function check1($this) {
		if ($this.val() != "") {
			$this.next().remove();
		}
	}
	function check2($this) {
		if ($this.val() != "") {
			$this.next().remove();
		} else {
			var html = '<p class="remove red" style="text-align:center;">*必填字段</p>';
			$this.parent().append(html);
		}
	}

	var b = 0;
	function detelefile() {
		if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
		$('input:checkbox[name=files]:checked').each(function(i) {
			if (0 == i) {
				id = $(this).val();
			} else {
				id += ("," + $(this).val());
			}
		});
		var cc = $("input[type='checkbox']:checked.b").length;
		if (id == "" && cc == 0) {
			alert("请选择删除项!");
			return;
		}
		if (id != "") {
			var url = "/t_reso/deletefile.htm?Stringid=" + id;
			$.ajax({
				type : "post",
				url : url,
				dataType : 'text',
				success : function(d) {
					if (d == '1') {
						alert('删除成功');
						//window.parent.location.reload();
						//location.reload();
					} else {
						alert('删除失败');
					}
				}
			});
		}
		$("input[type='checkbox']:checked.files").parent().parent().remove();
		$("input[type='checkbox']:checked.c").parent().parent().remove();
	}
	//附件
	function attachment() {
		var iWidth = 650; //弹出窗口的宽度; 
		var iHeight = 500; //弹出窗口的高度; 
		//获得窗口的垂直位置 
		var iTop = (window.screen.availHeight - 30 - iHeight) / 2;
		//获得窗口的水平位置 
		var iLeft = (window.screen.availWidth - 10 - iWidth) / 2;
		window
				.open(
						'/t_file/filet_reso.htm',
						'',
						'height='
								+ iHeight
								+ ',innerHeight='
								+ iHeight
								+ ',width='
								+ iWidth
								+ ',innerWidth='
								+ iWidth
								+ ',top='
								+ iTop
								+ ',left='
								+ iLeft
								+ ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no');
	}
	function returnreso(id, name, remarks) {
		b = $("#fileTableId tr").length - 1;
		b++;
		var htmls = '<tr>';
		htmls += '<td style="text-align:center;background-color:#fff; width:42px;">'
				+ b
				+ ' <input  name="file_id"  type="hidden" value="'+id+'"/></td>';
		htmls += '<td style="text-align:center;background-color:#fff; width:42px;"><input  type="checkbox" autocomplete="off" value="'+id+'" class="c" /></td>';
		htmls += '<td style="text-align:center;background-color:#fff; width:42px;">'
				+ name + '</td>';
		htmls += '<td style="text-align:center;background-color:#fff; width:42px;">'
				+ remarks + '</td>';
		htmls += '<td style="text-align:center;background-color:#fff;"><a href="/t_file/xiazai.htm?t_file.id='
				+ id + '">下载</a></td>';
		htmls += '</tr>'
		$("#fileTableId").append(htmls);
	}

	var i = 0;
	var flag = true;
	var fsss = true;
	var id = "";
	var j = 0;
	function addpaper() {
		var tab1 = document.getElementById("papersTableId");
		if (fsss) {
			j = j + tab1.rows.length;
			fsss = false;
		} else {
			j = j + 1;
		}
		var newTr = tab1.insertRow();
		newTr.id = j;
		//添加列
		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();
		var newTd5 = newTr.insertCell();
		var newTd6 = newTr.insertCell();
		var newTd7 = newTr.insertCell();
		var newTd8 = newTr.insertCell();
		var newTd9 = newTr.insertCell();
		//设置列内容和属性
		newTd0.innerHTML = '<p style="text-align:center;">' + j + '</p>';
		newTd1.innerHTML = '<input type="checkbox" class="text_a ac"  />';
		newTd2.innerHTML = '<input type="text" name="name"  class="text_a"  />';
		newTd3.innerHTML = '<input type="text" name="no"  class="text_a"  />';
		newTd4.innerHTML = '<input type="text" name="major"  class="text_a"  />';
		newTd5.innerHTML = '<input type="text" name="grade"  class="text_a"  />';
		newTd6.innerHTML = '<input type="text" name="issuing_unit"  class="text_a"  />';
		newTd7.innerHTML = '<input type="text" name="idate"  class="text_a ccc" readonly="true" type="text" />';
		newTd8.innerHTML = '<input type="text" name="validity_period"  class="text_a"  />';
		newTd9.innerHTML = '<input type="text" name="remark"  class="text_a"  />';
	}
	function addpapers() {
		j = $("#papersTableId tr").length;

		var html = '<tr>';
		html += '<td style="text-align:center;">' + j + '</td>';
		html += '<td><input type="checkbox" class="text_a ac"  /></td>';
		html += '<td><input type="text" name="name" onchange="check($(this));" class="text_a"  /></td>';
		html += '<td><input type="text" name="no"  class="text_a"  /></td>';
		html += '<td><input type="text" name="major" onchange="check($(this));" class="text_a"  /></td>';
		html += '<td><input type="text" name="grade"  class="text_a"  /></td>';
		html += '<td><input type="text" name="issuing_unit"  class="text_a"  /></td>';
		html += '<td><input type="text" name="idate"  class="text_a ccc" readonly="true" type="text" /></td>';
		html += '<td><input type="text" name="validity_period"  class="text_a"  /></td>';
		html += '<td><input type="text" name="remark"  class="text_a"  /></td>';
		html += '</tr>';
		$("#papersTableId").append(html);
	}
	$(function() {
		//全选
		$("#qxcheckbox1").click(function() {

			if ($("#qxcheckbox1").is(':checked')) {
				$("[name='zz']").attr("checked", 'true');
				$(".b").attr("checked", 'true');
			} else {
				$("[name='zz']").removeAttr("checked", 'true');
				$(".b").removeAttr("checked", 'true');
			}
			$("input[name='zz']").click(function() {
				$("#qxcheckbox1").removeAttr("checked", 'true')
			});
			$(".b").click(function() {
				$("#qxcheckbox1").removeAttr("checked", 'true')
			});
		});
		$("#qxcheckbox2").click(function() {

			if ($("#qxcheckbox2").is(':checked')) {
				$("[name='yy']").attr("checked", 'true');
				$(".ac").attr("checked", 'true');
			} else {
				$("[name='yy']").removeAttr("checked", 'true');
				$(".ac").removeAttr("checked", 'true');
			}
			$("input[name='yy']").click(function() {
				$("#qxcheckbox2").removeAttr("checked", 'true')
			});
			$(".ac").click(function() {
				$("#qxcheckbox2").removeAttr("checked", 'true')
			});
		});
		$("#qxcheckbox3").click(function() {

			if ($("#qxcheckbox3").is(':checked')) {
				$("[name='files']").attr("checked", 'true');
				$(".c").attr("checked", 'true');
			} else {
				$("[name='files']").removeAttr("checked", 'true');
				$(".c").removeAttr("checked", 'true');
			}

			$("input[name='files']").click(function() {
				$("#qxcheckbox3").removeAttr("checked", 'true')
			});
			$(".c").click(function() {
				$("#qxcheckbox3").removeAttr("checked", 'true')
			});
		});

		$("table").on("click", ".ccc", function() {
			WdatePicker({
				maxDate : '%y-%M-%d'
			});
		});
	});
	//删除证书
	function removepapers() {
		if (!confirm("确定删除?")) {
			return;
		}
		var id = "";
		$('input:checkbox[name=yy]:checked').each(function(i) {
			if (0 == i) {
				id = $(this).val();
			} else {
				id += ("," + $(this).val());
			}
		});
		var cc = $("input[type='checkbox']:checked.ac").length;
		if (id == "" && cc == 0) {
			alert("请选择删除项!");
			return;
		}
		if (id == "" && cc != 0) {//当删除的只是新增的时候，重置序号
			j = $("input[type='checkbox']:unchecked.dd").length;
			$("input[type='checkbox']:unchecked.ac").each(function(i) {
				$(this).parent().prev().text(++j);
			});
		}
		if (id != "") {
			var url = "/t_reso/deletess.htm?zsid=" + id;
			$.ajax({
				type : "post",
				url : url,
				dataType : 'text',
				success : function(d) {
					if (d == '1') {
						alert('删除成功');
						$("input[type='checkbox']:unchecked.dd").each(
								function(i) {//删除了包含原始存在的证照时重置序号
									$(this).parent().prev().text(i + 1);
								});
						j = $("input[type='checkbox']:unchecked.dd").length;
						$("input[type='checkbox']:unchecked.ac").each(
								function(i) {//删除了包含原始存在的证照时重置序号
									$(this).parent().prev().text(++j);
								});
						//window.parent.location.reload();
						//location.reload();
					} else {
						alert('删除失败');
					}
				}
			});
		}
		$("input[type='checkbox']:checked.ac").parent().parent().remove();
		$("input[type='checkbox']:checked.dd").parent().parent().remove();
	}
	//删除学历
	function removeContact() {
		if (!confirm("确定删除?")) {
			return;
		}
		var idxl = "";
		$('input:checkbox[name=zz]:checked').each(function(i) {
			if (0 == i) {
				idxl = $(this).val();
			} else {
				idxl += ("," + $(this).val());
			}
		});
		var cc = $("input[type='checkbox']:checked.b").length;
		if (idxl == "" && cc == 0) {
			alert("请选择删除项!");
			return;
		}
		if (idxl == "" && cc != 0) {//当删除的只是新增的时候，重置序号
			j = $("input[type='checkbox']:unchecked.cc").length;
			$("input[type='checkbox']:unchecked.b").each(function(i) {
				$(this).parent().prev().text(++j);
			});
		}
		if (idxl != "") {
			var url = "/t_reso/deletess.htm?xlid=" + idxl;
			$.ajax({
				type : "post",
				url : url,
				dataType : 'text',
				success : function(d) {
					if (d == '1') {
						alert('删除成功');
						$("input[type='checkbox']:unchecked.cc").each(
								function(i) {//删除了包含原始存在的证照时重置序号
									$(this).parent().prev().text(i + 1);
								});
						j = $("input[type='checkbox']:unchecked.cc").length;
						$("input[type='checkbox']:unchecked.b").each(
								function(i) {//删除了包含原始存在的证照时重置序号
									$(this).parent().prev().text(++j);
								});
						//window.parent.location.reload();
						//location.reload();
					} else {
						alert('删除失败');
					}
				}
			});
		}
		$("input[type='checkbox']:checked.b").parent().parent().remove();
		$("input[type='checkbox']:checked.cc").parent().parent().remove();
	}

	function AddRow1() {
		i = $("#contactTableId tr").length;
		var htmls = '<tr>';
		htmls += '<td style="text-align:center;">' + i + '</td>';
		htmls += '<td><input type="checkbox"  class="text_a b"  /></td>';
		htmls += '<td><input type="text" name="college" onchange="check($(this));"  class="text_a"  /></td>';
		htmls += '<td><select id="cusnatures" onchange="check2($(this));"  name="education"><option value="">请选择</option><option value="初中">初中</option><option value="高中">高中</option><option value="大专">大专</option><option value="大本">大本</option><option value="硕士">硕士</option><option value="博士">博士</option></select></td>';
		htmls += '<td style="text-align:center;"><input type="text" name="gdate" onblur="check1($(this));"  class="text_agdate ccc" readonly="true" /></td>';
		htmls += '</tr>';
		$("#contactTableId").append(htmls);
	}

	function AddRow() {
		//添加一行
		var tab1 = document.getElementById("contactTableId");
		if (flag) {
			i = i + tab1.rows.length;
			flag = false;
		} else {
			i = i + 1;
		}
		var newTr = tab1.insertRow();
		newTr.id = i;
		//添加列
		var newTd0 = newTr.insertCell();
		var newTd1 = newTr.insertCell();
		var newTd2 = newTr.insertCell();
		var newTd3 = newTr.insertCell();
		var newTd4 = newTr.insertCell();

		//设置列内容和属性
		newTd0.innerHTML = '<p style="text-align:center;">' + i + '</p>';
		newTd1.innerHTML = '<input type="checkbox"  class="text_a b"  />';
		newTd2.innerHTML = '<input type="text" name="college"  class="text_a"  />';
		newTd3.innerHTML = '<select id="cusnatures" name="education"><option value="">请选择</option><option value="初中">初中</option><option value="高中">高中</option><option value="大专">大专</option><option value="大本">大本</option><option value="硕士">硕士</option><option value="博士">博士</option></select>';

		newTd4.innerHTML = '<input type="text" name="gdate"  class="text_a ccc" readonly="true" type="text" />';
	}
	function addContact() {
		AddRow1();
	}

	function submitForm() {

		var ok = true;

		for (var i = 0; i < $("input[name='validity_period']").length; i++) {
			if ($("input[name='validity_period']").eq(i).val().trim() != "") {
				if (!$("input[name='validity_period']").eq(i).val().trim()
						.match(/^[1-9]\d*$/)
						|| $("input[name='validity_period']").eq(i).val()
								.trim() > 30) {
					alert("有效期格式错误(1-30)!");
					ok = false;
					return;
				}
			}
		}
		if ($("#yeename").val().trim() == "") {
			alert("姓名不能为空!");
			ok = false;
			return;
		}
		if ($("#senderDeptIdId").val().trim() == "") {
			alert("分配部门不能为空!");
			ok = false;
			return;
		}
		if ($("#projectid").val().trim() == "") {
			alert("性别不能为空");
			ok = false;
			return;
		}
		;
		if ($("#yeesys_account").val().trim() == "") {
			alert("系统账号不能为空!");
			ok = false;
			return;
		}
		if ($("#yeeedate").val().trim() == "") {
			alert("入职时间不能为空!");
			ok = false;
			return;
		}
		if ($("#yeepost").val().trim() == "") {
			alert("岗位不能为空!");
			ok = false;
			return;
		}
		for (var i = 0; i < $("input[name='college']").length; i++) {
			if ($("input[name='college']").eq(i).val().trim() == "") {
				alert("毕业院校不能为空!");
				ok = false;
				return;
			}
		}
		for (var i = 0; i < $("input[name='education']").length; i++) {
			if ($("input[name='education']").eq(i).val().trim() == "") {
				alert("学历不能为空!");
				ok = false;
				return;
			}
		}
		for (var i = 0; i < $("input[name='gdate']").length; i++) {
			if ($("input[name='gdate']").eq(i).val().trim() == "") {
				alert("毕业时间不能为空!");
				ok = false;
				return;
			}
		}
		for (var i = 0; i < $("input[name='name']").length; i++) {
			if ($("input[name='name']").eq(i).val().trim() == "") {
				alert("证件名称不能为空!");
				ok = false;
				return;
			}
		}
		for (var i = 0; i < $("input[name='major']").length; i++) {
			if ($("input[name='major']").eq(i).val().trim() == "") {
				alert("专业不能为空!");
				ok = false;
				return;
			}
		}
		var phones = $("#phones").val().trim();
		if (phones != "") {
			var a = !phones
					.match(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/);
			var b = !phones.match(/^\d{3}-\d{8}|\d{4}-\d{7}$/);
			if (a && b) {
				alert("联系电话格式不正确!");
				ok = false;
				return;
			}
		}
		var emails = $("#emails").val().trim();
		if (emails != "") {
			if (!emails
					.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
				alert("电子邮箱格式不正确!");
				ok = false;
				return;
			}
		}
		var id_cards = $("#id_cards").val().trim();
		if (id_cards != "") {
			if (!id_cards
					.match(/^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X)?$/)) {
				alert("身份证格式不正确!");
				ok = false;
				return;
			}
		}
		var sort_nos = $("#sort_nos").val().trim();
		if (sort_nos != "") {
			if (!sort_nos.match(/^[1-9]\d*$/)) {
				alert("排序号格式不正确!");
				ok = false;
				return;
			}
		}
		var qqs = $("#qqs").val().trim();
		if (qqs != "") {
			if (!qqs.match(/^[1-9]\d{4,10}$/)) {
				alert("QQ格式不正确!");
				ok = false;
				return;
			}
		}
		var msns = $("#msns").val().trim();
		if (msns != "") {
			if (!msns
					.match(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/)) {
				alert("MSN格式不正确!");
				ok = false;
				return;
			}
		}
		var ss_bases = $("#ss_bases").val().trim();
		if (ss_bases != "") {
			if (!ss_bases.match(/^[0-9]*$/)) {
				alert("社保基数不正确!");
				ok = false;
				return;
			}
		}
		var af_bases = $("#af_bases").val().trim();
		if (af_bases != "") {
			if (!af_bases.match(/^[0-9]*$/)) {
				alert("公积金基数不正确!");
				ok = false;
				return;
			}
		}
		if (ok) {
			$("#contractForm").submit();
		}
	}
	function getRevise() {
		window.location.href = "/user/revisepass.htm";
	}
</script>