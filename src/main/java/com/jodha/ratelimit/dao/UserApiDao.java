package com.jodha.ratelimit.dao;

import org.springframework.data.repository.CrudRepository;

import com.jodha.ratelimit.dto.UserApiTokenDto;

public interface UserApiDao extends CrudRepository<UserApiTokenDto, Long> {

}