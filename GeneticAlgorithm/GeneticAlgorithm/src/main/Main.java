package main;
import com.sun.org.apache.bcel.internal.generic.POP;

import algorithm.GeneticAlgorithm;
import components.Population;
import events.EventList;

public class Main {

    public static void main(String[] args) {
    	
    	EventList.setScheduleCapacity(30);
    	
    	EventList.addEvent("Maths", 8);
    	EventList.setEventPriority("Maths", 1);
    	
    	EventList.addEvent("English", 8);
    	EventList.setEventPriority("English", 2);
    	
    	EventList.addEvent("Science", 8);
    	EventList.setEventPriority("Science", 3);
    	
    	EventList.addEvent("AI", 8);
    	EventList.setEventPriority("AI", 4);
    	
    	EventList.addEvent("DSA", 8);
    	EventList.setEventPriority("DSA", 5);
    	
    	EventList.addEvent("FOB", 8);
    	EventList.setEventPriority("FOB", 6);
    	
    	EventList.addEvent("Eco", 8);
    	EventList.setEventPriority("Eco", 7);
    	
    	EventList.addEvent("GT", 8);
    	EventList.setEventPriority("GT", 8);
    	
    	EventList.addEvent("NLE", 8);
    	EventList.setEventPriority("NLE", 9);
    	
    	EventList.addEvent("CR", 8);
    	EventList.setEventPriority("CR", 10);

    	
    	// Create an initial population
        Population myPop = new Population(100);
        //myPop.printPopulation();
        
        // Evolve our population until we reach an optimum solution
        int generationCount = 100;
        
        
        int onePercent = generationCount/100; 
        int fitnessAtOnePercent = 0 ;
        
        int tenPercent = generationCount/10; 
        int fitnessAtTenPercent = 0 ;
        
        for(int i  = 0 ; i<generationCount ; i++){
            System.out.println("Generation: " + i + " Fittest: " + myPop.getFittest().getFitness());
            myPop = GeneticAlgorithm.evolvePopulation(myPop);
            
            if(i == onePercent)
            	fitnessAtOnePercent = myPop.getFittest().getFitness();
            if(i == tenPercent)
            	fitnessAtTenPercent = myPop.getFittest().getFitness();
        }

        System.out.println("----------------------------------------------");
        System.out.println("Population Size : " + myPop.size());
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        String[] schedule = myPop.getFittest().toString().split(" ");
        
        for(int i  = 0 ; i < schedule.length ; i++)
        	System.out.print(schedule [i]  + " ");
        
        System.out.println("\n");
        System.out.println("Answer at 1% : " + fitnessAtOnePercent);
        System.out.println("Answer at 10% : " + fitnessAtTenPercent);
        System.out.println("Best answer : " + myPop.getFittest().getFitness());
        
    }
}