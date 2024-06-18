package benchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Class to benchmark the performance of sorting algorithms.
 * This class generates random data sets of various sizes, runs sorting algorithms on them
 * and records the critical operation counts and run times.
 * Author: Levon Fischer
 * Course: CMSC 451/6381
 * Date: June 17, 2024
 */
public class BenchmarkSorts {

    // Array of dataset sizes for benchmarking
    private static final int[] SIZES = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000, 11000, 12000};

    public static void main(String[] args) {
        BenchmarkSorts benchmark = new BenchmarkSorts();
        try {
            benchmark.runBenchmarks();
        } catch (IOException | UnsortedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs the benchmarking process for the sorting algorithms.
     * This method benchmarks both ShellSort and InsertionSort algorithms and writes the results to files.
     *
     * @throws IOException          if an I/O error occurs during file writing.
     * @throws UnsortedException    if the sorted array is not in the correct order.
     */
    public void runBenchmarks() throws IOException, UnsortedException {
        try {
            benchmarkSort(new ShellSort(), "ShellSortResults.txt");
            benchmarkSort(new InsertionSort(), "InsertionSortResults.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Benchmarks a specific sorting algorithm
     * This method generates random data sets of different sizes, runs the sorting algorithm on each set,
     * and writes the critical operation counts and run times to a file.
     *
     * @param sorter    The sorting algorithm to benchmark
     * @param filename  The file to write the benchmarking results to.
     * @throws IOException          if an I/O error occurs during file writing.
     * @throws UnsortedException    if the sorted array is not in the correct order.
     */
    private void benchmarkSort(AbstractSort sorter, String filename) throws IOException, UnsortedException {
        FileWriter writer = new FileWriter(filename); // Open the file for writing
        Random rand = new Random(); // Random number generator for creating data sets

        // JVM warm-up to ensure accurate timing measurements
        for (int i = 0; i < 5; i++) {
            int[] warmUpData = generateRandomArray(1000, rand); // Generate a warm-up data set
            sorter.sort(warmUpData); // Run the sorting algorithm on the warm-up data set
        }

        // Loop through each size in the SIZES array
        for (int size : SIZES) {
            writer.write(size + " "); // Write the size to the file
            // Perform 40 runs for each size
            for (int i = 0; i < 40; i++) {
                int[] data = generateRandomArray(size, rand); // Generate a random data set
                sorter.sort(data); // Sort the data set using the sorting algorithm
                if (!isSorted(data)) { // Verify the data set is sorted correctly
                    throw new UnsortedException("Array is not sorted correctly");
                }
                writer.write(sorter.getCount() + " " + sorter.getTime() + " "); // Write the critical operation count and time to the file
            }
            writer.write("\n"); // New line after each size
        }
        writer.close(); // Close the file
    }

    /**
     * Generates a random array of integers
     * This method creates an array of the specified size and fills it with random integers
     *
     * @param size  The size of the array
     * @param rand  The Random object for generating random numbers
     * @return  an array of random integers
     */
    private int[] generateRandomArray(int size, Random rand) {
        int[] array = new int[size]; // Create a new array of the specified size
        // Fill the array with random integers
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(10000); // Random integer between 0 and 9999
        }
        return array;
    }

    /**
     * Checks if an array is sorted in ascending order
     * This method verifies that each element in teh array is less than or equal to the next element.
     *
     * @param array The array to check.
     * @return true if the array is sorted, false otherwise
     */
    private boolean isSorted(int[] array) {
        // Loop through the array and check if each element is less than or equal to the next element.
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false; // Return false if any element is greater than the next.
            }
        }
        return true;
    }
}
