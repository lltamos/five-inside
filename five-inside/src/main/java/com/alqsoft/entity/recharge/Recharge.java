package com.alqsoft.entity.recharge;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 充值记录表
 * Recharge.java
 * @copyright 2015-2016
 * @create-time 2017年2月9日 上午10:30:39
 *
 */
@Entity
@Table(name = "recharget")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Recharge  extends IdEntity{

private static final long serialVersionUID = 1L;
private String UserKeyId;//用户ID
private String PayBankNo;//支付银行账号
private String PayBankName;//支付人姓名
private Double Money;//支付金额
private String Type;//支付类型 0:线下充值 1:线上充值
private String State;//汇款充值：0：提交了等充值 1：已充值  2:已撤销 在线充值：0：未支付 1：已支付并且充值2:已撤销
private Integer IsOrderCenter;
private String Remark;
public String getUserKeyId() {
	return UserKeyId;
}
public void setUserKeyId(String userKeyId) {
	UserKeyId = userKeyId;
}
public String getPayBankNo() {
	return PayBankNo;
}
public void setPayBankNo(String payBankNo) {
	PayBankNo = payBankNo;
}
public String getPayBankName() {
	return PayBankName;
}
public void setPayBankName(String payBankName) {
	PayBankName = payBankName;
}
public Double getMoney() {
	return Money;
}
public void setMoney(Double money) {
	Money = money;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}
public String getState() {
	return State;
}
public void setState(String state) {
	State = state;
}
public Integer getIsOrderCenter() {
	return IsOrderCenter;
}
public void setIsOrderCenter(Integer isOrderCenter) {
	IsOrderCenter = isOrderCenter;
}
public String getRemark() {
	return Remark;
}
public void setRemark(String remark) {
	Remark = remark;
}

}
