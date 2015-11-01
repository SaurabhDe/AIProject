package algorithm;
import components.Individual;
import components.Population;
import events.EventList;

public class GeneticAlgorithm {

    // GA parameters
    private static final double uniformRate = 0.5;
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
        for (int i = elitismOffset; i < pop.size(); i=i+2) {
            //Individual indiv1 = tournamentSelection(pop);
            //Individual indiv2 = tournamentSelection(pop);
                    	
        	Individual newIndiv = crossover(pop.getIndividual(i), pop.getIndividual(i+1));
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++)
            mutate(newPopulation.getIndividual(i));

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(indiv1.geneSize());

        for (int i = 0; i < indiv1.geneSize(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
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

    /*
     * To be corrected
     * Using this function throw Exception
     * 
     * */
    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize,true);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}