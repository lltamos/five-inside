package com.alqsoft.service.impl.financialstatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.alqframework.result.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.fiancialstatement.FinancialStatementMapper;
import com.alqsoft.service.financialstatement.FinancialStatementService;
import com.alqsoft.service.impl.salesreturn.SalesReturnServiceImpl;
import com.alqsoft.utils.ArgsBean;
import com.alqsoft.utils.PoiExcelExport;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月17日 下午1:47:19 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
@Transactional
public class FinancialStatementServiceImpl implements FinancialStatementService {
	@Resource
	private FinancialStatementMapper financialStatementMapper;

	private static Logger logger = LoggerFactory.getLogger(FinancialStatementServiceImpl.class);

	@Override
	public ArgsBean findFinancialStatements(ArgsBean bean) {
		bean.InitializeFilter();

		int count = financialStatementMapper.findFinancialStatementsCount(bean);
		List<Map<String, Object>> statements = financialStatementMapper.findFinancialStatements(bean);
		bean.setData(statements);
		bean.setTotalRecords(count);
		return bean;

	}

	@Override
	public Object exportExcel(HttpServletRequest request, HttpServletResponse response, ArgsBean page) {
		page.setOffset(0);
		page.InitializeFilter();
		Object start = page.getFilter().get("start");
		Object end = page.getFilter().get("end");
		if (page.getFilter().values().size()==0) {			
			if (null == start && null == end) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c = Calendar.getInstance();
				Date time = c.getTime();
				end = sdf.format(time);
				page.getFilter().put("end", end);
				c.add(Calendar.DATE, -7);
				Date monday = c.getTime();
				String preMonday = sdf.format(monday);
				start = preMonday;
				page.getFilter().put("start", start);
				if (null == end || null == start) {
					return ResultUtils.returnError("请输入初始时间和结束时间!");
				}
			}
		} 
			
		Integer count = 1;
	
		page.setRows(financialStatementMapper.findFinancialStatementsCount(page));
		List<Map<String, Object>> storageApplys = financialStatementMapper.findFinancialStatements(page);
		for (Map<String, Object> temp : storageApplys) {
			Map<String, Object> map = new HashMap<>();
			map.put("end", end);
			map.put("gid", temp.get("gid"));
			temp.put("indexnum", count++);
			Integer InventoryNum = financialStatementMapper.findInventoryt(map);
			Integer RamianNum = financialStatementMapper.findOrdergoodstRemainNum(map);
			if (null == RamianNum || RamianNum <= 0) {
				RamianNum = 0;
			}
			if (null == InventoryNum || InventoryNum <= 0) {
				InventoryNum = 0;
			}
			temp.put("leftNum", InventoryNum + RamianNum);
			temp.put("xuhao", "");
			temp.put("remark", "");
		}

		String[] datas = { "序号", "供应商名称", "供应商ID", "产品名称", "产品ID", "销量", "单笔序号", "收款方银行账号", "银行类型", "真实姓名", "付款金额(元)",
				"未结数量", "账户属性", "账户类型", "开户地区（对公必填）", "开户城市（对公必填）", "支行名称", "联行号（对公打款必填）", "付款说明", "收款人手机号", "所属机构" };
		int[] clumnWidth = { 12, 20, 20, 20, 20, 20, 20, 20, 20, 20, 12, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20 };
		String[] header = { "indexnum", "name", "pid", "good_name", "gid", "salesNum", "xuhao", "account_num",
				"bank_name", "account_owner", "t", "leftNum", "account_attribute", "account_type", "account_area",
				"account_city", "branch_name", "bank_num", "remark", "mobile", "organization" };

		PoiExcelExport poi = new PoiExcelExport();

		return poi.export(request, response, datas, header, clumnWidth, storageApplys, "caiwubaobiao_");
	}

}
