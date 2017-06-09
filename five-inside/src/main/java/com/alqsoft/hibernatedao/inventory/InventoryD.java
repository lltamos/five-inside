package com.alqsoft.hibernatedao.inventory;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.alqsoft.entity.inventory.Inventory;

public interface InventoryD extends BaseDao<Inventory>{
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("FROM Inventory where goodsId=:goodid")
	public Inventory inventoryGoodsId(@Param("goodid") Long goodid);
}
