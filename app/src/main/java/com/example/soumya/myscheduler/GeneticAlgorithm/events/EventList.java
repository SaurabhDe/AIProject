package com.example.soumya.myscheduler.GeneticAlgorithm.events;

import com.example.soumya.myscheduler.Tasks;

import java.util.ArrayList;
import java.util.HashMap;

public class EventList {

	private ArrayList<Event> eventList;
	private HashMap<Integer, Integer>  eventIDIndexMap;		//Keeps index of events in list
	private HashMap<String, Integer>  eventNameIndexMap;		//Keeps index of events in list
    private HashMap<Integer , String> eventIDNameMap;       //Keeps names of events
	private ArrayList<Integer> eventIDs;
	private int scheduleCapacity;

	public EventList(int scheduleCapacity)
    {
        this.eventList = new ArrayList<>();
        this.eventIDIndexMap =  new HashMap<>();
        this.eventNameIndexMap =  new HashMap<>();
        this.eventIDNameMap = new HashMap<>();
        this.eventIDs  = new ArrayList<>();
        this.scheduleCapacity = scheduleCapacity;
    }

	public void addEvent(Tasks task) {
		Event newEvent = new Event(task) ;
		eventList.add(newEvent);
		eventNameIndexMap.put(newEvent.getEventName(), eventList.size() - 1);
		eventIDIndexMap.put(newEvent.getEventID(), eventList.size()-1);
        eventIDNameMap.put(newEvent.getEventID() , newEvent.getEventName());
		eventIDs.add(newEvent.getEventID());
	}
	
	/*public void setScheduleCapacity(int capacity){
		scheduleCapacity = capacity;
	}
	public void setEventPriority(String eventName , int priority)
	{
		int index = eventNameIndexMap.get(eventName);
		eventList.get(index).setPriority(priority);
	}
	public void setEventDeadline(String eventName , int deadline)
	{
		int index = eventNameIndexMap.get(eventName);
		eventList.get(index).setDeadline(deadline);
	}*/

	//Generate random gene of Event IDs
	public ArrayList<Integer> getRandomGene()
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
	
	public ArrayList<Event> getEventList() {
		return eventList;
	}
	public HashMap<Integer, Integer> getEventIDIndexMap() {
		return eventIDIndexMap;
	}
	public HashMap<String, Integer> getEventNameIndexMap() {
		return eventNameIndexMap;
	}
    public HashMap<Integer , String> getEventIDNameMap(){return eventIDNameMap;}
	public int getScheduleCapacity() {
		return scheduleCapacity;
	}
	public int getRandomEventID(){
		if(Math.random() < 0.3)		//Empty event slot
			return 0;
		return eventIDs.get( (int)(Math.random()* eventIDs.size()) );
	}
}