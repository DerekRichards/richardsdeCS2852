/*
 * Course: CS2852 - 071
 * Spring 2021
 * Lab 2 - Connect the Dots
 * Name: Derek Richards
 * Created: 3/21/2021
 */
package richardsde;

import java.util.Scanner;

/**
 * A class representing a dot.
 */
public class Dot {

    private double x;
    private double y;
    private double criticalValue;

    /**
     * The constructor for the Dot class
     * @param in A scanner object.
     */
    public Dot(Scanner in){
        String line = in.nextLine();
        //String xCoordinate = in.next();
        this.x = Double.parseDouble(line.substring(0, line.indexOf(",")));
        //String yCoordinate = in.next();
        this.y = Double.parseDouble(line.substring(line.indexOf(",") + 1));
    }

    private static double distance(Dot a, Dot b){
        // Use the Pythagorean Theorem to find the distance
        return Math.sqrt(Math.pow((Math.abs(a.x - b.x)), 2)
                + Math.pow((Math.abs(a.y - b.y)), 2));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Calculates the critical value of the dot.
     * @param previous The dot preceding the current one.
     * @param next The dot after the current one.
     */
    public void setCriticalValue(Dot previous, Dot next){
        criticalValue = distance(previous, this) + distance(this, next) - distance(previous, next);
    }

    public double getCriticalValue() {
        return criticalValue;
    }
}