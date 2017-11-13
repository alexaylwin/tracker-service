package com.nova.services.model.gson;

import java.util.List;

import com.nova.services.model.gson.Activity;
import com.nova.services.model.gson.CompletedActivity;

import lombok.Data;

@Data
public class User {
	private int id;
	private int user_id;
	private String user_name;
	private List<Activity> activity_list;
	private List<CompletedActivity> completed_activities;

}
