package com.nova.services.dao;

import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.nova.services.model.ActivityRecord;

@RunWith(SpringRunner.class)
public class ActivityDaoTests {
	
	private ActivityDao testDao;
	private final int ALEX_ID = 1;
	
	@Before
	public void setUp() throws MalformedURLException {
		testDao = new ActivityDao();
		testDao.postConstruct();
		System.out.println("Starting DAO Unit Tests");
		
	}
	
	@Test
	public void testDaoConstruction() {

		assertTrue(testDao.client.getAllDbs().size() > 0);
	}
	
	@Test
	public void testGetActivityList() {
		
		List<ActivityRecord> recordedActivities = new ArrayList<ActivityRecord>();
		recordedActivities = testDao.getRecordedActivities(LocalDateTime.MIN, LocalDateTime.now(), ALEX_ID);
		assertTrue(recordedActivities.size() > 0);
		
	}

}
