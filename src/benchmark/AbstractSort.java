package benchmark;

/**
 * Class to provide a template for sorting algorithms.
 * This class defines methods to start and end the sorting process, count critical operations,
 * and measure the elapsed time.
 * Author: Levon Fischer
 * Course: CMSC 451/6381
 * Date: 17 June, 2024
 */
public abstract class AbstractSort {
    protected long count; // Counter for critical operations
    protected long startTime; // Start time for sorting
    protected long endTime; // End time for sorting

    /**
     * Sorts the given array.
     * This must be implemented by algorithm subclasses to provide the sorting logic.
     *
     * @param array The array to sort.
     */
    public abstract void sort(int[] array);

    /**
     * Starts the sorting process by initializing the counter and recording the start time.
     */
    public void startSort() {
        count = 0;
        startTime = System.nanoTime();
    }

    /**
     * Ends the sorting process by recording the end time.
     */
    public void endSort() {
        endTime = System.nanoTime();
    }

    /**
     * Increments the count of critical operations.
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Gets the count of critical operations.
     *
     * @return The count of critical operations
     */
    public long getCount() {
        return count;
    }

    /**
     * Gets the elapsed time of the sorting process.
     *
     * @return the elapsed time in nanoseconds.
     */
    public long getTime() {
        return endTime - startTime;
    }
}
