/*
 * Course: CS2852-071
 * Spring 2021
 * Lab 7- Morse Code Decoder
 * Name: Derek Richards
 * Created: 4/23/2021
 */
package richardsde;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;

/**
 * A class used for decoding and encoding morse code.
 */
public class MorseDecoder {
    private static MorseTree<Character> morseTree = new MorseTree<>();

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        File addFile = new File("./data/morsecode.txt");
        loadDecoder(addFile.toPath());

        System.out.println("Enter an input file name:");
        String inputFileName = in.nextLine().trim();
        File inputFile = new File("./data/" + inputFileName);
        System.out.println("Enter an output file name:");
        String outputFileName = in.nextLine().trim();
        File outputFile = new File("./data/" + outputFileName);
        decodeCode(inputFile.toPath(), outputFile.toPath());
    }

    /**
     * This method tries to load in the elements of the MorseTree object.
     * @param path The file path of the file used to load the elements of the tree.
     */
    public static void loadDecoder(Path path){
        try {
            Scanner in = new Scanner(path.toFile());
            while (in.hasNextLine()){
                String line = in.nextLine();
                char character;
                String code;
                if (line.charAt(0) == '\\' && line.charAt(1) == 'n'){
                    character = '\n';
                    code = line.substring(2);
                } else {
                    character = line.charAt(0);
                    code = line.substring(1);
                }
                morseTree.add(character, code);
            }
        } catch (FileNotFoundException e){
            System.out.println("That file could not be found.");
        }
    }

    /**
     * Reads in morse code from one file and writes the decoded message into another file.
     * @param input The file to read morse code from.
     * @param output The file to write to.
     */
    public static void decodeCode(Path input, Path output){
        try {
            Scanner in = new Scanner(input.toFile());
            PrintWriter fileWriter = new PrintWriter(output.toFile());
            while (in.hasNext()){
                String code = in.next();
                if (code.charAt(0) != '.' && code.charAt(0) != '-'){
                    System.err.println("Warning: Skipping " + code);
                } else {
                    Optional<Character> symbol = morseTree.decode(code);
                    if (symbol.isPresent()){
                        fileWriter.print(symbol.get());
                        fileWriter.flush();
                    } else {
                        System.err.println("Waring: Skipping " + code);
                    }
                }

            }
        } catch (FileNotFoundException e){
            System.out.println("That file could not be found.");
        } catch (IllegalArgumentException e){
            System.out.println("Invalid character found.");
        }

    }

}