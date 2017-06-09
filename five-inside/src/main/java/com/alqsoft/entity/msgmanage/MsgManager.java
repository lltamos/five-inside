package com.alqsoft.entity.msgmanage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO 废弃
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "msgmanaget")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class MsgManager extends IdEntity {
	private static final long serialVersionUID = 1L;

	private String MsgTitle;
	private String MsgInfo;
	private Integer MsgType;
	private Integer State;
	private Date GoOnlineTime;

	public String getMsgTitle() {
		return MsgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		MsgTitle = msgTitle;
	}

	public String getMsgInfo() {
		return MsgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		MsgInfo = msgInfo;
	}

	public Integer getMsgType() {
		return MsgType;
	}

	public void setMsgType(Integer msgType) {
		MsgType = msgType;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public Date getGoOnlineTime() {
		return GoOnlineTime;
	}

	public void setGoOnlineTime(Date goOnlineTime) {
		GoOnlineTime = goOnlineTime;
	}

}
