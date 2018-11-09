<%@page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String contextPath = request.getContextPath();
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">安全定位设置</h4>
</div>

<div class="modal-body">
	<div class="row">
		<div id="myMap" style="height: 450px; width: 700px; float: left; margin-left: 6px; margin-right: 5px; margin-top: -5px; margin-bottom: -5px;"></div>
		<div style="height: 450px; width: 260px; display: inline; float: right;">
			<form id="locationSettingForm" action="<%=contextPath%>/devicesetting/locationSetting" method="post">
				<input type="hidden" name="cid" value="${locationSetting.cid }" />
				<input type="hidden" name="imei" value="${imei }" /> 
				
				<label for="longitude" style="color: #ea5200;">经度*:</label>
				<div style="margin-bottom: 10px;">
					<input type="text" class="form-control" id="longitude"
						name="longitude" value="${locationSetting.longitude }"
						placeholder="经度" style="width: 220px;" readonly="readonly">
				</div>
				
				<label for="latitude" style="color: #ea5200;">纬度*:</label>
				<div style="margin-bottom: 10px;">
					<input type="text" class="form-control" id="latitude"
						name="latitude" value="${locationSetting.latitude }"
						placeholder="纬度" style="width: 220px;" readonly="readonly">
				</div>
				
				<label for="radius" style="color: #ea5200;">半径(米)*:</label>
				<div style="margin-bottom: 10px;">
					<input type="text" class="form-control" id="radius" name="radius" value="${locationSetting.radius }" 
						placeholder="请输入半径" style="width: 220px;" onkeyup="this.value=this.value.replace(/\D/g,'')"
						onafterpaste="this.value=this.value.replace(/\D/g,'')">
				</div>
				
				<label style="color: #ea5200;">定位报警开关设置*:</label>
				<div style="margin-bottom: 10px;">
					<input type="radio" name="alarmSwitch" value="0" checked="checked" />开&nbsp;&nbsp;&nbsp;
					<input type="radio" name="alarmSwitch" value="1" /> 关
				</div>
				
				<div class="alert alert-info" style="width: 220px;">
					操作说明:<br>
					<p>右键单击地图选中心点,拖动圆上的圆改变半径后点击鼠标左键</p>
				</div>
				
				<button type="submit" class="btn btn-primary" id="saveBtn">保存</button> &nbsp;
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
			</form>
		</div>
	</div>


</div>

<script type="text/javascript">
	$(function() {

		var map = new AMap.Map('myMap', {
			resizeEnable : true,
			zoom : 11
		});

		map.plugin([ "AMap.ToolBar", "AMap.Scale", "AMap.CircleEditor" ],
				function() {
					map.addControl(new AMap.ToolBar());
					map.addControl(new AMap.Scale());
					map.addControl(new AMap.CircleEditor());
				}
		);
		
		//创建圆形覆盖物对象
		var circle = new AMap.Circle({
			map : map,
			radius : 5000, // 半径
			strokeColor : "#F33", //线颜色
			strokeOpacity : 1, //线透明度
			strokeWeight : 3, //线粗细度
			fillColor : "#ee2200", //填充颜色
			fillOpacity : 0.35 //填充透明度
		});
		
		//创建圆形编辑器对象
		var circleEditor = new AMap.CircleEditor(map, circle); 

		map.on('rightclick', function(e) {
			// 设置圆心位置 
			circle.setCenter(new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat()));
			circleEditor.open(); //打开圆形编辑器
		});
		
		circle.on('change', function(e) {
			$('#longitude').val(circle.getCenter( ).getLng());
			$('#latitude').val(circle.getCenter( ).getLat());
			$('#radius').val(circle.getRadius());
		});
		
		$('#radius').on('change', function(e) {
			if ($('#radius').val() > 0 ) {
				circle.setRadius($('#radius').val());
			}
		});
		
		$(":radio[name='alarmSwitch'][value='${locationSetting.alarmSwitch}']").prop("checked", "checked");
		var longitude = $('#longitude').val();
		var latitude = $('#latitude').val();
		var radius = $('#radius').val();
		if (longitude!='' && latitude!='' && radius !='') {
			circle.setCenter(new AMap.LngLat(longitude, latitude));
			circle.setRadius(radius);
			map.setCenter(new AMap.LngLat(longitude, latitude));
			circleEditor.open(); //打开圆形编辑器
		}

	});
</script>