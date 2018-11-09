
package com.yhcrt.test.serviceTest;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yhcrt.entity.system.SysSystemLog;
import com.yhcrt.service.signUp.UnitInfoService;
import com.yhcrt.service.stsyem.SysDeptUserService;
import com.yhcrt.service.stsyem.SysDictService;
import com.yhcrt.service.stsyem.SysMuserInfoService;
import com.yhcrt.service.stsyem.SysResService;
import com.yhcrt.service.stsyem.SysRoleResService;
import com.yhcrt.service.stsyem.SysStsyemLogService;

/**  
 * @Description: TODO
 * @author: 陈伟  
 * @date: 2017年5月19日 下午2:54:06
 * @version: V1.0  
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:/application.xml" })
public class ServiceTest {
	@Autowired
	private UnitInfoService unitInfoService;
	@Autowired
	private SysRoleResService sysRoleResService;
	@Autowired
	private SysResService sysResService;
	@Autowired
	private SysDeptUserService sysDeptUserService;
	@Autowired
	private SysMuserInfoService sysMuserInfoService;
	@Autowired
	private SysDictService sysDictService;
	@Autowired
	private SysStsyemLogService sysStsyemLogService;
	
	
	@Test
	public void mybatisTest() throws Exception{
		SysSystemLog bean = new SysSystemLog();
		bean.setOpeartionType(0);
		 List<SysSystemLog>  list = sysStsyemLogService.quertByParam(bean);

		System.out.println("=========" + list.size());
	}
	
	

}
