package com.yhcrt.iHealthCloud.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.HdSleep;
import com.yhcrt.iHealthCloud.entity.WorkOrder;
import com.yhcrt.iHealthCloud.entity.YwOrder;
import com.yhcrt.iHealthCloud.mapper.HdPulseMapper;
import com.yhcrt.iHealthCloud.mapper.HdSleepMapper;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.service.WorkOrderService;
import com.yhcrt.iHealthCloud.service.YwOrderService;
import com.yhcrt.iHealthCloud.util.DateUtil;
import com.yhcrt.iHealthCloud.util.DateUtils;


@Component
@Lazy(false)
public class HealthTask {
	
	@Autowired private HdPulseMapper pulseMapper;
	
	@Autowired private HdSleepMapper hdSleepMapper;
	
	@Autowired private SysSequenceService sysSequenceService;
	
	@Autowired private YwOrderService  ywOrderService ;
	
	@Autowired private WorkOrderService workOrderService;

    /**
     * 自动计算睡眠数据
     */
    @Scheduled(cron="0 30 09 * * ?")
//	@Scheduled(cron="0/5 * * * * ?")
    public void healthTask() {
		System.out.println("----------------------开始插入睡眠数据-------------------------");
    	String dateStr = DateUtils.dateToString19(new Date());
    	String lastDateStr = DateUtils.dateToString19(new Date(System.currentTimeMillis()-3600*24*1000));
    	List<Object[]> list = pulseMapper.selectForSleep(lastDateStr,dateStr);
        for(Object[] object : list){
//        	Double avgpulse = (Double) object[0];
        	Integer countpulse = (Integer) object[1];
//        	Integer maxpulse = (Integer) object[2];
//        	Integer minpulse = (Integer) object[3];
        	Integer memberId = (Integer) object[4];
        	String imei = (String) object[5];
        	Integer sleepQuality = 4;  //1：优  2：良  3：一般 4：差
        	if(countpulse>6*9){ //正常睡眠大于9小时
        		sleepQuality = 1;
        	}else if(countpulse>=6*7){
        		sleepQuality = 2;
        	}else if(countpulse>=6*5){
        		sleepQuality = 3;
        	}
        	HdSleep hdSleep = new HdSleep();
        	hdSleep.setCid(sysSequenceService.getSequenceValue(Constants.SequenceName.HD_SLEEP));
        	hdSleep.setDataDate(dateStr);
        	hdSleep.setDeepSleepDuration(countpulse*60/3.0);
        	hdSleep.setImei(imei);
        	hdSleep.setMemberId(memberId);
        	hdSleep.setSleepQuality(sleepQuality);
        	hdSleep.setUploadTime(dateStr);
        	hdSleepMapper.insert(hdSleep);
        	System.out.println("插入睡眠数据成功，memberID："+memberId);
        }
       // System.out.println("-----------------------------------------------"+list.size());
    }
    
    
    /**
     * 
     * 每30分钟查询一下过期的订单
     * @return void
     */
	@Scheduled(cron = "0/30 * * * * ? ")
	//@Scheduled(cron = "0 * 14 * * ?")
	public void setAgeWarn(){
		//商品
		List<YwOrder> list = ywOrderService.selectYwOrderList(Constants.OrderType.ORDER_NOPAY);
		if(list.size()>0){
			for (YwOrder o : list) {
				long time = DateUtil.validTime(o.getCreateTime());
				if(time<=0){
					ywOrderService.updateStatus(o.getOrderId(), Constants.OrderType.ORDER_CANCER,null);
				}
			}
		}
		//服务
		List<WorkOrder> list2 = workOrderService.selectYwOrderList(Constants.OrderType.ORDER_NOPAY);
		if(list2.size()>0){
			for (WorkOrder w : list2) {
				long time = DateUtil.validTime(w.getCreateTime());
				if(time<=0){
					workOrderService.updateStatus(w.getOrderId(), Constants.OrderType.ORDER_CANCER,null);
				}
			}
		}
	}
	
}
