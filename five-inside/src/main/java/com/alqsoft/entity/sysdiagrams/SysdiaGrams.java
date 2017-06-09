package com.alqsoft.entity.sysdiagrams;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表不存在
 */
@Entity
@Table(name = "sysdiagrams")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SysdiaGrams extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Long principal_id;
	private Long diagram_id;
	private Integer version;
	private byte[] definition;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPrincipal_id() {
		return principal_id;
	}

	public void setPrincipal_id(Long principal_id) {
		this.principal_id = principal_id;
	}

	public Long getDiagram_id() {
		return diagram_id;
	}

	public void setDiagram_id(Long diagram_id) {
		this.diagram_id = diagram_id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public byte[] getDefinition() {
		return definition;
	}

	public void setDefinition(byte[] definition) {
		this.definition = definition;
	}

}
