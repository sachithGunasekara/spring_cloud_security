package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
@CrossOrigin(origins = "http://localhost:4200/")
public class OrderController {

	@RequestMapping(method = RequestMethod.GET, path = "/place", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> placeOrder() {
		Map<String, String> res = new HashMap<>();
		res.put("status", "success");
		res.put("message", "order placed");
		return ResponseEntity.ok(res);
	}
}
