package com.zhumagulorken.ui;

import com.zhumagulorken.algorithms.BubbleSort;
import com.zhumagulorken.algorithms.BucketSort;
import com.zhumagulorken.algorithms.CountingSort;
import com.zhumagulorken.algorithms.HeapSort;
import com.zhumagulorken.algorithms.InsertionSort;
import com.zhumagulorken.algorithms.MergeSort;
import com.zhumagulorken.algorithms.QuickSort;
import com.zhumagulorken.algorithms.RadixSort;
import com.zhumagulorken.algorithms.SelectionSort;
import com.zhumagulorken.algorithms.SortAlgorithm;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;

import java.util.Random;

public class MainController {
    @FXML private Canvas canvas;
    @FXML private Button generateButton, startButton;
    @FXML private ChoiceBox<String> algorithmChoice;
    @FXML private Slider speedSlider;

    private int[] array;

    @FXML
    public void initialize() {
        algorithmChoice.getItems().addAll("Bubble Sort", "Insertion Sort", "Selection Sort", "Heap Sort", "Merge Sort", "Quick Sort", "Counting Sort", "Bucket Sort", "Radix Sort");
        generateButton.setOnAction(e -> generateArray());
        startButton.setOnAction(e -> startSorting());
        generateArray();
    }

    private void generateArray() {
        array = new int[50];
        Random r = new Random();
        for (int i = 0; i < array.length; i++)
            array[i] = r.nextInt(400) + 50;
        drawArray(array);
    }

    private void drawArray(int[] arr) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        double barWidth = canvas.getWidth() / arr.length;
        for (int i = 0; i < arr.length; i++) {
            double height = arr[i];
            gc.setFill(Color.CORNFLOWERBLUE);
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
            default -> null;
        };
    }

    private void startSorting() {
        SortAlgorithm algorithm = getSelectedAlgorithm();
        if (algorithm == null) return;
        new Thread(() -> {
            try {
                algorithm.sort(array, () -> {
                    javafx.application.Platform.runLater(() -> drawArray(array));
                });
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
