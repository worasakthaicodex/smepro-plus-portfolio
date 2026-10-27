package com.smepro.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class mainexception {

	@ExceptionHandler(value=Exception.class)
	public String handleException(HttpServletRequest request , Exception ex) {
		
		System.out.println("request" + request.getRequestURI() + "error" + ex);
		return "error";
	}
	
}
