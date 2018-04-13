/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.knapsack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Population {
    public static List<Individual> individuals=new ArrayList<Individual>();
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

    public static List<Individual> getIndividuals() {
        return individuals;
    }

    public static void setIndividuals(List<Individual> individuals) {
        Population.individuals = individuals;
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
