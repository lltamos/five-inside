package com.alqsoft.entity.stocktakerecord;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * TODO 盘存记录表
 */
@Entity
@Table(name = "stocktakerecordt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class StockTakeRecord extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long InventoryId;
	private String BarCode;
	private Integer Num;
	private Integer RemainNum;
	private String Remark;

	public Long getInventoryId() {
		return InventoryId;
	}

	public void setInventoryId(Long inventoryId) {
		InventoryId = inventoryId;
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

	public Integer getRemainNum() {
		return RemainNum;
	}

	public void setRemainNum(Integer remainNum) {
		RemainNum = remainNum;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
