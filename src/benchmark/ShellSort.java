package benchmark;

/**
 * Class to implement the Shell sort algorithm
 * This class extends AbstractSort and provides the sorting logic for the Shell sort algorithm.
 * Author: Levon Fischer
 * Course: CMSC 451/6381
 * Date: 17 July, 2024
 */
public class ShellSort extends AbstractSort {
    /**
     * Sorts the given array using the Shell sort algorithm
     * This method uses a series of gap values to divide the array into subarrays, which are then sorted using insertion sort.
     *
     * @param array The array to sort.
     */
    @Override
    public void sort(int[] array) {
        startSort(); // Start timing and initialize counter
        int n = array.length;

        // Start with a large gap, then reduce the gap
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Perform a gapped insertion sort for this gap size
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                // Shift elements of array[0..i-gap] that are greater than temp
                // to the one position ahead of their current position
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                    incrementCount(); // Increment critical operation counter
                }
                array[j] = temp;
            }
        }
        endSort(); // End timing
    }
}
