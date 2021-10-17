package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dto.ItemResponseDTO;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

	@Autowired
	private WebClient webClient;
	
	@RequestMapping(method = RequestMethod.GET, path = "/place", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> placeOrder() {
		ItemResponseDTO response = webClient.get().uri("http://localhost:8083/item-service/item/get").retrieve().bodyToMono(ItemResponseDTO.class).block();
		Map<String, Object> res = new HashMap<>();
		res.put("status", "success");
		res.put("message", "order placed");
		res.put("orderItems", response.getItemList());
		return ResponseEntity.ok(res);
	}
}
