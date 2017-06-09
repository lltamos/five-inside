package com.alqsoft.entity.cardmanagerecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CardManageRecondT 银行卡记录信息表
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "cardmanagerecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class CardManageRecond extends IdEntity {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String CardNum;//银行卡号
	private String IDCard;//省份证
	private String Name;//用户名
	private String BankName;//银行名称
	private String Mobile;//手机号
	private Long UserID;//用户编号
	private Integer State;//状态
	private String BankOpen;//开户行

	public String getCardNum() {
		return CardNum;
	}

	public void setCardNum(String cardNum) {
		CardNum = cardNum;
	}

	public String getIDCard() {
		return IDCard;
	}

	public void setIDCard(String iDCard) {
		IDCard = iDCard;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public String getBankOpen() {
		return BankOpen;
	}

	public void setBankOpen(String bankOpen) {
		BankOpen = bankOpen;
	}
	

}
