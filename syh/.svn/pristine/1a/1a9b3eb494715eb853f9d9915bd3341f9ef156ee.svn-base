package com.yhcrt.utils.page;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

/**
 * 列表分页。包含list属性。
 */
public class PageBean<T> extends PageInfo<T> {
    private static final long serialVersionUID = 8656597559014685635L;
    private long total;        //总记录数
    private List<T> list;    //结果集
    private int pageNum;    // 第几页
    private int pageSize;    // 每页记录数
    private int pages;        // 总页数
    private int size;        // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性
    
    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     * @param list          page结果
     * @param navigatePages 页码数量
     */
    public PageBean(List<T> list) {
	   if (list instanceof Page) {
           Page<T> page = (Page<T>) list;
           this.pageNum = page.getPageNum();
           this.pageSize = page.getPageSize();
           this.total = page.getTotal();
           this.pages = page.getPages();
           this.list = page;
           this.size = page.size();
       }
    }

	public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNum() {
    	if(pageNum>getPages()){
    		setPageNum(1);
		}
    	return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
	
	/**
	 * 
	 * @Title: getPageHtml
	 * @Description: 普通翻页
	 * @return
	 * @return: String
	 */
	public String getPageHtml(){
		StringBuffer pageHtml = new StringBuffer("<div id='pageInfo' class='pagination page-notop page'> ");
		pageHtml.append("<span ");
		if(pageNum == 1){//是否为第一页
			pageHtml.append("class='disabled' ");
			pageHtml.append("><a href='javascript:' aria-label='firstPage'><i class='icon-step-backward'></i></a></span> ");
		}else{
			pageHtml.append("><a href='javascript:' onclick='_pageclick(1)' aria-label='firstPage'><i class='icon-step-backward'></i></a></span> ");
		}
		
		pageHtml.append("<span ");
		if(pageNum > 1){//是否有前一页
			pageHtml.append("><a  href='javascript:' onclick='_pageclick("+(this.pageNum - 1)+")' aria-label='Previous'><i class='icon-backward'></i></a></span> ");
		}else{
			pageHtml.append("class='disabled' ");
			pageHtml.append("><a  href='javascript:' aria-label='Previous'><i class='icon-backward'></i></a></span> ");
		}
		pageHtml.append("<span class='lrline'> ");
		pageHtml.append("<input type='text' value='"+pageNum+"' name='currentPage' id='currentPage' class='page-input'>共 <em id='totalPage'>"+pages+"</em> 页");
		pageHtml.append(" </span>");
		
		pageHtml.append("<span ");
		if(pageNum < pages){//是否有下一页
			pageHtml.append("><a href='javascript:' onclick='_pageclick("+(this.pageNum + 1)+")' aria-label='Next'><i class='icon-forward'></i></a></span> ");
		}else{
			pageHtml.append("class='disabled' ");
			pageHtml.append("><a href='javascript:' aria-label='Next'><i class='icon-forward'></i></a></span> ");
		}
		
		pageHtml.append("<span ");
		if(pageNum == pages){//是否为最后一页
			pageHtml.append("class='disabled' ");
			pageHtml.append("><a href='javascript:' aria-label='LastPage'><i class='icon-step-forward'></i></a></span> ");
		}else{
			pageHtml.append("><a href='javascript:' onclick='_pageclick("+(this.pages)+")' aria-label='LastPage'><i class='icon-step-forward'></i></a></span> ");
		}
		pageHtml.append("</div>");
		return pageHtml.toString();
	}
	/**
	 * 
	 * @Title: getPageHtml
	 * @Description: 特殊翻页
	 * @return
	 * @return: String
	 */
	public String getPageHtml1(){
		StringBuffer pageHtml = new StringBuffer("<ul class='pager nomar-tb' >");
		if(pageNum > 1){//是否有前一页
			pageHtml.append("<li class='previous'><a href='javascript:' onclick='_pageclick1("+(this.pageNum - 1)+")'  ><span aria-hidden='true'>&larr;</span> 上一页</a></li>");
		}else{
			pageHtml.append("<li class='previous disabled'><a href='javascript:'><span aria-hidden='true'>&larr;</span> 上一页</a></li>");
		}
		
		if(pageNum < pages){//是否有下一页
			pageHtml.append("<li class='next'><a href='javascript:' onclick='_pageclick1("+(this.pageNum + 1)+")' >下一页 <span aria-hidden='tru'>&rarr;</span></a></li>");
		}else{
			pageHtml.append("<li class='next disabled'><a href='javascript:'>下一页 <span aria-hidden='tru'>&rarr;</span></a></li>");
		}
		pageHtml.append("</ul>");
		return pageHtml.toString();
	}
	
}
