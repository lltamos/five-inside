package com.alqsoft.entity.frstatesrecond;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * FrStatesRecondT 分润状态表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "frstatesrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FRStatesRecond extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 开始时间
	private Date Stime;
	// 订单时间
	private Date OrderTime;
	// 结束时间
	private Date Etime;
	// 推荐费状态
	private Integer TjStates;
	// 管理费状态
	private Integer MgStates;
	// 首次反润状态
	private Integer FirstStates;

	// 推荐费
	private Double TjMoney;
	// 管理费
	private Double MgMoney;
	// 首次反润金额
	private Double FirstMoney;

	public Date getStime() {
		return Stime;
	}

	public void setStime(Date stime) {
		Stime = stime;
	}

	public Date getOrderTime() {
		return OrderTime;
	}

	public void setOrderTime(Date orderTime) {
		OrderTime = orderTime;
	}

	public Date getEtime() {
		return Etime;
	}

	public void setEtime(Date etime) {
		Etime = etime;
	}

	public Integer getTjStates() {
		return TjStates;
	}

	public void setTjStates(Integer tjStates) {
		TjStates = tjStates;
	}

	public Integer getMgStates() {
		return MgStates;
	}

	public void setMgStates(Integer mgStates) {
		MgStates = mgStates;
	}

	public Integer getFirstStates() {
		return FirstStates;
	}

	public void setFirstStates(Integer firstStates) {
		FirstStates = firstStates;
	}

	public Double getTjMoney() {
		return TjMoney;
	}

	public void setTjMoney(Double tjMoney) {
		TjMoney = tjMoney;
	}

	public Double getMgMoney() {
		return MgMoney;
	}

	public void setMgMoney(Double mgMoney) {
		MgMoney = mgMoney;
	}

	public Double getFirstMoney() {
		return FirstMoney;
	}

	public void setFirstMoney(Double firstMoney) {
		FirstMoney = firstMoney;
	}

}
