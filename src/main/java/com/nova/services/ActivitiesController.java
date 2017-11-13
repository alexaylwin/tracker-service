package com.nova.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nova.services.dao.ActivityDao;
import com.nova.services.model.CompletedActivity;

@RestController
public class ActivitiesController {

	@Autowired
	ActivityDao activityDao;
	
	@RequestMapping("/activities")
	public List<CompletedActivity> getActivities(@RequestParam(value="userid") String userId) {
		
		return activityDao.getActivities(null, null, userId);
	}
	
	@RequestMapping("/recent-activities")
	public List<CompletedActivity> getRecentActivities(@RequestParam(value="userid") String userId) {
		LocalDateTime tmp = LocalDateTime.now();
		tmp = tmp.minusWeeks(4);
		return activityDao.getActivities(tmp, null, userId);
	}
	
	@RequestMapping(value="/activity", method=RequestMethod.PUT)
	public CompletedActivity addActivity(@RequestParam(value="activityid") int activityId) {
		
		return null;
	}
	
}
