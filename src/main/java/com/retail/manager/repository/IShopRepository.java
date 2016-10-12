package com.retail.manager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.retail.manager.domain.ShopDetails;

@Repository
public interface IShopRepository extends MongoRepository<ShopDetails, String>{
}	
