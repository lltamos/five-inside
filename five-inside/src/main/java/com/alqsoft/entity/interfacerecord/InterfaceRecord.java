package com.alqsoft.entity.interfacerecord;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * InterfaceRecordT 接口开关记录表 (目前已停用)
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "interfacerecordt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class InterfaceRecord extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 主键
	// 模块名称
	private String ModelName;
	// 模块代码
	private String ModelCode;
	// 关闭时间
	private Date CloseTime;
	// 关闭人
	// 打开时间
	private Date OpenTime;
	// 打开人
	private String OpenUser;
	// 备注
	private String Remark;

	private String CloseUser;

	public String getModelName() {
		return ModelName;
	}

	public void setModelName(String modelName) {
		ModelName = modelName;
	}

	public String getModelCode() {
		return ModelCode;
	}

	public void setModelCode(String modelCode) {
		ModelCode = modelCode;
	}

	public Date getCloseTime() {
		return CloseTime;
	}

	public void setCloseTime(Date closeTime) {
		CloseTime = closeTime;
	}

	public Date getOpenTime() {
		return OpenTime;
	}

	public void setOpenTime(Date openTime) {
		OpenTime = openTime;
	}

	public String getOpenUser() {
		return OpenUser;
	}

	public void setOpenUser(String openUser) {
		OpenUser = openUser;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getCloseUser() {
		return CloseUser;
	}

	public void setCloseUser(String closeUser) {
		CloseUser = closeUser;
	}

}
