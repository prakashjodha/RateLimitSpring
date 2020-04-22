package com.jodha.ratelimit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jodha.ratelimit.cache.UserApiCache;
import com.jodha.ratelimit.dto.ApiDto;
import com.jodha.ratelimit.dto.UserApiTokenDto;
import com.jodha.ratelimit.dto.UserDto;
import com.jodha.ratelimit.service.UserApiService;
import com.jodha.ratelimit.util.RateConstant;

@Component
public class UserApiServiceImpl implements UserApiService {
	
	@Autowired UserApiCache userApiCache;
	
	@Override
	public UserDto getUser(long userId) {
		return userApiCache.getUser(userId).orElse(new UserDto());
	}
	
	@Override
	public ApiDto getAPIObj(String apiUrl) {
		return userApiCache.getAPIObj(apiUrl).orElse(new ApiDto());
	}
	
	@Override
	public UserApiTokenDto getUserApiToken(String  userAPiKey) {
		System.out.println("hhhh");
		return userApiCache.getUserApiToken(userAPiKey).orElse(new UserApiTokenDto());
	}
	
	public String getUserApiKey(long userId,long apiId) {
		return RateConstant.KEY_FUNCTION.apply(userId, apiId);
	}

}
