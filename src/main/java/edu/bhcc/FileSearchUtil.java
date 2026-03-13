package edu.bhcc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for performing case-insensitive searches of a term within a target file.
 * Handles reading the file line-by-line and extracting context snippets.
 */
public class FileSearchUtil {

    /**
     * Searches for a specific term within a file and formats the output based on the search mode.
     *
     * @param file     The target file to search.
     * @param term     The search term to look for.
     * @param isWarmUp Boolean flag indicating if this is a warm-up run (excludes snippets).
     * @return A list of formatted string matches.
     */
    public static List<String> search(File file, String term, boolean isWarmUp) {
        List<String> matches = new ArrayList<>();
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmedLine = line.trim();

                if (trimmedLine.toLowerCase().contains(term.toLowerCase())) {
                    if (isWarmUp) {
                        matches.add(file.getPath() + ", Line " + lineNumber);
                    } else {
                        String snippet = getSnippet(trimmedLine, term);
                        matches.add(file.getPath() + ", Line " + lineNumber + ": " + snippet + "...");
                    }
                }
            }
        } catch (IOException e) {
            // Silently skip unreadable files
        }
        return matches;
    }

    /**
     * Extracts a context snippet around the search term, capturing roughly
     * 6 words (spaces) before and after the term for context.
     *
     * @param line The full trimmed line containing the search term.
     * @param term The search term found in the line.
     * @return A formatted substring of the line surrounding the term.
     */
    private static String getSnippet(String line, String term) {
        String lowerLine = line.toLowerCase();
        String lowerTerm = term.toLowerCase();
        int termIndex = lowerLine.indexOf(lowerTerm);

        int start = termIndex;
        int spacesBack = 0;
        while (start > 0 && spacesBack < 6) {
            start--;
            if (line.charAt(start) == ' ') {
                spacesBack++;
            }
        }
        if (start > 0) start++;

        int end = termIndex + term.length();
        int spacesForward = 0;
        while (end < line.length() && spacesForward < 6) {
            if (line.charAt(end) == ' ') {
                spacesForward++;
            }
            end++;
        }

        return line.substring(start, end).trim();
    }
}