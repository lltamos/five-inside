package com.alqsoft.entity.interfacestatus;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * InterfaceStatusT 接口开关状态表(已停用)
 * 
 * @author Administrator
 *
 */

@Entity
@Table(name = "interfacestatust")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class InterfaceStatus extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 模块名称
	private String ModelName;
	// 模块代码
	private String ModelCode;
	// 接口状态
	private String InterfaceStatus;
	// 备注
	private String Remark;

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

	public String getInterfaceStatus() {
		return InterfaceStatus;
	}

	public void setInterfaceStatus(String interfaceStatus) {
		InterfaceStatus = interfaceStatus;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
