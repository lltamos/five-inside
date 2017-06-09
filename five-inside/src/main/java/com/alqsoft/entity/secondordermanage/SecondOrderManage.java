package com.alqsoft.entity.secondordermanage;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 二级订单中心管理费记录表
 */
@Entity
@Table(name = "secondordermanaget")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SecondOrderManage extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 父级id
	private Long Pid;
	// 子级id
	private Long Tid;
	// 总应该发给管理费
	private Double ZMoney;
	// 二级订单中心获得金额
	private Double YMoney;
	// 周期
	private String Time;

	public Long getPid() {
		return Pid;
	}

	public void setPid(Long pid) {
		Pid = pid;
	}

	public Long getTid() {
		return Tid;
	}

	public void setTid(Long tid) {
		Tid = tid;
	}

	public Double getZMoney() {
		return ZMoney;
	}

	public void setZMoney(Double zMoney) {
		ZMoney = zMoney;
	}

	public Double getYMoney() {
		return YMoney;
	}

	public void setYMoney(Double yMoney) {
		YMoney = yMoney;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

}
