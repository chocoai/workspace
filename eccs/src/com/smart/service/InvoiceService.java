package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.smart.model.Invoice;
import com.smart.dao.InvoiceDao;

/**
 * InvoiceService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class InvoiceService {

	@Autowired
	private InvoiceDao invoiceDao;

	// ====================== 基本 C R U D 方法 ===========================
	public Invoice get(int id) {
		return invoiceDao.get(id);
	}

	public void save(Invoice entity) {
		invoiceDao.save(entity);
	}

	public void update(Invoice entity) {
		invoiceDao.update(entity);
	}

	public void delete(int id) {
		invoiceDao.delete(id);
	}

	public List<Invoice> getAll() {
		return invoiceDao.getAll();
	}

}
