package com.alqsoft.entity.orderbyordercenter;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "orderbyordercentert")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderByOrderCenter extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 订单ID
	private Long OrderID;
	// 订单地区ID
	private Long AreaOrderID;
	// 订单城市ID
	private Long CityOrderID;
	// 订单省份ID
	private Long ProOrderID;
	// 订单大区域ID
	private Long BigAreaOrderID;
	// 订单支付ID
	private Long OrderApplyID;
	// 0:订单中心1:区域订单中心
	private Integer IsOC;

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

	public Long getAreaOrderID() {
		return AreaOrderID;
	}

	public void setAreaOrderID(Long areaOrderID) {
		AreaOrderID = areaOrderID;
	}

	public Long getCityOrderID() {
		return CityOrderID;
	}

	public void setCityOrderID(Long cityOrderID) {
		CityOrderID = cityOrderID;
	}

	public Long getProOrderID() {
		return ProOrderID;
	}

	public void setProOrderID(Long proOrderID) {
		ProOrderID = proOrderID;
	}

	public Long getBigAreaOrderID() {
		return BigAreaOrderID;
	}

	public void setBigAreaOrderID(Long bigAreaOrderID) {
		BigAreaOrderID = bigAreaOrderID;
	}

	public Long getOrderApplyID() {
		return OrderApplyID;
	}

	public void setOrderApplyID(Long orderApplyID) {
		OrderApplyID = orderApplyID;
	}

	public Integer getIsOC() {
		return IsOC;
	}

	public void setIsOC(Integer isOC) {
		IsOC = isOC;
	}

}
