package com.alqsoft.entity.temstockdata;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 
 * 表不存在
 */
@Entity
@Table(name = "temstockdatat")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class TemStockData extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String Mobile;
	private Long ZrIng;
	private Long SgIng;
	private Long CyIng;
	private Integer TotalNum;

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public Long getZrIng() {
		return ZrIng;
	}

	public void setZrIng(Long zrIng) {
		ZrIng = zrIng;
	}

	public Long getSgIng() {
		return SgIng;
	}

	public void setSgIng(Long sgIng) {
		SgIng = sgIng;
	}

	public Long getCyIng() {
		return CyIng;
	}

	public void setCyIng(Long cyIng) {
		CyIng = cyIng;
	}

	public Integer getTotalNum() {
		return TotalNum;
	}

	public void setTotalNum(Integer totalNum) {
		TotalNum = totalNum;
	}

}
