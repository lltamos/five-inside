package com.alqsoft.entity.useridcrad;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 用户身份证
 */
@Entity
@Table(name = "useridcradt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class UserIdcrad extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 用户id
	private Long UserId;
	// 省
	private String Province;
	// 市
	private String City;
	// 县
	private String Town;
	// 性别
	private String Sex;
	// 生日
	private Date Birth;
	// 地区
	private String Area;

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getTown() {
		return Town;
	}

	public void setTown(String town) {
		Town = town;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public Date getBirth() {
		return Birth;
	}

	public void setBirth(Date birth) {
		Birth = birth;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}

}
