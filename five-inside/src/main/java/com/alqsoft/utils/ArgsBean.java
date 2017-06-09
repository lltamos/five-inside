package com.alqsoft.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @Description: 分页，参数传递对象
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月10日 上午11:10:53 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@SuppressWarnings("rawtypes")
public class ArgsBean {

	private List data; // 总记录数
	private int totalRecords; // 总记录数
	private int totalPage; // 总页数
	private int rows = 10;// 抓取记录数
	private int page = 1;// 当前页
	private int offset;// 开始位置
	private Map filter = null;
	private String pageAction;
	private String searchStr;

	@SuppressWarnings("unchecked")
	public void InitializeFilter() {
		if (StringUtils.isNotBlank(searchStr)) {
			filter = getFilter();
			String[] split = searchStr.split("&@&");
			for (String string : split) {
				String[] split2 = string.split("=");
				if (split2.length == 2) {
					if (!StringUtils.equals("undefined", split2[1])) {
						filter.put(split2[0], split2[1].trim());
					}

				}
			}
		}
		this.offset = (this.page - 1) * this.rows;

	}

	public List getData() {
		return data;
	}

	public void setData(List data) {

		this.data = data;

	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {

		double centerTotalPage = Math.ceil(totalRecords / Integer.valueOf(getRows()).doubleValue());// 向上取整

		this.totalPage = (new Double(centerTotalPage)).intValue();

		this.totalRecords = totalRecords;
	}

	public Map getFilter() {
		if (filter == null) {
			this.filter = new HashMap<>();
		}
		return filter;
	}

	public void setFilter(Map filter) {
		this.filter = filter;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;

	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getPageAction() {
		return pageAction;
	}

	public void setPageAction(String pageAction) {
		this.pageAction = pageAction;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

}
