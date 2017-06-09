package com.alqsoft.entity.untake;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 定时任务任务提现表
 */
@Entity
@Table(name = "untake")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class UnTake extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String UserKeyId;// 用户ID
	private String Name;// 名称
	private String Bank;// 银行名称
	private String BankOpen;// 开户行
	private String BankNo;// 银行卡号
	private String Num;// 提现单号
	private Double Money;// 金额
	private Double Sxf;// 手续费
	private Double RealTxMoney;// 实际提现金额
	private Integer State;// 0代提现，1请求过提现
	private Integer AutoState;// 0 自动发起银联请求 不用审核 1 需要审核通过才发起请求
	private Long takeOutId;//提现id
	private String Remark; //记录
	private String Mobile;
	
	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getUserKeyId() {
		return UserKeyId;
	}

	public void setUserKeyId(String userKeyId) {
		UserKeyId = userKeyId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBank() {
		return Bank;
	}

	public void setBank(String bank) {
		Bank = bank;
	}

	public String getBankOpen() {
		return BankOpen;
	}

	public void setBankOpen(String bankOpen) {
		BankOpen = bankOpen;
	}

	public String getBankNo() {
		return BankNo;
	}

	public void setBankNo(String bankNo) {
		BankNo = bankNo;
	}


	public String getNum() {
		return Num;
	}

	public void setNum(String num) {
		Num = num;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public Double getSxf() {
		return Sxf;
	}

	public void setSxf(Double sxf) {
		Sxf = sxf;
	}

	public Double getRealTxMoney() {
		return RealTxMoney;
	}

	public void setRealTxMoney(Double realTxMoney) {
		RealTxMoney = realTxMoney;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public Integer getAutoState() {
		return AutoState;
	}

	public void setAutoState(Integer autoState) {
		AutoState = autoState;
	}

	public Long getTakeOutId() {
		return takeOutId;
	}

	public void setTakeOutId(Long takeOutId) {
		this.takeOutId = takeOutId;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
