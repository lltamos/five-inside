package com.alqsoft.entity.zzdate;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "zzdate")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ZZDate extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注册姓名
	private String zcName;

	private Double mobile;

	private String num;

	// 收款姓名
	private String scName;

	public String getZcName() {
		return zcName;
	}

	public void setZcName(String zcName) {
		this.zcName = zcName;
	}

	public Double getMobile() {
		return mobile;
	}

	public void setMobile(Double mobile) {
		this.mobile = mobile;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

}
