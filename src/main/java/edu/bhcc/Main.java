package edu.bhcc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * File Search Utility.
 */
public class Main {

    /**
     * File Search Utility.
     *
     * @param args Command Line Arguments.
     * @throws IOException IO Error.
     */
    static void main(String[] args) throws IOException {
        File rootDir = new File("test_data");
        DirectoryUtil dirUtil = new DirectoryUtil(rootDir);
        outputIntroduction(rootDir, dirUtil);
        Scanner scanner = new Scanner(System.in);
        System.out.print("To warm-up the system, enter a search term:  ");
        String term = scanner.next();


        // First search - no timing (warmup)
        executeSearch(dirUtil, term, 3, true);
        System.out.print("Enter search term or q to quit:  ");
        term = scanner.next();


        // Subsequent searches - with timing
        while (!term.equals("q")) {
            System.out.print("Enter number of worker threads:  ");
            int numWorkerThreads = scanner.nextInt();
            executeSearch(dirUtil, term, numWorkerThreads, false);
            System.out.print("Enter search term, or q to quit:  ");
            term = scanner.next();
        }
    }


    private static void outputIntroduction(File rootDir, DirectoryUtil dirUtil) {
        System.out.println("File Search 1.0");
        System.out.println("________________");
        System.out.println("Root dir:  " + rootDir);
        System.out.println("Total number of files: " + dirUtil.getFileList().length);
    }

    private static void executeSearch(DirectoryUtil directoryUtil, String term,
                                      int numWorkerThreads, boolean isWarmUp) {
        long startTime = System.nanoTime();
        ExecutorService executor = Executors.newFixedThreadPool(numWorkerThreads);
        List<String> results = new ArrayList<>();

        // Add Each File to the Thread Pool
        for (File file : directoryUtil.getFileList()) {
            Runnable printFileSearch = new FileSearch(file, term, results, isWarmUp);
            executor.execute(printFileSearch);
        }

        // Wait for all tasks to complete
        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        // Output all Matches
        if (!isWarmUp) {
            for (String result : results) {
                System.out.println(result);
            }
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds

        if (isWarmUp) {
            System.out.println("System is now warmed-up!");
        } else {
            System.out.println("Execution time: " + duration + " milliseconds");
        }
    }
}
