package com.alqsoft.entity.ordercenterinfo;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ordercenterinfot")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderCenterInfo extends IdEntity {
	private static final long serialVersionUID = 1L;
	// 订单中心编号
	private String OrderCenterBh;
	// 订单中心名称
	private String OrderCenterName;
	// 订单中心电话
	private String OrderCenterMobile;
	// 订单中心地址
	private String OrderCenterAddress;

	public String getOrderCenterBh() {
		return OrderCenterBh;
	}

	public void setOrderCenterBh(String orderCenterBh) {
		OrderCenterBh = orderCenterBh;
	}

	public String getOrderCenterName() {
		return OrderCenterName;
	}

	public void setOrderCenterName(String orderCenterName) {
		OrderCenterName = orderCenterName;
	}

	public String getOrderCenterMobile() {
		return OrderCenterMobile;
	}

	public void setOrderCenterMobile(String orderCenterMobile) {
		OrderCenterMobile = orderCenterMobile;
	}

	public String getOrderCenterAddress() {
		return OrderCenterAddress;
	}

	public void setOrderCenterAddress(String orderCenterAddress) {
		OrderCenterAddress = orderCenterAddress;
	}

}
