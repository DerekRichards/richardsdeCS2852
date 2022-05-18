/*
 * Course: CS2852
 * Spring 2021
 * Lab 4 - Call Stack
 * Name: Derek Richards
 * Created: 4/2/2021
 */

package richardsde;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;

/**
 * The driver class for the program.
 */
public class Lab4 {

    public static void main(String[] args) {
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter the name of the input file: ");
            String fileName = in.next().trim();
            File file = new File("./data/" + fileName);
            Scanner fileReader = new Scanner(file);
            ProgramStack programStack = new ProgramStack();
            while (fileReader.hasNext()){
                String line = fileReader.nextLine();
                System.out.println(line);
                Optional<String> methodName = FileReaderUtils.parseMethodName(line);
                int[] arguments = FileReaderUtils.parseArguments(line);
                if (methodName.isPresent()){
                    programStack.callMethod(methodName.get(), arguments);
                }
                if (line.contains("return")){
                    if (FileReaderUtils.isVoidReturn(line)){
                        programStack.returnFromMethod();
                    } else{
                        OptionalInt returnValue = FileReaderUtils.parseReturnValue(line);
                        assert returnValue.isPresent();
                        programStack.returnFromMethod(returnValue.getAsInt());
                    }
                }
                if (!line.contains("return") && !methodName.isPresent()){
                    System.out.println("Invalid line, ignored.");
                } else{
                    System.out.println(programStack.toString());
                }
                System.out.println();
            }
        } catch (FileNotFoundException e){
            System.out.println("That file could not be found.");
        }

    }
}