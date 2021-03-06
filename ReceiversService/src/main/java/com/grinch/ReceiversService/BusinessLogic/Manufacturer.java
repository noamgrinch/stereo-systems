package com.grinch.ReceiversService.BusinessLogic;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grinch.ReceiversService.BusinessLogic.Utils.ValidManufacturerReference;

import lombok.Data;
@Embeddable
@ValidManufacturerReference
@Data
public class Manufacturer implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 192482548894420474L;
	
	@JsonProperty("id")
	@Min(1)
	private Long id;
	@JsonProperty("name")
	private String name;
	
	public Manufacturer(Long id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public Manufacturer() {
		
	}

}
