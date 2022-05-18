/*
 * Course: CS2852-071
 * Spring 2021
 * Lab 9 - Word Search
 * Name: Derek Richards
 * Created: 5/10/2021
 */
package richardsde;

import java.io.File;
import java.util.*;

/**
 * A driver class for the word search
 */
public class WordSearchCLI {

    public static void main(String[] args) {
        // First, we need to check that four strings are passed into the main() method.
        // We only have enough information to run the class if that's the case.
        if (args.length == 4){
            boolean isFourWay;
            if (args[0].equalsIgnoreCase("4way")){
                isFourWay = true;
            } else {
                isFourWay = false;
            }

            //File dictionaryFile = new File("..//..//../" + args[2]);
            //File gridFile = new File("..//..//../" + args[1]);
            File gridFile = new File("./" + args[1]);
            File dictionaryFile = new File("./" + args[2]);
            GameBoard board = null;
            Collection<String> words;
            final long start;
            final long end;
            double timeElapsed;

            switch (args[3]) {
                case "ArrayList":
                    ArrayList<String> arrayListDictionary = new ArrayList<>();
                    board = new GameBoard(arrayListDictionary);
                    break;
                case "LinkedList":
                    LinkedList<String> linkedListDictionary = new LinkedList<>();
                    board = new GameBoard(linkedListDictionary);
                    break;
                case "HashSet":
                    HashSet<String> hashSetDictionary = new HashSet<>();
                    board = new GameBoard(hashSetDictionary);
                    break;
                case "TreeSet":
                    TreeSet<String> treeSetDictionary = new TreeSet<>();
                    board = new GameBoard(treeSetDictionary);
                    break;
            }

            if (board != null){
                board.loadDictionary(dictionaryFile.toPath());
                board.loadGrid(gridFile.toPath());

                start = System.nanoTime();
                words = board.findWords(isFourWay);
                end = System.nanoTime();

                timeElapsed = (end - start) * Math.pow(10.0, -9);

                int numWords = 0;
                for (String word : words){
                    System.out.println(word);
                    numWords++;
                }
                System.out.println("There was a total of " + numWords + " words found.");

                System.out.println("Search completed in a total of " + timeElapsed + " seconds.");
                System.out.println("Search completed in a total of " +
                        (timeElapsed / 60) + " minutes.");

            }

        }
    }
}
