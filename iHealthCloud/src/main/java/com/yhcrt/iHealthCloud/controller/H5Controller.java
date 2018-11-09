package com.yhcrt.iHealthCloud.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yhcrt.iHealthCloud.common.Constants;
import com.yhcrt.iHealthCloud.entity.Area;
import com.yhcrt.iHealthCloud.entity.Cart;
import com.yhcrt.iHealthCloud.entity.Goods;
import com.yhcrt.iHealthCloud.entity.Member;
import com.yhcrt.iHealthCloud.entity.Service;
import com.yhcrt.iHealthCloud.entity.ServiceProvider;
import com.yhcrt.iHealthCloud.entity.SysDictionary;
import com.yhcrt.iHealthCloud.entity.SysUser;
import com.yhcrt.iHealthCloud.entity.UserCollect;
import com.yhcrt.iHealthCloud.entity.UserComment;
import com.yhcrt.iHealthCloud.entity.YwImage;
import com.yhcrt.iHealthCloud.mapper.CartMapper;
import com.yhcrt.iHealthCloud.mapper.MemberDeviceMapper;
import com.yhcrt.iHealthCloud.mapper.MemberMapper;
import com.yhcrt.iHealthCloud.mapper.ServiceMapper;
import com.yhcrt.iHealthCloud.mapper.SysUserMapper;
import com.yhcrt.iHealthCloud.service.CartService;
import com.yhcrt.iHealthCloud.service.GoodsService;
import com.yhcrt.iHealthCloud.service.OrganizationService;
import com.yhcrt.iHealthCloud.service.ServiceProviderService;
import com.yhcrt.iHealthCloud.service.ServiceService;
import com.yhcrt.iHealthCloud.service.SysDictService;
import com.yhcrt.iHealthCloud.service.SysSequenceService;
import com.yhcrt.iHealthCloud.service.UserCollectService;
import com.yhcrt.iHealthCloud.service.UserCommentService;
import com.yhcrt.iHealthCloud.service.UserService;
import com.yhcrt.iHealthCloud.service.YwImageService;
import com.yhcrt.iHealthCloud.util.Md5PwdEncoder;
import com.yhcrt.iHealthCloud.util.OrgConst;

@Controller
@RequestMapping(value = "/h5")
public class H5Controller {

	@Autowired
	MemberDeviceMapper memberDeviceMapper;
	@Autowired
	CartMapper cartMapper;
	@Autowired
	SysUserMapper sysUserMapper;
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ServiceService serviceService;
	@Autowired
	ServiceMapper serviceMapper;
	
	@Autowired
	SysDictService sysDictService;
	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	SysSequenceService sysSequenceService;
	@Autowired
	UserCollectService userCollectService;
	@Autowired
	UserCommentService userCommentService;
	@Autowired
	OrganizationService organizationService;
	@Autowired
	ServiceProviderService serviceProviderService;
	@Autowired
	YwImageService ywImageService;


	/**
	 * h5首页
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param userId
	 * @throws IOException
	 */
	@RequestMapping()
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,Integer userId)
			throws IOException {
		JSONObject pdata = new JSONObject();
		Map<String, String> map = new HashMap<String, String>();
		map.put("current_page", "1");
		map.put("page_size", "4");
		pdata.put("biz", map);
		// 获取商品信息
		JSONObject jsStr = JSONObject.parseObject(goodsService.getDiscountedGoods(pdata));
		modelMap.put("goodsList", jsStr.get("biz"));
		//获取服务预约
		PageHelper.startPage(1, 6);
		modelMap.put("serviceList", serviceMapper.getServices(null,null,null, " create_time desc"));
		//保存用户信息
		if(null != userId && userId > 0){
			Member memberDto = memberMapper.selectByUserId(userId);
			if(null!=memberDto){
				 request.getSession().setAttribute("memberSession", memberDto);
			}
		}
		return "mall.html";
	}


	/**
	 * 商品页
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/goods")
	public String goods(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@RequestParam(name = "pageValue", defaultValue = "1") Integer page) throws IOException {
		List<SysDictionary> list = sysDictService.selectDictEnName("goods_category");
		modelMap.put("dictList", list);
		return "goods_list.html";
	}

	/**
	 * 按条件查询商品列表信息
	 * 
	 * @param dictValue
	 *            分类
	 * @param orderNum
	 *            排序
	 * @param page
	 *            当前页数
	 */
	@RequestMapping(value = "/goods/getGoodsAjax")
	@ResponseBody
	public void getGoodsAjax(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(defaultValue = "0")Integer orderNum,String first, String second, @RequestParam(name = "pageValue", defaultValue = "1") Integer page) {
		if (page < 1) {
			page = 1;
		}
		PageHelper.startPage(page, 8);
		String orderBy = null;
		if (orderNum == 1) {
			// 价格升序
			orderBy = "unit_price";
		} else if (orderNum == 2) {
			// 价格降序
			orderBy = "unit_price Desc";
		}else{
			// 热度降序
			orderBy = "iext2 Desc";
		}
		List<Goods> goods = goodsService.selectByExample(first,second,orderBy);
		PageInfo<Goods> p = new PageInfo<Goods>(goods);
		try {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("goods", goods);
			jsonObj.put("pageNum", p.getPageNum());
			jsonObj.put("pages", p.getPages());
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 商品详情页
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping("/goods/detail/{id}")
	public String goodDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@PathVariable Integer id) {
		JSONObject pdata = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_id", id);
		pdata.put("biz", map);
		// 获取商品信息
		JSONObject jsStr = JSONObject.parseObject(goodsService.getGoodsDetail(pdata));
		modelMap.put("biz", jsStr.get("biz"));
		UserCollect dto = null;
		Member member = (Member) request.getSession().getAttribute("memberSession");
		if(null!=member){
			dto = userCollectService.getUserCollect(member.getMemberId(), Constants.GOODS, id);
		}
		modelMap.put("dto", dto);
		return "goods_detail.html";
	}

	/**
	 * 登录页
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, HttpServletResponse response,String url,String code, ModelMap modelMap) throws IOException {
		modelMap.put("url", url);
		modelMap.put("code", code);
		return "login.html";
	}
	
	/**
	 * 登录方法
	 * 
	 * @param request
	 * @param response
	 * @param usercode
	 * @param password
	 */
	@RequestMapping(value = "/getLogin")
	@ResponseBody
	public void getLogin(HttpServletRequest request, HttpServletResponse response, String usercode, String password,String wxGzhOpenId) {
		try {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("status", 0);
			SysUser user = sysUserMapper.selectByUserCode(usercode);
			if (null == user)
				jsonObj.put("error", Constants.ExceptionMsg.USER_NOT_EXIST);
			else {
				if (!Md5PwdEncoder.isPasswordValid(user.getPassword(), password))
					jsonObj.put("error", Constants.ExceptionMsg.PASSWORD_ERROR);
				else {
					Member member = memberMapper.selectByUserId(user.getUserId());
					if(null!=member){
						if(wxGzhOpenId!=null&&!wxGzhOpenId.equals("")){
							member.setWxGzhOpenId(wxGzhOpenId);
							memberMapper.updateByPrimaryKey(member);
						}
						request.getSession().setAttribute("memberSession", member);
						jsonObj.put("status", 1);
					}
				}
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 进入个人中心页
	 */
	@RequestMapping(value = "/mycenter")
	public String mycenter(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap) throws IOException {
		return "mycenter.html";
	}
	
	@RequestMapping(value = "/relogin")
	public String relogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("memberSession");
		return "login.html";
	}
	
	/**
	 * 注册
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/register")
	public String register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		return "register.html";
	}
	
	/**
	 * 发短信接口
	 */
	@RequestMapping("/getTelCode")
	@ResponseBody
	public void getTelCode(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			 String userCode) {
		try {
			JSONObject pdata = new JSONObject();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_code", userCode);
			pdata.put("biz", map);
			JSONObject jsStr = JSONObject.parseObject(userService.getTelCode(pdata));
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsStr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 注册方法
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/getRegister")
	@ResponseBody
	public void getRegister(HttpServletRequest request, HttpServletResponse response,String usercode,String password,String code) throws IOException {
		try {
			JSONObject pdata = new JSONObject();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_code", usercode);
			map.put("password", password);
			map.put("captcha", code);
			pdata.put("biz", map);
			JSONObject json = JSONObject.parseObject(userService.register(pdata));
			if(Integer.parseInt(json.get("sts").toString())==1){
				SysUser user = sysUserMapper.selectByUserCode(usercode);
				if(null!=user){
					Member member = memberMapper.selectByUserId(user.getUserId());
					if(null!=member){
						request.getSession().setAttribute("memberSession", member);
					}
				}
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * 查看我的购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/cart/getPersonalCart")
	public String getPersonalCart(HttpServletRequest request,HttpServletResponse response){
		Member member =(Member) request.getSession().getAttribute("memberSession");
		if(member==null){
			request.setAttribute("cartsList", null);
			return "shoppingcart.html";
		}
		List<Cart> carts = cartService.selectByMemberId(member.getMemberId());
		request.setAttribute("cartsList", null!=carts&&carts.size()>0?carts:null);
		return "shoppingcart.html";
	}
	/**********************************************服务****************************************************/
	
	/**
	 * 服务预约
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param dictValue
	 * @param dictValue2
	 * @param areaName
	 * @param orderNum
	 * @param serviceName
	 * @param page
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/service")
	public String service(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String dictValue,
			String dictValue2, String areaName, Integer orderNum, String serviceName,
			@RequestParam(name = "pageValue", defaultValue = "1") Integer page) throws IOException {
		List<Area> areas =sysDictService.selectArea(1004);//武汉市区级别查询
		List<SysDictionary> providerType = sysDictService.selectDictEnName(OrgConst.PROVIDER_TYPE);
		List<SysDictionary> serviceType = sysDictService.selectDictEnName(OrgConst.SERVICE_TYPE);
		modelMap.put("areas", areas);
		modelMap.put("providerType", providerType);
		modelMap.put("serviceType", serviceType);
		return "service_list.html";
	}

	/**
	 * 按条件查询服务预约项目列表信息
	 * 
	 * @param dictValue
	 *            机构类型
	 * @param dictValue2
	 *            服务类型
	 * @param areaName
	 *            地区
	 * @param orderNum
	 *            排序
	 * @param serviceName
	 *            名称
	 * @param page
	 *            当前页数
	 */
	@RequestMapping(value = "/service/getServiceAjax")
	@ResponseBody
	public void getServiceAjax(HttpServletRequest request, HttpServletResponse response, String providerType,String serviceType,String area,
			@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "0")Integer orderNum) {
		if (pageNum < 1) {
			pageNum = 1;
		}
		PageHelper.startPage(pageNum, 8);
		List<Service> services = new ArrayList<>();
		String orderBy = null;
		if (orderNum == 1) {
			// 价格升序
			orderBy = "unit_price+0 ";
		} else if (orderNum == 2) {
			// 价格降序
			orderBy = "unit_price+0 desc";
		} else if (orderNum == 3) {
			// 分数降序
			orderBy = "score ";
		}else{
			// 热度降序
			orderBy = "iext2 desc";
		}
		
		services = serviceMapper.getServices(providerType, area, serviceType, orderBy);
		PageInfo<Service> p = new PageInfo<Service>(services);
		try {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("service", services);
			jsonObj.put("pageNum", p.getPageNum());
			jsonObj.put("pages", p.getPages());
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(jsonObj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 服务详情页
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping("/service/detail/{id}")
	public String serviceDetail(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@PathVariable Integer id) {
		JSONObject pdata = new JSONObject();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("service_id", id);
		pdata.put("biz", map);
		// 获取商品信息
		JSONObject jsStr = JSONObject.parseObject(serviceService.getServiceDetailItem(pdata));
		modelMap.put("biz", jsStr.get("biz"));
		UserCollect dto = null;
		Member member = (Member) request.getSession().getAttribute("memberSession");
		if(null!=member){
			dto = userCollectService.getUserCollect(member.getMemberId(), Constants.GOODS, id);
		}
		modelMap.put("dto", dto);
		return "service_detail.html";
	}
	/**
	 * 
	 * @Description: 服务商
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param id
	 * @return String
	 */
	@RequestMapping("/service/provider/{id}")
	public String serviceOrg(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			@PathVariable Integer id) {
		ServiceProvider provider = serviceProviderService.getProviderById(id);
		List<Service> lists = serviceService.selectByProviderId(id);
		
		List<YwImage> listi =  ywImageService.getByRefId(id,Constants.PROVIDER); 
		if(listi.size()<6){
			List<YwImage> listj = new ArrayList<YwImage>();
			for(int i = listi.size();i<6;i++){
				YwImage ywImage = new YwImage();
				ywImage.setImgPath("pages/mall/images/j"+(i+1)+".jpg");
				listj.add(ywImage);
			}
			modelMap.put("listj",listj);
		}
		modelMap.put("provider",provider);
		modelMap.put("lists",lists);
		modelMap.put("listi",listi);
		return "facilitator.html";
	}
	
	/**********************************************评论****************************************************/
	/**
	 * 
	 * @Description: 跳转评论页面
	 * @return String
	 */
	@RequestMapping(value="/remarks",method=RequestMethod.GET)
	public String remarks(Integer id,String type,ModelMap modelMap){
		modelMap.put("id", id);
		modelMap.put("type", type);
		return 	"all_remarks.html";	
	}
	
	@RequestMapping(value="/remarks",method=RequestMethod.POST)
	@ResponseBody
	public void remarksTo(HttpServletResponse response,@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,Integer id,String type){
		try {
			UserComment comment = new UserComment();
			comment.setRefId(id);
			comment.setCommentType(type);
			PageHelper.startPage(pageNum, 8);
			List<UserComment> list = userCommentService.selectAll(comment, null);
			PageInfo<UserComment> p = new PageInfo<UserComment>(list);
			
			JSONArray jsonList = new JSONArray();
			for(UserComment userComment : list){
				JSONObject userCommentJson = (JSONObject) JSON.toJSON(userComment);
				Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(userComment.getMemberId()));
				userCommentJson.put("userLogo", member.getHeadPic());
				userCommentJson.put("nickName", member.getNickName());
				jsonList.add(userCommentJson);
			}
			
			JSONObject json = new JSONObject();
			json.put("list", jsonList);
			json.put("id", id);
			json.put("type", type);
			json.put("pageNum", p.getPageNum());
			json.put("pages", p.getPages());
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**********************************************首页搜索****************************************************/
	/**
	 * 
	 * @Description: 搜索页面
	 * @param contentText
	 * @return String
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public String searchGet(){
		return "search.html";
	}
	/**
	 * 
	 * @Description: 类型搜索页面
	 * @return String
	 */
	@RequestMapping(value="/search/{type}",method=RequestMethod.GET)
	public String searchTo(@PathVariable String type, ModelMap modelMap){
		modelMap.put("type", type);
		return "search_detail.html";
	}

	/**
	 * 
	 * @Description: 搜索页面
	 * @param contentText
	 * @return String
	 */
	@RequestMapping(value = "/search",method=RequestMethod.POST)
	@ResponseBody
	public void searchPost(HttpServletResponse response,String type,String searchText,@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum){
		JSONObject json = new JSONObject();
		try {
			if(StringUtils.isBlank(searchText)){
				json.put("status", 0);
			}else{
				json.put("status", 1);
				searchText = URLDecoder.decode(searchText,"UTF-8");
				if(StringUtils.isBlank(type)){
					List<Goods> listg = goodsService.selectBySearchText(searchText);
					List<Service> lists = serviceService.selectBySearchText(searchText);
					json.put("listg", listg);
					json.put("lists", lists);
				}else{
					if (pageNum < 1) {
						pageNum = 1;
					}
					json.put("type", type);
					PageHelper.startPage(pageNum, 8);
					if (type.equals(Constants.GOODS)) {
						List<Goods> list = goodsService.selectBySearchText(searchText);
						PageInfo<Goods> p = new PageInfo<Goods>(list);
						json.put("list", list);
						json.put("pageNum", p.getPageNum());
						json.put("pages", p.getPages());
					} else {
						List<Service> list = serviceService.selectBySearchText(searchText);
						PageInfo<Service> p = new PageInfo<Service>(list);
						json.put("list", list);
						json.put("pageNum", p.getPageNum());
						json.put("pages", p.getPages());
					}
				}
			}
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(json.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 按父节点ID查询对应的数据字典数据
	 */
	@RequestMapping("/dict/getSysDictParentId")
	@ResponseBody
	public void getSysDictParentId(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap,
			 String parentId) {
		try {
			JSONObject pdata = new JSONObject();
			pdata.put("list", sysDictService.getSysDictParentId(parentId));
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().write(pdata.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/404")
	public String getCantFind() throws IOException {
		return "404.html";
	}
	
	@RequestMapping(value = "/500")
	public String getSystemError() throws IOException {
		return "500.html";
	}
}