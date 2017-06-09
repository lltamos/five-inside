package com.alqsoft.entity.ordercenterarea;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ordercenterareat")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderCenterArea extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 订单中心ID
	private Long OrderCenterID;
	// 区域编码
	private Long AreaCodeID;

	public Long getOrderCenterID() {
		return OrderCenterID;
	}

	public void setOrderCenterID(Long orderCenterID) {
		OrderCenterID = orderCenterID;
	}

	public Long getAreaCodeID() {
		return AreaCodeID;
	}

	public void setAreaCodeID(Long areaCodeID) {
		AreaCodeID = areaCodeID;
	}

}
