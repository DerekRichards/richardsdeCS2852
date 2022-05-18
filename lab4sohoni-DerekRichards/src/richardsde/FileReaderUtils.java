/*
 * Course: CS2852
 * Spring 2021
 * Lab 4 - Call Stack
 * Name: Derek Richards
 * Created: 4/2/2021
 */

package richardsde;

import java.util.Optional;
import java.util.OptionalInt;

/**
 * Creates a number of methods used to make reading input easier.
 */
public class FileReaderUtils {

    /**
     * Checks if a method is a void or not.
     * @param line The line to be analysed.
     * @return True if the statement contains only "return", false otherwise.
     */
    public static boolean isVoidReturn(String line){
        boolean isVoid = false;
        if (line.trim().equals("return")){
            isVoid = true;
        }
        return isVoid;
    }

    /**
     * If a method returns an int, this method finds
     * the value of the string when given a return statement.
     * @param line The line to be analysed (should be a return statement).
     * @return The integer value being returned by the return statement.
     */
    public static OptionalInt parseReturnValue(String line){
        OptionalInt optionalInt = OptionalInt.empty();
        final int numberStartLength = 8;
        final int numberStartIndex = 7;
        String trimmedLine = line.trim();

        // Checking that the line has a return statement and has a number after it.
        if (trimmedLine.indexOf("return") == 0){
            if (trimmedLine.length() >= numberStartLength){
                char startingDigit = trimmedLine.charAt(numberStartIndex);
                if (Character.isDigit(startingDigit)){
                    optionalInt = OptionalInt.of(Integer.parseInt(
                            trimmedLine.substring(numberStartIndex)));
                }
            }
        }

        return optionalInt;
    }

    /**
     * This method finds the name of a method.
     * @param line The line to be analysed (should be a method signature).
     * @return The name of the method.
     */
    public static Optional<String> parseMethodName(String line){
        String trimmedLine = line.trim();
        Optional<String> stringOptional = Optional.empty();

        // Checking that it is a method. Checking what comes before the parentheses.
        if (trimmedLine.contains("(")){
            if (trimmedLine.contains("void")){
                final int nameStart = 5;
                int nameEnd = trimmedLine.indexOf("(");
                String methodName = trimmedLine.substring(nameStart, nameEnd).trim();
                stringOptional = Optional.of(methodName);
            } else if(trimmedLine.contains("int")){
                final int nameStart = 4;
                int nameEnd = trimmedLine.indexOf("(");
                String methodName = trimmedLine.substring(nameStart, nameEnd).trim();
                stringOptional = Optional.of(methodName);
            } else {
                int nameEnd = trimmedLine.indexOf("(");
                String methodName = trimmedLine.substring(0, nameEnd).trim();
                stringOptional = Optional.of(methodName);
            }
        }

        return stringOptional;
    }

    /**
     * Finds the arguments to a method that takes in methods.
     * @param line The line to be analysed (should be a call to a method).
     * @return The integers being passed into the method.
     * @throws IllegalArgumentException If the line contains anything between
     * the parentheses other than integers, commas, or spaces.
     */
    public static int[] parseArguments(String line) throws IllegalArgumentException{
        String trimmedLine = line.trim();
        int[] arguments;

        if (!trimmedLine.contains("(")){
            arguments = null;
        } else {
            int parenBegin = trimmedLine.indexOf("(");
            int parenEnd = trimmedLine.indexOf(")");
            String argsList = trimmedLine.substring(parenBegin + 1, parenEnd).trim();
            arguments = new int[argsList.length()];
            int numArguments = 0;

            for (int i = 0; i < argsList.length(); i++){
                char current = argsList.charAt(i);
                if (Character.isDigit(current)){
                    arguments[numArguments] = Integer.parseInt(Character.toString(current));
                    numArguments++;
                } else if (current != ',' && current != ' '){
                    throw new IllegalArgumentException();
                }
            }
        }

        return arguments;
    }
}