/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.knapsack;

import static com.me.knapsack.Values.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author HP
 */
public class Main {

    public static List<Product> productList = new ArrayList<>();
    public static Population pop;

    public static void main(String[] args) {

        createProducts();
        System.out.println(productList.size() + " Products have been created as:");
        System.out.println("Product No.    Price      Weight");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(i + "            " + productList.get(i).getPrice() + "           " + productList.get(i).getWeight());
        }

        /////Initialize population
        pop = new Population(initialpop);

        tracker(0);
        for (int i = 1; i < 15; i++) {
            selection();
            evolvePopulation();
            tracker(i);
        }

    }

//    initially generates products
    public static void createProducts() {
        Random rand = new Random();
        for (int i = 0; i < totalItems; i++) {
            Product p = new Product();
            p.setWeight(rand.nextInt(10) + 1);
            p.setPrice(rand.nextInt(10) + 1);
            productList.add(p);
        }
    }

    public static void tracker(int generationCount) {
        System.out.println("For generation: " + generationCount + " chromosomes have been generated as:");
        System.out.println("Chromosome      Weight      Value       Fitness");
        for (int i = 0; i < pop.getIndividuals().size(); i++) {
            for (int g : pop.getIndividuals().get(i).getGenes()) {
                System.out.print(g);
            }
            System.out.println("        " + pop.getIndividuals().get(i).getWeight() + "          " + pop.getIndividuals().get(i).getPrice()
                    + "           " + pop.getIndividuals().get(i).getFitness());

        }

    }

    public static void selection() {
        Comparator<Individual> sort = (Individual ind1, Individual ind2) -> {
            int cmp1 = ind1.getFitness();
            int cmp2 = ind2.getFitness();
            if (cmp2 > cmp1) {
                return 1;
            } else if (cmp2 < cmp1) {
                return -1;
            } else {
                return 0;
            }
        };

        Queue<Individual> q = new PriorityQueue<>(sort);
        List<Individual> individuals = pop.getIndividuals();
        for (int i = 0; i < individuals.size(); i++) {
            q.add(individuals.get(i));
        }
        int halfpop = individuals.size() / 2;
        individuals.clear();
        for (int i = 0; i < halfpop; i++) {
            individuals.add(q.poll());
        }
    }

    public static void evolvePopulation() {
        List<Individual> individuals = pop.getIndividuals();
        for (int i = 0; i < individuals.size() - 1; i += 2) {
            Individual i1 = individuals.get(i);
            Individual i2 = individuals.get(i + 1);
            crossover(i1, i2, individuals);
        }
    }

    public static void crossover(Individual i1, Individual i2, List<Individual> individuals) {
        Random rand = new Random();
        Individual child = new Individual();
        for (int i = 0; i < individuals.get(i).getGenes().size(); i++) {
            int r = rand.nextInt(2);
            if (r == 0) {
                child.getGenes().add(i2.getGenes().get(i));   //for each gene from parents'
            } else {
                child.getGenes().add(i1.getGenes().get(i));
            }
        }
        child.calculateFitness();
        pop.getIndividuals().add(child);
    }
}
