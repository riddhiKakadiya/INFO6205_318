/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.knapsack;

import static com.me.knapsack.Values.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author HP
 */
public class Main {
    public static List<Product> productList=new ArrayList<>();
    
    public static void main(String[] args){
        
        createProducts();
        System.out.println(productList.size() + " Products have been created as:");
        System.out.println("Product No.    Price      Volume");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(i + "            " + productList.get(i).getPrice() + "           " + productList.get(i).getWeight());
        }
        
        Population initPop=new Population(initialpop);
        
        System.out.println(initialpop + " Initial chromosomes have been generated as:");
        System.out.println("Chromosome      Weight      Value       Fitness");
        for(int i=0;i<initialpop;i++){
            for(int g:initPop.getIndividuals().get(i).getGenes()){
                System.out.print(g);
                }
            System.out.println("        " + initPop.getIndividuals().get(i).getWeight()+ "          " + initPop.getIndividuals().get(i).getPrice()
            + "           " + initPop.getIndividuals().get(i).getFitness());
    
        }
        
        
    } 
    /////Initialize population
        
//    initially generates products
    public static void createProducts(){
        Random rand=new Random();  
        for(int i=0;i<totalItems;i++){
            Product p=new Product();
            p.setWeight(rand.nextInt(10)+1);
            p.setPrice(rand.nextInt(10)+1);
            productList.add(p);
        }
    }
}
