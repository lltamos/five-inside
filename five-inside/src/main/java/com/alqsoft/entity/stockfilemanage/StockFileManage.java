package com.alqsoft.entity.stockfilemanage;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表不存在
 */
@Entity
@Table(name = "stockfilemanaget")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class StockFileManage extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String NuberTitle;
	private String Url;
	private Integer States;

	public String getNuberTitle() {
		return NuberTitle;
	}

	public void setNuberTitle(String nuberTitle) {
		NuberTitle = nuberTitle;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}
