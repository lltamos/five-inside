package com.alqsoft.entity.exceldata;

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
@Table(name = "exceldatat")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ExcelData extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String SrcInfo;
	private Double Tranmoney;

	public String getSrcInfo() {
		return SrcInfo;
	}

	public void setSrcInfo(String srcInfo) {
		SrcInfo = srcInfo;
	}

	public Double getTranmoney() {
		return Tranmoney;
	}

	public void setTranmoney(Double tranmoney) {
		Tranmoney = tranmoney;
	}

}
