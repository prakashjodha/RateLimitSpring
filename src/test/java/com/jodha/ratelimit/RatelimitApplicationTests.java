package com.jodha.ratelimit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jodha.ratelimit.cache.UserApiCache;
import com.jodha.ratelimit.dto.BasicRateLimiter;
import com.jodha.ratelimit.service.RateLimitService;
import com.jodha.ratelimit.service.UserApiService;
import com.jodha.ratelimit.util.RateConstant;

@SpringBootTest
class RatelimitApplicationTests {
	
	  @Autowired
	  UserApiService userApiService;
	  
	  @Autowired
	  UserApiCache userApiCache;
	  
	  @Autowired
	  RateLimitService rateLimitService;
	  
	  
	  @Test
		public void contextLoads() {
		  assertThat(userApiService).isNotNull();
		}
	  
	  
	  @Test
		public void test1() throws InterruptedException {
		  System.out.println(rateLimitService);
		  String key = RateConstant.KEY_FUNCTION.apply(1l, 1l);
		  assertThat(userApiService.getUserApiToken(key)).isNotNull();
		  BasicRateLimiter rateLimit = rateLimitService.getRateLimit(key);
		  assertThat(rateLimit.getMaxTokens()).isEqualTo(rateLimit.getCurrentTokens());
		  while(rateLimit.tryAcquire()) {
			  
		  }
		  assertThat(0).isEqualTo(rateLimit.getCurrentTokens());
		  rateLimit.fillToken();
		  //Thread.sleep(60000);         //We can let executor fill the bucket but it takes 1 min pause.
		  assertThat(rateLimit.getMaxTokens()).isEqualTo(rateLimit.getCurrentTokens());
		}
	  
	  
	  @Test
		public void test2() throws InterruptedException {
		  System.out.println(rateLimitService);
		  String key = RateConstant.KEY_FUNCTION.apply(1l, 0l);
		  assertThat(userApiService.getUserApiToken(key)).isNotNull();
		  BasicRateLimiter rateLimit = rateLimitService.getRateLimit(key);
		  assertThat(1).isEqualTo(rateLimit.getMaxTokens());
		  assertThat(1).isEqualTo(rateLimit.getCurrentTokens());
		  rateLimit.tryAcquire();
		  assertThat(0).isEqualTo(rateLimit.getCurrentTokens());
		  rateLimit.fillToken();
		  //Thread.sleep(60000);      //We can let executor fill the bucket but it takes 1 min pause.
		  assertThat(rateLimit.getMaxTokens()).isEqualTo(rateLimit.getCurrentTokens());
		}

}
