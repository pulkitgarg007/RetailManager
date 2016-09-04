package com.retail.manager.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class ShopAddressClientTest {
	
	@InjectMocks
	private ShopAddressClient shopAddressClient;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Test
	public void shouldGetPlaceholderCostsForGivenSearchCriteria() throws Exception {
		/*PrimaryCostSearch primaryCostSearch = new PrimaryCostSearchBuilder().withSeason("HO").withYear("2015").build();
		List<String> assortedCCIdList = Lists.newArrayList("123452", "214124");
		List<PlaceholderCost> resource = Lists.newArrayList(new PlaceholderCost());
		PlaceholderCostEnvelope response = new PlaceholderCostEnvelope();
		response.setResource(resource);
		when(restTemplate.postForObject(anyString(), any(HttpEntity.class), any(Class.class))).thenReturn(response);
		List<PlaceholderCost> placeholderCosts = shopAddressClient.getPlaceHolderCosts(primaryCostSearch, assortedCCIdList);
		assertEquals(1, placeholderCosts.size());*/
	}
	
	@Test
	public void shouldReturnNullWhenPlaceholderCostsRequsetReturnsNullAsResponse() throws Exception {
	//	assertNull(shopAddressClient.getPlaceHolderCosts(new PrimaryCostSearch(), Lists.newArrayList("123452", "214124")));
	}
	
	public void shouldThrowExceptionWhenPlaceholderCostListRequestThrowsAnyException() throws Exception {
		/*PrimaryCostSearch primaryCostSearch = new PrimaryCostSearchBuilder().withSeason("HO").withYear("2015").build();
		List<String> assortedCCIdList = Lists.newArrayList("123452", "214124");
		when(restTemplate.postForObject(anyString(), any(HttpEntity.class), any(Class.class))).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		shopAddressClient.getPlaceHolderCosts(primaryCostSearch, assortedCCIdList);*/
	}
}
