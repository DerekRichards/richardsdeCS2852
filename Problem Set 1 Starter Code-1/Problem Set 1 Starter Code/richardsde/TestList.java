/*
 * Course: CS2852
 * Spring 2019
 * Lecture 2 - Implementing an ArrayList
 * Name: Dr. RJ Nowling
 * Created: 2019-03-05
 */

package richardsde;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for a list implementation
 */
public class TestList {

    public static void main(String[] args) {
        // Change the list type to test the RJList
        // You should get the same results with
        // both lists.
        // List<String> myList = new ArrayList<>();
        List<String> myList = new MSOEArrayList<>();

        // test isEmpty when empty
        System.out.println("The list is empty: " + myList.isEmpty());


        // Add names to the list
        myList.add("Dr. Nowling");
        myList.add("Dr. Hasker");
        myList.add("Dr. Yoder");

        // test isEmpty when empty
        System.out.println("The list is empty: " + myList.isEmpty());

        // check that the size is correct
        System.out.println("The size is: " + myList.size());

        // check the get method
        for(int i = 0; i < myList.size(); i++) {
            System.out.println("Element " + i + " is " + myList.get(i));
        }

        // check the set method
        myList.set(1, "Dr. Taylor");
        System.out.println("Element 1 is now: " + myList.get(1));

        // check clear
        myList.clear();
        System.out.println("The list is empty: " + myList.isEmpty());
        System.out.println("The size is: " + myList.size());

        // Tests for homework

        // add more names
        myList.add("Dr. Nowling");
        myList.add(0, "Dr. Hasker");
        myList.add("Dr. Yoder");

        // remove the center name
        myList.remove(1);
        System.out.println("The size is: " + myList.size());
        System.out.println("The first item is: " + myList.get(0));
        System.out.println("The second item is: " + myList.get(1));

        myList.remove("Dr. Hasker");
        myList.remove("Dr. Yoder");
        System.out.println("The size is: " + myList.size());
        System.out.println("The list is empty: " + myList.isEmpty());

    }

}
