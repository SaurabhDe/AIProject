package main;
import algorithm.GeneticAlgorithm;
import components.Population;
import events.EventList;

public class Main {

    public static void main(String[] args) {
    	
    	EventList.setScheduleCapacity(15);
    	EventList.addEvent("Maths", 1);
    	EventList.setEventPriority("Maths", 3);
    	EventList.addEvent("English", 2);
    	EventList.setEventPriority("English", 2);
    	EventList.addEvent("Science", 3);
    	
        // Create an initial population
        Population myPop = new Population(3);
        myPop.printPopulation();
        
        // Evolve our population until we reach an optimum solution
        
        int generationCount = 10000;
        for(int i  = 0 ; i<generationCount ; i++){
            //System.out.println("Generation: " + i + " Fittest: " + myPop.getFittest().getFitness());
            myPop = GeneticAlgorithm.evolvePopulation(myPop);
        }

        System.out.println("----------------------------------------------");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        String[] schedule = myPop.getFittest().toString().split(" ");
        System.out.println("Bleh");
        for (int i = 0; i < schedule.length; i++) {
			String string = schedule[i];
			System.out.println(string);
		}
        System.out.println("Bleh");
        for(int i  = 0 ; i < 3 ; i++)
        {
        	for(int j = 0 ; j < 5 ; j++)
        		System.out.print(schedule [ i*5 + j ]  + " ");
        	System.out.println();
        }
        System.out.println("----------------------------------------------");

        
    }
}