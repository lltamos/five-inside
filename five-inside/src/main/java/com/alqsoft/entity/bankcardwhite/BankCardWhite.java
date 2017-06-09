package com.alqsoft.entity.bankcardwhite;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *白名单表
 */
@Entity
@Table(name = "bankcardwhitet")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class BankCardWhite extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	private Integer UserId;//用户id
	private String CardNo;//银行卡号
	
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
	}
	public String getCardNo() {
		return CardNo;
	}
	public void setCardNo(String cardNo) {
		CardNo = cardNo;
	}

}
