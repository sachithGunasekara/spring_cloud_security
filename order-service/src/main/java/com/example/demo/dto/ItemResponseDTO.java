package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

public class ItemResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -935658056807527982L;
	List<String> itemList;

	public List<String> getItemList() {
		return itemList;
	}

	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}

}
