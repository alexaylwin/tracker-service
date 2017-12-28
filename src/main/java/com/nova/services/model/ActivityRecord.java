package com.nova.services.model;

import java.time.Duration;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ActivityRecord {

	private int activityId;
	
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss a")
	private LocalDateTime startTime;
	
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss a")
	private LocalDateTime endTime;
	
	@JsonProperty
	private long duration;
	
	public ActivityRecord(int activityId, LocalDateTime startTime, LocalDateTime endTime) {
		this.activityId = activityId;
		this.startTime = startTime;
		this.endTime = endTime;
		Duration tempDuration = Duration.between(startTime, endTime); 
		this.duration = tempDuration.getSeconds();
	}
	
	public ActivityRecord() {};
	
}
