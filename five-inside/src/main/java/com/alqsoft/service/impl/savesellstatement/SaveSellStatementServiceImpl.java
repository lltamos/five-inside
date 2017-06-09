package com.alqsoft.service.impl.savesellstatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.savesellstatement.SaveSellStatementDao;
import com.alqsoft.entity.good.Good;
import com.alqsoft.service.savesellstatement.SaveSellStatementService;
import com.alqsoft.utils.PoiExcelExport;
/**
 * 
* @ClassName: SaveSellStatementServiceImpl 
* @Description: 存销报表impl
* @author 腾卉 
* @date 2017年5月11日 上午11:53:40 
*
 */
@Service
@Transactional(readOnly=true)
public class SaveSellStatementServiceImpl implements SaveSellStatementService {

	@Autowired
	private SaveSellStatementDao saveSellStatementDao;

	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public Good get(Long arg0) {
		return null;
	}

	@Override
	public Good saveAndModify(Good arg0) {
		return null;
	}

	@Override
	public List<Map<String, Object>> findAll(String begin_time, String end_time, String good_name,Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("begin_time", begin_time);
		map.put("end_time", end_time);
		map.put("good_name", good_name);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		return saveSellStatementDao.findAll(map);
	}

	@Override
	public int getCount(String begin_time, String end_time, String good_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin_time", begin_time);
		map.put("end_time", end_time);
		map.put("good_name", good_name);
		int count = saveSellStatementDao.getCount(map);
		if(count >= 0){
			return count;
		}
		return 0;
	}

	@Override
	public Result deriveCxStatement(HttpServletRequest request, HttpServletResponse response,Map<String, Object> param) {
		String[] header ={"序号","商品名称","规格","总金额","销量","入库量","盘差数","盘余数","退货数"};
		String[] datas = { "indexnum", "good_name", "key_word", "buyPrice", "xl", "rk", "pc", "py","th" };
		Result result = new Result();
		PoiExcelExport poiec=new PoiExcelExport();
		List<Map<String, Object>> maos=saveSellStatementDao.deriveCxStatement(param);
		int i = 1;
		for(Map<String, Object> s:maos){
			s.put("indexnum", i++);
		}
		result=poiec.export(request, response, header, datas, null, maos, "存销报表");
		return result;
	}

	@Override
	public Result deriveKcStatement(HttpServletRequest request, HttpServletResponse response) {
		String[] header ={"序号","商品名称","商品条形码","商品规格","库存数量","只销不结数量","厂家名称"};
		String[] datas = { "indexnum", "good_name", "bar_code", "key_word", "num", "more_num", "name"};
		Result result = new Result();
		PoiExcelExport poiec=new PoiExcelExport();
		List<Map<String, Object>> maos=saveSellStatementDao.deriveKcStatement();
		int i = 1;
		for(Map<String, Object> s:maos){
			s.put("indexnum", i++);
		}
		result=poiec.export(request, response, header, datas, null, maos, "库存报表");
		return result;
	}
	
	
}
