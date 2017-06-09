package com.alqsoft.entity.ordercenterapplyrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ordercenterapplyrecond")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderCenterApplyRecond extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 区域ID
	private Long AreaID;
	// 城市ID
	private Long CityID;
	// 省份ID
	private Long ProID;
	// 大区域ID
	private Long BigAreaID;
	// 0:申请1:已审核2:审核不通过
	private Integer State;
	// 订单中心ID
	private Long OrderCenterID;
	// 1:县级2:市级3:省级4:大区
	private Long ApplyEval;

	public Long getAreaID() {
		return AreaID;
	}

	public void setAreaID(Long areaID) {
		AreaID = areaID;
	}

	public Long getCityID() {
		return CityID;
	}

	public void setCityID(Long cityID) {
		CityID = cityID;
	}

	public Long getProID() {
		return ProID;
	}

	public void setProID(Long proID) {
		ProID = proID;
	}

	public Long getBigAreaID() {
		return BigAreaID;
	}

	public void setBigAreaID(Long bigAreaID) {
		BigAreaID = bigAreaID;
	}

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

	public Long getApplyEval() {
		return ApplyEval;
	}

	public void setApplyEval(Long applyEval) {
		ApplyEval = applyEval;
	}

}
