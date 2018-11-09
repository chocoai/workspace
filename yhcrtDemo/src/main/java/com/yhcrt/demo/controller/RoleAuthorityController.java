package com.yhcrt.demo.controller;

import java.io.IOException;





import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yhcrt.demo.model.RoleAuthority;
import com.yhcrt.demo.service.RoleAuthorityService;


/**
 * @author fengkun
 * @email 231788364@qq.com
 */
@Controller
@RequestMapping("/sys/roleauthority")
public class RoleAuthorityController {

	@Resource
	private RoleAuthorityService roleAuthorityService;

	@RequestMapping(value = "/saveRoleAuthority")
	@ResponseBody
	public String saveRoleAuthority(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Short role = Short.valueOf(request.getParameter("role"));
		String ids = request.getParameter("ids");
		roleAuthorityService.deleteByRole(role);
		String[] idsValue = ids.split(",");
		for (int i = 0; i < idsValue.length; i++) {
			RoleAuthority roleAuthority = new RoleAuthority();
			roleAuthority.setRole(role);
			roleAuthority.setAuthorityId(idsValue[i]);
			roleAuthorityService.insert(roleAuthority);
		}
		return "{success:true}";
	}

}
