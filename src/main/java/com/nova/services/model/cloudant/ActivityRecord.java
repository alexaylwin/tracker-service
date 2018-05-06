package com.nova.services.model.cloudant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRecord {
	private String id;
	private String start_time;
	private String end_time;

}
