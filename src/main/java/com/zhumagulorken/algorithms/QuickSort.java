package com.zhumagulorken.algorithms;

public class QuickSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        quickSort(array, 0, array.length - 1, onUpdate);
    }

    private void quickSort(int[] array, int low, int high, Runnable onUpdate) throws InterruptedException {
        if (low >= high) return;

        int pivotIndex = partition(array, low, high, onUpdate);
        quickSort(array, low, pivotIndex - 1, onUpdate);
        quickSort(array, pivotIndex + 1, high, onUpdate);
    }

    private int partition(int[] array, int low, int high, Runnable onUpdate) throws InterruptedException {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                onUpdate.run();
                Thread.sleep(20);
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        onUpdate.run();
        Thread.sleep(20);

        return i + 1;
    }
}
