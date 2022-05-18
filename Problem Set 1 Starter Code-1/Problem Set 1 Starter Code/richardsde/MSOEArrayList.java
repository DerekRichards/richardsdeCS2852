/*
 * Course: CS2852
 * Spring 2019
 * Lecture 2 - Implementing an ArrayList
 * Name: Dr. RJ Nowling
 * Created: 2019-03-05
 */

package richardsde;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/**
 * A list implemented with an array
 *
 * @param <E> element type
 */
public class MSOEArrayList<E> implements List<E>, RandomAccess {

    int numElements = 0;
    E[] array = (E[]) new Object[0];

    private void checkIndexOutOfBounds(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public boolean add(E e) {
        E[] newArray = (E[]) new Object[size() + 1];

        for(int i = 0; i < size(); i++) {
            newArray[i] = array[i];
        }
		newArray[size()] = e;

        numElements++;
        array = newArray;
        return true;
    }

    @Override
    public void clear() {
        array = (E[]) new Object[0];
        numElements = 0;
    }

    @Override
    public E get(int index) {
        checkIndexOutOfBounds(index);

        return array[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndexOutOfBounds(index);

        E original = get(index);

        array[index] = element;

        return original;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size(); i++) {
            if(array[i].equals(o)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < size(); i++) {
            if(array[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }


    // Homework
    @Override
    public void add(int index, E element) {
        checkIndexOutOfBounds(index);

        E[] newArray = (E[]) new Object[size() + 1];

        for(int i = 0; i < index; i++) {
            newArray[i] = array[i];
        }
        newArray[index] = element;
        for (int i = index; i < size(); i++){
            newArray[i + 1] = array[i];
        }

        numElements++;
        array = newArray;

    }

    @Override
    public E remove(int index) {
        checkIndexOutOfBounds(index);

        E original = null;

        E[] newArray = (E[]) new Object[size() - 1];
        for (int i = 0; i < size() - 1; i++){
            if (i == index){
                original = get(index);
                newArray[i] = array[i + 1];
            } else if(i > index){
                newArray[i] = array[i + 1];
            } else {
                newArray[i] = array[i];
            }

        }

        numElements--;
        array = newArray;
        return original;
    }

    @Override
    public boolean remove(Object o) {
        if (indexOf(o) == -1){
            return false;
        } else {
            E[] newArray = (E[]) new Object[size() - 1];
            for (int i = 0; i < size() - 1; i++){
                if (i == indexOf(o)){
                    newArray[i] = array[i + 1];
                } else {
                    newArray[i] = array[i];
                }
            }

            numElements--;
            array = newArray;
            return true;
        }

    }

    // We are not implementing any of the methods below



    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
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
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
}
