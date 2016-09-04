package com.retail.manager.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.retail.manager.domain.ShopDetails;
import com.retail.manager.service.ShopService;

@RestController
@RequestMapping("/shops")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/addShop", method = RequestMethod.POST)
	public ResponseEntity<?> addShop(@RequestBody ShopDetails shopDetails) {
		String response =  shopService.addShop(shopDetails);
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value="/searchShop", method = RequestMethod.POST)
	public ResponseEntity<?> searchShop(@RequestParam(value = "customerLongitude") String customerLongitude,
			@RequestParam(value = "customerLatitude") String customerLatitude) {
		ShopDetails shopDetails = shopService.searchShop(customerLongitude, customerLatitude);
		return new ResponseEntity<>(shopDetails, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public int handleException(Exception exception) {
		return BAD_REQUEST.value();
	}

	public ShopService getShopService() {
		return shopService;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}

}
