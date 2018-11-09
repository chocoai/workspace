
package com.yhcrt.controller.stsyem;


import static com.yhcrt.utils.Constants.SYS_DICT_ROOT;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.yhcrt.controller.BaseController;
import com.yhcrt.entity.system.SysListData;
import com.yhcrt.service.stsyem.SysDictService;
import com.yhcrt.utils.page.PageBean;
import com.yhcrt.utils.validate.RegexUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 系统数据字典控制层
 * @author 陈伟
 * 2017年5月23日 下午12:56:56
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Controller
@RequestMapping("/sysDict")
public class SysDictController  extends BaseController {
	
	@Resource(name="sysDictService")
	private SysDictService sysDictService;
	
	/**
	 * 
	 * @Title: index
	 * @Description: 系统数据字典主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		model.clear();
		try {
			List<SysListData> list =  sysDictService.selectByItemType(SYS_DICT_ROOT);
			model.put("list", list);
			if(list!=null && list.size()>0){
				model.put("firstNode", list.get(0).getItemValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("stsyem/indexDict",model);
	}
	/**
	 * 
	 * @Title: indexType
	 * @Description: 系统数据字典类别主页面
	 * @return: ModelAndView
	 */
	@RequestMapping("/indexType")
	public ModelAndView indexType(){
		model.clear();
		model.put("rootNode", SYS_DICT_ROOT);
		return new ModelAndView("stsyem/indexType",model);
	}
	
	/**
	 * AJAX分页查询
	 * @param jsonString
	 * @return
	 */
	@RequestMapping("/queryPage")
	@ResponseBody
	public JSONObject queryPage(@RequestParam("page")Integer page,@RequestParam("rows")Integer pageSize,@RequestParam String itemType){
		List<SysListData> list =  new ArrayList<SysListData>();
		JSONObject json = new JSONObject();
		page = page ==null ? 1 :page;
		PageHelper.startPage(page, pageSize);
		try {
			list =  sysDictService.selectByItemType(itemType);
			PageBean<SysListData> pageInfo=new PageBean<SysListData>(list);
			json.put("total", pageInfo.getPages());
			json.put("page", pageInfo.getPageNum());
			json.put("itemType", itemType);
			json.put("records", pageInfo.getTotal());
			json.put("rows", JSONArray.fromObject(list));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 
	 * @Title: doAddInfo
	 * @Description: 新增/更新数据
	 * @return: String
	 */
	@RequestMapping("/saveToUpdate")
	@ResponseBody
	public String saveToUpdate(@RequestParam String jsonString){
		String result="";
		if (jsonString==null||jsonString.equals("")) {
			result="nodata";
		}else {
			JSONObject infoJson=JSONObject.fromObject(jsonString);
			SysListData info = (SysListData) JSONObject.toBean(infoJson, SysListData.class);
			result = validate(info);//验证文本之、隐藏值的格式化
			if(result.equals("normal")){
				Integer cid = info.getCid();
				try {
					boolean isSuccess =sysDictService.queryParam(info.getCid(),info.getItemType(),info.getItemValue());
					if(isSuccess){//验证隐藏值是否已录入
						if(cid==null){
							sysDictService.saveSelective(info);
						}else{
							sysDictService.updateByPrimaryKeySelective(info);
						}
						result="success";
					}else{
						result="empty";
					}
				} catch (Exception e) {
					result="failed";
					e.printStackTrace();
				}
			}
		}
		return result;
	};
	/**
	 * 记录执行删除
	 * @param cids
	 * @return
	 */
	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(@RequestParam Integer[] cids){
		String result="";
		if (cids==null||cids.length==0) {
			result="nodata";
		}else {
			try {
				sysDictService.deleteAll(cids);
				result= "success";
			} catch (Exception e) {
				result="failed";
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	
	/**
	 * 
	 * @Title: queryType
	 * @Description: 根据类型获取列表
	 * @return: ModelAndView
	 */
	@RequestMapping("/queryType")
	public ModelAndView queryType(@RequestParam String itemType,@RequestParam String number,@RequestParam String type,String other){
		model.clear();
		try {
			List<SysListData> list =  sysDictService.selectByItemType(itemType);
			model.put("list", list);
			model.put("number", number);
			model.put("type", type);
			model.put("other", other);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("common/dict.csi",model);
	}
	
	
	/**
	 * 
	 * @Title: validate
	 * @Description: 验证数据
	 * @return: String
	 */
	public  String validate(SysListData bean){
		if(RegexUtils.ifNotUserName(bean.getItemKey())){
			return "itemKey";
		}
		if(RegexUtils.ifNotUserName(bean.getItemValue())){
			return "itemValue";
		}
		return "normal";
	}
}
