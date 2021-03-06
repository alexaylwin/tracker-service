package com.nova.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.nova.services.dao.ActivityDao;
import com.nova.services.model.Activity;
import com.nova.services.model.ActivityRecord;

@RestController
public class ActivitiesController {

	@Autowired
	ActivityDao activityDao;
	
	@GetMapping("/tracker/api/activities")
	public List<Activity> getActivities(@RequestParam(value="userid") int userId) {
		
		return activityDao.getActivities(userId);
	}

	@GetMapping("/tracker/api/activities/record")
	public List<ActivityRecord> getActivityRecord(@RequestParam(value="userid") int userId) {
		return activityDao.getRecordedActivities(null, null, userId);
	}
	
	@PostMapping(value="/tracker/api/activities/record")
	public boolean addActivityRecord(@RequestParam(value="userid") int userid, @RequestBody ActivityRecord activityRecord) {
		
		return activityDao.addActivityRecord(userid, activityRecord);
	}
	
	@GetMapping("/error")
	public void handleError(WebRequest request) {
		System.out.println(request);
		System.out.println("Error!");
	}
	
}
