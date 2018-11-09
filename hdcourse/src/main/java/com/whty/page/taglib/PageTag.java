package com.whty.page.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.TagSupport;

public class PageTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	/**
	 * 页面form名称
	 */
	private String formName;
	/**
	 * 查询action
	 */
	private String action;
	/**
	 * form的target
	 */
	private String target;

	/**
	 * 是否创建form
	 */
	private boolean createForm = false;

	/**
	 * 当前页码
	 */
	private long currentPage;
	/**
	 * 总页码
	 */
	private long totalPage;
	/**
	 * 每页大小
	 */
	private long pageSize;
	/**
	 * 总条数
	 */
	private long totalRows;

	public int doStartTag() throws JspTagException {
		return SKIP_BODY;
	}

	private final long showAll = 8;
	private final long beforeCurrent = 1;
	private final long afterCurrent = 3;

	public int doEndTag() throws JspTagException {
		try {
			// 极端情况,没有任何数据,为了显示效果，进行处理
			if (totalPage == 0 && currentPage == 0) {
				totalPage = 1;
				currentPage = 1;
			}
			long start = 1;
			long end = totalPage;
			StringBuffer out = new StringBuffer();

			out.append("<script type=\"text/javascript\" language=\"javaScript\">\r\n");

			out.append("	function doWork(){\r\n");
			String newFormName = formName == null || formName.trim().length() == 0 ? "forms[0]" : formName;

			out.append("	document.").append(newFormName).append(".action=\"").append(action).append("\";\r\n");
			if (target != null && target.trim().length() > 0) {
				out.append("	document.").append(newFormName).append(".target=\"").append(target).append("\";\r\n");
			}
			out.append("	document." + newFormName + ".submit();\r\n");
			out.append("	}\r\n");
			out.append("</script>\r\n");
			if (isCreateForm()) {
				if (formName != null && formName.trim().length() > 0) {
					out.append("<form name=\"" + formName.trim() + "\" method=\"post\" action=\"\">\r\n");
				} else {
					out.append("<form method=\"post\" action=\"\">\r\n");
				}
			}
			out.append("<input type=\"hidden\" name=\"currPage\" id=\"currPage\" value=\"" + currentPage + "\" >\r\n");
			out.append("<input type=\"hidden\" name=\"totalPage\" id=\"totalPage\" value=\"" + totalPage + "\">\r\n");
			out.append("<input type=\"hidden\" name=\"pageSize\" id=\"pageSize\" value=\"" + pageSize + "\">\r\n");
			out.append("<div class=\"base_page clearfix\">\r\n");
			out.append("<p class=\"fl\"><span>共<strong>" + totalRows
					+ "</strong>条记录</span> <span>每页 <select name=\"\" onchange=\"changePageSize('selPageSize')\" id=\"selPageSize\">");
			if (pageSize == 20) {
				out.append("<option>10</option><option selected=\"selected\">20</option><option>30</option>");
			} else if (pageSize == 30) {
				out.append("<option>10</option><option>20</option><option selected=\"selected\">30</option>");
			} else {
				out.append("<option selected=\"selected\">10</option><option>20</option><option>30</option>");
			}
			out.append("</select> 条 </span> </p>");
			out.append("<a href=\"javascript:void(0);\" class=\"btn\" onclick=\"gotoFirstPage();\">首页</a>\r\n");
			out.append("<a class=\"btn\" href=\"javascript:void(0);\" onclick=\"gotoPrePage();\">上一页</a>\r\n");

			if (totalPage > showAll) {
				// 总页数大于showAll页
				if (currentPage < showAll) {
					end = showAll;
				} else {
					for (long i = 1; i <= 2; i++) {
						out.append("<a href=\"#\" onclick=\"gotoPage(").append(i).append(");\">").append(i)
								.append("</a>\r\n");
					}
					out.append("<span>... </span>\r\n");
					start = currentPage - beforeCurrent;
					end = currentPage + afterCurrent;
					if (end >= totalPage) {
						end = totalPage;
						start = totalPage - afterCurrent - beforeCurrent;
					}
				}
			}
			appendItems(out, start, end, currentPage);

			out.append("<a class=\"btn\" href=\"javascript:void(0);\" onclick=\"gotoNextPage();\">下一页</a>\r\n");
			out.append("<a href=\"javascript:void(0);\" class=\"btn\" onclick=\"goToLastPage();\">末页</a>");
			out.append("</div>\r\n");
			if (isCreateForm()) {
				out.append("</form>");
			}

			// 翻页js函数
			out.append("<script type=\"text/javascript\" language=\"javaScript\">\r\n");

			out.append("var currentPage='" + currentPage + "';\r\n");
			out.append("var thePage=0;\r\n");
			out.append("var lastPage='" + totalPage + "';\r\n");
			out.append("\r\n");
			out.append("currentPage='" + currentPage + "';\r\n");
			out.append("lastPage='" + totalPage + "';\r\n");

			out.append("$(document).ready(function(){\r\n");
			out.append(" });\r\n");
			out.append("\r\n");

			out.append("	function resetPageParam(){\r\n");
			out.append("$('#currPage').val('1');\r\n");
			out.append("}\r\n");

			out.append("	function gotoPage(thePage){\r\n");
			out.append("if(thePage==currentPage){\r\n");
			out.append("$.alert('当前所在页就是第'+thePage+'页!');\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			out.append("if(parseInt(thePage)<=0){\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			out.append("$('#currPage').val(thePage);\r\n");
			out.append("doWork();\r\n");
			out.append("}\r\n");

			out.append("function gotoPrePage(){\r\n");
			out.append("if(currentPage=='1'){\r\n");
			out.append("$.alert('当前所在页是第1页!');\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			// 没有任何数据时,提示
			out.append("if(currentPage=='0'){\r\n");
			out.append("$.alert('当前所在页是第1页!');\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			out.append("thePage=parseInt(currentPage)-1;\r\n");
			out.append("gotoPage(thePage);\r\n");
			out.append("}\r\n");

			out.append("function gotoNextPage(){\r\n");
			out.append("if(currentPage==lastPage){\r\n");
			out.append("$.alert('当前所在页是最后一页!');\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			out.append("thePage=parseInt(currentPage)+1;\r\n");
			out.append("gotoPage(thePage);\r\n");
			out.append("}\r\n");
			out.append("\r\n");

			out.append("function goToLastPage(){\r\n");
			out.append("if(currentPage==lastPage){\r\n");
			out.append("$.alert('当前所在页就是最后一页!');\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			out.append("thePage=lastPage;\r\n");
			out.append("gotoPage(lastPage);\r\n");
			out.append("}\r\n");

			out.append("function gotoFirstPage(){\r\n");
			out.append("if(currentPage=='1'){\r\n");
			out.append("$.alert('当前所在页是第1页!');\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			// 没有任何数据时,提示
			out.append("if(currentPage=='0'){\r\n");
			out.append("$.alert('当前所在页是第1页!');\r\n");
			out.append("return false;\r\n");
			out.append("}\r\n");
			out.append("thePage=1;\r\n");
			out.append("gotoPage(thePage);\r\n");
			out.append("}\r\n");

			// 改变pageSize
			out.append("function changePageSize(pageSize){\r\n");
			out.append("$(\"#pageSize\").val($(\"#\"+pageSize).val());\r\n");
			out.append("$('#currPage').val(1);\r\n");
			out.append("doWork();\r\n");
			out.append("}\r\n");

			out.append("</script>\r\n");
			pageContext.getOut().write(out.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	private void appendItems(StringBuffer out, long start, long end, long currentPage) {
		for (long i = start; i <= end; i++) {
			if (i == currentPage) {
				out.append("<span class=\"on\">").append(i).append("</span>\r\n");
			} else {
				out.append("<a href=\"#\" onclick=\"gotoPage(" + i + ");\">" + i + "</a> \r\n");
			}
		}
	}

	public void doInitBody() throws JspTagException {
	}

	public void setBodyContent(BodyContent bodyContent) {
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public boolean isCreateForm() {
		return createForm;
	}

	public void setCreateForm(boolean createForm) {
		this.createForm = createForm;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(long totalRows) {
		this.totalRows = totalRows;
	}
}
