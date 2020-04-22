package com.jodha.ratelimit.dao;

import org.springframework.data.repository.CrudRepository;

import com.jodha.ratelimit.dto.ApiDto;

public interface APIDao extends CrudRepository<ApiDto, Long> {

}