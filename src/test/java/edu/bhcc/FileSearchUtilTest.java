package edu.bhcc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSearchUtilTest {

    @TempDir
    Path tempDir;

    @Test
    public void testSearch_LogicAndFormatting() throws IOException {
        // --- 1. SETUP: Create a test file with specific content ---
        File testFile = tempDir.resolve("testdoc.md").toFile();
        String fileContent =
                "This first line has no matches.\n" +
                        "Here is the word fantasy in the middle of a sentence.\n" +
                        "And here is FANTASY in all caps to test case insensitivity.";
        Files.writeString(testFile.toPath(), fileContent);

        // --- 2. TEST WARM-UP MODE ---
        List<String> warmUpResults = FileSearchUtil.search(testFile, "fantasy", true);

        assertEquals(2, warmUpResults.size(), "Should find 2 matches regardless of case.");

        // Check Warm-up formatting (Should NOT have a colon or snippet)
        String firstWarmUpMatch = warmUpResults.get(0);
        assertTrue(firstWarmUpMatch.contains("Line 2"), "Should identify the correct line number.");
        assertFalse(firstWarmUpMatch.contains(":"), "Warm-up output should not contain a colon.");
        assertFalse(firstWarmUpMatch.contains("word"), "Warm-up output should not contain line text.");

        // --- 3. TEST NORMAL SEARCH MODE ---
        List<String> searchResults = FileSearchUtil.search(testFile, "fantasy", false);

        assertEquals(2, searchResults.size(), "Should still find 2 matches.");

        // Check Normal formatting (Should have a colon, snippet, and ellipsis)
        String firstSearchMatch = searchResults.get(0);
        String secondSearchMatch = searchResults.get(1);

        assertTrue(firstSearchMatch.contains("Line 2:"), "Normal output should contain Line X: format.");
        assertTrue(firstSearchMatch.contains("..."), "Normal output should end with ellipsis.");

        // Verify case insensitivity worked on the second match
        assertTrue(secondSearchMatch.contains("Line 3:"), "Should find the uppercase FANTASY on line 3.");
        assertTrue(secondSearchMatch.toLowerCase().contains("fantasy"), "Snippet should contain the target word.");
    }
}