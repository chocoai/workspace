package com.yhcrt.service.signUp;

import static com.yhcrt.utils.Constants.ATHLETE_BASE_INFO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.Template.AthleteTemplate;
import com.yhcrt.entity.signUp.AthleteBaseInfo;
import com.yhcrt.entity.system.SysListData;
import com.yhcrt.service.stsyem.SysDictService;
import com.yhcrt.shiro.TokenManager.TokenManager;
import com.yhcrt.utils.Constants;
import com.yhcrt.utils.DateUtil;
import com.yhcrt.utils.ExportExcel;
import com.yhcrt.utils.GetPinyin;
import com.yhcrt.utils.GetSequence;
import com.yhcrt.utils.ImportExcel;
import com.yhcrt.utils.StringUtils;
import com.yhcrt.utils.validate.RegexUtils;

/**
 * 运动员基本信息的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("athleteBaseInfoService")
public class AthleteBaseInfoService {
	@Resource
	private DaoSupport dao;
	@Resource(name="sysDictService")
	private SysDictService sysDictService;
	
	/**
	 * 新增参参赛运动员的基本信息
	 * @param UnitInfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void insert(AthleteBaseInfo athleteBaseInfo) throws Exception{
		int cid = GetSequence.getSequenceByName(dao, ATHLETE_BASE_INFO);
		athleteBaseInfo.setCid(cid);
		athleteBaseInfo.setCreaRen(TokenManager.getUserCode());
		athleteBaseInfo.setCreaTime(DateUtil.getTime());
		athleteBaseInfo.setErsionNum(TokenManager.getErsionNum());
		dao.save("AthleteBaseInfoMapper.insert", athleteBaseInfo);
	}
	/**
	 * 更新参赛运动员的基本信息
	 * @param UnitInfo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateByPrimaryKey(AthleteBaseInfo athleteBaseInfo) throws Exception{
		athleteBaseInfo.setErsionNum(TokenManager.getErsionNum());
		dao.update("AthleteBaseInfoMapper.updateByPrimaryKey", athleteBaseInfo);
	}
	
	/**
	 * 根据ID获取参赛运动员的基本信息
	 * @param getById
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public AthleteBaseInfo getById(Integer cid) throws Exception{
		if(cid!= null){
			return (AthleteBaseInfo) dao.findForObject("AthleteBaseInfoMapper.getById", cid);
		}
		return  null;
	}
	/**
	 * 根据ID批量获取参赛运动员的基本信息
	 * @param getByIds
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AthleteBaseInfo> getByIds(Integer[] cids){
		if(cids!= null&&cids.length>0){
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("cids", cids);
			try {
				return (List<AthleteBaseInfo>) dao.findForList("AthleteBaseInfoMapper.getByIds", cids);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return  null;
	}
	/**
	 * 批量删除参赛运动员的基本信息
	 * @param getById
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String deleteAll(Integer[] cids) throws Exception{
		String result= "nodelete";
		//查询运动员是否参赛
		Integer countDetail = (Integer) dao.findForObject("ParticipatDetailMapper.countDetail", cids);
		if(countDetail==0){
			//查询团队信息表中运动的数量
			dao.delete("AthleteBaseInfoMapper.deleteAll",cids);
			result="success";
		}
		return result;
	}
	
	/**
	 * 通过运动员姓名/简称或单位查询参赛单位信息
	 * @param findContent
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AthleteBaseInfo> quertAll(AthleteBaseInfo athleteBaseInfo) throws Exception{
		athleteBaseInfo.setErsionNum(TokenManager.getErsionNum());
		return (ArrayList<AthleteBaseInfo>)dao.findForList("AthleteBaseInfoMapper.queryAll", athleteBaseInfo);
	}
	
	/**
	 * 根据单位cid查询单位下所有的运动员
	 * @param cid
	 * @return 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AthleteBaseInfo> selectAthlete(Integer athletesType,Integer[] unitCids,Integer athleteCids[],Integer isSex) {
		List<AthleteBaseInfo> list = null;
		
	    HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("athletesType", athletesType);
		map.put("unitCids", unitCids);
		map.put("athleteCids", athleteCids);
		
		map.put("isSex", isSex);
		try {
			list = (List<AthleteBaseInfo>) dao.findForList("AthleteBaseInfoMapper.selectALLAthlete", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	
		
	}
	/**
	 * @Title: isName
	 * @Description: 判断参赛运动员是否重名
	 * @return: boolean
	 * @throws Exception 
	 */
	public boolean isName(String name) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("ersionNum", TokenManager.getErsionNum());
		Integer count = (Integer) dao.findForObject("AthleteBaseInfoMapper.countByName", name);
		return count >0 ?true :false;
	}
	/**
	 * @Title: isName
	 * @Description: 判断参赛运动员是否已录入
	 * @return: boolean
	 * @throws Exception 
	 */
	public boolean isIDcard(String IDcard) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("IDcard",IDcard);
		map.put("ersionNum", TokenManager.getErsionNum());
		Integer count = (Integer) dao.findForObject("AthleteBaseInfoMapper.countByIDcard", map);
		return count >0 ?true :false;
	}
	
	/**
	 * @Title: isManyUnit
	 * @Description: 判断参赛运动员是跨单位
	 * @return: boolean
	 */
	public boolean isManyUnit(String IDcard, Integer unitCid) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("IDcard", IDcard);
		map.put("unitCid", unitCid);
		map.put("ersionNum", TokenManager.getErsionNum());
		Integer count = (Integer) dao.findForObject("AthleteBaseInfoMapper.countByIDcardAndUnit", map);
		return count >0 ?true :false;
	}
	/**
	 * @Title: updayeAthlete
	 * @Description: 切换运动员的单位
	 * @return: void
	 * @throws Exception 
	 */
	public void updateAthlete(Integer unitCid, Integer[] ahtleteCids) throws Exception {
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("unitCid", unitCid);
		map.put("ahtleteCids", ahtleteCids);
		dao.update("AthleteBaseInfoMapper.updateAthlete", map);
		
	}
	/**
	 * @Title: saveExcel
	 * @Description: 导入运动员
	 * @return: void
	 * @throws IOException,Exception 
	 */
	@SuppressWarnings("unchecked")
	public String saveExcel(Integer unitCid, MultipartFile file) throws IOException,Exception {
			if(!file.isEmpty()){
				int startRow=2;//从第几行开始
	    	    int endRow=3;//到第几行结束(0表示所有行;正数表示到第几行结束; 负数表示到倒数第几行结束)
	    	    List<AthleteTemplate> AthleteTemplates = (List<AthleteTemplate>) ImportExcel.importExcel(file, startRow, endRow, AthleteTemplate.class);
				List<SysListData> dictList =  sysDictService.selectByItemType("athletesType");
	    	    
				for (AthleteTemplate bean : AthleteTemplates) {
					if(StringUtils.isBlank(bean.getAthleteName())){
						//日志
						continue;
					}
					if(StringUtils.isBlank(bean.getSex())){
						//日志
						continue;
					}
					if(StringUtils.isBlank(bean.getBirthday())&&DateUtil.isValidDate1(bean.getBirthday())){
						//日志
						continue;
					}
					if(StringUtils.isBlank(bean.getAthletesType())){
						//日志
						continue;
					}
					if(StringUtils.isBlank(bean.getIdCard())&&!RegexUtils.checkDigit(bean.getIdCard())){
						//日志
						continue;
					}
					if(isIDcard(bean.getIdCard())){
						//日志
						continue;
					}
					
					AthleteBaseInfo info = new AthleteBaseInfo();
					info.setAthleteName(bean.getAthleteName());
					info.setAbbreviation(GetPinyin.getPinYinHeadChar(bean.getAthleteName()));
					info.setAlias(bean.getAlias());
					info.setNation(bean.getNation());
					info.setUnitCid(unitCid);
					
					Integer sex = bean.getSex()=="男"? 0:1;
					info.setSex(sex);
					info.setBirthday(DateUtil.stringToString(bean.getBirthday()));
					
					String athletesType = bean.getAthletesType();
					Integer type = 0;
					for (SysListData dict : dictList) {
						if(athletesType.equals(dict.getItemKey())){
							type = Integer.parseInt(dict.getItemValue());
							break;
						}
					}
					info.setAthletesType(type);
					
					Integer isExamine = bean.getIsExamine()=="否"?1:0;
					info.setIsExamine(isExamine);
					info.setIdCard(bean.getIdCard());
					info.setOrderNum(Constants.Middle);
					insert(info);
				}
				return "success";
			}else{
				return "noformat";
			}
	}
	/**
	 * @return 
	 * @Title: saveExcel
	 * @Description: 导入运动员
	 * @return: void
	 * @throws IOException,Exception 
	 */
	@SuppressWarnings("unchecked")
	public byte[] saveExcel1(Integer unitCid, MultipartFile file){
			
			if (file.isEmpty()) {
				System.out.println("文件不存在");
			}
			int startRow=1;//从第几行开始
	    	// 判断文件是否存在
	        HSSFWorkbook wb = null;
	        InputStream fis=null;
	        ByteArrayOutputStream outputStream=null;
	        AthleteTemplate bean=null;
	        
	        List<AthleteTemplate> list=null;
	        String[][] datas = null;
	        int cellNum = 0;//列数
	        int rowNum = 0;//行数
	        String rowTitle [] = null;
	        try {
				fis = file.getInputStream();
	    		// 去读Excel
	    		wb = new HSSFWorkbook(fis);
	    		HSSFSheet sheet = wb.getSheetAt(0);
	    		sheet.autoSizeColumn(1, true); 
	    		// 获取最后行号
	    		rowNum = sheet.getLastRowNum();
	    		
	    		if (rowNum < startRow) { // 如果<开始行数，表示无数据
	    			//日志返回
	    		}
	    		List<SysListData> dictList =  sysDictService.selectByItemType("athletesType");
	    		HSSFRow row = null;
	    		// 循环读取
	    		outer:for (int i = startRow; i < rowNum; i++) {
	    			row = sheet.getRow(i);
	    			if(i==1){
	    				cellNum = row.getLastCellNum();
	    				rowTitle = new String[cellNum];
	    				for (int j = 0; j < cellNum; j++) {
							String value = row.getCell(j).getStringCellValue();//默认为所有单元格中数值都为文本
							rowTitle[j] = value;
	    				}
	    				datas = new String[rowNum-startRow][3];
	    				datas[i-startRow][0] = "状态";
	    				datas[i-startRow][1] = "时间";
	    				datas[i-startRow][2] = "详情";
	    				continue;
	    			}
	    			if (row == null) {
	    				datas[i-startRow][0] = "失败";
	    				datas[i-startRow][1] = DateUtil.getTime();
	    				datas[i-startRow][2] = "无数据";
	    				//日志
	    				continue;
	    			}						
					list=new ArrayList<AthleteTemplate>();
					Field[] declaredFields = AthleteTemplate.class.getDeclaredFields();
					bean = AthleteTemplate.class.newInstance();
					String attribute = null;
					// 获取每一单元格的值
					for (int j = 0; j < cellNum; j++) {
						HSSFCell cell =  row.getCell(j);
						//String value = row.getCell(j).getStringCellValue();//默认为所有单元格中数值都为文本
						
						if (cell == null || StringUtils.isBlank(ImportExcel.getCellValue(cell))) {
							//日志
							datas[i-startRow][0] = "失败";
		    				datas[i-startRow][1] = DateUtil.getTime();
		    				datas[i-startRow][2] = "【"+rowTitle[j]+"】的值为空";
		    				continue outer;
						}
						attribute=declaredFields[j].getName().toString();
						ImportExcel.setAttrributeValue(bean,attribute,ImportExcel.getCellValue(cell));
					}
					if(!DateUtil.isValidDate1(bean.getBirthday())){
						//日志
						datas[i-startRow][0] = "失败";
	    				datas[i-startRow][1] = DateUtil.getTime();
	    				datas[i-startRow][2] = "出生日期格式不正确";
						continue;
					}
					if(!RegexUtils.checkDigit(bean.getIdCard())){
						//日志
						datas[i-startRow][0] = "失败";
	    				datas[i-startRow][1] = DateUtil.getTime();
	    				datas[i-startRow][2] = "证件号格式不正确";
						continue;
					}
					if(isIDcard(bean.getIdCard())){
						//日志
						datas[i-startRow][0] = "失败";
	    				datas[i-startRow][1] = DateUtil.getTime();
	    				datas[i-startRow][2] = "该运动员已被录入";
						continue;
					}
					
					AthleteBaseInfo info = new AthleteBaseInfo();
					info.setAthleteName(bean.getAthleteName());
					info.setAbbreviation(GetPinyin.getPinYinHeadChar(bean.getAthleteName()));
					info.setAlias(bean.getAlias());
					info.setNation(bean.getNation());
					info.setUnitCid(unitCid);
					
					Integer sex = bean.getSex()=="男"? 0:1;
					info.setSex(sex);
					info.setBirthday(DateUtil.stringToString(bean.getBirthday()));
					
					String athletesType = bean.getAthletesType();
					Integer type = 0;
					for (SysListData dict : dictList) {
						if(athletesType.equals(dict.getItemKey())){
							type = Integer.parseInt(dict.getItemValue());
							break;
						}
					}
					info.setAthletesType(type);
					
					Integer isExamine = bean.getIsExamine()=="否"?1:0;
					info.setIsExamine(isExamine);
					info.setIdCard(bean.getIdCard());
					info.setOrderNum(Constants.Middle);
					insert(info);
					//日志
					datas[i-startRow][0] = "成功";
					datas[i-startRow][1] = DateUtil.getTime();
					datas[i-startRow][2] = "导入成功";
	    		}
	    		for (int i = 0; i < datas.length; i++) { //设置正文数据
	    			HSSFRow rowi = sheet.getRow(i+startRow);
	    			for (int j = 0; j <  datas[i].length; j++) {
	    				HSSFCell cell = rowi.createCell(cellNum+2+j);
	    				cell.setCellStyle(ExportExcel.setBodyStyle(wb));
	    				HSSFRichTextString text = new HSSFRichTextString(datas[i][j]);
	    				cell.setCellValue(text);
	    			}
	    		}
				outputStream=new ByteArrayOutputStream();
				wb.write(outputStream);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return outputStream.toByteArray();
	}
}
