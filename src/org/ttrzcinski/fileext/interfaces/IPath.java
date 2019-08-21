package org.ttrzcinski.fileext.interfaces;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public interface IPath {

    Path getPath();

    List<File> getFiles();

    List<File> getFiles(String filter);

    List<File> getSubdirectories();

    List<File> getSubdirectories(String filter);

    File create();

    File remove();

    boolean exists();
}
