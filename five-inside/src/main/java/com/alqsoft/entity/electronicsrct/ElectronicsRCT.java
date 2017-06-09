package com.alqsoft.entity.electronicsrct;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "electronicsrct")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class ElectronicsRCT extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	// 0:报单订单协议
	private Integer gType;
	// 路径
	private String Src;
	// 订单ID
	private String OrderID;
	// 标题
	private String Title;
	// Swf路径
	private String SwfSrc;

	public Integer getgType() {
		return gType;
	}

	public void setgType(Integer gType) {
		this.gType = gType;
	}

	public String getSrc() {
		return Src;
	}

	public void setSrc(String src) {
		Src = src;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getSwfSrc() {
		return SwfSrc;
	}

	public void setSwfSrc(String swfSrc) {
		SwfSrc = swfSrc;
	}

}
