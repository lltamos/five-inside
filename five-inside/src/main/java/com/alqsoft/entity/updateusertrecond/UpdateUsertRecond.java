package com.alqsoft.entity.updateusertrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 账户修改触发表
 */
@Entity
@Table(name = "updateusertrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class UpdateUsertRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 原金额
	private Double OldMoney;
	// 新金额
	private Double NewMoney;
	// 1 返现账户 2 商城账户 3 订单中心账户
	private Long cateId;
	// 用户id
	private Long Userid;

	public Double getOldMoney() {
		return OldMoney;
	}

	public void setOldMoney(Double oldMoney) {
		OldMoney = oldMoney;
	}

	public Double getNewMoney() {
		return NewMoney;
	}

	public void setNewMoney(Double newMoney) {
		NewMoney = newMoney;
	}

	public Long getCateId() {
		return cateId;
	}

	public void setCateId(Long cateId) {
		this.cateId = cateId;
	}

	public Long getUserid() {
		return Userid;
	}

	public void setUserid(Long userid) {
		Userid = userid;
	}

}
