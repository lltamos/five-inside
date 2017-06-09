package com.alqsoft.entity.livecomment;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 直播评论表 TODO 废弃
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "livecommentt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class LiveComment extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String Content;
	private Long userid;
	private Long LiveId;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public Long getLiveId() {
		return LiveId;
	}

	public void setLiveId(Long liveId) {
		LiveId = liveId;
	}

}
