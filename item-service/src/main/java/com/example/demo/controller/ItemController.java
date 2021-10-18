package com.example.demo.controller;

import java.util.Arrays;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ItemListResponseDTO;

@RestController
@RequestMapping(path = "/item")
public class ItemController {

	@RequestMapping(path = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ItemListResponseDTO> getItems() {
		return ResponseEntity.ok(new ItemListResponseDTO(Arrays.asList(new String[] { "Item 1", "Item 2", "Item 3" })));
	}
}
