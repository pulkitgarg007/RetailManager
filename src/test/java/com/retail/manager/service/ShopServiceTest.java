package com.retail.manager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.retail.manager.builder.ShopAddressBuilder;
import com.retail.manager.builder.ShopDetailsBuilder;
import com.retail.manager.client.ShopAddressClient;
import com.retail.manager.domain.Geometry;
import com.retail.manager.domain.GoogleResponse;
import com.retail.manager.domain.Location;
import com.retail.manager.domain.Results;
import com.retail.manager.domain.ShopAddress;
import com.retail.manager.domain.ShopDetails;
import com.retail.manager.repository.IShopRepository;
import com.retail.manager.repository.ShopRepository;

/**
 * This is a test class for Shop Service
 * @author Pulkit Garg
 *
 */
@RunWith(MockitoJUnitRunner.class)

public class ShopServiceTest {

	@InjectMocks
	private ShopService shopService;

	@Mock
	private ShopRepository shopRepository;

	@Mock
	private ShopAddressClient shopAddressClient;
	
	@Mock
	private MongoTemplate mongoTemplate;
	
	@Mock
	private IShopRepository iShopRepository;

	@Test
	public void shouldReturnOKIfGoogleResponseIsOk() {
		GoogleResponse response = buildGoogleResponse();
		
		when(shopAddressClient.getLongitudeLatitude(any())).thenReturn(response);
		when(shopRepository.addShop(any())).thenReturn(true);
		String resp = shopService.addShop(new ShopDetails());
		assertEquals("OK",resp);

	}
	
	@Test
	public void shouldReturnDuplicateIfRecordAlreadyExists() {
		GoogleResponse response = buildGoogleResponse();
		
		when(shopAddressClient.getLongitudeLatitude(any())).thenReturn(response);
		when(shopRepository.addShop(any())).thenReturn(false);
		String resp = shopService.addShop(new ShopDetails());
		assertEquals("DUPLICATE RECORD",resp);

	}

	@Test
	public void shouldReturnNotFoundIfGoogleResponseIsNotOk() {
		GoogleResponse response = new GoogleResponse();
		Results results = new Results();
		List<Results> resultsList = new ArrayList<Results>();
		results = null;
		resultsList.add(results);
		
		response.setStatus("BadRequest");
		response.setResults(resultsList);
		when(shopAddressClient.getLongitudeLatitude(any())).thenReturn(response);
		String resp = shopService.addShop(new ShopDetails());
		assertEquals("NOT FOUND", resp);

	}
	
	/*@Test(enabled= false)
	public void shouldReturnShopdetailsObject() {
		ShopAddress shopAddress = new ShopAddressBuilder().withNumber("123").withPostCode("412307").build();
		ShopDetails shopDetails = new ShopDetailsBuilder().withShopLatitude("75.234567")
				.withShopLongitude("95.65437").withShopName("Vijay Sales").withShopAddress(shopAddress).build();
		when(shopRepository.searchShop(anyString(),anyString())).thenReturn(shopDetails);
		ShopDetails resp = shopService.searchShop("95.23244","23.3445");
		assertEquals(resp.getShopName(), "Vijay Sales");

	}*/
	
	private GoogleResponse buildGoogleResponse() {
		GoogleResponse response = new GoogleResponse();
		Results results = new Results();
		List<Results> resultsList = new ArrayList<Results>();
		
		Geometry geometry = new Geometry();

		Location loc = new Location();
		loc.setLat("76.34265");
		loc.setLng("97.344656");
		geometry.setLocation(loc);
		results.setGeometry(geometry);
		resultsList.add(results);
		response.setStatus("OK");
		response.setResults(resultsList);
		return response;
	}
}
