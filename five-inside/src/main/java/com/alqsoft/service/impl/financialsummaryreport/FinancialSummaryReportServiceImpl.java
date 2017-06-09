package com.alqsoft.service.impl.financialsummaryreport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.financialdetailreport.IfinancialDetailReportMapper;
import com.alqsoft.dao.financialsummaryreport.IFinancialSummaryReportMapper;
import com.alqsoft.entity.exceldata.DetailReport;
import com.alqsoft.entity.financialdetailreport.FinancialDetailReport;
import com.alqsoft.entity.financialsummaryreport.FinancialSummaryReport;
import com.alqsoft.hibernatedao.financialDetailreport.FinancialDetailReportDao;
import com.alqsoft.hibernatedao.financialsummaryreport.FinancialSummaryReportDao;

import com.alqsoft.service.financialsummaryreport.FinancialSummaryReportService;
import com.alqsoft.utils.PoiExcelExport;
import com.alqsoft.utils.XlsMain;

@Service
@Transactional
public class FinancialSummaryReportServiceImpl implements FinancialSummaryReportService {
	@Autowired
	private IFinancialSummaryReportMapper reportMapper;
	@Autowired
	private FinancialSummaryReportDao reportDao;
	@Autowired
	private FinancialDetailReportDao fdrd ;
	@Autowired
	private IfinancialDetailReportMapper  ifdrm;
	@Override
	public List<Map<String, Object>> findSummeryReportList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return reportMapper.findSummeryReportList(params);
	}

	@Override
	public Integer getSummeryReportTotal(Map<String, Object> map) {
		return reportMapper.getSummeryReportTotal(map);
	}

	@Override
	public FinancialSummaryReport updataFinancialSummaryReport(FinancialSummaryReport f) {

		return reportDao.save(f);
	}

	@Override
	public FinancialSummaryReport get(Long id) {
		return reportDao.findOne(id);
	}

	@Override
	@Transactional
	public Result delById(Long id) {
		FinancialSummaryReport findOne = reportDao.findOne(id);
		if(null == findOne){
			return ResultUtils.returnError("删除失败!");
		}
		findOne.setState(2);
		FinancialSummaryReport save = reportDao.save(findOne);
		if(null != save){
			return ResultUtils.returnSuccess("删除成功!");
		}
		return ResultUtils.returnError("删除失败!");
	}

	@Override
	public Iterator<FinancialDetailReport> initialize(Long id ,HttpServletRequest request, HttpServletResponse response ) {
		FinancialSummaryReport findOne = reportDao.findOne(id);
		if(null == findOne){
			return null;
		}
		String path = request.getSession().getServletContext().getRealPath("/");
		String exlurl =path+findOne.getDetailRoute().replace("/", "\\");
		//"E:\\coding\\five-inside\\src\\main\\webapp\\upload\\uploadExcel\\4.15-4.21明细 (1).xls"
		XlsMain xm = new XlsMain();
		try {
			List<DetailReport> list = xm.readXls(exlurl);
			List<FinancialDetailReport> detailList = new ArrayList<>();
			for(DetailReport dr : list){
				if(dr==null){
					continue;
				}
				if(null==dr.getGoodId()||""==dr.getGoodId()){
					continue;
				}
				if(null==dr.getProvideId()||""==dr.getProvideId()){
					continue;
				}
				FinancialDetailReport fdr = new FinancialDetailReport();
				fdr.setGoodID(Long.parseLong(dr.getGoodId()));
				fdr.setSummaryId(id);
				fdr.setProviderId(Long.parseLong(dr.getProvideId()+""));
				if(null==dr.getSaleNum()||""==dr.getSaleNum()){
					fdr.setGoodNum(0L);
				}else{
					fdr.setGoodNum(Long.parseLong(dr.getSaleNum()+""));
				}
				
				if(null==dr.getPayMoney()||""==dr.getPayMoney()){
					fdr.setPayMoney(0.0);
				}else{
					fdr.setPayMoney(Double.parseDouble(dr.getPayMoney()+""));
				}
				fdr.setState(0);
				fdr.setDetailRoute(null);
				fdr.setEmailState(0);
				if(null==dr.getIngNum()||""==dr.getIngNum()){
					fdr.setLeftNum(0);
				}else{
					fdr.setLeftNum(Integer.parseInt(dr.getIngNum()+""));
				}
				boolean add = detailList.add(fdr);
				if(!add){
					return null;
				}
			}
			Iterable<FinancialDetailReport> save = fdrd.save(detailList);
			Iterator<FinancialDetailReport> iterator = save.iterator();
			if(iterator.hasNext()){
				return iterator;
			}
			return null;
		} catch (IOException e) {
			return null;
		}
		
	}

	@Override
	public Result createExcel(Long id, HttpServletRequest request, HttpServletResponse response) {
		String[] datas = {"序号", "供应商名称", "产品名称", "销量", "付款金额(元)", "未结数量" };
		int[] clumnWidth = { 20, 35, 35, 35, 35,35};
		String[] header = {"indexnum","name", "good_name", "good_num", "BuyPrice", "left_num"};
		PoiExcelExport poi = new PoiExcelExport();
		
		
		FinancialSummaryReport findOne = reportDao.findOne(id);
		if(null == findOne){
			return ResultUtils.returnError("初始化失败!");
		}
		//上传到exl的路径
		String exlurl = findOne.getCycle();
		//存在数据库中路径
		String fromurl ="/Emailxls/";
		String path = request.getSession().getServletContext().getRealPath(fromurl+exlurl);
		Map<String, Object> params = new HashMap<>();
		params.put("summaryId", id);
		params.put("state", 0);
		List<Map<String, Object>> detailList = ifdrm.findDetailListForEmail(params);
		if(null == detailList || detailList.size()<=0){
			return ResultUtils.returnError("初始化失败!");
		}
		for(Map<String, Object> dl : detailList){
				if(null != dl && dl.size()>0){
					if(null == dl.get("detail_route")){
						Map<String, Object> map = new HashMap<>();
						map.put("providerId", dl.get("provider_id"));
						map.put("summaryId", dl.get("summary_id"));
						//把生成的exl
						List<Map<String, Object>> sList = ifdrm.findDataForEmail(map);
						String fileName = "/"+dl.get("provider_id")+".xls";
						File targetFile = new File(path);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						int i = 1;
						for(Map<String, Object> s : sList){
							s.put("indexnum", i++);
						}
						poi.create(request, response, datas, header, clumnWidth, sList, findOne.getCycle(), path+fileName);
						//把生成的exl路径拼接到数据库中;
						List<FinancialDetailReport> DReport = fdrd.findReportByProviderId(Long.parseLong(dl.get("summary_id")+""),Long.parseLong(dl.get("provider_id")+""), 0);
						if(null == DReport || DReport.size()<=0){
							return ResultUtils.returnError("初始化失败!");
						}
						for(FinancialDetailReport f : DReport){
							String dataurl  =fromurl +exlurl+"/"+dl.get("provider_id")+".xls";
							f.setDetailRoute(dataurl);
						}
						Iterable<FinancialDetailReport> save = fdrd.save(DReport);
						Iterator<FinancialDetailReport> iterator = save.iterator();
						if(null == iterator || !iterator.hasNext()){
							return ResultUtils.returnError("初始化失败!");
						}
						
						for(Map<String, Object> dList : detailList){
							String pid = dl.get("provider_id")+"";
							String pid2 = dList.get("provider_id")+"";
							if(pid.equals(pid2)){
								dList.replace("detail_route", fromurl +exlurl+"/"+dl.get("provider_id")+".xls");
							}
						}
					}else{
						continue;
					}
				}else{
					break;
				}
				
			}
		findOne.setState(1);
		FinancialSummaryReport s = this.updataFinancialSummaryReport(findOne);
		if(s==null){
			return ResultUtils.returnError("初始化失败!");
		}
		return ResultUtils.returnSuccess("初始化成功!");
	}

	@Override
	public Result exportExcel(Long id, HttpServletRequest request, HttpServletResponse response) {
		if(null == id){
			return ResultUtils.returnError("下载失败!");
		}
		FinancialSummaryReport findOne = reportDao.findOne(id);
		if(null == findOne){
			return ResultUtils.returnError("下载失败!");
		}
		List<Map<String, Object>> summaryList = ifdrm.findDataForSummaryExcal(id);
		if(null ==summaryList || summaryList.size()<=0){
			return ResultUtils.returnError("下载失败!");
		}
		String[] datas = {"序号", "公司名称", "账号", "开户名", "开户省份", "开户城市", "付款金额", "银行名称", "账号种类","账号类型","开户证件类型","开户证件号","交易币种","备注","商户订单号","商户流水号" };
		int[] clumnWidth = { 20,35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, };
		String[] header = {"indexnum", "name", "account_num", "account_owner", "Province", "City", "BuyPrice", "bank_name", "银行卡","account_attribute","IDType","IDCard","MoneyType","Remark","ProviderOrderNum","ProviderNum"};
		PoiExcelExport poi = new PoiExcelExport();
		int i = 1;
		for(Map<String, Object> s:summaryList){
			s.put("indexnum", i++);
		}
		return poi.export(request, response, datas,header, clumnWidth, summaryList, "财务汇总报表_"+findOne.getCycle()+"_");
	}
	
}
