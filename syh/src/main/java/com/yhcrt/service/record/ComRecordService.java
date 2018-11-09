package com.yhcrt.service.record;
import static com.yhcrt.utils.Constants.COM_RECORD;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yhcrt.dao.DaoSupport;
import com.yhcrt.entity.record.ComRecord;
import com.yhcrt.utils.GetSequence;

/**
 * 竞赛项目记录的service
 * @author kejunzhong
 * 2017年5月11日
 * 版权所有：武汉炎黄创新服务有限公司
 */
@Service("comRecordService")
public class ComRecordService { 
	@Resource
	private DaoSupport dao;
	
	/**
	 * 查询所有竞赛记录库（可根据项目ID查询）
	 * @param comRecord
	 * @return
	 * @throws Exception
	 */
	public List<ComRecord> queryAll(ComRecord comRecord) throws Exception{
		return (List<ComRecord>) dao.findForList("ComRecordMapper.queryAll", comRecord);
	}
	
	/**
	 * 修改竞赛记录信息
	 * @param comRecord
	 * @throws Exception
	 */
	public void updateInfo(ComRecord comRecord) throws Exception{
		dao.update("ComRecordMapper.updateInfo", comRecord);
	}
	
	/**
	 * 新增竞赛记录信息
	 * @param comRecord
	 * @throws Exception
	 */
	public void insertNewInfo(ComRecord comRecord) throws Exception {
		comRecord.setCid(GetSequence.getSequenceByName(dao, COM_RECORD));
		comRecord.setVersionNum(0);
		comRecord.setPreCid(0);
		dao.save("ComRecordMapper.insertNewInfo", comRecord);
	}
	
	/**
	 * 删除竞赛记录信息同时将最近的一条历史记录设为最新记录
	 * @param cid
	 * @throws Exception
	 */
	public void deleteInfoByCid(Integer[] cid) throws Exception {
		for (int i = 0; i < cid.length; i++) {
			ComRecord comRecord = queryByCid(cid[i]);
			comRecord.setVersionNum(1);//获取历史版本
			List<ComRecord> list = queryAll(comRecord);
			if(list.size()>0){
				ComRecord bean = list.get(0);//将最近的一条历史记录设为最新记录
				bean.setVersionNum(0);
				updateInfo(bean);
			}
		}
		
		dao.delete("ComRecordMapper.deleteInfoByCid", cid);
	}
	
	/**
	 * 通过cid获取记录数量
	 * @param cid
	 * @throws Exception
	 */
	public Integer queryCountByProjectCid(Integer cid) throws Exception {
		return (Integer)dao.findForObject("ComRecordMapper.queryCountByCid", cid);
	}
	
	/**
	 * 刷新竞赛记录信息
	 * @param comRecord
	 * @throws Exception
	 */
	public void updateOldInfo(ComRecord comRecord) throws Exception{
		//获取旧记录信息
		ComRecord oldComRecord=(ComRecord)dao.findForObject("ComRecordMapper.queryAll", comRecord);
		oldComRecord.setVersionNum(1);//将版本设为历史版本
		comRecord.setCid(GetSequence.getSequenceByName(dao, "com_record"));
		comRecord.setVersionNum(0);		//设置新纪录生效
		comRecord.setPreCid(oldComRecord.getCid());
		dao.update("ComRecordMapper.updateInfo", oldComRecord);
		dao.save("ComRecordMapper.insertNewInfo", comRecord);
	}
	
	/**
	 * 通过CID获取记录信息
	 * @return
	 * @throws Exception 
	 */
	public ComRecord queryByCid(Integer cid) throws Exception{
		return (ComRecord)dao.findForObject("ComRecordMapper.queryByCid", cid);
	}
	/**
	 * 
	 * @Title: queryByProjectCid
	 * @Description: 获取某比赛项目的有效记录
	 * @return: ComRecord
	 */
	public ComRecord queryByProjectCid(Integer projectCid) throws Exception{
		return (ComRecord)dao.findForObject("ComRecordMapper.queryByProjectCid", projectCid);
	}
}
