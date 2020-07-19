package com.grinch.LoggerService.BusinessLogic.Entities;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@Data
@Document(collection = "logs")
public class Log {
	
	@Id
	private Long id;
	private Timestamp logEntryDate;
	private Timestamp logCreateDate;
	private String application;
	private String traceId;
	private String logLevel;
	private String className;
	private String Message;

}
