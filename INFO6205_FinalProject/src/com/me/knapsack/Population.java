/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.knapsack;

import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class Population {
    public static ArrayList<Individual> individuals=new ArrayList<Individual>();
    private int totalFitness;
    
    public Population(int initialpop){
        for(int i=0;i<initialpop;i++ ){
            Individual individual=new Individual();
            individual.generateGenes();
            individuals.add(individual);
        }
        calcTotalFitness();
        calcBestSolution();
    }

    public ArrayList<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(ArrayList<Individual> individuals) {
        this.individuals = individuals;
    }


    public int getTotalFitness() {
        return totalFitness;
    }

    public void setTotalFitness(int totalFitness) {
        this.totalFitness = totalFitness;
    }
    
    public static void calcTotalFitness(){
       int tempTotalFitness = 0;
       for(int i=0;i<individuals.size();i++){
           tempTotalFitness= tempTotalFitness+individuals.get(i).getFitness();
       }
    }
 
   public static void calcBestSolution(){
       
   }
}
