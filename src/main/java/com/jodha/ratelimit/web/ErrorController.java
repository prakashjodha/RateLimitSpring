package com.jodha.ratelimit.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {
	Logger logger= LoggerFactory.getLogger(ErrorController.class);
	

	 @RequestMapping(value = "apiError", method = RequestMethod.GET)
	 public ResponseEntity<String> error(HttpServletRequest request,@RequestParam Integer httpCode) {
		return  ResponseEntity.status(httpCode).header("Custom-Header", "foo").build();
	 }
}
