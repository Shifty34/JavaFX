package com.example.assignment3;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;


import javafx.scene.canvas.GraphicsContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class HistogramAlphabet {

    Map<Character, Integer> frequency = new HashMap<>();
    Map<Character, Double> probability = new HashMap<>();


    HistogramAlphabet(Map<Character, Integer> frequency, Map<Character, Double> probability) {
        this.frequency = frequency;
        this.probability = probability;
    }

    HistogramAlphabet() {
        this.frequency = this.getFrequency();
        this.probability = this.getProbability();
    }


    HistogramAlphabet(String text) {

        String w = text.replaceAll("[^a-zA-Z]", "").toLowerCase();

        for (int i = 0; i < w.length(); i++) {
            Character key = w.charAt(i);
            IncrementFrequency(frequency, key);
        }


    }

    public Map<Character, Integer> getFrequency() {
        return frequency;
    }

    public Integer getCumulativeFrequency() {
        return frequency.values().stream().reduce(0, Integer::sum);
    }

    public Map<Character, Integer> sortDownFrequency() {

        return frequency
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }

    public Map<Character, Double> getProbability() {

        double inverseCumulativeFrequency = 1.0 / getCumulativeFrequency();
        for (Character Key : frequency.keySet()) {
            probability.put(Key, frequency.get(Key).doubleValue() * inverseCumulativeFrequency);
        }
        return probability.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }

    public Double getSumOfProbability() {
        return probability.values().stream().reduce(0.0, Double::sum);
    }

    @Override
    public String toString() {

        String output = "Frequency of Characters\n\n";
        for (Character K : frequency.keySet()) {
            output += K + ": " + frequency.get(K) + "\n";
        }

        return output;

    }

    public static <K> void IncrementFrequency(Map<K, Integer> m, K Key) {
        m.putIfAbsent(Key, 0);
        m.put(Key, m.get(Key) + 1);
    }


    public class myPieChart {

        Map<Character, Slice> slicePieChart;
        public Map<Character, Slice> getMyPieChart;


        int N;
        myPoint center;
        int radius;
        double rotateAngle;

        myColor[] color;


        myPieChart(int N, myPoint center, int radius, double rotateAngle, myColor[] color) {
            this.N = N;
            this.center = center;
            this.radius = radius;
            this.color = color;
            this.rotateAngle = Optional.ofNullable(rotateAngle).orElse(0.0);

            probability = getProbability();
            slicePieChart = getMyPieChart();
        }

        public myPieChart(int n, myPoint center, int radius, double rotateAngle) {

        }


        public Map<Character, Slice> getMyPieChart() {

            myColor[] colors = myColor.getMyColors();

            Random rand = new Random();
            int colorsSize = colors.length;

            double startAngle = rotateAngle;
            for (Character Key : probability.keySet()) {

                double angle = 360.0 * probability.get(Key);
                slicePieChart.put(Key, new Slice(center, radius, startAngle, angle, colors[rand.nextInt(colorsSize)]));
            }
            return slicePieChart;
        }

        public void Draw(GraphicsContext gc) {
            Map<Character, Integer> sortedFrequency = sortDownFrequency();
            for(Character Key: sortedFrequency.keySet()){
                slicePieChart.get(Key).Draw(gc);
            }


            }


        }


    }





