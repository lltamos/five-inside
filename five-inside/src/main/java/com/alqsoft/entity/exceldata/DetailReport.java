package com.alqsoft.entity.exceldata;

public class DetailReport {
//	序号
	String id;
//	供应商名称
	String provideName;
//	供应商id
	String provideId;
//	产品名称
	String goodName;
//	产品ID
	String  goodId;
//	销量
	String saleNum;
//	单笔序号
	String signNum;
//	收款方银行账号
	String  collectionBank;
//	银行类型
	String collectionBankType;
//	真实姓名
	String realName;
//	付款金额(元)
	String payMoney;
//	未结数量
	String ingNum;
//	账户属性
	String accountAttr;
//	账户类型
	String accountType;
//	开户地区（对公必填）
	String accountArea;
//	开户城市（对公必填）
	String accountCity;
//	支行名称
	String zhiBankName;
//	联行号（对公打款必填）
	String ylNum;
//	付款说明
	String paySay;
//	收款人手机号
	String collectsPhone;
//	所属机构
	String  institution;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvideName() {
		return provideName;
	}
	public void setProvideName(String provideName) {
		this.provideName = provideName;
	}
	public String getProvideId() {
		return provideId;
	}
	public void setProvideId(String provideId) {
		this.provideId = provideId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public String getSaleNum() {
		return saleNum;
	}
	public void setSaleNum(String saleNum) {
		this.saleNum = saleNum;
	}
	public String getSignNum() {
		return signNum;
	}
	public void setSignNum(String signNum) {
		this.signNum = signNum;
	}
	public String getCollectionBank() {
		return collectionBank;
	}
	public void setCollectionBank(String collectionBank) {
		this.collectionBank = collectionBank;
	}
	public String getCollectionBankType() {
		return collectionBankType;
	}
	public void setCollectionBankType(String collectionBankType) {
		this.collectionBankType = collectionBankType;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(String payMoney) {
		this.payMoney = payMoney;
	}
	public String getIngNum() {
		return ingNum;
	}
	public void setIngNum(String ingNum) {
		this.ingNum = ingNum;
	}
	public String getAccountAttr() {
		return accountAttr;
	}
	public void setAccountAttr(String accountAttr) {
		this.accountAttr = accountAttr;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountArea() {
		return accountArea;
	}
	public void setAccountArea(String accountArea) {
		this.accountArea = accountArea;
	}
	public String getAccountCity() {
		return accountCity;
	}
	public void setAccountCity(String accountCity) {
		this.accountCity = accountCity;
	}
	public String getZhiBankName() {
		return zhiBankName;
	}
	public void setZhiBankName(String zhiBankName) {
		this.zhiBankName = zhiBankName;
	}
	public String getYlNum() {
		return ylNum;
	}
	public void setYlNum(String ylNum) {
		this.ylNum = ylNum;
	}
	public String getPaySay() {
		return paySay;
	}
	public void setPaySay(String paySay) {
		this.paySay = paySay;
	}
	public String getCollectsPhone() {
		return collectsPhone;
	}
	public void setCollectsPhone(String collectsPhone) {
		this.collectsPhone = collectsPhone;
	}
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	@Override
	public String toString() {
		return "DetailReport [id=" + id + ", provideName=" + provideName + ", provideId=" + provideId + ", goodName="
				+ goodName + ", goodId=" + goodId + ", saleNum=" + saleNum + ", signNum=" + signNum
				+ ", collectionBank=" + collectionBank + ", collectionBankType=" + collectionBankType + ", realName="
				+ realName + ", payMoney=" + payMoney + ", ingNum=" + ingNum + ", accountAttr=" + accountAttr
				+ ", accountType=" + accountType + ", accountArea=" + accountArea + ", accountCity=" + accountCity
				+ ", zhiBankName=" + zhiBankName + ", ylNum=" + ylNum + ", paySay=" + paySay + ", collectsPhone="
				+ collectsPhone + ", institution=" + institution + "]";
	}
	
}
