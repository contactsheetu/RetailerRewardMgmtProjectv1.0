package com.retailer.rewardmgmt.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.retailer.rewardmgmt.exceptionhandler.RewardAPIError;

@RestController
public class ErrorHandlerController implements ErrorController {
	
	 private static final String PATH = "/error";

	    private final ErrorAttributes errorAttributes;

	    public ErrorHandlerController(ErrorAttributes errorAttributes) {
	        this.errorAttributes = errorAttributes;
	    }

	@RequestMapping(value = PATH)
	public ResponseEntity<RewardAPIError> customError(WebRequest argRequest, HttpServletResponse argResponse) {
		
		Map<String, Object> tempErrorAttributes = this.errorAttributes.getErrorAttributes(argRequest, ErrorAttributeOptions.defaults());
		
		return new ResponseEntity<RewardAPIError>(new RewardAPIError(HttpStatus.BAD_REQUEST,
				"The link you followed may be broken, or the page may have been removed. Please refer to Readme for API specifications.",
				(String) tempErrorAttributes.get("message")), HttpStatus.BAD_REQUEST);
	}
	

	public String getErrorPath() {
		return PATH;
	}
}