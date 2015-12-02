package com.example.soumya.myscheduler.GeneticAlgorithm.main;

import com.example.soumya.myscheduler.GeneticAlgorithm.algorithm.GeneticAlgorithm;
import com.example.soumya.myscheduler.GeneticAlgorithm.components.Population;
import com.example.soumya.myscheduler.GeneticAlgorithm.events.EventList;
import com.example.soumya.myscheduler.Tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Schedule {

    public static ArrayList<Tasks> getFinalSchedule(ArrayList<Tasks> tasksList , int scheduleCapacity) {

        EventList eventList = new EventList(scheduleCapacity);
        for(Tasks t : tasksList)
            eventList.addEvent(t);

        Population myPop = new Population(100 , eventList);
        int generationCount = 1000;
        for(int i  = 0 ; i<generationCount ; i++)
            myPop = GeneticAlgorithm.evolvePopulation(myPop , eventList);

        String[] schedule = myPop.getFittest().toString().split(" ");
        HashMap<Integer,String> map = eventList.getEventIDNameMap();

        ArrayList<Tasks> result = new ArrayList<>();
        for(int i = 0 ; i < schedule.length ; i++) {
            Integer ID = Integer.parseInt(schedule[i]);
            String EventName = map.get(ID);

            int index = tasksList.indexOf(EventName);
            result.add(tasksList.get(index).getClone());
        }
        return result;
    }
}