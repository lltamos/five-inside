package com.alqsoft.entity.mdval;

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
@Table(name = "mdvalt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class MDval extends IdEntity {

	private static final long serialVersionUID = 1L;

	private String MdPwd;
	private String ValNum;

	public String getMdPwd() {
		return MdPwd;
	}

	public void setMdPwd(String mdPwd) {
		MdPwd = mdPwd;
	}

	public String getValNum() {
		return ValNum;
	}

	public void setValNum(String valNum) {
		ValNum = valNum;
	}

}
