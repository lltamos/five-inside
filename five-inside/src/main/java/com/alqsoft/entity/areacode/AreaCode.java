package com.alqsoft.entity.areacode;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * AreaCodeT  区域表
 * @author Administrator
 *
 */
@Entity
@Table(name = "areacodet")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class AreaCode extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//名称
	private String Area;
	//地区邮编
	private String AreaCode;
	//地区代号
	private String ResultCode;
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getAreaCode() {
		return AreaCode;
	}
	public void setAreaCode(String areaCode) {
		AreaCode = areaCode;
	}
	public String getResultCode() {
		return ResultCode;
	}
	public void setResultCode(String resultCode) {
		ResultCode = resultCode;
	}
	
	
}
