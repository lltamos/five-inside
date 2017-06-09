package com.alqsoft.entity.soldierimg;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表废弃
 */
@Entity
@Table(name = "soldierimgt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SoldierImg extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Long SoldierId;
	private String Src;
	private Integer Type;

	public Long getSoldierId() {
		return SoldierId;
	}

	public void setSoldierId(Long soldierId) {
		SoldierId = soldierId;
	}

	public String getSrc() {
		return Src;
	}

	public void setSrc(String src) {
		Src = src;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

}
