package com.grinch.OriginsService.BusinessLogic;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Origin implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4616585619457626111L;
	@JsonProperty("country")
	private String country;
	@JsonProperty("city")
	private String city;
}
