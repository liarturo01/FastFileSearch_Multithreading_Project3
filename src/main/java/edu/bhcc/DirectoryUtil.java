package edu.bhcc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class responsible for identifying and collecting all files
 * within a specific directory and its subdirectories.
 */
public class DirectoryUtil {
    private final File rootDir;

    /**
     * Constructs a DirectoryUtil with the specified root directory.
     *
     * @param rootDir The root directory to start searching from.
     */
    public DirectoryUtil(File rootDir) {
        this.rootDir = rootDir;
    }

    /**
     * Recursively traverses directories to collect all file references.
     *
     * @param file     The current file or directory being evaluated.
     * @param fileList The cumulative list of files being built.
     */
    public synchronized void addFiles(File file, List<File> fileList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    addFiles(f, fileList);
                }
            }
        } else {
            fileList.add(file);
        }
    }

    /**
     * Retrieves a comprehensive list of all files found within the root directory
     * and any nested subdirectories.
     *
     * @return An array of File objects representing all files found.
     */
    public File[] getFileList() {
        List<File> fileList = new ArrayList<>();
        addFiles(rootDir, fileList);
        return fileList.toArray(new File[0]);
    }
}
