package com.jodha.ratelimit.dto;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_detials")
public class UserDto  implements  Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7121015098401752279L;
	
	@Id
	@Column("user_id")
	private long userId;
	
	@Column("first_Name")
	private String firstName;
	
	@Column("last_Name")
	private String lastName;
	
	@Column("status")
	private int status;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public boolean isEmpty() {
		return this.userId==0?true:false;
	}
	
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", status="
				+ status + "]";
	}
	

}
