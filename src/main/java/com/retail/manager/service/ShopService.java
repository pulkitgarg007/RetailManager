package com.retail.manager.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.manager.client.ShopAddressClient;
import com.retail.manager.domain.GoogleResponse;
import com.retail.manager.domain.ShopDetails;
import com.retail.manager.repository.ShopRepository;

/**
 * This is a Shop Service class which delegates the call to repository class
 * @author Pulkit Garg
 *
 */
@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ShopAddressClient shopAddressClient;
	
	private final static Logger logger = Logger.getLogger(ShopService.class);
	
	public String addShop(ShopDetails shopDetails) {
		
		//creating string variable to avoid sonar complains
		String returnResponse = null;
		logger.debug("inside addShop method");
		//call to get latitude and logitude from google Geocoding API
		GoogleResponse response = shopAddressClient.getLongitudeLatitude(shopDetails);
		
		//if results is not empty and status is ok
		if(response.getStatus().equals("OK") && CollectionUtils.isNotEmpty(response.getResults())){
			shopDetails.setShopLatitude(response.getResults().get(0).getGeometry().getLocation().getLat());
			shopDetails.setShopLongitude(response.getResults().get(0).getGeometry().getLocation().getLng());
			
			//checking if duplicate shop details are added
			boolean duplicate = shopRepository.addShop(shopDetails);
			
			//returning message on basis of output
			if(!duplicate){
				returnResponse = "DUPLICATE RECORD";
			}else{
				returnResponse =  "OK";
			}			
		}else{
			returnResponse = "NOT FOUND";
		}
		return returnResponse;
	}

	public ShopDetails searchShop(String customerLongitude, String customerLatitude) {
		return shopRepository.searchShop(customerLongitude, customerLatitude);
	}

	public ShopRepository getShopRepository() {
		return shopRepository;
	}

	public void setShopRepository(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	public ShopAddressClient getShopAddressClient() {
		return shopAddressClient;
	}

	public void setShopAddressClient(ShopAddressClient shopAddressClient) {
		this.shopAddressClient = shopAddressClient;
	}

}
