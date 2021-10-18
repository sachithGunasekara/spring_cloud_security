package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class ItemListResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3845959032181118522L;
	List<String> itemList;
	
	

	public ItemListResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ItemListResponseDTO(List<String> itemList) {
		super();
		this.itemList = itemList;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}

}
