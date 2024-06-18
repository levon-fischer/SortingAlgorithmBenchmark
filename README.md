# Sorting Algorithm Benchmark

## Overview

This project benchmarks the performance of two sorting algorithms: Shell Sort and Insertion Sort. The benchmarks measure the number of critical operations and the time taken to sort datasets of varying sizes. The results are presented in a report that includes averages and coefficients of variance for both the operation counts and the time measurements.

## Features

- **Benchmarking:** Measures the performance of Shell Sort and Insertion Sort on datasets of different sizes.
- **Data Generation:** Creates random datasets for benchmarking.
- **Reporting:** Generates detailed reports of the benchmarking results, including averages and coefficients of variance.

## Project Structure

- `benchmark`: Contains the core classes for benchmarking the sorting algorithms.
  - `AbstractSort.java`: Abstract class that defines the structure for sorting algorithms.
  - `ShellSort.java`: Implements the Shell Sort algorithm.
  - `InsertionSort.java`: Implements the Insertion Sort algorithm.
  - `BenchmarkSorts.java`: Runs the benchmarking process and records the results.
  - `UnsortedException.java`: Custom exception for handling unsorted arrays.
- `report`: Contains the class for generating and displaying the benchmark report.
  - `ReportGenerator.java`: Reads the benchmark results and displays them in a table.

## Usage

### Prerequisites

- Java Development Kit (JDK) installed
- Git installed

### Running the Benchmark

1. Clone the repository:
   ```sh
   git clone https://github.com/levon-fischer/SortingAlgorithmBenchmark.git
   cd SortingAlgorithmBenchmark
   ```
2. Compile and run the benchmarking program:
   ```sh
   javac benchmark/*.java
   java benchmark.BenchmarkSorts
   ```
3. Generate and view the report:
   ```
   javac report/ReportGenerator.java
   java report.ReportGenerator
   ```

## License
This project is licensed under the MIT License.
