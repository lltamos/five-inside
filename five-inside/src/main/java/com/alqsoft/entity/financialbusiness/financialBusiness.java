package com.alqsoft.entity.financialbusiness;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "financialbusinesst")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class financialBusiness extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 转账卡号
	private String TranNumber;
	// 账户余额
	private String Account;
	// 转账金额
	private Double TranMoney;
	// 账户
	private String AcctNo;
	// 卡号
	private String CardNo;
	// 信用卡
	private String CreditCard;
	/**
	 * 1:银行卡余额 2:转账汇款 3:信用卡还款 4:账户充值 5:订单收款 6:提现
	 */
	private Integer Type;
	// 0:默认状态 1:已支付状态
	private Integer States;
	// 第三方订单号
	private String ThirdOrderNum;

	public String getTranNumber() {
		return TranNumber;
	}

	public void setTranNumber(String tranNumber) {
		TranNumber = tranNumber;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public Double getTranMoney() {
		return TranMoney;
	}

	public void setTranMoney(Double tranMoney) {
		TranMoney = tranMoney;
	}

	public String getAcctNo() {
		return AcctNo;
	}

	public void setAcctNo(String acctNo) {
		AcctNo = acctNo;
	}

	public String getCardNo() {
		return CardNo;
	}

	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

	public String getCreditCard() {
		return CreditCard;
	}

	public void setCreditCard(String creditCard) {
		CreditCard = creditCard;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public String getThirdOrderNum() {
		return ThirdOrderNum;
	}

	public void setThirdOrderNum(String thirdOrderNum) {
		ThirdOrderNum = thirdOrderNum;
	}

}
