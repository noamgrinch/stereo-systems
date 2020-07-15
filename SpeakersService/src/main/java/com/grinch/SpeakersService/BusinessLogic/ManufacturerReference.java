package com.grinch.SpeakersService.BusinessLogic;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grinch.SpeakersService.BusinessLogic.Utils.ValidManufacturerReference;

import lombok.Data;
@Embeddable
@ValidManufacturerReference
@Data
public class ManufacturerReference implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 192482548894420474L;
	
	@JsonProperty("id")
	@NotNull
	@Min(1)
	private Long id;
	@JsonProperty("name")
	@NotNull
	private String name;
	
	public ManufacturerReference(Long id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public ManufacturerReference() {
		
	}
}
