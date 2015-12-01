package com.example.soumya.myscheduler.GeneticAlgorithm.components;

import com.example.soumya.myscheduler.GeneticAlgorithm.events.EventList;

import java.util.ArrayList;

public class Population {

    ArrayList<Individual> individuals;
    EventList eventList;

    // Create a population
    public Population(int populationSize  , EventList eventList) {
        individuals = new ArrayList<>();
        this.eventList = eventList;
        // Loop and create individuals
        for (int i = 0; i < populationSize; i++)
            individuals.add(  new Individual( eventList.getRandomGene() , eventList )  );
    }

    // Create an empty population
    public Population(int populationSize , boolean emptyFlag)
    {
    	individuals = new ArrayList<>(populationSize);
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
    
    public void printPopulation(){
    	for(Individual i : this.individuals)
    		System.out.println(i);
    }
}