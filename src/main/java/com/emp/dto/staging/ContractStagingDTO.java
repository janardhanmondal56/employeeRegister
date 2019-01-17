package com.emp.dto.staging;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.emp.util.dto.AuditableDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractStagingDTO extends AuditableDTO {
	private static final long serialVersionUID = 3389405477804170807L;

	private String tenantId;

	private String entityId;

	private String contractName;

	private String contractIdentifier;

	private String poNumber;

	private String contractPaymentTerms;

	private String contractValue;

	private String slaTerms;

	private String expirationDate;

	private String notifyUser;

	private String notificationIndaysBeforeDate;

	private String renewalDate;

	private String autoRenewal;

	private String renewalPeriodInMonths;

	private String autoRenewalStartDate;

	private String effectiveDate;

	private String terminationDate;

	private String terminationInDays;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractIdentifier() {
		return contractIdentifier;
	}

	public void setContractIdentifier(String contractIdentifier) {
		this.contractIdentifier = contractIdentifier;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getContractPaymentTerms() {
		return contractPaymentTerms;
	}

	public void setContractPaymentTerms(String contractPaymentTerms) {
		this.contractPaymentTerms = contractPaymentTerms;
	}

	public String getContractValue() {
		return contractValue;
	}

	public void setContractValue(String contractValue) {
		this.contractValue = contractValue;
	}

	public String getSlaTerms() {
		return slaTerms;
	}

	public void setSlaTerms(String slaTerms) {
		this.slaTerms = slaTerms;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getNotifyUser() {
		return notifyUser;
	}

	public void setNotifyUser(String notifyUser) {
		this.notifyUser = notifyUser;
	}

	public String getNotificationIndaysBeforeDate() {
		return notificationIndaysBeforeDate;
	}

	public void setNotificationIndaysBeforeDate(String notificationIndaysBeforeDate) {
		this.notificationIndaysBeforeDate = notificationIndaysBeforeDate;
	}

	public String getRenewalDate() {
		return renewalDate;
	}

	public void setRenewalDate(String renewalDate) {
		this.renewalDate = renewalDate;
	}

	public String getAutoRenewal() {
		return autoRenewal;
	}

	public void setAutoRenewal(String autoRenewal) {
		this.autoRenewal = autoRenewal;
	}

	public String getRenewalPeriodInMonths() {
		return renewalPeriodInMonths;
	}

	public void setRenewalPeriodInMonths(String renewalPeriodInMonths) {
		this.renewalPeriodInMonths = renewalPeriodInMonths;
	}

	public String getAutoRenewalStartDate() {
		return autoRenewalStartDate;
	}

	public void setAutoRenewalStartDate(String autoRenewalStartDate) {
		this.autoRenewalStartDate = autoRenewalStartDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getTerminationDate() {
		return terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public String getTerminationInDays() {
		return terminationInDays;
	}

	public void setTerminationInDays(String terminationInDays) {
		this.terminationInDays = terminationInDays;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ContractStagingDTO [tenantId=").append(tenantId).append(", entityId=").append(entityId)
				.append(", contractName=").append(contractName).append(", contractIdentifier=")
				.append(contractIdentifier).append(", poNumber=").append(poNumber).append(", contractPaymentTerms=")
				.append(contractPaymentTerms).append(", contractValue=").append(contractValue).append(", slaTerms=")
				.append(slaTerms).append(", expirationDate=").append(expirationDate).append(", notifyUser=")
				.append(notifyUser).append(", notificationIndaysBeforeDate=").append(notificationIndaysBeforeDate)
				.append(", renewalDate=").append(renewalDate).append(", autoRenewal=").append(autoRenewal)
				.append(", renewalPeriodInMonths=").append(renewalPeriodInMonths).append(", autoRenewalStartDate=")
				.append(autoRenewalStartDate).append(", effectiveDate=").append(effectiveDate)
				.append(", terminationDate=").append(terminationDate).append(", terminationInDays=")
				.append(terminationInDays).append("]");
		return builder.toString();
	}

}
