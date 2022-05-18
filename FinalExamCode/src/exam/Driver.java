package exam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Driver {
    private static LookupTable<Character, Character> myTable = new LookupTable<>();


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        File loadFile = new File("./data/myCode.txt");
        load(loadFile.toPath());
        myTable.print();
    }

    /**
     * Load values from a *.txt file
     * @param path File path of myCode file
     */
    private static void load(Path path) {
        try (Scanner in = new Scanner(path.toFile())) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                char symbol = line.charAt(0);
                char value = line.charAt(2);
                myTable.put(symbol, value);
            }
        } catch (IOException e) {
            System.err.println("Warning: myCode file could not be found.");
        }
    }
}
