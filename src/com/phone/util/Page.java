package com.phone.util;

import com.phone.meta.Phone;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yunshang_734 E-mail:yunshang_734@163.com
 * @version CreateTime：2013-4-23 上午01:33:55 Class Description
 */
public final class Page {
	private int currentPage; // 当前页数
	private int totalPages; // 总页数
	private int pageRows; // 每页显示记录数
	private int totalRows; // 总数据数
	private int pageStartRow; // 每页的起始数
	private int pageEndRow; // 每页的终止数
	private List<Phone> list;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageRows() {
		return pageRows;
	}

	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public void setPageStartRow(int pageStartRow) {
		this.pageStartRow = pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public void setPageEndRow(int pageEndRow) {
		this.pageEndRow = pageEndRow;
	}

	/**
	 * 初始化对象
	 * 
	 * @param tempList
	 *            结果集
	 */
	public Page(List<Phone> tempList, int pageRows) {
		this.list = tempList;
		this.pageRows = pageRows;
		this.totalRows = tempList.size();
		this.currentPage = 1;
		if ((totalRows % pageRows) == 0) {
			totalPages = totalRows / pageRows;
			if (this.totalRows == 0) {
				this.pageRows = 0;
			}
		} else {
			totalPages = totalRows / pageRows + 1;
		}
		if (currentPage == totalPages) {
		} else {
		}
		this.pageStartRow = 0;
		if (totalRows < pageRows) {
			this.pageEndRow = totalRows;
		} else {
			this.pageEndRow = pageRows;
		}
	}

	/**
	 * 
	 * 获取当前页的对象集合
	 * 
	 * @return List 对象集合
	 */
	public List<Phone> getCurrentPageList() {
		if (currentPage * pageRows < totalRows) {
			pageEndRow = currentPage * pageRows;
			pageStartRow = pageEndRow - pageRows;
		} else {
			pageEndRow = totalRows;
			pageStartRow = pageRows * (totalPages - 1);
		}
		List<Phone> pageList = new ArrayList<Phone>();
		for (int i = pageStartRow; i < pageEndRow; i++) {
			pageList.add(list.get(i));
		}
		return pageList;
	}

	/**
	 * 获取上一页的对象集合
	 * 
	 * @return List 对象集合
	 */
	public List<Phone> getPreviousPage() {
		currentPage--;
		if (currentPage < 1) {
			currentPage = 1;
		}
		if (currentPage >= totalPages) {
		} else {
		}
		if ((currentPage - 1) > 0) {
		} else {
		}
		List<Phone> pageList = getCurrentPageList();
		return pageList;
	}

	/**
	 * 
	 * 获取下一页的对象集合
	 * 
	 * @return List 对象集合
	 */
	public List<Phone> getNextPage() {
		currentPage++;
		if ((currentPage - 1) > 0) {
		} else {
		}
		if (currentPage >= totalPages) {
		} else {
		}
		List<Phone> pageList = getCurrentPageList();
		return pageList;
	}

	/**
	 * 
	 * 获取第一页
	 * 
	 * @return List 对象集合
	 */
	public List<Phone> getFirstList() {
		List<Phone> tempList = new ArrayList<Phone>();
		currentPage = 1;
		int a = pageRows;
		if (pageRows > this.totalRows) {
			a = this.totalRows;
		} else {
		}
		for (int i = 0; i < a; i++) {
			tempList.add(list.get(i));
		}
		return tempList;
	}

	/**
	 * 
	 * 获取最后一页
	 * 
	 * @return List 最后一页对象集合
	 */
	public List<Phone> getLastList() {
		List<Phone> tempList = new ArrayList<Phone>();
		currentPage = totalPages;
		if (pageRows >= totalRows) {
		} else {
		}
		for (int i = pageRows * (totalPages - 1); i < this.totalRows; i++) {
			tempList.add(list.get(i));
		}
		return tempList;
	}

	/**
	 * 
	 * 获取指定页
	 * 
	 * @return List 获取指定页对象集合
	 */
	public List<Phone> AppointPageList(int specPages) {
		new ArrayList<Object>();
		this.currentPage = specPages;
		if (currentPage > this.totalPages) {
			this.currentPage = this.totalPages;
		}
		if (currentPage < 1) {
			this.currentPage = 1;
		} else {
		}
		if (currentPage > 1) {
		} else {
		}
		if (this.currentPage < this.totalPages) {
		} else {
		}
		List<Phone> pageList = this.getCurrentPageList();
		return pageList;
	}
}