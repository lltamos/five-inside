package com.alqsoft.entity.bdorderrecond;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * BD_OrderRecondT 订单中心表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "bd_orderrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class BDOrderRecond extends IdEntity implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 用户ID
	private Long UserID;
	// 0:初始 1:已审核2:已删除
	private Integer States;
	// 订单中心编号
	private String BianH;
	// 推荐人ID
	private Long TjUserID;
	// 0:普通报单中心1:县级报单中心2:市级报单中心3:省级报单中心4:大区报单中心
	private Integer Eval;
	// 区域ID
	private Long AreaID;
	// 城市ID
	private Long CityID;
	private Long ProID;
	private Long BigAreaID;
	private String Grade;
	private Long PID;

	private Integer isEntityShop;

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public String getBianH() {
		return BianH;
	}

	public void setBianH(String bianH) {
		BianH = bianH;
	}

	public Long getTjUserID() {
		return TjUserID;
	}

	public void setTjUserID(Long tjUserID) {
		TjUserID = tjUserID;
	}

	public Integer getEval() {
		return Eval;
	}

	public void setEval(Integer eval) {
		Eval = eval;
	}

	public Long getAreaID() {
		return AreaID;
	}

	public void setAreaID(Long areaID) {
		AreaID = areaID;
	}

	public Long getCityID() {
		return CityID;
	}

	public void setCityID(Long cityID) {
		CityID = cityID;
	}

	public Long getProID() {
		return ProID;
	}

	public void setProID(Long proID) {
		ProID = proID;
	}

	public Long getBigAreaID() {
		return BigAreaID;
	}

	public void setBigAreaID(Long bigAreaID) {
		BigAreaID = bigAreaID;
	}

	public String getGrade() {
		return Grade;
	}

	public void setGrade(String grade) {
		Grade = grade;
	}

	public Long getPID() {
		return PID;
	}

	public void setPID(Long pID) {
		PID = pID;
	}

	public Integer getIsEntityShop() {
		return isEntityShop;
	}

	public void setIsEntityShop(Integer isEntityShop) {
		this.isEntityShop = isEntityShop;
	}

}
