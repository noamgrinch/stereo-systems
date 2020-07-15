package com.grinch.ManufacturersService.BusinessLogic.Entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "Manufacturers")
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
	@Column("Origin")
	@JsonProperty("origin")
	private

}
