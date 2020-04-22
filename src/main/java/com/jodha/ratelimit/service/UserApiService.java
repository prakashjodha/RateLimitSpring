package com.jodha.ratelimit.service;

import com.jodha.ratelimit.dto.ApiDto;
import com.jodha.ratelimit.dto.UserApiTokenDto;
import com.jodha.ratelimit.dto.UserDto;

public interface UserApiService {
	/**
	 * This will return User against the given user ID
	 * IF no matching user found than new User with userId=0{@link #userId(UserDto)} will return.
	 * @param userId
	 * @return
	 */
	UserDto getUser(long userId);

	/**
	 * This will return UserApiTokenDto against the given userAPiKey
	 * IF no matching UserApiTokenDto found than new UserApiTokenDto with userApiId=0{@link #userApiId(UserApiTokenDto)} will return.
	 * @param userAPiKey
	 * @return
	 */
	UserApiTokenDto getUserApiToken(String userAPiKey);

	/**
	 * This will return ApiDto against the given apiUrl
	 * IF no matching ApiDto found than new ApiDto with apiId=0{@link #apiId(ApiDto)} will return.
	 * @param apiUrl
	 * @return
	 */
	ApiDto getAPIObj(String apiUrl);

}
