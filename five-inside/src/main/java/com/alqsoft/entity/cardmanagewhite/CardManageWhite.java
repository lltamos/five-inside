package com.alqsoft.entity.cardmanagewhite;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 提现卡白名单
 * 
 * @author panlichao
 *
 */
@Entity
@Table(name = "cardmanagewhitet")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class CardManageWhite extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户账号
	private String mobile;

	// 提现银行卡号
	private String cardNum;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

}
