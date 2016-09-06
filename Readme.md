Retail Manager exposes end points where people can register their shops. It internally uses Google Geocoding api to determine Longitude and Latitude of the shop and stores in In-Memory collection.
It also expose one search endpoint by which any one can search nearest shop by passing Longitude and Latitude in the request.

Anyone can clone the project in local using following url:-
https://github.com/pulkitgarg007/RetailManager.git

You need to execute gradle commands on your local.
gradle eclipse
gradle build

Gradle build takes care of running tasks like complileJava, complileTestJava, testClasses, test, build.

It is a spring boot application so Embedded Tomcat server included. To run the application in local use below command:-
gradle bootRun

By default application runs on port 8080. However you can change the port number in application.yml file.

Sample Endpoints:-
http://localhost:8080/RetailManager/shop/shop-details
Request Body:-
{  
   "shopName":"VijaySales",
   "shopAddress":{  
      "number":"12",
      "postCode":"411014"
   }
}

http://localhost:8080/RetailManager/shop/shop-address?longitude=73.8688812&latitude=18.5236714
Response Body :-
{
  "shopName": "Chroma",
  "shopAddress": {
    "number": "12",
    "postCode": "411028"
  },
  "shopLongitude": "73.9261587",
  "shopLatitude": "18.5149325"
}
