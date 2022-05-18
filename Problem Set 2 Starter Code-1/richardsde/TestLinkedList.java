/*
 * Course: CS2852
 * Spring 2019
 * Lecture 2 - Implementing an ArrayList
 * Name: Dr. RJ Nowling
 * Created: 2019-03-05
 */

package richardsde;

import java.util.LinkedList;
import java.util.List;

/**
 * Tests for a list implementation
 */
public class TestLinkedList {

    public static void main(String[] args) {
        // Change the list type to test the RJList
        // You should get the same results with
        // both lists.
        List<String> myList = new LinkedList<>();
        //List<String> myList = new MSOELinkedList<>();

        // test isEmpty when empty
        System.out.println("The list is empty: " + myList.isEmpty());
        System.out.println("The list size is: " + myList.size());
        System.out.println();

        // Testing adding a singe element
        myList.add("Dr. Nowling");
        System.out.println("Added a single element");

        System.out.println("The list is empty: " + myList.isEmpty());
        System.out.println("The list size is: " + myList.size());
        System.out.println();

        // Test clearing the list
        myList.clear();
        System.out.println("Cleared the list.");

        System.out.println("The list is empty: " + myList.isEmpty());
        System.out.println("The list size is: " + myList.size());
        System.out.println();

        // Add names to the list
        myList.add("Dr. Nowling");
        myList.add("Dr. Hasker");
        myList.add("Dr. Yoder");
        System.out.println("Added 3 names to the list.");

        // test isEmpty when empty
        System.out.println("The list is empty: " + myList.isEmpty());

        // check that the size is correct
        System.out.println("The size is: " + myList.size());

        // check the iterator
        for(String element : myList) {
            System.out.println("Element " + element);
        }
        System.out.println();


        // check clear
        myList.clear();
        System.out.println("Cleared the list.");
        System.out.println("The list is empty: " + myList.isEmpty());
        System.out.println("The size is: " + myList.size());
        System.out.println();

        // Tests for homework

        myList.add("Dr. Nowling");
        myList.add("Dr. Hasker");
        myList.add("Dr. Yoder");

        // check the get method
        for(int i = 0; i < myList.size(); i++) {
            System.out.println(i + " " + myList.get(i));
        }

        // check the set method
        // change the second element
        myList.set(1, "Dr. Taylor");
        System.out.println("Set element 1 to Dr. Taylor.");
        System.out.println("Element 1 is now: " + myList.get(1));
        System.out.println();

        // remove the center name
        myList.remove(1);
        System.out.println("The size is: " + myList.size());
        System.out.println("The first item is: " + myList.get(0));
        System.out.println("The second item is: " + myList.get(1));

        myList.remove("Dr. Nowling");
        myList.remove("Dr. Yoder");
        System.out.println("The size is: " + myList.size());
        System.out.println("The list is empty: " + myList.isEmpty());

    }

}
