package com.alqsoft.entity.financekeyrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "financekeyrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FinanceKeyRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 公钥
	private String PublicKey;
	// 私钥
	private String PrivateKey;
	// 账户余额
	private String UserAccount;

	public String getPublicKey() {
		return PublicKey;
	}

	public void setPublicKey(String publicKey) {
		PublicKey = publicKey;
	}

	public String getPrivateKey() {
		return PrivateKey;
	}

	public void setPrivateKey(String privateKey) {
		PrivateKey = privateKey;
	}

	public String getUserAccount() {
		return UserAccount;
	}

	public void setUserAccount(String userAccount) {
		UserAccount = userAccount;
	}

}
