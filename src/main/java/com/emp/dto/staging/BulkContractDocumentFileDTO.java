package com.emp.dto.staging;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.emp.util.dto.BaseDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BulkContractDocumentFileDTO extends BaseDTO {

	private static final long serialVersionUID = 7709043704294797462L;

	private String bulkImportFileName;

	private String protectedUrl;

	private String publicUrl;

	private String objectId;
	
	private String clientID;

	private String status;

	private String uploadedDate;

	private String uploadedBy;

	private int totalNofDocuments;

	private int noOfSuccessDocuments;

	private int noOfFailedDocuments;	

	public String getBulkImportFileName() {
		return bulkImportFileName;
	}

	public void setBulkImportFileName(String bulkImportFileName) {
		this.bulkImportFileName = bulkImportFileName;
	}

	public String getProtectedUrl() {
		return protectedUrl;
	}

	public void setProtectedUrl(String protectedUrl) {
		this.protectedUrl = protectedUrl;
	}

	public String getPublicUrl() {
		return publicUrl;
	}

	public void setPublicUrl(String publicUrl) {
		this.publicUrl = publicUrl;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}

	public String getUploadedBy() {
		return uploadedBy;
	}

	public void setUploadedBy(String uploadedBy) {
		this.uploadedBy = uploadedBy;
	}

	public int getTotalNofDocuments() {
		return totalNofDocuments;
	}

	public void setTotalNofDocuments(int totalNofDocuments) {
		this.totalNofDocuments = totalNofDocuments;
	}

	public int getNoOfSuccessDocuments() {
		return noOfSuccessDocuments;
	}

	public void setNoOfSuccessDocuments(int noOfSuccessDocuments) {
		this.noOfSuccessDocuments = noOfSuccessDocuments;
	}

	public int getNoOfFailedDocuments() {
		return noOfFailedDocuments;
	}

	public void setNoOfFailedDocuments(int noOfFailedDocuments) {
		this.noOfFailedDocuments = noOfFailedDocuments;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
}
