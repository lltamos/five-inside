package com.alqsoft.entity.inventory;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * InventoryT 库存表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "inventoryt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Inventory extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 商品ID
	private Long GoodsId;
	// 商品条码
	private String BarCode;
	// 商品数量
	private Integer Num;
	// 盘余库存数量
	private Integer MoreNum;

	public Long getGoodsId() {
		return GoodsId;
	}

	public void setGoodsId(Long goodsId) {
		GoodsId = goodsId;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public Integer getMoreNum() {
		return MoreNum;
	}

	public void setMoreNum(Integer moreNum) {
		MoreNum = moreNum;
	}

}
