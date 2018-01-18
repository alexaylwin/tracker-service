package com.nova.services.model.cloudant;

import java.util.List;

import com.nova.services.model.cloudant.Activity;
import com.nova.services.model.cloudant.ActivityRecord;

import lombok.Data;

@Data
public class User {
	private int id;
	private int user_id;
	private String user_name;
	private List<Activity> activity_list;
	private List<ActivityRecord> recorded_activities;

}
