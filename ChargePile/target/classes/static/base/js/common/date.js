$(function() {
	$('#reportrange').daterangepicker(
			{
				startDate : moment().subtract(29, 'days'),
				endDate : moment(),
				minDate : '01/01/2014',
				maxDate : '12/31/2020',
				dateLimit : {
					days : 365
				},
				showDropdowns : true,
				showWeekNumbers : false,
				timePicker : false,
				timePickerIncrement : 1,
				timePicker12Hour : true,
				ranges : {
					'今天' : [ moment(), moment() ],
					'昨天' : [ moment().subtract(1, 'days'),
							moment().subtract(1, 'days') ],
					'最近30天' : [ moment().subtract(29, 'days'), moment() ],
					'最近60天' : [ moment().subtract(59, 'days'), moment() ],
					'当前月' : [ moment().startOf('month'),
							moment().endOf('month') ],
					'上一个月' : [ moment().subtract(1, 'month').startOf('month'),
							moment().subtract(1, 'month').endOf('month') ]
				},
				opens : 'left',
				buttonClasses : [ 'btn btn-default' ],
				applyClass : 'btn-success',
				cancelClass : '',
				format : 'MM/DD/YYYY',
				separator : ' to ',
				locale : {
					applyLabel : '确定',
					cancelLabel : '取消',
					fromLabel : '从',
					toLabel : '到',
					customRangeLabel : '自定义日期',
					daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
					monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月',
							'八月', '九月', '十月', '十一月', '十二月' ],
					firstDay : 1
				}
			},
			function(start, end) {
				var dateStr = start.format('YYYY-MM-DD') + '_'
						+ end.format('YYYY-MM-DD');
				$('#reportrange span').html(dateStr);
				$('#reportrange input').val(dateStr);
			});
	
	//默认初始化显示的日期
//	var dateStrf = moment().subtract(29, 'days').format('YYYY-MM-DD') + '_' + moment().format('YYYY-MM-DD');
//	$('#reportrange span').html(dateStrf);
//	$('#reportrange input').val(dateStrf);
});
