package com.alqsoft.service.impl.ordergoods;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.ordergoods.OrderGoods;
import com.alqsoft.hibernatedao.ordergoods.OrderGoodsDao;
import com.alqsoft.service.ordergoods.OrderGoodsService;
@Service
@Transactional(readOnly=true)
public class OrderGoodsServiceImpl implements OrderGoodsService {

	@Autowired
	private OrderGoodsDao orderGoodsDao;
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderGoods get(Long arg0) {
		return orderGoodsDao.findOne(arg0);
	}

	@Override
	public OrderGoods saveAndModify(OrderGoods arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public int updateRemainNumByOrderNumAndGoodId(Integer remain_num, String order_num,Long good_id) {
		int updateRemainNumByOrderNum = orderGoodsDao.updateRemainNumByOrderNumAndGoodId(remain_num, order_num,good_id);
		if( updateRemainNumByOrderNum > 0 ){
			return updateRemainNumByOrderNum;
		}
		return 0;
	}

	@Override
	public List<OrderGoods> lock(String order_num, Long good_id) {
		return orderGoodsDao.lock(order_num, good_id);
	}

}
