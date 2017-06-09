package com.alqsoft.entity.caozrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "caozrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class CaozRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 执行sql
	private String SQLStr;
	// 参数备注
	private String CsBZ;
	// 类型中文说明
	private String CateName;
	// 0 正常 1 异常
	private Integer State;
	// 执行对象ID
	private Long CzUser;

	public String getSQLStr() {
		return SQLStr;
	}

	public void setSQLStr(String sQLStr) {
		SQLStr = sQLStr;
	}

	public String getCsBZ() {
		return CsBZ;
	}

	public void setCsBZ(String csBZ) {
		CsBZ = csBZ;
	}

	public String getCateName() {
		return CateName;
	}

	public void setCateName(String cateName) {
		CateName = cateName;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public Long getCzUser() {
		return CzUser;
	}

	public void setCzUser(Long czUser) {
		CzUser = czUser;
	}

}
