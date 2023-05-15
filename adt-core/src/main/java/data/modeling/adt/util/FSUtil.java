package data.modeling.adt.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.Normalizer;

public class FSUtil {
    public static void recursivelyForceDeleteDirectory(Path path) throws IOException {
        if (!Files.exists(path)) {
            return;
        }

        FileVisitor<Path> visitor =
                new SimpleFileVisitor<>() {

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                            throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        Files.delete(file);
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        if (exc != null) {
                            throw exc;
                        }
                        return FileVisitResult.CONTINUE;
                    }
                };
        Files.walkFileTree(path, visitor);
    }

    public static Path createFile(Path filePath) throws IOException {
        recursivelyForceDeleteDirectory(filePath);
        Files.createDirectories(filePath.getParent());
        return Files.createFile(filePath);
    }

    public static OutputStream createFileStream(Path filePath) throws IOException {
        recursivelyForceDeleteDirectory(filePath);
        Files.createDirectories(filePath.getParent());
        return Files.newOutputStream(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    private static String normalizeString(String input) {
        // Normalize the string to remove diacritical marks and convert to lowercase
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();

        // Remove any characters that are not alphanumeric, underscores, or spaces
        normalized = normalized.replaceAll("[^a-zA-Z0-9_\\s]", "");

        // Replace consecutive spaces with a single underscore
        normalized = normalized.replaceAll("\\s+", "_");

        return normalized;
    }

    public static String createFolderName(String input) {
        String normalized = normalizeString(input);
        // Remove any special characters that are not valid for folder names
        String folderName = normalized.replaceAll("[^a-zA-Z0-9_]", "");
        return folderName;
    }

    public static String createFileName(String input) {
        String normalized = normalizeString(input);
        // Remove any special characters that are not valid for file names
        String fileName = normalized.replaceAll("[^a-zA-Z0-9_.-]", "");
        return fileName;
    }
}
