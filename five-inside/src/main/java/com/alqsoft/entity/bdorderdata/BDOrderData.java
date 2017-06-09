package com.alqsoft.entity.bdorderdata;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "bd_orderdatat")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
/**
 * 订单申请表
 * 
 * @author Administrator
 *
 */
public class BDOrderData extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 订单号
	private String OrderNO;
	// 用户ID
	private Long UserID;
	// 订单中心ID
	private Long OrderCenterID;
	// 支付金额
	private Long PayMoney;
	// 订单数量
	private Long Num;
	// 0：个人支付1：订单中心支付
	private Integer PayType;
	//
	private Integer ProjactType;
	// 0:未审核1:已审核 2撤销
	private Integer States;
	// 报单推荐人
	private String BdTjr;
	// 0:PC 1:IOS 2:安卓 3:wap 4:互转保单
	private String OrderType;
	// 0:普通 1:高级
	private Integer TjrGread;

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Long getOrderCenterID() {
		return OrderCenterID;
	}

	public void setOrderCenterID(Long orderCenterID) {
		OrderCenterID = orderCenterID;
	}

	public Long getPayMoney() {
		return PayMoney;
	}

	public void setPayMoney(Long payMoney) {
		PayMoney = payMoney;
	}

	public Long getNum() {
		return Num;
	}

	public void setNum(Long num) {
		Num = num;
	}

	public Integer getPayType() {
		return PayType;
	}

	public void setPayType(Integer payType) {
		PayType = payType;
	}

	public Integer getProjactType() {
		return ProjactType;
	}

	public void setProjactType(Integer projactType) {
		ProjactType = projactType;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public String getBdTjr() {
		return BdTjr;
	}

	public void setBdTjr(String bdTjr) {
		BdTjr = bdTjr;
	}

	public String getOrderType() {
		return OrderType;
	}

	public void setOrderType(String orderType) {
		OrderType = orderType;
	}

	public Integer getTjrGread() {
		return TjrGread;
	}

	public void setTjrGread(Integer tjrGread) {
		TjrGread = tjrGread;
	}

}
