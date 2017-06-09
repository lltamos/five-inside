package com.alqsoft.entity.pushrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表废弃
 */
@Entity
@Table(name = "pushrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class PushRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 推荐人手机
	private String TjrMoblie;
	// 手机
	private String Mobile;
	// 0:已安装1:未安装
	private Integer IsInstall;

	public String getTjrMoblie() {
		return TjrMoblie;
	}

	public void setTjrMoblie(String tjrMoblie) {
		TjrMoblie = tjrMoblie;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public Integer getIsInstall() {
		return IsInstall;
	}

	public void setIsInstall(Integer isInstall) {
		IsInstall = isInstall;
	}

}
