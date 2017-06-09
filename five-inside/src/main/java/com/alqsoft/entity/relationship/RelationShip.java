package com.alqsoft.entity.relationship;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/*
 * 表废弃
 */

@Entity
@Table(name = "relationshipt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class RelationShip extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private Long GoodID;
	private Long providerID;

	public Long getGoodID() {
		return GoodID;
	}

	public void setGoodID(Long goodID) {
		GoodID = goodID;
	}

	public Long getProviderID() {
		return providerID;
	}

	public void setProviderID(Long providerID) {
		this.providerID = providerID;
	}

}
