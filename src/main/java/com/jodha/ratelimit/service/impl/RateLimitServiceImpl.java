package com.jodha.ratelimit.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jodha.ratelimit.dto.BasicRateLimiter;
import com.jodha.ratelimit.dto.UserApiTokenDto;
import com.jodha.ratelimit.service.RateLimitService;
import com.jodha.ratelimit.service.UserApiService;

@Service
public class RateLimitServiceImpl implements RateLimitService {
	 private Map<String, BasicRateLimiter> limiters = new ConcurrentHashMap<>(); 
	 @Autowired UserApiService userApiService;
	 
	 @Value("${ratelimit.defult.tokens}")
	 private int defultTokens;
	 
	 @Value("${ratelimit.default.refill.minutes}")
	 private int defultRefillInterval;
	 
	 private ScheduledExecutorService refillExecutor;
	 
	 @PostConstruct
	 private void intitSchedular() {
		 int totalAvailProcessor = Runtime.getRuntime().availableProcessors();
		 this.refillExecutor = Executors.newScheduledThreadPool(totalAvailProcessor);
		 refillExecutor.scheduleWithFixedDelay(refillingThread(), 0, defultRefillInterval, TimeUnit.MINUTES);
	 }
	 
	 public Runnable refillingThread() {
		 return ()->{
			 limiters.values().forEach(rateLimiter->rateLimiter.fillToken());
		 };
	 }
	 @Override
	public BasicRateLimiter getRateLimit(String userApiKey) {
		BasicRateLimiter rateLimit=limiters.get(userApiKey);
		if(rateLimit==null) {
			synchronized(userApiKey.intern()) {
                // double-checked locking to avoid multiple-reinitializations
                if (limiters.containsKey(userApiKey)) {
                    return limiters.get(userApiKey);
                }
                 
                rateLimit = createRateLimiter(userApiKey);
                 
                limiters.put(userApiKey, rateLimit);
            }
		}
		return rateLimit;
	}
	
	private BasicRateLimiter createRateLimiter(String key) {
		UserApiTokenDto userApiToken = userApiService.getUserApiToken(key);
		int  maxToken = userApiToken.isEmpty()?defultTokens:userApiToken.getToken();
		if(userApiToken.isEmpty()) {
			
		}
		BasicRateLimiter instance=BasicRateLimiter.getNewInstance(key, maxToken);
		return instance;
	}

}
