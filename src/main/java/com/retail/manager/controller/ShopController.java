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
import com.retail.manager.exception.ValidationException;
import com.retail.manager.service.ShopService;
/**
 * This is a Controller class of Application which exposes endpoints
 * @author Pulkit Garg
 *
 */
@RestController
@RequestMapping("/shop")
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping(value="/shop-details", method = RequestMethod.POST)
	public ResponseEntity<?> addShop(@RequestBody ShopDetails shopDetails) {
		//service call to add shop details
		String response =  shopService.addShop(shopDetails);
		if (response.equals("OK")){
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}else{
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/shop-address", method = RequestMethod.GET)
	public ResponseEntity<?> searchShop(@RequestParam(value = "longitude") String customerLongitude,
			@RequestParam(value = "latitude") String customerLatitude) {
		//Checking if any of the parameter is null then throw exception
		if(customerLongitude== null || customerLatitude == null){
			throw new ValidationException("Search Criteria is Empty");
		}
		//service call to search shop 
		ShopDetails shopDetails = shopService.searchShop(customerLongitude, customerLatitude);
		return new ResponseEntity<>(shopDetails, HttpStatus.OK);
	}
	
	/**
	 * Method to handle Exception
	 * @param exception
	 * @return string
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public String handleException(Exception exception) {
		return exception.getMessage();
	}
	
	/**
	 * Method to handle Custom validation Exception
	 * @param exception
	 * @return string
	 */
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(BAD_REQUEST)
	@ResponseBody
	public String handleValidationException(ValidationException exception) {
		
		return exception.getMessage();
	}

	public ShopService getShopService() {
		return shopService;
	}

	public void setShopService(ShopService shopService) {
		this.shopService = shopService;
	}

}
