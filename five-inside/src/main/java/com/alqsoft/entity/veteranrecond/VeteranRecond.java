package com.alqsoft.entity.veteranrecond;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * TODO 表废弃
 * 复转军人表
 */
@Entity
@Table(name = "veteranrecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class VeteranRecond extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 用户id
	private Long UserID;
	// 身份证号
	private String CardId;
	// 状态
	private Integer States;
	private Date ExamineTime;
	private String Remark;
	// 图片路径
	private String ImgSrc;
	//
	private Integer OcUserId;

	public Long getUserID() {
		return UserID;
	}

	public void setUserID(Long userID) {
		UserID = userID;
	}

	public String getCardId() {
		return CardId;
	}

	public void setCardId(String cardId) {
		CardId = cardId;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public Date getExamineTime() {
		return ExamineTime;
	}

	public void setExamineTime(Date examineTime) {
		ExamineTime = examineTime;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getImgSrc() {
		return ImgSrc;
	}

	public void setImgSrc(String imgSrc) {
		ImgSrc = imgSrc;
	}

	public Integer getOcUserId() {
		return OcUserId;
	}

	public void setOcUserId(Integer ocUserId) {
		OcUserId = ocUserId;
	}

}
