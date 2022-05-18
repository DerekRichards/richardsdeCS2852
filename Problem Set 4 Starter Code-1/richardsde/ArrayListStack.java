/*
 * Course: CS2852
 * Spring 2021
 * Problem Set 4
 * Name: Derek Richards
 * Created: 4/1/2021
 */
package richardsde;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class that implements the PureStack interface using an ArrayList.
 * @param <E> A type of object to hold.
 */
public class ArrayListStack<E> implements PureStack<E> {
    private ArrayList<E> stack;

    /**
     * Constructor for the class that initializes the ArrayList.
     */
    public ArrayListStack(){
        stack = new ArrayList<>();
    }

    /**
     * Tells the user if the stack is empty using the ArrayList isEmpty() method.
     * @return True if the stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Pushes an element to the top of the stack.
     * @param element The element to be pushed.
     */
    @Override
    public void push(E element) {
        stack.add(0, element);
    }

    /**
     * Pops an element off of the top of the stack.
     * @return The element that was popped off.
     * @throws NoSuchElementException If there is no element to pop off.
     */
    @Override
    public E pop() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException("Cannot pop from an empty stack");
        }
        return stack.remove(0);
    }

    /**
     * Returns (but does not pop off) the element at the top of the stack.
     * @return The element at the top of the stack.
     * @throws NoSuchElementException If there is no element to pop off.
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException("Cannot peek from an empty stack");
        }
        return stack.get(0);
    }
}
