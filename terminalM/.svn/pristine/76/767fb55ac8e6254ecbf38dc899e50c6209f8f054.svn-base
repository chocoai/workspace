package com.whty.assis.count.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.whty.assis.count.dao.TDataGatherCountLogMapper;
import com.whty.assis.count.model.TDataGatherCountLog;
import com.whty.assis.count.service.TDataGatherCountLogService;
import com.whty.assis.sysres.model.TaManageUserInfo;
import com.whty.common.util.BigDecimalUtils;
import com.whty.common.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* User: zjd
 * \* Date: 2018/6/20
 * \* Time: 9:02
 * \* Description:
 * \
 */
@Service
public class TDataGatherCountLogServiceImpl implements TDataGatherCountLogService {

    @Autowired
    private TDataGatherCountLogMapper tDataGatherCountLogMapper;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 默认累计使用人数一周的数据
     * @param
     * @return
     */
    @Override
    public List<TDataGatherCountLog> selectByUseCount(HttpServletRequest request,Integer timeType) {
        List<TDataGatherCountLog> tDataGatherLogs =tDataGatherCountLogMapper.selectByUseCount(getMapByTimeType(request,timeType));
        for(TDataGatherCountLog tDataGatherCountLog:tDataGatherLogs){
            tDataGatherCountLog.setCreateTimeStr(sdf.format(tDataGatherCountLog.getCreateTime()));
        }
        return tDataGatherLogs;
    }

    @Override
    public List<TDataGatherCountLog> selectByDevice(HttpServletRequest request,Integer timeType) {
        List<TDataGatherCountLog> devices = tDataGatherCountLogMapper.selectByDevice(getMapByTimeType(request,timeType));
        for(TDataGatherCountLog tDataGatherCountLog:devices){
            tDataGatherCountLog.setCreateTimeStr(sdf.format(tDataGatherCountLog.getCreateTime()));
        }
        return devices;
    }

    @Override
    public List<TDataGatherCountLog> selectBySoftware(HttpServletRequest request,Integer timeType) {
        List<TDataGatherCountLog> softwares = tDataGatherCountLogMapper.selectBySoftware(getMapByTimeType(request,timeType));
        return softwares;
    }

    @Override
    public List<TDataGatherCountLog> selectByTeacherRank(HttpServletRequest request,Integer timeType) {
        List<TDataGatherCountLog> teacher2 = tDataGatherCountLogMapper.selectByTeacher(getMapByTimeType(request,timeType));//昨天
        List<TDataGatherCountLog> teacher1 = tDataGatherCountLogMapper.selectByTeacher(getMapByTimeType(request,timeType+3));//前天
        int rank = 0;
        for (int i = 0; i < teacher2.size(); i++) {//昨天排名
            teacher2.get(i).setCreateTimeStr(changgeUseTaking(teacher2.get(i).getUseTaking()));
            String userAccount1 = teacher2.get(i).getUserAccount();
            teacher2.get(i).setRank(rank);
            for (int j = 0; j < teacher1.size(); j++) {//前天排名
                String user_account2 = teacher1.get(j).getUserAccount();
                if (userAccount1.equals(user_account2)) {
                    rank = j - i;
                    teacher2.get(i).setRank(rank);
                    break;
                } else {
                    rank = teacher1.size() - i;
                    teacher2.get(i).setRank(rank);
                }
            }
        }
        return teacher2;
    }


    @Override
    public JSONArray selectByUseTaking(HttpServletRequest request, Integer timeType) {
        List<TDataGatherCountLog> logUseTaking = tDataGatherCountLogMapper.selectByUseTaking(getMapByTimeType(request,timeType));
        // 周期初始化
        Map<Integer, Integer> cycleMap = new HashMap<Integer, Integer>();
        cycleMap.put(1, 0);
        cycleMap.put(2, 0);
        cycleMap.put(3, 0);
        cycleMap.put(4, 0);
        cycleMap.put(5, 0);
        cycleMap.put(6, 0);
        cycleMap.put(7, 0);
        JSONArray resultList = new JSONArray();
        for(TDataGatherCountLog tDataGatherCountLog :logUseTaking) {
            if (tDataGatherCountLog.getUseTaking()!=null) {
                if (tDataGatherCountLog.getUseTaking() >= 1*60*1000 && tDataGatherCountLog.getUseTaking() <= 10*60*1000) {
                    int cycleNum = cycleMap.get(1);
                    cycleNum = cycleNum + 1;// 台数累加
                    cycleMap.put(1, cycleNum);
                }
                if (tDataGatherCountLog.getUseTaking() >= 10*60*1000 && tDataGatherCountLog.getUseTaking() <= 30*60*1000) {
                    int cycleNum = cycleMap.get(2);
                    cycleNum = cycleNum + 1;// 台数累加
                    cycleMap.put(2, cycleNum);
                }
                if (tDataGatherCountLog.getUseTaking() >= 30*60*1000 && tDataGatherCountLog.getUseTaking() <= 60*60*1000) {
                    int cycleNum = cycleMap.get(3);
                    cycleNum = cycleNum + 1;// 台数累加
                    cycleMap.put(3, cycleNum);
                }
                if (tDataGatherCountLog.getUseTaking() >= 60*60*1000 && tDataGatherCountLog.getUseTaking() <= 180*60*1000) {
                    int cycleNum = cycleMap.get(4);
                    cycleNum = cycleNum + 1;// 台数累加
                    cycleMap.put(4, cycleNum);
                }
                if (tDataGatherCountLog.getUseTaking() >= 180*60*1000 && tDataGatherCountLog.getUseTaking() <= 360*60*1000) {
                    int cycleNum = cycleMap.get(5);
                    cycleNum = cycleNum + 1;// 台数累加
                    cycleMap.put(5, cycleNum);
                }
                if (tDataGatherCountLog.getUseTaking() >= 360*60*1000 && tDataGatherCountLog.getUseTaking() <= 480*60*1000) {
                    int cycleNum = cycleMap.get(6);
                    cycleNum = cycleNum + 1;// 台数累加
                    cycleMap.put(6, cycleNum);
                }
                if (tDataGatherCountLog.getUseTaking() > 480*60*1000) {
                    int cycleNum = cycleMap.get(7);
                    cycleNum = cycleNum + 1;// 台数累加
                    cycleMap.put(7, cycleNum);
                }
            }
        }

        for (Map.Entry<Integer, Integer> entry : cycleMap.entrySet()) {
            //容器装个数
            JSONObject qqq = new JSONObject();

            Integer key = entry.getKey();
            Integer value = entry.getValue();
            switch (key) {
                case 1:
                    qqq.put("cycleName", "1-10分钟");
                    break;
                case 2:
                    qqq.put("cycleName", "10-30分钟");
                    break;
                case 3:
                    qqq.put("cycleName", "30-60分钟");
                    break;
                case 4:
                    qqq.put("cycleName", "1-3小时");
                    break;
                case 5:
                    qqq.put("cycleName", "3-6小时");
                    break;
                case 6:
                    qqq.put("cycleName", "6-8小时");
                    break;
                case 7:
                    qqq.put("cycleName", "8小时以上");
                    break;
            }

            BigDecimal bigDecimal = new BigDecimal(value);// 单个的数
            // 总数
            BigDecimal bigDecimal2 = new BigDecimal(logUseTaking.size());
            if (bigDecimal2.toString().equals("0")) {
                qqq.put("deviceCount", 0);
                qqq.put("rate", "0");
            } else {
                String rate = (BigDecimalUtils.getPrettyNumber(bigDecimal
                        .divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).toString()));

                qqq.put("deviceCount", value);
                qqq.put("rate", rate);
            }
            resultList.add(qqq);
        }
        return resultList;
    }

    @Override
    public List<TDataGatherCountLog> selectBySubject(HttpServletRequest request,Integer timeType) {
        List<TDataGatherCountLog> subjects = tDataGatherCountLogMapper.selectBySubject(getMapByTimeType(request,timeType));
        //总时长
        Long total = 0L;
        for(TDataGatherCountLog tDataGatherCountLog :subjects){
            total = total + tDataGatherCountLog.getUseTaking();
        }
        BigDecimal bigDecimal2 = new BigDecimal(total);
        for(TDataGatherCountLog tDataGatherCountLog :subjects){
            BigDecimal bigDecimal = new BigDecimal(tDataGatherCountLog.getUseTaking());
            String rate = (BigDecimalUtils.getPrettyNumber(bigDecimal.divide(bigDecimal2, 2, BigDecimal.ROUND_HALF_UP)
                    .multiply(new BigDecimal(100)).toString()));
            tDataGatherCountLog.setPercent(rate);
            tDataGatherCountLog.setCreateTimeStr(changgeUseTaking(tDataGatherCountLog.getUseTaking()));
        }
        return subjects;
    }


    @Override
    public PageInfo<TDataGatherCountLog> selectByTeacher(HttpServletRequest request,String startTime,String endTime,Integer page) {
        TaManageUserInfo taManageUserInfo = (TaManageUserInfo) request.getSession().getAttribute("loginUser");
        Map<String, Object> param = new HashMap<>();
        param = getAreaId(taManageUserInfo,param);
        if(StringUtil.isNotEmpty(startTime) && (StringUtil.isNotEmpty(endTime))){
            param.put("startTime",startTime+" 00:00:00");
            param.put("endTime",endTime+" 23:59:59");
        }
        PageHelper.startPage(page,10);
        List<TDataGatherCountLog> teachers= tDataGatherCountLogMapper.selectByTeacher(param);
        PageInfo<TDataGatherCountLog> p = new PageInfo<>(teachers);
        for(TDataGatherCountLog tDataGatherCountLog :p.getList()){
            tDataGatherCountLog.setCreateTimeStr(changgeUseTaking(tDataGatherCountLog.getUseTaking()));
        }
        return p;
    }

    @Override
    public List<TDataGatherCountLog> selectByTeacherExcel(Map<String, Object> param) {
        return tDataGatherCountLogMapper.selectByTeacher(param);
    }

    @Override
    public PageInfo<TDataGatherCountLog> selectByDeviceUse(HttpServletRequest request,String startTime,String endTime,Integer page) {
        TaManageUserInfo taManageUserInfo = (TaManageUserInfo) request.getSession().getAttribute("loginUser");
        Map<String, Object> param = new HashMap<>();
        param = getAreaId(taManageUserInfo,param);
        if(StringUtil.isNotEmpty(startTime) && (StringUtil.isNotEmpty(endTime))){
            param.put("startTime",startTime+" 00:00:00");
            param.put("endTime",endTime+" 23:59:59");
        }
        PageHelper.startPage(page,10);
        List<TDataGatherCountLog> devices= tDataGatherCountLogMapper.selectByDeviceUse(param);
        PageInfo<TDataGatherCountLog> p = new PageInfo<>(devices);

        for(TDataGatherCountLog tDataGatherCountLog :p.getList()){
            tDataGatherCountLog.setCreateTimeStr(changgeUseTaking(tDataGatherCountLog.getUseTaking()));
            if(StringUtil.isNotEmpty(tDataGatherCountLog.getUserName())){
                if(tDataGatherCountLog.getUserName().split(",").length==1){
                    tDataGatherCountLog.setRank(1);
                }else{
                    Map<String, Object> param2 = strsRplit(tDataGatherCountLog.getUserName().split(","));
                    tDataGatherCountLog.setUserName((String)param2.get("userName"));
                    tDataGatherCountLog.setRank((Integer) param2.get("rank"));
                }

            }
        }
        return p;
    }


    /**
     * 时长转换
     * @param useTaking
     * @return
     */
    public static String changgeUseTaking(Long useTaking){
        if(useTaking != null){
            long days = useTaking / (1000 * 60 * 60 * 24);
            long hours = (useTaking-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60);
            long minutes = (useTaking-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60);
            return days+"天"+hours+"时"+minutes+"分";
        }
        return 0+"天"+0+"时"+0+"分";
    }

    /**
     * 判断获取区域ID
     * @param taManageUserInfo
     * @return
     */
    public static  Map<String, Object>  getAreaId(TaManageUserInfo taManageUserInfo,Map<String, Object> param){
        int level = taManageUserInfo.getRole().getRoleLevel();
        String  provinceCode = null;
        String  cityCode = null;
        String  areaCode = null;
        String  schoolId = null;
        if(level==5){//校级
            schoolId = taManageUserInfo.getSchoolId()==null?"":taManageUserInfo.getSchoolId()+"";
        }
        if(level==4){//区级
            areaCode = taManageUserInfo.getAreaCode()==null?"":taManageUserInfo.getAreaCode();
        }
        if(level==3){//市级
            cityCode = taManageUserInfo.getCityCode()==null?"":taManageUserInfo.getCityCode();
        }
        if(level==2){//省级
            provinceCode = taManageUserInfo.getProvinceCode()==null?"":taManageUserInfo.getProvinceCode();
        }
        param.put("schoolId",schoolId);
        param.put("areaCode",areaCode);
        param.put("cityCode",cityCode);
        param.put("provinceCode",provinceCode);
        return param;
    }

    /**
     * 根据timeType类型转换起始时间，及区域ID
     * @param timeType
     * @return
     */
    public static synchronized Map<String, Object> getMapByTimeType(HttpServletRequest request,Integer timeType){
        TaManageUserInfo taManageUserInfo = (TaManageUserInfo) request.getSession().getAttribute("loginUser");
        Map<String, Object> param = new HashMap<>();
        param = getAreaId(taManageUserInfo,param);
        Date startTime ;
        Date endTime ;


        if(timeType==1){//昨天
            startTime = DateUtils.getBeginDayOfYesterday();
            endTime = DateUtils.getEndDayOfYesterDay();
            param.put("startTime",startTime);
            param.put("endTime",endTime);
        }
        if(timeType==2){//上周
            startTime = DateUtils.getBeginDayOfLastWeek();
            endTime = DateUtils.getEndDayOfLastWeek();
            param.put("startTime",startTime);
            param.put("endTime",endTime);
        }
        if(timeType==3){//上个月
            startTime = DateUtils.getBeginDayOfLastMonth();
            endTime = DateUtils.getEndDayOfLastMonth();
            param.put("startTime",startTime);
            param.put("endTime",endTime);
        }
        if(timeType==4){//前天
            startTime = DateUtils.getBeginDayOfLastYesterday();
            endTime = DateUtils.getEndDayOfLastYesterDay();
            param.put("startTime",startTime);
            param.put("endTime",endTime);
        }
        if(timeType==5){//上上周
            startTime = DateUtils.getBeginDayOfLastWeek2();
            endTime = DateUtils.getEndDayOfLastWeek2();
            param.put("startTime",startTime);
            param.put("endTime",endTime);
        }
        if(timeType==6){//上上月
            startTime = DateUtils.getBeginDayOfLastMonth2();
            endTime = DateUtils.getEndDayOfLastMonth2();
            param.put("startTime",startTime);
            param.put("endTime",endTime);
        }
        if(timeType==7){//本周时间
            startTime = DateUtils.getBeginDayOfWeek();
            endTime = DateUtils.getEndDayOfWeek();
            param.put("startTime",startTime);
            param.put("endTime",endTime);
        }
        return param;
    }

    /**
     * 数组求值
     * @param strs
     * @return
     */
    public static  Map<String, Object>  strsRplit(String[] strs){
        Map<String, Object> param = new HashMap<String, Object>();
        int max=1;
        for(int i=0;i<strs.length-1;i++)
        {
            int count=1;
            for(int j=i+1;j<strs.length;j++)
            {
                if(strs[i].equals(strs[j]))
                    count++;
            }
            if(max<count)
                max=count;
        }
        System.out.println("重复最多次数为："+max);
        for(int i=0;i<strs.length-1;i++){
            int count=1;
            for(int j=i+1;j<strs.length;j++){
                if(strs[i].equals(strs[j]))
                    count++;
            }
            if(count==max){
                System.out.println("重复最多次("+max+")的字符串为："+strs[i]);
                param.put("rank",max);
                param.put("userName",strs[i]);
            }
        }
        return param;
    }


}