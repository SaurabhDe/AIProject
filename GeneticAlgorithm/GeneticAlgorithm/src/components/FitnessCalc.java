package components;

import java.util.ArrayList;
import java.util.HashMap;

import events.Event;
import events.EventList;

public class FitnessCalc {

	public static int getFitness(ArrayList<Integer> gene)
	{
		int fitnessValue = 0;
		int scheduleCapacity = EventList.getScheduleCapacity();
		ArrayList<Event> eventList = EventList.getEventList();
		
		HashMap<Integer, Integer> hrsCompleted = new HashMap<>();	// <EventID , NoOfHrsCompleted>
		for(Event e : eventList)
			hrsCompleted.put(e.getEventID(), 0);	//Set zero hrs completed initially
		
		//Check only top n-slots as per scheduleCapacity
		int slotCount = 0;
		for(Integer i : gene)		//gene of IDs
		{
			if(slotCount>= scheduleCapacity)
				break;
			slotCount++;
			
			if(i != 0)				//0 is empty slot
				hrsCompleted.put(i, hrsCompleted.get(i) + 1);
		}
		
		//Fitness contribution = No of event hrs scheduled * priority
		for(Event e : eventList)
		{
			int hrsC = hrsCompleted.get(e.getEventID());
			int hrsR = e.getHoursRequired();
			int pr = e.getPriority();
			
			fitnessValue += Math.min( hrsC , hrsR ) * Math.min( hrsC , hrsR )  *  pr;
			if( hrsC > hrsR )
				fitnessValue -= (hrsC - hrsR )*(hrsC - hrsR ) * pr ;
				
		}
		return fitnessValue;
	}

	public static int getFitness(ArrayList<Integer> gene , ArrayList<Integer> prevSchedule)
	{
		int fitnessValue = 0;
		int scheduleCapacity = EventList.getScheduleCapacity();
		ArrayList<Event> eventList = EventList.getEventList();
		
		HashMap<Integer, Integer> hrsCompleted = new HashMap<>();	// <EventID , NoOfHrsCompleted>
		for(Event e : eventList)
			hrsCompleted.put(e.getEventID(), 0);	//Set zero hrs completed initially
		
		//Check only top n-slots as per scheduleCapacity
		int slotCount = 0;
		for(Integer i : gene)		//gene of IDs
		{
			if(slotCount>= scheduleCapacity)
				break;
			slotCount++;
			
			if(i != 0)				//0 is empty slot
				hrsCompleted.put(i, hrsCompleted.get(i) + 1);
		}
		
		//Fitness contribution = No of event hrs scheduled * priority
		for(Event e : eventList)
		{
			int hrsC = hrsCompleted.get(e.getEventID());
			int hrsR = e.getHoursRequired();
			int pr = e.getPriority();
			
			fitnessValue += Math.min( hrsC , hrsR )  *  pr ;
			if(hrsC > hrsR )
				fitnessValue -= (hrsC - hrsR ) * pr;
				
		}
		return fitnessValue;
	}

	
}