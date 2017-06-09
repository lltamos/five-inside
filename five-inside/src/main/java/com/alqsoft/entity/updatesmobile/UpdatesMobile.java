package com.alqsoft.entity.updatesmobile;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 密保手机记录表
 */
@Entity
@Table(name = "updatesmobilet")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class UpdatesMobile extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 手机号
	private String Mobile;
	// 旧手机号
	private String OldMobile;
	// 用户id
	private Long Userid;

	private String userIp;

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getOldMobile() {
		return OldMobile;
	}

	public void setOldMobile(String oldMobile) {
		OldMobile = oldMobile;
	}

	public Long getUserid() {
		return Userid;
	}

	public void setUserid(Long userid) {
		Userid = userid;
	}

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

}
