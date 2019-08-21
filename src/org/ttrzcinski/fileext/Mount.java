package org.ttrzcinski.fileext;

import org.jetbrains.annotations.NotNull;
import org.ttrzcinski.fileext.interfaces.IPath;
import org.ttrzcinski.fileext.utils.FileExt;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

public class Mount implements IPath {

    private Path path;

    private Mount() { }

    public Mount(@NotNull String path) {
        this.path = Path.of(path);
    }

    @Override
    public Path getPath() {
        return this.path;
    }

    @Override
    public List<File> getFiles() {
        return FileExt.listFilesOf(this.path);
    }

    @Override
    public List<File> getFiles(final String filter) {
        return FileExt.listFilesOf(this.path, filter);
    }

    @Override
    public List<File> getSubdirectories() {
        return FileExt.listSubdirectoriesOf(this.path);
    }

    @Override
    public List<File> getSubdirectories(String filter) {
        return FileExt.listSubdirectoriesOf(this.path, filter);
    }

    @Override
    public File create() {
        return FileExt.create(this.path);
    }

    @Override
    public File remove() {
        return FileExt.remove(this.path);
    }

    @Override
    public boolean exists() {
        return FileExt.exists(this.path);
    }
}
