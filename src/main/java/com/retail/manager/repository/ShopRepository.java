package com.retail.manager.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.retail.manager.domain.ShopDetails;
import com.retail.manager.util.DistanceCalculator;

@Repository
public class ShopRepository {

	private Set<ShopDetails> shopDetailsList = new HashSet<ShopDetails>();
	
	public boolean addShop(ShopDetails shopDetails) {
		return this.shopDetailsList.add(shopDetails);
	}

	public ShopDetails searchShop(String customerLongitude, String customerLatitude) {
		if (CollectionUtils.isNotEmpty(shopDetailsList)){
			TreeMap<Double, ShopDetails> distanceMap = new TreeMap< Double, ShopDetails>();
			for(ShopDetails shopDetails :shopDetailsList){
				Double distance = DistanceCalculator.getDistance(customerLatitude, customerLongitude, shopDetails);
				distanceMap.put(distance, shopDetails);
			}		
			return distanceMap.firstEntry().getValue();
		}else{
			return new ShopDetails();
		}
	}

	public void setShopDetailsList(Set<ShopDetails> shopDetailsList) {
		this.shopDetailsList = shopDetailsList;
	}

	public Set<ShopDetails> getShopDetailsList() {
		return shopDetailsList;
	}

}
