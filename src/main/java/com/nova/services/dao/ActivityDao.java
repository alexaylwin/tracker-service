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
import org.springframework.stereotype.Component;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.nova.services.model.ActivityRecord;
import com.nova.services.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ActivityDao {
	
	private static final Logger logger = LoggerFactory.getLogger(ActivityDao.class);
	
	private static final String CLOUDANT_URL = "https://d37dd7ea-611f-4601-b63a-6ea1832af765-bluemix.cloudant.com";
	private static final String CLOUDANT_USER = "d37dd7ea-611f-4601-b63a-6ea1832af765-bluemix";
	private static final String CLOUDANT_PASS = "31b843b97d5299885254da9722dab071aefee2a98bc7c56257b99f119b9256cf";
	private static final String CLOUDANT_DB = "tracker";
	
	
	CloudantClient client;
	private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
	
	@PostConstruct
	public void postConstruct() throws MalformedURLException {
		this.client = ClientBuilder.url(new URL(CLOUDANT_URL))
						.username(CLOUDANT_USER)
						.password(CLOUDANT_PASS)
						.build();
	}
	
	public List<ActivityRecord> getActivities(LocalDateTime startTime, LocalDateTime endTime, int userId) { 
		Database db = this.client.database("tracker", false);
		com.nova.services.model.cloudant.User user = db.findByIndex("{\"selector\":{\"user_id\":1}}", com.nova.services.model.cloudant.User.class).get(0);

		/*
		 * TODO:
		 * Convert the Gson CompletedActivities object into a business CompletedActivity object
		 * Only return the activities that fall within the start and end time
		 * 
		 * Implement caching for the user object returned from the database
		 */
		System.out.println(user.toString());
		List<ActivityRecord> activityList = user.getRecorded_activities().stream().map(gu -> {
			LocalDateTime start;
			LocalDateTime end;
			start = LocalDateTime.parse(gu.getStart_time().trim(), dateFormatter);
			end = LocalDateTime.parse(gu.getEnd_time(), dateFormatter);
			return new com.nova.services.model.ActivityRecord(Integer.valueOf(gu.getId()), start, end);
		}).collect(Collectors.toList());
		
		activityList.stream().forEach(ca -> {System.out.println(ca.getActivityId());});
		
		return activityList;		
		
	}

}
