package com.alqsoft.entity.txbussiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 提现记录表
 */
@Entity
@Table(name = "txbussinesst")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TXBuddiness extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 0关闭;1开启
	private Integer IsOpen;
	// 最大提现额度
	private Double TxEd;
	// 1天最多提现次数
	private Integer Ocs;
	// 三天最多提现次数
	private Integer Tcs;

	public Integer getIsOpen() {
		return IsOpen;
	}

	public void setIsOpen(Integer isOpen) {
		IsOpen = isOpen;
	}

	public Double getTxEd() {
		return TxEd;
	}

	public void setTxEd(Double txEd) {
		TxEd = txEd;
	}

	public Integer getOcs() {
		return Ocs;
	}

	public void setOcs(Integer ocs) {
		Ocs = ocs;
	}

	public Integer getTcs() {
		return Tcs;
	}

	public void setTcs(Integer tcs) {
		Tcs = tcs;
	}

}
