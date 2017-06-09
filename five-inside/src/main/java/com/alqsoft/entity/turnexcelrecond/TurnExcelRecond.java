package com.alqsoft.entity.turnexcelrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * TODO  表废弃
 */
@Entity
@Table(name = "turnexcelrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TurnExcelRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Double JyMoney;
	private String ExcelSrc;
	private Integer States;
	private String Times;

	public Double getJyMoney() {
		return JyMoney;
	}

	public void setJyMoney(Double jyMoney) {
		JyMoney = jyMoney;
	}

	public String getExcelSrc() {
		return ExcelSrc;
	}

	public void setExcelSrc(String excelSrc) {
		ExcelSrc = excelSrc;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public String getTimes() {
		return Times;
	}

	public void setTimes(String times) {
		Times = times;
	}

}
