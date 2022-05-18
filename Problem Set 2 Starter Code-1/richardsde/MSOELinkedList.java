package richardsde;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Implementation of a singly-linked list.
 * @param <E> type of data the list holds
 */
public class MSOELinkedList<E> implements List<E> {

    private static class Node<T> {
        private T data;
        private Node<T> next;

        /**
         * Create Node with no next node
         *
         * @param dataItem
         */
        private Node(T dataItem) {
            this.data = dataItem;
            this.next = null;
        }

        /**
         * Create Node with next item
         *
         * @param dataItem
         */
        private Node(T dataItem, Node<T> next) {
            this.data = dataItem;
            this.next = next;
        }
    }

    // if first is null, then the list is empty
    private Node<E> first;

    // public interface

    @Override
    public int size() {
        int count = 0;
        Node<E> current = first;
        while(current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public void clear() {
        first = null;
    }

    @Override
    public void add(int index, E element) {
        int size = size();
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        /* Find nodes before and after new node */
        int currentIndex = 0;
        Node<E> current = first;
        Node<E> prev = null;
        while(current != null && currentIndex < index) {
            prev = current;
            current = current.next;
            currentIndex++;
        }

        Node<E> newNode = new Node<>(element, current);

        /*
         * If we insert at the front, prev will be null.
         * In this case, we are creating a new first node. */
        if (prev == null) {
            first = newNode;
        } else {
            prev.next = newNode;
        }
    }

    /**
     * Adds an element to the end of the list.
     *
     * @param element value to add
     * @return will always return true
     */
    @Override
    public boolean add(E element) {
        add(size(), element);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public int indexOf(Object o) {
        int index = -1;
        int currentIndex = 0;
        Node<E> current = first;
        while(current != null) {
            if(current.data.equals(o)) {
                index = currentIndex;
            }
            currentIndex++;
            current = current.next;
        }

        return index;
    }

    /**
     * Returns an iterator over the list. More efficient
     * than calling get() repeatedly.
     * @return returns an instance of Iterator<E>
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }


    // homework

    /**
     * Gets the element in the node at position index.
     *
     * Checks bounds on the index and throws an unchecked
     * exception.
     *
     * @param index Index of desired element, between 0 and size - 1.
     * @return element at index
     */
    @Override
    public E get(int index) {
        //throw new UnsupportedOperationException();
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Invalid index");
        } else{
            Node<E> current = first;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            return current.data;
        }

    }

    /**
     * Overwrites the element in the node at position index.
     *
     * Checks bounds on the index and throws an unchecked
     * exception.
     *
     * @param index Index to overwrite, between 0 and size - 1.
     * @param element New element to store
     * @return Returns original element at index
     */
    @Override
    public E set(int index, E element) {
        //throw new UnsupportedOperationException();
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Invalid Index");
        } else {
            Node<E> current = first;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            E object = current.data;
            current.data = element;
            return object;
        }


    }

    /**
     * Removes the position at the given index.  Remaining
     * elements are shifted up.
     *
     * Checks bounds on the index and throws an unchecked
     * exception.
     *
     * @param index Index to remove, between 0 and size - 1.
     * @return Returns removed element at index
     */
    @Override
    public E remove(int index) {
        //return null;
        if (index < 0 || index >= size()){
            throw new IndexOutOfBoundsException("Invalid Index");
        } else {
            Node<E> current = first;
            for (int i = 0; i < index - 1; i++){
                current = current.next;
            }
            E object = current.next.data;
            current.next = current.next.next;
            return object;
        }
    }

    /**
     * Removes the given object if it is in the list.  Remaining
     * elements are shifted up.
     *
     * @param o Object to remove if it is in the list
     * @return Returns a boolean indicating whether the element was found and removed
     */
    @Override
    public boolean remove(Object o) {
        //return false;
        boolean removed = false;
        if (contains(o)){
            remove(indexOf(o));
            removed = true;
        }
        return removed;
    }

    // not implementing

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
