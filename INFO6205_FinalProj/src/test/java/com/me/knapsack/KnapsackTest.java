package com.me.knapsack;

import static com.me.knapsack.Main.evolvePopulation;
import static com.me.knapsack.Main.productList;
import static com.me.knapsack.Main.selection;
import static com.me.knapsack.Values.initialpop;
import static com.me.knapsack.Values.totalItems;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author HP
 */
public class KnapsackTest {

    @Test
    public void generateGene() {
        //checks whether both genes are different
        Random rand = new Random();
        for (int i = 0; i < totalItems; i++) {
            Product p = new Product();

            p.setWeight(rand.nextInt(10) + 1);
            p.setPrice(rand.nextInt(10) + 1);

            productList.add(p);
        }

        Population pop = new Population(initialpop);
        Assert.assertFalse(pop.individuals.get(1).getGenes().equals(pop.individuals.get(0).getGenes()));
    }

    @Test
    public void generateProduct() {
        //test whether sack size is 20

        Assert.assertEquals(productList.size(), totalItems);
    }

    @Test
    public void selectionTest() {
        
        Random rand = new Random();
        for (int i = 0; i < totalItems; i++) {
            Product p = new Product();

            p.setWeight(rand.nextInt(10) + 1);
            p.setPrice(rand.nextInt(10) + 1);

            productList.add(p);
        }

        Population pop = new Population(initialpop);
        selection();
        pop.calcBestSolution();
        Assert.assertTrue(pop.individuals.get(0).getFitness()>=pop.individuals.get(1).getFitness());
        Assert.assertEquals(pop.individuals.get(0).getFitness(),pop.getFittest().getFitness());
    }
    
    @Test
    public void evolveTest(){
        Random rand = new Random();
        
        for (int i = 0; i < totalItems; i++) {
            Product p = new Product();

            p.setWeight(rand.nextInt(10) + 1);
            p.setPrice(rand.nextInt(10) + 1);

            productList.add(p);
        }
        Population pop = new Population(initialpop);
        selection();
        int beforeCrossover=pop.getIndividuals().size();
        evolvePopulation();
        int afterCrossover=pop.getIndividuals().size();
        
        Assert.assertFalse(beforeCrossover==afterCrossover);
        Assert.assertEquals(afterCrossover,initialpop);
    }
    
}
