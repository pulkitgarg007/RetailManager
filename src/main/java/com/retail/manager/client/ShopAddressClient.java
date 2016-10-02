package com.retail.manager.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.retail.manager.domain.GoogleResponse;
import com.retail.manager.domain.ShopDetails;
/**
 * This class is responsible to make a call to google API and fetching Longitude and Latitude for a 
 * given postal code, shop name and number
 * @author Pulkit Garg
 *
 */
@Component
public class ShopAddressClient {
	
	@Autowired
	private RestTemplate restTemplate;

    @Value("${google.geocoding.url}")
    private String googleGeocodingURL;
    
    public String getGoogleGeocodingURL() {
		return googleGeocodingURL;
	}

	public void setGoogleGeocodingURL(String googleGeocodingURL) {
		this.googleGeocodingURL = googleGeocodingURL;
	}

	private final String googleKey = "AIzaSyDuSvm27mjmj11Y6fLeyT6gzBGpH3fJ3Vo";
    
    
    public GoogleResponse getLongitudeLatitude(ShopDetails shopDetails) {
        GoogleResponse response;
        //building an url of google api
        String url = googleGeocodingURL + "address="+shopDetails.getShopAddress().getNumber()+","+shopDetails.getShopName()+
        		"&components=postal_code:"+shopDetails.getShopAddress().getPostCode()+"&key="+googleKey;
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
