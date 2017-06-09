package com.alqsoft.entity.takeoutdata;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表不存在
 */
@Entity
@Table(name = "takeoutdatat")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TakeOutData extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 订单号
	private String OrderNo;
	// 名字
	private String Name;

	public String getOrderNo() {
		return OrderNo;
	}

	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
