/*
 * Course: CS2852
 * Spring 2021
 * Problem Set 4
 * Name: Derek Richards
 * Created: 4/1/2021
 */
package richardsde;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Tests classes that implement the TestPureStack interface.
 */
public class TestPureStack {
    public static void main(String[] args) {
        PureStack<String> stack = new ArrayListStack<>();

        List<String> petNames = new ArrayList<>();
        petNames.add("Abby");
        petNames.add("Chloe");
        petNames.add("Lyndsey");
        petNames.add("Slice");

        List<String> reversedNames = new ArrayList<>(petNames);
        Collections.reverse(reversedNames);

        // should initially be empty
        System.out.println("Stack reports empty: " + stack.isEmpty());

        // add items to the stack
        for(String s : petNames) {
            stack.push(s);
            System.out.println("Peek returns correct value: " + stack.peek().equals(s));
        }

        // stack should not be empty anymore
        System.out.println("Stack reports not empty: " + !stack.isEmpty());

        // pop items from the stack
        int i = 0;
        while(!stack.isEmpty()) {
            String name = stack.pop();
            boolean matches = name.equals(reversedNames.get(i));
            System.out.println("String " + name + " returned in expected order: " + matches);
            i++;
        }
        System.out.println("Stack returned expected number of elements: " + (i == reversedNames.size()));

        // check if empty again
        System.out.println("Stack is now empty: " + stack.isEmpty());

        // check peek exceptions
        try {
            stack.peek();
            System.out.println("Stack.peek() didn't throw exception when expected!");
        } catch(NoSuchElementException e) {
            System.out.println("Stack.peek() threw NoSuchElementException as expected!");
        } catch(Exception e) {
            System.out.println("Stack.peek() threw the wrong type of exception!");
        }

        // check pop exceptions
        try {
            stack.pop();
            System.out.println("Stack.pop() didn't throw exception when expected!");
        } catch(NoSuchElementException e) {
            System.out.println("Stack.pop() threw NoSuchElementException as expected!");
        } catch(Exception e) {
            System.out.println("Stack.pop() threw the wrong type of exception!");
        }
    }
}
