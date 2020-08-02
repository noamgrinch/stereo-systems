package com.grinch.LoggerService.BusinessLogic.Entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
@Entity
@Table(name = "Logs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Log {
	
	@Column(name = "LogID")
	@JsonProperty("id")
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "LogEntryDate")
	@JsonProperty("logEntryDate")
	private Timestamp logEntryDate;
	@Column(name = "LogCreateDate")
	@JsonProperty("logCreateDate")
	private Timestamp logCreateDate;
	@Column(name = "Application")
	@JsonProperty("application")
	private String application;
	@Column(name = "TraceID")
	@JsonProperty("traceId")
	private String traceId;
	@Column(name = "LogLevel")
	@JsonProperty("level")
	private String logLevel;
	@Column(name = "ClassName")
	@JsonProperty("className")
	private String className;
	@Column(name = "Message")
	@JsonProperty("message")
	private String Message;
	@Transient
	@JsonProperty("loggerName")
	private String logger;
	@Transient
	@JsonProperty("contextMap")
	private ContextMap contextMap;

}
