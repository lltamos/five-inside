package com.alqsoft.entity.abnormalip;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "abnormalipt")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ABNormalIp extends IdEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 该类为用户黑IP;
	 */

//用户ip
private String UserIP;
	public String getUserIP() {
		return UserIP;
	}
	public void setUserIP(String userIP) {
		UserIP = userIP;
	}


}
