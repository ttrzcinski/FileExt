package org.ttrzcinski.fileext;

import org.jetbrains.annotations.NotNull;
import org.ttrzcinski.fileext.interfaces.IPath;
import org.ttrzcinski.fileext.utils.FileExt;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Represents a single known mounted point of directories structure.
 */
public class Mount implements IPath {

    /**
     * Kept path of the directory.
     */
    private Path keptPath;

    /**
     * Hidden constructor - there is no point to initialize an instance.
     */
    private Mount() { }

    /**
     * Creates new instance with given path.
     *
     * @param path given path
     */
    public Mount(@NotNull final String path) {
        this.keptPath = Path.of(path);
    }

    @Override
    public final Path getPath() {
        return this.keptPath;
    }

    @Override
    public final List<File> getFiles() {
        return FileExt.listFilesOf(this.keptPath);
    }

    @Override
    public final List<File> getFiles(final String filter) {
        return FileExt.listFilesOf(this.keptPath, filter);
    }

    @Override
    public final List<File> getSubdirectories() {
        return FileExt.listSubdirectoriesOf(this.keptPath);
    }

    @Override
    public final List<File> getSubdirectories(final String filter) {
        return FileExt.listSubdirectoriesOf(this.keptPath, filter);
    }

    @Override
    public final File create() {
        return FileExt.create(this.keptPath);
    }

    @Override
    public final File remove() {
        return FileExt.remove(this.keptPath);
    }

    @Override
    public final boolean exists() {
        return FileExt.exists(this.keptPath);
    }
}
