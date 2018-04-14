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
    private static Individual fittest;
    
    public Population(int initialpop){
        for(int i=0;i<initialpop;i++ ){
            Individual individual=new Individual();
            individual.generateGenes();
            individuals.add(individual);
        }
        calcTotalFitness();
    }

    public Individual getFittest() {
        return fittest;
    }

    public void setFittest(Individual fittest) {
        this.fittest = fittest;
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
       int max=0;
       int fitindex=0;
       for(int i=0;i<individuals.size();i++){
             if(individuals.get(i).getFitness()>max){
                max=individuals.get(i).getFitness();
                fitindex=i;
       }
     }
       fittest=individuals.get(fitindex);
  }
   
       public double calculateAverage(){
        double sum=0;
        for(int i=0;i<individuals.size();i++){
            sum+=individuals.get(i).getFitness();
        }
        return sum/individuals.size();
    }

}
