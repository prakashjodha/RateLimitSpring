package com.jodha.ratelimit.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jodha.ratelimit.dto.ApiDto;
import com.jodha.ratelimit.dto.BasicRateLimiter;
import com.jodha.ratelimit.dto.UserApiTokenDto;
import com.jodha.ratelimit.dto.UserDto;
import com.jodha.ratelimit.service.RateLimitService;
import com.jodha.ratelimit.service.UserApiService;
import com.jodha.ratelimit.util.APIMessages;
import com.jodha.ratelimit.util.RateConstant;

@Component
public class RateLimitHandler implements HandlerInterceptor{
	
	Logger logger= LoggerFactory.getLogger(RateLimitHandler.class);
	List<String> skipURL=Arrays.asList(new String[]{"/error","/apiError"});

	@Autowired UserApiService userApiService;
	@Autowired RateLimitService rateLimitService;
	
	 @Value("${ratelimit.default.refill.minutes}")
	 private int defultRefillInterval;


	 @Override
	   public boolean preHandle(
	      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		 String apiUrl=request.getServletPath();
		 Boolean isAllowed=true;
		 if(skipURL.contains(apiUrl)) {
			 return isAllowed;
		 }
		 long userId = request.getHeader(RateConstant.USER_ID)==null?0:Long.valueOf(request.getHeader(RateConstant.USER_ID));
		 UserDto user = userApiService.getUser(userId);
		 if(user.isEmpty()) {
			 response.setHeader(APIMessages.ERROR_MSG,APIMessages.USER_ID_MANDATORY);
			 response.sendError(HttpStatus.BAD_REQUEST.value());
			 return !isAllowed;
		 }
		 ApiDto apiObj = userApiService.getAPIObj(apiUrl);
		 //logger.error(" apiObj "+apiUrl);
		 if(apiObj.isEmpty()) {
			 response.setHeader(APIMessages.ERROR_MSG,APIMessages.API_NOT_CONFIGURED );
			 response.sendError(HttpStatus.BAD_REQUEST.value());
			 return !isAllowed;
		 }
		 UserApiTokenDto userApiToken = userApiService.getUserApiToken(RateConstant.KEY_FUNCTION.apply(userId, apiObj.getApiId()));
		 BasicRateLimiter rateLimit = rateLimitService.getRateLimit(userApiToken.getKey());
		 isAllowed=rateLimit.tryAcquire(); //Trying to get bucket token
		 if (!isAllowed) {
			 response.setHeader(APIMessages.ERROR_MSG,HttpStatus.TOO_MANY_REQUESTS.name());
			 response.addHeader(APIMessages.REFILL_INTERVAL, String.valueOf(defultRefillInterval));
	            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value());
	        }
	        return isAllowed;	      
	   }

}
