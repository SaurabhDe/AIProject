package com.example.soumya.myscheduler.GeneticAlgorithm.events;
import com.example.soumya.myscheduler.Tasks;

import java.util.Date;

public class Event {

	private static int globalEventsID = 0;
	
	private String eventName;
	private int eventID;
    //private String subject;
	private Date dueOnDate;
	private int priority;           // int between 1 - 10. Default is 1
	private int noOfHoursRequired;  // Default is 3
	//private boolean completed;
    private Long deadline;
	
	//Default priority = 1
	public Event(Tasks task)
	{
		this.eventName = task.getName();
        this.eventID =  ++Event.globalEventsID;
        //this.subject = task.getSubject();
        this.dueOnDate = task.getDueOnDate();
        this.priority = task.getPriority();
        this.noOfHoursRequired = task.getNoOfHoursRequired();
        this.deadline = this.dueOnDate.getTime();
    }

	public int getHoursRequired() {
		return noOfHoursRequired;
	}

	public int getPriority() {
		return priority;
	}
	public String getEventName() {
		return eventName;
	}
	public int getEventID() {
		return eventID;
	}
	public Long getDeadline(){
		return deadline;
	}
}
