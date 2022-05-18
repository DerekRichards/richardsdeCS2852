/*
 * Course: CS2852
 * Spring 2021
 * Lab 1 - Library Waiting List
 * Name: Derek Richards
 * Created: 3/13/2021
 */
package richardsde;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Creates two charts comparing the operations between LinkedLists and ArrayLists
 */
public class ScatterChartBenchmarks extends Application {
    private static final double NS_TO_MS = Math.pow(10.0, -6);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage){
        stage.setTitle("Benchmark Chart");
        // Creating multiple x and y axes to have more than one chart show up at a time
        final NumberAxis xAxisRemoveFromFront = new NumberAxis(0, 500000, 80000);
        final NumberAxis yAxisRemoveFromFront = new NumberAxis(0, 10000, 915);
        final NumberAxis xAxisGetMiddle = new NumberAxis(0, 500000, 80000);
        final NumberAxis yAxisGetMiddle = new NumberAxis(0, 60000, 1830);
        xAxisRemoveFromFront.setLabel("Number of Elements");
        yAxisRemoveFromFront.setLabel("Time in Milliseconds");
        xAxisGetMiddle.setLabel("Number of Elements");
        yAxisGetMiddle.setLabel("Time in Milliseconds");
        final ScatterChart<Number, Number> removeFromFrontChart =
                new ScatterChart<>(xAxisRemoveFromFront, yAxisRemoveFromFront);
        final ScatterChart<Number, Number> getFromMiddle =
                new ScatterChart<>(xAxisGetMiddle, yAxisGetMiddle);
        removeFromFrontChart.setTitle("Remove from Front");
        getFromMiddle.setTitle("Get from Middle");


        ArrayList<String> arrayList = new ArrayList<>();
        LinkedList<String> linkedList = new LinkedList<>();
        final int size1 = 20000;
        final int size2 = 40000;
        final int size3 = 200000;
        final int size4 = 400000;

        //Setting the values fro each series
        XYChart.Series removeFrontArrayListSeries = new XYChart.Series();
        removeFrontArrayListSeries.setName("ArrayList");
        removeFrontArrayListSeries.getData().add(new
                XYChart.Data(size1, removeFromFrontBenchmark(arrayList, size1)));
        removeFrontArrayListSeries.getData().add(new
                XYChart.Data(size2, removeFromFrontBenchmark(arrayList, size2)));
        removeFrontArrayListSeries.getData().add(new
                XYChart.Data(size3, removeFromFrontBenchmark(arrayList, size3)));
        removeFrontArrayListSeries.getData().add(new
                XYChart.Data(size4, removeFromFrontBenchmark(arrayList, size4)));

        XYChart.Series removeFrontLinkedListSeries = new XYChart.Series();
        removeFrontLinkedListSeries.setName("LinkedList");
        removeFrontLinkedListSeries.getData().add(new
                XYChart.Data(size1, removeFromFrontBenchmark(linkedList, size1)));
        removeFrontLinkedListSeries.getData().add(new
                XYChart.Data(size2, removeFromFrontBenchmark(linkedList, size2)));
        removeFrontLinkedListSeries.getData().add(new
                XYChart.Data(size3, removeFromFrontBenchmark(linkedList, size3)));
        removeFrontLinkedListSeries.getData().add(new
                XYChart.Data(size4, removeFromFrontBenchmark(linkedList, size4)));

        XYChart.Series getMiddleArrayListSeries = new XYChart.Series();
        getMiddleArrayListSeries.setName("ArrayList");
        getMiddleArrayListSeries.getData().add(new
                XYChart.Data(size1, getMiddleBenchmark(arrayList, size1)));
        getMiddleArrayListSeries.getData().add(new
                XYChart.Data(size2, getMiddleBenchmark(arrayList, size2)));
        getMiddleArrayListSeries.getData().add(new
                XYChart.Data(size3, getMiddleBenchmark(arrayList, size3)));
        getMiddleArrayListSeries.getData().add(new
                XYChart.Data(size4, getMiddleBenchmark(arrayList, size4)));

        XYChart.Series getMiddleLinkedListSeries = new XYChart.Series();
        getMiddleLinkedListSeries.setName("LinkedList");
        getMiddleLinkedListSeries.getData().add(new
                XYChart.Data(size1, getMiddleBenchmark(linkedList, size1)));
        getMiddleLinkedListSeries.getData().add(new
                XYChart.Data(size2, getMiddleBenchmark(linkedList, size2)));
        getMiddleLinkedListSeries.getData().add(new
                XYChart.Data(size3, getMiddleBenchmark(linkedList, size3)));
        getMiddleLinkedListSeries.getData().add(new
                XYChart.Data(size4, getMiddleBenchmark(linkedList, size4)));

        removeFromFrontChart.getData().addAll(removeFrontArrayListSeries,
                removeFrontLinkedListSeries);
        getFromMiddle.getData().addAll(getMiddleArrayListSeries, getMiddleLinkedListSeries);
        VBox displayBox = new VBox();
        displayBox.getChildren().addAll(removeFromFrontChart, getFromMiddle);
        final int sceneWidth = 500;
        final int sceneHeight = 800;
        Scene scene = new Scene(displayBox, sceneWidth, sceneHeight);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Measures the time it takes to remove the front element
     * from the list until the list is empty.
     * @param list - The list of strings to be emptied.
     * @param nElements - The number of elements in the list.
     * @return - Elapsed time in milliseconds.
     */
    public static double removeFromFrontBenchmark(List<String> list, int nElements){
        // fill waiting list
        list.clear();
        for(int i = 0; i < nElements; i++) {
            list.add("A");
        }

        final long startTime = System.nanoTime();
        do {
            list.remove(0);
        } while (!list.isEmpty());
        final long endTime = System.nanoTime();
        final long elapsed = endTime - startTime;

        return elapsed * NS_TO_MS;
    }

    /**
     * Measures the amount of time it takes to
     * obtain the middle element of a list 100000 times.
     * @param list - The list of strings to be used.
     * @param nElements - The number of elements in the list.
     * @return - Elapsed time in milliseconds.
     */
    public static double getMiddleBenchmark(List<String> list, int nElements){
        // fill waiting list
        list.clear();
        for(int i = 0; i < nElements; i++) {
            list.add("A");
        }

        final int timesToLoop = 100000;
        final long startTime = System.nanoTime();
        for (int i = 0; i < timesToLoop; i++){
            list.get(nElements/2);
        }
        final long endTime = System.nanoTime();
        final long elapsed = endTime - startTime;

        return elapsed * NS_TO_MS;
    }
}
