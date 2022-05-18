/*
 * Course: CS2852-071
 * Spring 2021
 * Lab 8 - Morse Code Encoder
 * Name: Derek Richards
 * Created: 5/1/2021
 */
package richardsde;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The driver for the program. For once, args will actually contain elements.
 */
public class MorseDriver {
    public static void main(String[] args) {
        try {
            MorseEncoder encoder = new MorseEncoder();
            File loadingFile = new File("..//..//../data/morsecode.txt");
            encoder.loadTable(loadingFile.toPath());

            if (args.length == 2){
                System.out.println("Input filename: " + args[0]);
                System.out.println("Output filename: " + args[1]);
                System.out.println("Encoding file . . . please wait . . .");

                File inputFile = new File("..//..//../data/" + args[0]);
                File outputFile = new File("..//..//../data/" + args[1]);
                Scanner in = new Scanner(inputFile);
                PrintWriter out = new PrintWriter(outputFile);

                while (in.hasNextLine()){
                    String line = in.nextLine();
                    out.print(encoder.encodeMessage(line));
                    out.println(encoder.encodeMessage("\n"));
                    out.flush();
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("One of the files could not be found.");
        }

    }
}