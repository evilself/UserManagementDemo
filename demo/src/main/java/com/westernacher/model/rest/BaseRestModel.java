package com.westernacher.model.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class BaseRestModel implements Serializable {
	private static final long serialVersionUID = 6767476982780500433L;
	
	protected String customStatus;	
	protected String errorMessage;
	protected String httpStatus;
	
	@XmlElement
	public String getcustomStatus() {
		return customStatus;
	}
	
	public void setcustomStatus(String customStatus) {
		this.customStatus = customStatus;
	}
	
	@XmlElement
	public String getHttpStatus() {
		return httpStatus;
	}
	
	public void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	@XmlElement
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
