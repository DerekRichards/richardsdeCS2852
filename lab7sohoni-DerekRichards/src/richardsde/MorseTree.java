/*
 * Course: CS2852-071
 * Spring 2021
 * Lab 7- Morse Code Decoder
 * Name: Derek Richards
 * Created: 4/23/2021
 */
package richardsde;

import java.util.Optional;

/**
 * A binary tree representing a collection of elements found through morse code.
 * Dots represent the left direction, dashes represent the right direction.
 * @param <E> The type of data being stored in the tree.
 */
public class MorseTree<E> {
    private Node<E> root;

    private static class Node<E> {
        private Optional<E> value;
        private Node<E> lchild;
        private Node<E> rchild;

        private Node(E value){
            this.value = Optional.ofNullable(value);
            this.lchild = null;
            this.rchild = null;
        }

        private Node(E value, Node<E> lchild, Node<E> rchild){
            this.value = Optional.ofNullable(value);
            this.lchild = lchild;
            this.rchild = rchild;
        }

        private void setValue(E value){
            this.value = Optional.of(value);
        }
    }

    /**
     * The constructor of the MorseTree class.
     * This constructor creates a root node with null contents.
     */
    public MorseTree(){
        root = new Node<E>(null);
    }

    /**
     * Adds an element to the MorseTree by calling a recursive add method.
     * @param symbol The element to be added.
     * @param code The symbol in Morse Code.
     * @return True if the element was added, false otherwise.
     * @throws NullPointerException If a null symbol is passed into the method.
     */
    public boolean add(E symbol, String code) throws NullPointerException{
        if (symbol == null){
            throw new NullPointerException("Cannot add a null symbol.");
        }
        boolean added = false;
        added = add(symbol, code, root);
        return added;
    }

    /**
     * Recursively goes through the tree to add an element.
     * @param symbol The element to be added.
     * @param code The symbol in Morse Code.
     * @param subroot The current root node.
     * @return False if the string passed in has a length of 0
     * (because an empty string is not useful and the method will try
     * to decrease the length of the string in order to find where to put the element),
     * true if the element was successfully added, and false in any other case.
     * @throws IllegalArgumentException If the code has any characters other than '.' or '-'.
     */
    private boolean add(E symbol, String code, Node<E> subroot) throws IllegalArgumentException{
        if (code.length() == 0){
            return false;
        }

        boolean added = false;

        if (code.charAt(0) == '.'){
            // Moving to the left side of the tree/subtree.
            if (subroot.lchild == null){
                // If there is no left child, one is created.
                // If the code has more than one character remaining,
                // then it means that the end of the code has not been reached.
                // In this case, a null value is assigned to the child.

                if (code.length() == 1){
                    subroot.lchild = new Node<E>(symbol);
                    added = true;
                } else {
                    subroot.lchild = new Node<E>(null);
                    added = add(symbol, code.substring(1), subroot.lchild);
                }
            } else {
                // If there is an existing left child but the end of the code is reached,
                // then a new inner value is assigned to the current node.

                if (code.length() == 1){
                    subroot.lchild.setValue(symbol);
                    added = true;
                } else {
                    added = add(symbol, code.substring(1), subroot.lchild);
                }
            }
        } else if (code.charAt(0) == '-'){
            // Moving to the right side of the tree/subtree.
            if (subroot.rchild == null){
                // If there is no right child, one is create.
                // If the code has more than one character remaining,
                // then it means that the end of the code has not been reached.
                // In this case, a null value is assigned to the child.

                if (code.length() == 1){
                    subroot.rchild = new Node<E>(symbol);
                    added = true;
                } else {
                    subroot.rchild = new Node<E>(null);
                    added = add(symbol, code.substring(1), subroot.rchild);
                }
            } else {
                // If there is an existing right child but the end of the code is reached,
                // then a new inner value is assigned to the current node.

                if (code.length() == 1){
                    subroot.rchild.setValue(symbol);
                    added = true;
                } else {
                    added = add(symbol, code.substring(1), subroot.rchild);
                }
            }
        } else {
            throw new IllegalArgumentException("Not a piece of morse code.");
        }

        return added;
    }

    /**
     * Given a string of Morse Code, this method finds attempts to find
     * an element that is represented by that string of morse code.
     * This method will only work if the element represented by the code is already in the tree.
     * @param code The code representing the element.
     * @return An Optional object containing the value of the element if it is found.
     * Otherwise, and empty Optional object is returned, either because the end of
     * the string was reached or because the element is not in the tree.
     */
    public Optional<E> decode(String code){
        Optional<E> decodedElement;
        decodedElement = decode(code, root);
        return decodedElement;
    }

    /**
     * This method recursively calls itself until the
     * element is found or the end of the string is reached.
     * @param code The morse code representing the element.
     * @param subroot The current root node of the tree/subtree.
     * @return An Optional object containing the value of the element if it is found.
     * Otherwise, an empty Optional object is returned, either because the end of
     * the string was reached or because the element is not in the tree.
     * @throws IllegalArgumentException If the code has any characters other than '.' or '-'.
     */
    private Optional<E> decode(String code, Node<E> subroot) throws IllegalArgumentException{
        if (code.length() == 0){
            return Optional.empty();
        }

        Optional<E> decodedElement;

        if (code.charAt(0) == '.'){
            if (code.length() == 1){
                if (subroot.lchild != null){
                    decodedElement = subroot.lchild.value;
                } else {
                    decodedElement = Optional.empty();
                }
            } else {
                if (subroot.lchild != null){
                    decodedElement = decode(code.substring(1), subroot.lchild);
                } else {
                    decodedElement = Optional.empty();
                }
            }
        } else if (code.charAt(0) == '-'){
            if (code.length() == 1){
                if (subroot.rchild != null){
                    decodedElement = subroot.rchild.value;
                } else {
                    decodedElement = Optional.empty();
                }
            } else {
                if (subroot.rchild != null){
                    decodedElement = decode(code.substring(1), subroot.rchild);
                } else {
                    decodedElement = Optional.empty();
                }
            }
        } else {
            throw new IllegalArgumentException("Not a piece of morse code");
        }

        return decodedElement;
    }


}