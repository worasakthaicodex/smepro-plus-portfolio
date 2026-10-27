package com.smepro.models;

import java.util.List;

public class StatusSummary {
	
	   private String startDateTime;
	    private String mostFrequentStatus;

	    public StatusSummary(String startDateTime, String mostFrequentStatus) {
	        this.startDateTime = startDateTime;
	        this.mostFrequentStatus = mostFrequentStatus;
	    }

	    // Getter/Setter
	    public String getStartDateTime() {
	        return startDateTime;
	    }

	    public void setStartDateTime(String startDateTime) {
	        this.startDateTime = startDateTime;
	    }

	    public String getMostFrequentStatus() {
	        return mostFrequentStatus;
	    }

	    public void setMostFrequentStatus(String mostFrequentStatus) {
	        this.mostFrequentStatus = mostFrequentStatus;
	    }
}
