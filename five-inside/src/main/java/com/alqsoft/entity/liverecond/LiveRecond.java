package com.alqsoft.entity.liverecond;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 直播表 TODO 废弃
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "liverecondt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class LiveRecond extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String Title;
	private String Url;
	private Integer States;
	private String KeyCont;
	private Integer IsLive;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public String getKeyCont() {
		return KeyCont;
	}

	public void setKeyCont(String keyCont) {
		KeyCont = keyCont;
	}

	public Integer getIsLive() {
		return IsLive;
	}

	public void setIsLive(Integer isLive) {
		IsLive = isLive;
	}

}
