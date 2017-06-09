package com.alqsoft.entity.balancedifferenceweek;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 周平衡差
 * 
 * @author panlichao
 *
 */
@Entity
@Table(name = "balancedifferenceweek")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class BalanceDifferenceWeek extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 用户ID
	private Long userId;
	// 周一
	private Double monday;
	// 周二
	private Double tuesday;
	// 周三
	private Double wednesday;
	// 周四
	private Double thursday;
	// 周五
	private Double friday;
	// 周六
	private Double saturday;
	// 周日
	private Double sunday;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getMonday() {
		return monday;
	}

	public void setMonday(Double monday) {
		this.monday = monday;
	}

	public Double getTuesday() {
		return tuesday;
	}

	public void setTuesday(Double tuesday) {
		this.tuesday = tuesday;
	}

	public Double getWednesday() {
		return wednesday;
	}

	public void setWednesday(Double wednesday) {
		this.wednesday = wednesday;
	}

	public Double getThursday() {
		return thursday;
	}

	public void setThursday(Double thursday) {
		this.thursday = thursday;
	}

	public Double getFriday() {
		return friday;
	}

	public void setFriday(Double friday) {
		this.friday = friday;
	}

	public Double getSaturday() {
		return saturday;
	}

	public void setSaturday(Double saturday) {
		this.saturday = saturday;
	}

	public Double getSunday() {
		return sunday;
	}

	public void setSunday(Double sunday) {
		this.sunday = sunday;
	}

}
