package com.alqsoft.entity.actionlogrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "actionlogrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ActionLogRecond extends IdEntity {
	private static final long serialVersionUID = 1L;

	private Long userId;

	private Integer actionId;

	private Integer actionStates;

	private String actionDetail;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public Integer getActionStates() {
		return actionStates;
	}

	public void setActionStates(Integer actionStates) {
		this.actionStates = actionStates;
	}

	public String getActionDetail() {
		return actionDetail;
	}

	public void setActionDetail(String actionDetail) {
		this.actionDetail = actionDetail;
	}

}
