(function($){
$.extend({
ms_DatePicker: function (options) {
            var defaults = {
                YearSelector: "#sel_year",
                MonthSelector: "#sel_month",
                DaySelector: "#sel_day",
                FirstText: "",
                FirstValue: 0,
                
            };
            var opts = $.extend({}, defaults, options);
            var $YearSelector = $(opts.YearSelector);
            var $MonthSelector = $(opts.MonthSelector);
            var $DaySelector = $(opts.DaySelector);
            var FirstText = opts.FirstText;
            var FirstValue = opts.FirstValue;

            // 初始化
            var Ystr = "<option value=\"" + FirstValue + "\">" + FirstText + "年</option>";
            var Mstr = "<option value=\"" + FirstValue + "\">" + FirstText + "月</option>";
            var Dstr = "<option value=\"" + FirstValue + "\">" + FirstText + "日</option>";
            $YearSelector.html(Ystr);
            $MonthSelector.html(Mstr);
            $DaySelector.html(Dstr);

            // 年份列表
            var yearNow = new Date().getFullYear();
			var yearSel = $YearSelector.attr("rel");
            for (var i = yearNow; i >= 1900; i--) {
				var sed = yearSel==i?"selected":"";
				var yearStr = "<option value=\"" + i + "\" " + sed+">" + i + "年</option>";
                $YearSelector.append(yearStr);
            }

            // 月列表(仅当选择了年)
            function BuildMonth() {
                if ($YearSelector.val() == 0) {
                    // 未选择年份
                    $DaySelector.html(Dstr);
                    $MonthSelector.html(Mstr);
                } else {
                    $MonthSelector.html(Mstr);
                    // 月份列表
                    var monthSel = $MonthSelector.attr("rel");
                    for (var i = 1; i <= 12; i++) {
                        var sed = monthSel==i?"selected":"";
                        var monthStr = "<option value=\"" + i + "\" "+sed+">" + i + "月</option>";
                        $MonthSelector.append(monthStr);
                    }
                }
            }

            // 日列表(仅当选择了年月)
            function BuildDay() {
                if ($YearSelector.val() == 0 || $MonthSelector.val() == 0) {
                    // 未选择年份或者月份
                    $DaySelector.html(Dstr);
                } else {
                    $DaySelector.html(Dstr);
                    var year = parseInt($YearSelector.val());
                    var month = parseInt($MonthSelector.val());
                    var dayCount = 0;
                    switch (month) {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            dayCount = 31;
                            break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            dayCount = 30;
                            break;
                        case 2:
                            dayCount = 28;
                            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
                                dayCount = 29;
                            }
                            break;
                        default:
                            break;
                    }
					var daySel = $DaySelector.attr("rel");
                    for (var i = 1; i <= dayCount; i++) {
						var sed = daySel==i?"selected":"";
						var dayStr = "<option value=\"" + i + "\" "+sed+">" + i + "日</option>";
                        $DaySelector.append(dayStr);
                    }
                }
            }
            $YearSelector.change(function () {
            	$($YearSelector).removeAttr("rel");
        		$($MonthSelector).removeAttr("rel");
        		$($DaySelector).removeAttr("rel");
            	BuildMonth();
            	BuildDay();
            });
            $MonthSelector.change(function () {
            	$($MonthSelector).removeAttr("rel");
        		$($DaySelector).removeAttr("rel");
                BuildDay();
            });
			if($DaySelector.attr("rel")!=""){
                BuildMonth();
                BuildDay();
			}
        }, // End ms_DatePicker
        
	ms_setDate: function (options) {
        var defaults = {
                YearSelector: "#sel_year",
                MonthSelector: "#sel_month",
                DaySelector: "#sel_day",
                FirstText: "",
                FirstValue: 0,
                yearValue:"",
                MonthValue:"",
                dayValue:""
            };
            var opts = $.extend({}, defaults, options);
            var $YearSelector = $(opts.YearSelector);
            var $MonthSelector = $(opts.MonthSelector);
            var $DaySelector = $(opts.DaySelector);
            var yearValue = opts.yearValue;
            var MonthValue = opts.MonthValue;
            var dayValue = opts.dayValue;
			$($YearSelector).attr("rel",yearValue);
			$($MonthSelector).attr("rel",MonthValue);
			$($DaySelector).attr("rel",dayValue);
	}

});

})(jQuery);