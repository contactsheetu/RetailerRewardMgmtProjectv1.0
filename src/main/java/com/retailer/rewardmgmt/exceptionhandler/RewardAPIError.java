package com.retailer.rewardmgmt.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RewardAPIError {

	private String debugMessage;

	private String message;

	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;

	private RewardAPIError() {
		timestamp = LocalDateTime.now();
	}

	public RewardAPIError(HttpStatus status) {
		this();
		this.status = status;
	}

	public RewardAPIError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getMessage();
	}

	public RewardAPIError(HttpStatus status, String message, String exceptionMessage) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = exceptionMessage;
	}	
	
	public RewardAPIError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getMessage();
	}


	public String getDebugMessage() {
		return debugMessage;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
}
