package richardsde;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ArrayListStack<E> implements PureStack<E> {
    private ArrayList<E> stack;

    public ArrayListStack(){
        stack = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void push(E element) {
        stack.add(0, element);
    }

    public E pop() throws NoSuchElementException{
        if (isEmpty()){
            throw new NoSuchElementException("Cannot pop from an empty stack.");
        }
        return stack.remove(0);
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()){
            throw new NoSuchElementException("Cannot peek from an empty stack.");
        }
        return stack.get(0);
    }
}
