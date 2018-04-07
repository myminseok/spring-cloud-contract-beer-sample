package com.example.beerclient;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWireMock
@AutoConfigureStubRunner(workOffline=true,
ids="com.example:beer-server:+:stubs:8081")
public class BeerClientApplicationTests {

	@Test
	public void old_enough_mock() {

		String requestJson = "{\"age\":\"21\"}";
		String responseJson = "{\"status\":\"OK\"}";


		WireMock.stubFor(
			WireMock.post("/check").withHeader("Content-Type", equalTo("application/json"))
					.withRequestBody(equalToJson(requestJson))
				.willReturn(WireMock.aResponse().withStatus(200).withBody(responseJson))
		);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

		RestTemplate restTemplate =  new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8080/check", httpEntity, String.class);

		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals(responseJson, responseEntity.getBody());

	}


	@Test
	public void old_enough_stubs() {

		String requestJson = "{\"age\":\"21\"}";
		String responseJson = "{\"status\":\"OK\"}";


		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> httpEntity = new HttpEntity<>(requestJson, httpHeaders);

		RestTemplate restTemplate =  new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8081/check", httpEntity, String.class);

		Assert.assertEquals(200, responseEntity.getStatusCodeValue());
		Assert.assertEquals(responseJson, responseEntity.getBody());

	}

}
