package com.alqsoft.entity.provider;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 供应商
 * 废弃 TODO
 * 
 * @author thinkpad
 *
 */
@Entity
@Table(name = "providert")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Provider extends IdEntity {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String Name;//供应商公司名称
	@Pattern(regexp="^((0\\d{2,3}-\\d{7,8})|(1[35784]\\d{9}))$", message="请输入正确的手机号码或固定电话",groups={providerSubmit.class})
	private String Contact;//供应商0公司联系电话联系方式（电话）
	private String AccountNum;//收款账户
	private String BankName;//银行名称
	private String AccountAttribute;//账户属性（对公或对私，Nvarchar类型）
	private String AccountType;//账户类型（借记卡或存折）
	private String AccountArea;//开户地区（省区）
	private String AccountCity;//开户地市区
	private String BankNum;//联行号码
	private String AccountOwner;//户主姓名
	private String BranchName;//支行名称
	private String Mobile;//开户人手机号
	private String Organization;//所属机构
	@Pattern(regexp="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message="请输入正确的电子邮箱",groups={providerSubmit.class})
	private String Email;//邮箱地址

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getContact() {
		return Contact;
	}

	public void setContact(String contact) {
		Contact = contact;
	}

	public String getAccountNum() {
		return AccountNum;
	}

	public void setAccountNum(String accountNum) {
		AccountNum = accountNum;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getAccountAttribute() {
		return AccountAttribute;
	}

	public void setAccountAttribute(String accountAttribute) {
		AccountAttribute = accountAttribute;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getAccountArea() {
		return AccountArea;
	}

	public void setAccountArea(String accountArea) {
		AccountArea = accountArea;
	}

	public String getAccountCity() {
		return AccountCity;
	}

	public void setAccountCity(String accountCity) {
		AccountCity = accountCity;
	}

	public String getBankNum() {
		return BankNum;
	}

	public void setBankNum(String bankNum) {
		BankNum = bankNum;
	}

	public String getAccountOwner() {
		return AccountOwner;
	}

	public void setAccountOwner(String accountOwner) {
		AccountOwner = accountOwner;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getOrganization() {
		return Organization;
	}

	public void setOrganization(String organization) {
		Organization = organization;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	public interface providerSubmit{
		
	}

}
