package richardsde;

import java.util.NoSuchElementException;

public interface PureStack<E> {
    E pop() throws NoSuchElementException;

    void push(E element);

    E peek() throws NoSuchElementException;

    boolean isEmpty();
}
