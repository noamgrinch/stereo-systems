package com.grinch.SpeakersService.BusinessLogic;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Embeddable
@Data
public class Dimensions implements java.io.Serializable{

	private static final long serialVersionUID = -5681607171540754194L;
	@JsonProperty("height")
	@NotNull
	@Max(1000)
	@Min(1)
	private float height;
	@JsonProperty("width")
	@NotNull
	@Max(1000)
	@Min(1)
	private float width;
	@JsonProperty("depth")
	@NotNull
	@Max(1000)
	@Min(1)
	private float depth;

}
