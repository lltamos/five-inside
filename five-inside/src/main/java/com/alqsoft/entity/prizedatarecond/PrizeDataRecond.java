package com.alqsoft.entity.prizedatarecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 中奖活动表 TODO 废弃
 * 
 * @author thinkpad
 *
 */

@Entity
@Table(name = "prizedatarecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class PrizeDataRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Mobile;
	private String OrderBh;
	private String OrderName;
	private Integer State;
	private Integer Stage;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getOrderBh() {
		return OrderBh;
	}

	public void setOrderBh(String orderBh) {
		OrderBh = orderBh;
	}

	public String getOrderName() {
		return OrderName;
	}

	public void setOrderName(String orderName) {
		OrderName = orderName;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public Integer getStage() {
		return Stage;
	}

	public void setStage(Integer stage) {
		Stage = stage;
	}

}
