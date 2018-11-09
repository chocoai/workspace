package com.yhcrt.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.system.SysSystemLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/application.xml" })
public class MybatisTest {
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	
	@Test
	public void mybatisTest() throws Exception{
		//Map<String, String> map = new HashMap<String, String>();
		//map.put("findContent", "十");
		//List<Integer> list = new  ArrayList<Integer>();
		//list.add(1);
		//list.add(2);
		//dao.batchDelete("UnitInfoMapper.deleteAll",list);
		
		//AthleteBaseInfo info = (AthleteBaseInfo) dao.findForObject("AthleteBaseInfoMapper.getById", 1);
		
		//AthleteBaseInfo athleteBaseInfo = new AthleteBaseInfo();
		//athleteBaseInfo.setCid(2);
		//athleteBaseInfo.setUnitCid(1);
		//athleteBaseInfo.setAthleteName("张文");
		//dao.save("AthleteBaseInfoMapper.insert", athleteBaseInfo);
		
		//int[] in = {2};
		//dao.delete("AthleteBaseInfoMapper.deleteAll",in);
		
		//UnitInfo unitInfo = new  UnitInfo();
		//unitInfo.setUnitName("十堰市");
		//athleteBaseInfo.setUnitInfo(unitInfo);
	    //ArrayList<AthleteBaseInfo> list = (ArrayList<AthleteBaseInfo>)dao.findForList("AthleteBaseInfoMapper.quertAll", null);
		//Map<String, String> map = new HashMap<String, String>();
		//map.put("findContent", "管");
		//map.put("pid", 1);
		//List<Integer> list = new ArrayList<Integer>();
		//list.add(1);
		//list.add(2);
		//List<SysMuserInfo> list =  (ArrayList<SysMuserInfo>) dao.findForList("SysMuserInfoMapper.quertByParam123",map);
		//List<SysMuserInfo> list = (List<SysMuserInfo>) dao.findForList("SysMuserInfoMapper.getByDeptCid",2);
		//List<SysMuserInfo> muserInfo = (List<SysMuserInfo>) dao.findForList("SysMuserInfoMapper.getByNotDeptCid",list);
		//List<SysSystemLog> list =  (List<SysSystemLog>) dao.findForList("SysSystemLogMapper.quertByParam",null);
		
		SysSystemLog bean= new SysSystemLog();
		 bean.setOpeartionType(0);
		List<SysSystemLog> list = (List<SysSystemLog>) dao.findForList("SysSystemLogMapper.quertByParam",bean);
		System.out.println(list.size());
		
	}
}
