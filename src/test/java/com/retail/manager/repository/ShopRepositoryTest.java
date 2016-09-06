package com.retail.manager.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.retail.manager.builder.ShopAddressBuilder;
import com.retail.manager.builder.ShopDetailsBuilder;
import com.retail.manager.domain.ShopAddress;
import com.retail.manager.domain.ShopDetails;

/**
 * This is a Junit test file for ShopRepository Class
 * @author Pulkit Garg
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ShopRepositoryTest {
	
	@InjectMocks
	private ShopRepository shopRepository;
		
	@Test
	public void shouldAddShopDetailsToShopDetailsList(){
		ShopAddress shopAddress = new ShopAddressBuilder().withNumber("123").withPostCode("412307").build();
		ShopDetails shopDetails = new ShopDetailsBuilder().withShopLatitude("75.234567")
				.withShopLongitude("95.65437").withShopName("Vijay Sales").withShopAddress(shopAddress).build();
		boolean firstRecord = shopRepository.addShop(shopDetails);
		assertTrue(firstRecord);
	}
	
	@Test
	public void shouldNotAddDuplicateShopDetailsToShopDetailsList(){
		ShopAddress shopAddress = new ShopAddressBuilder().withNumber("123").withPostCode("412307").build();
		ShopDetails shopDetails = new ShopDetailsBuilder().withShopLatitude("75.234567")
				.withShopLongitude("95.65437").withShopName("Vijay Sales").withShopAddress(shopAddress).build();
		boolean firstRecord = shopRepository.addShop(shopDetails);
		assertTrue(firstRecord);
		
		boolean duplicate = shopRepository.addShop(shopDetails);
		assertFalse(duplicate);
	}
	
	@Test
	public void shouldReturnNearestShopWhenSearchShopIsCalled(){
		populateShopdetailscollection();
		ShopDetails shopdetails = shopRepository.searchShop("94.234567","74.263940");
		assertEquals(shopdetails.getShopName(),"Vijay Sales");
		
	}
	
	@Test
	public void shouldReturnBlankShopWhenSearchShopIsCalledAndShopdetailsIsEmpty(){
		ShopDetails shopdetails = shopRepository.searchShop("94.234567","74.263940");
		assertEquals(shopdetails, null);
		
	}

	private void populateShopdetailscollection() {
		ShopAddress shopAddress1 = new ShopAddressBuilder().withNumber("123").withPostCode("412307").build();
		ShopDetails shopDetails1 = new ShopDetailsBuilder().withShopLatitude("75.234567")
				.withShopLongitude("95.65437").withShopName("Vijay Sales").withShopAddress(shopAddress1).build();
		ShopAddress shopAddress2 = new ShopAddressBuilder().withNumber("12").withPostCode("411007").build();
		ShopDetails shopDetails2 = new ShopDetailsBuilder().withShopLatitude("70.234567")
				.withShopLongitude("90.65437").withShopName("Chroma").withShopAddress(shopAddress2).build();
		shopRepository.addShop(shopDetails1);
		shopRepository.addShop(shopDetails2);
	}
	
}
