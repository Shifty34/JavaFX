package com.example.project_4;
import javafx.scene.canvas.GraphicsContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HistogramAlphaBet {

    private Map<Character, Integer> freq = new HashMap<>();
    Map<Character, Double> probability = new HashMap<>();


    // Constructors
    HistogramAlphaBet() { }

    // Accepts a String Val
    HistogramAlphaBet(String text) {
        String x = text.replaceAll("[^a-zA-Z]]", "").toLowerCase();
        for ( int i = 0; i < x.length(); i++ ){
            Character key = x.charAt(i);
            incrementFrequency(freq, key);
        }
    }

    // Accepts an HashMap
    HistogramAlphaBet(Map<Character, Integer> dbHashmap) {
        freq = dbHashmap;
    }

    // Get Methods
    public Map<Character, Integer> getFreq() { return freq; }
    public Integer getCumulativeFreq() { return freq.values().stream().reduce(0, Integer::sum); }

    /* Utility Functions */

    // Incrementing the frequency of each char
    private static<K> void incrementFrequency(Map<K, Integer> m, K key) {
        m.putIfAbsent(key, 0);
        m.put(key, m.get(key) + 1);
    }

    // Gets the Probability and Sorts Down at the same time
    public Map<Character, Double> getProbability() {
        double cumulativeFreq = 1.0 / getCumulativeFreq();
        for (Character k : freq.keySet()) {
            probability.put(k, (double) freq.get(k) * cumulativeFreq);
        }
        return probability
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }


    public String toString() {
        String out = "\nFrequency of each character:\n ";
        for (Character K: freq.keySet()) {
            out += K + ": " + freq.get(K) + "\n";
        }
        return out;
    }

    /*******************************    MY PIE CHART   *******************************/
    /*                                  MY PIE CHART                               */
    /*******************************    MY PIE CHART   *******************************/

    // MyPieChart Class
    class MyPieChart {

        int n;                  // Number of chars to display
        int radius;             // Radius of the pie chart
        double startAngle;      // Starting Angle set by user
        MyPoint centerPoint;    // Center of the pie chart
        Map<Character, Slice> slicedSections = new HashMap<Character, Slice>();

        MyPieChart (int n, MyPoint centerPoint, double startAngle, int radius) {
            this.n = n;
            this.centerPoint = centerPoint;
            this.radius = radius;
            this.startAngle = startAngle;
            probability = getProbability();
        }

        // GETTER METHODS

        // Gets the sum of all the probabilities
        public Double getSumOfProbability() {
            return probability.values().stream().reduce(0.0, Double::sum);
        }

        // Calculates and Draws the probabilities of the frequency in a descending order
        public void draw(GraphicsContext GC) {
            // Allocating all colors to an array
            MyColor [] colors = new MyColor[28];
            int i = 0;
            for (MyColor s : MyColor.values()) {
                colors[i] = s;
                i++;
            }

            // Drawing the legend title
            GC.setFill(colors[1].getColor());
            GC.fillText("LEGEND:", 10, 10);
            GC.fillText("__________", 10, 15);

            // Initializing Variables for the 'Other' Chars
            double RealAngleRest = startAngle; double sortedProbabilitySum = 0;

            // Puts all the data in a slicedSections Hashmap and draws
            int j = 2;
            int k = 0;

            // Sorting by Key
            TreeMap<Character, Double> sortedByVal = new TreeMap<>();
            sortedByVal.putAll(probability);

            for(Character key: sortedByVal.keySet()){
                double realAngle = 360 * sortedByVal.get(key);
                if ( k < n ) {
                    slicedSections.put(key, new Slice(centerPoint, radius, startAngle, realAngle, colors[j], key, sortedByVal.get(key)));
                    startAngle += realAngle;
                    slicedSections.get(key).draw(GC);
                }
                else {
                    RealAngleRest += realAngle; sortedProbabilitySum += sortedByVal.get(key);
                }
                j++; k++;
            }
            slicedSections.put('*', new Slice(centerPoint, radius, startAngle, RealAngleRest, colors[1], '*', sortedProbabilitySum));
            slicedSections.get('*').draw(GC);
        }
    }

}


