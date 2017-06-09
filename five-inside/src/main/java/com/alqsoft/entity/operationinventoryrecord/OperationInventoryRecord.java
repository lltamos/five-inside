package com.alqsoft.entity.operationinventoryrecord;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 商品操作记录表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "operationinventoryrecordt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OperationInventoryRecord extends IdEntity {

	private static final long serialVersionUID = 1L;

	// 关联Inventory表，库存ID
	private Long InventoryId;

	// 条码
	private String BarCode;

	// 订单编号（仅销售操作有订单号）
	private String OrderNo;

	// 商品数量
	private Integer Num;

	// 操作类型（0:入库 1:扫码购买 2:扣除盘余）
	private Integer OperationType;

	// 操作者ID
	private Long UserId;

	// 当前库存量
	private Integer CurrentInventoryNum;

	// 购买类型（0：新业态； 1：本地电商）
	private Integer ShopType;
	
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

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public Integer getOperationType() {
		return OperationType;
	}

	public void setOperationType(Integer operationType) {
		OperationType = operationType;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public Integer getCurrentInventoryNum() {
		return CurrentInventoryNum;
	}

	public void setCurrentInventoryNum(Integer currentInventoryNum) {
		CurrentInventoryNum = currentInventoryNum;
	}

	public Integer getShopType() {
		return ShopType;
	}

	public void setShopType(Integer shopType) {
		ShopType = shopType;
	}

}
