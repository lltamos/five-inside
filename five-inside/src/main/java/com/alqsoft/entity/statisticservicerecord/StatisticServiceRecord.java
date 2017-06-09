package com.alqsoft.entity.statisticservicerecord;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "statisticservicerecordt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class StatisticServiceRecord extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double amFxTotalMoney;

	private Double fhTotalMoney;

	private Double alreadyTxTotalMoney;

	private Integer alreadyTxNum;

	private Double txingTotalMoney;

	private Integer txingNum;

	private Double hzBdTotalMoney;

	private Integer hzBdNum;

	private Double pmFxTotalMoney;

	private Double differenceMoney;

	private Double artificialTotalMoney;

	public Double getAmFxTotalMoney() {
		return amFxTotalMoney;
	}

	public void setAmFxTotalMoney(Double amFxTotalMoney) {
		this.amFxTotalMoney = amFxTotalMoney;
	}

	public Double getFhTotalMoney() {
		return fhTotalMoney;
	}

	public void setFhTotalMoney(Double fhTotalMoney) {
		this.fhTotalMoney = fhTotalMoney;
	}

	public Double getAlreadyTxTotalMoney() {
		return alreadyTxTotalMoney;
	}

	public void setAlreadyTxTotalMoney(Double alreadyTxTotalMoney) {
		this.alreadyTxTotalMoney = alreadyTxTotalMoney;
	}

	public Integer getAlreadyTxNum() {
		return alreadyTxNum;
	}

	public void setAlreadyTxNum(Integer alreadyTxNum) {
		this.alreadyTxNum = alreadyTxNum;
	}

	public Double getTxingTotalMoney() {
		return txingTotalMoney;
	}

	public void setTxingTotalMoney(Double txingTotalMoney) {
		this.txingTotalMoney = txingTotalMoney;
	}

	public Integer getTxingNum() {
		return txingNum;
	}

	public void setTxingNum(Integer txingNum) {
		this.txingNum = txingNum;
	}

	public Double getHzBdTotalMoney() {
		return hzBdTotalMoney;
	}

	public void setHzBdTotalMoney(Double hzBdTotalMoney) {
		this.hzBdTotalMoney = hzBdTotalMoney;
	}

	public Integer getHzBdNum() {
		return hzBdNum;
	}

	public void setHzBdNum(Integer hzBdNum) {
		this.hzBdNum = hzBdNum;
	}

	public Double getPmFxTotalMoney() {
		return pmFxTotalMoney;
	}

	public void setPmFxTotalMoney(Double pmFxTotalMoney) {
		this.pmFxTotalMoney = pmFxTotalMoney;
	}

	public Double getDifferenceMoney() {
		return differenceMoney;
	}

	public void setDifferenceMoney(Double differenceMoney) {
		this.differenceMoney = differenceMoney;
	}

	public Double getArtificialTotalMoney() {
		return artificialTotalMoney;
	}

	public void setArtificialTotalMoney(Double artificialTotalMoney) {
		this.artificialTotalMoney = artificialTotalMoney;
	}

}
