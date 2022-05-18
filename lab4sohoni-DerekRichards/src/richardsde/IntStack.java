/*
 * Course: CS2852
 * Spring 2021
 * Lab 4 - Call Stack
 * Name: Derek Richards
 * Created: 4/2/2021
 */

package richardsde;

import java.util.EmptyStackException;

/**
 * An implementation of a Stack ADT in the form of a LinkedList.
 */
public class IntStack {


    private static class Node<T> {
        private T data;
        private Node<T> next;

        /**
         * Creates node without a next node.
         * @param dataItem The item to be stored.
         */
        private Node(T dataItem){
            this.data = dataItem;
            this.next = null;
        }

        /**
         * Creates node with next item.
         * @param dataItem The item to be stored.
         * @param next The next item.
         */
        private Node(T dataItem, Node<T> next){
            this.data = dataItem;
            this.next = next;
        }
    }

    private Node<Integer> head;

    /**
     * Pushes a number to the stack.
     * @param value The integer to be pushed.
     */
    public void push(int value){
        Node<Integer> first = head;
        head = new Node<>(value, first);
    }

    /**
     * Obtains the top number of the stack.
     * @return The top number of the stack.
     * @throws EmptyStackException If the stack is empty.
     */
    public int peek() throws EmptyStackException{
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return head.data;
    }

    /**
     * Pops the top number off of the stack.
     * @return The top number of the stack.
     * @throws EmptyStackException If the stack is empty.
     */
    public int pop() throws EmptyStackException{
        if (isEmpty()){
            throw new EmptyStackException();
        }

        int num = head.data;
        head = head.next;

        return num;
    }

    public boolean isEmpty(){
        return head == null;
    }

    /**
     * Goes throughout all of the numbers in the stack and counts the amount of numbers.
     * @return The total amount of elements.
     */
    public int size(){
        int count = 0;
        Node<Integer> current = head;

        while (current != null){
            count++;
            current = current.next;
        }

        return count;
    }

    /**
     * Displays the contents of the stack.
     * @return A string containing the current contents of the stack.
     */
    @Override
    public String toString(){
        String str = "";
        str += "|          |";
        str += "\n|----------|";
        int size = size();

        Node<Integer> current = head;
        for (int i = 0; i < size; i++){
            str += "\n|         " + current.data + "|";
            current = current.next;
        }

        str += "\n+----------+";
        return str;
    }
}