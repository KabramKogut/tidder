package com.tidder.repository;

import java.util.ArrayList;
import java.util.List;

import com.tidder.model.Activity;

public class ActivityRepositoryStub implements ActivityRepository {
	
	@Override
	public List<Activity> findAllActivities() {
		List<Activity> activities = new ArrayList<Activity>();
		
		Activity activity1 = new Activity();
		activity1.setDescription("swimming");
		activity1.setDuration(10);
		
		Activity activity2 = new Activity();
		activity2.setDescription("cycling");
		activity2.setDuration(15);
		
		activities.add(activity1);
		activities.add(activity2);
		
		return activities;
	}

}
