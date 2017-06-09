package com.alqsoft.entity.ordercenterupeval;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ordercenterupeval")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderCenterUpeval extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 1 已审核 2 删除 0 申请中
	private Integer State;
	// 报单中心表id
	private Long OrderCenterID;

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public Long getOrderCenterID() {
		return OrderCenterID;
	}

	public void setOrderCenterID(Long orderCenterID) {
		OrderCenterID = orderCenterID;
	}

}
