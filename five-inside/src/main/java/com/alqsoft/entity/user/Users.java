package com.alqsoft.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usert")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Users extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	// private Long UserId;
	// 姓名
	private String Name;
	// 密码
	private String Pwd;
	// 电话(登录帐号)
	private String Mobile;
	// 身份证
	private String IDCard;
	// 银行ID
	private String BankId;
	private String BankOpen;
	// 银行名称
	private String BankName;
	// 账户余额
	private Double balance;
	// 商城余额
	private Double GoodBalance;
	// 订单中心账户余额
	private Double OrderCenterAccount;
	// 返现金额
	private Double FanxAccount;
	// 推荐人ID
	private Long BdtjrId;
	// 用户等级0普通，1推广师
	private Long Grade;
	private Double StockAccount;
	// 绑卡次数
	private Integer UpdateBankCardCount;

	// 签值
	private String sign;

	private Date unLockTime;

	/*
	 * public Long getUserId() { return UserId; }
	 * 
	 * public void setUserId(Long userId) { UserId = userId; }
	 */

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPwd() {
		return Pwd;
	}

	public void setPwd(String pwd) {
		Pwd = pwd;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getBankId() {
		return BankId;
	}

	public void setBankId(String bankId) {
		BankId = bankId;
	}

	public String getBankOpen() {
		return BankOpen;
	}

	public void setBankOpen(String bankOpen) {
		BankOpen = bankOpen;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getGoodBalance() {
		return GoodBalance;
	}

	public void setGoodBalance(Double goodBalance) {
		GoodBalance = goodBalance;
	}

	public Double getOrderCenterAccount() {
		return OrderCenterAccount;
	}

	public void setOrderCenterAccount(Double orderCenterAccount) {
		OrderCenterAccount = orderCenterAccount;
	}

	public Double getFanxAccount() {
		return FanxAccount;
	}

	public void setFanxAccount(Double fanxAccount) {
		FanxAccount = fanxAccount;
	}

	public Long getBdtjrId() {
		return BdtjrId;
	}

	public void setBdtjrId(Long bdtjrId) {
		BdtjrId = bdtjrId;
	}

	public Long getGrade() {
		return Grade;
	}

	public void setGrade(Long grade) {
		Grade = grade;
	}

	public Double getStockAccount() {
		return StockAccount;
	}

	public void setStockAccount(Double stockAccount) {
		StockAccount = stockAccount;
	}

	public Integer getUpdateBankCardCount() {
		return UpdateBankCardCount;
	}

	public void setUpdateBankCardCount(Integer updateBankCardCount) {
		UpdateBankCardCount = updateBankCardCount;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Date getUnLockTime() {
		return unLockTime;
	}

	public void setUnLockTime(Date unLockTime) {
		this.unLockTime = unLockTime;
	}

}
