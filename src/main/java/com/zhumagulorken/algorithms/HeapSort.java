package com.zhumagulorken.algorithms;

public class HeapSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i, onUpdate);

        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            onUpdate.run();
            Thread.sleep(20);
            heapify(array, i, 0, onUpdate);
        }
    }

    private void heapify(int[] array, int n, int i, Runnable onUpdate) throws InterruptedException {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) largest = left;
        if (right < n && array[right] > array[largest]) largest = right;

        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            onUpdate.run();
            Thread.sleep(20);
            heapify(array, n, largest, onUpdate);
        }
    }
}
