package com.retail.manager.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.retail.manager.domain.GoogleResponse;
import com.retail.manager.domain.ShopDetails;

@Component
public class ShopAddressClient {
	
	@Autowired
	private RestTemplate restTemplate;

    @Value("https://maps.googleapis.com/maps/api/geocode/json?")
    private String googleGeocodingURL;
    private final String googleKey = "AIzaSyDuSvm27mjmj11Y6fLeyT6gzBGpH3fJ3Vo";

    public GoogleResponse getLongitudeLatitude(ShopDetails shopDetails) {
        GoogleResponse response;
        String url = googleGeocodingURL + "address="+shopDetails.getShopName()+"&components=postal_code:"+shopDetails.getShopAddress().getPostCode()+"&key="+googleKey;
        response =  restTemplate.getForObject(url, GoogleResponse.class);
        return response;        
    }
	    
   	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
    
}
