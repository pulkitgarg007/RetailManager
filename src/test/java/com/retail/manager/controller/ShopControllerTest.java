package com.retail.manager.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.retail.manager.domain.ShopDetails;
import com.retail.manager.exception.ValidationException;
import com.retail.manager.service.ShopService;

@RunWith(MockitoJUnitRunner.class)
public class ShopControllerTest {
	
	@InjectMocks
	private ShopController shopController;
	
	@Mock
	private ShopService shopService;
	
	@Test
	public void shouldReturnOkIfShopDetailsAddedSuccessfully(){
		String expected = "OK";
		when(shopService.addShop(any())).thenReturn(expected);
		shopController.addShop(new ShopDetails());
		assertEquals(expected,"OK");
	}
	
	@Test
	public void shouldReturnNotFoundIfShopDetailsNotAddedSuccessfully(){
		String expected = "Not Found";
		when(shopService.addShop(any())).thenReturn(expected);
		shopController.addShop(new ShopDetails());
		assertEquals(expected,"Not Found");
	}
	
	@Test(expected=ValidationException.class)
	public void shouldThrowExceptionIfSearchCriteriaLatitudeIsNull(){
		shopController.searchShop("78.53673",null);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldThrowExceptionIfSearchCriteriaLongitudeIsNull(){
		shopController.searchShop(null,"13.45466");
	}
		
}
