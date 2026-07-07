package com.zhumagulorken.algorithms;

public class MergeSort implements SortAlgorithm {
    @Override
    public void sort(int[] array, Runnable onUpdate) throws InterruptedException {
        mergeSort(array, 0, array.length - 1, onUpdate);
    }

    private void mergeSort(int[] array, int left, int right, Runnable onUpdate) throws InterruptedException {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(array, left, mid, onUpdate);
        mergeSort(array, mid + 1, right, onUpdate);
        merge(array, left, mid, right, onUpdate);
    }

    private void merge(int[] array, int left, int mid, int right, Runnable onUpdate) throws InterruptedException {
        int[] leftPart = new int[mid - left + 1];
        int[] rightPart = new int[right - mid];
        System.arraycopy(array, left, leftPart, 0, leftPart.length);
        System.arraycopy(array, mid + 1, rightPart, 0, rightPart.length);

        int i = 0, j = 0, k = left;
        while (i < leftPart.length && j < rightPart.length) {
            array[k++] = leftPart[i] <= rightPart[j] ? leftPart[i++] : rightPart[j++];
            onUpdate.run();
            Thread.sleep(20);
        }
        while (i < leftPart.length) {
            array[k++] = leftPart[i++];
            onUpdate.run();
            Thread.sleep(20);
        }
        while (j < rightPart.length) {
            array[k++] = rightPart[j++];
            onUpdate.run();
            Thread.sleep(20);
        }
    }
}
