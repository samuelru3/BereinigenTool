package com.company;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class apacheCommons {
    public static void cleanDirectory(File directory) throws IOException {
        String message;
        if (!directory.exists()) {
            message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        } else if (!directory.isDirectory()) {
            message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        } else {
            File[] files = directory.listFiles();
            if (files == null) {
                throw new IOException("Failed to list contents of " + directory);
            } else {
                IOException exception = null;

                for (File file : files) {
                    try {
                        forceDelete(file);
                    } catch (IOException var6) {
                        exception = var6;
                    }
                }

                if (null != exception) {
                    throw exception;
                }
            }
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
        } else {
            if (!file.exists()) {
                throw new FileNotFoundException("File does not exist: " + file);
            }

            if (!file.delete()) {
                String message = "Unable to delete file: " + file;
                throw new IOException(message);
            }
        }

    }

    public static void deleteDirectory(File directory) throws IOException {
        if (directory.exists()) {
            cleanDirectory(directory);
            if (!directory.delete()) {
                String message = "Unable to delete directory " + directory + ".";
                throw new IOException(message);
            }
        }
    }

    public static void copyDirectory(File srcDir, File destDir) throws IOException {
        copyDirectory(srcDir, destDir, true);
    }

    public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) throws IOException {
        copyDirectory(srcDir, destDir, (FileFilter) null, preserveFileDate);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter) throws IOException {
        copyDirectory(srcDir, destDir, filter, true);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate) throws IOException {
        copyDirectory(srcDir, destDir, filter, preserveFileDate, StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter fileFilter, boolean preserveFileDate, CopyOption... copyOptions) throws IOException {
        requireFileCopy(srcDir, destDir);
        requireDirectory(srcDir, "srcDir");
        requireCanonicalPathsNotEquals(srcDir, destDir);
        List<String> exclusionList = null;
        String srcDirCanonicalPath = srcDir.getCanonicalPath();
        String destDirCanonicalPath = destDir.getCanonicalPath();
        if (destDirCanonicalPath.startsWith(srcDirCanonicalPath)) {
            File[] srcFiles = listFiles(srcDir, fileFilter);
            if (srcFiles.length > 0) {
                exclusionList = new ArrayList(srcFiles.length);
                File[] var9 = srcFiles;
                int var10 = srcFiles.length;

                for (int var11 = 0; var11 < var10; ++var11) {
                    File srcFile = var9[var11];
                    File copiedFile = new File(destDir, srcFile.getName());
                    exclusionList.add(copiedFile.getCanonicalPath());
                }
            }
        }

        doCopyDirectory(srcDir, destDir, fileFilter, exclusionList, preserveFileDate, preserveFileDate ? addCopyAttributes(copyOptions) : copyOptions);
    }

    private static CopyOption[] addCopyAttributes(CopyOption... copyOptions) {
        CopyOption[] actual = (CopyOption[]) Arrays.copyOf(copyOptions, copyOptions.length + 1);
        Arrays.sort(actual, 0, copyOptions.length);
        if (Arrays.binarySearch(copyOptions, 0, copyOptions.length, StandardCopyOption.COPY_ATTRIBUTES) >= 0) {
            return copyOptions;
        } else {
            actual[actual.length - 1] = StandardCopyOption.COPY_ATTRIBUTES;
            return actual;
        }
    }

    private static void requireFileCopy(File source, File destination) throws FileNotFoundException {
        requireExistsChecked(source, "source");
        Objects.requireNonNull(destination, "destination");
    }

    private static File requireDirectory(File directory, String name) {
        Objects.requireNonNull(directory, name);
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a directory: '" + directory + "'");
        } else {
            return directory;
        }
    }

    private static void requireCanonicalPathsNotEquals(File file1, File file2) throws IOException {
        String canonicalPath = file1.getCanonicalPath();
        if (canonicalPath.equals(file2.getCanonicalPath())) {
            throw new IllegalArgumentException(String.format("File canonical paths are equal: '%s' (file1='%s', file2='%s')", canonicalPath, file1, file2));
        }
    }

    private static File[] listFiles(File directory, FileFilter fileFilter) throws IOException {
        requireDirectoryExists(directory, "directory");
        File[] files = fileFilter == null ? directory.listFiles() : directory.listFiles(fileFilter);
        if (files == null) {
            throw new IOException("Unknown I/O error listing contents of directory: " + directory);
        } else {
            return files;
        }
    }

    private static void doCopyDirectory(File srcDir, File destDir, FileFilter fileFilter, List<String> exclusionList, boolean preserveDirDate, CopyOption... copyOptions) throws IOException {
        File[] srcFiles = listFiles(srcDir, fileFilter);
        requireDirectoryIfExists(destDir, "destDir");
        mkdirs(destDir);
        requireCanWrite(destDir, "destDir");
        File[] var7 = srcFiles;
        int var8 = srcFiles.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            File srcFile = var7[var9];
            File dstFile = new File(destDir, srcFile.getName());
            if (exclusionList == null || !exclusionList.contains(srcFile.getCanonicalPath())) {
                if (srcFile.isDirectory()) {
                    doCopyDirectory(srcFile, dstFile, fileFilter, exclusionList, preserveDirDate, copyOptions);
                } else {
                    copyFile(srcFile, dstFile, copyOptions);
                }
            }
        }
        if (preserveDirDate) {
            setLastModified(srcDir, destDir);
        }

    }

    private static void setLastModified(File sourceFile, File targetFile) throws IOException {
        Objects.requireNonNull(sourceFile, "sourceFile");
//        setLastModified(targetFile, lastModified(sourceFile));
    }

    public static long lastModified(File file) throws IOException {
        return Files.getLastModifiedTime((Path) Objects.requireNonNull(file.toPath(), "file")).toMillis();
    }

    public static void copyFile(File srcFile, File destFile, CopyOption... copyOptions) throws IOException {
        requireFileCopy(srcFile, destFile);
        requireFile(srcFile, "srcFile");
        requireCanonicalPathsNotEquals(srcFile, destFile);
        createParentDirectories(destFile);
        requireFileIfExists(destFile, "destFile");
        if (destFile.exists()) {
            requireCanWrite(destFile, "destFile");
        }

        Files.copy(srcFile.toPath(), destFile.toPath(), copyOptions);
        requireEqualSizes(srcFile, destFile, srcFile.length(), destFile.length());
    }

    private static void requireEqualSizes(File srcFile, File destFile, long srcLen, long dstLen) throws IOException {
        if (srcLen != dstLen) {
            throw new IOException("Failed to copy full contents from '" + srcFile + "' to '" + destFile + "' Expected length: " + srcLen + " Actual: " + dstLen);
        }
    }

    private static File requireFileIfExists(File file, String name) {
        Objects.requireNonNull(file, name);
        return file.exists() ? requireFile(file, name) : file;
    }

    public static File createParentDirectories(File file) throws IOException {
        return mkdirs(getParentFile(file));
    }

    private static File getParentFile(File file) {
        return file == null ? null : file.getParentFile();
    }

    private static File requireFile(File file, String name) {
        Objects.requireNonNull(file, name);
        if (!file.isFile()) {
            throw new IllegalArgumentException("Parameter '" + name + "' is not a file: " + file);
        } else {
            return file;
        }
    }

    private static File mkdirs(File directory) throws IOException {
        if (directory != null && !directory.mkdirs() && !directory.isDirectory()) {
            throw new IOException("Cannot create directory '" + directory + "'.");
        } else {
            return directory;
        }
    }

    private static File requireExistsChecked(File file, String fileParamName) throws FileNotFoundException {
        Objects.requireNonNull(file, fileParamName);
        if (!file.exists()) {
            throw new FileNotFoundException("File system element for parameter '" + fileParamName + "' does not exist: '" + file + "'");
        } else {
            return file;
        }
    }

    private static File requireDirectoryExists(File directory, String name) {
        requireExists(directory, name);
        requireDirectory(directory, name);
        return directory;
    }

    private static File requireExists(File file, String fileParamName) {
        Objects.requireNonNull(file, fileParamName);
        if (!file.exists()) {
            throw new IllegalArgumentException("File system element for parameter '" + fileParamName + "' does not exist: '" + file + "'");
        } else {
            return file;
        }
    }

    private static void requireCanWrite(File file, String name) {
        Objects.requireNonNull(file, "file");
        if (!file.canWrite()) {
            throw new IllegalArgumentException("File parameter '" + name + " is not writable: '" + file + "'");
        }
    }

    private static File requireDirectoryIfExists(File directory, String name) {
        Objects.requireNonNull(directory, name);
        if (directory.exists()) {
            requireDirectory(directory, name);
        }

        return directory;
    }

}
