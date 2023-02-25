package com.example.assignment3;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Assignment3 extends Application {
    private static final myColor[] color = myColor.getMyColors();

    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene s = new Scene(root, 300, 300, Color.WHITE);
        stage.setTitle("Assignment 3");
        stage.setScene(s);
        stage.show();

}

    static String file = "/Users/iftheharahmed/Desktop/CSC22100/War and Peace.txt";
    static Scanner input;




    public static void openFile() {
        try{
            input = new Scanner(Paths.get(file));
        }
        catch(IOException ioException) {
            System.err.println("File is not found");
        }
    }


    public static void readFile() {

        try {
            String w = "";


            while (input.hasNext()) {

                w += input.nextLine().replaceAll("[^a-zA-Z]", "").toLowerCase();

            }

            System.out.println("\nNumber of Characters: " + w.length() + "\n");

            HistogramAlphabet H = new HistogramAlphabet(w);
            Map<Character, Integer> sortedFrequency = H.sortDownFrequency();

            sortedFrequency.forEach((K, V) -> System.out.println(K + ": " + V));
            System.out.println("\nCumulative Frequency: " + H.getCumulativeFrequency() + "\n");
            System.out.println("\nNumber of Characters: " + sortedFrequency.values().stream().reduce(0, (x,y)-> x +y)+ "\n");

            HistogramAlphabet.myPieChart piechart = H.new myPieChart(0, new myPoint(100,100), 50, 30.0);
            System.out.println(H.getProbability());
            System.out.println("\nSum of Probabilities: " + H.getSumOfProbability() + "\n");

            Map<Character, Slice> slices = piechart.getMyPieChart;
            sortedFrequency.forEach((K,V) -> System.out.println(K + ": " + slices.get(K)));

            double sumofangles = 0.0;
            for(Character key : slices.keySet()){ sumofangles += slices.get(key).getAngle();}
            System.out.println("\nSum of Angles: " + sumofangles);

        }

        catch (NoSuchElementException elementException) {


            System.err.println("Invalid Input! Terminating....");


        }

            catch (IllegalStateException stateException) {

            System.out.println("Error processing file! Terminating...");
        }




    }

    public static void closeFile(){
        if (input != null){
            input.close();
        }
    }


    public static void main(String[] args) {

        openFile();
        readFile();
        closeFile();
        launch();

    }
}