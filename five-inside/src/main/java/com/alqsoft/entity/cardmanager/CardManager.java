package com.alqsoft.entity.cardmanager;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CardManageT 银行卡信息表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "cardmanaget")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class CardManager extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//姓名
	private String Name;
	//银行卡名称
	private String BankName;
	//开户行名称
	private String BankOpen;
	//省
	private String Province;
	//市
	private String City;
	//银行卡号	
	private String CardNum;
    //用户ID
	private Long UserID;
	//手机号
	private String Mobile;
	//身份证
	private String IdCard;

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

	public String getBankOpen() {
		return BankOpen;
	}

	public void setBankOpen(String bankOpen) {
		BankOpen = bankOpen;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getCardNum() {
		return CardNum;
	}

	public void setCardNum(String cardNum) {
		CardNum = cardNum;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getIdCard() {
		return IdCard;
	}

	public void setIdCard(String idCard) {
		IdCard = idCard;
	}

}
