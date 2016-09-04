package com.retail.manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ShopDetails {
	
	private String shopName;	
	private ShopAddress shopAddress;	
	private String shopLongitude;	
	private String shopLatitude;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public ShopAddress getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}
	public String getShopLongitude() {
		return shopLongitude;
	}
	public void setShopLongitude(String shopLongitude) {
		this.shopLongitude = shopLongitude;
	}
	public String getShopLatitude() {
		return shopLatitude;
	}
	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	
}
