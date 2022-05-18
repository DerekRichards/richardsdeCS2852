/*
 * Course: CS2852
 * Spring 2021
 * Lab 4 - Call Stack
 * Name: Derek Richards
 * Date: 4/2/2021
 */
package richardsde;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class IntStackTest {

    private IntStack stack = new IntStack();

    @BeforeEach
    public void setUp() {
        assertTrue(stack.isEmpty());
    }

    @AfterEach
    public void tearDown() {
        stack = new IntStack();
    }


    @Test
    public void push(){
        stack.push(2);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void peek(){
        stack.push(2);
        stack.push(3);
        int top = stack.peek();
        assertEquals(3, top);
    }

    @Test
    public void testSize(){
        stack.push(1);
        stack.push(0);
        stack.push(1);
        stack.push(0);
        stack.push(1);
        assertEquals(5, stack.size());
    }

    @Test
    public void testEmptySize(){
        assertEquals(0, stack.size());
    }

    @Test
    public void pop(){
        stack.push(0);
        stack.push(1);
        int top = stack.pop();
        int secondNum = stack.pop();
        assertEquals(1, top);
        assertEquals(0, secondNum);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void peekEmptyStack(){
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    @Test
    public void popEmptyStack(){
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    public void testToString(){
        stack.push(1);
        int firstValue = stack.peek();
        stack.push(2);
        int secondValue = stack.peek();
        stack.push(3);
        int thirdValue = stack.peek();
        String str = "";
        str += "|----------|";
        str += "\n|----------|";
        str += "\n|         " + thirdValue + "|";
        str += "\n|         " + secondValue + "|";
        str += "\n|         " + firstValue + "|";
        str += "\n+----------+";

        assertEquals(str, stack.toString());

    }

}