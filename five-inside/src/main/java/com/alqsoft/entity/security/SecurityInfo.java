package com.alqsoft.entity.security;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 密保信息
 */

@Entity
@Table(name = "securityinfot")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SecurityInfo extends IdEntity implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 用户id
	private Long Userid;
	// 手机
	private String SMobile;

	public Long getUserid() {
		return Userid;
	}

	public void setUserid(Long userid) {
		Userid = userid;
	}

	public String getSMobile() {
		return SMobile;
	}

	public void setSMobile(String sMobile) {
		SMobile = sMobile;
	}

}
