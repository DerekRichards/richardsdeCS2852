/*
 * Course: CS2852
 * Spring 2021
 * Lab 4 - Call Stack
 * Name: Derek Richards
 * Created: 4/2/2021
 */

package richardsde;

/**
 * This class attempts to simulate the call stack in the Java Virtual Machine
 */
public class ProgramStack {
    private IntStack stack;

    /**
     * The constructor for the ProgramStack class.
     */
    public ProgramStack(){
        stack = new IntStack();
    }

    /**
     * For a method call, pushes the program counter,
     * number of arguments, and stack frame size to the stack.
     * @param name The name of the method being called.
     * @param arguments The arguments being passed to the method.
     */
    public void callMethod(String name, int... arguments){
        int programCounter = methodToProgramCounter(name, arguments);
        stack.push(programCounter);
        int stackFrameSize = 1;
        for(int argument: arguments){
            if (argument != 0){
                stack.push(argument);
                stackFrameSize++;
            }
        }
        stack.push(stackFrameSize);
    }

    /**
     * Pops the top stack frame off of the stack.
     */
    public void returnFromMethod(){
        int size = stack.pop();
        for (int i = 0; i < size; i++){
            stack.pop();
        }
    }

    /**
     * Pops the top stack frame off of the stack,
     * adds the returnValue to the next stack frame (if it exists),
     * and pushes the new size of the next stack frame of the stack.
     * @param returnValue The value returned by the method.
     */
    public void returnFromMethod(int returnValue){
        int size = stack.pop();
        for (int i = 0; i < size; i++){
            stack.pop();
        }
        if (!stack.isEmpty()){
            size = stack.pop() + 1;
            stack.push(returnValue);
            stack.push(size);
        }
    }

    private int methodToProgramCounter(String name, int... arguments){
        int numArguments = arguments.length;
        int programCounter = 0;

        for (int i = 0; i < name.length(); i++){
            char c = name.charAt(i);
            programCounter += c;
            programCounter *= 2;
        }
        if (arguments.length > 0){
            programCounter++;
        }

        return programCounter;
    }

    /**
     * Displays the contents of the stack using the IntStack's toString method.
     * @return A string containing the contents of the stack.
     */
    public String toString(){
        return stack.toString();
    }
}