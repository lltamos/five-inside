package com.alqsoft.entity.order;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 废弃 TODO
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "ordert")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Order extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String OrderNo;
	private String OrderType;
	private String OrderComKeyId;
	private String OrderUserKeyId;
	private String PayType;

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public String getOrderComKeyId() {
		return OrderComKeyId;
	}

	public void setOrderComKeyId(String orderComKeyId) {
		OrderComKeyId = orderComKeyId;
	}

	public String getOrderUserKeyId() {
		return OrderUserKeyId;
	}

	public void setOrderUserKeyId(String orderUserKeyId) {
		OrderUserKeyId = orderUserKeyId;
	}

	public String getPayType() {
		return PayType;
	}

	public void setPayType(String payType) {
		PayType = payType;
	}

}
