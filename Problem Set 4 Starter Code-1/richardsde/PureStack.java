/*
 * Course: CS2852
 * Spring 2021
 * Problem Set 4
 * Name: Derek Richards
 * Created: 4/1/2021
 */
package richardsde;

import java.util.NoSuchElementException;

/**
 * An interface based on the Stack ADT
 * @param <E> A type of element to hold.
 */
public interface PureStack<E> {
    /**
     * Pops the top element off of the stack.
     * @return The element.
     * @throws NoSuchElementException If the stack has no top element.
     */
    E pop() throws NoSuchElementException;

    /**
     * Pushes an element onto the stack.
     * @param element The element to be pushed.
     */
    void push(E element);

    /**
     * Grabs (but does not pop off) the top element from the stack.
     * @return The element.
     * @throws NoSuchElementException If the stack has no top element.
     */
    E peek() throws NoSuchElementException;

    /**
     * Tells the user if the stack is empty.
     * @return True if the stack is empty, false otherwise.
     */
    boolean isEmpty();
}
