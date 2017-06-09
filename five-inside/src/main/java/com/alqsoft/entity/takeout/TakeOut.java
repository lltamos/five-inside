package com.alqsoft.entity.takeout;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 提货表
 */
@Entity
@Table(name = "takeoutt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TakeOut extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String UserKeyId;// 用户ID
	private String Name;// 名称
	private String Bank;// 银行名称
	private String BankOpen;// 开户行
	private String BankNo;// 银行卡号
	private String Province;// 省份
	private String city;// 城市
	private String Num;// 提现单号
	private Double Money;// 金额
	private Double Sxf;// 手续费
	private Double RealTxMoney;// 实际提现金额
	private Integer State;// 0初始未审核1 已提现 2 撤销 3处理中4提现失败
	private String KQorderId;// 回执号
	private Date ChkTime;// 审核时间
	private Date DelTime;// 审核的时候撤销的时间
	private Integer AutoState;// 0 自动发起银联请求 不用审核 1 需要审核通过才发起请求

	private String sn;
	private String returnState;

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

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getKQorderId() {
		return KQorderId;
	}

	public void setKQorderId(String kQorderId) {
		KQorderId = kQorderId;
	}

	public Date getChkTime() {
		return ChkTime;
	}

	public void setChkTime(Date chkTime) {
		ChkTime = chkTime;
	}

	public Date getDelTime() {
		return DelTime;
	}

	public void setDelTime(Date delTime) {
		DelTime = delTime;
	}

	public Integer getAutoState() {
		return AutoState;
	}

	public void setAutoState(Integer autoState) {
		AutoState = autoState;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getReturnState() {
		return returnState;
	}

	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}

}
