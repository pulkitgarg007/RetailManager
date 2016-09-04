package com.retail.manager.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class GoogleResponse {
	private List<GoogleResponse.Geometry> results;
	private String status;
	
	public List<GoogleResponse.Geometry> getResults() {
		return results;
	}

	public void setResults(List<GoogleResponse.Geometry> results) {
		this.results = results;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static class Geometry{
		private GoogleResponse.Location location;
		
		public GoogleResponse.Location getLocation() {
			return location;
		}

		public void setLocation(GoogleResponse.Location location) {
			this.location = location;
		}
		
	}
	
	public static class Location{
		private String lat;
		private String lng;
		
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
	}

}
