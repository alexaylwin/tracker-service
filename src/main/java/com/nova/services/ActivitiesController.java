package com.nova.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nova.services.dao.ActivityDao;
import com.nova.services.model.Activity;
import com.nova.services.model.ActivityRecord;

@RestController
public class ActivitiesController {

	@Autowired
	ActivityDao activityDao;
	
	@RequestMapping("/activities")
	public List<Activity> getActivities(@RequestParam(value="userid") int userId) {
		
		return activityDao.getActivities(userId);
	}
	
	@RequestMapping("/activities/record")
	public List<ActivityRecord> getActivityRecord(@RequestParam(value="userid") int userId) {
		LocalDateTime tmp = LocalDateTime.now();
		tmp = tmp.minusWeeks(4);
		return activityDao.getRecordedActivities(tmp, null, userId);
	}
	
	@RequestMapping(value="/activity/record", method=RequestMethod.POST)
	public ActivityRecord addActivityRecord(@RequestParam(value="activityid") int activityId) {
		
		return null;
	}
	
}
