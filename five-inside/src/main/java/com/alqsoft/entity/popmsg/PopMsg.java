package com.alqsoft.entity.popmsg;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO 表废弃
 * 
 * @author thinkpad
 *
 */

@Entity
@Table(name = "popmsgt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class PopMsg extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String Title;
	private String Context;
	private Integer States;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getContext() {
		return Context;
	}

	public void setContext(String context) {
		Context = context;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}
