package com.alqsoft.entity.bianht;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bianht")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Bianht extends IdEntity {
	
	
	private static final long serialVersionUID = 1L;
	
	
	//订单中心编号
	private String BianH;
	//订单中心状态:0初始，1已使用
	private Integer States;

	public String getBianH() {
		return BianH;
	}

	public void setBianH(String bianH) {
		BianH = bianH;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}
