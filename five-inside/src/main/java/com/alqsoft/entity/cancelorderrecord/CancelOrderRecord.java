package com.alqsoft.entity.cancelorderrecord;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CancelOrderRecord  撤销订单表
 * @author Administrator
 *
 */


@Entity
@Table(name = "cancelorderrecord")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class CancelOrderRecord extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//订单号
	private String OrderNO;
	//订单数量
	private Long OrderNum;
	//姓名
	private String UserName;
	//电话
	private String UserMobile;
	//推荐人姓名
	private String TjrName;
	//推荐人电话
	private String TjrMobile;
	//订单中心编号
	private String BianH;
	//撤销时间
	private Date CancelTime;
	//备注
	private String Remark;
	public String getOrderNO() {
		return OrderNO;
	}
	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}
	public Long getOrderNum() {
		return OrderNum;
	}
	public void setOrderNum(Long orderNum) {
		OrderNum = orderNum;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserMobile() {
		return UserMobile;
	}
	public void setUserMobile(String userMobile) {
		UserMobile = userMobile;
	}
	public String getTjrName() {
		return TjrName;
	}
	public void setTjrName(String tjrName) {
		TjrName = tjrName;
	}
	public String getTjrMobile() {
		return TjrMobile;
	}
	public void setTjrMobile(String tjrMobile) {
		TjrMobile = tjrMobile;
	}
	public String getBianH() {
		return BianH;
	}
	public void setBianH(String bianH) {
		BianH = bianH;
	}
	public Date getCancelTime() {
		return CancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		CancelTime = cancelTime;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	
	
	
}
