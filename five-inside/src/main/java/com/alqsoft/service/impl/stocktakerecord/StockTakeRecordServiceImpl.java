package com.alqsoft.service.impl.stocktakerecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.stocktakerecord.StockTakeRecord;
import com.alqsoft.hibernatedao.stocktakerecord.StockTakeRecordDao;
import com.alqsoft.service.stocktakerecord.StockTakeRecordService;
/**
 * 
* @ClassName: StockTakeRecordServiceImpl 
* @Description: 盘存记录impl 
* @author 腾卉 
* @date 2017年5月18日 下午5:13:23 
*
 */
@Service
@Transactional(readOnly=true)
public class StockTakeRecordServiceImpl implements StockTakeRecordService {

	@Autowired
	private StockTakeRecordDao stockTakeRecordDao;
	
	@Override
	public boolean delete(Long arg0) {
		stockTakeRecordDao.delete(arg0);
		return true;
	}

	@Override
	public StockTakeRecord get(Long arg0) {
		return stockTakeRecordDao.findOne(arg0);
	}

	@Override
	@Transactional
	public StockTakeRecord saveAndModify(StockTakeRecord arg0) {
		return stockTakeRecordDao.save(arg0);
	}

}
