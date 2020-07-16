package com.grinch.ManufacturersService.BusinessLogic.Entites;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.grinch.ManufacturersService.BusinessLogic.Origin;


import lombok.Data;

@Entity
@Table(name = "Manufacturers")
@Data
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,property="@id", scope = Manufacturer.class)
public class Manufacturer implements java.io.Serializable{


	private static final long serialVersionUID = -8401395593441412050L;
	@Column(name = "ManufacturerID")
	@JsonProperty("id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Name")
	@JsonProperty("name")
	@NotNull
	@Length(min=1,max=40)
	private String name;
	@JsonProperty("origin")
	@Embedded
	@AttributeOverrides({
		@AttributeOverride( name = "country", column = @Column(name = "OriginCountry")),
		@AttributeOverride( name = "city", column = @Column(name = "OriginCity"))
	})
	private Origin origin; // Validate via a different microservice.

}
