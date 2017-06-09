package com.alqsoft.entity.takeaddrest;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 取货地址表
 */
@Entity
@Table(name = "takeaddrest")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TakeAddRest extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 用户名
	private String UserName;
	// 手机
	private String Mobile;
	// 省份
	private Integer Provice;
	// 城市
	private Integer City;
	// 地区
	private Integer Area;
	// 地址
	private String Address;
	// 0:初始1：默认2：删除
	private Integer States;
	// 编号
	private String Code;
	// 用户id
	private Long UserID;

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public Integer getProvice() {
		return Provice;
	}

	public void setProvice(Integer provice) {
		Provice = provice;
	}

	public Integer getCity() {
		return City;
	}

	public void setCity(Integer city) {
		City = city;
	}

	public Integer getArea() {
		return Area;
	}

	public void setArea(Integer area) {
		Area = area;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

}
