package components;

import java.util.ArrayList;
import java.util.Collections;

import events.EventList;

public class Population {

    ArrayList<Individual> individuals;
    
    // Create a population
    public Population(int populationSize ) {
        individuals = new ArrayList<>();
        // Loop and create individuals
        for (int i = 0; i < populationSize; i++)
            individuals.add(  new Individual( EventList.getRandomGene() )  );  	
    }
    
    // Create an empty population
    public Population(boolean emptyFlag)
    {
    	individuals = new ArrayList<>();
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public Individual getFittest() {
        Individual fittest = individuals.get(0);
        // Loop through individuals to find fittest
        for (int i = 0; i < size(); i++) {
            if (fittest.getFitness() <= getIndividual(i).getFitness()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public int size() {
        return individuals.size();
    }

    public void saveIndividual(int index, Individual indiv) {
        individuals.set(index, indiv);
    }
    
    public void addIndividual(Individual indiv){
    	individuals.add(indiv);
    }
    
    public void printPopulation(){
    	for(Individual i : this.individuals)
    		System.out.println(i);
    }
    
    public void sortPopulation()
    {
    	Collections.sort(this.individuals);
    }
}