package algorithm;
import components.Individual;
import components.Population;
import events.EventList;

public class GeneticAlgorithm {

    // GA parameters
    private static final double uniformCrossoverRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size());

        // Keep our best individual
        if (elitism)
            newPopulation.saveIndividual(0, pop.getFittest());

        // Crossover population
        int elitismOffset;
        if (elitism)
            elitismOffset = 1;
        else
            elitismOffset = 0;
        
        // Loop over the population size and create new individuals with crossover
        for (int i = elitismOffset; i+1 < pop.size(); i=i+2) {
                    	
        	Individual newIndiv = crossover(pop.getIndividual(i), pop.getIndividual(i+1));
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++)
            mutate(newPopulation.getIndividual(i));

        pop.sortPopulation();
        newPopulation.sortPopulation();
        //System.out.println("Pop :" + pop.size());
        //System.out.println("NewPop : " + newPopulation.size());
        
        
        //Select individuals from both generations
        Population resultPopulation = new Population(true);
        //System.out.println("ResultPop :" + resultPopulation.size());
        
        int c1=0,c2=0;
        for(int i = 0 ; i<pop.size() ; i++)
        {
        	float prob = (float)pop.getIndividual(c1).getFitness() / (float)(pop.getIndividual(c1).getFitness() + newPopulation.getIndividual(c2).getFitness()) ;
        	if(Math.random() < prob){
        		resultPopulation.addIndividual(pop.getIndividual(c1));
        		c1++;
        	}
        	else{
        		resultPopulation.addIndividual(newPopulation.getIndividual(c2));
        		c2++;
        	}
        }
        //System.out.println(c1 + "  " + c2);
        //System.out.println(resultPopulation.size());
        return resultPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(indiv1.geneSize());

        for (int i = 0; i < indiv1.geneSize(); i++) {
            // Crossover
            if (Math.random() <= uniformCrossoverRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv){
        for (int i = 0; i < indiv.geneSize(); i++)
            if (Math.random() <= mutationRate)
                indiv.setGene(i, EventList.getRandomEventID());
    }

}