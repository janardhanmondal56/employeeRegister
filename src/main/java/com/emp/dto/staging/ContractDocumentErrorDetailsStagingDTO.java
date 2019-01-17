package com.emp.dto.staging;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.emp.util.dto.BaseDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDocumentErrorDetailsStagingDTO extends BaseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8437013224029747143L;
	private String contractDocumentId;

	public String getContractDocumentId() {
		return contractDocumentId;
	}

	public void setContractDocumentId(String contractDocumentId) {
		this.contractDocumentId = contractDocumentId;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}

	private String errorDescription;
}
