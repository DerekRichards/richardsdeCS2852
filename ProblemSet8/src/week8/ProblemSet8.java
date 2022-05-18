/*
 * Course: CS2852-071
 * Spring 2021
 * Problem Set 8
 * Name: Derek Richards
 * Created: 4/29/2021
 */
package week8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class ProblemSet8 {
    private static List<String> classStandings = new ArrayList<>();

    public static void main(String[] args) {
        //countClassStandings();
        calculateHashCodes("Brewers");
        calculateHashCodes("Dodgers");
        calculateHashCodes("Braves");
        calculateHashCodes("Cubs");
        calculateHashCodes("Rays");
    }

    private static void countClassStandings() {
        classStandings.add("Sophomore");
        classStandings.add("Freshman");
        classStandings.add("Sophomore");
        classStandings.add("Sophomore");
        classStandings.add("Sophomore");
        classStandings.add("Sophomore");
        classStandings.add("Junior");
        classStandings.add("Junior");
        classStandings.add("Sophomore");
        classStandings.add("Sophomore");
        classStandings.add("Junior");
        classStandings.add("Freshman");
        classStandings.add("Sophomore");
        classStandings.add("Freshman");
        classStandings.add("Sophomore");
        classStandings.add("Junior");
        classStandings.add("Sophomore");
        classStandings.add("Sophomore");
        classStandings.add("Freshman");
        classStandings.add("Sophomore");

        Map<String, Integer> classCounts = new TreeMap<>();

        for (String standing : classStandings){
            int count = classCounts.getOrDefault(standing, 0);
            classCounts.put(standing, count + 1);
        }

        for (Map.Entry<String, Integer> classCount : classCounts.entrySet()){
            String output = classCount.getValue() + " students are in the "
                    + classCount.getKey() + " class";
            System.out.println(output);
        }
    }

    private static void calculateHashCodes(String input){
        int hashCode = input.hashCode();
        System.out.println("The hashCode for " + input + " is " + hashCode);
        System.out.println("Absolute value: " + Math.abs(hashCode));
        int idx = Math.abs(hashCode) % 4;
        System.out.println("The index for an array of length 4 is " + idx);
        idx = Math.abs(hashCode) % 16;
        System.out.println("The index for an array of length 16 is " + idx);
        idx = Math.abs(hashCode) % 64;
        System.out.println("The index for na array of length 64 is " + idx);
    }
}
