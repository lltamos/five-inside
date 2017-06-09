package com.alqsoft.entity.financialsummaryreport;

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
@Table(name = "financialsummaryreportt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FinancialSummaryReport extends IdEntity {
	private static final long serialVersionUID = 1L;
	// -周期
	private String Cycle;

	private String SummaryRoute;
	// 上传路径
	private String DetailRoute;
	// State---状态（0未初始化，1初始化，2已删除）

	private Integer State;

	public String getCycle() {
		return Cycle;
	}

	public void setCycle(String cycle) {
		Cycle = cycle;
	}

	public String getSummaryRoute() {
		return SummaryRoute;
	}

	public void setSummaryRoute(String summaryRoute) {
		SummaryRoute = summaryRoute;
	}

	public String getDetailRoute() {
		return DetailRoute;
	}

	public void setDetailRoute(String detailRoute) {
		DetailRoute = detailRoute;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

}
