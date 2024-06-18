package benchmark;

/**
 * Class to implement the insertion sort algorithm.
 * This class extends AbstractSort and provides teh sorting logic for the insertion sort algorithm.
 * Author: Levon Fischer
 * Course: CMSC 451/6381
 * Date: 17 July, 2024
 */
public class InsertionSort extends AbstractSort {
    /**
     * Sorts the given array using the insertion sort algorithm.
     * This method builds the final sorted array one item at a time, shifting larger elements to the right to make room for the current element.
     *
     * @param array The array to sort.
     */
    @Override
    public void sort(int[] array) {
        startSort(); // Start timing and initialize counter
        int n = array.length;

        // Traverse through 1 to n
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            // Move elements of array[0..i-1], that are greater than key,
            // to one position ahead of their current position.
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
                incrementCount(); // Increment critical operation counter
            }
            array[j + 1] = key;
        }
        endSort(); // End timing
    }
}
