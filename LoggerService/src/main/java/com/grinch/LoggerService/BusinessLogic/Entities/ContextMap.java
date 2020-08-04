package com.grinch.LoggerService.BusinessLogic.Entities;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContextMap {

	@JsonProperty("traceId")
	private String traceId;
}
