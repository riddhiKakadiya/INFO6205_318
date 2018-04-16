/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.me.knapsack;

import static com.me.knapsack.Values.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author HP
 */
public class Main extends Application{

    public static List<Product> productList = new ArrayList<>();
    public static List<Integer> fitnessTrack = new ArrayList<>();
    public static Population pop;
    final static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        org.apache.log4j.BasicConfigurator.configure();

        createProducts();
        System.out.println(productList.size() + " Products have been created as:");
        System.out.println("Product No.    Price      Weight");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(i + "            " + productList.get(i).getPrice() + "           " + productList.get(i).getWeight());
        }

        /////Initialize population
        pop = new Population(initialpop);
        tracker(0);
        
        ////run for totalGenerations 
        for (int i = 1; i < totalGeneration; i++) {
            selection();
            evolvePopulation();
            tracker(i);
        }
        launch();
    }

//    initially generates products from text file
    public static void createProducts() throws FileNotFoundException, IOException {
//        Random rand = new Random();
         FileReader fileReader = new FileReader(filename);
         BufferedReader bufferedReader =new BufferedReader(fileReader);
         String str;
         str = bufferedReader.readLine();
         String[] price=str.split(",");

         str = bufferedReader.readLine();
         String[] weight=str.split(",");

        for (int i = 0; i < totalItems; i++) {
            Product p = new Product();
            
//            p.setWeight(rand.nextInt(10) + 1);
//            p.setPrice(rand.nextInt(10) + 1);
              
              p.setPrice(Integer.parseInt(price[i]));
              p.setWeight(Integer.parseInt(weight[i]));
            productList.add(p);
        }
    }

    public static void tracker(int generationCount) {
        pop.calcBestSolution();
//        logger.info("Best Chromosone of the Generation "+generationCount+": "+pop.getFittest().toString());
        logger.info("Fitness of fittest chromosone of Generation "+generationCount+": "+pop.getFittest().getFitness());
        logger.info("Average Fitness: "+pop.calculateAverage());
        System.out.println("");
        
        ////to print each chromosone of population
//        System.out.println("Chromosome               Weight      Value       Fitness");
//        for (int i = 0; i < pop.getIndividuals().size(); i++) {
//            for (int g : pop.getIndividuals().get(i).getGenes()) {
//                System.out.print(g);
//            }
//            System.out.println("        " + pop.getIndividuals().get(i).getWeight() + "          " + pop.getIndividuals().get(i).getPrice()
//                    + "           " + pop.getIndividuals().get(i).getFitness());
//
//        }
        
        

    }
    /////////select 75% of the fittest individuals
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
        int remainpPop = 3*individuals.size() / 4;
        individuals.clear(); //////cull 25% of unfittest individual
        for (int i = 0; i < remainpPop; i++) {
            individuals.add(q.poll());
        }
    }

    public static void evolvePopulation() {
        List<Individual> individuals = pop.getIndividuals();
        int popTobeEvolved=(4*individuals.size()/3)/2; ////select 50% of the population to generate new population
        for (int i = 0; i < popTobeEvolved; i += 2) {
            Individual i1 = individuals.get(i);
            Individual i2 = individuals.get(i + 1);
            crossover(i1, i2, individuals);
        }
            fitnessTrack.add(pop.getFittest().getFitness());

    }
    ///perform crossover between two fittest individual
    public static void crossover(Individual i1, Individual i2, List<Individual> individuals) {
        Random rand = new Random();
        Individual child = new Individual();
        for (int i = 0; i < i1.getGenes().size(); i++) {
            int r = rand.nextInt(2);
            if (r == 0) {
                child.getGenes().add(i2.getGenes().get(i));   //for each gene from parents'
            } else {
                child.getGenes().add(i1.getGenes().get(i));
            }
        }
        child.calculateFitness();
        mutate(child);
        pop.getIndividuals().add(child);
    }
    ///mutate each child which depends on mutation probability
    public static void mutate(Individual child){
          Random random=new Random();
          if(Math.random()>mutationProb) return;
          int pos = random.nextInt(child.getGenes().size()); 
          if(child.getGenes().get(pos)==0)  child.getGenes().set(pos,1);
          
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Fitness chart");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Generation");
        yAxis.setLabel("Fitness");
        yAxis.setForceZeroInRange(false);
        //creating the chart
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Fitness Tracker for 0-1 Knapsack problem");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("My portfolio");
        //populating the series with data
        for(int i=0;i<fitnessTrack.size();i++){
        series.getData().add(new XYChart.Data(i, fitnessTrack.get(i)));
        }
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
}
