package com.example.soumya.myscheduler.GeneticAlgorithm.components;

import com.example.soumya.myscheduler.GeneticAlgorithm.events.EventList;

import java.util.ArrayList;

public class Individual {

    ArrayList<Integer> genes;
    private int fitness = 0;
    EventList eventList;

    public Individual(int size , EventList eventList)
    {
    	this.genes = new ArrayList<>();
    	for(int i  = 0 ; i< size ; i++)
    		genes.add(0);
        this.eventList = eventList;
    }
    public Individual(ArrayList<Integer> randomGene, EventList eventList)
    {
    	this.genes = randomGene;
        this.eventList = eventList;
    }
    
    public int getGene(int index) {
        return genes.get(index);
    }

    public void setGene(int index, int value) {
        genes.set(index, value);
        fitness = 0;				//So fitness gets recalculated
    }

    public int geneSize() {
        return genes.size();
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessCalc.getFitness(this.genes , eventList);
        }
        return fitness;
    }
    
    @Override
    public String toString() {
        StringBuilder geneString = new StringBuilder();
        for (int i = 0; i < genes.size(); i++) {
            geneString.append( getGene(i));
            geneString.append(" ");
        }
        return geneString.toString();
    }
}