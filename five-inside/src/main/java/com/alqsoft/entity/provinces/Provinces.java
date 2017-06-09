package com.alqsoft.entity.provinces;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 省份
 */
@Entity
@Table(name = "provinces")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Provinces extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 省份识别ID
	private String KeyID;
	// 省份名字
	private String Name;
	// 上级id
	private Long fatherID;
	// 上级名字
	private String ParentName;
	// 是否删除
	private Integer IsDel;
	// 身份证中前六位
	private String ICardNum;
	// 地区
	private String Area;

	public String getKeyID() {
		return KeyID;
	}

	public void setKeyID(String keyID) {
		KeyID = keyID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Long getFatherID() {
		return fatherID;
	}

	public void setFatherID(Long fatherID) {
		this.fatherID = fatherID;
	}

	public String getParentName() {
		return ParentName;
	}

	public void setParentName(String parentName) {
		ParentName = parentName;
	}

	public Integer getIsDel() {
		return IsDel;
	}

	public void setIsDel(Integer isDel) {
		IsDel = isDel;
	}

	public String getICardNum() {
		return ICardNum;
	}

	public void setICardNum(String iCardNum) {
		ICardNum = iCardNum;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

}
