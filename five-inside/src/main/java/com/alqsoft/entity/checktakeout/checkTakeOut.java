package com.alqsoft.entity.checktakeout;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CateProjactT 金额变动类型记录表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "checktakeout")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class checkTakeOut extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//提现状态：0过时,1当前正在使用
	public Integer state;
	
	//当前提现是否可以提现：0关闭，1开启
	private Integer type;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
	
}
