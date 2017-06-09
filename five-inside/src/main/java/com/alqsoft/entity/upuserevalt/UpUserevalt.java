package com.alqsoft.entity.upuserevalt;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 推广师升级记录表
 * 
 * @author panlichao
 *
 */
@Entity
@Table(name = "upuserevalt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class UpUserevalt extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private Date CreatedTime;
	// 用户id
	private Integer UserID;
	// 用户等级 0:普通用户 1:推广师
	private Integer Grade;
	// 操作管理员
	private String AdminUser;

	// public Date getCreatedTime() {
	// return CreatedTime;
	// }
	//
	// public void setCreatedTime(Date createdTime) {
	// CreatedTime = createdTime;
	// }

	public Integer getUserID() {
		return UserID;
	}

	public void setUserID(Integer userID) {
		UserID = userID;
	}

	public Integer getGrade() {
		return Grade;
	}

	public void setGrade(Integer grade) {
		Grade = grade;
	}

	public String getAdminUser() {
		return AdminUser;
	}

	public void setAdminUser(String adminUser) {
		AdminUser = adminUser;
	}

}
