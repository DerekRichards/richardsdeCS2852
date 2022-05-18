/*
 * Course: CS2852
 * Spring 2021
 * Lab 6 - Smaller/Bigger Sort
 * Name: Derek Richards
 * Created: 4/18/2021
 */
package richardsde;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SmallerBiggerSortTest {

    @Test
    public void testCorrectIndexSpecificBoundaries(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(8);
        list.add(1);
        list.add(13);
        int index = SmallerBiggerSort.smallerBigger(list, 2, 6);
        assertEquals(4, index);
    }

    @Test
    public void testCorrectElementsSpecificBoundaries(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(8);
        list.add(1);
        list.add(13);
        int index = SmallerBiggerSort.smallerBigger(list, 2, 6);

        assertEquals(8, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(5, list.get(3));
        assertEquals(8, list.get(4));
        assertEquals(12, list.get(5));
        assertEquals(8, list.get(6));
        assertEquals(1, list.get(7));
        assertEquals(13, list.get(8));
    }

    @Test
    public void testCorrectIndexStandardBoundaries(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(8);
        list.add(18);
        list.add(13);
        int index = SmallerBiggerSort.smallerBigger(list, 0, list.size());
        assertEquals(4, index);
    }

    @Test
    public void testCorrectElementsStandardBoundaries(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(8);
        list.add(18);
        list.add(13);
        int index = SmallerBiggerSort.smallerBigger(list, 0, list.size());

        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(8, list.get(2));
        assertEquals(5, list.get(3));
        assertEquals(8, list.get(index));
        assertEquals(12, list.get(5));
        assertEquals(8, list.get(6));
        assertEquals(18, list.get(7));
        assertEquals(13, list.get(8));
    }

    @Test
    public void testSmallerBiggerOrderedList(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 25; i++){
            list.add(i);
        }

        SmallerBiggerSort.smallerBigger(list, 0, list.size());

        for (int i = 0; i < 25;i++){
            assertEquals(i, list.get(i));
        }
    }

    @Test
    public void testSmallerBiggerRandomList(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            list.add((int) (Math.random() * 20 + 1));
        }

        System.out.print("[");
        for (int i : list){
            System.out.print(i + ", ");
        }
        System.out.println("]");

        int index = SmallerBiggerSort.smallerBigger(list, 0, list.size());
        System.out.println(index);
        System.out.print("[");
        for (int i : list){
            System.out.print(i + ", ");
        }
        System.out.println("]");
    }

    @Test
    public void testSort(){
        long startTime = System.nanoTime();

        ArrayList<Integer> list = new ArrayList<>();
        list.add(8);
        list.add(3);
        list.add(8);
        list.add(5);
        list.add(12);
        list.add(1);
        list.add(8);
        list.add(18);
        list.add(13);
        SmallerBiggerSort.sort(list);

        long endTime = System.nanoTime();
        System.out.println("Time for sorting list: " + (endTime - startTime) +" ns");

        System.out.println(list.toString());
    }

    @Test
    public void testSortOrderedList(){
        long startTime = System.nanoTime();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 25; i++){
            list.add(i);
        }

        SmallerBiggerSort.sort(list);

        long endTime = System.nanoTime();
        System.out.println("Time for sorting an ordered list: " + (endTime - startTime) + " ns");
        for (int i = 0; i < list.size(); i++){
            assertEquals(i, list.get(i));
        }
    }

    @Test
    public void testSortRandomList(){
        long startTime = System.nanoTime();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            list.add((int) (Math.random() * 20 + 1));
        }

        System.out.print("[");
        for (int i : list){
            System.out.print(i + ", ");
        }
        System.out.println("]");

        SmallerBiggerSort.sort(list);

        long endTime = System.nanoTime();
        System.out.println("Time to sort a randomly-generated list: " +
                (endTime - startTime) + " ns");

        System.out.print("[");
        for (int i : list){
            System.out.print(i + ", ");
        }
        System.out.println("]");
    }
}