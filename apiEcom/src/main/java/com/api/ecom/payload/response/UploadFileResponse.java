package com.api.ecom.payload.response;

import com.api.ecom.models.UploadFile;

public class UploadFileResponse {

	public UploadFile uploadFile;
	public MessageResponse message;
	
	public UploadFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(UploadFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public MessageResponse getMessage() {
		return message;
	}
	public void setMessage(MessageResponse message) {
		this.message = message;
	}
	
	
}
