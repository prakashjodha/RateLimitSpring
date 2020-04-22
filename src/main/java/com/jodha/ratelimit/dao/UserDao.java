package com.jodha.ratelimit.dao;

import org.springframework.data.repository.CrudRepository;

import com.jodha.ratelimit.dto.UserDto;

public interface UserDao extends CrudRepository<UserDto, Long> {

}