
<%
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
%>
<%@page language="java" pageEncoding="utf-8"%>
<nav class="navbar-default navbar-static-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="side-menu">
			<li class="nav-header">
				<div class="dropdown profile-element">
					<span>
						<img alt="image" class="img-circle" src="resources/img/profile_small.jpg" />
					</span>
					<a data-toggle="dropdown" class="dropdown-toggle" href="#">
						<span class="clear">
							<span class="block m-t-xs">
								<strong class="font-bold">管理员</strong>
								<b class="caret"></b>
							</span>
							<span class="text-muted text-xs block">

							</span>
						</span>
					</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li>
							<a href="" data-toggle="modal" data-target="#updatePwd">修改密码</a>
						</li>
						<li><a href="pages/profile.html">个人信息</a></li>

					</ul>
				</div>
				
			</li>
			<li class="active">
				<a href="dashboard">
					<i class="fa fa-desktop"></i>
					<span class="nav-label">我的工作台 </span>
				</a>

			</li>
			<li>
				<a href="" target="rframe">
					<!-- <i class="fa fa-map-marker"></i> -->
					<i class="glyphicon glyphicon-map-marker"></i>
					<span class="nav-label">安全定位</span>
				</a>
			</li>
			<li>
				<a href="content" target="rframe">
					<!-- <i class="fa fa-comments-o"></i> -->
					<i class="fa fa-th-large"></i>
					<span class="nav-label">健康管理</span>
				</a>
			</li>

			<li>
				<a href="content">
					<i class="fa fa-shopping-cart"></i>
					<span class="nav-label">商城管理</span>
					
				</a>
				
			</li>

			<li>
				<a href="#">
					<i class="fa fa-files-o"></i>
					<span class="nav-label">工单管理</span>
					
				</a>
				
			</li>

			<li>
				<a href="">
					<i class="fa fa-users"></i>
					<span class="nav-label">会员管理</span>
					<span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li>
						<a href="" target="rframe">会员用户</a>
					</li>
					<li>
						<a href="" target="rframe">亲友用户</a>
					</li>
					<li>
						<a href="" target="rframe">积分规则</a>
					</li>
					<li>
						<a href="" target="rframe">等级设置</a>
					</li>
					<li>
						<a href="" target="rframe">会员充值</a>
					</li>
					
					</li>
				</ul>
			</li>

			<li>
				<a href="#">
					<!-- <i class="glyphicon glyphicon-cog"></i> -->
					<i class="fa fa-user-o"></i>
					<span class="nav-label">员工管理</span>
					<span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li>
						<a href="" target="rframe">员工列表</a>
					</li>
					<li>
						<a href="" target="rframe">医师列表</a>
					</li>
					<li>
						<a href="" target="rframe">管家统计</a>
					</li>

				</ul>
			</li>

			<li>
				<a href="#">
					<i class="fa fa-sitemap"></i>
					<span class="nav-label">机构管理</span>
					
				</a>
				
			</li>

			<li>
				<a href="#">
					<i class="fa fa-plug "></i>
					<span class="nav-label">终端管理</span>
					<span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li>
						<a href="https://www.jiguang.cn/accounts/login/form" target="_blank">终端列表</a>
					</li>

					<li>
						<a href="https://i.umeng.com/?spm=0.0.0.0.DOEXDP" target="_blank">终端设置</a>
					</li>
					
				</ul>
			</li>

			<li>
				<a href="#">
					<i class="fa fa-cogs"></i>
					<span class="nav-label">系统管理</span>
					<span class="fa arrow"></span>
				</a>
				<ul class="nav nav-second-level">
					<li>
						<a href="role/getList.do" target="rframe">角色管理</a>
					</li>

					<li>
						<a href="res/getList.do" target="rframe">权限资源管理</a>
					</li>


					<li>
						<a href="dict/getList.do" target="rframe">数据字典管理</a>
					</li>

				</ul>
			</li>

		</ul>

	</div>

	<div class="modal inmodal" id="updatePwd" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content animated flipInY">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<!-- <h6 class="modal-title">修改密码</h6> -->

					<small class="font-bold" style="float: left;">修改密码</small>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="" method="post" name="">
						<div class="form-group">
							<label class="col-lg-2 control-label">原始密码</label>

							<div class="col-lg-10">
								<input type="password" id="oldPwd" placeholder="请输入原始密码" required="required" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">新密码</label>

							<div class="col-lg-10">
								<input type="password" id="newPwd" placeholder="请输入新密码" required="required" class="form-control">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-2 control-label">确认密码</label>
							<div class="col-lg-10">
								<input type="password" id="rePass" placeholder="请输入新密码" required="required" class="form-control">
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" id="btnSave" class="btn btn-primary">保存</button>
					<button type="button" class="btn btn-white" data-dismiss="modal">取消</button>
				</div>
			</div>
		</div>
	</div>
</nav>

<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSave").click(function() {
			var oldPwd = $("#oldPwd").val();
			var newPwd = $("#newPwd").val();
			var rePass = $("#rePass").val();

			if (!oldPwd || !newPwd || !rePass) {
				layer.msg("请输入密码");
				return;
			}

			if (newPwd.length < 6) {
				layer.msg("您的密码设置的过于简单，请重新设置");
				return;
			}

			if (newPwd != rePass) {
				layer.msg("两次数据的密码不一致，请重新输入");
				return;
			}

			//读取json文件，并转换为data对象		
			var paras = {};
			paras.oldPassword = oldPwd;
			paras.newPassword = newPwd;

			$.post("adminUser/updatePassword.do", paras, function(data) {
				if (data.result) {
					//密码修改成功
					layer.msg("密码修改成功");
					location.reload();
				} else {
					//密码修改失败
					layer.msg(data.errorMsg);
				}
			});

		});
	});
</script>

