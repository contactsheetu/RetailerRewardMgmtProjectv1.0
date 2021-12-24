package com.retailer.rewardmgmt.exceptionhandler;

import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RewardAPIExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LogManager.getLogger(RewardAPIExceptionHandler.class.getName());

	private ResponseEntity<Object> buildResponseEntity(RewardAPIError RewardAPIError) {
		return new ResponseEntity<>(RewardAPIError, RewardAPIError.getStatus());
	}

	@ExceptionHandler(value = { InternalServerError.class })
	public ResponseEntity<Object> handleInternalServerError(InternalServerError ex) {
		LOGGER.error("InternalServerError: ", ex.getMessage());
		String error = "InternalServerError encountered. Please refer to Readme for API specifications.";
		return buildResponseEntity(new RewardAPIError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	public ResponseEntity<Object> handleIllegalArgumentException(InternalServerError ex) {
		LOGGER.error("IllegalArgumentException: ", ex.getMessage());
		String error = "Argument didnt match the required format. Please refer to Readme for API specifications.";
		return buildResponseEntity(new RewardAPIError(HttpStatus.INTERNAL_SERVER_ERROR, error, ex));
	}
	
	@ExceptionHandler(value = { NumberFormatException.class })
	public ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex) {
		LOGGER.error("NumberFormatException: ", ex.getMessage());
		String error = "Invalid Format encountered. Please refer to Readme for API specifications.";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}	
	
	@ExceptionHandler(value = { HttpClientErrorException.NotFound.class })
	public ResponseEntity<Object> handle404NotFoundError(HttpClientErrorException.NotFound ex) {
		LOGGER.error("404NotFoundError: ", ex.getMessage());
		String error = "404NotFoundError encountered. Please refer to Readme for API specifications.";
		return buildResponseEntity(new RewardAPIError(HttpStatus.NOT_FOUND, error, ex));
	}

	@ExceptionHandler(value = { HttpClientErrorException.class })
	public ResponseEntity<Object> handleHttpClientError(HttpClientErrorException ex) {
		LOGGER.error("HttpClientError: ", ex.getMessage());
		String error = "HttpClientError encountered. Please refer to Readme for API specifications.";
		return buildResponseEntity(new RewardAPIError(HttpStatus.NOT_FOUND, error, ex));
	}

	@ExceptionHandler(value = { Custom400Exception.class })
	public ResponseEntity<Object> handleCustom400Exception(Custom400Exception ex) {
		LOGGER.error("HttpClientError: ", ex.getMessage());
		String error = ex.getMessage();
		return buildResponseEntity(new RewardAPIError(HttpStatus.NOT_FOUND, error, ex));
	}
	
	@ExceptionHandler(value = { InvalidInputException.class })
	public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
		LOGGER.error("Invalid Input Exception: ", ex.getMessage());
		String error = "Invalid Input Exception";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	ResponseEntity<Object> handleConstraintValidationException(ConstraintViolationException ex) {
		LOGGER.error("Constraint is voilated: ", ex.getMessage());
		String error = "Constraint is voilated";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception ex) {
		LOGGER.error("Exception: ", ex.getMessage());
		String error = "Exception encountered. Please refer to Readme for API specifications.";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));

	}

	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<Object> handleRuntimeException(Exception ex) {
		LOGGER.error("Exception: ", ex.getMessage());
		String error = "Snap! Exception encountered. Please refer to Readme for API specifications.";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));

	}	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Malformed JSON request";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "No handler Found";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "Internal Exception";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Missing Request Param";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Method Argument Not Valid";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String error = "Missing Path Variable";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String error = "Method not supported";
		return buildResponseEntity(new RewardAPIError(HttpStatus.BAD_REQUEST, error, ex));
	}

}
