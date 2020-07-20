package com.grinch.SpeakersService.BusinessLogic.Entites;

import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.grinch.SpeakersService.BusinessLogic.Manufacturer;
import com.grinch.SpeakersService.BusinessLogic.SpeakerType;
import com.grinch.SpeakersService.BusinessLogic.Way;
import com.grinch.SpeakersService.BusinessLogic.Utils.ValidSpeaker;

import lombok.Data;

@Entity
@Table(name = "Speakers")
@Data
@ValidSpeaker
public class Speaker implements java.io.Serializable{

	private static final long serialVersionUID = -2885796212065899715L;
	@Column(name = "SpeakerID")
	@JsonProperty("id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Name")
	@JsonProperty("name")
	@NotNull
	private String name;
	@Column(name = "Way")
	@JsonProperty("way")
	@Enumerated(EnumType.STRING)
	@NotNull
	private Way way;
	@Column(name = "SpeakerType")
	@JsonProperty("speakerType")
	@Enumerated(EnumType.STRING)
	@NotNull
	private SpeakerType speakerType;
	@NotNull
	@Embedded
	@AttributeOverrides({
		@AttributeOverride( name = "id", column = @Column(name = "ManufacturerId")),
		@AttributeOverride( name = "name", column = @Column(name = "ManufacturerName"))
	})
	private Manufacturer manufacturerReference;
	@Column(name = "MinFreqResponse")
	@JsonProperty("minFreqResponse")
	@NotNull
	@Min(0)
	private Integer minFreqResponse;
	@Column(name = "MaxFreqResponse")
	@JsonProperty("maxFreqResponse")
	@NotNull
	@Max(40000)
	private Integer maxFreqResponse;
	@Column(name = "Height")
	@JsonProperty("height")
	@NotNull
	@Max(1000)
	@Min(1)
	private Float height;
	@Column(name = "Width")
	@JsonProperty("width")
	@NotNull
	@Max(1000)
	@Min(1)
	private Float width;
	@Column(name = "Depth")
	@JsonProperty("depth")
	@NotNull
	@Max(1000)
	@Min(1)
	private Float depth;
	@Column(name = "Impedence")
	@JsonProperty("impedence")
	@NotNull
	@Max(40)
	@Min(0)
	private Integer impedence;
	@JsonProperty("elements")
	@OneToMany(mappedBy="speaker")
	private List<Element> elements;
}
