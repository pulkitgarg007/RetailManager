package com.retail.manager.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retail.manager.client.ShopAddressClient;
import com.retail.manager.domain.GoogleResponse;
import com.retail.manager.domain.ShopDetails;
import com.retail.manager.repository.ShopRepository;

@Service
public class ShopService {
	
	@Autowired
	private ShopRepository shopRepository;
	
	@Autowired
	private ShopAddressClient shopAddressClient;

	public String addShop(ShopDetails shopDetails) {
		GoogleResponse response = shopAddressClient.getLongitudeLatitude(shopDetails);
		if(response.getStatus().equals("OK") && CollectionUtils.isNotEmpty(response.getResults())){
			shopDetails.setShopLatitude(response.getResults().get(0).getGeometry().getLocation().getLat());
			shopDetails.setShopLongitude(response.getResults().get(0).getGeometry().getLocation().getLng());
			boolean duplicate = shopRepository.addShop(shopDetails);
			if(!duplicate){
				return "DUPLICATE RECORD";
			}
			return "OK";
		}else{
			return "NOT FOUND";
		}
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
