package com.tidder.repository;

import java.util.List;

import com.tidder.model.Activity;

public interface ActivityRepository {

	List<Activity> findAllActivities();

}