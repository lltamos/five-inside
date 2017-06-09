package com.alqsoft.entity.yyzyoperatedrecord;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 运营专员记录表
 */
@Entity
@Table(name = "yyzyoperatedrecord")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class YYZYOperatedRecord extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 手机
	private String Mobile;
	// 姓名
	private String Name;
	// 操作类型（1修改密码，2撤销充值，3撤销订单）
	private Integer OpType;
	// 备注说明
	private String Remark;

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Integer getOpType() {
		return OpType;
	}

	public void setOpType(Integer opType) {
		OpType = opType;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
