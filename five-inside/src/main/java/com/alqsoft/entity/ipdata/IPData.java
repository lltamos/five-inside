package com.alqsoft.entity.ipdata;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ipdatat")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class IPData extends IdEntity {
	private static final long serialVersionUID = 1L;
	// 类型 分登陆和提现
	private String TypeN;
	// 用户IP
	private String UserIp;
	// 用户ID
	private Long UserID;
	// 用户手机号
	private String UserMobile;
	// HTTP 请求的详细信息
	private String CData;
	// 阿里云IP接口返回的详细地址
	private String Address;

	public String getTypeN() {
		return TypeN;
	}

	public void setTypeN(String typeN) {
		TypeN = typeN;
	}

	public String getUserIp() {
		return UserIp;
	}

	public void setUserIp(String userIp) {
		UserIp = userIp;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getUserMobile() {
		return UserMobile;
	}

	public void setUserMobile(String userMobile) {
		UserMobile = userMobile;
	}

	public String getCData() {
		return CData;
	}

	public void setCData(String cData) {
		CData = cData;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

}
