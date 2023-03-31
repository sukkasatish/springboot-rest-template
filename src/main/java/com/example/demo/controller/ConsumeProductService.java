package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConsumeProductService {

	@Autowired
	RestTemplate template;
	
	@GetMapping("/consume/productList")
	String getProductList() {
		HttpHeaders headers = new HttpHeaders();
		List<MediaType> acceptableformat =new ArrayList<MediaType>();
		acceptableformat.add(MediaType.APPLICATION_JSON);		
		headers.setAccept(acceptableformat);
		
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		
		return template.exchange("http://localhost:8080/products", HttpMethod.GET,
				entity, String.class).getBody();
		
		
	}
}
