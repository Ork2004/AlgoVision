package com.zhumagulorken.ui;

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
        algorithmChoice.getItems().addAll("Bubble Sort", "Insertion Sort");
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

    private void startSorting() {
        String algo = algorithmChoice.getValue();
        if (algo == null) return;

        switch (algo) {
            case "Bubble Sort" -> bubbleSort();
            case "Insertion Sort" -> insertionSort();
        }
    }

    private void bubbleSort() {
        new Thread(() -> {
            try {
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - i - 1; j++) {
                        if (array[j] > array[j + 1]) {
                            int temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                        drawArray(array);
                        Thread.sleep((long) (200 / speedSlider.getValue()));
                    }
                }
            } catch (InterruptedException ignored) {}
        }).start();
    }

    private void insertionSort() {
        new Thread(() -> {
            try {
                for (int i = 1; i < array.length; i++) {
                    int key = array[i];
                    int j = i - 1;
                    while (j >= 0 && array[j] > key) {
                        array[j + 1] = array[j];
                        j--;
                        drawArray(array);
                        Thread.sleep((long) (200 / speedSlider.getValue()));
                    }
                    array[j + 1] = key;
                    drawArray(array);
                }
            } catch (InterruptedException ignored) {}
        }).start();
    }
}
