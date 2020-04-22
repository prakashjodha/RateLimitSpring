package com.jodha.ratelimit.util;

import java.util.function.BiFunction;

public interface RateConstant {
	
	int ACTIVE=1;
	int INACTIVE=0;
	String KEY_SEPARATOR=".";
	String USER_ID="userId";
	BiFunction<Long, Long, String> KEY_FUNCTION=(userId,apiId)->userId+RateConstant.KEY_SEPARATOR+apiId;

}
