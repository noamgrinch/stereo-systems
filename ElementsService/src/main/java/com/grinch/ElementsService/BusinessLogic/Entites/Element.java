package com.grinch.ElementsService.BusinessLogic.Entites;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grinch.ElementsService.BusinessLogic.ElementType;
import com.grinch.ElementsService.BusinessLogic.Manufacturer;

import lombok.Data;


@Entity
@Table(name="Elements")
@Data
public class Element implements java.io.Serializable{

	static final long serialVersionUID = 2609712647054547105L;
	@Column(name = "ElementID")
	@JsonProperty("id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty("name")
	@Column(name = "Name")
	@Length(max=100,min=1)
	private String name;
	@JsonProperty("elementType")
	@Enumerated(EnumType.STRING)
	@Column(name="ElementType")
	private ElementType elementType;
	@JsonProperty("diameter")
	@Column(name="Diameter")
	@Min(0)
	@Max(10000)
	private Float diameter;
	@NotNull
	@Embedded
	@AttributeOverrides({
		@AttributeOverride( name = "id", column = @Column(name = "ManufacturerId")),
		@AttributeOverride( name = "name", column = @Column(name = "ManufacturerName"))
	})
	private Manufacturer manufacturerReference;
}
