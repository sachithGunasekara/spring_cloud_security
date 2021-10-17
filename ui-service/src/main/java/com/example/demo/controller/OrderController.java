package com.example.demo.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.CreateOrderResponseDTO;

@RestController
@RequestMapping(path = "/order")
@CrossOrigin(origins = "http://localhost:4200/")
public class OrderController {

	@Autowired
	private WebClient webClient;

	@RequestMapping(method = RequestMethod.GET, path = "/place", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateOrderResponseDTO> placeOrder(@AuthenticationPrincipal JwtAuthenticationToken token) {
		JwtAuthenticationToken authentication = (JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
		String tokenValue = authentication.getToken().getTokenValue();
		String username = authentication.getToken().getClaimAsString("preferred_username");
		
		System.out.println(tokenValue);
		System.out.println(username);
		
		URI uri = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/order-service").path("/order/place").build()
				.toUri();
		CreateOrderResponseDTO dto = this.webClient.get()
				.uri(uri)
				.headers(header -> header.setBearerAuth(tokenValue))
				.retrieve()
				.bodyToMono(CreateOrderResponseDTO.class)
				.block();
		return ResponseEntity.ok(dto);
	}
}
