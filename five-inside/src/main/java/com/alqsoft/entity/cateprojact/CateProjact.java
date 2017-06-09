package com.alqsoft.entity.cateprojact;

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
@Table(name = "cateprojactt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class CateProjact extends IdEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//报单类型名称
	private String ProjactName;
	//报单类型创建时间
	private Integer States;

	public String getProjactName() {
		return ProjactName;
	}

	public void setProjactName(String projactName) {
		ProjactName = projactName;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}
