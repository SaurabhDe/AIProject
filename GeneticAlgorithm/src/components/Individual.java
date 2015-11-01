package components;

import java.util.ArrayList;

public class Individual {

    ArrayList<Integer> genes;
    private int fitness = 0;

    public Individual(int size)
    {
    	this.genes = new ArrayList<>();
    	for(int i  = 0 ; i< size ; i++)
    		genes.add(0);
    }
    public Individual(ArrayList<Integer> randomGene)
    {
    	this.genes = randomGene;
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
            fitness = FitnessCalc.getFitness(this.genes);
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