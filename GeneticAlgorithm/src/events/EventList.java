package events;

import java.util.ArrayList;
import java.util.HashMap;

public class EventList {

	private static ArrayList<Event> eventList = new ArrayList<>();
	private static HashMap<Integer, Integer>  eventIDIndexMap =  new HashMap<>();		//Keeps index of events in list
	private static HashMap<String, Integer>  eventNameIndexMap =  new HashMap<>();		//Keeps index of events in list
	private static ArrayList<Integer> eventIDs = new ArrayList<>(); 

	private static int scheduleCapacity = 0;
	
	public static void addEvent(String eventName , int hoursRequired)
	{
		Event newEvent = new Event(eventName, hoursRequired) ;
		eventList.add(newEvent);
		eventNameIndexMap.put(eventName,eventList.size()-1);
		eventIDIndexMap.put(newEvent.getEventID(), eventList.size()-1);
		eventIDs.add(newEvent.getEventID());
	}
	
	public static void setScheduleCapacity(int capacity){
		scheduleCapacity = capacity;
	}
	public static void setEventPriority(String eventName , int priority)
	{
		int index = eventNameIndexMap.get(eventName);
		eventList.get(index).setPriority(priority);
	}
	public static void setEventDeadline(String eventName , int deadline)
	{
		int index = eventNameIndexMap.get(eventName);
		eventList.get(index).setDeadline(deadline);
	}

	//Generate random gene of Event IDs
	public static ArrayList<Integer> getRandomGene()
	{
		ArrayList<Integer> gene =  new ArrayList<>();
		
		//Generate Gene
		for(Event e : eventList)
		{
			int hrs = e.getHoursRequired();
			int eventId = e.getEventID();
			for(int i  = 0 ; i<hrs ; i++)
				gene.add(eventId);
		}
		
		if(gene.size() < scheduleCapacity)
		//Fill empty slots with zeroes
		{
			int size = gene.size();
			for ( ; size<scheduleCapacity ; size++ )
				gene.add(0);
		}
		
		//Random Shuffling
		int genelength = gene.size();
		for(int i = 0 ; i<genelength ; i++)
		{
			int swapIndex = (int)(Math.random()*i);
			
			int a = gene.get(swapIndex);
			int b = gene.get(i);
			
			gene.set(swapIndex, b);
			gene.set(i ,a);
		}
		return gene;
	}
	
	public static ArrayList<Event> getEventList() {
		return eventList;
	}
	public static HashMap<Integer, Integer> getEventIDIndexMap() {
		return eventIDIndexMap;
	}
	public static HashMap<String, Integer> getEventNameIndexMap() {
		return eventNameIndexMap;
	}
	public static int getScheduleCapacity() {
		return scheduleCapacity;
	}
	public static int getRandomEventID(){
		if(Math.random() < 0.3)		//Empty event slot
			return 0;
		return eventIDs.get( (int)(Math.random()* eventIDs.size()) );
	}
	
}
