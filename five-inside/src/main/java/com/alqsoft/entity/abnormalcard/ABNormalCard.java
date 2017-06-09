package com.alqsoft.entity.abnormalcard;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 黑卡表
 * @author Administrator
 *
 */
@Entity
@Table(name = "abnormalcardt")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ABNormalCard extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//卡号
	private String CardNo;
	//户名
	private String BankName;
	//状态
	private Integer States;
	
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}
	public String getBankName() {
		return BankName;
	}
	public void setBankName(String bankName) {
		BankName = bankName;
	}
	public Integer getStates() {
		return States;
	}
	public void setStates(Integer states) {
		States = states;
	}
}
