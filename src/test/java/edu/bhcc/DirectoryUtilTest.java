package edu.bhcc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DirectoryUtilTest {

    @TempDir
    Path tempDir;

    @Test
    public void testGetFileList_Recursion() throws IOException {
        // --- 1. SETUP: Create a dummy directory structure ---
        // Root Level
        Files.createFile(tempDir.resolve("file1.txt"));

        // Sub-folder Level 1
        Path subDir = Files.createDirectory(tempDir.resolve("subfolder"));
        Files.createFile(subDir.resolve("file2.txt"));

        // Sub-folder Level 2 (Nested)
        Path deepDir = Files.createDirectory(subDir.resolve("deepfolder"));
        Files.createFile(deepDir.resolve("file3.txt"));

        // --- 2. EXECUTION: Run your utility ---
        DirectoryUtil util = new DirectoryUtil(tempDir.toFile());
        File[] files = util.getFileList();

        // --- 3. ASSERTION: Verify the results ---
        // We created exactly 3 files, so the array should be size 3
        assertEquals(3, files.length, "DirectoryUtil should find all files, including those in nested subdirectories.");
    }
}