package com.grinch.ReceiversService.BusinessLogic.Entities;

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
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grinch.ReceiversService.BusinessLogic.Dimensions;
import com.grinch.ReceiversService.BusinessLogic.Manufacturer;
import com.grinch.ReceiversService.BusinessLogic.RecieverType;

import lombok.Data;

@Entity
@Table(name="Receivers")
@Data
public class Receiver implements java.io.Serializable{

	private static final long serialVersionUID = -6842860078213616634L;
	@Column(name = "ReceiverID")
	@JsonProperty("id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty("name")
	@Column(name = "Name")
	@Length(max=100,min=1)
	private String name;
	@JsonProperty("type")
	@Column(name = "Type")
	@Enumerated(EnumType.STRING)
	private RecieverType type;
	@NotNull
	@Embedded
	@AttributeOverrides({
		@AttributeOverride( name = "height", column = @Column(name = "Height")),
		@AttributeOverride( name = "width", column = @Column(name = "Width")),
		@AttributeOverride( name = "depth", column = @Column(name = "Depth"))
	})
	private Dimensions dimensions;
	@NotNull
	@Embedded
	@AttributeOverrides({
		@AttributeOverride( name = "id", column = @Column(name = "ManufacturerId")),
		@AttributeOverride( name = "name", column = @Column(name = "ManufacturerName"))
	})
	private Manufacturer manufacturerReference;

}
