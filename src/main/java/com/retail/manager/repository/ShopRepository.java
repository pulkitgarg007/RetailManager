package com.retail.manager.repository;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.retail.manager.domain.ShopDetails;

@Repository
public class ShopRepository {

	private Set<ShopDetails> shopDetailsList = new HashSet<ShopDetails>();
	
	public boolean addShop(ShopDetails shopDetails) {
		return this.shopDetailsList.add(shopDetails);
	}

	public ShopDetails searchShop(String customerLongitude, String customerLatitude) {
		return new ShopDetails();
	}

	public void setShopDetailsList(Set<ShopDetails> shopDetailsList) {
		this.shopDetailsList = shopDetailsList;
	}

	public Set<ShopDetails> getShopDetailsList() {
		return shopDetailsList;
	}

}
