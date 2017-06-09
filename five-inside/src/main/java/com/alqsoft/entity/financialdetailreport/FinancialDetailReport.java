package com.alqsoft.entity.financialdetailreport;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 废弃 TODO
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "financialdetailreportt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class FinancialDetailReport extends IdEntity {
	private static final long serialVersionUID = 1L;
//关联FinancialSummaryReportT的ID列
	private Long SummaryId;
//	ProviderID---关联ProviderT（供应商ID）
	private Long ProviderId;
//	GoodNum---商品数量
	private Long GoodNum;
//	PayMoney---支付金额
	private Double PayMoney;
//	State---状态（ 1已删除，0未删除）
	private Integer State;
//	DetailRoute---生成Excel文件路径
	private String DetailRoute;
//	GoodID---商品ID
	private Long GoodID;
	/*0：未发送 1：发送成功 2：发送失败*/
	private Integer EmailState;
//未结数量
	private Integer leftNum;

	public Long getSummaryId() {
		return SummaryId;
	}

	public void setSummaryId(Long summaryId) {
		SummaryId = summaryId;
	}

	public Long getProviderId() {
		return ProviderId;
	}

	public void setProviderId(Long providerId) {
		ProviderId = providerId;
	}

	public Long getGoodNum() {
		return GoodNum;
	}

	public void setGoodNum(Long goodNum) {
		GoodNum = goodNum;
	}

	public Double getPayMoney() {
		return PayMoney;
	}

	public void setPayMoney(Double payMoney) {
		PayMoney = payMoney;
	}

	public Integer getState() {
		return State;
	}

	public void setState(Integer state) {
		State = state;
	}

	public String getDetailRoute() {
		return DetailRoute;
	}

	public void setDetailRoute(String detailRoute) {
		DetailRoute = detailRoute;
	}

	public Long getGoodID() {
		return GoodID;
	}

	public void setGoodID(Long goodID) {
		GoodID = goodID;
	}

	public Integer getEmailState() {
		return EmailState;
	}

	public void setEmailState(Integer emailState) {
		EmailState = emailState;
	}

	public Integer getLeftNum() {
		return leftNum;
	}

	public void setLeftNum(Integer leftNum) {
		this.leftNum = leftNum;
	}

}
