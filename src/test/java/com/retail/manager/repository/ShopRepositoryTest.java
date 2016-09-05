package com.retail.manager.repository;

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
	
}
