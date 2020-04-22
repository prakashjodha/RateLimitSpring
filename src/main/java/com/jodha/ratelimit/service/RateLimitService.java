package com.jodha.ratelimit.service;

import com.jodha.ratelimit.dto.BasicRateLimiter;

public interface RateLimitService {

	BasicRateLimiter getRateLimit(String userApiKey);

}
