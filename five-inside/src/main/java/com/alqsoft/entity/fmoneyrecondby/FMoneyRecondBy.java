package com.alqsoft.entity.fmoneyrecondby;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO 废弃
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "fmoneyrecondbyt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FMoneyRecondBy extends IdEntity {
	private static final long serialVersionUID = 1L;

	private Long UserID;
	private Double MdMoney;
	private Integer Num;
	private Double ZMoney;
	private Long OrderID;

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public Double getMdMoney() {
		return MdMoney;
	}

	public void setMdMoney(Double mdMoney) {
		MdMoney = mdMoney;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public Double getZMoney() {
		return ZMoney;
	}

	public void setZMoney(Double zMoney) {
		ZMoney = zMoney;
	}

	public Long getOrderID() {
		return OrderID;
	}

	public void setOrderID(Long orderID) {
		OrderID = orderID;
	}

}
