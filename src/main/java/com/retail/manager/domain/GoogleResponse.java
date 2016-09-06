package com.retail.manager.domain;

import java.util.List;

/**
 * Google Response Domian Class
 * @author Pulkit Garg
 *
 */
public class GoogleResponse {
	private List<Results> results;
	private String status;
	
	
	public List<Results> getResults() {
		return results;
	}

	public void setResults(List<Results> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
