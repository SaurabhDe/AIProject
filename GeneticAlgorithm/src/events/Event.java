package events;

public class Event {

	private static int globalEventsID = 0;
	
	private int hoursRequired;
	private int priority;
	private int deadline;
	
	private String eventName;
	private int eventID;
	
	//Default priority = 1
	public Event(String eventName , int hoursRequired)
	{
		this.eventName = eventName;
		this.hoursRequired = hoursRequired;
		this.priority = 1;
		this.deadline = Integer.MAX_VALUE;
		this.eventID =  ++Event.globalEventsID;
	}
	
	public void setPriority(int priority){
		this.priority = priority;
	}
	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public int getHoursRequired() {
		return hoursRequired;
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
	public int getDeadline(){
		return deadline;
	}
}
