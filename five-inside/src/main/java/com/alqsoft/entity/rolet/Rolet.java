package com.alqsoft.entity.rolet;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 角色表
 */
@Entity
@Table(name = "rolet")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Rolet extends IdEntity implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 手机
	private String Mobile;
	// 验证手机号
	private String SafeMobile;
	// 登录密码
	private String Pwd;
	// 名字
	private String Name;
	// 备注说明
	private String Remark;
	// 是否禁用 0正常 1禁用
	private Integer IsProhibit;
	// 角色
	private String role;
	private String otpcode;

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getSafeMobile() {
		return SafeMobile;
	}

	public void setSafeMobile(String safeMobile) {
		SafeMobile = safeMobile;
	}

	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public Integer getIsProhibit() {
		return IsProhibit;
	}

	public void setIsProhibit(Integer isProhibit) {
		IsProhibit = isProhibit;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getOtpcode() {
		return otpcode;
	}

	public void setOtpcode(String otpcode) {
		this.otpcode = otpcode;
	}

}
