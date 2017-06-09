package com.alqsoft.entity.logrecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * LogRecondT 日志记录表
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "logrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class LogRecond extends IdEntity {

	private static final long serialVersionUID = 1L;

	// 日志等级
	private Long Eval;
	// 类型名称
	private String CateName;
	// 信息
	private String Dinfo;

	public Long getEval() {
		return Eval;
	}

	public void setEval(Long eval) {
		Eval = eval;
	}

	public String getCateName() {
		return CateName;
	}

	public void setCateName(String cateName) {
		CateName = cateName;
	}

	public String getDinfo() {
		return Dinfo;
	}

	public void setDinfo(String dinfo) {
		Dinfo = dinfo;
	}

}
