package com.alqsoft.entity.good;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * GoodT 商品表 目前用于自提系统
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "goodt")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Good extends IdEntity {

	private static final long serialVersionUID = 1L;
	// 商品名称
	@NotBlank(message = "商品名称不能为空",groups={goodSubmit.class})
	private String GoodName;
	// 商品价格
	private Double GoodMoney;
	// 关键字
	@NotBlank(message = "关键字不能为空",groups={goodSubmit.class})
	private String KeyWord;
	// 商品图片
	private String GoodImgUrl;
	// 详细说明
	private String Details;
	// 状态  0:初始值1:上架2:下架3:删除
	private Integer States;
    //1 推荐产品 ,2套餐产品,3家具产品,4食品饮料,5美容日用,6其它专属,7公司直发,8厂家直邮,9上门自提,10套餐商品
	private Integer GoodCate;
	// 商品条码
	private String BarCode;
	// 销售总量，目前已弃用
	private Integer TotalSaleNum;
	// 进货价格
	private Double BuyPrice;

	
	public String getGoodName() {
		return GoodName;
	}

	public void setGoodName(String goodName) {
		GoodName = goodName;
	}

	public Double getGoodMoney() {
		return GoodMoney;
	}

	public void setGoodMoney(Double goodMoney) {
		GoodMoney = goodMoney;
	}

	public String getKeyWord() {
		return KeyWord;
	}

	public void setKeyWord(String keyWord) {
		KeyWord = keyWord;
	}

	public String getGoodImgUrl() {
		return GoodImgUrl;
	}

	public void setGoodImgUrl(String goodImgUrl) {
		GoodImgUrl = goodImgUrl;
	}

	public String getDetails() {
		return Details;
	}

	public void setDetails(String details) {
		Details = details;
	}

	public Integer getStates() {
		return States;
	}

	public void setStates(Integer states) {
		States = states;
	}

	public Integer getGoodCate() {
		return GoodCate;
	}

	public void setGoodCate(Integer goodCate) {
		GoodCate = goodCate;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	public Integer getTotalSaleNum() {
		return TotalSaleNum;
	}

	public void setTotalSaleNum(Integer totalSaleNum) {
		TotalSaleNum = totalSaleNum;
	}

	public Double getBuyPrice() {
		return BuyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		BuyPrice = buyPrice;
	}

	
	public interface goodSubmit{
		
	}
}
