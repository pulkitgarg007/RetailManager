package com.retail.manager.service;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.manager.client.ShopAddressClient;
import com.retail.manager.domain.GoogleResponse;
import com.retail.manager.domain.ShopDetails;
import com.retail.manager.repository.IShopRepository;
import com.retail.manager.repository.ShopRepository;

/**
 * This is a Shop Service class which delegates the call to repository class
 * @author Pulkit Garg
 *
 */
@Service
public class ShopService {
	
	@Autowired
	private IShopRepository iShopRepository;
	
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
			
			
			for(int i=0; i<1000;i++){
				ShopDetails shopDetails1 = new ShopDetails();
			}
			//checking if duplicate shop details are added
			boolean duplicate = shopRepository.addShop(shopDetails);
			//iShopRepository.save(shopDetails);
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
	//	return shopRepository.searchShop(customerLongitude, customerLatitude);
		return null;
	}

	public IShopRepository getShopRepository() {
		return iShopRepository;
	}

	public void setShopRepository(IShopRepository iShopRepository) {
		this.iShopRepository = iShopRepository;
	}

	public ShopAddressClient getShopAddressClient() {
		return shopAddressClient;
	}

	public void setShopAddressClient(ShopAddressClient shopAddressClient) {
		this.shopAddressClient = shopAddressClient;
	}
	
	public void setShopRepository(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

}
