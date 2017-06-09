package com.alqsoft.entity.servicetask;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "servicetask")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ServiceTask extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer states;

	private String triggerName;

	private Date triggerStartTime;

	private Date triggerEndTime;

	private String triggerRule;

	public Integer getStates() {
		return states;
	}

	public void setStates(Integer states) {
		this.states = states;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}

	public Date getTriggerStartTime() {
		return triggerStartTime;
	}

	public void setTriggerStartTime(Date triggerStartTime) {
		this.triggerStartTime = triggerStartTime;
	}

	public Date getTriggerEndTime() {
		return triggerEndTime;
	}

	public void setTriggerEndTime(Date triggerEndTime) {
		this.triggerEndTime = triggerEndTime;
	}

	public String getTriggerRule() {
		return triggerRule;
	}

	public void setTriggerRule(String triggerRule) {
		this.triggerRule = triggerRule;
	}

}
