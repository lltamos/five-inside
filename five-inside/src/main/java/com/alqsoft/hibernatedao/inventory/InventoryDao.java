package com.alqsoft.hibernatedao.inventory;


import javax.persistence.LockModeType;
import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.inventory.Inventory;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年5月9日 上午10:40:4 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface InventoryDao extends BaseDao<Inventory> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("FROM Inventory where goodsId=:goodid")
	public Inventory lock(@Param("goodid") Long goodid);
	@Query(value="select * from inventoryt where goods_id = ?",nativeQuery=true)
	public Inventory getInventoryByGoodsId(@Param("goodid") Long goodid);
	//数据加锁
	//修改盘余库存数量
	@Query(value="update inventoryt set more_num = ? where goods_id = ?",nativeQuery=true)
	@Modifying
	public int updateMoreNumByGoodsId(@Param("more_num") Integer more_num,@Param("goods_id") Long goodid);
	//修改盘余库存数量和商品数量
	@Query(value="update inventoryt set num = ? , more_num = ? where goods_id = ?",nativeQuery=true)
	@Modifying
	public int updateInventoryByGoodsId(@Param("num") Integer num,@Param("more_num") Integer more_num,@Param("goods_id") Long goods_id);
	
}
