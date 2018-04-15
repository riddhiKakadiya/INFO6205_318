package com.me.knapsack;

import static com.me.knapsack.Values.capacity;
import static com.me.knapsack.Values.totalItems;
import java.util.ArrayList;
import java.util.Random;

public class Individual {

    public ArrayList<Integer> genes = new ArrayList<>();
    public int weight = 0;
    public int price = 0;
    public int fitness = 0;

    public ArrayList<Integer> getGenes() {
        return genes;
    }

    public void setGenes(ArrayList<Integer> genes) {
        this.genes = genes;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void generateGenes() {
        Random rand = new Random();
        for (int i = 0; i < totalItems; i++) {
            this.genes.add(rand.nextInt(2));
        }
        calculateFitness();
    }

    public void calculateFitness() {
        int tempPrice = 0;
        int tempWeight = 0;
        for (int i = 0; i < totalItems; i++) {
            if (this.genes.get(i) == 1) {
                tempWeight = tempWeight + Main.productList.get(i).getWeight();
                tempPrice = tempPrice + Main.productList.get(i).getPrice();
            }
        }
        this.price = tempPrice;
        this.weight = tempWeight;
        if (this.weight <= capacity) {
            this.fitness = this.price;
        }
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i : genes) {
            geneString += i;
        }
        return geneString;
    }
}
