package com.jodha.ratelimit.cache.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jodha.ratelimit.cache.UserApiCache;
import com.jodha.ratelimit.dao.APIDao;
import com.jodha.ratelimit.dao.UserApiDao;
import com.jodha.ratelimit.dao.UserDao;
import com.jodha.ratelimit.dto.ApiDto;
import com.jodha.ratelimit.dto.UserApiTokenDto;
import com.jodha.ratelimit.dto.UserDto;
import com.jodha.ratelimit.util.RateConstant;

@Component
public class UserApiCacheImpl implements UserApiCache {
	
	private Map<Long,UserDto> userCache=new ConcurrentHashMap<>();
	private Map<String,ApiDto> apiCache=new ConcurrentHashMap<>();
	private Map<String,UserApiTokenDto> userApiCache=new ConcurrentHashMap<>();
	
	@Autowired APIDao apidao;
	@Autowired UserDao userdao;

	@Autowired UserApiDao userApidao;

	
	@PostConstruct
	private void init() {
		
		apidao.findAll().forEach(dto->{
			if(dto.getStatus()==RateConstant.ACTIVE) {
				apiCache.put(dto.getApiUrl(), dto);
			}
		});
		userdao.findAll().forEach(dto->{
			if(dto.getStatus()==RateConstant.ACTIVE) {
				userCache.put(dto.getUserId(), dto);
			}
		});
		userApidao.findAll().forEach(dto->{
			if(dto.getStatus()==RateConstant.ACTIVE) {
				userApiCache.put(dto.getKey(), dto);
			}
		});
	}
	
	@Override
	public Optional<UserDto> getUser(long userId) {
		return Optional.ofNullable(userCache.get(userId));
	}
	
	@Override
	public Optional<ApiDto> getAPIObj(String apiUrl) {
		return Optional.ofNullable(apiCache.get(apiUrl));
	}
	
	@Override
	public Optional<UserApiTokenDto> getUserApiToken(String  userAPiKey) {
		return Optional.ofNullable(userApiCache.get(userAPiKey));
	}

}
