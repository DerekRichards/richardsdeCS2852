/*
 * Course: CS2852
 * Spring 2021
 * Lab 6 - Smaller/Bigger Sort
 * Name: Derek Richards
 * Created: 4/16/2021
 */
package richardsde;

import java.util.List;

/**
 * A class with methods used to help sort a list.
 */
public class SmallerBiggerSort {

    /**
     * Sorts the list so that all elements smaller than or equal to the former first element
     * are before it and all elements that are bigger than or equal to it are after it.
     * Returns the new index of that element.
     * @param list The list to be sorted.
     * @param start The place to start sorting from.
     * @param end The place to stop sorting.
     * @param <T> A variable that implements the Comparable interface.
     * @return The new index of the starting element.
     */
    public static <T extends Comparable<T>> int smallerBigger(List<T> list, int start, int end){
        int index = start;
        boolean foundBigger = false;
        boolean foundSmaller = false;
        int smallerIndex = start;
        int biggerIndex = start;

        if (!list.isEmpty() && start < end){
            T first = list.get(start);

            // Tries to find a value bigger than the starting value
            for (int i = start; i < end && (!foundBigger); i++){
                T current = list.get(i);
                int compare = current.compareTo(first);
                if (compare > 0){
                    smallerIndex = i;
                    foundBigger = true;
                }
            }

            // Tries to find a value smaller than the starting value, starting at the end.
            for (int i = end - 1; i > start && (!foundSmaller); i--){
                T current = list.get(i);
                int compare = current.compareTo(first);
                if (compare < 0){
                    biggerIndex = i;
                    foundSmaller = true;
                }
            }

            // If the index of the smaller value is greater than the index of the bigger value,
            // meaning that the smaller value is further in the list.
            if (biggerIndex > smallerIndex){
                T small = list.get(biggerIndex);
                T big = list.get(smallerIndex);
                list.set(smallerIndex, small);
                list.set(biggerIndex, big);
                if (smallerIndex != start){
                    list.set(start, small);
                    list.set(smallerIndex, first);
                }

                index = smallerIndex;
            }

        }

        return index;
    }

    /**
     * The recursive sorting method. May be called by the default sort method. Calls the
     * smallerBigger() method and recursively calls itself using the index returned.
     * @param list The list to be sorted.
     * @param start The part of the list to start sorting from.
     * @param end The part of the list to stop sorting from.
     * @param <T> A variable type that implements the Comparable interface.
     */
    public static <T extends Comparable<T>> void sort(List<T> list, int start, int end){
        if (start < end){
            int index = smallerBigger(list, start, end);
            sort(list, start, index);
            sort(list, index + 1, end);
        }

    }

    /**
     * The default sorting method. Calls the recursive sort method and uses
     * the start and beginning of the list as the boundaries of the list.
     * @param list The list to be sorted.
     * @param <T> A variable type that implements the Comparable interface.
     */
    public static <T extends Comparable<T>> void sort(List<T> list){
        sort(list, 0, list.size());
    }
}