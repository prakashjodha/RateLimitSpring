package com.jodha.ratelimit.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("api")
public class ApiDto {

	@Id
	@Column("api_id")
	private long apiId;
	
	@Column("api_url")
	private String apiUrl;
	
	@Column("status")
	private int status;

	public long getApiId() {
		return apiId;
	}

	public void setApiId(long apiId) {
		this.apiId = apiId;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean isEmpty() {
		return this.apiId==0?true:false;
	}

	@Override
	public String toString() {
		return "ApiDto [apiId=" + apiId + ", apiUrl=" + apiUrl + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (apiId ^ (apiId >>> 32));
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
		ApiDto other = (ApiDto) obj;
		if (apiId != other.apiId)
			return false;
		return true;
	}
	
	
	
}
