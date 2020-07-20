package com.grinch.SpeakersService.BusinessLogic.Entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.grinch.SpeakersService.BusinessLogic.ElementType;

@Entity
@Table(name="Elements")
public class Element implements java.io.Serializable{

	static final long serialVersionUID = 2609712647054547105L;
	@Column(name = "ElementID")
	@JsonProperty("id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonProperty("elementType")
	@Enumerated(EnumType.STRING)
	@Column(name="ElementType")
	private ElementType elementType;
	@JsonProperty("diameter")
	@Column(name="Diameter")
	private Float diameter;
	@ManyToOne
	@JoinColumn(name="SpeakerID", nullable=false)
	private Speaker speaker;
}
