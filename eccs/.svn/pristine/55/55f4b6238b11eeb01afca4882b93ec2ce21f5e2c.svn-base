package com.smart.web.action.t_customer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.T_Contact;
import com.smart.model.T_Customer;
import com.smart.model.T_file;
import com.smart.service.T_ContactService;
import com.smart.service.T_CustomerService;
import com.smart.service.T_fileService;
import com.smart.util.DateUtils;
import com.smart.util.FileRepository;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_CustomerAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_CustomerService t_CustomerService;

	@Autowired
	private T_ContactService t_ContactService;

	@Autowired
	private T_fileService t_fileService;

	private T_file t_file;

	private T_Customer t_Customer;

	private T_Contact t_Contact;

	private String contact;

	private String deptName;

	private String post;

	private String phone;

	private String telephone;

	private String email;

	private String qq;

	private String defContact;

	private String remark;

	private String Stringid;// 删除所需字符串id数组

	// 编辑联系人
	private String t_Contactcontact;

	private String t_ContactdeptName;

	private String t_Contactpost;

	private String t_Contactphone;

	private String t_Contacttelephone;

	private String t_Contactemail;

	private String t_Contactqq;

	private String t_ContactdefContact;

	private String t_Contactremark;

	private String t_Contactid;

	private File file;

	private String fileFileName;

	private String file_id;

	private String remarks;

	private String fileContentType;

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {
		t_Customer = t_Customer == null ? new T_Customer() : t_Customer;
		Page<T_Customer> pageBean = t_CustomerService.getPage(getPageNo(), 10,
				t_Customer.getCusName(), t_Customer.getCusNature(),
				t_Customer.getEmail(), t_Customer.getCusType());
		List<T_Contact> list = t_ContactService.getList();
		List<T_Customer> list2 = pageBean.getList();
		for (int i = 0; i < list2.size(); i++) {
			for (int n = 0; n < list.size(); n++) {
				if (list.get(n).getT_Customer().getId() == list2.get(i).getId()
						&& list.get(n).getDefContact() == 1) {
					list2.get(i).setContacts(list.get(n));
				}
			}
		}
		pageBean.setList(list2);
		put("pageBean", pageBean);
		return "list";
	}

	@Action("new")
	public String news() {

		return "new";
	}

	/**
	 * 添加
	 * 
	 * @return
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String saves() {
		// 新增客户
		if (t_Customer.getId() == null) {
			t_Customer.setStatus(1);
			t_Customer.setRtime(new Date());
			t_Customer.setCtime(new Date());
			t_Customer.setUser(this.getUser());
			t_CustomerService.save(t_Customer);
		} else {
			T_Customer px = t_CustomerService.get(t_Customer.getId());
			px.setCusName(t_Customer.getCusName());
			px.setCusType(t_Customer.getCusType());
			px.setCusNature(t_Customer.getCusNature());
			px.setOgCode(t_Customer.getOgCode());
			px.setCusLevel(t_Customer.getCusLevel());
			px.setLega(t_Customer.getLega());
			px.setCusHomepage(t_Customer.getCusHomepage());
			px.setPostCode(t_Customer.getPostCode());
			px.setFax(t_Customer.getFax());
			px.setBankAccount(t_Customer.getBankAccount());
			px.setAccountOpening(t_Customer.getAccountOpening());
			px.setEmail(t_Customer.getEmail());
			px.setOfficePhone(t_Customer.getOfficePhone());
			px.setAddress(t_Customer.getAddress());
			px.setRemark(t_Customer.getRemark());
			t_CustomerService.update(px);
		}

		// 新增联系人
		String[] arrcontact = {};
		String[] arrdeptName = {};
		String[] arrpost = {};
		String[] arrphone = {};
		String[] arremail = {};
		String[] arrqq = {};
		String[] arrdefContact = {};
		String[] arrremark = {};
		String[] arrtelephone = {};
		String[] arrcid = {};
		if (!StringUtil.isBlank(contact)) {
			arrcontact = contact.split(",");
			arrdeptName = deptName.split(",");
			arrpost = post.split(",");
			arrphone = phone.split(",");
			arremail = email.split(",");
			arrqq = qq.split(",");
			arrdefContact = defContact.split(",");
			arrremark = remark.split(",");
			arrtelephone = telephone.split(",");
			for (int i = 0; i < arrcontact.length; i++) {
				T_Contact pc = new T_Contact();
				pc.setContact(arrcontact[i].trim());
				pc.setDeptName(arrdeptName[i].trim());
				pc.setPost(arrpost[i].trim());
				pc.setPhone(arrphone[i].trim());
				pc.setEmail(arremail[i].trim());
				pc.setQq(arrqq[i].trim());
				pc.setDefContact(Integer.parseInt(arrdefContact[i].trim()));
				pc.setRemark(arrremark[i].trim());
				pc.setTelephone(arrtelephone[i].trim());
				pc.setT_Customer(t_Customer);
				t_ContactService.save(pc);
			}
		}
		// 编辑联系人
		if (!StringUtil.isBlank(t_Contactcontact)) {
			arrcontact = t_Contactcontact.split(",");
			arrdeptName = t_ContactdeptName.split(",");
			arrpost = t_Contactpost.split(",");
			arrphone = t_Contactphone.split(",");
			arremail = t_Contactemail.split(",");
			arrqq = t_Contactqq.split(",");
			arrdefContact = t_ContactdefContact.split(",");
			arrremark = t_Contactremark.split(",");
			arrtelephone = t_Contacttelephone.split(",");
			arrcid = t_Contactid.split(",");
			for (int i = 0; i < arrcontact.length; i++) {
				T_Contact pc = t_ContactService
						.get(Integer.parseInt(arrcid[i].trim()));
				pc.setContact(arrcontact[i].trim());
				pc.setDeptName(arrdeptName[i].trim());
				pc.setPost(arrpost[i].trim());
				pc.setPhone(arrphone[i].trim());
				pc.setEmail(arremail[i].trim());
				pc.setQq(arrqq[i].trim());
				pc.setDefContact(Integer.parseInt(arrdefContact[i].trim()));
				pc.setRemark(arrremark[i].trim());
				pc.setTelephone(arrtelephone[i].trim());
				pc.setT_Customer(t_Customer);
				t_ContactService.update(pc);
			}
		}
		if (file_id != null) {
			String[] fileid = file_id.split(",");
			for (int i = 0; i < fileid.length; i++) {
				T_file px = t_fileService
						.get(Integer.parseInt(fileid[i].trim()));
				px.setType_id(t_Customer.getId());
				t_fileService.update(px);
			}
		}
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		String[] a = getStringid().split(",");
		for (int i = 0; i < a.length; i++) {
			t_Customer = t_CustomerService.get(Integer.parseInt(a[i]));
			t_Customer.setStatus(-1);
			t_CustomerService.update(t_Customer);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 删除附件
	 * 
	 * @param id
	 * @retur
	 */
	@Action("deletefile")
	public String deletefile() {
		if (Stringid != null) {
			String[] str = Stringid.split(",");
			for (int i = 0; i < str.length; i++) {
				t_file = t_fileService.get(Integer.parseInt(str[i].trim()));
				file = new File(t_file.getPath());
				file.delete();
				t_file.setStatus(-1);
				t_fileService.update(t_file);
			}
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 删除联系人
	 * 
	 * @param id
	 * @return
	 */
	@Action("deletecls")
	public String deletecls() {
		String[] a = getStringid().split(",");
		for (int i = 0; i < a.length; i++) {
			t_Contact = t_ContactService.get(Integer.parseInt(a[i]));
			t_Contact.setStatus(-1);
			t_ContactService.update(t_Contact);
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 查看
	 * 
	 * @return
	 */
	@Action("show")
	public String show() {
		t_Customer = t_CustomerService.get(t_Customer.getId());
		Page<T_Contact> t_Contacts = t_ContactService.getPage(pageNo, pageSize,
				t_Customer.getId());
		put("t_Customer", t_Customer);
		put("t_Contact", t_Contacts);
		List<T_file> lists = t_fileService.getList(t_Customer.getId(), 2);
		put("lists", lists);
		return "show";
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	@Action("print")
	public String print() {
		t_Customer = t_CustomerService.get(t_Customer.getId());
		Page<T_Contact> t_Contacts = t_ContactService.getPage(pageNo, pageSize,
				t_Customer.getId());
		put("t_Customer", t_Customer);
		put("t_Contact", t_Contacts);
		List<T_file> lists = t_fileService.getList(t_Customer.getId(), 2);
		put("lists", lists);
		return "print";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	@Action("edit")
	public String edit() {
		t_Customer = t_CustomerService.get(t_Customer.getId());
		Page<T_Contact> t_Contacts = t_ContactService.getPage(pageNo, pageSize,
				t_Customer.getId());
		put("t_Contact", t_Contacts);
		put("t_Customer", t_Customer);
		List<T_file> list = t_fileService.getList(t_Customer.getId(), 2);
		put("list", list);
		return "new";
	}

	/**
	 * 文件上传保存
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("uploadFile")
	public String uploadFile() throws Exception {

		String filedir = "t_customer/" + this.getUser().getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String root = fileRepository.storeByExt(filedir, fileFileName, file);

		T_file fis = new T_file();
		fis.setSize(file.getTotalSpace());
		fis.setName(fileFileName);
		fis.setPath(root);
		fis.setUser(this.getUser());
		fis.setRtime(DateUtils.getCurrentTime());
		fis.setRemarks(getRemarks());
		fis.setType(2);// 客户管理
		t_fileService.save(fis);
		JSONObject json = new JSONObject();
		json.put("id", fis.getId());
		json.put("name", fis.getName());
		writeJson(json.toString());
		return null;
	}

	/**
	 * 统计
	 * 
	 * @return
	 */
	@Action("squery")
	public String squery() {
		t_Customer = t_Customer == null ? new T_Customer() : t_Customer;
		Page<T_Customer> pageBean = t_CustomerService.getPage(getPageNo(),
				getPageSize(), t_Customer.getCusName(),
				t_Customer.getCusNature(), t_Customer.getEmail(),
				t_Customer.getCusType());
		for (int i = 0; i < pageBean.getList().size(); i++) {
			pageBean.getList().get(i)
					.setContactlist(t_ContactService
							.getPage(getPageNo(), getPageSize(),
									pageBean.getList().get(i).getId())
							.getList());
		}
		put("pageBean", pageBean);
		return "squery";
	}

	/**
	 * 统计打印
	 * 
	 * @return
	 */
	@Action("squeryPrint")
	public String squeryPrint() {
		t_Customer = t_Customer == null ? new T_Customer() : t_Customer;
		List<T_Customer> list = t_CustomerService.getList1(
				t_Customer.getCusName(), t_Customer.getCusNature(),
				t_Customer.getEmail(), t_Customer.getCusType());
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setContactlist(
					t_ContactService.getLists(list.get(i).getId()));
		}
		put("list", list);
		return "squeryPrint";
	}

	@Action("excel")
	public String excel() {
		t_Customer = t_Customer == null ? new T_Customer() : t_Customer;
		Page<T_Customer> pageBean = t_CustomerService.getPage(getPageNo(),
				getPageSize(), t_Customer.getCusName(),
				t_Customer.getCusNature(), t_Customer.getEmail(),
				t_Customer.getCusType());
		List<T_Customer> list = pageBean.getList();
		int indexs = 0;
		for (int i = 0; i < list.size(); i++) {
			if (t_ContactService.getLists(list.get(i).getId()) != null
					&& t_ContactService.getLists(list.get(i).getId())
							.size() != 0) {
				indexs += t_ContactService.getLists(list.get(i).getId()).size();
				list.get(i).setContactlist(
						t_ContactService.getLists(list.get(i).getId()));
			} else {
				list.get(i).setContactlist(null);
				indexs++;

			}
		}

		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("客户登记列表");

		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		row.setHeight((short) 600);

		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		style.setWrapText(true);
		sheet.setColumnWidth(0, 100 * 20);
		sheet.setColumnWidth(1, 100 * 40);
		sheet.setColumnWidth(2, 100 * 40);
		sheet.setColumnWidth(3, 100 * 40);
		sheet.setColumnWidth(4, 100 * 40);
		sheet.setColumnWidth(5, 100 * 40);
		sheet.setColumnWidth(6, 100 * 40);
		sheet.setColumnWidth(7, 100 * 40);
		sheet.setColumnWidth(8, 100 * 40);
		sheet.setColumnWidth(9, 100 * 40);
		sheet.setColumnWidth(10, 100 * 40);
		sheet.setColumnWidth(11, 100 * 40);
		sheet.setColumnWidth(12, 100 * 40);
		sheet.setColumnWidth(13, 100 * 40);
		sheet.setColumnWidth(14, 100 * 40);
		sheet.setColumnWidth(15, 150 * 40);

		HSSFCell cell = row.createCell(0);
		cell.setCellValue("序号");
		cell.setCellStyle(style);

		cell = row.createCell(1);
		cell.setCellValue("客户名称");
		cell.setCellStyle(style);

		cell = row.createCell(2);
		cell.setCellValue("单位性质");
		cell.setCellStyle(style);

		cell = row.createCell(3);
		cell.setCellValue("单位类别");
		cell.setCellStyle(style);

		cell = row.createCell(4);
		cell.setCellValue("主页");
		cell.setCellStyle(style);

		cell = row.createCell(5);
		cell.setCellValue("电子邮箱");
		cell.setCellStyle(style);

		cell = row.createCell(6);
		cell.setCellValue("办公电话");
		cell.setCellStyle(style);

		cell = row.createCell(7);
		cell.setCellValue("邮编");
		cell.setCellStyle(style);

		cell = row.createCell(8);
		cell.setCellValue("传真");
		cell.setCellStyle(style);

		cell = row.createCell(9);
		cell.setCellValue("地址");
		cell.setCellStyle(style);

		cell = row.createCell(10);
		cell.setCellValue("备注");
		cell.setCellStyle(style);

		cell = row.createCell(11);
		cell.setCellValue("联系人");
		cell.setCellStyle(style);

		cell = row.createCell(12);
		cell.setCellValue("电话");
		cell.setCellStyle(style);

		cell = row.createCell(13);
		cell.setCellValue("手机");
		cell.setCellStyle(style);

		cell = row.createCell(14);
		cell.setCellValue("qq");
		cell.setCellStyle(style);

		cell = row.createCell(15);
		cell.setCellValue("E-mail");
		cell.setCellStyle(style);

		T_Customer tApply = new T_Customer();
		// 先创建单元格

		for (int i = 1; i <= indexs; i++) {
			row = sheet.createRow((short) (i));
			for (int j = 0; j < 16; j++) {
				row.createCell(j);
				HSSFCell tmp_cell = row.createCell(j);
				tmp_cell.setCellStyle(style);
			}
		}

		int end = 0;
		for (int i = 0; i < list.size(); i++) {
			tApply = list.get(i);
			List<T_Contact> contactList = tApply.getContactlist();

			if (contactList != null && contactList.size() > 0) {

				int start = end + 1;

				if (i == 0)
					end = i + 1 + list.get(i).getContactlist().size() - 1;
				else
					end = start + list.get(i).getContactlist().size() - 1;
				// 合并公共部分单元格
				for (int j = 0; j < 11; j++) {

					CellRangeAddress cra = new CellRangeAddress(start, end, j,
							j);
					sheet.addMergedRegion(cra);
					cell = sheet.getRow(start).getCell(j);
					cell.setCellStyle(style);
					if (j == 0) {
						cell.setCellValue(i + 1);
					} else if (j == 1) {
						cell.setCellValue(tApply.getCusName());
					} else if (j == 2) {
						if (tApply.getCusNature() == 1) {
							cell.setCellValue("委托单位");
						}
						if (tApply.getCusNature() == 2) {
							cell.setCellValue("建设单位");
						}
						if (tApply.getCusNature() == 3) {
							cell.setCellValue("施工单位");
						}
						if (tApply.getCusNature() == 4) {
							cell.setCellValue("设计单位");
						}
					} else if (j == 3) {
						if (tApply.getCusType() != null)
							if (tApply.getCusType() == 1) {
								cell.setCellValue("企业客户");
							}
						if (tApply.getCusType() != null)
							if (tApply.getCusType() == 2) {
								cell.setCellValue("政府客户");
							}
						if (tApply.getCusType() != null)
							if (tApply.getCusType() == 3) {
								cell.setCellValue("其它");
							}
					} else if (j == 4) {
						cell.setCellValue(tApply.getCusHomepage());
					} else if (j == 5) {
						cell.setCellValue(tApply.getEmail());
					} else if (j == 6) {
						cell.setCellValue(tApply.getOfficePhone());
					} else if (j == 7) {
						cell.setCellValue(tApply.getPostCode());
					} else if (j == 8) {
						cell.setCellValue(tApply.getFax());
					} else if (j == 9) {
						cell.setCellValue(tApply.getAddress());
					} else if (j == 10) {
						cell.setCellValue(tApply.getRemark());
					}
				} // end for 11

				for (int k = 0; k < contactList.size(); k++) {
					for (int j = 11; j < 16; j++) {
						cell = sheet.getRow(start + k).getCell(j);
						cell.setCellStyle(style);
						if (j == 11) {
							if (tApply.getContactlist() != null) {
								cell.setCellValue(tApply.getContactlist().get(k)
										.getContact());
							}
						} else if (j == 12) {
							if (tApply.getContactlist() != null) {
								cell.setCellValue(tApply.getContactlist().get(k)
										.getPhone());
							}
						} else if (j == 13) {
							if (tApply.getContactlist() != null) {
								cell.setCellValue(tApply.getContactlist().get(k)
										.getTelephone());
							}
						} else if (j == 14) {
							if (tApply.getContactlist() != null) {
								cell.setCellValue(
										tApply.getContactlist().get(k).getQq());
							}
						} else if (j == 15) {
							if (tApply.getContactlist() != null) {
								cell.setCellValue(tApply.getContactlist().get(k)
										.getEmail());

							}
						}
					}
				} // end for -k

			} else {
				// 正常写单元格数据
				int start = end + 1;

				if (i == 0)
					end = i + 1;
				else
					end = start;
				for (int j = 0; j < 11; j++) {

					cell = sheet.getRow(start).getCell(j);
					cell.setCellStyle(style);
					if (j == 0) {
						cell.setCellValue(i + 1);
					} else if (j == 1) {
						cell.setCellValue(tApply.getCusName());
					} else if (j == 2) {
						if (tApply.getCusNature() == 1) {
							cell.setCellValue("委托单位");
						}
						if (tApply.getCusNature() == 2) {
							cell.setCellValue("建设单位");
						}
						if (tApply.getCusNature() == 3) {
							cell.setCellValue("施工单位");
						}
						if (tApply.getCusNature() == 4) {
							cell.setCellValue("设计单位");
						}
					} else if (j == 3) {
						if (tApply.getCusType() != null)
							if (tApply.getCusType() == 1) {
								cell.setCellValue("企业客户");
							}
						if (tApply.getCusType() != null)
							if (tApply.getCusType() == 2) {
								cell.setCellValue("政府客户");
							}
						if (tApply.getCusType() != null)
							if (tApply.getCusType() == 3) {
								cell.setCellValue("其它");
							}
					} else if (j == 4) {
						cell.setCellValue(tApply.getCusHomepage());
					} else if (j == 5) {
						cell.setCellValue(tApply.getEmail());
					} else if (j == 6) {
						cell.setCellValue(tApply.getOfficePhone());
					} else if (j == 7) {
						cell.setCellValue(tApply.getPostCode());
					} else if (j == 8) {
						cell.setCellValue(tApply.getFax());
					} else if (j == 9) {
						cell.setCellValue(tApply.getAddress());
					} else if (j == 10) {
						cell.setCellValue(tApply.getRemark());
					}
				} // end for 11

				for (int j = 11; j < 15; j++) {
					cell = sheet.getRow(start).getCell(j);
					cell.setCellStyle(style);
					if (j == 10) {

						cell.setCellValue("");

					} else if (j == 11) {
						cell.setCellValue("");
					} else if (j == 12) {
						cell.setCellValue("");
					} else if (j == 13) {
						cell.setCellValue("");
					} else if (j == 14) {
						cell.setCellValue("");
					} else if (j == 15) {

					} else if (j == 16) {
						cell.setCellValue("");
					}
				} // end for -k

			}

		} // end-for
		OutputStream fout = null;
		try {
			HttpServletResponse response = getHttpServletResponse();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String(
							("客户信息.xls").getBytes("GB2312"), "ISO-8859-1"));
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			fout = response.getOutputStream();
			wb.write(fout);
			wb.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fout != null) {
				try {
					fout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	@Action("checkNO")
	public String checkNO() {
		Page<T_Customer> page = t_CustomerService.getAll1(pageNo, pageSize,
				t_Customer.getCusName());
		if (page.getList().size() > 0) {
			write("0"); // ajax请求用write返回数据
		} else {
			write("1"); // ajax请求用write返回数据
		}
		return null;
	}

	// ---------------------------------------------------------
	public T_Customer getT_Customer() {
		return t_Customer;
	}

	public void setT_Customer(T_Customer t_Customer) {
		this.t_Customer = t_Customer;
	}

	public T_Contact getT_Contact() {
		return t_Contact;
	}

	public void setT_Contact(T_Contact t_Contact) {
		this.t_Contact = t_Contact;
	}

	public T_ContactService getT_ContactService() {
		return t_ContactService;
	}

	public void setT_ContactService(T_ContactService t_ContactService) {
		this.t_ContactService = t_ContactService;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getDefContact() {
		return defContact;
	}

	public void setDefContact(String defContact) {
		this.defContact = defContact;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getT_Contactcontact() {
		return t_Contactcontact;
	}

	public void setT_Contactcontact(String t_Contactcontact) {
		this.t_Contactcontact = t_Contactcontact;
	}

	public String getT_ContactdeptName() {
		return t_ContactdeptName;
	}

	public void setT_ContactdeptName(String t_ContactdeptName) {
		this.t_ContactdeptName = t_ContactdeptName;
	}

	public String getT_Contactpost() {
		return t_Contactpost;
	}

	public void setT_Contactpost(String t_Contactpost) {
		this.t_Contactpost = t_Contactpost;
	}

	public String getT_Contactphone() {
		return t_Contactphone;
	}

	public void setT_Contactphone(String t_Contactphone) {
		this.t_Contactphone = t_Contactphone;
	}

	public String getT_Contacttelephone() {
		return t_Contacttelephone;
	}

	public void setT_Contacttelephone(String t_Contacttelephone) {
		this.t_Contacttelephone = t_Contacttelephone;
	}

	public String getT_Contactemail() {
		return t_Contactemail;
	}

	public void setT_Contactemail(String t_Contactemail) {
		this.t_Contactemail = t_Contactemail;
	}

	public String getT_Contactqq() {
		return t_Contactqq;
	}

	public void setT_Contactqq(String t_Contactqq) {
		this.t_Contactqq = t_Contactqq;
	}

	public String getT_ContactdefContact() {
		return t_ContactdefContact;
	}

	public void setT_ContactdefContact(String t_ContactdefContact) {
		this.t_ContactdefContact = t_ContactdefContact;
	}

	public String getT_Contactremark() {
		return t_Contactremark;
	}

	public void setT_Contactremark(String t_Contactremark) {
		this.t_Contactremark = t_Contactremark;
	}

	public String getT_Contactid() {
		return t_Contactid;
	}

	public void setT_Contactid(String t_Contactid) {
		this.t_Contactid = t_Contactid;
	}

	public T_file getT_file() {
		return t_file;
	}

	public void setT_file(T_file t_file) {
		this.t_file = t_file;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

}
