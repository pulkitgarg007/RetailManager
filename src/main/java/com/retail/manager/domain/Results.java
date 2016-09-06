package com.retail.manager.domain;

/**
 * Results domian class
 * @author Pulkit Garg
 *
 */
public class Results {
	private String place_id;

	private String formatted_address;

	private String[] types;

	private Geometry geometry;

	public String getPlace_id() {
		return place_id;
	}

	public void setPlace_id(String place_id) {
		this.place_id = place_id;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "ClassPojo [place_id = " + place_id + ", formatted_address = " + formatted_address + ", types = "
				+ types + ", geometry = " + geometry + "]";
	}
}
