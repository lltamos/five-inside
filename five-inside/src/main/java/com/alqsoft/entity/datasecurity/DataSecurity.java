package com.alqsoft.entity.datasecurity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "datasecurityt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class DataSecurity extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;

	private String json;

	private String dSign;

	private String codeType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getdSign() {
		return dSign;
	}

	public void setdSign(String dSign) {
		this.dSign = dSign;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}
