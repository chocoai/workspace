package com.smart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart.dao.BidInfoQuoteDao;
import com.smart.model.BidInfoQuote;
import com.smart.model.ProjectInfo;

/**
 * BidInfoQuoteService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidInfoQuoteService {
	
	
	@Autowired
	private BidInfoQuoteDao bidInfoQuoteDao;
	
	
	
	
	
	//====================== 基本 C R U D 方法  ===========================
	public BidInfoQuote get(Integer id) {
		return bidInfoQuoteDao.get(id);
	}
	
	public void save(BidInfoQuote entity) {
		bidInfoQuoteDao.save(entity);
	}
	
	public void update(BidInfoQuote entity) {
		bidInfoQuoteDao.update(entity);
	}
	
	public void delete(Integer id) {
		bidInfoQuoteDao.delete(id);
	}
	
	public List<BidInfoQuote> getAll() {
		return bidInfoQuoteDao.getAll();
	}

	/**
	 * @param projectInfo
	 * @return
	 */
	public List<BidInfoQuote> getInfoQuoteByProjectInfo(
			ProjectInfo projectInfo) {
		StringBuilder hql = new StringBuilder();
		hql.append("from BidInfoQuote o where o.projectInfo like ? ");
		hql.append("order by o.cid asc");
		return bidInfoQuoteDao.getList(hql.toString(), projectInfo);
	}
	
}

