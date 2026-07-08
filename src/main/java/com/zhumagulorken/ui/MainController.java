package com.zhumagulorken.ui;

import com.zhumagulorken.algorithms.BubbleSort;
import com.zhumagulorken.algorithms.BucketSort;
import com.zhumagulorken.algorithms.CountingSort;
import com.zhumagulorken.algorithms.HeapSort;
import com.zhumagulorken.algorithms.InsertionSort;
import com.zhumagulorken.algorithms.LinearSearch;
import com.zhumagulorken.algorithms.MergeSort;
import com.zhumagulorken.algorithms.QuickSort;
import com.zhumagulorken.algorithms.RadixSort;
import com.zhumagulorken.algorithms.SearchAlgorithm;
import com.zhumagulorken.algorithms.SelectionSort;
import com.zhumagulorken.algorithms.ShellSort;
import com.zhumagulorken.algorithms.SortAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.util.Random;

public class MainController {
    @FXML private Canvas canvas;
    @FXML private Button generateButton, startButton, searchButton;
    @FXML private ChoiceBox<String> algorithmChoice;
    @FXML private ChoiceBox<String> searchChoice;
    @FXML private TextField targetField;
    @FXML private Slider speedSlider;

    private int[] array;

    @FXML
    public void initialize() {
        algorithmChoice.getItems().addAll("Bubble Sort", "Insertion Sort", "Selection Sort", "Heap Sort", "Merge Sort", "Quick Sort", "Counting Sort", "Bucket Sort", "Radix Sort", "Shell Sort");
        searchChoice.getItems().addAll("Linear Search");
        generateButton.setOnAction(e -> generateArray());
        startButton.setOnAction(e -> startSorting());
        searchButton.setOnAction(e -> startSearching());
        generateArray();
    }

    private void generateArray() {
        array = new int[50];
        Random r = new Random();
        for (int i = 0; i < array.length; i++)
            array[i] = r.nextInt(400) + 50;
        drawArray(array, -1);
    }

    private void drawArray(int[] arr, int highlightIndex) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double barWidth = canvas.getWidth() / arr.length;
        for (int i = 0; i < arr.length; i++) {
            double height = arr[i];
            gc.setFill(i == highlightIndex ? Color.ORANGERED : Color.CORNFLOWERBLUE);
            gc.fillRect(i * barWidth, canvas.getHeight() - height, barWidth - 2, height);
        }
    }

    private SortAlgorithm getSelectedAlgorithm() {
        String algo = algorithmChoice.getValue();
        return switch (algo) {
            case "Bubble Sort" -> new BubbleSort();
            case "Insertion Sort" -> new InsertionSort();
            case "Selection Sort" -> new SelectionSort();
            case "Heap Sort" -> new HeapSort();
            case "Merge Sort" -> new MergeSort();
            case "Quick Sort" -> new QuickSort();
            case "Counting Sort" -> new CountingSort();
            case "Bucket Sort" -> new BucketSort();
            case "Radix Sort" -> new RadixSort();
            case "Shell Sort" -> new ShellSort();
            default -> null;
        };
    }

    private void startSorting() {
        SortAlgorithm algorithm = getSelectedAlgorithm();
        if (algorithm == null) return;
        new Thread(() -> {
            try {
                algorithm.sort(array, () -> {
                    javafx.application.Platform.runLater(() -> drawArray(array, -1));
                });
            } catch (InterruptedException ignored) {}
        }).start();
    }

    private SearchAlgorithm getSelectedSearchAlgorithm() {
        String algo = searchChoice.getValue();
        return switch (algo) {
            case "Linear Search" -> new LinearSearch();
            default -> null;
        };
    }

    private void startSearching() {
        SearchAlgorithm algorithm = getSelectedSearchAlgorithm();
        if (algorithm == null) return;

        int target;
        try {
            target = Integer.parseInt(targetField.getText());
        } catch (NumberFormatException e) {
            return;
        }

        new Thread(() -> {
            try {
                int result = algorithm.search(array, target, index -> {
                    javafx.application.Platform.runLater(() -> drawArray(array, index));
                });
                javafx.application.Platform.runLater(() -> drawArray(array, result));
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
