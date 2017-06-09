package com.alqsoft.hibernatedao.ordergoods;

import java.util.List;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.ordergoods.OrderGoods;

public interface OrderGoodsDao extends BaseDao<OrderGoods> {
	//数据加锁
	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("FROM OrderGoods where orderNum =:order_num and goodId =:good_id")
	public List<OrderGoods> lock(@Param("order_num") String order_num,@Param("good_id") Long good_id);
	//修改商品购买记录剩余数量
	@Query(value="update ordergoodst set remain_num = ? where order_num = ? and good_id = ?",nativeQuery=true)
	@Modifying
	public int updateRemainNumByOrderNumAndGoodId(@Param("remain_num") Integer remain_num,@Param("order_num") String order_num,@Param("good_id") Long good_id);
}
