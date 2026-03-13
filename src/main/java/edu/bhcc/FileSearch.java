package edu.bhcc;

import java.io.File;
import java.util.List;

/**
 * A Runnable task that executes a file search on a single target file and
 * appends the results to a shared, thread-safe list.
 */
public class FileSearch implements Runnable {
    private File file;
    private String term;
    private List<String> sharedResults;
    private boolean isWarmUp;

    /**
     * Constructs a new FileSearch task.
     *
     * @param file          The file to be searched.
     * @param term          The search term to look for.
     * @param sharedResults The thread-safe list to store formatted matches.
     * @param isWarmUp      Flag indicating if the system is in warm-up mode.
     */
    public FileSearch(File file, String term, List<String> sharedResults, boolean isWarmUp) {
        this.file = file;
        this.term = term;
        this.sharedResults = sharedResults;
        this.isWarmUp = isWarmUp;
    }

    /**
     * Executes the search operation using FileSearchUtil and appends any matches
     * to the shared results list. If in warm-up mode, prints matches immediately.
     */
    @Override
    public void run() {
        List<String> fileMatches = FileSearchUtil.search(file, term, isWarmUp);
        for (String match : fileMatches) {
            if (isWarmUp) {
                System.out.println("Warming up -->  " + match);
            }
            sharedResults.add(match);
        }
    }
}