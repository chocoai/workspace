<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 模态框（处理组合队分配奖励） -->
<div class="modal fade" id="allotReward" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">组合单位奖励分配</h4>
				<input type="hidden" id="allotProjectCidModal"/>
			</div>
			<div class="modal-body">
				<label>待分配奖牌：<span id="allotMedal"></span><span id="allotMedalCount"></span>枚；待分配积分数量：<span id="allotIntrgral"></span></label>
				<div class="table-responsive">
					<table class="table table-hover table-bordered table-striped">
						<thead>
							<tr>
								<th>单位名称</th>
								<th>分配奖牌数量</th>
								<th>分配积分数量</th>
							</tr>
						</thead>
						<tbody id="judgelist">
							
						</tbody>
					</table>
					<div class="col-md-10 col-md-offset-1">
						<button type="button" name="closeModal" id="cancleBtn2"
							data-dismiss="modal" class="btn btn-warning myformbtn pull-right">取消</button>
						<button type="button" id="submitAllot"
							class="btn btn-success myformbtn pull-right">保存</button>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>