package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.smart.model.T_Invoice;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_InvoiceDao;

/**
 * T_InvoiceService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_InvoiceService {

	@Autowired
	private T_InvoiceDao t_InvoiceDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_Invoice get(int id) {
		return t_InvoiceDao.get(id);
	}

	public void save(T_Invoice entity) {
		t_InvoiceDao.save(entity);
	}

	public void update(T_Invoice entity) {
		t_InvoiceDao.update(entity);
	}

	public void delete(int id) {
		t_InvoiceDao.delete(id);
	}

	public List<T_Invoice> getAll() {
		return t_InvoiceDao.getAll();
	}

	// 发票报表
	public Page<T_Invoice> getPage(int pageNo, int pageSize, String pName,
			String iNo, String pNo, Integer reStatus, String rtimec,
			String rtimej) {
		StringBuilder hql = new StringBuilder(
				"from T_Invoice o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(pName)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + pName + "%");
		}
		if (!StringUtil.isBlank(iNo)) {
			hql.append("and o.invoiceNo like ? ");
			paramList.add("%" + iNo + "%");
		}
		if (!StringUtil.isBlank(pNo)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + pNo + "%");
		}
		if (reStatus != null && reStatus != 0) {
			hql.append("and o.reStatus = ? ");
			paramList.add(reStatus);
		}
		if (!StringUtil.isBlank(rtimec)) {
			hql.append(" and o.invoiceDate>='" + rtimec + "'");
		}
		if (!StringUtil.isBlank(rtimej)) {
			hql.append(" and o.invoiceDate<'" + rtimej + "'");
		}
		hql.append(" order by o.id desc");
		return t_InvoiceDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	// 收款报表
	public Page<T_Invoice> getPages(int pageNo, int pageSize, String pName,
			String iNo, String pNo, String unit, String rtimec, String rtimej) {
		StringBuilder hql = new StringBuilder(
				"from T_Invoice o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(pName)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + pName + "%");
		}
		if (!StringUtil.isBlank(iNo)) {
			hql.append("and o.invoiceNo like ? ");
			paramList.add("%" + iNo + "%");
		}
		if (!StringUtil.isBlank(pNo)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + pNo + "%");
		}
		if (!StringUtil.isBlank(unit)) {
			hql.append("and o.payCompany like ? ");
			paramList.add("%" + unit + "%");
		}
		if (!StringUtil.isBlank(rtimec) && StringUtil.isBlank(rtimej)) {// 同一个项目有多个到账日期有交叉
			hql.append(
					" and o.id in (select invoice from T_Receivables where arrivalDate >= '"
							+ rtimec + "')");
		}
		if (!StringUtil.isBlank(rtimej) && StringUtil.isBlank(rtimec)) {
			hql.append(
					" and o.id in (select invoice from T_Receivables where arrivalDate <= '"
							+ rtimej + "')");
		}
		if (!StringUtil.isBlank(rtimec) && !StringUtil.isBlank(rtimej)) {
			hql.append(
					" and o.id in (select invoice from T_Receivables where arrivalDate <= '"
							+ rtimej + "' and arrivalDate >='" + rtimec + "')");
		}
		hql.append(" order by o.id desc");
		return t_InvoiceDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	/**
	 * 查询累计开票
	 * 
	 * @return
	 */
	public List<T_Invoice> getcAccount() {
		String hql = "select o.project.id,sum(o.invoiceAmount) from T_Invoice o  where o.status = 1 and fistatu=1 group by o.project.id";
		return t_InvoiceDao.getList(hql);
	}

	// 累计开票
	public List<T_Invoice> getList(Integer id) {
		String hql = "select sum(invoiceAmount) from T_Invoice where status=1 and fistatu=1  GROUP BY project HAVING project="
				+ id + "";
		return t_InvoiceDao.getList(hql);
	}

	// 截止当前发票日期累计发票金额
	public List<T_Invoice> getgrand(Date time, Integer id) {
		String hql = "select sum(invoiceAmount) from T_Invoice where invoiceDate<='"
				+ time
				+ "' and status=1 and fistatu=1  GROUP BY project HAVING project="
				+ id + "";
		return t_InvoiceDao.getList(hql);
	}

	// 截止当前发票日期累计发票金额--编辑专用
	public List<T_Invoice> getgrands(Date time, Integer id) {
		String hql = "select sum(invoiceAmount) from T_Invoice where invoiceDate<'"
				+ time
				+ "' and status=1 and fistatu=1  GROUP BY project HAVING project="
				+ id + "";
		return t_InvoiceDao.getList(hql);
	}

	public List<T_Invoice> getAlls() {
		String hql = "from T_Invoice o  where o.status = 1";
		return t_InvoiceDao.getList(hql);
	}

	// 条件查询不分页 发票统计打印
	public List<T_Invoice> getAll(String pName, String iNo, String pNo,
			Integer reStatus, String rtimec, String rtimej) {
		StringBuilder hql = new StringBuilder(
				"from T_Invoice o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(pName)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + pName + "%");
		}
		if (!StringUtil.isBlank(iNo)) {
			hql.append("and o.invoiceNo like ? ");
			paramList.add("%" + iNo + "%");
		}
		if (!StringUtil.isBlank(pNo)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + pNo + "%");
		}
		if (reStatus != null && reStatus != 0) {
			hql.append("and o.reStatus = ? ");
			paramList.add(reStatus);
		}
		if (!StringUtil.isBlank(rtimec)) {
			hql.append(" and o.invoiceDate>='" + rtimec + "'");
		}
		if (!StringUtil.isBlank(rtimej)) {
			hql.append(" and o.invoiceDate<'" + rtimej + "'");
		}
		hql.append(" order by o.id desc");
		return t_InvoiceDao.getList(hql.toString(), paramList);
	}

	// 条件查询不分页 收款统计打印
	public List<T_Invoice> getPages(String pName, String iNo, String pNo,
			String unit, String rtimec, String rtimej) {
		StringBuilder hql = new StringBuilder(
				"from T_Invoice o where o.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(pName)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + pName + "%");
		}
		if (!StringUtil.isBlank(iNo)) {
			hql.append("and o.invoiceNo like ? ");
			paramList.add("%" + iNo + "%");
		}
		if (!StringUtil.isBlank(pNo)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + pNo + "%");
		}
		if (!StringUtil.isBlank(unit)) {
			hql.append("and o.payCompany like ? ");
			paramList.add("%" + unit + "%");
		}
		if (!StringUtil.isBlank(rtimec) && StringUtil.isBlank(rtimej)) {// 同一个项目有多个到账日期有交叉
			hql.append(
					" and o.id in (select invoice from T_Receivables where arrivalDate >= '"
							+ rtimec + "')");
		}
		if (!StringUtil.isBlank(rtimej) && StringUtil.isBlank(rtimec)) {
			hql.append(
					" and o.id in (select invoice from T_Receivables where arrivalDate <= '"
							+ rtimej + "')");
		}
		if (!StringUtil.isBlank(rtimec) && !StringUtil.isBlank(rtimej)) {
			hql.append(
					" and o.id in (select invoice from T_Receivables where arrivalDate <= '"
							+ rtimej + "' and arrivalDate >='" + rtimec + "')");
		}
		hql.append(" order by o.id desc");
		return t_InvoiceDao.getList(hql.toString(), paramList);
	}

}
