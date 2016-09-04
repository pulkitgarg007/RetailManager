package com.retail.manager.service;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.retail.manager.repository.ShopRepository;

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest {
	
	@InjectMocks
	private ShopService shopService;
	
	@Mock
	private ShopRepository shopRepository;
}
