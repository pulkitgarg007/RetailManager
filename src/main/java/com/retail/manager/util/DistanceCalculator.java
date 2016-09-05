package com.retail.manager.util;

import com.retail.manager.domain.ShopDetails;

public class DistanceCalculator {

	public static double getDistance(String latitude, String longitude, ShopDetails shopDetails) {
		
		double lat1 = Double.valueOf(shopDetails.getShopLatitude());
		double lng1 = Double.valueOf(shopDetails.getShopLongitude());
		
		double lat2 = Double.valueOf(latitude);
		double lng2 = Double.valueOf(longitude);

		return distance(lat1, lng1, lat2, lng2);
	}

	private static double distance(double lat1, double lng1, double lat2, double lng2) {

		double earthRadius = 6371; 

		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);

		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);

		double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2));

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		double dist = earthRadius * c;

		return dist; 
	}
}
