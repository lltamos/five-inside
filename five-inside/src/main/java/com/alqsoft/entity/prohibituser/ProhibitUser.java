package com.alqsoft.entity.prohibituser;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 黑账户表
 * 
 * @author thinkpad
 *
 */

@Entity
@Table(name = "prohibit_usert")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ProhibitUser extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 用户id
	private Long Userid;
	// 手机号
	private String Mobile;
	// 备注说明
	private String Remark;

	public Long getUserid() {
		return Userid;
	}

	public void setUserid(Long userid) {
		Userid = userid;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
