package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.smart.model.T_Receivables;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.T_ReceivablesDao;

/**
 * T_ReceivablesService. @author Auto Tools by 充满智慧的威哥
 */
@Service
public class T_ReceivablesService {

	@Autowired
	private T_ReceivablesDao t_ReceivablesDao;

	// ====================== 基本 C R U D 方法 ===========================
	public T_Receivables get(int id) {
		return t_ReceivablesDao.get(id);
	}

	public void save(T_Receivables entity) {
		t_ReceivablesDao.save(entity);
	}

	public void update(T_Receivables entity) {
		t_ReceivablesDao.update(entity);
	}

	public void delete(int id) {
		t_ReceivablesDao.delete(id);
	}

	public List<T_Receivables> getAll() {
		return t_ReceivablesDao.getAll();
	}

	/**
	 * 发票累计到账
	 * 
	 * @return
	 */
	public List<T_Receivables> getByIdAccount(int id) {
		String hql = "select sum(o.arrivalAmount) from T_Receivables o  where o.status = 1 and o.invoice.id="
				+ id;
		return t_ReceivablesDao.getList(hql);
	}

	public List<T_Receivables> getsumAccount() {
		String hql = "select o.invoice.id,sum(o.arrivalAmount) from T_Receivables o  where o.status = 1 group by o.invoice.id";
		return t_ReceivablesDao.getList(hql);
	}

	public Page<T_Receivables> getPage(int pageNo, int pageSize,
			String projectname, String invoiceNo, String projectno,
			String payCompany, String rtimec, String rtimej) {
		StringBuilder hql = new StringBuilder(
				"from T_Receivables c where c.status = 1 ");
		List<Object> paramList = new ArrayList<Object>();
		if (!StringUtil.isBlank(projectname)) {
			hql.append(
					"and c.invoice.project in (select id from Project where name like '%"
							+ projectname + "%')");
		}
		if (!StringUtil.isBlank(rtimec)) {
			hql.append(" and c.arrivalDate>='" + rtimec + "'");
		}
		if (!StringUtil.isBlank(rtimej)) {
			hql.append(" and c.arrivalDate<'" + rtimej + "'");
		}
		if (!StringUtil.isBlank(invoiceNo)) {
			hql.append(
					"and c.invoice in (select id from T_Invoice where invoiceNo like '%"
							+ invoiceNo + "%')");
		}
		if (!StringUtil.isBlank(projectno)) {
			hql.append(
					"and c.invoice.project in (select id from Project where no like '%"
							+ projectno + "%')");
		}
		if (!StringUtil.isBlank(payCompany)) {
			hql.append(
					"and c.invoice in (select id from T_Invoice where payCompany like '%"
							+ payCompany + "%')");
		}
		hql.append(" order by c.id desc");
		return t_ReceivablesDao.getPage(pageNo, pageSize, hql.toString(),
				paramList);
	}

	// 根据发票id查询收款信息
	public List<T_Receivables> getList(Integer lire) {
		String hql = "from T_Receivables c where c.status = 1 and c.invoice="
				+ lire + " order by c.arrivalDate desc";
		return t_ReceivablesDao.getList(hql);
	}

	// 当前发票累计收款金额
	public List<T_Receivables> getCumulative(Date arrivalDate,
			Integer invoice) {
		String hql = "select SUM(arrivalAmount) from T_Receivables where arrivalDate<='"
				+ arrivalDate + "' and invoice=" + invoice
				+ " and status=1 GROUP BY invoice ";
		return t_ReceivablesDao.getList(hql);
	}

	// 当前发票累计收款金额-编辑专用
	public List<T_Receivables> getCumulatives(Date arrivalDate,
			Integer invoice) {
		String hql = "select SUM(arrivalAmount) from T_Receivables where arrivalDate<'"
				+ arrivalDate + "' and invoice=" + invoice
				+ " and status=1 GROUP BY invoice ";
		return t_ReceivablesDao.getList(hql);
	}

	// 累计收款总和
	public List<T_Receivables> getAccount(Integer invoice) {
		String hql = "select SUM(arrivalAmount) from T_Receivables where invoice="
				+ invoice + " and status=1 GROUP BY invoice ";
		return t_ReceivablesDao.getList(hql);
	}

	public List<T_Receivables> getlist() {
		String hql = "from T_Receivables where status=1 ";
		return t_ReceivablesDao.getList(hql);
	}
}
