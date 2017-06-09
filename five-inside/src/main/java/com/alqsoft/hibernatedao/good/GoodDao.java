package com.alqsoft.hibernatedao.good;


import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.good.Good;

public interface GoodDao extends BaseDao<Good>{

	/**
	 * 根据条形码获取商品信息
	 * @param bc
	 * @return
	 */
	@Query( value="select * from goodt where bar_code = ?",nativeQuery=true)
	public Good getGoodByBarCode(@Param("BarCode") String BarCode);
	
	/**
	 * 根据id获取商品信息
	 * @param gid
	 * @return
	 */
	@Query("FROM Good where id=:id")
	public Good getGoodById(@Param("id") Long id);
}
