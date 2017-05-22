package com.westernacher.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.westernacher.model.rest.BaseRestModel;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler { 
	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<BaseRestModel> handleAccessDeniedException(Exception ex, WebRequest request) {  	
    	LOGGER.error("ERROR OCCURED IN OUR REST BACKEND", ex);
    	
    	BaseRestModel errorModel = new BaseRestModel();
    	errorModel.setErrorMessage(ex.getMessage());
    	errorModel.setcustomStatus("There was a problem with your request!");
    	errorModel.setHttpStatus(HttpStatus.BAD_REQUEST.name());
    	
        return new ResponseEntity<BaseRestModel>(
        		errorModel, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }   
}