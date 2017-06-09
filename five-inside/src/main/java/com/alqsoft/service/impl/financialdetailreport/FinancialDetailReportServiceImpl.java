package com.alqsoft.service.impl.financialdetailreport;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.financialdetailreport.IfinancialDetailReportMapper;
import com.alqsoft.entity.financialdetailreport.FinancialDetailReport;
import com.alqsoft.entity.financialsummaryreport.FinancialSummaryReport;
import com.alqsoft.entity.provider.Provider;
import com.alqsoft.hibernatedao.financialDetailreport.FinancialDetailReportDao;
import com.alqsoft.hibernatedao.financialsummaryreport.FinancialSummaryReportDao;
import com.alqsoft.hibernatedao.provider.ProviderDao;
import com.alqsoft.init.InitParams;
import com.alqsoft.service.financialdetailreport.FinancialDetailReportService;
import com.alqsoft.utils.TestEmail;

@Service
@Transactional
public class FinancialDetailReportServiceImpl implements FinancialDetailReportService{
	@Autowired
	private IfinancialDetailReportMapper detailReportMapper;
	@Autowired
	private FinancialDetailReportDao fdrd;
	@Autowired 
	private ProviderDao providerDao;
	@Autowired 
	private FinancialSummaryReportDao fsrd;
	@Autowired
	private InitParams initParams;
	@Override
	public List<Map<String, Object>> findDetailReportList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return detailReportMapper.findDetailReportList(params);
	}

	@Override
	public Integer getDetailReportTotal(Map<String, Object> map) {
		return detailReportMapper.getDetailReportTotal(map);
	}

	@Override
	public Result sendEmail(Long id,HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/");
		List<Map<String, Object>> datail = detailReportMapper.findDatailBySummaryId(id);
		if(null ==datail || datail.size()<=0){
			ResultUtils.returnError("已发送完毕!");
		}
		for(Map<String, Object> d : datail){
			Object p = d.get("providerId");
			Object s = d.get("summary_id");
			String email = d.get("email")+"";
			String fileName = d.get("detailRoute")+"".replace("/", "\\");
			if(""==email||""==fileName||null ==p || null==s){
				return ResultUtils.returnError("发送邮件有误!");
			}
			
			FinancialSummaryReport findOne = fsrd.findOne(Long.parseLong(s+""));
			Provider find = providerDao.findOne(Long.parseLong(p+""));
			
			if(null ==findOne || null==find){
				return ResultUtils.returnError("发送邮件有误!");
			}
			List<FinancialDetailReport> dReport = fdrd.findReportByProviderId(Long.parseLong(s+""), Long.parseLong(p+""), 0);
			if(null==dReport||dReport.size()<=0){
				return ResultUtils.returnError("发送邮件有误!");
			}
			
			TestEmail testEmail = new TestEmail(email,initParams);
			try {
				testEmail.sendEmail(path+fileName, "财务报表", find.getName()+findOne.getCycle()+"的财务报表已发送,注意查收");
			} catch (Exception e) {
				//改变状态
				for(FinancialDetailReport financial : dReport){
					financial.setEmailState(2);
				}
				Iterable<FinancialDetailReport> save = fdrd.save(dReport);
				Iterator<FinancialDetailReport> iterator = save.iterator();
				if(null==iterator||!iterator.hasNext()){
					return ResultUtils.returnError("发送邮件有误!");
				}
				continue;
			}
			for(FinancialDetailReport financial : dReport){
				financial.setEmailState(1);
			}
			Iterable<FinancialDetailReport> save = fdrd.save(dReport);
			Iterator<FinancialDetailReport> iterator = save.iterator();
			if(null==iterator||!iterator.hasNext()){
				return ResultUtils.returnError("发送邮件有误!");
			}
			
		}
		return ResultUtils.returnSuccess("发送邮件完毕!");
	}

	@Override
	public Result reSendEmail(Long id,Long summaryId,HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/");
		FinancialSummaryReport fo = fsrd.findOne(summaryId);
		Provider findOne = providerDao.findOne(id);
		if(null==fo||null==findOne){
			ResultUtils.returnError("发送邮件有误!");
		}
		String email = findOne.getEmail();
		List<FinancialDetailReport> dReport = fdrd.findReportByProviderId(summaryId, id, 0);
		String replace = dReport.get(0).getDetailRoute().replace("/", "\\");
		if(null==dReport||dReport.size()<=0){
			ResultUtils.returnError("发送邮件有误!");
		}
		//发送邮件;
		TestEmail testEmail = new TestEmail(email,initParams);
		try {
			testEmail.sendEmail(path+replace, "财务报表",findOne.getName()+fo.getCycle()+"的财务报表已发送!");
		} catch (Exception e) {
			//改变状态
			for(FinancialDetailReport financial : dReport){
				financial.setEmailState(2);
			}
			Iterable<FinancialDetailReport> save = fdrd.save(dReport);
			Iterator<FinancialDetailReport> iterator = save.iterator();
			if(null==iterator||!iterator.hasNext()){
				ResultUtils.returnError("发送邮件有误!");
			}
			return ResultUtils.returnError("发送邮件失败!");
		}
		//改变状态
		for(FinancialDetailReport financial : dReport){
			financial.setEmailState(1);
		}
		Iterable<FinancialDetailReport> save = fdrd.save(dReport);
		Iterator<FinancialDetailReport> iterator = save.iterator();
		if(null==iterator||!iterator.hasNext()){
			ResultUtils.returnError("发送邮件有误!");
		}
		return ResultUtils.returnError("发送邮件成功!");
	}

}
