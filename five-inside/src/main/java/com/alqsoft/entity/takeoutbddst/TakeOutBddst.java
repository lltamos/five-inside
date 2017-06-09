package com.alqsoft.entity.takeoutbddst;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表不存在
 */
@Entity
@Table(name = "takeoutbddst")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TakeOutBddst extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String Orderno;

	public String getOrderno() {
		return Orderno;
	}

	public void setOrderno(String orderno) {
		Orderno = orderno;
	}

}
