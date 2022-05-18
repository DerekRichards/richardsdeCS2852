/*
 * Course: CS2852-071
 * Spring 2021
 * Problem Set 5
 * Name: Derek Richards
 * Created: 4/10/2021
 */
package richardsde;

public class Driver {
    public static void main(String[] args) {
        //countDown(10);
        //countDown2(10);
        //System.out.println(power2(10));
        //countUp(1, 10);
        System.out.println(sum(10));
    }

    public static void countDown(int n) {
        System.out.println(n + "…");
        if(n > 1) {
            countDown(n - 1);
        }
    }

    public static void countDown2(int n) {
        System.out.println(n + "…");
        countDown2(n - 1);
    }

    public static long power2(int n) {
        System.out.println("Finding the power of 2 for " + n);
        long power;
        if(n == 0) {
            power = 1;
        } else {
            power = 2 * power2(n - 1);
        }
        System.out.println("The power is " + power);
        return power;
    }

    public static void countUp(int i, int end){
        if (end < i){
            System.out.println("Those values are invalid");
        } else if (i == end){
            System.out.println("End reached (" + i + ")");
        } else {
            System.out.println("Counting up from " + i);
            countUp(i + 1, end);
        }
    }

    public static int sum(int n){
        int sum = 0;
        if (n <= 0){
            sum = 0;
        } else {
            sum = n + sum(n - 1);
        }
        return sum;
    }


}
