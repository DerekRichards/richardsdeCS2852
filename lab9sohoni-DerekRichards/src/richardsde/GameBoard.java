/*
 * Course: CS2852-071
 * Spring 2021
 * Lab 9 - Word Search
 * Name: Derek Richards
 * Created: 5/7/2021
 */
package richardsde;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

/**
 * A class that represents a word search puzzle.
 */
public class GameBoard {

    private Collection<String> dictionary;
    private Cell[][] grid;

    /**
     * An inner class used to represent the cells of a grid.
     */
    private static class Cell {
        private int row;
        private int col;
        private char letter;

        /**
         * Constructor of the Cell class.
         * @param row The row being represented.
         * @param col The column being represented.
         * @param letter The letter stored in the Cell.
         */
        public Cell(int row, int col, char letter){
            this.row = row;
            this.col = col;
            this.letter = letter;
        }

        /**
         * Creates a HashCode for the current object using Objects.hash().
         * @return The object's HashCode.
         */
        @Override
        public int hashCode() {
            return Objects.hash(row, col, letter);
        }

        /**
         * Checks for equality between the current object an another object.
         * @param obj The object being compared.
         * @return Whether or not the two objects are equivalent.
         */
        @Override
        public boolean equals(Object obj) {
            // We need to make sure that the object can be converted to a Cell.
            try {
                Cell data = (Cell) obj;
            } catch (ClassCastException e){
                // If it can't be converted, we no for sure that the objects aren't equal.
                return false;
            }

            return this.row == ((Cell) obj).row
                    && this.col == ((Cell) obj).col
                    && this.letter == ((Cell) obj).letter;

        }

        /**
         * Gets the letter stored in the cell.
         * @return The letter stored in the cell.
         */
        public char getLetter(){
            return letter;
        }
    }

    /**
     * The constructor for the GameBoard class.
     * @param emptyDictionary An empty collection of String
     * objects that will be used for the dictionary.
     */
    public GameBoard(Collection<String> emptyDictionary){
        dictionary = emptyDictionary;
        // grid = new Cell[6][6];
        // I don't know how to set the dimensions of the grid.
    }

    /**
     * Uses a file containing strings to load the dictionary object.
     * @param path The filepath of the file used to load the dictionary.
     */
    public void loadDictionary(Path path){
        try {
            Scanner scan = new Scanner(path.toFile());
            while (scan.hasNextLine()){
                String word = scan.nextLine();
                dictionary.add(word);
            }
        } catch (FileNotFoundException e){
            System.out.println("The dictionary file could not be found.");
        }

    }

    /**
     * Uses a file containing uppercase characters to load the grid object.
     * @param path The filepath of the file used to load the dictionary.
     */
    public void loadGrid(Path path){
        try {
            // First, we need to initialize the grid object. In order to do that,
            // we need to figure out what the grid's dimensions are supposed to be.
            Scanner initialScan = new Scanner(path.toFile());
            int numLine = 0;
            int columnLength = 0;
            while (initialScan.hasNextLine()){
                columnLength = initialScan.nextLine().length();
                numLine++;
            }
            grid = new Cell[numLine][columnLength];

            // Now we actually generate the grid.
            Scanner scan = new Scanner(path.toFile());
            numLine = 0;
            while (scan.hasNextLine()){
                String line = scan.nextLine();
                for (int i = 0; i < line.length(); i++){
                    grid[numLine][i] = new Cell(numLine, i, line.charAt(i));
                }
                numLine++;
            }
        } catch (FileNotFoundException e){
            System.out.println("The grid file could not be found.");
        }
    }

    private Collection<String> recursiveSearch(int row, int col, String partialWord,
                                               HashSet<Cell> visited, boolean isFourWay){
        // We need to initialize the collection, but you can't create a new Collection on its own.
        Collection<String> collection = null;
        if (dictionary instanceof ArrayList){
            collection = new ArrayList<>();
        } else if (dictionary instanceof LinkedList){
            collection = new LinkedList<>();
        } else if (dictionary instanceof HashSet){
            collection = new HashSet<>();
        } else if (dictionary instanceof TreeSet){
            collection = new TreeSet<>();
        }

        if ((row >= 0 && col >= 0) && (row < grid.length && col < grid[0].length)){
            Cell currentCell = grid[row][col];
            boolean added = visited.add(currentCell);
            // Creating a deep copy of the HashSet
            HashSet<Cell> set = new HashSet<>(visited);

            // If the cell is already in the HashSet (meaning that it isn't added),
            // this part of the code will be skipped.
            if (added){
                String text = partialWord + currentCell.getLetter();
                if (dictionary.contains(text.toLowerCase()) &&
                        text.length() > 2 && text.length() < 16){
                    collection.add(text);
                }

                // To make the recursive calls, the method adjusts the row and column values by 1.
                if (isFourWay){
                    collection.addAll(recursiveSearch(row - 1, col, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row, col - 1, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row + 1, col, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row, col + 1, text, set, isFourWay));
                } else {
                    // If this grid allows for eight way traversal,
                    // the row and column values can be adjusted at the same time.
                    collection.addAll(recursiveSearch(row - 1, col, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row, col - 1, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row + 1, col, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row, col + 1, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row - 1, col - 1, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row - 1, col + 1, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row + 1, col - 1, text, set, isFourWay));
                    collection.addAll(recursiveSearch(row + 1, col + 1, text, set, isFourWay));
                }
            }
        }

        return collection;
    }

    /**
     * Finds all of the words that can be found in the grid using a recursive method call.
     * @param isFourWay Whether or not the grid only enables
     * the user to go in 4 directions (as opposed to 8).
     * @return The Collection of Strings used to represent all of the words that were found?
     */
    public Collection<String> findWords(boolean isFourWay){
        HashSet<Cell> cells = new HashSet<>();
        // We need to initialize the collection, but you can't create a new Collection on its own.
        Collection<String> collection = null;
        if (dictionary instanceof ArrayList){
            collection = new ArrayList<>();
        } else if (dictionary instanceof LinkedList){
            collection = new LinkedList<>();
        } else if (dictionary instanceof HashSet){
            collection = new HashSet<>();
        } else if (dictionary instanceof TreeSet){
            collection = new TreeSet<>();
        }

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                //System.out.println("Testing");
                HashSet<Cell> set = new HashSet<>(cells);
                Collection<String> words = recursiveSearch(i, j, "", set, isFourWay);
                collection.addAll(words);
            }
        }

        return collection;
    }
}