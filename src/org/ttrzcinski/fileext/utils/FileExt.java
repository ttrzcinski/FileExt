package org.ttrzcinski.fileext.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileExt {

    public static List<File> listFilesOf(Path path) {
        List<File> files = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path dirPath : directoryStream) {
                files.add(new File(dirPath.toString()));
            }
        } catch (IOException ex) {}
        return files;
    }

    public static List<File> listSubdirectoriesOf(Path path) {
        List<File> subdirectories = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path dirPath : directoryStream) {
                var file = new File(dirPath.toString());
                if (file.isDirectory()) subdirectories.add(file);
            }
        } catch (IOException ex) {}
        return subdirectories;
    }

    public static List<File> listOnlyFilesOf(Path path) {
        List<File> onlyFiles = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path dirPath : directoryStream) {
                var file = new File(dirPath.toString());
                if (file.isFile()) onlyFiles.add(file);
            }
        } catch (IOException ex) {}
        return onlyFiles;
    }

    public static List<File> listFilesOf(final Path path, final String filter) {
        if (!ParamCheck.isSet(filter)) return listFilesOf(path);
        String simpleFilter = StringFix.simple(filter);

        List<File> results;

        if (simpleFilter.equals("*.*")) {
            // no filter at all
            return listFilesOf(path);
        } else if (simpleFilter.startsWith("*.") && simpleFilter.length() > 2) {
            // filter by extension
            String ending = simpleFilter.substring(2);
            results = listFilesOf(path).stream()
                    .filter(file -> file.getName().endsWith(ending)).collect(Collectors.toList());
        } else if (simpleFilter.endsWith(".*") && simpleFilter.length() > 2) {
            // filter by name starting
            String beginning = simpleFilter.substring(0, simpleFilter.length()-2);
            results = listFilesOf(path).stream()
                    .filter(file -> file.getName().startsWith(beginning)).collect(Collectors.toList());
        } else {
            // contains
            results = listFilesOf(path).stream()
                    .filter(file -> file.getName().contains(simpleFilter)).collect(Collectors.toList());
        }
        return results;
    }

    public static List<File> listSubdirectoriesOf(final Path path, final String filter) {
        if (!ParamCheck.isSet(filter)) return listSubdirectoriesOf(path);
        String simpleFilter = StringFix.simple(filter);

        List<File> results;

        if (simpleFilter.equals("*.*")) {
            // no filter at all
            return listSubdirectoriesOf(path);
        } else if (simpleFilter.startsWith("*.") && simpleFilter.length() > 2) {
            // filter by extension
            String ending = simpleFilter.substring(2);
            results = listSubdirectoriesOf(path).stream()
                    .filter(file -> file.getName().endsWith(ending)).collect(Collectors.toList());
        } else if (simpleFilter.endsWith(".*") && simpleFilter.length() > 2) {
            // filter by name starting
            String beginning = simpleFilter.substring(0, simpleFilter.length()-2);
            results = listSubdirectoriesOf(path).stream()
                    .filter(file -> file.getName().startsWith(beginning)).collect(Collectors.toList());
        } else {
            // contains
            results = listSubdirectoriesOf(path).stream()
                    .filter(file -> file.getName().contains(simpleFilter)).collect(Collectors.toList());
        }
        return results;
    }

    public static File create(Path path) {
        File file = new File(path.toString());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                return null;
            }
        }
        return file;
    }

    public static boolean exists(Path path) {
        return new File(path.toString()).exists();
    }

    public static boolean exists(File file) {
        return file.exists();
    }

    public static File remove(Path path) {
        File file = new File(path.toString());
        return remove(file);
    }

    public static File remove(File file) {
        if (!file.exists()) {
            file.delete();
            return file;
        }
        return null;
    }
}
