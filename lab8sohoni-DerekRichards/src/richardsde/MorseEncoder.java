/*
 * Course: CS2852-071
 * Spring 2021
 * Lab 8 - Morse Code Encoder
 * Name: Derek Richards
 * Created: 5/1/2021
 */
package richardsde;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * A class used for inputting regular text and encoding it in morse code.
 */
public class MorseEncoder {

    private HashMap<Character, String> hashTable;

    /**
     * The constructor for the class.
     */
    public MorseEncoder(){
        hashTable = new HashMap<>();
    }

    /**
     * This method builds the hash table and gives it values consistent with the path passed in.
     * @param path The file path of the file used to build the hashTable.
     */
    public void loadTable(Path path){
        try {
            Scanner in = new Scanner(path.toFile());
            while (in.hasNextLine()){
                String line = in.nextLine();
                char symbol;
                String code;
                if (line.charAt(0) == '\\' && line.charAt(1) == 'n'){
                    symbol = '\n';
                    code = line.substring(2);
                } else {
                    symbol = line.charAt(0);
                    code = line.substring(1);
                }
                hashTable.put(symbol, code);
            }
        } catch (FileNotFoundException e){
            System.out.println("The file could not be read because it could not be found.");
        }

    }

    /**
     * Takes in a string and returns that string in morse code.
     * @param message The message to be converted to morse code.
     * @return The morse code string.
     */
    public String encodeMessage(String message){
        String input = message.toUpperCase();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++){
            char symbol = input.charAt(i);

            if (!hashTable.containsKey(symbol)){
                System.err.println("Warning: skipping: " + symbol);
            } else {
                output.append(hashTable.get(symbol) + " ");
            }
        }

        return output.toString();
    }
}