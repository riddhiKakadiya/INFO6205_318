package com.me.knapsack;

import static com.me.knapsack.Main.productList;
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

        Population pop = new Population(totalItems);
        Assert.assertFalse(pop.individuals.get(1).getGenes().equals(pop.individuals.get(0).getGenes()));
    }

    @Test
    public void generateProduct() {
        //test whether sack size is 20
        Random rand = new Random();
        for (int i = 0; i < totalItems; i++) {
            Product p = new Product();

            p.setWeight(rand.nextInt(10) + 1);
            p.setPrice(rand.nextInt(10) + 1);

            productList.add(p);
        }

        Assert.assertEquals(productList.size(), 20);
    }

    @Test
    public void selectionTest() {

    }
}
