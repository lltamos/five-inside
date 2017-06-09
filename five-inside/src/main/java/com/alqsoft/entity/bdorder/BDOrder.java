package com.alqsoft.entity.bdorder;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * BD_OrderT  订单表
 * @author Administrator
 *
 */

@Entity
@Table(name = "bd_ordert")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class BDOrder extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//订单号
	private String OrderNo;
	//用户ID
	private Long UserID;
	//订单中心ID
	private Long OrderCenterID;
	//支付金额
	private Long PayMoney;
	//订单数量
	private Long Num;
	//支付类型0互转报单1订单中心报单
	private Integer PayType;
	//寄售类型 0:寄售0元 1:寄售1000元
	private Integer ProjactType;
	//订单状态 0:尚未返现1:返现中2:返现结束
	private Integer States;
	//0:已返 1:未返
	private Integer TjStates;
	//0:PC 1:安卓 2:Ios 3:wap 4:互转保单 
	private String OrderType;
	//推荐人等级
	private Integer TjrGread;
	//待分润金额
	private BigDecimal DfMoney;
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
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
	public Integer getTjStates() {
		return TjStates;
	}
	public void setTjStates(Integer tjStates) {
		TjStates = tjStates;
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
	public BigDecimal getDfMoney() {
		return DfMoney;
	}
	public void setDfMoney(BigDecimal dfMoney) {
		DfMoney = dfMoney;
	}
	public Long getNum() {
		return Num;
	}
	public void setNum(Long num) {
		Num = num;
	}
	
	
	
}
