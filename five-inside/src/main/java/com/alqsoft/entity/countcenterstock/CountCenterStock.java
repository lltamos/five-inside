package com.alqsoft.entity.countcenterstock;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 不存在 TODO
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "countcenterstockt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class CountCenterStock extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long OrderCenterID;
	private Long StockFileID;
	private Long CountStock;
	private Long CountUser;
	private String StockNumberTitle;
	private Long Zring;
	private Long Sging;
	private Long Cying;

	public Long getOrderCenterID() {
		return OrderCenterID;
	}

	public void setOrderCenterID(Long orderCenterID) {
		OrderCenterID = orderCenterID;
	}

	public Long getStockFileID() {
		return StockFileID;
	}

	public void setStockFileID(Long stockFileID) {
		StockFileID = stockFileID;
	}

	public Long getCountStock() {
		return CountStock;
	}

	public void setCountStock(Long countStock) {
		CountStock = countStock;
	}

	public Long getCountUser() {
		return CountUser;
	}

	public void setCountUser(Long countUser) {
		CountUser = countUser;
	}

	public String getStockNumberTitle() {
		return StockNumberTitle;
	}

	public void setStockNumberTitle(String stockNumberTitle) {
		StockNumberTitle = stockNumberTitle;
	}

	public Long getZring() {
		return Zring;
	}

	public void setZring(Long zring) {
		Zring = zring;
	}

	public Long getSging() {
		return Sging;
	}

	public void setSging(Long sging) {
		Sging = sging;
	}

	public Long getCying() {
		return Cying;
	}

	public void setCying(Long cying) {
		Cying = cying;
	}

}
