package com.grinch.SpeakersService.BusinessLogic.Entites;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grinch.SpeakersService.BusinessLogic.Utils.ValidManufacturerReference;

import lombok.Data;
@Entity
@Table(name = "ManufacturersReferences")
@ValidManufacturerReference
@Data
public class ManufacturerReference implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 192482548894420474L;
	
	@Column(name = "ReferenceID")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@Column(name = "ManufacturerID")
	@JsonProperty("id")
	@NotNull
	@Min(1)
	private Long manufacturerId;
	@JsonProperty("name")
	@Column(name = "ManufacturerName")
	@NotNull
	private String name;
	
	public ManufacturerReference(Long id, String name) {
		this.manufacturerId=id;
		this.name=name;
	}
	
	public ManufacturerReference() {
		
	}
}
