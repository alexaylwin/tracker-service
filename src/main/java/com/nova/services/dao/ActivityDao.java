package com.nova.services.dao;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.client.api.model.Response;
import com.nova.services.config.CredentialConfig;
import com.nova.services.model.Activity;
import com.nova.services.model.ActivityRecord;
import com.nova.services.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ActivityDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityDao.class);
	
	@Autowired
	CredentialConfig credentials;
	
	@Value("${cloudant.url}")
	private String CLOUDANT_URL;
	
	private static final String CLOUDANT_DB = "tracker";
	
	CloudantClient client;
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
	
	//TODO: Implement caching for the database and user object
	
	@PostConstruct
	public void postConstruct() throws MalformedURLException {
		this.client = ClientBuilder.url(new URL(CLOUDANT_URL))
						.username(credentials.getCloudantUsername())
						.password(credentials.getCloudantPassword())
						.build();
	}
	
	public List<ActivityRecord> getRecordedActivities(LocalDateTime startTime, LocalDateTime endTime, int userId) { 
		Database db = this.client.database(CLOUDANT_DB, false);
		com.nova.services.model.cloudant.User user = db.findByIndex("{\"selector\":{\"user_id\":1}}", com.nova.services.model.cloudant.User.class).get(0);

		List<ActivityRecord> activityRecordList = user.getRecorded_activities().stream().map(gu -> {
			LocalDateTime start;
			LocalDateTime end;
			System.out.println(gu.getStart_time());
			start = LocalDateTime.parse(gu.getStart_time().trim(), dateFormatter);
			end = LocalDateTime.parse(gu.getEnd_time(), dateFormatter);
			return new com.nova.services.model.ActivityRecord(Integer.valueOf(gu.getId()), start, end);
		}).collect(Collectors.toList());
		
		//activityRecordList.stream().forEach(ca -> {System.out.println(ca.getActivityId());});
		
		return activityRecordList;		
		
	}
	
	public List<Activity> getActivities(int userid) {
		Database db = this.client.database(CLOUDANT_DB, false);
		com.nova.services.model.cloudant.User user = db.findByIndex("{\"selector\":{\"user_id\":1}}", com.nova.services.model.cloudant.User.class).get(0);
		
		List<Activity> activityList = user.getActivity_list().stream().map(cloudantActivity -> {
			Long id = 0l;
			int order = 0;
			String name = "";
			try { 
			id = Long.parseLong(cloudantActivity.getId());
			order = Integer.parseInt(cloudantActivity.getActivity_order());
			} catch (NumberFormatException e) {
				System.err.printf("Could not convert id or sort-order: [%s] [%s] \n", cloudantActivity.getId(), cloudantActivity.getActivity_order());
			}
			name = cloudantActivity.getActivity_name();
			
			return new Activity(id, name, order);
		}).collect(Collectors.toList());
		
		return activityList;
	}
	
	public boolean addActivityRecord(int userid, ActivityRecord activityRecord) {
		Database db = this.client.database(CLOUDANT_DB, false);
		com.nova.services.model.cloudant.User user = db.findByIndex("{\"selector\":{\"user_id\":1}}", com.nova.services.model.cloudant.User.class).get(0);
		com.nova.services.model.cloudant.ActivityRecord record = 
				new com.nova.services.model.cloudant.ActivityRecord(
						Integer.toString(activityRecord.getActivityId()),
						activityRecord.getStartTime().format(dateFormatter),
						activityRecord.getEndTime().format(dateFormatter)
					);
		user.getRecorded_activities().add(record);
		Response resp = db.update(user);
		return (resp.getError() == null || resp.getError().isEmpty());
	}

}
