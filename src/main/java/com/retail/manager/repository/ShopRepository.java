package com.retail.manager.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.retail.manager.domain.ShopDetails;
import com.retail.manager.util.DistanceCalculator;

/**
 * This is a repository class which stores shop details in In Memory collection
 * @author Pulkit Garg
 */

@Repository
public class ShopRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	private Set<ShopDetails> shopDetailsList = new HashSet<ShopDetails>();
	
	public boolean addShop(ShopDetails shopDetails) {
		//add the shopDetails in shopDetailsList collection
		//will return true for a new entry and false for duplicate entry
		mongoTemplate.save(shopDetails);
		return this.shopDetailsList.add(shopDetails);
	}

	public ShopDetails searchShop(String customerLongitude, String customerLatitude) {
		
		//check to see if collection is empty
		if (CollectionUtils.isNotEmpty(shopDetailsList)){
			//creating a treemap to get sorted data
			TreeMap<Double, ShopDetails> distanceMap = new TreeMap< Double, ShopDetails>();
			for(ShopDetails shopDetails :shopDetailsList){
				
				//calculate distance of user search criteria from all the shops listed in collection
				Double distance = DistanceCalculator.getDistance(customerLatitude, customerLongitude, shopDetails);
				distanceMap.put(distance, shopDetails);
			}
			//returning the first value(shop details object) as lowest distance will be sorted to top and will be nearest to customer
			return distanceMap.firstEntry().getValue();
		}else{
			return null;
		}
	}

	public void setShopDetailsList(Set<ShopDetails> shopDetailsList) {
		this.shopDetailsList = shopDetailsList;
	}

	public Set<ShopDetails> getShopDetailsList() {
		return shopDetailsList;
	}

}
