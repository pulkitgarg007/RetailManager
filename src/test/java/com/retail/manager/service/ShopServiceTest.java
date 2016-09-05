package com.retail.manager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.retail.manager.client.ShopAddressClient;
import com.retail.manager.domain.Geometry;
import com.retail.manager.domain.GoogleResponse;
import com.retail.manager.domain.Location;
import com.retail.manager.domain.Results;
import com.retail.manager.domain.ShopDetails;
import com.retail.manager.repository.ShopRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest {

	@InjectMocks
	private ShopService shopService;

	@Mock
	private ShopRepository shopRepository;

	@Mock
	private ShopAddressClient shopAddressClient;

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
