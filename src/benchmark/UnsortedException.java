package benchmark;

/**
 * Class for handling cases where the array is not sorted correctly.
 * This exception is thrown if the array is found to be unsorted after the sorting process.
 * Author: Levon Fischer
 * Course: CMSC 451/6381
 * Date: 17 July, 2024
 */
public class UnsortedException extends Exception {
    public UnsortedException(String message) {
        super(message);
    }
}
