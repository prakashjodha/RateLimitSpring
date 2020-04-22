package com.jodha.ratelimit.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.jodha.ratelimit.util.RateConstant;

@Table("user_api_rule")
public class UserApiTokenDto {
	
	
	@Id
	@Column("user_api_id")
	private long userApiId;
	
	@Column("user_id")
	private long userId;
	@Column("api_id")
	private long apiId;
	@Column("status")
	private int status;
	@Column("token")
	private int token;
	
	public String getKey() {
		return RateConstant.KEY_FUNCTION.apply(userId, apiId);
	}
	
	public boolean isEmpty() {
		return this.userApiId==0?true:false;
	}
	
	public long getUserApiId() {
		return userApiId;
	}

	public void setUserApiId(long userApiId) {
		this.userApiId = userApiId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getApiId() {
		return apiId;
	}

	public void setApiId(long apiId) {
		this.apiId = apiId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserApiTokenDto [userApiId=" + userApiId + ", userId=" + userId + ", apiId=" + apiId + ", status="
				+ status + ", token=" + token + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (apiId ^ (apiId >>> 32));
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserApiTokenDto other = (UserApiTokenDto) obj;
		if (apiId != other.apiId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
