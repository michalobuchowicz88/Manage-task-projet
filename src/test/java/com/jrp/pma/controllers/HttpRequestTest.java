package com.jrp.pma.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest (webEnvironment = WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void homePageReturnsVersionCorrectly_thenSuccess() {
		String renderHtml = this.restTemplate.getForObject("http://localhost:" + port +"/", String.class);
		assertEquals(renderHtml.contains("v1"), true);
	}

	
}