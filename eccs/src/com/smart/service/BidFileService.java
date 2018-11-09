package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import com.smart.model.BidFile;
import com.smart.dao.BidFileDao;

/**
 * BidFileService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class BidFileService {

	@Autowired
	private BidFileDao bidFileDao;

	public BidFile get(int id) {
		return bidFileDao.get(id);
	}

	public void save(BidFile entity) {
		bidFileDao.save(entity);
	}

	public void update(BidFile entity) {
		bidFileDao.update(entity);
	}

	public void delete(int id) {
		bidFileDao.delete(id);
	}

	public List<BidFile> getAll() {
		return bidFileDao.getAll();
	}

}
