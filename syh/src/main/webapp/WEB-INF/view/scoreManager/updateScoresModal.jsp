<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 模态框（修改成绩） -->
<div class="modal fade" id="updateAthleteScore" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title">修改成绩</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="form-horizontal">
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">项目名称</label>
								<div class="col-md-8">
									<input type="text" id="updateProjectName"
										class="form-control" readonly> <input type="hidden"
										id="updateProjectCid" >
									<input type="hidden" id="updateParticipatId" >
									<input type="hidden" id="isTeam" >
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">参赛人/队名称</label>
								<div class="col-md-8">
									<input type="text" 
										class="form-control" id="updateAthleteName" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">所属单位</label>
								<div class="col-md-8">
									<input type="text" 
										class="form-control" id="updateUnitName" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">成绩单位</label>
								<div class="col-md-8">
									<input type="text" id="updateResultTypeVal" class="form-control" readonly>
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<input type="hidden" id="updateResultType" >
								<div class="form-group" id="showUpdateScores">
									<label class="col-md-3 control-label">成绩</label>
									<div class="col-md-8">
										<input type="text" class="form-control" id="updateScores"
											placeholder="请输入成绩" >
									</div>
								</div>
							<div class="form-group">
								<label class="col-md-3 control-label">名次</label>
								<div class="col-md-8">
									<input type="text" class="form-control" id="updateRanking"
										placeholder="请输入名次">
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">奖牌</label>
								<div class="col-md-3">
									<select id="updateMedal" class="form-control">
										<option value="0">无奖牌</option>
										<option value="1">金牌</option>
										<option value="2">银牌</option>
										<option value="3">铜牌</option>
									</select>
								</div>
								<div class="col-md-5">
									<input type="text" id="updateMedalNum" class="form-control"
										placeholder="输入奖牌数量">
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">积分</label>
								<div class="col-md-8">
									<input type="text" id="updateIntrgral" class="form-control" >
								</div>
							</div>
						</div>
						<div class="col-md-11">
							<div class="form-group">
								<label class="col-md-3 control-label">备注</label>
								<div class="col-md-8">
									<textarea class="form-control" rows="3" id="updateBackup" ></textarea>
								</div>
							</div>
						</div>
						<div class="col-md-10 col-md-offset-1">
							<button type="button" name="closeModal"
								data-dismiss="modal" class="btn btn-warning myformbtn pull-right">取消</button>
							<button type="button" id="submitBtn"
								class="btn btn-success myformbtn pull-right">保存</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal -->
</div>