package com.retail.manager.repository;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.retail.manager.builder.ShopAddressBuilder;
import com.retail.manager.builder.ShopDetailsBuilder;
import com.retail.manager.domain.ShopAddress;
import com.retail.manager.domain.ShopDetails;

@RunWith(MockitoJUnitRunner.class)
public class ShopRepositoryTest {
	
	@InjectMocks
	private ShopRepository shopRepository;
	
	@Mock
	private Set<ShopDetails> shopDetailsList = new HashSet<ShopDetails>();
	
	@Test
	public void shouldAddShopDetailsToShopDetailsList(){
		ShopAddress shopAddress = new ShopAddressBuilder().withNumber("123").withPostCode("412307").build();
		ShopDetails shopDetails = new ShopDetailsBuilder().withShopLatitude("75.234567")
				.withShopLongitude("95.65437").withShopName("Vijay Sales").withShopAddress(shopAddress).build();
		shopRepository.addShop(shopDetails);
		for (ShopDetails shopDetails1 :shopDetailsList){
			assertEquals(shopDetails1.getShopLatitude(), "75.234567");
			assertEquals(shopDetails1.getShopLongitude(), "75.234567");
			assertEquals(shopDetails1.getShopName(), "Vijay Sales");
		}
	}
	
}
