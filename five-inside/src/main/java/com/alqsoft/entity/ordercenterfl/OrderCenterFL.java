package com.alqsoft.entity.ordercenterfl;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ordercenterflt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class OrderCenterFL extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 地区FL
	private Double AreaFL;
	// 城市FL
	private Double CityFL;
	// 省份FL
	private Double ProFL;
	// 0:正在用1没在用
	private Integer State;
	// 大区域
	private Double BigArea;

	public Double getAreaFL() {
		return AreaFL;
	}

	public void setAreaFL(Double areaFL) {
		AreaFL = areaFL;
	}

	public Double getCityFL() {
		return CityFL;
	}

	public void setCityFL(Double cityFL) {
		CityFL = cityFL;
	}

	public Double getProFL() {
		return ProFL;
	}

	public void setProFL(Double proFL) {
		ProFL = proFL;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public Double getBigArea() {
		return BigArea;
	}

	public void setBigArea(Double bigArea) {
		BigArea = bigArea;
	}

}
