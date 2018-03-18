package com.tidder;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tidder.model.Activity;
import com.tidder.repository.ActivityRepository;

@Component
@Path("activities")
public class ActivityResource {

	@Autowired
	private ActivityRepository activityRepository ;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<Activity> getAllActivities() { 
		return activityRepository.findAllActivities();
	}
	
	
}
