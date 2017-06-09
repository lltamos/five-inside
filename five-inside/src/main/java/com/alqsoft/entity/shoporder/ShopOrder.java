package com.alqsoft.entity.shoporder;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 店铺订单表
 */
@Entity
@Table(name = "shopordert")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ShopOrder extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 商品个数
	private Integer GoodTotal;
	// 总金额
	private Double TotalMoney;
	//物流类型
	private Integer LogisticsType;
	// 1:订单已支付2:订单已配送      3:订单已完成       4 :订单已撤销
	private Integer States;
	// 订单号
	private String OrderNum;
	//0
	private Double LogisiticsMoney;
	//0
	private Long LogisID;
	// 日志公司
	private String LogCompany;
	// 用户id
	private Long UserID;
	// 日志数量
	private String LogNum;
	// 备注
	private String BeiZu;
	// 取消备注
	private String RevokeBz;
	// 商店类型0新业态 1 本地电商
	private Integer ShopType;

	public Integer getGoodTotal() {
		return GoodTotal;
	}

	public void setGoodTotal(Integer goodTotal) {
		GoodTotal = goodTotal;
	}

	public Double getTotalMoney() {
		return TotalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		TotalMoney = totalMoney;
	}

	public Integer getLogisticsType() {
		return LogisticsType;
	}

	public void setLogisticsType(Integer logisticsType) {
		LogisticsType = logisticsType;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public String getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(String orderNum) {
		OrderNum = orderNum;
	}

	public Double getLogisiticsMoney() {
		return LogisiticsMoney;
	}

	public void setLogisiticsMoney(Double logisiticsMoney) {
		LogisiticsMoney = logisiticsMoney;
	}

	public Long getLogisID() {
		return LogisID;
	}

	public void setLogisID(Long logisID) {
		LogisID = logisID;
	}

	public String getLogCompany() {
		return LogCompany;
	}

	public void setLogCompany(String logCompany) {
		LogCompany = logCompany;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getLogNum() {
		return LogNum;
	}

	public void setLogNum(String logNum) {
		LogNum = logNum;
	}

	public String getBeiZu() {
		return BeiZu;
	}

	public void setBeiZu(String beiZu) {
		BeiZu = beiZu;
	}

	public String getRevokeBz() {
		return RevokeBz;
	}

	public void setRevokeBz(String revokeBz) {
		RevokeBz = revokeBz;
	}

	public Integer getShopType() {
		return ShopType;
	}

	public void setShopType(Integer shopType) {
		ShopType = shopType;
	}

}
