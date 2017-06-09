package com.alqsoft.model;

import java.io.Serializable;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail com
 * @version v1 0
 * @copyright 2010-2015
 * @create-time 2017年2月16日 下午3:13:16
 * 
 */
@SuppressWarnings("serial")
public class YLpayModel implements Serializable{
	
        private String   Version ;                   //报文版本号
        
	    private String   ProcCode ;                  //消息类型
	    
	    private String   ProcessCode ;               //处理码
	    
	    private String   AccountNo ;                 //交易卡号
	    
	    private String   AccountType ;               //交易卡类型
	    
	    private String   MobileNo ;                  //交易手机号
	    
	    private String   Amount ;                    //交易金额
	    
	    private String   Currency ;                  //交易币种
	    
	    private String   SynAddress ;                //同步返回地址
	    
	    private String   AsynAddress ;               //异步返回地址
	    
	    private String   Remark ;                    //备注
	    
	    private String   TerminalNo ;                //终端号
	    
	    private String   MerchantNo ;                //商户号
	    
	    private String   MerchantOrderNo ;           //商户订单编号
	    
	    private String   orderNo;					 //---未使用-- 为空值
	    
	    private String   OrderFrom ;                 //订单来源
	    
	    private String   Language ;                  //订单播报语言类型
	    
	    private String   Description ;               //订单描述
	    
	    private String   OrderType ;                 //订单类型
	    
	    private String   AcqSsn ;                    //系统跟踪号
	    
	    private String   Reference ;                 //系统参考号
	    
	    private String   TransDatetime ;             //传输日期时间
	    
	    private String   MerchantName ;              //商户名称
	    
	    private String   TransData ;                 //其他业务资料
	    
	    private String   IDCardName ;                //银行开户姓名
	    
	    private String   IDCardNo ;                  //银行开户证件号码
	    
	    private String   BankAddress ;               //开户银行所在省市
	    
	    private String   IDCardType ;                //开户证件类型
	    
	    private String   BeneficiaryName ;           //受益人姓名
	    
	    private String   BeneficiaryMobileNo ;       //受益人手机号
	    
	    private String   DeliveryAddress ;           //产品销售地
	    
	    private String   IpAddress ;                 //Ip地址
	    
	    private String   Location ;                  //位置信息
	    
	    private String   UserFlag ;                  //用户定制标记
	    
	    private String   czlaunch ;					 //充值发起项目标记
	    
	    private String   state;						 //充值状态

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getCzlaunch() {
			return czlaunch;
		}

		public void setCzlaunch(String czlaunch) {
			this.czlaunch = czlaunch;
		}

		public String getVersion() {
			return Version;
		}

		public void setVersion(String version) {
			Version = version;
		}

		public String getProcCode() {
			return ProcCode;
		}

		public void setProcCode(String procCode) {
			ProcCode = procCode;
		}

		public String getProcessCode() {
			return ProcessCode;
		}

		public void setProcessCode(String processCode) {
			ProcessCode = processCode;
		}

		public String getAccountNo() {
			return AccountNo;
		}

		public void setAccountNo(String accountNo) {
			AccountNo = accountNo;
		}

		public String getAccountType() {
			return AccountType;
		}

		public void setAccountType(String accountType) {
			AccountType = accountType;
		}

		public String getMobileNo() {
			return MobileNo;
		}

		public void setMobileNo(String mobileNo) {
			MobileNo = mobileNo;
		}

		public String getAmount() {
			return Amount;
		}

		public void setAmount(String amount) {
			Amount = amount;
		}

		public String getCurrency() {
			return Currency;
		}

		public void setCurrency(String currency) {
			Currency = currency;
		}

		public String getSynAddress() {
			return SynAddress;
		}

		public void setSynAddress(String synAddress) {
			SynAddress = synAddress;
		}

		public String getAsynAddress() {
			return AsynAddress;
		}

		public void setAsynAddress(String asynAddress) {
			AsynAddress = asynAddress;
		}

		public String getRemark() {
			return Remark;
		}

		public void setRemark(String remark) {
			Remark = remark;
		}

		public String getTerminalNo() {
			return TerminalNo;
		}

		public void setTerminalNo(String terminalNo) {
			TerminalNo = terminalNo;
		}

		public String getMerchantNo() {
			return MerchantNo;
		}

		public void setMerchantNo(String merchantNo) {
			MerchantNo = merchantNo;
		}

		public String getMerchantOrderNo() {
			return MerchantOrderNo;
		}

		public void setMerchantOrderNo(String merchantOrderNo) {
			MerchantOrderNo = merchantOrderNo;
		}

		public String getOrderNo() {
			return orderNo;
		}

		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}

		public String getOrderFrom() {
			return OrderFrom;
		}

		public void setOrderFrom(String orderFrom) {
			OrderFrom = orderFrom;
		}

		public String getLanguage() {
			return Language;
		}

		public void setLanguage(String language) {
			Language = language;
		}

		public String getDescription() {
			return Description;
		}

		public void setDescription(String description) {
			Description = description;
		}

		public String getOrderType() {
			return OrderType;
		}

		public void setOrderType(String orderType) {
			OrderType = orderType;
		}

		public String getAcqSsn() {
			return AcqSsn;
		}

		public void setAcqSsn(String acqSsn) {
			AcqSsn = acqSsn;
		}

		public String getReference() {
			return Reference;
		}

		public void setReference(String reference) {
			Reference = reference;
		}

		public String getTransDatetime() {
			return TransDatetime;
		}

		public void setTransDatetime(String transDatetime) {
			TransDatetime = transDatetime;
		}

		public String getMerchantName() {
			return MerchantName;
		}

		public void setMerchantName(String merchantName) {
			MerchantName = merchantName;
		}

		public String getTransData() {
			return TransData;
		}

		public void setTransData(String transData) {
			TransData = transData;
		}

		public String getIDCardName() {
			return IDCardName;
		}

		public void setIDCardName(String iDCardName) {
			IDCardName = iDCardName;
		}

		public String getIDCardNo() {
			return IDCardNo;
		}

		public void setIDCardNo(String iDCardNo) {
			IDCardNo = iDCardNo;
		}

		public String getBankAddress() {
			return BankAddress;
		}

		public void setBankAddress(String bankAddress) {
			BankAddress = bankAddress;
		}

		public String getIDCardType() {
			return IDCardType;
		}

		public void setIDCardType(String iDCardType) {
			IDCardType = iDCardType;
		}

		public String getBeneficiaryName() {
			return BeneficiaryName;
		}

		public void setBeneficiaryName(String beneficiaryName) {
			BeneficiaryName = beneficiaryName;
		}

		public String getBeneficiaryMobileNo() {
			return BeneficiaryMobileNo;
		}

		public void setBeneficiaryMobileNo(String beneficiaryMobileNo) {
			BeneficiaryMobileNo = beneficiaryMobileNo;
		}

		public String getDeliveryAddress() {
			return DeliveryAddress;
		}

		public void setDeliveryAddress(String deliveryAddress) {
			DeliveryAddress = deliveryAddress;
		}

		public String getIpAddress() {
			return IpAddress;
		}

		public void setIpAddress(String ipAddress) {
			IpAddress = ipAddress;
		}

		public String getLocation() {
			return Location;
		}

		public void setLocation(String location) {
			Location = location;
		}

		public String getUserFlag() {
			return UserFlag;
		}

		public void setUserFlag(String userFlag) {
			UserFlag = userFlag;
		}
	    
}
