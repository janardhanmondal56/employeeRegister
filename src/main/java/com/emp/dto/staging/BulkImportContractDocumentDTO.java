package com.emp.dto.staging;

import com.emp.util.dto.BaseDTO;

public class BulkImportContractDocumentDTO extends BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8019327361450486249L;
	private String vendorID;
	private String clientID;
	private String contractIdentifire;
	private String documentType;
	private String contractStatus;
	private String documentPath;
	private ContractDocumentErrorDetailsStagingDTO contractDocumentErrorDetailsStagingDTO;
	public String getDocumentLevelStatus() {
		return documentLevelStatus;
	}
	public void setDocumentLevelStatus(String documentLevelStatus) {
		this.documentLevelStatus = documentLevelStatus;
	}
	private String documentLevelStatus;
	public ContractDocumentErrorDetailsStagingDTO getContractDocumentErrorDetailsStagingDTO() {
		return contractDocumentErrorDetailsStagingDTO;
	}
	public void setContractDocumentErrorDetailsStagingDTO(
			ContractDocumentErrorDetailsStagingDTO contractDocumentErrorDetailsStagingDTO) {
		this.contractDocumentErrorDetailsStagingDTO = contractDocumentErrorDetailsStagingDTO;
	}
	public String getVendorID() {
		return vendorID;
	}
	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getContractIdentifire() {
		return contractIdentifire;
	}
	public void setContractIdentifire(String contractIdentifire) {
		this.contractIdentifire = contractIdentifire;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	public String getDocumentPath() {
		return documentPath;
	}
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	
}
