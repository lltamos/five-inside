package com.alqsoft.entity.manageorderfr;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 管理费记录表
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "manageorderfrt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ManagOrderFR extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 法人ID
	private Long UserId;
	// 周期
	private String Time;

	// 订单数量
	private Integer OrderNum;
	// 总金额
	private Double TotalMoney;
	// 管理费金额
	private Double FrMoney;
	// 订单中心编号
	private String BianH;
	// 订单确认
	private Integer IsSure;
	// 自营范润
	private Double ZyFrMoney;
	// 代营范润
	private Double DyFrMoney;

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public Integer getOrderNum() {
		return OrderNum;
	}

	public void setOrderNum(Integer orderNum) {
		OrderNum = orderNum;
	}

	public Double getTotalMoney() {
		return TotalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		TotalMoney = totalMoney;
	}

	public Double getFrMoney() {
		return FrMoney;
	}

	public void setFrMoney(Double frMoney) {
		FrMoney = frMoney;
	}

	public String getBianH() {
		return BianH;
	}

	public void setBianH(String bianH) {
		BianH = bianH;
	}

	public Integer getIsSure() {
		return IsSure;
	}

	public void setIsSure(Integer isSure) {
		IsSure = isSure;
	}

	public Double getZyFrMoney() {
		return ZyFrMoney;
	}

	public void setZyFrMoney(Double zyFrMoney) {
		ZyFrMoney = zyFrMoney;
	}

	public Double getDyFrMoney() {
		return DyFrMoney;
	}

	public void setDyFrMoney(Double dyFrMoney) {
		DyFrMoney = dyFrMoney;
	}

}
