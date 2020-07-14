package com.grinch.SpeakersService.BusinessLogic;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Element {
	
	@JsonProperty("elementType")
	private ElementType elementType;
	@JsonProperty("diameter")
	private Float diameter;
}
