package com.alqsoft.entity.prizerecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 中奖纪录表 TODO 废弃
 * 
 * @author thinkpad
 *
 */

@Entity
@Table(name = "prizerecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class PrizeRecond extends IdEntity {
	private String Name;
	private String Mobile;
	private String OrderBH;
	private String OrderName;
	private Integer Stage;
	private Integer IsGet;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getOrderBH() {
		return OrderBH;
	}

	public void setOrderBH(String orderBH) {
		OrderBH = orderBH;
	}

	public String getOrderName() {
		return OrderName;
	}

	public void setOrderName(String orderName) {
		OrderName = orderName;
	}

	public Integer getStage() {
		return Stage;
	}

	public void setStage(Integer stage) {
		Stage = stage;
	}

	public Integer getIsGet() {
		return IsGet;
	}

	public void setIsGet(Integer isGet) {
		IsGet = isGet;
	}

}
