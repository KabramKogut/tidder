package com.tidder.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Activity {
	
	String description;
	int duration;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

}
