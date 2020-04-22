package com.jodha.ratelimit.cache;

import java.util.Optional;

import com.jodha.ratelimit.dto.ApiDto;
import com.jodha.ratelimit.dto.UserApiTokenDto;
import com.jodha.ratelimit.dto.UserDto;

public interface UserApiCache {

	Optional<ApiDto> getAPIObj(String apiUrl);

	Optional<UserDto> getUser(long userId);

	Optional<UserApiTokenDto> getUserApiToken(String userAPiKey);

	
}
