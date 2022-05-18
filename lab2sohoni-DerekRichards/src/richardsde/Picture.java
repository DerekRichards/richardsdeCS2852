/*
 * Course: CS2852 - 071
 * Spring 2021
 * Lab 2 - Connect the Dots
 * Name: Derek Richards
 * Created: 3/21/2021
 */
package richardsde;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.paint.Color;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.*;

/**
 * A class representing a picture
 */
public class Picture {
    private List<Dot> dotList;
    private Canvas canvas;

    /**
     * A constructor that sets that initializes the dotList to reference an empty list.
     * @param emptyList An empty List of Dot objects.
     */
    public Picture(List<Dot> emptyList){
        dotList = emptyList;
    }

    /**
     * A constructor that copies the dots from another Picture object into
     * an empty List and uses it to store the dots for the current Picture object.
     * @param original A Picture object.
     * @param emptyList An empty List of Dot objects.
     */
    public Picture(Picture original, List<Dot> emptyList){
        emptyList = original.getDotList();
        dotList = emptyList;
    }

    /**
     * Method used to create the image on the canvas
     * @param path The filepath used to find the information for the dots.
     * @throws IllegalArgumentException If the file type is one other than ".dot"
     */
    public void load(Path path) throws IllegalArgumentException{
        try {
            String pathAsString = path.toString();
            if (pathAsString.endsWith(".dot")){
                Scanner in = new Scanner(path.toFile());


                GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                graphicsContext.beginPath();
                graphicsContext.setFill(Color.BLACK);

                while (!dotList.isEmpty()){
                    dotList.remove(0);
                }

                while (in.hasNext()){
                    dotList.add(new Dot(in));
                }
                Dot beginningDot = dotList.get(0);
                double xCoordinate = canvas.getWidth() * beginningDot.getX();
                double yCoordinate = canvas.getHeight() * (1 - beginningDot.getY());
                graphicsContext.moveTo(xCoordinate, yCoordinate);
                graphicsContext.fillOval(xCoordinate, yCoordinate, 2, 2);
                for (Dot dot: dotList){
                    double dotX = canvas.getWidth() * dot.getX();
                    double dotY = canvas.getHeight() * (1 - dot.getY());
                    graphicsContext.lineTo(dotX, dotY);
                    graphicsContext.fillOval(dotX, dotY, 2, 2);
                }
                graphicsContext.closePath();
                graphicsContext.stroke();
            } else {
                throw new IllegalArgumentException("Not a valid file type.");
            }
        } catch (FileNotFoundException e){
            fileNotFoundAlert();
        }

    }

    /**
     * Tries to write in the x and y coordinates of the dots in the image.
     * @param path The file path of the image.
     */
    public void save(Path path){
        try {
            PrintWriter fileWriter = new PrintWriter(path.toFile());
            for(Dot dot: dotList){
                fileWriter.println(dot.getX() + ", " + dot.getY());
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (FileNotFoundException e){
            fileNotFoundAlert();
        }


    }

    /**
     * Tries to draw the current picture with only dots.
     * @param canvas The canvas where the image is being displayed.
     */
    public void drawDots(Canvas canvas){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0,
                this.canvas.getWidth(), this.canvas.getHeight());
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.beginPath();

        for (Dot dot: dotList){
            graphicsContext.fillOval(this.canvas.getWidth() * dot.getX(),
                    this.canvas.getHeight() * (1 - dot.getY()), 2, 2);
        }
        graphicsContext.closePath();
        graphicsContext.stroke();
    }

    /**
     * Tries to recreate the original drawing without any dots.
     * @param canvas The canvas where the image is being displayed.
     */
    public void drawLines(Canvas canvas){
        GraphicsContext graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0,
                this.canvas.getWidth(), this.canvas.getHeight());
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.beginPath();


        Dot firstDot = dotList.get(0);
        graphicsContext.moveTo(this.canvas.getWidth() * firstDot.getX(),
                this.canvas.getHeight() * (1 - firstDot.getY()));

        for (Dot dot: dotList){
            graphicsContext.lineTo(this.canvas.getWidth() * dot.getX(),
                    this.canvas.getHeight() * (1 - dot.getY()));
        }

        graphicsContext.closePath();
        graphicsContext.stroke();
    }

    /**
     * Has the user input the amount of dots to keep and
     * removes dots from the image so that the image has that amount of dots.
     * @param numberDesired The number of dots to keep.
     * @param strategy A string containing the strategy used to remove
     * dots from the picture: using indices or using an iterator.
     * @throws IllegalArgumentException If the number of dots to keep is a number less than three.
     * @throws InputMismatchException If the strategy entered is
     * not one of the two supported strategies.
     */
    public void removeDots(int numberDesired, String strategy)
            throws IllegalArgumentException, InputMismatchException {
        if (numberDesired < 3){
            throw new IllegalArgumentException("Can't create an image with less than three dots.");
        } else if (numberDesired < dotList.size()){
            GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
            long time;

            if (strategy.equalsIgnoreCase("indices") ||
                    strategy.equalsIgnoreCase("index")){
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                time = removeDotsIndex(dotList, numberDesired);
            } else if (strategy.equalsIgnoreCase("iterators") ||
                    strategy.equalsIgnoreCase("iterator")) {
                graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                time = removeDotsIterator(dotList, numberDesired);
            } else {
                throw new InputMismatchException("Strategy entered is not " +
                        "one of the types of strategies supported.");
            }

            graphicsContext.beginPath();
            graphicsContext.setFill(Color.BLACK);
            Dot beginningDot = dotList.get(0);
            double xCoordinate = canvas.getWidth() * beginningDot.getX();
            double yCoordinate = canvas.getHeight() * (1 - beginningDot.getY());
            graphicsContext.moveTo(xCoordinate, yCoordinate);
            graphicsContext.fillOval(xCoordinate, yCoordinate, 2, 2);
            for (Dot dot: dotList){
                double dotX = canvas.getWidth() * dot.getX();
                double dotY = canvas.getHeight() * (1 - dot.getY());
                graphicsContext.lineTo(dotX, dotY);
                graphicsContext.fillOval(dotX, dotY, 2, 2);
            }
            graphicsContext.closePath();
            graphicsContext.stroke();

            System.out.println("Time taken for strategy \"" + strategy + "\": " + time + " ns");
        }
    }

    public void setCanvas(Canvas canvas){
        this.canvas = canvas;
    }

    private List<Dot> getDotList(){
        return dotList;
    }

    private static long removeDotsIndex(List<Dot> dots, int numberDesired){
        final long startTime = System.nanoTime();
        while (numberDesired < dots.size()){
            double min = Double.MAX_VALUE;
            int minIndex = -1;
            int currentSize = dots.size();

            for (int i = 0; i < currentSize; i++){
                double critValue;
                if (i == 0){
                    dots.get(i).setCriticalValue(dots.get(currentSize - 1), dots.get(i + 1));
                    critValue = dots.get(i).getCriticalValue();
                } else if (i == currentSize - 1){
                    dots.get(i).setCriticalValue(dots.get(i - 1), dots.get(0));
                    critValue = dots.get(i).getCriticalValue();
                } else{
                    dots.get(i).setCriticalValue(dots.get(i - 1), dots.get(i + 1));
                    critValue = dots.get(i).getCriticalValue();
                }

                if (critValue < min){
                    min = critValue;
                    minIndex = i;
                }
            }
            dots.remove(minIndex);
        }
        final long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long removeDotsIterator(Collection<Dot> dots, int numberDesired){
        final long startTime = System.nanoTime();

        while (numberDesired < dots.size()){
            double min = Double.MAX_VALUE;

            // Attempting to calculate the critical value for the first dot.
            Iterator<Dot> firstDotIterator = dots.iterator();
            Dot firstDot = firstDotIterator.next();
            Dot secondDot = firstDotIterator.next();
            Dot lastDot = null;
            for (Dot dot : dots) {
                lastDot = dot;
            }
            firstDot.setCriticalValue(lastDot, secondDot);
            double critValue = firstDot.getCriticalValue();
            if (critValue < min){
                min = critValue;
            }


            // Moving on to the rest of the dots
            Iterator<Dot> previous = dots.iterator();
            Iterator<Dot> dotIterator = dots.iterator();
            dotIterator.next();
            Iterator<Dot> next = dots.iterator();
            next.next();
            next.next();
            Dot previousDot = null;
            Dot currentDot = null;
            Dot nextDot = null;

            while (dotIterator.hasNext()){

                previousDot = previous.next();
                currentDot = dotIterator.next();
                if (next.hasNext()){
                    nextDot = next.next();
                    currentDot.setCriticalValue(previousDot, nextDot);
                    critValue = currentDot.getCriticalValue();
                }



                if (critValue < min){
                    min = critValue;
                }
            }

            // Calculating the critical value of the last dot
            Iterator<Dot> lastIterator = dots.iterator();
            lastDot.setCriticalValue(previousDot, firstDot);
            critValue = lastDot.getCriticalValue();
            if (critValue < min){
                min = critValue;
            }

            Iterator<Dot> dotRemover = dots.iterator();
            while (dotRemover.hasNext()){
                Dot removedDot = dotRemover.next();
                if (removedDot.getCriticalValue() == min){
                    dotRemover.remove();
                }
            }
        }

        final long endTime = System.nanoTime();
        return endTime - startTime;
    }


    private void fileNotFoundAlert() {
        Alert error = new Alert(Alert.AlertType.ERROR, "The file could not be found");
        error.setTitle("Error Dialog");
        error.setHeaderText("File Not Found Error");
        error.show();
    }

}