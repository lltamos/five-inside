package com.alqsoft.entity.runbackrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * TODO 表废弃
 * 返现表
 */

@Entity
@Table(name = "runbackrecond")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class RunBackRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 日期
	private String Datetime;
	// 账户余额
	private String Account;
	// 数量
	private Integer Num;
	// 订单数量
	private String Ordernum;
	// 金额
	private Double Money;

	private String CateName;

	private Integer IndexNum;
	// 用户姓名
	private String UserName;

	public String getDatetime() {
		return Datetime;
	}

	public void setDatetime(String datetime) {
		Datetime = datetime;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public String getOrdernum() {
		return Ordernum;
	}

	public void setOrdernum(String ordernum) {
		Ordernum = ordernum;
	}

	public Double getMoney() {
		return Money;
	}

	public void setMoney(Double money) {
		Money = money;
	}

	public String getCateName() {
		return CateName;
	}

	public void setCateName(String cateName) {
		CateName = cateName;
	}

	public Integer getIndexNum() {
		return IndexNum;
	}

	public void setIndexNum(Integer indexNum) {
		IndexNum = indexNum;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

}
