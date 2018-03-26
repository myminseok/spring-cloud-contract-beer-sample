package com.example.beerserver;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseClass {

	@Autowired BeerController beerController;
	@Before
	public void contextLoads() {
		RestAssuredMockMvc.standaloneSetup(beerController);
	}

}
