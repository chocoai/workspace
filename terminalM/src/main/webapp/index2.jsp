<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/pages/common/taglib.jsp"%>
<div class="wql_g_content wql_bge7e9eb pdb30">
    <div class="w1200">
        <div class="clearfix">
            <!-- 面包屑 S-->
            <div class="wql_g_mbx fl">
                <div class="wql_mbx01">
                    <span class="prev"><a href="" class="mgr5">全省设备数据仪表盘</a></span>
                </div>
            </div>
            <!-- 面包屑 E-->
            <div class="fr lh50">
                <span class="">管理员姓名</span><span class="mgl20">管理员所属单位</span>
            </div>
        </div>
        
        <!-- 主体部分 S-->
        <div class="wql_mainBox wql_bgfff pdt15 pdlr20 wql_b_radius6 pdb40">
            <div class="wql_g_box pdtb30">
            	<h3 class="f20">今日终端设备开机率</h3>
                <div class="wql_box03 mgt20">
                    <div class="wql_box_l t_c cfff f16 lh32">
                		<div class="mgt15">
                			<div class="wql_num">200000台</div>
                			<div class="wql_name mgt5">设备总数</div>
                		</div>
                		<div class="mgt30">
                			<div class="wql_num">45.44%</div>
                			<div class="wql_name mgt5">班级教学覆盖率</div>
                		</div>
                	</div>
                	<div class="wql_box_r clearfix t_c lh20 f16">
                		<div class="wql_chartbox fl">
                			<div id="MyChart01" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">今日开机率</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart02" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日开机率</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart03" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日平均每台一体机使用时长</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart04" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日授课数占比</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart05" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日受一体机教学的学生数占比</p>
                		</div>

                	</div>
                        
                    
                </div>
            </div>
            <div class="wql_g_box pdtb30">
            	<h3 class="f20">今日终端设备开机率</h3>
                <div class="wql_box03 mgt20">
                    <div class="wql_box_l t_c cfff f16 lh32">
                		<div class="mgt15">
                			<div class="wql_num">200000台</div>
                			<div class="wql_name mgt5">设备总数</div>
                		</div>
                		<div class="mgt30">
                			<div class="wql_num">45.44%</div>
                			<div class="wql_name mgt5">班级教学覆盖率</div>
                		</div>
                	</div>
                	<div class="wql_box_r clearfix t_c lh20 f16">
                		<div class="wql_chartbox fl">
                			<div id="MyChart06" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">今日开机率</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart07" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日开机率</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart08" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日平均每台一体机使用时长</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart09" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日授课数占比</p>
                		</div>
                		<div class="wql_chartbox fl">
                			<div id="MyChart10" style="width:145px;height:185px;" class="wql_MyChart"></div>
                			<p class="wql_tit mgt5 pdlr10">昨日受一体机教学的学生数占比</p>
                		</div>

                	</div>
                        
                    
                </div>
            </div>   
        </div>

        <!-- 主体部分 E-->

    </div>
</div>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/zj_edu_dev/touch/js/echarts1.min.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/china.js"></script>
<script type="text/javascript" src="http://ued.t.huijiaoyun.com/tianyu_edu_dev/html_other_edu/tianyu_terminal/js/tianyu_terminal_base.js"></script>

<script type="text/javascript">

$(function(){
    //复选框
    $('.chklist2').hcheckboxnew2(); 

    $('.radiolist2').hradio2()
	
    $(".sys_seleautodiv").sysSeleautoBox();
   
    function oneScreen(){
        var win_h = $(window).height();        
        var header_h = $('.g_syshead').outerHeight();
        var footer_h = $('.g_sysfooter').outerHeight();
        var mainWrap_h = win_h - header_h - footer_h;      
        $('.wql_mainBox').css('minHeight',mainWrap_h-135);
    };
    oneScreen();
    $(window).on('resize',function(){
        oneScreen();
    });

    

    // 更多 hover 

    // $('.wql_menu_more').hover(function(){
    //     var l = $(this).offset().left
    //     var h = $(this).outerHeight();
    //     var w1 = $(this).children('a').outerWidth()
    //     var w2 = $(this).find('.wql_submenuBox').outerWidth()
    //     var W = (w2-w1)/2;
    //     $(this).find('.wql_submenuBox').show();
    //     $(this).find('.wql_submenuBox').css({left: l-W,top: h});;
       
     
    // },function(){
    //     $(this).find('.wql_submenuBox').hide();
    // })

var data1 = [{'color':'#429de3','value':9700,},{'color':'#cecece','value':10300,}]
var data2 = [{'color':'#1bd6ab','value':10700,},{'color':'#cecece','value':9300,}]
var data3 = [{'color':'#fbbc49','value':12000,},{'color':'#cecece','value':8000,}]
var data4 = [{'color':'#0e9cf6','value':20000,},{'color':'#0fdbac','value':12000,},{'color':'#fdad66','value':12000,}]
var data5 = [{'color':'#0e9cf6','value':20000,},{'color':'#0fdbac','value':12000,},{'color':'#fdad66','value':12000,}]

    myChart1('MyChart01',data1)
    myChart1('MyChart02',data2)
    myChart1('MyChart03',data3)
    myChart1('MyChart04',data2)
    myChart1('MyChart05',data1)
    myChart1('MyChart06',data1)
    myChart1('MyChart07',data2)
    myChart1('MyChart08',data3)
    myChart1('MyChart09',data2)
    myChart1('MyChart10',data1)
   //统计图
function myChart1(obj,d){
  var myChart = echarts.init(document.getElementById(obj));
  var option1 = {
      
        series: [{
            name: '',
            type: 'pie',
            radius: ['80%', '90%'],   
            labelLine: {normal: { show: false} },
            data: [{
                value: d[0].value,
                name: '今日开机数',
                hoverAnimation: false,
                label: {normal: {formatter: '{d} %',textStyle: {fontSize: 20},position: 'center' }},
                itemStyle: {
                    normal: {color: d[0].color,borderWidth: 2,borderColor: '#ffffff'},

                },
            }, {
                value: d[1].value,
                name: '今日未开机数',
                hoverAnimation : false,
                legendHoverLink :false,
                label: {normal: {show: false}},
                itemStyle: {normal: {color: d[1].color,borderWidth: 2,borderColor: '#ffffff' }},
            }]
        }]
    };
    myChart.setOption(option1);
}

function myChart2(obj,d){
  var myChart = echarts.init(document.getElementById(obj));
  var option1 = {
      
        series: [{
            name: '',
            type: 'pie',
            radius: ['80%', '90%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    formatter: '{b}:\n\n {c} 个',
                    position: 'center'
                },
                
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '14',
                        
                    }
                }
            },   
            labelLine: {normal: { show: false} },
            data: [ 
                    {
                        value: d[0].value,
                        name: '设备A',
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {color: d[0].color,borderWidth: 2,borderColor: '#ffffff'},

                        },
                    },
                    {
                        value: d[1].value,
                        name: '设备B',
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {color: d[1].color,borderWidth: 2,borderColor: '#ffffff'},

                        },
                    }, 
                    {
                        value: d[2].value,
                        name: '设备C',
                        hoverAnimation: false,
                        itemStyle: {
                            normal: {color: d[2].color,borderWidth: 2,borderColor: '#ffffff'},

                        },
                    },  
                    
                ]
        }]
    };
    myChart.setOption(option1);
}
})



</script>
