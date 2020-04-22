package com.jodha.ratelimit.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jodha.ratelimit.service.UserApiService;
import com.jodha.ratelimit.util.APIMessages;

@RestController
@RequestMapping("api/v1/")
public class ApiController {
	Logger logger= LoggerFactory.getLogger(ApiController.class);
	
	@Autowired UserApiService userService;
	
	 @RequestMapping(value = "developers", method = RequestMethod.GET)
	 public String getDevelopers() {
		 logger.info("data"+userService.getUser(1));
		 return APIMessages.DEVELOPERS;
	 }

	 @RequestMapping(value = "organizations", method = RequestMethod.GET)
	 public String getorganizations() {
		 return APIMessages.ORGANIZATIONS;
	 }
	 
	 @RequestMapping(value = "test", method = RequestMethod.GET)
	 public String test() {
		 return APIMessages.TEST;
	 }
}
