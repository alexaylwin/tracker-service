package com.nova.services.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ActivityRecord {

	@JsonProperty("activityId")
	private int activityId;
	
	@JsonProperty("startTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss a")
	private LocalDateTime startTime;
	
	@JsonProperty("endTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss a")
	private LocalDateTime endTime;
	
	public ActivityRecord(int activityId, LocalDateTime startTime, LocalDateTime endTime) {
		this.activityId = activityId;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public ActivityRecord() {};
	
}
