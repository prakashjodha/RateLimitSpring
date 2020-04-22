package com.jodha.ratelimit.dto;

/**
 * @author prakashjodha
 *
 */
public class BasicRateLimiter {
	
	 private String id;
	 private int maxTokens;
	 private int currentTokens;
	 private Object obj=new Object();
	 
	 private BasicRateLimiter() {}
	 
	 private BasicRateLimiter(int maxTokens,int currentTokens,String id) {
		 this.id=id;
		 this.maxTokens=maxTokens;
		 this.currentTokens=currentTokens;
	 }
	 
	 public static BasicRateLimiter getNewInstance(String id, int maxToken) {
		 BasicRateLimiter basicRateLimiter=new BasicRateLimiter(maxToken,maxToken,id);
		 return basicRateLimiter;
	 }
	 
	 public boolean tryAcquire() {
		 boolean isAcquied=false;
		 	synchronized (obj) {
		 		if(currentTokens>0) {
		 			isAcquied=true;
		 			currentTokens--;
		 		}
			}
	        return isAcquied;
	    }
	 public void fillToken() {
			synchronized (obj) {
				currentTokens=maxTokens;
			}
	 }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getMaxTokens() {
		return maxTokens;
	}
	public void setMaxTokens(int maxTokens) {
		this.maxTokens = maxTokens;
	}
	public int getCurrentTokens() {
		return currentTokens;
	}
	public void setCurrentTokens(int currentTokens) {
		this.currentTokens = currentTokens;
	}
	@Override
	public String toString() {
		return "BasicRateLimiter [id=" + id + ", maxTokens=" + maxTokens + ", currentTokens=" + currentTokens + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentTokens;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		BasicRateLimiter other = (BasicRateLimiter) obj;
		if (currentTokens != other.currentTokens)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	 
}
