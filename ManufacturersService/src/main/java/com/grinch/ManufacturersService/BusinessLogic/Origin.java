package com.grinch.ManufacturersService.BusinessLogic;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Origin {

	@JsonProperty("country")
	private String country;
	@JsonProperty("city")
	private String city;
}
