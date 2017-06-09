package com.alqsoft.entity.stocktakeapply;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/*
 * 盘存申请记录
 */
@Entity
@Table(name = "stocktakeapply")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class StockTakeApply extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 商品ID
	private Long GoodId;
	// 商品名称
	private String Goodname;
	// 商品数量
	private Integer Num;
	// 商品单价
	private Double GoodMoney;
	// 备注
	private String Remark;
	// 处理类型（厂家处理或公司支付，Nvarchar）
	private String HandleType;
	// 状态（0：未审批； 1：已审批； 2：已撤销）
	private Integer States;

	public Long getGoodId() {
		return GoodId;
	}

	public void setGoodId(Long goodId) {
		GoodId = goodId;
	}

	public String getGoodname() {
		return Goodname;
	}

	public void setGoodname(String goodname) {
		Goodname = goodname;
	}

	public Integer getNum() {
		return Num;
	}

	public void setNum(Integer num) {
		Num = num;
	}

	public Double getGoodMoney() {
		return GoodMoney;
	}

	public void setGoodMoney(Double goodMoney) {
		GoodMoney = goodMoney;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getHandleType() {
		return HandleType;
	}

	public void setHandleType(String handleType) {
		HandleType = handleType;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

}
