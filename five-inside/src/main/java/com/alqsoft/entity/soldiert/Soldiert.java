package com.alqsoft.entity.soldiert;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 表废弃
 */

@Entity
@Table(name = "soldiert")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Soldiert extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String Name;
	private String Mobile;
	private String IdCard;
	private String Szbd;
	private String Jx;
	private String Sjld;
	private String Cbs;
	private Integer Age;
	private String Hyqk;
	private Long ProID;
	private Long CityID;
	private String Jtqk;
	private String Jkqk;
	private String Zyqk;
	private String Srqk;
	private String Gzqk;
	private String Zc;
	private String Syxq;
	private String Yw;
	private Integer States;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getIdCard() {
		return IdCard;
	}

	public void setIdCard(String idCard) {
		IdCard = idCard;
	}

	public String getSzbd() {
		return Szbd;
	}

	public void setSzbd(String szbd) {
		Szbd = szbd;
	}

	public String getJx() {
		return Jx;
	}

	public void setJx(String jx) {
		Jx = jx;
	}

	public String getSjld() {
		return Sjld;
	}

	public void setSjld(String sjld) {
		Sjld = sjld;
	}

	public String getCbs() {
		return Cbs;
	}

	public void setCbs(String cbs) {
		Cbs = cbs;
	}

	public Integer getAge() {
		return Age;
	}

	public void setAge(Integer age) {
		Age = age;
	}

	public String getHyqk() {
		return Hyqk;
	}

	public void setHyqk(String hyqk) {
		Hyqk = hyqk;
	}

	public Long getProID() {
		return ProID;
	}

	public void setProID(Long proID) {
		ProID = proID;
	}

	public Long getCityID() {
		return CityID;
	}

	public void setCityID(Long cityID) {
		CityID = cityID;
	}

	public String getJtqk() {
		return Jtqk;
	}

	public void setJtqk(String jtqk) {
		Jtqk = jtqk;
	}

	public String getJkqk() {
		return Jkqk;
	}

	public void setJkqk(String jkqk) {
		Jkqk = jkqk;
	}

	public String getZyqk() {
		return Zyqk;
	}

	public void setZyqk(String zyqk) {
		Zyqk = zyqk;
	}

	public String getSrqk() {
		return Srqk;
	}

	public void setSrqk(String srqk) {
		Srqk = srqk;
	}

	public String getGzqk() {
		return Gzqk;
	}

	public void setGzqk(String gzqk) {
		Gzqk = gzqk;
	}

	public String getZc() {
		return Zc;
	}

	public void setZc(String zc) {
		Zc = zc;
	}

	public String getSyxq() {
		return Syxq;
	}

	public void setSyxq(String syxq) {
		Syxq = syxq;
	}

	public String getYw() {
		return Yw;
	}

	public void setYw(String yw) {
		Yw = yw;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}
